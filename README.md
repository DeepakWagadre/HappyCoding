# Agentic AI Property Discovery Platform - Bangalore

## 🏛️ Architecture Overview

This is a production-ready, end-to-end Agentic AI platform for intelligent property discovery in Bangalore. The system uses multi-agent architecture to process natural language queries and deliver transparent, explainable property recommendations.

### System Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                        Frontend Layer                        │
│    Next.js 14 + React 18 + TypeScript + Tailwind CSS       │
│           SSE Streaming + Real-time Updates                 │
└─────────────────────┬───────────────────────────────────────┘
                      │
                      │ REST + SSE
                      ▼
┌─────────────────────────────────────────────────────────────┐
│                      Backend Layer                           │
│      Java 21 + Spring Boot 3.x + Virtual Threads           │
│          REST APIs + Server-Sent Events                      │
└─────────────────────┬───────────────────────────────────────┘
                      │
                      │ HTTP/JSON
                      ▼
┌─────────────────────────────────────────────────────────────┐
│                    AI Agent Layer                            │
│         Python 3.11 + LangGraph + OpenAI                    │
│                                                              │
│  ┌──────────────────────────────────────────────────────┐  │
│  │  • Location Intelligence Agent                       │  │
│  │  • Price Fairness Agent                             │  │
│  │  • Commute & Connectivity Agent                     │  │
│  │  • Builder & Legal Risk Agent                       │  │
│  │  • Rental Yield & Appreciation Agent                │  │
│  │  • Livability & Family Suitability Agent            │  │
│  └──────────────────────────────────────────────────────┘  │
└─────────────────────┬───────────────────────────────────────┘
                      │
                      │
        ┌─────────────┼─────────────┬──────────────┐
        ▼             ▼             ▼              ▼
   ┌─────────┐  ┌──────────┐  ┌────────┐   ┌──────────┐
   │PostgreSQL│  │Elasticsearch│ │ Redis  │   │Vector DB │
   │  (Data)  │  │(Geo+Search)│ │(Cache) │   │(Pinecone)│
   └──────────┘  └──────────┘  └────────┘   └──────────┘
```

### Technology Stack

#### Backend
- **Runtime:** Java 21 with Virtual Threads (Project Loom)
- **Framework:** Spring Boot 3.x
- **APIs:** REST + Server-Sent Events (SSE)
- **Build:** Maven

#### AI/Agent Layer
- **Language:** Python 3.11
- **Framework:** LangGraph (Agentic workflow orchestration)
- **LLM:** OpenAI GPT-4 (configurable)
- **Design:** Stateless, idempotent agents with confidence scoring

#### Data Layer
- **Primary Database:** PostgreSQL 15 (property data, transactions)
- **Search Engine:** Elasticsearch 8.x (geo-queries, filters, ranking)
- **Cache:** Redis 7.x (session, computed scores)
- **Vector DB:** Pinecone (semantic search, embeddings)

#### Frontend
- **Framework:** Next.js 14 (App Router)
- **UI Library:** React 18 + TypeScript
- **Styling:** Tailwind CSS
- **State Management:** React Query (server) + Zustand (client)
- **Streaming:** SSE-based progressive UI updates
- **Maps:** Mapbox GL JS

#### Infrastructure
- **Containerization:** Docker + Docker Compose
- **Configuration:** Environment-based (.env)
- **Monitoring:** Structured logging, health checks

---

## 🚀 Quick Start (Local Development)

### Prerequisites

- **Docker Desktop** (or Docker Engine + Docker Compose)
- **Node.js 18+** (for frontend development)
- **Java 21** (for backend development)
- **Python 3.11** (for AI agent development)
- **OpenAI API Key** (for LLM integration)

### Environment Setup

1. **Clone and navigate:**
```bash
cd /path/to/ruby-juno
```

2. **Create environment file:**
```bash
cp .env.example .env
```

3. **Configure `.env`:**
```env
# OpenAI Configuration
OPENAI_API_KEY=your_openai_api_key_here

# Database Configuration
POSTGRES_USER=propertyai
POSTGRES_PASSWORD=propertyai_secret_2024
POSTGRES_DB=bangalore_properties

# Redis Configuration
REDIS_PASSWORD=redis_secret_2024

# Elasticsearch Configuration
ELASTIC_PASSWORD=elastic_secret_2024

# Pinecone Configuration (Optional - can use local alternative)
PINECONE_API_KEY=your_pinecone_key_here
PINECONE_ENV=us-west1-gcp

# Application Configuration
BACKEND_PORT=8080
AI_AGENT_PORT=8000
FRONTEND_PORT=3000

# Environment
NODE_ENV=development
SPRING_PROFILES_ACTIVE=local
```

### Run the Complete System

**Single command to start everything:**

```bash
docker-compose up --build
```

This will start:
- PostgreSQL on `localhost:5432`
- Elasticsearch on `localhost:9200`
- Redis on `localhost:6379`
- Backend API on `localhost:8080`
- AI Agent Service on `localhost:8000`
- Frontend UI on `localhost:3000`

### Access the Application

- **Frontend UI:** http://localhost:3000
- **Backend API:** http://localhost:8080/api
- **AI Agent API:** http://localhost:8000
- **API Documentation:** http://localhost:8080/swagger-ui.html

### Sample Queries to Try

```
"3 BHK under 1.5 crore near Sarjapur with good schools"
"Best areas to invest in Bangalore for rental yield"
"Villa near Whitefield within 45 mins commute to Koramangala"
"Budget apartment near Electronic City with metro connectivity"
"Luxury penthouse in Indiranagar for end use"
```

---

## 📁 Project Structure

```
ruby-juno/
├── backend/                        # Java Spring Boot Backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/propertyai/
│   │   │   │   ├── controller/    # REST Controllers
│   │   │   │   ├── service/       # Business Logic
│   │   │   │   ├── repository/    # Data Access
│   │   │   │   ├── model/         # Domain Models
│   │   │   │   ├── dto/           # Data Transfer Objects
│   │   │   │   └── config/        # Configuration
│   │   │   └── resources/
│   │   │       ├── application.yml
│   │   │       └── data/          # Sample Data
│   │   └── test/
│   ├── pom.xml
│   └── Dockerfile
│
├── ai-agents/                      # Python AI Agent Services
│   ├── src/
│   │   ├── agents/                # Individual Agents
│   │   │   ├── location_agent.py
│   │   │   ├── price_agent.py
│   │   │   ├── commute_agent.py
│   │   │   ├── legal_agent.py
│   │   │   ├── yield_agent.py
│   │   │   └── livability_agent.py
│   │   ├── orchestrator/          # LangGraph Orchestration
│   │   │   ├── graph.py
│   │   │   └── state.py
│   │   ├── services/
│   │   │   ├── intent_parser.py   # NL -> Structured Intent
│   │   │   ├── scorer.py          # Scoring Engine
│   │   │   └── recommender.py     # Final Recommendations
│   │   ├── models/
│   │   │   └── schemas.py         # Pydantic Models
│   │   ├── api/
│   │   │   └── main.py            # FastAPI Server
│   │   └── utils/
│   ├── requirements.txt
│   ├── Dockerfile
│   └── tests/
│
├── frontend/                       # Next.js Frontend
│   ├── src/
│   │   ├── app/                   # App Router
│   │   │   ├── page.tsx           # Home Page
│   │   │   ├── layout.tsx
│   │   │   └── api/               # API Routes
│   │   ├── components/
│   │   │   ├── SearchBar.tsx
│   │   │   ├── IntentPreview.tsx
│   │   │   ├── StreamingProgress.tsx
│   │   │   ├── PropertyCard.tsx
│   │   │   ├── ScoreBreakdown.tsx
│   │   │   ├── MapView.tsx
│   │   │   └── CompareView.tsx
│   │   ├── lib/
│   │   │   ├── api.ts            # API Client
│   │   │   └── sse.ts            # SSE Streaming
│   │   ├── store/                # Zustand Store
│   │   └── types/                # TypeScript Types
│   ├── public/
│   ├── package.json
│   ├── tsconfig.json
│   ├── tailwind.config.ts
│   ├── next.config.js
│   └── Dockerfile
│
├── docker-compose.yml             # Complete Stack Orchestration
├── .env.example                   # Environment Template
├── .gitignore
└── README.md
```

---

## 🧠 AI Agent Architecture

### Intent Extraction

Every query is first converted to structured JSON:

```json
{
  "transaction_type": "buy | rent",
  "property_type": "apartment | villa | plot | independent_house | commercial",
  "budget": {
    "min": 5000000,
    "max": 15000000
  },
  "preferred_locations": ["Sarjapur", "HSR Layout"],
  "amenities": {
    "must_have": ["parking", "security"],
    "nice_to_have": ["gym", "pool"]
  },
  "commute": {
    "office_location": "Koramangala",
    "max_travel_time_minutes": 45
  },
  "family_context": {
    "kids": true,
    "school_priority": true,
    "senior_citizens": false
  },
  "investment_goal": "end_use | rental_yield | appreciation | mixed",
  "confidence_level": "high | medium | low"
}
```

### Multi-Agent System

Each agent is **stateless** and **parallelizable**:

1. **Location Intelligence Agent**
   - Analyzes neighborhood quality
   - Infrastructure development
   - Proximity to amenities
   - Returns: `location_score` (0-10)

2. **Price Fairness Agent**
   - Compares against market rates
   - Historical price trends
   - Value for money assessment
   - Returns: `price_fairness_score` (0-10)

3. **Commute & Connectivity Agent**
   - Travel time calculations
   - Public transport access
   - Traffic patterns
   - Returns: `commute_score` (0-10)

4. **Builder & Legal Risk Agent**
   - Builder reputation
   - Legal clearances
   - RERA compliance
   - Returns: `risk_penalty` (-5 to 0)

5. **Rental Yield & Appreciation Agent**
   - ROI projections
   - Growth potential
   - Market trends
   - Returns: `growth_potential_score` (0-10)

6. **Livability & Family Suitability Agent**
   - Schools, hospitals nearby
   - Safety metrics
   - Community quality
   - Returns: `amenities_match_score` (0-10)

### Scoring & Ranking

**Final Score Calculation:**

```
final_score = (
  location_score * w1 +
  price_fairness_score * w2 +
  amenities_match_score * w3 +
  commute_score * w4 +
  growth_potential_score * w5 +
  risk_penalty
)
```

**Weights vary based on intent:**
- End-use priority: Higher weight on livability, commute
- Investment priority: Higher weight on growth potential, price fairness

---

## 📡 API Contracts

### Backend API

**POST** `/api/search`

Request:
```json
{
  "query": "3 BHK under 1.5 crore near Sarjapur with good schools"
}
```

Response (SSE Stream):
```
event: intent_extracted
data: {"transaction_type": "buy", "budget": {"max": 15000000}, ...}

event: agent_progress
data: {"agent": "Location Intelligence", "status": "analyzing"}

event: agent_complete
data: {"agent": "Location Intelligence", "score": 8.5}

event: recommendations
data: {"top_recommendations": [...], "alternatives": [...]}

event: complete
data: {"status": "success"}
```

**GET** `/api/properties/{id}`

Response:
```json
{
  "property_id": "PROP-BLR-001",
  "name": "Prestige Lakeside Habitat",
  "location": "Varthur, Bangalore",
  "price": 12500000,
  "property_type": "apartment",
  "bedrooms": 3,
  "area_sqft": 1850,
  "amenities": ["gym", "pool", "clubhouse"],
  "builder": "Prestige Group",
  "scores": {...}
}
```

### AI Agent API

**POST** `/extract-intent`

Request:
```json
{
  "query": "Looking for a villa near Whitefield"
}
```

Response:
```json
{
  "intent": {...},
  "confidence": "medium",
  "clarifications_needed": ["budget range", "transaction type"]
}
```

**POST** `/analyze`

Request:
```json
{
  "intent": {...},
  "properties": [...]
}
```

Response:
```json
{
  "results": [
    {
      "property_id": "...",
      "scores": {...},
      "explanations": [...]
    }
  ]
}
```

---

## 🔧 Development

### Running Individual Services

**Backend:**
```bash
cd backend
./mvnw spring-boot:run
```

**AI Agents:**
```bash
cd ai-agents
pip install -r requirements.txt
uvicorn src.api.main:app --reload --port 8000
```

**Frontend:**
```bash
cd frontend
npm install
npm run dev
```

### Running Tests

**Backend:**
```bash
cd backend
./mvnw test
```

**AI Agents:**
```bash
cd ai-agents
pytest
```

**Frontend:**
```bash
cd frontend
npm test
```

### Database Migrations

Sample data is automatically loaded on first startup. To reload:

```bash
docker-compose exec backend curl -X POST http://localhost:8080/api/admin/reload-data
```

---

## 🚢 Moving to Production

### Architecture Changes Needed

1. **Database:**
   - Use managed PostgreSQL (AWS RDS, Google Cloud SQL)
   - Enable connection pooling
   - Set up read replicas

2. **Search:**
   - Use managed Elasticsearch (AWS OpenSearch, Elastic Cloud)
   - Configure proper sharding and replication

3. **Cache:**
   - Use managed Redis (AWS ElastiCache, Redis Cloud)
   - Enable persistence and clustering

4. **Vector DB:**
   - Use Pinecone production tier
   - Or self-hosted Weaviate/Milvus with backups

5. **Backend:**
   - Deploy to Kubernetes or ECS
   - Enable auto-scaling based on CPU/memory
   - Set up proper health checks and readiness probes

6. **AI Agents:**
   - Deploy as separate service with auto-scaling
   - Implement request queuing for high load
   - Add circuit breakers and fallbacks

7. **Frontend:**
   - Deploy to Vercel/Netlify or CDN
   - Enable edge caching
   - Use production API endpoints

8. **Monitoring:**
   - Add Prometheus + Grafana for metrics
   - Implement distributed tracing (Jaeger/Zipkin)
   - Set up alerts for failures and latency

9. **Security:**
   - Enable HTTPS/TLS everywhere
   - Implement rate limiting
   - Add authentication and authorization
   - Use secrets management (AWS Secrets Manager, Vault)

10. **CI/CD:**
    - Set up GitHub Actions / GitLab CI
    - Automated testing pipeline
    - Blue-green or canary deployments

### Production Checklist

- [ ] Environment-specific configurations
- [ ] Secrets management implemented
- [ ] Logging aggregation (ELK, Datadog)
- [ ] Monitoring and alerting
- [ ] Backup and disaster recovery
- [ ] Load testing completed
- [ ] Security audit passed
- [ ] API rate limiting enabled
- [ ] CDN configured for static assets
- [ ] Database indexes optimized
- [ ] Cost optimization reviewed

---

## 🧪 Sample Data

The system includes **50+ real Bangalore properties** across:

- **Locations:** Whitefield, Sarjapur, Koramangala, Indiranagar, Electronic City, HSR Layout, Hebbal, Yelahanka, JP Nagar, Bannerghatta Road
- **Types:** Apartments, Villas, Independent Houses, Plots, Commercial Spaces
- **Builders:** Prestige, Brigade, Sobha, Godrej, Puravankara, Embassy
- **Price Range:** ₹30L - ₹5Cr

All data is **synthetic but realistic**, based on actual Bangalore real estate patterns.

---

## 🤝 Contributing

This is a production-ready starter template. To extend:

1. Add more agents (e.g., Sustainability Agent, Pet-Friendly Agent)
2. Integrate real estate APIs (99acres, MagicBricks, Housing.com)
3. Add user authentication and saved searches
4. Implement personalized recommendations based on user history
5. Add property comparison and shortlisting features

---

## 📄 License

MIT License - See LICENSE file for details

---

## 🆘 Support

For issues or questions:
- Check logs: `docker-compose logs [service-name]`
- Verify environment variables in `.env`
- Ensure all services are healthy: `docker-compose ps`
- Review API documentation at http://localhost:8080/swagger-ui.html

---

**Built with ❤️ for Bangalore Property Seekers**

This system demonstrates production-grade engineering practices:
- Clean architecture
- SOLID principles
- Transparent AI decisions
- Scalable design
- Observable and maintainable
