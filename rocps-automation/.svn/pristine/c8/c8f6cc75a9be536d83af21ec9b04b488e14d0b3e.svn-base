package com.subex.rocps.automation.helpers.application.referenceTable;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class RoamingSerMatchExpresion extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> roamSerMatExpExcelMap = null;
	protected Map<String, String> roamSerMatExpMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookField;
	protected String sheetField;
	protected String testCaseField;
	protected int occurence;
	protected int colSize;
	protected String clientPartition;
	protected String colmHdrs;
	protected String expressionGrp;
	protected String field;
	protected String matchExpression;
	protected String orderNo;
	protected String joinType;

	protected int index;
	protected PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookField
	 * @param sheetField
	 * @param testCaseField
	 * @throws Exception 
	 */
	public RoamingSerMatchExpresion( String path, String workBookField, String sheetField, String testCaseField ) throws Exception
	{
		this.path = path;
		this.workBookField = workBookField;
		this.sheetField = sheetField;
		this.testCaseField = testCaseField;
		roamSerMatExpExcelMap = excelData.readDataByColumn( path, workBookField, sheetField, testCaseField );
		excelHolderObj = new ExcelHolder( roamSerMatExpExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/**Constructor : Initializing the excel with occurrence
	 * @param path
	 * @param workBookField
	 * @param sheetField
	 * @param testCaseField
	 * @param occurence
	 * @throws Exception 
	 */
	public RoamingSerMatchExpresion( String path, String workBookField, String sheetField, String testCaseField, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookField = workBookField;
		this.sheetField = sheetField;
		this.testCaseField = testCaseField;
		this.occurence = occurence;
		roamSerMatExpExcelMap = excelData.readDataByColumn( path, workBookField, sheetField, testCaseField, occurence );
		excelHolderObj = new ExcelHolder( roamSerMatExpExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		expressionGrp = ExcelHolder.getKey( map, "ExpressionGroup" );
		field = ExcelHolder.getKey( map, "Field" );
		matchExpression = ExcelHolder.getKey( map, "MatchExpression" );
		orderNo = ExcelHolder.getKey( map, "OrderNo" );
		joinType = ExcelHolder.getKey( map, "JoinType" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'Roaming Service Match Expression' screen common method
	 */
	private void roamSerMatExpScreen() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "Roaming Service Match Expression" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		roamSerMatExpMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Field" );
	}

	/*
	 * This method is for 'Roaming Service Match Expression' screen column validation
	 */
	public void roamSerMatExpColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamSerMatExpScreen();
				colmHdrs = ExcelHolder.getKey( roamSerMatExpMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnFields = new ArrayList<String>();
				String roamSerMatExpGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : roamSerMatExpGridColumnsArr )
					excelColumnFields.add( column );
				psGenericHelper.totalColumns( excelColumnFields );
				Log4jHelper.logInfo( "'Roaming Service Match Expression' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Roaming Service Match Expression'  creation
	 */
	public void roamSerMatExpCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamSerMatExpScreen();
				initializeVariable( roamSerMatExpMap );
				boolean isFieldPresentOfExpGrp = isRoamingServMatchExpPresent( field, "Field" );
				if ( !isFieldPresentOfExpGrp )
				{
					clickNewAction( clientPartition );
					configureRoamingServMatchExp();
					clickOnSave();
					Log4jHelper.logInfo( "'Roaming Service Match Expression' is successfully created with  field:- '" + field + " for expression:-" + expressionGrp );

				}
				else

					Log4jHelper.logInfo( "'Roaming Service Match Expression' is already avilable with  field:- '" + field + " for expression:-" + expressionGrp );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Roaming Service Match Expression'  edit
	 */
	public void roamSerMatExpEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamSerMatExpScreen();
				initializeVariable( roamSerMatExpMap );
				boolean isFieldPresentOfExpGrp = isRoamingServMatchExpPresent( field, "Field" );
				if ( isFieldPresentOfExpGrp )
				{
					GridHelper.clickRow( "SearchGrid", field, "Field" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					clickOnAction( "Common Tasks", "Edit" );
					modifyRoamingServMatchExp();
					clickOnSave();
					Log4jHelper.logInfo( "'Roaming Service Match Expression' is successfully updated with  field:- '" + field + " for expression:-" + expressionGrp );

				}
				else

					Log4jHelper.logInfo( "'Roaming Service Match Expression' is not avilable with  field:- '" + field + " for expression:-" + expressionGrp );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/* This method is used 
	 * check roaming service match expression present
	 */
	private boolean isRoamingServMatchExpPresent( String txtValue, String txtColumnHeader ) throws Exception
	{
		ComboBoxHelper.select( "PS_Detail_roamServMatchExp_ExpGrpFilter_comboId", expressionGrp );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Field" );
		return isDataPresent( txtValue, txtColumnHeader );

	}

	/* This method is used  to check data present*/
	private boolean isDataPresent( String txtValue, String txtColumnHeader ) throws Exception
	{
		int row = GridHelper.getRowCount( "SearchGrid" );
		boolean rowVal = false;
		if ( row > 0 )
		{
			for ( int i = 0; i < row; i++ )
			{
				String cellValue = GridHelper.getCellValue( "SearchGrid", i + 1, txtColumnHeader );
				GenericHelper.waitForLoadmask();
				rowVal = cellValue.contains( txtValue );
				if ( rowVal )
					return true;
			}
			return rowVal;
		}
		return false;
	}

	/* This method is used 
	 * configure Roaming Service Match Expression
	 */
	private void configureRoamingServMatchExp() throws Exception
	{
		ComboBoxHelper.select( "PS_Detail_roamServMatchExp_ExpGrp_comboId", expressionGrp );
		ComboBoxHelper.select( "PS_Detail_roamServMatchExp_field_comboId", field );
		TextBoxHelper.type( "PS_Detail_roamServMatchExp_matchExp_txtId", matchExpression );
		TextBoxHelper.type( "PS_Detail_roamServMatchExp_orderNo_txtId", orderNo );
		ComboBoxHelper.select( "PS_Detail_roamServMatchExp_joinType_comboId", joinType );
	}

	/* This method is used 
	 * modify Roaming Service Match Expression
	 */
	private void modifyRoamingServMatchExp() throws Exception
	{
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_roamServMatchExp_ExpGrp_comboId" ), expressionGrp, "'Expression Group' is not matched" );
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_roamServMatchExp_field_comboId" ), field, "'Field' is not matched" );
		ProductUtil.modifyTextBox( "PS_Detail_roamServMatchExp_matchExp_txtId", matchExpression );
		ProductUtil.modifyTextBox( "PS_Detail_roamServMatchExp_orderNo_txtId", orderNo );
		ProductUtil.modifyComboBox( "PS_Detail_roamServMatchExp_joinType_comboId", joinType );
	}

	/* This method is used to click on save button*/
	private void clickOnSave() throws Exception
	{
		psGenericHelper.detailSave( "PS_Detail_roamService_save_btnId", field, "Field" );
	}

	/* This method is used to click on 'New' action */
	private void clickNewAction( String clientPartition ) throws Exception
	{
		ProductUtil.waitForParentActionElementTOBeclickable( "Common Tasks" );
		psGenericHelper.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( "PS_Detail_roamServMatchExp_detailXpath", searchScreenWaitSec );
	}

	/* This method is used to click on  action'*/
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable( parentActionNm );
		psGenericHelper.validateActionText( parentActionNm, childActionNm );
		NavigationHelper.navigateToAction( parentActionNm, childActionNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_roamServMatchExp_detailXpath" ), searchScreenWaitSec );
	}

	// Method: for Roaming Service Match Expression deletion action
	public void roamServMatchExpressionDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			roamSerMatExpScreen();
			expressionGrp = ExcelHolder.getKey( roamSerMatExpMap, "ExpressionGroup" );
			field = ExcelHolder.getKey( roamSerMatExpMap, "Field" );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isfieldPresent = isRoamingServMatchExpPresent( field, "Field" );
			assertTrue( isfieldPresent, "Roaming Service Match Expression  is not available in the screen with  Field: -'" + field + " for expression:-" + expressionGrp );
			psGenericHelper.clickDeleteOrUnDeleteAction( field, "Field", "Delete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			isfieldPresent = isRoamingServMatchExpPresent( field, "Field" );
			assertTrue( isfieldPresent, field + " is not present" );
			Log4jHelper.logInfo( "Roaming Service Match Expression  is deleted successfully with the value-:'" + field + " for expression:-" + expressionGrp );
		}

	}

	// Method: for Roaming Service Match Expression  Undeletion action
	public void roamServMatchExpressionUnDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			roamSerMatExpScreen();
			expressionGrp = ExcelHolder.getKey( roamSerMatExpMap, "ExpressionGroup" );
			field = ExcelHolder.getKey( roamSerMatExpMap, "Field" );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean isfieldPresent = isRoamingServMatchExpPresent( field, "Field" );
			assertTrue( isfieldPresent, "Roaming Service Match Expression  is not available in the screen with  Field: -'" + field + " for expression:-" + expressionGrp );
			psGenericHelper.clickDeleteOrUnDeleteAction( field, "Field", "Undelete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			isfieldPresent = isRoamingServMatchExpPresent( field, "Field" );
			assertTrue( isfieldPresent, field + " is not present" );
			Log4jHelper.logInfo( "Roaming Service Match Expression  is undeleted successfully with the  value:  '" + field + " for expression:-" + expressionGrp );
		}

	}
}
