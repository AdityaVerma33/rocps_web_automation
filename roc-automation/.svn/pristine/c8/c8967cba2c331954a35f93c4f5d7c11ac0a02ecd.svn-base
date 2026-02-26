package com.subex.automation.helpers.scripts;

import java.util.HashMap;

import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class MethodHelper extends ROCAcceptanceTest {
	private static HashMap<String, Integer> methodOccurance = new HashMap<String, Integer>();
	
	public void updateMethodOccurance(String tcName) throws Exception {
		try {
			
			int occurance = 1;
			if (methodOccurance != null && methodOccurance.get(tcName) != null) {
				occurance = methodOccurance.get(tcName);
				if (occurance > 0) {
					occurance++;
					methodOccurance.remove(tcName);
				}
			}
			
			methodOccurance.put(tcName, occurance);
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public int getMethodOccurance(String tcName) throws Exception {
		try {
			
			int occurance = 1;
			if (methodOccurance != null && methodOccurance.get(tcName) != null) {
				occurance = methodOccurance.get(tcName);
			}
			
			return occurance;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void clearMethodOccurance(String tcName) throws Exception {
		try {
			
			if (methodOccurance != null && methodOccurance.get(tcName) != null) {
				methodOccurance.remove(tcName);
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void clearMethodOccurance() throws Exception {
		try {
			
			if (methodOccurance != null) {
				methodOccurance = new HashMap<String, Integer>();
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}