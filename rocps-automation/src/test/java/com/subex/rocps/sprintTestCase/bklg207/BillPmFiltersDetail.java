package com.subex.rocps.sprintTestCase.bklg207;

import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class BillPmFiltersDetail
{

	protected Map<String, String> billPMFilterMap = null;

	protected String filterDetails_LeftBracketFlg;
	protected String[] filterDetails_LeftBracketFlgArr;
	protected String filterDetails_RightBracketFlg;
	protected String[] filterDetails_RightBracketFlgArr;
	protected String expression1;
	protected String[] expression1Arr;
	protected String operator;
	protected String[] operatorArr;
	protected String expression2;
	protected String[] expression2Arr;
	BillParameterActImpl BillParameterActImpl = new BillParameterActImpl();
	protected DataSelectionHelper dataSelection = new DataSelectionHelper();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	PSActionImpl psActionImpl = new PSActionImpl();
	PSStringUtils psStringUtils = new PSStringUtils();

	public BillPmFiltersDetail( Map<String, String> billPMFilterMap ) throws Exception
	{
		this.billPMFilterMap = billPMFilterMap;
		initialzeVariable( billPMFilterMap );
		intializeArray();

	}

	/*
	 * This method is for  initialze Variable
	 */
	public void initialzeVariable( Map<String, String> Map ) throws Exception
	{
		filterDetails_LeftBracketFlg = ExcelHolder.getKey( Map, "FilterDetails_LeftBracketFlg" );
		expression1 = ExcelHolder.getKey( Map, "FilterDetails_LeftExpression" );
		expression2 = ExcelHolder.getKey( Map, "FilterDetails_RightExpression" );
		operator = ExcelHolder.getKey( Map, "FilterDetails_Operator" );
		filterDetails_RightBracketFlg = ExcelHolder.getKey( Map, "FilterDetails_RightBracketFlg" );

	}

	/*
	 * This method is for  intialize Array
	 */
	public void intializeArray() throws Exception
	{
		filterDetails_LeftBracketFlgArr = psStringUtils.stringSplitFirstLevel( filterDetails_LeftBracketFlg );
		expression1Arr = psStringUtils.stringSplitFirstLevel( expression1 );
		expression2Arr = psStringUtils.stringSplitFirstLevel( expression2 );
		operatorArr = psStringUtils.stringSplitFirstLevel( operator );
		filterDetails_RightBracketFlgArr = psStringUtils.stringSplitFirstLevel( filterDetails_RightBracketFlg );
	}

	/*
	 * This method is for configure Filters in bill parameter 
	 */
	public void ConfigureFiltersTab() throws Exception
	{

		TabHelper.gotoTab( "PSDetail_Bill_Parameter_Filter_TabId" );
		String gridId = GenericHelper.getORProperty( "PSDetail_Bill_Parameter_Filter_GridId" );
		for ( int row = 1; row <= expression1Arr.length; row++ )
		{

			ButtonHelper.click( "PSDetail_Bill_Parameter_Filter_addButton" );
			ButtonHelper.click( "PSDetail_Bill_Parameter_Condition_select" );

			if ( ValidationHelper.isNotEmpty( filterDetails_LeftBracketFlgArr[row - 1] ) && ValidationHelper.isTrue( filterDetails_LeftBracketFlgArr[row - 1] ) )
				GridHelper.updateGridTextBox( gridId, "PSDetail_Bill_Parameter_Filter_Left_ComboId", row, "(", "(" );

			psDataComponentHelper.updateGridComboBox( gridId, "PSDetail_Bill_Parameter_Filter_Expression1_ComboId", row, "Expression", expression1Arr[row - 1] );

			psDataComponentHelper.updateGridComboBox( gridId, "PSDetail_Bill_Parameter_operator_ComboId", row, "Operator", operatorArr[row - 1] );

			GridHelper.updateGridTextBox( gridId, "PSDetail_Bill_Parameter_expression2_TextId", row, 5, "Expression", expression2Arr[row - 1] );
			if ( ValidationHelper.isNotEmpty( filterDetails_RightBracketFlgArr[row - 1] ) && ValidationHelper.isTrue( filterDetails_RightBracketFlgArr[row - 1] ) )

				GridHelper.updateGridTextBox( gridId, "PSDetail_Bill_Parameter_Right_ComboId", row, ")", ")" );

		}
	}

}
