package com.subex.automation.helpers.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class ExcelWriter extends AcceptanceTest {

	public static String path;
	public static ExcelWriterHelper xlsxWriter = null;
	
	private static XSSFSheet writeToExcel(String sheetName, Map<Integer, Object[]> data) throws Exception {
		try {
			XSSFSheet excelSheet = xlsxWriter.initializeExcel(sheetName);
			List<Integer> lKeyset = GenericHelper.getIntegerKeySet(data);
			int rownum = 0;
	        
	        for (Integer key : lKeyset) {
				XSSFRow row = excelSheet.createRow(rownum++);
				Object [] objArr = data.get(key);
				int cellnum = 0;
				
				for (Object obj : objArr) {
					xlsxWriter.createCell(row, cellnum++, obj);
				}
		    }
	        
	        return excelSheet;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void writeToExcel(String workBookName, String sheetName, Map<Integer, Object[]> data) throws Exception {
		try {
			xlsxWriter = new ExcelWriterHelper(GenericHelper.updatePath(workBookName));
			writeToExcel(sheetName, data);
			
	        xlsxWriter.setRowStyle(1, 0);
	        xlsxWriter.setHeaderStyle(1);
	        xlsxWriter.autoSizeColumns();
	        
	        xlsxWriter.writeToExcel();
		}
		catch (FileNotFoundException e) {
			FailureHelper.setError("File '" + path + "' not found");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (IOException e) {
			FailureHelper.setError("File '" + path + "' is not accessible. May be the file is already open or permission denied.");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			xlsxWriter.closeExcel();
		}
	}
	
	public static boolean writeToExcel(String workBookPath, String workBookName, String sheetName, Map<Integer, Object[]> data, String type) throws Exception {
		try {
			String temp = GenericHelper.updatePath(workBookPath);
			xlsxWriter = new ExcelWriterHelper(GenericHelper.updatePath(temp + "\\" + workBookName));
			XSSFSheet excelSheet = writeToExcel(sheetName, data);
		   
	        boolean hasErrors = setRowStyle(type);
	        if (hasErrors)
	        	excelSheet.setTabColor(xlsxWriter.redColor);
	        xlsxWriter.autoSizeColumns();
	        xlsxWriter.writeToExcel();
			
		   	return hasErrors;
		}
		catch (FileNotFoundException e) {
			FailureHelper.setError("File '" + path + "' not found");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (IOException e) {
			FailureHelper.setError("File '" + path + "' is not accessible. May be the file is already open or permission denied.");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			xlsxWriter.closeExcel();
		}
	}
	
	public static void writeToExcel(String workBookPath, String workBookName, String sheetName, Map<Integer, Object[]> data, boolean[][] hasError, String type) throws Exception {
		try {
			String temp = GenericHelper.updatePath(workBookPath);
			xlsxWriter = new ExcelWriterHelper(GenericHelper.updatePath(temp + "\\" + workBookName));
			XSSFSheet excelSheet = writeToExcel(sheetName, data);
	        
	        setRowStyle(hasError, type);
	        
	        boolean hasErrors = false;
	        for (int i = 0; i < hasError.length; i++) {
	        	int index = GenericHelper.searchBooleanArray(hasError[i], true);
	        	if (index >= 0) {
        			hasErrors = true;
        			break;
        		}
	        }
	        
	        if (hasErrors)
	        	excelSheet.setTabColor(xlsxWriter.redColor);
	        
	        xlsxWriter.autoSizeColumns();
	        xlsxWriter.writeToExcel();
		}
		catch (FileNotFoundException e) {
			FailureHelper.setError("File '" + path + "' not found");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (IOException e) {
			FailureHelper.setError("File '" + path + "' is not accessible. May be the file is already open or permission denied.");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			xlsxWriter.closeExcel();
		}
	}
	
	public static void writeToExcel(String workBookPath, String workBookName, String sheetName, Map<Integer, Object[]> data, String[] result, String type) throws Exception {
		try {
			String temp = GenericHelper.updatePath(workBookPath);
			xlsxWriter = new ExcelWriterHelper(GenericHelper.updatePath(temp + "\\" + workBookName));
			XSSFSheet excelSheet = writeToExcel(sheetName, data);
	        
	        boolean hasErrors = setRowStyle(result, type);
	        
	        if (hasErrors)
	        	excelSheet.setTabColor(xlsxWriter.redColor);
	        
	        xlsxWriter.writeToExcel();
		}
		catch (FileNotFoundException e) {
			FailureHelper.setError("File '" + path + "' not found");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (IOException e) {
			FailureHelper.setError("File '" + path + "' is not accessible. May be the file is already open or permission denied.");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			xlsxWriter.closeExcel();
		}
	}
	
	public static void autoSizeColumns(XSSFSheet sheet) throws Exception {
		try {
			int columns = sheet.getRow( 0 ).getLastCellNum();
			
		    for (int i = 0; i < columns; i++)
		    	sheet.autoSizeColumn(i);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void setStyle(XSSFWorkbook workbook, XSSFSheet excelSheet) throws Exception {
		try {
			
			XSSFRow row=excelSheet.getRow(0);
		    int columns = row.getLastCellNum();
		    
		    XSSFCellStyle cellStyle = getCellStyleWithFont(workbook, HSSFColor.BLUE.index);
		    cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		    
		    for (int col = 0; col < columns; col++) {
		    	XSSFCell cell = row.getCell(col);
		        cell.setCellStyle(cellStyle);
		    }
		    
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean setRowStyle(XSSFWorkbook workbook, XSSFSheet excelSheet) throws Exception {
		try {
			int rows = excelSheet.getLastRowNum();
			XSSFCellStyle cellStyle = getCellStyle(workbook);
			
			for (int i = 1; i < rows+1; i++) {
				XSSFRow row = excelSheet.getRow(i);
				if (row == null)
					FailureHelper.failTest("Row number '" + i + "' is empty.");
			    int columns = row.getLastCellNum();
			    
			    for (int col = 0; col < columns; col++) {
			    	XSSFCell cell = row.getCell(col);
			    	if (cell != null)
			    		cell.setCellStyle(cellStyle);
			    }
			}
			
			return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static boolean setRowStyle(String type) throws Exception {
		try {
			boolean hasErrors = false;
	        
	        if (type != null) {
	        	switch (type) {
	        	case "MappingSheet":
				case "LDCSparkOutput":
					hasErrors = xlsxWriter.setRowColor(xlsxWriter.lightTurquoiseColor, xlsxWriter.lemonChiffonColor, xlsxWriter.redColor, 1, 0);
					xlsxWriter.setHeaderStyle(1);
					break;
					
				case "GlobalVariable":
					hasErrors = xlsxWriter.setGlobalVariablesRowColor();
					xlsxWriter.mergeHeader(2, 2, 1);
					break;
					
				case "GlobalVariableReinitialize":
					hasErrors = xlsxWriter.setGlobalVariables1RowColor();
					xlsxWriter.mergeHeader(2, 5, 1);
					break;
					
				case "GlobalVariableAssignments":
					hasErrors = xlsxWriter.setGlobalVariables2RowColor();
					xlsxWriter.mergeHeader(2, 4, 2);
					break;
					
				case "StaticLookup":
					hasErrors = xlsxWriter.setStaticLookupRowColor();
					xlsxWriter.setHeaderStyle(1);
					break;
					
				case "GenerateLongID":
					hasErrors = xlsxWriter.setRowColor(xlsxWriter.redColor, 2, 0);
					xlsxWriter.mergeHeader(2, 0, 2);
					break;
					
				case "Query":
					xlsxWriter.setRowColor(0, 0);
					break;
					
				case "CompareTDs":
					hasErrors = xlsxWriter.setRowColor(xlsxWriter.lightTurquoiseColor, xlsxWriter.lemonChiffonColor, xlsxWriter.redColor, 2, 0);
					xlsxWriter.mergeHeader(2, 0, 1);
					break;
					
				case "CoreFields":
					hasErrors = xlsxWriter.setCoreFieldsRowColor();
					xlsxWriter.setHeaderStyle(1);
					break;
					
				case "QMFrequency":
					hasErrors = xlsxWriter.setQMFrequencyRowColor();
					xlsxWriter.setHeaderStyle(1);
					break;
					
				case "TableStructure":
					hasErrors = xlsxWriter.setTableStructureRowColor();
					xlsxWriter.setHeaderStyle(1);
					break;
					
				case "KPIAndCases":
					hasErrors = xlsxWriter.setKPICasesRowColor();
					xlsxWriter.setHeaderStyle(1);
					break;
					
				case "ReconFields":
					hasErrors = xlsxWriter.setReconFieldsRowColor();
					xlsxWriter.mergeHeader(2, 3, 1, 4);
					break;
					
				case "MeasureFilter":
					hasErrors = xlsxWriter.setMeasureFilterRowColor();
					xlsxWriter.setHeaderStyle(1);
					break;
					
				case "LDCObjectKey":
					hasErrors = xlsxWriter.setLDCObjectKeyRowColor();
					xlsxWriter.setHeaderStyle(1);
					break;
					
				case "GVAssignmentOrder":
					hasErrors = xlsxWriter.setGVAssignmentOrderRowColor();
					xlsxWriter.setHeaderStyle(1);
					break;
					
				case "LDCIndicator":
					hasErrors = xlsxWriter.setLDCIndicatorRowColor();
					xlsxWriter.mergeHeader(2, 1, 4, 2);
					xlsxWriter.mergeHeader(2, 6, 2, 7);
					break;
					
				default:
					hasErrors = xlsxWriter.setRowColor(xlsxWriter.lightTurquoiseColor, xlsxWriter.lemonChiffonColor, xlsxWriter.redColor, 1, 0);
					xlsxWriter.setHeaderStyle(1);
					break;
				}
	        }
	        else {
	        	hasErrors = xlsxWriter.setRowStyle(1, 0);
	        	xlsxWriter.setHeaderStyle(1);
	        }
			
	        return hasErrors;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static void setRowStyle(boolean[][] hasError, String type) throws Exception {
		try {
			switch (type) {
				case "PartKey":
			 		xlsxWriter.setRowColor(xlsxWriter.redColor, hasError, 2, 0);
					xlsxWriter.mergeHeader(2, 1, 1, 3);
					break;
					
			 	case "CompareTDs":
				case "SparkOutput":
					xlsxWriter.setRowColor(xlsxWriter.lightTurquoiseColor, xlsxWriter.lemonChiffonColor, xlsxWriter.redColor, hasError, 2, 0);
		        	xlsxWriter.mergeHeader(2, 0, 1);
					break;
					
				case "MeasureRTDataType":
					xlsxWriter.setRowColor(xlsxWriter.lightTurquoiseColor, xlsxWriter.lemonChiffonColor, xlsxWriter.redColor, hasError, 2, 0);
					xlsxWriter.mergeHeader(2, 0, 5);
					break;
					
				default:
					xlsxWriter.setRowColor(xlsxWriter.redColor, hasError, 1, 0);
		        	xlsxWriter.setHeaderStyle(1);
		        	break;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static boolean setRowStyle(String[] result, String type) throws Exception {
		try {
			boolean hasErrors = false;
			
			 switch (type) {
			 	case "ChangePredictor1":
			 		xlsxWriter.setRowColor(1, 0);;
	        		xlsxWriter.setHeaderStyle(1);
//	        		xlsxWriter.autoSizeColumns(2, 50);
	        		xlsxWriter.autoSizeColumns();
	        		break;
	        		
			 	case "ChangePredictor":
			 		hasErrors = xlsxWriter.setChangePredictorRowColor(result);
	        		xlsxWriter.setHeaderStyle(1);
//	        		xlsxWriter.autoSizeColumns(2, 50);
	        		xlsxWriter.autoSizeColumns();
	        		break;
					
			 	default:
					xlsxWriter.setRowColor(xlsxWriter.redColor, 1, 0);
		        	xlsxWriter.setHeaderStyle(1);
		        	xlsxWriter.autoSizeColumns();
					break;
				}
			 
			 return hasErrors;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static XSSFCellStyle getCellStyleWithFont(XSSFWorkbook workbook, Short fontColorIndex) throws Exception {
		try {
			XSSFCellStyle cellStyle = workbook.createCellStyle();
			XSSFFont font = (XSSFFont) workbook.createFont();
		    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
	        font.setBold(true);
	        font.setColor(fontColorIndex);
	        cellStyle.setFont(font);
	        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		    cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			
		    return cellStyle;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static XSSFCellStyle getCellStyle(XSSFWorkbook workbook) throws Exception {
		try {
			XSSFCellStyle cellStyle = workbook.createCellStyle();
	        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		    cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			
		    return cellStyle;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}