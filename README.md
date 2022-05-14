# Coin Market Automation

This Repository contains automation code using Java and Cucumber Coin Market website

## Pre-Requisite
The system must have following setup before running this code  
JDK  
Maven  
Appium and android studio(if running the mobile automation locally)
Chrome Browser Version 101

Open command promt and type "java --version".  
Similar result should be displayed.

```bash
openjdk 15 2020-09-15
OpenJDK Runtime Environment (build 15+36-1562)
OpenJDK 64-Bit Server VM (build 15+36-1562, mixed mode, sharing)
```
Open command promt and type "mvn--version".  
Similar result should be displayed.

```bash
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: C:\Program Files\apache-maven-3.6.3\bin\..
Java version: 15, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-15
Default locale: en_IN, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```

## Usage
Clone the repository.
```bash
git clone https://github.com/rafi-m/coinMarketCapAutomation.git
```
use command promt to navigate to project folder
```bash
cd coin_market_automation
```
Running the test
```bash
mvn test
```

Use the following command to Run specfic test by replacing "@tagName"
```bash
 mvn test -Dcucumber.options="--tags @tagName"
``` 
Run All Web Tasks => @web-tasks   
Run Web Task 1 => @web-task1  
Run Mobile task => @mobile-task1  
Run All Backend Tasks => @backend-task  
Run Backend Task 2 => @backend-task2

## Mobile Automation
currently mobile tasks are configured to run on browser stack.
follow below steps to run the test locally.
1. install appium and android studio
2. navigate to "{project_path}/coin_market_automation/env/devices.json"
3. add the required capablities as JSON object
4. open ZoomApp.feature file and change the device name as given in devices.json

## Test Reports
HTML test reports will automatically open once all the test has been run.  
Following command can be used to view the test report manually.
```bash
test-output\spark\index.html
```
### Sample Report
sample report looks like this!!

https://user-images.githubusercontent.com/43473887/168449109-3365841a-7d9f-4950-aea0-16d50eda924b.mp4


