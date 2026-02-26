package com.subex.automation.helpers.db;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.subex.automation.helpers.report.ReportHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class ExecuteQueryHelper extends AcceptanceTest {

	public void executeQuery(String dbType, String query, HashMap<String, String[]> values) throws Exception {
		DBHelper dbHelper1 = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String databaseType = null;
			if (dbType.equalsIgnoreCase("Reference")) {
				dbHelper1 = DBHelper.createReferenceConnection();
				databaseType = configProp.getDbType();
			}
			else {
				dbHelper1 = DBHelper.createUsageConnection();
				databaseType = configProp.getUsageDBType();
			}
			
			TestDataHelper testData = new TestDataHelper();
			Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
			int count = 1;
			
			for (int x = 0; x < values.size(); x++) {
				query = testData.updateQueryValue(values, query, x);
				String[] queryType = query.split(" ");
				
				if(queryType[0].equalsIgnoreCase("insert") || queryType[0].equalsIgnoreCase("delete") || queryType[0].equalsIgnoreCase("create") || queryType[0].equalsIgnoreCase("Alter") || queryType[0].equalsIgnoreCase("update")) {
					dbHelper1.dbConnection.createStatement().executeUpdate(query.replace(";", ""));
					
					if(!databaseType.equalsIgnoreCase("sqlserver"))
						dbHelper1.dbConnection.createStatement().executeUpdate("commit");
				}
				else {
					stmt = dbHelper1.dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
					rs = stmt.executeQuery(query.replace(";", ""));
					
					if (rs != null) {
						int rowCount = QueryHelper.getRowCount(rs);
						int colCount = QueryHelper.getColumnCount(rs);
						
						
						if (rowCount > 0 && colCount > 0) {
							if (x == 0) {
								Object[] header = QueryHelper.addHeader(rs);
								data.put(0, header);
								rs.first();
							}
							
							for (int i = 0; i < rowCount; i++) {
								Object[] obj = new Object[colCount];
	
								for (int j = 0; j < colCount; j++) {
									obj[j] = rs.getString(colCount);
								}
								
								data.put(count, obj);
								count++;
								rs.next();
							}
						}
						
						QueryHelper.createFile(data, "Query");
					}
					else {
						ReportHelper.addWarning("The query returned 0 rows");
					}
				}
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (dbHelper1 != null) {
				if (!rs.isClosed())
					rs.close();
				stmt.close();
				dbHelper1.closeConnection();
			}
		}
	}
	
	public void executeQuery(String queryName, String dbType, String query, HashMap<String, String[]> values) throws Exception {
		DBHelper dbHelper1 = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String databaseType = null;
			if (dbType.equalsIgnoreCase("Reference")) {
				dbHelper1 = DBHelper.createReferenceConnection();
				databaseType = configProp.getDbType();
			}
			else {
				dbHelper1 = DBHelper.createUsageConnection();
				databaseType = configProp.getUsageDBType();
			}
			
			TestDataHelper testData = new TestDataHelper();
			int qCount = 1;
			if (values.size() > 0) {
				qCount = testData.getTestDataCount(values);
				if (qCount == 0)
					qCount = 1;
			}
			
			String[] queryType = query.split(" ");
			
			if(queryType[0].equalsIgnoreCase("insert") || queryType[0].equalsIgnoreCase("delete") || queryType[0].equalsIgnoreCase("create") || queryType[0].equalsIgnoreCase("Alter") || queryType[0].equalsIgnoreCase("update")) {
				for (int x = 0; x < qCount; x++) {
					String actualQuery = testData.updateQueryValue(values, query, x);
					dbHelper1.dbConnection.createStatement().executeUpdate(actualQuery.replace(";", ""));
					
					if(!databaseType.equalsIgnoreCase("sqlserver"))
						dbHelper1.dbConnection.createStatement().executeUpdate("commit");
				}
			}
			else {
				Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
				int count = 1;
				stmt = dbHelper1.dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				
				for (int x = 0; x < qCount; x++) {
					String actualQuery = testData.updateQueryValue(values, query, x);
					rs = stmt.executeQuery(actualQuery.replace(";", ""));
					
					if (rs != null) {
						int rowCount = QueryHelper.getRowCount(rs);
						int colCount = QueryHelper.getColumnCount(rs);
						
						if (rowCount > 0 && colCount > 0) {
							if (x == 0) {
								Object[] header = QueryHelper.addHeader(rs);
								data.put(0, header);
								rs.first();
							}
							else
								rs.next();
							
							for (int i = 0; i < rowCount; i++) {
								Object[] obj = new Object[colCount];
	
								for (int j = 0; j < colCount; j++) {
									obj[j] = rs.getString(j+1);
								}
								
								data.put(count, obj);
								count++;
								rs.next();
							}
							
						}
						else {
							ReportHelper.addWarning("The query returned no rows");
						}
					}
					else {
						ReportHelper.addWarning("The query returned no rows");
					}
				}
				
				if (data != null && data.size() > 0)
					QueryHelper.createFile(data, queryName);
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			try {
				
				if (dbHelper1 != null) {
					if (rs != null && !rs.isClosed())
						rs.close();
					if (stmt != null && !stmt.isClosed())
						stmt.close();
					dbHelper1.closeConnection();
				}
				
			} catch (Exception e2) {
				FailureHelper.setErrorMessage(e2);
				throw e2;
			}
		}
	}
}