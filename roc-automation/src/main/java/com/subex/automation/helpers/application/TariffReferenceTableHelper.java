package com.subex.automation.helpers.application;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class TariffReferenceTableHelper extends ROCAcceptanceTest {
	
	public void elementSet(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++ )
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				
				elementSet(partition, name);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void elementSet(String partition, String name) throws Exception {
		try {
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			boolean isPresent = referenceTable.navigateToEntity("Element Set", "ElementSet_Name", name, partition, "Element Set Name");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			TextBoxHelper.type("Detail_Popup", "ElementSet_Name", name);
			
			referenceTable.saveReferenceTable("Element Set", detailScreenTitle, name, "Element Set Name", isPresent);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void tariffRateName(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++ )
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				
				tariffRateName(partition, name);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void tariffRateName(String partition, String name) throws Exception {
		try {
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			boolean isPresent = referenceTable.navigateToEntity("Tariff Rate Name", "TariffRateName_Name", name, partition, "Name");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			TextBoxHelper.type("Detail_Popup", "TariffRateName_Name", name);
			
			referenceTable.saveReferenceTable("Tariff Rate Name", detailScreenTitle, name, "Name", isPresent);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void tariffMetricType(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++ )
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String usageName = excelData.get("Usage Name").get(i);
				String usageDivisorName = excelData.get("Usage Divisor Name").get(i);
				String usageDivisor = excelData.get("Usage Divisor").get(i);
				
				tariffMetricType(partition, name, usageName, usageDivisorName, usageDivisor);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void tariffMetricType(String partition, String name, String usageName, String usageDivisorName, String usageDivisor) throws Exception {
		try {
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			boolean isPresent = referenceTable.navigateToEntity("Tariff Metric Type", "TariffMetricType_Name", name, partition, "Name");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			TextBoxHelper.type("Detail_Popup", "TariffMetricType_Name", name);
			TextBoxHelper.type("Detail_Popup", "TariffMetricType_UsageName", usageName);
			TextBoxHelper.type("Detail_Popup", "TariffMetricType_UsageDivisorName", usageDivisorName);
			TextBoxHelper.type("Detail_Popup", "TariffMetricType_UsageDivisor", usageDivisor);
			
			referenceTable.saveReferenceTable("Tariff Metric Type", detailScreenTitle, name, "Name", isPresent);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void bandType(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++ )
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String classCode = excelData.get("Class Code").get(i);
				
				bandType(partition, name, classCode);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void bandType(String partition, String name, String classCode) throws Exception {
		try {
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			boolean isPresent = referenceTable.navigateToEntity("Band Type", "BandType_Name", name, partition, "Name");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			TextBoxHelper.type("Detail_Popup", "BandType_Name", name);
			TextBoxHelper.type("Detail_Popup", "BandType_ClassCode", classCode);
			
			referenceTable.saveReferenceTable("Band Type", detailScreenTitle, name, "Name", isPresent);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void tariffSwitch(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++ )
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String matchString = excelData.get("Match String").get(i);
				String element = excelData.get("Element").get(i);
				
				tariffSwitch(partition, name, matchString, element);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void tariffSwitch(String partition, String name, String matchString, String element) throws Exception {
		try {
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			boolean isPresent = referenceTable.navigateToEntity("Switch", "Switch_Name", name, partition, "Name");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			TextBoxHelper.type("Detail_Popup", "Switch_Name", name);
			TextBoxHelper.type("Detail_Popup", "Switch_MatchString", matchString);
			EntityComboHelper.selectUsingGridFilterTextBox("Switch_Element", "Element Search", "Elements_Name", element, "Name");
			
			referenceTable.saveReferenceTable("Switch", detailScreenTitle, name, "Name", isPresent);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void routeClass(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++ )
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String code = excelData.get("Code").get(i);
				
				routeClass(partition, name, code);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void routeClass(String partition, String name, String code) throws Exception {
		try {
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			boolean isPresent = referenceTable.navigateToEntity("Route Class", "RouteClass_Name", name, partition, "Name");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			TextBoxHelper.type("Detail_Popup", "RouteClass_Name", name);
			TextBoxHelper.type("Detail_Popup", "RouteClass_Code", code);
			
			referenceTable.saveReferenceTable("Route Class", detailScreenTitle, name, "Name", isPresent);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void routeType(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++ )
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String code = excelData.get("Code").get(i);
				
				routeType(partition, name, code);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void routeType(String partition, String name, String code) throws Exception {
		try {
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			boolean isPresent = referenceTable.navigateToEntity("Route Type", "RouteType_Name", name, partition, "Name");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			TextBoxHelper.type("Detail_Popup", "RouteType_Name", name);
			TextBoxHelper.type("Detail_Popup", "RouteType_Code", code);
			
			referenceTable.saveReferenceTable("Route Type", detailScreenTitle, name, "Name", isPresent);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void tariffType(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++ )
			{
				String partition = excelData.get("Partition").get(i);
				String code = excelData.get("Code").get(i);
				String name = excelData.get("Name").get(i);
				String enableTrafficType = excelData.get("Enable Traffic Type").get(i);
				
				tariffType(partition, code, name, enableTrafficType);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void tariffType(String partition, String code, String name, String enableTrafficType) throws Exception {
		try {
			ReferenceTableHelper referenceTable = new ReferenceTableHelper();
			boolean isPresent = referenceTable.navigateToEntity("Tariff Type", "TariffType_Name", name, partition, "Name");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			TextBoxHelper.type("Detail_Popup", "TariffType_Code", code);
			TextBoxHelper.type("Detail_Popup", "TariffType_Name", name);
			ComboBoxHelper.select("Detail_Popup", "TariffType_EnableTariffType", enableTrafficType);
			
			referenceTable.saveReferenceTable("Tariff Type", detailScreenTitle, name, "Name", isPresent);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}