package com.subex.automation.helpers.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;

public class CommandLineScriptHelper extends AcceptanceTest {
	
	public void runScript(String consoleTitle, String scriptFile, String arguments, boolean exit) throws Exception
	{	
		String Command = null;
		if (arguments == null)
			Command = GenericHelper.getPath(automationOS, automationPath + "\\eclipse\\scripts\\" + scriptFile);
		else
			Command = GenericHelper.getPath(automationOS, automationPath + "\\eclipse\\scripts\\" + scriptFile) + " " + arguments;
		
		try {

			Runtime rt = Runtime.getRuntime();
			Log4jHelper.logInfo("Running the batch script.. " + scriptFile);
			if (ValidationHelper.isEmpty(consoleTitle))
				consoleTitle = "RunScriptFile";
			
			if (exit)
				rt.exec("cmd /c start /min title " + consoleTitle + " ^& " + Command + " ^& exit");
			else
				rt.exec("cmd /c start /min title " + consoleTitle + " ^& " + Command);
		}
		catch(Exception e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		
	}
	
	public void stopScript(String consoleTitle) throws Exception {
		try {
			
			Runtime.getRuntime().exec("taskkill /im cmd.exe /fi \"windowtitle eq " + consoleTitle + "*\" ");
			Log4jHelper.logInfo("Console with title '" + consoleTitle + "' was stopped.");
		}
		catch(Exception e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean isConsolePresent(String consoleTitle) throws Exception {
		BufferedReader input = null;
		try {
			String line;
			String pidInfo ="";
			Process p =Runtime.getRuntime().exec("tasklist /fi \"WINDOWTITLE eq " + consoleTitle + "*\"");
			input = new BufferedReader(new InputStreamReader(p.getInputStream()));
	
			while ((line = input.readLine()) != null) {
			    pidInfo+=line; 
			}
	
			if(pidInfo.contains("cmd.exe")) {
			    return true;
			}
			else
				return false;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (input != null)
				input.close();
		}
	}
	
	public void waitForConsoleToClose(String consoleTitle, int waitInSecs) throws Exception {
		try {
			boolean consoleClosed = false;
			for (int i = 0; i < waitInSecs; i++) {
				if (isConsolePresent(consoleTitle))
					Thread.sleep(1000);
				else {
					consoleClosed = true;
					break;
				}
			}
			
			if (!consoleClosed) {
				FailureHelper.failTest("Console "+ consoleTitle + " is still open");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void executeCommand(String command) throws Exception
	{	
		try {

			Runtime rt = Runtime.getRuntime();
			String consoleTitle = "ExecuteCommand";
			
			rt.exec("cmd /c start /min title " + consoleTitle + " ^& " + command +" ^& exit");
		}
		catch(Exception e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		
	}
}