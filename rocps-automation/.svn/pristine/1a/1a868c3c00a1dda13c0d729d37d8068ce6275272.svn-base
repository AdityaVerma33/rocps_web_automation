package com.subex.rocps.automation.helpers.application.eventandaggregation.eventandaggregationconfig;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class EvtAggInputFeedDetails extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> evtAggInputFeedExcelMap = null;
	protected Map<String, String> evtAggInputFeedMap = null;
	protected ExcelHolder excelHolderObj = null;

	String path;
	String workBookName;
	String sheetName;
	String testCaseName;
	int colSize;
	int paramVal;
	int index;
	String inputField;
	String inputFieldArr[];
	String displayName;
	String displayNameArr[];
	String mandatoryFlg;
	String mandatoryFlgArr[];
	String datatype;
	String datatypeArr[];
	String eventDateFlg;
	String eventDateFlgArr[];
	String ratingToFlg;
	String ratingToFlgArr[];
	String ratingFromFlg;
	String ratingFromFlgArr[];
	String defaultAggregation;
	String defaultAggregationArr[];

	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils=new PSStringUtils();

	public EvtAggInputFeedDetails( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		evtAggInputFeedExcelMap = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( evtAggInputFeedExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	public void configureInputFeedDetails() throws Exception
	{

			evtAggInputFeedMap = excelHolderObj.dataMap( index );
			initializeVariable( evtAggInputFeedMap );
			initializeInputFeedArr();
			configInputFeedGrid();
		

	}

	public void configInputFeedGrid() throws Exception
	{
		String gridId = "PSDetail_Event_Aggregation_InputFeed_Grid_ID";
		 int rowNum =0;
		 for(int i=0;i<inputFieldArr.length;i++)
		 {
			rowNum=i+1; 
		 
		ButtonHelper.click( "PSDetail_Event_Aggregation_InputFeed_Add_Button_ID" );
		GridHelper.updateGridTextBox( gridId, "PSDetail_Event_Aggregation_InputFeed_FieldName_Text_ID", rowNum, "Input Field", inputFieldArr[i] );
		GridHelper.updateGridTextBox( gridId, "PSDetail_Event_Aggregation_InputFeed_DisplayName_Text_ID", rowNum, "Display Name", displayNameArr[i] );
		
		if (ValidationHelper.isNotEmpty( mandatoryFlg ) && ValidationHelper.isTrue( mandatoryFlgArr[i] ) )
			GridHelper.updateGridCheckBox( gridId, "PSDetail_Event_Aggregation_InputFeed_Mandatory_Check_ID", rowNum, "Mandatory", mandatoryFlgArr[i] );

		GridHelper.updateGridComboBox( gridId, "PSDetail_Event_Aggregation_InputFeed_Datatype_Combo_ID", rowNum, "Data Type", datatypeArr[i] );

		if (ValidationHelper.isNotEmpty( eventDateFlg ) &&  ValidationHelper.isTrue( eventDateFlgArr[i] ) )
			GridHelper.updateGridCheckBox( gridId, "PSDetail_Event_Aggregation_InputFeed_EventDate_Check_ID", rowNum, "Event Date Field", eventDateFlgArr[i] );
		if (ValidationHelper.isNotEmpty( ratingFromFlg ) &&  ValidationHelper.isTrue( ratingFromFlgArr[i] ) )
			GridHelper.updateGridCheckBox( gridId, "PSDetail_Event_Aggregation_InputFeed_RatingFrom_Check_ID", rowNum, "Rating From", ratingFromFlgArr[i] );
		if (ValidationHelper.isNotEmpty( ratingToFlg ) &&  ValidationHelper.isTrue( ratingToFlgArr[i] ) )
			GridHelper.updateGridCheckBox( gridId, "PSDetail_Event_Aggregation_InputFeed_RatingTo_Check_ID", rowNum, "Rating To", ratingToFlgArr[i] );
		
		GridHelper.updateGridComboBox( gridId, "PSDetail_Event_Aggregation_InputFeed_DefaultAggregation_Combo_ID", rowNum, "Default Aggregation", defaultAggregationArr[i] );

		 }
	}

	private void initializeVariable( Map<String, String> map ) throws Exception
	{

		inputField = ExcelHolder.getKey( map, "FieldName" );
		displayName = ExcelHolder.getKey( map, "DisplayName" );
		mandatoryFlg = ExcelHolder.getKey( map, "Mandatory" );
		datatype = ExcelHolder.getKey( map, "Datatype" );
		eventDateFlg = ExcelHolder.getKey( map, "EventDate" );
		ratingToFlg = ExcelHolder.getKey( map, "RatingTo" );
		ratingFromFlg = ExcelHolder.getKey( map, "RatingFrom" );
		defaultAggregation = ExcelHolder.getKey( map, "DefaultAggregation" );

	}
	
	private void initializeInputFeedArr() throws Exception{
		inputFieldArr=psStringUtils.stringSplitFirstLevel( inputField );
		displayNameArr=psStringUtils.stringSplitFirstLevel( displayName );
		mandatoryFlgArr=psStringUtils.stringSplitFirstLevel( mandatoryFlg );
		datatypeArr=psStringUtils.stringSplitFirstLevel( datatype );
		eventDateFlgArr=psStringUtils.stringSplitFirstLevel( eventDateFlg );
		ratingToFlgArr=psStringUtils.stringSplitFirstLevel( ratingToFlg );
		ratingFromFlgArr=psStringUtils.stringSplitFirstLevel( ratingFromFlg );
		defaultAggregationArr=psStringUtils.stringSplitFirstLevel( defaultAggregation );
		
		
	}

}
