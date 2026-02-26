package com.subex.rocps.automation.helpers.application.eventandaggregation.eventandaggregationconfig;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class EvtAggNormTabDetails extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> evtAggNormTabExcelMap = null;
	protected Map<String, String> evtAggNormTabMap = null;
	protected ExcelHolder excelHolderObj = null;

	String path;
	String workBookName;
	String sheetName;
	String testCaseName;
	String normalisationFlg;
	int colSize;
	int paramVal;
	int index;
	String norminputField;
	String norminputFieldArr[];
	String normdisplayName;
	String normdisplayNameArr[];
	String normmandatoryFlg;
	String normmandatoryFlgArr[];
	String normdatatype;
	String normdatatypeArr[];
	String normeventDateFlg;
	String normeventDateFlgArr[];
	String normratingToFlg;
	String normratingToFlgArr[];
	String normratingFromFlg;
	String normratingFromFlgArr[];
	String normdefaultAggregation;
	String normdefaultAggregationArr[];
	String revinputField;
	String revinputFieldArr[];
	String revdisplayName;
	String revdisplayNameArr[];
	String revmandatoryFlg;
	String revmandatoryFlgArr[];
	String revdatatype;
	String revdatatypeArr[];
	String revdefaultAggregation;
	String revdefaultAggregationArr[];

	PSGenericHelper psGenericHelper = new PSGenericHelper();
	EventAndAggConfigDetails evtAggConfig = new EventAndAggConfigDetails();
	PSStringUtils psStringUtils = new PSStringUtils();

	/**
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @param normalisationFlg
	 * @throws Exception 
	 */
	public EvtAggNormTabDetails( String path, String workBookName, String sheetName, String testCaseName, String normalisationFlg ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.normalisationFlg = normalisationFlg;
		excelData = new ExcelReader();
		evtAggNormTabExcelMap = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( evtAggNormTabExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	public void configureNormTabDetails() throws Exception
	{

		evtAggNormTabMap = excelHolderObj.dataMap( index );
		initializeVariable( evtAggNormTabMap );
		initializeNormTabGridArray();

		if ( ValidationHelper.isTrue( normalisationFlg ) )
			configNormTabGrid();

		
		initializeRevGridArray();
		configRatedEvtGrid();

	}

	public void configNormTabGrid() throws Exception
	{
		String normGridId = "PSDetail_Event_Aggregation_NormGrid_Grid_ID";
		int rowNum = 0;
		for ( int i = 0; i < norminputFieldArr.length; i++ )
		{
			rowNum = i + 1;
			ButtonHelper.click( "PSDetail_Event_Aggregation_NormGrid_Add_Button_ID" );
			GridHelper.updateGridTextBox( normGridId, "PSDetail_Event_Aggregation_NormGrid_FieldName_Text_ID", rowNum, "Input Field", norminputFieldArr[i] );
			GridHelper.updateGridTextBox( normGridId, "PSDetail_Event_Aggregation_NormGrid_DisplayName_Text_ID", rowNum, "Display Name", normdisplayNameArr[i] );

			if ( ValidationHelper.isNotEmpty( normmandatoryFlg ) && ValidationHelper.isTrue( normmandatoryFlgArr[i] ) )
				GridHelper.updateGridCheckBox( normGridId, "PSDetail_Event_Aggregation_NormGrid_Mandatory_Check_ID", rowNum, "Mandatory", normmandatoryFlgArr[i] );

			GridHelper.updateGridComboBox( normGridId, "PSDetail_Event_Aggregation_NormGrid_Datatype_Combo_ID", rowNum, "Data Type", normdatatypeArr[i] );

			if ( ValidationHelper.isNotEmpty( normeventDateFlg )&& ValidationHelper.isTrue( normeventDateFlgArr[i] ) )
				GridHelper.updateGridCheckBox( normGridId, "PSDetail_Event_Aggregation_NormGrid_EventDate_Check_ID", rowNum, "Event Date Field", normeventDateFlgArr[i] );
			if ( ValidationHelper.isNotEmpty( normratingFromFlg )&& ValidationHelper.isTrue( normratingFromFlgArr[i] ) )
				GridHelper.updateGridCheckBox( normGridId, "PSDetail_Event_Aggregation_NormGrid_RatingFrom_Check_ID", rowNum, "Rating From", normratingFromFlgArr[i] );
			if ( ValidationHelper.isNotEmpty( normratingToFlg )&& ValidationHelper.isTrue( normratingToFlgArr[i] ) )
				GridHelper.updateGridCheckBox( normGridId, "PSDetail_Event_Aggregation_NormGrid_RatingTo_Check_ID", rowNum, "Rating To", normratingToFlgArr[i] );

			GridHelper.updateGridComboBox( normGridId, "PSDetail_Event_Aggregation_NormGrid_DefaultAggregation_Combo_ID", rowNum, "Default Aggregation", normdefaultAggregationArr[i] );

		}
	}

	public void configRatedEvtGrid() throws Exception
	{
		evtAggConfig.openPanel( "Rated Event" );
		String revGridId = "PSDetail_Event_Aggregation_RevGrid_Grid_ID";
		int rowNum = 0;
		for ( int i = 0; i < revinputFieldArr.length; i++ )
		{
			rowNum=i+1;
			ButtonHelper.click( "PSDetail_Event_Aggregation_RevGrid_Add_Button_ID" );
			GridHelper.updateGridTextBox( revGridId, "PSDetail_Event_Aggregation_RevGrid_FieldName_Text_ID", rowNum, "Input Field", revinputFieldArr[i] );
			GridHelper.updateGridTextBox( revGridId, "PSDetail_Event_Aggregation_RevGrid_DisplayName_Text_ID", rowNum, "Display Name", revdisplayNameArr[i] );

			if ( ValidationHelper.isNotEmpty( revmandatoryFlg ) && ValidationHelper.isTrue( revmandatoryFlgArr[i] ) )
				GridHelper.updateGridCheckBox( revGridId, "PSDetail_Event_Aggregation_RevGrid_Mandatory_Check_ID", rowNum, "Mandatory", revmandatoryFlgArr[i] );

			GridHelper.updateGridComboBox( revGridId, "PSDetail_Event_Aggregation_RevGrid_Datatype_Combo_ID", rowNum, "Data Type", revdatatypeArr[i] );

			GridHelper.updateGridComboBox( revGridId, "PSDetail_Event_Aggregation_RevGrid_DefaultAggregation_Combo_ID", rowNum, "Default Aggregation", revdefaultAggregationArr[i] );
		}
	}

	private void initializeVariable( Map<String, String> map ) throws Exception
	{

		norminputField = ExcelHolder.getKey( map, "Norm_FieldName" );
		normdisplayName = ExcelHolder.getKey( map, "Norm_DisplayName" );
		normmandatoryFlg = ExcelHolder.getKey( map, "Norm_MandatoryFlg" );
		normdatatype = ExcelHolder.getKey( map, "Norm_Datatype" );
		normeventDateFlg = ExcelHolder.getKey( map, "Norm_EventDateFlg" );
		normratingToFlg = ExcelHolder.getKey( map, "Norm_RatingToFlg" );
		normratingFromFlg = ExcelHolder.getKey( map, "Norm_RatingFromFlg" );
		normdefaultAggregation = ExcelHolder.getKey( map, "Norm_DefaultAggregation" );
		revinputField = ExcelHolder.getKey( map, "Rev_FieldName" );
		revdisplayName = ExcelHolder.getKey( map, "Rev_DisplayName" );
		revmandatoryFlg = ExcelHolder.getKey( map, "Rev_MandatoryFlg" );
		revdatatype = ExcelHolder.getKey( map, "Rev_Datatype" );
		revdefaultAggregation = ExcelHolder.getKey( map, "Rev_DefaultAggregation" );

	}

	private void initializeNormTabGridArray() throws Exception
	{

		norminputFieldArr = psStringUtils.stringSplitFirstLevel( norminputField );
		normdisplayNameArr = psStringUtils.stringSplitFirstLevel( normdisplayName );
		normmandatoryFlgArr = psStringUtils.stringSplitFirstLevel( normmandatoryFlg );
		normdatatypeArr = psStringUtils.stringSplitFirstLevel( normdatatype );
		normeventDateFlgArr = psStringUtils.stringSplitFirstLevel( normeventDateFlg );
		normratingToFlgArr = psStringUtils.stringSplitFirstLevel( normratingToFlg );
		normratingFromFlgArr = psStringUtils.stringSplitFirstLevel( normratingFromFlg );
		normdefaultAggregationArr = psStringUtils.stringSplitFirstLevel( normdefaultAggregation );
		

	}
	private void initializeRevGridArray() throws Exception
	{
		revinputFieldArr = psStringUtils.stringSplitFirstLevel( revinputField );
		revdisplayNameArr = psStringUtils.stringSplitFirstLevel( revdisplayName );
		revmandatoryFlgArr = psStringUtils.stringSplitFirstLevel( revmandatoryFlg );
		revdatatypeArr = psStringUtils.stringSplitFirstLevel( revdatatype );
		revdefaultAggregationArr = psStringUtils.stringSplitFirstLevel( revdefaultAggregation );
	}

}
