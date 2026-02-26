package com.subex.rocps.automation.helpers.application.accruals.glCodeInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.EnitityValuesSelectionHelper;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class GLCodeInstanceDetail extends PSAcceptanceTest
{
	protected Map<String, String> glCodeInstDetailsMap = null;
	protected String glCodeDefnNm;
	protected String glCode;
	protected String fromDate;
	protected String toDate;
	protected String entity;
	protected String entityArr[];
	protected String entityValue;
	protected String entityValueArr[];
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	EnitityValuesSelectionHelper enitityValuesSelectionOb = new EnitityValuesSelectionHelper();

	/**Constructor
	 * @param glCodeInstDetailsMap
	 */
	public GLCodeInstanceDetail( Map<String, String> glCodeInstDetailsMap )
	{

		this.glCodeInstDetailsMap = glCodeInstDetailsMap;
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeBasicVariable( Map<String, String> map ) throws Exception
	{
		glCodeDefnNm = ExcelHolder.getKey( map, "GlCdDefnName" );
		glCode = ExcelHolder.getKey( map, "GLCode" );
		fromDate = ExcelHolder.getKey( map, "FromDate" );
		toDate = ExcelHolder.getKey( map, "ToDate" );

	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeEntityVariable( Map<String, String> map ) throws Exception
	{
		entity = ExcelHolder.getKey( map, "Entity" );
		entityValue = ExcelHolder.getKey( map, "EntityValue" );
	}

	/*
	 * This method is for create Gl Code Instance
	 */
	public void createGlCodeInstance() throws Exception
	{
		configBasicDetails();
		configEntity();
		psGenericHelper.detailSave( "PS_Detail_glCodeInst_save_BtnId", glCodeDefnNm, "GL Code Definition" );
	}

	/*
	 * This method is for modify Gl Code Instance
	 */
	public void modifyGlCodeInstance() throws Exception
	{
		modifyBasicDetails();
		configEntity();
		psGenericHelper.detailSave( "PS_Detail_glCodeInst_save_BtnId", glCodeDefnNm, "GL Code Definition" );
	}

	/*
	 * This method is for configure Basic details of Gl Code Instance
	 */
	private void configBasicDetails() throws Exception
	{
		initializeBasicVariable( glCodeInstDetailsMap );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforEntityElement();
		glCodeDefnEntitySrch( "PS_Detail_glCodeInst_glCdDfn_entityId", glCodeDefnNm, "PS_Detail_glCodeInst_Details_xpath" );
		TextBoxHelper.type( "PS_Detail_glCodeInst_glCode_txtId", glCode );
		TextBoxHelper.type( "PS_Detail_glCodeInst_fromDt_txtId", fromDate );
		TextBoxHelper.type( "PS_Detail_glCodeInst_toDt_txtId", toDate );
	}

	/*
	 * This method is for modify Basic details of Gl Code Instance
	 */
	private void modifyBasicDetails() throws Exception
	{
		initializeBasicVariable( glCodeInstDetailsMap );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforEntityElement();
		assertEquals( EntityComboHelper.getValue( "PS_Detail_glCodeInst_glCdDfn_entityId" ), glCodeDefnNm, " GL code Defn name is not matched" );
		ProductUtil.modifyTextBox( "PS_Detail_glCodeInst_glCode_txtId", glCode );
		ProductUtil.modifyTextBox( "PS_Detail_glCodeInst_fromDt_txtId", fromDate );
		ProductUtil.modifyTextBox( "PS_Detail_glCodeInst_toDt_txtId", toDate );
	}

	/*
	 * This method is for configure entity Gl Code Instance
	 */
	private void configEntity() throws Exception
	{
		try
		{
			initializeEntityVariable( glCodeInstDetailsMap );
			entityArr = psStringUtils.stringSplitFirstLevel( entity );
			entityValueArr = psStringUtils.stringSplitFirstLevel( entityValue );
			List<String> getKeysOfGlCdDefnEntityGrid = getKeysOfGlCdDefnEntityGrid();
			Map<String, ArrayList<String>> mapOfGlCdDefnGrid = ProductUtil.getGridColumnValues( "PS_Detail_glCodeInst_entityGridId", "grid_column_header_undefined_", getKeysOfGlCdDefnEntityGrid );
			for ( int i = 0; i < entityArr.length; i++ )
			{
				boolean isEntityPresentInGrid = ProductUtil.isDataPresentInGrid( "PS_Detail_glCodeInst_entityGridId", mapOfGlCdDefnGrid, "Entity", entityArr[i] );
				assertTrue( isEntityPresentInGrid, "This parameter name value: " + entityArr[i] + " is not found in this grid:" + GenericHelper.getORProperty( "PS_Detail_glCodeInst_entityGridId" ) );
				Log4jHelper.logInfo( "This Entity name : " + entityArr[i] + " is  found in this grid:" + GenericHelper.getORProperty( "PS_Detail_glCodeInst_entityGridId" ) );
				GridHelper.clickRow( "PS_Detail_glCodeInst_entityGridId", entityArr[i], "Entity" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				configEntityValue( entityValueArr[i] );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for configure entity value of Gl Code Instance
	 */
	private void configEntityValue( String entityValue ) throws Exception
	{
		try
		{
			String[] entityValueArr = entityValue.split( secondLevelDelimiter );
			List<String> getKeysOfGlCdInstValueGrid = getKeysOfGlCdInstValueGrid();
			Map<String, ArrayList<String>> mapOfGlCdInstGrid = ProductUtil.getGridColumnValues( "PS_Detail_glCodeInst_entityValueGridId", "grid_column_header_undefined_", getKeysOfGlCdInstValueGrid );
			for ( int i = 0; i < entityValueArr.length; i++ )
			{
				boolean isEntityPresentInGrid = ProductUtil.isDataPresentInGrid( "PS_Detail_glCodeInst_entityValueGridId", mapOfGlCdInstGrid, "Value", entityValueArr[i] );
				if ( !isEntityPresentInGrid )
				{
					ButtonHelper.click( "PS_Detail_glCodeInst_entityValue_add_BtnId" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					enitityValuesSelectionOb.entityValueSelectionGlCdInstance( "PS_Detail_glCodeInst_entityValueGridId", "GL Code Instance", entityValueArr[i], i + 1 );
				}
				else
					Log4jHelper.logInfo( "This Entity Value name : " + entityValueArr[i] + " is  already present in this grid:" + GenericHelper.getORProperty( "PS_Detail_glCodeInst_entityValueGridId" ) );

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	//Gl Code Definition grid columns keys of 'entity'
	private List<String> getKeysOfGlCdDefnEntityGrid()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "Entity" );
		return listColumn;
	}

	//Gl Code Instance grid columns Keys of'Value'
	private List<String> getKeysOfGlCdInstValueGrid()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "Value" );
		return listColumn;
	}

	/*
	 * This method is for Gl Code definition entity search
	 */
	private void glCodeDefnEntitySrch( String entiTyId, String glCodeDefnNm, String detailsPageXpath ) throws Exception
	{
		EntityComboHelper.clickEntityIcon( entiTyId );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforPopupHeaderElement( "Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		int row = GridHelper.getRowNumber( "SearchGrid", glCodeDefnNm, "Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertTrue( row > 0, "This GL Code Defn name is not found " + glCodeDefnNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "Detail_popUpWindowId", "SearchGrid", row, "Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.clickIfEnabled( "Detail_popUpWindowId", "OKButton" );
		GenericHelper.waitForLoadmask();
		ElementHelper.waitForElement( detailsPageXpath, searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

}
