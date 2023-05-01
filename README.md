# _API Testing Framework_
This API Testing Framework is built using Java and RestAssured, which is a popular library for testing RESTful APIs. It is a data-driven testing framework, which means that test data is fetched from an external source, in this case, an Excel spreadsheet.
The framework is designed as a data-driven testing framework, meaning that test data is stored in an external data source such as an Excel file, and the framework reads the data from the file during test execution. This allows for easy maintenance and updates to test data without requiring changes to the actual test code.
The framework includes an implementation of the Logger, which is used to log details during the execution of tests. This feature helps in debugging and identifying issues during the testing phase. The Logger can be customized based on the user's requirements and can be used to output logs to different sources such as the console, log files, or database.
For reporting purposes, the framework uses Extent Reports, which is a powerful reporting library that generates HTML reports. These reports include information about the tests that were run, their status, and other details such as the time taken to execute the tests. The reports can be easily shared with other team members and stakeholders to keep them informed about the testing progress and results.
Overall, this API Testing Framework provides a comprehensive solution for testing RESTful APIs. Its data-driven approach allows for greater flexibility in testing different scenarios and its use of Extent Reports and Logger ensures that test results are well-documented and easily accessible and customizable.


## Features

- Data Driven framework based on Excel
- Implementation of Loggers so that user can log info,warn,debug or trace call based on requirement
- Reporting can be done using extent report which can be customized as per need






## Folder Structure
The folder structure of your API Testing Framework consists of the following:

- src/main/java: This is the main source code directory that contains all the Java packages for the framework.
- AdequateEndpoints: This package contains the methods to perform CRUD operations for Adequate endpoints. The response is sent back so that it can be called in test scripts.
- MobileEndpoints: This package contains the methods to perform CRUD operations for Mobile endpoints. The response is sent back so that it can be called in test scripts.
- Payload: This package contains POJO (Plain Old Java Object) classes that can be utilized in test scripts to create objects and set values in variables. These objects can then be sent as payloads in the POST request body.
- Utils: This package contains utility files like FileUtil, DataProvider, ExcelUtil, ExtentSetup, and ListenerTest.
- FileUtil: This class takes two parameters: a file path and string data. Based on the file path, the data will be written in the file.
- DataProvider: This class reads data from Excel and then can be utilized in test scripts to create objects and set values in variables. These objects can then be sent as payloads in the POST request body.
- ExcelUtil: This class reads data from an Excel file.
- ExtentSetup: This class sets up the Extent report.
- ListenerTest: This class logs data based on test result such as test pass, test fail, or test skipped.
- Routes: This package contains the endpoints for the API.

- src/main/resource: log4j2.xml file which is configured for two loggers and each logger logs the data in Logs folder with separate file name(Adequate,Mobilestore).
- product-schema: This is the json-schema which is used to validate the json response back when post call is made.
- src/test/java - Contains package testEndPoints inside it we have written testcases for each endpoint(Adequate,Mobilestore).
- src/test/resource: contains files like testdata excel for mobilestore, login and register token and properties files.

Overall, the structure follows a modular approach with separate packages for endpoints, payload, utilities, and routes. This makes it easy to maintain and extend the framework. The use of POJO classes and DataProvider helps to achieve data-driven testing, while Extent reporting provides detailed reports for better analysis.


## Installation
If you want to run the code locally you can clone the repo and run either using testng.xml or
``` mvn clean test``` command.

You can also integrate pom.xml with Jenkins and run the scripts on CI/CD pipeline.

All the Logs are being generated in Logs folder and Extent-Report is generated in Reports folder. You can change the logging information as per your need in both logger and extent report.

You have any suggestion you are most welcome. We can connect over email for anything required.

Thank You\
Happy Coding,\
Learn,Code and Earn\
Stay Safe and Stay Positive :)
