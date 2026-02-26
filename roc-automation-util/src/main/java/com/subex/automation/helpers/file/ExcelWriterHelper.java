package com.subex.automation.helpers.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.StringHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class ExcelWriterHelper extends AcceptanceTest implements ExcelWriterInterface {
	public final short redColor = HSSFColor.RED.index;
	public final short OrangeColor = HSSFColor.ORANGE.index;
	public final short lightTurquoiseColor = HSSFColor.LIGHT_TURQUOISE.index;
	public final short lemonChiffonColor = HSSFColor.LEMON_CHIFFON.index;
	public final short turquoiseColor = HSSFColor.TURQUOISE.index;
	public final short orange = HSSFColor.LIGHT_ORANGE.index;
	
	private OPCPackage opcPackage = null;
	private XSSFWorkbook excelWorkbook = null;
	private XSSFSheet excelSheet = null;
	private static String path = null;

	public ExcelWriterHelper(String excelPath) throws Exception {
		try {
			path = excelPath;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public XSSFWorkbook getExcelWorkbook() throws Exception {
		try {
			return excelWorkbook;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public XSSFSheet getExcelSheet() throws Exception {
		try {
			return excelSheet;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void setExcelSheet(XSSFSheet excel) throws Exception {
		try {
			excelSheet = excel;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String getPath() throws Exception {
		try {
			return path;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Override
	public XSSFSheet initializeExcel( String sheetName ) throws Exception {
		try {
			File file = new File(path);
			if (!file.exists()) {
				excelWorkbook = new XSSFWorkbook();
			}
			else {
				if (file.length() == 0) {
					FileHelper.deleteFile(automationOS, path);
					excelWorkbook = new XSSFWorkbook();
				}
				else {
					opcPackage = OPCPackage.open(new FileInputStream(file));
					excelWorkbook = (XSSFWorkbook) WorkbookFactory.create( opcPackage );
				}
			}
			
			initializeSheet( sheetName );
			return excelSheet;
		} catch ( FileNotFoundException e1 ) {
			FailureHelper.setError("Excel '" + path + "' is not found");
			FailureHelper.setErrorMessage(e1);
			throw e1;
		} catch ( IOException e ) {
			FailureHelper.setError("Sheet '" + sheetName + "' is not found");
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch ( Exception e ) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@Override
	public void initializeSheet( String sheetName ) throws Exception {
		try {
			if (excelWorkbook.getSheet(sheetName) != null)
				excelSheet = excelWorkbook.getSheet(sheetName);
			else
				excelSheet = excelWorkbook.createSheet(sheetName);
		} catch ( Exception e ) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Override
	public int getTotalRows() throws Exception {
		try {
			if (excelSheet != null) {
				int colNum = excelSheet.getPhysicalNumberOfRows();
				return colNum;
			}
			else
				return 0;
		} catch ( Exception e ) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Override
	public int getTotalColumns() throws Exception {
		try {
			if (excelSheet != null) {
				int colNum = excelSheet.getRow( 0 ).getLastCellNum();
				return colNum;
			}
			else
				return 0;
		} catch ( Exception e ) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public int getTotalColumns(XSSFRow row) throws Exception {
		try {
			if (row != null) {
				int colNum = row.getLastCellNum();
				return colNum;
			}
			else
				return 0;
		} catch ( Exception e ) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Override
	public String getCelldata( XSSFCell cell ) throws Exception {
		try {
			String cellData = null;
			
			if ( cell == null )
				cellData = "";
			else {
				switch( cell.getCellType() ) {
					case 0:
						if ( HSSFDateUtil.isCellDateFormatted( cell ) ) {
							DateFormat sdf = new SimpleDateFormat( configProp.getDateFormat() + " " + configProp.getTimeFormat() );
							cellData = sdf.format( cell.getDateCellValue() );
						}
						else {
							DataFormatter fmt = new DataFormatter();
							cellData = fmt.formatCellValue(cell);
						}
						break;
					case 1:
					case 2:
						cellData = cell.getStringCellValue();
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
			
			return cellData;
		} catch ( Exception e ) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Override
	public XSSFCellStyle getCellStyle() throws Exception {
		try {
			XSSFCellStyle cellStyle = excelWorkbook.createCellStyle();     
			cellStyle = setBorder(cellStyle);
			cellStyle.setWrapText(true);
			cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP);
			
		    return cellStyle;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Override
	public XSSFCellStyle getCellStyle(Short cellColorIndex) throws Exception {
		try {
			XSSFCellStyle cellStyle = getCellStyle();
	        cellStyle.setFillForegroundColor(cellColorIndex);
	        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
		    return cellStyle;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Override
	public XSSFCellStyle getFontStyle(XSSFCellStyle cellStyle, Short fontColorIndex) throws Exception {
		try {
			XSSFFont font = getFont(fontColorIndex, true);
	        cellStyle.setFont(font);
	       
		    return cellStyle;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public XSSFCellStyle setBorder(XSSFCellStyle cellStyle) throws Exception {
		try {
			short borderThin = HSSFCellStyle.BORDER_THIN;
			cellStyle.setBorderBottom(borderThin);
		    cellStyle.setBorderTop(borderThin);
		    cellStyle.setBorderRight(borderThin);
		    cellStyle.setBorderLeft(borderThin);
			
		    return cellStyle;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private XSSFFont getFont(Short fontColorIndex, boolean bold) throws Exception {
		try {
			XSSFFont font = (XSSFFont) excelWorkbook.createFont();
			if (bold) {
			    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		        font.setBold(true);
			}
			
	        font.setColor(fontColorIndex);
	        return font;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private int getMaxChar(int colNo) throws Exception {
		try {
			int maxNumCharacters = 0;
			int rows = getTotalRows();
			
			for (int i = 0; i < rows; i++) {
				XSSFCell cell = excelSheet.getRow(i).getCell(colNo);
				int length = getCelldata(cell).length();
				
				if (length > maxNumCharacters)
					maxNumCharacters = length;
			}
			
			return maxNumCharacters;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Override
	public void autoSizeColumns() throws Exception {
		try {
			int columns = getTotalColumns();
			
			if (columns > 7) {
				int maxWidth = 100 * 256;
				
			    for (int i = 0; i < columns; i++) {
			    	int maxNumCharacters = getMaxChar(i);
			    	if (maxNumCharacters > 255)
			    		maxNumCharacters = 255;
			    	int width = (int)(maxNumCharacters * 256);
	
			    	if (width > maxWidth)
			    		width = maxWidth;
			    	excelSheet.setColumnWidth(i, width);
			    }
			}
			else {
				 for (int i = 0; i < columns; i++) {
			    	int maxNumCharacters = getMaxChar(i);
			    	if (maxNumCharacters > 255)
			    		maxNumCharacters = 255;
			    	int width = (int)(maxNumCharacters * 256);
			    	
			    	excelSheet.setColumnWidth(i, width);
			    }
			}

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void autoSizeColumns(int colNumber, int noOfChars) throws Exception {
		try {
			autoSizeColumns();
			
			int width = noOfChars * 256;
			int maxWidth = 255 * 256;
			if (width > maxWidth)
	    		width = maxWidth;
			excelSheet.setColumnWidth(colNumber, width);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void mergeHeader(int noOfHeaderRows, int startColNo, int noOfColsToMerge) throws Exception {
		try {
			short[] cellColor = {HSSFColor.ROYAL_BLUE.index, HSSFColor.BLUE_GREY.index};
			short[] fontColor = {HSSFColor.WHITE.index, HSSFColor.WHITE.index};
			int count = 0;
			
			for (int i = 0; i < noOfHeaderRows; i++) {
				setHeaderStyle(cellColor[count], fontColor[count], i, 1);
				count++;
				if (count == cellColor.length)
					count = 0;
			}
			
			int columns = excelSheet.getRow(0).getLastCellNum();
		    int colIncrement = noOfColsToMerge + 1;
		    
		    for (int col = startColNo; col < columns; col+=colIncrement) {
		    	CellRangeAddress region = new CellRangeAddress(0, 0, col, (col + noOfColsToMerge));
		    	excelSheet.addMergedRegion(region);
		    }
		    
		    freezePanel(noOfHeaderRows, 0);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void mergeHeader(int noOfHeaderRows, int startColNo, int noOfColsToMerge, int endColNo) throws Exception {
		try {
			short[] cellColor = {HSSFColor.ROYAL_BLUE.index, HSSFColor.BLUE_GREY.index};
			short[] fontColor = {HSSFColor.WHITE.index, HSSFColor.WHITE.index};
			int count = 0;
			
			for (int i = 0; i < noOfHeaderRows; i++) {
				setHeaderStyle(cellColor[count], fontColor[count], i, 1);
				count++;
				if (count == cellColor.length)
					count = 0;
			}
			
			int columns = excelSheet.getRow(0).getLastCellNum();
			if (columns < endColNo)
				endColNo = columns;
		    int colIncrement = noOfColsToMerge + 1;
		    
		    for (int col = startColNo; col < endColNo; col+=colIncrement) {
		    	CellRangeAddress region = new CellRangeAddress(0, 0, col, (col + noOfColsToMerge));
		    	excelSheet.addMergedRegion(region);
		    }
		    
		    freezePanel(noOfHeaderRows, 0);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Override
	public void setHeaderStyle(int noOfRows) throws Exception {
		try {
			XSSFCellStyle cellStyle = getCellStyle(HSSFColor.ROYAL_BLUE.index);
			cellStyle = getFontStyle(cellStyle, HSSFColor.WHITE.index);
		    cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		    
			for (int i = 0; i < noOfRows; i++) {
				XSSFRow row = excelSheet.getRow(i);
				setCellStyle(row, cellStyle, 0);
			}
			
			freezePanel(noOfRows, 0);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void setHeaderStyle(short cellColor, short fontColor, int startRowNo, int noOfRows) throws Exception {
		try {
			XSSFCellStyle cellStyle = getCellStyle(cellColor);
			cellStyle = getFontStyle(cellStyle, fontColor);
		    cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		    
			for (int i = startRowNo; i < noOfRows+1; i++) {
				XSSFRow row = excelSheet.getRow(i);
				setCellStyle(row, cellStyle, 0);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Override
	public boolean setRowStyle(int rowStartNo, int colStartNo) throws Exception {
		try {
			int rows = getTotalRows();
			XSSFCellStyle cellStyle = getCellStyle();
			
			for (int i = rowStartNo; i < rows+1; i++) {
				XSSFRow row = excelSheet.getRow(i);
				setCellStyle(row, cellStyle, colStartNo);
			}
			
			return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void setCellStyle(XSSFRow row, XSSFCellStyle cellStyle, int colStartNo) throws Exception {
		try {
			int columns = getTotalColumns(row);

			for (int col = colStartNo; col < columns; col++) {
		    	XSSFCell cell = row.getCell(col);
		    	if (cell != null)
		    		cell.setCellStyle(cellStyle);
		    }
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private boolean setCellStyle(XSSFRow row, XSSFCellStyle cellStyle1, XSSFCellStyle errorCellStyle, int colStartNo) throws Exception {
		try {
			int columns = getTotalColumns(row);
			boolean hasErrors = false;
			
		    for (int col = colStartNo; col < columns; col+=2) {
		    	XSSFCell cell1 = row.getCell(col);
		        String eValue = getCelldata(cell1);
		        cell1.setCellStyle(cellStyle1);
		        
		        XSSFCell cell2 = row.getCell(col+1);
		        String aValue = getCelldata(cell2);
		        
		        if (!eValue.equalsIgnoreCase(aValue)) {
		        	cell1.setCellStyle(errorCellStyle);
		        	if (cell2 != null)
		        		cell2.setCellStyle(errorCellStyle);
		        	hasErrors = true;
		        }
		        else {
		        	cell1.setCellStyle(cellStyle1);
		        	if (cell2 != null)
		        		cell2.setCellStyle(cellStyle1);
		        }
		    }
		    
			return hasErrors;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private boolean setCellStyle(XSSFRow row, XSSFCellStyle cellStyle1, XSSFCellStyle cellStyle2, XSSFCellStyle errorCellStyle, int colStartNo) throws Exception {
		try {
			int columns = getTotalColumns(row);
			boolean hasErrors = false;
			XSSFCellStyle cellStyle = null;
			
		    for (int col = colStartNo, count = 0; col < columns; col+=2, count++) {
		    	if (count % 2 == 0)
		    		cellStyle = cellStyle1;
		    	else
		    		cellStyle = cellStyle2;
		    	
		    	XSSFCell cell1 = row.getCell(col);
		        String eValue = getCelldata(cell1);
		        
		        XSSFCell cell2 = row.getCell(col+1);
		        String aValue = getCelldata(cell2);
		        
		        boolean isDifferent = false;
		        if (!eValue.equalsIgnoreCase(aValue)) {
		        	isDifferent = isDifferent(eValue, aValue);
		        	isDifferent = isDifferent(aValue, eValue);
		        }
		        
		        if (isDifferent) {
		        	cell1.setCellStyle(errorCellStyle);
		        	cell2.setCellStyle(errorCellStyle);
		        	hasErrors = true;
		        }
		        else {
		        	cell1.setCellStyle(cellStyle);
		        	cell2.setCellStyle(cellStyle);
		        }
		    }
		    
			return hasErrors;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static boolean isDifferent(String value1, String value2) throws Exception {
		try {
			boolean isDifferent = false;
			switch (value1) {
			case "long":
				if (!value2.equalsIgnoreCase("integer") && !value2.equalsIgnoreCase("int"))
        			isDifferent = true;
				break;
			
			case "int":
			case "integer":
				if (!value2.equalsIgnoreCase("long") && !value2.equalsIgnoreCase("int") && !value2.equalsIgnoreCase("integer"))
        			isDifferent = true;
				break;
				
			case "bool":
			case "boolean":
				if (!value2.equalsIgnoreCase("boolean") && !value2.equalsIgnoreCase("bool"))
        			isDifferent = true;
				break;
				
			default:
				isDifferent = true;
				break;
			}
			
			return isDifferent;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void setCellStyle(XSSFRow row, XSSFCellStyle cellStyle1, XSSFCellStyle cellStyle2, XSSFCellStyle errorCellStyle, boolean[] hasErrors, int colStartNo) throws Exception {
		try {
			int columns = getTotalColumns(row);
			XSSFCellStyle cellStyle = null;
			int cCount = 0;
			
		    for (int col = colStartNo, count = 0; col < columns; col+=2, count++) {
		    	if (count % 2 == 0)
		    		cellStyle = cellStyle1;
		    	else
		    		cellStyle = cellStyle2;
		    	
		    	XSSFCell cell1 = row.getCell(col);
		        XSSFCell cell2 = row.getCell(col+1);
		        if (hasErrors[cCount]) {
		        	cell1.setCellStyle(errorCellStyle);
		        	cell2.setCellStyle(errorCellStyle);
		        }
		        else {
		        	cell1.setCellStyle(cellStyle);
		        	cell2.setCellStyle(cellStyle);
		        }
		        
		        cCount+=2;
		    }
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void setCellStyle(XSSFRow row, XSSFCellStyle cellStyle1, XSSFCellStyle errorCellStyle, boolean[] hasError, int rowNo, int colStartNo) throws Exception {
		try {
			int columns = getTotalColumns(row);
			int count = 0;
			
			for (int col = colStartNo; col < columns; col++) {
		    	XSSFCell cell = row.getCell(col);
		        if (hasError != null && hasError.length >= count && hasError[count]) {
		        	cell.setCellStyle(errorCellStyle);
		        }
		        else {
		        	cell.setCellStyle(cellStyle1);
		        }
		        
		        count++;
		    }
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void setCellStyle(XSSFRow row, XSSFCellStyle cellStyle1, XSSFCellStyle errorCellStyle, boolean[][] hasError, int rowNo, int colStartNo) throws Exception {
		try {
			int columns = getTotalColumns(row);
			
			if (ValidationHelper.isNotEmpty(hasError)) {
				int eCount = hasError.length;
				
				for (int i = 0; i < eCount; i++) {
					for (int col = colStartNo+i; col < columns; col+=eCount) {
				    	XSSFCell cell = row.getCell(col);
				        if (hasError[i] != null && hasError[i].length >= col && hasError[i][col])
				        	cell.setCellStyle(errorCellStyle);
				        else
				        	cell.setCellStyle(cellStyle1);
				    }
				}
			}
			else {
				for (int col = colStartNo; col < columns; col++) {
			    	XSSFCell cell = row.getCell(col);
			    	cell.setCellStyle(cellStyle1);
			    }
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Override
	public void setRowColor(int rowStartNo, int colStartNo) throws Exception {
		try {
			int rows = getTotalRows();
			XSSFCellStyle normalCellStyle = getCellStyle();
			
			for (int i = rowStartNo; i < rows+1; i++) {
				XSSFRow row = excelSheet.getRow(i);
			    
				for (int j = 0; j < colStartNo; j++) {
				    XSSFCell cell = row.getCell(j);
			        cell.setCellStyle(normalCellStyle);
				}
				
				setCellStyle(row, normalCellStyle, colStartNo);
			}

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean setRowColor(short errorColor, int rowStartNo, int colStartNo) throws Exception {
		try {
			int rows = getTotalRows();
			boolean[] hasError = new boolean[rows];
			XSSFCellStyle normalCellStyle = getCellStyle();
		    XSSFCellStyle errorCellStyle = getCellStyle(errorColor);
			
			for (int i = rowStartNo; i < rows; i++) {
				XSSFRow row = excelSheet.getRow(i);
			    
				for (int j = 0; j < colStartNo; j++) {
				    XSSFCell cell = row.getCell(j);
			        cell.setCellStyle(normalCellStyle);
				}
				
				hasError[i] = setCellStyle(row, normalCellStyle, errorCellStyle, colStartNo);
			}
			
			int index = GenericHelper.searchBooleanArray(hasError, true);
			if (index >= 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean setRowColor(short column1Color, short column2Color, short errorColor, int rowStartNo, int colStartNo) throws Exception {
		try {
			int rows = getTotalRows();
			boolean[] hasError = new boolean[rows+1];
			
			XSSFCellStyle normalCellStyle = getCellStyle();
		    XSSFCellStyle errorCellStyle = getCellStyle(errorColor);
		    XSSFCellStyle cellStyle1 = getCellStyle(column1Color);
		    XSSFCellStyle cellStyle2 = getCellStyle(column2Color);
			
			for (int i = rowStartNo; i < rows+1; i++) {
				XSSFRow row = excelSheet.getRow(i);
			    
				for (int j = 0; j < colStartNo; j++) {
				    XSSFCell cell = row.getCell(j);
			        cell.setCellStyle(normalCellStyle);
				}
			    
				hasError[i] = setCellStyle(row, cellStyle1, cellStyle2, errorCellStyle, colStartNo);
			}
			
			int index = GenericHelper.searchBooleanArray(hasError, true);
			if (index >= 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void setRowColor(short column1Color, short column2Color, short errorColor, boolean[][] hasErrors, int rowStartNo, int colStartNo) throws Exception {
		try {
			int rows = getTotalRows();
			XSSFCellStyle normalCellStyle = getCellStyle();
		    XSSFCellStyle errorCellStyle = getCellStyle(errorColor);
		    XSSFCellStyle cellStyle1 = getCellStyle(column1Color);
		    XSSFCellStyle cellStyle2 = getCellStyle(column2Color);
			int count = 0;
			
			for (int i = rowStartNo; i < rows; i++) {
				XSSFRow row = excelSheet.getRow(i);
			    
				for (int j = 0; j < colStartNo; j++) {
				    XSSFCell cell = row.getCell(j);
			        cell.setCellStyle(normalCellStyle);
				}
			    
				setCellStyle(row, cellStyle1, cellStyle2, errorCellStyle, hasErrors[count], colStartNo);
				count++;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void setRowColor(short errorColor, boolean[] hasError, int rowStartNo, int colStartNo) throws Exception {
		try {
			int rows = getTotalRows();
			XSSFCellStyle normalCellStyle = getCellStyle();
			XSSFCellStyle errorCellStyle = getCellStyle(errorColor);
			
			for (int i = rowStartNo; i < rows+1; i++) {
				XSSFRow row = excelSheet.getRow(i);
				
				for (int j = 0; j < colStartNo; j++) {
				    XSSFCell cell = row.getCell(j);
			        cell.setCellStyle(normalCellStyle);
				}
				
				setCellStyle(row, normalCellStyle, errorCellStyle, hasError, i, colStartNo);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void setRowColor(short columnColor, short errorColor, boolean[] hasError, int rowStartNo, int colStartNo) throws Exception {
		try {
			int rows = getTotalRows();
			
			XSSFCellStyle normalCellStyle = getCellStyle();
			XSSFCellStyle cellStyle = getCellStyle(columnColor);
			XSSFCellStyle errorCellStyle = getCellStyle(errorColor);
			
			for (int i = rowStartNo; i < rows+1; i++) {
				XSSFRow row = excelSheet.getRow(i);
				
				for (int j = 0; j < colStartNo; j++) {
				    XSSFCell cell = row.getCell(j);
			        cell.setCellStyle(normalCellStyle);
				}
			    
				setCellStyle(row, cellStyle, errorCellStyle, hasError, i, colStartNo);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void setRowColor(short errorColor, boolean[][] hasError, int rowStartNo, int colStartNo) throws Exception {
		try {
			int rows = getTotalRows();
			
			XSSFCellStyle normalCellStyle = getCellStyle();
			XSSFCellStyle errorCellStyle = getCellStyle(errorColor);
			int count = 0;
			
			for (int i = rowStartNo; i < rows; i++) {
				XSSFRow row = excelSheet.getRow(i);
				
				setCellStyle(row, normalCellStyle, errorCellStyle, hasError[count], i, colStartNo);
				count++;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void setRowColor1(short errorColor, boolean[][] hasError, int rowStartNo, int colStartNo) throws Exception {
		try {
			int rows = getTotalRows();
			
			XSSFCellStyle normalCellStyle = getCellStyle();
			XSSFCellStyle errorCellStyle = getCellStyle(errorColor);
			
			for (int i = rowStartNo; i < rows; i++) {
				XSSFRow row = excelSheet.getRow(i);
				
				setCellStyle(row, normalCellStyle, errorCellStyle, hasError, i, colStartNo);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void freezePanel(int rowNo, int colNo) throws Exception {
		try {
			excelSheet.createFreezePane(colNo, rowNo);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void createCell(XSSFRow row, int cellNo, Object value) throws Exception {
		try {
			XSSFCell cell = row.createCell(cellNo);
			
			if (value != null) {
				if(value instanceof String)
					cell.setCellValue((String)value);
				else if(value instanceof Integer)
					cell.setCellValue((Integer)value);
				else
					cell.setCellValue(value.toString());
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Override
	public void writeToExcel() throws Exception {
		try {
			FileOutputStream excel = new FileOutputStream( path );
	        excelWorkbook.write(excel);
	        excel.flush();
			excel.close();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Override
	public void closeExcel() throws Exception {
		try {
			excelWorkbook.close();
			if (opcPackage != null) {
			   	opcPackage.flush();
			   	opcPackage.close();
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public boolean setDynamicFunctionRowColor() throws Exception {
		try {
			int rows = getTotalRows();
			boolean hasErrors = false;
			XSSFCellStyle normalCellStyle = getCellStyle();
			XSSFCellStyle errorCellStyle = getCellStyle(redColor);
			
			for (int i = 1; i < rows; i++) {
				XSSFRow row = excelSheet.getRow(i);
				setCellStyle(row, normalCellStyle, 0);
				
				XSSFCell cell = row.getCell(5);
				int actualValue = Integer.parseInt(getCelldata(cell));
			    if (actualValue == 0) {
			    	cell.setCellStyle(errorCellStyle);
			    	hasErrors = true;
			    }
			    else
			    	cell.setCellStyle(normalCellStyle);
			}

			return hasErrors;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean setStaticLookupRowColor() throws Exception {
		try {
			int rows = getTotalRows();
			boolean hasErrors = false;
			XSSFCellStyle normalCellStyle = getCellStyle();
			XSSFCellStyle errorCellStyle = getCellStyle(redColor);
			
			for (int i = 1; i < rows; i++) {
				XSSFRow row = excelSheet.getRow(i);
				setCellStyle(row, normalCellStyle, 1);
				
				XSSFCell cell = row.getCell(0);
				String actualValue = getCelldata(row.getCell(3));
			    if (ValidationHelper.isFalse(actualValue)) {
			    	cell.setCellStyle(errorCellStyle);
			    	hasErrors = true;
			    }
			    else
			    	cell.setCellStyle(normalCellStyle);
			}

			return hasErrors;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean setGlobalVariables1RowColor() throws Exception {
		try {
			int rows = getTotalRows();
			int columns = getTotalColumns();
			boolean hasErrors = false;
						
			XSSFCellStyle normalCellStyle = getCellStyle();
		    XSSFCellStyle errorCellStyle = getCellStyle(redColor);
		    XSSFCellStyle cellStyle1 = getCellStyle(lightTurquoiseColor);
		    XSSFCellStyle cellStyle2 = getCellStyle(lemonChiffonColor);
			int startColNo = 5;
			
			for (int i = 2; i < rows; i++) {
				XSSFRow row = excelSheet.getRow(i);
				boolean[] reinitialized = new boolean[columns];
			    String[] values = new String[startColNo];
			    boolean markError = false;
			    
			    for (int j = 0; j < startColNo; j++) {
				    XSSFCell cell = row.getCell(j);
				    cell.setCellStyle(normalCellStyle);
				    values[j] = getCelldata(cell);
			    }
			    
			    String initialValue = values[2];
			    int assignCount = Integer.parseInt(values[3]);
		        int reinitilizeCount = Integer.parseInt(values[4]);
		        if (assignCount > 0 && reinitilizeCount == 0) {
		        	row.getCell(4).setCellStyle(errorCellStyle);
		        	hasErrors = true;
		        }
		        
			    for (int col = startColNo, count = 0; col < columns; col+=2, count++) {
			    	CellStyle cellStyle = null;
			    	if ((count % 2) == 0)
			    		cellStyle = cellStyle1;
			    	else
			    		cellStyle = cellStyle2;

			    	XSSFCell cell1 = row.getCell(col);
			    	int assignmentCount = Integer.parseInt(getCelldata(cell1));
			    	XSSFCell cell2 = row.getCell(col+1);
			    	String expression = getCelldata(cell2);
			    	
			    	if (assignmentCount > 0) {
			    		reinitialized[col] = checkReinitalizeExpression(values[1], initialValue, expression);
				    	
				    	if (reinitialized[col] || !initialValue.equals("")) {
				    		cell1.setCellStyle(cellStyle);
				    		cell2.setCellStyle(cellStyle);
				    	}
				    	else {
				    		cell1.setCellStyle(errorCellStyle);
				    		cell2.setCellStyle(errorCellStyle);
				    		markError = true;
				    		hasErrors = true;
				    	}
			    	}
			    	else {
			    		cell1.setCellStyle(cellStyle);
			    		cell2.setCellStyle(cellStyle);
			    	}
			    }
			    
			    if (markError)
			    	row.getCell(0).setCellStyle(errorCellStyle);
			}

			return hasErrors;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static boolean checkReinitalizeExpression(String datatype, String initialValue, String actualValue) throws Exception {
		try {
			String[] dataTypes = {"string", "integer", "decimal", "datetime"};
			String[] reinitializeFns = {"NullString()", "NullInteger()", "NullDecimal()", "NullDateTime()"};
			boolean reinitialized = false;
			
			switch (datatype) {
			case "boolean":
				reinitialized = checkBooleanReinitalizeExpression(initialValue, actualValue);
				break;

			default:
				int index = StringHelper.searchArray(dataTypes, datatype);
				
				if (index >= 0) {
					if (initialValue.equals("") && (actualValue.equals(reinitializeFns[index]) || actualValue.contains(reinitializeFns[index])))
						reinitialized = true;
					else if (actualValue.equals(initialValue))
						reinitialized = true;
				}
				break;
			}
			
			return reinitialized;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static boolean checkBooleanReinitalizeExpression(String initialValue, String actualValue) throws Exception {
		try {
			boolean reinitalized = false;
			
			if (initialValue.equals("") && !actualValue.equals(""))
					reinitalized = true;
			else if (actualValue.equals(initialValue) || actualValue.contains(initialValue))
					reinitalized = true;
			
			return reinitalized;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private int[] setCellStyle(int columns, XSSFRow row, XSSFCellStyle cellStyle1, XSSFCellStyle cellStyle2) throws Exception {
		try {
			int targetErrorCount = 0;
		    int expErrorCount = 0;
		    
			for (int col = 2, count = 2; col < columns; col+=2, count++) {
		    	XSSFCell cell1 = row.getCell(col);
		        int eValue1 = Integer.parseInt(getCelldata(cell1));
		        if (eValue1 == 0)
		        	targetErrorCount++;
		        
		        XSSFCell cell2 = row.getCell(col+1);
		        eValue1 = Integer.parseInt(getCelldata(cell2));
		        if (eValue1 == 0) {
		        	expErrorCount++;
		        }
		        
		        if ((count % 2) == 0) {
			        cell1.setCellStyle(cellStyle1);
			        cell2.setCellStyle(cellStyle1);
		        }
		        else {
		        	cell1.setCellStyle(cellStyle2);
			        cell2.setCellStyle(cellStyle2);
		        }
		    }
			
			return new int[] {targetErrorCount, expErrorCount};
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean setGlobalVariablesRowColor() throws Exception {
		try {
			int rows = getTotalRows();
			int columns = getTotalColumns();
			boolean hasErrors = false;
						
			XSSFCellStyle normalCellStyle = getCellStyle();
			XSSFCellStyle errorCellStyle = getCellStyle(redColor);
		    XSSFCellStyle cellStyle1 = getCellStyle(lightTurquoiseColor);
		    XSSFCellStyle cellStyle2 = getCellStyle(lemonChiffonColor);
			
			for (int i = 2; i < rows; i++) {
				XSSFRow row = excelSheet.getRow(i);
				
				for (int j = 0; j < 2; j++) {
				    XSSFCell cell = row.getCell(j);
			        cell.setCellStyle(normalCellStyle);
				}
				
			    int[] count = setCellStyle(columns, row, cellStyle1, cellStyle2);
			    int targetErrorCount = count[0];
			    int expErrorCount = count[1];
			    
			    int threshold = ((columns - 2) / 2);
			    XSSFCell cell = row.getCell(0);
			    if (targetErrorCount == threshold) {
			    	String initialValue = row.getCell(1).getStringCellValue();
			    	
			    	if (initialValue.equals("")) {
			    		row.getCell(2).setCellStyle(errorCellStyle);
			    		cell.setCellStyle(errorCellStyle);
				    	hasErrors = true;
			    	}
		    	}
		    	if (expErrorCount == threshold) {
		    		row.getCell(3).setCellStyle(errorCellStyle);
		    		cell.setCellStyle(errorCellStyle);
			    	hasErrors = true;
		    	}
			    
		    	if (!hasErrors) {
			    	cell.setCellStyle(normalCellStyle);
			    }
			}

			return hasErrors;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean setGlobalVariables2RowColor() throws Exception {
		try {
			int rows = getTotalRows();
			int columns = getTotalColumns();
			boolean hasErrors = false;
						
			XSSFCellStyle normalCellStyle = getCellStyle();
		    XSSFCellStyle errorCellStyle = getCellStyle(redColor);
		    XSSFCellStyle cellStyle1 = getCellStyle(lightTurquoiseColor);
		    XSSFCellStyle cellStyle2 = getCellStyle(lemonChiffonColor);
			
			for (int i = 2; i < rows; i++) {
				XSSFRow row = excelSheet.getRow(i);
			    boolean error = false;
			    XSSFCell cell0 = row.getCell(1);
			    cell0.setCellStyle(normalCellStyle);
			    String initialValue = cell0.getStringCellValue();
			    
			    for (int j = 2; j < 4; j++) {
			    	XSSFCell cell = row.getCell(j);
			    	
				    int value = Integer.parseInt(getCelldata(cell));
			    	if (value == 0 && initialValue.equals("")) {
			    		cell.setCellStyle(errorCellStyle);
			    		error = true;
			    	}
			    	else
			    		cell.setCellStyle(normalCellStyle);
			    }
			    
		        for (int col = 4, count = 0; col < columns; col+=3, count++) {
		        	boolean hasError = false;
		        	CellStyle cellStyle = cellStyle2;
		        	if ((count % 2) == 0)
		        		cellStyle = cellStyle1;
		        	
		        	XSSFCell cell1 = row.getCell(col);
			    	int aValue = (int) cell1.getNumericCellValue();
			    	
			    	XSSFCell cell2 = row.getCell(col+1);
			    	int eValue1 = (int) cell2.getNumericCellValue();
			    	
			    	XSSFCell cell3 = row.getCell(col+2);
			    	int eValue2 = (int) cell3.getNumericCellValue();
			    	
			    	if (aValue > 0 && (eValue1 == 0 && eValue2 == 0)) {
			    		hasError = true;
			    		error = true;
			    	}
			    	else if (aValue == 0 && (eValue1 > 0 || eValue2 > 0) && initialValue.equals("")) {
			    		hasError = true;
			    		error = true;
			    	}
			    	
		    		if (hasError) {
		    			cell1.setCellStyle(errorCellStyle);
		    			cell2.setCellStyle(errorCellStyle);
		    			cell3.setCellStyle(errorCellStyle);
		    		}
		    		else {
		    			cell1.setCellStyle(cellStyle);
			    		cell2.setCellStyle(cellStyle);
			    		cell3.setCellStyle(cellStyle);
		    		}
			    }
		        
		        cell0 = row.getCell(0);
		        if (error) {
		        	cell0.setCellStyle(errorCellStyle);
		        	hasErrors = true;
		        }
		        else
		        	cell0.setCellStyle(normalCellStyle);
			}
			
			return hasErrors;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean setCoreFieldsRowColor() throws Exception {
		try {
			int rows = getTotalRows();
			int columns = getTotalColumns();
			boolean hasErrors = false;
						
			XSSFCellStyle cellStyle = null;
		    XSSFCellStyle errorCellStyle = getCellStyle(redColor);
		    XSSFCellStyle cellStyle1 = getCellStyle(lightTurquoiseColor);
		    XSSFCellStyle cellStyle2 = getCellStyle(lemonChiffonColor);
			
			for (int i = 1; i < rows; i++) {
				XSSFRow row = excelSheet.getRow(i);
				
				for (int j = 0; j < columns; j+=3) {
					if (j % 2 == 0)
						cellStyle = cellStyle1;
					else
						cellStyle = cellStyle2;
					row.getCell(j).setCellStyle(cellStyle);
					
					XSSFCell cell1 = row.getCell(j+1);
					String actualPosition = getCelldata(cell1);
					XSSFCell cell2 = row.getCell(j+2);
					String expectedPosition = getCelldata(cell2);
					
					if (!expectedPosition.equals("") && !actualPosition.equalsIgnoreCase(expectedPosition)) {
						cell1.setCellStyle(errorCellStyle);
						cell2.setCellStyle(errorCellStyle);
						hasErrors = true;
					}
					else {
						cell1.setCellStyle(cellStyle);
						cell2.setCellStyle(cellStyle);
					}
				}
			}

			return hasErrors;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public boolean setPartKeyRowColor() throws Exception {
		try {
			int rows = getTotalRows();
			boolean hasErrors = false;
						
			XSSFCellStyle normalCellStyle = getCellStyle();
		    XSSFCellStyle errorCellStyle = getCellStyle(redColor);
		    
			for (int i = 2; i < rows; i++) {
				XSSFRow row = excelSheet.getRow(i);
				row.getCell(0).setCellStyle(normalCellStyle);
				
				XSSFCell cell1 = row.getCell(1);
				String value1 = getCelldata(cell1);
				XSSFCell cell2 = row.getCell(2);
				String value2 = getCelldata(cell2);
				
				if (!value1.equalsIgnoreCase(value2) || value1.equals("") || value2.equals("")) {
					cell1.setCellStyle(errorCellStyle);
					cell2.setCellStyle(errorCellStyle);
					hasErrors = true;
				}
				else {
					cell1.setCellStyle(normalCellStyle);
					cell2.setCellStyle(normalCellStyle);
				}
				
				XSSFCell cell3 = row.getCell(3);
				String value3 = getCelldata(cell3);
				if (value3.equalsIgnoreCase("Not Checked"))
					cell3.setCellStyle(errorCellStyle);
				else
					cell3.setCellStyle(normalCellStyle);
			}
			
			return hasErrors;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public boolean setPrimaryKeyRowColor() throws Exception {
		try {
			int rows = getTotalRows();
			boolean hasErrors = false;
						
			XSSFCellStyle normalCellStyle = getCellStyle();
		    XSSFCellStyle errorCellStyle = getCellStyle(redColor);
		    
			for (int i = 1; i < rows; i++) {
				XSSFRow row = excelSheet.getRow(i);
				row.getCell(0).setCellStyle(normalCellStyle);
				
				XSSFCell cell1 = row.getCell(1);
				String value1 = getCelldata(cell1) + "_id";
				XSSFCell cell2 = row.getCell(2);
				String value2 = getCelldata(cell2);
				
				if (!value1.equals(value2)) {
					cell1.setCellStyle(errorCellStyle);
					cell2.setCellStyle(errorCellStyle);
					hasErrors = true;
				}
				else {
					cell1.setCellStyle(normalCellStyle);
					cell2.setCellStyle(normalCellStyle);
				}
			}
			
			return hasErrors;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public boolean setQMFrequencyRowColor() throws Exception {
		try {
			int rows = getTotalRows();
			boolean hasErrors = false;
						
			XSSFCellStyle normalCellStyle = getCellStyle();
		    XSSFCellStyle errorCellStyle = getCellStyle(redColor);
		    
			for (int i = 1; i < rows; i++) {
				XSSFRow row = excelSheet.getRow(i);
				row.getCell(0).setCellStyle(normalCellStyle);
				row.getCell(2).setCellStyle(normalCellStyle);
				
				XSSFCell cell1 = row.getCell(1);
				String value1 = getCelldata(cell1);
				XSSFCell cell2 = row.getCell(3);
				String value2 = getCelldata(cell2);
				
				if (!value1.equals(value2)) {
					cell1.setCellStyle(errorCellStyle);
					cell2.setCellStyle(errorCellStyle);
					hasErrors = true;
				}
				else {
					cell1.setCellStyle(normalCellStyle);
					cell2.setCellStyle(normalCellStyle);
				}
				
				XSSFCell cell3 = row.getCell(4);
				String value3 = getCelldata(cell3);
				if (value3.equals(""))
					cell3.setCellStyle(errorCellStyle);
				else
					cell3.setCellStyle(normalCellStyle);
				
				XSSFCell cell4 = row.getCell(5);
				String value4 = getCelldata(cell4);
				if (value4.endsWith("00:00:00"))
					cell4.setCellStyle(normalCellStyle);
				else {
					cell4.setCellStyle(errorCellStyle);
					hasErrors = true;
				}
			}
			
			return hasErrors;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean setChangePredictorRowColor(String[] result) throws Exception {
		try {
			int rows = getTotalRows();
			int columns = getTotalColumns();
			boolean hasErrors = false;
						
			XSSFCellStyle normalCellStyle = getCellStyle();
		    XSSFCellStyle errorCellStyle = getCellStyle(redColor);
		    XSSFCellStyle newCellStyle = getCellStyle(turquoiseColor);
		    XSSFCellStyle missingCellStyle = getCellStyle(orange);
		    
			for (int i = 1; i < rows; i++) {
				XSSFRow row = excelSheet.getRow(i);
				CellStyle cellStyle = null;
				
				if (result[i] != null) {
					switch (result[i]) {
					case "New":
						cellStyle = newCellStyle;
						break;
						
					case "Same":
						cellStyle = normalCellStyle;
						break;
						
					case "Not Same":
						cellStyle = errorCellStyle;
						hasErrors = true;
						break;
						
					case "Missing":
						cellStyle = missingCellStyle;
						hasErrors = true;
						break;
	
					default:
						cellStyle = normalCellStyle;
						break;
					}
				}
				else {
					cellStyle = missingCellStyle;
					hasErrors = true;
				}
				
				for (int j = 0; j < columns; j++)
					row.getCell(j).setCellStyle(cellStyle);
			}
			
			return hasErrors;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getPrefix(String columnName) throws Exception {
		try {
			String prefix = columnName.substring(0, 4);
			if (prefix.endsWith("_"))
				return prefix.substring(0, 3);
			else
				return prefix;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean setTableStructureRowColor() throws Exception {
		try {
			int rows = getTotalRows();
			int columns = getTotalColumns();
			boolean hasErrors = false;
			
			XSSFRow row1 = excelSheet.getRow(1);
			String firstXMLColumn = row1.getCell(2).getStringCellValue();
			String firstTDColumn = row1.getCell(3).getStringCellValue();
			String xmlPrefix = getPrefix(firstXMLColumn);
			String tdPrefix = getPrefix(firstTDColumn);
			
			XSSFCellStyle cellStyle1 = getCellStyle(lightTurquoiseColor);
			XSSFCellStyle cellStyle2 = getCellStyle(lemonChiffonColor);
		    XSSFCellStyle errorCellStyle = getCellStyle(redColor);
		    
			for (int i = 1; i < rows; i++) {
				XSSFRow row = excelSheet.getRow(i);
				int count = 0;
				
				for (int j = 0; j < columns; j+=2) {
					CellStyle cellStyle = cellStyle2;
					if ((count % 2) == 0)
						cellStyle = cellStyle1;
					
					XSSFCell cell1 = row.getCell(j);
					XSSFCell cell2 = row.getCell(j+1);
					String value1 = getCelldata(cell1);
					String value2 = getCelldata(cell2);
					
					if (j == 2 || j == 4) {
						String tempValue1 = value1.replace(xmlPrefix, "");
						String tempValue2 = value2.replace(tdPrefix, "");
						
						if (!value1.equals(value2) && !tempValue1.equals(tempValue2)) {
							cell1.setCellStyle(errorCellStyle);
							cell2.setCellStyle(errorCellStyle);
							hasErrors = true;
						}
						else {
							cell1.setCellStyle(cellStyle);
							cell2.setCellStyle(cellStyle);
						}
					}
					else {
						if (!value1.equals(value2)) {
							boolean isDifferent = isDifferent(value1, value2);
							if (!isDifferent)
								isDifferent = isDifferent(value1, value2);
				        	
				        	if (isDifferent) {
								cell1.setCellStyle(errorCellStyle);
								cell2.setCellStyle(errorCellStyle);
								hasErrors = true;
				        	}
				        	else {
				        		cell1.setCellStyle(cellStyle);
								cell2.setCellStyle(cellStyle);
				        	}
						}
						else {
			        		cell1.setCellStyle(cellStyle);
							cell2.setCellStyle(cellStyle);
			        	}
					}
					
					count++;
				}
			}
			
			return hasErrors;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean setKPICasesRowColor() throws Exception {
		try {
			int rows = getTotalRows();
			int columns = getTotalColumns();
			boolean hasErrors = false;
			
			XSSFCellStyle normalCellStyle = getCellStyle();
		    XSSFCellStyle errorCellStyle = getCellStyle(redColor);
		    
			for (int i = 1; i < rows; i++) {
				XSSFRow row = excelSheet.getRow(i);
				
				for (int j = 0; j < columns; j++) {
					row.getCell(j).setCellStyle(normalCellStyle);
				}
				
				XSSFCell cell1 = row.getCell(2);
				XSSFCell cell2 = row.getCell(3);
				XSSFCell cell3 = row.getCell(4);
				String value1 = getCelldata(cell1);
				String value2 = getCelldata(cell2);
				String value3 = getCelldata(cell3);
				
				switch (value1) {
				case "":
					if (value2.equals("") && value3.equals("")) {
						cell1.setCellStyle(errorCellStyle);
						cell2.setCellStyle(errorCellStyle);
						cell3.setCellStyle(errorCellStyle);
						hasErrors = true;
					}
					else if (!value2.equals("") && value3.equals("")) {
						cell1.setCellStyle(errorCellStyle);
						cell3.setCellStyle(errorCellStyle);
						hasErrors = true;
					}
					break;

				default:
					if (value2.equals("")) {
						cell2.setCellStyle(errorCellStyle);
						hasErrors = true;
					}
					break;
				}
			}
			
			return hasErrors;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean setReconFieldsRowColor() throws Exception {
		try {
			int rows = getTotalRows();
			int columns = getTotalColumns();
			boolean hasErrors = false;
			
			XSSFCellStyle normalCellStyle = getCellStyle();
		    XSSFCellStyle errorCellStyle = getCellStyle(redColor);
		    
			for (int i = 2; i < rows; i++) {
				XSSFRow row = excelSheet.getRow(i);
				
				for (int j = 0; j < columns; j++) {
					row.getCell(j).setCellStyle(normalCellStyle);
				}
				
				XSSFCell cell1 = row.getCell(columns-1);
				String value1 = getCelldata(cell1);
				
				if (value1.equals("")) {
					cell1.setCellStyle(errorCellStyle);
					hasErrors = true;
				}
			}
			
			return hasErrors;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean setMeasureFilterRowColor() throws Exception {
		try {
			int rows = getTotalRows();
			int columns = getTotalColumns();
			boolean hasErrors = false;
			
			XSSFCellStyle normalCellStyle = getCellStyle();
		    XSSFCellStyle errorCellStyle = getCellStyle(redColor);
		    
			for (int i = 1; i < rows; i++) {
				XSSFRow row = excelSheet.getRow(i);
				
				for (int j = 0; j < columns; j++) {
					row.getCell(j).setCellStyle(normalCellStyle);
				}
				
				XSSFCell cell1 = row.getCell(columns-1);
				String value1 = getCelldata(cell1);
				
				if (!value1.equals("Yes")) {
					cell1.setCellStyle(errorCellStyle);
					hasErrors = true;
				}
			}
			
			return hasErrors;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean setLDCObjectKeyRowColor() throws Exception {
		try {
			int rows = getTotalRows();
			int columns = getTotalColumns();
			boolean hasErrors = false;
			
			XSSFCellStyle normalCellStyle = getCellStyle();
		    XSSFCellStyle errorCellStyle = getCellStyle(redColor);
		    
			for (int i = 1; i < rows; i++) {
				XSSFRow row = excelSheet.getRow(i);
				
				for (int j = 0; j < columns; j++) {
					row.getCell(j).setCellStyle(normalCellStyle);
				}
				
				XSSFCell cell1 = row.getCell(columns-1);
				String value1 = getCelldata(cell1);
				XSSFCell cell2 = row.getCell(columns-2);
				String value2 = getCelldata(cell2);
				
				if (!value1.equals(value2)) {
					cell1.setCellStyle(errorCellStyle);
					cell2.setCellStyle(errorCellStyle);
					hasErrors = true;
				}
			}
			
			return hasErrors;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean setGVAssignmentOrderRowColor() throws Exception {
		try {
			int rows = getTotalRows();
			int columns = getTotalColumns() - 1;
			boolean hasErrors = false;
			
			XSSFCellStyle normalCellStyle = getCellStyle();
		    XSSFCellStyle errorCellStyle = getCellStyle(redColor);
		    
			for (int i = 1; i < rows; i++) {
				XSSFRow row = excelSheet.getRow(i);
				
				for (int j = 0; j < columns; j++) {
					row.getCell(j).setCellStyle(normalCellStyle);
				}
				
				XSSFCell cell1 = row.getCell(columns);
				cell1.setCellStyle(errorCellStyle);
				hasErrors = true;
			}
			
			return hasErrors;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static boolean isPresent(String expression, String value) throws Exception {
		try {
			String[] expressions = {", " + value + ")", "'" + value + "'"};
			
			for (int i = 0; i < expressions.length; i++) {
				if (expression.contains(expressions[i]))
					return true;
			}
			
			return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean setLDCIndicatorRowColor() throws Exception {
		try {
			int rows = getTotalRows();
			int columns = getTotalColumns();
			boolean hasErrors = false;
			
			XSSFCellStyle normalCellStyle = getCellStyle();
		    XSSFCellStyle errorCellStyle = getCellStyle(redColor);
		    
			for (int i = 2; i < rows; i++) {
				XSSFRow row = excelSheet.getRow(i);
				
				for (int j = 0; j < columns; j++) {
					row.getCell(j).setCellStyle(normalCellStyle);
				}
				
				XSSFCell cell1 = row.getCell(columns-2);
				String columnName = cell1.getStringCellValue();
				
				if (columnName.equals("")) {
					cell1.setCellStyle(errorCellStyle);
					hasErrors = true;
				}
				else {
					XSSFCell cell2 = row.getCell(columns-1);
					String expression = cell2.getStringCellValue();
					
					if (expression.equals("")) {
						cell2.setCellStyle(errorCellStyle);
						hasErrors = true;
					}
					else {
						String initalLeg = row.getCell(3).getStringCellValue();
						String intermediateLeg = row.getCell(4).getStringCellValue();
						String finalLeg = row.getCell(5).getStringCellValue();
						boolean intialLegPresent = isPresent(expression, initalLeg);
						boolean intermediateLegPresent = isPresent(expression, intermediateLeg);
						boolean finalLegPresent = isPresent(expression, finalLeg);
						
						if (!intialLegPresent || !intermediateLegPresent || !finalLegPresent) {
							cell2.setCellStyle(errorCellStyle);
							hasErrors = true;
						}
					}
				}
			}
			
			return hasErrors;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}