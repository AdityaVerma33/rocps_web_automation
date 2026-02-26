package com.subex.automation.helpers.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.UnhandledAlertException;
import org.testng.Reporter;

import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;

public class VerifyURL extends AcceptanceTest{

	public void verifyURLStatus( String workbookName,String sheetName) throws Exception {
		try {
			ArrayList<String> testURL = readURLFromXls(workbookName,sheetName);
			int invalidURLCount=0;
			
			for(String url:testURL) {		
					try {
						driver.navigate().to(url);
							if(driver.getPageSource().contains("HTTP Status 404 -")) {
								Reporter.log("Invalid CSH url - "+url,true);//it writes invalid url to html report and also to console
								invalidURLCount++;
							}
						}
					
					catch(UnhandledAlertException e) {
						Log4jHelper.logInfo("stop script error at firefox with "+url);
					};
			}
			
			assertEquals(invalidURLCount, 0 ,"Total "+testURL.size() + " CSH are verified\n" + invalidURLCount + " - Not working CSH links  present in the product");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}


	public ArrayList<String> readURLFromXls( String workbookName, String sheetName) throws Exception {
		try {
			HSSFWorkbook wb = null;
			HSSFSheet sheet = null;
			int lastrow = 0;
			ArrayList<String> URLFromXls = new ArrayList<String>();
			FileInputStream fis = null;
			
			try{
				fis = new FileInputStream(new File(automationPath + "\\src\\main\\resources\\" + workbookName));
				wb = new HSSFWorkbook(fis);
				sheet = wb.getSheet(sheetName);
				lastrow = sheet.getLastRowNum();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				if (fis != null)
					fis.close();
			}
			
			for(int i=0;i<=lastrow;i++)
				URLFromXls.add(sheet.getRow(i).getCell(0).getStringCellValue());
			
			return URLFromXls;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}