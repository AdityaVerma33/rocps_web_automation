# ROCPS Automation Framework - Quick Reference Card

**Version:** 10.5.33.0 | **Date:** February 26, 2026 | **Status:** ✅ Ready for Use

---

## 🚀 INSTANT START

### 1. BUILD ALL PROJECTS (One-Time Setup)

```powershell
# Copy-paste this entire block
$env:Path += ";C:\Program Files\apache-maven-3.9.12\bin"
cd C:\Users\aditya.verma\rocps_web_automation_master\rocps_web_automation\roc-automation-util
mvn clean install -DskipTests
cd ..\roc-automation
mvn clean install -DskipTests
cd ..\rocps-automation
mvn clean install -DskipTests
Write-Host "✅ ALL BUILDS COMPLETE!" -ForegroundColor Green
```

### 2. RUN FUNCTIONAL TESTS

```powershell
cd C:\Users\aditya.verma\rocps_web_automation_master\rocps_web_automation\rocps-automation
mvn test -Dtestng.filename=FunctionalTesting_RunScript.xml
```

### 3. VIEW REPORTS

```powershell
Start-Process "test-output\index.html"
```

---

## 📁 PROJECT STRUCTURE

```
rocps_web_automation/
├── roc-automation-util/      ← Build FIRST (Base framework)
├── roc-automation/            ← Build SECOND (ROC helpers)
└── rocps-automation/          ← Build THIRD (Test cases)
```

---

## ⚡ ESSENTIAL COMMANDS

### Build Commands

| Command | Purpose |
|---------|---------|
| `mvn clean install -DskipTests` | Build & install to local repo |
| `mvn clean` | Clean target directory |
| `mvn compile` | Compile only |
| `mvn package` | Create JAR file |

### Test Execution

| Command | Purpose |
|---------|---------|
| `mvn test` | Run all tests |
| `mvn test -Dtest=TCAgent` | Run specific test class |
| `mvn test -Dtest=TCAgent#agentCreation` | Run specific test method |
| `mvn test -Dtestng.filename=FunctionalTesting_RunScript.xml` | Run specific suite |

### Browser Selection

| Command | Browser |
|---------|---------|
| `mvn test -Dbrowser=firefox` | Firefox |
| `mvn test -Dbrowser=chrome` | Chrome |
| `mvn test -Dbrowser=ie` | Internet Explorer |

---

## 📝 CRITICAL FILES TO UPDATE

### Before Running Tests:

1. **psconfig.properties** (`rocps-automation/src/main/resources/`)
   ```properties
   clientUrl=http://YOUR_SERVER:8080/rocps_automate_10.5.55
   applicationUsername=YOUR_USERNAME
   applicationPassword=YOUR_PASSWORD
   browser=firefox
   ```

2. **FunctionalTestCases.xlsx** (`rocps-automation/src/main/resources/`)
   - Update test data as needed

3. **PS_OR.properties** (`rocps-automation/src/main/resources/`)
   - Update element locators if UI changes

---

## 🎯 COMMON TASKS

### Task 1: Create New Test Case

```java
// Location: rocps-automation/src/main/java/com/subex/rocps/automation/testcases/functionaltesting/
public class TCNewFeature extends PSAcceptanceTest {
    
    String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
    String workBookName = "FunctionalTestCases.xlsx";
    String sheetName = "NewFeature";
    
    @Test(priority = 1, description = "Test description",
          retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
    public void testNewFeature() throws Exception {
        try {
            // Your test code
        } catch (Exception e) {
            FailureHelper.reportFailure(e);
            throw e;
        }
    }
}
```

### Task 2: Add to TestNG Suite

```xml
<!-- Location: rocps-automation/src/main/resources/FunctionalTesting_RunScript.xml -->
<class name="com.subex.rocps.automation.testcases.functionaltesting.TCNewFeature">
    <methods>
        <include name="testNewFeature" />
    </methods>
</class>
```

### Task 3: Run Your New Test

```powershell
mvn test -Dtest=TCNewFeature#testNewFeature
```

---

## 🐛 QUICK TROUBLESHOOTING

| Problem | Solution |
|---------|----------|
| **Build fails** | Build in order: util → automation → rocps |
| **Dependency error** | Run `mvn clean install -DskipTests` in base project |
| **Browser not launching** | Check `browser=firefox` in `psconfig.properties` |
| **Element not found** | Add wait: `WaitHelper.waitForElement(driver, element, 30)` |
| **Config not loaded** | Verify `<parameter name="config" value="psconfig.properties">` in TestNG XML |
| **Excel not found** | Check path: `src/main/resources/FunctionalTestCases.xlsx` |

---

## 📊 FRAMEWORK COMPONENTS

### Helper Classes (Use in your tests)

| Helper | Purpose | Example |
|--------|---------|---------|
| `TextBoxHelper` | Text input | `TextBoxHelper.enterText(element, "value")` |
| `ComboBoxHelper` | Dropdowns | `ComboBoxHelper.selectByText(element, "option")` |
| `ButtonHelper` | Buttons | `ButtonHelper.click(element)` |
| `GridHelper` | Tables | `GridHelper.getCellValue(grid, row, col)` |
| `WaitHelper` | Waits | `WaitHelper.waitForElement(driver, element, 30)` |

### Test Base Classes

```
AcceptanceTest           ← WebDriver lifecycle
    └── ROCAcceptanceTest    ← Config/OR loading
            └── PSAcceptanceTest   ← ROCPS context
                    └── Your Test Classes
```

---

## 📈 REPORTING

### Report Locations

| Report Type | Location |
|------------|----------|
| **TestNG HTML** | `test-output/index.html` |
| **Emailable Report** | `test-output/emailable-report.html` |
| **ExtentReports** | `Report/Run1/ExtentReport.html` |
| **XML Results** | `test-output/testng-results.xml` |
| **Failed Tests** | `test-output/testng-failed.xml` |

### Re-run Failed Tests

```powershell
mvn test -Dtestng.filename=test-output/testng-failed.xml
```

---

## 🔑 KEY DIRECTORIES

| Directory | Purpose |
|-----------|---------|
| `src/main/java/.../testcases/functionaltesting/` | Functional test cases |
| `src/main/java/.../testcases/systemtesting/` | System test cases |
| `src/main/java/.../helpers/application/` | Helper classes |
| `src/main/resources/` | Config, OR, test data |
| `src/main/resources/Data/` | Test data files |
| `test-output/` | Test execution reports |
| `Report/` | ExtentReports |
| `target/` | Compiled classes & JARs |

---

## 🎓 BEST PRACTICES

✅ Always build in dependency order: **util → automation → rocps**  
✅ Update `psconfig.properties` before running tests  
✅ Use retry analyzer for flaky tests  
✅ Capture screenshots on failure  
✅ Use meaningful test descriptions  
✅ Clean build before committing: `mvn clean install`  
✅ Run smoke tests before full regression  
✅ Review reports after execution  

---

## 📚 DOCUMENTATION

| Document | Purpose |
|----------|---------|
| `COMPLETE_FRAMEWORK_ANALYSIS.md` | Comprehensive framework documentation |
| `BUILD_AND_EXECUTION_GUIDE.md` | Detailed build & execution instructions |
| `automation_constitution.md` | Framework standards & guidelines |
| `README.md` | Setup & getting started |
| `QUICK_REFERENCE.md` | This document |

---

## 🎯 SUCCESS CHECKLIST

After setup, verify:

- [ ] All 3 projects built successfully
- [ ] `mvn test` runs without errors
- [ ] Reports are generated in `test-output/`
- [ ] Screenshots captured on failure
- [ ] Can run individual test: `mvn test -Dtest=TCAgent#agentCreation`
- [ ] Browser launches correctly

---

## 💡 PRO TIPS

1. **Faster builds:** Use `mvn install -DskipTests` (skip clean)
2. **Debug mode:** Add `-X` flag: `mvn test -X`
3. **Offline mode:** Use `mvn -o test` (no dependency check)
4. **Parallel execution:** Update TestNG XML: `<suite parallel="tests" thread-count="5">`
5. **Custom wait time:** Set in `psconfig.properties`
6. **Video recording:** Set `recordExecution=Yes` in config

---

## 🚨 EMERGENCY COMMANDS

### If everything breaks:

```powershell
# 1. Clean everything
cd roc-automation-util; mvn clean
cd ..\roc-automation; mvn clean
cd ..\rocps-automation; mvn clean

# 2. Delete local Maven cache (nuclear option)
Remove-Item -Recurse -Force $env:USERPROFILE\.m2\repository\com\subex

# 3. Rebuild from scratch
cd roc-automation-util; mvn clean install -DskipTests -U
cd ..\roc-automation; mvn clean install -DskipTests -U
cd ..\rocps-automation; mvn clean install -DskipTests -U
```

---

## 📞 QUICK HELP

**Maven not found?**
```powershell
$env:Path += ";C:\Program Files\apache-maven-3.9.12\bin"
```

**Java not found?**
```powershell
$env:JAVA_HOME = "C:\Program Files\Java\openlogic-openjdk-11.0.23+9-windows-x64"
$env:Path += ";$env:JAVA_HOME\bin"
```

**Check versions:**
```powershell
mvn --version
java -version
```

---

## 🎉 YOU'RE READY!

**Framework Status:** ✅ Fully Operational  
**Last Built:** February 26, 2026  
**Build Status:** All 3 projects built successfully  

**Start testing:**
```powershell
cd rocps-automation
mvn test -Dtestng.filename=FunctionalTesting_RunScript.xml
```

---

**Keep this card handy for daily work!**

**For detailed information, see:** `COMPLETE_FRAMEWORK_ANALYSIS.md` or `BUILD_AND_EXECUTION_GUIDE.md`

