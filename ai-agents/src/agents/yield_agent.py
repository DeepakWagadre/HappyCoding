"""
Yield & Appreciation Agent
Evaluates investment potential
"""

import logging
from typing import Dict, Any
from ..models.schemas import Property, UserIntent

logger = logging.getLogger(__name__)


class YieldAgent:
    """
    Evaluates rental yield and appreciation potential
    """
    
    def analyze(self, property: Property, intent: UserIntent) -> Dict[str, Any]:
        score = 5.0
        reasons = []
        risks = []
        
        try:
            # Expected appreciation
            if property.expectedAppreciation:
                if property.expectedAppreciation >= 10:
                    score += 2.0
                    reasons.append(f"High growth potential: {property.expectedAppreciation}% per year")
                elif property.expectedAppreciation >= 7:
                    score += 1.0
                    reasons.append(f"Good appreciation expected: {property.expectedAppreciation}%/year")
                elif property.expectedAppreciation < 5:
                    score -= 0.5
                    risks.append(f"Low appreciation potential: {property.expectedAppreciation}%/year")
            
            # Rental yield
            if property.rentalYield:
                if property.rentalYield >= 4:
                    score += 1.5
                    reasons.append(f"Excellent rental yield: {property.rentalYield}%")
                elif property.rentalYield >= 3:
                    score += 0.8
                    reasons.append(f"Good rental yield: {property.rentalYield}%")
                elif property.rentalYield < 2.5:
                    score -= 0.5
                    risks.append(f"Low rental yield: {property.rentalYield}%")
            
            # Location-based growth potential
            high_growth_areas = ["yelahanka", "electronic city", "hebbal", "sarjapur"]
            if property.location.lower() in high_growth_areas:
                score += 1.0
                reasons.append(f"{property.location} - emerging high-growth area")
            
            # IT corridor proximity (Whitefield, Sarjapur, Electronic City)
            it_corridors = ["whitefield", "sarjapur", "electronic city"]
            if property.location.lower() in it_corridors:
                score += 0.8
                reasons.append("IT corridor - strong rental demand")
            
            score = max(0, min(10, score))
            
            return {
                "growth_potential_score": round(score, 2),
                "reasons": reasons,
                "risks": risks
            }
            
        except Exception as e:
            logger.error(f"Yield analysis failed: {e}")
            return {"growth_potential_score": 5.0, "reasons": [], "risks": []}
