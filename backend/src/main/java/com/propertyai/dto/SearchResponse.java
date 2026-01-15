package com.propertyai.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.propertyai.model.PropertyScore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * Search response DTO - matches EXACT output contract specified in requirements
 * NO markdown, NO emojis, NO sales language
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchResponse {

    private List<String> assumptions;

    @JsonProperty("top_recommendations")
    private List<PropertyRecommendation> topRecommendations;

    private List<PropertyRecommendation> alternatives;

    @JsonProperty("areas_to_avoid")
    private List<AreaWarning> areasToAvoid;

    @JsonProperty("follow_up_questions")
    private List<String> followUpQuestions;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PropertyRecommendation {
        @JsonProperty("property_id")
        private String propertyId;

        @JsonProperty("property_name")
        private String propertyName;

        private String location;

        @JsonProperty("price_range")
        private String priceRange;

        @JsonProperty("property_type")
        private String propertyType;

        private Integer bedrooms;

        @JsonProperty("area_sqft")
        private Integer areaSqft;

        private String builder;

        @JsonProperty("possession_status")
        private String possessionStatus;

        private List<String> amenities;

        @JsonProperty("why_recommended")
        private List<String> whyRecommended;

        @JsonProperty("key_risks")
        private List<String> keyRisks;

        private PropertyScore scores;

        // Geographic coordinates for map
        private GeoLocation location_coords;

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class GeoLocation {
            private BigDecimal latitude;
            private BigDecimal longitude;
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class AreaWarning {
        private String area;
        private String reason;
        private String severity; // high, medium, low
    }
}
