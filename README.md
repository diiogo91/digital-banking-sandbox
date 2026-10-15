# Digital Banking Sandbox 🏦

**A fictional, non-production banking app built for engineering demonstrations and test automation.**

Part of [Diogo Amaral's Engineering Portfolio](https://diiogo91.github.io/developer-portfolio/).

## Why this exists
Demonstrate Java backend design, atomic financial-style operations, web application development, API testing, performance testability, containerization, and CI/CD without exposing any confidential systems or data.

## Tech stack
- Java 21, Spring Boot 3.5, Spring Data JPA, Jakarta Validation
- PostgreSQL 16 + Flyway SQL migrations
- React 19, TypeScript, Vite
- Docker Compose, GitHub Actions, JUnit 5 / Mockito

## Implemented in MVP 0.1
- Seeded fictional accounts and balance overview
- List accounts and transaction history
- Execute **simulated** same-currency transfers
- Decimal amount validation, insufficient-funds checks, transactional ledger event
- Deterministic database locking to reduce deadlock risk on concurrent transfers
- API error responses, simple health endpoint, CI build and unit tests

> ⚠️ **Not real banking software.** No authentication, authorization, audit controls, idempotency guarantees, production-grade transaction ledger, fraud controls, regulated data, real payments, or external banking connectivity. This MVP is **local development only** and must not be exposed publicly as a transactional API.

## Quick start
Requirements: Docker with Compose and Node.js 22.

```bash
docker compose up --build -d
cd frontend
npm install
npm run dev
```

Open **http://localhost:5173**. The backend runs at `http://localhost:8080`; database at local port 5432.

Stop: `docker compose down` (or `docker compose down -v` to reset demo data).

**Without Docker:** Start PostgreSQL and run `mvn spring-boot:run` inside `backend`, then `npm run dev` in `frontend`.

## API contract
| Method | Path | Description |
|---|---|---|
| GET | `/api/v1/accounts` | List fictional accounts |
| GET | `/api/v1/accounts/{id}` | Read account |
| GET | `/api/v1/accounts/{id}/transfers` | Read selected account's transfer history |
| POST | `/api/v1/transfers` | Simulated transfer |
| GET | `/actuator/health` | Health check |

Sample transfer:
```bash
curl -X POST http://localhost:8080/api/v1/transfers \
  -H 'Content-Type: application/json' \
  -d '{"sourceAccountId":"11111111-1111-1111-1111-111111111111","targetAccountId":"22222222-2222-2222-2222-222222222222","amount":25.50}'
```

## Architecture
```text
React + TypeScript (localhost:5173)
              |
      Vite /api proxy
              |
Spring Boot REST API (localhost:8080)
              |
     BankingService (@Transactional)
              |
     JPA repositories + Flyway
              |
        PostgreSQL
```

## Test roadmap
- Integration tests with Testcontainers, negative test matrix, concurrency scenarios
- UI / E2E tests with Playwright Java and Selenium via separate Automation Testing Platform
- JMeter/Grafana performance lab
- Idempotency keys, immutable double-entry ledger and auth in a later demo phase

## Security and attribution
All names, account numbers, and amounts are invented. Never copy code, data, architecture diagrams, internal documents, or credentials from employer/client systems into this repository.

See [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md) and [docs/TEST_CASES.md](docs/TEST_CASES.md).
