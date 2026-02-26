package com.subex.automation.helpers.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;
public class CreateNewDB extends AcceptanceTest {

	static Connection connection = null;
	static Statement statement = null;
	static String url=null;
	static String dbName = null;
	static String username = null;
	static String password = null;
	static String dbHost = null;
	static String dbInstance = null;
	static String dbType = null;
	static String portNumber = null;
//	static String tableSpacePath = null;
	
	public void createDataBase(String databaseType) throws Exception  {
		try {
			
			updateParameters(databaseType);
			
			if(dbType.equalsIgnoreCase("sqlserver"))
			{
				createDBInSQLServer();
			}
			else if (dbType.equalsIgnoreCase("oracle"))
			{
				createDBInOracle();
			}
			else if (dbType.equalsIgnoreCase("PostgreSQL") || dbType.equalsIgnoreCase("Postgre")) {
				createDBInPostgreSQL();
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private void updateParameters(String databaseType) throws Exception {
		try {
			
			if (databaseType.equalsIgnoreCase("Reference")) {
				dbType = configProp.getDbType();
				dbName= configProp.getDB();
				username=configProp.getDbUserName();
				password=configProp.getDbPassword();
				dbHost = configProp.getDbMachineName();
				dbInstance = configProp.getDbInstance();
				portNumber = configProp.getDBPortNumber();
			}
			else {
				dbType = configProp.getUsageDBType();
				dbName= configProp.getUsageDatabase();
				username=configProp.getUsageUsername();
				password=configProp.getUsagePassword();
				dbHost = configProp.getUsageMachineName();
				dbInstance = configProp.getUsageInstance();
				portNumber = configProp.getUsagePortNumber();
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void createDBInSQLServer() throws Exception {
		try {
			url = "jdbc:jtds:sqlserver://"+ dbHost + ";Instance=" + dbInstance + ";PrepareSQL=0";
			
			Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT name FROM master.dbo.sysdatabases WHERE ('[' + name + ']' = " + dbName + " OR name = " + dbName + ")");
			if (rs == null)
				statement.executeUpdate("DROP DATABASE  " + dbName);
		}
		catch (ClassNotFoundException e) {
			FailureHelper.setError("Could not find the class Name : " + e.getMessage());
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (SQLException e) {
			FailureHelper.setError("SQl exception : " + e.getMessage());
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			
			try {
				String hrappSQL = "CREATE DATABASE " + dbName;
				statement.executeUpdate(hrappSQL);
			}
			catch (Exception e) {
				FailureHelper.setError("Dropping existing database Failed(if any) : " + e.getMessage());
				FailureHelper.setErrorMessage(e);
				throw e;
			}
			
			if (statement != null) {
				try {
					statement.close();
				} 
				catch (SQLException e) {} // nothing we can do
			}
			if (connection != null) {
				try {
					connection.close();
				} 
				catch (SQLException e) {} // nothing we can do
			}
		}
	}
	
	private void createDBInOracle() throws Exception {
		try {
			url = "jdbc:oracle:thin:@" + dbHost + ":" + portNumber + ":" + dbInstance;
			
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			if (dbHost.equals("10.113.49.82"))
				connection = DriverManager.getConnection(url, "test", "test");
			else
				connection = DriverManager.getConnection(url, "sys as sysdba", "sys");
			statement = connection.createStatement();
			
			boolean dbExists = false;
			ResultSet rs = statement.executeQuery("select username from dba_users where username='" + username.toUpperCase() + "'");
			if (rs != null) {
				if (rs.next()) {
					dbExists = true;
				}
			}
			
			if (dbExists) {
				statement.executeUpdate("drop user " + username +" cascade");
				Log4jHelper.logInfo("Existing Oracle User "+ username +" is dropped");
			}
		} 
		catch (ClassNotFoundException e) {
			FailureHelper.setError("Could not find the class Name : " + e.getMessage());
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (SQLException e) {
			FailureHelper.setError("SQl exception : " + e.getMessage());
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {	 
			try {
				statement.executeUpdate("create user " + username+" identified by "+password);
				statement.executeUpdate("grant dba to "+ username);
				Log4jHelper.logInfo("Created Oracle User " + username);
			}
			catch(SQLException sqlex) {
				FailureHelper.setErrorMessage(sqlex);
				throw sqlex;
			}
			 
			if (statement != null) {
				try {
					statement.close();
				} 
				catch (SQLException e) {} // nothing we can do
			}
			if (connection != null) {
				try {
					connection.close();
				} 
				catch (SQLException e) {} // nothing we can do
			}
		}
	}
	
	private void createDBInPostgreSQL() throws Exception {
		try {
			if (portNumber.equals(""))
				portNumber = configProp.getDBPortNumber();
			
			url = "jdbc:postgresql://" + dbHost + ":" + portNumber + "/postgres";
			
			Class.forName("org.postgresql.Driver").newInstance();
			connection = DriverManager.getConnection(url, "postgres", "");
			statement = connection.createStatement();

			statement.executeUpdate("DROP DATABASE IF EXISTS " + dbName);
			statement.executeUpdate("DROP USER IF EXISTS " + username);

		} 
		catch (ClassNotFoundException e) {
			FailureHelper.setError("Could not find the class Name : " + e.getMessage());
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (SQLException e) {
			FailureHelper.setError("SQl exception : " + e.getMessage());
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {	 
			try {
				statement.executeUpdate("CREATE USER " + username + " WITH PASSWORD '" + password + "'");
				statement.executeUpdate("CREATE DATABASE " + dbName + " OWNER " + username);
				statement.executeUpdate("GRANT ALL PRIVILEGES ON DATABASE " + dbName + " TO " + username);
				
				Log4jHelper.logInfo("Created PostgreSQL User '" + username);
				Log4jHelper.logInfo("Created PostgreSQL Database '" + dbName);
			}
			catch(SQLException sqlex) {
				FailureHelper.setErrorMessage(sqlex);
				throw sqlex;
			}
			 
			if (statement != null) {
				try {
					statement.close();
				} 
				catch (SQLException e) {} // nothing we can do
			}
			if (connection != null) {
				try {
					connection.close();
				} 
				catch (SQLException e) {} // nothing we can do
			}
		}
	}
}