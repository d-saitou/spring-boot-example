# Spring Boot example application
\* [Japanese version](/README.ja.md)

## 1. Overview
This project is Gradle project of Web application example using Spring Boot.

## 2. Implementation
The following functions were implemented as an implementation example.

* form validation
* file upload and download
* DB CRUD
* authentication and authorization
* AOP (transaction configuration and logging)
* REST API
* sending an email using asynchronous processing
* resource access using webjars
* exception handling
* internationalization
* task scheduler

## 3. Development environment
To run this application, please follow the steps below.

1. Install the following items.
	* JDK 11 (or later)
	* IDEs such as VS Code or Eclipse (require Lombok and Gradle plugins)
	* MySQL 8.0

2. Check out this source project on the IDE.

3. Change the following parameters of the config file [application.yml](/src/main/resources/application.yml) as necessary.

| Paramater Name               | Description                                        |
|:-----------------------------|:---------------------------------------------------|
| anchor-params.data-directory | files storage location (log, temporary file, etc.) |
| anchor-params.mail-address   | e\-mail address                                    |
| spring.mail.\*               | e\-mail config                                     |
| spring.datasource.url        | database connection                                |
| spring.datasource.username   | database user name                                 |
| spring.datasource.password   | database password                                  |

4. Create database and user in MySQL according to parameters in [application.yml](/src/main/resources/application.yml).

5. Create tables in MySQL by running SQL files under [db](/data/db) directory.  

6. Run this application as a Spring Boot application.
