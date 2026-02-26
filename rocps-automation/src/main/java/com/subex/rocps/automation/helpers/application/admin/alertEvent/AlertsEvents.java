package com.subex.rocps.automation.helpers.application.admin.alertEvent;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.AlertsHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bulkentityexport.BulkEntityExportImpl;
import com.subex.rocps.automation.helpers.application.bulkentityexport.BulkEntityFileHelper;
import com.subex.rocps.automation.helpers.application.deal.deal.DealBandConfiguration;
import com.subex.rocps.automation.helpers.application.deal.deal.DealRatesImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class AlertsEvents extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> bulkExcelMap = null;
	protected Map<String, ArrayList<String>> excelReaderMapObj = null;
	protected AlertDetailsImpl detailsObj = new AlertDetailsImpl();
	protected EntityAddedDeletedImpl entityObj = new EntityAddedDeletedImpl();
	protected FieldChangeImpl changeObj = new FieldChangeImpl();
	protected QueryBasedImpl queryObj = new QueryBasedImpl();
	protected ComponentImpl compObj = new ComponentImpl();
	protected Map<String, String> alertMap = null;
	protected ExcelHolder excelHolderObj = null;
	Map<String, ArrayList<String>> configMap = null;
	protected Map<String, String> mapObj = null;
	AlertsHelper alertObj = new AlertsHelper();
	PSGenericHelper genObj = new PSGenericHelper();
	String path;
	String workBookName;
	String sheetName;
	String testCaseName;
	int occurence;
	String columnHeader;
	String results;
	int colSize;
	int paramVal;
	String partition;
	String entityAddedDeleted;
	String queryBased;
	String fieldChange;
	String component;
	String added;
	String active;
	String mobileApp;
	String alertEmailLevel;
	String systemAlertLevel;
	String alertEventName;
	String alertEventGroup;
	String guidedEntity;
	String alertDefinition;
	String stream;
	String order;
	String priority;
	String otherDetails;
	String fieldChangeColumn;
	String fieldChangeValue;
	String frequency;
	String dayOf;
	String alignmentDate;
	String trendRequired;
	String trendValue;
	String repeatAlert;
	String componentDetails;

	/*
	 * Constructor : Initialising the excel
	 */
	public AlertsEvents( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		bulkExcelMap = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( bulkExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Constructor : Initialising the excel
	 * 
	 * @Param : occurence of test case in a sheet
	 */
	public AlertsEvents( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		bulkExcelMap = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, this.occurence );
		excelHolderObj = new ExcelHolder( bulkExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Alert
	 * 
	 */
	public void alertCreation() throws Exception
	{
		try
		{
			GenericHelper.waitForLoadmask();
			NavigationHelper.navigateToScreen( "Alert Events" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				alertMap = excelHolderObj.dataMap( paramVal );
				initializeInstanceVariables( alertMap );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isAlertEventPresent = genObj.isDataPresent( alertEventName, "Name"  );
				if ( isAlertEventPresent == false )
				{

					if ( ValidationHelper.isTrue( entityAddedDeleted ) )
					{
						entityObj.newAlertEvent( partition );
						entityObj.alertGenerationCondition( added );
						detailsObj.alertEventProperties( active, mobileApp, alertEmailLevel, systemAlertLevel );
						commonOperations();
						otherDetailsTestCaseInitialize( this.path, this.workBookName, this.sheetName, otherDetails );
						detailsObj.save(alertEventName, "Name");
						GenericHelper.waitForLoadmask( searchScreenWaitSec );
						Log4jHelper.logInfo( "Alert Event "+alertEventName+" created" );
						

					}

					else if ( ValidationHelper.isTrue( fieldChange ) )
					{
						changeObj.newAlertEvent( partition );
						detailsObj.alertEventProperties( active, mobileApp, alertEmailLevel, systemAlertLevel );
						commonOperations();
						changeObj.fieldChangeConfiguration( fieldChangeColumn, fieldChangeValue );
						otherDetailsTestCaseInitialize( this.path, this.workBookName, this.sheetName, otherDetails );
						changeObj.save(alertEventName, "Name") ;
						GenericHelper.waitForLoadmask( searchScreenWaitSec );
						Log4jHelper.logInfo( "Alert Event "+alertEventName+" created" );
					}

					else if ( ValidationHelper.isTrue( queryBased ) )
					{
						queryObj.newAlertEvent( partition );
						queryObj.scheduleFrequency( frequency, dayOf, alignmentDate );
						commonOperations();
						queryObj.alertEventProperties( active, mobileApp, alertEmailLevel, systemAlertLevel, trendRequired, trendValue, repeatAlert );
						otherDetailsQueryTestCaseInitialize( this.path, this.workBookName, this.sheetName, otherDetails );
						queryObj.save(alertEventName, "Name");
						GenericHelper.waitForLoadmask( searchScreenWaitSec );
						Log4jHelper.logInfo( "Alert Event "+alertEventName+" created" );

					}
					else if ( ValidationHelper.isTrue( component ) )
					{
						compObj.newAlertEvent( partition );
						compObj.scheduleFrequency( frequency, dayOf, alignmentDate );
						detailsObj.alertEventProperties( active, mobileApp, alertEmailLevel, systemAlertLevel );
						commonOperations();
						otherDetailsQueryTestCaseInitialize( this.path, this.workBookName, this.sheetName, otherDetails );
						componentDetailsTestCaseInitialize( this.path, this.workBookName, this.sheetName, componentDetails );
						queryObj.save(alertEventName, "Name");
						Log4jHelper.logInfo( "Alert Event "+alertEventName+" created" );

					}

				}
				else
				{
					Log4jHelper.logInfo( "Alert Event "+alertEventName+" is already present." );
				}

			}

		}

		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public void commonOperations() throws Exception
	{
		detailsObj.headerDetails( alertEventName, alertEventGroup );
		detailsObj.entityDetails( guidedEntity, alertDefinition );
		detailsObj.alertEventStream( stream, order );
		detailsObj.priorities( priority );
	}

	private void otherDetailsTestCaseInitialize( String path, String workBook, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBook;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		configMap = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( configMap );
		colSize = excelHolderObj.totalColumns();
		for ( int paramVal = 0; paramVal < colSize; paramVal++ )
		{
			mapObj = excelHolderObj.dataMap( paramVal );
			OtherDetailsImpl otherObj = new OtherDetailsImpl( mapObj );
			otherObj.switchToOtherDetailsTab();
			otherObj.completeOtherDetails();
		}
	}

	private void otherDetailsQueryTestCaseInitialize( String path, String workBook, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBook;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		configMap = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( configMap );
		colSize = excelHolderObj.totalColumns();
		for ( int paramVal = 0; paramVal < colSize; paramVal++ )
		{
			mapObj = excelHolderObj.dataMap( paramVal );
			QueryBasedOtherDetails otherObj = new QueryBasedOtherDetails( mapObj );
			otherObj.switchToOtherDetailsTab();
			otherObj.completeOtherDetails();
		}
	}

	private void componentDetailsTestCaseInitialize( String path, String workBook, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBook;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		configMap = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( configMap );
		colSize = excelHolderObj.totalColumns();
		for ( int paramVal = 0; paramVal < colSize; paramVal++ )
		{
			mapObj = excelHolderObj.dataMap( paramVal );
			ComponentDetails compObj = new ComponentDetails( mapObj );
			compObj.switchComponentDetailsTab();
			compObj.completeComponentDetails();
		}
	}

	public void initializeInstanceVariables( Map<String, String> alertMap ) throws Exception
	{
		partition = ExcelHolder.getKey( alertMap, "Partition" );
		entityAddedDeleted = ExcelHolder.getKey( alertMap, "EntityAddedDeleted" );
		queryBased = ExcelHolder.getKey( alertMap, "QueryBased" );
		fieldChange = ExcelHolder.getKey( alertMap, "FieldChange" );
		added = ExcelHolder.getKey( alertMap, "Added" );
		active = ExcelHolder.getKey( alertMap, "Active" );
		mobileApp = ExcelHolder.getKey( alertMap, "SendToMobileApp" );
		alertEmailLevel = ExcelHolder.getKey( alertMap, "AlertEmailLevel" );
		systemAlertLevel = ExcelHolder.getKey( alertMap, "SystemAlertLevel" );
		alertEventName = ExcelHolder.getKey( alertMap, "AlertEventName" );
		alertEventGroup = ExcelHolder.getKey( alertMap, "AlertEventGroup" );
		guidedEntity = ExcelHolder.getKey( alertMap, "GuidedEntity" );
		alertDefinition = ExcelHolder.getKey( alertMap, "AlertDefinition" );
		stream = ExcelHolder.getKey( alertMap, "AlertEventStream" );
		order = ExcelHolder.getKey( alertMap, "OrderNo" );
		priority = ExcelHolder.getKey( alertMap, "Priority" );
		otherDetails = ExcelHolder.getKey( alertMap, "OtherDetails" );
		fieldChangeColumn = ExcelHolder.getKey( alertMap, "FieldChangeColumn" );
		fieldChangeValue = ExcelHolder.getKey( alertMap, "FieldChangeValue" );
		frequency = ExcelHolder.getKey( alertMap, "Frequency" );
		dayOf = ExcelHolder.getKey( alertMap, "DayOf" );
		alignmentDate = ExcelHolder.getKey( alertMap, "AlignmentDate" );
		trendRequired = ExcelHolder.getKey( alertMap, "TrendRequired" );
		trendValue = ExcelHolder.getKey( alertMap, "TrendValue" );
		repeatAlert = ExcelHolder.getKey( alertMap, "RepeatAlert" );
		componentDetails = ExcelHolder.getKey( alertMap, "ComponentDetails" );

	}
}
