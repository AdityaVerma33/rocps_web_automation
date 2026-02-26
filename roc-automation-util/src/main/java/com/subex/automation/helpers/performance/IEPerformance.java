package com.subex.automation.helpers.performance;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;

import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class IEPerformance extends AcceptanceTest {
	private static JavascriptExecutor jsExecutor;
//	private static PerformanceReportHelper prh = null;
	
    private static long navigationStart;
//    private static long domContentLoadedEventEnd;
    private static long loadEventEnd;
    
	public static void getPerformance(JavascriptExecutor executor, String screen, String fileName) throws Exception {
    	try {
    		jsExecutor = executor;
//    		prh = new PerformanceReportHelper();

    		getPerformance(screen, fileName);
	        updateReportFile(screen);
    	} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
    }
	
	public static void clearPerformance(JavascriptExecutor executor) throws Exception {
		try {
			jsExecutor = executor;
			String script = "var supported = typeof performance.clearResourceTimings == \"function\"; "
							+ "if (supported) {"
							+ " console.log(\"Run: performance.clearResourceTimings()\");"
							+ " performance.clearResourceTimings();"
							+ " } else {"
							+ "  console.log(\"performance.clearResourceTimings() NOT supported\");"
							+ " return;"
							+ "  }";

			if((Boolean) executor.executeScript("return performance != undefined")) {
				jsExecutor.executeScript(script);
				jsExecutor.executeScript("performance.clearMeasures();");
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
    private static long getTime(String key) throws Exception {
    	try {
			
    		String script = "var performance = window.performance || window.webkitPerformance || window.mozPerformance || window.msPerformance || {}; var timings = performance.timing." + key + " || {};return timings;";
			Object timing = (Object) jsExecutor.executeScript(script);
			if (timing == null)
				timing = (long) 0;
    		
			return (long) timing;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
    }
    
    private static void updateReportFile(String screen) throws Exception {
    	try {
//	        String href = (String) jsExecutor.executeScript("return window.location.href;");
//		    Double contentLoadTime = (double) (domContentLoadedEventEnd - navigationStart);
		    
//    		Double fullLoadTime = (double) (loadEventEnd - navigationStart);
//		    String timeTaken = prh.convertTime( ( fullLoadTime / 1000 ) );
//		    Object[] summary = {screen, timeTaken};
//		    int count = 1;
//			if (performanceSummary != null && performanceSummary.size() > 0)
//				count = performanceSummary.size();
//			performanceSummary.put(count, summary);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
    }
    
    private static void getPerformance(String screen, String fileName) throws Exception {
		try {
			ArrayList<String> content = new ArrayList<String>();
			content.add("Screen," + screen);
			content.add("\n");
			
			String[] keys = {"unloadEventStart", "unloadEventEnd", "redirectStart", "redirectEnd", "fetchStart",
						"domainLookupStart", "domainLookupEnd", "connectStart", "connectEnd", "secureConnectionStart",
						"requestStart", "responseStart", "responseEnd", "domLoading", "domInteractive",
						"domContentLoadedEventStart", "domContentLoadedEventEnd", "domComplete", "loadEventStart"};
			
			navigationStart = getTime("navigationStart");
			content.add( "navigationStart," + String.valueOf(navigationStart));
			content.add("\n");
			
//			domContentLoadedEventEnd = getTime("domContentLoadedEventEnd");
//			loadEventStart = getTime("loadEventStart");
			
			for (int i = 0; i < keys.length; i++) {
				long value = getTime(keys[i]);
				content.add( keys[i] + "," + String.valueOf(value));
				content.add("\n");
			}
			
			loadEventEnd = getTime("loadEventEnd");
			content.add( "loadEventEnd," + String.valueOf(loadEventEnd));
			content.add("\n");
			
			String path = GenericHelper.getPath(automationOS, configProp.getDownloadDirectory() + "\\Performance_Report\\");
			FileHelper.writeToFile(GenericHelper.getPath(automationOS, path + fileName + ".csv"), content);
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}