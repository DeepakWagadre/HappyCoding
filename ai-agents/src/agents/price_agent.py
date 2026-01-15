"""
Price Fairness Agent
Evaluates if property is priced fairly compared to market rates
"""

import logging
from typing import Dict, Any
from ..models.schemas import Property, UserIntent

logger = logging.getLogger(__name__)


class PriceAgent:
    """
    Evaluates price fairness and value for money
    
    Factors:
    - Price per sqft vs area average
    - Budget alignment
    - Market value comparison
    - Builder premium justification
    """
    
    def analyze(self, property: Property, intent: UserIntent) -> Dict[str, Any]:
        """
        Analyze price fairness
        
        Returns:
            {
                "price_fairness_score": float (0-10),
                "reasons": List[str],
                "risks": List[str]
            }
        """
        score = 5.0
        reasons = []
        risks = []
        
        try:
            # Budget alignment
            if intent.budget:
                if intent.budget.max:
                    if property.price <= intent.budget.max:
                        budget_ratio = property.price / intent.budget.max
                        if budget_ratio <= 0.8:
                            score += 2.0
                            reasons.append(f"Well within budget (₹{property.price/10000000:.2f}Cr vs ₹{intent.budget.max/10000000:.2f}Cr budget)")
                        elif budget_ratio <= 0.95:
                            score += 1.0
                            reasons.append("Within budget with good margin")
                        else:
                            score += 0.5
                    else:
                        score -= 2.0
                        risks.append(f"Over budget by ₹{(property.price - intent.budget.max)/100000:.1f}L")
                
                if intent.budget.min and property.price < intent.budget.min:
                    risks.append("Suspiciously low price - verify quality")
                    score -= 0.5
            
            # Price per sqft analysis
            if property.pricePerSqft:
                # Bangalore typical ranges (2024)
                area_price_ranges = {
                    "indiranagar": (7000, 10000),
                    "koramangala": (7000, 9500),
                    "whitefield": (5500, 7500),
                    "sarjapur": (6000, 7500),
                    "electronic city": (4500, 6000),
                    "hsr layout": (6500, 8000),
                    "hebbal": (5000, 6500),
                    "yelahanka": (3500, 5500),
                    "jp nagar": (5000, 6500),
                    "bannerghatta": (5500, 7000)
                }
                
                location_lower = property.location.lower()
                price_range = area_price_ranges.get(location_lower, (5000, 7000))
                
                if price_range[0] <= property.pricePerSqft <= price_range[1]:
                    score += 1.5
                    reasons.append(f"Fair market price at ₹{property.pricePerSqft:.0f}/sqft")
                elif property.pricePerSqft < price_range[0]:
                    score += 2.0
                    reasons.append(f"Good value - below market average (₹{property.pricePerSqft:.0f}/sqft)")
                else:
                    score -= 1.0
                    risks.append(f"Premium pricing at ₹{property.pricePerSqft:.0f}/sqft")
            
            # Market value comparison
            if property.marketValue and property.price:
                value_diff_pct = ((property.marketValue - property.price) / property.price) * 100
                if value_diff_pct > 5:
                    score += 1.0
                    reasons.append(f"Undervalued by ~{value_diff_pct:.1f}%")
                elif value_diff_pct < -5:
                    score -= 0.8
                    risks.append(f"Overvalued by ~{abs(value_diff_pct):.1f}%")
            
            # Builder reputation bonus
            premium_builders = ["prestige", "sobha", "brigade", "godrej", "puravankara"]
            if property.builder and any(b in property.builder.lower() for b in premium_builders):
                score += 0.5
                reasons.append(f"Reputable builder: {property.builder}")
            
            # Ready vs under construction
            if property.possessionStatus == "Ready":
                score += 0.5
                reasons.append("Ready for possession - no construction delay risk")
            elif property.possessionStatus == "Under Construction":
                score -= 0.3
                risks.append("Under construction - possession delay risk")
            
            # Cap score
            score = max(0, min(10, score))
            
            logger.info(f"Price score for {property.propertyId}: {score}")
            
            return {
                "price_fairness_score": round(score, 2),
                "reasons": reasons,
                "risks": risks
            }
            
        except Exception as e:
            logger.error(f"Price analysis failed: {e}", exc_info=True)
            return {
                "price_fairness_score": 5.0,
                "reasons": ["Unable to fully analyze pricing"],
                "risks": []
            }
