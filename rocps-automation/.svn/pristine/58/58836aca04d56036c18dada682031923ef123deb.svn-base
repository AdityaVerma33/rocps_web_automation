package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.bilateral.MergerResults;
import com.subex.rocps.automation.helpers.application.deal.Deal;
import com.subex.rocps.automation.helpers.application.deal.DealRate;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCDeal extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestingDeals.xlsx";
	String sheetName = "Deal";

	@org.testng.annotations.Test( priority = 1, description = "Deal seach screen col validation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealsearchScreenColVal() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "DealColVal" );
			dealObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Deal Creation- Tiered Incoming", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealCreationTierIn() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "Test01Deal" );
			dealObj.dealCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Deal Creation- Tiered outgoing", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealCreationTierOut() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "Test01DealOut" );
			dealObj.dealCreation();

			DealRate dealrateObj = new DealRate( path, workBookName, "DealRate", "Test01DealOut_Rate" );
			dealrateObj.dealRateConfig();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Deal Creation- Tiered Incomig/Outgoing ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealCreationTieredInOut() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "Test01DealIO" );
			dealObj.dealCreation();

			DealRate dealrateObj = new DealRate( path, workBookName, "DealRate", "Test01Deal_RateIn" );
			dealrateObj.dealRateConfig();

			DealRate dealrateOutObj = new DealRate( path, workBookName, "DealRate", "Test01Deal_RateOut" );
			dealrateOutObj.dealRateConfig();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Deal Creation- Comitted incoming", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealCreationCommittedIn() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "Test03Deal" );
			dealObj.dealCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "Deal Creation- Comitted outgoing", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealCreationCommittedOut() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "Test03DealOut" );
			dealObj.dealCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "Deal Committed outgoing rate Creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealrateOutCreation() throws Exception
	{
		try
		{
			DealRate dealrateObj = new DealRate( path, workBookName, "DealRate", "Test03DealOut_Rate" );
			dealrateObj.dealRateConfig();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "Deal Creation- Comitted Incomig with shortfall ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealCreationCommittedShortFallIn() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "Test03DealIn_Shortfal" );
			dealObj.dealCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "Deal shortfall rate Creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealShortFallrateCreation() throws Exception
	{
		try
		{

			DealRate dealrateInObj = new DealRate( path, workBookName, "DealRate", "Test03DealIn_Shortfall" );
			dealrateInObj.dealRateConfig();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "Deal Creation- Comitted Incomig/Outgoing ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealCreationCommittedInOut() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "Test03DealIO" );
			dealObj.dealCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 11, description = "Deal committed Incoming/Outgoing rate Creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealInOutrateCreation() throws Exception
	{
		try
		{
			DealRate dealrateObj = new DealRate( path, workBookName, "DealRate", "Test03Deal_In" );
			dealrateObj.dealRateConfig();

			DealRate dealrateOutObj = new DealRate( path, workBookName, "DealRate", "Test03Deal_Outgoing" );
			dealrateOutObj.dealRateConfig();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 12, description = "Deal Creation Balanced", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealCreationBalanced() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "Test02Deal" );
			dealObj.dealCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 13, description = "Deal Creation Balanced-Incoming & Outgoing", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealCreationBalancedIO() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "Test02DealIO" );
			dealObj.dealCreation();

			DealRate dealrateObj = new DealRate( path, workBookName, "DealRate", "Test02Deal_RateIn" );
			dealrateObj.dealRateConfig();

			DealRate dealrateOutObj = new DealRate( path, workBookName, "DealRate", "Test02Deal_RateOut" );
			dealrateOutObj.dealRateConfig();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 14, description = "Deal delete" )
	public void dealDelete() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "DealDelete" );
			dealObj.dealDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 15, description = "Deal undelete" )
	public void dealUnDelete() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "DealUndelete" );
			dealObj.dealUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 16, description = "View Results - bilateral ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void viewResultDeal() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "ViewResults-Bilateral" );
			dealObj.dealViewResults();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 17, description = "View Results - Merger ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void mergerResultValidation() throws Exception
	{
		try
		{
			MergerResults mergerObj = new MergerResults( path, workBookName, "DealTrafficType", "MergerResults" );
			mergerObj.viewMergerResults();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 18, description = "View Results - Merger Column validation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void mergerResultColVal() throws Exception
	{
		try
		{
			MergerResults mergerObj = new MergerResults( path, workBookName, "DealTrafficType", "DealMergerColVal" );
			mergerObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 19, description = "Deal View Audit Action", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealViewAudit() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "DealViewAudit" );
			dealObj.dealViewAudit();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 20, description = "Deal Change Owner Action", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealChangeOwner() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "DealChangeOwner" );
			dealObj.dealChangeOwner();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 21, description = "Deal Creation- Tiered Incomig with Multiple Cap Config ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealCreationTieredWithMultiCap() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "Test01DealCap" );
			dealObj.dealCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 22, description = "Deal Creation- Tiered Incomig with ExternalRates ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealCreationTieredExternal() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "Test01DealExternalRates" );
			dealObj.dealCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 23, description = "Deal Creation- Commted with grace period days ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealCreationgracePerioDays() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "Test02DealIn_GracePeriod_01" );
			dealObj.dealCreation();

			Deal deal1Obj = new Deal( path, workBookName, sheetName, "Test02DealIn_GracePeriod_02" );
			deal1Obj.dealCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 24, description = "Deal tier rate Creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealrateInCreation() throws Exception
	{
		try
		{
			DealRate dealrateObj = new DealRate( path, workBookName, "DealRate", "Test01Deal_Rate" );
			dealrateObj.dealRateConfig();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 25, description = "Deal external rate Creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void dealExternalrateCreation() throws Exception
	{
		try
		{
			DealRate dealrateObj = new DealRate( path, workBookName, "DealRate", "Test01Deal_EditExternalRate" );
			dealrateObj.dealRateConfig();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	//@org.testng.annotations.Test( priority = 26, description = "Edit Deal " )
	public void editDeal() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "EditTest02Deal" );
			dealObj.dealEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
