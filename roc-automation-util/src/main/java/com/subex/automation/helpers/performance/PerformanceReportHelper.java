package com.subex.automation.helpers.performance;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.subex.automation.helpers.file.ExcelWriter;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class PerformanceReportHelper extends AcceptanceTest {
	public static boolean reportGenerated = false;
	
	public static XSSFWorkbook workbook = null;
	public static XSSFSheet sheet = null;
	public static XSSFSheet summarySheet = null;
	public static Row row = null;
	public static Cell cell = null;
	
	public PerformanceReportHelper() throws Exception {
		try {
			workbook = new XSSFWorkbook();
			summarySheet = workbook.getSheet("Summary");
			if (summarySheet == null)
				summarySheet = workbook.createSheet("Summary");
			sheet = workbook.getSheet("Net Report");
			if (sheet == null)
				sheet = workbook.createSheet("Net Report");
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addHeader(String[] values) throws Exception {
		try {
			
			row = sheet.createRow(0);
			
			for (int colnum = 0; colnum < values.length; colnum++) {
				cell = row.createCell(colnum);
				cell.setCellValue(values[colnum]);
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addData(int rowNo, int colNo, String value) throws Exception {
		try {
			
			row = sheet.createRow(rowNo);
			cell = row.createCell(colNo);
			cell.setCellValue(value);
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addData(int rowNo, String[] values) throws Exception {
		try {
			
			row = sheet.createRow(rowNo);
			
			for (int colnum = 0; colnum < values.length; colnum++) {
				cell = row.createCell(colnum);
				cell.setCellValue(values[colnum]);
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addData(int rowNo, ArrayList<String> values) throws Exception {
		try {
			
			row = sheet.createRow(rowNo);
			
			for (int colnum = 0; colnum < values.size(); colnum++) {
				cell = row.createCell(colnum);
				cell.setCellValue(values.get(colnum));
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addData(int colNo, String value) throws Exception {
		try {
			
			cell = row.createCell(colNo);
			cell.setCellValue(value);
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addSummaryData(int rowNo, String[] values) throws Exception {
		try {
			
			Row row = summarySheet.createRow(rowNo);
			Cell cell = null;
			
			for (int colnum = 0; colnum < values.length; colnum++) {
				cell = row.createCell(colnum);
				cell.setCellValue(values[colnum]);
			}
			
			ExcelWriter.autoSizeColumns(summarySheet);
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createReportFile(String reportDirectory, String fileName) throws Exception {
		FileOutputStream out = null;
		
		try {
			String excelFileName = reportDirectory + "\\" + fileName.substring(0, (fileName.length() - 4)) + ".xlsx";
			File file = new File(excelFileName);
			if (!file.exists())
				file.createNewFile();
			out = new FileOutputStream(file);
			
			ExcelWriter.setStyle(workbook, sheet);
			ExcelWriter.setRowStyle(workbook, sheet);
			ExcelWriter.autoSizeColumns(sheet);
			workbook.write(out);
			workbook.close();
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
	
	public void closeReportFile() throws Exception {
		try {
			
			workbook.close();
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String convertTime(double totalTime) throws Exception {
		try {
			String timeTaken = "";
			
			long hours = (long) (totalTime / 3600);
			double reminder = totalTime - (hours * 3600);
			if (hours > 0)
				timeTaken = hours + " hrs ";
			
			long mins = (long) (reminder / 60);
			reminder = reminder - (mins * 60);
			if (mins > 0)
				timeTaken = timeTaken + mins + " mins ";
			
			long secs = (long) Math.abs(reminder);
			reminder = reminder - secs;
			if (secs > 0)
				timeTaken = timeTaken + secs + " secs ";
			
			long millisecs = (long) (reminder * 1000);
			if (millisecs > 0)
				timeTaken = timeTaken + millisecs + " millisecs ";
			return timeTaken;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
//	private String getScreen(String fileName) throws Exception {
//		try {
//			String screen = fileName.substring(0, fileName.length() - 20);
//			
//			return screen;
//		} catch (Exception e) {
//			FailureHelper.setErrorMessage(e);
//			throw e;
//		}
//	}
	
	public void updatePerformanceSummary(String fileName, String timeTaken) throws Exception {
		try {
			
//			int count = 1;
//			String screen = getScreen(fileName);
//			if (performanceSummary != null)
//				count = performanceSummary.size();
//			
//			performanceSummary.put(count, new Object[] {screen, timeTaken});
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}