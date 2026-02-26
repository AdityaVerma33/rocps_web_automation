package com.subex.automation.helpers.db;

import java.sql.Connection;

import org.testng.annotations.AfterSuite;

import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class DBConnectionHelper extends AcceptanceTest {
	private static String[][] dbDetails = null;
	private static DBHelper[] dbHelper = null;
	private static Connection[] connection = null;
	
	public void createConnections() throws Exception {
		try {
			dbDetails = DBHelper.getReferenceDatabases();
			
			if (ValidationHelper.isNotEmpty(dbDetails)) {
				int length = dbDetails.length;
				dbHelper = new DBHelper[length];
				connection = new Connection[length];
				
				for (int i = 0; i < length; i++) {
					dbHelper[i] = DBHelper.createConnection(dbDetails[i][0], dbDetails[i][1], dbDetails[i][2], dbDetails[i][3], dbDetails[i][4], dbDetails[i][5]);
					connection[i] = dbHelper[i].dbConnection;
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String[][] getDBDetails() throws Exception {
		try {
			return dbDetails;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String[] getDBDetails(int dbIndex) throws Exception {
		try {
			return dbDetails[dbIndex];
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public Connection getConnection(int dbIndex) throws Exception {
		try {
			 return connection[dbIndex];
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public Connection[] getConnections() throws Exception {
		try {
			 return connection;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean isDBConfigured() throws Exception {
		try {
			if (ValidationHelper.isEmpty(dbDetails))
				return false;
			else
				return true;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@AfterSuite( alwaysRun = true )
	public void closeConnections() throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(dbDetails)) {
				int length = dbDetails.length;
				
				for (int i = 0; i < length; i++) {
					if (connection != null && connection.length >= i && connection[i] != null)
						connection[i].close();
					
					if (ValidationHelper.isNotEmpty(dbDetails))
						dbHelper[i].closeConnection();
				}
			}
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
		}
	}
}