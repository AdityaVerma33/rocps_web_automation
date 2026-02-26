package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class RecurringTaskHelper extends ROCAcceptanceTest {
	
	public void createRecurringTask( String path, String workBookName, String workSheetName, String testCaseName, int occurence ) throws Exception
	{
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
			
			for ( int i = 0; i < excelData.get( "Name" ).size(); i++ )
			{
				String partition = excelData.get( "Partition" ).get( i );
				String stream = excelData.get( "Stream" ).get( i );
				String streamStage = excelData.get( "Stream Stage" ).get( i );
				String name = excelData.get( "Name" ).get( i );
				
				String priority = excelData.get( "Priority" ).get( i );
				boolean reuseExistingTask = ValidationHelper.isTrue(excelData.get( "Reuse Existing Task" ).get( i ));
				boolean runTaskIgnoringFailure = ValidationHelper.isTrue(excelData.get( "Run Task Ignoring Failure" ).get( i ));
				
				String frequencyMultiplier = excelData.get("Frequency Multiplier").get(i);
				String frequency = excelData.get("Frequency").get(i);
				String nextSchedule = excelData.get("Next Schedule").get(i);
				String[][] dayGroups = testData.getStringValue(excelData.get("Day Groups").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				createRecurringTask(partition, stream, streamStage, name, priority, reuseExistingTask, runTaskIgnoringFailure,
						frequencyMultiplier, frequency, nextSchedule, dayGroups);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createRecurringTask( String partition, String stream, String streamStage, String name, String priority, boolean reuseExistingTask,
			boolean runTaskIgnoringFailure, String frequencyMultiplier, String frequency, String nextSchedule, String[][] dayGroups ) throws Exception
	{
		try {
			int rowNo = navigateToRecurringTask(name);
			
			if (rowNo == 0) {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "RecurringTask_Name");
				
				ComboBoxHelper.select( "RecurringTask_Stream", stream );
				ComboBoxHelper.select( "RecurringTask_StreamStage", streamStage );
				TextBoxHelper.type( "RecurringTask_Name", name);
				ComboBoxHelper.select( "RecurringTask_Priority", priority );
				
				if ( reuseExistingTask )
					CheckBoxHelper.check( "RecurringTask_ReuseExistingTask" );
				if ( runTaskIgnoringFailure )
					CheckBoxHelper.check( "RecurringTask_RunTaskIgnoringFailure" );
				
				ROCHelper rocHelper = new ROCHelper();
				rocHelper.updateCollectionTimes(frequencyMultiplier, frequency, nextSchedule, dayGroups);
				
				saveRecurringTask(name, detailScreenTitle);
			}
			else {
				Log4jHelper.logInfo("Recurring Task with name '" + name + "' is already present.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private int navigateToRecurringTask(String name) throws Exception {
		try {
			NavigationHelper.navigateToScreen( "Recurring Tasks", "Recurring Task Search" );
			int row = SearchGridHelper.gridFilterSearchWithTextBox("RecurringTask_Name", name, "Name");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveRecurringTask(String name, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Recurring Task save did not happen.");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), "Value '" + name + "' is not found in grid.");
			Log4jHelper.logInfo("Recurring Task '" + name + "' created");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}