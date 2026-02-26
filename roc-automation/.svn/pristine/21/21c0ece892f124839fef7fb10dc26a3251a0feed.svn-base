package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class FederatedDataSourceHelper extends ROCAcceptanceTest {
	
	private String[] fdsDataSourceType = null;
	private String[] fdsDatabase = null;
	private String[] fdsInstance = null;
	private String[] fdsMachine = null;
	private String[] fdsPassword = null;
	private String[] fdsUserName = null;
	private String[] fdsPort = null;
	
	public String[][] getFDSDBDetails() throws Exception {
		try {
			fdsDataSourceType = configProp.getStringProperty("FDSDataSourceType").split(",", -1);
			fdsDatabase = configProp.getStringProperty("FDSDatabase").split(",", -1);
			fdsInstance = configProp.getStringProperty("FDSInstance").split(",", -1);
			fdsMachine = configProp.getStringProperty("FDSMachine").split(",", -1);
			fdsPassword = configProp.getStringProperty("FDSPassword").split(",", -1);
			fdsUserName = configProp.getStringProperty("FDSUserName").split(",", -1);
			fdsPort = configProp.getStringProperty("FDSPort").split(",", -1);
			
			String[][] dbDetails = {fdsDataSourceType, fdsMachine, fdsDatabase, fdsUserName, fdsPassword, fdsInstance, fdsPort};
			return dbDetails;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createFederatedDataSource(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
			
			getFDSDBDetails();
			
			if (ValidationHelper.isNotEmpty(fdsMachine)) {
				for(int i = 0; i < excelData.get("Name").size(); i++)
				{
					String partition = excelData.get("Partition").get(i);
					String fdsName = excelData.get("Name").get(i);
					String prefix = excelData.get("Prefix").get(i);
					
					String dataSourceType = null;
					if (ValidationHelper.isNotEmpty(fdsDataSourceType)) {
						if (fdsDataSourceType.length > i && ValidationHelper.isNotEmpty(fdsDataSourceType[i]))
							dataSourceType = fdsDataSourceType[i];
						else if (ValidationHelper.isNotEmpty(fdsDataSourceType[0]))
							dataSourceType = fdsDataSourceType[0];
					}
					
					String database = null;
					if (ValidationHelper.isNotEmpty(fdsDatabase)) {
						if (fdsDatabase.length > i && ValidationHelper.isNotEmpty(fdsDatabase[i]))
							database = fdsDatabase[i];
						else if (ValidationHelper.isNotEmpty(fdsDatabase[0]))
							database = fdsDatabase[0];
					}
					
					String instance = null;
					if (ValidationHelper.isNotEmpty(fdsInstance)) {
						if (fdsInstance.length > i && ValidationHelper.isNotEmpty(fdsInstance[i]))
							instance = fdsInstance[i];
						else if (ValidationHelper.isNotEmpty(fdsInstance[0]))
							instance = fdsInstance[0];
					}
					
					String machine = null;
					if (ValidationHelper.isNotEmpty(fdsMachine)) {
						if (fdsMachine.length > i && ValidationHelper.isNotEmpty(fdsMachine[i]))
							machine = fdsMachine[i];
						else if (ValidationHelper.isNotEmpty(fdsMachine[0]))
							machine = fdsMachine[0];
					}
					
					String password = null;
					if (ValidationHelper.isNotEmpty(fdsPassword)) {
						if (fdsPassword.length > i && ValidationHelper.isNotEmpty(fdsPassword[i]))
							password = fdsPassword[i];
						else if (ValidationHelper.isNotEmpty(fdsPassword[0]))
							password = fdsPassword[0];
					}
					
					String userName = null;
					if (ValidationHelper.isNotEmpty(fdsUserName)) {
						if (fdsUserName.length > i && ValidationHelper.isNotEmpty(fdsUserName[i]))
							userName = fdsUserName[i];
						else if (ValidationHelper.isNotEmpty(fdsUserName[0]))
							userName = fdsUserName[0];
					}
					
					String port = null;
					if (ValidationHelper.isNotEmpty(fdsPort)) {
						if (fdsPort.length > i && ValidationHelper.isNotEmpty(fdsPort[i]))
							port = fdsPort[i];
						else if (ValidationHelper.isNotEmpty(fdsPort[0]))
							port = fdsPort[0];
					}
					
					createFederatedDataSource(partition, fdsName, prefix, dataSourceType, database, instance, machine, password,
							userName, port);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createFederatedDataSource(String partition, String fdsName, String prefix, String dataSourceType, String fdsDatabase,
			String fdsInstance, String fdsMachine, String fdsPassword, String fdsUserName, String fdsPort) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Federated Data Sources", "Federated Data Source Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("FedDataSource_Name", fdsName, "Name");

			if (row > 0) {
				Log4jHelper.logWarning("Federated Data Sources '" + fdsName + "' is already present.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "FedDataSource_Name");
				
				updateFederatedDataSource(fdsName, prefix, dataSourceType, fdsDatabase, fdsInstance, fdsMachine, fdsPassword, fdsUserName, fdsPort);
				
				saveFederatedDataSource(fdsName, detailScreenTitle);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateFederatedDataSource(String fdsName, String prefix, String dataSourceType, String fdsDatabase, String fdsInstance,
			String fdsMachine, String fdsPassword, String fdsUserName, String fdsPort) throws Exception {
		try {
			TextBoxHelper.type("FedDataSource_Name", fdsName);
			TextBoxHelper.type("FedDataSource_Prefix", prefix);
			ComboBoxHelper.select("FedDataSource_DataSourceType", dataSourceType);
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			Thread.sleep(500);
			
			updateProperties(dataSourceType, fdsDatabase, fdsInstance, fdsMachine, fdsPassword, fdsUserName, fdsPort);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateProperties(String dataSourceType, String fdsDatabase, String fdsInstance, String fdsMachine, String fdsPassword,
			String fdsUserName, String fdsPort) throws Exception {
		try {
			if (dataSourceType.equals("MS SQL-Server")) {
				PropertyGridHelper.typeInTextBox("Database *", fdsDatabase);
				PropertyGridHelper.typeInTextBox("Instance", fdsInstance);
				PropertyGridHelper.typeInTextBox("Machine *", fdsMachine);
				PropertyGridHelper.typeInTextBox("Password *", fdsPassword);
				PropertyGridHelper.typeInTextBox("User Name *", fdsUserName);		
			}
			else if (dataSourceType.equals("Oracle")) {
				PropertyGridHelper.typeInTextBox("Instance *", fdsInstance);
				PropertyGridHelper.typeInTextBox("Machine *", fdsMachine);
				PropertyGridHelper.typeInTextBox("Password *", fdsPassword);
				PropertyGridHelper.typeInTextBox("Port *", fdsPort);	
				PropertyGridHelper.typeInTextBox("User Name *", fdsUserName);
			}
			PropertyGridHelper.clickProperty("Machine *");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void testConnection(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String fdsName = excelData.get("Name").get(i);
				
				testConnection(fdsName);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void testConnection(String fdsName) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Federated Data Sources", "Federated Data Source Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("FedDataSource_Name", fdsName, "Name");

			if (row > 0) {
				GridHelper.clickRow("SearchGrid", row, "Name");
				NavigationHelper.navigateToAction("Federated Data Source Actions", "Test Connection");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				GenericHelper.waitForElement("OKButton", searchScreenWaitSec);
				
				assertTrue(PopupHelper.isTextPresent("Connection Successful."));
				ButtonHelper.click("OKButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
			else {
				FailureHelper.failTest("Federated Data Source '" + fdsName + "' not found.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void saveFederatedDataSource(String fdsName, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Federated Data Source save did not happen");
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", fdsName, "Name"), "Value '" + fdsName + "' is not found in grid.");
			Log4jHelper.logInfo("Federated Data Source '" + fdsName + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}