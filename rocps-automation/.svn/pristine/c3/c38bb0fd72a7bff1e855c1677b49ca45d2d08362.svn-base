package com.subex.rocps.automation.helpers.application.partnerConfiguration.eventUsgRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.filters.EnitityValuesSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.EventUsgRequestHelper;
import com.subex.rocps.automation.helpers.application.xdrextraction.XdrExtrTempHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class EventUsgReqDetailImpl extends PSAcceptanceTest
{
	protected Map<String, String> eventUsgDetailImplMap;
	protected String colmHdrs;
	protected String requestType;
	protected String autoSchduleFlg;
	protected String account;
	protected String billProfile;
	protected String billPeriodFromDt;
	protected String billPeriodToDt;
	protected String description;
	protected String streamStage;
	protected String eventType;
	protected String eventFromDt;
	protected String eventToDt;
	protected String xdrTemplate;
	protected String viewXdrTemplateTestCase;
	protected String path;
	protected String workBookNm;
	protected String sheetNm;
	protected String lateTraffic;
	protected String entities;
	protected String values;
	protected Map<String, String> gridKeyValuemap = null;
	protected String colmnHdrsArr[];
	protected String entitiesArr[];
	protected String valuesArr[];

	PSStringUtils psStringUtils = new PSStringUtils();
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	DataSelectionHelper dataSelectionHelper = new DataSelectionHelper();
	EnitityValuesSelectionHelper enitityValuesSelectionImpl = new EnitityValuesSelectionHelper();
	EventUsgRequestHelper eventUsgRequestHelper = new EventUsgRequestHelper();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();

	// Constructor:
	public EventUsgReqDetailImpl( Map<String, String> eventUsgDetailImplMap ) throws Exception
	{

		this.eventUsgDetailImplMap = eventUsgDetailImplMap;
	}

	/**
	 * Constructor default
	 */
	public EventUsgReqDetailImpl()
	{

	}

	// Method: Initialize the variables
	public void initializeVariable( Map<String, String> map ) throws Exception
	{
		requestType = ExcelHolder.getKey( map, "RequestType" );
		autoSchduleFlg = ExcelHolder.getKey( map, "AutoSchedule" );
		account = ExcelHolder.getKey( map, "Account" );
		billProfile = ExcelHolder.getKey( map, "BillProfile" );
		billPeriodFromDt = ExcelHolder.getKey( map, "BillPeriodFromDt" );
		billPeriodToDt = ExcelHolder.getKey( map, "BillPeriodToDt" );
		description = ExcelHolder.getKey( map, "Description" );
		streamStage = ExcelHolder.getKey( map, "StreamStage" );
		eventType = ExcelHolder.getKey( map, "EventType" );
		eventFromDt = ExcelHolder.getKey( map, "EventFromDt" );
		eventToDt = ExcelHolder.getKey( map, "EventToDt" );
		xdrTemplate = ExcelHolder.getKey( map, "XdrExtTemplate" );
		viewXdrTemplateTestCase = ExcelHolder.getKey( map, "ViewXDRTemplateTestCase" );
		lateTraffic = ExcelHolder.getKey( map, "ConsiderLateTraffic" );
		entities = ExcelHolder.getKey( map, "Entities" );
		values = ExcelHolder.getKey( map, "Values" );
		if ( ValidationHelper.isNotEmpty( entities ) )
			entitiesArr = psStringUtils.stringSplitFirstLevel( entities );
		if ( ValidationHelper.isNotEmpty( values ) )
			valuesArr = psStringUtils.stringSplitFirstLevel( values );
	}

	// Method: Initialize the variables
	public void initializePathWorkBkNmSheetNm()
	{
		path = eventUsgRequestHelper.getPath();
		workBookNm = eventUsgRequestHelper.getWorkbookName();
		sheetNm = eventUsgRequestHelper.getSheetName();
	}

	// Method: Verify the column headers of Event Usage Request
	public void verifyColmnHeaderOfEventUsgReq() throws Exception
	{
		colmHdrs = ExcelHolder.getKey( eventUsgDetailImplMap, "SearchScreenColumns" );
		if ( ValidationHelper.isNotEmpty( colmHdrs ) )
			colmnHdrsArr = psStringUtils.stringSplitFirstLevel( colmHdrs );

		ArrayList<String> excelColumnNames = new ArrayList<String>();
		String evenErrGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
		for ( int col = 0; col < evenErrGridColumnsArr.length; col++ )
		{
			excelColumnNames.add( evenErrGridColumnsArr[col] );
		}
		psGenericHelper.totalColumns( excelColumnNames );
	}

	// Methods: configure event usage request
	public void configEventUsgRequest( String description ) throws Exception
	{
		createEventUsgRequest( description );
		configEnitiesValues();

	}

	// Methods: Save as event usage request
	public void configSaveAsEventUsgRequest( String currdDescription, String newDescription ) throws Exception
	{

		assertEquals( TextAreaHelper.getValue( "PS_Detail_eventUsgReq_description_textID" ), currdDescription, " Description is not matched" );
		modifyEventUsgRequest( newDescription );
		configEnitiesValues();

	}

	// Methods: create usage request
	public void createEventUsgRequest( String description ) throws Exception
	{
		initializeVariable( eventUsgDetailImplMap );
		validateDisabledField();
		ComboBoxHelper.select( "PS_Detail_eventUsgReq_reqType_comboId", requestType );
		configDescriptionAutoSchedule( description, autoSchduleFlg );
		if ( requestType.contentEquals( "Bill Based" ) )
			billBasedRequestType();
		if ( requestType.contentEquals( "Stream Stage Based" ) )
			streamStageBasedReqType();
	}

	// Methods: modify usage request
	public void modifyEventUsgRequest( String description ) throws Exception
	{
		initializeVariable( eventUsgDetailImplMap );
		String actualReqType = ComboBoxHelper.getValue( "PS_Detail_eventUsgReq_reqType_comboId" );
		psDataComponentHelper.checkComboBoxDisabled( "PS_Detail_eventUsgReq_reqType_comboId", requestType );
		configDescriptionAutoSchedule( description, autoSchduleFlg );
		if ( actualReqType.contentEquals( "Bill Based" ) )
			modifyBillBasedRequestType();
		if ( actualReqType.contentEquals( "Stream Stage Based" ) )
			modifyStreamStageBasedReqType();
	}

	// Methods: configure description and auto schedule
	public void configDescriptionAutoSchedule( String description, String autoSchduleFlg ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( description ) )
			TextAreaHelper.type( "PS_Detail_eventUsgReq_description_textID", description );
		if ( ValidationHelper.isTrue( autoSchduleFlg ) )
			CheckBoxHelper.check( "PS_Detail_eventUsgReq_autoScheFlg_chckBoxId" );
		if ( ValidationHelper.isFalse( autoSchduleFlg ) )
			CheckBoxHelper.uncheck( "PS_Detail_eventUsgReq_autoScheFlg_chckBoxId" );
	}

	// Methods: bill based request type
	public void billBasedRequestType() throws Exception
	{
		validateDisabledField();
		billEntitySearch();
		if ( ValidationHelper.isTrue( lateTraffic ) )
			CheckBoxHelper.check( "PS_Detail_eventUsgReq_lateTraffic_ChkBxID" );
		if ( ValidationHelper.isFalse( lateTraffic ) )
			CheckBoxHelper.uncheck( "PS_Detail_eventUsgReq_lateTraffic_ChkBxID" );
		configXdrTemplateEntitySearch( xdrTemplate );
	}

	// Methods: modify bill based request type
	public void modifyBillBasedRequestType() throws Exception
	{
		validateEnabledField();
		billEntitySearch();
		if ( ValidationHelper.isTrue( lateTraffic ) )
			CheckBoxHelper.check( "PS_Detail_eventUsgReq_lateTraffic_ChkBxID" );
		if ( ValidationHelper.isFalse( lateTraffic ) )
			CheckBoxHelper.uncheck( "PS_Detail_eventUsgReq_lateTraffic_ChkBxID" );
		configXdrTemplateEntitySearch( xdrTemplate );
	}

	// Methods: stream stage based request type
	public void streamStageBasedReqType() throws Exception
	{
		ComboBoxHelper.select( "PS_Detail_eventUsgReq_streamStg_ComboID", streamStage );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		validateEnabledField();
		configXdrTemplateEntitySearch( xdrTemplate );
		TextBoxHelper.type( "PS_Detail_eventUsgReq_eventFromDt_textID", eventFromDt );
		TextBoxHelper.type( "PS_Detail_eventUsgReq_eventToDt_textID", eventToDt );
		if ( ValidationHelper.isNotEmpty( eventType ) )
			assertEquals( ComboBoxHelper.getValue( "PS_Detail_eventUsgReq_eventType_ComboID" ), eventType, "Event Type is not matched" );

	}

	// Methods: modify stream stage based request type
	public void modifyStreamStageBasedReqType() throws Exception
	{
		validateEnabledField();
		psDataComponentHelper.modifyComboBox( "PS_Detail_eventUsgReq_streamStg_ComboID", streamStage );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		modifyxdrTemplateEntitySearch( xdrTemplate );
		psDataComponentHelper.modifyTextBox( "PS_Detail_eventUsgReq_eventFromDt_textID", eventFromDt );
		psDataComponentHelper.modifyTextBox( "PS_Detail_eventUsgReq_eventToDt_textID", eventToDt );
		if ( ValidationHelper.isNotEmpty( eventType ) )
			assertEquals( ComboBoxHelper.getValue( "PS_Detail_eventUsgReq_eventType_ComboID" ), eventType, "Event Type is not matched" );
	}

	// Methods: validate disabled field
	public void validateDisabledField() throws Exception
	{
		assertTrue( EntityComboHelper.isDisabled( "PS_Detail_eventUsgReq_xdrExtTemp_triggerID" ), "Xdr Extraction Template should be disabled" );
		assertTrue( TextBoxHelper.isDisabled( "PS_Detail_eventUsgReq_eventFromDt_textID" ), "Event From date should be disabled" );
		assertTrue( TextBoxHelper.isDisabled( "PS_Detail_eventUsgReq_eventToDt_textID" ), "Event To Date should be disabled" );
		assertTrue( ComboBoxHelper.isDisabled( "PS_Detail_eventUsgReq_eventType_ComboID" ), "Event Type should be disabled" );

	}

	// Methods: validate enabled field
	public void validateEnabledField() throws Exception
	{
		assertTrue( EntityComboHelper.isEnabled( "PS_Detail_eventUsgReq_xdrExtTemp_triggerID" ), "Xdr Extraction Template should be enabled" );
		assertTrue( TextBoxHelper.isEnabled( "PS_Detail_eventUsgReq_eventFromDt_textID" ), "Event From date should be Enabled" );
		assertTrue( TextBoxHelper.isEnabled( "PS_Detail_eventUsgReq_eventToDt_textID" ), "Event To Date should be Enabled" );
		assertTrue( ComboBoxHelper.isEnabled( "PS_Detail_eventUsgReq_eventType_ComboID" ), "Event Type should be Enabled" );

	}

	/*// Methods: validate field for stream stage
	public void validateFieldOfStremStage() throws Exception
	{
		assertTrue( EntityComboHelper.isDisabled( "PS_Detail_eventUsgReq_xdrExtTemp_triggerID" ), "Xdr Extraction Template should be disabled" );
		assertTrue( TextBoxHelper.isEnabled( "PS_Detail_eventUsgReq_eventFromDt_textID" ), "Event From date should be Enabled" );
		assertTrue( TextBoxHelper.isEnabled( "PS_Detail_eventUsgReq_eventToDt_textID" ), "Event To Date should be Enabled" );
		assertTrue( ComboBoxHelper.isDisabled( "PS_Detail_eventUsgReq_eventType_ComboID" ), "Event Type should be disabled" );
	}*/

	// Method bill entity search
	public void billEntitySearch() throws Exception
	{
		psGenericHelper.waitforEntityElement();
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		EntityComboHelper.clickEntityIcon( "PS_Detail_eventUsgReq_billSearch_triggerID" );
		List<String> listColumn = getKeysOfBillGrid();
		gridKeyValuemap = dataSelectionHelper.billsearch( account, billProfile, billPeriodFromDt, billPeriodToDt, "SearchGrid", "grid_column_header_searchGrid_", listColumn );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		billEntityAssertion();
	}

	// Method bill entity assertion
	private void billEntityAssertion() throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_eventUsgReq_eventType_ComboID" ), eventType );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );

		String actualEventFromDt = TextBoxHelper.getValue( "PS_Detail_eventUsgReq_eventFromDt_textID" );
		assertTrue( actualEventFromDt.contains( gridKeyValuemap.get( "Bill Period From" ) ), "'Event From Date'  is not matched on 'event usage request detail screen' for Bill based" );

		String actualEventToDt = TextBoxHelper.getValue( "PS_Detail_eventUsgReq_eventToDt_textID" );
		assertTrue( actualEventToDt.contains( gridKeyValuemap.get( "Bill Period To" ) ), "'Event To  Date' is not matched on  'event usage request detail screen' for Bill based" );

		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitTime( 2, "" );
		clickOnHeaderPanelArrow("Basic Filters");
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		String entityValue = GridHelper.getCellValue( "billProfileGrid", 1, "Profile" );
		assertTrue( entityValue.contains( billProfile ) );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		clickOnHeaderPanelArrow("Basic Filters");

	}

	/* This method is for modify xdrTemplateEntitySearch */
	private void modifyxdrTemplateEntitySearch( String xdrTemplate ) throws Exception
	{

		if ( ValidationHelper.isNotEmpty( xdrTemplate ) && !EntityComboHelper.getValue( "PS_Detail_eventUsgReq_xdrExtTemp_entityID" ).equals( xdrTemplate ) )
			xdrTemplateEntitySearch( xdrTemplate );
		initializePathWorkBkNmSheetNm();
		viewTemplateScreen( path, workBookNm, sheetNm, viewXdrTemplateTestCase );
	}

	// Methods: configure xdr template entity search
	private void configXdrTemplateEntitySearch( String xdrTemplate ) throws Exception
	{
		xdrTemplateEntitySearch( xdrTemplate );
		initializePathWorkBkNmSheetNm();
		viewTemplateScreen( path, workBookNm, sheetNm, viewXdrTemplateTestCase );

	}

	// Method: xdr template entity search
	public void xdrTemplateEntitySearch( String xdrTemplate ) throws Exception
	{

		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		EntityComboHelper.clickEntityIcon( "PS_Detail_eventUsgReq_xdrExtTemp_triggerID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforPopupHeaderElement( "Status" );
		int row = SearchGridHelper.gridFilterSearchWithTextBox( "PS_Detail_xdrExtTemp_name_textID", xdrTemplate, "Name" );
		GridHelper.clickRow( "SearchGrid", row, "Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	// Method view Xdr template screen
	public void viewTemplateScreen( String path, String workBookNm, String sheetNm, String testCase ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( testCase ) )
		{
			ButtonHelper.click( "viewTemplate" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "View XDR Extraction Template" );

			XdrExtrTempHelper xdrExtrTempHelper = new XdrExtrTempHelper( path, workBookNm, sheetNm, testCase );
			xdrExtrTempHelper.ViewXdrTemplatScreen();
		}
	}

	// Methods: configure entity value
	private void configEnitiesValues() throws Exception
	{
		if ( ValidationHelper.isNotEmpty( entities ) )
		{

			ArrayList<String>getBasicFilterLabelValues=getBasicFilterLabelValues();
			for(int i=0;i<entitiesArr.length;i++)
			{
				String filterLabelEntity=entitiesArr[i];
				if(getBasicFilterLabelValues.stream().anyMatch(x->x.contains(filterLabelEntity)))
				   entitiesOfBasicFilterLabel(entitiesArr[i], valuesArr[i]);
				else 
					entitieOfAdvanceFilterLabel();
			}
			
		}
		}
	private void entitiesOfBasicFilterLabel(String filterLabelEntity ,String filterLabelValue) throws Exception
	{
		
			clickOnHeaderPanelArrow("Basic Filters");
			if(!ElementHelper.isElementPresent(getXpathOfLabelFilterValue("Bands")))
				clickOnHeaderPanelArrow("Basic Filters");
			switch (filterLabelEntity) {
			case "Band":
				configureBandEntityVaue(filterLabelValue);
				break;
			case "Tariff":
				configureTariffEntityVaue(filterLabelValue);
                break;
			case "Service":
				configureServiceEntityVaue(filterLabelValue);
				break;
			default:
				Log4jHelper.logInfo("The given filterlabel entity is not found:-"+filterLabelEntity);
				break;
			}
			clickOnHeaderPanelArrow("Basic Filters");
			entities=entities.replace(filterLabelEntity, "");
			
			if(entities.startsWith("|"))
			   entities=entities.substring(1, entities.length());
			if(entities.endsWith("|"))
				entities=entities.substring(0, entities.length()-1);
			
			values=values.replace(filterLabelValue,"");
			if(values.startsWith("|"))
				values=values.substring(1, values.length());
			if(values.endsWith("|"))
				values=values.substring(0, values.length()-1);
		
	}
	private void  entitieOfAdvanceFilterLabel() throws Exception
	{
		clickOnHeaderPanelArrow("Advanced Filters");
		if(!ElementHelper.isElementPresent(getXpathOfLabelFilterValue("Entities")))
			clickOnHeaderPanelArrow("Advanced Filters");
		enitityValuesSelectionImpl.entityGridConfig( "PS_Detail_eventUsgReq_entities_editor_ComboID", "PS_Detail_eventUsgReq_entities_GridID", "PS_Detail_eventUsgReq_ent_ADD_BtnID", entities, values, "PS_Detail_eventUsgReq_values_GridID", "PS_Detail_eventUsgReq_entVal_ADD_BtnID", "Event Usage Request" );
		clickOnHeaderPanelArrow("Advanced Filters");
	}
	private void configureBandEntityVaue(String bandVaue) throws Exception
	{
		String bandVaueArr[]=psStringUtils.stringSplitSecondLevel(bandVaue);
		for(int i=0;i<bandVaueArr.length;i++)
		{	
		ButtonHelper.click("toolbar-button-label-bandGridToolbar.Add");
		dataSelectionHelper.bandSelection(bandVaueArr[i]);
		}
	}
	
	private void configureTariffEntityVaue(String tariffVaue) throws Exception
	{
		String tariffVaueArr[]=psStringUtils.stringSplitSecondLevel(tariffVaue);
		for(int i=0;i<tariffVaueArr.length;i++)
		{
		ButtonHelper.click("toolbar-button-label-tariffGridToolbar.Add");
		dataSelectionHelper.tariffSelection(tariffVaueArr[i]);
		}
	}
	
	private void configureServiceEntityVaue(String serviceVaue) throws Exception
	{
		String serviceVaueArr[]=psStringUtils.stringSplitSecondLevel(serviceVaue);
		for(int i=0;i<serviceVaueArr.length;i++)
		{
		ButtonHelper.click("toolbar-button-label-serviceGridToolbar.Add");
		dataSelectionHelper.serviceSelection(serviceVaueArr[i]);
		}
	}
	
private void clickOnHeaderPanelArrow(String labelFilterName) throws Exception
{
	String xpath="//div/h1[text()='"+labelFilterName+"']/ancestor::div[@class='disclosure-panel-header-container']//div[@class='disclosure-panel-header-arrow']/img";
   MouseHelper.click(xpath); 
}
private String getXpathOfLabelFilterValue(String labelFilterEntityName)
{
	String xpath="//div[@id='manualDiscrepancyPanel']//div[contains(text(),'"+labelFilterEntityName+"')]/ancestor::div[starts-with(@id,'collapsable')]/parent::div[not(@aria-hidden='true')]";
    return xpath;
}
//Bill grid columns keys
	private ArrayList<String> getBasicFilterLabelValues()
	{
		ArrayList<String> listColumn = new ArrayList<String>();
		listColumn.add( "Band" );
		listColumn.add( "Tariff" );
		listColumn.add( "Bill Profile" );
		listColumn.add( "Service" );
		listColumn.add( "Bill Period" );
		return listColumn;

	}
	// Method: Save the Event Usage' Request
	public void saveEventUsgReq( String eventUsgReqDes, int oldRowCount ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "PS_Detail_eventUsgReq_save_BtnID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		boolean isSearchElemPresent = ElementHelper.isElementPresent( "searchPanel" );
		assertTrue( isSearchElemPresent, " Failed to save this Event Usage Request with description:- '" + eventUsgReqDes + "' with the error message:- " + LabelHelper.getText( "PS_Detail_xdrExtTemp_errorTxt_txtID" ) );
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		int newrowCount = GridHelper.getRowCount( "searchGrid" );
		assertEquals( newrowCount, ( oldRowCount + 1 ), "This event usage request is not found " );
	}

	// Bill grid columns keys
	private List<String> getKeysOfBillGrid()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "Bill Profile" );
		listColumn.add( "Bill Period From" );
		listColumn.add( "Bill Period To" );
		listColumn.add( "Bill Amount" );
		listColumn.add( "Bill Profile Currency" );
		listColumn.add( "Bill Ref No" );
		return listColumn;

	}
}
