# TheCatApiTest Framework (API)

The project is based on a test task for testing TheCatApi

Tools:
* Maven
* Testng
* Allure
* OkHTTP
* Lombok
* Assertj

Requirements

In order to utilise this project you need to have the following installed locally:

* Maven 3
* Java 1.8


Usage
The project is intended for testing API as part of a test task, but it can also be easily expanded.

To run tests in parallel , navigate to terminal and run:

mvn clean test -DauthKey="your personal key" 

Get your private key - https://docs.thecatapi.com/

Get report - mvn allure:serve 


