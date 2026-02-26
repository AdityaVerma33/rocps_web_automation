package com.subex.rocps.automation.helpers.application.reportingAndExtraction.repColMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ConfigRepColMapTableInstDetail extends PSAcceptanceTest
{
	protected Map<String, String> configRepColTableInstDetailap = null;
	protected String tableInstance;
	protected String requiredColumn;
	protected String requiredColumnArr[];
	protected String dataType;
	protected String dataTypeArr[];
	protected String entityNm;
	protected String entityNmArr[];
	protected String isMandatory;
	protected String isMandatoryArr[];
	protected String isKey;
	protected String isKeyArr[];
	protected String tableInstColumn;
	protected String tableInstColumnArr[];
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	DataSelectionHelper dataSelectionHelper = new DataSelectionHelper();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();

	/**Constructor
	 * @param configRepColTableInstDetailap
	 */
	public ConfigRepColMapTableInstDetail( Map<String, String> configRepColTableInstDetailap )
	{

		this.configRepColTableInstDetailap = configRepColTableInstDetailap;
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		tableInstance = ExcelHolder.getKey( map, "TableInstance" );
		requiredColumn = ExcelHolder.getKey( map, "RequiredColumn" );
		dataType = ExcelHolder.getKey( map, "DataType" );
		entityNm = ExcelHolder.getKey( map, "EntityName" );
		isMandatory = ExcelHolder.getKey( map, "IsMandatory" );
		isKey = ExcelHolder.getKey( map, "IsKey" );
		tableInstColumn = ExcelHolder.getKey( map, "TableInstanceColumn" );

	}

	/*
	 * This method is for configure Report Column Table instance
	 */
	public void configRepColTableInstance() throws Exception
	{
		initializeVariable( configRepColTableInstDetailap );
		List<String> getKeysOfTableInstGrid = getKeysOfTableInstGrid();
		Map<String, ArrayList<String>> mapOfTableInstFieldGrid = psDataComponentHelper.getGridColumnValues( "PS_Detail_repColMapping_tableInst_gridId", "grid_column_header_undefined_", getKeysOfTableInstGrid );
		boolean isTableInstPresentInGrid = psDataComponentHelper.isDataPresentInGrid( "PS_Detail_repColMapping_tableInst_gridId", mapOfTableInstFieldGrid, "Table  Instance", tableInstance );
		if ( !isTableInstPresentInGrid )
			configTableInstance();
		else
			Log4jHelper.logInfo( "The given Table Instance is already avilable with name: " + tableInstance );
	}

	/*
	 * This method is for  configure table instance
	 */
	private void configTableInstance() throws Exception
	{
		ButtonHelper.click( "PS_Detail_repColMapping_tableInst_add_btnId" );
		psGenericHelper.waitforEntityElement();
		tableInstEntitySearch();
		configRepInstColMapGrid();
		ButtonHelper.click( "PS_Detail_repColMapping_tableInst_ok_BtnId" );

	}

	/*
	 * This method is for configure Report Instance columne map grid
	 */
	private void configRepInstColMapGrid() throws Exception
	{
		try
		{
			requiredColumnArr = psStringUtils.stringSplitFirstLevel( requiredColumn );
			tableInstColumnArr = psStringUtils.stringSplitFirstLevel( tableInstColumn );
			dataTypeArr = psStringUtils.stringSplitFirstLevel( dataType );
			entityNmArr = psStringUtils.stringSplitFirstLevel( entityNm );
			isMandatoryArr = psStringUtils.stringSplitFirstLevel( isMandatory );
			isKeyArr = psStringUtils.stringSplitFirstLevel( isKey );
			for ( int i = 0; i < requiredColumnArr.length; i++ )
			{
				GridHelper.clickRow( "PS_Detail_repColMapping_tableInstColMap_gridId", i + 1, "Required  Column" );
				psDataComponentHelper.verifyGridCellValue( "PS_Detail_repColMapping_tableInstColMap_gridId", i + 1, "Required  Column", requiredColumnArr[i] );

				if ( ValidationHelper.isNotEmpty( dataType ) )
					psDataComponentHelper.verifyGridCellValue( "PS_Detail_repColMapping_tableInstColMap_gridId", i + 1, "Data  Type", dataTypeArr[i] );
				if ( ValidationHelper.isNotEmpty( entityNm ) )
					psDataComponentHelper.verifyGridCellValue( "PS_Detail_repColMapping_tableInstColMap_gridId", i + 1, "Entity  Name", entityNmArr[i] );
				if ( ValidationHelper.isNotEmpty( isMandatory ) )
					psDataComponentHelper.verifyGridCheckBox( "PS_Detail_repColMapping_tableInstColMap_gridId", requiredColumnArr[i], isMandatoryArr[i], "Is  Mandatory" );
				if ( ValidationHelper.isNotEmpty( isKey ) )
					psDataComponentHelper.verifyGridCheckBox( "PS_Detail_repColMapping_tableInstColMap_gridId", requiredColumnArr[i], isKeyArr[i], "Is  Key" );
				selectTableInstColValue( i + 1, tableInstColumnArr[i] );

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * Method for selecting the tableInstanceColumnVal
	 */
	public void selectTableInstColValue( int row, String tableInstanceColumnVal ) throws Exception
	{

		String gridColHeader = "Table  Instance  Column";
		GridHelper.clickRow( "PS_Detail_repColMapping_tableInstColMap_gridId", row, gridColHeader );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psDataComponentHelper.selectComboBoxVal( "PS_Detail_repColMapping_tableInst_tableInstCol_combId", tableInstanceColumnVal );
		GridHelper.updateGridComboBox( "PS_Detail_repColMapping_tableInstColMap_gridId", "PS_Detail_repColMapping_tableInst_tableInstCol_combId", row, "Table  Instance  Column", tableInstanceColumnVal );

	}

	/*
	 * This method is for table instance entity search
	 */
	private void tableInstEntitySearch() throws Exception
	{
		dataSelectionHelper.tableInstanceEntitySearch( tableInstance );
	}

	//Paramters grid columns keys
	private List<String> getKeysOfTableInstGrid()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "Table  Instance" );
		return listColumn;
	}

}
