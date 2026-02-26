package com.subex.automation.testcases.regressiontesting.masking;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.application.screens.MaskingConfigurationHelper;
import com.subex.automation.helpers.application.screens.RolesHelper;
import com.subex.automation.helpers.application.screens.SettingsHelper;
import com.subex.automation.helpers.application.screens.StreamHelper;
import com.subex.automation.helpers.application.screens.TableDefinitionHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class testReferenceMasking extends testMasking {
	
	final String sheetName = "ReferenceMasking";
	
	public testReferenceMasking() throws Exception {
		super();
	}
	
	private void checkMaskedColumns(String[] columnName) throws Exception
	{
		try {
			for (int i = 0; i < columnName.length; i++) {
				int rowNo = GridHelper.getRowNumber("J2S_Edit_Record_Grid", columnName[i], "Name");
				GridHelper.clickRow("J2S_Edit_Record_Grid", rowNo, "Value");
				
				assertFalse(TextBoxHelper.isPresent("J2S_Edit_Integer_TextBox"));
				assertFalse(TextBoxHelper.isPresent("J2S_Edit_String_TextBox"));
				assertFalse(TextBoxHelper.isPresent("J2S_Edit_DateTime_TextBox"));
				assertFalse(TextBoxHelper.isPresent("J2S_Edit_Decimal_TextBox"));
				assertFalse(ComboBoxHelper.isPresent("J2S_Edit_Boolean_ComboBox"));
				
				GridHelper.clickRow("J2S_Edit_Record_Grid", rowNo, "Type");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void checkEditableColumns(String[] editableColumns, String[] updateValue) throws Exception
	{
		try {
			for (int i = 0; i < editableColumns.length; i++) {
				int rowNo = GridHelper.getRowNumber("J2S_Edit_Record_Grid", editableColumns[i], "Name");
				GridHelper.clickRow("J2S_Edit_Record_Grid", rowNo, "Value");
				
				if (i == 0) {
					if (!TextBoxHelper.isPresent("J2S_Edit_String_TextBox"))
						GridHelper.clickRow("J2S_Edit_Record_Grid", rowNo, "Value");
					assertTrue(TextBoxHelper.isPresent("J2S_Edit_String_TextBox"));
					TextBoxHelper.type("J2S_Edit_String_TextBox", updateValue[i]);
				}
				
				if (i == 1) {
					if (!TextBoxHelper.isPresent("J2S_Edit_Integer_TextBox"))
						GridHelper.clickRow("J2S_Edit_Record_Grid", rowNo, "Value");
					assertTrue(TextBoxHelper.isPresent("J2S_Edit_Integer_TextBox"));
					TextBoxHelper.type("J2S_Edit_Integer_TextBox", updateValue[i]);
				}
				
				if (i == 2) {
					if (!ComboBoxHelper.isPresent("J2S_Edit_Boolean_ComboBox"))
						GridHelper.clickRow("J2S_Edit_Record_Grid", rowNo, "Value");
					assertTrue(ComboBoxHelper.isPresent("J2S_Edit_Boolean_ComboBox"));
					ComboBoxHelper.select("J2S_Edit_Boolean_ComboBox", updateValue[i]);
				}
				
				GridHelper.clickRow("J2S_Edit_Record_Grid", rowNo, "Type");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=24, description="Manual data load into masked table", groups = { "GDPRReference" })
	public void testManualDataLoad() throws Exception
	{
		boolean tcFailed = false;
		
		try {
			// Validate that when a ref tbl is masked then user should be able to import the data
			TableDefinitionHelper tableDfn = new TableDefinitionHelper();
			tableDfn.importFromDiamond(path, fileName, sheetName, "ImportFromDiamond", 1);
			tableDfn.createTableInstance(path, fileName, sheetName, "TableInstance", 1);
			
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			UserHelper user = new UserHelper();
			user.createUser(path, fileName, sheetName, "Users", 1);
			
			MaskingConfigurationHelper masking = new MaskingConfigurationHelper();
			masking.createMaskingConfiguration(path, fileName, sheetName, "MaskingConfig", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.manualDataLoad(path, fileName, sheetName, "ManualDataLoad", 1);
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "MaskingJ2S", 1);
		} catch (Exception e) {
			tcFailed = true;
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			if (tcFailed) {
				LoginHelper login = new LoginHelper();
				login.loginWithConfigPropertyDetails();
			}
		}
	}
	
	@Test(priority=25, description="Test edit in Jump to Search screen for unprivileged user", groups = { "GDPRReference" })
	public void testUnprivilegedUserJ2SEdit() throws Exception
	{
		try {
			// Validate that when a ref tbl is masked and unprivileged user tries to modify the masked field then data should be displayed
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			String[] maskedColumns = {"B_Number", "Time_Stamp", "Amount", "In_Route", "Extra1", "File_Size"};
			String[] editableColumns = {"Extra2", "Duration", "Is_Active"};
			String[] updateValue = {"Extra 2", "15", "false"};
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "MaskingJ2S", 1);
			GenericHelper.collapseSearchFilterPanel();
			
			int row = GridHelper.getRowNumber("SearchGrid", "984566573766", "A_Number");
			GridHelper.clickRow("SearchGrid", row, 1);
			NavigationHelper.navigateToAction("Common Tasks", "Edit");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			GenericHelper.waitForElement("J2S_Edit_Save", searchScreenWaitSec);
			
			checkMaskedColumns(maskedColumns);
			
			checkEditableColumns(editableColumns, updateValue);
			
			ButtonHelper.click("J2S_Edit_Save");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			row = GridHelper.getRowNumber("SearchGrid", "984566573766", "A_Number");
			
			for (int i = 0; i < editableColumns.length-1; i++) {
				assertTrue(GridHelper.isValuePresent("SearchGrid", row, updateValue[i], editableColumns[i]), "Expected value '" + updateValue[i] + "' not found.");
			}
			assertTrue(GridHelper.isBooleanValuePresent("SearchGrid", false, row, editableColumns[2]));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=26, description="Test edit in Jump to Search screen for unprivileged user", groups = { "GDPRReference" })
	public void testUnprivilegedUserJ2SEditMask() throws Exception
	{
		try {
			// Validate that when any unprivileged user clicks on view then data should be displayed in masked format
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			String[] maskedColumns = {"B_Number", "Time_Stamp", "Amount", "In_Route", "Extra1", "File_Size"};
			String[] expectedValues = {"xxxxxxx547xx", "0xxxxx2020 10:10:10", "xxxxxxxxxx", "Swixxxx1", "Exxxxxx", "xxxxx"};
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "MaskingJ2S1", 1);
			GenericHelper.collapseSearchFilterPanel();
			
			int row = GridHelper.getRowNumber("SearchGrid", "984566573766", "A_Number");
			GridHelper.clickRow("SearchGrid", row, 1);
			NavigationHelper.navigateToAction("Common Tasks", "Edit");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			GenericHelper.waitForElement("J2S_Edit_Save", searchScreenWaitSec);
			
			for (int i = 0; i < maskedColumns.length; i++) {
				int rowNo = GridHelper.getRowNumber("J2S_Edit_Record_Grid", maskedColumns[i], "Name");
				assertTrue(GridHelper.isValuePresent("J2S_Edit_Record_Grid", rowNo, expectedValues[i], "Value"));
			}
			
			ButtonHelper.click("J2S_Edit_Cancel");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			if (ButtonHelper.isPresent("YesButton")) {
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=27, description="Test edit in Jump to Search screen for privileged user", groups = { "GDPRReference" })
	public void testPrivilegedUserJ2SEdit() throws Exception
	{
		try {
			// Validate that privileged user should be able to edit the reference table in J2S
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
			
			String[] maskedColumns = {"B_Number", "Time_Stamp", "Amount", "In_Route", "Extra1", "File_Size"};
			String decimalColumn = null;
			String updatedDecimalValue = "1020.50";
			String booleanColumn = "Is_Active";
			String updatedBooleanValue = "true";
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "J2S", 1);
			GenericHelper.collapseSearchFilterPanel();
			
			int row = GridHelper.getRowNumber("SearchGrid", "984566573766", "A_Number");
			GridHelper.clickRow("SearchGrid", row, 1);
			NavigationHelper.navigateToAction("Common Tasks", "Edit");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			GenericHelper.waitForElement("J2S_Edit_Save", searchScreenWaitSec);
			
			for (int i = 0; i < maskedColumns.length; i++) {
				boolean isEditable = false;
				int rowNo = GridHelper.getRowNumber("J2S_Edit_Record_Grid", maskedColumns[i], "Name");
				GridHelper.clickRow("J2S_Edit_Record_Grid", rowNo, "Value");
				
				if (TextBoxHelper.isPresent("J2S_Edit_Integer_TextBox"))
					isEditable = true;
				if(TextBoxHelper.isPresent("J2S_Edit_String_TextBox"))
					isEditable = true;
				if(TextBoxHelper.isPresent("J2S_Edit_DateTime_TextBox"))
					isEditable = true;
				if(TextBoxHelper.isPresent("J2S_Edit_Decimal_TextBox")) {
					isEditable = true;
					decimalColumn = maskedColumns[i];
					TextBoxHelper.type("J2S_Edit_Decimal_TextBox", updatedDecimalValue);
				}
				
				if(ComboBoxHelper.isPresent("J2S_Edit_Boolean_ComboBox"))
					isEditable = true;
				
				GridHelper.clickRow("J2S_Edit_Record_Grid", rowNo, "Type");
				if (!isEditable)
					FailureHelper.failTest("Column '" + maskedColumns[i] + "' is not editable for privileged user in J2S screen.");
			}
			
			int rowNo = GridHelper.getRowNumber("J2S_Edit_Record_Grid", booleanColumn, "Name");
			GridHelper.updateGridComboBox("J2S_Edit_Record_Grid", "J2S_Edit_Boolean_ComboBox", rowNo, "Value", updatedBooleanValue);
			
			ButtonHelper.click("J2S_Edit_Save");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			updatedDecimalValue = "1,020.5000";
			row = GridHelper.getRowNumber("SearchGrid", "984566573766", "A_Number");
			assertTrue(GridHelper.isValuePresent("SearchGrid", row, updatedDecimalValue, decimalColumn));
			assertTrue(GridHelper.isBooleanValuePresent("SearchGrid", true, row, booleanColumn));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=28, description="Test Export for masked data", groups = { "GDPRReference" })
	public void testUnprivilegedUserExportAsTxt() throws Exception
	{
		boolean tcFailed = false;
		
		try {
			// Verify the data is masked when Exported the masked data (Partial and All masked) when exported as TXT.
			StreamHelper stream = new StreamHelper();
			stream.createStream(path, fileName, sheetName, "Stream", 1);
			
			ControllerHelper controller = new ControllerHelper();
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
			
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.setTaskControllerCapability(path, fileName, sheetName, "TCCapability", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			SettingsHelper settings = new SettingsHelper();
			settings.updateSettings(path, fileName, sheetName, "SettingsTAB", 1);
	
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "ExportJ2SWithMask", 1);
			GenericHelper.collapseSearchFilterPanel();
			
			// Export Selected Rows
			String expectedValue = "984566573766	xxxxxxx547xx	0xxxxx2020 10:10:10	15	MOC	Outgoing	xxxxxxxxxxx	Y	1	Swixxxx1	Switch 2	Exxxxxx";
			exportSelectedRows(1, 3, "A_Number", expectedValue);
			
			// Export Configured Rows
			expectedValue = "984566573766	xxxxxxx547xx	0xxxxx2020 10:10:10	15	MOC	Outgoing	xxxxxx	Y	1	Swixxxx1	Switch 2	Exxxxxx";
			exportConfiguredRows(expectedValue);
			
			// Export All Rows
			expectedValue = "984566573766	xxxxxxx547xx	0xxxxx2020 10:10:10	15	MOC	Outgoing	xxxxxxxxxxx	true	1	Swixxxx1	Switch 2	Exxxxxx";
			exportAllRows(sheetName, "ExportTask", "masking_ref", true, expectedValue);
		} catch (AssertionError e) {
			tcFailed = true;
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			tcFailed = true;
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			if (tcFailed) {
				LoginHelper login = new LoginHelper();
				login.loginWithConfigPropertyDetails();
			}
		}
	}
	
	@Test(priority=29, description="Test Export for masked data", groups = { "GDPRReference" })
	public void testUnprivilegedUserExportAsCsv() throws Exception
	{
		try {
			// Verify the data is masked when Exported the masked data (Partial and All masked) when exported as CSV.
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			SettingsHelper settings = new SettingsHelper();
			settings.updateSettings(path, fileName, sheetName, "SettingsCSV", 1);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "ExportJ2SWithMask", 1);
			GenericHelper.collapseSearchFilterPanel();
			
			// Export Selected Rows
			String expectedValue = "\"1\",\"984566573766\",\"xxxxxxx547xx\",\"0xxxxx2020 10:10:10\",\"15\",\"MOC\",\"Outgoing\",\"xxxxxxxxxxx\",\"Y\",\"1\",\"Swixxxx1\",\"Switch 2\",\"Exxxxxx\"";
			exportSelectedRows(1, 3, "A_Number", expectedValue);
			
			// Export Configured Rows
			expectedValue = "1,\"984566573766\",\"xxxxxxx547xx\",\"0xxxxx2020 10:10:10\",\"15\",\"MOC\",\"Outgoing\",\"xxxxxx\",\"Y\",\"1\",\"Swixxxx1\",\"Switch 2\",\"Exxxxxx\""; 
			exportConfiguredRows(expectedValue);
			
			// Export All Rows
			expectedValue = "\"1\",\"984566573766\",\"xxxxxxx547xx\",\"0xxxxx2020 10:10:10\",\"15\",\"MOC\",\"Outgoing\",\"xxxxxxxxxxx\",\"true\",\"1\",\"Swixxxx1\",\"Switch 2\",\"Exxxxxx\"";
			exportAllRows(sheetName, "ExportTask", "masking_ref", false, expectedValue);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
		}
	}
	
	@
	
	Test(priority=30, description="Test Export for masked data", groups = { "GDPRReference" })
	public void testPrivilegedUserExport() throws Exception
	{
		try {
			// Validate that when a privileged user exports the masked fields in TD data should be exposed
			SettingsHelper settings = new SettingsHelper();
			settings.updateSettings(path, fileName, sheetName, "SettingsTAB", 1);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "ExportJ2SNoMask", 1);
			GenericHelper.collapseSearchFilterPanel();
			
			// Export Selected Rows
			String expectedValue = "984566573766	989435454754	02/22/2020 10:10:10	15	MOC	Outgoing	1020.500000	Y	1	Switch 1	Switch 2	Extra_1";
			exportSelectedRows(1, 3, "A_Number", expectedValue);
			
			// Export Configured Rows
			expectedValue = "984566573766	989435454754	02/22/2020 10:10:10	15	MOC	Outgoing	1020.5	Y	1	Switch 1	Switch 2	Extra_1";
			exportConfiguredRows(expectedValue);
			
			// Export All Rows
			expectedValue = "984566573766	989435454754	02/22/2020 10:10:10	15	MOC	Outgoing	1020.500000	true	1	Switch 1	Switch 2	Extra_1";
			exportAllRows(sheetName, "ExportTask", "masking_ref", true, expectedValue);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			ControllerHelper controller = new ControllerHelper();
			controller.stopTaskController("Task Controller");
			controller.stopStreamController();
		}
	}
	
	@Test(priority=31, description="Test enabling and disabling masking of a column", groups = { "GDPRReference" })
	public void testEnableDisableColumnMask() throws Exception
	{
		try {
			// Enable and Disable the columns in Masking Config Screen for reference tbl should work as expected.
			MaskingConfigurationHelper maskingConfig = new MaskingConfigurationHelper();
			maskingConfig.createMaskingConfiguration(path, fileName, sheetName, "MaskingConfigEdit", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "J2SDisableColumn", 1);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
		}
	}
	
	@Test(priority=32, description="Test delete of masking configuration", groups = { "GDPRReference" })
	public void testDelete() throws Exception
	{
		try {
			// Validate that after deleting the masking configuration masking should be removed from TD, TI J2S
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "MaskingConfigEdit", 1 );
			String name = excelData.get("Name").get(0);
			
			MaskingConfigurationHelper maskingConfig = new MaskingConfigurationHelper();
			int row = maskingConfig.navigateToMaskingConfiguration(name);
			
			if (row > 0) {
				NavigationHelper.delete("SearchGrid", name, "Name");
				
				JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
				jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "ExportJ2SNoMask", 1);
				
				LoginHelper login = new LoginHelper();
				login.login(path, fileName, sheetName, "UserLogin", 1);
				
				jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "ExportJ2SNoMask", 1);
			}
			else {
				FailureHelper.failTest("Masking Configuration '" + name + "' is not found.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}