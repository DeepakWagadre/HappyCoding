package com.propertyai.controller;

import com.propertyai.dto.SearchRequest;
import com.propertyai.dto.SearchResponse;
import com.propertyai.model.Property;
import com.propertyai.service.PropertySearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * REST Controller for property search operations
 */
@RestController
@RequestMapping("/api")
@Tag(name = "Property Search", description = "Property search and discovery APIs")
public class PropertySearchController {

    private static final Logger logger = LoggerFactory.getLogger(PropertySearchController.class);

    private final PropertySearchService propertySearchService;

    public PropertySearchController(PropertySearchService propertySearchService) {
        this.propertySearchService = propertySearchService;
    }

    /**
     * Execute property search with Server-Sent Events streaming
     * 
     * POST /api/search
     * 
     * Request:
     * {
     * "query": "3 BHK under 1.5 crore near Sarjapur with good schools"
     * }
     * 
     * Response: SSE stream with events:
     * - intent_extraction_started
     * - intent_extracted
     * - candidates_found
     * - agent_analysis_started
     * - agent_progress (multiple)
     * - recommendations_ready
     * - complete
     */
    @PostMapping(value = "/search", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @Operation(summary = "Search properties with natural language", description = "Returns a stream of events showing search progress and final recommendations")
    public Flux<ServerSentEvent<Object>> searchProperties(@Valid @RequestBody SearchRequest request) {
        logger.info("Received search request: {}", request.getQuery());

        return propertySearchService.executeSearch(request.getQuery())
                .map(event -> ServerSentEvent.builder()
                        .event(event.eventType())
                        .data(event.data())
                        .build())
                .delayElements(Duration.ofMillis(100)) // Smooth streaming
                .doOnComplete(() -> logger.info("Search completed for query: {}", request.getQuery()))
                .doOnError(error -> logger.error("Search failed: {}", error.getMessage(), error));
    }

    /**
     * Get property details by ID
     * 
     * GET /api/properties/{id}
     */
    @GetMapping("/properties/{id}")
    @Operation(summary = "Get property by ID", description = "Returns detailed property information")
    public ResponseEntity<Property> getPropertyById(@PathVariable String id) {
        logger.info("Fetching property: {}", id);

        try {
            Property property = propertySearchService.getPropertyById(id);
            return ResponseEntity.ok(property);
        } catch (RuntimeException e) {
            logger.error("Property not found: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Health check endpoint
     */
    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Check service health")
    public ResponseEntity<HealthResponse> health() {
        return ResponseEntity.ok(new HealthResponse("healthy", "Backend service is running"));
    }

    /**
     * Exception handler for validation errors
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        logger.error("Error processing request: {}", e.getMessage(), e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("error", e.getMessage()));
    }

    // Response DTOs

    public record HealthResponse(String status, String message) {
    }

    public record ErrorResponse(String error, String message) {
    }
}
