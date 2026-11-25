# EXOVERSE

## Worlds beyond our own

*Explore Beyond What We Know.*

Exoverse is an immersive astronomy exploration platform that makes the mysteries of space accessible through interactive experiences, scientific discoveries, cinematic visualizations and evidence-based explanations.

Our goal is to make complex astronomical concepts easy to understand without sacrificing scientific accuracy.

## 🌌 Project Vision

Explore distant worlds, understand cosmic phenomena, discover the possibilities of the universe and experience theoretical concepts through an engaging, visually immersive platform.

## 🚀 Technology Stack

### Frontend

- React
- TypeScript
- Vite
- React Router
- Tailwind CSS
- Three.js
- React Three Fiber
- GSAP

### Backend

- Java 21
- Spring Boot
- Spring Data JPA
- Maven
- Flyway (database schema migrations only)
- Spring Security (planned for M9)

### Database

- MySQL

### Machine Learning & AI

Python for offline ML (in the separate `exoverse-ml` repository) and, from M13, the internal AI service.

**Planned ML Labs:**

1. Exoplanet Finder
2. Mars Rover Terrain Segmentation
3. Spacecraft Anomaly Detection

**AI:**

- AI Astronomy Research Assistant
- Retrieval-Augmented Generation (RAG)
- Answers cite retrieved sources; the assistant does not invent citations.

### Infrastructure & DevOps

- Docker
- Docker Compose
- GitHub Actions
- AWS
- Amazon S3
- Amazon CloudFront

## 🛰️ Product Features

### Release 1: Public Astronomy Atlas

- Interactive astronomical topic exploration.
- Exoplanets, planets, galaxies and cosmic phenomena.
- Global search and curated Explore collections.
- Experiences featuring interactive 3D viewers, simulations and What If scenarios.
- Immersive planetary and black-hole experiences.
- Evidence labels: Observed, Established, Theoretical, Hypothesis and Speculative.
- Scientific provenance for every published numerical value.

### Release 2: Intelligent Exploration

- User accounts, saved topics and exploration history.
- AI Astronomy Research Assistant grounded in scientific sources and citations.
- Exoplanet Finder: predicts candidates only. It does not confirm planets.
- Mars Rover Terrain Segmentation Lab.
- Spacecraft Anomaly Detection Lab.

ML predictions and AI-generated explanations are presented with their limitations and scientific context.

## 🔬 Scientific Integrity

Scientific honesty is a core principle of Exoverse.

We clearly distinguish between different levels of scientific evidence:

- **Observed:** What has been directly measured or seen.
- **Established:** What is strongly supported by scientific evidence.
- **Theoretical:** What follows from mathematical or physical models but is not directly confirmed.
- **Hypothesis:** A proposal that active research is still testing.
- **Speculative:** Possibilities with no current evidence.

**Under investigation** is a separate research status indicating that a subject is currently being actively investigated. It is not an evidence label.

Habitability does not mean life has been discovered, and theoretical possibilities are never presented as established scientific facts.

## 📁 Repository Structure

```text
EXOVERSE/
│
├── frontend/             # React application
├── backend/              # Java Spring Boot application
├── infrastructure/       # Docker and deployment
├── docs/                 # Project documentation
│
├── .github/
│   └── workflows/        # CI/CD pipelines
│
├── README.md
├── CLAUDE.md
├── .gitignore
└── LICENSE
```

Machine learning development, datasets, experiments and model training live in a separate repository:

**exoverse-ml**

## 🛠️ Project Status

Currently in the initial development and architecture setup phase.

## 📄 License

This project is licensed under the MIT License.