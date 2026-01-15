package com.propertyai.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Property scoring breakdown from AI agents
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyScore implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("location_score")
    private Double locationScore; // 0-10

    @JsonProperty("price_fairness_score")
    private Double priceFairnessScore; // 0-10

    @JsonProperty("amenities_match_score")
    private Double amenitiesMatchScore; // 0-10

    @JsonProperty("commute_score")
    private Double commuteScore; // 0-10

    @JsonProperty("growth_potential_score")
    private Double growthPotentialScore; // 0-10

    @JsonProperty("livability_score")
    private Double livabilityScore; // 0-10

    @JsonProperty("risk_penalty")
    private Double riskPenalty; // -5 to 0

    @JsonProperty("final_score")
    private Double finalScore; // Weighted aggregate

    @JsonProperty("confidence_level")
    private String confidenceLevel; // high, medium, low

    /**
     * Calculate final score based on weights
     * 
     * @param weights Scoring weights based on user intent
     * @return Calculated final score
     */
    public Double calculateFinalScore(ScoringWeights weights) {
        double score = 0.0;

        score += (locationScore != null ? locationScore : 0) * weights.getLocationWeight();
        score += (priceFairnessScore != null ? priceFairnessScore : 0) * weights.getPriceWeight();
        score += (amenitiesMatchScore != null ? amenitiesMatchScore : 0) * weights.getAmenitiesWeight();
        score += (commuteScore != null ? commuteScore : 0) * weights.getCommuteWeight();
        score += (growthPotentialScore != null ? growthPotentialScore : 0) * weights.getGrowthWeight();
        score += (livabilityScore != null ? livabilityScore : 0) * weights.getLivabilityWeight();
        score += (riskPenalty != null ? riskPenalty : 0);

        this.finalScore = Math.max(0, Math.min(10, score));
        return this.finalScore;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ScoringWeights {
        private double locationWeight;
        private double priceWeight;
        private double amenitiesWeight;
        private double commuteWeight;
        private double growthWeight;
        private double livabilityWeight;

        /**
         * Get default weights for end-use buyers
         */
        public static ScoringWeights endUseWeights() {
            return ScoringWeights.builder()
                    .locationWeight(0.20)
                    .priceWeight(0.15)
                    .amenitiesWeight(0.25)
                    .commuteWeight(0.25)
                    .growthWeight(0.05)
                    .livabilityWeight(0.10)
                    .build();
        }

        /**
         * Get default weights for investors
         */
        public static ScoringWeights investmentWeights() {
            return ScoringWeights.builder()
                    .locationWeight(0.25)
                    .priceWeight(0.30)
                    .amenitiesWeight(0.05)
                    .commuteWeight(0.05)
                    .growthWeight(0.30)
                    .livabilityWeight(0.05)
                    .build();
        }

        /**
         * Get default weights for rental yield focus
         */
        public static ScoringWeights rentalYieldWeights() {
            return ScoringWeights.builder()
                    .locationWeight(0.20)
                    .priceWeight(0.25)
                    .amenitiesWeight(0.15)
                    .commuteWeight(0.15)
                    .growthWeight(0.20)
                    .livabilityWeight(0.05)
                    .build();
        }
    }
}
