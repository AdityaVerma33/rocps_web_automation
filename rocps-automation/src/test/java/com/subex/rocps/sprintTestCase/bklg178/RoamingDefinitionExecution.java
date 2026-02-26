package com.subex.rocps.sprintTestCase.bklg178;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class RoamingDefinitionExecution extends PSAcceptanceTest {
	 @org.testng.annotations.Test(priority = 4)
	    /*public void newRoamingDef() throws Exception {

	        try {
	            String Path;
	            String WorkbookName;
	            String sheetName;
	            String testCaseName;
	            Path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
	            WorkbookName = "PSSprintFunctional_ExcelData.xlsx";
	            sheetName = "BKLG_278RoamigDefinition";
	            testCaseName = "RoamingDefinition";
	            System.out.println(Path);
	            RoamingDef roamDEFobj = new RoamingDef(Path, WorkbookName, sheetName, testCaseName);
	            roamDEFobj.newRoamingDef();

	        } catch (Exception e) {
	            FailureHelper.reportFailure(e);
	            throw e;
	        }	 

	    }*/
	 
	   public void editRoamingDefinition() throws Exception {
	        try {
	            String Path;
	            String WorkbookName;
	            String sheetName;
	            String testCaseName;
	            Path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
	            WorkbookName = "PSSprintFunctional_ExcelData.xlsx";
	            sheetName = "BKLG_278RoamigDefinition";
	            testCaseName = "editRoamingDefinition";
	            RoamingDef roamDEFobj = new RoamingDef(Path, WorkbookName, sheetName, testCaseName);
	            roamDEFobj.editRoamingDef();
	            ;
	        } catch (Exception e) {
	            FailureHelper.reportFailure(e);
	            throw e;
	        }

	    }
}
