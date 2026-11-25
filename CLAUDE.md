# Exoverse

Cinematic space-exploration platform ("Worlds beyond our own"). Solo portfolio and learning project.

## Stack (do not change without asking)
- Backend: Java 21, Spring Boot modular monolith (the primary product backend), Maven (use the `mvnw` wrapper), Spring Data JPA, MySQL, Flyway.
- Frontend: React, TypeScript, Vite, Tailwind CSS, React Router. Three.js (React Three Fiber) and GSAP only when a milestone needs them.
- Infrastructure: Docker Compose locally, GitHub Actions for CI, AWS later (M8).
- Python for offline ML (in the separate `exoverse-ml` repository) and, from M13, the internal AI service. Offline ML work starts alongside the Java learning track. Never put ML notebooks, datasets or model files in this repo.
- Kafka, Redis, OpenSearch and Kubernetes are not currently planned. Introduce any of them only if a real requirement justifies it. No microservices.

## Source of truth
The authoritative documents are in `docs/` as Markdown files. Use these exact filenames:
- `docs/Exoverse — Software Requirements Specification.md` (SRS v2.0, the requirements)
- `docs/Exoverse — Technical Blueprint.md` (Technical Blueprint v0.4, the architecture and the milestone plan in section 7)
- `docs/Exoverse — Homepage Visual Breakdown.md` (Homepage Visual Breakdown v0.2)
- `docs/Exoverse — Visual Requirements & Design Guide.md` (Visual Requirements & Design Guide v2.0)
- `docs/Exoverse — Scope Alignment ML, AI Assistant and Roadmap.md` (Scope Alignment v0.1, working draft — not an approved strategy)
- Blueprint v0.4 is delta-style: sections marked "Unchanged" refer to Blueprint v0.3, which is not in this repo. If you need a v0.3 detail that is unavailable, STOP and ask me. Do not invent missing requirements or architecture.
- If code and docs disagree, stop and tell me. Do not invent scope.
- ML scope is exactly three Labs (Exoplanet Finder, Mars Rover Terrain Segmentation, Spacecraft Anomaly Detection) plus the AI Astronomy Research Assistant. Nothing else.
- Flyway is for schema migrations only. ML prediction batches use a dedicated reviewed importer (M12).

## Repository layout
`frontend/`, `backend/`, `infrastructure/`, `docs/`, `.github/workflows/`.

## Working rules
- Plan first: outline the files, approach and tests, then WAIT for my approval before writing or changing any code.
- Work on one milestone at a time, as numbered in Blueprint section 7. Release 1 is M0 to M8.
- If you find a conflict with the docs, existing code, technical debt, or a better approach, stop and raise it before continuing.
- Teach as you go: explain each new technology briefly when it is introduced. This is a learning project.
- No overengineering. Build the smallest thing that meets the milestone's exit check.
- Include tests with the code. Use JUnit 5 and Mockito for the backend.
- Commit in small steps with clear messages. Never commit secrets, `.env` files, or large data or model files.

## Scientific honesty (a product rule, not just a feature)
- Keep these separate everywhere: the evidence label (Observed, Established, Theoretical, Hypothesis, Speculative), the research status ("Under investigation"), the image class (observed, visualization, artist's impression), and the value kind (measured, derived, estimated, theoretical, model-predicted).
- Every published number needs a source, a unit and a stored uncertainty where one exists.
- AI-generated or model-predicted content is always marked and never trusted automatically.