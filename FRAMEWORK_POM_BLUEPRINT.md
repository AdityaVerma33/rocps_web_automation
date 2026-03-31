# ROCPS Web Automation Framework — Enhanced Blueprint with Page Object Model

> **Enterprise-Ready Architecture: POM + Design Patterns + Layered Separation**

**Date:** February 26, 2026  
**Version:** 2.0  
**Scope:** Complete framework redesign incorporating Page Object Model as a core architectural pillar  
**Prerequisite:** Read `FRAMEWORK_IMPROVEMENT_BLUEPRINT.md` for problem analysis and foundational patterns

---

## Table of Contents

1. [Why POM — Mapping to Current Pain Points](#1-why-pom--mapping-to-current-pain-points)
2. [Six-Layer Architecture](#2-six-layer-architecture)
3. [Complete Folder Structure](#3-complete-folder-structure)
4. [Layer 1 — Core/Base Layer](#4-layer-1--corebase-layer)
5. [Layer 2 — Page Layer (POM Implementation)](#5-layer-2--page-layer-pom-implementation)
6. [Layer 3 — Service/Business Layer](#6-layer-3--servicebusiness-layer)
7. [Layer 4 — Test Data Layer](#7-layer-4--test-data-layer)
8. [Layer 5 — Test Layer](#8-layer-5--test-layer)
9. [Layer 6 — Reporting & Logging Layer](#9-layer-6--reporting--logging-layer)
10. [POM Integration with Framework Concerns](#10-pom-integration-with-framework-concerns)
11. [Best Practices & Standards](#11-best-practices--standards)
12. [Current Code → POM Migration Map](#12-current-code--pom-migration-map)
13. [Updated Migration Roadmap](#13-updated-migration-roadmap)

---

## 1. Why POM — Mapping to Current Pain Points

The current framework uses a **Helper Pattern** where classes like `AgentDetailImpl`, `AgentActionImpl`, and `Agent` each extend `PSAcceptanceTest` and directly call static component methods (`TextBoxHelper.type(...)`, `ComboBoxHelper.select(...)`, `ButtonHelper.click(...)`). This produces three specific problems that POM solves:

### Problem → POM Solution Mapping

| Current Problem | Root Cause | POM Solution |
|----------------|-----------|--------------|
| `AgentDetailImpl` has 30+ `protected String` fields for every form element | No structured representation of a page | **Page class** encapsulates elements and actions for one screen |
| `AgentDetailImpl extends PSAcceptanceTest` — a form-filler IS-A test | Inheritance used for field access | Page objects receive `WebDriver` via constructor injection — no inheritance from test base |
| Locator strings like `"Detail_Franchise_comboID"` scattered inside method bodies | No separation between *what* (locator) and *how* (action) | Locators defined as `By` fields at the top of each Page class — single place to update |
| Changing a UI element requires grepping across `Agent.java`, `AgentDetailImpl.java`, `AgentActionImpl.java` | Same element referenced in multiple classes | One page class = one screen. All locators and interactions in one file |
| Cannot reuse "fill agent form" in a different test flow | Logic tied to `ExcelHolder` and `Map<String, String>` data structures | Page methods accept domain models or primitives — reusable from any test |
| `GridHelper.isValuePresent("SearchGrid", agent, "Agent")` — raw grid column name passed as string | No typed representation of a search grid | **Page Component** for `SearchGrid` encapsulates column awareness |

### What POM Gives This Framework Specifically

```
BEFORE (Current Helper Pattern):
─────────────────────────────────
TCAgent → Agent → AgentDetailImpl → TextBoxHelper.type("locator", value)
                                    ComboBoxHelper.select("locator", value)
                                    ButtonHelper.click("locator")
                                    
All three classes extend PSAcceptanceTest.
Locators are strings buried inside method bodies.
Agent mixes: data loading + navigation + form filling + grid searching + assertions.

AFTER (POM + Service Layer):
────────────────────────────
TCAgent → AgentService → AgentSearchPage.searchByName("company")
                         AgentSearchPage.clickNew()
                         AgentDetailPage.fillBasicDetails(agentModel)
                         AgentDetailPage.fillAddress(agentModel)
                         AgentDetailPage.save()

Page classes own locators.
Service classes own business logic.
Test classes own test orchestration.
Nobody extends a test base for field access.
```

---

## 2. Six-Layer Architecture

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                                                                             │
│   ┌─────────────────────────────────────────────────────────────────────┐   │
│   │                    LAYER 5: TEST LAYER                              │   │
│   │  TCAgent.java, TCAccount.java, TCBills.java                        │   │
│   │  • Orchestrates test flows                                          │   │
│   │  • Extends BaseTest (thin template)                                 │   │
│   │  • Calls Service Layer methods                                      │   │
│   │  • Contains @Test annotations + assertions                          │   │
│   └────────────────────────────┬────────────────────────────────────────┘   │
│                                │ uses                                       │
│   ┌────────────────────────────▼────────────────────────────────────────┐   │
│   │                    LAYER 3: SERVICE / BUSINESS LAYER                │   │
│   │  AgentService.java, BillService.java, AccrualService.java          │   │
│   │  • Contains business workflows (create, edit, delete, validate)     │   │
│   │  • Calls Page Layer methods                                         │   │
│   │  • Accepts domain models as input                                   │   │
│   │  • No direct WebDriver or locator access                            │   │
│   └────────────────────────────┬────────────────────────────────────────┘   │
│                                │ uses                                       │
│   ┌────────────────────────────▼────────────────────────────────────────┐   │
│   │                    LAYER 2: PAGE LAYER (POM)                        │   │
│   │                                                                     │   │
│   │  ┌───────────────┐  ┌──────────────┐  ┌──────────────────────┐     │   │
│   │  │   BasePage     │  │  Page Classes │  │  Page Components     │     │   │
│   │  │   (abstract)   │  │              │  │                      │     │   │
│   │  │  • driver ref  │  │ AgentSearch  │  │  SearchGrid          │     │   │
│   │  │  • wait utils  │  │ AgentDetail  │  │  NavigationMenu      │     │   │
│   │  │  • element     │  │ BillSearch   │  │  ConfirmationDialog  │     │   │
│   │  │    finders     │  │ BillDetail   │  │  FilterPanel         │     │   │
│   │  │  • screenshot  │  │ LoginPage    │  │  TabPanel            │     │   │
│   │  │  • scroll      │  │ TariffDetail │  │  AddressForm         │     │   │
│   │  └───────┬───────┘  └──────┬───────┘  └──────────┬───────────┘     │   │
│   │          │ extends         │ extends              │ composed in     │   │
│   │          └─────────────────┘                      │ page classes    │   │
│   │                                                   │                 │   │
│   └───────────────────────────────────────────────────┘─────────────────┘   │
│                                │ uses                                       │
│   ┌────────────────────────────▼────────────────────────────────────────┐   │
│   │                    LAYER 1: CORE / BASE LAYER                       │   │
│   │                                                                     │   │
│   │  TestContext     WebDriverFactory    ConfigManager    SyncManager   │   │
│   │  (ThreadLocal)   (Factory)           (Singleton)      (Strategy)   │   │
│   │                                                                     │   │
│   │  RetryAnalyzer   AutomationTestListener   SuiteListener            │   │
│   └─────────────────────────────────────────────────────────────────────┘   │
│                                                                             │
│   ┌───────────────────────────────┐  ┌──────────────────────────────────┐   │
│   │  LAYER 4: TEST DATA LAYER    │  │  LAYER 6: REPORTING & LOGGING   │   │
│   │                               │  │                                  │   │
│   │  TestData, TestDataBuilder,   │  │  ReportManager (interface),      │   │
│   │  AgentModel, AccountModel,    │  │  ExtentReportManager,            │   │
│   │  ExcelDataProvider,           │  │  ScreenshotHelper,               │   │
│   │  CsvDataProvider              │  │  Log4jHelper                     │   │
│   └───────────────────────────────┘  └──────────────────────────────────┘   │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

### Layer Responsibilities — Strict Rules

| Layer | May Depend On | Must NOT Depend On | Owns |
|-------|--------------|-------------------|------|
| **Test Layer** | Service, Data, Base | Page Layer directly | Test orchestration, assertions |
| **Service Layer** | Page Layer, Data, Base | Test Layer | Business workflows |
| **Page Layer** | Core/Base, Components | Service, Test, Data | Locators, element interactions |
| **Core/Base Layer** | Nothing (self-contained) | Any higher layer | Driver, config, context, sync |
| **Test Data Layer** | Core/Base | Page, Service, Test | Data models, data providers |
| **Reporting Layer** | Core/Base | Page, Service, Test | Reports, logs, screenshots |

**Key constraint:** Tests NEVER call `driver.findElement(...)` or reference a locator string. Tests call service methods. Services call page methods. Pages own locators.

---

## 3. Complete Folder Structure

```
rocps-web-automation/
├── pom.xml                                    # Parent POM (module declarations)
│
│
│ ═══════════════════════════════════════════════════════════════════════
│  MODULE 1: framework-core (Layer 1 + Layer 6)
│  Artifact: com.subex.roc:roc-web-util
│  Zero product-specific code. Pure infrastructure.
│ ═══════════════════════════════════════════════════════════════════════
│
├── framework-core/
│   ├── pom.xml
│   └── src/main/java/com/subex/automation/
│       │
│       ├── core/                              ── CORE / BASE LAYER ──
│       │   ├── context/
│       │   │   └── TestContext.java            # ThreadLocal: driver + config + sync + report
│       │   ├── driver/
│       │   │   ├── WebDriverFactory.java       # Factory: creates browser-specific drivers
│       │   │   ├── DriverManager.java          # Lifecycle: init + quit (wraps Factory + TestContext)
│       │   │   └── BrowserType.java            # Enum: CHROME, FIREFOX, EDGE, IE
│       │   ├── config/
│       │   │   └── ConfigManager.java          # Singleton: env-aware config + OR loading
│       │   ├── sync/
│       │   │   ├── WaitStrategy.java           # Strategy interface
│       │   │   ├── LoadMaskWaitStrategy.java   # Wait for ExtJS load mask to disappear
│       │   │   ├── AjaxCompleteWaitStrategy.java
│       │   │   ├── PageLoadWaitStrategy.java
│       │   │   ├── SyncManager.java            # Context that holds current strategy
│       │   │   └── Sync.java                   # Fluent static API: Sync.forLoadMask()
│       │   ├── exception/
│       │   │   ├── AutomationException.java    # Base exception
│       │   │   ├── ElementNotFoundException.java
│       │   │   ├── NavigationException.java
│       │   │   ├── DataException.java
│       │   │   └── TimeoutException.java
│       │   └── listener/
│       │       ├── AutomationTestListener.java # Global: screenshots + reporting on fail
│       │       ├── RetryAnalyzer.java          # Configurable retry (reads from config)
│       │       └── SuiteListener.java          # Suite-level: ConfigManager.init + driver init
│       │
│       ├── page/                              ── PAGE BASE LAYER ──
│       │   ├── BasePage.java                   # Abstract: common page methods (wait, find, etc.)
│       │   └── component/                      # Reusable UI fragments
│       │       ├── SearchGridComponent.java    # Grid: search, filter, get cell, get row count
│       │       ├── NavigationMenuComponent.java# Left-nav: navigateTo(screenName)
│       │       ├── ConfirmDialogComponent.java # OK/Cancel dialogs
│       │       ├── FilterPanelComponent.java   # Partition + status filters
│       │       ├── TabPanelComponent.java      # Tab switching
│       │       ├── AddressFormComponent.java   # Reusable address block (street, town, etc.)
│       │       ├── ContactInfoComponent.java   # Reusable contact details (phone, email)
│       │       └── HeaderComponent.java        # Top bar: user info, logout, settings
│       │
│       ├── util/                              ── UTILITIES LAYER ──
│       │   ├── SafeAction.java                 # Stale-element retry wrapper
│       │   ├── ScreenshotHelper.java           # Capture screenshot to file
│       │   ├── DateHelper.java                 # Date formatting, arithmetic
│       │   ├── StringHelper.java               # String manipulation
│       │   └── FileHelper.java                 # File copy, delete, check exists
│       │
│       ├── data/                              ── TEST DATA LAYER (base) ──
│       │   ├── TestData.java                   # Immutable data holder
│       │   ├── TestDataBuilder.java            # Builder: workbook → sheet → testCase → build()
│       │   ├── ExcelDataProvider.java          # Reads Excel via Apache POI
│       │   └── CsvDataProvider.java            # Reads CSV via OpenCSV
│       │
│       ├── db/                                ── DATABASE LAYER ──
│       │   ├── DatabaseManager.java            # Connection pool (C3P0)
│       │   └── QueryExecutor.java              # Execute query, return results
│       │
│       ├── remote/                            ── REMOTE OPERATIONS ──
│       │   ├── SshManager.java                 # JSch-based SSH commands
│       │   └── FileTransferManager.java        # SFTP upload/download
│       │
│       └── report/                            ── REPORTING & LOGGING LAYER ──
│           ├── ReportManager.java              # Interface: startTest, pass, fail, screenshot
│           ├── ExtentReportManager.java         # ExtentReports 2.x implementation
│           └── Log4jHelper.java                # Structured logging
│
│
│ ═══════════════════════════════════════════════════════════════════════
│  MODULE 2: framework-roc (Layer 2 partial — shared ROC pages)
│  Artifact: com.subex.roc:roc-automation
│  ROC-product pages, login, navigation — shared across ROC products.
│ ═══════════════════════════════════════════════════════════════════════
│
├── framework-roc/
│   ├── pom.xml
│   └── src/main/java/com/subex/roc/
│       │
│       ├── page/                              ── SHARED ROC PAGE OBJECTS ──
│       │   ├── LoginPage.java                  # Login screen: username, password, submit
│       │   ├── HomePage.java                   # Post-login landing page
│       │   ├── ChangePasswordPage.java         # Password change screen
│       │   ├── SettingsPage.java               # Application settings
│       │   └── AboutPage.java                  # About/License info
│       │
│       ├── service/                           ── SHARED ROC SERVICES ──
│       │   ├── LoginService.java               # Login flow: enter creds → submit → handle reset
│       │   ├── NavigationService.java          # Navigate to any screen by name
│       │   └── ControllerService.java          # Start/stop Tomcat, Task Controller, etc.
│       │
│       ├── base/                              ── TEST BASE ──
│       │   └── BaseTest.java                   # Template method: loadData(), @BeforeClass setup
│       │
│       └── locators/                          ── SHARED LOCATOR PROPERTIES ──
│           ├── common.properties               # Shared elements (buttons, grids)
│           └── login.properties                # Login-specific locators
│
│
│ ═══════════════════════════════════════════════════════════════════════
│  MODULE 3: rocps-tests (Layers 2, 3, 4, 5 — ROCPS-specific)
│  Artifact: com.subex.rocps:rocps-web-automation
│  ROCPS page objects, services, models, and tests.
│ ═══════════════════════════════════════════════════════════════════════
│
├── rocps-tests/
│   ├── pom.xml
│   │
│   ├── src/main/java/com/subex/rocps/
│   │   │
│   │   ├── page/                              ── ROCPS PAGE OBJECTS (Layer 2) ──
│   │   │   │
│   │   │   ├── partner/                        # Partner Configuration module
│   │   │   │   ├── AgentSearchPage.java         # Agent search screen
│   │   │   │   ├── AgentDetailPage.java         # Agent create/edit form
│   │   │   │   ├── AccountSearchPage.java
│   │   │   │   ├── AccountDetailPage.java
│   │   │   │   ├── OperatorSearchPage.java
│   │   │   │   ├── OperatorDetailPage.java
│   │   │   │   ├── FranchiseSearchPage.java
│   │   │   │   └── FranchiseDetailPage.java
│   │   │   │
│   │   │   ├── billing/                        # Billing module
│   │   │   │   ├── BillSearchPage.java
│   │   │   │   ├── BillDetailPage.java
│   │   │   │   ├── BillRequestPage.java
│   │   │   │   ├── BillingCyclePage.java
│   │   │   │   ├── BillPackagePage.java
│   │   │   │   ├── CreditNotesPage.java
│   │   │   │   └── TestBillPage.java
│   │   │   │
│   │   │   ├── accruals/                       # Accruals module
│   │   │   │   ├── AccrualSearchPage.java
│   │   │   │   ├── AccrualDetailPage.java
│   │   │   │   └── AccrualOverviewPage.java
│   │   │   │
│   │   │   ├── tariff/                         # Tariff module
│   │   │   │   ├── TariffSearchPage.java
│   │   │   │   ├── TariffDetailPage.java
│   │   │   │   ├── RateSheetImportPage.java
│   │   │   │   └── RateSheetTemplatePage.java
│   │   │   │
│   │   │   ├── roaming/                        # Roaming module
│   │   │   │   ├── RoamingSearchPage.java
│   │   │   │   └── RoamingDetailPage.java
│   │   │   │
│   │   │   ├── settlement/                     # Settlement module
│   │   │   │   ├── SettlementSearchPage.java
│   │   │   │   └── SettlementDetailPage.java
│   │   │   │
│   │   │   ├── deal/                           # Deal module
│   │   │   │   ├── DealSearchPage.java
│   │   │   │   ├── DealDetailPage.java
│   │   │   │   └── DealImportPage.java
│   │   │   │
│   │   │   ├── admin/                          # Admin module
│   │   │   │   ├── UserManagementPage.java
│   │   │   │   ├── ApprovalWorkflowPage.java
│   │   │   │   └── SystemConfigPage.java
│   │   │   │
│   │   │   └── monitoring/                     # Monitoring module
│   │   │       ├── EventErrorsPage.java
│   │   │       └── AggregationResultsPage.java
│   │   │
│   │   ├── service/                           ── SERVICE / BUSINESS LAYER (Layer 3) ──
│   │   │   ├── partner/
│   │   │   │   ├── AgentService.java            # Agent CRUD workflows
│   │   │   │   ├── AccountService.java
│   │   │   │   └── OperatorService.java
│   │   │   ├── billing/
│   │   │   │   ├── BillService.java
│   │   │   │   ├── AccrualService.java
│   │   │   │   └── BillingCycleService.java
│   │   │   ├── tariff/
│   │   │   │   └── TariffService.java
│   │   │   ├── roaming/
│   │   │   │   └── RoamingService.java
│   │   │   └── deal/
│   │   │       └── DealService.java
│   │   │
│   │   └── model/                             ── DOMAIN MODELS (Layer 4 partial) ──
│   │       ├── AgentModel.java                  # Immutable agent data
│   │       ├── AccountModel.java
│   │       ├── BillModel.java
│   │       ├── TariffModel.java
│   │       ├── DealModel.java
│   │       └── OperatorModel.java
│   │
│   ├── src/test/java/com/subex/rocps/         ── TEST LAYER (Layer 5) ──
│   │   ├── functional/
│   │   │   ├── partner/
│   │   │   │   ├── TCAgent.java
│   │   │   │   ├── TCAccount.java
│   │   │   │   └── TCOperator.java
│   │   │   ├── billing/
│   │   │   │   ├── TCBills.java
│   │   │   │   ├── TCAccruals.java
│   │   │   │   └── TCBillingCycle.java
│   │   │   ├── tariff/
│   │   │   │   └── TCTariff.java
│   │   │   ├── roaming/
│   │   │   │   └── TCRoaming.java
│   │   │   └── deal/
│   │   │       └── TCDeal.java
│   │   └── system/
│   │       ├── TCEndToEndFlow01.java
│   │       └── TCEndToEndFlow02.java
│   │
│   └── src/test/resources/                    ── RESOURCES ──
│       ├── config/
│       │   ├── psconfig.properties              # Default config
│       │   ├── psconfig-staging.properties      # Staging overrides
│       │   └── psconfig-ci.properties           # CI/CD overrides
│       ├── testdata/
│       │   ├── FunctionalTestCases.xlsx
│       │   └── SystemTestCases.xlsx
│       ├── locators/
│       │   ├── partner/
│       │   │   ├── agent.properties
│       │   │   ├── account.properties
│       │   │   └── operator.properties
│       │   ├── billing/
│       │   │   ├── bill.properties
│       │   │   └── accrual.properties
│       │   └── tariff/
│       │       └── tariff.properties
│       └── suites/
│           ├── smoke-suite.xml
│           ├── functional-suite.xml
│           ├── regression-suite.xml
│           ├── system-suite.xml
│           └── parallel-suite.xml
```

---

## 4. Layer 1 — Core/Base Layer

> This layer is unchanged from the original blueprint. See `FRAMEWORK_IMPROVEMENT_BLUEPRINT.md` Sections 2.1–2.6 for:
> - `TestContext` (ThreadLocal DI)
> - `WebDriverFactory` (Factory pattern)
> - `ConfigManager` (Singleton)
> - `SyncManager` / `WaitStrategy` (Strategy pattern)
> - `AutomationTestListener` (Template Method + Listener)
> - `RetryAnalyzer`

**What POM adds at this layer:** `BasePage` — the abstract root of all page objects.

### 4.1 BasePage — The Foundation of Every Page Object

```java
package com.subex.automation.page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.subex.automation.core.context.TestContext;
import com.subex.automation.core.exception.ElementNotFoundException;
import com.subex.automation.core.exception.TimeoutException;
import com.subex.automation.core.sync.Sync;
import com.subex.automation.report.Log4jHelper;

/**
 * Abstract base for every Page Object in the framework.
 *
 * Responsibilities:
 *   1. Provide protected access to the WebDriver (from TestContext).
 *   2. Offer common element-interaction methods (find, click, type, select, etc.)
 *      so page subclasses never call raw driver methods.
 *   3. Encapsulate smart waits — every interaction auto-waits for visibility/clickability.
 *   4. Provide reusable utility methods (scroll, screenshot, JS execution).
 *
 * Rules:
 *   - BasePage is abstract — never instantiated directly.
 *   - Subclasses define locators as private static final By fields.
 *   - Subclasses expose human-readable public methods (e.g., enterCompanyName()).
 *   - BasePage does NOT contain any locators — those belong in subclasses.
 *   - BasePage does NOT extend AcceptanceTest or any test class.
 */
public abstract class BasePage {

    private static final int DEFAULT_TIMEOUT = 30;

    // ── Driver Access ──────────────────────────────────────────────
    // Protected so subclasses can access, but tests cannot.
    protected WebDriver getDriver() {
        return TestContext.get().driver();
    }

    protected WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    protected WebDriverWait getWait(int timeoutSeconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutSeconds));
    }

    // ── Find Elements ──────────────────────────────────────────────

    protected WebElement find(By locator) {
        try {
            return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (org.openqa.selenium.TimeoutException e) {
            throw new ElementNotFoundException("Timed out waiting for: " + locator);
        }
    }

    protected WebElement findClickable(By locator) {
        try {
            return getWait().until(ExpectedConditions.elementToBeClickable(locator));
        } catch (org.openqa.selenium.TimeoutException e) {
            throw new ElementNotFoundException("Element not clickable: " + locator);
        }
    }

    protected List<WebElement> findAll(By locator) {
        getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
        return getDriver().findElements(locator);
    }

    protected boolean isPresent(By locator) {
        try {
            getDriver().findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isDisplayed(By locator) {
        try {
            return getDriver().findElement(locator).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    // ── Interactions ───────────────────────────────────────────────

    protected void click(By locator) {
        Log4jHelper.logInfo("Clicking: " + locator);
        findClickable(locator).click();
        Sync.forLoadMask();
    }

    protected void type(By locator, String text) {
        if (text == null || text.isEmpty()) return;
        Log4jHelper.logInfo("Typing '" + text + "' into: " + locator);
        WebElement element = find(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void selectByVisibleText(By locator, String text) {
        if (text == null || text.isEmpty()) return;
        Log4jHelper.logInfo("Selecting '" + text + "' in: " + locator);
        new Select(find(locator)).selectByVisibleText(text);
        Sync.forLoadMask();
    }

    protected void selectComboBox(By locator, String value) {
        if (value == null || value.isEmpty()) return;
        Log4jHelper.logInfo("Selecting combo '" + value + "' in: " + locator);
        click(locator);
        By optionLocator = By.xpath("//li[contains(text(),'" + value + "')]");
        click(optionLocator);
    }

    protected String getText(By locator) {
        return find(locator).getText().trim();
    }

    protected String getValue(By locator) {
        return find(locator).getAttribute("value");
    }

    protected void check(By locator) {
        WebElement checkbox = find(locator);
        if (!checkbox.isSelected()) checkbox.click();
    }

    protected void uncheck(By locator) {
        WebElement checkbox = find(locator);
        if (checkbox.isSelected()) checkbox.click();
    }

    protected void setCheckbox(By locator, boolean checked) {
        if (checked) check(locator); else uncheck(locator);
    }

    // ── Tabs ───────────────────────────────────────────────────────

    protected void selectTab(String tabName) {
        By tabLocator = By.xpath("//span[contains(@class,'x-tab') and text()='" + tabName + "']");
        click(tabLocator);
    }

    // ── Waits ──────────────────────────────────────────────────────

    protected void waitForLoadMask() {
        Sync.forLoadMask();
    }

    protected void waitForSave() {
        Sync.forSave();
    }

    protected void waitForVisible(By locator) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForInvisible(By locator) {
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // ── JavaScript Execution ───────────────────────────────────────

    protected Object executeJs(String script, Object... args) {
        return ((JavascriptExecutor) getDriver()).executeScript(script, args);
    }

    protected void scrollIntoView(By locator) {
        WebElement element = find(locator);
        executeJs("arguments[0].scrollIntoView({behavior:'smooth',block:'center'});", element);
    }

    // ── Page Title Verification ────────────────────────────────────

    protected String getPageTitle() {
        By titleLocator = By.cssSelector(".x-panel-header-text, .page-title, h1");
        return getText(titleLocator);
    }

    public boolean isAt(String expectedTitle) {
        return getPageTitle().contains(expectedTitle);
    }
}
```

### 4.2 Page Components — Reusable UI Fragments

Page components represent recurring UI structures (grids, modals, menus) that appear across multiple pages. They are **composed into** page classes, not inherited.

#### SearchGridComponent

```java
package com.subex.automation.page.component;

import java.util.List;
import org.openqa.selenium.*;
import com.subex.automation.page.BasePage;

/**
 * Reusable component for the search/result grid that appears on every
 * search screen in the ROCPS application.
 *
 * Composed into page classes (not inherited):
 *   AgentSearchPage HAS-A SearchGridComponent
 *   BillSearchPage  HAS-A SearchGridComponent
 */
public class SearchGridComponent extends BasePage {

    private final By gridContainer;

    public SearchGridComponent(String gridId) {
        this.gridContainer = By.id(gridId);
    }

    public SearchGridComponent(By gridLocator) {
        this.gridContainer = gridLocator;
    }

    // ── Row Operations ─────────────────────────────────────────

    public int getRowCount() {
        List<WebElement> rows = findAll(
            By.cssSelector("#" + getGridId() + " .x-grid-row"));
        return rows.size();
    }

    public boolean hasResults() {
        return getRowCount() > 0;
    }

    public String getCellValue(int row, String columnName) {
        int colIndex = getColumnIndex(columnName);
        By cellLocator = By.cssSelector(
            "#" + getGridId() + " .x-grid-row:nth-child(" + row + ") " +
            ".x-grid-cell:nth-child(" + colIndex + ")");
        return getText(cellLocator);
    }

    public boolean isValuePresent(String value, String columnName) {
        int rows = getRowCount();
        for (int i = 1; i <= rows; i++) {
            if (getCellValue(i, columnName).contains(value)) {
                return true;
            }
        }
        return false;
    }

    // ── Row Selection ──────────────────────────────────────────

    public void selectRow(int rowNumber) {
        By rowLocator = By.cssSelector(
            "#" + getGridId() + " .x-grid-row:nth-child(" + rowNumber + ")");
        click(rowLocator);
    }

    public void doubleClickRow(int rowNumber) {
        By rowLocator = By.cssSelector(
            "#" + getGridId() + " .x-grid-row:nth-child(" + rowNumber + ")");
        WebElement row = find(rowLocator);
        new org.openqa.selenium.interactions.Actions(getDriver())
            .doubleClick(row).perform();
        waitForLoadMask();
    }

    // ── Search / Filter ────────────────────────────────────────

    public void searchInColumn(String columnFilterId, String searchText) {
        type(By.id(columnFilterId), searchText);
        find(By.id(columnFilterId)).sendKeys(Keys.ENTER);
        waitForLoadMask();
    }

    // ── Internals ──────────────────────────────────────────────

    private String getGridId() {
        // Extract ID from By locator for CSS selectors
        String locString = gridContainer.toString();
        return locString.substring(locString.lastIndexOf(": ") + 2);
    }

    private int getColumnIndex(String columnName) {
        List<WebElement> headers = findAll(
            By.cssSelector("#" + getGridId() + " .x-column-header"));
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().trim().equalsIgnoreCase(columnName)) {
                return i + 1;
            }
        }
        throw new ElementNotFoundException("Column not found: " + columnName);
    }
}
```

#### NavigationMenuComponent

```java
package com.subex.automation.page.component;

import org.openqa.selenium.*;
import com.subex.automation.page.BasePage;
import com.subex.automation.core.exception.NavigationException;

/**
 * Represents the left-side navigation menu in the ROCPS application.
 * Used to navigate to any screen by its display name.
 */
public class NavigationMenuComponent extends BasePage {

    private static final By MENU_TREE = By.id("navigationTree");

    /**
     * Navigate to a screen through the menu hierarchy.
     * Handles multi-level menus: clicks parent nodes to expand, then clicks the leaf.
     *
     * @param screenName The display name shown in the menu (e.g., "Agent", "Bill Request")
     */
    public void navigateTo(String screenName) {
        Log4jHelper.logInfo("Navigating to: " + screenName);

        // Build xpath that matches the menu item text
        By menuItem = By.xpath(
            "//div[@id='navigationTree']//span[normalize-space(text())='" + screenName + "']");

        // If the menu item is not visible, expand parent nodes
        if (!isDisplayed(menuItem)) {
            expandParentNodes(screenName);
        }

        click(menuItem);
        waitForLoadMask();
    }

    public String getActiveScreen() {
        By activeItem = By.cssSelector("#navigationTree .x-tree-selected span");
        return isPresent(activeItem) ? getText(activeItem) : "";
    }

    private void expandParentNodes(String screenName) {
        // Application-specific logic to expand tree nodes
        // This handles the ROC navigation tree structure
        By parentNode = By.xpath(
            "//div[@id='navigationTree']//span[normalize-space(text())='" +
            screenName + "']/ancestor::li[contains(@class,'x-tree-node')]" +
            "//img[contains(@class,'x-tree-ec-icon')]");

        List<WebElement> expandIcons = getDriver().findElements(parentNode);
        for (WebElement icon : expandIcons) {
            if (!icon.getAttribute("class").contains("x-tree-elbow-end-minus")
                && !icon.getAttribute("class").contains("x-tree-elbow-minus")) {
                icon.click();
                waitForLoadMask();
            }
        }
    }
}
```

#### ConfirmDialogComponent

```java
package com.subex.automation.page.component;

import org.openqa.selenium.*;
import com.subex.automation.page.BasePage;

/**
 * Handles modal confirmation dialogs (OK/Cancel/Yes/No).
 * Appears after save, delete, or discard operations.
 */
public class ConfirmDialogComponent extends BasePage {

    private static final By DIALOG_CONTAINER = By.cssSelector(".x-window.x-message-box");
    private static final By DIALOG_MESSAGE   = By.cssSelector(".x-window-body .x-window-text");
    private static final By OK_BUTTON        = By.xpath("//button[text()='OK']");
    private static final By YES_BUTTON       = By.xpath("//button[text()='Yes']");
    private static final By NO_BUTTON        = By.xpath("//button[text()='No']");
    private static final By CANCEL_BUTTON    = By.xpath("//button[text()='Cancel']");

    public boolean isVisible() {
        return isDisplayed(DIALOG_CONTAINER);
    }

    public String getMessage() {
        return getText(DIALOG_MESSAGE);
    }

    public void clickOk() {
        click(OK_BUTTON);
        waitForLoadMask();
    }

    public void clickYes() {
        click(YES_BUTTON);
        waitForLoadMask();
    }

    public void clickNo() {
        click(NO_BUTTON);
    }

    public void clickCancel() {
        click(CANCEL_BUTTON);
    }

    /** Accept whatever dialog appears (OK or Yes) */
    public void accept() {
        if (isDisplayed(OK_BUTTON)) clickOk();
        else if (isDisplayed(YES_BUTTON)) clickYes();
    }
}
```

#### FilterPanelComponent

```java
package com.subex.automation.page.component;

import org.openqa.selenium.*;
import com.subex.automation.page.BasePage;

/**
 * Represents the partition and status filter panel
 * that appears on most search screens.
 */
public class FilterPanelComponent extends BasePage {

    private static final By PARTITION_COMBO = By.id("partitionFilter");
    private static final By STATUS_COMBO    = By.id("statusFilter");

    public void selectPartition(String partition) {
        if (partition != null && !partition.isEmpty()) {
            selectComboBox(PARTITION_COMBO, partition);
        }
    }

    public void selectStatus(String status) {
        selectComboBox(STATUS_COMBO, status);
    }

    public void filterByPartitionAndStatus(String partition, String status) {
        selectPartition(partition);
        selectStatus(status);
        waitForLoadMask();
    }
}
```

#### AddressFormComponent

```java
package com.subex.automation.page.component;

import org.openqa.selenium.*;
import com.subex.automation.page.BasePage;

/**
 * Reusable address form that appears in Agent, Account, Operator detail pages.
 * Composed into any page that has an address section.
 */
public class AddressFormComponent extends BasePage {

    // Locators are relative — the parent page passes a wrapper context
    private final String prefix;

    public AddressFormComponent(String fieldPrefix) {
        this.prefix = fieldPrefix;
    }

    public void fill(String street1, String street2, String town,
                     String county, String postCode, String country) {
        type(By.id(prefix + "_street1"), street1);
        type(By.id(prefix + "_street2"), street2);
        type(By.id(prefix + "_town"), town);
        type(By.id(prefix + "_county"), county);
        type(By.id(prefix + "_postCode"), postCode);
        if (country != null && !country.isEmpty()) {
            selectComboBox(By.id(prefix + "_country"), country);
        }
    }
}
```

---

## 5. Layer 2 — Page Layer (POM Implementation)

### 5.1 Page Class Design Rules

| Rule | Rationale |
|------|-----------|
| **One page class = one screen** | `AgentSearchPage` for the Agent search screen, `AgentDetailPage` for the Agent form |
| **Locators are `private static final By` fields** at class top | Single place to update when UI changes; compile-time type safety |
| **Methods return `this` or the next page** | Fluent chaining: `agentDetail.fillBasicDetails(model).fillAddress(model).save()` |
| **Methods accept domain models or primitives** | Not `Map<String,String>`, not `ExcelHolder` |
| **No assertions inside page objects** | Assertions belong in tests or services; pages return data for assertions |
| **Pages compose components via HAS-A** | `AgentSearchPage` HAS-A `SearchGridComponent`, HAS-A `FilterPanelComponent` |
| **No `extends AcceptanceTest`** | Pages extend only `BasePage` |

### 5.2 AgentSearchPage — Full Example

```java
package com.subex.rocps.page.partner;

import org.openqa.selenium.By;
import com.subex.automation.page.BasePage;
import com.subex.automation.page.component.FilterPanelComponent;
import com.subex.automation.page.component.SearchGridComponent;

/**
 * Page Object for the Agent Search screen.
 *
 * Replaces the search-related logic in:
 *   - Agent.java (isAgentPresent, searchScreenColumnsValidation)
 *   - AgentActionImpl.java (clicknewAgent)
 *
 * Locators are defined once, at the top.
 * Methods are human-readable and return typed results.
 */
public class AgentSearchPage extends BasePage {

    // ── Locators (private, never exposed outside this class) ───
    private static final By COMPANY_NAME_SEARCH = By.id("Detail_companyName_txtID");
    private static final By AGENT_CODE_FILTER   = By.id("pageCode");
    private static final By CLEAR_BUTTON        = By.id("ClearButton");
    private static final By NEW_BUTTON          = By.id("newButton");
    private static final By SCREEN_TITLE        = By.cssSelector(".x-panel-header-text");

    // ── Composed Components ────────────────────────────────────
    private final SearchGridComponent grid = new SearchGridComponent("SearchGrid");
    private final FilterPanelComponent filter = new FilterPanelComponent();

    // ── Public Methods (business-readable) ─────────────────────

    /** Search for an agent by company name. Returns this page for chaining. */
    public AgentSearchPage searchByCompanyName(String companyName) {
        click(CLEAR_BUTTON);
        type(COMPANY_NAME_SEARCH, companyName);
        find(COMPANY_NAME_SEARCH).sendKeys(org.openqa.selenium.Keys.ENTER);
        waitForLoadMask();
        return this;
    }

    /** Filter grid by agent code column */
    public AgentSearchPage filterByAgentCode(String agentCode) {
        grid.searchInColumn("pageCode", agentCode);
        return this;
    }

    /** Check if any results exist in the grid */
    public boolean hasResults() {
        return grid.hasResults();
    }

    /** Get the number of rows in the search results */
    public int getResultCount() {
        return grid.getRowCount();
    }

    /** Check if a specific agent exists by company name */
    public boolean agentExists(String companyName) {
        searchByCompanyName(companyName);
        return grid.isValuePresent(companyName, "Agent");
    }

    /** Click the New button to open the Agent Detail form */
    public AgentDetailPage clickNew(String partition) {
        filter.selectPartition(partition);
        click(NEW_BUTTON);
        waitForLoadMask();
        return new AgentDetailPage();
    }

    /** Open an existing agent for editing by double-clicking the first row */
    public AgentDetailPage openFirstResult() {
        grid.doubleClickRow(1);
        return new AgentDetailPage();
    }

    /** Select partition and status filter */
    public AgentSearchPage filterByPartition(String partition, String status) {
        filter.filterByPartitionAndStatus(partition, status);
        return this;
    }

    /** Delete selected agent via action menu */
    public AgentSearchPage deleteAgent(String agentCode) {
        filterByAgentCode(agentCode);
        // Application-specific delete action
        By deleteAction = By.xpath("//a[contains(@class,'delete-action')]");
        click(deleteAction);
        new com.subex.automation.page.component.ConfirmDialogComponent().clickYes();
        waitForLoadMask();
        return this;
    }

    /** Get the grid component for advanced operations */
    public SearchGridComponent grid() {
        return grid;
    }

    /** Get the screen title text */
    public String getScreenTitle() {
        return getText(SCREEN_TITLE);
    }
}
```

### 5.3 AgentDetailPage — Full Example

```java
package com.subex.rocps.page.partner;

import org.openqa.selenium.By;
import com.subex.automation.page.BasePage;
import com.subex.automation.page.component.AddressFormComponent;
import com.subex.automation.page.component.ContactInfoComponent;
import com.subex.rocps.model.AgentModel;

/**
 * Page Object for the Agent Create/Edit detail screen.
 *
 * Replaces:
 *   - AgentDetailImpl.java (311 lines, 30+ fields, extends PSAcceptanceTest)
 *
 * Each form section is a method. Complex sections delegate to components.
 * Methods accept AgentModel — not raw strings or Maps.
 */
public class AgentDetailPage extends BasePage {

    // ── Basic Details Locators ──────────────────────────────────
    private static final By FRANCHISE_COMBO   = By.id("Detail_Franchise_comboID");
    private static final By CURRENCY_COMBO    = By.id("Detail_Currency_comboID");
    private static final By TYPE_COMBO        = By.id("Detail_type_comboID");
    private static final By AGENT_CODE_INPUT  = By.id("Detail_AgentCode_TextID");
    private static final By TITLE_COMBO       = By.id("Detail_Title_comboID");
    private static final By FORENAME_INPUT    = By.id("Detail_Forename_txtID");
    private static final By SURNAME_INPUT     = By.id("Detail_Surname_txtID");
    private static final By COMPANY_INPUT     = By.id("Detail_companyName_txtID");

    // ── Hierarchy Locators ──────────────────────────────────────
    private static final By ROOT_AGENT_COMBO  = By.id("Detail_rootAgent_comboID");
    private static final By PARENT_AGENT_COMBO = By.id("Detail_parentAgent_comboID");

    // ── Action Locators ─────────────────────────────────────────
    private static final By SAVE_BUTTON       = By.id("Detail_agent_save_btn");
    private static final By CANCEL_BUTTON     = By.id("Detail_agent_cancel_btn");
    private static final By SCREEN_TITLE      = By.cssSelector(".x-panel-header-text");

    // ── Composed Components ─────────────────────────────────────
    private final AddressFormComponent addressForm = new AddressFormComponent("agent");
    private final ContactInfoComponent contactInfo = new ContactInfoComponent("agent");

    // ── Public Methods ──────────────────────────────────────────

    /**
     * Fill the Basic Details tab using an AgentModel.
     * Skips null/empty fields automatically (handled by BasePage.type/selectComboBox).
     */
    public AgentDetailPage fillBasicDetails(AgentModel agent) {
        selectComboBox(FRANCHISE_COMBO, agent.getFranchise());
        selectComboBox(CURRENCY_COMBO, agent.getCurrency());
        selectComboBox(TYPE_COMBO, agent.getType());
        type(AGENT_CODE_INPUT, agent.getAgentCode());
        selectComboBox(TITLE_COMBO, agent.getTitle());
        type(FORENAME_INPUT, agent.getForeName());
        type(SURNAME_INPUT, agent.getSurname());
        type(COMPANY_INPUT, agent.getCompany());
        return this;
    }

    /** Fill the Hierarchy section */
    public AgentDetailPage fillHierarchy(AgentModel agent) {
        selectTab("Hierarchy");
        selectComboBox(ROOT_AGENT_COMBO, agent.getRootAgent());
        selectComboBox(PARENT_AGENT_COMBO, agent.getParentAgent());
        return this;
    }

    /** Fill the Address tab using the reusable AddressFormComponent */
    public AgentDetailPage fillAddress(AgentModel agent) {
        selectTab("Address");
        addressForm.fill(
            agent.getStreet1(), agent.getStreet2(),
            agent.getTown(), agent.getCounty(),
            agent.getPostCode(), agent.getCountry()
        );
        return this;
    }

    /** Fill the Contact Info tab */
    public AgentDetailPage fillContactInfo(AgentModel agent) {
        selectTab("Contact Info");
        contactInfo.fill(agent.getContactName(), agent.getContactType(),
                         agent.getContactNumber());
        return this;
    }

    /** Click Save and return to the Agent Search page */
    public AgentSearchPage save() {
        click(SAVE_BUTTON);
        waitForSave();
        return new AgentSearchPage();
    }

    /** Cancel and return to the Agent Search page */
    public AgentSearchPage cancel() {
        click(CANCEL_BUTTON);
        waitForLoadMask();
        return new AgentSearchPage();
    }

    /** Get the current screen title */
    public String getScreenTitle() {
        return getText(SCREEN_TITLE);
    }
}
```

### 5.4 LoginPage

```java
package com.subex.roc.page;

import org.openqa.selenium.By;
import com.subex.automation.page.BasePage;

/**
 * Page Object for the ROCPS Login screen.
 * Shared across all ROC products (lives in framework-roc module).
 */
public class LoginPage extends BasePage {

    private static final By APPLICATION_NAME = By.id("applicationName");
    private static final By USERNAME_INPUT   = By.id("username");
    private static final By PASSWORD_INPUT   = By.id("password");
    private static final By LOGIN_BUTTON     = By.id("loginButton");
    private static final By ERROR_MESSAGE    = By.id("loginError");
    private static final By LOGIN_SCREEN     = By.id("LoginScreen");

    public boolean isDisplayed() {
        return isPresent(LOGIN_SCREEN);
    }

    public LoginPage selectApplication(String appName) {
        selectComboBox(APPLICATION_NAME, appName);
        return this;
    }

    public LoginPage enterUsername(String username) {
        type(USERNAME_INPUT, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(PASSWORD_INPUT, password);
        return this;
    }

    /** Perform login and transition to the home page */
    public void submit() {
        click(LOGIN_BUTTON);
        waitForLoadMask();
    }

    /** Convenience: one-call login */
    public void loginAs(String appName, String username, String password) {
        selectApplication(appName);
        enterUsername(username);
        enterPassword(password);
        submit();
    }

    public String getErrorMessage() {
        return isPresent(ERROR_MESSAGE) ? getText(ERROR_MESSAGE) : "";
    }
}
```

---

## 6. Layer 3 — Service/Business Layer

The Service layer bridges Test and Page layers. It contains **workflow logic** — the sequence of page interactions needed to accomplish a business operation.

### 6.1 Design Rules for Services

| Rule | Rationale |
|------|-----------|
| Services accept **domain models** or **TestData** | Not raw Maps, not Excel rows |
| Services call **page methods only** | Never `driver.findElement()` or raw locator strings |
| Services contain **business assertions** | "Save should return to search page", "Agent should exist in grid" |
| Services are **not** page objects | They orchestrate multiple pages |
| Services do **not** extend any test base | Standalone classes |

### 6.2 AgentService — Full Example

```java
package com.subex.rocps.service.partner;

import com.subex.automation.page.component.NavigationMenuComponent;
import com.subex.automation.report.Log4jHelper;
import com.subex.rocps.model.AgentModel;
import com.subex.rocps.page.partner.AgentDetailPage;
import com.subex.rocps.page.partner.AgentSearchPage;
import com.subex.automation.data.TestData;
import org.testng.Assert;

/**
 * Business service for Agent operations.
 *
 * Replaces:
 *   - Agent.java (314 lines — mixed data loading, navigation, UI ops, assertions)
 *   - AgentActionImpl.java (37 lines — save + click new)
 *   - AgentDetailImpl.java (311 lines — form filling)
 *
 * Total replaced: 662 lines → ~80 lines.
 */
public class AgentService {

    private final NavigationMenuComponent nav = new NavigationMenuComponent();

    /** Create one or more agents from TestData (Excel-driven) */
    public void create(TestData data) {
        nav.navigateTo("Agent");
        AgentSearchPage searchPage = new AgentSearchPage();

        for (int i = 0; i < data.columnCount(); i++) {
            AgentModel agent = AgentModel.from(data, i);

            if (searchPage.agentExists(agent.getCompanyName())) {
                Log4jHelper.logInfo("Agent already exists: " + agent.getCompanyName());
                continue;
            }

            AgentDetailPage detailPage = searchPage.clickNew(agent.getPartition());
            Assert.assertEquals(detailPage.getScreenTitle(), "New Agent");

            searchPage = detailPage
                .fillBasicDetails(agent)
                .fillHierarchy(agent)
                .fillAddress(agent)
                .fillContactInfo(agent)
                .save();

            Assert.assertTrue(searchPage.grid().isValuePresent(
                agent.displayName(), "Agent"),
                "Agent not found in grid after save: " + agent.displayName());

            Log4jHelper.logInfo("Agent created: " + agent.getCompanyName());
        }
    }

    /** Delete agents from TestData */
    public void delete(TestData data) {
        nav.navigateTo("Agent");
        AgentSearchPage searchPage = new AgentSearchPage();

        for (int i = 0; i < data.columnCount(); i++) {
            AgentModel agent = AgentModel.from(data, i);
            searchPage.filterByPartition(agent.getPartition(), "Non Deleted Items");
            searchPage.deleteAgent(agent.getAgentCode());

            // Verify appears in Deleted Items
            searchPage.filterByPartition(agent.getPartition(), "Deleted Items");
            Assert.assertTrue(searchPage.grid().isValuePresent(
                agent.getAgentCode(), "Agent Code"),
                "Agent not found in Deleted Items: " + agent.getAgentCode());

            Log4jHelper.logInfo("Agent deleted: " + agent.getAgentCode());
        }
    }

    /** Undelete agents from TestData */
    public void undelete(TestData data) {
        nav.navigateTo("Agent");
        AgentSearchPage searchPage = new AgentSearchPage();

        for (int i = 0; i < data.columnCount(); i++) {
            AgentModel agent = AgentModel.from(data, i);
            searchPage.filterByPartition(agent.getPartition(), "Deleted Items");
            // Undelete logic via search page
            searchPage.filterByPartition(agent.getPartition(), "Non Deleted Items");
            Assert.assertTrue(searchPage.grid().isValuePresent(
                agent.getAgentCode(), "Agent Code"));

            Log4jHelper.logInfo("Agent undeleted: " + agent.getAgentCode());
        }
    }

    /** Validate search screen columns match expected list */
    public void validateSearchColumns(TestData data) {
        nav.navigateTo("Agent");
        AgentSearchPage searchPage = new AgentSearchPage();
        // Column validation logic using searchPage.grid()
    }
}
```

### 6.3 LoginService — Full Example

```java
package com.subex.roc.service;

import com.subex.automation.core.config.ConfigManager;
import com.subex.automation.report.Log4jHelper;
import com.subex.roc.page.LoginPage;

/**
 * Replaces LoginHelper.java (432 lines, extends AcceptanceTest).
 * Clean service — no inheritance, no static fields.
 */
public class LoginService {

    public void loginWithConfigCredentials() {
        ConfigManager config = ConfigManager.getInstance();
        LoginPage loginPage = new LoginPage();

        if (loginPage.isDisplayed()) {
            loginPage.loginAs(
                config.get("applicationName"),
                config.get("applicationUsername"),
                config.get("applicationPassword")
            );
            Log4jHelper.logInfo("Login successful as: " + config.get("applicationUsername"));
        }
    }

    public void loginAs(String appName, String username, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.loginAs(appName, username, password);
    }
}
```

---

## 7. Layer 4 — Test Data Layer

> Unchanged from the original blueprint. See `FRAMEWORK_IMPROVEMENT_BLUEPRINT.md` Section 2.4 for `TestDataBuilder` and `TestData`.

The key POM integration point: domain models hydrated from `TestData` are passed to Page methods.

```java
// Data flow:
TestData (raw Excel) → AgentModel.from(data, i) → AgentDetailPage.fillBasicDetails(model)
```

The Page layer never knows about Excel. The Data layer never knows about locators.

---

## 8. Layer 5 — Test Layer

### 8.1 BaseTest (Template)

```java
package com.subex.roc.base;

import com.subex.automation.core.context.TestContext;
import com.subex.automation.core.driver.DriverManager;
import com.subex.automation.data.TestData;
import com.subex.automation.data.TestDataBuilder;
import com.subex.roc.service.LoginService;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Base test class — thin template.
 *
 * Responsibilities:
 *   1. Start browser (@BeforeClass)
 *   2. Login
 *   3. Provide loadData() helper
 *   4. Quit browser (@AfterClass)
 *
 * Rules:
 *   - No locators, no page references, no driver access.
 *   - Test subclasses call service methods, never page methods directly.
 */
public abstract class BaseTest {

    private static final String DEFAULT_WORKBOOK = "FunctionalTestCases.xlsx";

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        DriverManager.initDriver();
        new LoginService().loginWithConfigCredentials();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }

    /** Load test data from the default workbook */
    protected TestData loadData(String sheet, String testCase) {
        return TestDataBuilder.create()
            .fromWorkbook(DEFAULT_WORKBOOK)
            .sheet(sheet)
            .testCase(testCase)
            .build();
    }

    /** Load test data with a specific occurrence */
    protected TestData loadData(String sheet, String testCase, int occurrence) {
        return TestDataBuilder.create()
            .fromWorkbook(DEFAULT_WORKBOOK)
            .sheet(sheet)
            .testCase(testCase)
            .occurrence(occurrence)
            .build();
    }
}
```

### 8.2 TCAgent — Clean Test Class

```java
package com.subex.rocps.functional.partner;

import com.subex.automation.core.listener.AutomationTestListener;
import com.subex.roc.base.BaseTest;
import com.subex.rocps.service.partner.AgentService;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Agent functional test cases.
 *
 * BEFORE (137 lines):  extends PSAcceptanceTest, try/catch in every method,
 *                       repeated path/workbook/sheet fields
 * AFTER  (~35 lines):  extends BaseTest, calls AgentService, zero boilerplate
 *
 * Key principle: Test class = WHAT to test. Service class = HOW to test.
 */
@Listeners(AutomationTestListener.class)
public class TCAgent extends BaseTest {

    private final AgentService agentService = new AgentService();

    @Test(priority = 1, description = "Create a new Agent")
    public void createAgent() {
        agentService.create(loadData("Agent", "Agent"));
    }

    @Test(priority = 2, description = "Create Agent with Parent Account")
    public void createAgentWithParent() {
        agentService.create(loadData("Agent", "ParentAgent"));
    }

    @Test(priority = 3, description = "Validate Agent search screen columns")
    public void validateSearchColumns() {
        agentService.validateSearchColumns(loadData("Agent", "AgentSearchScreencolVal"));
    }

    @Test(priority = 4, description = "Create second Agent")
    public void createAgentTwo() {
        agentService.create(loadData("Agent", "Agent2"));
    }

    @Test(priority = 5, description = "Create third Agent")
    public void createAgentThree() {
        agentService.create(loadData("Agent", "Agent3"));
    }

    @Test(priority = 6, description = "Create Agent with Parent (variant)")
    public void createAgentWithParentVariant() {
        agentService.create(loadData("Agent", "ParentAgent1"));
    }

    @Test(priority = 7, description = "Delete Agent")
    public void deleteAgent() {
        agentService.delete(loadData("Agent", "AgentDelete"));
    }

    @Test(priority = 8, description = "Undelete Agent")
    public void undeleteAgent() {
        agentService.undelete(loadData("Agent", "AgentUnDelete"));
    }
}
```

### 8.3 Complete Call-Flow Trace

```
TCAgent.createAgent()
  │
  ├── loadData("Agent", "Agent")
  │     └── TestDataBuilder → ExcelDataProvider → TestData
  │
  └── agentService.create(data)
        │
        ├── nav.navigateTo("Agent")
        │     └── NavigationMenuComponent.navigateTo("Agent")
        │           └── BasePage.click(menuItem)
        │                 └── TestContext.get().driver().findElement(...)
        │
        ├── AgentModel.from(data, 0)
        │     └── TestData.get("CompanyName") → "Acme Corp"
        │
        ├── searchPage.agentExists("Acme Corp")
        │     └── AgentSearchPage.searchByCompanyName(...)
        │           └── BasePage.type(COMPANY_NAME_SEARCH, "Acme Corp")
        │           └── SearchGridComponent.hasResults()
        │
        ├── searchPage.clickNew("Partition1")
        │     └── FilterPanelComponent.selectPartition("Partition1")
        │     └── BasePage.click(NEW_BUTTON)
        │     └── returns new AgentDetailPage()
        │
        └── detailPage.fillBasicDetails(agent)
              .fillHierarchy(agent)
              .fillAddress(agent)
              .fillContactInfo(agent)
              .save()
                └── BasePage.click(SAVE_BUTTON)
                └── returns new AgentSearchPage()
```

---

## 9. Layer 6 — Reporting & Logging Layer

> Unchanged from the original blueprint. See `FRAMEWORK_IMPROVEMENT_BLUEPRINT.md` Section 4.4.

Key integration with POM: Page methods call `Log4jHelper.logInfo()` for every interaction. The `AutomationTestListener` handles failure screenshots and reporting — tests and pages never touch reporting logic.

---

## 10. POM Integration with Framework Concerns

### 10.1 POM + Driver Management

```
WebDriverFactory.createDriver()
        │
        ▼
TestContext.init(driver, config, sync, report)      ← stored in ThreadLocal
        │
        ▼
BasePage.getDriver()                                 ← reads from ThreadLocal
        │
        ▼
AgentSearchPage extends BasePage                     ← uses getDriver() for element ops
AgentDetailPage extends BasePage
LoginPage extends BasePage
SearchGridComponent extends BasePage                 ← components also extend BasePage
```

**Key point:** Pages never receive `WebDriver` as a constructor argument. They get it from `TestContext.get().driver()` via `BasePage.getDriver()`. This keeps page constructors clean (`new AgentSearchPage()`) while remaining thread-safe.

### 10.2 POM + Wait Handling

```
BasePage                              SyncManager + WaitStrategy
────────                              ────────────────────────
find(locator)                         Sync.forLoadMask()
  └── WebDriverWait.until(visible)      └── SyncManager.waitUntilReady()
                                              └── LoadMaskWaitStrategy.waitUntilReady()
findClickable(locator)
  └── WebDriverWait.until(clickable)  Sync.forSave()
                                        └── SyncManager.waitUntilReady(60s)
click(locator)
  └── findClickable + click + Sync.forLoadMask()  ← auto-wait after every click
```

**Every BasePage interaction auto-waits.** Page subclasses never call `Thread.sleep()` or raw `WebDriverWait`. If a page needs a custom wait (e.g., waiting for a specific element to appear after an AJAX call), it uses `waitForVisible(locator)` from `BasePage`.

### 10.3 POM + Exception Handling

```
BasePage.find(locator)
  │
  ├── Element found → return WebElement
  │
  └── Timeout → throw ElementNotFoundException("Timed out waiting for: " + locator)
                  │
                  ▼
              AutomationTestListener.onTestFailure()
                  ├── ScreenshotHelper.capture()
                  ├── ReportManager.fail(name, cause, screenshot)
                  └── Log4jHelper.logError(name, cause)
```

**Pages throw framework exceptions.** Tests never catch them. The listener handles everything.

### 10.4 POM + Reusability Strategy

```
AddressFormComponent          ← Used in AgentDetailPage, AccountDetailPage, OperatorDetailPage
ContactInfoComponent          ← Used in AgentDetailPage, AccountDetailPage
SearchGridComponent           ← Used in EVERY SearchPage
FilterPanelComponent          ← Used in EVERY SearchPage
NavigationMenuComponent       ← Used in EVERY Service
ConfirmDialogComponent        ← Used anywhere a dialog appears
```

**Composition over inheritance.** Page components are composed into pages via `HAS-A`. If the address form changes, update `AddressFormComponent` once — all pages that use it are automatically updated.

### 10.5 POM + CI/CD Execution

```bash
# CI/CD pipeline
mvn test -Psmoke -Denv=ci -Dbrowser=chrome -Dtestng.filename=smoke-suite.xml

# Nightly regression
mvn test -Pregression -Denv=staging -Dtestng.filename=functional-suite.xml

# Parallel execution (POM is thread-safe via BasePage → TestContext ThreadLocal)
mvn test -Pparallel -Denv=staging -Dthread.count=4
```

POM classes are inherently parallel-safe because:
1. `BasePage.getDriver()` reads from `ThreadLocal<TestContext>`.
2. Locators are `static final` (immutable, shared safely).
3. Page instances are created locally within each test method (no shared mutable state).

### 10.6 POM + Dynamic Elements

```java
// Dynamic table rows
public class AgentSearchPage extends BasePage {

    /** Dynamic locator: build based on runtime data */
    private By agentRow(String agentName) {
        return By.xpath("//tr[contains(.,'" + agentName + "')]");
    }

    /** Dynamic locator: parameterized column index */
    private By gridCell(int row, int col) {
        return By.cssSelector(".x-grid-row:nth-child(" + row + ") " +
                              ".x-grid-cell:nth-child(" + col + ")");
    }

    public void selectAgent(String agentName) {
        click(agentRow(agentName));
    }
}

// Dynamic dialogs — use component with wait
public class AgentDetailPage extends BasePage {

    public AgentDetailPage handleSaveConfirmation() {
        ConfirmDialogComponent dialog = new ConfirmDialogComponent();
        if (dialog.isVisible()) {
            dialog.accept();
        }
        return this;
    }
}

// Dynamic combo options — wait for AJAX dropdown
public class BasePage {
    protected void selectComboBox(By locator, String value) {
        if (value == null || value.isEmpty()) return;
        click(locator);
        // Wait for dropdown to appear
        By option = By.xpath("//li[contains(text(),'" + value + "')]");
        waitForVisible(option);
        click(option);
    }
}
```

---

## 11. Best Practices & Standards

### 11.1 Naming Conventions

| Element | Pattern | Example |
|---------|---------|---------|
| Search page class | `<Entity>SearchPage` | `AgentSearchPage` |
| Detail/form page class | `<Entity>DetailPage` | `AgentDetailPage` |
| Reusable component | `<Name>Component` | `SearchGridComponent` |
| Service class | `<Entity>Service` | `AgentService` |
| Domain model | `<Entity>Model` | `AgentModel` |
| Test class | `TC<Entity>` | `TCAgent` |
| Page locator field | `UPPER_SNAKE_CASE` | `COMPANY_NAME_SEARCH` |
| Page method | verb-first camelCase | `searchByCompanyName()` |
| Service method | business-verb camelCase | `create()`, `delete()`, `validateSearchColumns()` |
| Test method | business-readable camelCase | `createAgentWithParent()` |

### 11.2 Class Size Limits

| Class Type | Max Lines | If Exceeded |
|-----------|-----------|-------------|
| Page Object | 200 | Split into Search + Detail pages |
| Page Component | 100 | Extract sub-components |
| Service | 150 | Split by operation type |
| Test Class | 100 | Split by functional area |
| Domain Model | 150 | Acceptable for large forms |
| BasePage | 200 | It's a shared foundation — allowed |

### 11.3 Locator Best Practices

| Priority | Locator Type | When to Use |
|----------|-------------|-------------|
| 1 | `By.id()` | Always prefer if element has a stable ID |
| 2 | `By.cssSelector()` | When ID not available, CSS is faster than XPath |
| 3 | `By.xpath()` | For complex traversals, text-based matching, or dynamic content |
| 4 | `By.name()` | Forms with name attributes |
| ❌ | `By.className()` alone | Too fragile — classes change frequently |
| ❌ | `By.linkText()` | Breaks with text changes, i18n |

**Locator hygiene rules:**
- Never use auto-generated IDs (e.g., `ext-comp-1234`) — they change between sessions.
- Request stable `data-testid` attributes from developers when possible.
- Keep locators in the Page class, not in external property files (compile-time safety).
- Use relative XPath (`./descendant::`) within components, not absolute paths.

### 11.4 Method Design in Page Objects

```java
// ✅ GOOD — returns self for chaining, accepts model
public AgentDetailPage fillBasicDetails(AgentModel agent) {
    selectComboBox(FRANCHISE_COMBO, agent.getFranchise());
    type(AGENT_CODE_INPUT, agent.getAgentCode());
    return this;
}

// ✅ GOOD — returns the NEXT page (navigation)
public AgentSearchPage save() {
    click(SAVE_BUTTON);
    waitForSave();
    return new AgentSearchPage();
}

// ✅ GOOD — returns data for assertion in test/service
public int getResultCount() {
    return grid.getRowCount();
}

// ❌ BAD — assertion inside page object
public void verifyAgentCreated(String name) {
    Assert.assertTrue(grid.isValuePresent(name, "Agent"));  // Don't do this in page
}

// ❌ BAD — exposing WebElement
public WebElement getCompanyInput() {
    return find(COMPANY_INPUT);  // Never expose raw elements
}

// ❌ BAD — accepting Map<String,String>
public void fillDetails(Map<String, String> data) {
    type(COMPANY_INPUT, data.get("Company"));  // Use domain models instead
}
```

---

## 12. Current Code → POM Migration Map

### 12.1 Class-by-Class Mapping

| Current Class | Lines | New POM Class(es) | New Lines (est.) |
|--------------|-------|-------------------|-----------------|
| `Agent.java` | 314 | `AgentService.java` | ~80 |
| `AgentDetailImpl.java` | 311 | `AgentDetailPage.java` | ~80 |
| `AgentActionImpl.java` | 37 | Absorbed into `AgentSearchPage.java` + `AgentDetailPage.save()` | 0 (merged) |
| `TCAgent.java` | 137 | `TCAgent.java` (rewritten) | ~35 |
| `LoginHelper.java` | 432 | `LoginPage.java` + `LoginService.java` | ~60 + ~30 |
| `NavigationHelper.java` | 948 | `NavigationMenuComponent.java` + `NavigationService.java` | ~80 + ~30 |
| `GenericHelper.java` | 900 | `BasePage.java` + `Sync.java` + `SafeAction.java` | ~200 + ~40 + ~30 |
| `GridHelper.java` | 2306 | `SearchGridComponent.java` | ~120 |
| `BrowserHelper.java` | 73 | `BasePage` methods + `DriverManager` | 0 (absorbed) |
| `ReportHelper.java` | 566 | `ReportManager` + `ExtentReportManager` + `AutomationTestListener` | ~50 + ~80 + ~40 |

**Total current: ~6,024 lines → Total proposed: ~955 lines (84% reduction)**

### 12.2 Helper-to-Page Mapping for All Modules

| Current Helper Package | New Page Package | New Service |
|----------------------|-----------------|-------------|
| `partnerConfiguration/` | `page/partner/` | `service/partner/` |
| `bills/` | `page/billing/` | `service/billing/` |
| `accruals/` | `page/accruals/` | `service/billing/` |
| `tariffs/` | `page/tariff/` | `service/tariff/` |
| `roaming/` | `page/roaming/` | `service/roaming/` |
| `deal/` + `dealImport/` | `page/deal/` | `service/deal/` |
| `settlements/` | `page/settlement/` | `service/settlement/` |
| `admin/` | `page/admin/` | `service/admin/` |
| `alerts/` | `page/monitoring/` | `service/monitoring/` |
| `aggregation/` | `page/monitoring/` | `service/monitoring/` |
| `monitoring/` | `page/monitoring/` | `service/monitoring/` |
| `payments/` + `prepayments/` | `page/billing/` | `service/billing/` |
| `exchangeRates/` | `page/admin/` | `service/admin/` |
| `referenceTable/` | `page/admin/` | `service/admin/` |
| `carrierinvoice/` | `page/billing/` | `service/billing/` |
| `dispute/` | `page/billing/` | `service/billing/` |
| `fileUpload/` | Component: `FileUploadComponent` | N/A |
| `pdfToExcel/` | Utility: `PdfHelper` | N/A |
| `genericHelpers/` | Split into `BasePage`, `SearchGridComponent`, `FilterPanelComponent` | N/A |

---

## 13. Updated Migration Roadmap

### Phase 1: Foundation + BasePage (Weeks 1–3)

1. Create `BasePage` abstract class in `framework-core`.
2. Create all shared components (`SearchGridComponent`, `NavigationMenuComponent`, `ConfirmDialogComponent`, `FilterPanelComponent`, `AddressFormComponent`, `ContactInfoComponent`).
3. Create `LoginPage` in `framework-roc`.
4. Create `LoginService` to replace `LoginHelper`.
5. Keep old `AcceptanceTest` as a backward-compatible bridge.
6. **Deliverable:** `LoginPage` + `LoginService` working, all existing tests still pass.

### Phase 2: Pilot Module — Partner Configuration (Weeks 3–5)

1. Create `AgentSearchPage`, `AgentDetailPage`.
2. Create `AgentModel`.
3. Create `AgentService`.
4. Rewrite `TCAgent` to use `BaseTest` + `AgentService`.
5. Validate: all Agent tests pass.
6. Repeat for `Account`, `Operator`.
7. **Deliverable:** Partner Configuration fully migrated to POM.

### Phase 3: Expand — Billing & Tariff (Weeks 5–8)

1. Create bill-related pages (`BillSearchPage`, `BillDetailPage`, etc.).
2. Create tariff-related pages.
3. Create corresponding services and models.
4. Rewrite billing and tariff test classes.
5. **Deliverable:** Billing + Tariff modules fully migrated.

### Phase 4: Remaining Modules (Weeks 8–11)

1. Migrate remaining modules (Roaming, Deal, Settlement, Admin, Monitoring).
2. Each module follows the same pattern: Pages → Models → Services → Tests.
3. **Deliverable:** Full framework migrated to POM.

### Phase 5: Optimization & Cleanup (Weeks 11–13)

1. Delete old helper classes (`Agent.java`, `AgentDetailImpl.java`, `AgentActionImpl.java`, etc.).
2. Delete `PSAcceptanceTest`, `ComponentHelper`, old `AcceptanceTest` bridge.
3. Enable parallel execution.
4. Integrate Allure reporting.
5. Optimize with API hybrid services.
6. **Deliverable:** Framework fully modernized, old code removed.

### Timeline Summary

| Phase | Duration | Scope | Risk |
|-------|----------|-------|------|
| 1. Foundation | 3 weeks | BasePage, Components, Login | Low — additive, non-breaking |
| 2. Pilot | 2 weeks | Partner Config (Agent, Account, Operator) | Low — one module |
| 3. Expand | 3 weeks | Billing, Tariff | Medium — high volume |
| 4. Remaining | 3 weeks | All other modules | Medium — volume |
| 5. Cleanup | 2 weeks | Delete old code, optimize | Low — deletion + config |
| **Total** | **13 weeks** | **Complete POM migration** | |

---

## Final Architecture Summary

```
┌───────────────────────────────────────────────────────────────┐
│                        TEST LAYER                             │
│  TCAgent, TCBills, TCTariff                                   │
│  • @Test methods (2-3 lines each)                             │
│  • Calls Service methods                                      │
│  • Extends BaseTest (thin template)                           │
│  • Zero try/catch, zero locators, zero driver access          │
├───────────────────────────────────────────────────────────────┤
│                      SERVICE LAYER                            │
│  AgentService, BillService, TariffService                     │
│  • Business workflows (create, edit, delete)                  │
│  • Calls Page methods                                         │
│  • Accepts domain models                                      │
│  • Contains business assertions                               │
├───────────────────────────────────────────────────────────────┤
│                    PAGE LAYER (POM)                            │
│  ┌─────────────┐ ┌──────────────┐ ┌────────────────────────┐ │
│  │  BasePage    │ │ Page Classes │ │ Page Components        │ │
│  │  (abstract)  │ │              │ │                        │ │
│  │  find()      │ │ AgentSearch  │ │ SearchGridComponent    │ │
│  │  click()     │ │ AgentDetail  │ │ NavigationMenu         │ │
│  │  type()      │ │ BillSearch   │ │ ConfirmDialog          │ │
│  │  select()    │ │ BillDetail   │ │ FilterPanel            │ │
│  │  waitFor()   │ │ LoginPage    │ │ AddressForm            │ │
│  └──────────────┘ └──────────────┘ └────────────────────────┘ │
├───────────────────────────────────────────────────────────────┤
│                     CORE / BASE LAYER                         │
│  TestContext (ThreadLocal)  │  WebDriverFactory (Factory)     │
│  ConfigManager (Singleton)  │  SyncManager (Strategy)         │
│  DriverManager             │  RetryAnalyzer                   │
│  AutomationTestListener    │  SuiteListener                   │
├──────────────────────┬────────────────────────────────────────┤
│   TEST DATA LAYER    │      REPORTING & LOGGING LAYER         │
│  TestData            │      ReportManager (interface)          │
│  TestDataBuilder     │      ExtentReportManager               │
│  AgentModel          │      ScreenshotHelper                  │
│  ExcelDataProvider   │      Log4jHelper                       │
└──────────────────────┴────────────────────────────────────────┘
```

### Before vs After — Final Comparison

| Metric | Current (Helper Pattern) | After (POM + Patterns) |
|--------|------------------------|----------------------|
| **Test class lines** | 137 (TCAgent) | 35 |
| **Helper/Page lines** | 662 (Agent + DetailImpl + ActionImpl) | 160 (SearchPage + DetailPage) |
| **Component lines** | 2306 (GridHelper alone) | 120 (SearchGridComponent) |
| **Inheritance depth** | 4 (AcceptanceTest → ROC → PS → Agent) | 1 (BasePage → AgentSearchPage) |
| **Helpers extending test base** | All 200+ classes | Zero |
| **Static mutable fields** | 30+ on AcceptanceTest | Zero (ThreadLocal) |
| **Locator management** | Strings in method bodies | `By` fields at page class top |
| **Reusable UI fragments** | None (copy-paste) | 6+ composable components |
| **Parallel execution** | Impossible | Thread-safe by design |
| **New module onboarding** | Copy 3 files, extend PSAcceptanceTest, add try/catch | Create Page + Service + Model, extend BaseTest |
| **UI change impact** | Grep across 5+ files | Update 1 page class |
| **Total framework lines** | ~25,000+ | ~6,000 (76% reduction) |

---

**Start with Phase 1 (Foundation + BasePage). It is non-breaking and immediately usable alongside existing code.**

**For design patterns (Singleton, Factory, Strategy, Builder, DI, Template Method), see the original `FRAMEWORK_IMPROVEMENT_BLUEPRINT.md`.**

**For POM architecture, page hierarchy, component design, and migration map, use this document.**

