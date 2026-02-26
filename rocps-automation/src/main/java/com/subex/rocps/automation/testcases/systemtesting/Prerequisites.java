package com.subex.rocps.automation.testcases.systemtesting;

import java.io.File;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.file.CopyFile;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class Prerequisites extends PSAcceptanceTest {

	/*
	 * @org.testng.annotations.Test( priority = 1, description = "Starting tomcat",
	 * groups = { "Prerequisites1" } ) public void startTomcat() throws Exception {
	 * try { ControllerHelper obj = new ControllerHelper(); obj.startTomcat(); }
	 * catch ( Exception e ) { FailureHelper.setErrorMessage( e ); throw e; } }
	 */

	@org.testng.annotations.Test(priority = 1, description = "update password for 1st time login")
	public void updatePassword() throws Exception {
		String newPassword = configProp.getApplicationPassword();

		LoginHelper login = new LoginHelper();
		login.login(configProp.getApplicationName(), configProp.getApplicationUsername(), "welcome",
				configProp.getApplicationPassword());

		/*
		 * if ( newPassword.equals( "welcome" ) ) { newPassword = "welcome1";
		 * //FileHelper.updatePropertyFile( configFile, "applicationPassword",
		 * newPassword );
		 * 
		 * Thread.sleep( 1000 ); }
		 */

	}

	@org.testng.annotations.Test(priority = 2, description = "copy birt folder")
	public void copyBirtFile() throws Exception {
		try {

			String birtDirPath = configProp.getDataDirPath() + "\\Birt";
			String birtSrcPath = automationPath + "\\src\\main\\resources\\Birt";
			String diamondDirPath = configProp.getDataDirPath() + "\\Diamond";
			String diamondSrcPath = automationPath + "\\src\\main\\resources\\Diamond";
			File birtsrc = new File(birtSrcPath);
			File birtdest = new File(birtDirPath);
			File diamondDir = new File(diamondDirPath);
			File diamondSrc = new File(diamondSrcPath);

			CopyFile.copyFile(birtsrc, birtdest);
			Log4jHelper.logInfo("Birt folder is successfully copied");
			CopyFile.copyFile(diamondSrc, diamondDir);
			Log4jHelper.logInfo("Diamond folder is successfully copied");

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 3, description = "copy 'RateSheetImportStatusAlert' folder   ")
	public void copyRateSheetImportStatusAlertFile() throws Exception {
		try {

			String rateSheetImportStatusAlertDirPath = configProp.getDataDirPath() + "\\RateSheetImportStatusAlert";
			String rateSheetImportStatusAlertSrcPath = automationPath
					+ "\\src\\main\\resources\\RateSheetImportStatusAlert";
			File rateSheetImportStatusAlertSrc = new File(rateSheetImportStatusAlertSrcPath);
			File rateSheetImportStatusAlertDest = new File(rateSheetImportStatusAlertDirPath);
			CopyFile.copyFile(rateSheetImportStatusAlertSrc, rateSheetImportStatusAlertDest);
			Log4jHelper.logInfo("'RateSheetImportStatusAlert' folder is successfully copied");

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 4, description = "copy 'Email' folder")
	public void copyBirtEmailFile() throws Exception {
		try {

			String birtEmailDirPath = configProp.getDataDirPath() + "\\Email";
			String birtEmailSrcPath = automationPath + "\\src\\main\\resources\\Birt\\email_template";
			File birtEmailSrc = new File(birtEmailSrcPath);
			File birtEmailDest = new File(birtEmailDirPath);
			CopyFile.copyFile(birtEmailSrc, birtEmailDest);
			Log4jHelper.logInfo("'Email' folder is successfully copied");

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

}
