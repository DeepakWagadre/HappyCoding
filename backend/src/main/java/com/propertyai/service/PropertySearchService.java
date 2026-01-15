package com.propertyai.service;

import com.propertyai.dto.SearchResponse;
import com.propertyai.dto.UserIntent;
import com.propertyai.model.Property;
import com.propertyai.model.PropertyScore;
import com.propertyai.repository.PropertyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main property search service orchestrating the entire search flow
 */
@Service
@Transactional(readOnly = true)
public class PropertySearchService {

        private static final Logger logger = LoggerFactory.getLogger(PropertySearchService.class);

        private final PropertyRepository propertyRepository;
        private final AiAgentService aiAgentService;

        public PropertySearchService(
                        PropertyRepository propertyRepository,
                        AiAgentService aiAgentService) {
                this.propertyRepository = propertyRepository;
                this.aiAgentService = aiAgentService;
        }

        /**
         * Execute natural language property search with streaming support
         * 
         * @param query Natural language query
         * @return Flux of search events for SSE streaming
         */
        public Flux<SearchEvent> executeSearch(String query) {
                logger.info("Starting property search for query: {}", query);

                return Flux.create(emitter -> {
                        // Event 1: Intent extraction started
                        emitter.next(new SearchEvent("intent_extraction_started",
                                        "Analyzing your requirements"));

                        // Extract intent
                        aiAgentService.extractIntent(query)
                                        .flatMap(intentResponse -> {
                                                UserIntent intent = intentResponse.intent();

                                                // Event 2: Intent extracted
                                                emitter.next(new SearchEvent("intent_extracted", intent));

                                                // Find candidate properties
                                                List<Property> candidates = findCandidateProperties(intent);
                                                logger.info("Found {} candidate properties", candidates.size());

                                                // Event 3: Candidates found
                                                emitter.next(new SearchEvent("candidates_found",
                                                                String.format("Found %d matching properties",
                                                                                candidates.size())));

                                                // Event 4: Agent analysis started
                                                emitter.next(new SearchEvent("agent_analysis_started",
                                                                "AI agents analyzing properties"));

                                                // Analyze with AI agents
                                                return aiAgentService.analyzeProperties(intent, candidates)
                                                                .map(analysisResponse -> buildSearchResponse(intent,
                                                                                candidates, analysisResponse));
                                        })
                                        .doOnSuccess(response -> {
                                                // Event 5: Final recommendations
                                                emitter.next(new SearchEvent("recommendations_ready", response));

                                                // Event 6: Complete
                                                emitter.next(new SearchEvent("complete",
                                                                "Search completed successfully"));
                                                emitter.complete();
                                        })
                                        .doOnError(error -> {
                                                logger.error("Search failed: {}", error.getMessage(), error);
                                                emitter.next(new SearchEvent("error",
                                                                "Search failed: " + error.getMessage()));
                                                emitter.error(error);
                                        })
                                        .subscribe();
                });
        }

        /**
         * Find candidate properties based on intent
         */
        private List<Property> findCandidateProperties(UserIntent intent) {
                BigDecimal minPrice = intent.getBudget() != null && intent.getBudget().getMin() != null
                                ? intent.getBudget().getMin()
                                : BigDecimal.ZERO;

                BigDecimal maxPrice = intent.getBudget() != null && intent.getBudget().getMax() != null
                                ? intent.getBudget().getMax()
                                : new BigDecimal("999999999");

                List<Property> candidates;

                // Search by location if specified
                if (intent.getPreferredLocations() != null && !intent.getPreferredLocations().isEmpty()) {
                        // Fetch broader set by transaction type and price
                        List<Property> broadCandidates = propertyRepository.findByTransactionTypeAndPriceRange(
                                        intent.getTransactionType(),
                                        minPrice,
                                        maxPrice);

                        // Filter in-memory for partial location match (fuzzy search)
                        candidates = broadCandidates.stream()
                                        .filter(p -> {
                                                String pLoc = p.getLocation().toLowerCase();
                                                String pSubLoc = p.getSubLocation() != null
                                                                ? p.getSubLocation().toLowerCase()
                                                                : "";
                                                return intent.getPreferredLocations().stream()
                                                                .anyMatch(pref -> {
                                                                        String prefLower = pref.toLowerCase();
                                                                        return pLoc.contains(prefLower)
                                                                                        || pSubLoc.contains(prefLower);
                                                                });
                                        })
                                        .collect(Collectors.toList());

                        logger.info("Location filtering: Found {} candidates matching locations {}",
                                        candidates.size(), intent.getPreferredLocations());
                }
                // Search by property type and budget
                else if (intent.getPropertyType() != null) {
                        candidates = propertyRepository.findByDetailedCriteria(
                                        intent.getPropertyType(),
                                        intent.getTransactionType(),
                                        0, // min bedrooms
                                        minPrice,
                                        maxPrice);
                }
                // Search by transaction type and budget if specified
                else if (intent.getTransactionType() != null) {
                        candidates = propertyRepository.findByTransactionTypeAndPriceRange(
                                        intent.getTransactionType(),
                                        minPrice,
                                        maxPrice);
                }
                // MVP Fallback: return all properties to demonstrate the system
                else {
                        logger.info("Intent doesn't have specific criteria, returning all properties for MVP demo");
                        candidates = propertyRepository.findAll();
                }

                // If still no candidates, return all properties (for MVP)
                if (candidates.isEmpty()) {
                        logger.info("No properties found with criteria, returning all properties for MVP demo");
                        candidates = propertyRepository.findAll();
                }

                // Limit candidates to avoid overwhelming AI agents
                int limit = Math.min(candidates.size(), 50);
                List<Property> limited = candidates.stream()
                                .limit(limit)
                                .collect(Collectors.toList());

                logger.info("Returning {} candidate properties out of {} total", limited.size(), candidates.size());
                return limited;
        }

        /**
         * Build final search response from AI analysis
         */
        private SearchResponse buildSearchResponse(
                        UserIntent intent,
                        List<Property> candidates,
                        AiAgentService.AgentAnalysisResponse analysisResponse) {
                // Calculate scores with appropriate weights
                PropertyScore.ScoringWeights weights = getWeightsForIntent(intent);

                // Map properties with their scores
                List<SearchResponse.PropertyRecommendation> recommendations = analysisResponse.results()
                                .stream()
                                .map(result -> {
                                        Property property = candidates.stream()
                                                        .filter(p -> p.getPropertyId().equals(result.propertyId()))
                                                        .findFirst()
                                                        .orElse(null);

                                        if (property == null)
                                                return null;

                                        // Calculate final score
                                        PropertyScore scores = result.scores();
                                        scores.calculateFinalScore(weights);

                                        return SearchResponse.PropertyRecommendation.builder()
                                                        .propertyId(property.getPropertyId())
                                                        .propertyName(property.getName())
                                                        .location(property.getLocation() +
                                                                        (property.getSubLocation() != null ? ", "
                                                                                        + property.getSubLocation()
                                                                                        : ""))
                                                        .priceRange(formatPriceRange(property.getPrice()))
                                                        .propertyType(property.getPropertyType().name())
                                                        .bedrooms(property.getBedrooms())
                                                        .areaSqft(property.getAreaSqft())
                                                        .builder(property.getBuilder())
                                                        .possessionStatus(property.getPossessionStatus())
                                                        .amenities(property.getAmenities())
                                                        .whyRecommended(result.whyRecommended())
                                                        .keyRisks(result.keyRisks())
                                                        .scores(scores)
                                                        .location_coords(
                                                                        SearchResponse.PropertyRecommendation.GeoLocation
                                                                                        .builder()
                                                                                        .latitude(property
                                                                                                        .getLatitude())
                                                                                        .longitude(property
                                                                                                        .getLongitude())
                                                                                        .build())
                                                        .build();
                                })
                                .filter(rec -> rec != null)
                                .sorted(Comparator.comparing(
                                                rec -> rec.getScores().getFinalScore(),
                                                Comparator.reverseOrder()))
                                .collect(Collectors.toList());

                // Split into top recommendations and alternatives
                List<SearchResponse.PropertyRecommendation> topRecommendations = recommendations.stream()
                                .limit(5)
                                .collect(Collectors.toList());

                List<SearchResponse.PropertyRecommendation> alternatives = recommendations.stream()
                                .skip(5)
                                .limit(5)
                                .collect(Collectors.toList());

                return SearchResponse.builder()
                                .assumptions(analysisResponse.assumptions())
                                .topRecommendations(topRecommendations)
                                .alternatives(alternatives)
                                .areasToAvoid(new ArrayList<>()) // Would come from AI agents
                                .followUpQuestions(analysisResponse.followUpQuestions())
                                .build();
        }

        /**
         * Get scoring weights based on user intent
         */
        private PropertyScore.ScoringWeights getWeightsForIntent(UserIntent intent) {
                if (intent.getInvestmentGoal() == null) {
                        return PropertyScore.ScoringWeights.endUseWeights();
                }

                return switch (intent.getInvestmentGoal()) {
                        case RENTAL_YIELD -> PropertyScore.ScoringWeights.rentalYieldWeights();
                        case APPRECIATION -> PropertyScore.ScoringWeights.investmentWeights();
                        case MIXED -> PropertyScore.ScoringWeights.investmentWeights();
                        default -> PropertyScore.ScoringWeights.endUseWeights();
                };
        }

        /**
         * Format price in Indian currency format
         */
        private String formatPriceRange(BigDecimal price) {
                long priceValue = price.longValue();

                if (priceValue >= 10000000) { // 1 crore
                        double crores = priceValue / 10000000.0;
                        return String.format("₹%.2f Cr", crores);
                } else if (priceValue >= 100000) { // 1 lakh
                        double lakhs = priceValue / 100000.0;
                        return String.format("₹%.2f L", lakhs);
                } else {
                        return String.format("₹%,d", priceValue);
                }
        }

        /**
         * Get property by ID (cached)
         */
        @Cacheable(value = "properties", key = "#propertyId")
        public Property getPropertyById(String propertyId) {
                return propertyRepository.findByPropertyId(propertyId)
                                .orElseThrow(() -> new RuntimeException("Property not found: " + propertyId));
        }

        /**
         * Search event for SSE streaming
         */
        public record SearchEvent(String eventType, Object data) {
        }
}
