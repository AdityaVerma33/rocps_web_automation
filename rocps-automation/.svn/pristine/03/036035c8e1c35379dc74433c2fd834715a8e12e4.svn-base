package com.subex.rocps.automation.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.ExportHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.automation.helpers.util.UnzipHelper;

public class FileExport extends PSAcceptanceTest
{
	
	
	public void readData(String exportContentHeader, String exportContentRow, Map<String, String> mapObj) throws Exception
	{
		PSStringUtils psStrObj = new PSStringUtils();
		String path = GenericHelper.getPath(automationOS, configProp.getDataDirPath() +"\\Downloads");
		String zipfile = FileHelper.getLastModifiedFile(automationOS, path );
		
		UnzipHelper unZip = new UnzipHelper();
		unZip.unzip(applicationOS, zipfile, path);
		FileHelper.deleteFile(applicationOS, zipfile);
		
		String fileName = FileHelper.getLastModifiedFile(automationOS, path );
		String[] content = FileHelper.readFileContent(automationOS, fileName);
		
			
		ArrayList<String> strExcelobj = new ArrayList<String>();
		String[] rows = psStrObj.stringSplitFirstLevel(exportContentRow);
		for (int i = 0; i < rows.length; i++)
		{
			if(i==0)
			{
				String actualVal = content[i];
				String val1 = actualVal.replace( "\"", "" ).replace( " ", "" );				
				String[] actualExportval = val1.split( ",", -1);
				
				List<String> actualVal1 = Arrays.asList(actualExportval);
				
				String actualContantVal = psStrObj.stringformation( actualVal1 );
				String[] excelHeaderVal = psStrObj.stringSplitFirstLevel( exportContentHeader );
				List<String> excelVal = Arrays.asList(excelHeaderVal);
				Log4jHelper.logInfo("Actual value : " + actualContantVal);
				Log4jHelper.logInfo("Excel value : " + excelVal);
				Assert.assertEquals(actualContantVal, excelVal, "Values are not matching ");
			}
			String actualVal = content[i+1];
			String val1 = actualVal.replace( "\"", "" ).replace( " ", "" );
			String[] actualExportval = val1.split( ",", -1);
			
			List<String> actualVal1 = Arrays.asList(actualExportval);
			
			String actualContantVal = psStrObj.stringformation( actualVal1 );			
			
				String rowValue = mapObj.get(rows[i]).replace( " ", "" );
				strExcelobj.clear();
				strExcelobj.add( rowValue );
				String excelExportVal = psStrObj.stringformation( strExcelobj );
				
				Log4jHelper.logInfo("Actual value : " + actualContantVal);
				Log4jHelper.logInfo("Excel value : " + excelExportVal);
				Assert.assertEquals(actualContantVal, excelExportVal, "Values are not matching ");
		}
	}

	public void exportSelected( String name ) throws Exception
	{
		fileDownload();
		String path = GenericHelper.getPath(automationOS, configProp.getDownloadDirectory());
		String fileName = FileHelper.getLastModifiedFile(automationOS, path + "\\Client_Downloads");
		
		String[] content = FileHelper.readFileContent(automationOS, fileName);
		System.out.println( content );
		
		List<String> actualVal = Arrays.asList(content);
		
		PSStringUtils psStrObj = new PSStringUtils();
		String actualContantVal = psStrObj.stringformation( actualVal ).replace( " ", "" );
		String[] contentArr = actualContantVal.split( secondLevelDelimiter);
		List<String> actualVal1 = Arrays.asList(contentArr);
	
		actualContantVal = psStrObj.stringformation( actualVal1 );
		String val= actualContantVal.replace( " ", "" );
		System.out.println( val );	
		

	}
	
	public static void fileDownload() throws Exception {
		try {
			((JavascriptExecutor) driver).executeScript("window.focus();");
			String fileName = null;
			String path = GenericHelper.getPath(automationOS, configProp.getDownloadDirectory());
			Robot robot = new Robot();
			
          
            robot.keyPress(KeyEvent.VK_DOWN);			
            robot.delay(200);
            robot.keyPress(KeyEvent.VK_ENTER);
            
            Thread.sleep(2000);
			
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}
