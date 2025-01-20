# Archetype Template

## What is an Archetype?

An **Archetype** serves as a foundation for standardized and reusable project structures. In this case, it defines the
standards for directories, configurations, and basic dependencies to ensure consistency and efficiency when starting new
projects. The goal is to create a template that can be customized to our specific needs and provides a quick start for
project development.

## Maven Archetype Documentation

### Structure

The following structure provides a clear separation of concerns within a project and serves as the basis for a clean
architecture:

### Description of Key Components:

#### **Parent**

The parent container for the entire project. It defines dependencies and build configurations that apply to all modules.

#### **Logic (Business Logic)**

- **service**: Contains the business logic of the project.
- **security**: Responsible for security aspects like authentication and authorization.
- **config**: Configuration modules required throughout the system.
- **utility (optional)**: Helper classes and methods for recurring logical tasks.
- **controller**: For recieveing api calls

#### **Shared**(Everything that is used across the project)

- **model**: Shared data models.
- **client**: Interfaces for external communication (e.g., APIs).
- **dto**: Data transfer objects for communication between systems.
- **utility (optional)**: General helper functions that can be used multiple times.

#### **Persistence (Data Access Layer)**

- **repo**: Repositories for database operations.
- **entity**: Database entities representing tables.
- **dao**: Data Access Objects for abstracting database interactions.

#### **UI (User Interface)**

- **config**: Configuration for the UI application.
- **model (optional)**: Special models for the presentation layer.
- **utility (optional)**: Helper classes for the UI.

---

## Features

- **Modular Architecture**: Organized structure with a clear separation of responsibilities.
- **Maven Build**: Pre-configured Maven build setup for easy project setup.
- **Seamless Integration**: Easily integrates into existing Maven projects.
- **Scalable**: Suitable for projects of any size, from small to large-scale enterprise applications.

## Getting Started

### 1. Clone the Repository

To start using this template in your own project, clone this repository:

```bash
