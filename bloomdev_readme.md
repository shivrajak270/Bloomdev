# 🌸 Bloomdev Project

This project can be run in **two ways** depending on your preference:

---

# 🚀 Method 1: Run Using Pre-built Docker Images (Fastest - Recommended)

You don’t need to build anything — just pull and run.

## Steps

1. Clone the repository:
```
git clone https://github.com/shivrajak270/Bloomdev.git
```

2. Navigate to the direct docker setup (choose your OS):
```
cd full-stack-project/direct-doc/mac
```
*(For Windows, go to the windows folder instead)*

3. Run the application:
```
docker compose up
```

## What this does
- Pulls pre-built images from Docker Hub
- Starts:
  - PostgreSQL
  - Backend (Spring Boot)
  - Frontend (React + Nginx)

---

# 🛠️ Method 2: Build and Run Locally Using Source Code

Use this if you want to modify or understand the code.

## Steps

1. Clone the repository:
```
git clone https://github.com/shivrajak270/Bloomdev.git
```

2. Navigate to the main project folder:
```
cd full-stack-project/bloom
```

3. Run using docker compose:
```
docker compose -f docker-compose.yml up
```

## What this does
- Builds images locally from source code
- Uses:
  - ./backend/backend for backend
  - ./frontend for frontend
  - Local SQL dump for database

---

# 📦 Docker Configuration (Reference)

## Local Build Setup

```
version: "3.8"

services:

  postgres:
    image: postgres:18
    container_name: mediassist_postgres
    restart: always
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: 8431437824
      POSTGRES_DB: postgres
    ports:
      - "5435:5432"
    volumes:
      - ./db/mediassist_backup.sql:/docker-entrypoint-initdb.d/mediassist_backup.sql

  backend:
    build: ./backend/backend
    container_name: mediassist_backend
    restart: always
    ports:
      - "8080:8080"
    depends_on:
      - postgres
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/postgres
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: 8431437824

  frontend:
    build: ./frontend
    container_name: react-app
    restart: always
    ports:
      - "3000:80"
    depends_on:
      - backend
```

---

## Pre-built Image Setup

```
version: "3.8"

services:

  postgres:
    image: shivrajak47/bloomdev-postgres:18
    container_name: mediassist_postgres
    restart: always
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: 8431437824
      POSTGRES_DB: postgres
    ports:
      - "5435:5432"

  backend:
    image: shivrajak47/bloomdev-backend:1.0
    container_name: mediassist_backend
    restart: always
    ports:
      - "8080:8080"
    depends_on:
      - postgres
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/postgres
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: 8431437824

  frontend:
    image: shivrajak47/bloomdev-frontend:1.0
    container_name: react-app
    ports:
      - "3000:80"
    depends_on:
      - backend
```

---

# 🌐 Access the Application

- Frontend → http://localhost:3000
- Backend → http://localhost:8080
- PostgreSQL → localhost:5435

---

# 💡 Notes

- Method 1 is faster (no build time)
- Method 2 is useful for development and debugging
- Make sure Docker is running before executing commands

