# ExampleTest Framework (API, GUI)
Java quickstart project for test automation, UI acceptance and API acceptance testing. Created with lessons learned from a IT courses.

Tools:
Maven
Testng
Webdrivermanager
Allure
OkHTTP
Lombok
Assertj
Requirements
In order to utilise this project you need to have the following installed locally:

Maven 3
Java 1.8

Concepts Included
Dependency injection
Page Object pattern
Common web page interaction methods
Common api interaction methods
Usage
The project is broken into separate modules for API, UI testing. Each of these modules can be utilised independently of the others using maven profiles.

To run all modules, navigate to terminal and run:

mvn clean test -DauthKey="your personal key" allure:serve

Get your private key - https://docs.thecatapi.com/

Get report - mvn allure:serve 
or mvn allure:report

