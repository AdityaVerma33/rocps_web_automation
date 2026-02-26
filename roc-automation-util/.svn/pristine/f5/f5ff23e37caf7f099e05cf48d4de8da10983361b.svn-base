package com.subex.automation.helpers.db;

import java.sql.ResultSet;

import com.subex.automation.helpers.scripts.ExecuteScript;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class TableHelper extends AcceptanceTest {

	public String checkTableInstExists(String tableName, String[] dbDetails) throws Exception {
		ResultSet rs = null;
		
		try {
			String[] tableNames = {tableName, tableName.toLowerCase(), tableName.toUpperCase()};
			int length = tableNames.length;
			boolean tableExists = false;
			String initialQuery = "SELECT TIN_ID FROM TABLE_INST WHERE TIN_TABLE_NAME LIKE '";
			
			for (int i = 0; i < length; i++) {
				rs = ExecuteScript.exeQuery(initialQuery + tableNames[i] + "'", dbDetails);
				while (rs.next()) {
					if (rs.getInt("TIN_ID") > 0) {
						tableName = tableNames[i];
						tableExists = true;
						i = length + 1;
						break;
					}
				}
			}
			
			if (tableExists)
				return tableName;
			else
				return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			ExecuteScript.closeConnection(rs);
		}
	}
}