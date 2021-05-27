# TheCatApiTest Framework (API)

The project is based on a test task for testing TheCatApi

Tools:
* Maven
* Testng
* Webdrivermanager
* Allure
* OkHTTP
* Lombok
* Assertj
* Requirements

In order to utilise this project you need to have the following installed locally:

* Maven 3
* Java 1.8


Usage
The project is broken into separate modules for API, UI testing. Each of these modules can be utilised independently of the others using maven profiles.

To run all modules, navigate to terminal and run:

mvn clean test -DauthKey="your personal key" 

Get your private key - https://docs.thecatapi.com/

Get report - mvn allure:serve 


