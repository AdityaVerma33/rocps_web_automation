package com.subex.rocps.sprintTestCase.bklg113;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;

import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;

import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.componentHelpers.SearchHelper;
import com.subex.automation.helpers.config.OR_Reader;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.account.AccountActionImpl;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.account.AccountDetailImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.sprintTestCase.bklg201.imsiManagementExecution;

public class NRTRDERoamingConfiguration extends PSAcceptanceTest {
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> roamConfExcelMap = null;
	protected Map<String, String> roamMap = null;
	protected ExcelHolder excelHolderObj = null;
	String path;
	String workBookName;
	String sheetName;
	String testCaseName;
	int colSize;
	int paramVal;

	String clientPartition;

	static String dependantEntities;

	String configurationType;
	String roamingDefinition;
	String billProfile;
	String version;
	String eventsPerFile;
	String fileSize;
	String dailyFileLimit;
	String FTP;
	String tadigCode;
	String startSequence;
	String callAgeTimeFrame;
	String excludeSMS;
	String excludeGPRS;
	String cDStartSequence;
	String tDStartSequence;
	String endNumber;
	String increment;

	@Test
	public NRTRDERoamingConfiguration(String path, String workBookName, String sheetName, String testCaseName)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		roamConfExcelMap = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(roamConfExcelMap);
		colSize = excelHolderObj.totalColumns();
	}

	public void nrtrdeBasicDetailsRoamingConfiguration() throws Exception {
		ComboBoxHelper.select("PSDetail_ConfigurationType", configurationType);
		ButtonHelper.click("yes");
		ElementHelper.click("PSDetail_SelectRoamingDefinition");
		filterStatus(tadigCode);
		ButtonHelper.click("PSDetail_SelectBillProfile");
		ElementHelper.click("grid_header_filter_value_searchGrid_pbipName");
		TextBoxHelper.type("pbipName", billProfile);
		SearchHelper searchHelper = new SearchHelper();
		searchHelper.clickSearch();
		GridHelper.clickRow("Detail_popUpWindowId", "SearchGrid", 1, 2);
		ButtonHelper.click("ok");
		ComboBoxHelper.select("PSDetail_SelectVersion", version);
	}

	public void nrtrdeFileSequenceDetailsRoamingConfiguration() throws Exception {
		TextBoxHelper.type("PSDetail_FileSequenceStartNumber", startSequence);
		TextBoxHelper.type("PSDetail_FileSequenceEndNumber", endNumber);
	}

	public void ntrtrdeAdvancedDetails() throws Exception {
		TextBoxHelper.type("PSDetail_EventsPerFile", eventsPerFile);
		TextBoxHelper.type("PSDetail_FileSize", fileSize);
		TextBoxHelper.type("PSDetail_DailyFileLimit", dailyFileLimit);
		TextBoxHelper.type("PSDetail_CallAgeTimeFrame", callAgeTimeFrame);
		ComboBoxHelper.select("PSDetail_FTPLocation", FTP);
		boolean excludeSMSMTEvents = Boolean.valueOf(excludeSMS);
		boolean excludeGPRSEvents = Boolean.valueOf(excludeGPRS);
		if (excludeSMSMTEvents == false && excludeGPRSEvents == false) {
			CheckBoxHelper.uncheck("PSDetail_ExcludeSMSFlag");
			CheckBoxHelper.uncheck("PSDetail_ExcludeGPRSFlag");
		} else if (excludeSMSMTEvents == true && excludeGPRSEvents == true) {

			CheckBoxHelper.check("PSDetail_ExcludeSMSFlag");
			CheckBoxHelper.check("PSDetail_ExcludeGPRSFlag");
		}
		ButtonHelper.click("PSDetail_RoamingConfigurationSave");
	}

	public void newRoamingConfiguration() throws Exception {
		navigate();
		// checking if Roaming Configuration is present for Tadig Code
		boolean isRoamingConfPresent = GridHelper.isValuePresent("searchGrid", tadigCode, "Tadig");
		if (!isRoamingConfPresent) {
			genericHelperObj.clickNewAction(clientPartition);
			GenericHelper.waitForLoadmask();
			nrtrdeBasicDetailsRoamingConfiguration();
			nrtrdeFileSequenceDetailsRoamingConfiguration();
			ntrtrdeAdvancedDetails();
		} else {
			Log4jHelper.logInfo("Roaming Configuration Already Present");
		}
	}

	public void editRoamingConfiguration() throws Exception {

		navigate();
		// checking if tadig code is present in NRTRDE roaming definition
		boolean isRoamingConfPresent = GridHelper.isValuePresent("searchGrid", tadigCode);
		if (isRoamingConfPresent) {
			NavigationHelper.navigateToAction("Common Tasks", "Edit");
			GenericHelper.waitForLoadmask();
			nrtrdeFileSequenceDetailsRoamingConfiguration();
			ntrtrdeAdvancedDetails();
		} else {
			Log4jHelper.logInfo("Roaming Configuration Not Present");
		}
	}

	private void navigate() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Roaming Configuration");
			GenericHelper.waitForLoadmask();
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				roamMap = excelHolderObj.dataMap(paramVal);
				initializeInstanceVariables(roamMap);
				ElementHelper.click("PSDetail_ClickFilterRoamingDefinition");
				GenericHelper.waitInSeconds("3");
				boolean isTadigCodePresent = ComboBoxHelper.containsValue("PSDetail_SelectTadigRoamingDefinition",
						tadigCode);
				if (isTadigCodePresent) {
					ComboBoxHelper.select("PSDetail_SelectTadigRoamingDefinition", tadigCode);
					ButtonHelper.click("search");
					ElementHelper.click("PSDetail_ClickFilterConfiguration");
					ComboBoxHelper.select("PSDetail_SelectConfigurationType", configurationType);
					ButtonHelper.click("search");
					GenericHelper.waitForLoadmask();
				} else {
					Log4jHelper.logInfo("Tadig Code not Present");
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public void deleteRoamingConfiguration() throws Exception {
		try {
			// clientPartition = "Common";

			NavigationHelper.navigateToScreen("Roaming Configuration");
			GenericHelper.waitForLoadmask();

			genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
			ElementHelper.click(or.getProperty("PS_RoamingConf_TadigCodeFilter"));
			GenericHelper.waitInSeconds("3");
			boolean isTadigCodePresent = ComboBoxHelper.containsValue("tadigCode_gwt_uid_", tadigCode);

			System.out.println(isTadigCodePresent);
			if (isTadigCodePresent) {

				ComboBoxHelper.select("tadigCode_gwt_uid_", tadigCode);
				ElementHelper.click(or.getProperty("PS_RoamingDef_search"));
				GenericHelper.waitForLoadmask();
				boolean isRoamingConfPresent = GridHelper.isValuePresent("searchGrid", tadigCode);
				if (isRoamingConfPresent) {
					genericHelperObj.clickDeleteOrUnDeleteAction(tadigCode, "Tadig Code", "Delete");
					GridHelper.clickRow("searchGrid", 1, 1);
					NavigationHelper.navigateToAction("Common Tasks", "Delete");
					GenericHelper.waitForLoadmask();
					genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
					assertTrue(GridHelper.isValuePresent("SearchGrid", tadigCode, "Tadig Code"), tadigCode);
					Log4jHelper.logInfo("Roaming Configuration is deleted successfully with name " + tadigCode);

				} else {
					Log4jHelper.logInfo("Roaming Configuration is not available with name " + tadigCode);
				}

			} else {
				Log4jHelper.logInfo("Tadig Code not Present");
			}
		} catch (Exception e) {

			throw e;
		}
	}

	public void UndeleteRoamingConfiguration() throws Exception {
		try {
			clientPartition = "Common";

			NavigationHelper.navigateToScreen("Roaming Configuration");
			GenericHelper.waitForLoadmask();

			genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
			ElementHelper.click(or.getProperty("PS_RoamingConf_TadigCodeFilter"));
			GenericHelper.waitInSeconds("3");
			boolean isTadigCodePresent = ComboBoxHelper.containsValue("tadigCode_gwt_uid_", tadigCode);

			System.out.println(isTadigCodePresent);
			if (isTadigCodePresent) {

				ComboBoxHelper.select("tadigCode_gwt_uid_", tadigCode);
				ElementHelper.click(or.getProperty("PS_RoamingDef_search"));
				GenericHelper.waitForLoadmask();
				boolean isRoamingConfPresent = GridHelper.isValuePresent("searchGrid", tadigCode);
				if (isRoamingConfPresent) {
					genericHelperObj.clickDeleteOrUnDeleteAction(tadigCode, "Tadig Code", "UnDelete");
					GridHelper.clickRow("searchGrid", 1, 1);
					NavigationHelper.navigateToAction("Common Tasks", "Undelete");
					GenericHelper.waitForLoadmask();
					genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
					assertTrue(GridHelper.isValuePresent("SearchGrid", tadigCode, "Tadig Code"), tadigCode);
					Log4jHelper.logInfo("Roaming Configuration is undeleted successfully with name " + tadigCode);

				} else {
					Log4jHelper.logInfo("Roaming Configuration is not available with name " + tadigCode);
				}

			} else {
				Log4jHelper.logInfo("Tadig Code not Present");
			}
		} catch (Exception e) {

			throw e;
		}
	}

	public void initializeInstanceVariables(Map<String, String> map) throws Exception {
		configurationType = ExcelHolder.getKey(map, "configurationType");
		clientPartition = ExcelHolder.getKey(map, "Partition");
		tadigCode = ExcelHolder.getKey(map, "TadigCode");
		version = ExcelHolder.getKey(map, "Version");
		eventsPerFile = ExcelHolder.getKey(map, "EventsPerFile");
		fileSize = ExcelHolder.getKey(map, "FileSize");
		dailyFileLimit = ExcelHolder.getKey(map, "DailyFileLimit");
		FTP = ExcelHolder.getKey(map, "FTPLocation");
		startSequence = ExcelHolder.getKey(map, "StartSequence");
		callAgeTimeFrame = ExcelHolder.getKey(map, "CallAgeTimeFrame");
		excludeSMS = ExcelHolder.getKey(map, "ExcludeSMS-MTEvents");
		excludeGPRS = ExcelHolder.getKey(map, "ExcludeGPRSEvents");
		endNumber = ExcelHolder.getKey(map, "EndNumber");
		billProfile = ExcelHolder.getKey(map, "BillProfile");
	}

	public void clickNewAction(String clientPartition) throws Exception {
		genericHelperObj.clickNewAction(clientPartition);
		GenericHelper.waitForLoadmask();
	}

	public void filterStatus(String tadigCode) throws Exception {
		GenericHelper.waitForLoadmask();
		ElementHelper.click("grid_column_header_filtersearchGrid_tadigCode$ptdgCode");
		GenericHelper.waitForLoadmask();
		ComboBoxHelper.select("tadigCode_gwt_uid_", tadigCode);
		GenericHelper.waitForLoadmask();
		SearchHelper searchHelper = new SearchHelper();
		searchHelper.clickSearch();
		GridHelper.clickRow("Detail_popUpWindowId", "SearchGrid", 1, 2);
		ButtonHelper.click("ok");
	}

}
