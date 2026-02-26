package com.subex.rocps.automation.helpers.listener;

import static com.subex.automation.helpers.selenium.AcceptanceTest.report;
import static com.subex.automation.helpers.selenium.AcceptanceTest.stepKeys;
import static com.subex.automation.helpers.selenium.AcceptanceTest.stepName;
import static com.subex.automation.helpers.selenium.AcceptanceTest.testReport;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.ReportHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;

public class PSCustomTestListenerAdapter extends TestListenerAdapter implements ITestListener {
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
			PSGenericHelper.takeSnapShot( "skip",ROCAcceptanceTest.testCaseName, result.getName());
			AcceptanceTest.driver.navigate().refresh();
			
			/*AcceptanceTest.driver.quit();
			AcceptanceTest acceptanceTest=new AcceptanceTest();
			acceptanceTest.startWebDriver();
			LoginHelper login = new LoginHelper();
            login.loginWithConfigPropertyDetails();
            */
            
			Thread.sleep(1000); 
	    	GenericHelper.waitForLoadmask(120);
//			 ButtonHelper.click("//div[@id='Home']"); 
//			  GenericHelper.waitForLoadmask();
//			  if(PopupHelper.isPresent("Popup_Panel"))
//			  {
//				 ButtonHelper.click("YesButton");
//				 Thread.sleep(1000);
//				 GenericHelper.waitForLoadmask();
//			  //ButtonHelper.click("//div[@id='Home']"); 
//			  //GenericHelper.waitForLoadmask();
//			  }
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
				/*
				 * if (!AcceptanceTest.suiteName.equals("CICD")) { ROCHelper rocHelper = new
				 * ROCHelper(); rocHelper.handleFailures(); } else if
				 * (AcceptanceTest.suiteName.equals("CICD") &&
				 * AcceptanceTest.isInstallationDone) { ROCHelper rocHelper = new ROCHelper();
				 * rocHelper.handleFailures(); }
				 */
		    	if (testReport != null)
		    		testReport = ReportHelper.endReport(report, testReport);
		    	PSGenericHelper.takeSnapShot( "fail",ROCAcceptanceTest.testCaseName, result.getName());
		    	AcceptanceTest.driver.navigate().refresh();
		    	
		    	/*AcceptanceTest.driver.quit();
				AcceptanceTest acceptanceTest=new AcceptanceTest();
				acceptanceTest.startWebDriver();
				LoginHelper login = new LoginHelper();
	            login.loginWithConfigPropertyDetails();
	            */
		    	Thread.sleep(1000); 
		    	GenericHelper.waitForLoadmask(120);
				
				/*
				 * ButtonHelper.click("//div[@id='Home']"); GenericHelper.waitForLoadmask();
				 * if(PopupHelper.isPresent("Popup_Panel")) { ButtonHelper.click("YesButton");
				 * Thread.sleep(1000); GenericHelper.waitForLoadmask(); //
				 * ButtonHelper.click("//div[@id='Home']"); //GenericHelper.waitForLoadmask(); }
				 */
				 
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
