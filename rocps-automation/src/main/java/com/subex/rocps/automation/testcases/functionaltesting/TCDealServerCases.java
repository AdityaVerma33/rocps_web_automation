package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.bilateral.MergerResults;
import com.subex.rocps.automation.helpers.application.deal.Deal;
import com.subex.rocps.automation.helpers.application.deal.DealRate;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCDealServerCases extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestingDeals.xlsx";
	String sheetName = "DealServer";
	TaskSchedule taskObj = new TaskSchedule();
	PSTaskSearchHelper tskObj = new PSTaskSearchHelper();

	@org.testng.annotations.Test( priority = 1, description = "task Controller capabilities", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void taskControllerCapabilities() throws Exception
	{
		try
		{

			TaskControllerHelper taskObj = new TaskControllerHelper();
			taskObj.setTaskControllerCapability( path, workBookName, "Prerequsities", "TCCapability", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Deal change status Action" )
	public void dealTierInAuthorize() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "Test01Deal" );
			dealObj.dealValidate();

			Deal deal1Obj = new Deal( path, workBookName, sheetName, "Test01Deal" );
			deal1Obj.dealCheckIn();

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "DealCheckinStatus", 1 );

			Deal deal2Obj = new Deal( path, workBookName, sheetName, "Test01Deal" );
			deal2Obj.dealAuthorize();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Mnr and Aggregation for deal tierin" )
	public void dealTierInMatchandRate() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, sheetName, "FileSchedule TieredDealIn", 1 );

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, "FunctionalTestCases.xlsx", "AggregationResults", "MnrTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "scheduling recuring task for tiered Deal -Incoming" )
	public void dealTierInScheduleRecurringTask() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();

			taskObj.scheduleRecurringTask( path, "FunctionalTestCases.xlsx", "AggregationResults", "RecurringTask", 1 );

			tskObj.psVerifyTaskStatus( path, "FunctionalTestCases.xlsx", "AggregationResults", "AggrTaskStatus", 1 );

			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "AggregationResult TierdDEalIn" );
			aggrResObj.isAggregationResultPresent();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Scheduling bilater master request and validatin brd results" )
	public void bilateralRecurringTask() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.scheduleRecurringTask( path, workBookName, sheetName, "RecurringTask", 1 );

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "brdTaskStatus", 1 );

			//Deal deal2Obj = new Deal( path, workBookName, sheetName, "BilateralResults-TieredDeal" );
			//deal2Obj.dealViewResults();

			MergerResults mergerObj = new MergerResults( path, workBookName, sheetName, "MergerResults-TierIn" );
			mergerObj.isMergerResultsPresent();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "Deal change status Actions- committed deal - outgoing" )
	public void dealAuthorizeCommittedDeal() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "Test03Deal_Out_Status" );
			dealObj.dealValidate();

			Deal deal1Obj = new Deal( path, workBookName, sheetName, "Test03Deal_Out_Status" );
			deal1Obj.dealCheckIn();

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "DealCheckinStatus", 1 );

			Deal deal2Obj = new Deal( path, workBookName, sheetName, "Test03Deal_Out_Status" );
			deal2Obj.dealAuthorize();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "Mnr for deal committed deal - outgoing" )
	public void dealCommittedOutMatchandRate() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, sheetName, "FileSchedule CommittedDealOut", 1 );

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, "FunctionalTestCases.xlsx", "AggregationResults", "MnrTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "scheduling recuring task for Committed deal -Outgoing" )
	public void dealCommittedOutScheduleRecurringTask() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();

			taskObj.scheduleRecurringTask( path, "FunctionalTestCases.xlsx", "AggregationResults", "RecurringTask", 1 );

			tskObj.psVerifyTaskStatus( path, "FunctionalTestCases.xlsx", "AggregationResults", "AggrTaskStatus", 1 );

			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "AggregationResult CommittedOut" );
			aggrResObj.isAggregationResultPresent();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "Scheduling bilater master request and validation of brd results and merger results" )
	public void bilateralResultsCommittedOut() throws Exception
	{
		try
		{

			taskObj.scheduleRecurringTask( path, workBookName, sheetName, "RecurringTask", 1 );

			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "brdTaskStatus", 1 );

			MergerResults mergerObj = new MergerResults( path, workBookName, sheetName, "MergerResults-CommittedDealOut" );
			mergerObj.isMergerResultsPresent();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "Deal chnage status Action- Committed deal Incoming- Short Fal" )
	public void dealAuthorizeCommittedShortFallIn() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "Test03Deal_ShortFallOut_Status" );
			dealObj.dealValidate();

			Deal deal1Obj = new Deal( path, workBookName, sheetName, "Test03Deal_ShortFallOut_Status" );
			deal1Obj.dealCheckIn();

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "DealCheckinStatus", 1 );

			Deal deal2Obj = new Deal( path, workBookName, sheetName, "Test03Deal_ShortFallOut_Status" );
			deal2Obj.dealAuthorize();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 11, description = "Mnr committed deal Incoming - ShortFall" )
	public void dealCommittedSFMatchandRate() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, sheetName, "FileSchedule CommittedSFIn", 1 );

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, "FunctionalTestCases.xlsx", "AggregationResults", "MnrTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 12, description = "scheduling recuring task for Committed deal incoming - Shortfall" )
	public void dealCommittedSFRecurringTask() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();

			taskObj.scheduleRecurringTask( path, "FunctionalTestCases.xlsx", "AggregationResults", "RecurringTask", 1 );

			tskObj.psVerifyTaskStatus( path, "FunctionalTestCases.xlsx", "AggregationResults", "AggrTaskStatus", 1 );

			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "AggregationResult CommittedSFIn" );
			aggrResObj.isAggregationResultPresent();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 13, description = "Scheduling bilateral master request and validatin merger results- shortfall" )
	public void bilateralRecurringTaskCommittedSF() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.scheduleRecurringTask( path, workBookName, sheetName, "RecurringTask", 1 );

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "brdTaskStatus", 1 );

			//Deal deal2Obj = new Deal( path, workBookName, sheetName, "BilateralResults-CommittedSFIn" );
			//deal2Obj.dealViewResults();			

			MergerResults mergerObj = new MergerResults( path, workBookName, sheetName, "MergerResults-CommittedDealSFIn" );
			mergerObj.isMergerResultsPresent();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 14, description = "Deal chnage status Action- Tiered Deal- Incoming/Outgoing" )
	public void dealAuthorizeTierIO() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "Test01Deal_IO_Status" );
			dealObj.dealValidate();

			Deal deal1Obj = new Deal( path, workBookName, sheetName, "Test01Deal_IO_Status" );
			deal1Obj.dealCheckIn();

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "DealCheckinStatus", 1 );

			Deal deal2Obj = new Deal( path, workBookName, sheetName, "Test01Deal_IO_Status" );
			deal2Obj.dealAuthorize();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 15, description = "Mnr for Tiered Deal- Incoming/Outgoing" )
	public void dealTierIOMatchandRate() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, sheetName, "FileSchedule TierIO", 1 );

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, "FunctionalTestCases.xlsx", "AggregationResults", "MnrTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 16, description = "scheduling recuring task for Tiered Deal- Incoming/Outgoing" )
	public void dealTierIORecurringTask() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();

			taskObj.scheduleRecurringTask( path, "FunctionalTestCases.xlsx", "AggregationResults", "RecurringTask", 1 );

			tskObj.psVerifyTaskStatus( path, "FunctionalTestCases.xlsx", "AggregationResults", "AggrTaskStatus", 1 );

			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "AggregationResult TierIO" );
			aggrResObj.isAggregationResultPresent();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 17, description = "Scheduling bilater master request and validatin brd results-Tiered Deal- Incoming/Outgoing" )
	public void bilateralRecurringTaskTierIO() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.scheduleRecurringTask( path, workBookName, sheetName, "RecurringTask", 1 );

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "brdTaskStatus", 1 );

			//Deal deal2Obj = new Deal( path, workBookName, sheetName, "BilateralResults-TierIO" );
			//deal2Obj.dealViewResults();

			MergerResults mergerObj = new MergerResults( path, workBookName, sheetName, "MergerResults-TierInOut" );
			mergerObj.isMergerResultsPresent();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 18, description = "Deal chnage status Action- Balanced Deal- Incoming/Outgoing" )
	public void dealAuthorizeBalancedIO() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "Test02Deal_IO_Status" );
			dealObj.dealValidate();

			Deal deal1Obj = new Deal( path, workBookName, sheetName, "Test02Deal_IO_Status" );
			deal1Obj.dealCheckIn();

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "DealCheckinStatus", 1 );

			Deal deal2Obj = new Deal( path, workBookName, sheetName, "Test02Deal_IO_Status" );
			deal2Obj.dealAuthorize();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 19, description = "Mnr for Balanced Deal- Incoming/Outgoing" )
	public void dealBalancedIOMatchandRate() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, sheetName, "FileScheduleBalancedIO", 1 );

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, "FunctionalTestCases.xlsx", "AggregationResults", "MnrTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 20, description = "scheduling recuring task for Balanced Deal- Incoming/Outgoing" )
	public void dealBalancedIORecurringTask() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();

			taskObj.scheduleRecurringTask( path, "FunctionalTestCases.xlsx", "AggregationResults", "RecurringTask", 1 );

			tskObj.psVerifyTaskStatus( path, "FunctionalTestCases.xlsx", "AggregationResults", "AggrTaskStatus", 1 );

			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "AggregationResultBalancedIO" );
			aggrResObj.isAggregationResultPresent();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 21, description = "Scheduling bilater master request and validatin brd results-Balanced Deal- Incoming/Outgoing" )
	public void bilateralRecurringTaskBalancedIO() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.scheduleRecurringTask( path, workBookName, sheetName, "RecurringTask", 1 );

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "brdTaskStatus", 1 );

			//Deal deal2Obj = new Deal( path, workBookName, sheetName, "BilateralResults-BalancedIO" );
			//deal2Obj.dealViewResults();

			MergerResults mergerObj = new MergerResults( path, workBookName, sheetName, "MergerResults-BalancedInOut" );
			mergerObj.isMergerResultsPresent();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 22, description = "Deal chnage status Action- Tier Deal- Outgoing" )
	public void dealAuthorizeTierOut() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "Test01DealOut_Status" );
			dealObj.dealValidate();

			Deal deal1Obj = new Deal( path, workBookName, sheetName, "Test01DealOut_Status" );
			deal1Obj.dealCheckIn();

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "DealCheckinStatus", 1 );

			Deal deal2Obj = new Deal( path, workBookName, sheetName, "Test01DealOut_Status" );
			deal2Obj.dealAuthorize();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 23, description = "Mnr for Tier Deal- Outgoing" )
	public void dealTierOutMatchandRate() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, sheetName, "FileScheduleTierOut", 1 );

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, "FunctionalTestCases.xlsx", "AggregationResults", "MnrTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 24, description = "scheduling recuring task for Tier Deal- Outgoing" )
	public void dealTierOutRecurringTask() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();

			taskObj.scheduleRecurringTask( path, "FunctionalTestCases.xlsx", "AggregationResults", "RecurringTask", 1 );

			tskObj.psVerifyTaskStatus( path, "FunctionalTestCases.xlsx", "AggregationResults", "AggrTaskStatus", 1 );

			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "AggregationResultTierOut" );
			aggrResObj.isAggregationResultPresent();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 25, description = "Scheduling bilater master request and validatin brd results-Tier Deal- Outgoing" )
	public void bilateralRecurringTaskTierOut() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.scheduleRecurringTask( path, workBookName, sheetName, "RecurringTask", 1 );

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "brdTaskStatus", 1 );

			//	Deal deal2Obj = new Deal( path, workBookName, sheetName, "BilateralResults-TierOut" );
			//	deal2Obj.dealViewResults();

			MergerResults mergerObj = new MergerResults( path, workBookName, sheetName, "MergerResults-TierOut" );
			mergerObj.isMergerResultsPresent();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 26, description = "Deal chnage status Action- Committed Deal- Incoming/Outgoing" )
	public void dealAuthorizeCommittedIO() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "Test03DealIO_Status" );
			dealObj.dealValidate();

			Deal deal1Obj = new Deal( path, workBookName, sheetName, "Test03DealIO_Status" );
			deal1Obj.dealCheckIn();

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "DealCheckinStatus", 1 );

			Deal deal2Obj = new Deal( path, workBookName, sheetName, "Test03DealIO_Status" );
			deal2Obj.dealAuthorize();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 27, description = "Mnr for Committed Deal- Incoming/Outgoing" )
	public void dealCommittedIOMatchandRate() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection( path, workBookName, sheetName, "FileSchedule CommittedIO", 1 );

			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();
			tskObj.psVerifyTaskStatus( path, "FunctionalTestCases.xlsx", "AggregationResults", "MnrTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 28, description = "scheduling recuring task for Committed Deal- Incoming/Outgoing" )
	public void dealComittedIORecurringTask() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper tskObj = new PSTaskSearchHelper();

			taskObj.scheduleRecurringTask( path, "FunctionalTestCases.xlsx", "AggregationResults", "RecurringTask", 1 );

			tskObj.psVerifyTaskStatus( path, "FunctionalTestCases.xlsx", "AggregationResults", "AggrTaskStatus", 1 );

			AggregationResult aggrResObj = new AggregationResult( path, workBookName, sheetName, "AggregationResult CommittedIO" );
			aggrResObj.isAggregationResultPresent();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 29, description = "Scheduling bilater master request and validatin brd results-committed Deal- Incoming/Outgoing" )
	public void bilateralMergerRecurringTaskCommittedIO() throws Exception
	{
		try
		{

			taskObj.scheduleRecurringTask( path, workBookName, sheetName, "RecurringTask", 1 );

			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "brdTaskStatus", 1 );

			//	Deal deal2Obj = new Deal( path, workBookName, sheetName, "BilateralResults-CommittedDealIO" );
			//	deal2Obj.dealViewResults();			

			MergerResults mergerObj = new MergerResults( path, workBookName, sheetName, "MergerResults-CommittedDealIO-In" );
			mergerObj.isMergerResultsPresent();

			MergerResults merger1Obj = new MergerResults( path, workBookName, sheetName, "MergerResults-CommittedDealIO-Out" );
			merger1Obj.isMergerResultsPresent();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 30, description = "Deal chnage status Action" )
	public void dealChangeStatus() throws Exception
	{
		try
		{
			Deal dealObj = new Deal( path, workBookName, sheetName, "Test01DealOut_Checkout" );
			dealObj.dealChangeStatusDraft();

			tskObj.psVerifyTaskStatus( path, workBookName, sheetName, "DealCheckOutStatus", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	
}
