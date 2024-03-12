[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/jwssRZI4)

SpringBoot Application with Docker Setup
This repository contains a SpringBoot application along with Docker setup for easy deployment. The application can be run using Maven plugins or Docker containers. Additionally, it includes tests for both web interface and functionality, which can be executed with or without visualizations.
Usage
Running the Application
To run the SpringBoot application, you have two options:
1.	Using Maven Plugins: Execute the following Maven command:
arduinoCopy code
mvn spring-boot:run 
2.	Using Docker: Run the following Docker command:
arduinoCopy code
docker run -p 8080:8080 springio/webtest 
This command will run the project on the default port 8080.
Running Tests
The repository includes four new tests: three web interface tests and one functionality test. To run these tests:
1.	With Visualizations: Simply click on the "Run Test" logo.
2.	Without Visualizations: Uncomment the following line in seleniumConfig.java:
javaCopy code
return new FirefoxDriver(); 
And comment out the following line:
javaCopy code
return new FirefoxDriver(options); 
Functionality tests and Unit tests follow the same structure and can be run similarly.
Continuous Integration
This repository is integrated with GitHub Actions for Continuous Integration (CI). The testing.yml file configures CI to automatically run tests on every push to the main branch and on pull requests targeting the main branch.
testing.yml Configuration
_____________________________________________________________________
name: Testing

on:
  push:
    branches:
      - main
  pull_request:
    branches:
      - main

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout
        uses: actions/checkout@v4.1.1

      - name: Build with Maven
        # run: mvn clean package -DskipTests
        run: mvn clean package

      - name: Run all tests
        run: mvn test

_________________________________________________________________________

This YAML file defines a GitHub Actions workflow named Testing. It specifies that the workflow should trigger on every push to the main branch and on pull requests targeting the main branch. The workflow runs on an Ubuntu environment.

The build job includes the following steps:

Checkout: Clones the repository code.
Build with Maven: Cleans the project and packages it using Maven.
Run all tests: Executes all tests using Maven.


video link:
https://youtu.be/zbUb25SzDsg?si=xaWfvBkwLyDbNqHr 
