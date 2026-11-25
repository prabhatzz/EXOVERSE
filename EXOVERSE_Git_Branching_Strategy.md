# Exoverse Git Branching Strategy

This document defines the Git branching strategy for Exoverse, including branch responsibilities, development workflows, pull requests, releases, and branch synchronization.

## 1. Branch Overview

| Branch | Purpose | Direct Commits |
|---|---|---|
| `main` | Protected, production-ready branch containing approved releases. | Not allowed for normal development |
| `develop` | Integration branch where completed features are combined and tested before a release. | Avoid direct commits; use PRs |
| `feature/*` | Isolated development of new features or tasks. | Yes, on the feature branch |
| `bugfix/*` | Non-urgent bug fixes intended for the next development release. | Yes, on the bugfix branch |
| `hotfix/*` | Urgent fixes for issues affecting production. | Yes, on the hotfix branch |

**Source of truth for normal development: `develop`.**

Normal feature and bug-fix branches must be created from the latest `develop`, not `main`. This ensures that new branches contain the latest integrated development changes.

## 2. Standard Development Workflow

1. Switch to the `develop` branch.
2. Pull the latest changes from `origin/develop`.
3. Create a new feature branch from `develop`.
4. Implement the feature and test the changes.
5. Commit changes on the feature branch.
6. Push the feature branch to GitHub.
7. Create a Pull Request with `develop` as the base branch.
8. Review and test the PR.
9. Merge the feature branch into `develop`.
10. Delete the feature branch after successful merging.

### Development Flow Diagram

```mermaid
flowchart TD
    M["main - Protected / Production Ready"]
    D["develop - Integration Branch"]
    F["feature/* - Task Branch"]
    W["Develop and Test Changes"]
    PR["Pull Request: feature/* → develop"]
    T["Integration Testing on develop"]
    R["Release Pull Request: develop → main"]

    M -->|"Initial Setup / Release History"| D
    D -->|"Create from Latest develop"| F
    F --> W --> PR --> D
    D --> T --> R --> M