# 🚀 Automation Testing Framework

Repository ini merupakan Automation Testing Framework berbasis Java + Gradle + JUnit 5 yang mencakup API Testing dan Web UI Testing. Pada bagian API Testing memakai Rest Assured + Cucumber untuk menguji DummyAPI yang mencakup skenario CRUD user dan list tag, ditulis dalam format Gherkin dan menghasilkan Cucumber Reports. Bagian Web UI menguji saucedemo.com end-to-end (login hingga checkout) menggunakan Selenium WebDriver dengan Page Object Model serta skenario negatif/positif untuk login, juga dilaporkan via Cucumber Reports. Proyek ini terintegrasi GitHub Actions untuk menjalankan test headless di CI dan meng-upload laporan sebagai artifacts.

---


## ⚙️ Tools & Libraries

- **Bahasa & Build:** Java 17, Gradle
- **Web UI Automation:** Selenium Web Driver
- **API Testing:** Rest Assured
- **Test Report:** Cucumber Reports
- **CI/CD:** GitHub Actions
---

## 🛠️ Installation & Setup

1. **Clone repository**
   ```bash
   git clone https://github.com/Lotusleef/FinalProject.git
   ```
2. **Buka IntelliJ IDEA atau VS Code**

3. **Pastikan sudah ter-install:**
   - JDK 17 atau versi terbaru  
   - Gradle 

4. **Menjalankan Tests**
   - Untuk menjalankan **semua tests**:
     ```bash
     ./gradlew clean test
     ```
   - Untuk menjalankan **hanya Web UI tests**:
     ```bash
     ./gradlew clean test --tests "web.runners.WebTestRunner"
     ```
   - Untuk menjalankan **hanya API tests**:
     ```bash
     ./gradlew clean test --tests "api.runners.ApiTestRunner"
     ```

---

## 📊 Test Reports

Setelah menjalankan tests, report akan dihasilkan otomatis ke:

- **JUnit Reports:**  
  `build/test-results/test`
- **Cucumber HTML Reports:**  
  `build/reports/tests/test`
- **Allure or Cucumber JSON Results (if configured):**  
  `build/allure-results` or `build/cucumber-reports`

Report bisa ditemukan di:
* `build/reports/cucumber/api.html`
* `build/reports/cucumber/web.html`

## 🧩 Project Structure

```
src
├── main
│   ├── java
│   │   ├── org.example/Main.java
├── test
│   ├── java
│   │   ├── api
│   │   │   ├── runners/ApiTestRunner.java
│   │   │   ├── stepdef/ApiStepDef.java
│   │   │   ├── stepdef/ApiWorld
│   │   ├── web
│   │   │   ├── pages/CartPage.java
│   │   │   ├── pages/LoginPage.java
│   │   │   ├── runners/WebTestRunner.java
│   │   │   ├── stepdef/CartStepDef.java
│   │   │   ├── stepdef/LoginStepDef.java
│   │   │   ├── utils/Hooks.java
│   │   ├── TestChrome.java
│   ├── resources
│   │   ├── features
│   │   │   ├── api/sample.feature
│   │   │   ├── web/login.feature

```


---

## 👤 Author

**Muhammad Rayhan**

🌐 [GitHub](https://github.com/Lotusleef)

---

> 💡 *This project is created for educational and portfolio purposes as part of a Software Quality Assurance course.*
