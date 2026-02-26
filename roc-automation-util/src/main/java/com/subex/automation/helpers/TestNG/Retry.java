package com.subex.automation.helpers.TestNG;

import java.util.concurrent.atomic.AtomicInteger;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.subex.automation.helpers.config.PropertyReader;
import com.subex.automation.helpers.report.Log4jHelper;

public class Retry implements IRetryAnalyzer {
	public static PropertyReader configProp = null;
	private int retryCount = 0;
    private int maxRetryCount = 2;
    public int retryCountValue = 0;
    
    AtomicInteger count = new AtomicInteger(maxRetryCount);
    
// Below method returns 'true' if the test method has to be retried else 'false' 
//and it takes the 'Result' as parameter of the test method that just ran
    public boolean retry(ITestResult result) {
        if (isRetryAvailable()) {
        	try {
        		configProp = new PropertyReader("config.properties");
				Log4jHelper.logInfo("Retrying test " + result.getName() + " with status "
				        		+ getResultStatusName(result.getStatus()) + " for the " + (retryCount+1) + " time(s).");
			} catch (Exception e) {
				e.printStackTrace();
			}
            retryCount++;
            retryCountValue = retryCount;
            count.decrementAndGet();
            return true;
        }
        
        return false;
    }
    
    public String getResultStatusName(int status) {
    	String resultName = null;
    	if(status==1)
    		resultName = "SUCCESS";
    	if(status==2)
    		resultName = "FAILURE";
    	if(status==3)
    		resultName = "SKIP";
    	
		return resultName;
    }

	public boolean isRetryAvailable() {
		 return (count.intValue() > 0);
	}
	
	public int retryCount() {
		return retryCount;
	}
}