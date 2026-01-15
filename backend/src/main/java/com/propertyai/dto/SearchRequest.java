package com.propertyai.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

/**
 * Search request DTO - accepts natural language query
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchRequest {

    @NotBlank(message = "Query cannot be empty")
    private String query;

    // Optional: User ID for personalized recommendations
    private String userId;

    // Optional: Override default max results
    private Integer maxResults;

    // Optional: Force refresh cache
    private Boolean forceRefresh;
}
