"""
AI Agent Service - FastAPI Application
Provides REST APIs for intent extraction and property analysis
"""

import logging
import os
from contextlib import asynccontextmanager
from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from fastapi.responses import JSONResponse

from ..models.schemas import (
    IntentExtractionRequest,
    IntentExtractionResponse,
    PropertyAnalysisRequest,
    AgentAnalysisResponse
)
from ..services.intent_parser import get_intent_parser
from ..services.orchestrator import get_orchestrator

# Configure logging
logging.basicConfig(
    level=os.getenv("LOG_LEVEL", "INFO"),
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s'
)
logger = logging.getLogger(__name__)


@asynccontextmanager
async def lifespan(app: FastAPI):
    """Application lifespan - startup and shutdown"""
    # Startup
    logger.info("AI Agent Service starting up...")
    logger.info(f"OpenAI Model: {os.getenv('OPENAI_MODEL', 'gpt-4-turbo-preview')}")
    logger.info(f"Environment: {os.getenv('PYTHON_ENV', 'development')}")
    
    # Warm up services
    try:
        intent_parser = get_intent_parser()
        orchestrator = get_orchestrator()
        logger.info("AI services initialized successfully")
    except Exception as e:
        logger.error(f"Failed to initialize services: {e}", exc_info=True)
    
    yield
    
    # Shutdown
    logger.info("AI Agent Service shutting down...")


# Create FastAPI app
app = FastAPI(
    title="Property AI Agent Service",
    description="Multi-agent AI system for intelligent property discovery in Bangalore",
    version="1.0.0",
    lifespan=lifespan
)

# CORS configuration
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # In production, restrict to specific origins
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


@app.get("/")
async def root():
    """Root endpoint"""
    return {
        "service": "Property AI Agent Service",
        "version": "1.0.0",
        "status": "running",
        "endpoints": {
            "intent_extraction": "/extract-intent",
            "property_analysis": "/analyze",
            "health": "/health"
        }
    }


@app.get("/health")
async def health_check():
    """Health check endpoint"""
    try:
        # Verify OpenAI key is set
        if not os.getenv("OPENAI_API_KEY"):
            return JSONResponse(
                status_code=503,
                content={
                    "status": "unhealthy",
                    "error": "OPENAI_API_KEY not configured"
                }
            )
        
        return {
            "status": "healthy",
            "service": "ai-agents",
            "model": os.getenv("OPENAI_MODEL", "gpt-4-turbo-preview")
        }
    except Exception as e:
        logger.error(f"Health check failed: {e}")
        return JSONResponse(
            status_code=503,
            content={"status": "unhealthy", "error": str(e)}
        )


@app.post("/extract-intent", response_model=IntentExtractionResponse)
async def extract_intent(request: IntentExtractionRequest):
    """
    Extract structured intent from natural language query
    
    Request:
        {
            "query": "3 BHK under 1.5 crore near Sarjapur with good schools"
        }
    
    Response:
        {
            "intent": { ... structured UserIntent ... },
            "confidence": "HIGH|MEDIUM|LOW",
            "clarifications_needed": [...]
        }
    """
    try:
        logger.info(f"Intent extraction request: {request.query}")
        
        intent_parser = get_intent_parser()
        response = await intent_parser.parse(request.query)
        
        logger.info(f"Intent extracted with confidence: {response.confidence}")
        return response
        
    except Exception as e:
        logger.error(f"Intent extraction failed: {e}", exc_info=True)
        raise HTTPException(
            status_code=500,
            detail=f"Intent extraction failed: {str(e)}"
        )


@app.post("/analyze", response_model=AgentAnalysisResponse)
async def analyze_properties(request: PropertyAnalysisRequest):
    """
    Analyze properties using multi-agent system
    
    Request:
        {
            "intent": { ... UserIntent ... },
            "properties": [ ... list of properties ... ]
        }
    
    Response:
        {
            "results": [
                {
                    "property_id": "...",
                    "scores": { ... },
                    "why_recommended": [...],
                    "key_risks": [...]
                }
            ],
            "assumptions": [...],
            "areas_to_avoid": [...],
            "follow_up_questions": [...]
        }
    """
    try:
        logger.info(f"Analysis request for {len(request.properties)} properties")
        
        if not request.properties:
            raise HTTPException(
                status_code=400,
                detail="No properties provided for analysis"
            )
        
        orchestrator = get_orchestrator()
        response = await orchestrator.analyze_properties(
            intent=request.intent,
            properties=request.properties
        )
        
        logger.info(f"Analysis completed. {len(response.results)} properties scored.")
        return response
        
    except Exception as e:
        logger.error(f"Property analysis failed: {e}", exc_info=True)
        raise HTTPException(
            status_code=500,
            detail=f"Property analysis failed: {str(e)}"
        )


@app.exception_handler(Exception)
async def global_exception_handler(request, exc):
    """Global exception handler"""
    logger.error(f"Unhandled exception: {exc}", exc_info=True)
    return JSONResponse(
        status_code=500,
        content={
            "error": "Internal server error",
            "detail": str(exc)
        }
    )


if __name__ == "__main__":
    import uvicorn
    
    port = int(os.getenv("AI_AGENT_PORT", "8000"))
    
    uvicorn.run(
        "src.api.main:app",
        host="0.0.0.0",
        port=port,
        reload=os.getenv("PYTHON_ENV") == "development",
        log_level=os.getenv("LOG_LEVEL", "info").lower()
    )
