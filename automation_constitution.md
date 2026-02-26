# ROCPS Web Automation Constitution

> The definitive reference for the ROCPS automation suite — its architecture, standards, and guidelines for contributors.

---

## 1. Purpose & Scope

This automation suite provides **end-to-end UI and functional testing** for the ROCPS (Revenue Operations & Controls - Partner Settlement) platform. It validates billing, accruals, roaming, tariff management, and administrative workflows through browser-based automation.

**What this suite covers:**
- System testing of ROCPS web application features
- Prerequisite setup and teardown automation
- Data-driven test execution via Excel/CSV inputs
- Cross-browser validation (Firefox, Chrome, IE)
- CI/CD pipeline integration

---

## 2. Architecture

### 2.1 Three-Layer Project Hierarchy

The suite is organized into three Maven projects with a strict dependency chain:

```
┌─────────────────────────────────────────────────────────────────┐
│   rocps-automation (rocps-web-automation v10.5.33.0)            │
│   Product-specific test cases & helpers                        │
├─────────────────────────────────────────────────────────────────┤
│   roc-automation (roc-automation v1.0.0.1-SNAPSHOT)            │
│   ROC application helpers, config/OR loading, listeners        │
├─────────────────────────────────────────────────────────────────┤
│   roc-automation-util (roc-web-util v1.0.0.1-SNAPSHOT)         │
│   Generic UI helpers, component helpers, utilities             │
└─────────────────────────────────────────────────────────────────┘
```

**Build order:** `roc-automation-util` → `roc-automation` → `rocps-automation`

| Project | ArtifactId | GroupId | Role |
|---------|-----------|---------|------|
| roc-automation-util | `roc-web-util` | `com.subex.roc` | Foundation — generic Selenium helpers, component interactions, data/file utilities |
| roc-automation | `roc-automation` | `com.subex.roc` | Middle layer — ROC-specific application helpers, Object Repository, configuration management |
| rocps-automation | `rocps-web-automation` | `com.subex.rocps` | Top layer — ROCPS product test cases, domain-specific helpers (Bills, Accruals, Roaming, etc.) |

### 2.2 Inheritance Chain

```
org.testng.Assert
    └── AcceptanceTest              (roc-automation-util)
            └── ROCAcceptanceTest   (roc-automation)
                    └── PSAcceptanceTest   (rocps-automation)
                            └── Test Classes (BillPreRequisites, TestCase01, etc.)
```

| Base Class | Responsibilities |
|-----------|-----------------|
| `AcceptanceTest` | WebDriver lifecycle, browser setup, report initialization, download path management |
| `ROCAcceptanceTest` | Config/OR file loading, remote machine connectivity, video recording, custom reporting hooks |
| `PSAcceptanceTest` | ROCPS-specific lightweight wrapper |

### 2.3 Helper Pattern (Instead of Page Object Model)

The framework uses **domain-specific helper classes** rather than traditional page objects. Each functional area has its own helper package:

```
rocps-automation/helpers/
├── Bills/
│   ├── BillBreakdownConfiguration.java
│   ├── BillBreakdownConfigDetails.java
│   ├── BillBreakdownConfigSearchImpl.java
│   └── ...
├── Accruals/
├── Admin/
├── Roaming/
├── Tariff/
└── System/
```

Each helper encapsulates:
- **Main class** — primary operations (create, edit, delete)
- **Details class** — form-level field interactions
- **SearchImpl class** — grid search and navigation

---

## 3. Technology Stack

### 3.1 Core Frameworks

| Technology | Version | Purpose |
|-----------|---------|---------|
| **Java** | JDK 17.0.12 | Language runtime |
| **Selenium WebDriver** | 4.28.1 | Browser automation |
| **WebDriverManager** | 6.3.2 | Automatic driver binary management |
| **TestNG** | 7.5.1 | Test framework, execution, and reporting |
| **Maven** | 3.9.3 | Build and dependency management |
| **Maven Surefire** | 3.5.2 | Test execution plugin |

### 3.2 Reporting & Logging

| Technology | Version | Purpose |
|-----------|---------|---------|
| **ExtentReports** | 2.41.2 | HTML test execution reports |
| **Log4j 2** | 2.24.3 | Application logging (rolling file appender) |
| **SLF4J** | 1.7.36 | Logging facade (test scope) |

### 3.3 Data Handling

| Technology | Version | Purpose |
|-----------|---------|---------|
| **Apache POI** | 3.12 | Excel read/write (`.xlsx`) |
| **OpenCSV** | 2.3 | CSV parsing |
| **XMLBeans** | 2.6.0 | XML processing |
| **JSoup** | 1.10.2 | HTML parsing |

### 3.4 Database

| Technology | Version | Purpose |
|-----------|---------|---------|
| **DBUnit** | 2.7.3 | Database testing |
| **PostgreSQL JDBC** | 9.1-901.jdbc4 | PostgreSQL connectivity |
| **JTDS** | 1.3.1 | SQL Server connectivity |
| **C3P0** | 0.9.5.5 | Connection pooling |

### 3.5 Utilities

| Technology | Version | Purpose |
|-----------|---------|---------|
| **Apache HttpClient** | 4.5.2 | HTTP operations |
| **JSch** | 0.1.55 | SSH/SFTP for remote machines |
| **Joda Time** | 2.9.7 | Date/time operations |
| **JSON / json-simple** | 20160810 / 1.1.1 | JSON processing |
| **Sikulix** | 2.0.5 | Image-based recognition |
| **ZT-Zip** | 1.10 | Archive handling |

---

## 4. Configuration Management

### 4.1 Properties Files

**Primary configuration:** `rocps-automation/src/main/resources/psconfig.properties`

Key categories:
```properties
# Application
clientUrl=<client url to be updated>

# Browser
browser=firefox

# Paths
utilPath=C:\\...
downloadDirectory=C:\\...
dataDir=C:\\...

# Features
recordExecution=Yes
embedImageInReport=Yes
```

### 4.2 Object Repository (OR)

Element locators are externalized into `.properties` files in `roc-automation/src/main/resources/`:

| File | Module |
|------|--------|
| `OR.properties` | Core/shared elements |
| `PS_OR.properties` | Partner Settlement |
| `CM_OR.properties` | Change Management |
| `Tariff_OR.properties` | Tariff Management |
| `Users_OR.properties` | User Administration |
| `ROCView_OR.properties` | ROC View |
| `Measures_Audits_OR.properties` | Measures & Audits |
| `ROCRA_OR.properties` | ROCRA module |
| `LDC_OR.properties` | LDC module |

**OR entry format:**
```properties
Login_Username_TextBox=//input[@id='username-input-area' or @id='username']
Loading_Mask=//div[@class='phoenix-loading-image']/img[contains(@src,'loading.gif')]
```

**Locator resolution order** (via `GenericHelper.getORProperty()`):
1. XPath (starts with `//`)
2. ID attribute
3. Name attribute
4. CSS class
5. CSS selector
6. Title attribute
7. Text content

### 4.3 TestNG Suite Files

Located in `rocps-automation/src/main/resources/`:

| Suite File | Purpose |
|-----------|---------|
| `SystemTesting_RunScript.xml` | Primary system testing suite |
| `CICD_RunScript.xml` | CI/CD pipeline execution |
| `Regression_ROCView.xml` | ROC View regression |
| `Dummy_RunScript.xml` | Template for new suites |

**Suite parameters passed to tests:**
- `config` — Properties file name
- `orFiles` — Comma-separated OR file names
- `driverFile` — Excel test driver file name

---

## 5. Test Organization

### 5.1 Test Class Categories

| Category | Classes | Purpose |
|----------|---------|---------|
| Prerequisites | `Prerequisites`, `PSPrerequisites`, `ROCPreRequisites`, `BillPreRequisites` | Environment setup, master data creation |
| Controller | `ControllerStartup`, `ControllerStop` | Application controller lifecycle |
| Configuration | `TCConfigurations` | System configuration tests |
| Streams | `TCVoiceStream`, `TCFiosStream` | Stream processing tests |
| Functional | `TestCase01` through `TestCase17` | End-to-end business scenarios |

### 5.2 Test Execution Controls

**TestNG annotations used:**
```java
@Test(
    priority = 1,                    // Execution order
    description = "...",             // Human-readable description
    groups = {"Prerequisites4"},     // Logical grouping
    retryAnalyzer = Retry.class,     // Auto-retry on failure (max 2)
    dependsOnMethods = {"method"}    // Method-level dependency
)
```

**Suite-level settings:**
- `preserve-order="true"` — Respects class ordering in XML
- `configfailurepolicy="skip"` — Skips dependent tests when setup fails
- `verbose="2"` — Detailed console output

### 5.3 Lifecycle Hooks

| Annotation | Action |
|-----------|--------|
| `@BeforeSuite` | Initialize report, load configuration |
| `@BeforeClass` | Initialize WebDriver, set up browser |
| `@BeforeMethod` | Handle session timeout, start report entry |
| `@AfterMethod` | Capture result (pass/fail/skip), screenshot |
| `@AfterClass` | Flush report |
| `@AfterSuite` | Close browser, finalize report, update statistics |

---

## 6. Component Helper Library

The `roc-automation-util` project provides 28+ reusable component helpers:

### 6.1 UI Component Helpers

| Helper | Purpose |
|--------|---------|
| `TextBoxHelper` | Type, clear, get text from input fields |
| `ButtonHelper` | Click buttons, submit forms |
| `CheckBoxHelper` | Check, uncheck, verify state |
| `ComboBoxHelper` | Select dropdown values |
| `RadioHelper` | Radio button selection |
| `GridHelper` | Table/grid row interactions |
| `SearchGridHelper` | Grid search and filtering |
| `PropertyGridHelper` | Property grid operations |
| `CalendarHelper` | Date picker interactions |
| `TreeHelper` | Tree view navigation |
| `TabHelper` | Tab switching |
| `PopupHelper` | Dialog/popup handling |
| `LinkHelper` | Hyperlink interactions |
| `LabelHelper` | Label text retrieval |
| `ImageHelper` | Image element interactions |
| `MouseHelper` | Hover, drag-and-drop, right-click |
| `FileUpload` | File upload via Robot class |
| `FileDownload` | File download verification |

### 6.2 Application Helpers

| Helper | Purpose |
|--------|---------|
| `LoginHelper` | Authentication flows |
| `NavigationHelper` | Screen-to-screen navigation |
| `NavigationMenuHelper` | Menu tree interactions |
| `BrowserHelper` | Refresh, back, forward, window management |
| `ROCHelper` | Session timeout handling, ROC-specific operations |

### 6.3 Data & Utility Helpers

| Helper | Purpose |
|--------|---------|
| `ExcelReader` / `ExcelWriter` | Excel test data I/O |
| `CSVReader` | CSV file parsing |
| `DatabaseHelper` | DB connections, query execution |
| `DateHelper` / `StringHelper` | Data manipulation |
| `ValidationHelper` | Null/empty/value checks |
| `RemoteMachineHelper` | SSH/SFTP operations |
| `EmailHelper` | Email notifications |
| `WindowsHelper` | OS-level interactions |
| `VideoHelper` | Test execution recording |

---

## 7. Test Data Management

### 7.1 Data Sources

| Source | Location | Format |
|--------|----------|--------|
| Test case data | `rocps-automation/src/main/resources/SystemTestCases.xlsx` | Excel |
| Element scripts | `rocps-automation/src/main/resources/ElementsScripts.xlsx` | Excel |
| CDR files | `src/main/resources/Data/FunctionalTesting/CDRS/` | Various |
| Rate sheets | `src/main/resources/Data/FunctionalTesting/RateSheet/` | Various |
| Carrier invoices | `src/main/resources/Data/FunctionalTesting/CarrierInvoice/` | Various |
| Roaming data | `src/main/resources/Data/FunctionalTesting/RoamingFilePath/` | Various |

### 7.2 Data Resolution

`TestDataHelper` resolves test data values with special syntax:

| Syntax | Meaning | Example |
|--------|---------|---------|
| `value1;value2;value3` | Multiple field values (semicolon-delimited) | `"Field1;Field2;Field3"` |
| `value1\|value2` | Alternative values (pipe-delimited) | `"Option1\|Option2"` |
| `$$propertyName` | Property substitution from config | `$$clientUrl` |
| `$$NOW$$` | Current date/time injection | Dynamic timestamps |

### 7.3 Execution Statistics

After each run, results are appended to `ROCPS_Automation_Report.xlsx`:
- Date, Product, Baselined Tests, Automated Tests
- Total Executed, Passed, Failed, Skipped
- Execution Duration (seconds)

---

## 8. Reporting & Logging

### 8.1 ExtentReports

**Output:** `Report/Run<N>/<SuiteName>_<DateTime>_AT_Report.html`

Report flow:
1. **Suite start** — Report file created, run directory established
2. **Each test class** — New test entry with description
3. **Each method** — Step logged with status (Pass/Fail/Skip) and optional screenshot
4. **Suite end** — Report flushed, statistics written to Excel

### 8.2 Log4j 2

**Pattern:** `%d{yyyy-MM-dd HH:mm:ss} [%-5p] - %m%n`
**Output:** `Report/Run<N>/<SuiteName>_<DateTime>.log`
**Rolling:** 20MB file size threshold

### 8.3 Screenshots

- Captured on **every test method completion** (pass and fail)
- Embedded in ExtentReport when `embedImageInReport=Yes`
- Stored in the run's report directory

---

## 9. Wait Strategy

The framework uses a combination of wait mechanisms:

| Type | Usage |
|------|-------|
| **Load mask wait** | `GenericHelper.waitForLoadmask(seconds)` — waits for loading overlays to disappear |
| **Explicit wait** | `WebDriverWait` with `ExpectedConditions` for specific element states |
| **Fluent wait** | Custom polling intervals with ignored exceptions for complex scenarios |
| **Thread.sleep** | Used sparingly for hard waits where dynamic waits are insufficient |

---

## 10. Error Handling & Retry

### 10.1 Retry Mechanism

- **Implementation:** `Retry.java` implements `IRetryAnalyzer`
- **Max retries:** 2 attempts per failed test method
- **Scope:** Applied per test method via `retryAnalyzer = Retry.class`

### 10.2 Failure Handling

```
Test Failure → FailureHelper.setErrorMessage(exception)
             → Screenshot captured
             → ReportHelper.reportFailure()
             → TestNG marks test as FAIL
             → Retry analyzer decides whether to re-execute
```

### 10.3 Session Timeout Recovery

`ROCHelper.handleSessionTimeout()` runs in `@BeforeMethod` to detect and recover from application session timeouts, preventing cascading false failures.

---

## 11. Browser & WebDriver Management

### 11.1 Supported Browsers

| Browser | Driver Management | Configuration |
|---------|------------------|---------------|
| Firefox | WebDriverManager auto-download | Custom profile with download preferences |
| Chrome | WebDriverManager auto-download | Headless-capable, custom download directory |
| IE | WebDriverManager auto-download | Standard options |

### 11.2 Driver Lifecycle

- **Initialization:** `@BeforeClass` in `AcceptanceTest` — one driver instance per suite
- **Reuse:** Static `WebDriver driver` shared across all test classes
- **Teardown:** `@AfterSuite` calls `driver.quit()`

### 11.3 Browser Configuration

- Download directory set per browser type
- Firefox: Custom profile with MIME type handling
- Chrome: `--disable-notifications`, custom prefs for downloads
- Window maximized on launch

---

## 12. Coding Standards & Conventions

### 12.1 Naming Conventions

| Element | Convention | Example |
|---------|-----------|---------|
| Test classes | PascalCase, descriptive | `BillPreRequisites`, `TestCase01` |
| Test methods | camelCase, action-oriented | `createBillingStream()`, `verifyBillOutput()` |
| Helper classes | PascalCase, domain + "Helper" | `BillBreakdownConfiguration`, `TextBoxHelper` |
| OR keys | Module_Element_Type | `Login_Username_TextBox`, `Bill_Save_Button` |
| Properties | camelCase | `clientUrl`, `downloadDirectory` |
| Packages | lowercase, dot-separated | `com.subex.rocps.automation.helpers` |

### 12.2 Test Method Structure

```java
@Test(priority = N, description = "...", retryAnalyzer = Retry.class)
public void testMethodName() throws Exception {
    // 1. Navigate to screen
    // 2. Perform actions using helper classes
    // 3. Assert expected outcomes
    // 4. Errors handled via FailureHelper
}
```

### 12.3 Helper Method Structure

```java
public static void performAction(String param) throws Exception {
    // 1. Resolve OR locator via GenericHelper
    // 2. Wait for element readiness
    // 3. Perform interaction via component helper
    // 4. Log action via Log4jHelper
}
```

---

## 13. Build & Execution

### 13.1 Build Commands

```bash
# Build order (must follow dependency chain)
# 1. Foundation layer
cmd.exe /c "cd /d C:\Temp_Rahul\Claude\rocps_web_automation-main\roc-automation-util && C:\PlayWright_Java_Workspace\apache-maven-3.9.3-bin\apache-maven-3.9.3\bin\mvn.cmd clean install -DskipTests"

# 2. Middle layer
cmd.exe /c "cd /d C:\Temp_Rahul\Claude\rocps_web_automation-main\roc-automation && C:\PlayWright_Java_Workspace\apache-maven-3.9.3-bin\apache-maven-3.9.3\bin\mvn.cmd clean install -DskipTests"

# 3. Product layer
cmd.exe /c "cd /d C:\Temp_Rahul\Claude\rocps_web_automation-main\rocps-automation && C:\PlayWright_Java_Workspace\apache-maven-3.9.3-bin\apache-maven-3.9.3\bin\mvn.cmd clean install -DskipTests"
```

### 13.2 Test Execution

```bash
# Run via TestNG suite XML
cmd.exe /c "cd /d C:\Temp_Rahul\Claude\rocps_web_automation-main\rocps-automation && C:\PlayWright_Java_Workspace\apache-maven-3.9.3-bin\apache-maven-3.9.3\bin\mvn.cmd clean test -DsuiteXmlFile=src/main/resources/SystemTesting_RunScript.xml"
```

### 13.3 Build Notes

- **Environment:** WSL2 with Windows-side Java/Maven executables
- **JAVA_HOME:** `C:\Program Files\Java\jdk-17.0.12`
- Always use `clean` — Maven caches compiled classes and may report false success without it

---

## 14. Directory Structure

```
rocps_web_automation-main/
├── roc-automation-util/              # Foundation layer
│   ├── pom.xml
│   └── src/main/java/com/subex/automation/helpers/
│       ├── selenium/                 # AcceptanceTest base class
│       ├── component/                # UI component helpers (28+)
│       ├── componentHelpers/         # Low-level element helpers (22+)
│       ├── application/              # Browser, Login, Navigation helpers
│       ├── data/                     # Date, String, Validation helpers
│       ├── db/                       # Database helpers
│       ├── file/                     # Excel, CSV, XML readers/writers
│       ├── report/                   # ExtentReports, Log4j helpers
│       ├── config/                   # Property/Config readers
│       ├── util/                     # Email, SSH, Window, Video helpers
│       ├── scripts/                  # Test data resolution, script execution
│       ├── dataGeneration/           # Test data generation
│       └── TestNG/                   # Custom listener, Retry analyzer
│
├── roc-automation/                   # Middle layer
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/subex/automation/helpers/
│       │   └── selenium/             # ROCAcceptanceTest
│       └── resources/
│           ├── config.properties
│           ├── OR.properties         # Core Object Repository
│           ├── PS_OR.properties      # + 7 module-specific OR files
│           └── ...
│
└── rocps-automation/                 # Product layer
    ├── pom.xml
    └── src/main/
        ├── java/com/subex/rocps/automation/
        │   ├── helpers/
        │   │   ├── selenium/         # PSAcceptanceTest
        │   │   ├── listener/         # ROCPS-specific Retry
        │   │   ├── Bills/            # Billing helpers
        │   │   ├── Accruals/         # Accruals helpers
        │   │   ├── Admin/            # Admin helpers
        │   │   ├── Roaming/          # Roaming helpers
        │   │   └── System/           # System helpers
        │   └── testcases/
        │       └── systemtesting/    # All test classes
        └── resources/
            ├── psconfig.properties   # Primary configuration
            ├── SystemTesting_RunScript.xml  # TestNG suite
            ├── SystemTestCases.xlsx  # Test data
            └── Data/                 # CDRs, Rate Sheets, etc.
```

---

## 15. Key Design Decisions

| Decision | Rationale |
|----------|-----------|
| **Helper pattern over Page Objects** | Domain helpers align with business modules (Bills, Accruals) rather than individual pages, enabling richer business-level abstractions |
| **Static WebDriver** | Single browser session shared across all tests reduces setup overhead and mirrors real user workflow |
| **Externalized Object Repository** | `.properties` files for locators allow locator updates without recompilation |
| **Three-layer architecture** | Separates generic utilities (reusable) from product-specific logic (varies per deployment) |
| **Excel-driven test data** | Non-technical stakeholders can maintain test data without code changes |
| **Retry analyzer** | Mitigates flaky test failures from timing/network issues with automatic re-execution (max 2 retries) |
| **Session timeout handling** | Proactive detection in `@BeforeMethod` prevents cascading failures from idle sessions |

---

*Last updated: February 2026*
