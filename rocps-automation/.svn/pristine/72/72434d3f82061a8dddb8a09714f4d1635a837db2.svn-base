package com.subex.rocps.sprintTestCase.bklg178;





import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;

import com.subex.automation.helpers.component.ButtonHelper;

import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;

import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;

import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.config.OR_Reader;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.account.AccountActionImpl;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.account.AccountDetailImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class RoamingDefinition extends PSAcceptanceTest {
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
	public RoamingDefinition(String path, String workBookName, String sheetName, String testCaseName)
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


	public void newRoamingDefinition() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Roaming Definition");

			

			
			GenericHelper.waitForLoadmask();
			initializeInstanceVariables();
			ElementHelper.click(or.getProperty("PS_RoamingDef_TadigCodeFilter"));
			GenericHelper.waitInSeconds("3");
			//checking if tadig code is available in the dropdown
			boolean isTadigCodePresent=ComboBoxHelper.containsValue("tadigCode_gwt_uid_", tadigCode);
			
			if(isTadigCodePresent){
				
				ComboBoxHelper.select("tadigCode_gwt_uid_", tadigCode);
				ElementHelper.click(or.getProperty("PS_RoamingDef_search"));
				GenericHelper.waitForLoadmask();
				//checking if tadig code is used for a roaming definition
				boolean isRoamingDefPresent=GridHelper.isValuePresent("searchGrid", tadigCode);
						if(!isRoamingDefPresent){
							//NavigationHelper.navigateToAction("Common Tasks", "New");
							genericHelperObj.clickNewAction("Common");
							GenericHelper.waitForLoadmask();
							
							ComboBoxHelper.select("prdfAgreementType_gwt_uid_", typeOfAgreement);
							ComboBoxHelper.select("tadigCode_gwt_uid_", tadigCode);

							TextBoxHelper.type("prdfMcc", mcc);
							TextBoxHelper.type("prdfMnc", mnc);
							TextBoxHelper.type("prdfNetwork", network);
							TextBoxHelper.type("prdfOperator", operator);
							ComboBoxHelper.select("prdfStatus_gwt_uid_", status);
							if (typeOfAgreement == "Home") {
								ButtonHelper.click("roamingDefinitionDetail.save");
							} else {
								TextBoxHelper.type("prdfCdStartDttm", fromDate);
								TextBoxHelper.type("prdfCdEndDttm", toDate);
								if (typeOfAgreement == "Hub") {
									ComboBoxHelper.select("secondarytadigCode_gwt_uid", secondaryTadig);
								}

								ButtonHelper.click("roamingDefinitionDetail.save");
							}

						}
						else {
							Log4jHelper.logInfo("Roaming Definition Already Present");
						}
				
			} else {
				Log4jHelper.logInfo("Tadig Code not Present");
			}

		} catch (Exception e) {

			throw e;
		}
	}

	public void editRoamingDefinition() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Roaming Definition");
			GenericHelper.waitForLoadmask();
			ElementHelper.click(or.getProperty("PS_RoamingDef_TadigCodeFilter"));
			GenericHelper.waitInSeconds("3");
			boolean isTadigCodePresent=ComboBoxHelper.containsValue("tadigCode_gwt_uid_", tadigCode);
			
			if(isTadigCodePresent){
				
				ComboBoxHelper.select("tadigCode_gwt_uid_", tadigCode);
				ElementHelper.click(or.getProperty("PS_RoamingDef_search"));
				GenericHelper.waitForLoadmask();
				boolean isRoamingDefPresent=GridHelper.isValuePresent("searchGrid", tadigCode);
						if(isRoamingDefPresent){
							GridHelper.clickRow("searchGrid", 1, 1);
							NavigationHelper.navigateToAction("Common Tasks", "Edit");
							GenericHelper.waitForLoadmask();
							TextBoxHelper.type("prdfMcc", mcc);
							TextBoxHelper.type("prdfMnc", mnc);
							TextBoxHelper.type("prdfNetwork", network);
							TextBoxHelper.type("prdfOperator", operator);
							ComboBoxHelper.select("prdfStatus_gwt_uid_", "Inactive");
							ButtonHelper.click("roamingDefinitionDetail.save");
						}
						else {
							Log4jHelper.logInfo("Roaming Definition Not Present");
						}
				
			} else {
				Log4jHelper.logInfo("Tadig Code not Present");
			}


		} catch (Exception e) {

			throw e;
		}
	}

	public void deleteRoamingDefinition() throws Exception {
		try {
			clientPartition="Common";
			
			NavigationHelper.navigateToScreen("Roaming Definition");
			GenericHelper.waitForLoadmask();
			
			genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
			ElementHelper.click(or.getProperty("PS_RoamingDef_TadigCodeFilter"));
			GenericHelper.waitInSeconds("3");
			boolean isTadigCodePresent=ComboBoxHelper.containsValue("tadigCode_gwt_uid_", tadigCode);
			
			System.out.println(isTadigCodePresent);
			if(isTadigCodePresent){
				
				ComboBoxHelper.select("tadigCode_gwt_uid_", tadigCode);
				ElementHelper.click(or.getProperty("PS_RoamingDef_search"));
				GenericHelper.waitForLoadmask();
				boolean isRoamingDefPresent=GridHelper.isValuePresent("searchGrid", tadigCode);
						if(isRoamingDefPresent){
							genericHelperObj.clickDeleteOrUnDeleteAction(tadigCode, "Tadig Code", "Delete");
							GridHelper.clickRow("searchGrid", 1, 1);
							NavigationHelper.navigateToAction("Common Tasks", "Delete");
							GenericHelper.waitForLoadmask();
							genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
							assertTrue(GridHelper.isValuePresent("SearchGrid", tadigCode, "Tadig Code"), tadigCode);
							Log4jHelper.logInfo("Roaming Definition is deleted successfully with name " + tadigCode);

						} else {
							Log4jHelper.logInfo("Account is not available with name " + tadigCode);
						}
						
				
			} else {
				Log4jHelper.logInfo("Tadig Code not Present");
			}
		} catch (Exception e) {

			throw e;
		}
	}
	public void initializeInstanceVariables() {

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
	public void clickNewAction(String clientPartition) throws Exception {
		genericHelperObj.clickNewAction(clientPartition);
		GenericHelper.waitForLoadmask();
	}
	


}

