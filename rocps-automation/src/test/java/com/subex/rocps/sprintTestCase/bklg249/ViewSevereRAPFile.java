package com.subex.rocps.sprintTestCase.bklg249;

import java.util.List;

import org.testng.Assert;

import com.subex.automation.helpers.application.BrowserHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.componentHelpers.SearchHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

public class ViewSevereRAPFile extends PSAcceptanceTest {
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	

	public  void viewSevereFile() throws Exception {
        try {
        	NavigationHelper.navigateToScreen("Roaming File Status");
        	GenericHelper.waitForLoadmask();
        	filterStatus();
        	GenericHelper.waitForLoadmask();
        	
        	int rows = GridHelper.getRowCount(or.getProperty("SearchGrid"));
        	Assert.assertTrue(rows>0, "No Severe File Found");
        	GridHelper.clickRow(or.getProperty("PSSearch_roamingFileStatusGridWrapperId"), or.getProperty("SearchGrid"), 1, 2);
        	GenericHelper.waitForLoadmask();
        	String gridfilename = GridHelper.getCellValue(or.getProperty("SearchGrid"),1," File Name");
        	GenericHelper.waitForLoadmask();
        	NavigationHelper.navigateToAction(or.getProperty("PSNavigateToViewID"), "View RAP File");
        	GenericHelper.waitForLoadmask(5);
        	String viewfilename = TextBoxHelper.getValue("prfsFileName");
        	GenericHelper.waitForLoadmask();
        	
        	Assert.assertEquals(viewfilename, gridfilename, "File names are not matching");
        	
        	String[] arr = {"GPRS","MOC","MTC"};
        	
        	for(String str : arr) {
        		SevereFileSearch(str);
        	}
        		
}
        catch (Exception e) {
            FailureHelper.reportFailure(e);
            throw e;
    
        }
	}
        
        public void filterStatus() throws Exception {
        	 GenericHelper.waitForLoadmask();
 	        ElementHelper.click(or.getProperty("PSConfigTypeSearchFilterID"));
 	        GenericHelper.waitForLoadmask();
 	        ComboBoxHelper.select(or.getProperty("PSConfigTypeSelectionFilterID"),"Rap In");
 	        GenericHelper.waitForLoadmask();
 	        ElementHelper.click(or.getProperty("PSFailureReasonSearchFilterID"));
 	        GenericHelper.waitForLoadmask();
 	        ComboBoxHelper.select(or.getProperty("PSFailureReasonSelectionFilterID"),"SEVERE RETURN");
 	        GenericHelper.waitForLoadmask();
 	        SearchHelper searchHelper = new SearchHelper();
 	        searchHelper.clickSearch();
	    }
        
        private void SevereFileSearch(String callType) throws Exception{
        	ComboBoxHelper.select(or.getProperty("PSRoamingErrorMessageCallTypeSelectionID"),callType);
        	ButtonHelper.click("search");
        	GenericHelper.waitForLoadmask(10);
        	int totalrows = GridHelper.getRowCount(or.getProperty("SearchGrid"));
        	if (totalrows > 0)
        		viewData(totalrows);
        	else
        		Log4jHelper.logInfo("No data found in : " + callType);	
        }
        private static void viewData(int rows) throws Exception
        {
        	PSStringUtils ps = new PSStringUtils();
        	for(int i=0;i<rows;i++)
        	{
        		List<String> list = GridHelper.getRowValues(or.getProperty("SearchGrid"),i+1);
        		String value = null;
        		value = ps.stringformation(list);
        		
        		Log4jHelper.logInfo(value);
        	}
        }
        	public  void viewSevereerror() throws Exception {
                try {
                	NavigationHelper.navigateToScreen("Roaming File Status");
                	GenericHelper.waitForLoadmask();
                	filterStatus();
                	GenericHelper.waitForLoadmask();
                	GridHelper.clickRow("bottomSplitPanel", "searchGrid", 1, 2);
                	GenericHelper.waitForLoadmask();
                	String gridfilename = GridHelper.getCellValue("searchGrid",1," File Name");
                	GenericHelper.waitForLoadmask();
                	NavigationHelper.navigateToAction("View", "View Severe Rap Error");
                	GenericHelper.waitForLoadmask(5);
                	String viewfilename = TextBoxHelper.getValue("prfsFileName");
                	GenericHelper.waitForLoadmask();
                	
                if(gridfilename.equals(viewfilename))
                	{
                	System.out.println("Equals");
                	GenericHelper.waitForLoadmask(20);
                	}
                	else
                	{
                		
                		System.out.println("Not Equals");
                	}
        }
                catch (Exception e) {
                    FailureHelper.reportFailure(e);
                    throw e;
            
                }
        }
        

}
