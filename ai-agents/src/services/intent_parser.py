"""
Intent Parser Service
Converts natural language queries to structured UserIntent JSON
Uses OpenAI GPT-4 with strict output formatting
"""

import os
import json
import logging
from typing import Dict, Any
from langchain_openai import ChatOpenAI
from langchain_core.prompts import ChatPromptTemplate
from langchain_core.output_parsers import PydanticOutputParser

from ..models.schemas import (
    UserIntent,
    IntentExtractionResponse,
    ConfidenceLevel,
    Budget,
    PropertyType
)

logger = logging.getLogger(__name__)


class IntentParser:
    """
    Parses natural language property queries into structured intent
    """
    
    def __init__(self):
        self.llm = ChatOpenAI(
            model=os.getenv("OPENAI_MODEL", "gpt-4o-mini"),
            temperature=float(os.getenv("OPENAI_TEMPERATURE", "0.1")),
            max_tokens=int(os.getenv("OPENAI_MAX_TOKENS", "2000")),
            api_key=os.getenv("OPENAI_API_KEY"),
            base_url=os.getenv("OPENAI_API_BASE")
        )
        
        self.parser = PydanticOutputParser(pydantic_object=UserIntent)
        
        # Define system prompt with variable placeholder
        system_template = """You are an expert real estate intent extraction system for Bangalore, India.

Your task: Convert natural language property search queries into STRICT JSON format.

MANDATORY RULES:
1. Output ONLY valid JSON matching the UserIntent schema
2. NO Markdown formatting (do not use ```json ... ```)
3. NO Explanations or conversational text
4. Use Indian number format (crore = 10,000,000, lakh = 100,000)
5. Extract ALL relevant information from the query
4. Set confidence_level based on query clarity:
   - HIGH: Specific budget, location, property type mentioned
   - MEDIUM: Some details missing
   - LOW: Vague query needing clarification

OUTPUT FORMAT:
{format_instructions}

BANGALORE LOCATION MAPPING:
- "Sarjapur" -> ["Sarjapur", "Sarjapur Road", "Carmelaram"]
- "Whitefield" -> ["Whitefield", "Varthur", "Harlur"]  
- "Koramangala" -> ["Koramangala"]
- "Indiranagar" -> ["Indiranagar"]
- "Electronic City" -> ["Electronic City"]
- "HSR Layout" -> ["HSR Layout", "HSR"]
- "Hebbal" -> ["Hebbal", "Manyata"]
- "Yelahanka" -> ["Yelahanka", "Devanahalli"]
- "JP Nagar" -> ["JP Nagar", "J.P. Nagar"]
- "Bannerghatta" -> ["Bannerghatta Road"]

BUDGET EXTRACTION:
- "under 1.5 crore" -> max: 15000000
- "around 50 lakhs" -> min: 4500000, max: 5500000
- "1-2 crore" -> min: 10000000, max: 20000000
- "budget 80L" -> max: 8000000

PROPERTY TYPE MAPPING:
- "flat", "apartment", "3 BHK" -> APARTMENT
- "villa", "row house" -> VILLA
- "independent house" -> INDEPENDENT_HOUSE  
- "plot", "land" -> PLOT
- "office", "commercial" -> COMMERCIAL
- "penthouse" -> PENTHOUSE
- "studio" -> STUDIO

AMENITIES:
- "good schools" -> must_have: ["schools nearby"]
- "parking" -> must_have: ["parking"]
- "gym" -> nice_to_have: ["gym"]
- "pool" -> nice_to_have: ["swimming pool"]
- "security" -> must_have: ["security"]

FAMILY CONTEXT:
- "with kids", "children", "good schools" -> kids: true, school_priority: true
- "parents", "senior citizens" -> senior_citizens: true

INVESTMENT GOAL:
- "investment", "ROI", "returns" -> APPRECIATION or RENTAL_YIELD
- "rental", "rent out" -> RENTAL_YIELD  
- "live in", "end use", "family" -> END_USE
"""
        
        self.prompt = ChatPromptTemplate.from_messages([
            ("system", system_template),
            ("user", "{query}")
        ])

    async def parse(self, query: str) -> IntentExtractionResponse:
        """
        Extract intent from natural language query
        """
        try:
            logger.info(f"Parsing query: {query}")
            
            # Create chain with properly injected format instructions
            # formatting happens at invocation time to avoid template issues
            chain = self.prompt | self.llm | self.parser
            
            # Execute with partial variables
            intent: UserIntent = await chain.ainvoke({
                "query": query,
                "format_instructions": self.parser.get_format_instructions()
            })
            
            # Determine clarifications needed
            clarifications = self._get_clarifications(intent)
            
            # Determine confidence
            confidence = self._calculate_confidence(intent, clarifications)
            intent.confidence_level = confidence
            
            logger.info(f"Intent parsed with confidence: {confidence}")
            
            return IntentExtractionResponse(
                intent=intent,
                confidence=confidence.value,
                clarifications_needed=clarifications
            )
            
        except Exception as e:
            logger.error(f"Failed to parse intent: {e}", exc_info=True)
            
            # Use strict regex fallback to extract value from query locally
            return self._extract_fallback_intent(query)
            
    def _extract_fallback_intent(self, query: str) -> IntentExtractionResponse:
        """Extract intent using regex patterns when LLM fails"""
        import re
        
        query_lower = query.lower()
        intent = UserIntent()
        intent.confidence_level = ConfidenceLevel.LOW
        
        # 0. Extract Transaction Type
        if re.search(r'\b(rent|lease)\b', query_lower):
            from ..models.schemas import TransactionType
            intent.transaction_type = TransactionType.RENT
        else:
            from ..models.schemas import TransactionType
            intent.transaction_type = TransactionType.BUY

        # 1. Extract Budget
        # Matches: "1.5 cr", "1.5 crore", "80 lakhs", "80 l", "under 1 cr"
        crore_match = re.search(r'(\d+(?:\.\d+)?)\s*(?:cr|crore|c)', query_lower)
        lakh_match = re.search(r'(\d+(?:\.\d+)?)\s*(?:l|lakh|lac)', query_lower)
        
        if crore_match:
            val = float(crore_match.group(1)) * 10000000
            if "under" in query_lower or "budget" in query_lower:
                intent.budget = Budget(max=val)
            else:
                # Assume around range (+- 30%)
                intent.budget = Budget(min=val*0.7, max=val*1.3)
                
        elif lakh_match:
            val = float(lakh_match.group(1)) * 100000
            if "under" in query_lower or "budget" in query_lower:
                intent.budget = Budget(max=val)
            else:
                intent.budget = Budget(min=val*0.7, max=val*1.3)

        # 2. Extract Location
        # List of known Bangalore locations to search for
        locations = [
            "sarjapur", "whitefield", "koramangala", "indiranagar", 
            "electronic city", "hsr layout", "hebbal", "yelahanka", 
            "jp nagar", "bannerghatta", "marathahalli", "bellandur"
        ]
        
        found_locations = []
        for loc in locations:
            if loc in query_lower:
                # Map to proper case or canonical name if needed
                if loc == "sarjapur": found_locations.extend(["Sarjapur", "Sarjapur Road"])
                elif loc == "whitefield": found_locations.append("Whitefield")
                elif loc == "koramangala": found_locations.append("Koramangala")
                elif loc == "indiranagar": found_locations.append("Indiranagar")
                elif loc == "electronic city": found_locations.append("Electronic City")
                elif loc == "hsr layout": found_locations.append("HSR Layout")
                elif loc == "hebbal": found_locations.append("Hebbal")
                elif loc == "yelahanka": found_locations.append("Yelahanka")
                else: found_locations.append(loc.title())
                
        if found_locations:
            intent.preferred_locations = list(set(found_locations))

        # 3. Extract Property Type
        if "villa" in query_lower:
            intent.property_type = PropertyType.VILLA
        elif "plot" in query_lower or "land" in query_lower:
            intent.property_type = PropertyType.PLOT
        elif "apartment" in query_lower or "flat" in query_lower or "bhk" in query_lower:
            intent.property_type = PropertyType.APARTMENT
            
        # 4. Extract Bedrooms
        # This is not in UserIntent main fields but helps confidence if present
        
        # Calculate confidence
        clarifications = self._get_clarifications(intent)
        intent.confidence_level = self._calculate_confidence(intent, clarifications)
        
        logger.info(f"Fallback extraction results: {intent}")
        
        return IntentExtractionResponse(
            intent=intent,
            confidence=intent.confidence_level.value,
            clarifications_needed=clarifications
        )
    
    def _get_clarifications(self, intent: UserIntent) -> list[str]:
        """Determine what information is missing"""
        clarifications = []
        
        if intent.transaction_type is None:
            clarifications.append("Are you looking to buy or rent?")
            
        if intent.budget is None or (intent.budget.max is None and intent.budget.min is None):
            clarifications.append("What is your budget range?")
            
        if not intent.preferred_locations:
            clarifications.append("Which areas in Bangalore are you considering?")
            
        if intent.property_type is None:
            clarifications.append("What type of property (apartment, villa, etc.)?")
            
        return clarifications
    
    def _calculate_confidence(
        self, 
        intent: UserIntent, 
        clarifications: list[str]
    ) -> ConfidenceLevel:
        """Calculate confidence based on completeness"""
        
        # Count how many key fields are filled
        filled_count = 0
        total_key_fields = 4
        
        if intent.transaction_type is not None:
            filled_count += 1
        if intent.budget is not None and (intent.budget.max or intent.budget.min):
            filled_count += 1
        if intent.preferred_locations:
            filled_count += 1
        if intent.property_type is not None:
            filled_count += 1
            
        completion_ratio = filled_count / total_key_fields
        
        if completion_ratio >= 0.75:
            return ConfidenceLevel.HIGH
        elif completion_ratio >= 0.5:
            return ConfidenceLevel.MEDIUM
        else:
            return ConfidenceLevel.LOW


# Singleton instance
_intent_parser: IntentParser | None = None


def get_intent_parser() -> IntentParser:
    """Get or create intent parser instance"""
    global _intent_parser
    if _intent_parser is None:
        _intent_parser = IntentParser()
    return _intent_parser
