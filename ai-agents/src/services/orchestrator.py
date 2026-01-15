"""
Multi-Agent Orchestrator
Coordinates all agents in parallel for property analysis
"""

import logging
import asyncio
from typing import List
from ..models.schemas import (
    Property,
    UserIntent,
    PropertyScore,
    PropertyAnalysisResult,
    AgentAnalysisResponse,
    ConfidenceLevel
)
from ..agents.location_agent import LocationAgent
from ..agents.price_agent import PriceAgent
from ..agents.commute_agent import CommuteAgent
from ..agents.legal_agent import LegalAgent
from ..agents.yield_agent import YieldAgent
from ..agents.livability_agent import LivabilityAgent

logger = logging.getLogger(__name__)


class AgentOrchestrator:
    """
    Orchestrates multi-agent property analysis
    All agents run in parallel for efficiency
    """
    
    def __init__(self):
        self.location_agent = LocationAgent()
        self.price_agent = PriceAgent()
        self.commute_agent = CommuteAgent()
        self.legal_agent = LegalAgent()
        self.yield_agent = YieldAgent()
        self.livability_agent = LivabilityAgent()
    
    async def analyze_properties(
        self, 
        intent: UserIntent, 
        properties: List[Property]
    ) -> AgentAnalysisResponse:
        """
        Analyze all properties using multi-agent system
        
        Args:
            intent: User's structured intent
            properties: List of candidate properties
            
        Returns:
            AgentAnalysisResponse with scored and ranked properties
        """
        logger.info(f"Analyzing {len(properties)} properties with multi-agent system")
        
        try:
            # Analyze all properties concurrently
            analysis_tasks = [
                self._analyze_single_property(prop, intent)
                for prop in properties
            ]
            
            results = await asyncio.gather(*analysis_tasks)
            
            # Filter out None results
            valid_results = [r for r in results if r is not None]
            
            # Sort by final score
            valid_results.sort(key=lambda x: x.scores.final_score, reverse=True)
            
            # Generate insights
            assumptions = self._generate_assumptions(intent)
            follow_up_questions = self._generate_follow_up_questions(intent)
            
            logger.info(f"Analysis completed. {len(valid_results)} properties scored.")
            
            return AgentAnalysisResponse(
                results=valid_results,
                assumptions=assumptions,
                areas_to_avoid=[],  # Could be enhanced
                follow_up_questions=follow_up_questions
            )
            
        except Exception as e:
            logger.error(f"Orchestration failed: {e}", exc_info=True)
            return AgentAnalysisResponse(
                results=[],
                assumptions=["Analysis encountered errors"],
                areas_to_avoid=[],
                follow_up_questions=[]
            )
    
    async def _analyze_single_property(
        self, 
        property: Property, 
        intent: UserIntent
    ) -> PropertyAnalysisResult | None:
        """Analyze a single property with all agents"""
        try:
            # Run all agents in parallel
            location_result, price_result, commute_result, legal_result, yield_result, livability_result = await asyncio.gather(
                asyncio.to_thread(self.location_agent.analyze, property, intent),
                asyncio.to_thread(self.price_agent.analyze, property, intent),
                asyncio.to_thread(self.commute_agent.analyze, property, intent),
                asyncio.to_thread(self.legal_agent.analyze, property, intent),
                asyncio.to_thread(self.yield_agent.analyze, property, intent),
                asyncio.to_thread(self.livability_agent.analyze, property, intent)
            )
            
            # Build scores
            scores = PropertyScore(
                location_score=location_result["location_score"],
                price_fairness_score=price_result["price_fairness_score"],
                commute_score=commute_result["commute_score"],
                risk_penalty=legal_result["risk_penalty"],
                growth_potential_score=yield_result["growth_potential_score"],
                amenities_match_score=livability_result["amenities_match_score"],
                livability_score=livability_result["livability_score"],
                confidence_level=intent.confidence_level
            )
            
            # Calculate final score with weights
            final_score = self._calculate_weighted_score(scores, intent)
            scores.final_score = final_score
            
            # Collect recommendations and risks
            why_recommended = []
            key_risks = []
            
            for result in [location_result, price_result, commute_result, legal_result, yield_result, livability_result]:
                why_recommended.extend(result["reasons"][:2])  # Top 2 reasons per agent
                key_risks.extend(result["risks"][:2])  # Top 2 risks per agent
            
            # Keep only top reasons/risks
            why_recommended = why_recommended[:5]
            key_risks = key_risks[:3]
            
            return PropertyAnalysisResult(
                property_id=property.propertyId,
                scores=scores,
                why_recommended=why_recommended if why_recommended else ["Meets basic criteria"],
                key_risks=key_risks if key_risks else []
            )
            
        except Exception as e:
            logger.error(f"Failed to analyze property {property.propertyId}: {e}")
            return None
    
    def _calculate_weighted_score(self, scores: PropertyScore, intent: UserIntent) -> float:
        """Calculate weighted final score based on intent"""
        
        # Determine weights based on investment goal
        if intent.investment_goal == "END_USE":
            weights = {
                "location": 0.20,
                "price": 0.15,
                "amenities": 0.25,
                "commute": 0.25,
                "growth": 0.05,
                "livability": 0.10
            }
        elif intent.investment_goal == "RENTAL_YIELD":
            weights = {
                "location": 0.20,
                "price": 0.25,
                "amenities": 0.15,
                "commute": 0.15,
                "growth": 0.20,
                "livability": 0.05
            }
        elif intent.investment_goal in ["APPRECIATION", "MIXED"]:
            weights = {
                "location": 0.25,
                "price": 0.30,
                "amenities": 0.05,
                "commute": 0.05,
                "growth": 0.30,
                "livability": 0.05
            }
        else:
            # Default: balanced
            weights = {
                "location": 0.20,
                "price": 0.20,
                "amenities": 0.15,
                "commute": 0.15,
                "growth": 0.15,
                "livability": 0.15
            }
        
        final = (
            scores.location_score * weights["location"] +
            scores.price_fairness_score * weights["price"] +
            scores.amenities_match_score * weights["amenities"] +
            scores.commute_score * weights["commute"] +
            scores.growth_potential_score * weights["growth"] +
            scores.livability_score * weights["livability"] +
            scores.risk_penalty
        )
        
        return max(0, min(10, round(final, 2)))
    
    def _generate_assumptions(self, intent: UserIntent) -> List[str]:
        """Generate assumptions based on missing intent data"""
        assumptions = []
        
        if not intent.preferred_locations:
            assumptions.append("Searching across all Bangalore locations")
        
        if not intent.budget or not intent.budget.max:
            assumptions.append("Considering properties across all price ranges")
        
        if intent.investment_goal == "END_USE" and intent.family_context:
            if intent.family_context.kids:
                assumptions.append("Prioritizing family-friendly neighborhoods with schools")
        
        if intent.investment_goal in ["RENTAL_YIELD", "APPRECIATION"]:
            assumptions.append("Focusing on investment potential and ROI")
        
        return assumptions
    
    def _generate_follow_up_questions(self, intent: UserIntent) -> List[str]:
        """Generate helpful follow-up questions"""
        questions = []
        
        if not intent.commute or not intent.commute.office_location:
            questions.append("Where is your office located for commute planning?")
        
        if intent.property_type == "APARTMENT" and not intent.amenities:
            questions.append("Any specific amenities you're looking for (gym, pool, clubhouse)?")
        
        if intent.budget and not intent.budget.min:
            questions.append("What's your minimum budget to narrow down options?")
        
        if not intent.family_context or intent.family_context.kids is None:
            questions.append("Is proximity to good schools important?")
        
        return questions[:3]  # Max 3 questions


# Singleton instance
_orchestrator: AgentOrchestrator | None = None


def get_orchestrator() -> AgentOrchestrator:
    """Get or create orchestrator instance"""
    global _orchestrator
    if _orchestrator is None:
        _orchestrator = AgentOrchestrator()
    return _orchestrator
