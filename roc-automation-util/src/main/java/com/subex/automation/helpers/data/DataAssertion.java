package com.subex.automation.helpers.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class DataAssertion extends AcceptanceTest {
	
	/*
	 * This method is used to validate the results.
	 * Here data keyed in excel is compared against the data fetched from GUI .
	 * headerValuesColName @ Send the column name in which column numbers are stored.
	 * keyValColName @ Column name corresponding to key value in excel sheet should be passed
	 * rowNumColName @ Column name corresponding to row values stored in excel should be passed.
	 * keyVal @ Key value corresponding to test case should be passed.
	 */
	private void DataEvaluation( String excelPath, String fileName, String sheetName, String headerValuesColName, String keyValColName, HashMap<Integer, ArrayList<String>> map, String rowNumColName, String keyVal ) throws Exception
	{
		try {
			ExcelReader excelReader = new ExcelReader();
			int headerColNum = excelReader.getColumnNumber( excelPath, fileName, sheetName, headerValuesColName );
			int keyValColNum = excelReader.getColumnNumber( excelPath, fileName, sheetName, keyValColName );
			int rowNum = excelReader.getColumnNumber( excelPath, fileName, sheetName, rowNumColName );
	
			for ( int row = 1; row <= map.size(); row++ )
			{
				if ( keyVal.equals( map.get( row ).get( keyValColNum - 1 ) ) )
				{
	
					Map<Integer, String> actualCellValues = new HashMap<Integer, String>();
					Map<Integer, String> expectedCellValues = new HashMap<Integer, String>();
					String headerVal = map.get( row ).get( headerColNum - 1 );
					String[] value = headerVal.split( ",", -1 );
					StringBuilder var = new StringBuilder();
					for ( int colNum = 0; colNum < value.length; colNum++ )
					{
						int rowNum1 = Integer.parseInt( map.get( row ).get( rowNum - 1 ) );
						int colNum1 = Integer.parseInt( value[colNum] );
						String val1 = GenericHelper.getValueForAssertion(rowNum1-1, colNum1-1 );
						var.append( val1 ).append( colNum == value.length-1 ? "" : "," );
						
					}
					actualCellValues.put( row, var.toString() );
					Log4jHelper.logInfo("Actual Value : " + actualCellValues );
	
					int columns = keyValColNum + value.length;
					StringBuilder var1 = new StringBuilder();
					for ( int colNum2 = keyValColNum; colNum2 < columns; colNum2++ )
					{
	
						String val2= map.get( row ).get( colNum2 ).trim();
						var1.append( val2 ).append( colNum2 == columns-1 ? "" : "," );	
					}
					expectedCellValues.put( row, var1.toString() );
					Log4jHelper.logInfo("Expected value : " + expectedCellValues );
	
					assertEquals( actualCellValues, expectedCellValues, "Data is not proper in row num = "+ row );
				}
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void DataEvaluation( String excelPath, String fileName, String sheetName, String headerValuesColName, HashMap<Integer, ArrayList<String>> map ) throws Exception
	{
		try {
			ExcelReader excelReader = new ExcelReader();
			int headerColNum = excelReader.getColumnNumber( excelPath, fileName, sheetName, headerValuesColName );
			int keyValColNum = excelReader.getColumnNumber( excelPath, fileName, sheetName, "Key" );
			int rowNum = excelReader.getColumnNumber( excelPath, fileName, sheetName, "RowNumber" );
	
			for ( int row = 1; row <= map.size(); row++ )
			{
				if ( "1".equals( map.get( row ).get( keyValColNum - 1 ) ) )
				{
					Map<Integer, String> actualCellValues = new HashMap<Integer, String>();
					Map<Integer, String> expectedCellValues = new HashMap<Integer, String>();
					String headerVal = map.get( row ).get( headerColNum - 1 );
					String[] value = headerVal.split( ",", -1 );
					StringBuilder var = new StringBuilder();
					
					for ( int colNum = 0; colNum < value.length; colNum++ )
					{
						int rowNum1 = Integer.parseInt( map.get( row ).get( rowNum - 1 ) );
						int colNum1 = Integer.parseInt( value[colNum] );
						String val1 = GenericHelper.getValueForAssertion(rowNum1-1, colNum1-1 ).trim();
						var.append( val1 ).append( colNum == value.length-1 ? "" : "," );
					}
					
					actualCellValues.put( row, var.toString() );
					Log4jHelper.logInfo("Actual Value : " + actualCellValues );
	
					int columns = keyValColNum + value.length;
					StringBuilder var1 = new StringBuilder();
					for ( int colNum2 = keyValColNum; colNum2 < columns; colNum2++ )
					{
						String val2= map.get( row ).get( colNum2 ).trim();
						var1.append( val2 ).append( colNum2 == columns-1 ? "" : "," );	
					}
					
					expectedCellValues.put( row, var1.toString() );
					Log4jHelper.logInfo("Expected value : " + expectedCellValues );
					assertEquals( actualCellValues, expectedCellValues, "Data is not proper in row num = " + row );
				}
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}

	}
	
	public void testDataAssertion(String excelPath, String fileName, String sheetName, String TCName, String headerValuesColName, String keyValColName, HashMap<Integer, ArrayList<String>> map, String rowNumColName, String keyValue ) throws Exception {
		try {
			excelPath = GenericHelper.getPath(automationOS, excelPath);
			fileName = GenericHelper.getPath(automationOS, fileName);
			String keyVal = map.get( 1 ).get( 0 );
			
			if ( !keyVal.equals( "" ) )
			{
				DataEvaluation( excelPath, fileName, sheetName, headerValuesColName, keyValColName, map, rowNumColName, keyValue );
				Log4jHelper.logInfo( "Result Evaluated Successfully" );
			}
			else
			{
				boolean result = GridHelper.hasNoResult("SearchGrid");
				if ( result ) {
					Log4jHelper.logInfo( "No records found" );
				}
			}
		}
		catch(Exception e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void testDataAssertion(String excelPath, String fileName, String sheetName, String TCName ) throws Exception {
		try {
			excelPath = GenericHelper.getDataPath(excelPath, fileName);
			excelPath = GenericHelper.getPath(automationOS, excelPath);
			fileName = GenericHelper.getPath(automationOS, fileName);
			ExcelReader excelReader = new ExcelReader();
			HashMap<Integer, ArrayList<String>> map = excelReader.readDataByTestCase( excelPath, fileName, sheetName, TCName );
			
			if (map != null) {
				String keyVal = map.get( 1 ).get( 0 );
				if ( !keyVal.equals( "" ) ) {
					DataEvaluation( excelPath, fileName, sheetName, "HeaderColumnNumber", map );
					Log4jHelper.logInfo( "Result Evaluated Successfully" );
				}
				else {
					boolean result = GridHelper.hasNoResult("SearchGrid");
					if ( result ) {
						Log4jHelper.logInfo( "No records found" );
					}
				}
			}
			else {
				FailureHelper.failTest("There is no data in the excel file.");
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}