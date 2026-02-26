package com.subex.rocps.automation.helpers.application.products.productArgType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ProductArgTypeDetailHelper extends PSAcceptanceTest
{
	protected Map<String, String> productArgTypeDetailsMap = null;
	protected String productArgTypeName;
	protected String productArgType;
	protected String productArgFieldParam;
	protected String productArgFieldParamArr[];
	protected String productArgFieldType;
	protected String productArgFieldTypeArr[];
	protected String productArgFieldMandatory;
	protected String productArgFieldMandatoryArr[];

	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	ProductArgTypeDetailImpl productArgTypeDetailOb = new ProductArgTypeDetailImpl();

	/**Constructor:
	 * @param productArgTypeDetailsMap
	 */
	public ProductArgTypeDetailHelper( Map<String, String> productArgTypeDetailsMap )
	{
		this.productArgTypeDetailsMap = productArgTypeDetailsMap;
	}

	/*
	 * This method is for initialize variable for Basic Details
	 */
	private void initializeVariableBasicDetails( Map<String, String> map ) throws Exception
	{
		productArgTypeName = ExcelHolder.getKey( map, "Name" );
		productArgType = ExcelHolder.getKey( map, "Type" );
		productArgFieldParam = ExcelHolder.getKey( map, "ProductArgFieldParam" );
		productArgFieldType = ExcelHolder.getKey( map, "ProductArgFieldType" );
		productArgFieldMandatory = ExcelHolder.getKey( map, "ProductArgFieldMandatory" );

	}

	/*
	 * This method is for creation*/
	public void createProductArgType() throws Exception
	{
		initializeVariableBasicDetails( productArgTypeDetailsMap );
		productArgTypeDetailOb.configureBasicDetail( productArgTypeName, productArgType );
		configureProductArgFieldPanel();
	}

	/*
	 * This method is for modification*/
	public void modifyProductArgType() throws Exception
	{
		initializeVariableBasicDetails( productArgTypeDetailsMap );

		assertEquals( TextBoxHelper.getValue( "PS_Detail_prodArgType_name_textID" ), productArgTypeName, "Product argument type name is not matched" );
		ProductUtil.modifyComboBox( "PS_Detail_prodArgType_type_comboID", productArgType );
		configureProductArgFieldPanel();
	}

	/*
	 * This method is for configure product arhument field panel*/
	private void configureProductArgFieldPanel() throws Exception
	{
		try
		{
				productArgFieldParamArr = psStringUtils.stringSplitFirstLevel( productArgFieldParam );
				productArgFieldTypeArr = psStringUtils.stringSplitFirstLevel( productArgFieldType );
				productArgFieldMandatoryArr = psStringUtils.stringSplitFirstLevel( productArgFieldMandatory );
			List<String> getKeysOfArgFieldGrid = getKeysOfArgFieldGrid();
			for ( int i = 0; i < productArgFieldParamArr.length; i++ )
			{
				Map<String, ArrayList<String>> mapOfArgumentFieldGrid = getGridColumnValues( "PS_Detail_prodArgType_productField_gridID", "grid_column_header_undefined_", getKeysOfArgFieldGrid );
				boolean isArgumentParamPresentInGrid = isDataPresentInGrid( "PS_Detail_prodArgType_productField_gridID", mapOfArgumentFieldGrid, "Param", productArgFieldParamArr[i] );
				if ( !isArgumentParamPresentInGrid )
					productArgTypeDetailOb.configProductArgumentField( ( i + 1 ), productArgFieldParamArr[i], productArgFieldTypeArr[i], productArgFieldMandatoryArr[i] );
				else
					Log4jHelper.logInfo( "This paramvalue: " + productArgFieldParamArr[i] + " is already present in this grid:" + GenericHelper.getORProperty( "PS_Detail_prodArgType_productField_gridID" ) );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
		}
	}

	/*
	 * This method is for save product argument type*/
	public void saveProductArgType( String productArgType ) throws Exception
	{
		psGenericHelper.detailSave( "PS_Detail_prodArgType_save_btnID", productArgType, "Name" );
	}

	//Amounts grid columns keys
	private List<String> getKeysOfArgFieldGrid()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "Param" );
		return listColumn;
	}

	/*Method to check is data present in grid*/
	public boolean isDataPresentInGrid( String gridId, Map<String, ArrayList<String>> mapOfGrid, String columnHeader, String columnValue ) throws Exception
	{
		gridId = GenericHelper.getORProperty( gridId );
		boolean isDataPresentInGrid = mapOfGrid.entrySet().stream().filter( x -> x.getKey().equals( columnHeader ) ).anyMatch( x -> x.getValue().contains( columnValue ) );
		return isDataPresentInGrid;
	}

	/*Method get the key values from search screen of grid*/
	public Map<String, ArrayList<String>> getGridColumnValues( String gridId, String columnHeaderId, List<String> listColumn ) throws Exception
	{
		Map<String, ArrayList<String>> hmap = new HashMap<String, ArrayList<String>>();
		String key;
		List<String> uIColumns = psGenericHelper.getGridColumns( columnHeaderId );

		for ( String col : listColumn )
		{
			ArrayList<String> colCellValues = new ArrayList<String>();
			key = col;
			int colNum = uIColumns.indexOf( key );
			if ( colNum < 0 )
				FailureHelper.failTest( "This column-" + key + " is not found in the gridId:" + GenericHelper.getORProperty( gridId ) );
			else
				colCellValues = getCellValues( gridId, key );
			hmap.put( key, colCellValues );
		}
		return hmap;

	}

	/*Method get the  values from search screen of grid*/
	public ArrayList<String> getCellValues( String gridId, String key ) throws Exception
	{
		ArrayList<String> colCellValues = new ArrayList<String>();
		int noOfrow = GridHelper.getRowCount( gridId );
		for ( int i = 0; i < noOfrow; i++ )
		{
			String cellValue = GridHelper.getCellValue( gridId, i + 1, key );
			colCellValues.add( cellValue );
		}

		return colCellValues;
	}
}
