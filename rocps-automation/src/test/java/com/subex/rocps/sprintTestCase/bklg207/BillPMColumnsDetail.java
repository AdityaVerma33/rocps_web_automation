package com.subex.rocps.sprintTestCase.bklg207;

import java.util.Map;



import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class BillPMColumnsDetail extends PSAcceptanceTest
{
	protected Map<String, String> billPMColumnsMap = null;
	protected String tableNameParent;
	protected String columnNameChild;
	protected String columnDetails_Aggregate;
	protected String columnDetails_Statement;
	protected String columnDetails_ColumnName;
	protected String columnDetails_DisplayName;
	protected String columnDetails_Type;
	protected String columnDetails_NotNull;
	protected String columnDetails_Sort;
	

	/*
	 * Constructor 
	 */
	BillPMColumnsDetail( Map<String, String> billPMColumnsMap ) throws Exception
	{
		this.billPMColumnsMap = billPMColumnsMap;
		initialzeVariable( billPMColumnsMap );
	}

	/*
	 * This method is to initialze the variables 
	 */
	public void initialzeVariable( Map<String, String> Map ) throws Exception
	{
		tableNameParent = ExcelHolder.getKey( Map, "ColumnTable" );
		columnNameChild = ExcelHolder.getKey( Map, "ColumnToAdd" );
		columnDetails_Statement = ExcelHolder.getKey( Map, "ColumnDetails_Statement" );
		columnDetails_Aggregate = ExcelHolder.getKey( Map, "ColumnDetails_Aggregate" );
		columnDetails_ColumnName = ExcelHolder.getKey( Map, "ColumnDetails_ColumnName" );
		columnDetails_DisplayName = ExcelHolder.getKey( Map, "ColumnDetails_DisplayName" );
		columnDetails_Type = ExcelHolder.getKey( Map, "ColumnDetails_Type" );
		columnDetails_NotNull = ExcelHolder.getKey( Map, "ColumnDetails_NotNull" );
		columnDetails_Sort = ExcelHolder.getKey( Map, "ColumnDetails_Sort" );
	}

	/*
	 * This method is for Configure Column 
	 */
	public void ConfigureColumnsTab() throws Exception
	{

		TabHelper.gotoTab( "PSDetail_Bill_Parameter_Column_TabId" );
		PSDataComponentHelper.selectChildOfParent( "availableColumnsTree", tableNameParent, columnNameChild );
		ButtonHelper.click( "PSDetail_Bill_Parameter_add_ButtonID" );

	}


}
