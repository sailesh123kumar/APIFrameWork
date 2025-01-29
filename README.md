# API Framework

This repository is a Java-based REST API testing framework built using RestAssured. It provides an efficient and scalable way to automate the testing of RESTful services with a focus on maintainability and ease of use.

## Features

- **RestAssured Integration**: Streamlined API testing with RestAssured for crafting and validating HTTP requests.
- **TestNG Support**: Utilize TestNG for managing and running test cases with advanced reporting capabilities.
- **Maven Build**: Dependency management and build automation through Maven.
- **Utility Classes**: Reusable utilities for enhanced testing efficiency.
- **Listener Support**: Customized listeners for better test execution monitoring.
- **Dockerized Execution**: Run tests in isolated Docker containers.
- **CI/CD Integration**: Configured for Jenkins to facilitate continuous integration and delivery.

## Project Structure

```
APIFrameWork/
├── src/
│   ├── main/
│   │   └── java/
│   └── test/
│       └── java/
│           └── tests/
├── .gitignore
├── Dockerfile
├── Jenkinsfile
├── pom.xml
└── README.md
```

- **src/main/java/**: Placeholder for application-specific code (if any).
- **src/test/java/tests/**: Houses all API test cases.
- **Dockerfile**: Defines the containerized environment for executing tests.
- **Jenkinsfile**: Automates build and test processes in Jenkins.
- **pom.xml**: Maven configuration file for dependencies and plugins.

## Prerequisites

- **Java**: Ensure JDK 8 or later is installed.
- **Maven**: Install Maven 3.6 or higher.
- **Docker**: Install Docker to run tests in a containerized environment (optional).
- **Jenkins**: Set up Jenkins for CI/CD integration (optional).

## Setup and Installation

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/sailesh123kumar/APIFrameWork.git
   cd APIFrameWork
   ```

2. **Install Dependencies**:

   ```bash
   mvn clean install
   ```

## Usage

### Running Tests with Maven

Execute the following command to run all test cases:

```bash
mvn test
```

### Running Tests with Docker

1. **Build Docker Image**:

   ```bash
   docker build -t api-framework .
   ```

2. **Run Docker Container**:

   ```bash
   docker run --rm api-framework
   ```

## Continuous Integration with Jenkins

### Steps to Set Up Jenkins

1. **Create a Pipeline Job**:
   - Configure Jenkins to pull the code from this repository.

2. **Use the Provided Jenkinsfile**:
   - The Jenkinsfile in this repository defines a pipeline with stages for building and testing the project.

3. **Run the Pipeline**:
   - Execute the pipeline and monitor the results in Jenkins.

## Utilities and Features

- **Utility Package**: Contains reusable methods to streamline test development.
- **BaseTest Class**: Provides a foundation for initializing and managing test configurations.
- **Custom Listeners**: Enhance test execution monitoring and reporting.
- **Test Package**: Organized test cases for structured execution.

## Contribution

Contributions are welcome! Feel free to fork this repository, create a branch, and submit a pull request

---

For more information, visit the [GitHub Repository](https://github.com/sailesh123kumar/APIFrameWork).

