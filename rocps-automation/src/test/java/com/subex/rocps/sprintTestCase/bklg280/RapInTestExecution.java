package com.subex.rocps.sprintTestCase.bklg280;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.sprintTestCase.bklg113.NRTRDERoamingConfiguration;

public class RapInTestExecution extends PSAcceptanceTest {
	
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "PSSprintFunctional_ExcelData.xlsx";
	String sheetName = "BKLG280_RAPIN_RoamConf";
	@org.testng.annotations.Test(priority = 13)
	public void newRapINRoamingConfiguration() throws Exception {

		try {
			String testCaseName ="RapInCreation";
			System.out.println(path);
			RapInRoamingConfiguration rapINobj = new RapInRoamingConfiguration(path, workBookName, sheetName, testCaseName);
			rapINobj.newRapInRoamingConfiguration();

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}

	}
	@org.testng.annotations.Test(priority = 14)
	public void editRapINRoamingConfiguration() throws Exception {
		try {
			String testCaseName ="EditRapInCreation";
			RapInRoamingConfiguration rapINobj = new RapInRoamingConfiguration(path, workBookName, sheetName, testCaseName);
			rapINobj.editRapInRoamingConfiguration();
			;
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}

	}

}



