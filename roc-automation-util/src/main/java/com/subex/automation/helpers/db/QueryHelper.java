package com.subex.automation.helpers.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelWriter;
import com.subex.automation.helpers.report.ReportHelper;
import com.subex.automation.helpers.scripts.ExecuteScript;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class QueryHelper extends AcceptanceTest {

	public static int getRowCount(ResultSet rs) throws Exception {
		try {
			int length = 0;
			
			if (rs != null) {
				rs.last();
				length = rs.getRow();
				rs.beforeFirst();
			}
			
			return length;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getColumnCount(ResultSet rs) throws Exception {
		try {
			int length = 0;
			
			if (rs != null) {
				ResultSetMetaData rsmd = rs.getMetaData();
				length = rsmd.getColumnCount();
			}
			
			return length;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getFirstTableName(ResultSet rs) throws Exception {
		try {
			String tableName = null;
			
			if (rs != null) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int length = rsmd.getColumnCount();
				
				for (int i = 0; i < length; i++)
					if (ValidationHelper.isEmpty(tableName))
						tableName = rsmd.getTableName(i+1);
			}
			
			return tableName;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[] getColumnNames(ResultSet rs) throws Exception {
		try {
			String[] columnName = new String[10];
			
			if (rs != null) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int length = rsmd.getColumnCount();
				columnName = new String[length];
				
				for (int i = 0; i < length; i++) {
					columnName[i] = rsmd.getColumnName(i+1);
				}
			}
			
			return columnName;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static Object[] addHeader(ResultSet rs1) throws Exception {
		try {
			String[] columnNames = getColumnNames(rs1);
			int length = columnNames.length;
			Object[] obj = GenericHelper.createObject(length);
			
			for (int i = 0; i < columnNames.length; i++) {
				obj[i] = columnNames[i];
			}
			
			return obj;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static ArrayList<String> addHeaderArray(ResultSet rs1) throws Exception {
		try {
			String[] columnNames = getColumnNames(rs1);
			ArrayList<String> header = new ArrayList<String>();
			int length = columnNames.length;
			
			for (int i = 0; i < length; i++) {
				if (length > 1)
					header.add(columnNames[i] + "\t");
				else
					header.add(columnNames[i]);
			}
			
			return header;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String createFile(Map<Integer, Object[]> data, String queryName) throws Exception {
		try {
			
			if (data != null) {
				String oPath = automationPath + "\\Query_Results\\";
				if (!FileHelper.checkDirectoryExists(oPath))
					FileHelper.makeDirectory(oPath);
				
				String oFileName = queryName + "_Report_" + DateHelper.getCurrentDateTime("yyyyMMdd_HHmmss") + ".xlsx";
				boolean hasErrors = ExcelWriter.writeToExcel(oPath, oFileName, queryName, data, "Query");
				
				if (hasErrors)
					ReportHelper.addWarning(oFileName + " has error details.");
				else
					ReportHelper.updateStepKey("Result File", "DarkViolet", oFileName);
				
				String fileName = GenericHelper.updatePath(oPath + "\\" + oFileName);
				return fileName;
			}
			else {
				ReportHelper.addWarning("The query returned 0 rows");
				return null;
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public static String createCSVFile(Map<Integer, ArrayList<String>> data, String queryName) throws Exception {
		try {
			
			if (data != null) {
				String oPath = "Query_Results\\";
				String oFileName = queryName + "_Report_" + DateHelper.getCurrentDateTime("yyyyMMdd_HHmmss") + ".csv";
				FileHelper.writeToFile(oPath + oFileName, data);
								
				String fileName = GenericHelper.updatePath(oPath + oFileName);
				return fileName;
			}
			else {
				ReportHelper.addWarning("The query returned 0 rows");
				return null;
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[][] getResult(String[] dbDetails, String query) throws Exception {
		try {
			ResultSet rs = ExecuteScript.exeQuery(query, dbDetails);
			int rowCount = getRowCount(rs);
			
			if (rowCount > 0) {
				int colCount = getColumnCount(rs);
				String[][] resultValues = new String[rowCount][colCount];
				int rowIndex = 0;
				
				while (rs.next()) {
				    List<Object> rowData = new ArrayList<>();
				    for (int i = 1; i <= colCount; i++) {
				        rowData.add(rs.getObject(i));
				    }
				    
				    for (int colIndex = 0; colIndex < colCount; colIndex++) {
				        Object columnObject = rowData.get(colIndex);
				        if (columnObject != null) {
				        	resultValues[rowIndex][colIndex] = columnObject.toString();
				        }
				    }
				    rowIndex++;
				}
				
				return resultValues;
			}
			else
				return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[][] getResult(Connection connection, String query) throws Exception {
		try {
			ResultSet rs = ExecuteScript.exeQuery(connection, query);
			int rowCount = getRowCount(rs);
			
			if (rowCount > 0) {
				int colCount = getColumnCount(rs);
				String[][] resultValues = new String[rowCount][colCount];
				int rowIndex = 0;
				
				while (rs.next()) {
				    List<Object> rowData = new ArrayList<>();
				    for (int i = 1; i <= colCount; i++) {
				        rowData.add(rs.getObject(i));
				    }
				    
				    for (int colIndex = 0; colIndex < colCount; colIndex++) {
				        Object columnObject = rowData.get(colIndex);
				        if (columnObject != null) {
				        	resultValues[rowIndex][colIndex] = columnObject.toString();
				        }
				    }
				    rowIndex++;
				}
				
				return resultValues;
			}
			else
				return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}