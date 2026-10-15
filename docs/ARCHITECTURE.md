# Architecture Decisions

**ADR-001: Separate frontend and backend.** Frontend uses a development proxy; API boundaries allow browser, API, and load tests independently.

**ADR-002: Store money using BigDecimal and NUMERIC(19,2).** Floating point is not suitable for balances. MVP only handles a single currency per transfer.

**ADR-003: Transactional money movement.** Debit/credit/history insertion runs within one database transaction. Account rows are locked in a canonical order to reduce concurrent transfer races and deadlocks.

**ADR-004: Flyway migrations.** Production-style versioned schema definitions; Hibernate set to validate (not auto-create).

**ADR-005: Seed synthetic data only.** Fixed UUIDs make demo requests and test cases reproducible.

**Limitations:** This is not a double-entry accounting ledger; concurrent operations need integration tests; no idempotency on retries; missing authorization. Therefore the service is local-only and intentionally not production ready.
