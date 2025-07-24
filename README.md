# GitHub Client API

A simple Spring Boot application that consumes the GitHub REST API to retrieve non-forked repositories of a user and their branches along with the last commit SHA.

## ✨ Features

- Retrieve all **non-forked repositories** for a given GitHub username
- For each repository, list all **branches** with their **last commit SHA**
- Handles **404 errors** with a custom exception

## 📦 Technologies Used

- Java 21
- Spring Boot 3.5.3
- Spring Web
- Spring RestClient
- Gradle
- GitHub REST API v3

## 🚀 Getting Started

### Running the application

1. Clone the repository:

```bash
git clone https://github.com/TomaszZiola/githubreporetriever.git
cd githubreporetriever
```
2. Build and run the project

```./gradlew bootRun```

## 🌐 API Usage

Access the API at ```http://localhost:8080/{username}```



