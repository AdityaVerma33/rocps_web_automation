package com.subex.rocps.sprintTestCase.bklg310;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.roaming.RoamingFileStatus;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TestRoamingRapOut extends PSAcceptanceTest {
	
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "PSSprintFunctional_ExcelData.xlsx";
	String sheetName = "BKLG310_RAPOutRoamingFileStatus";
	
	@org.testng.annotations.Test( priority = 1, enabled = true, description = "'Roaming File Status' validate Rap out   with Severe Return for 'GPRS, MOC, MTC' Context" )
	public void validateRapoutRoamFileStatusWithSevere_AllContext() throws Exception

	{
		try
		{
			RoamingFileStatus roamingFlStatusRapOb = new RoamingFileStatus( path, workBookName, sheetName, "RoamingRapOutFlValidate__TAPIN_SevereError_AllContext" );
			roamingFlStatusRapOb.validateRapOutFileWithActionPerform();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
