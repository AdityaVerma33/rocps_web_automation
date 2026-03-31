# ROCPS Web Automation Framework — Improvement Blueprint

> **A practical, pattern-driven redesign guide to make the framework more readable, maintainable, scalable, and efficient.**

**Date:** February 26, 2026  
**Scope:** Architecture, Design Patterns, Code Quality, Execution, and Scalability

---

## Table of Contents

1. [Critical Problems in the Current Framework](#1-critical-problems-in-the-current-framework)
2. [Design Patterns — What to Apply and Why](#2-design-patterns--what-to-apply-and-why)
3. [Framework Architecture Improvements](#3-framework-architecture-improvements)
4. [Code-Level Best Practices](#4-code-level-best-practices)
5. [Execution Optimization](#5-execution-optimization)
6. [Maintainability Enhancements](#6-maintainability-enhancements)
7. [Scalability & Future-Proofing](#7-scalability--future-proofing)
8. [Migration Roadmap](#8-migration-roadmap)

---

## 1. Critical Problems in the Current Framework

After a deep code-level review, the following structural problems have been identified. Every recommendation in this document maps back to one of these root causes.

### 1.1 God-Object Anti-Pattern — `AcceptanceTest`

```
AcceptanceTest.java (614 lines)
├── 30+ public static fields (driver, configProp, report, testReport, etc.)
├── WebDriver lifecycle      (@BeforeClass / @AfterClass / @AfterSuite)
├── Browser factory logic     (setFirefoxDriver, setChromeDriver, setIEDriver)
├── Firefox profile config    (100+ lines of commented-out code)
├── CSV run-counter logic     (increaseCounter — 50 lines)
├── Time calculation logic    (getTimeTaken, getTime, getTimeInSeconds — 100 lines)
├── Statistics report writing (generateStatisticsReport — 50 lines)
└── Remote SSH session fields (sftpChannel, execChannel, execSession)
```

**Problems:**
- One class owns driver creation, browser config, reporting, statistics, time math, CSV I/O, and SSH state.
- Everything is `public static`, making parallel execution impossible — a single WebDriver instance is shared.
- Test classes, helpers, and utilities all inherit from it — so every class carries the full weight of every concern.

### 1.2 Toxic Inheritance Chain

```java
AcceptanceTest                 ← 30+ static fields, browser setup, report, stats
  └── ROCAcceptanceTest        ← 391 lines: config loading, OR loading, login, video, remote connections
        └── PSAcceptanceTest   ← Empty class (0 logic)
              └── Agent        ← Business helper that also IS-A PSAcceptanceTest
              └── TCAgent      ← Test class
              └── BrowserHelper
              └── GenericHelper (900 lines)
              └── GridHelper   (2306 lines)
              └── LoginHelper  (432 lines)
              └── ReportHelper (566 lines)
              └── FailureHelper
              └── ComponentHelper
              └── NavigationHelper (948 lines)
              └── ... every single class
```

**Problems:**
- **Every** helper, utility, and test class extends `AcceptanceTest`. This means:
  - A `GridHelper` is conceptually "a test" (nonsensical).
  - Changing `AcceptanceTest` can break 200+ classes.
  - Inheritance is used for *field access* (`driver`, `or`, `configProp`), not for polymorphism.
- `PSAcceptanceTest` is a completely empty class — pure ceremony with zero value.

### 1.3 Static Mutable State Everywhere

```java
public static WebDriver driver = null;          // Shared single driver
public static PropertyReader configProp = null;  // Shared config
public static Properties or = null;              // Shared Object Repository
public static ExtentReports report = null;       // Shared reporter
public static ExtentTest testReport = null;      // Shared test report
public static String result = null;              // Shared pass/fail flag
public static String errorMsg = null;            // Shared error message
public static String stepKeys = "";              // Shared step keys
```

**Problems:**
- Completely blocks parallel execution — two tests writing to the same `driver` or `result` would collide.
- Makes debugging nearly impossible — any class anywhere can silently overwrite `errorMsg`.
- Tight coupling: 200+ classes reach into the same static mutable bag.

### 1.4 Massive Boilerplate in Test Classes

Every test method across 150+ classes repeats the exact same pattern:

```java
@Test(priority = 1, description = "Agent creation",
      retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
public void agentCreation() throws Exception {
    try {
        Agent accobj = new Agent(path, workBookName, sheetName, "Agent", 1);
        accobj.agentCreation();
    } catch (Exception e) {
        FailureHelper.reportFailure(e);
        throw e;
    }
}
```

**Problems:**
- The `try/catch` wrapping `FailureHelper.reportFailure(e)` is repeated in every single method.
- `path`, `workBookName`, `sheetName` are repeated fields in every test class.
- The fully-qualified `retryAnalyzer` class name is copy-pasted everywhere.
- No way to add cross-cutting concerns (logging, screenshots, timing) without touching every method.

### 1.5 Business Helpers Extend the Test Base

```java
public class Agent extends PSAcceptanceTest { ... }        // Helper IS-A Test?
public class BrowserHelper extends AcceptanceTest { ... }  // Utility IS-A Test?
public class GridHelper extends ComponentHelper { ... }    // Component IS-A Test?
public class ReportHelper extends AcceptanceTest { ... }   // Reporter IS-A Test?
```

**Problems:**
- Helpers inherit from test base only to access `driver`, `or`, and `configProp`.
- They carry all TestNG lifecycle annotations baggage.
- Violates Single Responsibility Principle and Liskov Substitution Principle.

### 1.6 No Thread-Safety

- `WebDriver` is a single `public static` instance.
- All helpers access it via inheritance.
- Running two tests concurrently would cause immediate `NoSuchSessionException` or data corruption.

---

## 2. Design Patterns — What to Apply and Why

### 2.1 Singleton Pattern — Driver Manager & Configuration

**Problem it solves:** Multiple `PropertyReader` instantiations, uncontrolled driver lifecycle.

**Where to apply:** `DriverManager` and `ConfigManager` classes.

```java
/**
 * Thread-safe Singleton for configuration.
 * Loaded once per JVM, immutable after init.
 */
public final class ConfigManager {

    private static volatile ConfigManager instance;
    private final Properties config;
    private final Properties objectRepository;

    private ConfigManager(String configFile, String[] orFiles) {
        this.config = loadConfig(configFile);
        this.objectRepository = loadOR(orFiles);
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("ConfigManager not initialized. Call init() first.");
        }
        return instance;
    }

    public static synchronized void init(String configFile, String[] orFiles) {
        if (instance == null) {
            instance = new ConfigManager(configFile, orFiles);
        }
    }

    public String get(String key) {
        return config.getProperty(key, "").trim();
    }

    public String getLocator(String key) {
        String value = objectRepository.getProperty(key);
        return (value != null) ? value.trim() : key; // fallback to raw xpath
    }

    public int getInt(String key, int defaultValue) {
        String val = config.getProperty(key);
        return (val != null && !val.isEmpty()) ? Integer.parseInt(val.trim()) : defaultValue;
    }

    // ... private loader methods
}
```

**Why Singleton for Config:**
- Config is read-only after initialization.
- Must be accessible everywhere without passing as constructor arg.
- Exactly one instance is semantically correct.

### 2.2 Factory Pattern — Browser/WebDriver Creation

**Problem it solves:** The 200-line `switch(browser)` block inside `AcceptanceTest.startWebDriver()`.

```java
/**
 * Factory that produces configured WebDriver instances.
 * Each call returns a NEW driver — safe for parallel execution.
 */
public final class WebDriverFactory {

    private WebDriverFactory() {} // non-instantiable

    public static WebDriver createDriver(BrowserType browser, String downloadPath) {
        switch (browser) {
            case FIREFOX: return createFirefoxDriver(downloadPath);
            case CHROME:  return createChromeDriver(downloadPath);
            case IE:      return createIEDriver();
            default: throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    private static WebDriver createFirefoxDriver(String downloadPath) {
        FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);
        options.addPreference("layout.css.devPixelsPerPx", "0.9");
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.download.dir", downloadPath);
        options.addPreference("browser.download.manager.showWhenStarting", false);
        options.addPreference("browser.helperApps.neverAsk.saveToDisk",
            "text/plain;text/csv;application/pdf;" +
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;" +
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        return new FirefoxDriver(options);
    }

    private static WebDriver createChromeDriver(String downloadPath) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito", "disable-infobars",
            "start-maximized", "--force-device-scale-factor=0.9");
        options.setExperimentalOption("excludeSwitches",
            List.of("enable-automation"));
        options.setAcceptInsecureCerts(true);
        Map<String, Object> prefs = Map.of(
            "profile.default_content_settings.popups", 0,
            "download.default_directory", downloadPath
        );
        options.setExperimentalOption("prefs", prefs);
        return new ChromeDriver(options);
    }

    private static WebDriver createIEDriver() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.setCapability("EnableNativeEvents", false);
        options.setCapability("ignoreZoomSetting", true);
        options.setAcceptInsecureCerts(true);
        return new InternetExplorerDriver(options);
    }
}
```

**Why Factory:**
- Encapsulates all browser-specific configuration in one place.
- Easy to add Edge, Safari, or Remote WebDriver later.
- Test classes never touch driver creation details.
- Returns new instances → parallel-safe.

### 2.3 Strategy Pattern — Wait Strategies & Synchronization

**Problem it solves:** Hardcoded waits and inconsistent synchronization across helpers.

```java
/**
 * Strategy interface for different synchronization approaches.
 */
public interface WaitStrategy {
    void waitUntilReady(WebDriver driver, int timeoutSeconds);
}

public class LoadMaskWaitStrategy implements WaitStrategy {
    @Override
    public void waitUntilReady(WebDriver driver, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
            .until(d -> {
                // Wait until load mask is not displayed
                JavascriptExecutor js = (JavascriptExecutor) d;
                return (Boolean) js.executeScript(
                    "return document.querySelectorAll('.x-mask').length === 0;");
            });
    }
}

public class AjaxCompleteWaitStrategy implements WaitStrategy {
    @Override
    public void waitUntilReady(WebDriver driver, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
            .until(d -> {
                JavascriptExecutor js = (JavascriptExecutor) d;
                return (Boolean) js.executeScript(
                    "return (typeof jQuery === 'undefined') || jQuery.active === 0;");
            });
    }
}

public class PageLoadWaitStrategy implements WaitStrategy {
    @Override
    public void waitUntilReady(WebDriver driver, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
            .until(d -> ((JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));
    }
}

// Usage in a context:
public class SyncManager {
    private WaitStrategy strategy;

    public SyncManager(WaitStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(WaitStrategy strategy) {
        this.strategy = strategy;
    }

    public void waitUntilReady(WebDriver driver, int timeout) {
        strategy.waitUntilReady(driver, timeout);
    }
}
```

**Why Strategy:**
- Different pages may need different wait approaches (load mask, AJAX, page load).
- Swappable at runtime per context.
- New strategies can be added without modifying existing code.

### 2.4 Builder Pattern — Test Data Construction

**Problem it solves:** Massive constructors like `new Agent(path, workBookName, sheetName, "Agent", 1)` and 30+ field initializations in `initializeVariables()`.

```java
/**
 * Builder for constructing test data from Excel in a type-safe, readable way.
 */
public class TestDataBuilder {

    private String basePath;
    private String workbook;
    private String sheet;
    private String testCaseId;
    private int occurrence = 1;

    private TestDataBuilder() {}

    public static TestDataBuilder create() {
        return new TestDataBuilder();
    }

    public TestDataBuilder fromWorkbook(String workbook) {
        this.workbook = workbook;
        return this;
    }

    public TestDataBuilder sheet(String sheet) {
        this.sheet = sheet;
        return this;
    }

    public TestDataBuilder testCase(String testCaseId) {
        this.testCaseId = testCaseId;
        return this;
    }

    public TestDataBuilder occurrence(int occurrence) {
        this.occurrence = occurrence;
        return this;
    }

    public TestData build() {
        ExcelReader reader = new ExcelReader();
        Map<String, ArrayList<String>> rawData = reader.readDataByColumn(
            basePath, workbook, sheet, testCaseId, occurrence);
        return new TestData(rawData);
    }
}

// Immutable test data holder:
public class TestData {
    private final Map<String, ArrayList<String>> data;

    TestData(Map<String, ArrayList<String>> data) {
        this.data = Collections.unmodifiableMap(data);
    }

    public String get(String key) {
        List<String> values = data.get(key);
        return (values != null && !values.isEmpty()) ? values.get(0) : "";
    }

    public String get(String key, int index) {
        List<String> values = data.get(key);
        return (values != null && values.size() > index) ? values.get(index) : "";
    }

    public int columnCount() {
        return data.values().stream().mapToInt(List::size).max().orElse(0);
    }
}

// Usage — clean and readable:
TestData agentData = TestDataBuilder.create()
    .fromWorkbook("FunctionalTestCases.xlsx")
    .sheet("Agent")
    .testCase("Agent")
    .occurrence(1)
    .build();

String companyName = agentData.get("CompanyName");
```

**Why Builder:**
- Eliminates constructor parameter confusion (what is param 4? param 5?).
- Self-documenting code.
- Easy to extend with new options (environment, locale, etc.).

### 2.5 Dependency Injection (Manual) — Eliminate Inheritance-for-Access

**Problem it solves:** Every helper extends `AcceptanceTest` just to reach `driver`, `or`, `configProp`.

**Current anti-pattern:**
```java
public class GridHelper extends ComponentHelper {       // inherits AcceptanceTest
    public static boolean isPresent(String gridId) {
        gridId = GenericHelper.getORProperty(gridId);   // static call to reach `or`
        // uses `driver` from AcceptanceTest via inheritance
    }
}
```

**Proposed approach — inject dependencies via a context object:**

```java
/**
 * Thread-local context holding all test-scoped dependencies.
 * This is the SINGLE replacement for 30+ static fields in AcceptanceTest.
 */
public class TestContext {

    private static final ThreadLocal<TestContext> CONTEXT = new ThreadLocal<>();

    private final WebDriver driver;
    private final ConfigManager config;
    private final SyncManager syncManager;
    private final ReportManager reportManager;

    private TestContext(WebDriver driver, ConfigManager config,
                        SyncManager syncManager, ReportManager reportManager) {
        this.driver = driver;
        this.config = config;
        this.syncManager = syncManager;
        this.reportManager = reportManager;
    }

    /** Initialize at test start, accessible anywhere via get() */
    public static void init(WebDriver driver, ConfigManager config,
                            SyncManager sync, ReportManager report) {
        CONTEXT.set(new TestContext(driver, config, sync, report));
    }

    public static TestContext get() {
        TestContext ctx = CONTEXT.get();
        if (ctx == null) throw new IllegalStateException("TestContext not initialized for this thread.");
        return ctx;
    }

    public static void clear() {
        TestContext ctx = CONTEXT.get();
        if (ctx != null && ctx.driver != null) {
            ctx.driver.quit();
        }
        CONTEXT.remove();
    }

    public WebDriver driver()           { return driver; }
    public ConfigManager config()       { return config; }
    public SyncManager sync()           { return syncManager; }
    public ReportManager report()       { return reportManager; }

    public String locator(String key)   { return config.getLocator(key); }
}
```

**Now helpers NO LONGER need inheritance:**

```java
// BEFORE: public class GridHelper extends ComponentHelper { ... }
// AFTER:
public final class GridHelper {

    private GridHelper() {}

    public static boolean isPresent(String gridId) {
        WebDriver driver = TestContext.get().driver();
        String resolvedId = TestContext.get().locator(gridId);
        // ... use driver and resolvedId directly
    }

    public static String getCellValue(String gridId, int row, String column) {
        WebDriver driver = TestContext.get().driver();
        // ... pure utility, no inheritance needed
    }
}
```

**Why DI (via ThreadLocal context):**
- Eliminates the toxic inheritance chain entirely.
- Each thread gets its own `TestContext` → parallel execution is trivially safe.
- Helpers become pure utility classes (final, non-inheriting, stateless).
- Single point of control for driver lifecycle.

### 2.6 Template Method Pattern — Eliminate Try-Catch Boilerplate

**Problem it solves:** Every test method wraps the same `try/catch/FailureHelper` block.

```java
/**
 * Base test class using Template Method pattern.
 * Subclasses never write try/catch — the listener handles failures.
 */
public abstract class BaseTest {

    protected final String workbook;
    protected final String dataPath;

    protected BaseTest() {
        this.dataPath = System.getProperty("user.dir") + "/src/main/resources/";
        this.workbook = "FunctionalTestCases.xlsx";
    }

    protected TestData loadData(String sheet, String testCase) {
        return TestDataBuilder.create()
            .fromWorkbook(workbook)
            .sheet(sheet)
            .testCase(testCase)
            .build();
    }

    protected TestData loadData(String sheet, String testCase, int occurrence) {
        return TestDataBuilder.create()
            .fromWorkbook(workbook)
            .sheet(sheet)
            .testCase(testCase)
            .occurrence(occurrence)
            .build();
    }
}
```

**Combined with a TestNG Listener that handles failures globally:**

```java
/**
 * Listener that captures failures, takes screenshots, and reports —
 * so individual test methods never need try/catch.
 */
public class AutomationTestListener implements ITestListener, IInvokedMethodListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Throwable cause = result.getThrowable();
        TestContext ctx = TestContext.get();

        // Screenshot
        String screenshot = ScreenshotHelper.capture(ctx.driver(), result.getName());

        // Report
        ctx.report().fail(result.getName(), cause, screenshot);

        // Log
        Log4jHelper.logError("FAILED: " + result.getName(), cause);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        TestContext ctx = TestContext.get();
        ctx.report().pass(result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        TestContext ctx = TestContext.get();
        ctx.report().skip(result.getName());
    }
}
```

**Now test classes become clean:**

```java
// BEFORE: 10 lines per method, repeated 150+ times
// AFTER:
@Listeners(AutomationTestListener.class)
public class TCAgent extends BaseTest {

    private static final String SHEET = "Agent";

    @Test(priority = 1, description = "Agent creation")
    public void agentCreation() {
        TestData data = loadData(SHEET, "Agent");
        new AgentService().create(data);
    }

    @Test(priority = 2, description = "Agent with Parent Account")
    public void agentWithParentCreation() {
        TestData data = loadData(SHEET, "ParentAgent");
        new AgentService().create(data);
    }

    @Test(priority = 7, description = "Agent deletion")
    public void agentDelete() {
        TestData data = loadData(SHEET, "AgentDelete");
        new AgentService().delete(data);
    }
}
```

**Why Template Method + Listener:**
- Eliminates ~1000+ lines of duplicated try/catch across 150+ test classes.
- Cross-cutting concerns (screenshot, logging, reporting) handled in ONE place.
- Test methods are 2-3 lines instead of 10-12.
- Adding a new concern (e.g., video recording on failure) requires changing one class, not 150.

### 2.7 Summary of Design Patterns

| Pattern | Where Applied | What It Replaces | Key Benefit |
|---------|--------------|-----------------|-------------|
| **Singleton** | `ConfigManager` | 30+ `public static` fields in `AcceptanceTest` | Single source of truth, thread-safe |
| **Factory** | `WebDriverFactory` | 200-line `switch(browser)` block | Encapsulated browser creation, extensible |
| **Strategy** | `WaitStrategy` / `SyncManager` | Hardcoded `waitForLoadmask()` calls | Swappable sync per context |
| **Builder** | `TestDataBuilder` | Complex constructors with 5 params | Readable, self-documenting data setup |
| **Dependency Injection** | `TestContext` (ThreadLocal) | Toxic inheritance chain for field access | Parallel-safe, no inheritance needed |
| **Template Method** | `BaseTest` + `AutomationTestListener` | Repeated try/catch in every test method | Zero boilerplate, centralized failure handling |

---

## 3. Framework Architecture Improvements

### 3.1 Proposed Folder Structure

```
rocps-web-automation/
├── pom.xml (parent POM with module declarations)
│
├── framework-core/                         ← Was: roc-automation-util
│   └── src/main/java/com/subex/automation/
│       ├── context/
│       │   └── TestContext.java             ← ThreadLocal context (replaces AcceptanceTest static fields)
│       ├── config/
│       │   ├── ConfigManager.java           ← Singleton config reader
│       │   └── BrowserType.java             ← Enum (FIREFOX, CHROME, IE, EDGE)
│       ├── driver/
│       │   └── WebDriverFactory.java        ← Factory for driver creation
│       ├── component/                       ← Pure utility classes (no inheritance)
│       │   ├── GridHelper.java
│       │   ├── TextBoxHelper.java
│       │   ├── ComboBoxHelper.java
│       │   ├── ButtonHelper.java
│       │   ├── CheckBoxHelper.java
│       │   └── ... (all component helpers)
│       ├── sync/
│       │   ├── WaitStrategy.java            ← Strategy interface
│       │   ├── LoadMaskWaitStrategy.java
│       │   ├── AjaxCompleteWaitStrategy.java
│       │   └── SyncManager.java
│       ├── data/
│       │   ├── TestData.java                ← Immutable data holder
│       │   ├── TestDataBuilder.java         ← Builder for data construction
│       │   ├── ExcelDataProvider.java       ← Excel reader
│       │   └── CsvDataProvider.java         ← CSV reader
│       ├── report/
│       │   ├── ReportManager.java           ← Reporting abstraction
│       │   ├── ExtentReportManager.java     ← ExtentReports implementation
│       │   └── ScreenshotHelper.java        ← Screenshot capture
│       ├── db/
│       │   ├── DatabaseManager.java         ← Connection pooling + queries
│       │   └── QueryExecutor.java
│       ├── remote/
│       │   ├── SshManager.java              ← SSH operations
│       │   └── FileTransferManager.java     ← SFTP operations
│       └── listener/
│           ├── AutomationTestListener.java  ← Global failure handler
│           ├── RetryAnalyzer.java           ← Configurable retry
│           └── SuiteListener.java           ← Suite setup/teardown
│
├── framework-roc/                           ← Was: roc-automation
│   └── src/main/java/com/subex/roc/
│       ├── navigation/
│       │   ├── NavigationService.java       ← Menu navigation
│       │   └── ScreenNavigator.java
│       ├── application/
│       │   ├── LoginService.java            ← Login operations
│       │   ├── ControllerService.java       ← Controller management
│       │   └── ExportService.java
│       ├── locators/                        ← Object Repository organized by module
│       │   ├── common/
│       │   │   └── common_locators.properties
│       │   ├── users/
│       │   │   └── user_locators.properties
│       │   └── tariff/
│       │       └── tariff_locators.properties
│       └── base/
│           └── BaseTest.java                ← Template method base class
│
├── rocps-tests/                             ← Was: rocps-automation
│   ├── src/main/java/com/subex/rocps/
│   │   ├── services/                        ← Business logic (separated from tests)
│   │   │   ├── partner/
│   │   │   │   ├── AgentService.java
│   │   │   │   ├── AccountService.java
│   │   │   │   └── OperatorService.java
│   │   │   ├── billing/
│   │   │   │   ├── BillService.java
│   │   │   │   └── AccrualService.java
│   │   │   ├── tariff/
│   │   │   │   └── TariffService.java
│   │   │   └── roaming/
│   │   │       └── RoamingService.java
│   │   └── model/                           ← Domain models (NOT 30 loose fields)
│   │       ├── AgentModel.java
│   │       ├── AccountModel.java
│   │       └── BillModel.java
│   │
│   ├── src/test/java/com/subex/rocps/       ← Test classes in src/test
│   │   ├── functional/
│   │   │   ├── partner/
│   │   │   │   ├── TCAgent.java
│   │   │   │   ├── TCAccount.java
│   │   │   │   └── TCOperator.java
│   │   │   ├── billing/
│   │   │   │   ├── TCBills.java
│   │   │   │   └── TCAccruals.java
│   │   │   └── tariff/
│   │   │       └── TCTariff.java
│   │   └── system/
│   │       ├── TCEndToEndFlow01.java
│   │       └── TCEndToEndFlow02.java
│   │
│   └── src/test/resources/
│       ├── config/
│       │   ├── psconfig.properties
│       │   ├── psconfig-staging.properties
│       │   └── psconfig-prod.properties
│       ├── testdata/
│       │   ├── FunctionalTestCases.xlsx
│       │   └── SystemTestCases.xlsx
│       ├── locators/
│       │   ├── PS_OR.properties
│       │   └── Tariff_OR.properties
│       └── suites/
│           ├── functional-suite.xml
│           ├── system-suite.xml
│           ├── smoke-suite.xml
│           └── regression-suite.xml
```

### 3.2 Key Architecture Changes

| Current | Proposed | Rationale |
|---------|----------|-----------|
| Tests in `src/main/java` | Tests in `src/test/java` | Maven convention; `test-jar` plugin workaround not needed |
| 30+ static fields on `AcceptanceTest` | `TestContext` (ThreadLocal) | Thread-safe, parallel-ready |
| Helpers extend `AcceptanceTest` | Helpers are final utility classes | No inheritance baggage |
| `PSAcceptanceTest` (empty) | Deleted | Zero value |
| `Agent` helper IS-A `PSAcceptanceTest` | `AgentService` (standalone service) | SRP: business logic ≠ test lifecycle |
| Config hardcoded to single file | Environment-aware config loading | Profile-based: `dev`, `staging`, `prod` |
| Single TestNG XML with everything commented | Separate suite files by purpose | Clear, maintainable, selectable |

### 3.3 Driver Management Strategy

```java
/**
 * Manages WebDriver lifecycle per test class or per thread.
 * Integrated with TestContext for thread-safety.
 */
public class DriverManager {

    public static void initDriver() {
        ConfigManager config = ConfigManager.getInstance();
        BrowserType browser = BrowserType.fromString(config.get("browser"));
        String downloadPath = config.get("downloadDirectory");

        WebDriver driver = WebDriverFactory.createDriver(browser, downloadPath);
        driver.manage().window().maximize();
        driver.get(config.get("clientUrl"));

        // Store in thread-local context
        TestContext.init(driver, config,
            new SyncManager(new LoadMaskWaitStrategy()),
            new ExtentReportManager());
    }

    public static void quitDriver() {
        TestContext.clear(); // quits driver and cleans up
    }
}
```

### 3.4 Environment Configuration Management

```java
/**
 * Config loading with environment awareness.
 *
 * Priority: System property > env variable > properties file
 * Profile: -Denv=staging → loads psconfig-staging.properties
 */
public final class ConfigManager {
    // ...

    private Properties loadConfig(String baseConfigFile) {
        String env = System.getProperty("env", "default");
        String envFile = baseConfigFile.replace(".properties", "-" + env + ".properties");

        Properties props = new Properties();

        // Load base config first
        loadPropertiesFile(props, baseConfigFile);

        // Override with environment-specific file
        if (!"default".equals(env)) {
            loadPropertiesFile(props, envFile);
        }

        // Override with system properties (-DclientUrl=http://...)
        System.getProperties().forEach((k, v) -> {
            String key = k.toString();
            if (props.containsKey(key)) {
                props.setProperty(key, v.toString());
            }
        });

        return props;
    }
}
```

**Usage:**
```bash
# Run against staging
mvn test -Denv=staging -Dtestng.filename=smoke-suite.xml

# Override specific properties
mvn test -DclientUrl=http://staging.example.com:8080/rocps -Dbrowser=chrome
```

---

## 4. Code-Level Best Practices

### 4.1 Reducing Duplication — Before vs After

**BEFORE (Current — repeated 150+ times):**
```java
public class TCAgent extends PSAcceptanceTest {
    String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
    String workBookName = "FunctionalTestCases.xlsx";
    String sheetName = "Agent";

    @Test(priority = 1, description = "Agent creation",
          retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
    public void agentCreation() throws Exception {
        try {
            Agent accobj = new Agent(path, workBookName, sheetName, "Agent", 1);
            accobj.agentCreation();
        } catch (Exception e) {
            FailureHelper.reportFailure(e);
            throw e;
        }
    }
    // ... 7 more methods with identical pattern
}
```

**AFTER (Proposed — clean, no boilerplate):**
```java
@Listeners(AutomationTestListener.class)
public class TCAgent extends BaseTest {

    private final AgentService agentService = new AgentService();

    @Test(priority = 1, description = "Agent creation")
    public void agentCreation() {
        agentService.create(loadData("Agent", "Agent"));
    }

    @Test(priority = 2, description = "Agent with Parent Account creation")
    public void agentWithParentCreation() {
        agentService.create(loadData("Agent", "ParentAgent"));
    }

    @Test(priority = 3, description = "Agent Column Validation")
    public void agentColumnValidation() {
        agentService.validateSearchColumns(loadData("Agent", "AgentSearchScreencolVal"));
    }

    @Test(priority = 7, description = "Agent deletion")
    public void agentDelete() {
        agentService.delete(loadData("Agent", "AgentDelete"));
    }

    @Test(priority = 8, description = "Agent un-delete")
    public void agentUnDelete() {
        agentService.undelete(loadData("Agent", "AgentUnDelete"));
    }
}
```

**Lines of code comparison:**
- Before: 137 lines (TCAgent.java)
- After: ~30 lines
- **~78% reduction per test class × 150+ classes = ~16,000 lines eliminated**

### 4.2 Improving Reusability — Service Layer

**BEFORE:** `Agent` class (314 lines) mixes data loading, variable initialization (30+ fields), UI operations, navigation, and assertions — all in one class that also extends `PSAcceptanceTest`.

**AFTER:** Clean service class with single responsibility:

```java
/**
 * Pure business service — no test inheritance, no data loading.
 * Receives data, performs operations.
 */
public class AgentService {

    private final NavigationService nav = new NavigationService();
    private final SyncManager sync = TestContext.get().sync();

    public void create(TestData data) {
        nav.navigateTo("Agent");

        for (int i = 0; i < data.columnCount(); i++) {
            AgentModel agent = AgentModel.from(data, i);
            ButtonHelper.click("ClearButton");
            sync.waitUntilReady(TestContext.get().driver(), 30);

            if (!isPresent(agent.getCompanyName())) {
                openNewForm(agent.getPartition());
                fillDetails(agent);
                save(agent.displayName());
                Log4jHelper.logInfo("Agent created: " + agent.getCompanyName());
            } else {
                Log4jHelper.logInfo("Agent already exists: " + agent.getCompanyName());
            }
        }
    }

    public void delete(TestData data) {
        nav.navigateTo("Agent");
        for (int i = 0; i < data.columnCount(); i++) {
            AgentModel agent = AgentModel.from(data, i);
            // ... deletion logic
        }
    }

    private boolean isPresent(String companyName) {
        SearchGridHelper.searchWithTextBox("Detail_companyName_txtID", companyName, "Agent");
        return GridHelper.getRowCount("SearchGrid") > 0;
    }
    // ...
}
```

**Domain Model — replaces 30+ loose fields:**

```java
/**
 * Immutable domain model for Agent data.
 * Replaces 30+ individual String fields scattered across the old Agent helper.
 */
public class AgentModel {
    private final String partition;
    private final String companyName;
    private final String company;
    private final String agentCode;
    private final String currency;
    private final String parentAgent;
    // ... other fields

    private AgentModel(Builder builder) {
        this.partition = builder.partition;
        this.companyName = builder.companyName;
        this.company = builder.company;
        this.agentCode = builder.agentCode;
        this.currency = builder.currency;
        this.parentAgent = builder.parentAgent;
    }

    /** Factory method to hydrate from TestData at a given column index */
    public static AgentModel from(TestData data, int index) {
        return new Builder()
            .partition(data.get("Partition", index))
            .companyName(data.get("CompanyName", index))
            .company(data.get("Company", index))
            .agentCode(data.get("AgentCode", index))
            .currency(data.get("Currency", index))
            .parentAgent(data.get("ParentAgent", index))
            .build();
    }

    public String displayName() {
        return company + " (" + agentCode + ")";
    }

    // Getters...

    public static class Builder {
        private String partition, companyName, company, agentCode, currency, parentAgent;
        public Builder partition(String v)    { this.partition = v; return this; }
        public Builder companyName(String v)  { this.companyName = v; return this; }
        public Builder company(String v)      { this.company = v; return this; }
        public Builder agentCode(String v)    { this.agentCode = v; return this; }
        public Builder currency(String v)     { this.currency = v; return this; }
        public Builder parentAgent(String v)  { this.parentAgent = v; return this; }
        public AgentModel build()             { return new AgentModel(this); }
    }
}
```

### 4.3 Exception Handling Improvements

**BEFORE — Scattered, inconsistent:**
```java
// In EVERY method:
try {
    // ... code
} catch (Exception e) {
    FailureHelper.setErrorMessage(e);   // sometimes
    FailureHelper.reportFailure(e);     // sometimes
    throw e;                            // sometimes
    // sometimes just e.printStackTrace()
}
```

**AFTER — Centralized, layered:**

```java
/**
 * Custom exception hierarchy for clear error categorization.
 */
public class AutomationException extends RuntimeException {
    public AutomationException(String message) { super(message); }
    public AutomationException(String message, Throwable cause) { super(message, cause); }
}

public class ElementNotFoundException extends AutomationException {
    public ElementNotFoundException(String locator) {
        super("Element not found: " + locator);
    }
}

public class NavigationException extends AutomationException {
    public NavigationException(String screen) {
        super("Failed to navigate to screen: " + screen);
    }
}

public class DataException extends AutomationException {
    public DataException(String message) {
        super("Test data error: " + message);
    }
}
```

**Exception handling rules:**
1. **Helpers** throw domain-specific `AutomationException` subtypes (never raw `Exception`).
2. **Services** catch helper exceptions and add business context, then re-throw.
3. **Test methods** never catch exceptions — the `AutomationTestListener` handles everything globally.
4. **No more `FailureHelper.setErrorMessage(e); throw e;`** boilerplate.

### 4.4 Logging and Reporting Enhancements

**BEFORE:**
- `ReportHelper` extends `AcceptanceTest` (566 lines).
- Mixes screenshot capture, HTML report generation, system info, and step tracking.
- `Log4jHelper` is a static utility with no structured context.

**AFTER — Clean reporting abstraction:**

```java
/**
 * Interface for reporting — allows swapping implementations.
 * (ExtentReports today, Allure tomorrow, without touching test code.)
 */
public interface ReportManager {
    void startSuite(String suiteName);
    void startTest(String testName);
    void pass(String stepName);
    void pass(String stepName, String details);
    void fail(String stepName, Throwable cause, String screenshotPath);
    void warn(String stepName, String message);
    void skip(String stepName);
    void info(String message);
    void endTest();
    void endSuite();
}

/**
 * ExtentReports implementation.
 */
public class ExtentReportManager implements ReportManager {

    private final ExtentReports report;
    private ExtentTest currentTest;

    public ExtentReportManager(String reportPath) {
        this.report = new ExtentReports(reportPath);
        File configFile = new File("src/main/resources/extent-config.xml");
        if (configFile.exists()) report.loadConfig(configFile);
    }

    @Override
    public void fail(String stepName, Throwable cause, String screenshotPath) {
        String details = cause.getMessage();
        if (screenshotPath != null) {
            details += currentTest.addScreenCapture(screenshotPath);
        }
        currentTest.log(LogStatus.FAIL, stepName, details);
    }
    // ... other methods
}
```

### 4.5 Proper Synchronization Strategy

**BEFORE — Inconsistent, fragile:**
```java
GenericHelper.waitForLoadmask();                    // used everywhere
GenericHelper.waitForLoadmask(searchScreenWaitSec); // magic static int
GenericHelper.waitForSave();                        // duplicated
GenericHelper.waitForSave(waitTimeInSecs);          // duplicated
```

**AFTER — Fluent, strategy-based:**

```java
/**
 * Fluent synchronization API.
 */
public final class Sync {

    private Sync() {}

    /** Wait for load mask to disappear */
    public static void forLoadMask() {
        TestContext ctx = TestContext.get();
        ctx.sync().waitUntilReady(ctx.driver(),
            ctx.config().getInt("searchScreenWaitSec", 30));
    }

    /** Wait for save to complete (uses longer timeout) */
    public static void forSave() {
        TestContext ctx = TestContext.get();
        ctx.sync().waitUntilReady(ctx.driver(),
            ctx.config().getInt("detailScreenWaitSec", 60));
    }

    /** Wait for element to be clickable */
    public static void forClickable(String locator, int timeoutSec) {
        WebDriver driver = TestContext.get().driver();
        String resolved = TestContext.get().locator(locator);
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSec))
            .until(ExpectedConditions.elementToBeClickable(By.xpath(resolved)));
    }

    /** Wait for element to be visible */
    public static void forVisible(String locator, int timeoutSec) {
        WebDriver driver = TestContext.get().driver();
        String resolved = TestContext.get().locator(locator);
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSec))
            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(resolved)));
    }
}
```

---

## 5. Execution Optimization

### 5.1 Parallel Execution Strategy

**Current blocker:** `public static WebDriver driver` — one driver for the entire JVM.

**Solution:** With `TestContext` using `ThreadLocal`, parallel execution becomes trivial:

```xml
<!-- TestNG parallel suite -->
<suite name="Parallel_Functional_Tests" parallel="classes" thread-count="4">
    <listeners>
        <listener class-name="com.subex.automation.listener.SuiteListener" />
        <listener class-name="com.subex.automation.listener.AutomationTestListener" />
    </listeners>
    <parameter name="config" value="psconfig.properties" />
    <parameter name="orFiles" value="PS_OR.properties,Tariff_OR.properties" />

    <test name="Partner Configuration Tests">
        <classes>
            <class name="com.subex.rocps.functional.partner.TCAgent" />
            <class name="com.subex.rocps.functional.partner.TCAccount" />
            <class name="com.subex.rocps.functional.partner.TCOperator" />
        </classes>
    </test>

    <test name="Billing Tests">
        <classes>
            <class name="com.subex.rocps.functional.billing.TCBills" />
            <class name="com.subex.rocps.functional.billing.TCAccruals" />
        </classes>
    </test>
</suite>
```

**Suite Listener that initializes per-thread context:**

```java
public class SuiteListener implements ISuiteListener, ITestListener {

    @Override
    public void onStart(ISuite suite) {
        String configFile = suite.getParameter("config");
        String orFiles = suite.getParameter("orFiles");
        ConfigManager.init(configFile,
            orFiles != null ? orFiles.split(",") : new String[0]);
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Each test class gets its own driver on its own thread
        DriverManager.initDriver();
    }

    @Override
    public void onFinish(ITestResult result) {
        DriverManager.quitDriver();
    }
}
```

### 5.2 Reducing Flaky Tests

| Technique | Implementation |
|-----------|---------------|
| **Smart waits** | Replace all `Thread.sleep()` with `Sync.forLoadMask()`, `Sync.forClickable()` |
| **Stale element retry** | Wrap element interactions in a retry loop (3 attempts) |
| **Session timeout handling** | `@BeforeMethod` check (already exists, keep it) |
| **Test isolation** | Each test class gets its own driver via `TestContext` |
| **Data isolation** | Unique test data per run (append timestamp to names) |
| **Retry analyzer** | Already exists; make `maxRetryCount` configurable via config |

**Stale element retry utility:**

```java
public final class SafeAction {

    private static final int MAX_RETRIES = 3;

    @FunctionalInterface
    public interface Action<T> {
        T execute() throws Exception;
    }

    public static <T> T retry(Action<T> action) {
        Exception lastException = null;
        for (int attempt = 0; attempt < MAX_RETRIES; attempt++) {
            try {
                return action.execute();
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                lastException = e;
                Sync.forLoadMask();
            }
        }
        throw new AutomationException("Action failed after " + MAX_RETRIES + " retries", lastException);
    }

    // Usage:
    // String value = SafeAction.retry(() -> GridHelper.getCellValue("grid", 1, "Name"));
}
```

### 5.3 CI/CD Improvements

**Maven profile-based execution:**

```xml
<!-- pom.xml profiles -->
<profiles>
    <profile>
        <id>smoke</id>
        <properties>
            <testng.filename>smoke-suite.xml</testng.filename>
            <skipTests>false</skipTests>
        </properties>
    </profile>

    <profile>
        <id>regression</id>
        <properties>
            <testng.filename>functional-suite.xml</testng.filename>
            <skipTests>false</skipTests>
        </properties>
    </profile>

    <profile>
        <id>parallel</id>
        <properties>
            <testng.filename>parallel-suite.xml</testng.filename>
            <skipTests>false</skipTests>
            <thread.count>4</thread.count>
        </properties>
    </profile>
</profiles>
```

**CI/CD commands:**
```bash
# Smoke tests (PR validation — fast)
mvn test -Psmoke -Denv=staging

# Full regression (nightly)
mvn test -Pregression -Denv=staging -Dbrowser=chrome

# Parallel execution
mvn test -Pparallel -Denv=staging -Dthread.count=6
```

### 5.4 Performance Optimization

| Area | Current | Proposed |
|------|---------|----------|
| **Browser startup** | New browser per `@BeforeClass` (150+ starts) | Reuse browser per `<test>` group |
| **Excel reading** | Read per helper instantiation | Cache `TestData` at class level |
| **Page source parsing** | JSoup parses full HTML for every grid op | Cache parsed DOM, invalidate on navigation |
| **OR lookup** | `or.getProperty(key)` per call | `ConfigManager.getLocator(key)` with lazy cache |
| **Screenshot** | Full-page screenshot always | Screenshot only on failure (via listener) |

---

## 6. Maintainability Enhancements

### 6.1 Locator Management Best Practices

**BEFORE — Flat, monolithic:**
```properties
# PS_OR.properties — everything in one file
LoginPage_Username_ID=username
AgentPage_CompanyName_XPATH=//input[@id='company']
BillPage_Amount_CSS=.bill-amount
# ... 500+ locators in one file
```

**AFTER — Module-organized, self-documenting:**

```
locators/
├── common/
│   └── common.properties       # Login, Navigation, shared elements
├── partner/
│   ├── agent.properties         # Agent-specific locators
│   ├── account.properties
│   └── operator.properties
├── billing/
│   ├── bill.properties
│   └── accrual.properties
└── tariff/
    └── tariff.properties
```

**Locator file format — with comments:**
```properties
# agent.properties
# Search Screen
agent.search.companyName = Detail_companyName_txtID
agent.search.grid        = SearchGrid
agent.search.clearBtn    = ClearButton

# Detail Screen
agent.detail.company     = //input[@id='company']
agent.detail.agentCode   = //input[@id='agentCode']
agent.detail.currency    = //select[@id='currency']
agent.detail.parentAgent = //input[@id='parentAgent']
```

**Locator constants class (compile-time safety):**

```java
/**
 * Compile-time locator constants — typos caught at build time.
 */
public final class AgentLocators {
    private AgentLocators() {}

    // Search Screen
    public static final String SEARCH_COMPANY_NAME = "agent.search.companyName";
    public static final String SEARCH_GRID         = "agent.search.grid";
    public static final String CLEAR_BUTTON        = "agent.search.clearBtn";

    // Detail Screen
    public static final String COMPANY     = "agent.detail.company";
    public static final String AGENT_CODE  = "agent.detail.agentCode";
    public static final String CURRENCY    = "agent.detail.currency";
}

// Usage:
TextBoxHelper.enterText(AgentLocators.COMPANY, agent.getCompany());
```

### 6.2 Modularization — Decoupling Test Logic from Business Logic

**Current coupling:**
```
TCAgent (test) → Agent (helper that IS-A PSAcceptanceTest)
                  ├── Reads Excel
                  ├── Navigates to screen
                  ├── Fills forms
                  ├── Saves records
                  ├── Validates grid
                  └── Extends PSAcceptanceTest (inherits driver, config, reporting)
```

**Proposed decoupling:**
```
TCAgent (test)
  │
  ├── loadData("Agent", "Agent")        → TestDataBuilder (data layer)
  │
  └── agentService.create(data)         → AgentService (business layer)
        │
        ├── nav.navigateTo("Agent")     → NavigationService (navigation layer)
        ├── ButtonHelper.click(...)     → GridHelper/TextBoxHelper (component layer)
        └── Sync.forLoadMask()          → SyncManager (synchronization layer)
```

**Each layer is independently testable, changeable, and understandable.**

### 6.3 Code Readability Standards

**Naming conventions to enforce:**

| Element | Convention | Example |
|---------|-----------|---------|
| Test class | `TC` + PascalCase module | `TCAgent`, `TCBillGeneration` |
| Test method | camelCase, verb-first, descriptive | `createAgentWithParent()`, `deleteInactiveAgent()` |
| Service class | PascalCase + `Service` | `AgentService`, `BillService` |
| Model class | PascalCase + `Model` | `AgentModel`, `AccountModel` |
| Locator key | dot-notation: `module.screen.element` | `agent.detail.company` |
| Config key | camelCase | `clientUrl`, `downloadDirectory` |
| Constants | UPPER_SNAKE_CASE | `SEARCH_GRID`, `CLEAR_BUTTON` |

**Method length rule:** No method exceeds 30 lines. Extract sub-methods with descriptive names.

**Class length rule:** No class exceeds 300 lines. Split by responsibility.

---

## 7. Scalability & Future-Proofing

### 7.1 API + UI Hybrid Automation

```java
/**
 * Interface for operations that can execute via UI or API.
 */
public interface AgentOperations {
    void create(AgentModel agent);
    void delete(String agentCode);
    boolean exists(String companyName);
}

/** UI implementation — existing Selenium-based approach */
public class AgentUIService implements AgentOperations {
    @Override
    public void create(AgentModel agent) {
        NavigationService.navigateTo("Agent");
        // ... Selenium operations
    }
}

/** API implementation — faster, for prerequisite setup */
public class AgentAPIService implements AgentOperations {
    private final RestClient client;

    @Override
    public void create(AgentModel agent) {
        client.post("/api/agents", agent.toJson());
    }
}

/** Strategy-based usage in tests */
public class TCAgent extends BaseTest {

    // Use API for fast prerequisite creation
    private final AgentOperations setupService = new AgentAPIService();
    // Use UI for actual test validation
    private final AgentOperations uiService = new AgentUIService();

    @Test(description = "Create agent via UI after API setup")
    public void createAgentFlow() {
        // Fast setup via API
        setupService.create(AgentModel.prerequisite("ParentAgent"));

        // Actual test via UI
        AgentModel agent = AgentModel.from(loadData("Agent", "Agent"), 0);
        uiService.create(agent);

        // Validate via API (fast assertion)
        assertTrue(setupService.exists(agent.getCompanyName()));
    }
}
```

### 7.2 Cross-Browser and Cross-Environment Support

**Already partially supported via `psconfig.properties`. Enhancement:**

```xml
<!-- Multi-browser parallel suite -->
<suite name="Cross_Browser" parallel="tests" thread-count="3">
    <test name="Chrome Tests">
        <parameter name="browser" value="chrome" />
        <classes>
            <class name="com.subex.rocps.functional.partner.TCAgent" />
        </classes>
    </test>
    <test name="Firefox Tests">
        <parameter name="browser" value="firefox" />
        <classes>
            <class name="com.subex.rocps.functional.partner.TCAgent" />
        </classes>
    </test>
    <test name="Edge Tests">
        <parameter name="browser" value="edge" />
        <classes>
            <class name="com.subex.rocps.functional.partner.TCAgent" />
        </classes>
    </test>
</suite>
```

**Environment config structure:**
```
config/
├── psconfig.properties           # Base/default config
├── psconfig-dev.properties       # Dev overrides
├── psconfig-staging.properties   # Staging overrides
├── psconfig-prod.properties      # Prod overrides (read-only tests)
└── psconfig-ci.properties        # CI/CD pipeline config
```

### 7.3 Preparing for Long-Term Scaling

| Enhancement | When | Effort | Impact |
|-------------|------|--------|--------|
| ThreadLocal `TestContext` | **Phase 1** | Medium | Unblocks parallel, eliminates static state |
| Factory + Strategy patterns | **Phase 1** | Low | Clean driver/wait management |
| Builder + Template Method | **Phase 1** | Low | Eliminates 16,000+ lines of boilerplate |
| Listener-based reporting | **Phase 1** | Medium | Centralized failure handling |
| Modular locator files | **Phase 2** | Low | Easier maintenance per module |
| Service layer extraction | **Phase 2** | High | Business logic separated from tests |
| Domain models | **Phase 2** | Medium | Replace 30+ loose fields with typed objects |
| API hybrid support | **Phase 3** | Medium | Faster prerequisite setup |
| Docker/Selenium Grid | **Phase 3** | Medium | Cloud-scalable execution |
| Allure reporting | **Phase 3** | Low | Modern reporting with trends |

---

## 8. Migration Roadmap

### Phase 1: Foundation (2-3 weeks) — Non-Breaking

These changes can be made **without modifying existing test classes**:

1. **Create `TestContext`** — ThreadLocal wrapper around existing static fields.
2. **Create `WebDriverFactory`** — Extract from `AcceptanceTest`.
3. **Create `ConfigManager`** — Singleton wrapping `PropertyReader`.
4. **Create `AutomationTestListener`** — Global failure/screenshot handler.
5. **Create `BaseTest`** — New base class with `loadData()` helper.
6. **Keep old `AcceptanceTest`** — Have it delegate to `TestContext` internally (backward compatible).

```java
// Backward-compatible bridge — old code still works:
public class AcceptanceTest extends Assert {
    // Old static fields now delegate to TestContext
    public static WebDriver driver;

    @BeforeSuite
    public void legacyInit(ITestContext ctx) {
        // Initialize TestContext, then set static fields for old code
        DriverManager.initDriver();
        driver = TestContext.get().driver();
        configProp = new PropertyReader(...);
        // ... other static field bridges
    }
}
```

### Phase 2: Migration (4-6 weeks) — Incremental

Migrate test classes one module at a time:

1. **Start with one module** (e.g., `partner/`).
2. Extract `AgentService` from `Agent` helper.
3. Create `AgentModel` domain object.
4. Rewrite `TCAgent` to use `BaseTest` + `AgentService`.
5. Validate with smoke test.
6. Repeat for next module.

**Key rule:** Old and new tests run in the same suite. No big-bang rewrite.

### Phase 3: Optimization (2-3 weeks)

1. Enable parallel execution (`parallel="classes"` or `parallel="tests"`).
2. Modularize locator files.
3. Add API services for prerequisite setup.
4. Integrate Allure reporting.
5. Create Docker/Selenium Grid configuration.

### Total Estimated Timeline: 8-12 weeks

---

## Summary: Before vs After

| Aspect | Current State | After Improvement |
|--------|--------------|-------------------|
| **Readability** | 10-12 line methods with boilerplate | 2-3 line methods, self-documenting |
| **Maintainability** | Change base class → risk 200+ classes | Change one layer → isolated impact |
| **Scalability** | Cannot run in parallel | Thread-safe, parallel-ready |
| **Efficiency** | Serial execution only | 4-8x faster with parallel |
| **Onboarding** | Must understand 3-layer inheritance chain | Write test in 5 lines, call a service |
| **Error handling** | Scattered try/catch in every method | Centralized listener, custom exceptions |
| **Test lines of code** | ~20,000+ lines (150 × 137 avg) | ~4,500 lines (150 × 30 avg) |
| **Static mutable fields** | 30+ on `AcceptanceTest` | Zero — all in ThreadLocal `TestContext` |
| **Helper inheritance depth** | 4 levels (`AcceptanceTest → ROC → PS → Agent`) | 0 levels (utility classes, no inheritance) |
| **Config management** | Single hardcoded file | Environment-aware with profiles |
| **Driver management** | One static instance | Per-thread via Factory + ThreadLocal |
| **Reporting** | Mixed with test logic | Listener-based, swappable implementation |

---

**This document provides the complete improvement blueprint. Start with Phase 1 (Foundation) — it unblocks everything else without breaking existing tests.**

