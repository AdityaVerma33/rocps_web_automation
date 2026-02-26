package com.subex.automation.helpers.db;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.report.ReportHelper;
import com.subex.automation.helpers.scripts.ExecuteScript;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class DatabaseHelper extends AcceptanceTest {

	/**
	 * The following method creates a db back up of the database in to a file 'db.data'. 
	 * Takes the back up of the db mentioned in the spark-config file.
	 * @throws Exception 
	 */
	public void createDbBackUp(String dbType, String fileName) throws Exception {
		DBHelper helper = null;
		
		try {
			if (dbType.equalsIgnoreCase("Reference"))
				helper = DBHelper.createReferenceConnection();
			else
				helper = DBHelper.createUsageConnection();
		
			if ( helper == null )
				Log4jHelper.logInfo( "Failed DBHelper Initilization" );
			else {
				long time = System.currentTimeMillis();
				helper.saveDBtoFile( fileName, false, "AllTables" );
				Log4jHelper.logInfo( "Time taken in sec = " + ( System.currentTimeMillis() - time ) / 1000 );
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (helper != null)
				helper.closeConnection();
		}
	}
	
	/**
	 * Reads the backup db file and restores the same to the db mentioned in the spark-config file.
	 * The data present in the file is cleared before restoring the backup.
	 */
	public void restoreDbBackUp(String dbType, String fileName) throws Exception {
		DBHelper helper = null;
		
		try {
			if (!fileName.endsWith(".data"))
				fileName = fileName + ".data";
			if (dbType.equalsIgnoreCase("Reference"))
				helper = DBHelper.createReferenceConnection();
			else
				helper = DBHelper.createUsageConnection();
			
			long time = System.currentTimeMillis();
			if ( helper == null )
				Log4jHelper.logInfo( "Failed DBHelper initialization." );
			else {
				String dbinput = fileName == null ? "db.data" : fileName;
				helper.clearDB( dbinput );
				helper.populateDBFromFile( dbinput );
				Log4jHelper.logInfo( "Time taken in sec = " + ( System.currentTimeMillis() - time ) / 1000 );
				
				ResultSet rs = ExecuteScript.exeQuery("select table_name from dba_tables where table_name like 'CASE_INPUT_%' and owner = '" + configProp.getDbUserName().toUpperCase() + "'");
				while (rs.next())
					ExecuteScript.exeQuery("truncate table " + rs.getString(0));
				rs.close();
			}
		}
		catch ( SQLException e ) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch ( IOException e ) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch ( ClassNotFoundException e ) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (helper != null)
				helper.closeConnection();
		}
	}
	
	public void exportAllTables(String dbType, String fileName) throws Exception {
		DBHelper helper = null;
		
		try {
			if (dbType.equalsIgnoreCase("Reference"))
				helper = DBHelper.createReferenceConnection();
			else
				helper = DBHelper.createUsageConnection();
			
			if ( helper == null )
				Log4jHelper.logInfo( "Failed DBHelper Initilization" );
			else {
				long time = System.currentTimeMillis();
				fileName = DBHelper.getFileName(fileName);
				
				if (helper.dbType.productName.equalsIgnoreCase("Oracle"))
					helper.writeToFile(fileName, "AllTables");
				else if (helper.dbType.productName.equalsIgnoreCase("PostgreSQL")){
					CopyManager cm = new CopyManager((BaseConnection) helper.dbConnection);
					cm.copyOut(fileName);
				}
				
				Log4jHelper.logInfo( "Time taken in sec = " + ( System.currentTimeMillis() - time ) / 1000 );
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (helper != null)
				helper.closeConnection();
		}
	}
	
	public void exportModifiedTables(String dbType, String fileName) throws Exception {
		DBHelper helper = null;
		
		try {
			if (dbType.equalsIgnoreCase("Reference"))
				helper = DBHelper.createReferenceConnection();
			else
				helper = DBHelper.createUsageConnection();
		
			if ( helper == null )
				Log4jHelper.logInfo( "Failed DBHelper Initilization" );
			else {
				long time = System.currentTimeMillis();
				fileName = DBHelper.getFileName(fileName);
				
				if (helper.dbType.productName.equalsIgnoreCase("Oracle")) {
					helper.writeToFile(fileName, "ModifiedTables");
					Log4jHelper.logInfo( "Time taken = " + ( System.currentTimeMillis() - time ) / 1000 + " secs");
				}
				else if (helper.dbType.productName.equalsIgnoreCase("PostgreSQL")){
					Log4jHelper.logInfo("Cannot export modified tables in PostgreSQL");
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (helper != null)
				helper.closeConnection();
		}
	}
	
	public void dbImp(String dbType, String fileName) throws Exception {
		try {
			String dbHost = null;
			String dbUsername = null;
			String dbPassword = null;
			String databaseType = null;
			
			if (dbType.equalsIgnoreCase("Reference")) {
				databaseType = configProp.getDbType();
				dbHost = configProp.getDbMachineName();
				dbUsername = configProp.getDbUserName();
				dbPassword = configProp.getDbPassword();
			}
			else {
				databaseType = configProp.getUsageDBType();
				dbHost = configProp.getUsageMachineName();
				dbUsername = configProp.getUsageUsername();
				dbPassword = configProp.getUsagePassword();
			}
			
			if (databaseType.equalsIgnoreCase("Oracle")) {
				DBHelper.importOracleDump(fileName, dbUsername, dbPassword, dbHost);
			}
			else if (databaseType.equalsIgnoreCase("PostgreSQL")) {
				String restoreExeFile = configProp.getPostgreSQLRestoreExeFile();
				
				if (!restoreExeFile.equals("")) {
					PostgreSQLHelper postgresHelper = new PostgreSQLHelper();
					postgresHelper.dbRestore(dbHost, dbUsername, dbPassword, restoreExeFile, fileName);
				}
				else
					FailureHelper.failTest("PostgreSQL Restore Executable file 'postgreSQLRestoreExeFile' is empty in config.properties file.");
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void dbExp(String dbType, String fileName) throws Exception {
		try {
			String dbHost = null;
			String dbUsername = null;
			String dbPassword = null;
			String databaseType = null;
			String dbName = null;
			
			if (dbType.equalsIgnoreCase("Reference")) {
				databaseType = configProp.getDbType();
				dbHost = configProp.getDbMachineName();
				dbUsername = configProp.getDbUserName();
				dbPassword = configProp.getDbPassword();
				dbName = configProp.getDB();
			}
			else {
				databaseType = configProp.getUsageDBType();
				dbHost = configProp.getUsageMachineName();
				dbUsername = configProp.getUsageUsername();
				dbPassword = configProp.getUsagePassword();
				dbName = configProp.getUsageDatabase();
			}
			
			if (databaseType.equalsIgnoreCase("Oracle")) {
				DBHelper.exportOracleDB(fileName, dbUsername, dbPassword, dbHost);
			}
			else if (databaseType.equalsIgnoreCase("PostgreSQL")) {
				PostgreSQLHelper postgresHelper = new PostgreSQLHelper();
				postgresHelper.dbExport(dbHost, dbUsername, dbPassword, dbName, fileName);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void executeCommand(String dbType, String command) throws Exception {
		DBHelper dbHelper1 = null;
		
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
			
			String[] queryType = command.split(" ");
			queryType[0] = queryType[0].toLowerCase();
			command = command.replace(";", "");
			switch (queryType[0]) {
			case "insert":
			case "delete":
			case "create":
			case "alter":
			case "update":
				dbHelper1.dbConnection.createStatement().executeUpdate(command);
				break;

			case "select":
				execute(dbHelper1, command);
				break;
				
			default:
				dbHelper1.dbConnection.createStatement().execute(command);
				break;
			}
			
			Log4jHelper.logInfo("'" + queryType[0].toUpperCase() + "' query completed successfully");
			
			if(!databaseType.equalsIgnoreCase("sqlserver"))
				dbHelper1.dbConnection.createStatement().executeUpdate("commit");
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (dbHelper1 != null)
				dbHelper1.closeConnection();
		}
	}
	
	public String[] getFDSDBDetails() throws Exception {
		try {
			String[] fdsDataSourceType = configProp.getStringProperty("FDSDataSourceType").split(",", -1);
			String[] fdsDatabase = configProp.getStringProperty("FDSDatabase").split(",", -1);
			String[] fdsInstance = configProp.getStringProperty("FDSInstance").split(",", -1);
			String[] fdsMachine = configProp.getStringProperty("FDSMachine").split(",", -1);
			String[] fdsPassword = configProp.getStringProperty("FDSPassword").split(",", -1);
			String[] fdsUserName = configProp.getStringProperty("FDSUserName").split(",", -1);
			String[] fdsPort = configProp.getStringProperty("FDSPort").split(",", -1);
			
			String[] dbDetails = {fdsDataSourceType[0], fdsMachine[0], fdsDatabase[0], fdsUserName[0], fdsPassword[0], fdsInstance[0], fdsPort[0]};
			return dbDetails;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void executeScript(String dbType, String fileName) throws Exception {
		try {
			executeScript(dbType, applicationOS, fileName);
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void executeScript(String dbType, String os, String fileName) throws Exception {
		DBHelper dbHelper1 = null;
		
		try {
			String databaseType = null;
			
			if (dbType.equalsIgnoreCase("Reference")) {
				dbHelper1 = DBHelper.createReferenceConnection();
				databaseType = configProp.getDbType();
			}
			else if (dbType.equalsIgnoreCase("FederatedDataSource")) {
				String[] dbDetails = getFDSDBDetails();
				dbHelper1 = DBHelper.createFederatedDataSourceonnection(dbDetails);
				databaseType = dbDetails[0];
			}
			else {
				dbHelper1 = DBHelper.createUsageConnection();
				databaseType = configProp.getUsageDBType();
			}
			
			String[] strQuery = FileHelper.readFileContent(os, GenericHelper.getPath(os, fileName));
			for (int i = 0; i < strQuery.length; i++) {
				if (ValidationHelper.isNotEmpty(strQuery[i])) {
					String[] queryType = strQuery[i].split(" ");
					queryType[0] = queryType[0].toLowerCase();
					if (strQuery[i].endsWith(";")) {
						int index = strQuery[i].lastIndexOf(";");
						strQuery[i] = strQuery[i].substring(0, index);
					}
					
					switch (queryType[0]) {
					case "insert":
					case "delete":
					case "create":
					case "alter":
					case "update":
						dbHelper1.dbConnection.createStatement().executeUpdate(strQuery[i]);
						break;

					case "select":
						execute(dbHelper1, strQuery[i]);
						break;
						
					case "drop":
						try {
						dbHelper1.dbConnection.createStatement().execute(strQuery[i]);
						} catch (SQLSyntaxErrorException e) {
							if (e.getMessage().contains("table or view does not exist")) {
								
							}
							else {
								FailureHelper.setErrorMessage(e);
								throw e;
							}
						}
						break;
						
					default:
						dbHelper1.dbConnection.createStatement().execute(strQuery[i]);
						break;
					}
				}
			}
			
			if(!databaseType.equalsIgnoreCase("sqlserver"))
				dbHelper1.dbConnection.createStatement().executeUpdate("commit");
			
			Log4jHelper.logInfo("File '" + fileName + "' executed successfully");
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (dbHelper1 != null)
				dbHelper1.closeConnection();
		}
	}
	
	public String executeQuery(String dbType, String query) throws Exception {
		DBHelper dbHelper1 = null;
		Statement stmt = null;
		
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
			
			String[] queryType = query.split(" ");
			queryType[0] = queryType[0].toLowerCase();
			
			switch (queryType[0]) {
				case "insert":
				case "delete":
				case "create":
				case "alter":
				case "update":
					stmt = dbHelper1.dbConnection.createStatement();
					stmt.executeUpdate(query.replace(";", ""));
					
					if(!databaseType.equalsIgnoreCase("sqlserver"))
						stmt.executeUpdate("commit");
//					ReportHelper.updateStepKey("Success", "Green", "Query executed successfully");
					return null;
					
				case "select":
					String fileName = execute(dbHelper1, query);
					if (fileName != null)
//						ReportHelper.updateStepKey("Success", "Green", "Query executed successfully");
					return fileName;
					
				default:
					stmt = dbHelper1.dbConnection.createStatement();
					stmt.execute(query.replace(";", ""));
					
					if(!databaseType.equalsIgnoreCase("sqlserver"))
						stmt.executeUpdate("commit");
//					ReportHelper.updateStepKey("Success", "Green", "Query executed successfully");
					return null;
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (dbHelper1 != null) {
				if (stmt != null && !stmt.isClosed())
					stmt.close();
				dbHelper1.closeConnection();
			}
		}
	}
	
	public String execute(DBHelper dbHelper1, String query) throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = dbHelper1.dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(query.replace(";", ""));
			Map<Integer, ArrayList<String>> data = new HashMap<Integer, ArrayList<String>>();
			int count = 1;
			
			if (rs != null) {
				int rowCount = QueryHelper.getRowCount(rs);
				int colCount = QueryHelper.getColumnCount(rs);
				
				if (rowCount > 0 && colCount > 0) {
					ArrayList<String> header = QueryHelper.addHeaderArray(rs);
					header.add("\n");
					data.put(0, header);
					rs.first();
					
					for (int i = 0; i < rowCount; i++) {
						ArrayList<String> values = new ArrayList<String>();
						for (int j = 0; j < colCount; j++) {
							if (colCount > 1)
								values.add(rs.getString(colCount) + "\t");
							else
								values.add(rs.getString(colCount));
						}
						
						values.add("\n");
						data.put(count, values);
						count++;
						rs.next();
					}
					
					String fileName = QueryHelper.createCSVFile(data, "Query");
					return fileName;
				}
				else {
					ReportHelper.addWarning("The query returned 0 rows");
					return null;
				}
			}
			else {
				ReportHelper.addWarning("The query returned 0 rows");
				return null;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (stmt != null && !stmt.isClosed())
				stmt.close();
		}
	}
	
	public void executeQuery(String dbType, String query, String compareFile) throws Exception {
		try {
			String fileName = executeQuery(dbType, query);
			
			if (fileName != null) {
				FileHelper.compareFiles(fileName, compareFile);
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}