package com.subex.rocps.automation.helpers.application.eventandaggregation.eventandaggregationconfig;

import java.util.Map;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.componentHelpers.TabElementHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class EventAndAggConfigDetails extends PSAcceptanceTest
{

	protected Map<String, String> evtAggDetailsMap = null;

	String path;
	String workBookName;
	String sheetName;
	String testCaseName;
	int occurences;
	int colSize;
	int paramVal;
	int index;
	protected String inputFeedName;
	protected String eventTableSchema;
	protected String eventErrorTableSchema;
	protected String aggregationSchema;
	protected String agreementRequiredFlg;
	protected String duplicateCheckRequiredFlg;
	protected String bilateralsRequiredFlg;
	protected String normalisationRequiredFlg;
	protected String eventLegGroup;
	protected String evtAggInputFeedDetailsTCName;
	protected String evtAggNormTabDetailsTCName;
	protected String evtAggConfTabDetailsTCName;
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	/**
	 * @param evtAggMap
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * Constructor
	 */
	public EventAndAggConfigDetails( Map<String, String> evtAggMap, String path, String workBookName, String sheetName )
	{

		this.evtAggDetailsMap = evtAggMap;
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
	}

	public EventAndAggConfigDetails()
	{
	}

	public void configureEventAndAggregation() throws Exception
	{
		initializeVariable( evtAggDetailsMap );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		configureGeneralDetails();
		EvtAggInputFeedDetails evtAggInputFeed = new EvtAggInputFeedDetails( path, workBookName, sheetName, evtAggInputFeedDetailsTCName );
		evtAggInputFeed.configureInputFeedDetails();
		if ( ValidationHelper.isNotEmpty( evtAggNormTabDetailsTCName ) )
		{
			navigateToTab( "Normal" );
			EvtAggNormTabDetails evtAggNormTab = new EvtAggNormTabDetails( path, workBookName, sheetName, evtAggNormTabDetailsTCName, normalisationRequiredFlg );
			evtAggNormTab.configureNormTabDetails();
		}
		navigateToTab( "Configuration" );
		EvtAggConfTabDetails evtAggConfTab = new EvtAggConfTabDetails( path, workBookName, sheetName, evtAggConfTabDetailsTCName, bilateralsRequiredFlg, agreementRequiredFlg, normalisationRequiredFlg );
		evtAggConfTab.configureConfTabDetails();
	}

	public void configureGeneralDetails() throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		TextBoxHelper.type( "PSDetail_Event_Aggregation_InputFeedName_Text_ID", inputFeedName );
		ComboBoxHelper.select( "PSDetail_Event_Aggregation_EventTableSchema_Combo_ID", eventTableSchema );
		ComboBoxHelper.select( "PSDetail_Event_Aggregation_EventErrorTableSchema_Combo_ID", eventErrorTableSchema );
		ComboBoxHelper.select( "PSDetail_Event_Aggregation_AggregationSchema_Combo_ID", aggregationSchema );

		if ( ValidationHelper.isTrue( duplicateCheckRequiredFlg ) )
			CheckBoxHelper.check( "PSDetail_Event_Aggregation_DuplicateCheckRequired_Check_ID" );

		if ( ValidationHelper.isTrue( normalisationRequiredFlg ) )
			CheckBoxHelper.check( "PSDetail_Event_Aggregation_NormalisationRequired_Check_ID" );

		if ( ValidationHelper.isTrue( bilateralsRequiredFlg ) )
			CheckBoxHelper.check( "PSDetail_Event_Aggregation_BilateralsRequired_Check_ID" );

		if ( ValidationHelper.isTrue( agreementRequiredFlg ) )
			CheckBoxHelper.check( "PSDetail_Event_Aggregation_AgreementRequired_Check_ID" );

		ComboBoxHelper.select( "PSDetail_Event_Aggregation_EventLegGroup_Combo_ID", eventLegGroup );

	}

	private void initializeVariable( Map<String, String> map ) throws Exception
	{

		inputFeedName = ExcelHolder.getKey( map, "InputFeedName" );
		eventTableSchema = ExcelHolder.getKey( map, "EventTableSchema" );
		eventErrorTableSchema = ExcelHolder.getKey( map, "EventErrorTableSchema" );
		aggregationSchema = ExcelHolder.getKey( map, "AggregationSchema" );
		duplicateCheckRequiredFlg = ExcelHolder.getKey( map, "DuplicateCheckRequiredFlg" );
		normalisationRequiredFlg = ExcelHolder.getKey( map, "NormalisationRequiredFlg" );
		bilateralsRequiredFlg = ExcelHolder.getKey( map, "BilateralsRequiredFlg" );
		agreementRequiredFlg = ExcelHolder.getKey( map, "AgreementRequiredFlg" );
		eventLegGroup = ExcelHolder.getKey( map, "EventLegGroup" );
		evtAggInputFeedDetailsTCName = ExcelHolder.getKey( map, "InputFeedDetailsTCName" );
		evtAggNormTabDetailsTCName = ExcelHolder.getKey( map, "NormalisationDetailsTCName" );
		evtAggConfTabDetailsTCName = ExcelHolder.getKey( map, "ConfigurationDetailsTCName" );

	}

	public void navigateToTab( String tabName ) throws Exception
	{

		TabHelper.gotoTab( "//*[@id='wrokflowTopPanel']//div[contains(text(),'" + tabName + "')]" );

	}

	public void openPanel( String panelName ) throws Exception
	{
		String panelXpath = "//*[@id='wrapper']//div[contains(text(),'" + panelName + "')]";

		WebElement element = TabElementHelper.getElement( panelXpath );

		if ( element == null )
		{
			FailureHelper.failTest( "Tab '" + panelName + "' is not found." );
		}
		else
		{
			MouseHelper.click( element );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
		}

	}

	//write a generic method to navigate to tabs

}
