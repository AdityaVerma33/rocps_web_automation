package com.subex.rocps.automation.helpers.application.accruals.estimationprocessor;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.tigervnc.rdr.OutStream;

public class EstimationProcessorDetailImpl extends PSAcceptanceTest
{
	protected String name;
	protected String partition;
	protected String component;
	protected String inputTable;
	protected String outputTable;
	protected String lookBackDays;
	protected String outputGrain;
	protected String dealSimulationFlag;
	protected String column;
	protected String estimate;
	protected String pivotDate;
	protected String[] columnArr;
	protected String[] estimateArr;
	protected String[] pivotDaysArr;

	protected Map<String, String> map;
	PSStringUtils strObj = new PSStringUtils();
	PSGenericHelper genericObj = new PSGenericHelper();

	public EstimationProcessorDetailImpl( Map<String, String> map ) throws Exception
	{
		this.map = map;
		initialiseVaribles( map );

	}

	public void newEstimationProcessor() throws Exception
	{
		genericObj.clickNewAction( partition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		//assertTrue( ElementHelper.isElementPresent( "//div[text()='Accounting  Period  Frequency']" ), "Detail Page is not loaded" );
	}
	
	public void basicDetails() throws Exception
	{
		TextBoxHelper.type( "pespName", name );
		ComboBoxHelper.select( "component_gwt_uid_", component );
		tableInstanceSelection("inputTableInst", inputTable );
		
		TextBoxHelper.type( "pespLookbackDays", lookBackDays );
		ComboBoxHelper.select( "pespEstimationGrain_gwt_uid_", outputGrain );
		if(ValidationHelper.isTrue( dealSimulationFlag ))
			CheckBoxHelper.check( "pespSimulationFl_InputElement" );
		else
			tableInstanceSelection("outputTableInst", outputTable );
	}
	
	
	private void tableInstanceSelection(String iconId, String value) throws Exception
	{
		EntityComboHelper.clickEntityIcon( iconId );	
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		genericObj.waitforPopupHeaderElement( "Display Name" );
		SearchGridHelper.gridFilterSearchWithTextBox( "popupWindow", "PS_Detail_accrualMod_tableInstance_textID", value, "Display Name" );
		GridHelper.clickRow( "SearchGrid", value, "Display Name" );
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );

	}
	
	
	public void columnPropertiesGrid() throws Exception
	{
		columnArr = strObj.stringSplitFirstLevel( column );
		estimateArr = strObj.stringSplitFirstLevel( estimate );
		pivotDaysArr = strObj.stringSplitFirstLevel( pivotDate );
		
		for (int i=0;i<columnArr.length;i++)
		{
		int row = GridHelper.getRowNumber( "tableColumnGrid", columnArr[i], "Column" );
		if(ValidationHelper.isNotEmpty( estimateArr[i] ) && ValidationHelper.isTrue( estimateArr[i] ) && !isCheckBoxChecked( row, 2 ))
			GridHelper.updateGridCheckBox( "tableColumnGrid", row, "Estimate", estimateArr[i] );
		if(ValidationHelper.isNotEmpty( pivotDaysArr[i] ) && ValidationHelper.isTrue( pivotDaysArr[i] ) && !isCheckBoxChecked( row, 3 ))
			GridHelper.updateGridCheckBox( "tableColumnGrid", row, "Pivot Date", pivotDaysArr[i] );		
		
		}
	}
	
	public void editBasicDetails() throws Exception
	{
		TextBoxHelper.type( "pespName", name );
		if(ValidationHelper.isNotEmpty( component ))
			ComboBoxHelper.select( "component_gwt_uid_", component );
		if(ValidationHelper.isNotEmpty( inputTable ))
			tableInstanceSelection("inputTableInst", inputTable );
		
		if(ValidationHelper.isNotEmpty( lookBackDays ))
			TextBoxHelper.type( "pespLookbackDays", lookBackDays );
		if(ValidationHelper.isNotEmpty( outputGrain ))
			ComboBoxHelper.select( "pespEstimationGrain_gwt_uid_", outputGrain );
		if(ValidationHelper.isTrue( dealSimulationFlag ))
			CheckBoxHelper.check( "pespSimulationFl_InputElement" );
		else  if(ValidationHelper.isNotEmpty( outputTable ))
			tableInstanceSelection("outputTableInst", outputTable );
	}
	
	private boolean isCheckBoxChecked( int row, int col ) throws Exception
	{
		String xpath = GenericHelper.getORProperty( "//*[@id='tableColumnGrid']//table/tbody//tr[" + row + "]//td[" + col + "]//div[@id='grid_check_editor']/img" );
		String elementTxt = ElementHelper.getAttribute( xpath, "src" );
		if ( elementTxt.contains( "unchecked" ) )
			return false;
		else
			return true;
	}
	
	public void saveEstimationProcesor() throws Exception
	{
		genericObj.detailSave( "estimationProcessorDetail.save", name, "Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		Log4jHelper.logInfo( "Estimation Processor is saved successfully :" + name );
		
	}
	public void initialiseVaribles(Map<String, String> map) throws Exception
	{
		name = ExcelHolder.getKey( map, "Name" );
		partition = ExcelHolder.getKey( map, "Partition" );
		component =  ExcelHolder.getKey( map, "Component" );
		inputTable = ExcelHolder.getKey( map, "InputTable" );
		outputTable = ExcelHolder.getKey( map, "OutputTable" );
		lookBackDays =  ExcelHolder.getKey( map, "LookBackDays" );
		outputGrain = ExcelHolder.getKey( map, "OutputGrain" );
		dealSimulationFlag = ExcelHolder.getKey( map, "DealSimulationFlag" );
		column =  ExcelHolder.getKey( map, "Column" );
		estimate = ExcelHolder.getKey( map, "Estimate" );
		pivotDate = ExcelHolder.getKey( map, "PivotDate" );
	}
}
