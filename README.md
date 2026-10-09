# CodeSage

[![Next.js](https://img.shields.io/badge/Next.js-16-black?logo=next.js)](https://nextjs.org/)
[![TypeScript](https://img.shields.io/badge/TypeScript-5-blue?logo=typescript)](https://www.typescriptlang.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.x-brightgreen?logo=springboot)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-336791?logo=postgresql)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED?logo=docker)](https://www.docker.com/)
[![OpenAI](https://img.shields.io/badge/OpenAI-API-412991?logo=openai)](https://openai.com/)

## 📖 Description

**CodeSage** is a Retrieval-Augmented Generation (RAG) application that allows developers to ask natural-language questions about their GitHub codebases.

Instead of manually searching through hundreds or thousands of files, CodeSage retrieves relevant code from the user's repositories and provides AI-generated answers grounded in that code.


### The problem CodeSage solves

Understanding an unfamiliar or large codebase can be time-consuming. Developers often need to:

- Search through multiple repositories.
- Understand unfamiliar classes and methods.
- Trace how different components interact.
- Find where specific functionality is implemented.
- Understand configuration and project structure.
- Repeatedly switch between files while investigating a feature.

CodeSage provides an AI-powered interface where developers can ask questions such as:

```text
How does authentication work in this project?

Where is GitHub OAuth implemented?

Explain how the repository indexing process works.

Which classes are responsible for generating embeddings?
```

The answers are generated using relevant code retrieved from the user's indexed repositories rather than relying only on the model's general knowledge.

---

## ✨ Features

- 🔐 **GitHub OAuth Login**
  - Authenticate securely using a GitHub account.
  - Access repositories belonging to the authenticated user.

- 📚 **GitHub Repository Integration**
  - Fetch repositories associated with the authenticated GitHub account.
  - Select repositories for indexing and analysis.

- 🔍 **Codebase Indexing**
  - Retrieve repository file trees.
  - Filter files that are suitable for indexing.
  - Read source-code files from GitHub.
  - Split large files into manageable chunks.

- 🧠 **RAG-Based Question Answering**
  - Retrieve relevant code chunks for a user's question.
  - Provide the retrieved context to OpenAI.
  - Generate answers grounded in the indexed codebase.

- 🤖 **OpenAI Integration**
  - Uses OpenAI models for AI-powered code understanding and responses.

- 🗄️ **PostgreSQL Database**
  - Stores application and repository metadata.
  - Runs in a Docker container.

- 🐳 **Dockerized Infrastructure**
  - Containerized PostgreSQL setup.
  - Persistent database volumes.
  - Simplified local development environment.

- 🎨 **Modern Web Interface**
  - Next.js and TypeScript frontend.
  - Tailwind CSS styling.
  - shadcn/ui components.
  - Lucide React icons.

---

# 🛠️ Tech Stack

## Frontend

| Technology | Purpose |
|---|---|
| **Next.js** | React-based frontend framework |
| **TypeScript** | Type-safe frontend development |
| **Tailwind CSS** | Utility-first styling |
| **shadcn/ui** | Reusable UI components |
| **Lucide React** | Icons |

## Backend

| Technology | Purpose |
|---|---|
| **Spring Boot** | Backend REST API |
| **Spring Security / OAuth2 Client** | GitHub authentication |
| **GitHub API** | Repository and source-code access |
| **OpenAI API** | AI-powered code understanding |
| **Spring AI** | AI/vector-store integration |
| **PostgreSQL** | Persistent application data |
| **pgvector** | Vector storage/retrieval |

## Infrastructure

| Technology | Purpose |
|---|---|
| **Docker** | Containerization |
| **Docker Compose** | Local multi-container environment |
| **Docker Volumes** | Persistent PostgreSQL storage |
| **PostgreSQL Container** | Database infrastructure |

---

# 📁 Project Structure

The project is organized into separate frontend, backend, and infrastructure components.

```text
CodeSage/
│
├── frontend/
│   ├── app/
|   |     ├── auth/callback/
|   |     ├── chat/[repoId]
|   |     ├── dashboard/overview/
|   |     └──settings/
│   ├── components/
│   ├── lib/
│   ├── public/
│   ├── package.json
│   └── ...
│
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── codesage/
│   │   │   │       ├── Config/
│   │   │   │       ├── Controller/
|   |   |   |       ├── Dto/
│   │   │   │       ├── Entity/
│   │   │   │       ├── Repository/
│   │   │   │       ├── Service/
│   │   │   │       ├── Exceptions/
│   │   │   │       └── security/
│   │   │   │
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── ...
│   │   │
│   │   └── test/
│   │
│   ├── pom.xml
│   └── ...
│
├── docker/
│   └── postgres/
│       └── init-extensions.sql
│
├── docker-compose.yml
├── .gitignore
└── README.md
```

---



# 🏗️ Architecture / How It Works

CodeSage follows a Retrieval-Augmented Generation workflow.


## 🔄 RAG Workflow

### 1. GitHub Authentication

The user signs in using GitHub OAuth.

```text
User → CodeSage → GitHub OAuth → Authentication
```

After successful authentication, CodeSage can use the authorized GitHub access token to interact with the user's repositories.

### 2. Repository Fetching

CodeSage communicates with the GitHub API and retrieves repositories available to the authenticated user.

```text
GitHub Account
      │
      ▼
Repository List
      │
      ▼
CodeSage
```

### 3. Repository Indexing

When a repository is indexed, CodeSage retrieves its file tree and determines which files should be processed.

Files that are unsuitable for indexing, such as very large or unsupported files, can be filtered out.

### 4. Code Chunking

Source files are divided into smaller chunks.

```text
Large Source File
       │
       ├── Chunk 1
       ├── Chunk 2
       ├── Chunk 3
       └── Chunk 4
```

Chunking makes it possible to retrieve only the relevant portions of a codebase rather than sending an entire repository to an AI model.

### 5. Retrieval

When a user asks a question, CodeSage identifies relevant indexed code chunks.

```text
Question:
"How does authentication work?"

             │
             ▼
      Retrieval System
             │
       ┌─────┼─────┐
       ▼     ▼     ▼
 Security  OAuth  User
 Config    Service Service
```

### 6. OpenAI Answer Generation

The retrieved code context is provided to OpenAI along with the user's question.

OpenAI generates an answer based on the retrieved repository context.

```text
User Question
      +
Relevant Code
      │
      ▼
   OpenAI
      │
      ▼
Grounded Answer
```
# 🧠 Why RAG?

A general-purpose AI model may understand programming concepts but does not automatically know the implementation details of a private codebase.

For example:

```text
How does authentication work in my application?
```

requires knowledge of the actual project's:

- Security configuration
- Controllers
- Services
- Entities
- OAuth implementation
- Authentication flow

RAG solves this by retrieving relevant project information before generating the answer.

```text
         User Question
               │
               ▼
       Retrieve Relevant
          Code Chunks
               │
               ▼
       Build AI Context
               │
               ▼
             OpenAI
               │
               ▼
       Contextual Answer
```

This makes CodeSage useful for understanding **specific codebases**, rather than simply answering generic programming questions.

---

# 📈 Indexing Flow

When a repository is indexed:

```text
Repository Selected
        │
        ▼
Retrieve GitHub Tree
        │
        ▼
Filter Supported Files
        │
        ▼
Retrieve File Contents
        │
        ▼
Split Files into Chunks
        │
        ▼
Generate / Store Vector Data
        │
        ▼
Repository Ready
```

CodeSage tracks indexing progress so users can monitor how many files and chunks have been processed.

---

# 💬 Example Questions

Once a repository has been indexed, users can ask:

```text
How does the authentication flow work?

Where is the GitHub OAuth configuration?

Explain the repository indexing process.

Which service is responsible for fetching GitHub files?

How does the application handle failed indexing?

Where is the database connection configured?

Explain how the frontend communicates with the backend.

Which classes are responsible for RAG?

How are code files divided into chunks?

What happens when a repository is indexed twice?
```
---


# 🚀 Getting Started

## Prerequisites

Make sure the following tools are installed:

- **Git**
- **Node.js**
- **npm**
- **Java 17+**
- **Maven** or Maven Wrapper
- **Docker**
- **Docker Compose**
- A **GitHub OAuth App**
- An **OpenAI API key**

Verify your installations:

```bash
git --version
node --version
npm --version
java --version
docker --version
docker compose version
```

---

# 🔐 Environment Variables

CodeSage requires credentials for GitHub OAuth, OpenAI, and PostgreSQL.

## Backend environment variables

Example:

```env
spring.application.name=backend

spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5433/codesage}
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD:postgres}
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.open-in-view=false

spring.ai.openai.api-key=
spring.ai.openai.chat.model=gpt-4o-mini
spring.ai.openai.embedding.model=text-embedding-3-small

spring.ai.vectorstore.pgvector.initialize-schema=true
spring.ai.vectorstore.pgvector.dimensions=1536
spring.ai.vectorstore.pgvector.index-type=HNSW
spring.ai.vectorstore.pgvector.distance-type=COSINE_DISTANCE

spring.security.oauth2.client.registration.github.client-id=
spring.security.oauth2.client.registration.github.client-secret=
spring.security.oauth2.client.registration.github.scope=read:user,repo
spring.security.oauth2.client.provider.github.user-name-attribute=id

server.servlet.session.cookie.name=CODESAGE_SESSION
server.servlet.session.cookie.http-only=true
server.servlet.session.cookie.same-site=lax
server.servlet.session.timeout=7d

app.frontend-url:${FRONTEND_URL:http://localhost:3000}
app.cors.allowed-origins=${CORS_ALLOWED_ORIGINS:http://localhost:3000}
app.token-encryptor-password=${TOKEN_ENCRYPTOR_PASSWORD:}
app.token-encryptor-salt=${TOKEN_ENCRYPTOR_SALT:}
app.indexing.max-file-bytes=102400
app.indexing.chunk-size=800
app.indexing.chunk.overlap=100
app.github.api-delay-ms=50
```

Depending on the project's Spring configuration, property names may be mapped differently. Check the backend `application.properties` or `docker-compose.yml` for the exact names expected by your environment.

```

---

# 💻 Installation

## 1. Clone the repository

```bash
git clone https://github.com/<your-username>/CodeSage.git
cd CodeSage
```

## 2. Start PostgreSQL with Docker

CodeSage uses PostgreSQL as its database.

```bash
docker compose up -d postgres
```

Check the running containers:

```bash
docker compose ps
```

---

## 3. Backend Setup

Navigate to the backend:

```bash
cd backend
```

Configure the required environment variables.

If the project uses the Maven wrapper:

### Windows

```powershell
.\mvnw.cmd spring-boot:run
```

### Linux/macOS

```bash
./mvnw spring-boot:run
```

Or, if Maven is installed globally:

```bash
mvn spring-boot:run
```

The backend should start on:

```text
http://localhost:8080
```

---

# 🎨 Frontend Setup

Open another terminal and navigate to the frontend:

```bash
cd frontend
```

Install dependencies:

```bash
npm install
```

Start the development server:

```bash
npm run dev
```

The frontend should be available at:

```text
http://localhost:3000
```

---

# 🐳 Docker Setup

A simplified PostgreSQL Docker Compose configuration can look like:

```yaml
services:
  postgres:
    image: pgvector/pgvector:pg16
    container_name: codesage-postgres
    environment:
      POSTGRES_DB: codesage
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5432:5432"
    volumes:
      - codesage_pg_data:/var/lib/postgresql/data
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U postgres -d codesage"]
      interval: 5s
      timeout: 5s
      retries: 5

volumes:
  codesage_pg_data:
```

Start the database:

```bash
docker compose up -d
```

Stop the containers:

```bash
docker compose down
```

Stop containers and remove the database volume:

```bash
docker compose down -v
```

> **Warning:** `docker compose down -v` deletes the PostgreSQL volume and therefore removes persisted database data.

---
## Output

<img width="1920" height="975" alt="home" src="https://github.com/user-attachments/assets/2f05ea7b-4143-418a-ba80-f5c6bae29d8a" />
<img width="1920" height="982" alt="dashborad" src="https://github.com/user-attachments/assets/bd69a85c-ec96-4e97-b254-76466ca24f83" />
<img width="1022" height="353" alt="chat" src="https://github.com/user-attachments/assets/357cde38-191a-4c45-91fe-d4f062c850d5" />
<img width="1920" height="980" alt="overview" src="https://github.com/user-attachments/assets/d98633cd-d8e2-4d39-8f5f-4693cfe4392a" />
<img width="1920" height="926" alt="setting" src="https://github.com/user-attachments/assets/3a648749-d846-4fec-9932-b17dea35ce8e" />



## ⭐ Support

If you find CodeSage useful, consider giving the repository a ⭐ on GitHub.

> **CodeSage — understand your codebase by talking to it.**
