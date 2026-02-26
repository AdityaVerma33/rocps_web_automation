package com.subex.rocps.automation.helpers.application.bills.billParameter;

import java.util.Map;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class BillPMSorcesDetail extends PSAcceptanceTest
{

	protected Map<String, String> billPMSourceMap = null;

	protected String[] tableInstanceArr;
	protected String tableInstance;
	protected String joinType;
	protected String[] joinTypeArr;
	protected String column1;
	protected String[] column1Arr;
	protected String column2;
	protected String[] column2Arr;
	DataSelectionHelper dataSelection = new DataSelectionHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();

	public BillPMSorcesDetail( Map<String, String> billPMSourceMap ) throws Exception
	{
		this.billPMSourceMap = billPMSourceMap;
		initilizeVariables( billPMSourceMap );
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

		String gridId = GenericHelper.getORProperty( "PSDetail_Bill_Parameter_Sourceselection_GridId" );
		{
			for ( int i = 0; i < tableInstanceArr.length; i++ )
			{
				configBPSourceGridField( gridId, i + 1 );

			}
		}

	}

	/*
	 * Method: for configure bill parameter source grid field
	 */
	private void configBPSourceGridField( String gridId, int row ) throws Exception
	{

		ButtonHelper.click( "PSDetail_Bill_Parameter_Source_addtableInstance_ID" );
		dataSelection.tableInstanceSearch( tableInstanceArr[row - 1] );
		if ( row > 1 )
		{
			psDataComponentHelper.updateGridComboBox( gridId, "PSDetail_Bill_Parameter_JoinType_ComboId", row, "Join  Type", joinTypeArr[row - 1] );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psDataComponentHelper.updateGridComboBox( gridId, "PSDetail_Bill_Parameter_Column1_ComboId", row, "Column 1", column1Arr[row - 1] );
			psDataComponentHelper.updateGridComboBox( gridId, "PSDetail_Bill_Parameter_Column2_ComboId", row, "Column 2", column2Arr[row - 1] );

		}
	}

}
