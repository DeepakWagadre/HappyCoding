"""
Location Intelligence Agent
Analyzes neighborhood quality, infrastructure, and development potential
"""

import logging
from typing import Dict, Any
from ..models.schemas import Property, UserIntent, ConfidenceLevel

logger = logging.getLogger(__name__)


class LocationAgent:
    """
    Evaluates properties based on location quality
    
    Factors:
    - Infrastructure development
    - Proximity to amenities
    - Neighborhood quality
    - Future growth potential
    """
    
    def analyze(self, property: Property, intent: UserIntent) -> Dict[str, Any]:
        """
        Analyze location quality
        
        Returns:
            {
                "location_score": float (0-10),
                "reasons": List[str],
                "risks": List[str]
            }
        """
        score = 5.0  # Base score
        reasons = []
        risks = []
        
        try:
            # Check if property is in preferred locations
            if intent.preferred_locations:
                if property.location in intent.preferred_locations or \
                   property.subLocation in intent.preferred_locations:
                    score += 2.0
                    reasons.append(f"Located in preferred area: {property.location}")
                else:
                    score -= 1.0
            
            # Metro connectivity bonus
            if property.nearestMetroDistanceKm is not None:
                if property.nearestMetroDistanceKm <= 1:
                    score += 1.5
                    reasons.append(f"Excellent metro connectivity ({property.nearestMetroStation})")
                elif property.nearestMetroDistanceKm <= 3:
                    score += 0.8
                    reasons.append(f"Good metro access ({property.nearestMetroDistanceKm}km from {property.nearestMetroStation})")
                elif property.nearestMetroDistanceKm > 10:
                    score -= 0.5
                    risks.append(f"Limited metro connectivity ({property.nearestMetroDistanceKm}km)")
            
            # Airport proximity (for investment)
            if intent.investment_goal in ["RENTAL_YIELD", "APPRECIATION"]:
                if property.nearestAirportDistanceKm is not None and property.nearestAirportDistanceKm <= 15:
                    score += 0.5
                    reasons.append("Near airport - good for rental demand")
            
            # Nearby places evaluation
            if property.nearbyPlaces and len(property.nearbyPlaces) > 0:
                nearby_set = set(place.lower() for place in property.nearbyPlaces)
                
                # Schools (important for families)
                if intent.family_context and intent.family_context.school_priority:
                    if any("school" in place for place in nearby_set):
                        score += 1.0
                        reasons.append("Quality schools in vicinity")
                    else:
                        score -= 0.5
                        risks.append("No prominent schools nearby")
                
                # Hospitals
                if any("hospital" in place for place in nearby_set):
                    score += 0.5
                    reasons.append("Healthcare facilities nearby")
                
                # Shopping/Entertainment
                if any(kw in " ".join(nearby_set) for kw in ["mall", "market", "shopping"]):
                    score += 0.3
            
            # Premium locations in Bangalore
            premium_areas = ["indiranagar", "koramangala", "whitefield", "sarjapur"]
            if property.location.lower() in premium_areas:
                score += 0.5
                reasons.append(f"{property.location} is a well-established area")
            
            # Emerging areas with growth potential
            emerging_areas = ["yelahanka", "electronic city", "hebbal"]
            if property.location.lower() in emerging_areas:
                score += 0.7
                reasons.append(f"{property.location} has strong growth potential")
            
            # Cap score between 0-10
            score = max(0, min(10, score))
            
            logger.info(f"Location score for {property.propertyId}: {score}")
            
            return {
                "location_score": round(score, 2),
                "reasons": reasons,
                "risks": risks
            }
            
        except Exception as e:
            logger.error(f"Location analysis failed: {e}", exc_info=True)
            return {
                "location_score": 5.0,
                "reasons": ["Unable to fully analyze location"],
                "risks": []
            }
