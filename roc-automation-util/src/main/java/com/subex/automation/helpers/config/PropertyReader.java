package com.subex.automation.helpers.config;

import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class PropertyReader extends ConfigReader {
	
	public PropertyReader( String configFileName ) throws Exception {
		super( ValidationHelper.isEmpty( configFileName ) ? "config.properties" : configFileName );
	}
	
	public String getProperty(String propertyName) throws Exception {
		String temp = getProperties().getProperty(propertyName);
		
		if (temp != null)
			return temp.trim();
		else
			return null;
	}
	
	public String getStringProperty(String propertyName) throws Exception {
		String temp = getProperty(propertyName);
		
		if (temp != null)
			return temp.trim();
		else {
			FailureHelper.failTest("Property '" + propertyName + "' is missing in config.properties file");
			return null;
		}
	}
	
	public String getStringProperty(String propertyName, String defaultValue) throws Exception {
		String temp = getProperty(propertyName);
		
		if (temp != null)
			return temp.trim();
		else {
			return defaultValue;
		}
	}
	
	public int getIntegerProperty(String propertyName) throws Exception {
		String temp = getProperties().getProperty(propertyName);
		
		if (temp != null ) {
			if (temp.equals(""))
				return 0;
			else
				return Integer.parseInt(temp.trim());
		}
		else {
			FailureHelper.failTest("Property '" + propertyName + "' is missing in config.properties file");
			return 0;
		}
	}
	
	public int getIntegerProperty(String propertyName, int defaultValue) throws Exception {
		String temp = getProperties().getProperty(propertyName);
		
		if (temp != null) {
			if (temp.equals(""))
				return 0;
			else
				return Integer.parseInt(temp.trim());
		}
		else {
			return defaultValue;
		}
	}
	
	public int getIntegerProperty(String propertyName, String defaultValue) throws Exception {
		String temp = getProperties().getProperty(propertyName).trim();
		
		if (temp != null) {
			if (temp.equals(""))
				return 0;
			else
				return Integer.parseInt(temp);
		}
		else {
			return Integer.parseInt(defaultValue);
		}
	}
	
	public String getSeleniumDriver() throws Exception {
		return getStringProperty("seleniumDriver", "geckodriver.exe");
	}
	
	public String getChromeDriver() throws Exception {
		return getStringProperty("chromeDriver", "chromedriver.exe");
	}
	
	public String getIEDriver() throws Exception {
		return getStringProperty("ieDriver", "msedgedriver.exe");
	}
	
	
	public String getUtilPath() throws Exception {
		return getStringProperty("utilPath");
	}
	
	public String getBinaryDownloadPath() throws Exception {
		return getStringProperty( "binaryDownloadPath" );
	}
	
	public String getDeployPath() throws Exception {
		return getStringProperty( "deployPath" );
	}

	public String getTomcatPath() throws Exception {
		return getStringProperty( "tomcatPath" );
	}
	
	public String getDataDirPath() throws Exception {
		return getStringProperty( "dataDir" );
	}
	
	public String getProduct() throws Exception {
		return getStringProperty("product");
	}
	
	public String getMachineName() throws Exception {
		return getStringProperty( "machineName" );
	}
	
	public String getOS() throws Exception {
		return getStringProperty( "os" );
	}
	
	/*
	 * This property is used to get the Tomcat console title
	 * @return
	 */
	public String getTomcatConsoleTitle() throws Exception {
		return getStringProperty("tomcatConsoleTitle", "Tomcat");
	}
	
	public String getEntityExportVersion() throws Exception {
		return getStringProperty("EntityExportVersion", "");
	}
	
	public String getStartTomcat() throws Exception {
		return getStringProperty("startTomcat", "No");
	}
	
	public String getDateFormat() throws Exception {
		return getStringProperty("dateFormat");
	}
	
	public String getTimeFormat() throws Exception {
		return getStringProperty("timeFormat");
	}
	
	
	
	public String getRemoteHostname() throws Exception {
		return getStringProperty("remoteHostname");
	}
	
	public String getRemoteUsername() throws Exception {
		return getStringProperty("remoteUsername");
	}
	
	public String getRemotePassword() throws Exception {
		return getStringProperty("remotePassword");
	}
	
	public int getRemotePortNumber() throws Exception {
		return getIntegerProperty("remotePortNumber");
	}
	
	

	public String getApplicationName() throws Exception {
		return getStringProperty("applicationName");
	}
	
	public String getApplicationUsername() throws Exception {
		return getStringProperty("applicationUsername");
	}
	
	public String getApplicationPassword() throws Exception {
		return getStringProperty("applicationPassword");
	}
	
	
	/*
	 * This property is used for Tomcat restart wait time. 
	 * @return
	 */
	public int getClientStartWaitTimeMins() throws Exception {
		return getIntegerProperty("clientStartWaitTimeMins", 5);
	}
	
	/*
	 * This property is used to specify the wait time for Search screen actions
	 * @return
	 */
	public int getSearchScreenWaitSec() throws Exception {
		return getIntegerProperty("searchScreenWaitSec");
	}
	
	/*
	 * This property is used to specify the wait time for Detail screen actions
	 * @return
	 */
	public int getDetailScreenWaitSec() throws Exception {
		return getIntegerProperty("detailScreenWaitSec");
	}
	
	public int getCustomScreenWaitSec() throws Exception {
		return getIntegerProperty("customScreenWaitSec", 300);
	}
	
	
	// Reference DB Properties
	public String getDbType() throws Exception {
		return getStringProperty( "dbType" );
	}
	
	public String getDbMachineName() throws Exception {
		return getStringProperty( "dbMachineName" );
	}
	
	public String getDbInstance() throws Exception {
		return getStringProperty( "dbInstance" );
	}
	
	public String getDB() throws Exception {
		return getStringProperty( "database" );
	}
	
	public String getDbUserName() throws Exception {
		return getStringProperty( "dbUserName" );
	}
	
	public String getDbPassword() throws Exception {
		return getStringProperty( "dbPassword" );
	}
	
	public String getDBPortNumber() throws Exception {
		return getStringProperty( "dbPortNumber" );
	}
	
	public String getDbUnicode() throws Exception {
		return getStringProperty("dbUnicode", "N");
	}
	
	public String getDbEnableHA() throws Exception {
		return getStringProperty("dbEnableHA", "N");
	}
	
	public String getDbLinkToReferenceDB() throws Exception {
		return getStringProperty("dbLinkToReferenceDB", "");
	}

	public String getDbLinkToStagingDB() throws Exception {
		return getStringProperty("dbLinkToStagingDB", "");
	}

	public String getDbLinkToUsageServer() throws Exception {
		return getStringProperty("dbLinkToUsageServer", "");
	}

	public String getDbMaxActiveConnections() throws Exception {
		return getStringProperty("dbMaxActiveConnections", "");
	}
	
	public String getDbMaxIdleConnections() throws Exception {
		return getStringProperty("dbMaxIdleConnections", "");
	}
	
	public String getDbMinIdleConnections() throws Exception {
		return getStringProperty("dbMinIdleConnections", "");
	}
	
	public String getDbExhaustedAction() throws Exception {
		return getStringProperty("dbExhaustedAction", "");
	}
	
	public String getDbONSConfigOracleRAC() throws Exception {
		return getStringProperty("dbONSConfig", "");
	}
	
	
	// Usage Database Properties
	public String getUsageDBType() throws Exception {
		return getStringProperty( "usageDBType" );
	}
	
	public String getUsageMachineName() throws Exception {
		return getStringProperty( "usageMachineName" );
	}
	
	public String getUsageInstance() throws Exception {
		return getStringProperty( "usageInstance" );
	}
	
	public String getUsageSecondaryMachine() throws Exception {
		return getStringProperty( "usageSecondaryMachines", "" );
	}
	
	public String getUsageDatabase() throws Exception {
		return getStringProperty( "usageDatabase" );
	}
	
	public String getUsageUsername() throws Exception {
		return getStringProperty( "usageUsername" );
	}
	
	public String getUsagePassword() throws Exception {
		return getStringProperty( "usagePassword" );
	}
	
	public String getUsagePortNumber() throws Exception {
		return getStringProperty( "usagePortNumber" );
	}
	
	public String getUsageUnicode() throws Exception {
		return getStringProperty("usageUnicode");
	}
	
	public String getUsageLinkToReferenceDB() throws Exception {
		return getStringProperty("usageLinkToReferenceDB", "");
	}

	public String getUsageLinkToStagingDB() throws Exception {
		return getStringProperty("usageLinkToStagingDB", "");
	}

	public String getUsageLinkToUsageServer() throws Exception {
		return getStringProperty("usageLinkToUsageServer", "");
	}

	public String getUsageMaxActiveConnections() throws Exception {
		return getStringProperty("usageMaxActiveConnections", "");
	}
	
	public String getUsageMaxIdleConnections() throws Exception {
		return getStringProperty("usageMaxIdleConnections", "");
	}
	
	public String getUsageMinIdleConnections() throws Exception {
		return getStringProperty("usageMinIdleConnections", "");
	}
	
	public String getUsageExhaustedAction() throws Exception {
		return getStringProperty("usageExhaustedAction", "");
	}
	
	public String getUsageONSConfigOracleRAC() throws Exception {
		return getStringProperty("usageONSConfig", "");
	}
	
		
	
	public String getJenkinsURL() throws Exception {
		return getStringProperty( "jenkinsURL" );
	}
	
	public String getJenkinsClientProject() throws Exception {
		return getStringProperty( "jenkinsClientProject" );
	}
	
	public String getJenkinsServerProject() throws Exception {
		return getStringProperty( "jenkinsServerProject" );
	}
	
	public String getClientWarFilename() throws Exception {
		return getStringProperty( "warFileName" );
	}
	
	public String getServerFileName() throws Exception {
		return getStringProperty( "serverFileName" );
	}
	
	public String getArtifactoryURL() throws Exception {
		return getStringProperty( "artifactoryUrl" );
	}
	
	public String getArtifactoryServerProject() throws Exception {
		return getStringProperty( "artifactoryServerProject" );
	}
	
	public String getArtifactoryClientProject() throws Exception {
		return getStringProperty( "artifactoryClientProject" );
	}
	
	public String getROCViewFolderLocation() throws Exception {
		return getStringProperty( "ROCViewFolderLocation" );
	}
	
	public String getRunInstaller() throws Exception {
		return getStringProperty( "runInstaller" );
	}
	
	public String getUseSilentInstaller() throws Exception {
		return getStringProperty( "useSilentInstaller", "Yes" );
	}
	
	public String getFreshInstallation() throws Exception {
		return getStringProperty( "freshInstallation", "Yes" );
	}

	public String getBackupOldBinaries() throws Exception {
		return getStringProperty( "backupOldBinaries" );
	}

	public String getCopyCompleteDeploy() throws Exception {
		return getStringProperty( "copyCompleteDeploy" );
	}
	
	public String getDeployFilesToCopy() throws Exception {
		return getStringProperty( "deployFilesToCopy" );
	}
	
	public String getSendMail() throws Exception {
		return getStringProperty( "sendMail", "No" );
	}
	
	public String getMailHostname() throws Exception {
		return getStringProperty( "mailHostname" );
	}
	
	public String getMailSendingUserName() throws Exception {
		return getStringProperty( "mailSendingUserName" );
	}
	
	public String getMailToAddress() throws Exception {
		return getStringProperty( "mailToAddress" );
	}
	
	public String getMailCCAddress() throws Exception {
		return getStringProperty( "mailCCAddress" );
	}
	
	
	public String getClientBuildNo() throws Exception {
		return getStringProperty( "clientBuildNo", "" );
	}
	
	public String getServerBuildNo() throws Exception {
		return getStringProperty( "serverBuildNo", "" );
	}
	
	public int getInstallerWaitTime() throws Exception {
		return getIntegerProperty( "installerWaitTimeMins" );
	}
	
	public String getTimeZoneId() throws Exception {
		return getStringProperty( "timeZoneId", "Asia/Kolkata" );
	}
	
	public String getUseBuildNumber() throws Exception {
		return getStringProperty( "useBuildNumber" );
	}

	public String getClientPreviousBuildNo() throws Exception {
		return getStringProperty( "clientPreviousBuildNo" );
	}
	
	public String getServerPreviousBuildNo() throws Exception {
		return getStringProperty( "serverPreviousBuildNo" );
	}
	
	public String getJenkinsUsername() throws Exception {
		return getStringProperty( "jenkinsUsername" );
	}
	
	public String getJenkinsPassword() throws Exception {
		return getStringProperty( "jenkinsPassword" );
	}

	public String getEmbedImageInReport() throws Exception {
		return getStringProperty( "embedImageInReport", "Yes" );
	}
	
	public String getDataDateFormat() throws Exception {
		return getStringProperty( "dataDateFormat" );
	}
	
	public String getPostgreSQLRestoreExeFile() throws Exception {
		return getStringProperty( "postgreSQLRestoreExeFile" );
	}
	
	public String getCurrentNowDateTime() throws Exception {
		return getStringProperty( "currentNowDateTime" );
	}
	
	public String getPreviousNowDateTime() throws Exception {
		return getStringProperty( "previousNowDateTime" );
	}
	
	public String getDateTimeFormat() throws Exception {
		return getStringProperty( "dateTimeFormat", "ddMMyyyy_HHmmss" );
	}
	
	public String getLastReportDate() throws Exception {
		return getStringProperty( "lastReportDate" );
	}
	
	public String getAutomationSVNPath() throws Exception {
		return getStringProperty( "automationSVNPath" );
	}
	
	public String getClientURL() throws Exception {
		return getStringProperty( "clientUrl" );
	}

	public Integer getSearchTryCount() throws Exception {
		return getIntegerProperty( "searchTryCount" );
	}
	
	/*
	 * FireFox is default browser. Supported values are firefox, ie and chrome
	 */
	public String getBrowser() throws Exception {
		return getStringProperty( "browser", "firefox" );
	}

	public String getDownloadDirectory() throws Exception {
		return getStringProperty( "downloadDirectory" );
	}
	
	public String getCalculatePerformance() throws Exception {
		return getStringProperty( "calculatePerformance", "No" );
	}
	
	public String getRecordExecution() throws Exception {
		return getStringProperty( "recordExecution", "No" );
	}
	
	public String getCheckAllActions() throws Exception {
		return getStringProperty( "checkAllActions", "Yes" );
	}
	
	public String getMarkDifference() throws Exception {
		return getStringProperty( "markDifference", "Yes" );
	}
	
	public int getImplicitWaitInSecs() throws Exception {
		return getIntegerProperty( "implicitWaitInSecs", 2 );
	}
	
	public String getUseFirstRunScreens() throws Exception {
		return getStringProperty( "useFirstRunScreens", "Yes" );
	}
	
	public String getFirstRunFolder() throws Exception {
		return getStringProperty( "firstRunFolder", "" );
	}
	
	public String getRunInFullScreenMode() throws Exception {
		return getStringProperty( "runInFullScreenMode", "No" );
	}
	
	public String getSkipOptionsMenu() throws Exception {
		return getStringProperty( "skipOptionsMenu", "No" );
	}
	
	public String getFirstLevelDelimiter() throws Exception {
		return getStringProperty( "firstLevelDelimiter", "|" );
	}
	
	public String getSecondLevelDelimiter() throws Exception {
		return getStringProperty( "secondLevelDelimiter", ";" );
	}
	
	public String getThirdLevelDelimiter() throws Exception {
		return getStringProperty( "thirdLevelDelimiter", "!" );
	}
	
	public String getTaskControllerExeFile() throws Exception {
		return getStringProperty( "taskControllerExeFile", "tc.sh" );
	}
	
	public String getJava8Path() throws Exception {
		return getStringProperty( "java8Path", "" );
	}
	
	public String getJava11Path() throws Exception {
		return getStringProperty( "java11Path", "" );
	}
}