package com.subex.rocps.automation.helpers.application.partnerConfiguration.eventUsgReqScheduler;

import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.rocps.automation.helpers.application.filters.EnitityValuesSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.EventUsgRequestScheduler;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.eventUsgRequest.EventUsgReqDetailImpl;
import com.subex.rocps.automation.helpers.application.xdrextraction.XdrExtrTempHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class EventUsgReqSchedulerDetailImpl extends PSAcceptanceTest
{
	protected Map<String, String> eventUsgReqSchedulerDetailMap = null;
	protected String name;
	protected String description;
	protected String streamStage;
	protected String eventLookBack;
	protected String eventType;
	protected String xdrExtTemplate;
	protected String entities;
	protected String values;
	protected String viewXDRTemplateTestCase;
	protected String frequencyMultiplier;
	protected String frequency;
	protected String nextSchedule;
	protected String dayGroups;
	protected String path;
	protected String workBookNm;
	protected String sheetNm;
	EventUsgReqDetailImpl eventUsgReqDetailImpl = new EventUsgReqDetailImpl();
	EventUsgRequestScheduler eventUsgRequestScheduler = new EventUsgRequestScheduler();
	EnitityValuesSelectionHelper enitityValuesSelectionHelper = new EnitityValuesSelectionHelper();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	/**Constructor
	 * @param eventUsgReqSchedulerDetailMap
	 */
	public EventUsgReqSchedulerDetailImpl( Map<String, String> eventUsgReqSchedulerDetailMap )
	{

		this.eventUsgReqSchedulerDetailMap = eventUsgReqSchedulerDetailMap;
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{

		name = ExcelHolder.getKey( map, "Name" );
		description = ExcelHolder.getKey( map, "Description" );
		streamStage = ExcelHolder.getKey( map, "StreamStage" );
		eventLookBack = ExcelHolder.getKey( map, "EventLookBack" );
		eventType = ExcelHolder.getKey( map, "EventType" );
		xdrExtTemplate = ExcelHolder.getKey( map, "XdrExtTemplate" );
		entities = ExcelHolder.getKey( map, "Entities" );
		values = ExcelHolder.getKey( map, "Values" );
		viewXDRTemplateTestCase = ExcelHolder.getKey( map, "ViewXDRTemplateTestCase" );
		frequencyMultiplier = ExcelHolder.getKey( map, "FrequencyMultiplier" );
		frequency = ExcelHolder.getKey( map, "Frequency" );
		nextSchedule = ExcelHolder.getKey( map, "NextSchedule" );
		dayGroups = ExcelHolder.getKey( map, "DayGroups" );

	}

	// Method: Initialize the variables
	public void initializePathWorkBkNmSheetNm()
	{
		path = eventUsgRequestScheduler.getPath();
		workBookNm = eventUsgRequestScheduler.getWorkbookName();
		sheetNm = eventUsgRequestScheduler.getSheetName();
	}

	/*Method is for configure of   Event usage request scheduler*/
	public void configEventUsgScheduler() throws Exception
	{
		initializeVariable( eventUsgReqSchedulerDetailMap );
		configSchdulerNamePanel();
		configStreamStageXdrTempl();
		configEntitiesValue();
		configScheduleTime();
		clickOnSave();
	}

	/*Method is for modify of   Event usage request scheduler*/
	public void modifyEventUsgScheduler() throws Exception
	{
		initializeVariable( eventUsgReqSchedulerDetailMap );
		modifySchdulerNamePanel();
		modifyStreamStageXdrTempl();
		configEntitiesValue();
		configScheduleTime();
		clickOnSave();
	}

	/*Method is for save as of   Event usage request scheduler*/
	public void saveAsEventUsgScheduler( String name, String newName ) throws Exception
	{
		initializeVariable( eventUsgReqSchedulerDetailMap );
		saveAsSchdulerNamePanel( name, newName );
		modifyStreamStageXdrTempl();
		configEntitiesValue();
		configScheduleTime();
		clickOnSave();
	}

	/*Method is for configure of   name panel*/
	protected void configSchdulerNamePanel() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_eventUsgReqSch_name_txtId", name );
		TextAreaHelper.type( "PS_Detail_eventUsgReqSch_description_txtareaId", description );
	}

	/*Method is for modify of   name panel*/
	protected void modifySchdulerNamePanel() throws Exception
	{
		assertEquals( TextBoxHelper.getValue( "PS_Detail_eventUsgReqSch_name_txtId" ), name, "Name is not matched of Event Usage Request Scheduler" );
		psDataComponentHelper.modifyTextAreaBox( "PS_Detail_eventUsgReqSch_description_txtareaId", description );
	}

	/*Method is for save of   name panel*/
	protected void saveAsSchdulerNamePanel( String name, String newName ) throws Exception
	{
		assertEquals( TextBoxHelper.getValue( "PS_Detail_eventUsgReqSch_name_txtId" ), name, "Name is not matched of Event Usage Request Scheduler" );
		psDataComponentHelper.modifyTextBox( "PS_Detail_eventUsgReqSch_name_txtId", newName );
		psDataComponentHelper.modifyTextAreaBox( "PS_Detail_eventUsgReqSch_description_txtareaId", description );
	}

	/*Method is for configure of  stream stage, xdr extraction template*/
	protected void configStreamStageXdrTempl() throws Exception
	{
		ComboBoxHelper.select( "PS_Detail_eventUsgReq_streamStg_ComboID", streamStage );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		TextBoxHelper.type( "PS_Detail_eventUsgReqSch_eventLookback_txtId", eventLookBack );
		if ( ValidationHelper.isNotEmpty( eventType ) )
			assertEquals( ComboBoxHelper.getValue( "PS_Detail_eventUsgReq_eventType_ComboID" ), eventType, "Event Type is not matched" );
		configXdrTemplateEntitySearch( xdrExtTemplate );
	}

	/*Method is for modify of  stream stage, xdr extraction template*/
	protected void modifyStreamStageXdrTempl() throws Exception
	{
		psDataComponentHelper.modifyComboBox( "PS_Detail_eventUsgReq_streamStg_ComboID", streamStage );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psDataComponentHelper.modifyTextBox( "PS_Detail_eventUsgReqSch_eventLookback_txtId", eventLookBack );
		if ( ValidationHelper.isNotEmpty( eventType ) )
			assertEquals( ComboBoxHelper.getValue( "PS_Detail_eventUsgReq_eventType_ComboID" ), eventType, "Event Type is not matched" );
		configXdrTemplateEntitySearch( xdrExtTemplate );
	}

	// Methods: configure xdr template entity search
	private void configXdrTemplateEntitySearch( String xdrTemplate ) throws Exception
	{
		eventUsgReqDetailImpl.xdrTemplateEntitySearch( xdrTemplate );
		initializePathWorkBkNmSheetNm();
		eventUsgReqDetailImpl.viewTemplateScreen( path, workBookNm, sheetNm, viewXDRTemplateTestCase );
	}

	/*Method is for configure entities value*/
	protected void configEntitiesValue() throws Exception
	{
		if ( ValidationHelper.isNotEmpty( entities ) )
			enitityValuesSelectionHelper.entityGridConfig( "PS_Detail_eventUsgReqSch_entities_editor_comboId", "PS_Detail_eventUsgReqSch_entities_gridId", "PS_Detail_eventUsgReqSch_entities_add_btnId", entities, values, "PS_Detail_eventUsgReqSch_value_gridId", "PS_Detail_eventUsgReqSch_value_add_btnId", "Event Usage Request" );
	}

	/*Method is for configure of   Schedule  time*/
	private void configScheduleTime() throws Exception
	{

		TestDataHelper testDataObj = new TestDataHelper();
		String[][] dayGroupsArr = testDataObj.getStringValue( dayGroups, firstLevelDelimiter, secondLevelDelimiter );
		psDataComponentHelper.updateScheduleTimes( frequencyMultiplier, frequency, nextSchedule, dayGroupsArr );
	}

	/*Method is for click on save button*/
	private void clickOnSave() throws Exception
	{
		psGenericHelper.detailSave( "PS_Detail_eventUsgReqSch_save_btnId", name, "Name" );
	}
}
