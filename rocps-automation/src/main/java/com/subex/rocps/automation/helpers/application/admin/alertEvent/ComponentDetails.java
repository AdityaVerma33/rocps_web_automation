package com.subex.rocps.automation.helpers.application.admin.alertEvent;

import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.utils.ExcelHolder;

public class ComponentDetails {
	PSGenericHelper genObj = new PSGenericHelper();
	EmailConfigurationImpl emailObj = new EmailConfigurationImpl();
	AlertTextImpl alertTxtObj = new AlertTextImpl();
	AlertEvtOtherDetActImpl actionObj = new AlertEvtOtherDetActImpl();
	TimingDeviationComponent timeObj = new TimingDeviationComponent();
	TrendComparisonComponent trendObj = new TrendComparisonComponent();
	protected Map<String, String> map;
	String	emailConfiguration;
	String	emailColumn;
	String	emailJoiningTable;
	String	emailJoiningColumn;
	String	emailDisplayColumn;
	String	emailColumnHeader;
	String	emailSubject;
	String	emailHeader;
	String	emailFooter;
	String	alertJoiningTable;
	String	alertParameter;
	String	alertColumn;
	String	alertJoiningColumn;
	String	alertDisplayColumn;
	String	alertColumnHeader;
	String	actions;
	String	submitSqlValue;
	String	runProgramPath;
	String	runValue;
	String	component;
	String timeComponent;
	String resultantEntity;
	String foreignKey;
	String timingDateTimeColumn;
	String graceDays;
	String isExpectedDateRequired;
	String include;
	String valuesToSelect;
	String tableInstance;
	String trendDateTimeColumn;
	String  comparisonValue;
	String comparisonKey;
	String entity;
	String currentFunction;
	String currentLookBack;
	String currentSpan;
	String currentFrequency;
	String lastFunction;
	String lastLookBack;
	String lastSpan;
	String lastFrequency;
	String operator1;
	String value1;
	String type1;
	String operator2;
	String value2;
	String type2;
	String fieldsToSelect;
	String clause;
	String leftIndent;
	String tableColumn;
	String operator;
	String value;
	String rightIndent;


	
	
	public ComponentDetails(Map<String,String> map) throws Exception{
		this.map = map;
		initializeVariables( map );
	}
	

	
	public void switchComponentDetailsTab() throws Exception{
		ElementHelper.click("//div[@id='topPanelContainer']//div[text()='Component Details']");
		GenericHelper.waitForLoadmask();
	}
	
	public void completeComponentDetails() throws Exception{
		if(ValidationHelper.isTrue(timeComponent)){
			ComboBoxHelper.select("component_gwt_uid_","Timing Deviation Component");
			GenericHelper.waitForLoadmask();
			timeObj.timeDeviationComponent( resultantEntity, foreignKey, timingDateTimeColumn, graceDays, isExpectedDateRequired, include );
			timeObj.selectValues( valuesToSelect );
		}
		
		else{
			ComboBoxHelper.select("component_gwt_uid_","Trend Comparison Component");
			GenericHelper.waitForLoadmask();
			trendObj.detailsPanelHeader( tableInstance, trendDateTimeColumn, comparisonValue, comparisonKey, entity );
			trendObj.trendComparisonConfiguration( currentFunction, currentLookBack, currentSpan, currentFrequency, lastFunction, lastLookBack, lastSpan, lastFrequency );
			trendObj.thresholdConditionValue( operator1, value1, type1, operator2, value2, type2 );
			trendObj.switchToGroupByScreen();
			trendObj.groupByDetails( fieldsToSelect );
			trendObj.switchToGroupByScreen();
			trendObj.additionalFilters( clause, leftIndent, tableColumn, operator, rightIndent );
		}
	}
	

	public void initializeVariables(Map<String,String> detMap) throws Exception{
		timeComponent=ExcelHolder.getKey(detMap, "TimeComponent");
		resultantEntity=ExcelHolder.getKey(detMap, "ResultantEntity");
		foreignKey=ExcelHolder.getKey(detMap, "ForeignKey");
		timingDateTimeColumn=ExcelHolder.getKey(detMap, "TimingDateTimeColumn");
		graceDays=ExcelHolder.getKey(detMap, "GraceDays");
		isExpectedDateRequired=ExcelHolder.getKey(detMap, "IsExpectedDateRequired");
		include=ExcelHolder.getKey(detMap, "Include");
		valuesToSelect=ExcelHolder.getKey(detMap, "ValuesToSelect");
		tableInstance=ExcelHolder.getKey(detMap, "TableInstance");
		trendDateTimeColumn=ExcelHolder.getKey(detMap, "TrendDateTimeColumn");
		comparisonValue=ExcelHolder.getKey(detMap, "ComparisonValue");
		comparisonKey=ExcelHolder.getKey(detMap, "ComparisonKey");
		entity=ExcelHolder.getKey(detMap, "Entity");
		currentFunction=ExcelHolder.getKey(detMap, "CurrentFunction");
		currentLookBack=ExcelHolder.getKey(detMap, "CurrentLookBackPeriod");
		currentSpan=ExcelHolder.getKey(detMap, "CurrentSpan");
		currentFrequency=ExcelHolder.getKey(detMap, "CurrentFrequency");
		lastFunction=ExcelHolder.getKey(detMap, "LastFunction");
		lastLookBack=ExcelHolder.getKey(detMap, "LastLookBackPeriod");
		lastSpan=ExcelHolder.getKey(detMap, "LastSpan");
		lastFrequency=ExcelHolder.getKey(detMap, "LastFrequency");
		operator1=ExcelHolder.getKey(detMap, "Operator1");
		value1=ExcelHolder.getKey(detMap, "Value1");
		type1=ExcelHolder.getKey(detMap, "Type1");
		operator2=ExcelHolder.getKey(detMap, "Operator2");
		value2=ExcelHolder.getKey(detMap, "Value2");
		type2=ExcelHolder.getKey(detMap, "Type2");
		fieldsToSelect=ExcelHolder.getKey(detMap, "FieldsToSelect");
		clause=ExcelHolder.getKey(detMap, "Clause");
		leftIndent=ExcelHolder.getKey(detMap, "LeftIndent");
		tableColumn=ExcelHolder.getKey(detMap, "TableColumn");
		operator=ExcelHolder.getKey(detMap, "Operator");
		rightIndent=ExcelHolder.getKey(detMap, "RightIndent");
		

	}
	
	
}