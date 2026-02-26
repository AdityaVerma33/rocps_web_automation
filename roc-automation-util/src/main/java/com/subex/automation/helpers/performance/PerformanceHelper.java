package com.subex.automation.helpers.performance;

import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class PerformanceHelper extends AcceptanceTest {
	public static void generateLog() throws Exception {
		try {
			HarReader.generateHar();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void generateLog(String fileName) throws Exception {
		try {
			HarReader.generateHar(fileName);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void cleanLog() throws Exception {
		try {
			HarReader.clearHAR();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}