<!-- # order-management-system

## intitial setup

- Install Intellij community edition as IDE
- Install Mysql 5.7 set root username password as root
- Java 17+
- Install Maven
- Postman to test Apis
- Install mysql workbench to easy access DB

### Post Steps

- run createsql.sql in mysql
- Run orderManagementAppApplication -->

# McDonald's E-Menu Digital Ordering System (Backend)

![Build Status](https://travis-ci.org/your-username/mcdonalds-clone-backend.svg?branch=main)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [Installation](#installation)
  - [Database Configuration](#database-configuration)
<!-- - [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing) -->
- [License](#license)
<!-- - [Acknowledgments](#acknowledgments) -->

## Introduction

Welcome to the backend of the McDonald's E-Menu Digital Ordering System! This project is a clone of the digital ordering system used by McDonald's, built using Spring Boot, Maven, and MySQL. The backend handles the business logic, database interactions, and provides a RESTful API for the frontend.

## Features

- User authentication and authorization
- Menu management (CRUD operations)
- Order processing and management
- Integration with a MySQL database
- RESTful API for communication with the frontend

## Prerequisites

Before you begin, ensure you have the following installed:

- Java Development Kit (JDK) 17 or later
- Apache Maven
- MySQL Database

## Getting Started

Follow the steps below to set up and run the McDonald's E-Menu backend on your local machine.

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/mcdonalds-clone-backend.git

2. Navigate to the project directory:

    ```bash
    cd mcdonalds-clone-backend

3. Build the application:

    ```bash
    mvn clean install

## Database Configuration

1. Create a MySQL database for the project.

    ```sql
    CREATE DATABASE orderingSystem;

2. Update the application.properties file with your database connection details:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/orderingSystem
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

3. Run the SQL script to create the database schema:

    ```bash
    mysql -u root -p orderingSystem < config/sql/createsql.sql

4. Run the application:

    ```bash
    mvn spring-boot:run

## License
This project is licensed under the MIT License. See the LICENSE file for details.

