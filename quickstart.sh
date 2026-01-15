#!/bin/bash

# Property AI Platform - Quick Start Script
# This script sets up and launches the entire platform

set -e  # Exit on error

echo "╔═══════════════════════════════════════════════════════════════╗"
echo "║                                                               ║"
echo "║   Property AI Platform - Quick Start                         ║"
echo "║   Agentic AI Property Discovery for Bangalore                ║"
echo "║                                                               ║"
echo "╚═══════════════════════════════════════════════════════════════╝"
echo ""

# Check prerequisites
echo "🔍 Checking prerequisites..."

if ! command -v docker &> /dev/null; then
    echo "❌ Docker is not installed. Please install Docker Desktop first."
    echo "   Visit: https://www.docker.com/products/docker-desktop"
    exit 1
fi

if ! command -v docker-compose &> /dev/null; then
    echo "❌ Docker Compose is not installed. Please install it first."
    exit 1
fi

echo "✅ Docker is installed"
echo "✅ Docker Compose is installed"
echo ""

# Check for .env file
if [ ! -f .env ]; then
    echo "📝 Creating .env file from template..."
    cp .env.example .env
    
    echo ""
    echo "⚠️  IMPORTANT: Please configure your .env file"
    echo ""
    echo "Required configuration:"
    echo "1. Set OPENAI_API_KEY (get from https://platform.openai.com/api-keys)"
    echo ""
    echo "Optional (recommended for full features):"
    echo "2. Set NEXT_PUBLIC_MAPBOX_TOKEN for maps"
    echo "3. Set PINECONE_API_KEY for vector search"
    echo ""
    read -p "Press Enter after you've configured your .env file..."
else
    echo "✅ .env file exists"
fi

# Validate OpenAI API key
if ! grep -q "^OPENAI_API_KEY=sk-" .env; then
    echo ""
    echo "⚠️  WARNING: OPENAI_API_KEY not configured in .env"
    echo "The system will not work without a valid OpenAI API key."
    echo ""
    read -p "Continue anyway? (y/N) " -n 1 -r
    echo
    if [[ ! $REPLY =~ ^[Yy]$ ]]; then
        exit 1
    fi
fi

echo ""
echo "🚀 Starting Property AI Platform..."
echo ""

# Stop any existing containers
echo "🧹 Cleaning up existing containers..."
docker-compose down 2>/dev/null || true

# Build and start services
echo ""
echo "🔨 Building Docker images (this may take 5-10 minutes on first run)..."
docker-compose build

echo ""
echo "▶️  Starting all services..."
docker-compose up -d

echo ""
echo "⏳ Waiting for services to be healthy..."
sleep 10

# Check service health
check_service() {
    local service=$1
    local url=$2
    local max_attempts=30
    local attempt=1
    
    while [ $attempt -le $max_attempts ]; do
        if curl -f -s "$url" > /dev/null 2>&1; then
            echo "✅ $service is healthy"
            return 0
        fi
        echo "   Waiting for $service... (attempt $attempt/$max_attempts)"
        sleep 2
        attempt=$((attempt + 1))
    done
    
    echo "❌ $service failed to start"
    return 1
}

echo ""
echo "Checking services..."

check_service "PostgreSQL" "http://localhost:5432" || true
check_service "AI Agents" "http://localhost:8000/health" || echo "⚠️  AI Agents not ready yet"
check_service "Backend API" "http://localhost:8080/actuator/health" || echo "⚠️  Backend not ready yet"
check_service "Frontend" "http://localhost:3000" || echo "⚠️  Frontend not ready yet"

echo ""
echo "╔═══════════════════════════════════════════════════════════════╗"
echo "║                                                               ║"
echo "║   🎉 Property AI Platform is Starting!                       ║"
echo "║                                                               ║"
echo "║   Access the application:                                    ║"
echo "║                                                               ║"
echo "║   🌐 Frontend:        http://localhost:3000                  ║"
echo "║   🔧 Backend API:     http://localhost:8080/api              ║"
echo "║   🤖 AI Agents:       http://localhost:8000                  ║"
echo "║   📚 API Docs:        http://localhost:8080/swagger-ui.html  ║"
echo "║                                                               ║"
echo "║   Services may take 1-2 minutes to fully initialize.        ║"
echo "║   Watch logs: docker-compose logs -f                        ║"
echo "║                                                               ║"
echo "╚═══════════════════════════════════════════════════════════════╝"
echo ""

echo "📋 Quick Tips:"
echo "   • View logs: docker-compose logs -f [service-name]"
echo "   • Stop services: docker-compose down"
echo "   • Restart: docker-compose restart [service-name]"
echo "   • Check status: docker-compose ps"
echo ""

echo "🔍 Try these sample queries:"
echo "   • '3 BHK under 1.5 crore near Sarjapur with good schools'"
echo "   • 'Best areas to invest in Bangalore for rental yield'"
echo "   • 'Villa near Whitefield within 45 mins commute'"
echo ""

read -p "Open frontend in browser? (Y/n) " -n 1 -r
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
echo "✨ Enjoy discovering properties with AI!"
