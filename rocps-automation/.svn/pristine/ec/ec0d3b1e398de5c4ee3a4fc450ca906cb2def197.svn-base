package com.subex.rocps.automation.helpers.application.tariffs;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.EntitySearchHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridCheckBoxHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class PSFastEntryHelper extends PSTariffHelper

{
	public void createFastEntry(String path, String workBookName, String workSheetName, String testCaseName, int occurance) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurance);
		 	
	 		for(int i = 0; i < excelData.get("Tariff Name").size(); i++) {
	 			String tariffName = excelData.get("Tariff Name").get(i);
	 			boolean isChildTariff =  ValidationHelper.isTrue(excelData.get("Is Child Tariff").get(i));
	 			String parentTariffName = excelData.get("Parent Tariff Name").get(i);
	 			boolean newPeriod =  ValidationHelper.isTrue(excelData.get("New Period").get(i));
	 			String effectiveDate = excelData.get("Effective Date").get(i);
	 			boolean[] overrideParent = testData.getBooleanValue(excelData.get("Override Parent").get(i), firstLevelDelimiter);
	 			String[] bandNames = testData.getStringValue(excelData.get("Band Names").get(i), firstLevelDelimiter);
	 			String[][] rateNames = testData.getStringValue(excelData.get("Rate Names").get(i), firstLevelDelimiter, secondLevelDelimiter);
	 			String[][] rates = testData.getStringValue(excelData.get("Rates").get(i), firstLevelDelimiter, secondLevelDelimiter);
	 			boolean updateForwardRate = ValidationHelper.isTrue(excelData.get("Update Forward Rate").get(i));
	 			
	 			createFastEntry(tariffName, isChildTariff, parentTariffName, newPeriod, effectiveDate, overrideParent, bandNames, rateNames, rates, updateForwardRate);
	 		}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createFastEntry(String tariffName, boolean isChildTariff, String parentTariffName, boolean newPeriod, String effectiveDate,
			boolean[] overrideParent, String[] bandNames, String[][] rateNames, String[][] rates, boolean updateForwardRate) throws Exception {
		try {
			int row = navigateToFastEntry(tariffName, isChildTariff, parentTariffName, newPeriod, effectiveDate);
			
			if (row > 0) {
				addFastEntry(tariffName, effectiveDate, isChildTariff, overrideParent, bandNames, rateNames, rates, updateForwardRate);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateCurrency(String path, String workBookName, String workSheetName, String testCaseName, int occurance) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurance);
		 	
	 		for(int i = 0; i < excelData.get("Tariff Name").size(); i++) {
	 			String tariffName = excelData.get("Tariff Name").get(i);
	 			boolean isChildTariff =  ValidationHelper.isTrue(excelData.get("Is Child Tariff").get(i));
	 			String parentTariffName = excelData.get("Parent Tariff Name").get(i);
	 			String effectiveDate = excelData.get("Effective Date").get(i);
	 			String[] bandNames = testData.getStringValue(excelData.get("Band Names").get(i), firstLevelDelimiter);
	 			String[] newCurrency = testData.getStringValue(excelData.get("New Currency").get(i), firstLevelDelimiter);
	 			
	 			updateCurrency(tariffName, isChildTariff, parentTariffName, effectiveDate, bandNames, newCurrency);
	 		}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateCurrency(String tariffName, boolean isChildTariff, String parentTariffName, String effectiveDate, String[] bandNames,
			String[] newCurrency) throws Exception {
		try {
			int rowNum = navigateToFastEntry(tariffName, isChildTariff, parentTariffName, false, effectiveDate);
			
			if (rowNum > 0) {
				String screenTitle = NavigationHelper.getScreenTitle();
				
				for (int i = 0; i < bandNames.length; i++) {
					TextBoxHelper.type("FastEntry_BandName", bandNames[i]);
					ButtonHelper.click("FastEntry_Search");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					int row = GridHelper.getRowNumber("FastEntry_Grid", bandNames[i], "Band Name");
					
					if (row > 0) {
						NavigationHelper.navigateToAction("Band Actions", "Add Bands");
						GridHelper.clickRow("FastEntry_Grid", row, 1);
						NavigationHelper.navigateToAction("Rate Actions", "Set Currency", newCurrency[i]);
					}
				}
				
				saveFastEntry(tariffName, screenTitle, effectiveDate, false);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateRateDetails(String path, String workBookName, String workSheetName, String testCaseName, int occurance) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurance);
		 	
	 		for(int i = 0; i < excelData.get("Tariff Name").size(); i++) {
	 			String tariffName = excelData.get("Tariff Name").get(i);
	 			boolean isChildTariff =  ValidationHelper.isTrue(excelData.get("Is Child Tariff").get(i));
	 			String parentTariffName = excelData.get("Parent Tariff Name").get(i);
	 			String effectiveDate = excelData.get("Effective Date").get(i);
	 			String[] bandNames = testData.getStringValue(excelData.get("Band Names").get(i), firstLevelDelimiter);
	 			String[] newCurrency = testData.getStringValue(excelData.get("New Currency").get(i), firstLevelDelimiter);
	 			
	 			String[][] rateProperties = testData.getStringValue(excelData.get("Rate Properties").get(i), firstLevelDelimiter, secondLevelDelimiter);
	 			String[][] rateDefinitions = testData.getStringValue(excelData.get("Rate Definitions").get(i), firstLevelDelimiter, secondLevelDelimiter);
	 			String[][] rateThresholds = testData.getStringValue(excelData.get("Rate Thresholds").get(i), firstLevelDelimiter, secondLevelDelimiter);
	 			
	 			updateRateDetails(tariffName, isChildTariff, parentTariffName, effectiveDate, bandNames, newCurrency, rateProperties,
	 					rateDefinitions, rateThresholds);
	 		}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateRateDetails(String tariffName, boolean isChildTariff, String parentTariffName, String effectiveDate, String[] bandNames,
			String[] newCurrency, String[][] rateProperties, String[][] rateDefinitions, String[][] rateThresholds) throws Exception {
		try {
			int rowNum = navigateToFastEntry(tariffName, isChildTariff, parentTariffName, false, effectiveDate);
			
			if (rowNum > 0) {
				String screenTitle = NavigationHelper.getScreenTitle();
				
				for (int i = 0; i < bandNames.length; i++) {
					TextBoxHelper.type("FastEntry_BandName", bandNames[i]);
					ButtonHelper.click("FastEntry_Search");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					int row = GridHelper.getRowNumber("FastEntry_Grid", bandNames[i], "Band Name");
					
					if (row > 0) {
						GridHelper.clickRow("FastEntry_Grid", row, 1);
						
						if (ValidationHelper.isNotEmpty(newCurrency) && ValidationHelper.isNotEmpty(newCurrency[i])) {
							NavigationHelper.navigateToAction("Rate Actions", "Set Currency", newCurrency[i]);
							GenericHelper.waitForLoadmask(searchScreenWaitSec);
						}
						
						if (ValidationHelper.isNotEmpty(rateProperties) || ValidationHelper.isNotEmpty(rateDefinitions) || ValidationHelper.isNotEmpty(rateThresholds)) {
							NavigationHelper.navigateToAction("Open Rate Details");
							GenericHelper.waitForLoadmask(searchScreenWaitSec);
							assertTrue(LabelHelper.isTitlePresent("Rate Details"));
							
							if (ValidationHelper.isNotEmpty(rateProperties) && ValidationHelper.isNotEmpty(rateProperties[i])) {
								for (int j = 0; j < rateProperties[i].length; j++) {
									if (ValidationHelper.isNotEmpty(rateProperties[i][j])) {
										String[] properties = rateProperties[i][j].split(configProp.getThirdLevelDelimiter(), -1);
										
										if (ValidationHelper.isNotEmpty(properties)) {
											for (int k = 0; k < properties.length; k++) {
												GridHelper.updateGridTextBox("FastEntry_RateDetails_Properties_Grid", "FastEntry_RateDetails_RateProperties", (k+1), (j+2), 1, properties[k]);
											}
										}
									}
								}
							}
							
							if (ValidationHelper.isNotEmpty(rateDefinitions) && ValidationHelper.isNotEmpty(rateDefinitions[i])) {
								ROCHelper rocHelper = new ROCHelper();
								rocHelper.addDayGroup("FastEntry_RateDetails_RateDefinitions_Grid", rateDefinitions[i], configProp.getThirdLevelDelimiter());
							}
							
							if (ValidationHelper.isNotEmpty(rateThresholds) && ValidationHelper.isNotEmpty(rateThresholds[i])) {
								TabHelper.gotoTab("Rate Thresholds");
								
								for (int j = 0; j < rateThresholds[i].length; j++) {
									if (ValidationHelper.isNotEmpty(rateThresholds[i][j])) {
										String[] thresholds = rateThresholds[i][j].split(configProp.getThirdLevelDelimiter(), -1);
										int rowNumber = GridHelper.getRowNumber("FastEntry_RateDetails_RateThreshold_Grid", thresholds[0], "Threshold (sec)");
										
										if (rowNumber == 0) {
											ButtonHelper.click("FastEntry_RateDetails_Threshold_Add");
											GenericHelper.waitForLoadmask(detailScreenWaitSec);
											assertTrue(LabelHelper.isTitlePresent("Add Rate Threshold"));
											
											TextBoxHelper.type("FastEntry_RateDetails_Threshold", thresholds[0]);
											ButtonHelper.click("FastEntry_RateDetails_Threshold_OK");
											GenericHelper.waitForLoadmask(detailScreenWaitSec);
											assertTrue(LabelHelper.isTitleNotPresent("Add Rate Threshold"));
											rowNumber = GridHelper.getRowNumber("FastEntry_RateDetails_RateThreshold_Grid", thresholds[0], "Threshold (sec)");
										}
										
										for (int k = 1; k < thresholds.length; k++) {
											GridHelper.updateGridTextBox("FastEntry_RateDetails_RateThreshold_Grid", "FastEntry_RateDetails_RateThreshold", rowNumber, (k+2), 1, thresholds[k]);
										}
									}
								}
								
							}
							
							ButtonHelper.click("FastEntry_RateDetails_OK");
							GenericHelper.waitForLoadmask(searchScreenWaitSec);
							assertTrue(LabelHelper.isTitleNotPresent("Rate Details"));
						}
					}
					else {
						FailureHelper.failTest("Band '" + bandNames[i] + "' is not found in Fast Entry screen of Tariff '" + tariffName + "'");
					}
				}
				
				saveFastEntry(tariffName, screenTitle, effectiveDate, false);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addFastEntry(String tariffName, String effectiveDate, boolean isChildTariff, boolean[] overrideParent, String[] bandNames,
			String[][] rateNames, String[][] rates, boolean updateForwardRate) throws Exception {
		try {
			String screenTitle = NavigationHelper.getScreenTitle();
			EntitySearchHelper entitySearch = new EntitySearchHelper();
			
			for (int i = 0; i < bandNames.length; i++) {
				TextBoxHelper.type("FastEntry_BandName", bandNames[i]);
				ButtonHelper.click("FastEntry_Search");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				int row = GridHelper.getRowNumber("FastEntry_Grid", bandNames[i], "Band Name");
				boolean updateRates = false;
				
				if (row == 0) {
					NavigationHelper.navigateToAction("Band Actions", "Add Bands");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					entitySearch.selectUsingGridFilterTextBox("Band Search", "Bands_Name", bandNames[i], "Name");
					
					assertTrue(PopupHelper.isTextPresent("1 band(s) have been added to this tariff."), "Band addition confirmation popup did not appear.");
					ButtonHelper.click("OKButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					
					row = GridHelper.getRowNumber("FastEntry_Grid", bandNames[i], "Band Name");
					updateRates = true;
				}
				
				if (!isChildTariff)
					updateRates = true;
				else if (overrideParent[i]) {
					GridHelper.clickRow("FastEntry_Grid", row, "Band Name");
					NavigationHelper.navigateToAction("Override Parent");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					updateRates = true;
					
					if (ButtonHelper.isPresent("YesButton")) {
						ButtonHelper.click("YesButton");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
					}
				}
				
				if (updateRates) {
					for(int j = 0; j < rateNames[i].length; j++) {
						String textBoxLocator = or.getProperty("FastEntry_Rate").replace("rateName", rateNames[i][j]);
						GridHelper.updateGridTextBox("FastEntry_Grid", textBoxLocator, row, rateNames[i][j], "Band Name", rates[i][j]);
					}
				}
			}
			
			saveFastEntry(tariffName, screenTitle, effectiveDate, updateForwardRate);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public int navigateToFastEntry(String tariffName, boolean isChildTariff, String parentTariffName, boolean newPeriod, String effectiveDate) throws Exception {
		try {
			int row = 0;
			if (isChildTariff)
				row = navigateToTariff(parentTariffName);
			else
				row = navigateToTariff(tariffName);
			
			if (row == 0) {
				if (isChildTariff)
					FailureHelper.failTest("Parent Tariff '" + parentTariffName + "' is not present.");
				else
					FailureHelper.failTest("Tariff '" + tariffName + "' is not present.");
			}
			else {
				if (isChildTariff) {
					NavigationHelper.navigateToAction("Expand/Collapse");
					if (NavigationHelper.isActionPresent("Expand All"))
						NavigationHelper.navigateToAction("Expand All");
					row = GridHelper.getRowNumber("SearchGrid", tariffName, "Tariff Name");
				}
				
				GridHelper.clickRow("SearchGrid", row, "Tariff Name");
				
				if (newPeriod) {
					MouseHelper.click("Tariff_Timeline_Period");
					Thread.sleep(500);
					ButtonHelper.click("Tariff_Timeline_NewPeriod");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					assertTrue(LabelHelper.isTitlePresent("Create New Tariff Period"), "New Tariff Period popup did not appear.");
					
					TextBoxHelper.type("Tariff_NewPeriod_EffetiveDate", effectiveDate);
					ButtonHelper.click("Tariff_NewPeriod_OK");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					
					if (LabelHelper.isTitlePresent("Create New Tariff Period")) {
						ButtonHelper.click("CancelButton");
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
						
						ButtonHelper.click("DiscardButton");
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
					}
					
					GridHelper.clickRow("SearchGrid", row, "Tariff Name");
				}
				
				NavigationHelper.navigateToAction("Tariff Actions", "Open Fast Entry");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				if (LabelHelper.isTitlePresent("Open Fast Entry at Date")) {
					if (ValidationHelper.isNotEmpty(effectiveDate)) {
						assertTrue(LabelHelper.isTitlePresent("Open Fast Entry at Date"), "Fast Entry screen did not appear.");
						TextBoxHelper.type("FastEntry_EffectiveDate", effectiveDate);
						ButtonHelper.click("FastEntry_EffectiveDate_OK");
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
						
						if (LabelHelper.isTextPresent("Tariff period does not exist for the selected tariff on date")) {
							FailureHelper.failTest("For Tariff '" + tariffName + "', Fast Entry Period with Effective Date '" + effectiveDate + "' does not exists.");
						}
					}
					else {
						FailureHelper.failTest("Tariff '" + tariffName + "' has more than one Period. So Effective Date is required to Open Fast Entry screen.");
					}
				}
				
				assertTrue(LabelHelper.isTitlePresent("Edit Fast Entry"), "Fast Entry screen did not appear.");
			}
			
			return row;
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected void saveFastEntry(String tariffName, String screenTitle, String effectiveDate, boolean updateForwardRate) throws Exception {
		try {
			int waitTime = configProp.getCustomScreenWaitSec();
			ButtonHelper.click("FastEntry_Save");
			GenericHelper.waitForLoadmask(waitTime);
			
			if (ButtonHelper.isPresent("FastEntry_ZeroRate")) {
				ButtonHelper.click("FastEntry_ZeroRate");
				GenericHelper.waitForLoadmask(waitTime);
			}
			
			if (ButtonHelper.isPresent("OKButton")) {
				ButtonHelper.click("OKButton");
				GenericHelper.waitForLoadmask(waitTime);
			}
			
			if (LabelHelper.isTitlePresent("Tariff Rate Deviation")) {
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(waitTime);
			}
			
			if (LabelHelper.isTitlePresent("Update Forward Periods")) {
				if (updateForwardRate) {
					int rows = GridHelper.getRowCount("FastEntry_UpdateForwardRate_Grid");
					
					for (int k = 0; k < rows; k++) {
						GridCheckBoxHelper.check("FastEntry_UpdateForwardRate_Grid", "FastEntry_UpdateForwardRate_Update", (k+1), "Update");
					}
				}
				
				ButtonHelper.click("FastEntry_UpdateForwardRate_Save");
				GenericHelper.waitForLoadmask(waitTime);
			}
			
			assertTrue(LabelHelper.isTitleNotPresent(screenTitle), "Fast Entry save did not happen.");
			Log4jHelper.logInfo("Fast Entry for '" + tariffName + "' for Period '" + effectiveDate + "' created/updated.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}
