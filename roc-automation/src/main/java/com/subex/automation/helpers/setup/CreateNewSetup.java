package com.subex.automation.helpers.setup;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.report.ReportHelper;
import com.subex.automation.helpers.scripts.ExecuteScript;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

@Test(groups={"Setup"})
public class CreateNewSetup extends ROCAcceptanceTest {
	
	private static boolean isSetupInstalled = false;
	
	@Test(groups={"Setup"})
	public static void createSetup() throws Exception {
		try {
			String downloadPath = GenericHelper.getPath(applicationOS, configProp.getBinaryDownloadPath());
			String tomcatPath = GenericHelper.getPath(applicationOS, configProp.getTomcatPath());
			ROCHelper rocHelper = new ROCHelper();
			String clientContextPath = rocHelper.getTomcatContextPath();
			
			SetupHelper setupHelper = new SetupHelper(downloadPath, tomcatPath, clientContextPath);
			setupHelper.stopAllServices();
			
			if (ValidationHelper.isTrue(configProp.getFreshInstallation())) {
				Log4jHelper.logInfo("Creating reference database/user created\n");
				setupHelper.createDB("Reference", "Reference DB");
				Log4jHelper.logInfo("Reference database/user created\n");
				
				Log4jHelper.logInfo("Creating usage database/user created\n");
				setupHelper.createDB("Usage", "Usage DB");
				Log4jHelper.logInfo("Usage database/user created\n");
			}
			
			if (ValidationHelper.isTrue(configProp.getBackupOldBinaries())) {
				Log4jHelper.logInfo("Starting older binaries backup\n");
				setupHelper.backupBinaries();
				Log4jHelper.logInfo("Older binaries backup completed\n");
			}
			
			Log4jHelper.logInfo("Starting directories cleanup\n");
			setupHelper.dirCleanup();
			Log4jHelper.logInfo("Directories cleanup completed\n");
			
			Log4jHelper.logInfo("Starting Server binary download\n");
			setupHelper.serverDownload();
			Log4jHelper.logInfo("Server binary downloaded\n");
			
			Log4jHelper.logInfo("Starting Client binary download\n");
			setupHelper.clientDownload();
			Log4jHelper.logInfo("Client binary downloaded\n");
			
			Log4jHelper.logInfo("Starting ROCView binary download\n");
			setupHelper.rocviewDownload();
			Log4jHelper.logInfo("ROCView binary downloaded\n");
			
			Log4jHelper.logInfo("Extracting Server deploy\n");
			setupHelper.extractServerDeploy();
			Log4jHelper.logInfo("Extracted Server deploy\n");
			
			Log4jHelper.logInfo("Updating installer.properties file\n");
			setupHelper.createInstallerPropertyFile();
			Log4jHelper.logInfo("Updated installer.properties file\n");
			
			if (ValidationHelper.isTrue(configProp.getRunInstaller())) {
				Log4jHelper.logInfo("Running Installer\n");
				setupHelper.runInstaller();
				Log4jHelper.logInfo("Installer ran successfully\n");
			}
			
			Log4jHelper.logInfo("Extracting Client war file\n");
			setupHelper.extractClientWar();
			Log4jHelper.logInfo("Extracted Client war file\n");
			
			Log4jHelper.logInfo("Extracting ROCView folder\n");
			setupHelper.extractRocview();
			Log4jHelper.logInfo("Extracted ROCView folder\n");
			
			ExecuteScript.exeQuery("update property_inst set pri_value = '90' where prd_id = (select prd_id from property_dfn where prd_key like 'SvrMaxJumpSearchDays')");
			setupHelper.updateUsageDBPort();
			
			setupHelper.updateServerName();
			setupHelper.updateTimeZoneId();
			setupHelper.startTomcat();
			
			Log4jHelper.logInfo("Updating Root password\n");
			setupHelper.updateRootPassword();
			Log4jHelper.logInfo("Root password updated\n");
			
			Log4jHelper.logInfo("Checking Settings default properties\n");
			setupHelper.checkDefaultSettingsProperties();
			Log4jHelper.logInfo("Settings default properties checked\n");
			
			ReportHelper.clearStepKeyContent();
			isSetupInstalled = true;
			stepName = "Create Setup";
		}
		catch (Exception e) {
			isSetupInstalled = false;
			FailureHelper.setErrorMessage(e);
			ReportHelper.reportFailure(e);
		} finally {
			if (isSetupInstalled)
				isInstallationDone = true;
			else
				isInstallationDone = false;
		}
	}
}