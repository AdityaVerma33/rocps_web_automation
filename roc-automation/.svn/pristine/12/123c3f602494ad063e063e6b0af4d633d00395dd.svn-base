package com.subex.automation.testcases.regressiontesting.datafederation;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.FederatedDataSourceHelper;
import com.subex.automation.helpers.application.screens.FederatedViewHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.application.screens.TableDefinitionHelper;
import com.subex.automation.helpers.application.screens.TableInstanceHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridCheckBoxHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.componentHelpers.GridElementHelper;
import com.subex.automation.helpers.data.StringHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.scripts.ExecuteScript;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testCreateView extends ROCAcceptanceTest {
	
	static String path = null;
	final String fileName = "DataFederation_TestData.xlsx";
	final String sheetName = "CreateView";
	
	public testCreateView() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private String[] getDBDetails() throws Exception {
		try {
			FederatedDataSourceHelper fedDataSource = new FederatedDataSourceHelper();
			String[][] dbDetail = fedDataSource.getFDSDBDetails();
			String[] dbDetails = {dbDetail[0][0], dbDetail[1][0], dbDetail[2][0], dbDetail[3][0], dbDetail[4][0], dbDetail[5][0]};
			
			return dbDetails;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void navigateToNew(String partition) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Federated Views", "Federated View Search");
			NavigationHelper.navigateToNew(partition, "FedView_DataSource");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected String navigateToEdit(String name) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Federated Views", "Federated View Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("FedView_Name_Filter", name, "Name");
			GridHelper.clickRow("SearchGrid", row, 1);
			String detailScreenTitle = NavigationHelper.navigateToEdit("SearchGrid", row, "FedView_DataSource");
			
			return detailScreenTitle;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void verifyTDTI(String tableDfnName, String tableInstName, String j2sTestCaseName) throws Exception {
		try {
			TableDefinitionHelper tableDfn = new TableDefinitionHelper();
			int row = tableDfn.navigateToTableDefinition(tableDfnName);
			assertTrue(row > 0);
			
			TableInstanceHelper tableInstance = new TableInstanceHelper();
			row = tableInstance.navigateToTableInstance(tableInstName);
			assertTrue(row > 0);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, j2sTestCaseName, 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected void verifyTDColumns(String tableDfnName, String[] columnToBePresent, String[] columnNotToBePresent) throws Exception {
		try {
			TableDefinitionHelper tableDfn = new TableDefinitionHelper();
			int row = tableDfn.navigateToTableDefinition(tableDfnName);
			assertTrue(row > 0);
			GridHelper.clickRow("SearchGrid", row, 1);
			NavigationHelper.navigateToAction("Common Tasks", "View");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			GenericHelper.waitForElement("TableDfn_Name", detailScreenWaitSec);
			
			if (ValidationHelper.isNotEmpty(columnToBePresent)) {
				for (int i = 0; i < columnToBePresent.length; i++)
					assertTrue(GridHelper.isValuePresent("TableDfn_Column_Grid", columnToBePresent[i].toLowerCase(), "Column Name"));
			}
			
			if (ValidationHelper.isNotEmpty(columnNotToBePresent)) {
				for (int i = 0; i < columnNotToBePresent.length; i++)
					assertFalse(GridHelper.isValuePresent("TableDfn_Column_Grid", columnNotToBePresent[i].toLowerCase(), "Column Name"));
			}
			
			ButtonHelper.click("CloseButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Creation of Remote Data Source", groups = { "create" })
	public void testPrerequisite() throws Exception
	{
		try {
			FederatedDataSourceHelper fedDataSource = new FederatedDataSourceHelper();
			fedDataSource.createFederatedDataSource(path, fileName, sheetName, "FederatedDataSource", 1);
			
			String scriptFileName = automationPath + "\\src\\main\\resources\\Regression\\FederatedDataSource_Script.sql";
			ExecuteScript.exeScript(scriptFileName, "FederatedDataSource");
			scriptFileName = automationPath + "\\src\\main\\resources\\System_Test_Flows\\FederatedDataSource_Script.sql";
			ExecuteScript.exeScript(scriptFileName, "FederatedDataSource");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Creation of Remote Data Source view - 1", dependsOnMethods = { "testPrerequisite" }, groups = { "create" })
	public void testViewCreation() throws Exception
	{
		try {
			// Remote Data Source view should get created successfully.
			// A Table Definition and a table instance with name same as View name should get created.
			FederatedViewHelper fedView = new FederatedViewHelper();
			fedView.createFederatedView(path, fileName, sheetName, "FederatedView", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FederatedView", 1 );
			String name = excelData.get("Name").get(0);
			
			verifyTDTI(name, name, "J2SEtopup");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Creation of Remote Data Source view - 2", dependsOnMethods = { "testPrerequisite" }, groups = { "create" })
	public void testViewCreationWithAllTypes() throws Exception
	{
		try {
			// Verify that user should be able to add column with any data type such as String, Long, Decimal, Integer, Boolean and Datetime.
			FederatedViewHelper fedView = new FederatedViewHelper();
			fedView.createFederatedView(path, fileName, sheetName, "FederatedViewAll", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FederatedViewAll", 1 );
			String name = excelData.get("Name").get(0);
			
			verifyTDTI(name, name, "J2SEtopup");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Creation of Remote Data Source view - 3", dependsOnMethods = { "testPrerequisite" }, groups = { "create" })
	public void testViewWithLessColumns() throws Exception
	{
		try {
			// Remote Data Source view should get created successfully with less columns.
			// A Table Definition and a table instance with name same as View name should get created.
			FederatedViewHelper fedView = new FederatedViewHelper();
			fedView.createFederatedView(path, fileName, sheetName, "FederatedViewLessColumn", 1);
			
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FederatedViewLessColumn", 1 );
			String name = excelData.get("Name").get(0);
			String[] removeColumns = testData.getStringValue(excelData.get("Remove Columns").get(0), firstLevelDelimiter);
			
			verifyTDTI(name, name, "J2SRoamPartner");
			
			verifyTDColumns(name, null, removeColumns);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Creation of Remote Data Source view - 4", dependsOnMethods = { "testPrerequisite" }, groups = { "create" })
	public void testViewWithoutColumns() throws Exception
	{
		try {
			// 1. Save button should get disabled
			// 2. A validation message 'A table must contain at least one column selected' should be found in the grid.
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FederatedViewNoColumn", 1 );
			String partition = excelData.get("Partition").get(0);
			String name = excelData.get("Name").get(0);
			String federatedDataSource = excelData.get("Federated Data Source").get(0);
			String sourceTable = excelData.get("Source Table").get(0);
			String[] removeColumns = testData.getStringValue(excelData.get("Remove Columns").get(0), firstLevelDelimiter);
			
			FederatedViewHelper fedView = new FederatedViewHelper();
			navigateToNew(partition);
			
			fedView.selectTableDefinition(federatedDataSource, sourceTable);
			TextBoxHelper.type("FedView_Name", name);
			fedView.removeColumns(removeColumns);
			
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("New Federated View"));
			assertTrue(GridHelper.isErrorIconPresent("FedView_Columns_Grid"));
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Creation of Remote Data Source view - 5", dependsOnMethods = { "testPrerequisite" }, groups = { "create" })
	public void testViewWithDuplicateColumnName() throws Exception
	{
		try {
			// 1. In case of single duplicate column name, there should be a validation message in the grid saying 'Duplicate Column <column_name>' .
			// 2. In case of multiple duplicate column names, there should be 2 validation messages in the grid saying 'Duplicate Column abc' and 'Duplicate Column xyz'.
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FederatedViewDuplicateColumnName", 1 );
			
			for (int i = 0; i < excelData.get("Name").size(); i++) {
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String federatedDataSource = excelData.get("Federated Data Source").get(i);
				String sourceTable = excelData.get("Source Table").get(i);
				
				String[] selectColumns = testData.getStringValue(excelData.get("Select Columns").get(i), firstLevelDelimiter);
				String[] targetName = testData.getStringValue(excelData.get("Target Name").get(i), firstLevelDelimiter);
				String[] targetDataType = testData.getStringValue(excelData.get("Target DataType").get(i), firstLevelDelimiter);
				String[] unique = testData.getStringValue(excelData.get("Unique").get(i), firstLevelDelimiter);
				
				FederatedViewHelper fedView = new FederatedViewHelper();
				navigateToNew(partition);
				
				fedView.selectTableDefinition(federatedDataSource, sourceTable);
				TextBoxHelper.type("FedView_Name", name);
				fedView.selectColumns(selectColumns, targetName, targetDataType, unique);
				
				ButtonHelper.click("SaveButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("New Federated View"));
				assertTrue(GridHelper.isErrorIconPresent("FedView_Columns_Grid"));
				
				WebElement errorIcon = GridElementHelper.getErrorIconElement("FedView_Columns_Grid");
				if (errorIcon != null) {
					MouseHelper.mouseOver(errorIcon);
					String tooltip = GenericHelper.getTooltip();
					if (ValidationHelper.isNotEmpty(tooltip))
						assertTrue(tooltip.contains("Each display name must be unique - duplicate found: "));
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=7, description="Creation of Remote Data Source view - 6", dependsOnMethods = { "testPrerequisite" }, groups = { "create" })
	public void testViewNonselectedColumns() throws Exception
	{
		try {
			// User should not be allowed to make any changes to the unchecked column. Unchecked column should be disabled.
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FederatedViewNoColumn", 1 );
			String partition = excelData.get("Partition").get(0);
			String name = excelData.get("Name").get(0);
			String federatedDataSource = excelData.get("Federated Data Source").get(0);
			String sourceTable = excelData.get("Source Table").get(0);
			String[] removeColumns = testData.getStringValue(excelData.get("Remove Columns").get(0), firstLevelDelimiter);
			
			FederatedViewHelper fedView = new FederatedViewHelper();
			navigateToNew(partition);
			
			fedView.selectTableDefinition(federatedDataSource, sourceTable);
			TextBoxHelper.type("FedView_Name", name);
			fedView.removeColumns(removeColumns);
			
			for (int i = 0; i < removeColumns.length; i++) {
				int row = GridHelper.getRowNumber("FedView_Columns_Grid", removeColumns[i], "Source Name");
				GridHelper.clickRow("FedView_Columns_Grid", row, "Target Name");
				assertFalse(TextBoxHelper.isPresent("FedView_Columns_TargetColumnName"));
				
				GridHelper.clickRow("FedView_Columns_Grid", row, "Target Type");
				assertFalse(ComboBoxHelper.isPresent("FedView_Columns_TargetDataType"));
				
				GridCheckBoxHelper.check("FedView_Columns_Grid", "FedView_Columns_Unique", row, "Unique");
				assertFalse(GridCheckBoxHelper.isChecked("FedView_Columns_Grid", "FedView_Columns_Unique", row, "Unique"));
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=8, description="Creation of Remote Data Source view - 7", dependsOnMethods = { "testPrerequisite" }, groups = { "create" })
	public void testViewWithoutDatatype() throws Exception
	{
		try {
			// User should not be allowed to make any changes to the unchecked column. Unchecked column should be disabled.
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FederatedViewNoColumn", 1 );
			String partition = excelData.get("Partition").get(0);
			String name = excelData.get("Name").get(0);
			String federatedDataSource = excelData.get("Federated Data Source").get(0);
			String sourceTable = excelData.get("Source Table").get(0);
			String[] selectColumns = testData.getStringValue(excelData.get("Select Columns").get(0), firstLevelDelimiter);
			String[] targetDataType = testData.getStringValue(excelData.get("Target DataType").get(0), firstLevelDelimiter);
			
			FederatedViewHelper fedView = new FederatedViewHelper();
			navigateToNew(partition);
			
			fedView.selectTableDefinition(federatedDataSource, sourceTable);
			TextBoxHelper.type("FedView_Name", name);
			
			for (int i = 0; i < selectColumns.length; i++) {
				int row = GridHelper.getRowNumber("FedView_Columns_Grid", selectColumns[i], "Source Name");
				
				GridHelper.updateGridComboBox("FedView_Columns_Grid", "FedView_Columns_TargetDataType", row, "Target Type", "None");
				assertTrue(GridHelper.isErrorIconPresent("FedView_Columns_Grid"));
				
				GridHelper.updateGridComboBox("FedView_Columns_Grid", "FedView_Columns_TargetDataType", row, "Target Type", targetDataType[i]);
				GridHelper.clickRow("FedView_Columns_Grid", 1, 1);
				assertFalse(GridHelper.isErrorIconPresent("FedView_Columns_Grid"));
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=9, description="Creation of Remote Data Source view - 8", dependsOnMethods = { "testPrerequisite" }, groups = { "create" })
	public void testViewWithDatatypeChange() throws Exception
	{
		try {
			// Remote Data Source view should get created successfully on changing the data type of columns.
			FederatedViewHelper fedView = new FederatedViewHelper();
			fedView.createFederatedView(path, fileName, sheetName, "FederatedViewChangeType", 1);
			
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FederatedViewTypeEdit", 1 );
			String name = excelData.get("Name").get(0);
			String[] selectColumns = testData.getStringValue(excelData.get("Select Columns").get(0), firstLevelDelimiter);
			String[] targetDataType = testData.getStringValue(excelData.get("Target DataType").get(0), firstLevelDelimiter);
			String[] actualType = new String[targetDataType.length];
			
			String detailScreenTitle = navigateToEdit(name);
			
			for (int i = 0; i < selectColumns.length; i++) {
				int rowNo = GridHelper.getRowNumber("FedView_Columns_Grid", selectColumns[i], "Source Name");
				actualType[i] = StringHelper.convertToCamelCase(GridHelper.getCellValue("FedView_Columns_Grid", rowNo, "Target Type"));
				
				GridHelper.updateGridComboBox("FedView_Columns_Grid", "FedView_Columns_TargetDataType", rowNo, "Target Type", targetDataType[i]);
				assertFalse(GridHelper.isErrorIconPresent("FedView_Columns_Grid"));
			}
			
			fedView.saveFederatedView(name, detailScreenTitle);
			
			TableDefinitionHelper tableDfn = new TableDefinitionHelper();
			int row = tableDfn.navigateToTableDefinition(name);
			assertTrue(row > 0);
			GridHelper.clickRow("SearchGrid", row, 1);
			NavigationHelper.navigateToAction("Common Tasks", "View");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			GenericHelper.waitForElement("TableDfn_Name", detailScreenWaitSec);
			
			for (int i = 0; i < selectColumns.length; i++) {
				String columnName = selectColumns[i].toLowerCase();
				String dataType = targetDataType[i].toLowerCase();
				int rowNo = GridHelper.getRowNumber("TableDfn_Column_Grid", columnName, "Column Name");
				
				assertTrue(GridHelper.isValuePresent("TableDfn_Column_Grid", rowNo, dataType, "Type"));
			}
			
			ButtonHelper.click("CloseButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
	
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			row = jumpToSearch.navigateToTableInstance(name);
			GridHelper.clickRow("SearchGrid", row, 1);
			NavigationHelper.navigateToAction("Table Actions", "Jump To Search");
			assertTrue(PopupHelper.isTextPresent("Configured query filter is invalid."));
			ButtonHelper.click("OKButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			detailScreenTitle = navigateToEdit(name);
			
			for (int i = 0; i < selectColumns.length; i++) {
				int rowNo = GridHelper.getRowNumber("FedView_Columns_Grid", selectColumns[i], "Source Name");
				
				GridHelper.updateGridComboBox("FedView_Columns_Grid", "FedView_Columns_TargetDataType", rowNo, "Target Type", actualType[i]);
			}
			
			fedView.saveFederatedView(name, detailScreenTitle);
			
			verifyTDTI(name, name, "J2SRRoamPartner");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=10, description="Creation of Remote Data Source view - 9", dependsOnMethods = { "testPrerequisite" }, groups = { "create" })
	public void testViewWithNewColumn() throws Exception
	{
		try {
			// 1.There should be a entry for newly added column in Table Column grid. By default it should be unchecked.
			// 2. The Remote Data Source View should get updated. Corresponding table definition and table instance should also get updated.
			String[] dbDetails = getDBDetails();
			
			FederatedViewHelper fedView = new FederatedViewHelper();
			fedView.createFederatedView(path, fileName, sheetName, "FederatedViewChangeType", 1);
			
			String addColumnQuery = "ALTER TABLE ROAMING_PARTNER ADD (RRP_ISO_CODE NUMBER(19,0), RRP_DIAL_CODE VARCHAR2(255))";
			ExecuteScript.exeQuery(addColumnQuery, dbDetails);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FederatedViewChangeType", 1 );
			String name = excelData.get("Name").get(0);
			String[] columnName = {"RRP_ISO_CODE", "RRP_DIAL_CODE"};
			
			String detailScreenTitle = navigateToEdit(name);
			
			for (int i = 0; i < columnName.length; i++) {
				int rowNo = GridHelper.getRowNumber("FedView_Columns_Grid", columnName[i], "Source Name");
				
				if (rowNo > 0) {
					assertFalse(GridCheckBoxHelper.isChecked("FedView_Columns_Grid", "FedView_Columns_Select", rowNo, "Select"));
					
					GridHelper.updateGridCheckBox("FedView_Columns_Grid", "FedView_Columns_Select", rowNo, "Select", true);
					assertTrue(GridCheckBoxHelper.isChecked("FedView_Columns_Grid", "FedView_Columns_Select", rowNo, "Select"));
				}
				else {
					FailureHelper.failTest("Newly added column '" + columnName + "' is not visible in Federated View '" + name + "'");
				}
			}
			
			fedView.saveFederatedView(name, detailScreenTitle);
			
			// Verify the edit
			detailScreenTitle = navigateToEdit(name);
			for (int i = 0; i < columnName.length; i++) {
				int rowNo = GridHelper.getRowNumber("FedView_Columns_Grid", columnName[i], "Source Name");
				assertTrue(GridCheckBoxHelper.isChecked("FedView_Columns_Grid", "FedView_Columns_Select", rowNo, "Select"));
			}
			fedView.saveFederatedView(name, detailScreenTitle);
			
			// Verify Table Definition and Table Instance
			verifyTDTI(name, name, "J2SRRoamPartner");
			
			verifyTDColumns(name, columnName, null);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=11, description="Creation of Remote Data Source view - 11", dependsOnMethods = { "testPrerequisite" }, groups = { "create" })
	public void testViewWithRenameColumn() throws Exception
	{
		try {
			// 1. There should be a new entry in table column grid for the renamed column. The entry for the same column with old name should be found as strike through. The row which is strike should be disabled.
			// 2. There should be a confirm dialog with message 'The disabled column(s) are deleted in the source table and will be deleted in the table view. Do you want to continue?' with Yes and No button.
			// 3. Click on No button should redirect the user to detail screen without changing the view, table definition and table instance.
			// 4. Click on Yes button should redirect the user to the Remote Data Source View search screen by changing the view, table definition and table instance.
			String[] dbDetails = getDBDetails();
			
			FederatedViewHelper fedView = new FederatedViewHelper();
			fedView.createFederatedView(path, fileName, sheetName, "FederatedViewChangeType", 1);
			
			String addColumnQuery = "ALTER TABLE ROAMING_PARTNER RENAME COLUMN RRP_DIAL_CODE TO RRP_DIAL_STRING";
			ExecuteScript.exeQuery(addColumnQuery, dbDetails);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FederatedViewChangeType", 1 );
			String name = excelData.get("Name").get(0);
			String renamedColumn = "RRP_DIAL_CODE";
			String[] columnName = {"RRP_DIAL_STRING"};
			
			String detailScreenTitle = navigateToEdit(name);
			
			// Verify renamed column's older name
//			assertTrue(GridCheckBoxHelper.isChecked("FedView_Columns_Grid", "FedView_Columns_Select", renamedColumn, "Source Name", "Select"));
			assertTrue(GridHelper.isRowDeleted("FedView_Columns_Grid", renamedColumn, "Source Name"));
			
			// Verify renamed column
			for (int i = 0; i < columnName.length; i++) {
				assertFalse(GridCheckBoxHelper.isChecked("FedView_Columns_Grid", "FedView_Columns_Select", columnName[i], "Source Name", "Select"));
			}
			
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(PopupHelper.isTextPresent("The disabled column(s) are deleted in the source table and will be deleted in the table view. Do you want to continue?"));
			ButtonHelper.click("NoButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent(detailScreenTitle));
			
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(PopupHelper.isTextPresent("The disabled column(s) are deleted in the source table and will be deleted in the table view. Do you want to continue?"));
			ButtonHelper.click("YesButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle));
			
			// Verify the edit
			navigateToEdit(name);
			assertFalse(GridHelper.isValuePresent("FedView_Columns_Grid", renamedColumn, "Source Name"));
			
			// Verify the column in Table Definition
			verifyTDTI(name, name, "J2SRRoamPartner");
			
			verifyTDColumns(name, null, columnName);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=12, description="Creation of Remote Data Source view - 12", dependsOnMethods = { "testPrerequisite" }, groups = { "create" })
	public void testViewWithDropColumn() throws Exception
	{
		try {
			// 1. The created view should become invalid due to dropping of the column in table.
			// 2. The view should become valid only when user edits and save the view at least once,
			// after recreating the column back to it name in the source table.
			String[] dbDetails = getDBDetails();
			FederatedViewHelper fedView = new FederatedViewHelper();
			fedView.createFederatedView(path, fileName, sheetName, "FederatedViewChangeType", 1);
			
			String addColumnQuery = "ALTER TABLE ROAMING_PARTNER DROP COLUMN RRP_DIAL_STRING";
			ExecuteScript.exeQuery(addColumnQuery, dbDetails);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FederatedViewChangeType", 1 );
			String name = excelData.get("Name").get(0);
			String[] columnName = {"RRP_DIAL_STRING"};
			
			String detailScreenTitle = navigateToEdit(name);
			
			// Verify dropped column
			for (int i = 0; i < columnName.length; i++) {
				assertFalse(GridHelper.isValuePresent("FedView_Columns_Grid", columnName[i], "Source Name"));
			}
			
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle));
			
			// Verify the column in Table Definition
			verifyTDColumns(name, null, columnName);
			verifyTDTI(name, name, "J2SRRoamPartner");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=13, description="Creation of Remote Data Source view - 13", dependsOnMethods = { "testPrerequisite" }, groups = { "create" })
	public void testViewDropTable() throws Exception
	{
		try {
			// A new dialog box should get open with title 'Error' and message 'Could not find the source table <table_name>.
			// Please verify whether the Remote Data Source is proper and <table_name> exists' with OK button.
			// Click on OK button should redirect the user to the Remote Data Source Views search screen
			String[] dbDetails = getDBDetails();
			FederatedViewHelper fedView = new FederatedViewHelper();
			fedView.createFederatedView(path, fileName, sheetName, "FederatedViewChangeType", 1);
			
			String addColumnQuery = "DROP TABLE ROAMING_PARTNER";
			ExecuteScript.exeQuery(addColumnQuery, dbDetails);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FederatedViewChangeType", 1 );
			String name = excelData.get("Name").get(0);
			String sourceTable = excelData.get("Source Table").get(0);
			
			NavigationHelper.navigateToScreen("Federated Views", "Federated View Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("FedView_Name_Filter", name, "Name");
			GridHelper.clickRow("SearchGrid", row, 1);
			NavigationHelper.navigateToAction("Common Tasks", "Edit");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			assertTrue(PopupHelper.isTextPresent("Could not find the source table " + sourceTable + ". Please verify that " + sourceTable + " exists in the target database."));
			ButtonHelper.click("OKButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}