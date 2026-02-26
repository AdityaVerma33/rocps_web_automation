package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.Sales.ProposalRule;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCProposalRule extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "ProposalRule";

	@Test( priority = 1, enabled = true, description = "'Proposal Rule'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			ProposalRule proposalRule=new ProposalRule( path, workBookName, sheetName, "ProposalRuleScreencolVal" );
			proposalRule.proposalRuleColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
