package com.subex.rocps.automation.helpers.application.bills.billDataset;

import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class BillDSSourcesDetail extends PSAcceptanceTest
{

	protected Map<String, String> billDSSourceMap = null;
	protected String[] tableInstanceArr;
	protected String tableInstance;
	protected String joinType;
	protected String[] joinTypeArr;
	protected String column1;
	protected String[] column1Arr;
	protected String column2;
	protected String[] column2Arr;
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	DataSelectionHelper dataSelection = new DataSelectionHelper();
	PSStringUtils psStringUtils = new PSStringUtils();

	public BillDSSourcesDetail( Map<String, String> billDSSourceMap ) throws Exception
	{
		this.billDSSourceMap = billDSSourceMap;
		initilizeVariables( billDSSourceMap );
		intialiseArray();
	}


	public void initilizeVariables( Map<String, String> map ) throws Exception
	{
		tableInstance = ExcelHolder.getKey( map, "Sources_TableInstance" );
		joinType = ExcelHolder.getKey( map, "Sources_JointType" );
		column1 = ExcelHolder.getKey( map, "Sources_Column1" );
		column2 = ExcelHolder.getKey( map, "Sources_Column2" );
	}

	protected void intialiseArray() throws Exception
	{
		tableInstanceArr = psStringUtils.stringSplitFirstLevel( tableInstance );
		joinTypeArr = psStringUtils.stringSplitFirstLevel( joinType );
		column1Arr = psStringUtils.stringSplitFirstLevel( column1 );
		column2Arr = psStringUtils.stringSplitFirstLevel( column2 );

	}

	/*
	 * This method is for Configure Source 
	 */
	protected void configureSource() throws Exception
	{

		String gridId = GenericHelper.getORProperty( "PSBillDataset_Detail_Source_SourceselectionGridId" );
		{
			for ( int i = 0; i < tableInstanceArr.length; i++ )
			{
				configBDSourceGridField( gridId, i + 1 );

			}
		}

	}

	/*
	 * Method: for configure bill dataset source grid field
	 */
	private void configBDSourceGridField( String gridId, int row ) throws Exception
	{

		ButtonHelper.click( "PSBillDataset_Detail_Source_addtableInstance" );
		dataSelection.tableInstanceSearch( tableInstanceArr[row - 1] );
		if ( row > 1 )
		{
			updateGridComboBox( gridId, "PSBillDataset_Detail_Source_JoinTypeComboId", row, "Join  Type", joinTypeArr[row - 1] );
			GenericHelper.waitForLoadmask( 3000 );
			psDataComponentHelper.updateGridComboBox( gridId, "PSBillDataset_Detail_Source_Column1ComboId", row, "Column 1", column1Arr[row - 1] );
			psDataComponentHelper.updateGridComboBox( gridId, "PSBillDataset_Detail_Source_Column2ComboId", row, "Column 2", column2Arr[row - 1] );

		}
	}

	public static void updateGridComboBox( String gridId, String comboId, int rowNum, String valueColumnHeader, String value ) throws Exception
	{
		try
		{
			if ( ValidationHelper.isNotEmpty( value ) )
			{
				String gridMenuClickXpath = "//div[@id='" + gridId + "']/div[2]/div/div[2]/div/div[3]/div/div[3]";
				int columnNum = GridHelper.getColumnNumber( gridId, valueColumnHeader );
				GridHelper.clickRow( gridId, rowNum, valueColumnHeader );
				if ( !ComboBoxHelper.isPresent( comboId ) )
					GridHelper.clickRow( gridId, rowNum, valueColumnHeader );
				int tryCount = 0;
				int maxWaitCount = 5;
				int comboValueCount = 0;
				while ( true )
				{
					comboValueCount = ComboBoxHelper.getValuesCount( comboId );
					if ( comboValueCount > 1 || tryCount == maxWaitCount )
						break;

					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					MouseHelper.click( gridMenuClickXpath );
					GridHelper.clickRow( gridId, rowNum, valueColumnHeader );
					if ( !ComboBoxHelper.isPresent( comboId ) )
						GridHelper.clickRow( gridId, rowNum, valueColumnHeader );

					tryCount++;

					Log4jHelper.logInfo( "combo value count" + comboValueCount + " TryCount" + tryCount );

				}
				ComboBoxHelper.select( gridId, comboId, rowNum, columnNum, value );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
