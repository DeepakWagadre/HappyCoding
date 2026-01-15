package com.propertyai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Main application class for Property AI Backend
 * 
 * This application provides REST APIs and SSE streaming for the 
 * Agentic AI Property Discovery Platform.
 * 
 * Features:
 * - Virtual Threads (Java 21 Project Loom) for high concurrency
 * - Server-Sent Events for real-time streaming
 * - Multi-agent AI integration
 * - Transparent scoring and ranking
 * 
 * @author Property AI Team
 * @version 1.0.0
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
@EnableAsync
public class PropertyAiApplication {

    public static void main(String[] args) {
        // Enable virtual threads globally
        System.setProperty("jdk.virtualThreadScheduler.parallelism", 
            String.valueOf(Runtime.getRuntime().availableProcessors()));
        
        SpringApplication.run(PropertyAiApplication.class, args);
        
        System.out.println("""
            
            ╔═══════════════════════════════════════════════════════════════╗
            ║                                                               ║
            ║   Property AI Backend - Running Successfully                 ║
            ║                                                               ║
            ║   API Docs: http://localhost:8080/swagger-ui.html           ║
            ║   Health Check: http://localhost:8080/actuator/health       ║
            ║                                                               ║
            ║   Ready to process property search queries!                  ║
            ║                                                               ║
            ╚═══════════════════════════════════════════════════════════════╝
            
            """);
    }
}
