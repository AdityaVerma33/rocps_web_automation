package com.subex.automation.helpers.scripts;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

import com.subex.automation.helpers.db.DBHelper;
import com.subex.automation.helpers.db.DatabaseHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class ExecuteScript extends AcceptanceTest {
	static String s = null;
	
	public static void printMsgs(Process p) throws Exception {
		BufferedReader stdError = null;
		BufferedReader stdInput = null;
		try {
			stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
	
	        while ((s = stdInput.readLine()) != null)
	        	Log4jHelper.logInfo(s);
	
			while ((s = stdError.readLine()) != null)
				Log4jHelper.logInfo(s);
			
			p.waitFor();
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (stdInput != null)
				stdInput.close();
			if (stdError != null)
				stdError.close();
		}
	}
	
	/*
	 * Executes scripts, path should be provided along with the file name
	 * @throws Exception 
	 */
	public static int exeScript(String file, String dbType) throws Exception {
		try {
			DatabaseHelper dbHelper = new DatabaseHelper();
			dbHelper.executeScript(dbType, automationOS, file);
			
			return 0;
//			Process p = Runtime.getRuntime().exec("bash " + "eclipse/scripts/Execute \"" +  file + "\" " + dbType + " " + suiteName);
//			printMsgs(p);
//			return p.exitValue();
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * Executes Sql/Oracle queries
	 * @param configProp 
	 * @throws Exception 
	 */
	 public static ResultSet exeQuery(String strQuery) throws Exception {
		 DBHelper dbHelper1 = null;
		 String command = null;
		 
		 try {
			ResultSet tableData = null;
			dbHelper1 = DBHelper.createReferenceConnection();
			Statement stmt = dbHelper1.dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
			command = strQuery.split(" ")[0].toLowerCase();
			 switch (command) {
			 case "insert":
			 case "delete":
			 case "create":
			 case "alter":
			 case "update":
				 stmt.executeUpdate(strQuery);
				 if(!configProp.getDbType().equalsIgnoreCase("sqlserver"))
					 stmt.executeUpdate("commit");
				 Log4jHelper.logInfo(command.toUpperCase() + " query completed successfully");
				 break;

			 case "select":
				 tableData = stmt.executeQuery(strQuery.replace(";", ""));
				 break;
				
			 default:
				 stmt.execute(strQuery);
				 break;
			}
			
			return tableData;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (dbHelper1 != null && !command.equals("select"))
				dbHelper1.closeConnection();
		}
	 }
	 
	 public static ResultSet exeQuery(String strQuery, String[] dbDetails) throws Exception {
		 DBHelper dbHelper1 = null;
		 String command = null;
		 
		 try {
			 ResultSet tableData = null;
			 dbHelper1 = DBHelper.createConnection(dbDetails[0], dbDetails[1], dbDetails[2], dbDetails[3], dbDetails[4], dbDetails[5]);
			 Statement stmt = dbHelper1.dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			 
			 command = strQuery.split(" ")[0].toLowerCase();
			 switch (command) {
			 case "insert":
			 case "delete":
			 case "create":
			 case "alter":
			 case "update":
				 stmt.executeUpdate(strQuery);
				 if(!configProp.getDbType().equalsIgnoreCase("sqlserver"))
					 stmt.executeUpdate("commit");
				 Log4jHelper.logInfo(command.toUpperCase() + " query completed successfully");
				 break;

			 case "select":
				 tableData = stmt.executeQuery(strQuery.replace(";", ""));
				 break;
				
			 default:
				 stmt.execute(strQuery);
				 break;
			}
			
			return tableData;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (dbHelper1 != null && !command.equals("select"))
				dbHelper1.closeConnection();
		}
	 }
	 
	 /*
		 * Executes Sql/Oracle queries
		 * @param configProp 
	 	 * @throws Exception 
		 */
		 public static ResultSet exeUsgQuery(String strQuery) throws Exception {
			 DBHelper dbHelper1 = null;
			 try {
				ResultSet tableData = null;
				dbHelper1 = DBHelper.createUsageConnection();

				if(strQuery.contains("insert") || strQuery.contains("delete") || strQuery.contains("update") || strQuery.contains("create") || strQuery.contains("alter") ){
					dbHelper1.dbConnection.createStatement().executeUpdate(strQuery);
					if(!configProp.getDbType().equalsIgnoreCase("sqlserver"))
						dbHelper1.dbConnection.createStatement().executeUpdate("commit");
					Log4jHelper.logInfo(strQuery.substring(0, 6).trim() + " query completed successfully");
				}
				else {
					tableData=dbHelper1.dbConnection.createStatement().executeQuery(strQuery);
				}
			
				return tableData;
			} catch (SQLSyntaxErrorException e) {
				if (e.getMessage().contains("table or view does not exist") && strQuery.startsWith("truncate")) {
					return null;
				}
				else {
					FailureHelper.setErrorMessage(e);
					throw e;
				}
			} catch(Exception e) {
				FailureHelper.setErrorMessage(e);
				throw e;
			} finally {
//				 if (dbHelper1 != null)
//					 dbHelper1.closeConnection();
			}
		 }

		 /* The following method invokes Stream controller and Task controller
	 * @throws Exception 
	*/
	public static int runStreamTaskControllers() throws Exception {
		try {
			Process p = Runtime.getRuntime().exec("bash " + automationPath + "eclipse/data/Execute controllers");
			printMsgs(p);
			return p.exitValue();
		}
		catch(Exception e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	 public static ResultSet exeQuery(Connection connection, String strQuery) throws Exception {
		 try {
			 ResultSet tableData = null;
			 Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			 
			 String command = strQuery.split(" ")[0].toLowerCase();
			 switch (command) {
			 case "insert":
			 case "delete":
			 case "create":
			 case "alter":
			 case "update":
				 stmt.executeUpdate(strQuery);
				 if(!configProp.getDbType().equalsIgnoreCase("sqlserver"))
					 stmt.executeUpdate("commit");
				 Log4jHelper.logInfo(command.toUpperCase() + " query completed successfully");
				 break;

			 case "select":
				 tableData = stmt.executeQuery(strQuery.replace(";", ""));
				 break;
				
			 default:
				 stmt.execute(strQuery);
				 break;
			}
			
			return tableData;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	 
	public static void closeConnection(ResultSet resultSet) throws Exception {
		try {
			if (resultSet != null) {
				Statement stmt = resultSet.getStatement();
				Connection conn = stmt.getConnection();
				resultSet.close();
				conn.close();
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}