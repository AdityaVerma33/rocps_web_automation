package com.subex.rocps.automation.helpers.application.bills.billDataset;

import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class BillDsFiltersDetail
{

	protected Map<String, String> billDSFilterMap = null;
	protected String FilterDetails_LeftBracketFlg;
	protected String[] FilterDetails_LeftBracketFlgArr;
	protected String FilterDetails_RightBracketFlg;
	protected String[] FilterDetails_RightBracketFlgArr;
	protected String expression1;
	protected String[] expression1Arr;
	protected String operator;
	protected String[] operatorArr;
	protected String expression2;
	protected String[] expression2Arr;
	PSStringUtils psStringUtils = new PSStringUtils();
	
	public BillDsFiltersDetail( Map<String, String> billDSFilterMap ) throws Exception
	{
		this.billDSFilterMap = billDSFilterMap;
		initialzeVariable( billDSFilterMap );
		intializeArray();

	}

	public void initialzeVariable( Map<String, String> Map ) throws Exception
	{
		FilterDetails_LeftBracketFlg = ExcelHolder.getKey( Map, "FilterDetails_LeftBracketFlg" );
		expression1 = ExcelHolder.getKey( Map, "FilterDetails_LeftExpression" );
		expression2 = ExcelHolder.getKey( Map, "FilterDetails_RightExpression" );
		operator = ExcelHolder.getKey( Map, "FilterDetails_Operator" );
		FilterDetails_RightBracketFlg = ExcelHolder.getKey( Map, "FilterDetails_RightBracketFlg" );

	}

	public void intializeArray() throws Exception
	{
		FilterDetails_LeftBracketFlgArr = psStringUtils.stringSplitFirstLevel( FilterDetails_LeftBracketFlg );
		expression1Arr = psStringUtils.stringSplitFirstLevel( expression1 );
		expression2Arr = psStringUtils.stringSplitFirstLevel( expression2 );
		operatorArr = psStringUtils.stringSplitFirstLevel( operator );
		FilterDetails_RightBracketFlgArr = psStringUtils.stringSplitFirstLevel( FilterDetails_RightBracketFlg );
	}

	public void ConfigureFiltersTab() throws Exception
	{

		TabHelper.gotoTab( "PSBillDataset_Detail_Filter_FilterTab" );
		for ( int row = 1; row <= expression1Arr.length; row++ )
		{
			int col = 2;

			ButtonHelper.click( "PSBillDataset_Detail_FilterTab_FilterAddID" );
			ButtonHelper.click( "PSBillDataset_Detail_Filter_ConditionID" );
			//GridHelper.
			GridHelper.updateGridTextBox( "PSBillDataset_Detail_Filter_FilterGridId", "PSBillDataset_Detail_Filter_LeftComboId", row, col, col, FilterDetails_LeftBracketFlgArr[row - 1] );
			col++;
			GridHelper.updateGridComboBox( "PSBillDataset_Detail_Filter_FilterGridId", "PSBillDataset_Detail_Filter_Expression1ComboId", row, col, "Expression", expression1Arr[row - 1] );
			col++;
			GridHelper.updateGridComboBox( "PSBillDataset_Detail_Filter_FilterGridId", "PSBillDataset_Detail_Filter_operatorComboId", row, col, "Operator", operatorArr[row - 1] );
			col++;
			GridHelper.updateGridTextBox( "PSBillDataset_Detail_Filter_FilterGridId", "PSBillDataset_Detail_Filter_expression2ID", row, col, col, expression2Arr[row - 1] );
			col++;
			GridHelper.updateGridTextBox( "PSBillDataset_Detail_Filter_FilterGridId", "PSBillDataset_Detail_Filter_RightComboId", row, col, col, FilterDetails_RightBracketFlgArr[row - 1] );

		}

	}
}
