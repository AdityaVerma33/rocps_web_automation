package com.subex.rocps.automation.helpers.application.eventandaggregation;

import java.util.ArrayList;
import java.util.Map;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.eventandaggregation.eventandaggregationconfig.EventAndAggregationActionImpl;
import com.subex.rocps.automation.helpers.application.eventandaggregation.eventandaggregationconfig.EventAndAggConfigDetails;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;


public class EventAndAggregation extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> evtAggExcelMap = null;
	protected Map<String, String> evtAggMap = null;
	protected ExcelHolder excelHolderObj = null;
	String path;
	String workBookName;
	String sheetName;
	String testCaseName;
	int occurences;
	int colSize;
	int paramVal;
	int index;
	String clientPartition;
	String eventAndAggregationName;
	String evtAggTaskRunFlg;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	DataVerificationHelper dataVerifyobj=new DataVerificationHelper();

	//Constructor without occurences
	public EventAndAggregation( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		evtAggExcelMap = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( evtAggExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	//Constructor with occurences
	public EventAndAggregation( String path, String workBookName, String sheetName, String testCaseName, int occurences ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurences = occurences;
		excelData = new ExcelReader();
		evtAggExcelMap = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, this.occurences );
		excelHolderObj = new ExcelHolder( evtAggExcelMap );
		colSize = excelHolderObj.totalColumns();

	}

	public void eventAndAggregationScreen() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Event And Aggregation Configuration" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			evtAggMap = excelHolderObj.dataMap( index );
			psGenericHelper.waitforHeaderElement( "Name" );

		}
		catch ( Exception e )
		{

		}
	}

	public void createEventAndAggConfiguration() throws Exception
	{
		
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				eventAndAggregationScreen();
				initializeVariable( evtAggMap );
				boolean isEvtAggConfigPresent = psGenericHelper.isGridTextValuePresent( "PSDetail_Event_Aggregation_Conf_filter_Text_ID",  eventAndAggregationName , "Name" );
				if(!isEvtAggConfigPresent)
				{
				EventAndAggregationActionImpl evtAggActionImpl = new EventAndAggregationActionImpl();
				evtAggActionImpl.clickNewAction( clientPartition );
				EventAndAggConfigDetails evtAggConfig = new EventAndAggConfigDetails( evtAggMap, path,workBookName, sheetName );
				evtAggConfig.configureEventAndAggregation();
				saveEvtAggConfiguration();
				GenericHelper.waitInSeconds( "20" );
				Log4jHelper.logInfo( "Event and Aggregation Configuration created with name " + eventAndAggregationName);
				
				}
				else {
					Log4jHelper.logInfo("Event and Aggregation Configuration already present with name "+ eventAndAggregationName);
				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public void eventAndAggregationColumnsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				eventAndAggregationScreen();
				String colmHdrs = ExcelHolder.getKey( evtAggMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Name", colmHdrs, "Event And Aggregation Configuration" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	public void initializeVariable( Map<String, String> map ) throws Exception
	{
		try
		{

			clientPartition = ExcelHolder.getKey( map, "Partition" );
			eventAndAggregationName=ExcelHolder.getKey(map,"InputFeedName");
			evtAggTaskRunFlg=ExcelHolder.getKey( map, "ConfirmTaskRunFlg" );

		}
		catch ( Exception e )
		{

		}
	}
	
	public void saveEvtAggConfiguration() throws Exception
	{
		ButtonHelper.click( "PSDetail_Event_Aggregation_Save_Button_ID" );
		GenericHelper.waitForElement( "PSDetail_Event_Aggregation_Cofirm_Task_Run_Dialog", 3 );
		if(ValidationHelper.isTrue(evtAggTaskRunFlg ))
			ButtonHelper.click( "PSDetail_Event_Aggregation_Task_Confirm_Yes_Button_ID" );
		else
			ButtonHelper.click( "PSDetail_Event_Aggregation_Task_Confirm_No_Button_ID" );
	}
	
	public void evtAggCreationValidation() throws Exception
	{
		eventAndAggregationScreen();
		String colmHdrs = ExcelHolder.getKey( evtAggMap, "ColumnHeaders" );
		String rowsData=ExcelHolder.getKey( evtAggMap, "RowData" );
		psGenericHelper.isGridTextValuePresent( "PSDetail_Event_Aggregation_Conf_filter_Text_ID",  eventAndAggregationName , "Name" );
		dataVerifyobj.validateData( "PSDetail_Event_Aggregation_Search_Grid_HeaderName_ID", colmHdrs, "PSDetail_Event_Aggregation_Search_Grid_ID", rowsData );
	}

}
