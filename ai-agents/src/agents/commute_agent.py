"""
Commute & Connectivity Agent
Analyzes travel time and transport connectivity
"""

import logging
from typing import Dict, Any
from ..models.schemas import Property, UserIntent

logger = logging.getLogger(__name__)


class CommuteAgent:
    """
    Evaluates commute convenience and connectivity
    """
    
    def analyze(self, property: Property, intent: UserIntent) -> Dict[str, Any]:
        score = 5.0
        reasons = []
        risks = []
        
        try:
            # If user specified office location and max travel time
            if intent.commute and intent.commute.office_location:
                # Simplified: Check if property location matches office area
                office_area = intent.commute.office_location.lower()
                prop_area = property.location.lower()
                
                # Same area = good commute
                if office_area in prop_area or prop_area in office_area:
                    score += 2.5
                    reasons.append(f"Same area as office ({property.location})")
                # Adjacent areas (simplified logic)
                elif self._are_adjacent_areas(prop_area, office_area):
                    score += 1.0
                    reasons.append("Nearby office location")
                else:
                    score -= 1.0
                    risks.append("Potentially long commute to office")
            
            # Metro connectivity
            if property.nearestMetroDistanceKm:
                if property.nearestMetroDistanceKm <= 1:
                    score += 2.0
                    reasons.append(f"Walking distance to metro ({property.nearestMetroStation})")
                elif property.nearestMetroDistanceKm <= 3:
                    score += 1.0
                    reasons.append("Good metro connectivity")
                elif property.nearestMetroDistanceKm > 10:
                    score -= 1.5
                    risks.append("Limited public transport")
            
            # Airport proximity (for frequent travelers)
            if property.nearestAirportDistanceKm:
                if property.nearestAirportDistanceKm <= 15:
                    score += 0.5
                    reasons.append(f"Close to airport ({property.nearestAirportDistanceKm}km)")
            
            score = max(0, min(10, score))
            
            return {
                "commute_score": round(score, 2),
                "reasons": reasons,
                "risks": risks
            }
            
        except Exception as e:
            logger.error(f"Commute analysis failed: {e}")
            return {"commute_score": 5.0, "reasons": [], "risks": []}
    
    def _are_adjacent_areas(self, area1: str, area2: str) -> bool:
        """Check if two areas are adjacent (simplified)"""
        adjacent_map = {
            "koramangala": ["hsr layout", "indiranagar"],
            "whitefield": ["sarjapur", "varthur"],
            "electronic city": ["sarjapur", "bannerghatta"],
        }
        
        for key, adjacents in adjacent_map.items():
            if key in area1 and area2 in adjacents:
                return True
            if key in area2 and area1 in adjacents:
                return True
        return False
