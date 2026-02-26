package com.subex.automation.helpers.setup;

import java.io.File;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.application.screens.SettingsHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.StringHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.db.CreateNewDB;
import com.subex.automation.helpers.db.DBHelper;
import com.subex.automation.helpers.file.XMLReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.report.ReportHelper;
import com.subex.automation.helpers.scripts.ExecuteScript;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.DownloadBinaries;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.automation.helpers.util.RemoteMachineHelper;
import com.subex.automation.helpers.util.RunInstaller;
import com.subex.automation.helpers.util.UnzipHelper;

public class SetupHelper extends ROCAcceptanceTest {
	
	private String ciwFile = null;
	private String licenseOperator = null;
	private String licenseKey = null;
	
	private static String downloadPath = null;
	private String serverFileName = null;
	private String tomcatPath = null;
	private String clientContextPath = null;
	private String warFileName = null;
	
	public SetupHelper(String downloadPath, String tomcatPath, String clientContextPath) throws Exception {
		try {
			ciwFile = configProp.getStringProperty("ciwFile", "spark.ciw");
			licenseOperator = "Subex Ltd";
			licenseKey = FileHelper.readFileContent(automationPath + "\\src\\main\\resources\\License.txt");
			licenseKey = licenseKey.replace("\n", "");
			
			SetupHelper.downloadPath = downloadPath;
			serverFileName = configProp.getServerFileName();
			this.tomcatPath = tomcatPath;
			this.clientContextPath = clientContextPath;
			warFileName = clientContextPath + ".war";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void cleanupReport(String stepname) throws Exception {
		try {
			ReportHelper.clearStepKeyContent();
			stepName = stepname;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void updateReport(String message) throws Exception {
		try {
			ReportHelper.updateStepKey(stepName, "Green", message);
			ReportHelper.reportSuccess(null, false, null);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void stopAllServices() throws Exception {
		try {
			ControllerHelper controller = new ControllerHelper();
			cleanupReport("Stop Services");
			
			if (TextBoxHelper.isPresent("Login_Username_TextBox") || ButtonHelper.isPresent("NavigationMenu")) {
				controller.stopServices();
			}
			else {
				controller.killProcess("Server");
				controller.killProcess("TaskControllerService");
				controller.killProcess("StreamControllerService");
			}
			
			controller.stopTomcat();
			updateReport("Done");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createDB(String type, String stepname) throws Exception {
		try {
			cleanupReport(stepname);
			
			CreateNewDB createDB = new CreateNewDB();
			createDB.createDataBase(type);
			
			updateReport("Created");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void backupBinaries() throws Exception {
		try {
			cleanupReport("Backup Binaries");
			
			String currentDateTime = DateHelper.getCurrentDateTime("ddMMyyyy_HHmm");
			boolean backupCompleted = false;
			
			if (FileHelper.checkFileExists(downloadPath + "/" + serverFileName)) {
				String serverFile = currentDateTime + "_" + serverFileName;
				FileHelper.renameFile(applicationOS, downloadPath, serverFileName, serverFile);
				Log4jHelper.logInfo("'" + serverFileName + "' renamed to '" + serverFile + "'");
				backupCompleted = true;
			}
			
			if (FileHelper.checkFileExists(downloadPath + "/" + warFileName)) {
				String clientFile = currentDateTime + "_" + warFileName;
				FileHelper.renameFile(applicationOS, downloadPath, warFileName, clientFile);
				Log4jHelper.logInfo("'" + warFileName + "' renamed to '" + clientFile + "'");
				backupCompleted = true;
			}
			
			if (ValidationHelper.isTrue(configProp.getFreshInstallation())) {
				String datadir = configProp.getDataDirPath();
				if (FileHelper.checkDirectoryExists(applicationOS, datadir))
						FileHelper.cleanUpDir(applicationOS, datadir, true);
				if (FileHelper.isDirectoryEmpty(applicationOS, datadir + "/Task Logs"))
					FileHelper.renameFile(applicationOS, datadir + "/Task Logs", currentDateTime + "_" + "Task_Logs");
			}
			
			if (backupCompleted)
				updateReport("Completed");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void dirCleanup() throws Exception {
		try {
			cleanupReport("Directory Cleanup");
			if (!FileHelper.checkDirectoryExists(applicationOS, downloadPath))
				FileHelper.cleanUpDir(applicationOS, downloadPath, true);
			FileHelper.cleanUpDir(applicationOS, downloadPath + "/deploy", false);
			
			if (ValidationHelper.isFalse(configProp.getBackupOldBinaries())) {
				FileHelper.deleteFile(applicationOS, downloadPath + "/" + serverFileName);
				FileHelper.deleteFile(applicationOS, downloadPath + "/" + warFileName);
			}
			
			FileHelper.cleanUpDir(applicationOS, tomcatPath + "/webapps/" + clientContextPath, false);
			FileHelper.cleanUpDir(applicationOS, tomcatPath + "/logs/", true);
			
			String workDirectory = tomcatPath + "/work/Catalina/localhost/";
			if (FileHelper.checkDirectoryExists(applicationOS, workDirectory))
				FileHelper.cleanUpDir(applicationOS, workDirectory, true);
			FileHelper.deleteFile(applicationOS, tomcatPath + "/webapps/" + warFileName);
			
			if (ValidationHelper.isTrue(configProp.getFreshInstallation())) {
				String datadir = configProp.getDataDirPath();
				FileHelper.cleanUpDir(applicationOS, datadir + "/Task Logs/", false);
				FileHelper.cleanUpDir(applicationOS, datadir + "/Manual Import Log/", false);
				FileHelper.cleanUpDir(applicationOS, datadir + "/ParseOutput/", false);
				FileHelper.cleanUpDir(applicationOS, datadir + "/Collected Files/", false);
				FileHelper.cleanUpDir(applicationOS, datadir + "/Online_LDC/", false);
			}
			
			updateReport("Completed");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static boolean download(DownloadBinaries download, String downloadURL, String fileName, boolean useAuthentication) throws Exception {
		try {
			if (download.isURLAvailable(downloadURL, downloadPath + "/" + fileName, useAuthentication)) {
				Log4jHelper.logInfo("Download url is available\n");
				
				if (applicationOS.equalsIgnoreCase("Windows")) {
					download.download(downloadURL, downloadPath + "/" + fileName);
				}
				else {
					FileHelper.deleteFileIfExists(applicationOS, downloadPath + "/" + fileName);
					downloadURL = downloadURL.replace("$", "\\$");
					String command = null;
					if (useAuthentication && ValidationHelper.isNotEmpty(configProp.getJenkinsUsername()))
						command = "cd " + downloadPath + " && curl -o " + fileName + " -u " + configProp.getJenkinsUsername() + ":" + configProp.getJenkinsPassword() + " \"" + downloadURL + "\"";
					else
						command = "cd " + downloadPath + " && curl -o " + fileName + " \"" + downloadURL + "\"";
					
					RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
					remoteMachine.executeScripts(command, true);
				}
				
				ReportHelper.updateStepKey("JENKINS URL", "Blue", downloadURL);
				return true;
			}
			else {
				Log4jHelper.logInfo("Download url is not available\n");
				return false;
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void serverDownload() throws Exception {
		try {
			cleanupReport("Server Binary");
			
			DownloadBinaries downloadBinaries = new DownloadBinaries();
			JenkinsHelper jenkinsHelper = new JenkinsHelper();
			String artifactoryURL = configProp.getArtifactoryURL();
			boolean isURLAvailable = false;
			String downloadURL = null;
			
			String jenkinsURL = configProp.getJenkinsURL();
			downloadURL = jenkinsHelper.getDownloadURL(jenkinsURL, "Server");
			Log4jHelper.logInfo("Checking if Server binary download url is available in Jenkins\n");
			Log4jHelper.logInfo("Download url : " + downloadURL + "\n");
			isURLAvailable = download(downloadBinaries, downloadURL, serverFileName, true);
			
			if (!isURLAvailable && ValidationHelper.isNotEmpty(artifactoryURL)) {
				downloadURL = jenkinsHelper.artifactoryServerURL(artifactoryURL);
				Log4jHelper.logInfo("Checking if Server binary download url is available in Artifactory\n");
				Log4jHelper.logInfo("Download url : " + downloadURL + "\n");
				isURLAvailable = download(downloadBinaries, downloadURL, serverFileName, false);
			}
			
			if (isURLAvailable) {
				updateReport("Downloaded");
			}
			else {
				FailureHelper.failTest("Server binary download did not happen as download URL was not available");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void clientDownload() throws Exception {
		try {
			cleanupReport("Client Binary");
			
			DownloadBinaries downloadBinaries = new DownloadBinaries();
			JenkinsHelper jenkinsHelper = new JenkinsHelper();
			String artifactoryURL = configProp.getArtifactoryURL();
			boolean isURLAvailable = false;
			String downloadURL = null;
			
			String jenkinsURL = configProp.getJenkinsURL();
			downloadURL = jenkinsHelper.getDownloadURL(jenkinsURL, "Client");
			Log4jHelper.logInfo("Checking if Client binary download url is available in Jenkins\n");
			Log4jHelper.logInfo("Download url : " + downloadURL + "\n");
			isURLAvailable = download(downloadBinaries, downloadURL, warFileName, true);
			
			if (!isURLAvailable && ValidationHelper.isNotEmpty(artifactoryURL)) {
				downloadURL = jenkinsHelper.artifactoryClientURL(artifactoryURL);
				Log4jHelper.logInfo("Checking if Client binary download url is available in Artifactory\n");
				Log4jHelper.logInfo("Download url : " + downloadURL + "\n");
				isURLAvailable = download(downloadBinaries, downloadURL, warFileName, false);
			}
			
			if (isURLAvailable) {
				FileHelper.copyFile(downloadPath, tomcatPath + "/webapps/", warFileName, warFileName, true);
				updateReport("Downloaded");
			}
			else {
				FailureHelper.failTest("Client binary download did not happen as download URL was not available");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void rocviewDownload() throws Exception {
		try {
			cleanupReport("ROCView Binary");
			String downloadLocation = configProp.getROCViewFolderLocation();
			FileHelper.cleanUpDir(applicationOS, downloadLocation + "/view", false);
			UnzipHelper unzip = new UnzipHelper();
			unzip.unzip(applicationOS, "view.zip", downloadLocation);
			
			updateReport("Downloaded");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void extractServerDeploy() throws Exception {
		try {
			cleanupReport("Server Deploy");
			
			ROCHelper rocHelper = new ROCHelper();
			String downloadedServerFile = downloadPath + "/" + serverFileName;
			String delimiter = "\\\\";
			if (!applicationOS.equalsIgnoreCase("Windows"))
				delimiter = "/";
			
			UnzipHelper unZip = new UnzipHelper();
			unZip.unzip(applicationOS, downloadedServerFile, downloadPath);
			deployPath = configProp.getDeployPath();
			FileHelper.cleanUpDir(applicationOS, deployPath, "No");
			
			if (ValidationHelper.isTrue(configProp.getCopyCompleteDeploy())) {
				FileHelper.cleanUpDir(applicationOS, deployPath, "No");
				
				String[] deploy = deployPath.split(delimiter);
				String deployFolderName = deploy[deploy.length-1];
				int size = deployPath.length() - deployFolderName.length();
				if (deployPath.endsWith("/"))
					size--;
				String deployFolderPath = deployPath.substring(0, size);
				
				FileHelper.copyFile(downloadPath, deployFolderPath, "deploy", "", true);
			}
			else {
				String[] filesToCopy = configProp.getDeployFilesToCopy().split(",");
				String downloadedDeploy = rocHelper.getDeployPath(downloadedServerFile);
				
				for (int i = 0; i < filesToCopy.length; i++) {
					String[] file = filesToCopy[i].split(delimiter);
					String fileName = file[file.length-1];
					int size = filesToCopy[i].length() - fileName.length();
					
					if (size > 0) {
						String path = filesToCopy[i].substring(0, size);
						FileHelper.copyFile(downloadedDeploy + path, deployPath + path, filesToCopy[i], filesToCopy[i], true);
					}
					else
						FileHelper.copyFile(downloadedDeploy, deployPath, filesToCopy[i], filesToCopy[i], true);
				}
			}
			
			deployPath = rocHelper.getDeployPath(configProp.getDeployPath());
			updateReport("Extracted");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static int[] getLineNumber(String dbType, int startLineNumber) throws Exception {
		try {
			int[] lineNumber = new int[6];
			startLineNumber += 2;
			int length = 6;
			
			if (dbType.equalsIgnoreCase("oracle")) {
				lineNumber = new int[5];
				length = 5;
			}
			
			for (int i = 0; i < length; i++)
				lineNumber[i] = startLineNumber + i;
			
			return lineNumber;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String[] getReplaceProperties(String dbType, String[] dbDetails) throws Exception {
		try {
			String[] propertyValue = new String[6];
			String unicode = "N";
			if (ValidationHelper.isTrue(dbDetails[7]))
				unicode = "Y";
			
			if (dbType.equalsIgnoreCase("oracle")) {
				propertyValue = new String[5];
				propertyValue[0] = "DB_TYPE=Oracle";
				propertyValue[1] = "HOST_NAME=" + dbDetails[1];
				propertyValue[2] = "INSTANCE=" + dbDetails[5];
				propertyValue[3] = "PORT_NO=" + dbDetails[6];
				propertyValue[4] = "UNICODE=" + unicode;
			}
			else if (dbType.equalsIgnoreCase("sqlserver") || dbType.equalsIgnoreCase("sql server")) {
				propertyValue[0] = "DB_TYPE=MS SQL Server";
				propertyValue[1] = "HOST_NAME=" + dbDetails[1];
				propertyValue[2] = "DATABASE=" + dbDetails[4];
				propertyValue[3] = "UNICODE=" + unicode;
				
				if (ValidationHelper.isEmpty(dbDetails[5])) {
					propertyValue[4] = "IS_NAMED_INSTANCE=Y";
					propertyValue[5] = "NAMED_INSTANCE=";
				}
				else {
					propertyValue[4] = "IS_NAMED_INSTANCE=N";
					propertyValue[5] = "NAMED_INSTANCE=" + dbDetails[5];
				}
			}
			else if (dbType.equalsIgnoreCase("postgres") || dbType.equalsIgnoreCase("postgresql")) {
				propertyValue[0] = "DB_TYPE=Postgres";
				propertyValue[1] = "HOST_NAME=" + dbDetails[1];
				
				if (ValidationHelper.isEmpty(dbDetails[6]))
					propertyValue[2] = "PORT_NO=5432";
				else
					propertyValue[2] = "PORT_NO=" + dbDetails[6];
				
				propertyValue[3] = "UNICODE=" + unicode;
				propertyValue[4] = "DATABASE=" + dbDetails[4];
				propertyValue[5] = "ENABLE_HA=" + dbDetails[8];
			}
			
			return propertyValue;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createInstallerPropertyFile() throws Exception {
		try {
			cleanupReport("Installer Property File");
			
			String srcDir = deployPath + "/config";
			String destinationDir = configProp.getDownloadDirectory();
			if (srcDir.contains("//"))
				srcDir = srcDir.replace("//", "/");
			String fileName = "installer.properties";
			FileHelper.copyFile(applicationOS, srcDir, destinationDir, fileName, fileName, true);
			
			String[] dbDetails = DBHelper.getReferenceDatabase();
			String[] machine = configProp.getMachineName().split(",", -1);
			String dbType = StringHelper.convertToCamelCase(dbDetails[0]);
			
			int startLineNumber = FileHelper.getLineNumber(applicationOS, srcDir, fileName, dbType + " DB Details");
			int[] lineNumber = getLineNumber(dbType, startLineNumber);
			String[] replaceProperties = getReplaceProperties(dbType, dbDetails);
			FileHelper.updateFile(applicationOS, srcDir, fileName, lineNumber, replaceProperties);
			
			String[] propertyName = {"WORKSPACE_FILE", "SYS_OPERATOR_NAME", "SYS_LICENSE_KEY", "MACHINE_NAME", "HOST_ADDRESS", "DATA_DIR"};
			String[] propertyValue = {ciwFile, licenseOperator, licenseKey, machine[0], configProp.getRemoteHostname(), configProp.getDataDirPath()};
			FileHelper.updatePropertyFile(applicationOS, srcDir, fileName, propertyName, propertyValue);
			
			updateReport("Updated");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void runInstaller() throws Exception {
		try {
			cleanupReport("Installer Run");
			RunInstaller installer = new RunInstaller();
			installer.runInstaller(configProp.getJava11Path());
			
			updateReport("Succeeded");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void extractClientWar() throws Exception {
		try {
			cleanupReport("Client War");
			String dir = tomcatPath + "/webapps/";
			
			if (applicationOS.equalsIgnoreCase("Windows")) {
				File directory = new File(dir + clientContextPath);
				Boolean dirCreation = directory.exists() || directory.mkdir() ? true : false;
					
				if(dirCreation) {
					UnzipHelper unZip = new UnzipHelper();
					unZip.unzip(applicationOS, dir + warFileName, dir + clientContextPath);
					FileHelper.deleteFile(applicationOS, dir + warFileName);
				}
			}
			else {
				String[] filePath = {dir};
				String[] fileName = {warFileName};
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				remoteMachine.extractFiles(filePath, fileName);
			}
			
			ReportHelper.updateStepKey(stepName, "Green", "Extracted");
			ReportHelper.reportSuccess(null, false, null);
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void extractRocview() throws Exception {
		try {
			cleanupReport("ROC View");
			FileHelper.copyFile(configProp.getROCViewFolderLocation(), tomcatPath + "/webapps/" + clientContextPath, "view", "view", true);
			updateReport("Extracted");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateUsageDBPort() throws Exception {
		try {
			String database = configProp.getUsageDatabase();
			if (database.equalsIgnoreCase("Oracle")) {
				String usagePort = configProp.getUsagePortNumber();
				
				if (ValidationHelper.isNotEmpty(usagePort) && !usagePort.equals("1521")) {
					Log4jHelper.logInfo("Updating Usage DB Port in Data Source Type\n");
					cleanupReport("Usage DB Port");
					ExecuteScript.exeQuery("update property_inst set pri_value = 'jdbc:oracle:thin:@[%MACHINE%]:" + usagePort + ":%INSTANCE%' WHERE "
							+ "prd_id = (SELECT prd_id FROM property_dfn WHERE PRD_KEY ='HibernateConnectionUrl') and "
							+ "pig_id = (select pig_id from property_inst_group where pig_name like 'Oracle Properties' and pdg_id = (select pdg_id from property_dfn_group where pdg_key like 'DataSourceTypeProperties'))");
					
					updateReport("Updated");
					Log4jHelper.logInfo("Usage DB Port " + usagePort + " updated in Data Source Type\n");
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateServerName() throws Exception {
		try {
			cleanupReport("Server Name");
			String contextPath = GenericHelper.getPath(applicationOS, (tomcatPath + "/webapps/" + clientContextPath));
			String sparkConfigFile = "SparkPageConfig.prop";
			String serverName = configProp.getMachineName();
			Log4jHelper.logInfo("Updating ServerName in Tomcat " + sparkConfigFile + " file\n");
			
			String[] propertyName = {"Spark.ServerName"};
			if (configProp.getProduct().equalsIgnoreCase("ROCRA") || configProp.getProduct().equalsIgnoreCase("ROC RA") || configProp.getProduct().equalsIgnoreCase("RA"))
				propertyName[0] = "Moneta.ServerName";
			String[] propertyValue = {serverName};
			FileHelper.updatePropertyFile(applicationOS, contextPath, sparkConfigFile, propertyName, propertyValue);
			
			updateReport("Updated");
			Log4jHelper.logInfo("ServerName '" + serverName + "' updated in Tomcat " + sparkConfigFile + " file\n");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateTimeZoneId() throws Exception {
		try {
			cleanupReport("Time Zone ID");
			String contextPath = GenericHelper.getPath(applicationOS, (tomcatPath + "/webapps/" + clientContextPath + "/WEB-INF"));
			String webFilename = "web.xml";
			String timeZoneId = configProp.getTimeZoneId();
			Log4jHelper.logInfo("Updating Time Zone ID in Tomcat " + webFilename + " file\n");
			
			if (applicationOS.equalsIgnoreCase("Windows")) {
				String fileNameWithPath = contextPath + "/" + webFilename;
				String[][] stringToReplace = {{"UTC", timeZoneId}};
				XMLReader.replaceLine(fileNameWithPath, stringToReplace);				
			}
			else {
				String command = "cd " + contextPath + " && sed -ie 's#UTC#" + timeZoneId + "#g' " + webFilename;
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				remoteMachine.executeScripts(command);
			}
			
			updateReport("Updated");
			Log4jHelper.logInfo("Time Zone ID '" + timeZoneId + "' updated in Tomcat " + webFilename + " file\n");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void startTomcat() throws Exception {
		try {
			cleanupReport("Tomcat");
			
			String sourceDir = deployPath + "/config";
			String destinationDir = tomcatPath + "/webapps/" + clientContextPath + "/WEB-INF/classes";
			FileHelper.copyFile(sourceDir, destinationDir, "hibernate.cfg.xml", "hibernate.cfg.xml", true);
			ControllerHelper controller = new ControllerHelper();
			controller.startTomcat();
			
			updateReport("Started");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateRootPassword() throws Exception {
		try {
			cleanupReport("Update Root Password");
			String newPassword = configProp.getApplicationPassword();
			
			if (newPassword.equals("welcome")) {
				newPassword = "welcome1";
				FileHelper.updatePropertyFile(configFile, "applicationPassword", newPassword);
				if (!configFile.equals("config.properties"))
					FileHelper.updatePropertyFile(automationPath + "\\src\\main\\resources\\config.properties", "applicationPassword", newPassword);
			}
			
			LoginHelper login = new LoginHelper();
			login.login(configProp.getApplicationName(), "Root", "welcome", newPassword);
			
			updateReport("Done");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void checkProperty(String[] propertyName, String propertyType) throws Exception {
		try {
			TabHelper.gotoTab(propertyType);
			SettingsHelper settings = new SettingsHelper();
			String wrapperID = settings.getWrapperID(propertyType);
			
			for (int i = 0; i < propertyName.length; i++) {
				boolean isPresent = PropertyGridHelper.isPropertyPresent(wrapperID, propertyName[i].replace("\n", ""));
				if (!isPresent)
					FailureHelper.failTest("Property '" + propertyName[i] + "' is missing in " + propertyType);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void checkDefaultSettingsProperties() throws Exception {
		try {
			cleanupReport("Check Settings Properties");
			SettingsHelper settings = new SettingsHelper();
			settings.navigateToSettings();
			
			String tempProperties = FileHelper.readFileContent(automationPath + "\\src\\main\\resources\\Settings_User_Properties.txt");
			String[] userProperties = tempProperties.split(";", -1);
			checkProperty(userProperties, "User Properties");
			
			tempProperties = FileHelper.readFileContent(automationPath + "\\src\\main\\resources\\Settings_Client_Properties.txt");
			String[] clientProperties = tempProperties.split(";", -1);
			checkProperty(clientProperties, "Client Properties");
			
			tempProperties = FileHelper.readFileContent(automationPath + "\\src\\main\\resources\\Settings_Server_Properties.txt");
			String[] serverProperties = tempProperties.split(";", -1);
			checkProperty(serverProperties, "Server Properties");
			
			tempProperties = FileHelper.readFileContent(automationPath + "\\src\\main\\resources\\Settings_System_Properties.txt");
			String[] systemProperties = tempProperties.split(";", -1);
			checkProperty(systemProperties, "System Properties");
			
			if (TabHelper.isPresent("Dice Properties")) {
				tempProperties = FileHelper.readFileContent(automationPath + "\\src\\main\\resources\\Settings_Dice_Properties.txt");
				String[] diceProperties = tempProperties.split(";", -1);
				checkProperty(diceProperties, "Dice Properties");
			}
			
			settings.closeSettings();
			updateReport("Done");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}