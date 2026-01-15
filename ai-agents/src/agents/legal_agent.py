"""
Legal & Builder Risk Agent
Evaluates builder reputation and legal compliance
"""

import logging
from typing import Dict, Any
from ..models.schemas import Property, UserIntent

logger = logging.getLogger(__name__)


class LegalAgent:
    """
    Evaluates legal compliance and builder reputation
    """
    
    def analyze(self, property: Property, intent: UserIntent) -> Dict[str, Any]:
        penalty = 0.0  # Risk penalty: -5 to 0
        reasons = []
        risks = []
        
        try:
            # RERA compliance
            if property.reraApproved:
                reasons.append("RERA approved project")
            else:
                penalty -= 1.0
                risks.append("Not RERA approved - higher risk")
            
            # Legal status
            if property.legalStatus:
                if property.legalStatus.lower() == "clear":
                    reasons.append("Clear legal title")
                elif property.legalStatus.lower() == "disputed":
                    penalty -= 2.5
                    risks.append("CRITICAL: Property has legal disputes")
                elif "verification" in property.legalStatus.lower():
                    penalty -= 0.5
                    risks.append("Legal verification pending")
            
            # Builder reputation
            if property.builder:
                reputed_builders = {
                    "prestige": 0,
                    "sobha": 0,
                    "brigade": 0,
                    "godrej": 0,
                    "puravankara": 0,
                    "embassy": 0
                }
                
                is_reputed = any(
                    builder in property.builder.lower() 
                    for builder in reputed_builders
                )
                
                if is_reputed:
                    reasons.append(f"Reputed builder: {property.builder}")
                else:
                    penalty -= 0.3
                    risks.append("Lesser-known builder - verify track record")
            
            # Builder rating
            if property.builderRating:
                rating_str = property.builderRating
                if "/" in rating_str:
                    try:
                        rating = float(rating_str.split("/")[0])
                        if rating >= 4.5:
                            reasons.append(f"Excellent builder rating: {rating_str}")
                        elif rating < 4.0:
                            penalty -= 0.5
                            risks.append(f"Lower builder rating: {rating_str}")
                    except:
                        pass
            
            # Possession status risk
            if property.possessionStatus == "Under Construction":
                penalty -= 0.3
                risks.append("Construction delay risk")
            
            penalty = max(-5, min(0, penalty))
            
            return {
                "risk_penalty": round(penalty, 2),
                "reasons": reasons,
                "risks": risks
            }
            
        except Exception as e:
            logger.error(f"Legal analysis failed: {e}")
            return {"risk_penalty": -0.5, "reasons": [], "risks": ["Unable to verify legal status"]}
