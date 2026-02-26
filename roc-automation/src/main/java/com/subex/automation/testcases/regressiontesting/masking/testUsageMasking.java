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
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class testUsageMasking extends testMasking {
	
	final String sheetName = "UsageMasking";
	
	public testUsageMasking() throws Exception {
		super();
	}
	
	@Test(priority=33, description="Configure Prerequisites", groups = { "GDPRUsage" })
	public void prerequisite()throws Exception
	{
		try {
			UsagePrerequisite prerequisite = new UsagePrerequisite();
			prerequisite.runPrerequisite();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=34, description="Test masking of numeric fields", dependsOnMethods = { "prerequisite" }, groups = { "GDPRUsage" })
	public void testMaskingNumber() throws Exception
	{
		try {
			// Validate that all the values should be masked when numeric datatypes are considered
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			UserHelper user = new UserHelper();
			user.createUser(path, fileName, sheetName, "Users", 1);
			
			MaskingConfigurationHelper masking = new MaskingConfigurationHelper();
			masking.createMaskingConfiguration(path, fileName, sheetName, "MaskingNumber", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "MaskingNumberJ2S", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
		}
	}
	
	@Test(priority=35, description="Test masking of other datatypes", dependsOnMethods = { "prerequisite", "testMaskingNumber" }, groups = { "GDPRUsage" })
	public void testMaskingOtherDatatypes() throws Exception
	{
		try {
			// Verify the Masking for different Datatypes String, Timestamp
			MaskingConfigurationHelper masking = new MaskingConfigurationHelper();
			masking.createMaskingConfiguration(path, fileName, sheetName, "MaskingOthers", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "MaskingOthersJ2S", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
		}
	}
	
	@Test(priority=36, description="Test masking of special characters", dependsOnMethods = { "testMaskingNumber", "testMaskingOtherDatatypes" }, groups = { "GDPRUsage" })
	public void testMaskingSpecialCharacters() throws Exception
	{
		try {
			// Verify the masking for data having spaces and symbols
			MaskingConfigurationHelper masking = new MaskingConfigurationHelper();
			masking.createMaskingConfiguration(path, fileName, sheetName, "MaskingSpecialChar", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "MaskingSpecialCharJ2S", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
		}
	}
	
	@Test(priority=37, description="Test masking of first n characters", dependsOnMethods = { "testMaskingOtherDatatypes", "testMaskingSpecialCharacters" }, groups = { "GDPRUsage" })
	public void testMaskingFirstNCharacters() throws Exception
	{
		try {
			// Verify the masking for first n characters
			MaskingConfigurationHelper masking = new MaskingConfigurationHelper();
			masking.createMaskingConfiguration(path, fileName, sheetName, "MaskingFirstNChar", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "MaskingFirstNCharJ2S", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
		}
	}
	
	@Test(priority=38, description="Test masking of last n characters", dependsOnMethods = { "testMaskingSpecialCharacters", "testMaskingFirstNCharacters" }, groups = { "GDPRUsage" })
	public void testMaskingLastNCharacters() throws Exception
	{
		try {
			// Verify the masking for last n characters
			MaskingConfigurationHelper masking = new MaskingConfigurationHelper();
			masking.createMaskingConfiguration(path, fileName, sheetName, "MaskingLastNChar", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "MaskingLastNCharJ2S", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
		}
	}
	
	@Test(priority=39, description="Test masking of first but n characters", dependsOnMethods = { "testMaskingFirstNCharacters", "testMaskingLastNCharacters" }, groups = { "GDPRUsage" })
	public void testMaskingExceptFirst5Characters() throws Exception
	{
		try {
			// Verify that user has option to select masking for All except first n characters and able to configure
			// against the required field column in TD and data is displayed accordingly
			MaskingConfigurationHelper masking = new MaskingConfigurationHelper();
			masking.createMaskingConfiguration(path, fileName, sheetName, "MaskingExceptFirst5Char", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "MaskingExceptFirst5CharJ2S", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
		}
	}
	
	@Test(priority=40, description="Test masking of last but n characters", dependsOnMethods = { "testMaskingLastNCharacters", "testMaskingExceptFirst5Characters" }, groups = { "GDPRUsage" })
	public void testMaskingExceptLast5Characters() throws Exception
	{
		try {
			// Verify that user has option to select masking for All except last n characters and able to configure
			// against the required field column in TD and data is displayed accordingly
			MaskingConfigurationHelper masking = new MaskingConfigurationHelper();
			masking.createMaskingConfiguration(path, fileName, sheetName, "MaskingExceptLast5Char", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "MaskingExceptLast5CharJ2S", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
		}
	}
	
	@Test(priority=41, description="Test masking except for specific characters", dependsOnMethods = { "testMaskingExceptFirst5Characters", "testMaskingExceptLast5Characters" }, groups = { "GDPRUsage" })
	public void testMaskingExceptSpecificCharacters() throws Exception
	{
		try {
			// Verify the masking for All except specific position against the required field column in TD and data is displayed accordingly
			MaskingConfigurationHelper masking = new MaskingConfigurationHelper();
			masking.createMaskingConfiguration(path, fileName, sheetName, "MaskingExceptSpecificChar", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "MaskingExceptSpecificCharJ2S", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
		}
	}
	
	@Test(priority=42, description="Verify View Raw Record", dependsOnMethods = { "testMaskingExceptLast5Characters", "testMaskingExceptSpecificCharacters" }, groups = { "GDPRUsage" })
	public void testViewRawRecord() throws Exception
	{
		try {
			// Verify that a validation message is displayed when user not having the privileges to see unmasked data on View Raw record 
			ControllerHelper controller = new ControllerHelper();
			controller.startServerService();
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.viewRawRecord(path, fileName, sheetName, "ViewRawRecord", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			jumpToSearch.navigateToJ2S("masking_usg");
			jumpToSearch.setDates("06/01/2020 00:00:00", "06/30/2020 23:59:59");
			GridHelper.clickRow("SearchGrid", 1, 1);
			NavigationHelper.navigateToAction("Drill Down", "View Raw Record");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(PopupHelper.isTextPresent("You do not have sufficient privileges to view this data"));
			ButtonHelper.click("OKButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
			
			ControllerHelper controller = new ControllerHelper();
			controller.stopServerService();
		}
	}
	
	@Test(priority=43, description="Test Copy for masked data", dependsOnMethods = { "testMaskingExceptSpecificCharacters" }, groups = { "GDPRUsage" })
	public void testCopy() throws Exception
	{
		boolean tcFailed = false;
		
		try {
			// Verify the data is masked when copied the masked data
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "ExportJ2S", 1);
			GenericHelper.collapseSearchFilterPanel();
			
			// Copy Selected Cell
			String expectedContent = "xxxxxxx737xx";
			copySelectedCell(1, "B_Number", expectedContent);
			
			// Copy Selected Column
			expectedContent = "xxxxxxx737xx	\nxxxxxxx834xx	\nxxxxxxx834xx	\nxxxxxxx737xx	\nxxxxxxx834xx	\nxxxxxxx834xx	\nxxxxxxx737xx";
			copySelectedColumn(1, "B_Number", expectedContent);
			
			// Copy Selected Row
			expectedContent = "980343848454	xxxxxxx737xx	0xxxxx2020 01:18:04	10	MTC	Incoming	xxxxxxxx	true	10	Swixxxx1	Switch 2	Exxxxxx";
			copySelectedRow(1, "A_Number", expectedContent);
			
			// Copy All Rows
			copyAllRows(expectedContent);
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
	
	@Test(priority=44, description="Test Export Selected rows for masked data", dependsOnMethods = { "testMaskingExceptSpecificCharacters" }, groups = { "GDPRUsage" })
	public void testExportAsTxt() throws Exception
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
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "ExportJ2S", 1);
			GenericHelper.collapseSearchFilterPanel();
			
			// Export Selected Rows
			String expectedValue = "980343848454	xxxxxxx737xx	0xxxxx2020 01:18:04	10	MTC	Incoming	xxxxxxxxxx	Y	10	Swixxxx1	Switch 2	Exxxxxx";
			exportSelectedRows(1, 3, "A_Number", expectedValue);
			
			// Export Configured Rows
			expectedValue = "980343848454	xxxxxxx737xx	0xxxxx2020 01:18:04	10	MTC	Incoming	xxxxx	Y	10	Swixxxx1	Switch 2	Exxxxxx";
			exportConfiguredRows(expectedValue);
			
			// Export All Rows
			expectedValue = "980343848454	xxxxxxx737xx	0xxxxx2020 01:18:04	10	MTC	Incoming	xxxxxxxxxx	true	10	Swixxxx1	Switch 2	Exxxxxx";
			exportAllRows(sheetName, "ExportTask", "masking_usg", true, expectedValue);
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
	
	@Test(priority=45, description="Test Export Selected rows for masked data", dependsOnMethods = { "testMaskingExceptSpecificCharacters" }, groups = { "GDPRUsage" })
	public void testExportAsCsv() throws Exception
	{
		boolean tcFailed = false;
		
		try {
			// Verify the data is masked when Exported the masked data (Partial and All masked) when exported as CSV.
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			SettingsHelper settings = new SettingsHelper();
			settings.updateSettings(path, fileName, sheetName, "SettingsCSV", 1);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "ExportJ2S", 1);
			GenericHelper.collapseSearchFilterPanel();
			
			// Export Selected Rows
			String expectedValue = "\"980343848454\",\"xxxxxxx737xx\",\"0xxxxx2020 01:18:04\",\"10\",\"MTC\",\"Incoming\",\"xxxxxxxxxx\",\"Y\",\"10\",\"Swixxxx1\",\"Switch 2\",\"Exxxxxx\"";
			exportSelectedRows(1, 3, "A_Number", expectedValue);
			
			// Export Configured Rows
			expectedValue = "\"980343848454\",\"xxxxxxx737xx\",\"0xxxxx2020 01:18:04\",\"10\",\"MTC\",\"Incoming\",\"xxxxx\",\"Y\",\"10\",\"Swixxxx1\",\"Switch 2\",\"Exxxxxx\"";
			exportConfiguredRows(expectedValue);
			
			// Export All Rows
			expectedValue = "\"980343848454\",\"xxxxxxx737xx\",\"0xxxxx2020 01:18:04\",\"10\",\"MTC\",\"Incoming\",\"xxxxxxxxxx\",\"true\",\"10\",\"Swixxxx1\",\"Switch 2\",\"Exxxxxx\"";
			exportAllRows(sheetName, "ExportTask", "masking_usg", true, expectedValue);
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
			
			ControllerHelper controller = new ControllerHelper();
			controller.stopTaskController("Task Controller");
			controller.stopStreamController();
		}
	}
	
	@Test(priority=46, description="Test masking for privileged user.", dependsOnMethods = { "testMaskingExceptSpecificCharacters" }, groups = { "GDPRUsage" })
	public void testMaskingPrivilegedUser() throws Exception
	{
		try {
			// Validate that when logged in through a privileged user then masked data should be exposed
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "J2SPrivilegedUser", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=47, description="Test enabling and disabling masking of a column", dependsOnMethods = { "testMaskingExceptSpecificCharacters", "testMaskingPrivilegedUser" }, groups = { "GDPRUsage" })
	public void testEnableDisableColumnMask() throws Exception
	{
		try {
			// Enable and Disable the columns in Masking Config Screen for usage tbl should work as expected.
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
	
	@Test(priority=48, description="Test delete of masking configuration", dependsOnMethods = { "testMaskingExceptSpecificCharacters", "testEnableDisableColumnMask" }, groups = { "GDPRUsage" })
	public void testDelete() throws Exception
	{
		try {
			// Validate that after deleting the masking configuration masking should be removed from TD, TI J2S
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "MaskingExceptSpecificChar", 1 );
			String name = excelData.get("Name").get(0);
			
			MaskingConfigurationHelper maskingConfig = new MaskingConfigurationHelper();
			int row = maskingConfig.navigateToMaskingConfiguration(name);
			
			if (row > 0) {
				NavigationHelper.delete("SearchGrid", name, "Name");
				
				JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
				jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "J2SPrivilegedUser", 1);
				
				LoginHelper login = new LoginHelper();
				login.login(path, fileName, sheetName, "UserLogin", 1);
				
				jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "J2SPrivilegedUser", 1);
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