package com.subex.automation.helpers.TestNG;

import static com.subex.automation.helpers.selenium.AcceptanceTest.report;
import static com.subex.automation.helpers.selenium.AcceptanceTest.stepKeys;
import static com.subex.automation.helpers.selenium.AcceptanceTest.stepName;
import static com.subex.automation.helpers.selenium.AcceptanceTest.testReport;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.ReportHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class CustomTestListenerAdapter extends TestListenerAdapter implements ITestListener {
	
	@Override
	public void onTestSkipped(ITestResult result) {
		try {
			boolean startFailure = FailureHelper.getStartFailure();
			stepName = result.getMethod().getMethodName();
			AcceptanceTest.result = "skip";
			AcceptanceTest.totalSkipped++;
			
			if (startFailure)
				ReportHelper.reportSkip("Skipping Execution due to start failure");
			else
				ReportHelper.reportSkip("Skipping Test Case as dependent test case failed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    @Override
    public void onTestFailure(ITestResult result) {
    	try {
    		Throwable throwable = result.getThrowable();
    		AcceptanceTest.result = "fail";
    		AcceptanceTest.totalFail++;
    		
    		if (!throwable.toString().contains("Assert") || !throwable.toString().contains("AssertionError")) {
	    		if (ValidationHelper.isEmpty(stepKeys) || !stepKeys.contains("ERROR MESSAGE")) {
	    			Exception e = (Exception) throwable;
	    			FailureHelper.setErrorMessage(e);
	    			ReportHelper.reportFailure(e);
	    		}
    		}
		} catch (Exception e) {
			try {
				FailureHelper.setErrorMessage(e);
				ReportHelper.reportFailure(e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
    	finally {
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    	AcceptanceTest.result = "pass";
    	AcceptanceTest.totalPass++;
    }
    
	@Override
    public void onFinish(ITestContext context) {

    }
}