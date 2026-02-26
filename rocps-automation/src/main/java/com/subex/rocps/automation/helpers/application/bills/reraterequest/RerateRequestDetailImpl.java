package com.subex.rocps.automation.helpers.application.bills.reraterequest;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.filters.EnitityValuesSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class RerateRequestDetailImpl extends PSAcceptanceTest
{
	protected String partition;
	protected String name;
	protected String summaryOnly;
	protected String stream;
	protected String considerAdjustment;
	protected String keepSameBillPeriod;
	protected String eventType;
	protected String chargeLegsToReRate;
	protected String aggregationProcessor;
	protected String trafficPeriodFrom;
	protected String trafficPeriodTo;
	protected String account;
	protected String billProfile;
	protected String billPeriod;
	protected String rerateOpenBillPeriods;
	protected String tariffs;
	protected String bands;
	protected String entities;
	protected String values;
	protected String schedulingName;
	protected String schedulingDesc;
	protected String billPeriodOnly;
	String[] eventTypeArr;
	String[] chargeLegsToReRateArr;
	String[] aggregationProcessorArr;
	String[] trafficPeriodFromArr;
	String[] trafficPeriodToArr;
	String[] billProfileArr;
	String[] billPeriodArr;
	String[] tariffsArr;
	String[] bandsArr;
	String[] entitiesArr;
	String[] valuesArr;
	PSStringUtils stringObj = new PSStringUtils();
	DataSelectionHelper dataObj = new DataSelectionHelper();
	protected Map<String, String> map;

	public RerateRequestDetailImpl( Map<String, String> map ) throws Exception
	{
		this.map = map;
		initialiseVariables( map );
		initalizeArray();
	}

	/*
	 * This method is for new rerate request
	 */
	public void newRerateRequest() throws Exception
	{
		PSGenericHelper genericObj = new PSGenericHelper();
		genericObj.clickNewAction( partition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		//assertEquals( NavigationHelper.getScreenTitle(), "Carrier Invoice Search" );

	}

	/*
	 * This method is for rerate options
	 */
	public void rerateOptions() throws Exception
	{
		basicDetails();
		eventTypeConfig();
		chargeLegsToRerateConfig();
		aggregationProcesssorConfig();

	}

	/*
	 * This method is for basic details
	 */
	public void basicDetails() throws Exception
	{
		PSDataComponentHelper.select( "PS_Detail_rerate_rerateType_comboID", name );

		if ( name.contains( "Re-rate" ) && ValidationHelper.isTrue( summaryOnly ) && CheckBoxHelper.isEnabled( "rerateSummaryFl_InputElement" ) )
		{
			CheckBoxHelper.check( "PS_Detail_rerate_summaryChkbx" );
			ComboBoxHelper.select( "PS_Detail_rerate_stream_comboID", stream );
			if ( ValidationHelper.isTrue( "PS_Detail_rerate_adjustmentFl_chckbx" ) )
				CheckBoxHelper.check( "PS_Detail_rerate_adjustmentFl_chckbx" );
		}
		if ( name.contains( "Fully re-match and re-rate" ) && ValidationHelper.isFalse( keepSameBillPeriod ) )
			CheckBoxHelper.uncheck( "PS_Detail_rerate_sameBillPeriod_chckbx" );
	}

	/*
	 * This method is for event type config
	 */
	public void eventTypeConfig() throws Exception
	{
		if ( ValidationHelper.isNotEmpty( eventType ) )
		{
			for ( int row = 0; row < eventTypeArr.length; row++ )
			{
				ButtonHelper.click( "PS_Detail_rerate_eventType_addbtn" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				dataObj.eventTypeSelection( eventTypeArr[row] );
			}
		}
	}

	/*
	 * This method is for chargelegsto rerate config
	 */
	public void chargeLegsToRerateConfig() throws Exception
	{
		for ( int row = 0; row < chargeLegsToReRateArr.length; row++ )
		{
			if ( ValidationHelper.isNotEmpty( chargeLegsToReRate ) )
			{
				ButtonHelper.click( "PS_Detail_rerate_eventLeg_addbtn" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				assertEquals( NavigationHelper.getScreenTitle(), "Reference Table Search" );
				GridHelper.clickRow( "SearchGRid", chargeLegsToReRateArr[row], "Name" );
				ButtonHelper.click( "OKButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
			}

		}
	}

	/*
	 * This method is for aggregation processor config
	 */
	public void aggregationProcesssorConfig() throws Exception
	{
		String val = null;
		if ( !aggregationProcessor.isEmpty() )
		{
			for ( int row = 0; row < aggregationProcessorArr.length; row++ )
			{
				int rowNo = GridHelper.getRowNumber( "PS_Detail_rerate_aggrprocessor_GRidID", aggregationProcessorArr[row], 1 );
				GenericHelper.waitForLoadmask();
				if ( rowNo != 0 )
				{
					val = GridHelper.getCellValue( "PS_Detail_rerate_aggrprocessor_GRidID", rowNo, 1 );
					GenericHelper.waitForLoadmask();
					if ( val.contains( aggregationProcessorArr[row] ) )
						Log4jHelper.logInfo( "Aggregation Processor is present for the stream" );
				}
				else
				{
					ButtonHelper.click( "PS_Detail_rerate_aggrProcessor_addbtn" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					dataObj.aggregationProcessorSelection( aggregationProcessorArr[row] );
				}

			}
		}
	}

	/*
	 * This method is for rerate filter config
	 */
	public void reRateFiltersTabConfig() throws Exception
	{
		TabHelper.gotoTab( "//div[text()='Re-rate Fil...']" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		TextBoxHelper.type( "PS_Detail_rerate_trafficPeriodFrom_txtID", trafficPeriodFrom );
		TextBoxHelper.type( "PS_Detail_rerate_trafficPeriodTo_txtID", trafficPeriodTo );
		if ( ValidationHelper.isFalse( rerateOpenBillPeriods ) )
			CheckBoxHelper.uncheck( "PS_Detail_rerate_OpenBillPeriods_chckbx" );
		if ( ValidationHelper.isTrue( billPeriodOnly ) )
		{
			if ( billPeriod.isEmpty() || billProfile.isEmpty() )
				FailureHelper.failTest( "Bill Profile or bill period is empty" );
			billPeriodConfig();
		}
		if ( ValidationHelper.isFalse( billPeriodOnly ) && ValidationHelper.isNotEmpty( billProfile ) )
			billProfileConfig();
		if ( ValidationHelper.isFalse( billPeriodOnly ) && ValidationHelper.isNotEmpty( billPeriod ) )
			billPeriodConfig();
		tariffConfig();
		BandConfig();
	}

	private void billPeriodConfig() throws Exception
	{

		for ( int row = 0; row < billProfileArr.length; row++ )
		{
			billPeriodSelection( billPeriodArr[row], billProfileArr[row] );
		}

	}

	/*
	 * This method is for bill period config
	 */
	private void billPeriodSelection( String billPeriod, String billProfile ) throws Exception
	{
		String[] billPeriodAr = billPeriod.split( secondLevelDelimiter );
		for ( int row = 0; row < billPeriodAr.length; row++ )
		{
			if ( ValidationHelper.isNotEmpty( billPeriod ) )
				ButtonHelper.click( "PS_Detail_rerate_billPeriod_addbtn" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			dataObj.billPeriodSearch( account, billProfile, billPeriodAr[row] );

		}
	}

	/*
	 * This method is for bill profile config
	 */
	private void billProfileConfig() throws Exception
	{
		if ( ValidationHelper.isNotEmpty( billProfile ) )
		{
			for ( int row = 0; row < billProfileArr.length; row++ )
			{
				ButtonHelper.click( "PS_Detail_rerate_billProfile_addbtn" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				dataObj.billProfileSelection( billProfileArr[row] );
				GridHelper.click( "PS_Detail_rerate_billperiod_GridID" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );

			}
		}
	}

	/*
	 * This method is for tariff config
	 */

	private void tariffConfig() throws Exception
	{
		for ( int row = 0; row < tariffsArr.length; row++ )
		{
			if ( ValidationHelper.isNotEmpty( tariffs ) )
			{
				ButtonHelper.click( "PS_Detail_rerate_tariffGrid_addbtn" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				dataObj.tariffSelection( tariffsArr[row] );
			}
		}
	}

	/*
	 * This method is for band config
	 */
	private void BandConfig() throws Exception
	{
		for ( int row = 0; row < bandsArr.length; row++ )
		{
			if ( ValidationHelper.isNotEmpty( bands ) )
			{
				ButtonHelper.click( "PS_Detail_rerate_bandGrid_addbtn" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				dataObj.bandSelection( bandsArr[row] );
			}
		}
	}

	/*
	 * This method is for advance filters tab 
	 */
	public void advancedFiltersTabConfig() throws Exception
	{
		if ( !entities.isEmpty() )
		{
			TabHelper.gotoTab( "//div[text()='Advanced Fi...']" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );

			EnitityValuesSelectionHelper valObj = new EnitityValuesSelectionHelper();
			valObj.entityGridConfig( "PS_Detail_rerate_entity_comboID", "PS_Detail_rerate_entity_GridID", "PS_Detail_rerate_entity_addbtn", entities, values, "PS_Detail_rerate_values_gridID", "PS_Detail_rerate_values_addbtn", "Rerate Request" );
		}
	}

	/*
	 * This method is for scheduling tab
	 */
	public void schedulingConfig() throws Exception
	{
		TabHelper.gotoTab( "//div[text()='Scheduling']" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		TextBoxHelper.type( "prrqName", schedulingName );
	}

	/*
	 * This method is to save rerate request
	 */

	public void saveRerateRequest() throws Exception
	{
		ButtonHelper.click( "rerateRequestDetail.save" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitForElementToDisappear( "SaveButton", detailScreenWaitSec );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );

		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/*
	 * This method is to initialize array
	 */
	public void initalizeArray() throws Exception
	{
		eventTypeArr = stringObj.stringSplitFirstLevel( eventType );
		chargeLegsToReRateArr = stringObj.stringSplitFirstLevel( chargeLegsToReRate );
		aggregationProcessorArr = stringObj.stringSplitFirstLevel( aggregationProcessor );
		trafficPeriodFromArr = stringObj.stringSplitFirstLevel( trafficPeriodFrom );
		trafficPeriodToArr = stringObj.stringSplitFirstLevel( trafficPeriodTo );
		billProfileArr = stringObj.stringSplitFirstLevel( billProfile );
		billPeriodArr = stringObj.stringSplitFirstLevel( billPeriod );
		tariffsArr = stringObj.stringSplitFirstLevel( tariffs );
		bandsArr = stringObj.stringSplitFirstLevel( bands );
		entitiesArr = stringObj.stringSplitFirstLevel( entities );
		valuesArr = stringObj.stringSplitFirstLevel( values );

	}

	/*
	 * This method is to initialize instance variables
	 */
	public void initialiseVariables( Map<String, String> map ) throws Exception
	{
		partition = ExcelHolder.getKey( map, "Partition" );
		name = ExcelHolder.getKey( map, "RerateType" );
		summaryOnly = ExcelHolder.getKey( map, "SummaryOnly" );
		stream = ExcelHolder.getKey( map, "Stream" );
		considerAdjustment = ExcelHolder.getKey( map, "ConsiderAdjustment" );
		keepSameBillPeriod = ExcelHolder.getKey( map, "KeepSameBillPeriod" );
		eventType = ExcelHolder.getKey( map, "EventType" );
		chargeLegsToReRate = ExcelHolder.getKey( map, "ChargeLegsToReRate" );
		aggregationProcessor = ExcelHolder.getKey( map, "AggregationProcessor" );
		trafficPeriodFrom = ExcelHolder.getKey( map, "TrafficPeriodFrom" );
		trafficPeriodTo = ExcelHolder.getKey( map, "TrafficPeriodTo" );
		billProfile = ExcelHolder.getKey( map, "BillProfile" );
		billPeriod = ExcelHolder.getKey( map, "BillPeriod" );
		tariffs = ExcelHolder.getKey( map, "Tariffs" );
		bands = ExcelHolder.getKey( map, "Bands" );
		entities = ExcelHolder.getKey( map, "Entities" );
		values = ExcelHolder.getKey( map, "Values" );
		account = ExcelHolder.getKey( map, "Account" );
		billPeriodOnly = ExcelHolder.getKey( map, "BillPeriodOnly" );
		schedulingName = ExcelHolder.getKey( map, "SchedulingName" );
		schedulingDesc = ExcelHolder.getKey( map, "SchedulingDesc" );
		rerateOpenBillPeriods = ExcelHolder.getKey( map, "RerateOpenBillPeriods" );

	}
}
