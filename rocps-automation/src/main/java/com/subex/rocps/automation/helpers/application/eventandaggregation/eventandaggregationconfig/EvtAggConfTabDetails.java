package com.subex.rocps.automation.helpers.application.eventandaggregation.eventandaggregationconfig;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class EvtAggConfTabDetails extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> evtAggConfTabExcelMap = null;
	protected Map<String, String> evtAggConfTabMap = null;
	protected ExcelHolder excelHolderObj = null;

	String path;
	String workBookName;
	String sheetName;
	String testCaseName;
	String bilateralsRequiredFlg;
	String agreementRequiredFlg;
	String normalisationRequiredFlg;
	int colSize;
	int paramVal;
	int index;
	String eventModellingDefnName;
	String eventModellingDefnNameArr[];
	String aggConfig_aggConfigName;
	String aggConfig_aggConfigNameArr[];
	String aggConfig_estimationFlg;
	String aggConfig_estimationFlgArr[];
	String aggConfig_evtTypeName;
	String aggConfig_evtTypeNameArr[];
	String aggConfig_filterComp;
	String aggConfig_filterCompArr[];
	String aggConfig_frequency;
	String aggConfig_frequencyArr[];
	String aggConfig_ratingComp;
	String aggConfig_ratingCompArr[];
	String agrMod_agrModName;
	String agrMod_agrModNameArr[];
	String agrMod_configType;
	String agrMod_configTypeArr[];
	String agrMod_configTypeName;
	String agrMod_configTypeNameArr[];
	String brdMod_aggConfigName;
	String brdMod_aggConfigNameArr[];
	String brdMod_billedUsage;
	String brdMod_billedUsageArr[];
	String brdMod_brdModName;
	String brdMod_brdModNameArr[];
	String evtModInst_consTblPrefix;
	String evtModInst_consTblPrefixArr[];
	String evtModInst_errTblPrefix;
	String evtModInst_errTblPrefixArr[];
	String evtModInst_evtModInstName;
	String evtModInst_evtModInstNameArr[];
	String evtType_evtLegGroup;
	String evtType_evtLegGroupArr[];
	String evtType_evtModInstName;
	String evtType_evtModInstNameArr[];
	String evtType_evtProcComp;
	String evtType_evtProcCompArr[];
	String evtType_evtTypeName;
	String evtType_evtTypeNameArr[];
	String evtType_multSrvcLegFlg;
	String evtType_multSrvcLegFlgArr[];
	String evtType_normRatingFrom;
	String evtType_normRatingFromArr[];
	String evtType_normRatingTo;
	String evtType_normRatingToArr[];
	String evtType_ratingFrom;
	String evtType_ratingFromArr[];
	String evtType_ratingTo;
	String evtType_ratingToArr[];

	PSGenericHelper psGenericHelper = new PSGenericHelper();
	EventAndAggConfigDetails evtAggConfig = new EventAndAggConfigDetails();
	PSStringUtils psStringUtils = new PSStringUtils();

	/**
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @param bilateralsRequiredFlg
	 * @param agreementRequiredFlg
	 */
	public EvtAggConfTabDetails( String path, String workBookName, String sheetName, String testCaseName, String bilateralsRequiredFlg, String agreementRequiredFlg, String normalisationRequiredFlg ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.bilateralsRequiredFlg = bilateralsRequiredFlg;
		this.agreementRequiredFlg = agreementRequiredFlg;
		this.normalisationRequiredFlg = normalisationRequiredFlg;
		excelData = new ExcelReader();
		evtAggConfTabExcelMap = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( evtAggConfTabExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	public void configureConfTabDetails() throws Exception
	{
		evtAggConfTabMap = excelHolderObj.dataMap( index );
		initializeVariable( evtAggConfTabMap );
		initializeConfTabGridArray();
		configureEventModellingDefn();
		configureEventModellingInstance();
		configureEventType();
		configureAggregationConfig();
		if ( ValidationHelper.isTrue( bilateralsRequiredFlg ) )
			configureBilateralModelling();
		if ( ValidationHelper.isTrue( agreementRequiredFlg ) )
			configureAgreementModelling();

	}

	public void configureEventModellingDefn() throws Exception
	{
		evtAggConfig.openPanel( "Event Modelling Definition" );
		TextBoxHelper.isValuePresent( "PSDetail_Event_Aggregation_EventModellingDefn_Text_ID", eventModellingDefnName );
	}

	public void configureEventModellingInstance() throws Exception
	{
		evtAggConfig.openPanel( "Event Modelling Instance" );
		String evtModInstGridId="PSDetail_Event_Aggregation_EventModellingInstGrid_ID";
		int rowNum=0;
		for(int i=0;i<evtModInst_evtModInstNameArr.length;i++)
		{
			rowNum=i+1;
			if(rowNum>1)
				ButtonHelper.click( "PSDetail_Event_Aggregation_EventModellingInstGrid_Add_Button_ID" );
			GridHelper.updateGridTextBox( evtModInstGridId, "PSDetail_Event_Aggregation_EventModellingInstName_Text_ID", rowNum, "Event Modelling Instance Name", evtModInst_evtModInstNameArr[i] );
			GridHelper.updateGridTextBox( evtModInstGridId, "PSDetail_Event_Aggregation_EventModellingInstConsTblPrefix_Text_ID", rowNum, "Consolidated Table Prefix", evtModInst_consTblPrefixArr[i] );
			GridHelper.updateGridTextBox( evtModInstGridId, "PSDetail_Event_Aggregation_EventModellingInstErrorTblPrefix_Text_ID", rowNum, "Error Table Prefix", evtModInst_errTblPrefixArr[i] );
			
		}
	}

	public void configureEventType() throws Exception
	{
		evtAggConfig.openPanel( "Event Type" );
		String evtTypeGridId="PSDetail_Event_Aggregation_EventTypeGrid_ID";
		int rowNum=0;
		for(int i=0;i<evtType_evtTypeNameArr.length;i++)
		{
			rowNum=i+1;
			if(rowNum>1)
				ButtonHelper.click( "PSDetail_Event_Aggregation_EventTypeGrid_Add_Button_ID" );
			GridHelper.updateGridTextBox( evtTypeGridId, "PSDetail_Event_Aggregation_EventTypeName_Text_ID", rowNum, "Event Type Name", evtType_evtTypeNameArr[i] );
			GridHelper.updateGridComboBox( evtTypeGridId, "PSDetail_Event_Aggregation_EventType_EvtInst_Combo_ID", rowNum, "Event Modelling Instance Name", evtType_evtModInstNameArr[i] );
			GridHelper.updateGridComboBox( evtTypeGridId, "PSDetail_Event_Aggregation_EventType_EvtProcComp_Combo_ID", rowNum, "Event Processor Component(s)", evtType_evtProcCompArr[i] );
			GridHelper.updateGridCheckBox( evtTypeGridId, "PSDetail_Event_Aggregation_EventType_MultServLegs_Check_ID", rowNum, "Multiple service per leg", evtType_multSrvcLegFlgArr[i] );
			if(ValidationHelper.isFalse( normalisationRequiredFlg ))
			{
				PSDataComponentHelper.updateGridComboBox( evtTypeGridId, "PSDetail_Event_Aggregation_EventType_RatingFrom_Combo_ID", rowNum, "Rating  From", evtType_ratingFromArr[i] );
				PSDataComponentHelper.updateGridComboBox( evtTypeGridId, "PSDetail_Event_Aggregation_EventType_RatingTo_Combo_ID", rowNum, "Rating  To", evtType_ratingToArr[i] );
			}
			else
			{
				PSDataComponentHelper.updateGridComboBox( evtTypeGridId, "PSDetail_Event_Aggregation_EventType_RatingFromNorm_Combo_ID", rowNum, "Rating  From( Normalisation)", evtType_normRatingFromArr[i] );
				PSDataComponentHelper.updateGridComboBox( evtTypeGridId, "PSDetail_Event_Aggregation_EventType_RatingToNorm_Combo_ID", rowNum, "Rating  To( Normalisation)", evtType_normRatingToArr[i] );
			}
			PSDataComponentHelper.updateGridComboBox( evtTypeGridId, "PSDetail_Event_Aggregation_EventType_EvtLegGroup_Combo_ID", rowNum, "Event  Leg  Group", evtType_evtLegGroupArr[i] );	
		}

	}

	public void configureAggregationConfig() throws Exception
	{
		evtAggConfig.openPanel( "Aggregation Configuration" );
		String aggConfigGridId="PSDetail_Event_Aggregation_AggregationGrid_Grid_ID";
		int rowNum=0;
		for(int i=0;i<aggConfig_aggConfigNameArr.length;i++)
		{
			rowNum=i+1;
			if(rowNum>1)
				ButtonHelper.click( "PSDetail_Event_Aggregation_AggregationGrid_Add_Button_ID" );
			GridHelper.updateGridTextBox( aggConfigGridId, "PSDetail_Event_Aggregation_AggregationConfigName_Text_ID", rowNum,"Aggregation Configuration Name" , aggConfig_aggConfigNameArr[i] );
			GridHelper.updateGridComboBox( aggConfigGridId, "PSDetail_Event_Aggregation_AggregationConfig_EvtType_Combo_ID", rowNum, "Event Type Name", aggConfig_evtTypeNameArr[i] );
			GridHelper.updateGridComboBox( aggConfigGridId, "PSDetail_Event_Aggregation_AggregationConfig_RatingComp_Combo_ID", rowNum, "Rating Component", aggConfig_ratingCompArr[i] );
			GridHelper.updateGridComboBox( aggConfigGridId, "PSDetail_Event_Aggregation_AggregationConfig_Frequency_Combo_ID", rowNum, "Frequency", aggConfig_frequencyArr[i] );
			GridHelper.updateGridComboBox( aggConfigGridId, "PSDetail_Event_Aggregation_AggregationConfig_FilterComp_Combo_ID", rowNum, "Filter Component", aggConfig_filterCompArr[i] );
			GridHelper.updateGridCheckBox( aggConfigGridId, "PSDetail_Event_Aggregation_AggregationConfig_Estimation_Check_ID", rowNum, "Estimation  Required", aggConfig_estimationFlgArr[i] );
			saveAggregationFields( rowNum );
		}
	}

	public void configureBilateralModelling() throws Exception
	{
		evtAggConfig.openPanel( "Bilateral Modelling" );
		String brdConfigGridId="PSDetail_Event_Aggregation_BRDGrid_Grid_ID";
		int rowNum=0;
		for(int i=0;i<brdMod_brdModNameArr.length;i++)
		{
			rowNum=i+1;
			if(rowNum>1)
				ButtonHelper.click( "PSDetail_Event_Aggregation_BRDGrid_Add_Button_ID" );
			GridHelper.updateGridTextBox( brdConfigGridId, "PSDetail_Event_Aggregation_BRDName_Text_ID", rowNum, "BRD Modelling Name", brdMod_brdModNameArr[i] );
			GridHelper.updateGridComboBox( brdConfigGridId, "PSDetail_Event_Aggregation_BRDGrid_AggConfig_Combo_ID", rowNum, "Aggregation Configuration", brdMod_aggConfigNameArr[i] );
			GridHelper.updateGridComboBox( brdConfigGridId, "PSDetail_Event_Aggregation_BRDGrid_BilledUsage_Combo_ID", rowNum, "Billed Usage", brdMod_billedUsageArr[i] );
		}
	}

	public void configureAgreementModelling() throws Exception
	{
		evtAggConfig.openPanel( "Agreement Modelling" );
		String agmConfigGridId="PSDetail_Event_Aggregation_AgreementGrid_Grid_ID";
		int rowNum=0;
		for(int i=0;i<agrMod_agrModNameArr.length;i++)
		{
			rowNum=i+1;
			if(rowNum>1)
				ButtonHelper.click( "PSDetail_Event_Aggregation_AgreementGrid_Add_Button_ID" );
			GridHelper.updateGridTextBox( agmConfigGridId, "PSDetail_Event_Aggregation_AgreementName_Text_ID", rowNum, "Agreement Modelling Name", agrMod_agrModNameArr[i] );
			GridHelper.updateGridComboBox( agmConfigGridId, "PSDetail_Event_Aggregation_AgreementConfigType_Combo_ID", rowNum, "Type(Agg/BRD)", agrMod_configTypeArr[i] );
			GridHelper.updateGridComboBox( agmConfigGridId, "PSDetail_Event_Aggregation_AgreementConfigTypeName_Combo_ID", rowNum, "Aggregation/BRD", agrMod_configTypeNameArr[i] );
		}
	}

	private void saveAggregationFields(int rowNum) throws Exception
	{
		String aggConfigGridId="PSDetail_Event_Aggregation_AggregationGrid_Grid_ID";
		GridHelper.clickRow( aggConfigGridId, rowNum, "Aggregation Configuration Name" );
		ButtonHelper.click( "PSDetail_Event_Aggregation_AggregationGrid_ConfigureAggFields_Button_ID" );
		psGenericHelper.waitforPopupHeaderElement( "PSDetail_Event_Aggregation_AggGrid_ColumnInfo_PopupWindow_ID", "PSDetail_Event_Aggregation_AggGrid_ColumnInfo_PopupGrid_ID", "Display  Name" );
		ButtonHelper.click( "PSDetail_Event_Aggregation_AggregationGrid_ConfigureAggFields_Save_Button_ID" );
		psGenericHelper.waitforPopupHeaderElementToDisappear( "PSDetail_Event_Aggregation_AggGrid_ColumnInfo_PopupWindow_ID", "PSDetail_Event_Aggregation_AggGrid_ColumnInfo_PopupGrid_ID", "Display  Name" );
		
	}
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		eventModellingDefnName = ExcelHolder.getKey( map, "eventModellingDefnName" );

		evtModInst_evtModInstName = ExcelHolder.getKey( map, "evtModInst_evtModInstName" );
		evtModInst_consTblPrefix = ExcelHolder.getKey( map, "evtModInst_consTblPrefix" );
		evtModInst_errTblPrefix = ExcelHolder.getKey( map, "evtModInst_errTblPrefix" );

		evtType_evtTypeName = ExcelHolder.getKey( map, "evtType_evtTypeName" );
		evtType_evtModInstName = ExcelHolder.getKey( map, "evtType_evtModInstName" );
		evtType_evtProcComp = ExcelHolder.getKey( map, "evtType_evtProcComp" );
		evtType_multSrvcLegFlg = ExcelHolder.getKey( map, "evtType_multSrvcLegFlg" );
		evtType_ratingFrom = ExcelHolder.getKey( map, "evtType_ratingFrom" );
		evtType_ratingTo = ExcelHolder.getKey( map, "evtType_ratingTo" );
		evtType_normRatingFrom = ExcelHolder.getKey( map, "evtType_normRatingFrom" );
		evtType_normRatingTo = ExcelHolder.getKey( map, "evtType_normRatingTo" );
		evtType_evtLegGroup = ExcelHolder.getKey( map, "evtType_evtLegGroup" );

		aggConfig_aggConfigName = ExcelHolder.getKey( map, "aggConfig_aggConfigName" );
		aggConfig_evtTypeName = ExcelHolder.getKey( map, "aggConfig_evtTypeName" );
		aggConfig_ratingComp = ExcelHolder.getKey( map, "aggConfig_ratingComp" );
		aggConfig_frequency = ExcelHolder.getKey( map, "aggConfig_frequency" );
		aggConfig_filterComp = ExcelHolder.getKey( map, "aggConfig_filterComp" );
		aggConfig_estimationFlg = ExcelHolder.getKey( map, "aggConfig_estimationFlg" );

		brdMod_brdModName = ExcelHolder.getKey( map, "brdMod_brdModName" );
		brdMod_aggConfigName = ExcelHolder.getKey( map, "brdMod_aggConfigName" );
		brdMod_billedUsage = ExcelHolder.getKey( map, "brdMod_billedUsage" );

		agrMod_agrModName = ExcelHolder.getKey( map, "agrMod_agrModName" );
		agrMod_configType = ExcelHolder.getKey( map, "agrMod_configType" );
		agrMod_configTypeName = ExcelHolder.getKey( map, "agrMod_configTypeName" );

	}

	private void initializeConfTabGridArray() throws Exception
	{

		evtModInst_evtModInstNameArr = psStringUtils.stringSplitFirstLevel( evtModInst_evtModInstName );
		evtModInst_consTblPrefixArr = psStringUtils.stringSplitFirstLevel( evtModInst_consTblPrefix );
		evtModInst_errTblPrefixArr = psStringUtils.stringSplitFirstLevel( evtModInst_errTblPrefix );

		evtType_evtTypeNameArr = psStringUtils.stringSplitFirstLevel( evtType_evtTypeName );
		evtType_evtModInstNameArr = psStringUtils.stringSplitFirstLevel( evtType_evtModInstName );
		evtType_evtProcCompArr = psStringUtils.stringSplitFirstLevel( evtType_evtProcComp );
		evtType_multSrvcLegFlgArr = psStringUtils.stringSplitFirstLevel( evtType_multSrvcLegFlg );
		evtType_ratingFromArr = psStringUtils.stringSplitFirstLevel( evtType_ratingFrom );
		evtType_ratingToArr = psStringUtils.stringSplitFirstLevel( evtType_ratingTo );
		evtType_normRatingFromArr = psStringUtils.stringSplitFirstLevel( evtType_normRatingFrom );
		evtType_normRatingToArr = psStringUtils.stringSplitFirstLevel( evtType_normRatingTo );
		evtType_evtLegGroupArr = psStringUtils.stringSplitFirstLevel( evtType_evtLegGroup );

		aggConfig_aggConfigNameArr = psStringUtils.stringSplitFirstLevel( aggConfig_aggConfigName );
		aggConfig_evtTypeNameArr = psStringUtils.stringSplitFirstLevel( aggConfig_evtTypeName );
		aggConfig_ratingCompArr = psStringUtils.stringSplitFirstLevel( aggConfig_ratingComp );
		aggConfig_frequencyArr = psStringUtils.stringSplitFirstLevel( aggConfig_frequency );
		aggConfig_filterCompArr = psStringUtils.stringSplitFirstLevel( aggConfig_filterComp );
		aggConfig_estimationFlgArr = psStringUtils.stringSplitFirstLevel( aggConfig_estimationFlg );

		brdMod_brdModNameArr = psStringUtils.stringSplitFirstLevel( brdMod_brdModName );
		brdMod_aggConfigNameArr = psStringUtils.stringSplitFirstLevel( brdMod_aggConfigName );
		brdMod_billedUsageArr = psStringUtils.stringSplitFirstLevel( brdMod_billedUsage );

		agrMod_agrModNameArr = psStringUtils.stringSplitFirstLevel( agrMod_agrModName );
		agrMod_configTypeArr = psStringUtils.stringSplitFirstLevel( agrMod_configType );
		agrMod_configTypeNameArr = psStringUtils.stringSplitFirstLevel( agrMod_configTypeName );

	}

}
