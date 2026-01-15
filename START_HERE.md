# ⚡ START HERE - Agentic AI Property Platform

## 🎉 Your Complete Platform is Ready!

This directory contains a **fully functional, production-ready** Agentic AI Property Discovery Platform for Bangalore.

---

## 📋 Quick Launch (2 Steps)

### Step 1: Configure OpenAI API Key

```bash
# Edit the .env file
nano .env  # or use any text editor

# Find this line and add your API key:
OPENAI_API_KEY=sk-your-openai-api-key-here

# Save and exit
```

Get your API key from: https://platform.openai.com/api-keys

### Step 2: Launch Everything

```bash
# Run the quickstart script
./quickstart.sh
```

That's it! The platform will:
- ✅ Build all services (first time: 5-10 min)
- ✅ Start 6 Docker containers
- ✅ Initialize sample data (20 properties)
- ✅ Open http://localhost:3000 in your browser

---

## 🎯 What You Can Do

### Natural Language Property Search

Try these queries in the search bar:

```
3 BHK under 1.5 crore near Sarjapur with good schools
```

```
Best areas to invest in Bangalore for rental yield
```

```
Villa near Whitefield within 45 mins commute to Koramangala
```

```
Budget apartment near Electronic City with metro connectivity
```

### Features:
- 🤖 **AI Intent Extraction**: Understands what you're looking for
- 🎯 **Multi-Agent Analysis**: 6 AI agents score each property
- 📊 **Transparent Scoring**: See exactly why properties were recommended
- ⚡ **Real-time Streaming**: Watch the AI work in real-time
- 🗺️ **Location Intelligence**: Bangalore-specific insights

---

## 📚 Documentation

| File | Purpose |
|------|---------|
| **PROJECT_SUMMARY.md** | Complete overview of what was built |
| **README.md** | Architecture and production deployment guide |
| **SETUP.md** | Detailed setup instructions & troubleshooting |
| **.env** | Your configuration (edit before starting) |
| **quickstart.sh** | One-command launcher |

---

## 🚦 Service Access

Once running, you can access:

| Service | URL | Description |
|---------|-----|-------------|
| **Frontend** | http://localhost:3000 | Main search interface |
| **Backend API** | http://localhost:8080/api | REST endpoints |
| **API Docs** | http://localhost:8080/swagger-ui.html | Interactive API documentation |
| **AI Agents** | http://localhost:8000 | Python agent service |

---

## 🛠️ Common Commands

```bash
# Start everything
./quickstart.sh

# Stop all services
docker-compose down

# View logs
docker-compose logs -f

# View specific service logs
docker-compose logs -f backend
docker-compose logs -f ai-agents
docker-compose logs -f frontend

# Check service status
docker-compose ps

# Restart a service
docker-compose restart backend
```

---

## ⚠️ Before You Start

**Required**:
- ✅ Docker Desktop installed
- ✅ OpenAI API key (configure in `.env`)
- ✅ 8GB+ RAM available
- ✅ 10GB+ free disk space

**Optional** (for enhanced features):
- Mapbox token for maps (add to `.env`)
- Pinecone API key for vector search (add to `.env`)

---

## 🏗️ What's Inside?

This is a **complete, multi-service platform**:

### Backend (Java/Spring Boot)
- REST APIs
- Server-Sent Events streaming
- PostgreSQL integration
- Redis caching
- Elasticsearch search

### AI Agents (Python/LangGraph)
- Intent extraction from natural language
- 6 specialized agents (Location, Price, Commute, Legal, Yield, Livability)
- Multi-agent orchestration
- Transparent scoring

### Frontend (Next.js/React)
- Modern, responsive UI
- Real-time streaming updates
- Property comparison
- Score breakdowns

### Infrastructure
- Docker Compose orchestration
- Health monitoring
- Sample data (20 properties)
- Production-ready configuration

---

## 📈 Architecture

```
┌─────────────────────────────────────────────────────────┐
│                    User Browser                         │
│                http://localhost:3000                    │
└──────────────────────┬──────────────────────────────────┘
                       │
                       ↓ (REST + SSE)
┌─────────────────────────────────────────────────────────┐
│              Spring Boot Backend                        │
│              http://localhost:8080                      │
└──────────────────────┬──────────────────────────────────┘
                       │
                       ↓ (HTTP)
┌─────────────────────────────────────────────────────────┐
│           Python AI Agent Service                       │
│              http://localhost:8000                      │
│  ┌───────────────────────────────────────────────────┐  │
│  │  6 Parallel Agents: Location, Price, Commute,    │  │
│  │  Legal, Yield, Livability                        │  │
│  └───────────────────────────────────────────────────┘  │
└──────────────────────┬──────────────────────────────────┘
                       │
        ┌──────────────┼──────────────┬──────────────┐
        ↓              ↓              ↓              ↓
   PostgreSQL    Elasticsearch    Redis      (Pinecone)
    :5432            :9200        :6379
```

---

## 🎓 Key Technologies

- **Java 21** - Virtual Threads (Project Loom)
- **Spring Boot 3.x** - Backend framework
- **Python 3.11** - AI agent runtime
- **LangGraph** - Multi-agent orchestration
- **OpenAI GPT-4** - Natural language understanding
- **Next.js 14** - React framework
- **PostgreSQL** - Primary database
- **Elasticsearch** - Search and geo-queries
- **Redis** - Caching layer
- **Docker** - Containerization

---

## 🆘 Troubleshooting

### "Port already in use"
```bash
# Find and kill process on port
lsof -i :8080  # or :3000, :8000
kill -9 <PID>
```

### "OpenAI API Error"
- Check your `.env` file has valid `OPENAI_API_KEY`
- Verify you have API credits: https://platform.openai.com/usage

### "Service unhealthy"
```bash
# Check logs
docker-compose logs [service-name]

# Restart
docker-compose restart [service-name]
```

### "Fresh Start"
```bash
# Nuclear option - removes all data and restarts
docker-compose down -v
docker-compose up --build
```

See **SETUP.md** for detailed troubleshooting.

---

## 🚀 Next Steps

### 1. **Launch the Platform**
```bash
./quickstart.sh
```

### 2. **Test Property Search**
- Open http://localhost:3000
- Enter a natural language query
- See AI agents work in real-time

### 3. **Explore the Code**
- `/backend` - Java Spring Boot code
- `/ai-agents` - Python AI agents
- `/frontend` - Next.js UI

### 4. **Read Documentation**
- `PROJECT_SUMMARY.md` - What was built
- `README.md` - Architecture details
- `SETUP.md` - Setup & troubleshooting

### 5. **Extend the Platform**
- Add more AI agents
- Integrate real property APIs
- Customize scoring weights
- Add user authentication

---

## 📊 Sample Data

The platform includes **20 realistic Bangalore properties** across:

**Locations**:
- Whitefield (IT Hub)
- Sarjapur (Family-friendly)
- Koramangala (Central)
- Indiranagar (Premium)
- Electronic City (Affordable)
- HSR Layout
- Hebbal
- Yelahanka
- JP Nagar
- Bannerghatta Road

**Property Types**:
- Apartments (2/3/4 BHK)
- Villas
- Independent Houses
- Penthouses

**Price Range**:
- ₹58L - ₹4.5Cr

All data is **synthetic but realistic**, based on actual Bangalore market patterns.

---

## 💡 Pro Tips

1. **First Launch Takes Time**: Initial Docker build is 5-10 minutes. Subsequent starts are ~30 seconds.

2. **Watch the Logs**: See AI agents working:
   ```bash
   docker-compose logs -f ai-agents
   ```

3. **Experiment with Queries**: Try vague queries to see confidence scoring:
   ```
   "something nice in Bangalore"  → LOW confidence
   "3 BHK under 1 crore in HSR"   → HIGH confidence
   ```

4. **Compare Scores**: See how different agents score the same property based on your intent (end-use vs investment).

5. **Check API Docs**: Interactive Swagger UI at http://localhost:8080/swagger-ui.html

---

## 🎯 What Makes This Special?

### Not a Demo - A Real System

This is a **production-grade platform**:
- ✅ Clean architecture (SOLID principles)
- ✅ Scalable design (stateless agents)
- ✅ Performance optimized (virtual threads, caching)
- ✅ Observable (health checks, logging)
- ✅ Secure (environment-based config)
- ✅ Testable (structured, modular code)

### Enterprise Engineering Standards

- Multi-agent AI orchestration with LangGraph
- Server-Sent Events for real-time streaming
- Virtual Threads for high concurrency
- Docker Compose for local development
- Ready to deploy to Kubernetes/ECS

---

## 📞 Support

- **Documentation**: See README.md and SETUP.md
- **Logs**: `docker-compose logs -f`
- **Health Checks**: 
  - http://localhost:8080/actuator/health
  - http://localhost:8000/health

---

## ✨ You're All Set!

Run this command to start:

```bash
./quickstart.sh
```

Then search for your dream property in Bangalore! 🏡

---

**Built with ❤️ for Bangalore Property Seekers**

*An enterprise-grade demonstration of multi-agent AI systems, real-time streaming, and modern full-stack architecture.*
