package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.accruals.AccrualsModelling;
import com.subex.rocps.automation.helpers.application.accruals.AccrualsOverview;
import com.subex.rocps.automation.helpers.application.accruals.AccrualsOverviewModelling;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRule;
import com.subex.rocps.automation.helpers.application.system.PSUsageGroupHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.automation.helpers.application.ReferenceTableHelper;
import com.subex.automation.helpers.application.screens.UsageGroupHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCAccrulsOverviewModelling extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "AccrualsOverviewMod";

	@org.testng.annotations.Test( priority = 1, description = "Accruals Overview Modelling creation" )
	public void accrualsOverivewModellingCreation() throws Exception
	{
		try
		{
			AccrualsOverviewModelling accobj = new AccrualsOverviewModelling( path, workBookName, sheetName, "AccuralsOverviewModCreation", 1 );
			accobj.configureAccuralsOverviewModelling();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Accruals Overview Modelling Edit" )
	public void accrualsOverviewModellingEdit() throws Exception
	{
		try
		{
			AccrualsOverviewModelling accobj = new AccrualsOverviewModelling( path, workBookName, sheetName, "AccuralsOverviewModEdit", 1 );
			accobj.accrualsOverviewModelingEdit();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Accruals Overview Modelling Delete" )
	public void accrualsOverviewModellingDelete() throws Exception
	{
		try
		{
			AccrualsOverviewModelling accobj = new AccrualsOverviewModelling( path, workBookName, sheetName, "AccuralsOverviewModDelete", 1 );
			accobj.accrualsOverviewModellingDelete();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Accruals Overview Modelling undelete" )
	public void accrualsOverviewModellingUnDelete() throws Exception
	{
		try
		{
			AccrualsOverviewModelling accobj = new AccrualsOverviewModelling( path, workBookName, sheetName, "AccuralsOverviewModUnDelete", 1 );
			accobj.accrualsOverviewModellingUnDelete();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Accruals Overview Modelling seach screen column validation" )
	public void accrualsOverviewModellingColVal() throws Exception
	{
		try
		{
			AccrualsOverviewModelling accobj = new AccrualsOverviewModelling( path, workBookName, sheetName, "AccuralsOverviewModColVal", 1 );
			accobj.searchScreenColumnsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "Accruals Overview Modelling Chaneg status" )
	public void accrualsOverviewModellingChangeStatus() throws Exception
	{
		try
		{
			AccrualsOverviewModelling accobj = new AccrualsOverviewModelling( path, workBookName, sheetName, "AccuralsOverviewModStatus", 1 );
			accobj.accuralModellingChangeStatus();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "Refresh usage partition", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void refreshUsagePartitioned() throws Exception
	{
		try
		{
			PSUsageGroupHelper obj = new PSUsageGroupHelper();
			obj.refreshUsagePartitioned( path, "SystemTestCases.xlsx", "Pre-requisites", "AttachUsageServer", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "Accruals Overview  results" )
	public void viewAccrualOverviewResults() throws Exception
	{
		try
		{
			AccrualsOverview accobj = new AccrualsOverview( path, workBookName, "AccrualOverview", "AccOverview", 1 );
			accobj.viewAccrualOverviewResults();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
