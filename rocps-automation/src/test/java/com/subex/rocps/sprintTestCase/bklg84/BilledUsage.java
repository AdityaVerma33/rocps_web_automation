package com.subex.rocps.sprintTestCase.bklg84;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;

import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;

import com.subex.automation.helpers.config.OR_Reader;

import com.subex.automation.helpers.report.Log4jHelper;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import java.math.*;


public class BilledUsage extends PSAcceptanceTest {
	OR_Reader orData = new OR_Reader();


	ArrayList<String> columnValuesBillBreakDown=new ArrayList<String>();
	ArrayList<String> columnValuesTestBillBreakDown=new ArrayList<String>();
	
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	String billBreakdown= "Test";
	String billProfile1 = "Delta Inv BP - 10 - CBILL";         
	
	double expectedBilledUsageInSeconds =1101;     
	String testBillBreakdown= "Test";
	String billProfile2 = "BT_Invoice - 2 - BT_Account";
	double expectedTestBilledUsageInSeconds =120;
	int decimalPlaces=5;
	Boolean isValueFound;
	public BilledUsage ()throws Exception{
		
		
	}
		public void selectMinuteConfiguration() throws Exception{
			try{
				NavigationHelper.navigateToScreen("Bill Breakdown Configuration");
				
				SearchGridHelper.gridFilterSearchWithTextBox("pbbcBbnTblname","bbn" ,"Name");
				
				GridHelper.clickRow("searchGrid", 1, 1);
				
				NavigationHelper.navigateToAction( or.getProperty( "CommonTasks" ), or.getProperty("View"));
				int rowNum;
				String checkColumnValueForUsage;
				
				for(rowNum=1;rowNum<=GridHelper.getRowCount("billBreakdownValueGrid");rowNum++)
				{
					checkColumnValueForUsage=GridHelper.getCellValue("billBreakdownValueGrid", rowNum, 1);
					
					if(checkColumnValueForUsage.equals("Billed Usage ( RES )")){
						
						GridHelper.clickRow("billBreakdownValueGrid", rowNum, " Value  Display  Component");
						GridHelper.clickRow("billBreakdownValueGrid", rowNum, " Value  Display  Component");
					}
					break;
					
				}
				if(ComboBoxHelper.isValuePresent("usageViewComponentEditor_gwt_uid_","Minutes")){
					Log4jHelper.logInfo("Component Minute is already selected");
				}
				else{
					
					Assert.assertTrue(ComboBoxHelper.containsValue("usageViewComponentEditor_gwt_uid_","Minutes"),"Component Minute is not available for selection");
					ComboBoxHelper.select("usageViewComponentEditor_gwt_uid_","Minutes");
					
				}

				ButtonHelper.click("billBreakdownConfigDetail.save");
				
			}
			catch (Exception e) {

				throw e;
			}
		}
		public void checkBillUsageInMinutes() throws Exception{
			try {
				NavigationHelper.navigateToScreen("Bill");
				SearchGridHelper.gridFilterAdvancedSearch("billProfile", billProfile1, "Bill Profile");
				
				GenericHelper.waitForLoadmask();				
				GridHelper.clickRow("searchGrid", 1, 1);
				GenericHelper.waitInSeconds("2");
				NavigationHelper.navigateToAction("Bill Breakdowns", billBreakdown);
				GenericHelper.waitForLoadmask();
				
				columnValuesBillBreakDown=GridHelper.getColumnValues("drillDownGrid", " Billed Usage ( RES ) ");
				System.out.println("the billed usage values are: "+columnValuesBillBreakDown);
				String expectedBilledUsageInMinutes = evaluateUsageInMinutes(decimalPlaces, expectedBilledUsageInSeconds);
				
		        System.out.println("The value in minutes is: "+expectedBilledUsageInMinutes);
				for (int i=0; i<columnValuesBillBreakDown.size();i++){
					
					if(expectedBilledUsageInMinutes.equalsIgnoreCase(columnValuesBillBreakDown.get(i))) {
						isValueFound = true;
						break;
					}
					else{
						isValueFound=false;
					}
					
				}
				if(isValueFound){
					Log4jHelper.logInfo("The value was present in the column");
				}
				else{
					Log4jHelper.logInfo("The value was not present");
				}

			
				ElementHelper.click(or.getProperty("Dialog_Close_Icon"));
			}
			catch (Exception e) {

				throw e;
			}
			
		}
		public void checkTestBillUsageInMinutes() throws Exception{
			try {
				NavigationHelper.navigateToScreen("Test Bill");
				
				GenericHelper.waitForLoadmask();		
								
				SearchGridHelper.gridFilterAdvancedSearch("billProfile", billProfile2, "Bill Profile");
				GenericHelper.waitForLoadmask();
				GridHelper.clickRow("searchGrid", 1, 1);
				GenericHelper.waitInSeconds("2");
				NavigationHelper.navigateToAction("Test Bill Breakdowns", testBillBreakdown);
				GenericHelper.waitForLoadmask();
				columnValuesTestBillBreakDown=GridHelper.getColumnValues("drillDownGrid", " Billed Usage ( RES ) ");
				System.out.println("the billed usage values are: "+columnValuesTestBillBreakDown);
				
				String expectedTestBilledUsageInMinutes = evaluateUsageInMinutes(decimalPlaces, expectedTestBilledUsageInSeconds);
				
		        System.out.println("The value in minutes is: "+expectedTestBilledUsageInMinutes);
				for (int i=0; i<columnValuesTestBillBreakDown.size();i++){
					
					if(expectedTestBilledUsageInMinutes.equalsIgnoreCase(columnValuesTestBillBreakDown.get(i))) {
						isValueFound = true;
						break;
					}
					else{
						isValueFound=false;
					}
					
				}
				if(isValueFound){
					Log4jHelper.logInfo("The value was present in the column");
				}
				else{
					Log4jHelper.logInfo("The value was not present");
				}

			}
			catch (Exception e) {

				throw e;
			}
			

			
		}
	
		public void navigateToAction(String parent, String child) throws Exception{
			try{
			ElementHelper.click(or.getProperty("GroupActionName").replace("actionName", parent));
			ElementHelper.click(or.getProperty("SubMenuActionName").replace("actionName", child));
			}
			catch (Exception e) {
				

				throw e;
			}	
			
		}
		public String evaluateUsageInMinutes(int decimalPlaces, double expectedBilledUsageInSeconds){
			
		    Double convertedVal = expectedBilledUsageInSeconds/ 60;
		    
		    if(convertedVal % 1 == 0) {
                return String.format("%.0f", convertedVal);
               
            }
		    return (new BigDecimal(convertedVal).setScale(decimalPlaces, RoundingMode.HALF_UP)).toString();
		}
}
	

