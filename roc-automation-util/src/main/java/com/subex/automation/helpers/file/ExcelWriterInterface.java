package com.subex.automation.helpers.file;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface ExcelWriterInterface {

	public XSSFWorkbook getExcelWorkbook() throws Exception;
	public XSSFSheet getExcelSheet() throws Exception;
	public XSSFSheet initializeExcel( String sheetName ) throws Exception;
	void initializeSheet( String sheetName ) throws Exception;
	
	public int getTotalRows() throws Exception;
	public int getTotalColumns() throws Exception;
	public String getCelldata( XSSFCell cell ) throws Exception;
	
	public XSSFCellStyle getCellStyle() throws Exception;
	public XSSFCellStyle getCellStyle(Short cellColorIndex) throws Exception;
	public XSSFCellStyle getFontStyle(XSSFCellStyle cellStyle, Short fontColorIndex) throws Exception;
	
	public void setHeaderStyle(int noOfRows) throws Exception;
	public boolean setRowStyle(int rowStartNo, int colStartNo) throws Exception;
	public void setRowColor(int rowStartNo, int colStartNo) throws Exception;
	
	public void autoSizeColumns() throws Exception;
	public void freezePanel(int rowNo, int colNo) throws Exception;
	public void mergeHeader(int noOfRows, int startColNo, int colIncrement) throws Exception;
	
	public void writeToExcel() throws Exception;
	public void closeExcel() throws Exception;
}