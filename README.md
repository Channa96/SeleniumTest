# SeleniumTest 🚀
A beginner-to-intermediate level Selenium test automation framework built with **Java**, **TestNG**, and **Maven**. This project demonstrates various Selenium capabilities like alert handling, iframe interaction, screenshot capturing, and data-driven testing using Excel.

---

## 📌 Table of Contents
- [Technologies Used](#technologies-used)
- [Test Scenarios](#test-scenarios)
- [How to Run](#how-to-run)
- [Test Suite Configuration](#test-suite-configuration)
- [Screenshots](#screenshots)
- [Future Improvements](#future-improvements)
- [Author](#author)

---

## ⚙️ Technologies Used
- Java 8+
- Selenium WebDriver
- TestNG
- WebDriverManager
- Apache POI
- Maven
- ChromeDriver

---

## ✅ Test Scenarios

### 🔹 HandleAlertTest
- Interacts with JavaScript alerts (simple, confirm, prompt)
- Accepts or dismisses based on scenario
- Verifies alert messages

### 🔹 HandleFramesTest
- Handles single and nested iframes
- Uses `driver.switchTo().frame()` and `parentFrame()` properly
- Fills forms and selects dropdowns within iframes

### 🔹 HandleExternalDataProviderTest
- Reads data from `TestFile.xlsx` using Apache POI
- Prints extracted data to console

### 🔹 HandleScreenShotsTest
- Captures screenshots in:
    - `.jpg` file format
    - `Base64` string
    - `byte[]` format
- Saves screenshots in `./ScreenShots/`

### 🔹 TestAddToCart
- Extracts product name from Excel
- Searches and adds item to eBay cart
- Handles multiple windows and verifies cart page

---


## 👤 Author

**Channa Jayawickrama**  
📍 Sri Lanka

[![GitHub](https://img.shields.io/badge/GitHub-Channa96-black?style=flat&logo=github)](https://github.com/Channa96)  
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue?style=flat&logo=linkedin)](https://www.linkedin.com/in/channa-jayawickrama)   
[![Email](https://img.shields.io/badge/Email-Contact-red?style=flat&logo=gmail)](mailto:channa.kumara255@gmail.com) 
