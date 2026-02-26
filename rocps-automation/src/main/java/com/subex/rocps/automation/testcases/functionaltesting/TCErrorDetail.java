package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.referenceTable.Currency;
import com.subex.rocps.automation.helpers.application.roaming.ErrorDetail;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCErrorDetail extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "ErrorDetail";

	@Test( priority = 1, enabled = true, description = "'Error Detail'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			ErrorDetail errorDetail = new ErrorDetail( path, workBookName, sheetName, "ErrorDetailScreencolVal" );
			errorDetail.errorDetailColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'Error Detail'  verify the Search Result" )
	public void errorDetailValiateSearchResult() throws Exception
	{
		try
		{
			ErrorDetail errorDetail = new ErrorDetail( path, workBookName, sheetName, "ErrorDetailSearchResult" );
			errorDetail.errorDetailSearchResult();
			;
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
