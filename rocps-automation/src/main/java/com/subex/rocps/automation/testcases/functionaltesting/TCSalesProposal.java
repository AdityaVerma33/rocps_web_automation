package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.Sales.SalesProposal;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCSalesProposal extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "SalesProposal";

	@Test( priority = 1, enabled = true, description = "'Sales Proposal'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			SalesProposal salesProposal = new SalesProposal( path, workBookName, sheetName, "SalesProposalScreencolVal" );
			salesProposal.salesProposalColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
