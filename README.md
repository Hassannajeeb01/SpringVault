# Blackjack — Spring Boot REST API

A fully deployed Blackjack game with a live leaderboard, 
built with Spring Boot and PostgreSQL.

🎮 Live: https://springvault-production.up.railway.app/blackjack.html

## Features
- REST API with Spring Boot
- PostgreSQL leaderboard with JPA
- Global exception handling with @ControllerAdvice
- Unit tested with JUnit 5
- CI/CD via GitHub Actions, deployed on Railway

## Tech Stack
Java, Spring Boot, PostgreSQL, JPA, JUnit 5, Railway

## Endpoints
POST /startGame — start a new game
POST /hit       — draw a card
POST /stand     — end player turn
GET  /leaderboard — top players by points per game