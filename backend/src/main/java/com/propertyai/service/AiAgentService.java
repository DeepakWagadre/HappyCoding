package com.propertyai.service;

import com.propertyai.dto.UserIntent;
import com.propertyai.model.Property;
import com.propertyai.model.PropertyScore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * Service for communicating with AI Agent layer (Python/LangGraph)
 * Handles intent extraction and multi-agent property analysis
 */
@Service
public class AiAgentService {

        private static final Logger logger = LoggerFactory.getLogger(AiAgentService.class);

        private final WebClient aiAgentWebClient;
        private final Duration timeout;

        public AiAgentService(
                        @Qualifier("aiAgentWebClient") WebClient aiAgentWebClient,
                        @Value("${ai-agent.timeout}") Duration timeout) {
                this.aiAgentWebClient = aiAgentWebClient;
                this.timeout = timeout;
        }

        /**
         * Extract structured intent from natural language query
         * 
         * @param query Natural language search query
         * @return UserIntent with confidence level
         */
        public Mono<IntentExtractionResponse> extractIntent(String query) {
                logger.info("Extracting intent from query: {}", query);

                Map<String, String> request = Map.of("query", query);

                return aiAgentWebClient
                                .post()
                                .uri("/extract-intent")
                                .bodyValue(request)
                                .retrieve()
                                .bodyToMono(IntentExtractionResponse.class)
                                .timeout(timeout)
                                .doOnSuccess(response -> logger.info("Intent extracted with confidence: {}",
                                                response.confidence()))
                                .doOnError(error -> logger.error("Failed to extract intent: {}", error.getMessage()));
        }

        /**
         * Analyze properties using multi-agent system
         * 
         * @param intent     User intent
         * @param properties List of candidate properties
         * @return Analyzed properties with scores
         */
        public Mono<AgentAnalysisResponse> analyzeProperties(
                        UserIntent intent,
                        List<Property> properties) {
                logger.info("Analyzing {} properties with multi-agent system", properties.size());

                Map<String, Object> request = Map.of(
                                "intent", intent,
                                "properties", properties);

                return aiAgentWebClient
                                .post()
                                .uri("/analyze")
                                .bodyValue(request)
                                .retrieve()
                                .bodyToMono(AgentAnalysisResponse.class)
                                .timeout(timeout)
                                .doOnSuccess(
                                                response -> logger.info("Analysis completed for {} properties",
                                                                response.results().size()))
                                .doOnError(error -> logger.error("Failed to analyze properties: {}",
                                                error.getMessage()));
        }

        /**
         * Health check for AI agent service
         */
        public Mono<Boolean> checkHealth() {
                return aiAgentWebClient
                                .get()
                                .uri("/health")
                                .retrieve()
                                .bodyToMono(Map.class)
                                .map(response -> "healthy".equals(response.get("status")))
                                .timeout(Duration.ofSeconds(5))
                                .onErrorReturn(false);
        }

        // DTO classes for AI agent responses

        public record IntentExtractionResponse(
                        UserIntent intent,
                        String confidence,
                        List<String> clarificationsNeeded) {
        }

        public record AgentAnalysisResponse(
                        List<PropertyAnalysisResult> results,
                        List<String> assumptions,
                        List<String> areasToAvoid,
                        List<String> followUpQuestions) {
        }

        public record PropertyAnalysisResult(
                        String propertyId,
                        PropertyScore scores,
                        List<String> whyRecommended,
                        List<String> keyRisks) {
        }
}
