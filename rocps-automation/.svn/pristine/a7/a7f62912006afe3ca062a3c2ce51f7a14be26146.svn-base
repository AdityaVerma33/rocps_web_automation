package com.subex.rocps.automation.helpers.application.accruals.glCodeDefn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class GLCodeDefnDetails extends PSAcceptanceTest
{
	protected Map<String, String> glCodeDefnDetailsMap = null;
	protected String glCodeDefnNm;
	protected String entity;
	protected String entityArr[];
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();

	/**Construector
	 * @param glCodeDefnDetailsMap
	 */
	public GLCodeDefnDetails( Map<String, String> glCodeDefnDetailsMap )
	{

		this.glCodeDefnDetailsMap = glCodeDefnDetailsMap;
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		glCodeDefnNm = ExcelHolder.getKey( map, "Name" );
		entity = ExcelHolder.getKey( map, "Entitys" );

	}

	/*
	 * This method is for creationOf Gl code defnition
	 */
	public void createGlCdDefn() throws Exception
	{
		initializeVariable( glCodeDefnDetailsMap );
		TextBoxHelper.type( "PS_Detail_glCodeDefn_name_textId", glCodeDefnNm );
		configGlCdEntity();
		psGenericHelper.detailSave( "PS_Detail_glCodeDefn_save_BtnId", glCodeDefnNm, "Name" );
	}

	/*
	 * This method is for modify Gl code defnition
	 */
	public void modifyGlCdDefn() throws Exception
	{
		initializeVariable( glCodeDefnDetailsMap );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_glCodeDefn_name_textId" ), glCodeDefnNm, "Gl code defn Name is not matched" );
		configGlCdEntity();
		psGenericHelper.detailSave( "PS_Detail_glCodeDefn_save_BtnId", glCodeDefnNm, "Name" );
	}

	/*
	 * This method is for configure GL Code Defn Entity Grid panel*/
	private void configGlCdEntity() throws Exception
	{
		try
		{

			entityArr = psStringUtils.stringSplitFirstLevel( entity );
			List<String> getKeysOfGlCdDefnEntityGrid = getKeysOfGlCdDefnEntityGrid();
			Map<String, ArrayList<String>> mapOfGlCdDefnGrid = ProductUtil.getGridColumnValues( "PS_Detail_glCodeDefn_entity_GridId", "grid_column_header_undefined_", getKeysOfGlCdDefnEntityGrid );
			for ( int i = 0; i < entityArr.length; i++ )
			{
				boolean isEntityPresentInGrid = ProductUtil.isDataPresentInGrid( "PS_Detail_glCodeDefn_entity_GridId", mapOfGlCdDefnGrid, "Entity", entityArr[i] );
				if ( !isEntityPresentInGrid )
					configEntity( ( i + 1 ), entityArr[i] );
				else
				{
					GridHelper.clickRow( "PS_Detail_glCodeDefn_entity_GridId", entityArr[i], "Entity" );
					Log4jHelper.logInfo( "This parameter name value: " + entityArr[i] + " is already present in this grid:" + GenericHelper.getORProperty( "PS_Detail_glCodeDefn_entity_GridId" ) );
				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is for configure entity*/
	private void configEntity( int row, String entity ) throws Exception
	{
		ButtonHelper.click( "PS_Detail_glCodeDefn_entity_add_BtnId" );
		entitySearch( entity );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_glCodeDefn_Details_xpath" ), searchScreenWaitSec );

	}

	/*
	 * This method is for  entity search*/
	private void entitySearch( String entity ) throws Exception
	{
		psGenericHelper.waitforPopupHeaderElement( "Entity" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		PSSearchGridHelper.gridFilterSearchWithTextBox( "PS_Detail_entitySrch_entityNm_textId", entity, "Entity" );
		boolean isentityPresent = GridHelper.isValuePresent( "Detail_popUpWindowId", "SearchGrid", entity, "Entity" );
		assertTrue( isentityPresent, "'Entity'  with name :'" + entity + "'  is not found in 'Entity Search' popupScreen " );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "Detail_popUpWindowId", "SearchGrid", entity, "Entity" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.clickIfEnabled( "Detail_popUpWindowId", "OKButton" );
		GenericHelper.waitForLoadmask();
	}

	//Gl Code Definition grid columns keys
	private List<String> getKeysOfGlCdDefnEntityGrid()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "Entity" );
		return listColumn;
	}
}
