#!/bin/bash

#############################################################################
# Property AI Platform - Complete Startup Script
# This script ensures all services are built and running correctly
#############################################################################

set -e  # Exit on error

# Color codes for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}╔═══════════════════════════════════════════════════════════════╗${NC}"
echo -e "${BLUE}║                                                               ║${NC}"
echo -e "${BLUE}║   🚀 Property AI Platform - Complete Startup                 ║${NC}"
echo -e "${BLUE}║   End-to-End Agentic AI Property Discovery                   ║${NC}"
echo -e "${BLUE}║                                                               ║${NC}"
echo -e "${BLUE}╚═══════════════════════════════════════════════════════════════╝${NC}"
echo ""

# Check prerequisites
echo -e "${YELLOW}📋 Step 1: Checking prerequisites...${NC}"

if ! command -v docker &> /dev/null; then
    echo -e "${RED}❌ Docker is not installed or not running${NC}"
    echo "   Please start Docker Desktop and try again"
    exit 1
fi

if ! docker info &> /dev/null; then
    echo -e "${RED}❌ Docker daemon is not running${NC}"
    echo "   Please start Docker Desktop and try again"
    exit 1
fi

echo -e "${GREEN}✅ Docker is running${NC}"

# Check for .env file
if [ ! -f .env ]; then
    echo -e "${YELLOW}⚠️  .env file not found, creating from template...${NC}"
    cp .env.example .env
    echo -e "${RED}❌ Please configure your OPENAI_API_KEY in .env file${NC}"
    echo "   Edit .env and set: OPENAI_API_KEY=sk-your-key-here"
    exit 1
fi

# Validate OpenAI API key
if ! grep -q "^OPENAI_API_KEY=" .env; then
    echo -e "${RED}❌ OPENAI_API_KEY not configured in .env${NC}"
    echo "   Edit .env and set: OPENAI_API_KEY=sk-your-key-here"
    exit 1
fi

echo -e "${GREEN}✅ Configuration validated${NC}"
echo ""

# Clean up any existing containers
echo -e "${YELLOW}📋 Step 2: Cleaning up existing containers...${NC}"
docker-compose down -v 2>/dev/null || true
echo -e "${GREEN}✅ Cleanup complete${NC}"
echo ""

# Build all services
echo -e "${YELLOW}📋 Step 3: Building Docker images...${NC}"
echo "   This will take 5-10 minutes on first run..."
echo ""

if docker-compose build 2>&1 | tee build.log; then
    echo ""
    echo -e "${GREEN}✅ All images built successfully${NC}"
else
    echo ""
    echo -e "${RED}❌ Build failed. Check build.log for details${NC}"
    echo "   Last 20 lines of build output:"
    tail -20 build.log
    exit 1
fi
echo ""

# Start all services
echo -e "${YELLOW}📋 Step 4: Starting all services...${NC}"
docker-compose up -d

echo ""
echo -e "${YELLOW}📋 Step 5: Waiting for services to be healthy...${NC}"
echo "   This may take 1-2 minutes..."
echo ""

# Function to check service health
check_service() {
    local service=$1
    local url=$2
    local max_attempts=30
    local attempt=1
    
    while [ $attempt -le $max_attempts ]; do
        if curl -f -s "$url" > /dev/null 2>&1; then
            echo -e "${GREEN}✅ $service is healthy${NC}"
            return 0
        fi
        echo -e "   ⏳ Waiting for $service... (attempt $attempt/$max_attempts)"
        sleep 2
        attempt=$((attempt + 1))
    done
    
    echo -e "${RED}❌ $service failed to start${NC}"
    return 1
}

# Check each service
sleep 10  # Give containers time to start

echo "Checking service health..."
echo ""

# PostgreSQL (check via backend later)
echo -e "${BLUE}Checking PostgreSQL...${NC}"
if docker-compose exec -T postgres pg_isready -U propertyai &> /dev/null; then
    echo -e "${GREEN}✅ PostgreSQL is healthy${NC}"
    echo -e "${YELLOW}🌱 Seeding database properties...${NC}"
    if cat backend/src/main/resources/data.sql | docker-compose exec -T postgres psql -U propertyai -d bangalore_properties > /dev/null 2>&1; then
        echo -e "${GREEN}✅ Database seeded successfully${NC}"
    else
        echo -e "${RED}⚠️ Database seeding failed (might already be seeded)${NC}"
    fi
else
    echo -e "${YELLOW}⏳ PostgreSQL still starting...${NC}"
fi

# Elasticsearch
echo -e "${BLUE}Checking Elasticsearch...${NC}"
check_service "Elasticsearch" "http://localhost:9200/_cluster/health" || echo -e "${YELLOW}⚠️  Elasticsearch not ready yet${NC}"

# Redis
echo -e "${BLUE}Checking Redis...${NC}"
if docker-compose exec -T redis redis-cli ping &> /dev/null; then
    echo -e "${GREEN}✅ Redis is healthy${NC}"
else
    echo -e "${YELLOW}⏳ Redis still starting...${NC}"
fi

# AI Agents
echo -e "${BLUE}Checking AI Agent Service...${NC}"
check_service "AI Agents" "http://localhost:8000/health" || echo -e "${YELLOW}⚠️  AI Agents not ready yet (may need more time)${NC}"

# Backend
echo -e "${BLUE}Checking Backend API...${NC}"
check_service "Backend" "http://localhost:8080/api/health" || check_service "Backend (actuator)" "http://localhost:8080/actuator/health" || echo -e "${YELLOW}⚠️  Backend not ready yet (may need more time)${NC}"

# Frontend
echo -e "${BLUE}Checking Frontend...${NC}"
check_service "Frontend" "http://localhost:3000" || echo -e "${YELLOW}⚠️  Frontend not ready yet (may need more time)${NC}"

echo ""
echo -e "${BLUE}╔═══════════════════════════════════════════════════════════════╗${NC}"
echo -e "${BLUE}║                                                               ║${NC}"
echo -e "${BLUE}║   🎉 Property AI Platform Started!                           ║${NC}"
echo -e "${BLUE}║                                                               ║${NC}"
echo -e "${BLUE}║   Access the application:                                    ║${NC}"
echo -e "${BLUE}║                                                               ║${NC}"
echo -e "${BLUE}║   🌐 Frontend:        http://localhost:3000                  ║${NC}"
echo -e "${BLUE}║   🔧 Backend API:     http://localhost:8080/api              ║${NC}"
echo -e "${BLUE}║   🤖 AI Agents:       http://localhost:8000                  ║${NC}"
echo -e "${BLUE}║   📚 API Docs:        http://localhost:8080/swagger-ui.html  ║${NC}"
echo -e "${BLUE}║                                                               ║${NC}"
echo -e "${BLUE}║   Note: Services may take 1-2 minutes to fully initialize   ║${NC}"
echo -e "${BLUE}║                                                               ║${NC}"
echo -e "${BLUE}╚═══════════════════════════════════════════════════════════════╝${NC}"
echo ""

echo -e "${GREEN}✨ Quick Commands:${NC}"
echo "   • View all logs:        docker-compose logs -f"
echo "   • View service logs:    docker-compose logs -f [frontend|backend|ai-agents]"
echo "   • Check status:         docker-compose ps"
echo "   • Stop everything:      docker-compose down"
echo "   • Restart a service:    docker-compose restart [service-name]"
echo ""

echo -e "${GREEN}🔍 Sample Queries:${NC}"
echo "   • '3 BHK under 1.5 crore near Sarjapur with good schools'"
echo "   • 'Best areas to invest in Bangalore for rental yield'"
echo "   • 'Villa near Whitefield within 45 mins commute'"
echo ""

echo -e "${GREEN}📊 Service Status:${NC}"
docker-compose ps
echo ""

# Optionally open browser
read -p "$(echo -e ${YELLOW}Open frontend in browser? [Y/n]:${NC} )" -n 1 -r
echo
if [[ ! $REPLY =~ ^[Nn]$ ]]; then
    if command -v open &> /dev/null; then
        open http://localhost:3000
    elif command -v xdg-open &> /dev/null; then
        xdg-open http://localhost:3000
    else
        echo "Please open http://localhost:3000 in your browser"
    fi
fi

echo ""
echo -e "${GREEN}✅ Startup complete! Happy property hunting! 🏡${NC}"
