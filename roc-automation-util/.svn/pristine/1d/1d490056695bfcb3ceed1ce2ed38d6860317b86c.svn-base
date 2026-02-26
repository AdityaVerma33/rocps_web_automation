package com.subex.automation.helpers.util;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.report.ReportHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;

public class FailureHelper extends AcceptanceTest {

	private static boolean startFailure = false;
	
    public static Exception getRootCause(Exception e) throws Exception {
    	try {
	    	if (e != null) {
		        if (e.getCause() != null)
		            return getRootCause(e);
	    	}
	    	
	        return e;
    	} catch (Exception ex) {
			setErrorMessage(ex);
			throw ex;
		}
    }
    
    public static void failTest(String message) throws Exception {
    	try {
	    	errorMsg = message;
//			Log4jHelper.logError(errorMsg);
	    	
	    	result = "fail";
	    	ReportHelper.reportFailure();
	    	Assert.fail(message);
    	} catch(AssertionError e) {
    		setErrorMessage(e);
    		throw e;
    	} catch (Exception e) {
			setErrorMessage(e);
			throw e;
		}
    }
    
    public static void setError(String message) throws Exception {
    	try {
//	    	if (ValidationHelper.isEmpty(errorMsg)) {
				errorMsg = message;
//				Log4jHelper.logError(errorMsg);
//	    	}
    	} catch (Exception e) {
			setErrorMessage(e);
			throw e;
		}
    }
    
    public static void setErrorMessage(String[] error) throws Exception {
    	try {
    		result = "fail";
//	    	if (ValidationHelper.isEmpty(errorMsg)) {
				errorMsg = "Test case failed";
//			}
			
			if (ValidationHelper.isEmpty(stepKeys) || !stepKeys.contains("ERROR MESSAGE")) {
				ReportHelper.updateStepKey("ERROR MESSAGE", "Red", errorMsg);
			}
			
			if (ValidationHelper.isEmpty(stepKeys) || !stepKeys.contains("Show/Hide Exception")) {
				ReportHelper.addStackTrace("Red", error);
			}
    	} catch (Exception e) {
			throw e;
		}
    }
    
    private static void setError(StackTraceElement[] eStackTrace) throws Exception {
    	try {
    		Log4jHelper.logError(errorMsg);
			
			if (testReport != null) {
				if (ValidationHelper.isEmpty(stepKeys) || !stepKeys.contains("ERROR MESSAGE")) {
					ReportHelper.updateStepKey("ERROR MESSAGE", "Red", errorMsg);
				}
			}
			
			if (ValidationHelper.isEmpty(stepKeys) || !stepKeys.contains("Show/Hide Exception")) {
				String[] stackTrace = new String[eStackTrace.length];
				for (int i = 0; i < eStackTrace.length; i++) {
					stackTrace[i] = eStackTrace[i].toString();
					Log4jHelper.logError(stackTrace[i]);
				}
				
				if (testReport != null)
					ReportHelper.addStackTrace("Red", stackTrace);
			}
    	} catch (Exception ex) {
			throw ex;
		}
    }
    
    public static void setErrorMessage(Exception e) throws Exception {
    	try {
    		StackTraceElement[] eStackTrace = null;
    		if (e.getCause() != null)
    			eStackTrace = e.getCause().getStackTrace();
    		else
    			eStackTrace = e.getStackTrace();
	    	String message = e.getMessage();
	    	result = "fail";
	    	
			if (ValidationHelper.isNotEmpty(message))
				errorMsg = message;
			else {
				String eMessage = getRootCause(e).getMessage();
				if (ValidationHelper.isNotEmpty(eMessage))
					errorMsg = getRootCause(e).getMessage();
				else if (ValidationHelper.isEmpty(errorMsg))
					errorMsg = "Test case failed";
			}
			
			setError(eStackTrace);
    	} catch (Exception ex) {
			throw ex;
		}
    }
    
    public static void setErrorMessage(AssertionError e) throws Exception {
    	try {
    		StackTraceElement[] eStackTrace = null;
    		if (e.getCause() != null)
    			eStackTrace = e.getCause().getStackTrace();
    		else
    			eStackTrace = e.getStackTrace();
	    	String message = e.getMessage();
	    	result = "fail";
	    	
			if (ValidationHelper.isNotEmpty(message))
				errorMsg = message;
			else if (ValidationHelper.isEmpty(errorMsg))
				errorMsg = "Test case failed";
				
			setError(eStackTrace);
    	} catch (Exception ex) {
			throw ex;
		}
    }
    
    public static void setErrorMessage(StackOverflowError e) throws Exception {
    	try {
    		StackTraceElement[] eStackTrace = null;
    		if (e.getCause() != null)
    			eStackTrace = e.getCause().getStackTrace();
    		else
    			eStackTrace = e.getStackTrace();
	    	String message = e.getMessage();
	    	result = "fail";
	    	
			if (ValidationHelper.isNotEmpty(message))
				errorMsg = message;
			else if (ValidationHelper.isEmpty(errorMsg))
				errorMsg = "Test case failed";
				
			setError(eStackTrace);
    	} catch (Exception ex) {
			throw ex;
		}
    }
    
    public static void reportFailure(Exception e) throws Exception {
    	try {
			if (testReport == null) {
				if (testCaseName == null) {
					if (ValidationHelper.isNotEmpty(flow))
						testCaseName = flow;
					else
						testCaseName = "Automation";
				}
				
				testReport = ReportHelper.startReport(report, testCaseName);
			}
	    	
			if (testReport != null) {
				setErrorMessage(e);
				ReportHelper.reportFailure(e);
				testReport = ReportHelper.endReport(report, testReport);
			}
			
			result = "fail";
			startFailure = true;
//			throw new SkipException("Execution failed");
	    } catch (Exception ex) {
			throw ex;
		}
    }
    
    public static boolean getStartFailure() {
    	return startFailure;
    }
    
    public static void handleClickException(WebElement element) throws Exception {
		try {
			ElementHelper.scrollToView(element, false);
			Actions action = new Actions(driver);
			action.click(element).build().perform();
		} catch (WebDriverException ex) {
			setError("Element '" + element.toString() + "' is not clickable");
			setErrorMessage(ex);
			throw ex;
		} catch (Exception e) {
			setErrorMessage(e);
			throw e;
		}
	}
    
    public static void handleClickException(String xpath, WebElement element) throws Exception {
		try {
			ElementHelper.scrollToView(element, false);
			Actions action = new Actions(driver);
			action.click(element).build().perform();
		} catch (WebDriverException ex) {
			setError("Element '" + xpath + "' is not clickable");
			setErrorMessage(ex);
			throw ex;
		} catch (Exception e) {
			setErrorMessage(e);
			throw e;
		}
	}
    
    public static void handleDoubleClickException(WebElement element) throws Exception {
		try {
			ElementHelper.scrollToView(element, false);
			Actions action = new Actions(driver);
			action.doubleClick(element).build().perform();
		} catch (WebDriverException ex) {
			setError("Element '" + element.toString() + "' is not clickable");
			setErrorMessage(ex);
			throw ex;
		} catch (Exception e) {
			setErrorMessage(e);
			throw e;
		}
	}
}