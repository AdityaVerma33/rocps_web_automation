package com.subex.rocps.sprintTestCase.bklg234;

import java.util.ArrayList;
import java.util.Map;

import org.testng.Assert;

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


public class TAPErroredandExcludedRecordsSearchScreen extends PSAcceptanceTest {
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

              String streamStage;
              String view;
              String RecipientTadig;
            
              
              public TAPErroredandExcludedRecordsSearchScreen(String path, String workBookName, String sheetName, String testCaseName)
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

              public void TAPFilters() throws Exception {
            	  			 
                             ComboBoxHelper.select(or.getProperty("TAPStreamStageID"), streamStage);
                             GenericHelper.waitForLoadmask();
                             ButtonHelper.click(or.getProperty("TAPSearchID"));
                             
                             int rows = GridHelper.getRowCount(or.getProperty("TAPErrorRecordsSearchGridID"));
                             GenericHelper.waitForLoadmask();
                         	 Assert.assertTrue(rows>0, "No Records Found");
                                                  
                                  
              }
				 
          private void navigate() throws Exception {
                    try {
                     NavigationHelper.navigateToScreen("TAP Out Errored & Excluded Records");
                     GenericHelper.waitForLoadmask();
                     roamMap = excelHolderObj.dataMap(paramVal);
                     initializeInstanceVariables(roamMap);
                     
                    } catch (Exception e) {
                        throw e;
          }

              }

         public void TAPView() throws Exception {
        	 
              navigate();
              TAPFilters();
              filterStatus(RecipientTadig);
              GenericHelper.waitForLoadmask(10);
            
              }

             
              
         public void initializeInstanceVariables(Map<String, String> map) throws Exception {
                             streamStage = ExcelHolder.getKey(map, "streamStage");
                             clientPartition = ExcelHolder.getKey(map, "Partition");
                             RecipientTadig =ExcelHolder.getKey(map, "TadigCode");
                             
              }

              public void filterStatus(String RecipientTadig) throws Exception {
                             GenericHelper.waitForLoadmask();
                             ElementHelper.click(or.getProperty("RecipientTadigFilterID"));
                             GenericHelper.waitForLoadmask();
                             ComboBoxHelper.select(or.getProperty("RecipientTadigSelectionID"), RecipientTadig);
                           
              }
}
