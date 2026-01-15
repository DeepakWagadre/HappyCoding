"""
Livability & Family Suitability Agent
Evaluates lifestyle fit and amenities
"""

import logging
from typing import Dict, Any
from ..models.schemas import Property, UserIntent

logger = logging.getLogger(__name__)


class LivabilityAgent:
    """
    Evaluates lifestyle fit and amenity matching
    """
    
    def analyze(self, property: Property, intent: UserIntent) -> Dict[str, Any]:
        score = 5.0
        reasons = []
        risks = []
        
        try:
            amenity_score = 0
            
            # Check must-have amenities
            if intent.amenities and intent.amenities.must_have:
                property_amenities_lower = [a.lower() for a in property.amenities]
                
                matched_must_have = 0
                for required in intent.amenities.must_have:
                    if any(required.lower() in am for am in property_amenities_lower):
                        matched_must_have += 1
                
                match_rate = matched_must_have / len(intent.amenities.must_have)
                if match_rate >= 0.8:
                    amenity_score += 2.0
                    reasons.append("Has all essential amenities")
                elif match_rate >= 0.5:
                    amenity_score += 1.0
                    reasons.append("Has most required amenities")
                else:
                    amenity_score -= 1.0
                    risks.append("Missing key amenities")
            
            # Nice-to-have amenities bonus
            if intent.amenities and intent.amenities.nice_to_have:
                property_amenities_lower = [a.lower() for a in property.amenities]
                nice_matched = sum(
                    1 for nice in intent.amenities.nice_to_have
                    if any(nice.lower() in am for am in property_amenities_lower)
                )
                amenity_score += nice_matched * 0.3
            
            # Family context
            if intent.family_context:
                # Schools for kids
                if intent.family_context.kids or intent.family_context.school_priority:
                    has_schools_nearby = any(
                        "school" in place.lower() 
                        for place in property.nearbyPlaces
                    )
                    if has_schools_nearby:
                        amenity_score += 1.5
                        reasons.append("Good schools nearby - family-friendly")
                    else:
                        amenity_score -= 0.8
                        risks.append("No quality schools in vicinity")
                
                # Ground floor / elevator for senior citizens
                if intent.family_context.senior_citizens:
                    if "elevator" in [a.lower() for a in property.amenities]:
                        amenity_score += 0.5
                        reasons.append("Elevator available - senior-friendly")
            
            # General amenities count
            if len(property.amenities) >= 10:
                amenity_score += 0.8
                reasons.append(f"Excellent amenities ({len(property.amenities)} facilities)")
            elif len(property.amenities) >= 5:
                amenity_score += 0.4
            
            # Healthcare proximity
            has_hospitals = any("hospital" in place.lower() for place in property.nearbyPlaces)
            if has_hospitals:
                amenity_score += 0.5
                reasons.append("Healthcare facilities nearby")
            
            score += amenity_score
            score = max(0, min(10, score))
            
            return {
                "amenities_match_score": round(score, 2),
                "livability_score": round(score, 2),  # Same for simplicity
                "reasons": reasons,
                "risks": risks
            }
            
        except Exception as e:
            logger.error(f"Livability analysis failed: {e}")
            return {"amenities_match_score": 5.0, "livability_score": 5.0, "reasons": [], "risks": []}
