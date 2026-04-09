package com.subex.rocps.automation.helpers.application.genericHelpers;

import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.config.PropertyReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

public class DataVerificationHelper
{

	PSGenericHelper genericHelperObj = new PSGenericHelper();

	/*
	 * Method for validating results in search screens
	 * 
	 * @param: columheaderId - common headercolumn id across grid columns
	 * 
	 * @param: columnHeaderKey - cell name with column values
	 * 
	 * @param: rowData - cell with result data
	 * 
	 * @param: gridID - Search screen/Popup Screen Grid ID
	 */
	public void validateData( String columnHeaderId, String columnHeaderKey, String gridID, String rowsData ) throws Exception
	{

		PSStringUtils delimeterObj = new PSStringUtils();
		String[] excelColHeaderNames = delimeterObj.stringSplitFirstLevel( columnHeaderKey );
		String[] totalRows = delimeterObj.stringSplitFirstLevel( rowsData );

		List<String> UIColumns = genericHelperObj.getGridColumns( columnHeaderId );

		for ( int index = 0; index < totalRows.length; index++ )
		{
			String rowValue = totalRows[index];
			validateRowData( excelColHeaderNames, UIColumns, gridID, rowValue, index + 1, false );
		}
	}

	/*
	 * Method for validating results in search screens
	 * 
	 * @param: columheaderId - grid headercolumn id
	 * 
	 * @param: agcConfigMap - map storing rowname and data
	 * 
	 * @param: columnHeaderKey - cell name with column values
	 * 
	 * @param: rowKeys - cell name which gives row names for fetching the data
	 * from the map
	 * 
	 * @param: gridID - Search screen/Popup Screen Grid ID
	 */
	public void validateData( String columnHeaderId, Map<String, String> agcConfigMap, String gridID, String columnHeaderKey, String rowKeys ) throws Exception
	{

		PSStringUtils delimeterObj = new PSStringUtils();
		String[] excelColHeaderNames = delimeterObj.stringSplitFirstLevel( columnHeaderKey );
		String[] rows = delimeterObj.stringSplitFirstLevel( rowKeys );

		List<String> UIColumns = genericHelperObj.getGridColumns( columnHeaderId );

		for ( int index = 0; index < rows.length; index++ )
		{
			String rowValue = agcConfigMap.get( rows[index] );
			validateRowData( excelColHeaderNames, UIColumns, gridID, rowValue, index + 1, false );
		}
	}

	/*
	 * Method for validating results in search screens with Wrapper
	 * 
	 * @param: columheaderId - grid headercolumn id
	 * 
	 * @param: agcConfigMap - map storing rowname and data
	 * 
	 * @param: columnHeaderKey - cell name with column values
	 * 
	 * @param: rowKeys - cell name which gives row names for fetching the data
	 * from the map
	 * 
	 * @param: gridID - Search screen/Popup Screen Grid ID
	 * 
	 * @param: wrapperID - Div or Table id within which the grid is present.
	 */
	public void validateData( String wrapperID, String columnHeaderId, Map<String, String> agcConfigMap, String gridID, String columnHeaderKey, String rowKeys ) throws Exception
	{

		PSStringUtils delimeterObj = new PSStringUtils();
		String[] excelColHeaderNames = delimeterObj.stringSplitFirstLevel( columnHeaderKey );
		String[] rows = delimeterObj.stringSplitFirstLevel( rowKeys );

		List<String> UIColumns = genericHelperObj.getGridColumns( columnHeaderId, wrapperID );

		for ( int index = 0; index < rows.length; index++ )
		{
			String rowValue = agcConfigMap.get( rows[index] );
			validateRowData( wrapperID, excelColHeaderNames, UIColumns, gridID, rowValue, index + 1, false );
		}
	}

	/*
	 * Method for validating results in result screens
	 * 
	 * @param: columheaderId - common headercolumn id across grid columns
	 * 
	 * @param: columnHeaderKey - cell name with column values
	 * 
	 * @param: rowData - cell with result data
	 * 
	 * @param: parentRowVerificationFlag : If true will check for parent row in
	 * result screens
	 */
	public void validateDataInResultScreen( String columnHeaderId, String columnHeaderKey, String rowsData, boolean parentRowVerificationFlag ) throws Exception
	{

		PSStringUtils delimeterObj = new PSStringUtils();
		String[] excelColHeaderNames = delimeterObj.stringSplitFirstLevel( columnHeaderKey );
		String[] totalRows = delimeterObj.stringSplitFirstLevel( rowsData );

		List<String> UIColumns = genericHelperObj.getGridColumns( columnHeaderId );

		for ( int index = 0; index < totalRows.length; index++ )
		{
			String rowValue = totalRows[index];
			validateRowData( excelColHeaderNames, UIColumns, "SearchGrid", rowValue, index + 1, parentRowVerificationFlag );
		}
	}

	/*
	 * Method for validating results in result screens
	 * 
	 * @param: columheaderId - grid headercolumn id
	 * 
	 * @param: agcConfigMap - map storing rowname and data
	 * 
	 * @param: columnHeaderKey - cell name with column values
	 * 
	 * @param: rowKeys - cell name which gives row names for fetching the data
	 * from the map
	 * 
	 * @param: parentRowVerificationFlag : If true will check for parent row in
	 * result screens
	 */
	public void validateDataInResultScreen( String columnHeaderId, Map<String, String> agcConfigMap, String columnHeaderKey, String rowKeys, boolean parentRowVerificationFlag ) throws Exception
	{

		PSStringUtils delimeterObj = new PSStringUtils();
		String[] excelColHeaderNames = delimeterObj.stringSplitFirstLevel( columnHeaderKey );
		String[] rows = delimeterObj.stringSplitFirstLevel( rowKeys );

		List<String> UIColumns = genericHelperObj.getGridColumns( columnHeaderId );

		for ( int index = 0; index < rows.length; index++ )
		{
			String rowValue = agcConfigMap.get( rows[index] );
			validateRowData( excelColHeaderNames, UIColumns, "SearchGrid", rowValue, index + 1, parentRowVerificationFlag );
		}
	}

	/*
	 * Method for comparing the UI data and excel data
	 * 
	 * @param: parentRowVerificationFlag : If true will skip first row in the
	 * result screen
	 */
	private void validateRowData( String[] excelColNames, List<String> uIColumns, String gridID, String rowValue, int rowNum, boolean parentRowVerificationFlag ) throws Exception
	{

		Log4jHelper.logInfo("UI Columns from grid [" + gridID + "]: " + uIColumns);

		StringBuilder actualValue = new StringBuilder();
		for ( int col = 0; col < excelColNames.length; col++ )
		{
			String expectedHeader = excelColNames[col];
			int colNum = uIColumns.indexOf( excelColNames[col] );
			 Log4jHelper.logInfo("Looking for header [" + expectedHeader + "], found at index: " + colNum);

		        if (colNum == -1) {
		            throw new RuntimeException(
		                "Column header [" + expectedHeader + "] not found in UI columns: " + uIColumns
		            );
		        }

			String colCellValues;
			if ( parentRowVerificationFlag )
				colCellValues = GridHelper.getCellValue( gridID, rowNum + 1, colNum + 1 );
			else
				colCellValues = GridHelper.getCellValue( gridID, rowNum, colNum + 1 );

			// Trim the cell value to remove leading/trailing whitespace
			colCellValues = (colCellValues != null) ? colCellValues.trim() : "";

			actualValue.append( colCellValues ).append( col == excelColNames.length - 1 ? "" : ";" );
		}

		// Normalize date formats in the excel expected value to match the UI format (yyyy-MM-dd HH:mm:ss)
		String normalizedRowValue = normalizeExcelDateFormats( rowValue );

		Log4jHelper.logInfo( "Actual value : " + actualValue );
		Log4jHelper.logInfo( "Excel value : " + normalizedRowValue );
		Assert.assertEquals( actualValue.toString(), normalizedRowValue, "Values are not matching " );
	}

	/*
	 * Method for comparing the UI data and excel data
	 * 
	 * @param: parentRowVerificationFlag : If true will skip first row in the
	 * result screen
	 * 
	 * @param: wrapperID - Div or Table id within which the grid is present.
	 */
	private void validateRowData( String wrapperID, String[] excelColNames, List<String> uIColumns, String gridID, String rowValue, int rowNum, boolean parentRowVerificationFlag ) throws Exception
	{

		StringBuilder actualValue = new StringBuilder();
		for ( int col = 0; col < excelColNames.length; col++ )
		{

			int colNum = uIColumns.indexOf( excelColNames[col] );
			String colCellValues;
			if ( parentRowVerificationFlag )
				colCellValues = GridHelper.getCellValue( wrapperID, gridID, rowNum + 1, colNum + 1 );
			else
				colCellValues = GridHelper.getCellValue( wrapperID, gridID, rowNum, colNum + 1 );

			// Trim the cell value to remove leading/trailing whitespace
			colCellValues = (colCellValues != null) ? colCellValues.trim() : "";

			actualValue.append( colCellValues ).append( col == excelColNames.length - 1 ? "" : ";" );
		}

		// Normalize date formats in the excel expected value to match the UI format (yyyy-MM-dd HH:mm:ss)
		String normalizedRowValue = normalizeExcelDateFormats( rowValue );

		Log4jHelper.logInfo( "Actual value : " + actualValue );
		Log4jHelper.logInfo( "Excel value : " + normalizedRowValue );
		Assert.assertEquals( actualValue.toString(), normalizedRowValue, "Values are not matching " );
	}

	/*
	 * * Method for validating results in result screens without sorting
		 * 
		 * @param: columheaderId - grid headercolumn id
		 * 
		 * @param: agcConfigMap - map storing rowname and data
		 * 
		 * @param: columnHeaderKey - cell name with column values
		 * 
		 * @param: rowKeys - cell name which gives row names for fetching the data
		 * from the map
	 * @param: skipParentRowFlag : If true will skip first row in the grid
	 */
	public void validateDataWithoutSorting( String columnHeaderId, Map<String, String> agcConfigMap, String columnHeaderKey, String rowKeys, Boolean skipParentRowFlag ) throws Exception
	{

		PSStringUtils delimeterObj = new PSStringUtils();
		String[] excelColHeaderNames = delimeterObj.stringSplitFirstLevel( columnHeaderKey );
		String[] rows = delimeterObj.stringSplitFirstLevel( rowKeys );
		List<String> UIColumns = genericHelperObj.getGridColumns( columnHeaderId );
		List<String> UIRows = getRowsData( excelColHeaderNames, UIColumns, "SearchGrid" );
		if ( skipParentRowFlag )
		{
			Log4jHelper.logInfo( "Size of  Rows in UI : " + ( UIRows.size() - 1 ) + "\nSize of Rows for validation in Excle " + rows.length );
			UIRows.remove( 0 );
		}
		else
			Log4jHelper.logInfo( "Size of  Rows in UI : " + UIRows.size() + "\nSize of Rows for validation in Excle " + rows.length );
		Log4jHelper.logInfo( "List Row value : " );
		UIRows.forEach( System.out::println );
		for ( int index = 0; index < rows.length; index++ )
		{
			String rowValue = agcConfigMap.get( rows[index] );
			assertTrue( UIRows.contains( rowValue ), " Row Value:-" + rowValue + " is not found on grid" );
			Log4jHelper.logInfo( "Excel value : " + rowValue + " is found on Grid" );
		}

	}

	/*
	 * * Method for validating results in result screens without sorting
		 * 
		 * @param: gridId- gridId
		 * @param: columheaderId - grid headercolumn id
		 * 
		 * @param: agcConfigMap - map storing rowname and data
		 * 
		 * @param: columnHeaderKey - cell name with column values
		 * 
		 * @param: rowKeys - cell name which gives row names for fetching the data
		 * from the map
	 * @param: skipParentRowFlag : If true will skip first row in the grid
	 */
	public void validateDataWithoutSorting( String gridId, String columnHeaderId, Map<String, String> agcConfigMap, String columnHeaderKey, String rowKeys, Boolean skipParentRowFlag ) throws Exception
	{

		PSStringUtils delimeterObj = new PSStringUtils();
		String[] excelColHeaderNames = delimeterObj.stringSplitFirstLevel( columnHeaderKey );
		String[] rows = delimeterObj.stringSplitFirstLevel( rowKeys );
		gridId = GenericHelper.getORProperty( gridId );
		List<String> UIColumns = genericHelperObj.getGridColumns( columnHeaderId, gridId );
		List<String> UIRows = getRowsData( excelColHeaderNames, UIColumns, gridId );
		if ( skipParentRowFlag )
		{
			Log4jHelper.logInfo( "Size of  Rows in UI : " + ( UIRows.size() - 1 ) + "\nSize of Rows for validation in Excle " + rows.length );
			UIRows.remove( 0 );
		}
		else
			Log4jHelper.logInfo( "Size of  Rows in UI : " + UIRows.size() + "\nSize of Rows for validation in Excle " + rows.length );
		Log4jHelper.logInfo( "List Row value : " );
		UIRows.forEach( System.out::println );
		for ( int index = 0; index < rows.length; index++ )
		{
			String rowValue = agcConfigMap.get( rows[index] );
			assertTrue( UIRows.contains( rowValue ), " Row Value:-" + rowValue + " is not found on grid" );
			Log4jHelper.logInfo( "Excel value : " + rowValue + " is found on Grid" );
		}

	}

	/*
		 * Method for get the UI  rows data 
		 * 
		 * 
		 * result screen
		 */
	private List<String> getRowsData( String[] excelColNames, List<String> uIColumns, String gridID ) throws Exception
	{

		List<String> listOfRows = new ArrayList<String>();
		int rowCount = GridHelper.getRowCount( gridID );
		for ( int rowNum = 1; rowNum <= rowCount; rowNum++ )
		{
			StringBuilder actualValue = new StringBuilder();
			String colCellValues = "";
			for ( int col = 0; col < excelColNames.length; col++ )
			{
				int colNum = uIColumns.indexOf( excelColNames[col] );
				colCellValues = GridHelper.getCellValue( gridID, rowNum, colNum + 1 );
				actualValue.append( colCellValues ).append( col == excelColNames.length - 1 ? "" : ";" );

			}
			listOfRows.add( actualValue.toString() );
		}
		return listOfRows;

	}

	/*
	 * Normalizes date tokens in an expected (Excel) row value string to the
	 * canonical UI date format (yyyy-MM-dd HH:mm:ss).
	 *
	 * Strategy:
	 *  1. First candidate format is built from configProp.getDateFormat()+timeFormat
	 *     (matches how ExcelReaderHelper formats date-typed cells).
	 *  2. All other known date formats used across the project are added as fallbacks
	 *     (handles text-typed Excel cells that store dates as plain strings in a
	 *     different format, e.g. MM/dd/yyyy HH:mm:ss).
	 *  3. Each semicolon-delimited token is tried against each candidate format in order.
	 *     First successful parse wins; the token is then reformatted to yyyy-MM-dd HH:mm:ss.
	 *  4. Tokens that do not match any date format are left unchanged.
	 *
	 * This approach is resilient to dateFormat changes in psconfig.properties and
	 * also handles the case where Excel cells contain plain-text date strings.
	 */
	private String normalizeExcelDateFormats( String value ) throws Exception
	{
		if ( value == null )
			return null;

		final String UI_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

		// Build the list of candidate input formats to try, in priority order
		List<String> candidateFormats = new ArrayList<String>();
		try
		{
			PropertyReader configProp = AcceptanceTest.configProp;
			if ( configProp != null )
			{
				String dateFmt = configProp.getDateFormat();
				String timeFmt = configProp.getTimeFormat();
				if ( dateFmt != null && !dateFmt.trim().isEmpty() )
				{
					String combined = ( timeFmt != null && !timeFmt.trim().isEmpty() )
							? dateFmt.trim() + " " + timeFmt.trim()
							: dateFmt.trim() + " HH:mm:ss";
					candidateFormats.add( combined );
				}
			}
		}
		catch ( Exception e )
		{
			Log4jHelper.logInfo( "normalizeExcelDateFormats: could not read config, continuing with fallback formats. " + e.getMessage() );
		}

		// Add all other known formats used across the project as fallbacks.
		// Ensures text-typed Excel cells with any of these formats are normalized correctly.
		String[] knownFormats = {
				"MM/dd/yyyy HH:mm:ss",
				"dd/MM/yyyy HH:mm:ss",
				"yyyy-MM-dd HH:mm:ss",
				"MM/dd/yyyy",
				"dd/MM/yyyy",
				"yyyy-MM-dd"
		};
		for ( String fmt : knownFormats )
		{
			if ( !candidateFormats.contains( fmt ) )
				candidateFormats.add( fmt );
		}

		SimpleDateFormat uiSdf = new SimpleDateFormat( UI_DATE_FORMAT );
		uiSdf.setLenient( false );

		// Process each semicolon-delimited token independently
		String[] tokens = value.split( ";", -1 );
		StringBuilder result = new StringBuilder();
		for ( int i = 0; i < tokens.length; i++ )
		{
			String token = tokens[i].trim();
			String normalized = token;

			for ( String fmt : candidateFormats )
			{
				// Skip trying to reformat if the token is already in the UI format
				if ( UI_DATE_FORMAT.equals( fmt ) )
					continue;

				try
				{
					SimpleDateFormat candidateSdf = new SimpleDateFormat( fmt );
					candidateSdf.setLenient( false );
					Date parsed = candidateSdf.parse( token );
					// Verify full token was consumed (no trailing garbage)
					if ( candidateSdf.format( parsed ).equals( token ) )
					{
						normalized = uiSdf.format( parsed );
						break;
					}
				}
				catch ( ParseException e )
				{
					// This format did not match — try the next one
				}
			}
			result.append( normalized );
			if ( i < tokens.length - 1 )
				result.append( ";" );
		}
		return result.toString();
	}

}
