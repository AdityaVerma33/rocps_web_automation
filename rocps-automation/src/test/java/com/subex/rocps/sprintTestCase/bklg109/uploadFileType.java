package com.subex.rocps.sprintTestCase.bklg109;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class uploadFileType extends PSAcceptanceTest {

	PSGenericHelper genHelperObj = new PSGenericHelper();
	String clientPartition;
	Map<String, String> dpMap = null;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	public uploadFileType() throws Exception {

	}
	public static boolean isStringOnlyAlphabet(String str) 

    { 

        return ((str != null) 

                && (!str.equals("")) 
       
                && (str.matches("^[a-zA-Z\\.]*$"))); 

    }

	public void uploadFileTypeExtension(String filetype) throws Exception {
            
		    if(!isStringOnlyAlphabet(filetype)){ 
		    	System.out.println("TestCaseFailed because of wrong file type name");
		    	return;}
		try {
			NavigationHelper.navigateToScreen("Reference Tables");
			ComboBoxHelper.select("displayString_gwt_uid_", "Upload File Type");
			GenericHelper.waitForLoadmask();

			int rowCount = GridHelper.getRowCount("gridPanel");
			boolean dataExistFlag = false;
			for (int i = 1; i <= rowCount; i++) {
				ArrayList<String> existingData = GridHelper.getRowValues("gridPanel", i);
				if ((existingData.get(1).equals(filetype)) || (existingData.get(1).equals("." + filetype))) 
				{
                    
					dataExistFlag = true;
					Log4jHelper.logInfo("File Type extension already exists and the FileType is" + filetype);

					break;
				}

			}
			if (dataExistFlag == false) {

				GenericHelper.waitForLoadmask();
				NavigationHelper.navigateToAction("Common Tasks", "New");
				TextBoxHelper.type("//input[@id='puftName']", filetype);
				Log4jHelper.logInfo("Configured FileType is " + filetype);

				ButtonHelper.click("Save");

			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void initializeInstanceVariables() {

		clientPartition = dpMap.get("ClientPartition");

	}
}
