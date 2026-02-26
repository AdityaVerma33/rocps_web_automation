package com.subex.rocps.automation.helpers.application.admin;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.accruals.accountingperioddefinition.AccountingPeriodDefnDetailImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class Entities extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> entitiesExcel = null;
	protected Map<String, String> entitiesMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String entity;
	protected String allowNotes;
	protected String globallyCached;
	protected String idReset;
	protected String skipExistingID;
	protected String extraArguments;
	protected String extraControl;
	protected String type;
	protected String mandatory;

	protected Map<String, ArrayList<String>> excelReaderMapObj = null;
	protected Map<String, String> mapObj = null;
	String regex = new PSStringUtils().regexFirstLevelDelimeter();
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	@Test
	public Entities( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		entitiesExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( entitiesExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public Entities( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		entitiesExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( entitiesExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the entities
	 * 
	 */
	public void editEntities() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Entities" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				entitiesMap = excelHolderObj.dataMap( paramVal );
				initializeVariables( entitiesMap );
				boolean isEntitiesPresent = genericHelperObj.isGridTextValuePresent( "Entities_Entity", entity, "Entity" );
				if(isEntitiesPresent)
					editEntitiesConfig();
				else
					Log4jHelper.logInfo( "Accounting period definition is already availablewith name :" +entity);			
				
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	public void editEntitiesConfig() throws Exception
	{
		int row = GridHelper.getRowNumber( "SearchGrid", entity, "Entity" );
		NavigationHelper.navigateToEdit( "SearchGrid", row );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		if (ValidationHelper.isTrue(allowNotes) && CheckBoxHelper.isEnabled("Entities_AllowNotes"))
			CheckBoxHelper.check("Entities_AllowNotes");
		
		if (ValidationHelper.isTrue(globallyCached) && CheckBoxHelper.isEnabled("Entities_GloballyCached"))
			CheckBoxHelper.check("Entities_GloballyCached");
		
		if (ValidationHelper.isTrue(idReset)  && CheckBoxHelper.isEnabled("Entities_IDReset"))
			CheckBoxHelper.check("Entities_IDReset");
		
		addExtraArgs();
		ComboBoxHelper.select("Entities_ExtraControl", extraControl);
		
		saveEntities(entity);
	}
	
	public void addExtraArgs() throws Exception {
		try {
			String[] extraArg = extraArguments.split( regex, -1 );
			String[] typeArr = type.split( regex, -1 );
			String[] mandatoryArr = mandatory.split( regex , -1);
			if (ValidationHelper.isNotEmpty(extraArguments)) {
				for (int i = 0; i < extraArg.length; i++) {
					int rowNum = GridHelper.getRowNumber("Entities_ExtraArgument_Grid", extraArg[i], "Name");
					
					if (rowNum == 0) {
						ButtonHelper.click("Entities_ExtraArgument_Add");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						rowNum = GridHelper.getRowCount("Entities_ExtraArgument_Grid");
						
						GridHelper.updateGridTextBox("Entities_ExtraArgument_Grid", "Entities_ExtraArgument_Name", rowNum, "Name", extraArg[i]);
					}
					
					GridHelper.updateGridComboBox("Entities_ExtraArgument_Grid", "Entities_ExtraArgument_Type", rowNum, "Type", typeArr[i]);
					
					GridHelper.updateGridCheckBox("Entities_ExtraArgument_Grid", "Entities_ExtraArgument_Mandatory", rowNum, "Mandatory", mandatoryArr[i]);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	
	
	private void saveEntities(String entityName ) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
			
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", entityName, "Entity"), "Value '" + entityName + "' is not found in grid.");
			Log4jHelper.logInfo("Entity '" + entityName + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void initializeVariables(Map<String, String> map) throws Exception
	{
		entity = ExcelHolder.getKey( map, "Entity" );
		allowNotes = ExcelHolder.getKey( map, "Allow Notes" );
		globallyCached = ExcelHolder.getKey( map, "Globally Cached" );
		idReset = ExcelHolder.getKey( map, "ID Reset" );
		skipExistingID = ExcelHolder.getKey( map, "SkipExistingID" );
		extraArguments = ExcelHolder.getKey( map, "Extra Arguments" );
		extraControl = ExcelHolder.getKey( map, "Extra Control" );
		type = ExcelHolder.getKey( map, "Type" );
		mandatory = ExcelHolder.getKey( map, "Mandatory" );
	}
}
