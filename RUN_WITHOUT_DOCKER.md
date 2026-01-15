# Running Without Docker - Manual Setup Guide

If you don't want to use Docker, you can run each service manually. This requires installing all dependencies on your local machine.

## Prerequisites

You'll need to install:

1. **Java 21** - https://adoptium.net/
2. **Maven 3.9+** - https://maven.apache.org/download.cgi
3. **Python 3.11** - https://www.python.org/downloads/
4. **Node.js 18+** - https://nodejs.org/
5. **PostgreSQL 15** - https://www.postgresql.org/download/
6. **Elasticsearch 8.x** - https://www.elastic.co/downloads/elasticsearch
7. **Redis 7.x** - https://redis.io/download

---

## Step 1: Setup Database (PostgreSQL)

```bash
# Install PostgreSQL (macOS with Homebrew)
brew install postgresql@15

# Start PostgreSQL
brew services start postgresql@15

# Create database
createdb bangalore_properties

# Create user
psql -d postgres -c "CREATE USER propertyai WITH PASSWORD 'propertyai_secret_2024';"
psql -d postgres -c "GRANT ALL PRIVILEGES ON DATABASE bangalore_properties TO propertyai;"

# Load sample data
psql -U propertyai -d bangalore_properties -f backend/src/main/resources/sample-data.sql
```

---

## Step 2: Setup Elasticsearch

```bash
# Install Elasticsearch (macOS with Homebrew)
brew install elasticsearch

# Start Elasticsearch
brew services start elasticsearch

# Verify it's running
curl http://localhost:9200
```

---

## Step 3: Setup Redis

```bash
# Install Redis (macOS with Homebrew)
brew install redis

# Start Redis
brew services start redis

# Verify it's running
redis-cli ping
```

---

## Step 4: Run AI Agent Service (Python)

```bash
cd ai-agents

# Create virtual environment
python3.11 -m venv venv
source venv/bin/activate

# Install dependencies
pip install -r requirements.txt

# Set environment variables
export OPENAI_API_KEY="sk-your-openai-api-key-here"
export POSTGRES_HOST="localhost"
export POSTGRES_PORT="5432"
export POSTGRES_USER="propertyai"
export POSTGRES_PASSWORD="propertyai_secret_2024"
export POSTGRES_DB="bangalore_properties"
export REDIS_HOST="localhost"
export REDIS_PORT="6379"
export ELASTICSEARCH_HOST="localhost"
export ELASTICSEARCH_PORT="9200"

# Run the service
uvicorn src.api.main:app --host 0.0.0.0 --port 8000 --reload
```

**Keep this terminal open** - AI Agents running on http://localhost:8000

---

## Step 5: Run Backend (Java Spring Boot)

Open a **new terminal**:

```bash
cd backend

# Set environment variables
export SPRING_DATASOURCE_URL="jdbc:postgresql://localhost:5432/bangalore_properties"
export SPRING_DATASOURCE_USERNAME="propertyai"
export SPRING_DATASOURCE_PASSWORD="propertyai_secret_2024"
export SPRING_DATA_REDIS_HOST="localhost"
export SPRING_DATA_REDIS_PORT="6379"
export ELASTICSEARCH_HOST="localhost"
export ELASTICSEARCH_PORT="9200"
export AI_AGENT_URL="http://localhost:8000"

# Run the application
./mvnw spring-boot:run
```

**Keep this terminal open** - Backend running on http://localhost:8080

---

## Step 6: Run Frontend (Next.js)

Open a **new terminal**:

```bash
cd frontend

# Install dependencies
npm install

# Set environment variable
export NEXT_PUBLIC_BACKEND_URL="http://localhost:8080"

# Run development server
npm run dev
```

**Keep this terminal open** - Frontend running on http://localhost:3000

---

## Access the Application

Open your browser: **http://localhost:3000**

---

## Stopping Services

### Stop Background Services:
```bash
brew services stop postgresql@15
brew services stop elasticsearch
brew services stop redis
```

### Stop Running Services:
Press `Ctrl+C` in each terminal running:
- AI Agents
- Backend
- Frontend

---

## Environment Variables Summary

Create a file `.env.local` in project root:

```bash
# Database
POSTGRES_HOST=localhost
POSTGRES_PORT=5432
POSTGRES_USER=propertyai
POSTGRES_PASSWORD=propertyai_secret_2024
POSTGRES_DB=bangalore_properties

# Elasticsearch
ELASTICSEARCH_HOST=localhost
ELASTICSEARCH_PORT=9200

# Redis
REDIS_HOST=localhost
REDIS_PORT=6379

# OpenAI
OPENAI_API_KEY=sk-your-key-here

# Services
AI_AGENT_URL=http://localhost:8000
NEXT_PUBLIC_BACKEND_URL=http://localhost:8080
```

---

## Troubleshooting

### Database Connection Issues
```bash
# Check if PostgreSQL is running
brew services list | grep postgres

# Check connection
psql -U propertyai -d bangalore_properties -c "SELECT 1;"
```

### Elasticsearch Issues
```bash
# Check if running
curl http://localhost:9200

# View logs
tail -f /usr/local/var/log/elasticsearch.log
```

### Redis Issues
```bash
# Check if running
redis-cli ping

# Should return: PONG
```

### Port Conflicts
```bash
# Check what's using a port
lsof -i :8080  # or :3000, :8000, :5432, :9200, :6379

# Kill process if needed
kill -9 <PID>
```

---

## Why Docker is Easier

Running manually requires:
- ❌ Installing 7 different services
- ❌ Managing 3 separate terminals
- ❌ Configuring environment variables in multiple places
- ❌ Manual service lifecycle management
- ❌ Potential port conflicts
- ❌ OS-specific installation steps

With Docker:
- ✅ Single command: `docker-compose up`
- ✅ All services managed automatically
- ✅ Consistent across all operating systems
- ✅ Easy cleanup: `docker-compose down`
- ✅ Isolated environment

---

**Recommendation**: Install Docker Desktop for the best experience!
