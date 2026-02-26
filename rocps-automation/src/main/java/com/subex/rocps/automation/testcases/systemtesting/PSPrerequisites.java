package com.subex.rocps.automation.testcases.systemtesting;

import java.io.File;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.file.CopyFile;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSCopyFile;

public class PSPrerequisites extends PSAcceptanceTest
{
	/*@org.testng.annotations.Test( priority = 1, description = "Starting tomcat", groups =
	{ "Prerequisites1" } )
	public void startTomcat() throws Exception
	{
		try
		{
			ControllerHelper obj = new ControllerHelper();
			obj.startTomcat();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}*/

	@org.testng.annotations.Test( priority = 1, description = "update password for 1st time login" )
	public void updatePassword() throws Exception
	{
		String newPassword = configProp.getApplicationPassword();

		LoginHelper login = new LoginHelper();
		login.login( configProp.getApplicationName(), configProp.getApplicationUsername(), "welcome", configProp.getApplicationPassword() );

		/*
		 * if ( newPassword.equals( "welcome" ) ) { newPassword = "welcome1";
		 * //FileHelper.updatePropertyFile( configFile, "applicationPassword",
		 * newPassword );
		 * 
		 * Thread.sleep( 1000 ); }
		 */

	}

	@org.testng.annotations.Test( priority = 2, description = "copy birt folder" )
	public void copyBirtFile() throws Exception
	{
		try
		{

			String birtDirPath = configProp.getDataDirPath() + "\\Birt";
			String birtSrcPath = automationPath + "\\src\\main\\resources\\Birt";
			String diamondDirPath = configProp.getDataDirPath() + "\\Diamond";
			String diamondSrcPath = automationPath + "\\src\\main\\resources\\Diamond";
			String mainDirPath =  configProp.getDataDirPath();
			PSCopyFile.copyFile( mainDirPath, birtSrcPath, birtDirPath );
			Log4jHelper.logInfo( "Birt folder is successfully copied" );
			PSCopyFile.copyFile( mainDirPath, diamondSrcPath, diamondDirPath );
			Log4jHelper.logInfo( "Diamond folder is successfully copied" );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 3, description = "copy 'RateSheetImportStatusAlert' folder   " )
	public void copyRateSheetImportStatusAlertFile() throws Exception
	{
		try
		{

			String rateSheetImportStatusAlertDirPath = configProp.getDataDirPath() + "\\RateSheetImportStatusAlert";
			String rateSheetImportStatusAlertSrcPath = automationPath + "\\src\\main\\resources\\RateSheetImportStatusAlert";
			String mainDirPath =  configProp.getDataDirPath();
			PSCopyFile.copyFile( mainDirPath, rateSheetImportStatusAlertSrcPath, rateSheetImportStatusAlertDirPath );
			Log4jHelper.logInfo( "'RateSheetImportStatusAlert' folder is successfully copied" );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
