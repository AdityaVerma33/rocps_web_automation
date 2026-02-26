package com.subex.automation.helpers.application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.WebDriverException;

import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.ImageHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.db.DBHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.CommandLineScriptHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.automation.helpers.util.RemoteMachineHelper;

public class ControllerHelper extends ROCAcceptanceTest {
	static boolean isSCRunning=false;
	static boolean isSCConfigured=false;
	static Thread sc, tc, ts, ss;
	static RunStreamController runSC;
	static RunTaskController runTC;
	static RunServerService runSS;
	static RunTomcat runTomcat;
	static TaskSearchHelper taskSearch = null;
	
	private boolean updateJava = false;
	private String exportJava = "export JAVA_HOME=JAVAPATH && export PATH=$JAVA_HOME/bin:$PATH";
	
	public ControllerHelper() throws Exception {
		try {
			isSCConfigured = checkIfSCConfigured();
			isSCRunning = checkIfSCRunning();
			taskSearch = new TaskSearchHelper();
			
			String java8Path = configProp.getJava8Path();
			if (ValidationHelper.isNotEmpty(java8Path)) {
				updateJava = true;
				exportJava = exportJava.replace("JAVAPATH", java8Path);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean isSCRunning() {
		return isSCRunning;
	}
	
	public boolean isSCConfigured() {
		return isSCConfigured;
	}

	private boolean checkIfSCConfigured() throws Exception {
		DBHelper dbHelper = null;
		
		try {
			if (!isInstallationDone)
				return false;
			else {
				int x = 0;
				dbHelper = DBHelper.connectToReferenceDB(false);
				
				if (dbHelper != null && dbHelper.dbConnection != null && !dbHelper.dbConnection.isClosed()) {
					Statement stmt = dbHelper.dbConnection.createStatement();
					if (stmt != null && !stmt.isClosed()) {
						ResultSet rs = stmt.executeQuery("Select stc_id from stream_controller");
						
						if (rs != null) {
							while (rs.next()) {
								if (rs.getInt("STC_ID") > 0) {
									x++;
								}
							}
						}
					}
				}
				
				if (x > 0)
					return true;
				else
					return false;
			}
		} catch (SQLException e) {
			return false;
		} catch (Exception e) {
			System.out.println("\n" + e.getMessage() + "\n");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (dbHelper != null)
				dbHelper.closeConnection();
		}
	}
	
	private static boolean checkIfSCRunning() throws Exception {
		try {
			if (!isSCConfigured) {
				return false;
			}
			else {
				String scNotRunning = ElementHelper.getAttribute("SC_Status_Icon", "style");
				
				if (scNotRunning == null|| "".equals(scNotRunning))
					return false;
				else
					return true;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void startTomcat() throws Exception {
		try {
			Log4jHelper.logInfo("Starting Tomcat...");
			
			if (applicationOS.equalsIgnoreCase("Windows")) {
				runTomcat = new RunTomcat(configProp);
				ts=new Thread(runTomcat);
				ts.start();
			} else {
				String command = "cd "+ configProp.getTomcatPath() + "/bin && chmod 777 *.sh && " + "./startup.sh";
				if (updateJava) {
					command = exportJava + " && " + command;
				}
				
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				remoteMachine.executeScripts(command);
			}
			
			int count = 1;
			int waitTime = configProp.getClientStartWaitTimeMins();
			BrowserHelper browser = new BrowserHelper();
			browser.refresh();
			
			while (count != waitTime) {
				boolean isLoginScreenPresent = TextBoxHelper.isPresent("Login_Username_TextBox");
				boolean isAppScreenPresent = ButtonHelper.isPresent("NavigationMenu");
				if (isLoginScreenPresent || isAppScreenPresent)
					count = waitTime - 1;
				else
					GenericHelper.waitInSeconds("59");
				
				if (count == (waitTime/2))
					browser.refresh();
				
				count++;
			}
			
			Log4jHelper.logInfo("Started Tomcat...");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void startStreamController() throws Exception {
		try {
			if (isInstallationDone && !isSCRunning) {
				runSC = new RunStreamController(configProp);
				sc = new Thread(runSC);
				sc.run();
				
				waitForStreamControllerStart();
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void startTaskController(String tcbatFilename) throws Exception {
		try {
			if (isInstallationDone) {
				runTC = new RunTaskController(configProp, tcbatFilename);
				tc = new Thread(runTC);
				tc.run();
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void startServerService() throws Exception {
		try {
			if (!ImageHelper.isPresent("SS_RunningStatus_TopPanel")) {
				runSS = new RunServerService(configProp);
				ss = new Thread(runSS);
				ss.run();
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void stopTomcat() throws Exception {
		try {
			Log4jHelper.logInfo("Stopping Tomcat...");
			
			if (applicationOS.equalsIgnoreCase("Windows")) {
				String consoleTitle = configProp.getTomcatConsoleTitle();
				CommandLineScriptHelper cmdHelper = new CommandLineScriptHelper();
				
				if (cmdHelper.isConsolePresent(consoleTitle))
					cmdHelper.stopScript(consoleTitle);
				else
					cmdHelper.stopScript("start");
			}
			else {
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				String[] processes = remoteMachine.executeScripts("pgrep -f " + configProp.getTomcatPath(), false);
				if (ValidationHelper.isNotEmpty(processes)) {
					String process = processes[0].trim().replace("\n", "");
					if (ValidationHelper.isInteger(process))
						remoteMachine.executeScripts("kill -9 " + process);
				}
			}
			
			try {
				driver.navigate().refresh();
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			} catch (WebDriverException e) {
				// Ignoring exception as page wont load on killing tomcat
				Log4jHelper.logInfo("Stopped Tomcat...");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void killProcess(String processName) throws Exception {
		try {
			RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
			String[] pid = remoteMachine.executeScripts("jps | grep " + processName, false);
			if (ValidationHelper.isNotEmpty(pid)) {
				String[] processes = remoteMachine.executeScripts("pwdx " + pid[0] + " | grep " + deployPath, false);
				if (ValidationHelper.isNotEmpty(processes)) {
					String[] processId = processes[0].split(":");
					if (ValidationHelper.isNotEmpty(processId) && ValidationHelper.isInteger(processId[0]))
						remoteMachine.executeScripts("kill -9 " + processId[0]);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void stopStreamController() throws Exception {
		try {
			if (isSCRunning) {
				shutdownStreamController();
				
				if (applicationOS.equalsIgnoreCase("Windows")) {
					CommandLineScriptHelper cmdHelper = new CommandLineScriptHelper();
					cmdHelper.stopScript("StreamController");
				}
				else {
					killProcess("StreamController");
					killProcess("StreamControllerService");
				}
				
				waitForStreamControllerStop();
//				Log4jHelper.logInfo("Stream Controller is stopped.");
			}
			else
				Log4jHelper.logInfo("Stream Controller is not already running.");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void stopTaskController(String tcName) throws Exception  {
		try {
			if (isSCRunning) {
				shutdownTaskController(tcName);
				
				if (applicationOS.equalsIgnoreCase("Windows")) {
					CommandLineScriptHelper cmdHelper = new CommandLineScriptHelper();
					cmdHelper.stopScript("TaskController");
				}
				else {
					killProcess("TaskController");
					killProcess("TaskControllerService");
				}
				
				Log4jHelper.logInfo("Task Controller '" + tcName + "' is stopped.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void stopServerService() throws Exception{
		try {
			if (ImageHelper.isPresent("SS_RunningStatus_TopPanel")) {
				stopSS(searchScreenWaitSec);
				
				if (ImageHelper.isPresent("SS_RunningStatus_TopPanel")) {
					stopSS(searchScreenWaitSec);
				}
				
				if (applicationOS.equalsIgnoreCase("Windows")) {
					CommandLineScriptHelper cmdHelper = new CommandLineScriptHelper();
					cmdHelper.stopScript("ServerService");
				}
				else {
					killProcess("ServerService");
					killProcess("Server");
				}
				
				Log4jHelper.logInfo("Server Service is stopped.");
			}
			else
				Log4jHelper.logInfo("Server Service is either stopped already or the Server Service icon is not updated.");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void waitForStreamControllerStart() throws Exception {
		try {
			int timeout = configProp.getClientStartWaitTimeMins()*60;
			ElementHelper.waitForAttribute("SC_Status_Icon", "style", "display: none;", timeout);
			
			isSCRunning = checkIfSCRunning();
			if(isSCRunning) {
				Log4jHelper.logInfo("Stream controller is started");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void waitForStreamControllerStop() throws Exception {
		try {
			int timeout = configProp.getClientStartWaitTimeMins()*60;
			ElementHelper.waitForAttribute("SC_Status_Icon", "style", "", timeout);
			
			isSCRunning = checkIfSCRunning();
			if(!isSCRunning) {
				Log4jHelper.logInfo("Stream controller is stopped");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void stopSS(int searchScreenWaitSec) throws Exception {
		try {
			MouseHelper.click("SS_RunningStatus_TopPanel");
			GenericHelper.waitForElement("SS_Shutdown_Confirmation", searchScreenWaitSec);
			ButtonHelper.click("YesButton");
			GenericHelper.waitForElementToDisappear("SS_Shutdown_Confirmation", searchScreenWaitSec);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void stopServices()throws Exception {
		try {
			LoginHelper login = new LoginHelper();
			login.login();
			
			taskSearch.navigateToTaskSearch(false);
			stopServerService();
			
			if (isSCRunning)  {
				stopTC();
				
				stopStreamController();
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void stopTC() throws Exception {
		try {
			int rows = ElementHelper.getXpathCount("TaskSearch_TaskControllers");
			
			for (int i = 0; i < rows; i++) {
				String locator = or.getProperty("TaskSearch_TaskController_Names").replace("row", i+1+"");
				
				if (ElementHelper.isElementPresent(locator + "/img[contains(@src,'green.png')]")) {
					String controllerName = LabelHelper.getText(locator);
					stopTaskController(controllerName);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void restartTomcat() throws Exception {
		try {
			stopTomcat();
			Thread.sleep(2000);
			
			startTomcat();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void shutdownStreamController() throws Exception {
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.navigateToTaskSearch(true);
			
			MouseHelper.rightClick("TaskSearch_StreamController");
			if (ButtonHelper.isPresent("Shutdown Controller")) {
				NavigationHelper.navigateToAction("Shutdown Controller");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean shutdownTaskController(String tcName) throws Exception {
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.navigateToTaskSearch(true);
			
			String tcLocator = or.getProperty("TaskSearch_TaskController").replace("taskControllerName", tcName);
			MouseHelper.click(tcLocator);
			MouseHelper.rightClick(tcLocator);
			if (ButtonHelper.isPresent("Shutdown Controller")) {
				NavigationHelper.navigateToAction("Shutdown Controller");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				return true;
			}
			else {
				ElementHelper.pressEscape();
				return false;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}