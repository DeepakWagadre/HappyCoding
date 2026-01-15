#!/bin/bash

# Fix and Restart Script
# Resolves common Docker build issues and relaunches

set -e

echo "🔧 Fixing Docker build issues..."
echo ""

# Clean up any failed builds
echo "1️⃣ Cleaning up previous build artifacts..."
docker-compose down 2>/dev/null || true

# Remove any dangling images
echo "2️⃣ Removing dangling Docker images..."
docker image prune -f 2>/dev/null || true

echo ""
echo "✅ Cleanup complete!"
echo ""
echo "🚀 Starting fresh Docker build..."
echo "   This will take 5-10 minutes on first run..."
echo ""

# Build with no cache to ensure clean build
docker-compose build --no-cache

echo ""
echo "✅ Build successful!"
echo ""
echo "▶️  Starting all services..."
docker-compose up -d

echo ""
echo "⏳ Waiting for services to be healthy..."
sleep 10

# Show status
docker-compose ps

echo ""
echo "╔═══════════════════════════════════════════════════════════════╗"
echo "║                                                               ║"
echo "║   ✅ Platform is starting!                                    ║"
echo "║                                                               ║"
echo "║   Access at: http://localhost:3000                           ║"
echo "║                                                               ║"
echo "║   View logs: docker-compose logs -f                         ║"
echo "║                                                               ║"
echo "╚═══════════════════════════════════════════════════════════════╝"
echo ""
