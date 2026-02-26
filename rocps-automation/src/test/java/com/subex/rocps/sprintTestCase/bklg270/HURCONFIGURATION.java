package com.subex.rocps.sprintTestCase.bklg270;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
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
import com.subex.rocps.automation.utils.PSStringUtils;


public class HURCONFIGURATION extends PSAcceptanceTest {
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
              String ThresholdAmount;
              String ThresholdPeriod;
              String tadigCode;
              String SendMethod;
              String email;
              String endNumber;
              String increment;
              
              public HURCONFIGURATION(String path, String workBookName, String sheetName, String testCaseName)
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

              public void HURBasicDetailsRoamingConfiguration() throws Exception {
            	  			 
                             ComboBoxHelper.select(or.getProperty("PSDetail_ConfigurationType"), configurationType);
                             ButtonHelper.click("yes");
                             GenericHelper.waitForLoadmask();
                             ElementHelper.click(or.getProperty("PSDetail_SelectRoamingDefinition"));
                             GenericHelper.waitForLoadmask();
                             filterStatus(tadigCode); 
                             ElementHelper.click(or.getProperty("PSDetail_SelectBillProfile"));
                             GenericHelper.waitForLoadmask();
                             ElementHelper.click(or.getProperty("PSHUROUTRoamingConfiguration_BillProfileFilterID"));
                             GenericHelper.waitForLoadmask();
                             TextBoxHelper.type(or.getProperty("PSHUROUTRoamingConfiguration_BillProfileNameID"), billProfile);
                             GenericHelper.waitForLoadmask();
                             SearchHelper searchHelper = new SearchHelper();
                             searchHelper.clickSearch();
                             GridHelper.clickRow(or.getProperty("Detail_popUpWindowId"),or.getProperty("SearchGrid"), 1, 2);
                             ButtonHelper.click("ok");
                             ComboBoxHelper.select(or.getProperty("PSDetail_SelectVersion"), version);
              }

				
				  public void HURAdditionalDetails() throws Exception { 
					TextBoxHelper.type(or.getProperty("PSHUROUTRoamingConfiguration_ThresholdAmountID"),ThresholdAmount);
					GenericHelper.waitForLoadmask();
				  TextBoxHelper.type(or.getProperty("PSHUROUTRoamingConfiguration_ThresholdPeriodID"), ThresholdPeriod);
				  GenericHelper.waitForLoadmask();
				  ComboBoxHelper.select(or.getProperty("PSHUROUTRoamingConfiguration_SendMethodID"), SendMethod); 
				  GenericHelper.waitForLoadmask();
				  }
				 

				
				  public void HUREmailConfigurations() throws Exception {
					  PSStringUtils ps = new PSStringUtils();
					  String[] EmailArr = ps.stringSplitFirstLevel(email);
					  for (int i = 0; i < EmailArr.length; i++) {
						  GenericHelper.waitForLoadmask();
						  ButtonHelper.click("PSHUROUTRoamingConfiguration_AddEmailID"); 
						  GenericHelper.waitForLoadmask();
						  GridHelper.updateGridTextBox(or.getProperty("PSHUROUTRoamingConfiguration_EmailGridID"),
								  or.getProperty("PSHUROUTRoamingConfiguration_EnterEmailID"),i+1,
								  or.getProperty("PSHUROUTRoamingConfiguration_EmailAddressID"),EmailArr[i]);
						  GenericHelper.waitForLoadmask();
					  }
				  }
				 
          private void navigate() throws Exception {
                    try {
                     NavigationHelper.navigateToScreen("Roaming Configuration");
                     GenericHelper.waitForLoadmask();
						/* roamMap = excelHolderObj.dataMap(paramVal); */
                     initializeInstanceVariables(roamMap);
                     ElementHelper.waitForElement(or.getProperty("PSDetail_ClickFilterRoamingDefinition"),60);
                     ElementHelper.click(or.getProperty("PSDetail_ClickFilterRoamingDefinition"));
                     GenericHelper.waitInSeconds("3");
                     boolean isTadigCodePresent = ComboBoxHelper.containsValue(or.getProperty("PSDetail_SelectTadigRoamingDefinition"), tadigCode);
                     if (isTadigCodePresent) {
                     ComboBoxHelper.select(or.getProperty("PSDetail_SelectTadigRoamingDefinition"), tadigCode);
                     ButtonHelper.click("search");
                     ElementHelper.click(or.getProperty("PSDetail_ClickFilterConfiguration"));
                     ComboBoxHelper.select(or.getProperty("PSDetail_SelectConfigurationType"), configurationType);
                     ButtonHelper.click("search");
                     GenericHelper.waitForLoadmask();
                      } else {
                     Log4jHelper.logInfo("Tadig Code not Present");
                                                          }
                                          
                             } catch (Exception e) {
                                           throw e;
                             }
              }

         public void newRoamingConfiguration() throws Exception {
        	 
              navigate();
            // checking if Roaming Configuration is present for Tadig Code
              boolean isRoamingConfPresent = GridHelper.isValuePresent("searchGrid", tadigCode,"Tadig");
              if (!isRoamingConfPresent) {
                               genericHelperObj.clickNewAction(clientPartition);
                               GenericHelper.waitForLoadmask();
                               HURBasicDetailsRoamingConfiguration();
                               HURAdditionalDetails();
                               HUREmailConfigurations();
                               ButtonHelper.click("PSDetail_RoamingConfigurationSave");
                             } else {
                                           Log4jHelper.logInfo("Roaming Configuration Already Present");
                             }
              }

             
              
         public void initializeInstanceVariables(Map<String, String> map) throws Exception {
                             configurationType = ExcelHolder.getKey(map, "configurationType");
                             clientPartition = ExcelHolder.getKey(map, "Partition");
                             tadigCode =ExcelHolder.getKey(map, "TadigCode");
                               version = ExcelHolder.getKey(map, "Version");
                              ThresholdAmount = ExcelHolder.getKey(map, "ThresholdAmount"); 
                              ThresholdPeriod = ExcelHolder.getKey(map, "ThresholdPeriod");
                              SendMethod = ExcelHolder.getKey(map, "SendMethod");
                               email = ExcelHolder.getKey(map, "email");
                               billProfile = ExcelHolder.getKey(map, "billProfile");
              }

              public void clickNewAction(String clientPartition) throws Exception {
                             genericHelperObj.clickNewAction(clientPartition);
                             GenericHelper.waitForLoadmask();
              }

              public void filterStatus(String tadigCode) throws Exception {
                             GenericHelper.waitForLoadmask();
                             ElementHelper.click(or.getProperty("PSHUROUTRoamingConfiguration_TadigCodeFilterID"));
                             GenericHelper.waitForLoadmask();
                             ComboBoxHelper.select(or.getProperty("PSHUROUTRoamingConfiguration_TadigCodeID"), tadigCode);
                             GenericHelper.waitForLoadmask();
                             SearchHelper searchHelper = new SearchHelper();
                             searchHelper.clickSearch();
                             GridHelper.clickRow(or.getProperty("Detail_popUpWindowId"), or.getProperty("SearchGrid"), 1, 2);
                             ButtonHelper.click("ok");
              }
}
