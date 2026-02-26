package com.subex.automation.helpers.application.rocra;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class LVRConfigurationHelper extends ROCAcceptanceTest {
	
	public void createLVRConfiguration(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Channel").size(); i++)
			{
				String [] channel = testData.getStringValue(excelData.get("Channel").get(i), firstLevelDelimiter);
				String [] service = testData.getStringValue(excelData.get("Service").get(i), firstLevelDelimiter);
				String [] callType = testData.getStringValue(excelData.get("Call Type").get(i), firstLevelDelimiter);
				String [] pulse = testData.getStringValue(excelData.get("Pulse").get(i), firstLevelDelimiter);
				String [] pulseUnit = testData.getStringValue(excelData.get("Pulse Unit").get(i), firstLevelDelimiter);
				String [] lvrRate = testData.getStringValue(excelData.get("LVR Rate").get(i), firstLevelDelimiter);
				
				createLVRConfiguration(channel, service, callType, pulse, pulseUnit, lvrRate);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createLVRConfiguration(String[] channel, String[] service, String[] callType, String[] pulse, String[] pulseUnit,
			String[] lvrRate) throws Exception {
		try {
			NavigationHelper.navigateToScreen("LVR Configuration", "LVR Configuration");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			addChannel(channel, service, callType, pulse, pulseUnit, lvrRate);	
				
			saveLVRConfiguration(detailScreenTitle);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addChannel(String[] channel, String[] service, String[] callType, String[] pulse, String[] pulseUnit, String[] lvrRate) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(channel)) {
				for (int i = 0; i < channel.length; i++ ) {
					ButtonHelper.click("LVRConfiguration_Add");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					Thread.sleep(200);
					int row = GridHelper.getRowCount("LVRConfiguration_Grid");
					
					GridHelper.updateGridComboBox("LVRConfiguration_Grid", "LVRConfiguration_Channel", row, "Channel", "Service", channel[i]);
					
					GridHelper.updateGridComboBox("LVRConfiguration_Grid", "LVRConfiguration_Service", row, "Service", "Channel", service[i]);
					
					GridHelper.updateGridComboBox("LVRConfiguration_Grid", "LVRConfiguration_CallType", row, "Call Type", "Channel", callType[i]);
					
					GridHelper.updateGridTextBox("LVRConfiguration_Grid", "LVRConfiguration_Pulse", row, "Pulse", "Channel", pulse[i]);
					
					GridHelper.updateGridComboBox("LVRConfiguration_Grid", "LVRConfiguration_PulseUnit", row, "Pulse Unit", "Channel", pulseUnit[i]);
					
					GridHelper.updateGridTextBox("LVRConfiguration_Grid", "LVRConfiguration_LVRRate", row, "LVR Rate", "Channel", lvrRate[i]);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void saveLVRConfiguration(String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			if (ButtonHelper.isPresent("YesButton")) {
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}