package com.subex.automation.helpers.application;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class ReferenceTableHelper extends ROCAcceptanceTest {
	
	/**
	 * This method can be used to create or edit Data Source Location
	 * @param path - Path of the test data excel
	 * @param workBookName - Excel file name
	 * @param workSheetName - Excel sheet name within the excel file
	 * @param testCaseName - Test Case Name specified in under column "TCName" in test data excel
	 * @param occurence - Occurrence count of the test case in the excel sheet.
	 * @throws Exception
	 */
	public void dataSourceLocation(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception
	{
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
			
			for( int i = 0; i < excelData.get("Partition").size(); i++ ) {
				String partition = excelData.get("Partition").get(i);
				String dslName = excelData.get("Name").get(i);
				String databaseType =  excelData.get("Database Type").get(i);
				String dbDetails[] = getDSLDetails(databaseType);
				
				dataSourceLocation(partition, dslName, dbDetails[0], dbDetails[1], dbDetails[2], dbDetails[3]);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method can be used to create or edit Data Source Location
	 * @param partition - Security Partition
	 * @param dslName - Data Source Location Name
	 * @param dbType - Database Type
	 * @param dbHost - Database Host
	 * @param dbInstance - Database Instance
	 * @param dbSecondaryMachines - Database Secondary Machines
	 * @throws Exception
	 */
	public void dataSourceLocation(String partition, String dslName, String dbType, String dbHost, String dbInstance, String dbSecondaryMachines) throws Exception
	{
		try {
			boolean isPresent = navigateToEntity("Data Source Location", "DSL_Name", dslName, partition, "Name");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			dbType = getDataBaseType(dbType);
			
			TextBoxHelper.type("Detail_Popup", "DSL_Name", dslName);
			ComboBoxHelper.select("Detail_Popup", "DSL_DataSourceType", dbType);
			PropertyGridHelper.typeInTextBox("DSL_Instance", dbInstance);
			PropertyGridHelper.typeInTextBox("DSL_MachineName", dbHost);
			PropertyGridHelper.typeInTextBox("DSL_SecondaryMachines", dbSecondaryMachines);
			
			saveReferenceTable("Data Source Location", detailScreenTitle, dslName, "Name", isPresent);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method can be used to create or edit Data Source Connection
	 * @param path - Path of the test data excel
	 * @param workBookName - Excel file name
	 * @param workSheetName - Excel sheet name within the excel file
	 * @param testCaseName - Test Case Name specified in under column "TCName" in test data excel
	 * @param occurence - Occurrence count of the test case in the excel sheet.
	 * @throws Exception
	 */
	public void dataSourceConnection(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception
	{
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
			
			for( int i = 0; i < excelData.get("Name").size(); i++) {
				String partition = excelData.get("Partition").get(i);
				String dscName = excelData.get("Name").get(i);
				String dslName = excelData.get("DSL Name").get(i);
				String databaseType = excelData.get("Database Type").get(i);
				String dbDetails[] = getDSCDetails(databaseType);
				boolean unicode = ValidationHelper.isTrue(dbDetails[1]);
	
				dataSourceConnection(partition, dscName, dslName, dbDetails[0], unicode, dbDetails[2], dbDetails[3], dbDetails[4], dbDetails[5],
						dbDetails[6], dbDetails[7], dbDetails[8], dbDetails[9], dbDetails[10], dbDetails[11]);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method can be used to create or edit Data Source Connection
	 * @param partition - Security Partition
	 * @param dscName - Data Source Connection Name
	 * @param dslName - Data Source Location Name
	 * @param database - Database name
	 * @param unicode - Is database Unicode enabled
	 * @param username - Database username
	 * @param password - Database Password
	 * @param linkToRefDB - DBLink name to reference database
	 * @param linkToStagingDB - DBLink name to staging database
	 * @param linkToUsageServer - DBLink name to usage database
	 * @param ONSConfig - ONS Config for Oracle RAC
	 * @param maxActiveConnections - Maximum Active Connection Pools
	 * @param maxIdleConnections - Maximum Idle Connections
	 * @param minIdleConnection - Minimum Idle Connections
	 * @param exhaustedAction - Connection Pool Exhausted Action
	 * @throws Exception
	 */
	public void dataSourceConnection(String partition, String dscName, String dslName, String database, boolean unicode, String username,
			String password, String linkToRefDB, String linkToStagingDB, String linkToUsageServer, String ONSConfig, String maxActiveConnections,
			String maxIdleConnections, String minIdleConnection, String exhaustedAction) throws Exception
	{
		try {
			boolean isPresent = navigateToEntity("Data Source Connection", "DSC_Name", dscName, partition, "Name");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			TextBoxHelper.type("Detail_Popup", "DSC_Name", dscName);
			ComboBoxHelper.select("Detail_Popup", "DSC_DSL_Name", dslName);
			
			PropertyGridHelper.typeInTextBox("DSC_MaxActiveConnection", maxActiveConnections);
			PropertyGridHelper.typeInTextBox("DSC_MaxIdleConnection", maxIdleConnections);
			PropertyGridHelper.typeInTextBox("DSC_MinIdleConnection", minIdleConnection);
			PropertyGridHelper.selectInComboBox("DCS_ExhaustedAction", exhaustedAction);
			
			PropertyGridHelper.typeInTextBox("DSC_Database", database);
			PropertyGridHelper.typeInTextBox("DSC_LinkToRefDB", linkToRefDB);
			PropertyGridHelper.typeInTextBox("DSC_LinkToStagingDB", linkToStagingDB);
			PropertyGridHelper.typeInTextBox("DSC_LinkToUsageServer", linkToUsageServer);
			PropertyGridHelper.typeInTextBox("DSC_ONSConfig", ONSConfig);
			PropertyGridHelper.typeInTextBox("DSC_Password", password);
			PropertyGridHelper.clickProperty("DSC_Password");
			
			if(unicode)
				PropertyGridHelper.checkCheckBox("DSC_Unicode");
	
			PropertyGridHelper.typeInTextBox("DSC_Username", username);
			PropertyGridHelper.clickProperty("DSC_Username");
			
			saveReferenceTable("Data Source Connection", detailScreenTitle, dscName, "Name", isPresent);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method can be used to create or edit Usage Server
	 * @param path - Path of the test data excel
	 * @param workBookName - Excel file name
	 * @param workSheetName - Excel sheet name within the excel file
	 * @param testCaseName - Test Case Name specified in under column "TCName" in test data excel
	 * @param occurence - Occurrence count of the test case in the excel sheet.
	 * @throws Exception
	 */
	public void usageServer(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
			
			for(int i = 0; i < excelData.get("Name").size(); i++ )
			{
				String usageServerName = excelData.get("Name").get(i);
				String machineName = configProp.getMachineName().split(",", -1)[0];
				String dslName = excelData.get("DSL Name").get(i);
				String dscName = excelData.get("DSC Name").get(i);
				String dataLocation = excelData.get("Data Location").get(i);
				String indexLocation = excelData.get("Index Location").get(i);
				
				usageServer("Common", usageServerName, machineName, dslName, dscName, dataLocation, indexLocation);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void usageServer(String partition, String usageServerName, String machineName, String dslName, String dscName,
			String dataLocation, String indexLocation) throws Exception {
		try {
			boolean isPresent = navigateToEntity("Usage Server", "US_Name", usageServerName, partition, "Usage Server Name");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			TextBoxHelper.type("Detail_Popup", "US_Name", usageServerName);
			ComboBoxHelper.select("Detail_Popup", "US_MachineName", machineName);
			ComboBoxHelper.select("Detail_Popup", "US_DSL", dslName);
			ComboBoxHelper.select("Detail_Popup", "US_DSC", dscName);
			TextBoxHelper.type("Detail_Popup", "US_DataLocation", dataLocation);
			TextBoxHelper.type("Detail_Popup", "US_IndexLocation", indexLocation);
			
			saveReferenceTable("Usage Server", detailScreenTitle, usageServerName, "Usage Server Name", isPresent);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method can be used to create or edit Schema
	 * @param path - Path of the test data excel
	 * @param workBookName - Excel file name
	 * @param workSheetName - Excel sheet name within the excel file
	 * @param testCaseName - Test Case Name specified in under column "TCName" in test data excel
	 * @param occurence - Occurrence count of the test case in the excel sheet.
	 * @throws Exception
	 */
	public void schema(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName );
			
			for(int i = 0; i < excelData.get("Name").size(); i++ )
			{
				String partition = excelData.get("Partition").get(i);
				String schemaType = excelData.get("Schema Type").get(i);
				String schemaName = excelData.get("Name").get(i);
				String usageGroupName = excelData.get("Usage Group").get(i);
				
				schema(partition, schemaType, schemaName, usageGroupName);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void schema(String partition, String schemaType, String schemaName, String usageGroupName) throws Exception {
		try {
			boolean isPresent = navigateToEntity("Schema", "Schema_Name", schemaName, partition, "Name");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			ComboBoxHelper.select("Detail_Popup", "Schema_Schema_Type", schemaType);
			ComboBoxHelper.select("Detail_Popup", "Schema_Usage_Group", usageGroupName);
			TextBoxHelper.type("Detail_Popup", "Schema_Name", schemaName);
			
			saveReferenceTable("Schema", detailScreenTitle, schemaName, "Name", isPresent);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method can be used to create or edit Schema Type
	 * @param path - Path of the test data excel
	 * @param workBookName - Excel file name
	 * @param workSheetName - Excel sheet name within the excel file
	 * @param testCaseName - Test Case Name specified in under column "TCName" in test data excel
	 * @param occurence - Occurrence count of the test case in the excel sheet.
	 * @throws Exception
	 */
	public void schemaType(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
			
			for(int i = 0; i < excelData.get("Name").size(); i++ )
			{
				String partition = excelData.get("Partition").get(i);
				String schemaTypeName = excelData.get("Name").get(i);
				String component = excelData.get("Component").get(i);
				
				schemaType(partition, schemaTypeName, component);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void schemaType(String partition, String schemaTypeName, String component) throws Exception {
		try {
			boolean isPresent = navigateToEntity("Schema Type", "SchemaType_Name", schemaTypeName, partition, "Name");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			TextBoxHelper.type("Detail_Popup", "SchemaType_Name", schemaTypeName);
			ComboBoxHelper.select("Detail_Popup", "SchemaType_Component", component);
			
			saveReferenceTable("Schema Type", detailScreenTitle, schemaTypeName, "Name", isPresent);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method can be used to create or edit Token
	 * @param path - Path of the test data excel
	 * @param workBookName - Excel file name
	 * @param workSheetName - Excel sheet name within the excel file
	 * @param testCaseName - Test Case Name specified in under column "TCName" in test data excel
	 * @param occurence - Occurrence count of the test case in the excel sheet.
	 * @throws Exception
	 */
	public void token(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++ )
			{
				String partition = excelData.get("Partition").get(i);
				String tokenName = excelData.get("Name").get(i);
				boolean enableMachineFlag = ValidationHelper.isTrue(excelData.get("Machine Flag").get(i));
				
				token(partition, tokenName, enableMachineFlag);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void token(String partition, String tokenName, boolean enableMachineFlag) throws Exception {
		try {
			boolean isPresent = navigateToEntity("Token", "Token_Name", tokenName, partition, "Name");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			TextBoxHelper.type("Detail_Popup", "Token_Name", tokenName);
			if (enableMachineFlag)
				CheckBoxHelper.check("Detail_Popup", "Token_MachineFlag");
			
			saveReferenceTable("Token", detailScreenTitle, tokenName, "Name", isPresent);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private String getDataBaseType(String dbType) throws Exception
	{
		try {
			if (dbType.equalsIgnoreCase("sqlserver") || dbType.equalsIgnoreCase("sql server"))
				dbType = "MS SQL-Server";
			else if(dbType.equalsIgnoreCase("oracle"))
				dbType = "Oracle";
			else if (dbType.equalsIgnoreCase("postgres") || dbType.equalsIgnoreCase("postgresql"))
				dbType = "PostgreSQL";
			else if(dbType.equalsIgnoreCase("vertica"))
				dbType = "Vertica";
			else if(dbType.equalsIgnoreCase("mysql"))
				dbType = "MySQL";
			
			return dbType;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private String[] getDSLDetails(String databaseType) throws Exception {
		try {
			String dbDetails[] = new String[4];
			
			if (databaseType.equalsIgnoreCase("Reference")) {
				dbDetails[0]=configProp.getDbType();
				dbDetails[1] = configProp.getDbMachineName();
				dbDetails[2] = configProp.getDbInstance();
			}
			else {
				dbDetails[0] = configProp.getUsageDBType();
				dbDetails[1] = configProp.getUsageMachineName();
				dbDetails[2] = configProp.getUsageInstance();
				dbDetails[3] = configProp.getUsageSecondaryMachine();
			}
			
			return dbDetails;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private String[] getDSCDetails(String databaseType) throws Exception {
		try {
			String dbDetails[] = new String[12];
			
			if(databaseType.equalsIgnoreCase("Reference")) {
				dbDetails[0] = configProp.getDB();
				dbDetails[1] = configProp.getDbUnicode();
				dbDetails[2] = configProp.getDbUserName();
				dbDetails[3] = configProp.getDbPassword();
				
				dbDetails[4] = configProp.getDbLinkToReferenceDB();
				dbDetails[5] = configProp.getDbLinkToStagingDB();
				dbDetails[6] = configProp.getDbLinkToUsageServer();
				dbDetails[7] = configProp.getDbONSConfigOracleRAC();
				
				dbDetails[8] = configProp.getDbMaxActiveConnections();
				dbDetails[9] = configProp.getDbMaxIdleConnections();
				dbDetails[10] = configProp.getDbMinIdleConnections();
				dbDetails[11] = configProp.getDbExhaustedAction();
			}
			else {
				dbDetails[0] = configProp.getUsageDatabase();
				dbDetails[1] = configProp.getUsageUnicode();
				dbDetails[2] = configProp.getUsageUsername();
				dbDetails[3] = configProp.getUsagePassword();
				
				dbDetails[4] = configProp.getUsageLinkToReferenceDB();
				dbDetails[5] = configProp.getUsageLinkToStagingDB();
				dbDetails[6] = configProp.getUsageLinkToUsageServer();
				dbDetails[7] = configProp.getUsageONSConfigOracleRAC();
				
				dbDetails[8] = configProp.getDbMaxActiveConnections();
				dbDetails[9] = configProp.getDbMaxIdleConnections();
				dbDetails[10] = configProp.getDbMinIdleConnections();
				dbDetails[11] = configProp.getDbExhaustedAction();
			}
			
			return dbDetails;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean navigateToEntity(String refEntity, String nameIdOrXpath, String nameValue, String partition, String nameColumnHeader) throws Exception {
		try {
			NavigationHelper.navigateToReferenceTable(refEntity);
			Thread.sleep(1000);
			int rowIndex = SearchGridHelper.searchWithTextBox(nameIdOrXpath, nameValue, nameColumnHeader);
			boolean isPresent = NavigationHelper.navigateToNewOrEdit(rowIndex, partition, refEntity, "Detail_Popup");
			
			return isPresent;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void saveReferenceTable(String refEntity, String detailScreenTitle, String uniqueValue, String uniqueValueColumn, boolean isPresent) throws Exception
	{
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), refEntity + " save did not happen.");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", uniqueValue, uniqueValueColumn), "Value '" + uniqueValue + "' is not found in grid.");
			
			if (isPresent)
				Log4jHelper.logInfo(refEntity + " '" + uniqueValue + "' updated");
			else
				Log4jHelper.logInfo(refEntity + " '" + uniqueValue + "' created");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}