# ROCPS Web Automation Framework - Complete Analysis & Reference Guide

**Document Version:** 2.0  
**Date:** February 26, 2026  
**Purpose:** Comprehensive framework analysis for development, maintenance, and future enhancements

---

## 📋 TABLE OF CONTENTS

1. [Executive Summary](#1-executive-summary)
2. [Framework Architecture](#2-framework-architecture)
3. [Project Structure & Dependencies](#3-project-structure--dependencies)
4. [Technology Stack](#4-technology-stack)
5. [Component Breakdown](#5-component-breakdown)
6. [Test Execution Framework](#6-test-execution-framework)
7. [Configuration Management](#7-configuration-management)
8. [Helper Classes & Utilities](#8-helper-classes--utilities)
9. [Test Data Management](#9-test-data-management)
10. [Reporting & Logging](#10-reporting--logging)
11. [Build & Deployment](#11-build--deployment)
12. [Best Practices & Standards](#12-best-practices--standards)
13. [Troubleshooting Guide](#13-troubleshooting-guide)
14. [Future Enhancement Opportunities](#14-future-enhancement-opportunities)

---

## 1. EXECUTIVE SUMMARY

### 1.1 Framework Purpose

The ROCPS (Revenue Operations & Controls - Partner Settlement) Web Automation Framework is an enterprise-grade Selenium-based test automation solution designed for:

- **End-to-end UI testing** of the ROCPS web application
- **Functional validation** of billing, accruals, roaming, tariff management
- **Data-driven testing** with Excel/CSV inputs
- **Cross-browser testing** (Firefox, Chrome, IE)
- **CI/CD integration** with automated execution

### 1.2 Key Metrics

| Metric | Value |
|--------|-------|
| **Projects** | 3 (roc-automation-util, roc-automation, rocps-automation) |
| **Functional Test Modules** | 150+ Test Case Classes |
| **System Test Scenarios** | 15+ End-to-End Test Flows |
| **Helper Classes** | 200+ Domain-Specific Helpers |
| **Supported Browsers** | Firefox, Chrome, IE |
| **Java Version** | JDK 11+ |
| **Selenium Version** | 4.28.1 |
| **TestNG Version** | 7.5.1 |
| **Maven Version** | 3.9.12+ |

### 1.3 Framework Advantages

✅ **Modular Architecture** - Three-tier separation of concerns  
✅ **Reusable Components** - Helper pattern for code reuse  
✅ **Data-Driven** - Excel-based test data management  
✅ **Comprehensive Reporting** - ExtentReports with screenshots  
✅ **Cross-Browser Support** - WebDriverManager for automatic driver management  
✅ **CI/CD Ready** - Maven-based build with batch scripts  
✅ **Database Validation** - Built-in DB utilities (PostgreSQL, MSSQL)  
✅ **File Operations** - Upload, download, and file manipulation support  
✅ **Remote Execution** - SSH support for Linux server operations  

---

## 2. FRAMEWORK ARCHITECTURE

### 2.1 Three-Tier Architecture

```
┌──────────────────────────────────────────────────────────────────────┐
│                    ROCPS-AUTOMATION (Top Layer)                      │
│  ┌────────────────────────────────────────────────────────────────┐  │
│  │ Product-Specific Test Cases & Business Logic                   │  │
│  │ - Functional Test Cases (150+ classes)                         │  │
│  │ - System Test Cases (15+ end-to-end scenarios)                 │  │
│  │ - Domain Helpers (Bills, Accruals, Roaming, Tariffs, etc.)   │  │
│  │ - PSAcceptanceTest (Base test class)                          │  │
│  └────────────────────────────────────────────────────────────────┘  │
│          Artifact: com.subex.rocps:rocps-web-automation:10.5.33.0    │
└──────────────────────────────────────────────────────────────────────┘
                              ▼ depends on
┌──────────────────────────────────────────────────────────────────────┐
│                    ROC-AUTOMATION (Middle Layer)                     │
│  ┌────────────────────────────────────────────────────────────────┐  │
│  │ ROC Application-Specific Helpers & Configuration               │  │
│  │ - Object Repository Management (OR files)                      │  │
│  │ - Configuration Loading (config.properties)                    │  │
│  │ - Application Helpers (TariffHelper, MeasureHelper, etc.)     │  │
│  │ - ROCAcceptanceTest (Base test class)                         │  │
│  │ - Custom Listeners & Retry Logic                              │  │
│  └────────────────────────────────────────────────────────────────┘  │
│          Artifact: com.subex.roc:roc-automation:1.0.0.1-SNAPSHOT     │
└──────────────────────────────────────────────────────────────────────┘
                              ▼ depends on
┌──────────────────────────────────────────────────────────────────────┐
│                  ROC-AUTOMATION-UTIL (Base Layer)                    │
│  ┌────────────────────────────────────────────────────────────────┐  │
│  │ Generic Selenium & Utility Components                          │  │
│  │ - Component Helpers (TextBox, Grid, ComboBox, Button, etc.)   │  │
│  │ - Browser Management (BrowserHelper, WebDriver setup)         │  │
│  │ - Data Utilities (Excel, CSV, JSON, Database)                 │  │
│  │ - Reporting (ExtentReports, TestNG listeners)                 │  │
│  │ - File Operations (Upload, Download, File manipulation)       │  │
│  │ - AcceptanceTest (Base WebDriver lifecycle)                   │  │
│  └────────────────────────────────────────────────────────────────┘  │
│          Artifact: com.subex.roc:roc-web-util:1.0.0.1-SNAPSHOT       │
└──────────────────────────────────────────────────────────────────────┘
```

### 2.2 Build Order

**Critical:** Projects must be built in dependency order:

```
1. roc-automation-util  (no dependencies)
   ↓
2. roc-automation      (depends on roc-automation-util)
   ↓
3. rocps-automation    (depends on roc-automation + roc-automation-util)
```

### 2.3 Inheritance Hierarchy

```
java.lang.Object
    └── org.testng.Assert
            └── AcceptanceTest (roc-automation-util)
                    ├── WebDriver lifecycle management
                    ├── Browser initialization
                    ├── Report initialization
                    ├── Download path management
                    └── Screenshot capture
                            └── ROCAcceptanceTest (roc-automation)
                                    ├── Config/OR file loading
                                    ├── Remote machine connectivity
                                    ├── Video recording setup
                                    └── Custom reporting hooks
                                            └── PSAcceptanceTest (rocps-automation)
                                                    ├── ROCPS-specific setup
                                                    └── Partner Settlement context
                                                            └── Test Classes
                                                                    ├── TCAgent
                                                                    ├── TCAccount
                                                                    ├── TCBills
                                                                    ├── TCAccruals
                                                                    └── ... (150+ classes)
```

---

## 3. PROJECT STRUCTURE & DEPENDENCIES

### 3.1 Project: roc-automation-util (Base Framework)

**Maven Coordinates:**
```xml
<groupId>com.subex.roc</groupId>
<artifactId>roc-web-util</artifactId>
<version>1.0.0.1-SNAPSHOT</version>
```

**Purpose:** Core automation utilities and generic helpers

**Directory Structure:**
```
roc-automation-util/
├── pom.xml
├── src/
│   └── main/
│       ├── java/com/subex/automation/helpers/
│       │   ├── application/
│       │   │   ├── BrowserHelper.java              # Browser operations
│       │   │   ├── LoginHelper.java                 # Login functionality
│       │   │   ├── NavigationHelper.java            # Menu navigation
│       │   │   ├── NavigationMenuHelper.java        # Menu operations
│       │   │   └── ROCHelper.java                   # ROC app utilities
│       │   ├── component/
│       │   │   ├── TextBoxHelper.java               # Text input
│       │   │   ├── ComboBoxHelper.java              # Dropdowns
│       │   │   ├── ButtonHelper.java                # Button clicks
│       │   │   ├── GridHelper.java                  # Table operations
│       │   │   ├── CheckBoxHelper.java              # Checkbox operations
│       │   │   ├── RadioHelper.java                 # Radio buttons
│       │   │   ├── LinkHelper.java                  # Link operations
│       │   │   ├── TabHelper.java                   # Tab navigation
│       │   │   ├── TreeHelper.java                  # Tree navigation
│       │   │   ├── TextAreaHelper.java              # Textarea operations
│       │   │   ├── ImageHelper.java                 # Image handling
│       │   │   ├── SpinnerHelper.java               # Spinner controls
│       │   │   ├── SliderHelper.java                # Slider controls
│       │   │   ├── WindowsHelper.java               # Window handling
│       │   │   ├── CanvasHelper.java                # Canvas operations
│       │   │   ├── ConfirmBoxHelper.java            # Confirmation dialogs
│       │   │   ├── PropertyGridHelper.java          # Property grids
│       │   │   ├── HierarchyGridHelper.java         # Hierarchical grids
│       │   │   └── GridCheckBoxHelper.java          # Grid checkboxes
│       │   ├── componentHelpers/                    # Extended component helpers
│       │   ├── config/                              # Configuration management
│       │   ├── data/                                # Data management
│       │   ├── dataGeneration/                      # Test data generation
│       │   ├── db/                                  # Database utilities
│       │   │   ├── DatabaseHelper.java              # DB operations
│       │   │   └── DBConnectionPool.java            # Connection pooling
│       │   ├── enums/                               # Framework enumerations
│       │   ├── file/                                # File operations
│       │   │   ├── FileHelper.java                  # File utilities
│       │   │   ├── ExcelHelper.java                 # Excel operations
│       │   │   └── CSVHelper.java                   # CSV operations
│       │   ├── performance/                         # Performance testing
│       │   ├── report/                              # Reporting utilities
│       │   │   ├── ExtentManager.java               # ExtentReports setup
│       │   │   └── ReportHelper.java                # Report generation
│       │   ├── scripts/                             # Script execution
│       │   ├── selenium/
│       │   │   ├── AcceptanceTest.java              # Base test class
│       │   │   └── WebDriverFactory.java            # WebDriver creation
│       │   ├── TestNG/
│       │   │   ├── CustomTestListenerAdapter.java   # TestNG listener
│       │   │   └── RetryAnalyzer.java               # Test retry logic
│       │   ├── util/                                # General utilities
│       │   │   ├── DateHelper.java                  # Date operations
│       │   │   ├── StringHelper.java                # String utilities
│       │   │   └── WaitHelper.java                  # Wait strategies
│       │   └── voiceRecognition/                    # Voice recognition
│       └── resources/
│           └── helpers.properties                   # Helper class registry
├── eclipse/                                          # Eclipse utilities
│   ├── FirefoxTools/
│   │   └── harexporttrigger-0.5.0-beta.10.xpi      # Firefox extension
│   └── scripts/                                     # Batch scripts
├── eclipse-addons/
│   ├── Subex_CodeFormatter.xml                      # Code formatting
│   └── subex_rocps.importorder                      # Import order
└── plugins/
    └── drag_and_drop_helper.js                      # Drag-drop utility
```

**Key Dependencies:**
- Selenium WebDriver 4.28.1
- TestNG 7.5.1
- Apache POI 3.12 (Excel)
- ExtentReports 2.41.2
- PostgreSQL Driver 9.1-901.jdbc4
- JTDS 1.3.1 (SQL Server)
- Sikuli API 2.0.5
- JSoup 1.10.2
- Apache HttpClient 4.5.2
- Commons IO 2.6
- Log4j 2.24.3

### 3.2 Project: roc-automation (Middle Layer)

**Maven Coordinates:**
```xml
<groupId>com.subex.roc</groupId>
<artifactId>roc-automation</artifactId>
<version>1.0.0.1-SNAPSHOT</version>
```

**Purpose:** ROC application-specific helpers and configuration management

**Directory Structure:**
```
roc-automation/
├── pom.xml
├── src/
│   └── main/
│       ├── java/com/subex/automation/helpers/
│       │   └── application/
│       │       ├── ConfigureGridHelper.java         # Grid configuration
│       │       ├── ControllerHelper.java            # Controller operations
│       │       ├── CopyHelper.java                  # Copy operations
│       │       ├── ExportHelper.java                # Export operations
│       │       ├── MeasureHelper.java               # Measure management
│       │       ├── MeasureRequestHelper.java        # Measure requests
│       │       ├── NotesHelper.java                 # Notes management
│       │       ├── ReferenceTableHelper.java        # Reference tables
│       │       ├── TariffReferenceTableHelper.java  # Tariff tables
│       │       ├── RunServerService.java            # Server service control
│       │       ├── RunStreamController.java         # Stream controller
│       │       ├── RunTaskController.java           # Task controller
│       │       └── RunTomcat.java                   # Tomcat control
│       └── resources/
│           ├── config.properties                    # ROC configuration
│           ├── ctaf-config.properties               # CTAF configuration
│           ├── OR.properties                        # Object Repository
│           ├── Users_OR.properties                  # Users OR
│           ├── Tariff_OR.properties                 # Tariff OR
│           ├── ROCView_OR.properties                # ROC View OR
│           ├── ROCRA_OR.properties                  # ROCRA OR
│           ├── Measures_Audits_OR.properties        # Measures/Audits OR
│           ├── LDC_OR.properties                    # LDC OR
│           ├── CM_OR.properties                     # CM OR
│           ├── CICD_RunScript.xml                   # CI/CD TestNG suite
│           ├── CTAF_RunScript.xml                   # CTAF TestNG suite
│           └── Dummy_RunScript.xml                  # Dummy TestNG suite
├── eclipse/scripts/                                 # Batch scripts
│   ├── CopyFile.bat
│   ├── DeleteFile.bat
│   ├── MakeDirectory.bat
│   ├── RunAt.bat
│   ├── RunGUIInstaller.bat
│   ├── RunSilentInstaller.bat
│   ├── RunServerService.bat
│   ├── RunStreamController.bat
│   ├── RunTaskController.bat
│   └── RunTomcatServer.bat
├── CICD_RunScript.bat                               # CI/CD execution script
├── CTAF_RunScript.bat                               # CTAF execution script
└── RunScript.bat                                    # Main execution script
```

**Key Dependencies:**
- roc-web-util 1.0.0.1-SNAPSHOT
- TestNG 7.5.1

### 3.3 Project: rocps-automation (Top Layer)

**Maven Coordinates:**
```xml
<groupId>com.subex.rocps</groupId>
<artifactId>rocps-web-automation</artifactId>
<version>10.5.33.0</version>
```

**Purpose:** ROCPS Partner Settlement test cases and domain-specific helpers

**Directory Structure:**
```
rocps-automation/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/subex/rocps/automation/
│   │   │   ├── helpers/
│   │   │   │   ├── application/
│   │   │   │   │   ├── accruals/                   # Accruals helpers
│   │   │   │   │   ├── admin/                      # Admin helpers
│   │   │   │   │   ├── aggregation/                # Aggregation helpers
│   │   │   │   │   ├── alerts/                     # Alerts helpers
│   │   │   │   │   ├── amountthreshold/            # Amount threshold
│   │   │   │   │   ├── approvalworkflows/          # Approval workflows
│   │   │   │   │   ├── arms/                       # ARMS helpers
│   │   │   │   │   ├── bcrManagement/              # BCR management
│   │   │   │   │   ├── bilateral/                  # Bilateral agreements
│   │   │   │   │   ├── bills/                      # Bills helpers
│   │   │   │   │   ├── bulkentityexport/           # Bulk export
│   │   │   │   │   ├── bulkentityselection/        # Bulk selection
│   │   │   │   │   ├── carrierinvoice/             # Carrier invoice
│   │   │   │   │   ├── customexception/            # Custom exceptions
│   │   │   │   │   ├── deal/                       # Deal management
│   │   │   │   │   ├── dealImport/                 # Deal import
│   │   │   │   │   ├── dispute/                    # Dispute management
│   │   │   │   │   ├── eventandaggregation/        # Event aggregation
│   │   │   │   │   ├── eventErrors/                # Event errors
│   │   │   │   │   ├── exchangeRates/              # Exchange rates
│   │   │   │   │   ├── fileUpload/                 # File upload
│   │   │   │   │   ├── filters/                    # Filters
│   │   │   │   │   ├── genericHelpers/             # Generic helpers
│   │   │   │   │   ├── matchandrate/               # Match and rate
│   │   │   │   │   ├── monitoring/                 # Monitoring
│   │   │   │   │   ├── networkConfiguraiton/       # Network config
│   │   │   │   │   ├── partnerConfiguration/       # Partner config
│   │   │   │   │   │   ├── Account.java
│   │   │   │   │   │   ├── Agent.java
│   │   │   │   │   │   ├── Operator.java
│   │   │   │   │   │   └── ...
│   │   │   │   │   ├── payments/                   # Payments
│   │   │   │   │   ├── pdfToExcel/                 # PDF to Excel
│   │   │   │   │   ├── prepayments/                # Prepayments
│   │   │   │   │   ├── products/                   # Products
│   │   │   │   │   ├── quality/                    # Quality checks
│   │   │   │   │   ├── reaggregation/              # Re-aggregation
│   │   │   │   │   ├── referenceTable/             # Reference tables
│   │   │   │   │   ├── reportingAndExtraction/     # Reports/Extraction
│   │   │   │   │   ├── roaming/                    # Roaming
│   │   │   │   │   ├── Sales/                      # Sales
│   │   │   │   │   ├── settlements/                # Settlements
│   │   │   │   │   ├── system/                     # System helpers
│   │   │   │   │   ├── tariffs/                    # Tariffs
│   │   │   │   │   ├── vendorRateManagement/       # Vendor rates
│   │   │   │   │   └── xdrextraction/              # XDR extraction
│   │   │   │   ├── dbscript/                       # Database scripts
│   │   │   │   ├── listener/
│   │   │   │   │   └── Retry.java                  # Retry analyzer
│   │   │   │   ├── selenium/
│   │   │   │   │   └── PSAcceptanceTest.java       # Base test class
│   │   │   │   └── utils/                          # Utilities
│   │   │   └── testcases/
│   │   │       ├── functionaltesting/              # Functional tests
│   │   │       │   ├── ROCPreRequisites2.java
│   │   │       │   ├── TCAccount.java
│   │   │       │   ├── TCAccountAction.java
│   │   │       │   ├── TCAccountCategory.java
│   │   │       │   ├── TCAccountingPeriod.java
│   │   │       │   ├── TCAccountingPeriodDefinition.java
│   │   │       │   ├── TCAccrualPrerequisites.java
│   │   │       │   ├── TCAccrualsModelling.java
│   │   │       │   ├── TCAccrulsOverviewModelling.java
│   │   │       │   ├── TCAgent.java
│   │   │       │   ├── TCAggrComponentMapping.java
│   │   │       │   ├── TCAggregationConfiguration.java
│   │   │       │   ├── TCAggregationProcessor.java
│   │   │       │   ├── TCAggregationResultsActions.java
│   │   │       │   ├── TCAlertEvent.java
│   │   │       │   ├── TCAmountThreshold.java
│   │   │       │   ├── TCApprovalWorkflows.java
│   │   │       │   ├── TCAutomaticInvoiceConfig.java
│   │   │       │   ├── TCAutoRateSheetConfig.java
│   │   │       │   ├── TCBands.java
│   │   │       │   ├── TCBandTyToCalTyGrpMapping.java
│   │   │       │   └── ... (150+ test case classes)
│   │   │       └── systemtesting/                  # System tests
│   │   │           ├── Prerequisites.java
│   │   │           ├── ROCPreRequisites.java
│   │   │           ├── BillPreRequisites.java
│   │   │           ├── ControllerStartup.java
│   │   │           ├── TCVoiceStream.java
│   │   │           ├── TCConfigurations.java
│   │   │           ├── TestCase01.java
│   │   │           ├── TestCase02.java
│   │   │           ├── TestCase03.java
│   │   │           └── ... (TestCase01-15.java)
│   │   └── resources/
│   │       ├── psconfig.properties                 # ROCPS configuration
│   │       ├── PS_OR.properties                    # Partner Settlement OR
│   │       ├── Tariff_OR.properties                # Tariff OR
│   │       ├── FunctionalTestCases.xlsx            # Functional test data
│   │       ├── SystemTestCases.xlsx                # System test data
│   │       ├── FunctionalTesting_RunScript.xml     # Functional TestNG suite
│   │       ├── SystemTesting_RunScript.xml         # System TestNG suite
│   │       ├── Birt/                               # BIRT reports
│   │       ├── Data/                               # Test data
│   │       │   └── FunctionalTesting/
│   │       │       ├── CDRS/                       # CDR files
│   │       │       ├── RateSheet/                  # Rate sheets
│   │       │       ├── BulkLoadStream/             # Bulk load data
│   │       │       ├── CarrierInvoice/             # Carrier invoices
│   │       │       ├── RoamingFilePath/            # Roaming files
│   │       │       ├── FileUpload/                 # Upload files
│   │       │       ├── PdfToExcel/                 # PDF files
│   │       │       └── DealImport/                 # Deal import data
│   │       ├── Diamond/                            # Diamond data
│   │       ├── RateSheetImportStatusAlert/         # Alert data
│   │       └── TAPIN_Parse/                        # TAP files
│   └── test/                                       # Sprint-specific tests
│       └── java/com/subex/rocps/sprintTestCase/
│           ├── bklg33/                             # Sprint backlog 33
│           ├── bklg141/                            # Sprint backlog 141
│           └── ...
├── Images/FileUpload/
│   └── File_Upload.exe                             # File upload utility
├── Report/                                          # Test reports
│   ├── Run1/
│   ├── Run2/
│   └── Run3/
├── test-output/                                     # TestNG output
│   ├── emailable-report.html
│   ├── testng-results.xml
│   ├── CTAF/
│   ├── Default suite/
│   ├── Failed suite [CTAF]/
│   ├── junitreports/
│   ├── old/
│   └── TestExecution/
├── eclipse/scripts/                                 # Batch scripts
├── PS_RunScript.bat                                 # Main execution script
└── ROCPS_Automation_Report.xlsx                    # Report template
```

**Key Dependencies:**
- roc-automation 1.0.0.1-SNAPSHOT
- roc-web-util 1.0.0.1-SNAPSHOT
- TestNG 7.5.1

---

## 4. TECHNOLOGY STACK

### 4.1 Core Technologies

| Technology | Version | Purpose |
|-----------|---------|---------|
| **Java** | JDK 11.0.23 | Programming language |
| **Maven** | 3.9.12 | Build & dependency management |
| **Selenium WebDriver** | 4.28.1 | Browser automation |
| **TestNG** | 7.5.1 | Test framework & execution |
| **WebDriverManager** | 6.3.2 | Automatic driver management |

### 4.2 UI Automation Libraries

| Library | Version | Purpose |
|---------|---------|---------|
| **Selenium Java** | 4.28.1 | WebDriver API |
| **Sikuli API** | 2.0.5 | Image-based automation |
| **ngWebDriver** | 1.1.6 | AngularJS support |

### 4.3 Reporting & Logging

| Library | Version | Purpose |
|---------|---------|---------|
| **ExtentReports** | 2.41.2 | HTML test reports |
| **Log4j Core** | 2.24.3 | Logging framework |
| **Log4j API** | 2.24.3 | Logging API |
| **SLF4J Simple** | 1.7.36 | Simple logging facade |

### 4.4 Data Handling

| Library | Version | Purpose |
|---------|---------|---------|
| **Apache POI** | 3.12 | Excel file operations |
| **Apache POI OOXML** | 3.12 | Excel OOXML format |
| **OpenCSV** | 2.3 | CSV file operations |
| **JSON** | 20160810 | JSON parsing |
| **JSoup** | 1.10.2 | HTML parsing |
| **XMLBeans** | 2.6.0 | XML processing |

### 4.5 Database Connectivity

| Library | Version | Purpose |
|---------|---------|---------|
| **PostgreSQL Driver** | 9.1-901.jdbc4 | PostgreSQL connectivity |
| **JTDS** | 1.3.1 | SQL Server connectivity |
| **DBUnit** | 2.7.3 | Database testing |
| **C3P0** | 0.9.5.5 | Connection pooling |
| **Commons DBCP** | 1.4 | Database connection pooling |

### 4.6 Utilities

| Library | Version | Purpose |
|---------|---------|---------|
| **Apache HttpClient** | 4.5.2 | HTTP operations |
| **Commons IO** | 2.6 | File operations |
| **Commons JXPath** | 1.3 | XML/object navigation |
| **Commons Configuration** | 1.10 | Configuration management |
| **Joda Time** | 2.9.7 | Date/time operations |
| **ZT-Zip** | 1.10 | Zip file operations |
| **JSch** | 0.1.55 | SSH/SFTP operations |
| **Java Mail** | 1.5.6 | Email sending |
| **Simple XML** | 2.0.4 | XML serialization |

### 4.7 Image Processing

| Library | Version | Purpose |
|---------|---------|---------|
| **Java Image Scaling** | 0.8.6 | Image resizing |

---

## 5. COMPONENT BREAKDOWN

### 5.1 Component Helpers (roc-automation-util)

The framework provides 20+ component helpers for UI interactions:

#### 5.1.1 Input Components

| Helper Class | Purpose | Key Methods |
|-------------|---------|-------------|
| `TextBoxHelper` | Text field operations | `enterText()`, `getText()`, `clear()` |
| `TextAreaHelper` | Text area operations | `enterText()`, `getText()`, `clear()` |
| `ComboBoxHelper` | Dropdown/combobox | `selectByValue()`, `selectByText()`, `getSelectedValue()` |
| `CheckBoxHelper` | Checkbox operations | `check()`, `uncheck()`, `isChecked()` |
| `RadioHelper` | Radio button operations | `select()`, `isSelected()` |

#### 5.1.2 Action Components

| Helper Class | Purpose | Key Methods |
|-------------|---------|-------------|
| `ButtonHelper` | Button clicks | `click()`, `isEnabled()`, `isVisible()` |
| `LinkHelper` | Link operations | `click()`, `getHref()`, `getText()` |
| `ImageHelper` | Image operations | `click()`, `getSrc()`, `isDisplayed()` |

#### 5.1.3 Container Components

| Helper Class | Purpose | Key Methods |
|-------------|---------|-------------|
| `GridHelper` | Table/grid operations | `getCellValue()`, `getRowCount()`, `clickCell()` |
| `HierarchyGridHelper` | Hierarchical grids | `expandNode()`, `collapseNode()`, `getChildren()` |
| `GridCheckBoxHelper` | Grid checkboxes | `checkRow()`, `uncheckRow()`, `isRowChecked()` |
| `PropertyGridHelper` | Property grids | `setValue()`, `getValue()` |
| `TreeHelper` | Tree navigation | `expandNode()`, `selectNode()`, `getChildren()` |
| `TabHelper` | Tab navigation | `selectTab()`, `getActiveTab()` |

#### 5.1.4 Control Components

| Helper Class | Purpose | Key Methods |
|-------------|---------|-------------|
| `SpinnerHelper` | Spinner controls | `increment()`, `decrement()`, `setValue()` |
| `SliderHelper` | Slider controls | `setValue()`, `getValue()` |
| `WindowsHelper` | Window handling | `switchToWindow()`, `closeWindow()` |
| `CanvasHelper` | Canvas operations | `drawOn()`, `clear()` |
| `ConfirmBoxHelper` | Confirmation dialogs | `accept()`, `dismiss()`, `getText()` |

#### 5.1.5 Display Components

| Helper Class | Purpose | Key Methods |
|-------------|---------|-------------|
| `LabelHelper` | Label verification | `getText()`, `isVisible()` |

### 5.2 Application Helpers

#### 5.2.1 roc-automation-util Helpers

| Helper Class | Purpose |
|-------------|---------|
| `BrowserHelper` | Browser initialization, window management |
| `LoginHelper` | Login/logout operations |
| `NavigationHelper` | Page navigation |
| `NavigationMenuHelper` | Menu navigation |
| `ROCHelper` | ROC application utilities |

#### 5.2.2 roc-automation Helpers

| Helper Class | Purpose |
|-------------|---------|
| `ConfigureGridHelper` | Grid configuration |
| `ControllerHelper` | Controller operations |
| `CopyHelper` | Copy operations |
| `ExportHelper` | Export operations |
| `MeasureHelper` | Measure management |
| `MeasureRequestHelper` | Measure request operations |
| `NotesHelper` | Notes management |
| `ReferenceTableHelper` | Reference table operations |
| `TariffReferenceTableHelper` | Tariff table operations |
| `RunServerService` | Server service control |
| `RunStreamController` | Stream controller management |
| `RunTaskController` | Task controller management |
| `RunTomcat` | Tomcat server control |

#### 5.2.3 rocps-automation Helpers (Domain-Specific)

**42 functional domains with dedicated helper packages:**

1. **accruals** - Accruals modeling and processing
2. **admin** - Administrative functions
3. **aggregation** - Data aggregation
4. **alerts** - Alert management
5. **amountthreshold** - Amount threshold configuration
6. **approvalworkflows** - Approval workflow management
7. **arms** - ARMS integration
8. **bcrManagement** - BCR management
9. **bilateral** - Bilateral agreement management
10. **bills** - Bill generation and management
11. **bulkentityexport** - Bulk entity export
12. **bulkentityselection** - Bulk entity selection
13. **carrierinvoice** - Carrier invoice processing
14. **customexception** - Custom exception handling
15. **deal** - Deal management
16. **dealImport** - Deal import functionality
17. **dispute** - Dispute management
18. **eventandaggregation** - Event and aggregation
19. **eventErrors** - Event error handling
20. **exchangeRates** - Exchange rate management
21. **fileUpload** - File upload operations
22. **filters** - Filter management
23. **genericHelpers** - Generic helper utilities
24. **matchandrate** - Match and rate operations
25. **monitoring** - System monitoring
26. **networkConfiguraiton** - Network configuration
27. **partnerConfiguration** - Partner configuration
28. **payments** - Payment processing
29. **pdfToExcel** - PDF to Excel conversion
30. **prepayments** - Prepayment management
31. **products** - Product management
32. **quality** - Quality checks
33. **reaggregation** - Re-aggregation operations
34. **referenceTable** - Reference table management
35. **reportingAndExtraction** - Reporting and extraction
36. **roaming** - Roaming management
37. **Sales** - Sales management
38. **settlements** - Settlement processing
39. **system** - System configuration
40. **tariffs** - Tariff management
41. **vendorRateManagement** - Vendor rate management
42. **xdrextraction** - XDR extraction

---

## 6. TEST EXECUTION FRAMEWORK

### 6.1 Test Suites (TestNG XML)

The framework provides multiple TestNG suites for different testing scenarios:

#### 6.1.1 ROCPS-Automation Test Suites

| Suite File | Purpose | Parameters |
|-----------|---------|------------|
| `FunctionalTesting_RunScript.xml` | Functional test execution | config, orFiles |
| `SystemTesting_RunScript.xml` | System test execution | config, orFiles, driverFile |

#### 6.1.2 ROC-Automation Test Suites

| Suite File | Purpose |
|-----------|---------|
| `CICD_RunScript.xml` | CI/CD pipeline execution |
| `CTAF_RunScript.xml` | CTAF test execution |
| `Dummy_RunScript.xml` | Dummy/sample tests |

### 6.2 TestNG Configuration

**Common Parameters:**
```xml
<parameter name="config" value="psconfig.properties" />
<parameter name="orFiles" value="PS_OR.properties,Tariff_OR.properties" />
<parameter name="driverFile" value="TestDriver.xlsx" />
```

**Suite-Level Configuration:**
```xml
<suite name="Functional_Test_Suite" verbose="2" annotations="JDK" 
       configfailurepolicy="skip">
    <listeners>
        <listener class-name="com.subex.automation.helpers.TestNG.CustomTestListenerAdapter" />
    </listeners>
    <!-- Test classes -->
</suite>
```

### 6.3 Test Execution Flow

```
1. Suite Initialization
   ├── Load configuration (psconfig.properties)
   ├── Load Object Repository files (PS_OR.properties, Tariff_OR.properties)
   ├── Initialize ExtentReports
   └── Setup WebDriver

2. Test Execution
   ├── @BeforeClass - Setup test class
   │   ├── Read test data from Excel
   │   ├── Initialize helper classes
   │   └── Setup browser
   │
   ├── @Test methods - Execute test cases
   │   ├── Navigate to functionality
   │   ├── Perform operations
   │   ├── Capture screenshots
   │   └── Log results
   │
   └── @AfterClass - Teardown
       ├── Close browser
       └── Generate reports

3. Reporting
   ├── ExtentReports HTML
   ├── TestNG emailable report
   └── Screenshots for failures
```

### 6.4 Retry Mechanism

The framework includes automatic retry for failed tests:

```java
@Test(retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
public void testMethod() {
    // Test code
}
```

**Retry Configuration:**
- Maximum retries: 2 (configurable)
- Retry on: Exception, AssertionError
- Captures screenshots on each retry

---

## 7. CONFIGURATION MANAGEMENT

### 7.1 Configuration Files

#### 7.1.1 psconfig.properties (ROCPS Configuration)

**Location:** `rocps-automation/src/main/resources/psconfig.properties`

**Key Configuration Sections:**

```properties
# Framework Paths
utilPath=C:\\path\\to\\roc-automation-util
downloadDirectory=C:\\path\\to\\downloads

# Browser Configuration
browser=firefox                          # firefox, chrome, ie
recordExecution=Yes                      # Video recording
embedImageInReport=Yes                   # Screenshots in report
product=ROCPS                           # Product name

# Application Details
clientUrl=http://localhost:8080/rocps_automate_10.5.55
deployPath=C:\\path\\to\\rocps-server-distribution
tomcatPath=<Tomcat Deployment PATH>
applicationName=ROC Partner Settlement
applicationUsername=Root
applicationPassword=welcome@123456
clientPartitionFlag=Y
partition=

# Date/Time Formats
dateFormat=MM/dd/yyyy
timeFormat=HH:mm:ss

# OS Configuration
os=Windows                              # Windows, Linux
machineName=127.0.0.1
taskControllerExeFile=tc.bat
taskControllerName=Task Controller

# Data Directories
dataDir=C:\\path\\to\\datadir
cdrPath=\\src\\main\\resources\\Data\\FunctionalTesting\\CDRS\\
ratesheetPath=\\src\\main\\resources\\Data\\FunctionalTesting\\RateSheet\\
bulkLoadStreamPath=\\src\\main\\resources\\Data\\FunctionalTesting\\BulkLoadStream\\
carrierInvoiecPath=\\src\\main\\resources\\Data\\FunctionalTesting\\CarrierInvoice\\
roamingFilePath=\\src\\main\\resources\\Data\\FunctionalTesting\\RoamingFilePath\\
fileUploadFiles=\\src\\main\\resources\\Birt\\
pdfToExcelFiles=\\src\\main\\resources\\Data\\FunctionalTesting\\PdfToExcel\\
dealImport=\\src\\main\\resources\\Data\\FunctionalTesting\\DealImport\\

# Sikuli Image Names
fileTypeUploadImageName=fileTypeFU6.PNG
openButtoneUploadImageName=openButtonFU6.PNG

# Linux/Remote Configuration
remoteHostname=<Remote Host IP>
remoteUsername=<Remote Host Username>
remotePassword=<Remote Host Password>
remotePortNumber=22

# Database Configuration
dbType=PostgreSQL                       # PostgreSQL, MSSQL
```

### 7.2 Object Repository Files

Object Repository (OR) files store locators for web elements:

#### 7.2.1 PS_OR.properties

**Location:** `rocps-automation/src/main/resources/PS_OR.properties`

**Format:**
```properties
# Element naming convention: PageName_ElementName_LocatorType
LoginPage_Username_ID=username
LoginPage_Password_ID=password
LoginPage_LoginButton_XPATH=//button[@id='loginBtn']
```

#### 7.2.2 Tariff_OR.properties

**Location:** `rocps-automation/src/main/resources/Tariff_OR.properties`

**Purpose:** Tariff-specific element locators

### 7.3 helpers.properties

**Location:** `roc-automation-util/src/main/resources/helpers.properties`

**Purpose:** Registry of all helper classes loaded by the framework

```properties
com.subex.automation.helpers.component.TextBoxHelper
com.subex.automation.helpers.component.ComboBoxHelper
com.subex.automation.helpers.component.CheckBoxHelper
# ... all 30 helper classes
```

---

## 8. HELPER CLASSES & UTILITIES

### 8.1 Helper Design Pattern

Each functional domain follows a consistent helper pattern:

```
Domain Helper Package/
├── MainHelper.java           # Primary operations (CRUD)
├── DetailsHelper.java        # Form-level operations
└── SearchImpl.java           # Search and navigation
```

**Example: Agent Helper**

```java
// Agent.java - Main helper
public class Agent extends PSAcceptanceTest {
    public void agentCreation() { /* ... */ }
    public void agentModification() { /* ... */ }
    public void agentDeletion() { /* ... */ }
    public void searchScreenColumnsValidation() { /* ... */ }
}

// AgentDetails.java - Details helper
public class AgentDetails {
    public void fillAgentDetails() { /* ... */ }
    public void validateAgentDetails() { /* ... */ }
}

// AgentSearchImpl.java - Search helper
public class AgentSearchImpl {
    public void searchAgent(String agentName) { /* ... */ }
    public void filterAgents() { /* ... */ }
}
```

### 8.2 Utility Classes

#### 8.2.1 Data Utilities

| Class | Purpose | Key Methods |
|-------|---------|-------------|
| `ExcelHelper` | Excel operations | `readData()`, `writeData()`, `getCellValue()` |
| `CSVHelper` | CSV operations | `readCSV()`, `writeCSV()` |
| `DataHelper` | Test data generation | `generateTestData()`, `getRandomData()` |

#### 8.2.2 Database Utilities

| Class | Purpose | Key Methods |
|-------|---------|-------------|
| `DatabaseHelper` | DB operations | `executeQuery()`, `executeUpdate()`, `getConnection()` |
| `DBConnectionPool` | Connection pooling | `getConnection()`, `releaseConnection()` |

#### 8.2.3 File Utilities

| Class | Purpose | Key Methods |
|-------|---------|-------------|
| `FileHelper` | File operations | `copyFile()`, `deleteFile()`, `createDirectory()` |

#### 8.2.4 Date/Time Utilities

| Class | Purpose | Key Methods |
|-------|---------|-------------|
| `DateHelper` | Date operations | `getCurrentDate()`, `formatDate()`, `addDays()` |

#### 8.2.5 String Utilities

| Class | Purpose | Key Methods |
|-------|---------|-------------|
| `StringHelper` | String operations | `removeSpecialChars()`, `generateRandom()` |

#### 8.2.6 Wait Utilities

| Class | Purpose | Key Methods |
|-------|---------|-------------|
| `WaitHelper` | Wait strategies | `waitForElement()`, `waitForPageLoad()` |

---

## 9. TEST DATA MANAGEMENT

### 9.1 Excel-Based Test Data

Test data is stored in Excel workbooks with a standardized structure:

#### 9.1.1 FunctionalTestCases.xlsx

**Location:** `rocps-automation/src/main/resources/FunctionalTestCases.xlsx`

**Structure:**
- One sheet per test scenario
- Columns: Test data fields
- Rows: Test case variations

**Example Sheet Structure:**

| Test Case ID | Agent Name | Agent Code | Parent Account | Status | Expected Result |
|-------------|-----------|-----------|----------------|--------|----------------|
| TC_Agent_01 | Agent1 | A001 | ParentAcc1 | Active | Success |
| TC_Agent_02 | Agent2 | A002 | | Active | Success |

#### 9.1.2 SystemTestCases.xlsx

**Location:** `rocps-automation/src/main/resources/SystemTestCases.xlsx`

**Purpose:** System-level end-to-end test data

### 9.2 Test Data Files

**Data Directory:** `rocps-automation/src/main/resources/Data/FunctionalTesting/`

```
Data/FunctionalTesting/
├── CDRS/                    # CDR test files
├── RateSheet/               # Rate sheet files
├── BulkLoadStream/          # Bulk load data
├── CarrierInvoice/          # Carrier invoice files
├── RoamingFilePath/         # Roaming test files
├── FileUpload/              # Files for upload testing
├── PdfToExcel/              # PDF files for conversion
└── DealImport/              # Deal import templates
```

### 9.3 Data Access Pattern

```java
// Reading test data from Excel
String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
String workBookName = "FunctionalTestCases.xlsx";
String sheetName = "Agent";

Agent agentHelper = new Agent(path, workBookName, sheetName, "Agent", 1);
agentHelper.agentCreation();
```

---

## 10. REPORTING & LOGGING

### 10.1 ExtentReports

**Configuration:**
- HTML reports with screenshots
- Test step logging
- Pass/Fail status tracking
- Execution timeline

**Report Location:** `rocps-automation/Report/`

**Report Structure:**
```
Report/
├── Run1/
│   ├── ExtentReport.html
│   └── Screenshots/
├── Run2/
└── Run3/
```

### 10.2 TestNG Reports

**Output Location:** `rocps-automation/test-output/`

**Reports Generated:**
- `emailable-report.html` - Email-friendly summary
- `index.html` - Detailed test results
- `testng-results.xml` - XML results for CI/CD
- `testng-failed.xml` - Failed test suite for re-run

### 10.3 Logging

**Log4j Configuration:**
- Console logging
- File logging
- Log levels: DEBUG, INFO, WARN, ERROR

**Log Format:**
```
[TIMESTAMP] [LEVEL] [CLASS] - Log message
```

### 10.4 Screenshot Capture

**Automatic Screenshots:**
- On test failure
- On assertion failure
- On exceptions

**Manual Screenshots:**
```java
BrowserHelper.captureScreenshot("ScreenshotName");
```

---

## 11. BUILD & DEPLOYMENT

### 11.1 Maven Build Commands

#### 11.1.1 Clean & Install (All Projects)

```bash
# Project 1: roc-automation-util (Base)
cd roc-automation-util
mvn clean install -DskipTests

# Project 2: roc-automation (Middle)
cd ../roc-automation
mvn clean install -DskipTests

# Project 3: rocps-automation (Top)
cd ../rocps-automation
mvn clean install -DskipTests
```

#### 11.1.2 Run Tests

```bash
# Run with default TestNG suite
mvn test

# Run with specific TestNG suite
mvn test -Dtestng.filename=FunctionalTesting_RunScript.xml

# Run with custom driver file
mvn test -Dtestng.driverFile=CustomDriver.xlsx

# Skip tests during build
mvn clean install -DskipTests=true
```

### 11.2 Batch Scripts

#### 11.2.1 PS_RunScript.bat (ROCPS Execution)

**Location:** `rocps-automation/PS_RunScript.bat`

**Usage:**
```batch
PS_RunScript.bat
```

**Purpose:** Execute ROCPS functional tests

#### 11.2.2 Additional Scripts

**Location:** `rocps-automation/eclipse/scripts/`

- `CopyFile.bat` - Copy files
- `DeleteFile.bat` - Delete files
- `MakeDirectory.bat` - Create directories
- `RunAt.bat` - Schedule tasks
- `RunGUIInstaller.bat` - Run GUI installers
- `RunSilentInstaller.bat` - Run silent installers
- `RunServerService.bat` - Control server services
- `RunStreamController.bat` - Start stream controller
- `RunTaskController.bat` - Start task controller
- `RunTomcatServer.bat` - Start Tomcat server

### 11.3 CI/CD Integration

**Maven Surefire Configuration:**

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.5.2</version>
    <configuration>
        <suiteXmlFiles>
            <file>src/main/resources/${testng.filename}</file>
        </suiteXmlFiles>
        <skipTests>${skipTests}</skipTests>
        <systemPropertyVariables>
            <driverFile>${testng.driverFile}</driverFile>
        </systemPropertyVariables>
    </configuration>
</plugin>
```

**CI/CD Execution:**
```bash
mvn clean test -Dtestng.filename=CICD_RunScript.xml
```

---

## 12. BEST PRACTICES & STANDARDS

### 12.1 Naming Conventions

#### 12.1.1 Test Classes

```
Pattern: TC<FeatureName>
Examples:
- TCAgent.java
- TCAccount.java
- TCBills.java
- TCAccruals.java
```

#### 12.1.2 Test Methods

```
Pattern: <feature><Action><Scenario>
Examples:
- agentCreation()
- agentModification()
- agentparentCreation()
- agentcolVal()
```

#### 12.1.3 Helper Classes

```
Pattern: <DomainName><HelperType>
Examples:
- Agent.java (Main)
- AgentDetails.java (Details)
- AgentSearchImpl.java (Search)
```

#### 12.1.4 Object Repository

```
Pattern: <Page>_<Element>_<LocatorType>
Examples:
- LoginPage_Username_ID
- AgentPage_AgentName_XPATH
- GridPage_SaveButton_CSS
```

### 12.2 Code Standards

#### 12.2.1 Test Structure

```java
@Test(priority = 1, description = "Test description", 
      retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
public void testMethod() throws Exception {
    try {
        // Arrange
        HelperClass helper = new HelperClass(path, workbook, sheet, testCase, row);
        
        // Act
        helper.performAction();
        
        // Assert (implicit in helper)
    } catch (Exception e) {
        FailureHelper.reportFailure(e);
        throw e;
    }
}
```

#### 12.2.2 Helper Structure

```java
public class FeatureHelper extends PSAcceptanceTest {
    
    private String path;
    private String workbook;
    private String sheet;
    
    public FeatureHelper(String path, String workbook, String sheet, 
                         String testCase, int row) {
        this.path = path;
        this.workbook = workbook;
        this.sheet = sheet;
        // Read test data
    }
    
    public void featureOperation() throws Exception {
        // Navigate
        // Perform operations
        // Validate
        // Report
    }
}
```

### 12.3 Exception Handling

```java
try {
    // Test code
} catch (Exception e) {
    FailureHelper.reportFailure(e);
    throw e;
}
```

### 12.4 Wait Strategies

```java
// Explicit waits
WaitHelper.waitForElement(driver, element, 30);

// Page load waits
WaitHelper.waitForPageLoad(driver);

// Custom conditions
WaitHelper.waitForCondition(driver, condition, timeout);
```

---

## 13. TROUBLESHOOTING GUIDE

### 13.1 Common Build Issues

#### Issue 1: Maven Build Failure - Dependency Not Found

**Error:**
```
[ERROR] Failed to execute goal on project rocps-web-automation: 
Could not resolve dependencies for project com.subex.rocps:rocps-web-automation:jar:10.5.33.0: 
Could not find artifact com.subex.roc:roc-automation:jar:1.0.0.1-SNAPSHOT
```

**Solution:**
```bash
# Build projects in correct order
cd roc-automation-util
mvn clean install -DskipTests

cd ../roc-automation
mvn clean install -DskipTests

cd ../rocps-automation
mvn clean install -DskipTests
```

#### Issue 2: Java Version Mismatch

**Error:**
```
[ERROR] Source option 11 is no longer supported. Use 17 or later.
```

**Solution:**
```bash
# Check Java version
java -version

# Update pom.xml
<maven.compiler.source>11</maven.compiler.source>
<maven.compiler.target>11</maven.compiler.target>
```

#### Issue 3: WebDriver Binary Not Found

**Error:**
```
WebDriverException: The path to the driver executable must be set
```

**Solution:**
- Framework uses WebDriverManager 6.3.2 for automatic driver management
- Ensure internet connectivity for first-time driver download
- Or manually set driver path in configuration

### 13.2 Common Test Execution Issues

#### Issue 1: Browser Not Launching

**Possible Causes:**
- WebDriver binary not found
- Browser version incompatible with driver
- Port already in use

**Solution:**
```properties
# Check psconfig.properties
browser=firefox  # or chrome, ie

# Ensure WebDriverManager can download drivers
# Or set manual path
webdriver.chrome.driver=C:\\path\\to\\chromedriver.exe
```

#### Issue 2: Element Not Found

**Error:**
```
NoSuchElementException: Unable to locate element
```

**Solution:**
- Verify element locator in OR file
- Add explicit waits
- Check if element is in iframe
- Verify page load complete

#### Issue 3: Test Data Not Found

**Error:**
```
FileNotFoundException: FunctionalTestCases.xlsx
```

**Solution:**
- Verify file path in test class
- Ensure Excel file exists in resources
- Check file permissions

### 13.3 Configuration Issues

#### Issue 1: Configuration File Not Loaded

**Error:**
```
NullPointerException when accessing config property
```

**Solution:**
```java
// Verify config file parameter in TestNG XML
<parameter name="config" value="psconfig.properties"></parameter>

// Verify file location
src/main/resources/psconfig.properties
```

#### Issue 2: Object Repository Not Loaded

**Error:**
```
NullPointerException when accessing locator
```

**Solution:**
```xml
<!-- Verify orFiles parameter in TestNG XML -->
<parameter name="orFiles" value="PS_OR.properties,Tariff_OR.properties"></parameter>
```

### 13.4 Reporting Issues

#### Issue 1: ExtentReports Not Generated

**Solution:**
- Verify report directory exists
- Check write permissions
- Ensure CustomTestListenerAdapter is configured

#### Issue 2: Screenshots Not Captured

**Solution:**
```properties
# Check psconfig.properties
embedImageInReport=Yes
recordExecution=Yes
```

---

## 14. FUTURE ENHANCEMENT OPPORTUNITIES

### 14.1 Framework Improvements

#### 14.1.1 Upgrade Selenium

**Current:** Selenium 4.28.1  
**Recommendation:** Keep updated with latest stable releases

**Benefits:**
- Improved BiDi Protocol support
- Better Chrome DevTools integration
- Enhanced grid capabilities

#### 14.1.2 Upgrade TestNG

**Current:** TestNG 7.5.1  
**Recommendation:** Upgrade to TestNG 7.10+

**Benefits:**
- Better parallel execution
- Improved listeners
- Enhanced reporting

#### 14.1.3 Migrate to Page Object Model

**Current:** Helper Pattern  
**Recommendation:** Hybrid approach (Helper + POM)

**Benefits:**
- Better separation of concerns
- Easier maintenance
- Industry standard

#### 14.1.4 Add API Testing

**Recommendation:** Integrate REST Assured

**Benefits:**
- Backend validation
- Faster test execution
- Complete test coverage

### 14.2 Code Quality Improvements

#### 14.2.1 Add Code Coverage

**Tools:** JaCoCo, Cobertura

**Benefits:**
- Measure test coverage
- Identify untested code
- Quality metrics

#### 14.2.2 Add Static Code Analysis

**Tools:** SonarQube, Checkstyle, PMD

**Benefits:**
- Code quality metrics
- Bug detection
- Security vulnerability scanning

#### 14.2.3 Implement Parallel Execution

**Configuration:**
```xml
<suite name="Parallel_Suite" parallel="tests" thread-count="5">
```

**Benefits:**
- Faster execution
- Better resource utilization
- Quick feedback

### 14.3 CI/CD Enhancements

#### 14.3.1 Docker Integration

**Recommendation:** Containerize test execution

**Benefits:**
- Consistent environment
- Easy scaling
- Cloud-ready

#### 14.3.2 Jenkins Integration

**Recommendation:** Complete Jenkins pipeline

**Benefits:**
- Automated execution
- Scheduled runs
- Email notifications

#### 14.3.3 GitHub Actions

**Recommendation:** Add GitHub Actions workflows

**Benefits:**
- PR validation
- Automated builds
- Cloud-based execution

### 14.4 Reporting Enhancements

#### 14.4.1 Allure Reports

**Recommendation:** Add Allure reporting

**Benefits:**
- Beautiful UI
- Historical trends
- Better visualization

#### 14.4.2 Dashboard Integration

**Tools:** Grafana, Kibana

**Benefits:**
- Real-time monitoring
- Trend analysis
- Metrics tracking

### 14.5 Test Data Management

#### 14.5.1 Database Test Data

**Recommendation:** Generate test data from database

**Benefits:**
- Dynamic data
- No Excel maintenance
- Realistic scenarios

#### 14.5.2 Test Data API

**Recommendation:** Create test data API

**Benefits:**
- Centralized data management
- Version control
- Reusability

---

## 15. QUICK REFERENCE

### 15.1 Essential Commands

```bash
# Build all projects
cd roc-automation-util && mvn clean install -DskipTests
cd ../roc-automation && mvn clean install -DskipTests
cd ../rocps-automation && mvn clean install -DskipTests

# Run functional tests
cd rocps-automation
mvn test -Dtestng.filename=FunctionalTesting_RunScript.xml

# Run system tests
mvn test -Dtestng.filename=SystemTesting_RunScript.xml

# Run with specific browser
mvn test -Dbrowser=chrome

# Generate reports
mvn surefire-report:report
```

### 15.2 Essential Files

| File | Location | Purpose |
|------|----------|---------|
| `psconfig.properties` | `rocps-automation/src/main/resources/` | Main configuration |
| `PS_OR.properties` | `rocps-automation/src/main/resources/` | Object Repository |
| `FunctionalTestCases.xlsx` | `rocps-automation/src/main/resources/` | Test data |
| `FunctionalTesting_RunScript.xml` | `rocps-automation/src/main/resources/` | TestNG suite |
| `PSAcceptanceTest.java` | `rocps-automation/src/main/java/.../selenium/` | Base test class |

### 15.3 Essential Classes

| Class | Package | Purpose |
|-------|---------|---------|
| `AcceptanceTest` | `com.subex.automation.helpers.selenium` | Base WebDriver class |
| `ROCAcceptanceTest` | `com.subex.automation.helpers.selenium` | ROC base class |
| `PSAcceptanceTest` | `com.subex.rocps.automation.helpers.selenium` | ROCPS base class |
| `BrowserHelper` | `com.subex.automation.helpers.application` | Browser operations |
| `GridHelper` | `com.subex.automation.helpers.component` | Grid operations |

---

## 16. CONTACT & SUPPORT

### 16.1 Documentation

- **Framework Analysis:** `ROCPS_Framework_Analysis.md`
- **Constitution:** `automation_constitution.md`
- **README:** `README.md`
- **This Document:** `COMPLETE_FRAMEWORK_ANALYSIS.md`

### 16.2 Project Information

**Organization:** Subex Ltd  
**Website:** http://www.subex.com  
**Product:** ROCPS (Revenue Operations & Controls - Partner Settlement)  
**Version:** 10.5.33.0

---

## APPENDIX A: MAVEN POM CONFIGURATIONS

### A.1 roc-automation-util pom.xml

```xml
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.subex.roc</groupId>
    <artifactId>roc-web-util</artifactId>
    <version>1.0.0.1-SNAPSHOT</version>
    
    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    
    <dependencies>
        <!-- TestNG -->
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.5.1</version>
        </dependency>
        
        <!-- Selenium -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.28.1</version>
        </dependency>
        
        <!-- Other dependencies... -->
    </dependencies>
</project>
```

### A.2 roc-automation pom.xml

```xml
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.subex.roc</groupId>
    <artifactId>roc-automation</artifactId>
    <version>1.0.0.1-SNAPSHOT</version>
    
    <dependencies>
        <dependency>
            <groupId>com.subex.roc</groupId>
            <artifactId>roc-web-util</artifactId>
            <version>1.0.0.1-SNAPSHOT</version>
        </dependency>
        
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.5.1</version>
        </dependency>
    </dependencies>
</project>
```

### A.3 rocps-automation pom.xml

```xml
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.subex.rocps</groupId>
    <artifactId>rocps-web-automation</artifactId>
    <version>10.5.33.0</version>
    
    <properties>
        <skipTests>true</skipTests>
        <testng.filename>Dummy_RunScript.xml</testng.filename>
        <testng.driverFile>TestDriver.xlsx</testng.driverFile>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>com.subex.roc</groupId>
            <artifactId>roc-web-util</artifactId>
            <version>1.0.0.1-SNAPSHOT</version>
        </dependency>
        
        <dependency>
            <groupId>com.subex.roc</groupId>
            <artifactId>roc-automation</artifactId>
            <version>1.0.0.1-SNAPSHOT</version>
        </dependency>
        
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.5.1</version>
        </dependency>
    </dependencies>
</project>
```

---

## APPENDIX B: SAMPLE TEST CASE

### B.1 Functional Test Case Example

```java
package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCAgent extends PSAcceptanceTest {

    String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
    String workBookName = "FunctionalTestCases.xlsx";
    String sheetName = "Agent";

    @org.testng.annotations.Test(
        priority = 1, 
        description = "Agent creation", 
        retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class
    )
    public void agentCreation() throws Exception {
        try {
            Agent agentObj = new Agent(path, workBookName, sheetName, "Agent", 1);
            agentObj.agentCreation();
        } catch (Exception e) {
            FailureHelper.reportFailure(e);
            throw e;
        }
    }

    @org.testng.annotations.Test(
        priority = 2, 
        description = "Agent with Parent Account creation", 
        retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class
    )
    public void agentparentCreation() throws Exception {
        try {
            Agent agentObj = new Agent(path, workBookName, sheetName, "ParentAgent", 1);
            agentObj.agentCreation();
        } catch (Exception e) {
            FailureHelper.reportFailure(e);
            throw e;
        }
    }
}
```

---

**END OF DOCUMENT**

*This comprehensive analysis document serves as the complete reference for the ROCPS Web Automation Framework. For questions, updates, or contributions, please refer to the project repository.*

