package com.subex.rocps.automation.helpers.application.admin.alertGroup;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.admin.alertEvent.AlertDetailsImpl;
import com.subex.rocps.automation.helpers.application.admin.alertEvent.EntityAddedDeletedImpl;
import com.subex.rocps.automation.helpers.application.admin.alertEvent.FieldChangeImpl;
import com.subex.rocps.automation.helpers.application.bulkentityexport.BulkEntityExportImpl;
import com.subex.rocps.automation.helpers.application.bulkentityexport.BulkEntityFileHelper;
import com.subex.rocps.automation.helpers.application.deal.deal.DealBandConfiguration;
import com.subex.rocps.automation.helpers.application.deal.deal.DealRatesImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class AlertGroup extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> bulkExcelMap = null;
	protected Map<String, ArrayList<String>> excelReaderMapObj = null;
	protected AlertDetailsImpl detailsObj = new AlertDetailsImpl();
	protected EntityAddedDeletedImpl entityObj = new EntityAddedDeletedImpl();
	protected FieldChangeImpl changeObj = new FieldChangeImpl();
	protected Map<String, String> alertMap = null;
	protected ExcelHolder excelHolderObj = null;
	Map<String, ArrayList<String>> configMap = null;
	protected Map<String, String> mapObj = null;
	AlertGroupImpl grpObj = new AlertGroupImpl();
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
	String name;
	String resultEntity;
	String resultComponent;
	String definition;
	String alertNo;
	String alertText;
	String alertSeverity;
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

	/*
	 * Constructor : Initialising the excel
	 */
	public AlertGroup( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
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
	public AlertGroup( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
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
	public void alertGroupCreation() throws Exception
	{
		try
		{
			GenericHelper.waitForLoadmask();
			NavigationHelper.navigateToScreen( "Alert Groups" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				alertMap = excelHolderObj.dataMap( paramVal );
				initializeInstanceVariables( alertMap );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				PSGenericHelper genObj = new PSGenericHelper();
				if ( !genObj.isGridTextValuePresent( "PS_Detail_AlertGroup_name", name, "Name" ) )
				{

					grpObj.createNewGroup( partition );
					grpObj.headerDetails( name, guidedEntity, resultEntity, resultComponent );
					grpObj.alertDefinitions( definition, alertNo, alertSeverity, alertText );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					grpObj.save(name,"Name");
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					Log4jHelper.logInfo( "Alert Group "+name+" created." );
				}
				else
				{
					Log4jHelper.logInfo( "Alert Group "+name+" is already present." );
				}

			}

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public void initializeInstanceVariables( Map<String, String> alertMap ) throws Exception
	{
		partition = ExcelHolder.getKey( alertMap, "Partition" );
		name = ExcelHolder.getKey( alertMap, "Name" );
		guidedEntity = ExcelHolder.getKey( alertMap, "GuidedEntity" );
		resultEntity = ExcelHolder.getKey( alertMap, "ResultEntity" );
		resultComponent = ExcelHolder.getKey( alertMap, "ResultComponent" );
		definition = ExcelHolder.getKey( alertMap, "Definition" );
		alertNo = ExcelHolder.getKey( alertMap, "AlertNo" );
		alertSeverity = ExcelHolder.getKey( alertMap, "AlertSeverity" );
		alertText = ExcelHolder.getKey( alertMap, "AlertText" );

	}
}
