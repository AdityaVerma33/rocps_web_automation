package com.subex.rocps.sprintTestCase.bklg80;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.Logs;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.config.OR_Reader;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class SetupAmount extends PSAcceptanceTest {
	
	ExcelReader excelData = null;
	ExcelHolder excelHolderObj = null;
	Map<String, ArrayList<String>> setupAmtMap = null;
	Map<String, String> setMap = null;
	String path;
	String workBookName;
	String sheetName;
	String testCaseName;
	int colSize;
	int paramVal;
	OR_Reader orData = new OR_Reader();
	String dealName;
	String rateType;
	String rateChange;
	String trn;
	String rateTRN;
	Map <String, String> trn_col = null;
	List<String> Values;
	public SetupAmount(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		setupAmtMap = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(setupAmtMap);
		colSize = excelHolderObj.totalColumns();
		
		
	}
	
	public void configureSetupAmount() throws Exception{
		try{
			GenericHelper.waitForLoadmask();
			NavigationHelper.navigateToScreen("Deal");
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				setMap = excelHolderObj.dataMap(paramVal);
			GenericHelper.waitForLoadmask();	
			initializeVariables();
			selectExistingDeal();
			configureTierRates();
			
		}
		}
		catch (Exception e){
			throw e;
		}
	}
	
	public void selectExistingDeal() throws Exception{
		SearchGridHelper.gridFilterSearchWithTextBox("Deal_searchName", dealName, "Deal Name");
		GridHelper.clickRow("Deal_Grid", 1,1);
		ButtonHelper.click("Deal_tariffPeriod");
		ButtonHelper.click("Deal_tierRate");
		
		
		
	}
	
	protected void configureTierRates() throws Exception{
		ButtonHelper.click("Deal_arrow");
		ComboBoxHelper.select("Deal_rateType", rateType);
		ComboBoxHelper.select("Deal_rateChange",rateChange);
		
		String[] dealTRN = trn.split(",", -1);
		String[] rateTRN = this.rateTRN.split(",", -1);
		
		for(int i =0; i<dealTRN.length; i++){
			String modifiedXpath = "//input[contains(@id,'"+dealTRN[i]+"')]";
			TextBoxHelper.type(modifiedXpath, rateTRN[i]);
			
			
		}
		ButtonHelper.click("Deal_apply");
		compareValues();
	}
	
	protected void compareValues() throws Exception{
		
		List gridColumns = ElementHelper.getElements("Deal_gridColumns");
		List gridRows = ElementHelper.getElements("Deal_gridRows");
		String[] dealTRN = trn.split(",", -1);
		String[] rateTRN = this.rateTRN.split(",", -1);
		for(int i=1;i <=gridRows.size();i++){
			for(int j=1;j<=dealTRN.length;j++){
			String Value=GridHelper.getCellValue("Deal_bandGrid", i, (gridColumns.size()-j));
			Assert.assertEquals(Value, rateTRN[rateTRN.length-j]);
			}
			
		}
		
		
	}
	public void initializeVariables() throws Exception {
		dealName = setMap.get("DealName");
		rateType = setMap.get("RateType");
		rateChange = setMap.get("RateChange");
		trn = setMap.get("TRN");
		rateTRN = setMap.get("Rate");
		
	}	

}
