package com.propertyai.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.propertyai.model.Property.PropertyType;
import com.propertyai.model.Property.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * User intent extracted from natural language query
 * This is the STRICT JSON structure produced by AI intent parser
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserIntent {

    @JsonProperty("transaction_type")
    private TransactionType transactionType;

    @JsonProperty("property_type")
    private PropertyType propertyType;

    private Budget budget;

    @JsonProperty("preferred_locations")
    private List<String> preferredLocations;

    private Amenities amenities;

    private Commute commute;

    @JsonProperty("family_context")
    private FamilyContext familyContext;

    @JsonProperty("investment_goal")
    private InvestmentGoal investmentGoal;

    @JsonProperty("confidence_level")
    private ConfidenceLevel confidenceLevel;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Budget {
        private BigDecimal min;
        private BigDecimal max;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Amenities {
        @JsonProperty("must_have")
        private List<String> mustHave;

        @JsonProperty("nice_to_have")
        private List<String> niceToHave;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Commute {
        @JsonProperty("office_location")
        private String officeLocation;

        @JsonProperty("max_travel_time_minutes")
        private Integer maxTravelTimeMinutes;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class FamilyContext {
        private Boolean kids;

        @JsonProperty("school_priority")
        private Boolean schoolPriority;

        @JsonProperty("senior_citizens")
        private Boolean seniorCitizens;
    }

    public enum InvestmentGoal {
        END_USE,
        RENTAL_YIELD,
        APPRECIATION,
        MIXED
    }

    public enum ConfidenceLevel {
        HIGH,
        MEDIUM,
        LOW
    }
}
