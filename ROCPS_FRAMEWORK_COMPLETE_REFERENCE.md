# ROCPS Web Automation Framework - Complete Analysis

**Analysis Date:** March 26, 2026  
**Framework Type:** Selenium-based Web Automation Framework for ROCPS Application  
**Build Tool:** Maven  
**Test Framework:** TestNG  

---

## Framework Structure Overview

### Root Directory Structure
```
rocps_web_automation_master/
├── roc-automation/          # Core automation utilities and base classes
├── roc-automation-util/     # Common utilities and helper classes
└── rocps-automation/        # ROCPS-specific test automation implementation
```

---

## Module Breakdown

### 1. **roc-automation**
**Purpose:** Core automation framework with base utilities

**Key Components:**
- Base test classes
- Framework-level utilities
- Build artifacts (JAR generation)
- CI/CD and execution scripts

**Build Output:** `roc-automation-1.0.0.1-SNAPSHOT.jar`

### 2. **roc-automation-util**
**Purpose:** Reusable utility layer with helper classes

**Package Structure:**
```
com.subex.automation.helpers/
├── application/          # Application-level navigation and helpers
├── component/           # UI component helpers (Grid, Button, TextBox, etc.)
├── file/                # File operations (Excel, CSV readers)
├── report/              # Logging and reporting (Log4j integration)
└── util/                # Generic utilities
```

**Key Helpers:**
- `GridHelper` - Grid operations (search, selection, validation)
- `ButtonHelper` - Button interactions
- `ComboBoxHelper` - Dropdown/combobox operations
- `TextBoxHelper` - Input field operations
- `SearchGridHelper` - Advanced grid search and filtering
- `NavigationHelper` - Screen navigation
- `GenericHelper` - Wait mechanisms and common utilities

### 3. **rocps-automation**
**Purpose:** ROCPS application-specific test implementation

**Package Structure:**
```
com.subex.rocps.automation/
├── helpers/
│   ├── application/           # Domain-specific helpers
│   │   ├── partnerConfiguration/  # Account, Partner configuration
│   │   ├── matchandrate/         # Rating and matching logic
│   │   ├── aggregation/          # Aggregation results
│   │   └── genericHelpers/       # PS-specific generic utilities
│   └── selenium/              # Selenium-specific implementations
├── testcases/
│   └── functionaltesting/     # TestNG test cases
└── utils/                     # ROCPS-specific utilities
```

---

## Account.java - Detailed Analysis

### Class: `Account.java`
**Location:** `com.subex.rocps.automation.helpers.application.partnerConfiguration.Account`

**Purpose:** Handles Account entity operations in Partner Configuration module

### Key Features:

#### 1. **Excel-Driven Design**
- Uses Excel sheets for test data
- Supports multiple test case occurrences in same sheet
- Maps Excel columns to account attributes

#### 2. **Constructor Patterns**
```java
// Basic constructor
Account(path, workBookName, sheetName, testCaseName)

// With occurrence parameter for multiple test case instances
Account(path, workBookName, sheetName, testCaseName, occurrence)
```

#### 3. **Core Methods**

| Method | Purpose | Key Operations |
|--------|---------|----------------|
| `accountCreation()` | Create new accounts | Check existence → Create if absent → Save |
| `editAccountCreation()` | Modify existing accounts | Verify existence → Edit details → Save |
| `navigateToOperator()` | Create operators from account | Navigate to account → Create operator |
| `navigateToViewAggregationResults()` | View aggregation data | Navigate to account → Open aggregation view |
| `accountChangeStatus()` | Terminate accounts | Mark for termination → Verify status |
| `accountDelete()` | Delete accounts | Verify → Delete → Check in Deleted Items |
| `accountUnDelete()` | Restore deleted accounts | Verify in Deleted → Undelete → Check in Active |
| `viewProducts()` | View account products | Navigate → View product bundles |
| `viewEventMatchRules()` | View event matching rules | Navigate → Display rules |
| `viewEventUsage()` | View event usage data | Navigate → Display usage |
| `searchScreenColumnsValidation()` | Validate grid columns | Check expected columns present |

### 4. **Instance Variables**
```java
// Excel data handling
ExcelReader excelData
Map<String, ArrayList<String>> accExcelMap
Map<String, String> accMap
ExcelHolder excelHolderObj

// Account attributes
String clientPartition
String accountName
String customerType

// Helper objects
PSGenericHelper genHelperObj
AccountActionImpl accActionObj
DataVerificationHelper verifyObj
AccountSearchImpl accSearchObj
```

---

## Design Patterns Used

### 1. **Page Object Model (POM) Pattern**
- Separation of test logic from UI interactions
- Helper classes abstract UI element interactions
- Component-based helpers (GridHelper, ButtonHelper, etc.)

### 2. **Data-Driven Testing**
- Excel sheets drive test execution
- Parameterized test data
- Support for multiple iterations

### 3. **Helper Pattern**
- Specialized helper classes for each domain
- `AccountActionImpl` - Account-specific actions
- `AccountDetailImpl` - Detail screen operations
- `AccountSearchImpl` - Search operations

### 4. **Action-Based Navigation**
- `NavigationHelper.navigateToScreen()` - Screen navigation
- `NavigationHelper.navigateToAction()` - Context menu actions
- `NavigationHelper.navigateToEdit()` - Edit mode navigation

---

## Wait Strategy

The framework uses multiple wait mechanisms:
```java
GenericHelper.waitForLoadmask()                    // Default wait
GenericHelper.waitForLoadmask(searchScreenWaitSec) // Search screen wait
GenericHelper.waitForLoadmask(detailScreenWaitSec) // Detail screen wait
GenericHelper.waitForElementToDisappear()          // Element removal wait
```

---

## Grid Operations Pattern

### Standard Grid Interaction Flow:
```java
1. Navigate to screen
2. Clear previous search (ButtonHelper.click("ClearButton"))
3. Wait for loadmask
4. Set partition filter (if applicable)
5. Search for entity (SearchGridHelper or GridHelper)
6. Verify presence (GridHelper.isValuePresent())
7. Perform action (GridHelper.clickRow() + Action)
8. Validate result
```

### Column Header Consistency Rule:
**CRITICAL:** Column headers must be consistent across:
- Search operations
- Click/selection operations  
- Verification operations

**Example (Correct Pattern):**
```java
// All use "Account Name"
isGridTextValuePresent("accountName_Detail", accountName, "Account Name")
clickDeleteOrUnDeleteAction(accountName, "Account Name", "Delete")
GridHelper.isValuePresent("SearchGrid", accountName, "Account Name")
```

---

## Common Grid Column Headers

Based on code analysis:
- **"Account Name"** - Primary account identifier column
- **"Marked For Termination"** - Termination status
- Various dynamic columns from data verification

---

## Error Handling Pattern

```java
try {
    // Test logic
    NavigationHelper.navigateToScreen("Account");
    // ... operations ...
}
catch (Exception e) {
    FailureHelper.setErrorMessage(e);
    throw e;
}
```

---

## Assertion Pattern

The framework uses TestNG assertions:
```java
assertTrue(condition, message)
assertEquals(actual, expected)
```

**Best Practice:** Always provide meaningful assertion messages for debugging.

---

## Key Framework Dependencies

### External Libraries:
- **Selenium WebDriver** - Browser automation
- **TestNG** - Test framework and assertions
- **Apache POI** (implied) - Excel file handling
- **Log4j** - Logging framework
- **Apache Commons Configuration** - Configuration management

### Internal Dependencies:
```
rocps-automation → roc-automation-util → roc-automation
```

---

## Configuration Management

### Partition Handling:
- Multi-tenant support with client partitions
- Partition selection via `selectPartitionFilter()`
- Supports both configured and runtime partitions

### Filter Views:
- "Non Deleted Items" - Active entities
- "Deleted Items" - Soft-deleted entities

---

## Test Data Structure

Excel sheet structure for Account operations:

**Required Columns:**
- `Partition` - Client partition identifier
- `AccountName` - Unique account name
- `CustomerType` - Type of customer account

**Optional Columns (method-specific):**
- `TerminateFrom`, `TerminateComment` - For termination
- `SearchScreenColumns` - For column validation
- `BillProfile`, `AggregationName` - For aggregation views
- `ColHeaders`, `Results` - For data verification
- `EventMatchRule` - For event matching

---

## Recent Issues and Fixes

### Issue #1: Account Delete Column Header Mismatch
**Date:** March 26, 2026  
**Status:** ✅ FIXED

**Problem:** Column header inconsistency causing false negative in delete verification

**Solution:** Changed column header from "Name" to "Account Name" in:
- `accountDelete()` method (lines 445, 448)
- `accountUnDelete()` method (lines 475, 478)

**Files Modified:**
- `Account.java` (lines 445, 448, 475, 478)

---

## Framework Characteristics

### Strengths:
✅ Well-structured with clear separation of concerns  
✅ Reusable helper classes  
✅ Data-driven approach with Excel integration  
✅ Comprehensive wait mechanisms  
✅ Good logging and error handling  

### Areas for Improvement:
⚠️ Log4j configuration needs to be properly set up  
⚠️ Column header references should be centralized (constants/config)  
⚠️ Some duplicate code in AccountActionImpl instantiation  
⚠️ Magic strings for column headers - consider enum or constants class  

---

## Execution Flow for Account Delete

```
1. Navigate to Account screen
2. Loop through test data rows from Excel
3. For each row:
   a. Extract partition and account name
   b. Select "Non Deleted Items" filter
   c. Search for account using "Account Name" column
   d. If found:
      - Click account row using "Account Name" column
      - Navigate to "Delete" action
      - Confirm deletion
      - Switch to "Deleted Items" filter
      - Verify account appears using "Account Name" column
      - Log success
   e. If not found:
      - Log that account is not available
```

---

## Testing Best Practices for This Framework

1. **Always clear filters** before new search operations
2. **Use explicit waits** after navigation and filter changes
3. **Verify element presence** before performing actions
4. **Maintain column header consistency** within a method
5. **Use meaningful log messages** for debugging
6. **Handle both positive and negative scenarios** (account present/absent)

---

## Framework Memory Points for Future Reference

### Navigation Pattern:
```java
NavigationHelper.navigateToScreen("ScreenName")
NavigationHelper.navigateToAction("MenuName", "ActionName")
NavigationHelper.navigateToEdit("GridId", rowNumber)
NavigationHelper.selectPartition(partitionName)
```

### Grid Pattern:
```java
GridHelper.clickRow("SearchGrid", value, "Column Header")
GridHelper.isValuePresent("SearchGrid", value, "Column Header")
GridHelper.getRowNumber("SearchGrid", value, "Column Header")
```

### Search Pattern:
```java
SearchGridHelper.gridFilterSearchWithTextBox(fieldId, value, columnHeader)
SearchGridHelper.gridFilterSearchWithComboBox(fieldId, value, columnHeader)
```

### Button Pattern:
```java
ButtonHelper.click("ButtonId")
ButtonHelper.clickIfEnabled("ButtonId")
```

### Generic Pattern:
```java
GenericHelper.waitForLoadmask()
GenericHelper.waitForLoadmask(timeoutSeconds)
```

---

## File Locations Reference

**Account Helper:**
- `rocps-automation/src/main/java/com/subex/rocps/automation/helpers/application/partnerConfiguration/Account.java`

**Account Test Case:**
- `rocps-automation/src/main/java/com/subex/rocps/automation/testcases/functionaltesting/TCAccount.java`

**Grid Helper:**
- `roc-automation-util/src/main/java/com/subex/automation/helpers/component/GridHelper.java`

**PS Generic Helper:**
- `rocps-automation/src/main/java/com/subex/rocps/automation/helpers/application/genericHelpers/PSGenericHelper.java`

---

## End of Analysis

