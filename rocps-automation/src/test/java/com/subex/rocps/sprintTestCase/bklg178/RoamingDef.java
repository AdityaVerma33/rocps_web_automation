package com.subex.rocps.sprintTestCase.bklg178;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.config.OR_Reader;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class RoamingDef extends PSAcceptanceTest {
	OR_Reader orData = new OR_Reader();
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> roamDefExcelMap = null;
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
	
	String typeOfAgreement;
	String tadigCode;
	String mcc;
	String mnc;
	String network;
	String operator;
	String status;
	String fromDate; 
	String toDate;
	String secondaryTadig;
 
	@Test
	public RoamingDef(String path, String workBookName, String sheetName, String testCaseName)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		roamDefExcelMap = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(roamDefExcelMap);
		colSize = excelHolderObj.totalColumns();
	}
	
	public void newRoamingDef() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Roaming Definition");
			GenericHelper.waitForLoadmask();
			initializeInstanceVariables();
			//ElementHelper.click("PSRoamingDefinition_ClickFilterRoamingDefinition");
			PSSearchGridHelper.gridFilterSearchWithComboBox("PSRoamingDefinition_TadiCodeID", tadigCode , "Tadig code");
			boolean isTadigCodePresent=GridHelper.isValuePresent("SearchGrid",tadigCode , "Tadig code");
			if(!isTadigCodePresent){
				NavigationHelper.navigateToAction("Common Tasks", "New");
				GenericHelper.waitForLoadmask();
				ComboBoxHelper.select("PSRoamingDefinition_SelectCommonPartition","Common");
				GenericHelper.waitForLoadmask();
				ElementHelper.click("PSRoamingDefinition_SavePartition");
				GenericHelper.waitInSeconds("5");
				ComboBoxHelper.select("PSRoamingDefinition_TypeofAgreement", typeOfAgreement);
				ComboBoxHelper.select("PSRoamingDefinition_TadiCodeID", tadigCode);
				TextBoxHelper.type("PSRoamingDefinition_MCC", mcc);
				TextBoxHelper.type("PSRoamingDefinition_MNC", mnc);
				TextBoxHelper.type("PSRoamingDefinition_Network", network);
				TextBoxHelper.type("PSRoamingDefinition_Operator",operator);
				ComboBoxHelper.select("PSRoamingDefinition_Status", status);
				if (typeOfAgreement == "Home") {
					ButtonHelper.click("PSRoamingDefinition_RoamingDefinitionSave");
				} else {
					TextBoxHelper.type("PSRoamingDefinition_CDStartDate", fromDate);
					TextBoxHelper.type("PSRoamingDefinition_CDEndDate", toDate);
					if (typeOfAgreement == "Hub") {
						ComboBoxHelper.select("PSRoamingDefinition_SecondaryTadigID", secondaryTadig);
					}

					ButtonHelper.click("PSRoamingDefinition_RoamingDefinitionSave");
					GenericHelper.waitInSeconds("5");
				}
				
				
			}
			else {
				Log4jHelper.logInfo("Tadig Code not Present");
			}
		}
		catch (Exception e)
		{
			
		}

     }
	public void editRoamingDef() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Roaming Definition");
			GenericHelper.waitForLoadmask();
			initializeInstanceVariables();
			//ElementHelper.click("PSRoamingDefinition_ClickFilterRoamingDefinition");
			PSSearchGridHelper.gridFilterSearchWithComboBox("PSRoamingDefinition_TadiCodeID", tadigCode , "Tadig code");
			boolean isTadigCodePresent=GridHelper.isValuePresent("SearchGrid",tadigCode , "Tadig code");
			if(isTadigCodePresent){
				//GridHelper.clickRow("tadigCode_gwt_uid_", "INDTF");
				GridHelper.clickRow("searchGrid", 1, "Tadig code" );
				NavigationHelper.navigateToAction("Common Tasks", "Edit");
				GenericHelper.waitInSeconds("5");			
				ComboBoxHelper.select("PSRoamingDefinition_Status", status);
				TextBoxHelper.type("PSRoamingDefinition_MCC", mcc);
				TextBoxHelper.type("PSRoamingDefinition_MNC", mnc);
				TextBoxHelper.type("PSRoamingDefinition_CDEndDate", toDate);
				ButtonHelper.click("PSRoamingDefinition_RoamingDefinitionSave");	
				GenericHelper.waitInSeconds("5");
		}
		}
		catch (Exception e)
		{
			throw e;
		}
	
	}
	public void initializeInstanceVariables() throws Exception {
		
		roamMap=excelHolderObj.dataMap(0);
		clientPartition = roamMap.get("Partition");
		typeOfAgreement = roamMap.get("TypeOfAgreement");
		tadigCode = roamMap.get("TadigCode");
		mcc  = roamMap.get("MCC");
		mnc  = roamMap.get("MNC");
		network = roamMap.get("Network");
		operator = roamMap.get("Operator");
		status = roamMap.get("Status");
		fromDate = roamMap.get("FromDate");
		toDate = roamMap.get("ToDate");
		secondaryTadig = roamMap.get("SecondaryTadig");
		
	}
}
