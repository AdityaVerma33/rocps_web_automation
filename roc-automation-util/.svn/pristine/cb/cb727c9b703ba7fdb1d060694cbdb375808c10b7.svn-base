package com.subex.automation.helpers.TestNG;

import static com.subex.automation.helpers.selenium.AcceptanceTest.report;
import static com.subex.automation.helpers.selenium.AcceptanceTest.stepName;
import static com.subex.automation.helpers.selenium.AcceptanceTest.testReport;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.report.ReportHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class CustomInvokedMethodListener implements IInvokedMethodListener {
	
	@Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
//		AcceptanceTest.result = "unknown";
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
    	if (ITestResult.FAILURE == result.getStatus()) {
    		AcceptanceTest.result = "fail";
    		Throwable throwable = result.getThrowable();
    		if (stepName.equals(""))
				stepName = method.getTestMethod().getMethodName();
    		
    		try {
    			if (throwable instanceof Exception) {
	    			Exception e = (Exception) throwable;
	    			FailureHelper.setErrorMessage(e);
	    			ReportHelper.reportFailure(e);
    			}
    			else if (throwable instanceof AssertionError) {
    				AssertionError e = (AssertionError) throwable;
	    			FailureHelper.setErrorMessage(e);
	    			ReportHelper.reportFailure(e);
    			}
    			else if (throwable instanceof StackOverflowError) {
    				StackOverflowError e = (StackOverflowError) throwable;
	    			FailureHelper.setErrorMessage(e);
	    			ReportHelper.reportFailure(e);
    			}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
    	    	try {
    	    		if (!AcceptanceTest.suiteName.equals("CICD")) {
	    	    		ROCHelper rocHelper = new ROCHelper();
						rocHelper.handleFailures();
    	    		} else if (AcceptanceTest.suiteName.equals("CICD") && AcceptanceTest.isInstallationDone) {
    	    			ROCHelper rocHelper = new ROCHelper();
						rocHelper.handleFailures();
    	    		}
    	    		
					if (testReport != null)
						testReport = ReportHelper.endReport(report, testReport);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
	    }
    	else {
    		if (result.getStatus() == 1)
        		AcceptanceTest.result = "pass";
        	else if (result.getStatus() == 2)
    			AcceptanceTest.result = "fail";
        	else if (result.getStatus() == 3)
        		AcceptanceTest.result = "skip";
    	}
	}
}