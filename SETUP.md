# 🚀 QUICK START GUIDE - Property AI Platform

## Prerequisites

Before running the platform, ensure you have:

1. **Docker Desktop** (v20.10+) - [Download here](https://www.docker.com/products/docker-desktop)
2. **OpenAI API Key** - [Get from OpenAI](https://platform.openai.com/api-keys)
3. **Minimum 8GB RAM** available for Docker
4. **10GB free disk space**

## Step-by-Step Launch

### Option 1: Automated Quickstart (Recommended)

```bash
# 1. Clone/Navigate to the repository
cd /path/to/ruby-juno

# 2. Run the quickstart script
./quickstart.sh
```

The script will:
- ✅ Check all prerequisites
- ✅ Create `.env` from template
- ✅ Build all Docker images
- ✅ Start all services
- ✅ Verify health of each service
- ✅ Open the frontend in your browser

### Option 2: Manual Setup

```bash
# 1. Create environment file
cp .env.example .env

# 2. Edit .env and add your OpenAI API key
# Required:
OPENAI_API_KEY=sk-your-key-here

# 3. Build and start all services
docker-compose up --build
```

## Service URLs

Once running, access these URLs:

| Service | URL | Description |
|---------|-----|-------------|
| **Frontend** | http://localhost:3000 | Main user interface |
| **Backend API** | http://localhost:8080/api | REST endpoints |
| **API Documentation** | http://localhost:8080/swagger-ui.html | Interactive API docs |
| **AI Agents** | http://localhost:8000 | Python agent service |
| **Health Check** | http://localhost:8080/actuator/health | Backend health |

## First-Time Setup (5-10 minutes)

### What happens on first run:

1. **Docker Image Build** (3-5 min)
   - Backend: Java 21 + Spring Boot
   - AI Agents: Python 3.11 + LangGraph
   - Frontend: Next.js 14 build
   
2. **Database Initialization** (30 sec)
   - PostgreSQL container starts
   - Sample Bangalore property data loaded (20 properties)
   
3. **Service Startup** (1-2 min)
   - Elasticsearch cluster formation
   - Redis cache warming
   - Spring Boot application startup
   - Python FastAPI server startup
   - Next.js frontend compilation

### Monitoring Startup

Watch logs for all services:
```bash
docker-compose logs -f
```

Watch specific service:
```bash
docker-compose logs -f backend
docker-compose logs -f ai-agents
docker-compose logs -f frontend
```

Check service status:
```bash
docker-compose ps
```

Expected output when healthy:
```
NAME                    STATUS              PORTS
property-ai-postgres    Up (healthy)        5432
property-ai-elasticsearch Up (healthy)      9200
property-ai-redis       Up (healthy)        6379
property-ai-agents      Up (healthy)        8000
property-ai-backend     Up (healthy)        8080
property-ai-frontend    Up (healthy)        3000
```

## Testing the System

### Sample Queries to Try:

1. **Family Home Search:**
   ```
   3 BHK under 1.5 crore near Sarjapur with good schools
   ```

2. **Investment Search:**
   ```
   Best areas to invest in Bangalore for rental yield
   ```

3. **Commute-Focused:**
   ```
   Villa near Whitefield within 45 mins commute to Koramangala
   ```

4. **Budget Search:**
   ```
   Budget apartment near Electronic City with metro connectivity
   ```

### Expected Response Time:

- **Intent Extraction**: 2-3 seconds
- **Property Matching**: 1 second
- **AI Agent Analysis**: 5-10 seconds
- **Total Search Time**: 8-15 seconds

## Troubleshooting

### Common Issues:

#### 1. "Port already in use"
```bash
# Find what's using the port
lsof -i :8080  # or :3000, :8000

# Stop conflicting service or change port in .env
```

#### 2. "OpenAI API Error"
- Verify your API key in `.env`
- Check you have credits: https://platform.openai.com/usage
- Ensure no spaces around the `=` sign

#### 3. "Service unhealthy"
```bash
# Check logs for specific service
docker-compose logs backend

# Restart specific service
docker-compose restart backend

# Full restart
docker-compose down && docker-compose up -d
```

#### 4. "Database connection failed"
```bash
# Wait 30 seconds for PostgreSQL to initialize
# Check PostgreSQL logs
docker-compose logs postgres

# Verify PostgreSQL is running
docker-compose ps postgres
```

#### 5. "Frontend won't load"
```bash
# Check if build completed
docker-compose logs frontend

# Rebuild frontend only
docker-compose up --build frontend
```

### Still Having Issues?

1. **Check all containers are running:**
   ```bash
   docker-compose ps
   ```

2. **Check health endpoints:**
   ```bash
   curl http://localhost:8080/actuator/health
   curl http://localhost:8000/health
   ```

3. **View full logs:**
   ```bash
   docker-compose logs --tail=100
   ```

4. **Nuclear option (clean restart):**
   ```bash
   docker-compose down -v  # Warning: deletes all data
   docker-compose up --build
   ```

## Stopping the Platform

```bash
# Stop all services (data preserved)
docker-compose down

# Stop and remove all data
docker-compose down -v
```

## Development Mode

### Run Individual Services Locally:

**Backend (requires Java 21):**
```bash
cd backend
./mvnw spring-boot:run
```

**AI Agents (requires Python 3.11):**
```bash
cd ai-agents
pip install -r requirements.txt
uvicorn src.api.main:app --reload --port 8000
```

**Frontend (requires Node.js 18+):**
```bash
cd frontend
npm install
npm run dev
```

## System Requirements

### Minimum:
- **CPU**: 4 cores
- **RAM**: 8GB
- **Disk**: 10GB free

### Recommended:
- **CPU**: 8 cores
- **RAM**: 16GB
- **Disk**: 20GB free
- **Internet**: Stable connection for OpenAI API

## Architecture Overview

```
User Browser
    ↓
Next.js Frontend (Port 3000)
    ↓ [REST + SSE]
Spring Boot Backend (Port 8080)
    ↓ [HTTP]
Python AI Agents (Port 8000)
    ↓
┌─────────────┬──────────────┬────────┬──────────┐
PostgreSQL    Elasticsearch   Redis    (Pinecone)
```

## Data Flow

1. **User enters natural language query** → Frontend
2. **Frontend sends to Backend** → Spring Boot
3. **Backend calls AI Agent** → Python service extracts intent
4. **Backend queries database** → PostgreSQL finds candidates
5. **Backend sends properties to AI Agents** → Multi-agent analysis
6. **Agents return scored properties** → Backend
7. **Backend streams results** → Frontend via SSE
8. **User sees recommendations** → Results displayed with scores

## Next Steps

### For Users:
- Explore property search with different queries
- Compare properties using the score breakdown
- Try different search criteria

### For Developers:
- Read `/backend/README.md` for backend architecture
- Read `/ai-agents/README.md` for AI agent details
- Read `/frontend/README.md` for frontend structure
- Review API documentation at http://localhost:8080/swagger-ui.html

### For Production Deployment:
- See "Moving to Production" section in main README.md
- Configure proper secrets management
- Set up monitoring and logging
- Enable authentication and rate limiting
- Use managed databases and services

## Support

- **Documentation**: README.md in repository root
- **API Docs**: http://localhost:8080/swagger-ui.html (when running)
- **Logs**: `docker-compose logs -f`

---

**Built with ❤️ for Bangalore Property Seekers**

This is a production-ready starter platform demonstrating:
- ✅ Clean architecture principles
- ✅ Multi-agent AI system
- ✅ Real-time streaming
- ✅ Transparent scoring
- ✅ Modern tech stack
- ✅ Docker-based deployment
