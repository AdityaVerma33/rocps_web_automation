package com.subex.automation.helpers.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class ExcelReaderHelper extends AcceptanceTest {

	private static OPCPackage opcPackage = null;
	private static XSSFWorkbook excelWorkbook = null;
	private static XSSFSheet excelSheet = null;
	private static XSSFCell cell = null;
	private static XSSFRow row = null;
	private static String path = null;
	
	public ExcelReaderHelper(String excelPath) {
		path = excelPath;
	}
	
	protected XSSFWorkbook getWorkBook( ) throws Exception {
		try {
			return excelWorkbook;
		}
		catch ( Exception e ) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected XSSFSheet getSheet( ) throws Exception {
		try {
			return excelSheet;
		}
		catch ( Exception e ) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected String getSheetName( ) throws Exception {
		try {
			return excelSheet.getSheetName();
		}
		catch ( Exception e ) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected void excelIntialize() throws Exception {
		try {
			if (!path.endsWith(".xlsx")) {
				if (FileHelper.checkFileExists(path + ".xlsx"))
					path = path + ".xlsx";
				else
					FailureHelper.failTest("File '" + new File(path).getName() + "' does not exists.");
			}
			else {
				if (!FileHelper.checkFileExists(path))
					FailureHelper.failTest("File '" + new File(path).getName() + "' does not exists.");
			}
			
			File excelFile = new File( path );
			opcPackage = OPCPackage.open(excelFile, PackageAccess.READ);
			excelWorkbook = new XSSFWorkbook( opcPackage );
		}
		catch ( FileNotFoundException e1 ) {
			FailureHelper.setError("Excel '" + path + "' is not found");
			FailureHelper.setErrorMessage(e1);
			throw e1;
		}
		catch ( Exception e ) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected void excelIntialize( String sheetName ) throws Exception {
		try {
			if (excelSheet == null || !getSheetName().equals(sheetName)) {
				if (!path.endsWith(".xlsx"))
					path = path + ".xlsx";
				
				if (!FileHelper.checkFileExists(path))
					FailureHelper.failTest("File '" + new File(path).getName() + "' does not exists.");
				
				File excelFile = new File( path );
				opcPackage = OPCPackage.open(excelFile, PackageAccess.READ);
				excelWorkbook = new XSSFWorkbook( opcPackage );
				
				getSheet( sheetName );
				
				if ( excelSheet != null && !getSheetName().equalsIgnoreCase( sheetName )  ) {
					getSheet( sheetName );
				}
				
				if (excelSheet == null) {
					FailureHelper.failTest("Sheet with name '" + sheetName + "' is not found in the excel '" + excelFile.getName() + "'");
				}
			}
			
		}
		catch ( FileNotFoundException e1 ) {
			FailureHelper.setError("Excel '" + path + "' is not found");
			FailureHelper.setErrorMessage(e1);
			throw e1;
		}
		catch ( IOException e ) {
			FailureHelper.setError("Sheet '" + sheetName + "' is not found");
			FailureHelper.setErrorMessage(e);
			throw e;
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
	}

	protected void getSheet( String sheetName ) throws Exception {
		try {
			excelSheet = excelWorkbook.getSheet( sheetName );
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
	}
	
	protected void closeExcel() throws Exception {
		try {
			
			excelSheet = null;
			excelWorkbook.close();
			opcPackage.clearRelationships();
			opcPackage.revert();
			opcPackage.close();
			path = null;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected int getLastRowNum() throws Exception {
		try {
			if (excelSheet != null) {
				int rownum = excelSheet.getPhysicalNumberOfRows();
				return rownum;
			}
			else
				return 0;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected int getLastColumnNum() throws Exception
	{
		try {
			if (excelSheet != null) {
				int colNum = excelSheet.getRow( 0 ).getLastCellNum();
				return colNum;
			}
			else
				return 0;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private String getDate() throws Exception {
		try {
			DataFormatter fmt = new DataFormatter();
			DateFormat sdf = new SimpleDateFormat( configProp.getDateFormat() + " " + configProp.getTimeFormat() );
			String cellData = null;
			
			if ( HSSFDateUtil.isCellDateFormatted( cell ) ) {
				cellData = sdf.format( cell.getDateCellValue() );
			}
			else {
				cellData = fmt.formatCellValue(cell);
			}
			
			return cellData;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected String getCelldata( int rowNum, int colNum ) throws Exception
	{
		try
		{
			String cellData = null;
			row = excelSheet.getRow( rowNum );
			
			if (row == null)
				cellData = "";
			else {
				cell = row.getCell( colNum );
				if ( cell == null )
					cellData = "";
				else {
					switch( cell.getCellType() ) {
		
						case 0:
							cellData = getDate();
							break;
						case 1:
							cellData = cell.getStringCellValue();
							break;
						case 2:
							cellData = cell.getRawValue();
							break;
						case 3:
							cellData = "";
							break;
						case 4:
							cellData = cell.getBooleanCellValue() ? "TRUE" : "FALSE";
							break;
						case 5:
							cellData = Byte.toString( cell.getErrorCellValue() );
							break;
					}
				}
			}
			
			TestDataHelper testData = new TestDataHelper();
			cellData = testData.getValue(cellData);
			return cellData;
		}
		catch ( Exception e )
		{
			FailureHelper.setError("row " + rowNum + " or column " + colNum + " does not exist in xls");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected int getColumnNumber(String columnName ) throws Exception
	{
		try {
			int colNum = 0;
			String colname = null;
			int colSize = getLastColumnNum();
			
			for ( colNum = 0; colNum < colSize; colNum++ ) {
				colname = getCelldata( 0, colNum );
				
				if ( colname.trim().equalsIgnoreCase( columnName ) )
					return colNum;
			}
			
			return 0;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected int[] getColumnNumber( String[] columnName ) throws Exception
	{
		try {
			int count = columnName.length;
			int[] colNum = new int[count];
			int colSize = getLastColumnNum();
			String[] columnNames = new String[colSize];
			
			for (int i = 0; i < colSize; i++) {
				columnNames[i] = getCelldata( 0, i ).trim();
			}
			
			for ( int i = 0; i < count; i++ )
			{
				boolean colFound = false;
				
				for (int j = 0; j < colSize; j++) {
					if ( columnNames[j].equalsIgnoreCase( columnName[i] ) ) {
						colNum[i] = j;
						colFound = true;
						break;
					}
				}
				
				if (!colFound) {
					FailureHelper.failTest("Column '" + columnName[i] + "' not found in excel '" + getSheetName() + "'");
				}
			}
			
			return colNum;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected int[] getTotalRowValues( String tcName ) throws Exception
	{
		try {
			int totalRows = getLastRowNum();
			int count = 0;
			int startRowNum = 0;
			
			for ( int i = 1; i < totalRows; i++ )
			{
				String cellvalues = getCelldata( i, 0 );
				
				if ( cellvalues.equalsIgnoreCase( tcName ) )
				{
					count ++;
					if (count == 1)
						startRowNum = i;
				}
			}
			
			int[] values = {count, startRowNum};
			return values;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected int getValueRowCount( String tcName ) throws Exception
	{
		try {
			int totalRows = getLastRowNum();
			int count = 0;
			
			for ( int i = 1; i < totalRows; i++ )
			{
				String cellvalues = getCelldata( i, 0 );
				
				if ( cellvalues.equals( tcName ) )
					count ++;
			}
			
			return count;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected int getHeaderColumnNumber(int rows, int cols, String firstHeaderValue) throws Exception {
		try {
			int headerCol = -1;
			
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					String dValue = getCelldata(i, j);
					if (dValue.equalsIgnoreCase(firstHeaderValue)) {
						headerCol = i;
						i = rows;
						break;
					}
				}
			}
			
			return headerCol;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected int[] getRowCountForFilter(int rows, int filterCol, String filterValue) throws Exception {
		try {
			
			int[] rowNo = new int[rows];
			int count = 0;
			
			for (int i = 1; i <= rows; i++) {
				String temp = getCelldata(i, filterCol);
				
				if (temp.equalsIgnoreCase(filterValue)) {
					rowNo[count] = i;
					count++;
				}
			}
			
			rowNo = GenericHelper.resizeIntArray(rowNo, count);
			
			return rowNo;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected int[] getColumnNumbers(String sheetName, String columnName ) throws Exception {
		try {
			int[] cols = new int[1000];
			int colNum = 0;
			String colname = null;
			int colSize = getLastColumnNum();
			int count = 0;
			
			for ( colNum = 0; colNum < colSize; colNum++ )
			{
	
				colname = getCelldata( 0, colNum );
				if ( colname.trim().equalsIgnoreCase( columnName ) ) {
					cols[count] = colNum;
					count++;
				}
	
			}
			
			cols = GenericHelper.resizeIntArray(cols, count);
			
			return cols;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected int getTCColumnNumber(String sheetName, String tcName) throws Exception {
		try {
			excelIntialize(sheetName);
			
			int[] tcCol = getColumnNumbers(sheetName, "TCName");
			int nameCol = 0;
			
			for (int i = 0; i < tcCol.length; i++) {
				String dValue = getCelldata(1, tcCol[i]);
				if (dValue.equalsIgnoreCase(tcName)) {
					nameCol = tcCol[i];
					break;
				}
			}
			
			return nameCol;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected int getTCColumnNumber(String sheetName, String tcName, int occurance ) throws Exception {
		try {
			excelIntialize(sheetName);
			
			int[] tcCol = getColumnNumbers(sheetName, "TCName");
			int nameCol = -1;
			int count = 1;
			
			for (int i = 0; i < tcCol.length; i++) {
				String dValue = getCelldata(1, tcCol[i]);
				if (dValue.equalsIgnoreCase(tcName)) {
					nameCol = tcCol[i];
					
					if (count == occurance)
						break;
					else
						count++;
				}
			}
			
			return nameCol;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected int getHeaderColumnNumber( int startColNumber, String headerValue ) throws Exception {
		try {
			int totalColumns = getLastColumnNum();
			int valueCol = 0;
			
			for (int i = startColNumber; i < totalColumns; i++) {
				String dValue = getCelldata(0, i);
				if (dValue.equalsIgnoreCase(headerValue)) {
					valueCol = i;
					break;
				}
			}
			
			return valueCol;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected int[] getHeaderColumnNumber( int startColNumber, String[] headerValue ) throws Exception {
		try {
			int totalColumns = getLastColumnNum();
			int totalHeaders = headerValue.length;
			int[] colNum = new int[totalHeaders];
			
			for (int i = 0; i < totalHeaders; i++) {
				for (int j = startColNumber; j < totalColumns; j++) {
					String dValue = getCelldata(0, j);
					
					if (headerValue[i].equalsIgnoreCase(dValue)) {
						colNum[i] = j;
						break;
					}
				}
			}
			
			return colNum;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected int getValueColumnCount( int startNumber ) throws Exception {
		try {
			int colSize = getLastColumnNum();
			int count = 0;
			
			for ( int colNum = startNumber; colNum < colSize; colNum++ )
			{
	
				String colname = getCelldata( 0, colNum );
				if ( colname.trim().equalsIgnoreCase( "ParameterValue" ) ) {
					count++;
				}
				else
					break;
	
			}
			
			return count;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected int getTotalValues( String WorkbookName, String sheetName, String tcName, int occurance ) throws Exception {
		try {
			excelIntialize(sheetName);
			int tcCol = getTCColumnNumber(sheetName, tcName, occurance);
			
			if (tcCol == -1) {
				return 0;
			}
			else {
				int valueColCount = 0;
				String[] headerValues = {"ParameterName", "ParameterValue"};
				int[] colNums = getHeaderColumnNumber(tcCol+1, headerValues);
				
				if ( colNums[0] > 0 )
					valueColCount = getValueColumnCount(colNums[1]);
				
				return valueColCount;
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected int getTotalValues( String WorkbookName, String sheetName, String tcName, int occurance, int startColNo ) throws Exception {
		try {
			excelIntialize(sheetName);
			int tcCol = getTCColumnNumber(sheetName, tcName, occurance);
			int nameCol = getHeaderColumnNumber(tcCol+1, "ParameterName");
			
			if ( nameCol == 0 ) {
				return 0;
			}
			else {
				int valueCol = getHeaderColumnNumber(tcCol+1, "ParameterValue") + startColNo;
				int valueColCount = getValueColumnCount(valueCol);
				
				return valueColCount;
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected int getTotalProperties( String WorkbookName, String sheetName, int colNumber ) throws Exception {
		try {
			excelIntialize(sheetName);
			
			int count = 0;
			int totalRows = getLastRowNum();
			
			for (int i = 1; i < totalRows; i++) {
				String dValue = getCelldata(i, colNumber);
				
				if (!dValue.equals(""))
					count++;
			}
			
			return count;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected int getHeaderColumnNumber(String WorkbookName, String sheetName, String firstHeaderValue, String headerColumnName) throws Exception {
		try {
			excelIntialize(sheetName);
			
			int rows = getLastRowNum();
			int cols = getLastColumnNum();
			int headerCol = -1;
			int headerColNo = 0;
			
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					String dValue = getCelldata(i, j);
					if (dValue.equalsIgnoreCase(firstHeaderValue)) {
						headerCol = i;
						i = rows;
						break;
					}
				}
			}
			
			if (headerCol >= 0) {
				for (int i = 0; i < cols; i++) {
					String dValue = getCelldata(headerCol, i);
					if (dValue.equalsIgnoreCase(headerColumnName)) {
						headerColNo = i;
						break;
					}
				}
			}
			
			return headerColNo;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected int[] getHeaderRowColumn(String sheetName, String firstHeaderValue ) throws Exception {
		try {
			excelIntialize(sheetName);
			
			int rows = getLastRowNum();
			int cols = getLastColumnNum();
			int[] details = new int[2];
			
			for (int i = 0; i < rows; i++) {
				
				for (int j = 0; j < cols; j++) {
					String dValue = getCelldata(i, j);
					if (dValue.equalsIgnoreCase(firstHeaderValue)) {
						details[0] = i;
						details[1] = j;
						i = rows;
						break;
					}
				}
			}
			
			return details;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected int getTotalMappingRows(int startRowNo, int startColNo, int rows) throws Exception {
		try {
			int count = 0;
			
			for ( int j = startRowNo+1; j < rows; j++ ) {
				String cellvalues = getCelldata( j, startColNo ).trim();
				if (ValidationHelper.isNotEmpty(cellvalues)) {
					count++;
				}
				else {
					cellvalues = getCelldata( j, startColNo+1 ).trim();
					if (ValidationHelper.isNotEmpty(cellvalues)) {
						count++;
					}
				}
			}
			
			return count;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected int[] getHeaderRowColumn(String firstHeaderValue ) throws Exception {
		try {
			int rows = getLastRowNum();
			int cols = getLastColumnNum();
			int[] details = new int[2];
			
			for (int i = 0; i < rows; i++) {
				
				for (int j = 0; j < cols; j++) {
					String dValue = getCelldata(i, j);
					if (dValue.equalsIgnoreCase(firstHeaderValue)) {
						details[0] = i;
						details[1] = j;
						i = rows;
						break;
					}
				}
			}
			
			return details;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public Object[] getDataUsingColumnName( String workBookName, String sheetName, String colName ) throws Exception {
		try {
			Object[] obj = new Object[2];
			HashMap<Integer, ArrayList<String>> propertyValues = new HashMap<Integer, ArrayList<String>>();
			ArrayList<String> values = null;
			int valueColCount = 1;
			excelIntialize(sheetName);
			
			int tcCol = getTCColumnNumber(sheetName, colName);
			int nameCol = getHeaderColumnNumber(tcCol+1, "ParameterName");
			if ( nameCol == 0) {
				Log4jHelper.logInfo("Column name '" + colName + "' is not present in excel file '" + workBookName + "' > '" + sheetName + "'");
			}
			else {
				int valueCol = getHeaderColumnNumber(nameCol+1, "ParameterValue");
				valueColCount = getValueColumnCount(valueCol);
				int totalRows = getLastRowNum();
				
				String propertyName = null;
				for (int i = 1; i < totalRows; i++) {
					values = new ArrayList<>();
					propertyName = getCelldata( i, nameCol );
					
					if (!propertyName.equals("")) {
						values.add( propertyName );
						
						for ( int j = valueCol; j < valueCol+valueColCount; j++ ) {
							
							String cellvalues = getCelldata( i, j );
							values.add( cellvalues );
						}
						
						if (!values.isEmpty())
							propertyValues.put( i, values );
					}
					else
						break;
				}
			}
			
			obj[0] = propertyValues;
			obj[1] = valueColCount;
			return obj;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}