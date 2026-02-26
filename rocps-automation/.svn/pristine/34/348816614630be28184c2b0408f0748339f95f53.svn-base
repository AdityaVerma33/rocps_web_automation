package com.subex.rocps.automation.helpers.application.genericHelpers;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.customexception.ScreenTitleException;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.EntitySearchHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.componentHelpers.TabElementHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class PSDataComponentHelper extends PSAcceptanceTest
{
	static PSGenericHelper genericObj = new PSGenericHelper();
	String strVal;
	private static String gridId = "SearchGrid";
	private static String gridWrapper = "popupWindow";
	PSStringUtils psStringUtils = new PSStringUtils();

	public void typeInTextBoxOptional( String idOrXpath, String strName, String[] strArr, int index ) throws Exception
	{
		strVal = strName;
		if ( !strVal.isEmpty() )
			TextBoxHelper.type( idOrXpath, strArr[index] );
	}

	public void typeInTextBoxOptionalWithWrapper( String txtBoxWrapper, String idOrXpath, String strName, String[] strArr, int index ) throws Exception
	{
		strVal = strName;
		if ( !strVal.isEmpty() )
			TextBoxHelper.type( txtBoxWrapper, idOrXpath, strArr[index] );
	}

	public void comboBoxSelectOptional( String idOrXpath, String strName, String[] strArr, int index ) throws Exception
	{
		strVal = strName;
		if ( !strVal.isEmpty() )
			ComboBoxHelper.select( idOrXpath, strArr[index] );
	}

	public void comboBoxSelectOptional( String comboBoxWrapper, String idOrXpath, String strName, String[] strArr, int index ) throws Exception
	{
		strVal = strName;
		if ( !strVal.isEmpty() )
			ComboBoxHelper.select( comboBoxWrapper, idOrXpath, strArr[index] );
	}

	public static void select( String idOrXpath, String value ) throws Exception
	{
		idOrXpath = GenericHelper.getORProperty( idOrXpath );
		String arrowElementXpath = GenericHelper.getORProperty( "PS_Detail_rerate_comboArrowElement" ).replace( "comboid", idOrXpath );

		WebElement arrowElement = ElementHelper.getElement( arrowElementXpath );
		MouseHelper.click( arrowElement );
		String valueSelectXpath = GenericHelper.getORProperty( "PS_Detail_rerate_valueSelectXpath" ).replace( "value", value );
		valueSelectXpath = valueSelectXpath.replace( "comboid", idOrXpath );

		WebElement dateSelect = ElementHelper.getElement( valueSelectXpath );

		Actions actions = new Actions( driver );
		actions.moveToElement( dateSelect ).click().build().perform();

	}

	// Method:Get the value checkbox with src attribute :
	public String getCheckBoxText( String gridId, String fieldNm, String columnHeaderChckBx ) throws Exception
	{
		gridId = GenericHelper.getORProperty( gridId );
		int columnNoOfCheckBoxChecked = GridHelper.getColumnNumber( gridId, columnHeaderChckBx );
		String xpath = "//*[@id='gridId']//div[text()='Field name']//parent::td//parent::tr//td[" + columnNoOfCheckBoxChecked + " ]//div[@id='grid_check_editor']/img";
		xpath = xpath.replace( "gridId", gridId );
		xpath = xpath.replace( "Field name", fieldNm );
		String elementTxt = ElementHelper.getAttribute( xpath, "src" );
		return elementTxt;

	}

	/*
	 * This method is for entitycombo textbox selection
	 * 
	 * @param entityColHeader : unique col header in popup screen.
	 */
	public static void psSelectUsingGridFilterTextBox( String iconIdOrXpath, String entityColHeader, String txtBoxIdOrXpath, String value, String valueColumnHeader ) throws Exception
	{
		try
		{
			if ( ValidationHelper.isNotEmpty( value ) )
			{
				iconIdOrXpath = GenericHelper.getORProperty( iconIdOrXpath );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				EntityComboHelper.clickEntityIcon( iconIdOrXpath );
				selectUsingGridFilterTextBox( entityColHeader, txtBoxIdOrXpath, value, valueColumnHeader );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is for entitycombo textbox selection with wrappper
	 * 
	 * @param entityColHeader : unique col header in popup screen.
	 */
	public static void selectUsingGridFilterTextBox( String iconWrapperId, String iconIdOrXpath, String entityColHeader, String txtBoxIdOrXpath, String value, String valueColumnHeader ) throws Exception
	{
		try
		{
			if ( ValidationHelper.isNotEmpty( value ) )
			{
				iconWrapperId = GenericHelper.getORProperty( iconWrapperId );
				iconIdOrXpath = GenericHelper.getORProperty( iconIdOrXpath );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				EntityComboHelper.clickEntityIcon( iconWrapperId, iconIdOrXpath );
				selectUsingGridFilterTextBox( entityColHeader, txtBoxIdOrXpath, value, valueColumnHeader );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public static void selectUsingGridFilterTextBox( String entityColHeader, String txtBoxIdOrXpath, String value, String valueColumnHeader ) throws Exception
	{
		try
		{
			if ( ValidationHelper.isNotEmpty( value ) )
			{
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				// assertTrue(LabelHelper.isTitlePresent(entityScreenTitle), "Entity Search '" +
				// entityScreenTitle + "' did not appear.");
				assertTrue( genericObj.waitforPopupHeaderElement( entityColHeader ), "Entity Search  did not appear." );
				if ( ButtonHelper.isPresent( "ClearButton" ) )
				{
					ButtonHelper.click( "ClearButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
				}
				int row = SearchGridHelper.gridFilterSearchWithTextBox( txtBoxIdOrXpath, value, valueColumnHeader );
				if ( row > 0 )
				{
					GridHelper.clickRow( gridWrapper, gridId, value, valueColumnHeader );
					ButtonHelper.click( "OKButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
				}
				else
					FailureHelper.failTest( "Row with value '" + value + "' not found in Entity Search screen" );
			}
		}
		catch ( AssertionError e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/* Method to check combo box value */
	public void checkComboBoxDisabled( String comboId, String comboValue ) throws Exception
	{
		assertTrue( ComboBoxHelper.isDisabled( comboId ), "The given combobox  should be disabled" );
		if ( ValidationHelper.isNotEmpty( comboValue ) )
			assertEquals( ComboBoxHelper.getValue( comboId ), comboValue, "Combobox value should be match with '" + ComboBoxHelper.getValue( comboId ) + "' for validation" );
	}

	/* Method to check text box disabled and match the value */
	public void checkTextBoxDisabled( String textId, String textValue ) throws Exception
	{
		assertTrue( TextBoxHelper.isDisabled( textId ), "The given textbox  should be disabled" );
		if ( ValidationHelper.isNotEmpty( textValue ) )
			assertEquals( TextBoxHelper.getValue( textId ), textValue, "Textbox value should be match with '" + TextBoxHelper.getValue( textId ) + "' for validation" );
	}

	/* Method to modify combo box value */
	public void modifyComboBox( String comboId, String comboValue ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( comboValue ) && !ComboBoxHelper.getValue( comboId ).equals( comboValue ) )
			ComboBoxHelper.select( comboId, comboValue );

	}

	/* Method to modify text box value */
	public void modifyTextBox( String textId, String textValue ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( textValue ) && !TextBoxHelper.getValue( textId ).equals( textValue ) )
			TextBoxHelper.type( textId, textValue );

	}

	/* Method to modify textarea box value */
	public void modifyTextAreaBox( String textareaId, String textareaValue ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( textareaValue ) && !TextAreaHelper.getValue( textareaId ).equals( textareaValue ) )
			TextAreaHelper.type( textareaId, textareaValue );
	}

	/* Method to get the list of testcase map */
	public List<Map<String, String>> getListOfTestCaseMap( String path, String workbookname, String sheetName, String testcaseNm ) throws Exception
	{
		try
		{
			ExcelReader excelData = new ExcelReader();
			Map<String, ArrayList<String>> mapExcel = null;
			ExcelHolder excelHolderObj = null;
			List<Map<String, String>> listmap = new ArrayList<Map<String, String>>();
			Map<String, String> map = null;
			mapExcel = excelData.readDataByColumn( path, workbookname, sheetName, testcaseNm );
			excelHolderObj = new ExcelHolder( mapExcel );
			int colSize = excelHolderObj.totalColumns();
			for ( int index = 0; index < colSize; index++ )
			{
				map = excelHolderObj.dataMap( index );
				listmap.add( map );
			}
			return listmap;
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/* Method to get map for Testcase */
	public Map<String, String> getTestCaseMap( String path, String workbookname, String sheetName, String testcaseNm ) throws Exception
	{
		try
		{
			ExcelReader excelData = new ExcelReader();
			Map<String, ArrayList<String>> mapExcel = null;
			ExcelHolder excelHolderObj = null;
			Map<String, String> map = null;
			mapExcel = excelData.readDataByColumn( path, workbookname, sheetName, testcaseNm );
			excelHolderObj = new ExcelHolder( mapExcel );
			int colSize = excelHolderObj.totalColumns();
			map = excelHolderObj.dataMap( 0 );
			return map;
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/* Method to check is data present in grid */
	public boolean isDataPresentInGrid( String gridId, Map<String, ArrayList<String>> mapOfGrid, String columnHeader, String columnValue ) throws Exception
	{
		gridId = GenericHelper.getORProperty( gridId );
		boolean isDataPresentInGrid = mapOfGrid.entrySet().stream().filter( x -> x.getKey().equals( columnHeader ) ).anyMatch( x -> x.getValue().contains( columnValue ) );
		return isDataPresentInGrid;
	}

	/* Method get the key values from search screen of grid */
	public Map<String, ArrayList<String>> getGridColumnValues( String gridId, String columnHeaderId, List<String> listColumn ) throws Exception
	{
		Map<String, ArrayList<String>> hmap = new HashMap<String, ArrayList<String>>();
		String key;
		List<String> uIColumns = genericObj.getGridColumns( columnHeaderId );

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

	/* Method get the values from search screen of grid */
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

	// Method:verify The gridTxtValue
	public void verifyGridCellValue( String gridId, int rowNum, String columnHeader, String gridExpTxtVal ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( gridExpTxtVal ) )
		{
			String gridActTxtVal = GridHelper.getCellValue( gridId, rowNum, columnHeader );
			assertEquals( gridActTxtVal, gridExpTxtVal, " Grid cell value is not matched as expected " + gridExpTxtVal + " but found " + gridActTxtVal );
		}
	}

	// Method:verify The gridChckBoxVal
	public void verifyGridCheckBox( String gridId, String fieldNm, String colHeaderValFlg, String columnHeaderChckBx ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( colHeaderValFlg ) && ValidationHelper.isTrue( colHeaderValFlg ) )
			assertTrue( getCheckBoxText( gridId, fieldNm, columnHeaderChckBx ).contains( "cellchecked" ), " The given checkboxvalue for this is not matched as expected 'cellchecked'" );

		if ( ValidationHelper.isNotEmpty( colHeaderValFlg ) && ValidationHelper.isFalse( colHeaderValFlg ) )
			assertTrue( getCheckBoxText( gridId, fieldNm, columnHeaderChckBx ).contains( "cellunchecked" ), " The given checkboxvalue for this is not matched as expected 'cellunchecked'" );
	}

	public void selectComboBoxVal( String idOrXpath, String value ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( value ) )
		{
			idOrXpath = GenericHelper.getORProperty( idOrXpath );
			String arrowElementXpath = GenericHelper.getORProperty( "PS_Detail_rerate_comboArrowElement" ).replace( "comboid", idOrXpath );

			WebElement arrowElement = ElementHelper.getElement( arrowElementXpath );
			MouseHelper.click( arrowElement );
			String valueSelectXpath = GenericHelper.getORProperty( "PS_Detail_rerate_valueSelectXpath" ).replace( "value", value );
			valueSelectXpath = valueSelectXpath.replace( "comboid", idOrXpath );

			WebElement selectValueElement = ElementHelper.getElement( valueSelectXpath );
			String typeInTxtBxXpath = GenericHelper.getORProperty( "PS_Detail_valueType_txtBx_SelectXpath" );
			typeInTxtBxXpath = typeInTxtBxXpath.replace( "comboid", idOrXpath );

			TextBoxHelper.type( typeInTxtBxXpath, value );

			Actions actions = new Actions( driver );
			actions.moveToElement( selectValueElement ).click().build().perform();
		}

	}

	public void waitForTaskCompletion( int row ) throws Exception
	{

		int maxTryCount = searchScreenWaitSec;
		int tryCount = 0;
		String actualValue = GridHelper.getCellValue( "SearchGrid", row, "Task Status" ).trim();
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		while ( true )
		{
			ButtonHelper.click( "SearchButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			actualValue = GridHelper.getCellValue( "SearchGrid", row, "Task Status" ).trim();
			if ( ( actualValue.equals( "Failed" ) || actualValue.equals( "Completed" ) ) || tryCount == maxTryCount )
				break;
			tryCount++;
			Log4jHelper.logInfo( "Try count for Task Completion: " + tryCount );
		}
		GenericHelper.waitForLoadmask();
		assertEquals( actualValue, "Completed", "Task status is not in completed status but found- " + actualValue );
	}

	public void waitForTaskCompletion( int row, String columnHeaderTaskStatus ) throws Exception
	{

		int maxTryCount = searchScreenWaitSec;
		int tryCount = 0;
		String actualValue = GridHelper.getCellValue( "SearchGrid", row, columnHeaderTaskStatus ).trim();
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		while ( true )
		{
			ButtonHelper.click( "SearchButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			actualValue = GridHelper.getCellValue( "SearchGrid", row, columnHeaderTaskStatus ).trim();
			if ( ( actualValue.equals( "Failed" ) || actualValue.equals( "Completed" ) ) || tryCount == maxTryCount )
				break;
			tryCount++;
			Log4jHelper.logInfo( "Try count for Task Completion: " + tryCount );
		}
		GenericHelper.waitForLoadmask();
		assertEquals( actualValue, "Completed", "Task status is not in completed status but found- " + actualValue );
	}

	/*Method is for update of   Schedule  details*/
	public void updateScheduleTimes( String frequencyMultiplier, String frequency, String nextSchedule, String[][] dayGroups ) throws Exception
	{
		try
		{
			ROCHelper rocHelper = new ROCHelper();
			ElementHelper.scrollToView( GenericHelper.getORProperty( "PS_Detail_repAndExtSch_schTime_panelXpath" ), false );
			modifyTextBox( "Scheduling_FrequencyMultiplier", frequencyMultiplier );
			modifyComboBox( "Scheduling_Frequency", frequency );
			if ( ValidationHelper.isNotEmpty( nextSchedule ) )
				modifyTextBox( "Scheduling_NextScheduled", nextSchedule );
			else
				CalendarHelper.setNow( "Scheduling_NextScheduled" );
			for ( int i = 0; i < 3; i++ )
			{
				try
				{
					ElementHelper.scrollToView( GenericHelper.getORProperty( "PS_Detail_repAndExtSch_schTime_panelXpath" ), false );
					rocHelper.addDayGroup( "DayGroup_Grid", dayGroups );
					break;
				}
				catch ( StaleElementReferenceException e )
				{
					Log4jHelper.logInfo( "\nThe Exception message at 'updateScheduleTimes'-:" + e.getMessage() );
				}
				catch ( MoveTargetOutOfBoundsException e )
				{
					Log4jHelper.logInfo( "\nThe Exception message at 'updateScheduleTimes'-:" + e.getMessage() );
				}
				finally
				{
					Log4jHelper.logInfo( "Retrying for updateScheduleTimes " + ( i + 1 ) );
					ElementHelper.scrollToView( GenericHelper.getORProperty( "PS_Detail_repAndExtSch_schTime_panelXpath" ), false );
					
				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/* Method to check is data present in grid with contains of
	 * @param: gridId,
	 * @param:expectedRowValue*/
	public boolean isDataPresentInGrid( String gridId, String expectedRowValue ) throws Exception
	{
		gridId = GenericHelper.getORProperty( gridId );
		boolean isDataPresentInGrid = false;
		ArrayList<String> rowValueInList = null;
		int rowCount = GridHelper.getRowCount( gridId );
		for ( int i = 0; i < rowCount; i++ )
		{
			rowValueInList = GridHelper.getRowValues( gridId, i + 1 );
			rowValueInList.remove( rowValueInList.size() - 1 );
			String currentRowValue = rowValueInList.toString();
			if ( currentRowValue.contains( expectedRowValue ) )
				return true;
		}
		return isDataPresentInGrid;
	}

	/* Method to select  Entity Combo value from Grid*/
	public void selectGridEntityComboSelection( String gridId, String entityComboID, int row, String gridEntityColHeaderName, String value, String popuptxtID, String popupColHeader ) throws Exception
	{
		GridHelper.updateGridEntityCombo( gridId, entityComboID, row, gridEntityColHeaderName, value );
		genericObj.waitforPopupHeaderElement( popupColHeader );
		PSSearchGridHelper.gridFilterSearchWithTextBox( popuptxtID, value, popupColHeader );
		GridHelper.clickRow( "SearchGrid", value, popupColHeader );
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		genericObj.waitforPopupHeaderElementToDisappear( popupColHeader );
	}

	/*
	 * Method : Selecting  tariff in timeline
	 * 
	 
	 */
	public void selectTariffs( String tariff, String tarifsDt, String timeLineName, String timeLineId, String tariffBoxTxtId, String tariffPopupSaveBtnId ) throws Exception
	{

		genericObj.timeLineNew( timeLineName, timeLineId );

		try
		{
			ElementHelper.isElementPresent( " //div[text()='Service  Tariff']" );
		}
		catch ( Exception e )
		{
			throw new ScreenTitleException( "Screen title is not found" );
		}
		String serviceTariffDateLocator = GenericHelper.getORProperty( "TextBox_DatePicker" ).replace( "idOrName", or.getProperty( tariffBoxTxtId ) );
		TextBoxHelper.type( serviceTariffDateLocator, tarifsDt );
		String tffScrnTitle = "Tariff Search";

		tariffSelection( tffScrnTitle, tariff );
		ButtonHelper.click( tariffPopupSaveBtnId );
		GenericHelper.waitForLoadmask();

	}

	/*
	 * Method : Selecting  tariff in popup search
	 * 
	 
	 */
	public void tariffSelection( String tffScrnTitle, String tariffsPerTariffTypeArr ) throws Exception
	{
		EntityComboHelper.clickEntityIcon( "PSPopUp_emrSvcTffEntSrchId" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		//PSSearchGridHelper.gridFilterSearchWithTextBox( "PSPopUp_emrSvcTffNameTxtId", tariffsPerTariffTypeArr, "Tariff Name" );
		SearchGridHelper.searchWithTextBox( "PSPopUp_emrSvcTffNameTxtId", tariffsPerTariffTypeArr, "Tariff Name" );
		NavigationHelper.navigateToAction( "Expand/Collapse" );
		if ( NavigationHelper.isActionPresent( "Expand All" ) )
			NavigationHelper.navigateToAction( "Expand All" );
		int row = GridHelper.getRowNumber( "SearchGrid", tariffsPerTariffTypeArr, "Tariff Name" );
		GridHelper.clickRow( "SearchGrid", row, "Tariff Name" );
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );

	}

	/*
	 * Method : Update Grid Combo Box
	 * 
	 
	 */
	public static void updateGridComboBox( String gridId, String comboId, int rowNum, String valueColumnHeader, String value ) throws Exception
	{
		try
		{
			if ( ValidationHelper.isNotEmpty( value ) )
			{
				String gridMenuClickXpath = "//div[@id='" + gridId + "']/div[2]/div/div[2]/div/div[3]/div/div[3]";
				genericObj.scrollforHeaderElement( gridId, valueColumnHeader );
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
					GenericHelper.waitForLoadmask( 1100 );
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

	/*
	 * Method : Update Grid Combo Box
	 * 
	 
	 */
	public static void updateGridComboBox( String gridId, String comboId, int rowNum, int columnNumber, String value ) throws Exception
	{
		try
		{
			if ( ValidationHelper.isNotEmpty( value ) )
			{
				String gridMenuClickXpath = "//div[@id='" + gridId + "']/div[2]/div/div[2]/div/div[3]/div/div[3]";
				GridHelper.clickRow( gridId, rowNum, columnNumber );
				if ( !ComboBoxHelper.isPresent( comboId ) )
					GridHelper.clickRow( gridId, rowNum, columnNumber );
				int tryCount = 0;
				int maxWaitCount = 5;
				int comboValueCount = 0;
				while ( true )
				{
					comboValueCount = ComboBoxHelper.getValuesCount( comboId );
					if ( comboValueCount > 1 || tryCount == maxWaitCount )
						break;
					GenericHelper.waitForLoadmask( 1100 );
					MouseHelper.click( gridMenuClickXpath );
					GridHelper.clickRow( gridId, rowNum, columnNumber );
					if ( !ComboBoxHelper.isPresent( comboId ) )
						GridHelper.clickRow( gridId, rowNum, columnNumber );
					tryCount++;
					Log4jHelper.logInfo( "combo value count" + comboValueCount + " TryCount" + tryCount );
				}
				ComboBoxHelper.select( gridId, comboId, rowNum, columnNumber, value );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*Select child of parent with the child Text Value*/
	public static void selectChildOfParent( String treeName, String parentText, String childText ) throws Exception
	{
		treeName = GenericHelper.getORProperty( treeName );
		String expandXpath = "//div[@id='cell-list-panel']//div[text()='Expand All']";
		ElementHelper.scrollToView( expandXpath, false );
		ElementHelper.waitForClickableElement( expandXpath, searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		MouseHelper.click( expandXpath );
		String childXpath = "//div[starts-with(@id,'TreeName') and contains(text(),'ParentText')]/ancestor::div[@role='treeitem'][1]//div[contains(text(),'ChildText')]";
		childXpath = childXpath.replace( "TreeName", treeName );
		childXpath = childXpath.replace( "ParentText", parentText );
		childXpath = childXpath.replace( "ChildText", childText );
		ElementHelper.scrollToView( childXpath, false );
		ElementHelper.waitForClickableElement( childXpath, searchScreenWaitSec );
		MouseHelper.click( childXpath );

	}

	/* Method to get RowNumber with contains of multiple cell value as expctedRow in grid 
	 * @param: gridId,
	 * @param:expectedRowValue*/
	public int getRowNumOfGridWithMultCellValue( String gridId, String expectedRowValue ) throws Exception
	{
		int rowNum = 0;
		gridId = GenericHelper.getORProperty( gridId );
		ArrayList<String> rowValueInList = null;
		int rowCount = GridHelper.getRowCount( gridId );
		for ( int i = 0; i < rowCount; i++ )
		{
			int row = i + 1;
			rowValueInList = GridHelper.getRowValues( gridId, row );
			rowValueInList.remove( rowValueInList.size() - 1 );
			String currentRowValue = rowValueInList.toString();
			if ( currentRowValue.contains( expectedRowValue ) )
				return row;
		}
		return rowNum;
	}

	/* Method to get RowNumber with contains of multiple cell value as expctedRow in grid 
	 * @param: gridId,
	 * @param:expectedRowValue should be with second level split*/
	public int getRowNumOfGrid( String gridId, String expectedRowValue ) throws Exception
	{
		int rowNum = 0;
		gridId = GenericHelper.getORProperty( gridId );
		ArrayList<String> rowValueInList = null;
		int rowCount = GridHelper.getRowCount( gridId );
		String rowValuesArr[] = psStringUtils.stringSplitSecondLevel( expectedRowValue );
		List<String> expectedList = new ArrayList<String>( Arrays.asList( rowValuesArr ) );
		for ( int i = 0; i < rowCount; i++ )
		{
			int row = i + 1;
			rowValueInList = GridHelper.getRowValues( gridId, row );
			rowValueInList.replaceAll( String::trim );
			rowValueInList.remove( rowValueInList.size() - 1 );
			if ( rowValueInList.containsAll( expectedList ) )
				return row;
		}
		return rowNum;
	}

	/*
	 * Method to check if tab name is selected or not
	 */
	public boolean isTabSelected( String TabName ) throws Exception
	{
		String xpath = "//div[text()='" + TabName + "']/parent::div/parent::div/parent::div";
		String ancestorClassAttr = ElementHelper.getAttribute( xpath, "class" );
		return ancestorClassAttr.contains("selected" );

	}
}
