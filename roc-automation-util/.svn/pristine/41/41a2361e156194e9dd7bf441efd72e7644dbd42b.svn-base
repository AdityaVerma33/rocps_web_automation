package com.subex.automation.helpers.scripts;

import com.subex.automation.helpers.data.StringHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.ReportHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class ScriptHelper extends AcceptanceTest {
	
	public static void performAction(String component, String compWrapper, String compIdOrText, String componentAction, String[] values) throws Exception {
		try {
			boolean noValue = false;
			String[] methodParameters = generateParams(compWrapper, compIdOrText, values);
			if (ValidationHelper.isEmpty(values) || values[0].equals(""))
				noValue = true;
			
			InvokeMethodHelper.callAction(component, componentAction, methodParameters, noValue);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String[] generateParams(String compWrapper, String compIdOrText, String[] values) throws Exception {
		try {
			String[] methodParameters = new String[values.length+2];
			String[] params = {compWrapper, compIdOrText};
			int length = 0;
			
			if (params != null && params.length > 0 && params[0] != null) {
				for (int i = 0; i < params.length; i++) {
					if (ValidationHelper.isNotEmpty(params[i])) {
						methodParameters[length] = params[i];
						length++;
					}
				}
			}
			
			if (ValidationHelper.isNotEmpty(values)) {
				for (int i = 0; i < values.length; i++) {
					if (ValidationHelper.isNotEmpty(values[i])) {
						methodParameters[length] = values[i];
						length++;
					}
				}
			}
			
			if (length > 0)
				methodParameters = StringHelper.resizeStringArray(methodParameters, length);
			else
				methodParameters = null;
			
			return methodParameters;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void updateReportParams(String component, String compWrapper, String compIdOrText, String componentAction, String[] values) throws Exception {
		try {
			
			if (ValidationHelper.isNotEmpty(component))
				ReportHelper.updateStepKey("Component", "Blue", component);
			
			if (ValidationHelper.isNotEmpty(compWrapper))
				ReportHelper.updateStepKey("Wrapper ID", "Blue", compWrapper);
				
			if (ValidationHelper.isNotEmpty(compIdOrText))
				ReportHelper.updateStepKey("ID/Text/Xpath", "Blue", compIdOrText);
				
			if (ValidationHelper.isNotEmpty(componentAction))
				ReportHelper.updateStepKey("Component Action", "Blue", componentAction);
			
			if (ValidationHelper.isNotEmpty(values)) {
				for (int i = 0; i < values.length; i++) {
					if (ValidationHelper.isNotEmpty(values[i])) {
						ReportHelper.updateStepKey("Value", "Blue", values[i]);
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}