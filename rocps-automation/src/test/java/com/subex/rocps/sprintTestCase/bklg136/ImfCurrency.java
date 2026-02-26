package com.subex.rocps.sprintTestCase.bklg136;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.config.OR_Reader;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class ImfCurrency extends PSAcceptanceTest {
	OR_Reader orData = new OR_Reader();
	
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> imfExcelMap= null;
	protected Map<String, String> imfmap = null;
	protected ExcelHolder excelHolderObj = null;
	String path;
	String workBookName;
	String sheetName;
	String testCaseName;
	int colSize;
	int paramVal;
	String currency;
	String newCurrency;
	String imfCurrency;
	String clientPartition;

	public ImfCurrency(String path, String workBookName, String sheetName, String testCaseName)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		imfExcelMap = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(imfExcelMap);
		colSize = excelHolderObj.totalColumns();
	}

	


	public void newImfCurrency() throws Exception {
		try {
			NavigationHelper.navigateToReferenceTable("IMF Currency");
			GenericHelper.waitForLoadmask();
			imfmap=excelHolderObj.dataMap(0);
			initializeVariable(imfmap);
			
			boolean CurrencyExistFlag = checkCurrencyPresent(currency);
			if(CurrencyExistFlag==false) {
			genericHelperObj.clickNewAction("Common");
			GenericHelper.waitInSeconds("5");
			boolean isCurrencyPresent=ComboBoxHelper.containsValue("ImfCurrency_SelectCurrency_comboId", currency);
			if(isCurrencyPresent) {
			
			ComboBoxHelper.select("ImfCurrency_SelectCurrency_comboId", currency);
			TextBoxHelper.type("ImfCurrency_ImfCurrencyName_textId", imfCurrency);
			ButtonHelper.click("ImfCurrency_save_button");
			GenericHelper.waitForLoadmask();
			}
			else {
				Log4jHelper.logInfo(currency+"Is not present in system currency");
			}
			}
			else {

				Log4jHelper.logInfo( "IMF Currency already present" );
			}
		}
		catch(Exception e){
			
			throw e;
		}
		
	}
	public void editImfCurrency() throws Exception{
		try {
			NavigationHelper.navigateToReferenceTable("IMF Currency");
			GenericHelper.waitForLoadmask();
			imfmap=excelHolderObj.dataMap(0);
			initializeVariable(imfmap);
			boolean CurrencyExistFlag = false;
			int rowCount = GridHelper.getRowCount( "ImfCurrency_ImfCurrencyGrid" );
			int i;
			for ( i = 1; i <= rowCount; i++ )
			{
				ArrayList<String> existingData = GridHelper.getRowValues( "ImfCurrency_ImfCurrencyGrid", i );
				if ( ( existingData.get( 1 ).equals(currency ) ))
				{					
					CurrencyExistFlag = true;		
					break;
				}
			}
			if(CurrencyExistFlag==true)
				{
					GridHelper.clickRow("ImfCurrency_ImfCurrencyGrid", i, 1);
					NavigationHelper.navigateToAction("Common Tasks", "Edit");
					ComboBoxHelper.select("ImfCurrency_SelectCurrency_comboId", currency);
					TextBoxHelper.type("ImfCurrency_ImfCurrencyName_textId_textId", imfCurrency);
					ButtonHelper.click("ImfCurrency_save_button");
					GenericHelper.waitForLoadmask();
							
				}
				else {
					Log4jHelper.logInfo("Currency not found");
				}
			}
		catch(Exception e) {
			
		}
	}
	public void initializeVariable(Map<String,String> map) throws Exception{
		try {
			
			clientPartition=ExcelHolder.getKey(map,"Partition");
			currency=ExcelHolder.getKey(map,"Currency");
			imfCurrency=ExcelHolder.getKey(map,"ImfCurrency");		
		}
		catch(Exception e) {
			
		}
	}
	public boolean checkCurrencyPresent(String currency)throws Exception{
		
			int rowCount = GridHelper.getRowCount( "ImfCurrency_ImfCurrencyGrid" );
			boolean CurrencyExistFlag = false;
			int i;
			for ( i = 1; i <= rowCount; i++ )
			{
				ArrayList<String> existingData = GridHelper.getRowValues( "ImfCurrency_ImfCurrencyGrid", i );
				if ( ( existingData.get( 1 ).equals(currency ) ))
				{					
					CurrencyExistFlag = true;		
					break;
				}
			}	
		return CurrencyExistFlag;
	}

}
