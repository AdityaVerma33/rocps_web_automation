# Account Delete Issue - Root Cause Analysis & Fix

## Issue Summary
**Date:** March 26, 2026  
**Test Case:** TCAccount.accountDelete()  
**Account Name:** HomeCarrier  
**Error Location:** Account.java:448

## Error Details
```
java.lang.AssertionError: HomeCarrier
Expected: true
Actual: false
```

## Root Cause

The failure occurred due to **inconsistent column header references** in the `accountDelete()` method.

### Problem Flow:

1. **Line 442** - Initial Search:
   ```java
   boolean isAccountPresent = genHelperObj.isGridTextValuePresent( 
       "accountName_Detail", accountName, "Account Name" );
   ```
   ✅ Uses column header: **"Account Name"**

2. **Line 445** - Delete Action (BEFORE FIX):
   ```java
   genHelperObj.clickDeleteOrUnDeleteAction( accountName, "Name", "Delete" );
   ```
   ❌ Uses column header: **"Name"** (INCONSISTENT)

3. **Line 448** - Verification (BEFORE FIX):
   ```java
   assertTrue( GridHelper.isValuePresent( "SearchGrid", accountName, "Name" ), accountName );
   ```
   ❌ Uses column header: **"Name"** (INCONSISTENT)

### Why It Failed:

After the delete operation, the code switches to the "Deleted Items" view and tries to verify the account is present using the column header **"Name"**. However, the grid column is actually named **"Account Name"**, so the search fails and returns `false`, causing the assertion to fail.

## The Fix Applied

Changed lines 445 and 448 in the `accountDelete()` method to use consistent column header **"Account Name"**:

```java
// Line 445 - FIXED
genHelperObj.clickDeleteOrUnDeleteAction( accountName, "Account Name", "Delete" );

// Line 448 - FIXED
assertTrue( GridHelper.isValuePresent( "SearchGrid", accountName, "Account Name" ), accountName );
```

Similarly fixed the `accountUnDelete()` method for consistency:

```java
// Line 475 - FIXED
genHelperObj.clickDeleteOrUnDeleteAction( accountName, "Account Name", "Undelete" );

// Line 478 - FIXED
assertTrue( GridHelper.isValuePresent( "SearchGrid", accountName, "Account Name" ), accountName );
```

## Framework Pattern Analysis

This framework follows a pattern where:
- Column headers must be consistent across search, action, and verification operations
- The grid column name is **"Account Name"** (not "Name")
- Helper methods like `isGridTextValuePresent()` and `isValuePresent()` use column headers to locate elements

## Prevention Guidelines

1. **Always use consistent column headers** throughout a method
2. **Verify actual column names** in the UI grid before writing automation code
3. **Follow existing patterns** in similar methods (e.g., `accountCreation()` uses "Account Name")
4. **Add wait statements** after filter/view changes to ensure grid loads properly

## Testing Recommendation

After this fix:
1. Re-run the `accountDelete()` test case
2. Verify "HomeCarrier" account is successfully found in "Deleted Items" view
3. Validate the assertion passes with `Expected: true, Actual: true`

## Additional Notes

The log4j warning at the start is unrelated to this issue and indicates missing log4j configuration file. It can be addressed separately by:
- Adding log4j.properties or log4j.xml to the classpath
- Configuring proper appenders for logging

