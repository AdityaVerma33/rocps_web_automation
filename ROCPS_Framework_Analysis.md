projects# ROCPS Web Automation Framework - Comprehensive Analysis

## Document Version: 1.0
**Date**: January 30, 2026  
**Purpose**: Complete framework structure documentation for future reference and analysis

---

## 1. FRAMEWORK OVERVIEW

The ROCPS (Revenue and Order Calculation for Partner Settlement) Web Automation Framework is a comprehensive Selenium-based test automation framework built using Java, Maven, and TestNG. The framework follows a modular architecture with three main Maven projects working together.

### 1.1 High-Level Architecture

```
rocps_web_automation (Root Workspace)
│
├── roc-automation-util      (Utility/Core Framework Layer)
├── roc-automation            (ROC Specific Implementation Layer)
└── rocps-automation          (ROCPS/Partner Settlement Layer)
```

**Dependencies Flow**:
- `rocps-automation` depends on → `roc-automation` and `roc-automation-util`
- `roc-automation` depends on → `roc-automation-util`
- `roc-automation-util` is the base/core framework (no external project dependencies)

---

## 2. PROJECT STRUCTURE DETAILS

### 2.1 roc-automation-util (Core Utility Framework)

**Artifact**: `com.subex.roc:roc-web-util:1.0.0.1-SNAPSHOT`

**Purpose**: Core automation utilities, helpers, and base classes that are reusable across all ROC/ROCPS projects.

#### 2.1.1 Package Structure

```
com.subex.automation.helpers/
├── application/          - Application-level helpers
├── component/           - UI component helpers (TextBox, ComboBox, Grid, etc.)
├── componentHelpers/    - Extended component helpers
├── config/              - Configuration management
├── data/                - Data generation and management
├── dataGeneration/      - Test data generation utilities
├── db/                  - Database utilities and helpers
├── enums/               - Framework enumerations
├── file/                - File handling utilities
├── performance/         - Performance testing utilities
├── report/              - Reporting utilities (Extent Reports)
├── scripts/             - Script execution helpers
├── selenium/            - Selenium WebDriver base classes
│   └── AcceptanceTest.java - Base test class
├── TestNG/              - TestNG listeners and configurations
├── util/                - General utility classes
└── voiceRecognition/    - Voice recognition utilities
```

#### 2.1.2 Key Component Helpers

All helpers are registered in `helpers.properties`:

1. **TextBoxHelper** - Text input handling
2. **ComboBoxHelper** - Dropdown/combobox handling
3. **CheckBoxHelper** - Checkbox operations
4. **RadioHelper** - Radio button handling
5. **ButtonHelper** - Button click operations
6. **GridHelper** - Grid/table operations
7. **TreeHelper** - Tree structure navigation
8. **LabelHelper** - Label verification
9. **LinkHelper** - Link operations
10. **PropertyGridHelper** - Property grid handling
11. **TabHelper** - Tab navigation
12. **TextAreaHelper** - Text area operations
13. **ImageHelper** - Image handling
14. **SpinnerHelper** - Spinner control
15. **SliderHelper** - Slider control
16. **HierarchyGridHelper** - Hierarchical grid operations
17. **GridCheckBoxHelper** - Grid checkbox operations
18. **WindowsHelper** - Window handling
19. **CanvasHelper** - Canvas operations
20. **ConfirmBoxHelper** - Confirmation dialog handling

#### 2.1.3 Key Dependencies (roc-automation-util)

**Testing & Automation**:
- TestNG 6.9.10
- Selenium WebDriver 3.141.59
- Selenium Remote Driver 3.141.59
- WebDriverManager 6.3.2
- Sikuli API 2.0.5
- PhantomJS Driver 1.3.0
- ngWebDriver 1.1.6

**Data Handling**:
- Apache POI 3.12 (Excel operations)
- OpenCSV 2.3 (CSV operations)
- JSON 20160810
- JSON-Simple 1.1.1
- JSoup 1.10.2 (HTML parsing)

**Database**:
- DBUnit 2.7.3
- JTDS 1.3.1 (SQL Server)
- PostgreSQL 9.1-901.jdbc4
- Commons DBCP 1.4
- C3P0 0.9.5.5

**Reporting**:
- ExtentReports 2.41.2
- Log4j 1.2.17

**Utilities**:
- Apache Commons IO 2.6
- Apache Commons Configuration 1.10
- Apache HttpClient 4.5.2
- Joda Time 2.9.7
- ZT-Zip 1.10

**Other**:
- JCraft JSch 0.1.55 (SSH/Linux communication)
- JavaMail 1.5.6
- Sphinx4 (Voice Recognition)

#### 2.1.4 Resources

- `helpers.properties` - Component helper registration
- `extent-config.xml` - Extent report configuration

#### 2.1.5 Browser Drivers & Plugins

```
plugins/
└── drag_and_drop_helper.js
```

---

### 2.2 roc-automation (ROC Core Implementation)

**Artifact**: `com.subex.roc:roc-automation:1.0.0.1-SNAPSHOT`

**Purpose**: Contains ROC (Revenue Assurance and Optimization Center) specific test implementations including ETL, Audits, User Management, LDC, Tariffs, and Data Federation.

#### 2.2.1 Key Resources

**Test Suites (XML Files)**:
- `CICD_RunScript.xml` - CI/CD pipeline execution
- `CTAF_RunScript.xml` - CTAF test execution
- `Dummy_RunScript.xml` - Default/template suite

**Regression Test Suites**:
- `Regression_ETL.xml`
- `Regression_UserManagement.xml`
- `Regression_Masking.xml`
- `Regression_DuplicateCheck.xml`
- `Regression_DataFederation.xml`
- `Regression_ROCRA.xml`
- `Regression_ROCView.xml`

**System Testing Suites**:
- `SystemTesting_Security.xml`
- `SystemTesting_Tariff.xml`
- `SystemTesting_ETL.xml`
- `SystemTesting_OnlineLDC.xml`
- `SystemTesting_OfflineLDC.xml`
- `SystemTesting_DuplicateCheck.xml`
- `SystemTesting_DataFederation.xml`
- `SystemTesting_FileSequenceCheck.xml`
- `SystemTesting_AuditFlow1.xml`
- `SystemTesting_AuditFlow2.xml`
- `SystemTesting_ROCFlows.xml`

**Configuration Files**:
- `config.properties` - Main configuration
- `ctaf-config.properties` - CTAF specific config
- `OR.properties` - Object Repository (Main)
- `Users_OR.properties` - User Management OR
- `Tariff_OR.properties` - Tariff OR
- `ROCView_OR.properties` - ROC View OR
- `ROCRA_OR.properties` - ROC RA OR
- `Measures_Audits_OR.properties` - Measures & Audits OR
- `LDC_OR.properties` - LDC OR
- `CM_OR.properties` - Configuration Management OR

**Test Data Files**:
- `ROC_TestData_Format1.xlsx`
- `ROC_TestData_Format2.xlsx`
- `ETLRegression_TestData.xlsx`
- `AuditRegression_TestData.xlsx`
- `Masking_TestData.xlsx`
- `Masking_Assertion.xlsx`
- `DuplicateCheck_TestData.xlsx`
- `DataFederation_TestData.xlsx`
- `ROCRA_TestData.xlsx`
- `UserManagement_TestData.xlsx`
- `Zen_TestData.xlsx`
- `Zen_Assertion.xlsx`

**Data Files**:
```
Data/
├── Tapin.xml
├── Tapout.xml
├── String_Rule_Set.xml
├── Tariff/
│   └── Rating_Tariff_Scenario8.xml
└── StringRuleSet/
    └── String_Rule_Set.xml
```

#### 2.2.2 Browser Drivers

```
plugins/
├── chromedriver_78.0.3904.70.exe
├── chromedriver_98.0.4758.80.exe
├── chromedriver_99.0.4844.17.exe
├── ChromeDriver2.35.exe
├── ChromeDriver2.9.exe
├── geckodriver0.26.0.exe
├── geckodriver0.28.0.exe
├── geckodriver0.30.0.exe
└── IEDriverServer.exe
```

#### 2.2.3 Execution Scripts

```
eclipse/scripts/
├── CopyFile.bat
├── DeleteFile.bat
├── Execute
├── MakeDirectory.bat
├── RunAt.bat
├── RunGUIInstaller.bat
├── RunPreSilentInstaller.bat
├── RunServerService.bat
├── RunSilentInstaller.bat
├── RunStreamController.bat
├── RunTaskController.bat
└── RunTomcatServer.bat
```

**Root Level Scripts**:
- `CICD_RunScript.bat`
- `CTAF_RunScript.bat`
- `RunScript.bat`

#### 2.2.4 Images & Utilities

```
Images/
└── FileUpload/
    └── File_Upload.exe
```

---

### 2.3 rocps-automation (ROCPS Partner Settlement)

**Artifact**: `com.subex.rocps:rocps-web-automation:10.5.33.0`

**Purpose**: ROCPS (Partner Settlement) specific test automation including Roaming, Billing, Settlements, Deals, Tariffs, and all partner configuration modules.

#### 2.3.1 Package Structure

```
com.subex.rocps.automation/
├── helpers/
│   ├── application/          - Application helpers (40+ modules)
│   │   ├── accruals/
│   │   ├── admin/
│   │   ├── aggregation/
│   │   ├── alerts/
│   │   ├── amountthreshold/
│   │   ├── approvalworkflows/
│   │   ├── arms/
│   │   ├── bcrManagement/
│   │   ├── bilateral/
│   │   ├── bills/
│   │   ├── bulkentityexport/
│   │   ├── bulkentityselection/
│   │   ├── carrierinvoice/
│   │   ├── customexception/
│   │   ├── deal/
│   │   ├── dealImport/
│   │   ├── dispute/
│   │   ├── eventandaggregation/
│   │   ├── eventErrors/
│   │   ├── exchangeRates/
│   │   ├── fileUpload/
│   │   ├── filters/
│   │   ├── genericHelpers/
│   │   ├── matchandrate/
│   │   ├── monitoring/
│   │   ├── networkConfiguraiton/
│   │   ├── partnerConfiguration/  - Partner configuration helpers
│   │   │   ├── account/
│   │   │   ├── Account.java
│   │   │   ├── agent/
│   │   │   ├── Agent.java
│   │   │   ├── billprofile/
│   │   │   ├── BillProfile.java
│   │   │   ├── eventUsg/
│   │   │   ├── eventUsgReqScheduler/
│   │   │   ├── eventUsgRequest/
│   │   │   ├── EventUsageHelper.java
│   │   │   ├── EventUsgRequestHelper.java
│   │   │   ├── EventUsgRequestScheduler.java
│   │   │   └── Franchise.java
│   │   ├── payments/
│   │   ├── pdfToExcel/
│   │   ├── prepayments/
│   │   ├── products/
│   │   ├── quality/
│   │   ├── reaggregation/
│   │   ├── referenceTable/
│   │   ├── reportingAndExtraction/
│   │   ├── roaming/
│   │   ├── Sales/
│   │   ├── settlements/
│   │   ├── system/
│   │   ├── tariffs/
│   │   ├── vendorRateManagement/
│   │   └── xdrextraction/
│   ├── dbscript/
│   ├── listener/
│   └── selenium/
│       └── PSAcceptanceTest.java (Base test class)
├── testcases/
│   ├── functionaltesting/     - 250+ functional test classes
│   │   ├── TCAccount.java
│   │   ├── TCAgent.java
│   │   ├── TCBillingCycle.java
│   │   ├── TCDeal.java
│   │   ├── TCRoaming*.java (multiple roaming test classes)
│   │   ├── TCSettlements.java
│   │   ├── TCTariff.java
│   │   └── ... (250+ test case classes)
│   └── systemtesting/         - System/End-to-end test cases
│       ├── Prerequisites.java
│       ├── PSPrerequisites.java
│       ├── ROCPreRequisites.java
│       ├── TCConfigurations.java
│       ├── TCVoiceStream.java
│       ├── TCFiosStream.java
│       └── TestCase01.java - TestCase20.java
└── utils/
```

#### 2.3.2 Functional Test Case Classes (Partial List - 250+ Total)

**Partner Configuration**:
- TCAccount, TCAccountAction, TCAccountCategory
- TCAgent, TCAgentAction
- TCBillProfile, TCBillProfileAction
- TCFranchise

**Network Configuration**:
- TCOperator, TCSwitch, TCSwitchAction
- TCRoute, TCRouteGroup, TCRouteGroupAction
- TCTadigCodes
- TCDialStringMatch, TCInRouteString*

**Tariff & Rating**:
- TCTariff, TCTariffClass, TCTariffType
- TCBands, TCSwitchToBand
- TCMarkupRule, TCSurchargeRule, TCOfferRule
- TCFloorCeilingPrice, TCAutoRateSheetConfig
- TCRateSheetTemplate, TCRateSheetImportRequest

**Billing & Invoicing**:
- TCBillingCycle, TCBillingGroupCode
- TCBill*, TCHotBill, TCTestBill
- TCCarrierInvoice*, TCAutomaticInvoiceConfig
- TCInvoiceReconConfig, TCInvoiceReconRequest

**Roaming (30+ test classes)**:
- TCRoamingConfiguration, TCRoamingDefinition, TCRoamingDfnGrp
- TCRoamingService, TCRoamingServContextMapping, TCRoamingServMatchExpr
- TCRoamingChargeItem, TCRoamingDiscounting, TCRoamingTaxation
- TCRoamingTapIn/Out/RapIn/OutServerCases
- TCRoamingFileStatus, TCRoamingFlVersions
- TCRoamingAgreemConfig, TCRoamingExpGroup

**Deals & Settlements**:
- TCDeal, TCDealImport, TCDealSimulation, TCDealTrafficTypeOrder
- TCSettlements, TCSettlementsBill
- TCDispute, TCCreditNotes

**Aggregation & Events**:
- TCAggregationConfiguration, TCAggregationProcessor
- TCEventAndAggregation, TCEventType, TCEventUsage
- TCEventModellingDefinition, TCEventModellingInst
- TCEventMatchRule, TCEventMatchRuleGroup
- TCEventErrorRerate, TCEventErrorTest
- TCReAggregationRequest

**Quality & Analysis**:
- TCQualityManager, TCQualityRule, TCQualityMetric, TCQualityThreshold
- TCNetworkAnalysis, TCRankAnalysis
- TCQOSConfiguration, TCQOSTableInstance

**BCR Management**:
- TCBCRProduct, TCBCRPlan, TCBCRRequest
- TCBCRImport, TCConsolidatedBCRPlan

**Payments & Collections**:
- TCPaymentsAndCollections
- TCPrePayments, TCPrepaymentServerCases
- TCViewBalance

**Products**:
- TCProductBundles, TCProductInstance, TCProductInstanceServer
- TCProductArgumentType, TCProductBundleDrillDown

**Reporting & Extraction**:
- TCReportAndExtracts, TCReportAndExtDefinition, TCReportAndExtScheduler
- TCReportModelling, TCReportColumnMapping
- TCXdrExtTemplate, TCXdrExtractResult

**Reference Tables & Admin**:
- TCCurrency, TCIMFCurrency, TCCrossFXRate, TCCrossFXRateGroup
- TCBank, TCGLCodeDefn, TCGlCodeInstance
- TCLineOfBusiness, TCChargeType, TCTrafficType
- TCCallTypeGroup, TCCallTypeLevel1, TCCallTypeLevel2
- TCElements, TCEntities

**File Management**:
- TCFileUpload, TCFileUploadCategory, TCFileUploadPreReq
- TCBulkEntityExport, TCBulkLoaderStream
- TCStreams, TCUploadFileType

**Alerts & Monitoring**:
- TCAlertEvent, TCAmountThreshold
- TCNRTRDEFileStatus, TCNRTRDEReport

**Accruals & Approvals**:
- TCAccrualsModelling, TCAccrulsOverviewModelling
- TCApprovalWorkflows, TCCreditApprovalWorkflow

**Sales & Taxation**:
- TCSalesOffer, TCSalesProposal
- TCSalesTax, TCSalesTaxGroup, TCSalesTaxRate

**Miscellaneous**:
- TCPdfExcelConversion, TCPdfExcelTemplate
- TCRerateRequest, TCUsageBackoutRequest
- TCIMSIManagement, TCTestSIMManagement
- TCVoiceRO*, TCVoiceSurcharge* (Voice Routing)

#### 2.3.3 Configuration Files

**Main Config**:
- `psconfig.properties` - ROCPS main configuration
  - Browser settings
  - Deployment details (Client URL, Server paths, Tomcat)
  - Data directory paths
  - CDR, Ratesheet, Bulk Load paths
  - Carrier Invoice paths
  - XDR extraction paths
  - Roaming file paths
  - Reports and extracts paths
  - File upload paths

**Object Repository**:
- `PS_OR.properties` - ROCPS Object Repository
- `Tariff_OR.properties` - Tariff specific OR

#### 2.3.4 Test Data Structure

```
Data/
├── E2ECdrs/
├── FunctionalTesting/
│   ├── BulkLoadStream/
│   ├── CarrierInvoice/
│   ├── CDRS/
│   ├── DealImport/
│   │   ├── DealImportCommittedDeal1.xlsx
│   │   ├── DealImportNonCommitted1.xlsx
│   │   ├── DealImportErrorData1.xlsx
│   │   ├── DealImportErrorConfiguration1.xlsx
│   │   └── TemplateFile.xlsx
│   ├── FileUpload/
│   ├── PdfToExcel/
│   ├── RateSheet/
│   │   ├── RateSheet_Origin.xlsx
│   │   ├── Ratesheet_Destination.xlsx
│   │   ├── Ratesheet1.xlsx
│   │   ├── Ratesheet1-Error.xlsx
│   │   ├── Ratesheet01_dest.xlsx
│   │   ├── Ratesheet02_origin.xlsx
│   │   ├── Operator1.xlsx
│   │   ├── CountryWise_T7_AllScenarios.xlsx
│   │   ├── BKLG33_TC5.xlsx
│   │   └── SysMDL.xlsx
│   └── RoamingFilePath/
└── SystemTesting/
```

**Test Excel Files**:
- `SystemTestCases.xlsx` - System test case repository
- `FunctionalTestCases.xlsx` - Referenced by 250+ test classes (not found in scan, may be in different location)

#### 2.3.5 Test Suite Configuration

**Main Suite**:
- `SystemTesting_RunScript.xml` - Main system testing suite

**Parser Configurations**:
```
TAPIN_Parse/
├── roaming_parser_tap.xml
└── roaming_parser_rap.xml

Diamond/
├── roaming_parser_tap.xml
└── roaming_voice_tapOut.xml
```

**BIRT Reports**:
```
Birt/
└── (Report configuration files)
```

#### 2.3.6 Browser Drivers

```
plugins/
├── chromedriver.exe
├── geckodriver.exe
└── msedgedriver.exe
```

#### 2.3.7 Execution Scripts

- `PS_RunScript.bat` - Main ROCPS execution script

#### 2.3.8 Reports

```
test-output/                  - TestNG reports output
├── emailable-report.html
├── testng-results.xml
├── testng-failed.xml
├── index.html
├── CTAF/
├── Default suite/
├── Failed suite [CTAF]/
├── TestExecution/
├── junitreports/
└── old/

Report/                       - Custom reports location (mentioned in README)
ROCPS_Automation_Report.xlsx - Test execution tracking
```

#### 2.3.9 Images & Utilities

```
Images/
└── FileUpload/
    └── File_Upload.exe      - AutoIT file upload utility
```

---

## 3. FRAMEWORK DESIGN PATTERNS & FEATURES

### 3.1 Design Patterns Used

1. **Page Object Model (POM)**: Helper classes represent different modules/pages
2. **Factory Pattern**: Component helpers are registered and instantiated dynamically
3. **Singleton Pattern**: Configuration and driver management
4. **Data-Driven Testing**: Excel-based test data management
5. **Keyword-Driven Testing**: Helper methods act as keywords

### 3.2 Key Framework Features

1. **Multi-Browser Support**: Chrome, Firefox, Edge, IE, PhantomJS
2. **Cross-Platform**: Windows, Linux support via configuration
3. **Data-Driven**: Excel (Apache POI), CSV support
4. **Database Testing**: SQL Server, PostgreSQL, Oracle support
5. **Reporting**: ExtentReports, TestNG reports, Custom Excel reports
6. **Parallel Execution**: TestNG parallel execution support
7. **CI/CD Integration**: Maven + Jenkins/CI/CD ready
8. **Retry Mechanism**: Failed test retry capability
9. **Screenshot Capture**: On failure and embedded in reports
10. **Video Recording**: Optional execution recording
11. **File Operations**: Upload/Download handling via Sikuli and AutoIT
12. **SSH/Remote Execution**: Linux server operations
13. **API Testing**: HTTP Client support
14. **Performance Monitoring**: Performance helper utilities
15. **Voice Recognition**: Sphinx4 integration
16. **Configuration Management**: Properties-based configuration
17. **Object Repository**: Centralized element locator management
18. **Listeners**: Custom TestNG listeners for enhanced reporting

### 3.3 Test Data Management

1. **Excel Workbooks**: Primary test data source
   - Separate sheets for different test scenarios
   - Test case class reads specific sheet and row
   - Format: `new Agent(path, workBookName, sheetName, "TestDataKey", rowIndex)`

2. **Properties Files**: Configuration and locators
3. **XML Files**: Suite configuration, test flows, parser configs
4. **CSV Files**: Alternative data format
5. **Database**: Direct DB queries for data validation

### 3.4 Execution Model

**Test Execution Flow**:
```
TestNG XML Suite
    ↓
PSAcceptanceTest (Base Class)
    ↓
Test Case Class (e.g., TCAgent)
    ↓
Helper Class (e.g., Agent)
    ↓
Component Helpers (TextBox, ComboBox, Grid, etc.)
    ↓
Selenium WebDriver
```

**Example from TCAgent.java**:
```java
@Test(priority = 1, description = "Agent creation", 
      retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
public void agentCreation() throws Exception {
    Agent accobj = new Agent(path, workBookName, sheetName, "Agent", 1);
    accobj.agentCreation();
}
```

### 3.5 Configuration System

**Three-Level Configuration**:
1. **Global Config** (`psconfig.properties`)
   - Deployment URLs, paths
   - Browser settings
   - Date/time formats

2. **Object Repository** (`PS_OR.properties`, `Tariff_OR.properties`)
   - Element locators
   - XPath, ID, CSS selectors

3. **Test Data** (Excel files)
   - Test-specific input data
   - Expected results
   - Test flow variations

---

## 4. TEST COVERAGE AREAS

### 4.1 ROCPS Module Coverage

The framework covers these major ROCPS functional areas:

1. **Partner Management** (40+ test classes)
   - Accounts, Agents, Franchises
   - Bill Profiles
   - Event Usage Requests

2. **Network Configuration** (30+ test classes)
   - Operators, Switches, Routes
   - TADIG Codes, Dial Strings
   - IMSI Management

3. **Tariff & Rating** (50+ test classes)
   - Tariff Configuration
   - Rate Sheets, Bands
   - Markup, Surcharge, Offer Rules
   - Floor/Ceiling Pricing

4. **Roaming** (40+ test classes)
   - TAP/RAP In/Out processing
   - Roaming Definitions, Services
   - Charge Items, Discounting
   - Taxation, File Status

5. **Billing & Invoicing** (40+ test classes)
   - Billing Cycles, Bill Profiles
   - Carrier Invoices
   - Invoice Reconciliation
   - Hot Billing

6. **Deals & Settlements** (20+ test classes)
   - Deal Creation, Import, Simulation
   - Settlements, Disputes
   - Credit Notes

7. **Aggregation & Events** (25+ test classes)
   - Event Configuration
   - Aggregation Processing
   - Event Errors & Rerate
   - Re-aggregation

8. **Quality Management** (10+ test classes)
   - Quality Rules, Metrics
   - Network Analysis
   - QoS Configuration

9. **BCR Management** (10+ test classes)
   - BCR Products, Plans
   - BCR Import & Processing

10. **Reporting & Extraction** (15+ test classes)
    - Report Definitions
    - Scheduled/Adhoc Extracts
    - XDR Extraction

11. **Reference Data** (30+ test classes)
    - Currencies, Exchange Rates
    - Banks, GL Codes
    - Traffic Types, Call Types

12. **Sales & Products** (15+ test classes)
    - Sales Offers, Proposals
    - Product Bundles
    - Taxation

13. **Accruals & Approvals** (10+ test classes)
    - Accrual Modeling
    - Approval Workflows

14. **System Administration** (20+ test classes)
    - File Upload/Management
    - Bulk Operations
    - Monitoring & Alerts

### 4.2 ROC Module Coverage

1. **ETL Processing**
2. **Audits & Measures**
3. **User Management**
4. **LDC (Large Data Collection)**
5. **Data Federation**
6. **Duplicate Check**
7. **Masking**
8. **ROCRA (ROC Revenue Assurance)**
9. **ROC View**
10. **Security**

---

## 5. DEPENDENCIES & VERSIONS

### 5.1 Core Technologies

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 1.8+ | Programming Language |
| Maven | 3.x | Build & Dependency Management |
| TestNG | 6.9.10 | Test Framework |
| Selenium WebDriver | 3.141.59 | Browser Automation |

### 5.2 Project Versions

| Project | GroupId | ArtifactId | Version |
|---------|---------|------------|---------|
| ROCPS Automation | com.subex.rocps | rocps-web-automation | 10.5.33.0 |
| ROC Automation | com.subex.roc | roc-automation | 1.0.0.1-SNAPSHOT |
| ROC Utility | com.subex.roc | roc-web-util | 1.0.0.1-SNAPSHOT |

---

## 6. SETUP & EXECUTION

### 6.1 Prerequisites

1. JDK 1.8 or later
2. Maven 3.x (in PATH)
3. Eclipse IDE with TestNG plugin
4. Browser drivers (included in plugins folder)

### 6.2 Import & Configuration Steps

1. **Import Projects**
   - Import `roc-automation-util` as Maven project
   - Import `roc-automation` as Maven project
   - Import `rocps-automation` as Maven project

2. **Configure Dependencies**
   - Right-click `roc-automation` → Properties → Project References → Select `roc-automation-util`
   - Right-click `rocps-automation` → Properties → Project References → Select both `roc-automation` and `roc-automation-util`

3. **Maven Install**
   - Run Maven install on `roc-automation-util` first
   - Then on `roc-automation`
   - Finally on `rocps-automation`

4. **Update Configuration**
   - Edit `psconfig.properties`:
     - Set `utilPath` to roc-automation-util location
     - Set `downloadDirectory`
     - Set `browser` (firefox/chrome/edge)
     - Set `clientUrl` to application URL
     - Set `deployPath` and `tomcatPath`
     - Set `dataDir` to data directory

5. **Clear Temp** (Before each execution)
   - Windows + R → type `%temp%` → Delete all files

### 6.3 Execution Methods

**Method 1: Via Eclipse TestNG**
```
Right-click XML suite file (e.g., SystemTesting_RunScript.xml)
→ Run As → TestNG Suite
```

**Method 2: Via Maven**
```powershell
mvn clean test -DtestngFilename=SystemTesting_RunScript.xml
```

**Method 3: Via Batch Script**
```powershell
PS_RunScript.bat          # For ROCPS
RunScript.bat             # For ROC
CICD_RunScript.bat        # For CI/CD
```

### 6.4 Report Locations

1. **TestNG Reports**: `rocps-automation/test-output/`
2. **ExtentReports**: Configured in `extent-config.xml`
3. **Custom Reports**: `rocps-automation/Report/`
4. **Excel Report**: `ROCPS_Automation_Report.xlsx`

---

## 7. BEST PRACTICES & CONVENTIONS

### 7.1 Naming Conventions

1. **Test Classes**: `TC` + ModuleName (e.g., `TCAgent`, `TCBilling`)
2. **Helper Classes**: ModuleName (e.g., `Agent`, `Billing`)
3. **Test Methods**: descriptive names (e.g., `agentCreation`, `agentDelete`)
4. **Excel Sheets**: Match test data keys
5. **Properties Files**: `ModuleName_OR.properties`

### 7.2 Code Structure

1. Each test class extends `PSAcceptanceTest` or `AcceptanceTest`
2. Test data path, workbook, and sheet defined as class variables
3. Each test method creates helper object with specific test data key
4. Exception handling with `FailureHelper.reportFailure(e)`
5. Retry analyzer for flaky tests

### 7.3 Test Data Pattern

```java
String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
String workBookName = "FunctionalTestCases.xlsx";
String sheetName = "Agent";

@Test(priority = 1, description = "Agent creation", 
      retryAnalyzer = Retry.class)
public void agentCreation() throws Exception {
    Agent agent = new Agent(path, workBookName, sheetName, "Agent", 1);
    agent.agentCreation();
}
```

---

## 8. KEY OBSERVATIONS & RECOMMENDATIONS

### 8.1 Framework Strengths

1. **Comprehensive Coverage**: 250+ functional test classes covering all ROCPS modules
2. **Modular Architecture**: Clean separation between util, core, and implementation
3. **Reusable Components**: Rich set of component helpers
4. **Data-Driven**: Excel-based test data management
5. **Multiple Browser Support**: Chrome, Firefox, Edge, IE
6. **CI/CD Ready**: Maven + TestNG + Jenkins integration
7. **Robust Reporting**: Multiple reporting mechanisms
8. **Retry Mechanism**: Handles flaky tests
9. **Database Support**: Multiple database validation
10. **File Operations**: Upload/Download automation

### 8.2 Areas for Enhancement

1. **Upgrade Selenium**: Currently 3.141.59, consider Selenium 4.x
2. **Upgrade TestNG**: Currently 6.9.10, latest is 7.x
3. **Add Logging**: Enhance with SLF4J + Logback
4. **API Testing**: Add RestAssured for API validation
5. **Mobile Support**: Consider Appium integration
6. **Containerization**: Docker support for parallel execution
7. **Cloud Execution**: BrowserStack/Sauce Labs integration
8. **Page Object Generator**: Tool to generate page objects
9. **Test Data Generator**: Automated test data generation
10. **Code Coverage**: Add code coverage tools

### 8.3 Missing Components (Need Verification)

1. `FunctionalTestCases.xlsx` - Referenced by 250+ test classes but not found in scan
2. Some test data files may be in external locations
3. Database connection pool configuration details

---

## 9. FRAMEWORK WORKFLOW EXAMPLE

### 9.1 Agent Creation Test Flow

```
1. TestNG reads SystemTesting_RunScript.xml
   ↓
2. Executes TCAgent.agentCreation() test method
   ↓
3. Creates Agent helper object with test data:
   - Path: project\src\main\resources\
   - Workbook: FunctionalTestCases.xlsx
   - Sheet: Agent
   - Key: "Agent"
   - Row: 1
   ↓
4. Agent.agentCreation() method:
   - Reads test data from Excel
   - Uses NavigationHelper to navigate to Agent screen
   - Uses TextBoxHelper to fill agent name
   - Uses ComboBoxHelper to select agent type
   - Uses ButtonHelper to click Save
   - Validates success message using LabelHelper
   ↓
5. Reports result to ExtentReports and TestNG
   ↓
6. Screenshot on failure (if applicable)
```

---

## 10. SUMMARY

The ROCPS Web Automation Framework is a mature, comprehensive test automation solution covering:

- **250+ Functional Test Classes** for ROCPS modules
- **40+ Functional Areas** including Roaming, Billing, Settlements, Tariffs
- **30+ Component Helpers** for UI interactions
- **Multiple Test Types**: Functional, System, Regression, Integration
- **Data-Driven Architecture**: Excel + Properties + XML
- **Multi-Browser Support**: Chrome, Firefox, Edge, IE
- **CI/CD Integration**: Maven + TestNG + Jenkins
- **Robust Reporting**: ExtentReports + TestNG + Custom Excel
- **Database Validation**: SQL Server, PostgreSQL, Oracle
- **File Operations**: Upload/Download automation
- **Remote Execution**: SSH support for Linux servers

The framework follows industry-standard design patterns (POM, Factory, Singleton) and best practices for maintainability and scalability.

---

## APPENDIX A: File Structure Tree

```
rocps_web_automation/
│
├── README.md
├── framework_structure.txt
├── ROCPS_Framework_Analysis.md (this document)
│
├── roc-automation-util/
│   ├── pom.xml
│   ├── eclipse/
│   │   ├── FirefoxTools/
│   │   └── scripts/
│   ├── eclipse-addons/
│   ├── plugins/
│   │   └── drag_and_drop_helper.js
│   └── src/main/
│       ├── java/com/subex/automation/helpers/
│       │   ├── application/
│       │   ├── component/
│       │   ├── db/
│       │   ├── report/
│       │   ├── selenium/
│       │   └── ...
│       └── resources/
│           ├── helpers.properties
│           └── extent-config.xml
│
├── roc-automation/
│   ├── pom.xml
│   ├── CICD_RunScript.bat
│   ├── CTAF_RunScript.bat
│   ├── RunScript.bat
│   ├── eclipse/scripts/
│   ├── Images/FileUpload/
│   ├── plugins/ (multiple driver versions)
│   └── src/main/
│       ├── java/com/subex/.../ (ROC implementation)
│       └── resources/
│           ├── config.properties
│           ├── *_OR.properties
│           ├── *.xml (test suites)
│           ├── Data/
│           ├── Regression/
│           └── System_Test_Flows/
│
└── rocps-automation/
    ├── pom.xml
    ├── PS_RunScript.bat
    ├── ROCPS_Automation_Report.xlsx
    ├── eclipse/scripts/
    ├── Images/FileUpload/
    ├── plugins/ (chromedriver, geckodriver, msedgedriver)
    └── src/main/
        ├── java/com/subex/rocps/automation/
        │   ├── helpers/
        │   │   ├── application/ (40+ modules)
        │   │   ├── dbscript/
        │   │   ├── listener/
        │   │   └── selenium/
        │   ├── testcases/
        │   │   ├── functionaltesting/ (250+ test classes)
        │   │   └── systemtesting/ (20+ test classes)
        │   └── utils/
        └── resources/
            ├── psconfig.properties
            ├── PS_OR.properties
            ├── Tariff_OR.properties
            ├── SystemTesting_RunScript.xml
            ├── Birt/
            ├── Data/
            │   ├── E2ECdrs/
            │   ├── FunctionalTesting/
            │   └── SystemTesting/
            ├── Diamond/
            └── TAPIN_Parse/
```

---

## APPENDIX B: Key Helper Classes Reference

### Component Helpers (roc-automation-util)
- ButtonHelper
- CheckBoxHelper
- ComboBoxHelper
- GridHelper
- TextBoxHelper
- TextAreaHelper
- RadioHelper
- LinkHelper
- LabelHelper
- TabHelper
- TreeHelper
- PropertyGridHelper
- ImageHelper
- FileUpload/FileDownload
- EntitySearchHelper
- CalendarHelper
- WindowsHelper
- CanvasHelper

### Application Helpers (roc-automation-util)
- CommonHelper
- SearchGridHelper
- ApplicationHelper
- BrowserHelper
- NavigationHelper
- DataHelper
- DatabaseHelper
- DecoderHelper
- PerformanceHelper

### ROCPS Application Helpers (rocps-automation)
- Agent, Account, Franchise, BillProfile
- EventUsageHelper, EventUsgRequestHelper
- Roaming, Billing, Settlement helpers
- Tariff, Deal, Dispute helpers
- Quality, BCR, Product helpers
- Reporting, XDR, Aggregation helpers

---

## APPENDIX C: TestNG Suite Examples

### Functional Test Suite Pattern
```xml
<suite name="ROCPS Functional Tests">
  <test name="Agent Tests">
    <classes>
      <class name="com.subex.rocps.automation.testcases.functionaltesting.TCAgent"/>
    </classes>
  </test>
</suite>
```

### System Test Suite Pattern
```xml
<suite name="ROCPS System Tests">
  <test name="Voice Stream">
    <classes>
      <class name="com.subex.rocps.automation.testcases.systemtesting.Prerequisites"/>
      <class name="com.subex.rocps.automation.testcases.systemtesting.TCVoiceStream"/>
    </classes>
  </test>
</suite>
```

---

**Document End**

*This document provides a comprehensive analysis of the ROCPS Web Automation Framework structure for future reference, maintenance, and enhancement purposes.*
