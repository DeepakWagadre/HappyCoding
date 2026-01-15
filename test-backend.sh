#!/bin/bash

# Backend API Testing Script

echo "==================================="
echo "Testing Backend APIs"
echo "==================================="
echo ""

BASE_URL="http://localhost:8080"

# Test 1: Health Check
echo "1. Testing Health Endpoint..."
HEALTH=$(curl -s "$BASE_URL/actuator/health")
STATUS=$(echo $HEALTH | python3 -c "import sys, json; print(json.load(sys.stdin)['status'])" 2>/dev/null || echo "ERROR")
echo "   Status: $STATUS"
if [ "$STATUS" == "UP" ]; then
    echo "   ✅ Health check PASSED"
else
    echo "   ❌ Health check FAILED"
    echo "   Response: $HEALTH"
fi
echo ""

# Test 2: Elasticsearch Health
echo "2. Testing Elasticsearch Connection..."
ES_STATUS=$(echo $HEALTH | python3 -c "import sys, json; print(json.load(sys.stdin)['components']['elasticsearch']['status'])" 2>/dev/null || echo "ERROR")
echo "   Elasticsearch Status: $ES_STATUS"
if [ "$ES_STATUS" == "UP" ]; then
    echo "   ✅ Elasticsearch connection PASSED"
else
    echo "   ❌ Elasticsearch connection FAILED"
fi
echo ""

# Test 3: Database Health
echo "3. Testing Database Connection..."
DB_STATUS=$(echo $HEALTH | python3 -c "import sys, json; print(json.load(sys.stdin)['components']['db']['status'])" 2>/dev/null || echo "ERROR")
echo "   Database Status: $DB_STATUS"
if [ "$DB_STATUS" == "UP" ]; then
    echo "   ✅ Database connection PASSED"
else
    echo "   ❌ Database connection FAILED"
fi
echo ""

# Test 4: Redis Health
echo "4. Testing Redis Connection..."
REDIS_STATUS=$(echo $HEALTH | python3 -c "import sys, json; print(json.load(sys.stdin)['components']['redis']['status'])" 2>/dev/null || echo "ERROR")
echo "   Redis Status: $REDIS_STATUS"
if [ "$REDIS_STATUS" == "UP" ]; then
    echo "   ✅ Redis connection PASSED"
else
    echo "   ❌ Redis connection FAILED"
fi
echo ""

# Test 5: API Docs
echo "5. Testing API Documentation..."
API_DOCS_CODE=$(curl -s -o /dev/null -w "%{http_code}" "$BASE_URL/swagger-ui.html")
if [ "$API_DOCS_CODE" == "200" ] || [ "$API_DOCS_CODE" == "302" ]; then
    echo "   ✅ API Docs accessible (HTTP $API_DOCS_CODE)"
else
    echo "   ❌ API Docs not accessible (HTTP $API_DOCS_CODE)"
fi
echo ""

# Test 6: AI Agents Connection
echo "6. Testing AI Agents Service..."
AI_HEALTH=$(curl -s "http://localhost:8000/health" 2>/dev/null)
AI_STATUS=$(echo $AI_HEALTH | python3 -c "import sys, json; print(json.load(sys.stdin).get('status', 'UNKNOWN'))" 2>/dev/null ||  echo "ERROR")
if [ "$AI_STATUS" == "healthy" ] || [ "$AI_STATUS" == "ok" ]; then
    echo "   ✅ AI Agents service responding"
else
    echo "   ⚠️  AI Agents response: $AI_STATUS"
fi
echo ""

# Summary
echo "==================================="
echo "Test Summary"
echo "==================================="
echo ""
if [ "$STATUS" == "UP" ] && [ "$ES_STATUS" == "UP" ] && [ "$DB_STATUS" == "UP" ] && [ "$REDIS_STATUS" == "UP" ]; then
    echo "✅ ALL CORE SERVICES ARE UP!"
    echo ""
    echo "Your Property AI Platform is ready to use!"
    echo ""
    echo "🌐 Frontend:  http://localhost:3000"
    echo "🔧 Backend:   http://localhost:8080"
    echo "🤖 AI Agents: http://localhost:8000"
    echo "📚 API Docs:  http://localhost:8080/swagger-ui.html"
    echo ""
else
    echo "❌ Some services are not healthy"
    echo ""
    echo "Full Health Response:"
    echo $HEALTH | python3 -m json.tool 2>/dev/null || echo $HEALTH
fi

echo ""
echo "==================================="
