package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntitySearchHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.RadioHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class UsageGroupHelper extends ROCAcceptanceTest {
	
	public void createUsageGroup( String path, String workBookName, String workSheetName, String testCaseName, int occurence ) throws Exception
	{
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
			String detailScreenTitle = null;
			
			for ( int i = 0; i < excelData.get( "Partition" ).size(); i++ )
			{
				String partition = excelData.get( "Partition" ).get( i );
				String usageGroupName = excelData.get( "Name" ).get( i );
				String type = excelData.get( "Type" ).get( i );
				boolean isAutomatic = ValidationHelper.isTrue(excelData.get( "Is Automatic" ).get( i ));
				
				if (isAutomatic) {
					boolean enableRollOver = ValidationHelper.isTrue(excelData.get( "Enable Roll Over" ).get( i ));
					String partitionName = excelData.get( "Name" ).get( i );
					String partitionSuffix = excelData.get( "Table Suffix" ).get( i );
					String partitionFrom = excelData.get( "Partition From" ).get( i );
					String noOfPartitions = excelData.get( "No. of Partitions" ).get( i );
					boolean applyIndex = ValidationHelper.isTrue(excelData.get( "Apply Indexes" ).get( i ));
					String partitionTypeMultiplier = excelData.get( "Partition Type No" ).get( i );
					String partitionType = excelData.get( "Partition Type" ).get( i );
					String partitionIncrement = excelData.get( "Incremental Units" ).get( i );
					
					detailScreenTitle = createAutomaticUsageGroup(partition, usageGroupName, type, enableRollOver, partitionName, partitionSuffix, partitionFrom, noOfPartitions, applyIndex, partitionTypeMultiplier, partitionType, partitionIncrement);
				}
				else {
					String[] partitionName = testData.getStringValue(excelData.get( "Partition Name" ).get( i ), firstLevelDelimiter);
					String[] partitionSuffix = testData.getStringValue(excelData.get( "Table Suffix" ).get( i ), firstLevelDelimiter);
					String[] parititionFrom = testData.getStringValue(excelData.get( "Partition From" ).get( i ), firstLevelDelimiter);
					String[] partitionTo = testData.getStringValue(excelData.get( "Partition To" ).get( i ), firstLevelDelimiter);
					String[] applyIndex = testData.getStringValue(excelData.get( "Apply Indexes" ).get( i ), firstLevelDelimiter);
					
					detailScreenTitle = createManualUsageGroup(partition, usageGroupName, type, partitionName, partitionSuffix, parititionFrom, partitionTo, applyIndex);
				}
				
				if (detailScreenTitle != null) {
					String subpartitionType = excelData.get( "Subpartition Type" ).get( i );
					String subpartitionFunction = excelData.get( "Subpartition Function" ).get( i );
					String[] subpartitionName = testData.getStringValue(excelData.get( "Subpartition Name" ).get( i ), firstLevelDelimiter);
					String[] subpartitionSuffix = testData.getStringValue(excelData.get( "Subpartition Table Suffix" ).get( i ), firstLevelDelimiter);
					String[] subpartitionFrom = testData.getStringValue(excelData.get( "Subpartition From" ).get( i ), firstLevelDelimiter);
					String[] subpartitionTo = testData.getStringValue(excelData.get( "Subpartition To" ).get( i ), firstLevelDelimiter);
					
					updateSubpartition(subpartitionType, subpartitionFunction, subpartitionName, subpartitionSuffix, subpartitionFrom, subpartitionTo);
					
					boolean enableHybrid = ValidationHelper.isTrue(excelData.get( "Enable Hybrid" ).get( i ));
					String activePeriod = excelData.get( "Active Period" ).get( i );
					enableHybrid(enableHybrid, activePeriod);
					
					saveUsageGroup(usageGroupName, detailScreenTitle);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	
	public String createAutomaticUsageGroup( String partition, String usageGroupName, String type, boolean enableRollOver, String partitionName,
			String partitionSuffix, String parititionFrom, String noOfPartitions, boolean applyIndex, String partitionTypeMultiplier,
			String partitionType, String partitionIncrement ) throws Exception
	{
		try {
			int rowNo = navigateToUsageGroup(usageGroupName);
			
			if (rowNo == 0) {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "UsageGroup_Name");
				TextBoxHelper.type( "UsageGroup_Name", usageGroupName);
				ComboBoxHelper.select( "UsageGroup_Type_Dropdown", type );
				RadioHelper.click( "UsageGroup_Automatic_Radio" );
				if ( enableRollOver )
					CheckBoxHelper.check( "UsageGroup_RollOver_CheckBox" );
				
				automaticUsagePartition(partitionName, partitionSuffix, parititionFrom, noOfPartitions, applyIndex, partitionTypeMultiplier, partitionType, partitionIncrement);
				return detailScreenTitle;
			}
			else {
				Log4jHelper.logWarning("Usage Group with name '" + usageGroupName + "' is already present.");
				String detailScreenTitle = NavigationHelper.navigateToEdit("SearchGrid", rowNo, "UsageGroup_Name");
				
				return detailScreenTitle;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String createManualUsageGroup( String partition, String usageGroupName, String type, String[] partitionName, String[] partitionSuffix,
			String[] parititionFrom, String[] partitionTo, String[] applyIndex ) throws Exception
	{
		try {
			int row = navigateToUsageGroup(usageGroupName);
			
			if (row == 0) {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "UsageGroup_Name");
				TextBoxHelper.type( "UsageGroup_Name", usageGroupName);
				ComboBoxHelper.select( "UsageGroup_Type_Dropdown", type );
				
				for ( int i = 0; i < partitionName.length; i++ )
				{
					boolean toApplyIndex = true;
					if (ValidationHelper.isNotEmpty(applyIndex) && applyIndex.length > i && ValidationHelper.isFalse(applyIndex[i]))
						toApplyIndex = false;
					manualUsagePartition(partitionName[i], partitionSuffix[i], parititionFrom[i], partitionTo[i], toApplyIndex);
				}
				
				return detailScreenTitle;
			}
			else {
				Log4jHelper.logInfo("Usage Group with name '" + usageGroupName + "' is already present.");
				String detailScreenTitle = NavigationHelper.navigateToEdit("SearchGrid", row, "UsageGroup_Name");
				
				return detailScreenTitle;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void takePartitionOffline( String path, String WorkbookName, String workSheetName, String testCaseName, int occurence ) throws Exception
	{
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for ( int i = 0; i < excelData.get("Usage Group").size(); i++ )
			{
				String usageGroupName = excelData.get("Usage Group").get(i);
				String[] usagePartitionName = testData.getStringValue(excelData.get("Partition").get(i), firstLevelDelimiter);
				takePartitionOffline(usageGroupName, usagePartitionName);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void takePartitionOffline( String usageGroupName, String[] usagePartitionName ) throws Exception
	{
		try {
			int row = navigateToUsageGroup(usageGroupName);
			
			if (row == 0) {
				FailureHelper.failTest("Usage Group with name '" + usageGroupName + "' is not found.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToEdit("SearchGrid", row, "UsageGroup_Name");
				
				CheckBoxHelper.check("UsageGroup_ShowOffline_CheckBox");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				for (int i = 0; i < usagePartitionName.length; i++) {
					int rowNum = GridHelper.getRowCount("UsageGroup_UsagePartition_Grid", usagePartitionName[i], "Name");
					
					if (rowNum == 0) {
						FailureHelper.failTest("Usage Partition '" + usagePartitionName[i] + "' is not present in Usage Group '" + usageGroupName + "'");
					}
					else {
						boolean isOffline = GridHelper.isRowDeleted("UsageGroup_UsagePartition_Grid", usagePartitionName[i], "Name");
						
						if (!isOffline) {
							GridHelper.clickRow( "UsageGroup_UsagePartition_Grid", rowNum, "Name" );
							ButtonHelper.click( "UsageGroup_UsagePartition_TakeOffline" );
							GenericHelper.waitForLoadmask(detailScreenWaitSec);
							ButtonHelper.click( "UsageGroup_TakeOffline_Warning_Yes" );
							GenericHelper.waitForLoadmask(detailScreenWaitSec);
							
							isOffline = GridHelper.isRowDeleted("UsageGroup_UsagePartition_Grid", usagePartitionName[i], "Name");
							assertTrue(isOffline, "Take Offline for Usage Partition '" + usagePartitionName[i] + "' did not happen.");
						}
						else {
							Log4jHelper.logWarning("Usage Partition '" + usagePartitionName[i] + "' is already offline in Usage Group '" + usageGroupName + "'");
						}
					}
				}
				
				saveUsageGroup(usageGroupName, detailScreenTitle);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void editDefaultUsageGroup( String path, String WorkbookName, String workSheetName, String testCaseName, int occurance ) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurance );
			
			if (excelData.get( "Partition Name" ).size() > 0) {
				String[] partitionName = testData.getStringValue(excelData.get( "Partition Name" ).get( 0 ), firstLevelDelimiter);
				String[] partitionSuffix = testData.getStringValue(excelData.get( "Table Suffix" ).get( 0 ), firstLevelDelimiter);
				String[] parititionFrom = testData.getStringValue(excelData.get( "Partition From" ).get( 0 ), firstLevelDelimiter);
				String[] partitionTo = testData.getStringValue(excelData.get( "Partition To" ).get( 0 ), firstLevelDelimiter);
				String[] applyIndex = testData.getStringValue(excelData.get( "Apply Indexes" ).get( 0 ), firstLevelDelimiter);
				
				editDefaultUsageGroup(partitionName, partitionSuffix, parititionFrom, partitionTo, applyIndex);
			}
			else
				FailureHelper.failTest("Partition details are not provided in the test data excel");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void editDefaultUsageGroup( String[] partitionName, String[] partitionSuffix, String[] parititionFrom, String[] partitionTo, String[] applyIndex ) throws Exception {
		try {
			String usageGroupName = "Default Usage Group";
			int row = navigateToUsageGroup(usageGroupName);
			
			String detailScreenTitle = NavigationHelper.navigateToEdit("SearchGrid", row, "UsageGroup_Name");
			
			for ( int i = 0; i < partitionName.length; i++ )
			{
				boolean toApplyIndex = ValidationHelper.isTrue(applyIndex[i]);
				manualUsagePartition(partitionName[i], partitionSuffix[i], parititionFrom[i], partitionTo[i], toApplyIndex);
			}

			saveUsageGroup(usageGroupName, detailScreenTitle);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void attachUsageServer( String path, String WorkbookName, String workSheetName, String testCaseName, int occurence ) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
			boolean refreshUsageGroup = false;
			
			for ( int i = 0; i < excelData.get( "Usage Group Name" ).size(); i++ )
			{
				String usageGroupName = excelData.get( "Usage Group Name" ).get( i );
				String[] usageServers = testData.getStringValue(excelData.get( "Usage Server" ).get( i ), firstLevelDelimiter);
				
				boolean attached = attachUsageServer(usageGroupName, usageServers);
				if (attached)
					refreshUsageGroup = true;
			}
			
			if (refreshUsageGroup) {
				NavigationHelper.navigateToAction( "Usage Actions", "Refresh Usage Partitions" );
				GenericHelper.waitForElement( "RefreshUsagePartitions_Msg", configProp.getCustomScreenWaitSec());
				
				ButtonHelper.click( "OKButton" );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				Log4jHelper.logInfo( "Successfully refreshed Usage Groups." );
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean attachUsageServer( String usageGroupName, String[] usageServers ) throws Exception {
		try {
			int row = navigateToUsageGroup(usageGroupName);
			boolean attached = false;
			
			if (row == 0) {
				FailureHelper.failTest("Usage Group '" + usageGroupName + "' does not exists.");
			}	
			else {
				EntitySearchHelper entitySearch = new EntitySearchHelper();
				
				for(int i = 0; i < usageServers.length; i++) {
					GridHelper.clickRow( "SearchGrid", row, "Name" );
					NavigationHelper.navigateToAction( "Usage Servers" );
					
					if ( NavigationHelper.isActionPresent(usageServers[i]) ) {
						Log4jHelper.logWarning("Usage Server '" + usageServers[i] + "' is already attached to Usage Group '" + usageGroupName + "'");
						attached = false;
					}
					else {
						NavigationHelper.navigateToAction("Usage Servers", "Attach Usage Server");
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
						GenericHelper.waitForElement("OKButton", searchScreenWaitSec);
						entitySearch.select("Reference Table Search", usageServers[i], "Usage Server Name");
						
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
						GenericHelper.waitForElement("OKButton", searchScreenWaitSec);
						assertTrue(LabelHelper.isTitlePresent("Attach Usage Servers"), "Attach Usage Server popup did not appear.");
						ButtonHelper.click( "OKButton" );
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
						
						ButtonHelper.click( "SearchButton" );
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
						GridHelper.clickRow( "SearchGrid", row, "Name" );
						
						NavigationHelper.navigateToAction( "Usage Servers" );
						assertTrue(NavigationHelper.isActionPresent(usageServers[i]), "Action '" + usageServers[i] + "' missing in Usage Group");
						
						Log4jHelper.logInfo("Attached Usage Server '" + usageServers[i] + "' to Usage Group '" + usageGroupName + "'");
						attached = true;
					}
				}
			}
			
			return attached;
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private int navigateToUsageGroup(String usageGroupName) throws Exception {
		try {
			NavigationHelper.navigateToScreen( "Usage Groups", "Usage Group Search" );
			int row = SearchGridHelper.gridFilterSearchWithTextBox("UsageGroup_Name", usageGroupName, "Name");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveUsageGroup(String usageGroupName, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
			
			if (LabelHelper.isTextPresent("Saved Usage Group but failed to refresh physical Usage Partitions.")) {
				ButtonHelper.click("CancelButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				if (ButtonHelper.isPresent("DiscardButton")) {
					ButtonHelper.click("DiscardButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
				}
			}
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Usage Group save did not happen.");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", usageGroupName, "Name"), "Value '" + usageGroupName + "' is not found in grid.");
			Log4jHelper.logInfo("Usage Group '" + usageGroupName + "' created");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void automaticUsagePartition(String name, String suffix, String from, String noOfPartitions, boolean applyIndex,
			String typeMultiplier, String type, String increment) throws Exception {
		try {
			ButtonHelper.click( "UsageGroup_UsagePartition_Add" );
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			TextBoxHelper.type( "UsageGroup_AutomaticPartition_Name", name );
			TextBoxHelper.type( "UsageGroup_AutomaticPartition_TableSuffix", suffix );
			from = from.replace(" 00:00:00", "");
			TextBoxHelper.type( "UsageGroup_AutomaticPartition_From", from );
			TextBoxHelper.type( "UsageGroup_AutomaticPartition_NoPartitions", noOfPartitions );

			if ( !applyIndex )
				CheckBoxHelper.uncheck( "UsageGroup_AutomaticPartition_ApplyIndex" );
			TextBoxHelper.type( "UsageGroup_AutomaticPartition_TypeMultiplier", typeMultiplier );
			ComboBoxHelper.select( "UsageGroup_AutomaticPartition_Type", type );
			TextBoxHelper.type( "UsageGroup_AutomaticPartition_Increment", increment );
			ButtonHelper.click( "UsageGroup_AutomaticPartition_OK" );
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void manualUsagePartition(String name, String suffix, String from, String to, boolean applyIndex) throws Exception {
		try {
			int rowNum = GridHelper.getRowNumber("UsageGroup_UsagePartition_Grid", name, "Name");
			if (rowNum > 0) {
				GridHelper.clickRow("UsageGroup_UsagePartition_Grid", rowNum, "Name");
				ButtonHelper.click( "UsageGroup_UsagePartition_Edit" );
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				if (ButtonHelper.isPresent("YesButton")) {
					ButtonHelper.click( "YesButton" );
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
				}
			}
			else {
				ButtonHelper.click( "UsageGroup_UsagePartition_Add" );
			}
			
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			TextBoxHelper.type( "UsageGroup_ManualPartition_Name", name );
			TextBoxHelper.type( "UsageGroup_ManualPartition_TableSuffix", suffix );
			TextBoxHelper.type( "UsageGroup_ManualPartition_From", from );
			TextBoxHelper.type( "UsageGroup_ManualPartition_To", to );

			if ( !applyIndex )
				CheckBoxHelper.uncheck( "UsageGroup_ManualPartition_ApplyIndex" );
			ButtonHelper.click( "UsageGroup_ManualPartition_OK" );
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("UsageGroup_UsagePartition_Grid", name, "Name"), "Value '" + name + "' is not found in grid.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void updateSubpartition(String type, String function, String[] name, String[] suffix, String[] from, String[] to) throws Exception {
		try {
			if ( ValidationHelper.isNotEmpty(name) ) {
				ComboBoxHelper.select( "UsageGroup_Subpartition_Type", type );
				ComboBoxHelper.select( "UsageGroup_Subpartition_Function", function );
				
				for ( int i = 0; i < name.length; i++ ) {
					int row = GridHelper.getRowNumber("UsageGroup_Subpartition_Grid", name[i], "Name");
					
					if (row == 0) {
						ButtonHelper.click( "UsageGroup_Subpartition_Add" );
						row = GridHelper.getRowCount("UsageGroup_Subpartition_Grid");
					}
					
					GridHelper.updateGridTextBox("UsageGroup_Subpartition_Grid", "UsageGroup_Subpartition_Name", row, "Name", "To", name[i]);
					
					GridHelper.updateGridTextBox("UsageGroup_Subpartition_Grid", "UsageGroup_Subpartition_TableSuffix", row, "Table Suffix", "Name", suffix[i]);
					
					GridHelper.updateGridTextBox("UsageGroup_Subpartition_Grid", "UsageGroup_Subpartition_From", row, "From", "Name", from[i]);
					
					GridHelper.updateGridTextBox("UsageGroup_Subpartition_Grid", "UsageGroup_Subpartition_To", row, "To", "Name", to[i]);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void enableHybrid(boolean enableHybrid, String activePeriod) throws Exception {
		try {
			if (enableHybrid) {
				CheckBoxHelper.check( "UsageGroup_EnableHybrid" );
				TextBoxHelper.type( "UsageGroup_ActivePeriod", activePeriod );
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}