package com.subex.rocps.sprintTestCase.bklg41;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
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
public class Settings extends PSAcceptanceTest {
	
	String accountAddressSettingsComponent;
	Map<String,ArrayList<String>> addCompMap= null;
	Map<String, String> addComp = null;
	OR_Reader orData = new OR_Reader();
	String path;
	String workBookName;
	String sheetName;
	String testCaseName;
	List<String> columnList = null;
	ExcelReader excelData = null;
	ExcelHolder excelHolderObj = null;
	int colSize;
	int paramVal;
	String Component;
	public Settings(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		addCompMap = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(addCompMap);
		colSize = excelHolderObj.totalColumns();
	}

	
	public boolean isUSAddrComponentUsed() throws Exception {
		
		GenericHelper.waitForLoadmask();
		ElementHelper.click(or.getProperty("options"));
		ElementHelper.click(or.getProperty("settings"));
		GenericHelper.waitForLoadmask();
		ElementHelper.click(or.getProperty("client_settings"));
		ElementHelper.getText(or.getProperty("Accountaddress_Component"));
		addComp = excelHolderObj.dataMap(0);
		initializeVariables();
		if (Component.equals(ElementHelper.getText(or.getProperty("Accountaddress_Component")))) {
			return true;
		} else
			return false;
		
	}
	
	public void changeAddressComponent() throws Exception {
	List<WebElement> componentDropdown=ElementHelper.getElements(or.getProperty("Component_dropDown"));
	ElementHelper.click(componentDropdown.get(0));
	
	ComboBoxHelper.select(Component);	
	ButtonHelper.click("ok_button");
	GenericHelper.waitForLoadmask();
	}
	
	public void initializeVariables(){
		Component = addComp.get("AccountAddressValidationComponent");
	}
	
	public void checkOrChangeComponent() throws Exception {
		try {
			if (!isUSAddrComponentUsed()) {
				changeAddressComponent();
			}
			else{
				ButtonHelper.click("settings_OkButton");
				GenericHelper.waitForLoadmask();
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

}
