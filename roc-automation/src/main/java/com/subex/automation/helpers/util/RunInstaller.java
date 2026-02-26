package com.subex.automation.helpers.util;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;

public class RunInstaller extends ROCAcceptanceTest {
	
	boolean updateJava = false;
	private String exportJava = "export JAVA_HOME=JAVAPATH && export PATH=$JAVA_HOME/bin:$PATH";
	
	public void runInstaller(String javaPath) throws Exception
	{
		try {
			String arguments = null;
			if (applicationOS.equalsIgnoreCase("Windows")) {
				if (deployPath.startsWith("\""))
					arguments = " " + deployPath + " " + configProp.getDbUserName() + " " + configProp.getDbPassword();
				else
					arguments = " \"" + deployPath + "\" " + configProp.getDbUserName() + " " + configProp.getDbPassword();
			}
			else
				arguments = configProp.getDbUserName() + " " + configProp.getDbPassword();
			
			if (ValidationHelper.isNotEmpty(javaPath)) {
				updateJava = true;
				this.exportJava = this.exportJava.replace("JAVAPATH", javaPath);
			}
			
			runPreSilentInstaller(arguments);
			
			if (applicationOS.equalsIgnoreCase("Windows"))
				arguments = " \"" + deployPath + "\"";
			else
				arguments = null;
			
			runSilentInstaller(arguments);
			
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void runGUIInstaller() throws Exception
	{
		try{
			Screen s=new Screen();
			
			Pattern InstallerMaximize = new Pattern(automationPath+"\\Images\\Installer\\InstallerMaximize.png");
			Pattern File_settings= new Pattern(automationPath+"\\Images\\Installer\\File_settings.png");
			Pattern DB_Licence= new Pattern(automationPath+"\\Images\\Installer\\DB_Licence.png");
			Pattern DBTypeDropDown = new Pattern(automationPath+"\\Images\\Installer\\DBTypeDropDown.png");
			Pattern DBTypeList = new Pattern(automationPath+"\\Images\\Installer\\DBTypeList.png");
			Pattern MSSQl = new Pattern(automationPath+"\\Images\\Installer\\MSSQl.png");
			Pattern Oracle = new Pattern(automationPath+"\\Images\\Installer\\Oracle.png");
			Pattern OpenWorkspace = new Pattern(automationPath+"\\Images\\Installer\\OpenWorkspace.png");
			
			Pattern CIWFileName = new Pattern(automationPath+"\\Images\\Installer\\CIWFileName.png");
			Pattern Open = new Pattern(automationPath+"\\Images\\Installer\\Open.png");
			Pattern AfterCIWFile = new Pattern(automationPath+"\\Images\\Installer\\AfterCIWFile.png");
			Pattern Next = new Pattern(automationPath+"\\Images\\Installer\\Next.png");
			Pattern  LicenceDetails  = new Pattern(automationPath+"\\Images\\Installer\\LicenceDetails.png");
			Pattern  Execute  = new Pattern(automationPath+"\\Images\\Installer\\Execute.png");
	//		Pattern  Scroll  = new Pattern(automationPath+"\\Images\\Installer\\Scroll.png");
			Pattern  OK  = new Pattern(automationPath+"\\Images\\Installer\\OK.png");
			
			Pattern MachineDetails = new Pattern(automationPath+"\\Images\\Installer\\MachineDetails.png");
			Pattern After1stExecution = new Pattern(automationPath+"\\Images\\Installer\\After1stExecute.png");
			Pattern Finish = new Pattern(automationPath+"\\Images\\Installer\\Finish.png");
		
			Runtime GUIInstaller = Runtime.getRuntime();
			GUIInstaller.exec("cmd /c  start "+automationPath+"\\scripts\\RunGUIInstaller.bat "+deployPath);
			s.wait(InstallerMaximize,100);	
			s.click(InstallerMaximize.targetOffset(330, -3));
			s.click(File_settings.targetOffset(10,0));
			s.click(DB_Licence.targetOffset(5, -10));//db
			s.click(DBTypeDropDown.targetOffset(38,0));//db	
		
			if(configProp.getDbType().equalsIgnoreCase("sqlserver"))
			{
				s.click(DBTypeList.targetOffset(-2, -23));
				s.paste(MSSQl.targetOffset(37,-76),configProp.getDbUserName());
				s.paste(MSSQl.targetOffset(37,-55),configProp.getDbPassword());
				s.paste(MSSQl.targetOffset(37,-31),configProp.getDbMachineName());
				s.paste(MSSQl.targetOffset(37,-8),configProp.getDB());
				
				if(configProp.getDbInstance().length()>0)
				{
					s.click(MSSQl.targetOffset(-7,40));
					s.paste(MSSQl.targetOffset(40,40),configProp.getDbInstance());
				}
				
				s.click(MSSQl.targetOffset(1,97));
				s.waitVanish(MSSQl,5);
			}
	
			else if(configProp.getDbType().equalsIgnoreCase("oracle"))
			{
				s.click(DBTypeList.targetOffset(-28, -6));
				s.paste(Oracle.targetOffset(37,-76),configProp.getDbUserName());
				s.paste(Oracle.targetOffset(37,-55),configProp.getDbPassword());
				s.paste(Oracle.targetOffset(37,-31),configProp.getDbMachineName());
				s.paste(Oracle.targetOffset(37,-8),	configProp.getDbInstance());
	//			s.paste(Oracle.targetOffset(29,39),configProp.getdatabasePortNumber());
				s.click(Oracle.targetOffset(-11,97));
				s.waitVanish(Oracle,5);
			}
			
			s.click(File_settings.targetOffset(10,0));//settings
			s.mouseMove(File_settings.targetOffset(-27, 0));//file		
			s.click(OpenWorkspace);		
			s.paste(CIWFileName, deployPath+"\\metadata\\spark.ciw");		
			s.click(Open);	
			s.wait(AfterCIWFile,50);
			
			s.click(File_settings.targetOffset(10,0));//settings
			s.click(DB_Licence.targetOffset(3, 12));//licence		
			s.paste(LicenceDetails.targetOffset(34, -28),"Subex Ltd");
			s.paste(LicenceDetails.targetOffset(34, -6),"b9ESPdBb0nExSbaUysUeVS6FH+4COM1Ro0ZXeDC2jov4j8TccRxfyLbWOQrFfWnz5A8m3GCZKsvT+6YSY+vVXR7+XT2z0N0xP9pZwHTpb8Z0RYHaEgS6At897qzjz1IcDD6d4pzjinDbg51IGy9MsyZdu58a7nf2KuIuJYIt6qs5Or1hL9ymviKWDg7DJoq34JYzeemgKFtHOvnnuwPz4EpsYiNpGcQ8E1wBAfhez+yxOIw4XTE073bj+8P4V7v0");
			s.click(LicenceDetails.targetOffset(16, 29));
			
			s.click(Next);
			s.click(Execute);
			
			s.wait(OK,500);
			s.click(OK);
			
			s.wait(MachineDetails,1500);
			s.paste(MachineDetails.targetOffset(50, -28),configProp.getMachineName());
			s.paste(MachineDetails.targetOffset(50, -3),configProp.getMachineName());
			s.paste(MachineDetails.targetOffset(50, 25),configProp.getDataDirPath());
			s.click(MachineDetails.targetOffset(105, 50));

			s.wait(After1stExecution.exact(),1500);
			s.click(Next);
			s.click(Execute);
			
			s.wait(Finish,500);
			s.click(Finish);
		}
		
		catch(FindFailed e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		
		catch(Exception e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private void runPreSilentInstaller(String arguments) throws Exception {
		try {
			String fileName = null;
			String[] line = null;
			String result = null;
			String logFileMessage = "Pre installation tasks are over";
			Log4jHelper.logInfo("Running Pre-silent Installer....");
			
			if (applicationOS.equalsIgnoreCase("Windows")) {
				CommandLineScriptHelper cmdHelper = new CommandLineScriptHelper();
				cmdHelper.runScript("RunPreSilentInstaller", "RunPreSilentInstaller.bat", arguments, true);
				cmdHelper.waitForConsoleToClose("RunPreSilentInstaller", 500);
				
				fileName = FileHelper.getLastModifiedFile(applicationOS, deployPath + "\\logs", "pre_installer_");
				line = FileHelper.readFileContent(applicationOS, fileName);
				if (ValidationHelper.isNotEmpty(line))
					result = line[line.length-1];
			}
			else {
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				String command = "cd " + deployPath + "/bin && dos2unix *.sh && chmod 777 PreSilentInstallationTasks.sh && " + "./PreSilentInstallationTasks.sh " + arguments;
				if (updateJava)
					command = exportJava + " && " + command;
				
				String[] preSilentResult = remoteMachine.executeScripts(command);
				
				fileName = FileHelper.getLastModifiedFile(applicationOS, deployPath + "/logs", "pre_installer_");
				if (ValidationHelper.isEmpty(preSilentResult) || (!preSilentResult[1].contains("Pre Installation Tasks are now over") && !preSilentResult[1].contains(""))) {
					Log4jHelper.logError(preSilentResult[1] + "\n");
					FailureHelper.failTest("PreSilent Installer run failed. Please refer " + fileName + " log file for details");
				}
				
				command = "cd " + deployPath + "/logs" + " && grep -F \"" + logFileMessage + "\" " + fileName;
				line = remoteMachine.executeScripts(command);
				if (ValidationHelper.isNotEmpty(line))
					result = line[0];
			}
			
			if (ValidationHelper.isEmpty(result) || !result.contains(logFileMessage)) {
				Log4jHelper.logError(result + "\n");
				FailureHelper.failTest("PreSilent Installer run failed. Please refer " + fileName + " log file for details");
			}
			else {
				Log4jHelper.logInfo("Presilent installer ran successfully");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void runSilentInstaller(String arguments) throws Exception {
		try {
			String fileName = null;
			String result = null;
			String logFileMessage = "The installation is now complete";
			Log4jHelper.logInfo("Running Silent Installer....");
			
			if (applicationOS.equalsIgnoreCase("Windows")) {
				CommandLineScriptHelper cmdHelper = new CommandLineScriptHelper();
				cmdHelper.runScript("SilentInstaller", "RunSilentInstaller.bat", arguments, true);
				cmdHelper.waitForConsoleToClose("SilentInstaller", (configProp.getClientStartWaitTimeMins()*60));
				
				fileName = FileHelper.getLastModifiedFile(applicationOS, deployPath + "\\logs", "silent_installer_");
				String[] line = FileHelper.readFileContent(applicationOS, fileName);
				if (ValidationHelper.isNotEmpty(line))
					result = line[line.length-1];
			}
			else {
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				String command = "cd " + deployPath + "/bin && chmod 777 SilentInstaller.sh && ./SilentInstaller.sh ";
				if (updateJava)
					command = exportJava + " && " + command;
				String[] silentResult = remoteMachine.executeScripts(command);
				
				fileName = FileHelper.getLastModifiedFile(applicationOS, deployPath + "/logs", "silent_installer_");
				if (silentResult == null || silentResult.length < 2 || (!silentResult[1].contains("Installation in now complete") && !silentResult[1].contains(""))) {
					Log4jHelper.logError(silentResult[1] + "\n");
					FailureHelper.failTest("Silent Installer run failed. Please refer " + fileName + " log file for details");
				}
				
				command = "cd " + deployPath + "/logs" + " && grep -F \"" + logFileMessage + "\" " + fileName;
				String[] line = remoteMachine.executeScripts(command);
				if (ValidationHelper.isNotEmpty(line))
					result = line[0];
			}
			
			if (ValidationHelper.isEmpty(result) || !result.contains(logFileMessage)) {
				Log4jHelper.logError(result + "\n");
				FailureHelper.failTest("Silent Installer run failed. Please refer " + fileName + " log file for details");
			}
			else {
				Log4jHelper.logInfo("Silent Installer ran successfully");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}