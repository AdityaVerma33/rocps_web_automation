package com.subex.automation.helpers.file;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class ExcelReader extends AcceptanceTest {
	
	public boolean isFilePresent( String excelPath, String workBookName ) throws Exception {
		ExcelReaderHelper excelReader = null;
		
		try {
			String filename = excelPath + "//" + workBookName;
			excelReader = new ExcelReaderHelper(filename);
			excelReader.excelIntialize();
			XSSFWorkbook excelWorkbook = excelReader.getWorkBook();
			
			if (excelWorkbook != null)
				return true;
			else
				return false;
		}
		catch ( Exception e ) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			excelReader.closeExcel();
		}
	}
	
	public boolean isSheetPresent( String excelPath, String workBookName, String sheetName ) throws Exception {
		String filename = excelPath + "//" + workBookName;
		ExcelReaderHelper excelReader = new ExcelReaderHelper(filename);
		
		try {
			excelReader.excelIntialize(sheetName);
			XSSFSheet excelSheet = excelReader.getSheet();

			if (excelSheet != null)
				return true;
			else
				return false;
		}
		catch ( NullPointerException e ) {
			FailureHelper.setError("Sheet '" + sheetName + "' is not found");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch ( Exception e ) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			excelReader.closeExcel();
		}
	}
	
	public int getColumnNumber(String excelPath, String workBookName, String sheetName, String columnName ) throws Exception
	{
		String filename = excelPath + "//" + workBookName;
		ExcelReaderHelper excelReader = new ExcelReaderHelper(filename);
		
		try {
			excelReader.excelIntialize(sheetName);
			int colSize = excelReader.getLastColumnNum();
			
			for ( int colNum = 0; colNum < colSize; colNum++ ) {
				String colname = excelReader.getCelldata( 0, colNum );
				
				if ( colname.equals( columnName ) )
					return colNum;
			}
			
			return -1;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			excelReader.closeExcel();
		}
	}
	
	public int getDataCount( String excelPath, String workBookName, String sheetName, String tcName ) throws Exception {
		String filename = excelPath + "//" + workBookName;
		ExcelReaderHelper excelReader = new ExcelReaderHelper(filename);
		
		try {
			excelReader.excelIntialize(sheetName);
			
			int tcCol = excelReader.getTCColumnNumber(sheetName, tcName);
			if (tcCol == -1) {
				return 0;
			}
			else {
				int valueColCount = 0;
				String[] headerValues = {"ParameterName", "ParameterValue"};
				int[] colNums = excelReader.getHeaderColumnNumber(tcCol+1, headerValues);
				
				if ( colNums[0] > 0 )
					valueColCount = excelReader.getValueColumnCount(colNums[1]);
				else {
					valueColCount = excelReader.getValueRowCount(tcName);
				}
				
				return valueColCount;
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			excelReader.closeExcel();
		}
	}
	
	public HashMap<Integer, ArrayList<String>> readDataByTestCase( String excelPath, String workBookName, String sheetName ) throws Exception {
		String filename = excelPath + "\\" + workBookName;
		ExcelReaderHelper excelReader = new ExcelReaderHelper(filename);
		
		try {
			HashMap<Integer, ArrayList<String>> rowValues = new HashMap<Integer, ArrayList<String>>();
			ArrayList<String> colValues = null;
			excelReader.excelIntialize(sheetName);
			int rows = excelReader.getLastRowNum();
			
			if ( rows == 0 ) {
				return null;
			}
			else {
				int totalCols = excelReader.getLastColumnNum();
				int k = 1;
				
				for ( int i = 0; i < rows; i++ ) {
					colValues = new ArrayList<>();
					
					for ( int j = 1; j < totalCols; j++ ) {
						String cellvalues = excelReader.getCelldata( i, j );
						colValues.add( cellvalues );
						rowValues.put( k, colValues );
					}
					
					k++;
				}
			}
			
			return rowValues;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			excelReader.closeExcel();
		}
	}
	
	// Fetch row values using Test Case name
	public HashMap<Integer, ArrayList<String>> readDataByTestCase( String excelPath, String workBookName, String sheetName, String tcName ) throws Exception {
		String filename = excelPath + "\\" + workBookName;
		ExcelReaderHelper excelReader = null;
		
		try {
			excelReader = new ExcelReaderHelper(filename);
			HashMap<Integer, ArrayList<String>> rowValues = new HashMap<Integer, ArrayList<String>>();
			ArrayList<String> colValues = null;
			excelReader.excelIntialize(sheetName);
			int[] rows = excelReader.getTotalRowValues( tcName );
			
			if ( rows[0] == 0 ) {
				return null;
			}
			else {
				int totalCols = excelReader.getLastColumnNum();
				
				for ( int i = rows[1], k = 1; i < rows[0] + rows[1]; i++, k++ ) {
					colValues = new ArrayList<>();
					
					for ( int j = 1; j < totalCols; j++ ) {
						String cellvalues = excelReader.getCelldata( i, j );
						colValues.add( cellvalues );
						rowValues.put( k, colValues );
					}
				}
			}
			
			return rowValues;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			excelReader.closeExcel();
		}
	}
	
	public HashMap<String, ArrayList<String>> readDataByColumn( String excelPath, String workBookName, String sheetName, String[] colName ) throws Exception {
		String filename = excelPath + "//" + workBookName;
		ExcelReaderHelper excelReader = new ExcelReaderHelper(filename);
		
		try {
			HashMap<String, ArrayList<String>> rowValues = new HashMap<String, ArrayList<String>>();
			ArrayList<String> colValues = null;
			excelReader.excelIntialize(sheetName);
			
			int[] col = excelReader.getColumnNumber(colName);
			int rows = excelReader.getLastRowNum();
			
			if ( rows == 0 ) {
				return null;
			}
			else {
				for ( int i = 0; i < col.length; i++ ) {
					colValues = new ArrayList<>();
					
	 				for ( int j = 1; j < rows; j++ ) {
						String cellvalues = excelReader.getCelldata( j, col[i] );
						colValues.add( cellvalues );
					}
	 				
	 				rowValues.put( colName[i], colValues );
				}
			}
			
			return rowValues;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			excelReader.closeExcel();
		}
	}

	public HashMap<String, ArrayList<String>> readDataByColumn( String excelPath, String workBookName, String sheetName, String[] colName, String filterColumn, String filterValue ) throws Exception {
		String filename = excelPath + "//" + workBookName;
		ExcelReaderHelper excelReader = new ExcelReaderHelper(filename);
		
		try {
			excelReader.excelIntialize(sheetName);
			int rows = excelReader.getLastRowNum();
			
			if ( rows == 0 ) {
				return null;
			}
			else {
				HashMap<String, ArrayList<String>> rowValues = new HashMap<String, ArrayList<String>>();
				ArrayList<String> colValues = null;
				
				int[] col = excelReader.getColumnNumber(colName);
				int filterCol = excelReader.getColumnNumber(filterColumn);
				int[] rowNo = excelReader.getRowCountForFilter(rows, filterCol, filterValue);
				
				for ( int i = 0; i < col.length; i++ ) {
					colValues = new ArrayList<>();
					
	 				for ( int j = 0; j < rowNo.length; j++ ) {
						String cellvalues = excelReader.getCelldata( rowNo[j], col[i] );
						colValues.add( cellvalues );
					}
	 				
	 				rowValues.put( colName[i], colValues );
				}
				
				return rowValues;
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			excelReader.closeExcel();
		}
	}
	
	public HashMap<String, ArrayList<String>> readDataByColumn( String excelPath, String workBookName, String sheetName, String tcName ) throws Exception {
		try {
			HashMap<String, ArrayList<String>> propertyValues = readDataByColumn(excelPath, workBookName, sheetName, tcName, 1);
			
			return propertyValues;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public HashMap<String, ArrayList<String>> readDataByColumn( String excelPath, String workBookName, String sheetName, String tcName, int occurance ) throws Exception {
		String filename = excelPath + workBookName;
		ExcelReaderHelper excelReader = new ExcelReaderHelper(filename);
		String[] columnName = {"ParameterName", "ParameterValue"};
		
		try {
			HashMap<String, ArrayList<String>> propertyValues = new HashMap<String, ArrayList<String>>();
			ArrayList<String> values = null;
			excelReader.excelIntialize(sheetName);
			
			int tcCol = excelReader.getTCColumnNumber(sheetName, tcName, occurance);
			
			if (tcCol >= 0) {
				int nameCol = excelReader.getHeaderColumnNumber(tcCol+1, columnName[0]);
				
				if ( nameCol == 0) {
					return null;
				}
				else {
					int valueCol = excelReader.getHeaderColumnNumber(nameCol+1, columnName[1]);
					int valueColCount = excelReader.getValueColumnCount(valueCol);
					int totalRows = excelReader.getLastRowNum();
					
					String propertyName = null;
					for (int i = 1; i < totalRows; i++) {
						values = new ArrayList<>();
						propertyName = excelReader.getCelldata( i, nameCol );
						
						if (!propertyName.equals("")) {
							for ( int j = valueCol; j < valueCol+valueColCount; j++ ) {
								
								String cellvalues = excelReader.getCelldata( i, j );
								values.add( cellvalues );
							}
							
							if (!values.isEmpty())
								propertyValues.put( propertyName, values );
						}
						else
							break;
					}
				}
			}
			else {
				FailureHelper.failTest("Test case with name '" + tcName + "' is not found in file '" + filename + "'.");
			}
			
			return propertyValues;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			excelReader.closeExcel();
		}
	}
	
	public String readCellData( String excelPath, String workBookName, String sheetName, String tcName, String colName ) throws Exception {
		String filename = excelPath + "//" + workBookName;
		ExcelReaderHelper excelReader = new ExcelReaderHelper(filename);
		
		try {
			excelReader.excelIntialize(sheetName);
			String cellvalue = null;
			int[] values = excelReader.getTotalRowValues( tcName );
			int totalRows = values[0];
			
			if ( totalRows == 0 ) {
				return null;
			}
			else {
				int startingRow = values[1];
				int colNum = 0;
				if (ValidationHelper.isInteger(colName))
					colNum = Integer.parseInt(colName);
				else
					colNum = getColumnNumber(excelPath, workBookName, sheetName, colName);
				cellvalue = excelReader.getCelldata( startingRow, colNum );
			}
			
			return cellvalue;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			excelReader.closeExcel();
		}
	}
	
	/*
	 * The below function reads the contents of the column in excel and return the contents as a 2D array
	 * excelData = object of excelData
	 * ExcelColumn = String that will read the column
	 */
	public String[][] returnExcelColumnAs2DArray(HashMap<Integer, ArrayList<String>> excelData, int counter, int excelColumn, String delimiter1, String delimiter2) 
	{
		if(excelData.get(counter).get(excelColumn).isEmpty())
			return new String[1][1];
		String [] splitColumn = excelData.get(counter).get(excelColumn).replace("\n", "").split("\\"+delimiter1+"", -1);
		String [][] returnColumn = new String[splitColumn.length][]; 
		
		for(int m = 0; m < splitColumn.length ; m++) {
			returnColumn[m] = splitColumn[m].split(delimiter2, -1);
		}
		
		return returnColumn;
	}
}