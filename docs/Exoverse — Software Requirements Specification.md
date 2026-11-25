# Exoverse — Software Requirements Specification v2.0


Version 2.0 | Supersedes SRS v1.0 (23 November 2026) | Product requirements for the Exoverse atlas, three ML Labs and the AI Astronomy Research Assistant. Companion documents: Technical Blueprint v0.4 and Visual Requirements Guide v2.0.

This version keeps every v1 requirement and ID, and adds the agreed machine-learning and AI scope. Wherever v1 wording changed, the change is listed below.

## What changed from SRS v1

| Area | v1 | v2 | Why |
| --- | --- | --- | --- |
| Name | RabbitHole | Exoverse, "Worlds beyond our own" | Final product name |
| Evidence labels (FR-04) | Observed, Established science, Under investigation, Theoretical, Speculative | Observed, Established, Theoretical, Hypothesis, Speculative. "Under investigation" is kept as a separate research status, not a synonym for Hypothesis | Your decision 1 |
| Release structure | One "MVP" that included accounts, ingestion and the AI guide | Release 1 (atlas, M0 to M8) launches publicly; Release 2 adds accounts, extra providers, the assistant and the Labs | Blueprint v0.3 correction, confirmed by your decision 5 |
| Scientific data | One value per property | Many measurements per property, each with reference, uncertainty, original and normalized value, dataset version and review status | Blueprint v0.3 section 23 |
| Data providers | "NASA/exoplanet datasets and other authoritative sources" | Named providers integrated through backend adapters, with attribution and licence tracking | Blueprint v0.3 |
| AI Guide (FR-11) | One grounded guide, later phase | AI Astronomy Research Assistant in three stages, Java-owned, with Python as an internal service from stage B | Decisions 8 to 10, 12 |
| ML | Not in scope | Exactly three ML Labs: Exoplanet Finder, Mars Rover Terrain Segmentation, Spacecraft Anomaly Detection | Your agreed scope |
| Non-functional | General | Adds AI spending limits, internal-service security, ML result integrity and model cards | Decisions 4, 6, 8, 11 |

**Preserved without change:** the product principle, the target users, the main content collections, FR-01 to FR-03 and FR-05 to FR-10, FR-12 and FR-13 (FR-12 and FR-13 are extended, not replaced), the Asterion and expansion concepts as later labelled-speculative experiences, the definition of done, and the rule that AI-generated content is never trusted automatically.

## 1. Product vision

Exoverse is an interactive space and universe exploration platform, a cinematic, visual-first digital observatory. It helps people discover what humanity has observed, what scientists are investigating, and what remains theoretical or unknown, through scientifically grounded information, immersive visuals, interactive experiences, machine learning and AI. It combines curated scientific knowledge, connected topic discovery and interactive 3D experiences.

Product principle: Show what it is. Let users experience how it works. Explain the science when they are curious.

Exoverse is not intended to be a text-heavy encyclopedia, a generic AI chatbot, or a complex administrative dashboard. Explanations are simple enough for beginners and detailed enough to be useful to people who follow astronomy and physics. Visualizations explain science; they do not only decorate.

Scientific credibility is a product feature. Provenance, uncertainty, evidence labels, image classes and model limitations are visible wherever they apply.

## 2. Goals and non-goals

### 2.1 Goals

- Make unfamiliar cosmic topics approachable through strong visuals and concise explanations.
- Prioritize exoplanets, potentially habitable worlds, the search for life, cosmic mysteries, theoretical physics, future spacecraft and speculative travel concepts.
- Provide trustworthy scientific context with clear evidence and uncertainty labels.
- Connect topics through a progressive knowledge graph.
- Offer reusable interactive experiences, starting with a planet viewer and a black-hole visualization.
- Turn three machine-learning projects into meaningful, honestly labelled experiences (the Labs).
- Provide an AI Astronomy Research Assistant that answers from trusted sources and the platform's own knowledge base, with verifiable citations.
- Build a technically credible full-stack portfolio and learning project using React/TypeScript, Java/Spring Boot, MySQL, Docker, GitHub Actions and AWS, with Python for ML and AI workloads.
- Support guest exploration; authentication is required only for personal features and, in Release 2, for the assistant.

### 2.2 Non-goals

- Microservices, Kubernetes, Kafka, Redis and OpenSearch are not required. Each is introduced only when a measured requirement justifies it.
- Do not attempt to model a physically operational wormhole, warp drive or inter-universe vehicle.
- Do not build a full-scale space simulator or scientifically validated spacecraft engineering CAD tool.
- Do not populate thousands of topics before the core experience works.
- Do not require users to sign up before exploring public content.
- **ML scope is exactly three Labs** (section 5.2). Any other ML project requires a written change to this SRS.
- The ML Labs are not scientific confirmation tools or safety systems: the Exoplanet Finder does not confirm planets or detect life, and Mission Control is not a spacecraft safety system.
- No unrestricted public access to paid AI services.

## 3. Target users and primary journeys

### 3.1 Target users

- Curious general users who enjoy space and science.
- Students and self-learners seeking visual explanations.
- Space enthusiasts interested in exoplanets, cosmic mysteries and future space travel.

### 3.2 Core user journeys

1. Discover: land on the cinematic home page, see a featured discovery, search or choose an exploration category.
2. Explore a topic: open a topic page, read a short overview, inspect key facts, view media or 3D content, inspect evidence labels and follow related topics.
3. Experience: launch an interactive 3D experience, use simple controls and read optional explanations.
4. Follow curiosity: expand a small set of related knowledge-graph nodes and continue exploring without being overwhelmed.
5. Personalize: optionally create an account, save topics and revisit exploration history.
6. Ask: ask the AI assistant a question grounded in curated content and receive a cited answer that distinguishes known facts from uncertainty, at the chosen explanation level.
7. **Run a Lab (new):** open a Lab, inspect a model's prediction, its confidence, the underlying signal, image or telemetry, and a plain explanation with its limitations.
8. **Research a question (new):** discover relevant papers and see title, authors, date, source, peer-reviewed or preprint status, a summary, findings, limitations and related papers.

## 4. Information architecture

- Home: cinematic astronaut-led hero, search, featured discoveries, selected exploration cards and one featured experience.
- Explore: search and browse the main topic collections.
- Experiences: 3D viewers, simulations, What If scenarios, spacecraft concepts and journeys.
- My Universe: saved topics, recently explored topics and lightweight achievements; authenticated.
- AI Guide (contextual assistant): reachable from relevant topic and experience views; it must not dominate the interface. *Release 2.*
- **Labs (new, Release 2):** Exoplanet Finder, Mars Terrain Lab and Mission Control, each a separate experience page.
- **Ask (new, Release 2, stage C):** a global assistant and Research Paper Explorer page.
Main content collections: Exoplanets & Search for Life; Black Holes & Extreme Objects; Stars & Galaxies; Cosmic Mysteries; Multiverse & Theoretical Physics; Future Spacecraft & Space Travel; Universe & Origins. Solar System content is an introductory collection, not the main focus. Nebulae, supernovae, neutron stars and real missions are placed under the existing collections (to be confirmed in the content taxonomy).

**Navigation by release.** The top navigation shows only destinations that exist and have content, with no coming-soon, empty or disabled links. Release 1 (M8): Explore, Experiences and search. My Universe and account controls appear at M9, Labs when the first Lab is published (M12, listing only published Labs), and Ask only when the global Ask page launches (M17). Between M11 and M17 the assistant is reachable only from the "Ask about this topic" control on topic pages. Visibility is driven by release flags served by the API.

## 5. Functional requirements

"R1" is Release 1 (the public atlas); "R2" is Release 2. Requirement IDs FR-01 to FR-13 are unchanged from v1.

### 5.1 Atlas requirements (v1, preserved)

| ID | Requirement | Release | Change |
| --- | --- | --- | --- |
| FR-01 | Home: a responsive cinematic landing page with a clear value proposition, primary Explore action, search entry point and curated featured content | R1 | None |
| FR-02 | Browse: users browse topic collections and filter or search by topic name, category and selected attributes | R1 | None |
| FR-03 | Topic detail: a reusable template with title, category, concise overview, key properties where relevant, media, evidence label, what is known, what remains unknown, sources and related topics | R1 | None |
| FR-04 | Evidence labels: every scientific claim or topic carries one of five labels: Observed (directly supported by observations), Established (strongly supported by scientific evidence), Theoretical (supported by mathematical or physical models but not directly confirmed), Hypothesis (a scientific proposal still being investigated), Speculative (hypothetical future technologies or possibilities). Labels must not imply more certainty than the sources support. Speculation is never presented as established science | R1 | Amended: vocabulary changed; "Under investigation" moved to FR-32 |
| FR-05 | Sources: store source name, URL, access or retrieval date, attribution and licence notes, and verification status | R1 | None |
| FR-06 | Knowledge graph: a bounded set of related topics for a topic, expandable progressively; the full graph never loads by default | R1 | None |
| FR-07 | Experiences: list interactive experiences and load their metadata and assets on demand. First experiences: one planet viewer and one black-hole visualization; a wormhole journey may be a clearly labelled illustrative prototype | R1 | None |
| FR-08 | Media: reference images, videos and 3D assets with captions, attribution, asset type, loading metadata and image class (observation, scientific visualization or artist's impression) | R1 | Extended: image class |
| FR-09 | Accounts: users may register and log in to save topics and keep history; public browsing remains available | R2 | Release reassigned (v1 listed it in the MVP) |
| FR-10 | My Universe: authenticated users view and manage saved topics and recent history | R2 | Release reassigned |
| FR-11 | AI Guide: superseded by the AI Astronomy Research Assistant, FR-22 to FR-30. The v1 intent is kept: retrieved context, references, disclosed uncertainty, no speculation presented as verified science | R2 | Expanded |
| FR-12 | Data ingestion: authorized jobs retrieve external scientific datasets, validate fields and units, record provenance and stage data for review before publication | R1 (NASA import), R2 (further providers, ML results) | Extended: ML result batches use the same review path |
| FR-13 | Responsive and accessible: desktop, tablet and mobile, keyboard navigation, readable contrast, reduced motion, non-WebGL fallbacks | R1 and R2 | Extended: Labs and assistant meet the same rules |
| FR-31 | Measurement display: a topic page shows one primary value per property, a link to all measurements with their references, "Studies differ" where uncertainties do not overlap, "Updated" for recent revisions, and never an average of different studies | R1 | New (from Blueprint v0.3) |
| FR-32 | Research status: a topic or claim may carry a separate status "Under investigation" meaning active research or review is ongoing; it is independent of the evidence label | R1 | New (your decision 1) |

### 5.2 Machine-learning Labs (Release 2)

The ML scope is exactly three Labs. Each is a visual experience, not a notebook.

| ID | Requirement |
| --- | --- |
| FR-14 | Labs framework: a Labs area lists the three Labs. Every Lab page shows a "Model prediction" label, the model version, a link to its model card and a Limitations section. Outputs come from stored, reviewed records |
| FR-15 | Model cards: each published model version has a card stating purpose, training data and version, method, split policy, our own baseline-versus-model results, and limitations. Results quoted from papers are never presented as ours; no benchmark is promised before our own evaluation |
| FR-16 | Exoplanet Finder (first ML feature): users browse Kepler signals and see the folded light curve (global and local views), the predicted class (planet candidate, astrophysical false positive or non-transiting phenomenon), a confidence score and a short explanation. Version 1 uses a tabular gradient-boosting baseline; version 2 uses a two-view one-dimensional CNN. The page states that the model predicts candidates, does not confirm planets and does not detect extraterrestrial life. A topic link appears only where the signal has a topic page |
| FR-17 | Mars Terrain Lab: users compare a rover image with its predicted terrain segmentation overlay (soil, bedrock, sand, big rock) with a class legend, and see the model's per-class and mean evaluation results. The page states the limits of the labels |
| FR-18 | Mission Control: users view a telemetry channel with the model's anomaly score, the dynamic threshold, flagged events and the labelled anomalies (point or contextual), compared with a simple z-score baseline. The page states that this is an experimental system and not an official spacecraft safety system |
| FR-19 | ML result storage: every published prediction records the model version, dataset version, subject reference, predicted result, score or confidence, provenance and review and publication status. Classifications, segmentation masks and anomaly events each have a result structure suited to their type |
| FR-20 | Batch publication: predictions are produced offline and enter through the reviewed ingestion path. Live inference is not required; it may be introduced only for a feature that genuinely needs it, through a change to this SRS |
| FR-21 | Reproducibility: training code, notebooks, evaluation results and model cards live in a separate exoverse-ml repository. Large datasets and model artifacts are not committed to GitHub; they are described by manifests with checksums and stored outside the repository |

### 5.3 AI Astronomy Research Assistant (Release 2)

The assistant is a separate AI and RAG feature. It supports exploration and does not replace the main experience.

| ID | Requirement |
| --- | --- |
| FR-22 | Contextual assistant: on a topic or experience page the assistant knows the page context and can answer questions such as why a world is interesting, how far away it is, what is known about its atmosphere and what the limits of current research are |
| FR-23 | Explanation levels: Beginner, Intermediate and Advanced, selectable by the user |
| FR-24 | Grounding and citations: answers use only retrieved sources. Every cited item is a stored source or passage, rendered from the database; the assistant never invents papers or citations, states when reliable evidence is unavailable, distinguishes observation from interpretation, explains disagreements between sources, preserves publication dates and provenance, and separates established science from speculation |
| FR-25 | Access and spending control: usage limits per user and per day, rate limits, a cap on request size, and a hard monthly spending cap with an initial planning ceiling of ₹500. When the cap is reached the assistant shows a clear unavailable state. There is no unrestricted public access to the underlying AI service. Whether guests may use it is an open decision (section 13) |
| FR-26 | Staged delivery: stage A uses Spring Boot with SQL retrieval; stage B adds an internal Python FastAPI service for embeddings, semantic retrieval, RAG and citation verification; stage C adds the Research Paper Explorer and a global assistant page. Spring Boot owns the public API, authentication, user limits, rate limits and orchestration; the Python service is internal and secured |
| FR-27 | Research Paper Explorer: for each paper show title, authors, publication date, source, abstract summary (marked as AI-generated), important findings, limitations and related papers, and clearly distinguish peer-reviewed publications from preprints |
| FR-28 | Evaluation: a verified question set tests answer accuracy, retrieval quality, citation correctness and hallucination rate. Acceptance thresholds are agreed before stage B is released |
| FR-29 | Corpus: the platform's own published content, scientific metadata and paper abstracts. Full paper text is excluded until licensing and permissions are checked |
| FR-30 | Placement: a contextual assistant inside relevant topic pages, and a global exploration experience at stage C |

## 6. Content model and data requirements

Use a shared Topic model for planets, stars, galaxies, phenomena, theories, missions and spacecraft. Add category-specific properties without creating a separate page implementation for every topic.

| Entity | Content | Change from v1 |
| --- | --- | --- |
| Topic | id, slug, name, category, short and optional long description, evidence label, research status, publication status, hero media reference, timestamps | Evidence label vocabulary changed; research status new |
| PropertyDefinition and TopicMeasurement | Replace v1 TopicProperty. A definition names the property and its unit; a measurement is one value from one reference, with original and normalized value and unit, uncertainty (plus and minus), value kind, reference, dataset and version, publication date, retrieval date, verification status and publication status. Measurements are appended, never overwritten; a reviewer chooses which is shown first | Replaces TopicProperty (Blueprint v0.3) |
| TopicRelationship | source topic, target topic, relationship type, short explanation, source reference, display priority | None |
| Source | title, publisher, URL, retrieval date, publication date if known, licence and attribution, verification status | None |
| MediaAsset | key or URL, media type, caption, creator or source, licence, dimensions, file size, alt text, 3D format where relevant, image class (observation, scientific visualization, artist's impression) | Image class added |
| DiscoveryEvent | topic, date or year, event type (proposal, prediction, detection, observation, mission milestone), description, source | None |
| Experience | slug, title, type, evidence label, description, asset references, controls, related topics | None |
| User data | account, saved topics, history, optional achievements | None |
| ExternalProvider and ExternalDataset | provider, external record identifiers, dataset name and version, licence, retrieval details | New |
| MlModel and MlModelVersion | task, version, kind (baseline or CNN, and so on), training dataset and version, split policy, code commit of exoverse-ml, evaluation results, limitations, model card, artifact location and checksum | New |
| MlPrediction | model version, subject reference, predicted result, score or confidence, class scores, plain explanation, input hash, review and publication status | New |
| MlPredictionArtifact | a file attached to a prediction (segmentation mask, overlay, folded light curve view): kind, storage key, checksum, dimensions | New |
| MlAnomalyEvent | channel, start and end step, peak score, threshold, labelled anomaly type where known | New |
| ResearchPaper | source, identifier or DOI, title, authors, publication date, venue, peer-reviewed or preprint status, abstract, licence note, retrieval date, URL | New |
| AI usage records | request log (no raw IP), explanation level, outcome, token counts, estimated cost; monthly spend ledger | New |

### 6.1 Scientific data policy

- Store units, uncertainty and provenance. Preserve unknown and null values and avoid false precision.
- Three separate honesty axes, never merged: the **evidence label** describes a claim, the **value kind** describes a number, the **image class** describes a picture.
- Value kinds: measured, derived, estimated, theoretical, and **model-predicted** for predicted measurement values.
- Classification predictions, segmentation masks and anomaly events are not measurements; they use the ML result structures above with model version, dataset version, prediction, score and provenance.
- Preserve different measurements from different publications rather than overwriting them.
- Record provider, external record identifier, source URL, retrieval date, publication information, attribution and licence for every imported item.
- Do not infer habitability or life from a planet merely being in a habitable zone.
- AI-generated text (summaries, answers) is always marked as such and is never imported or published without review.

## 7. Non-functional requirements

v1 requirements are kept; additions are marked **new**.

- **Performance:** lazy-load 3D models and below-the-fold media; provide static or 2D fallbacks; avoid loading large experiences on the first page load. **New:** Labs load precomputed images and data, with no model running in the browser.
- **Reliability:** graceful loading, empty, error and offline states for external APIs. **New:** the assistant degrades to a clear unavailable state when the spending cap, rate limit or the Python service is unavailable; the atlas never depends on AI or ML services.
- **Security:** validate inputs, hash passwords with a suitable password encoder, protect secrets, use least-privilege access, secure CORS and rate-limit sensitive endpoints. **New:** the Python service is reachable only from the Spring Boot backend and has no public endpoint; calls between them are authenticated; AI provider keys never reach the browser; retrieved text is treated as untrusted data (prompt-injection defence); logs do not store raw IP addresses; untrusted model files are never loaded.
- **AI cost control (new):** a hard monthly spending cap, initially ₹500, per-user and per-IP limits, rate limits and a request-size cap, enforced by the backend before any paid call.
- **ML integrity (new):** every model version and prediction is traceable to a dataset version, a code commit and a checksum; random seeds and metrics are recorded; results shown are our own evaluation; limitations are always visible.
- **Maintainability:** modular monolith with clear module boundaries, DTOs, validation, centralized error handling and documented APIs. **New:** the Python service has a documented internal contract, tested from both sides.
- **Testability:** unit tests for domain and services, integration tests for repositories and controllers, frontend component and end-to-end tests for core journeys. **New:** tests for leakage-safe data splits, prediction import, citation verification, refusal behaviour and the spending cap; a nightly evaluation run once the evaluation set exists.
- **Accessibility:** semantic HTML, keyboard operation, meaningful alt text, reduced-motion support and sufficient contrast. **New:** Lab charts and overlays have text alternatives; model labels are never colour-only.
- **Scalability:** begin with a single deployable backend; add caching, search or event infrastructure only when measured needs justify it.
- **Observability:** structured logs, health checks and basic error monitoring. **New:** AI spend, refusal rate and latency are tracked.

## 8. Recommended technical architecture

A modular monolith: one Spring Boot deployable with independently organized modules. Microservices are avoided. Python is used only for specialized ML and AI workloads.

- **Frontend:** React and TypeScript, Tailwind CSS, React Router, a query library, Three.js via React Three Fiber for selected 3D experiences, GSAP for selected cinematic sequences, and lightweight state management.
- **Backend:** Java LTS (Java 21) and Spring Boot, Spring Web, Spring Security, Spring Data JPA and Hibernate, Bean Validation, Flyway, Maven, JUnit 5 and Mockito.
- **Database:** MySQL. Normalized tables for core entities; JSON only for genuinely variable attributes.
- **Media and artifacts:** object storage (S3) and CDN (CloudFront). Large models, masks and media stay out of the relational database.
- **External integrations:** scientific providers through backend adapters that preserve provenance.
- **AI and ML layer (new):** Python with FastAPI as an internal service from assistant stage B; offline ML work in a separate exoverse-ml repository (Python, pandas, NumPy, scikit-learn, PyTorch). Free Google Colab or Kaggle for GPU-dependent training; the first Exoplanet Finder baseline runs on a normal laptop. An external LLM API sits behind the backend with a spending cap.
- **Vector retrieval (new):** start with an in-process index; evaluate Qdrant later using the evaluation dataset. Not finalized.
- **Operations:** Docker Compose for local development, GitHub Actions for CI, a container registry and AWS for staging and production.

## 9. Backend module boundaries

v1 modules are kept: topics, relationships, search, experiences, users, media, ingestion, ai. Amendments and additions:

- **topics:** also owns property definitions and measurements.
- **ingestion:** also imports reviewed ML result batches from the exoverse-ml pipeline.
- **ml (new):** model versions, predictions, artifacts and the Labs read APIs.
- **research (new, stage C):** research papers and their relationships to topics.
- **ai:** stage A grounded answers with SQL retrieval; from stage B it orchestrates the internal Python service, and always owns authentication, limits, budget and the public API.

## 10. Initial API surface

v1 endpoints are kept. Additions are listed here; exact schemas, pagination, errors and authentication are specified in the Blueprint.

- GET /api/v1/topics/{slug}/measurements
- GET /api/v1/labs and GET /api/v1/labs/{lab}/model (current model card)
- GET /api/v1/labs/exoplanet-finder/signals and /signals/{id}
- GET /api/v1/labs/mars-terrain/images and /images/{id}
- GET /api/v1/labs/mission-control/channels and /channels/{id}
- POST /api/v1/ai/ask and GET /api/v1/ai/usage
- GET /api/v1/papers, /papers/{id} and /papers/{id}/related

## 11. Releases, MVP scope and release criteria

**Release 1: the public atlas (milestones M0 to M8).** A polished home page, Explore and search, the reusable topic detail template, curated topics (30 or more, through the content track), source and evidence metadata, measurements with provenance, related-topic exploration, a controlled NASA import (exoplanet measurements and images, reviewed before publication), one 3D planet experience, one black-hole visualization, guest browsing, and a deployed application with automated CI. v1 also listed optional accounts in the MVP; they moved to Release 2 (M9) so the launch is not delayed. This is a sequencing change, not a scope removal.

**Release 2 (milestones M9 to M17).** Accounts and My Universe, further data providers, the AI Astronomy Research Assistant (stages A to C) and the three ML Labs.

A full Asterion generation-ship simulator, an operational wormhole simulation, a full multiverse simulator, a Kafka pipeline or a Kubernetes cluster are not release blockers. They remain later concept experiences, clearly labelled as speculative.

**Definition of done** (v1, kept): acceptance criteria pass on supported viewports; loading, error and empty states exist; automated tests pass; scientific content has source and evidence labels; accessibility and reduced motion are checked; 3D assets are lazy-loaded with a fallback; code is reviewed and merged by pull request.

**Additional for Release 2 features:** limits and refusal states exist and are tested; ML pages show model version, model card and limitations; citations resolve to stored records; AI spend is observable; the Python service is not reachable from outside.

## 12. Delivery roadmap

v1's nine phases map onto the single milestone list below. Sizes are rough guesses for one developer working part-time, not commitments.

| Milestone | Content | Release | v1 phase |
| --- | --- | --- | --- |
| M0 Design | Scope, journeys, visual system, wireframes, taxonomy | R1 | 0 |
| M1 Repository and Docker | Monorepo, React, Spring Boot, MySQL, Compose, Flyway | R1 | 1 |
| M2 CI | Build and test checks on every pull request | R1 | 1 |
| M3 Vertical slice | One exoplanet topic end to end (TRAPPIST-1e), measurements, tests | R1 | 2 |
| M4 Explore and graph | Search, templates, relationships, first ten topics | R1 | 3 |
| M5 NASA data integration | Exoplanet Archive and NASA images, staged and reviewed | R1 | 6 (part) |
| M6 3D experience | Planet viewer, black-hole prototype, fallbacks | R1 | 4 |
| Content track | 30 or more curated topics, finished before M7 closes | R1 | 3 |
| M7 Polish | Choreography, accessibility, performance | R1 | 7 (part) |
| M8 AWS launch | Staging, production, HTTPS, backups, monitoring | R1 | 7 |
| M9 Accounts | Registration, login, saved topics, history | R2 | 5 |
| M10 Extended providers | JPL, MAST, Gaia, arXiv and Crossref, NASA ADS (to verify) | R2 | 6 (part) |
| M11 Assistant stage A | Grounded contextual assistant, Spring Boot and SQL retrieval, limits and spending cap | R2 | 6 (part) |
| M12 ML result platform and Exoplanet Finder v1 | ML result storage, review path, Labs area, tabular baseline results | R2 | new |
| M13 Assistant stage B | Python FastAPI service, embeddings, in-process index, RAG, citation verification, evaluation set | R2 | new |
| M14 Exoplanet Finder v2 | Two-view CNN results published | R2 | new |
| M15 Mars Terrain Lab | Segmentation overlays and evaluation | R2 | new |
| M16 Mission Control | Telemetry anomaly experience | R2 | new |
| M17 Assistant stage C | Research Paper Explorer and global assistant page | R2 | new |
| Expansion | Spacecraft Vault, Asterion concept, propulsion lab, What If and multiverse visualizations | Later | 8 |

**ML learning track (parallel, not a milestone).** Exoplanet Finder learning and experiments in exoverse-ml start once the Java foundation is underway (when the first M3 backend endpoint passes its tests) and run alongside the main work, time-boxed so they never delay a Release 1 milestone. Public product integration happens at M12 and M14. Mars and anomaly experiments start shortly before M15 and M16.

## 13. Assumptions, decisions and open questions

**Assumptions (v1, kept):** one developer, so scope stays small; English first; guest browsing supported; scientific content curated and sourced; AI-generated content is not automatically trusted; every visual is labelled accurately. Hosting region, domain and authentication token strategy are confirmed before production. **New:** no dedicated GPU is available; free Colab or Kaggle is used for GPU training and the first baseline runs on a laptop.

**Confirmed decisions (24 November 2026).** IDs continue the Blueprint's C1 to C7.

| ID | Decision |
| --- | --- |
| C8 | Evidence labels are Observed, Established, Theoretical, Hypothesis, Speculative. "Under investigation" is a separate research status |
| C9 | Exoplanet Finder is the first ML feature: tabular gradient-boosting baseline first, then the two-view CNN as v2 |
| C10 | ML predictions are published in reviewed batches; live inference only when a feature genuinely needs it |
| C11 | A separate exoverse-ml repository holds notebooks, training code, evaluation results and model cards; large datasets and model artifacts are not committed to GitHub |
| C12 | The atlas launches at M8 without ML or AI; Finder learning and experiments run alongside once the Java foundation is underway; public integration comes later |
| C13 | LLM planning ceiling is ₹500 per month, with usage limits, rate limits and a hard spending control; no unrestricted public API usage |
| C14 | Free Colab or Kaggle for GPU training; the first Finder baseline suits a normal laptop |
| C15 | Assistant in three stages (A: Spring Boot and SQL retrieval; B: internal Python FastAPI service; C: Paper Explorer and global page). Java owns the public API, authentication, user limits, rate limits and orchestration |
| C16 | Vector retrieval starts with an in-process index; Qdrant is evaluated later on our evaluation set |
| C17 | Corpus is our own published content, scientific metadata and paper abstracts; no full paper text until licensing is checked |
| C18 | MODEL_PREDICTED covers predicted measurement values; classifications, masks and anomaly events use dedicated ML result structures |
| C19 | The assistant supports Beginner, Intermediate and Advanced explanation levels |
| C20 | ML scope is exactly three Labs (Exoplanet Finder, Mars Rover Terrain Segmentation, Spacecraft Anomaly Detection) and the assistant is a separate AI and RAG feature. No other ML project is in scope unless you propose it |

**Open questions.**

- **Q7. Guest access to the assistant.** Recommended: signed-in users only at first, under the global ₹500 cap. Alternative: a small guest allowance under the same cap.
- **Q8. Research status values.** Recommended: a single "Under investigation" flag with an optional note. Alternative: a richer set of review states. To confirm.
- **Q9. LLM and embedding provider.** The provider, its prices and the rupee conversion are verified before M11. For embeddings, a small local model on CPU avoids API cost and is preferred; to confirm by measurement.
- **Q10. Content taxonomy** for nebulae, supernovae, neutron stars and real missions.
- **Q11. Where model files live.** Recommended: a private S3 location plus checksum manifests in exoverse-ml.

**Traceability.**

| Requirements | Technical Blueprint v0.4 | Visual Guide v2 |
| --- | --- | --- |
| FR-01 to FR-10, FR-31, FR-32 | Sections 3 and 6 (data model, API) | Sections 4 to 6, 14 |
| FR-12, FR-19 to FR-21 | Sections 3, 4 and 6 | Sections 12 and 14 |
| FR-14 to FR-18 | Section 4 (ML integration) | Sections 12 and 14 |
| FR-22 to FR-30 | Section 5 (AI and RAG) | Sections 13 and 14 |
| FR-13, NFRs | Sections 6 and 8 | Sections 7 to 9, 11, 15 |
| Roadmap and releases | Section 7 | Section 16 (open items) |
