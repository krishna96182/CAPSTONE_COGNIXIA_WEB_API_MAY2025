# CAPSTONE_COGNIXIA_WEB_API_MAY2025
# Capstone Project – Full Stack Test Automation Suite

This repository contains the **Capstone Test Automation Project** for Cognixia’s QA Automation training. It demonstrates practical implementation of Selenium (UI testing), Postman + Newman (API testing), JMeter (performance testing), and JIRA (test management), integrated and version-controlled via GitHub.

---

## Project Structure


---

## 1. Selenium Web UI Test Automation (Java + TestNG)

### Features
- Functional test cases for:
  - Login
  - Form submission
  - Search functionality
- **Page Object Model (POM)** architecture
- **Assertions** using TestNG
- **Logging** using Log4j
- **Screenshots** captured on test failure
- **Cross-browser testing** (Chrome, Edge)
- **Headless/headed mode** controlled via `config.properties`
- Executable via:
  - `testng.xml`
  - Maven: `mvn test` / `mvn clean install`

### Utility Functions (Optional)
- `Helper.java` with:
  - `clickOnElementUsingJS(WebElement ele)`
  - `captureScreenshot(WebDriver driver, String fileName)`
- Additional test types:
  - IFrame handling
  - Window switching
  - JavaScript alerts

### Sample App Used:
[https://demoqa.com/login](https://demoqa.com/login)

---

## 2. API Automation with Postman and Newman

### Features
- Covered Methods:
  - `GET`, `POST`, `PUT`, `PATCH`, `DELETE`
- Collection exported and executed with **Newman CLI**
- Key Validations:
  - ✔ Status Code
  - ✔ Response Body (e.g., `Apple iPhone 12 Mini, 256GB, Blue`)
  - ✔ Response Time (e.g., `<2000ms`)
  - ✔ Response Headers (e.g., `Content-Type: application/json`)

### Execution
```bash
# Basic
newman run postman-api-tests/Collection.json

# With HTML extra report
newman run postman-api-tests/Collection.json -r htmlextra

###3. Performance Testing with Apache JMeter

#### 🔧 Test Plan Overview
- **Tool:** Apache JMeter  
- **Targeted Endpoints:** Configurable via `.jmx` file  
- **Virtual Users (Threads):** 10  
- **Ramp-up Time:** 1 second  
- **Loop Count:** Configurable in test plan  
- **Duration:** 120 seconds  

#### ✅ Assertions Included
- **Response Time:** Within expected SLA (e.g., < 2000ms)  
- **Success Rate:** 100%  
- **Error % Thresholds:** < 1%  

#### 🛠 Execution Command
```bash
# Headless execution with HTML report generation
jmeter -n -t jmeter-performance-tests/TestPlan.jmx -l test-results/results.jtl -e -o test-results/html-report


