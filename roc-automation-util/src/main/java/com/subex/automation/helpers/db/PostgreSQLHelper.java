package com.subex.automation.helpers.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class PostgreSQLHelper extends AcceptanceTest {

	public void dbExport(String hostName, String userName, String password, String dbName, String fileName) throws Exception {
		try {
			String portNumber = String.valueOf(DBHelper.getPortNumber());
			
			final List<String> baseCmds = new ArrayList<String>();
		    baseCmds.add("/usr/bin/pg_dump");
		    baseCmds.add("-h");
		    baseCmds.add(hostName);
		    baseCmds.add("-p");
		    baseCmds.add(portNumber);
		    baseCmds.add("-U");
		    baseCmds.add(userName);
		    baseCmds.add("-b");
		    baseCmds.add("-v");
		    baseCmds.add("-f");
		    baseCmds.add(fileName);
		    baseCmds.add(dbName);
		    final ProcessBuilder pb = new ProcessBuilder(baseCmds);
	
		    // Set the password
		    final Map<String, String> env = pb.environment();
		    env.put("PGPASSWORD", password);

	       final Process process = pb.start();
	       final BufferedReader r = new BufferedReader(new InputStreamReader(process.getErrorStream()));
	       ArrayList<String> line = new ArrayList<String>();
	       int count = 0;
	       line.add(r.readLine());
	       
	       while (line.get(count) != null) {
	    	   System.err.println(line);
	    	   line.add(r.readLine());
	           count++;
	       }
	       r.close();

	       final int dcertExitCode = process.waitFor();
	       
	       if (!(dcertExitCode == 0))
	    	   FailureHelper.failTest("Export failed due to error: " + line.toString());
	    }
	    catch (IOException e) {
	    	FailureHelper.setErrorMessage(e);
	    	throw e;
	    }
	    catch (InterruptedException ie) {
	    	FailureHelper.setErrorMessage(ie);
	    	throw ie;
	     }
		 catch (Exception e) {
	    	FailureHelper.setErrorMessage(e);
	    	throw e;
	     }
	}
	
	public void dbRestore(String hostName, String userName, String dbName, String restoreExeFile, String fileName) throws Exception {
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			Process p;
			ProcessBuilder pb;
			pb = new ProcessBuilder(restoreExeFile, "--host", hostName,
								    "--port", "5432", "--username", userName,
								    "--dbname", dbName, "--role", "postgres",
								    "--no-password", "--verbose", fileName);
			pb.redirectErrorStream(true);
			p = pb.start();
			
			InputStream is = p.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String ll;
			
			while ((ll = br.readLine()) != null) {
				Log4jHelper.logInfo(ll);
			}
			is.close();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
	    	throw e;
		}
		finally {
			if (isr != null)
				isr.close();
			if (br != null)
				br.close();
		}
	}
}