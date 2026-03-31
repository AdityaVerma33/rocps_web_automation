package com.subex.rocps.automation.helpers.application.tariffs;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.application.screens.FastEntryHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.componentHelpers.GridElementHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;

public class PSTariffHelper extends ROCAcceptanceTest {
	PSGenericHelper psGenericHelper=new PSGenericHelper();
	public void createTariff(String path, String workBookName, String workSheetName, String testCaseName, int occurance) throws Exception 
	{
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurance);
			
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String tariffName = excelData.get("Name").get(i);
				String tariffClass = excelData.get("Tariff Class").get(i);
				String tariffType = excelData.get("Tariff Type").get(i);
				String trafficType = excelData.get("Traffic Type").get(i);
				String country = excelData.get("Country").get(i);
				String currency = excelData.get("Currency").get(i);
				String cashflow = excelData.get("Cashflow").get(i);
				String rounding = excelData.get("Rounding").get(i);
				String trafficDP = excelData.get("DP").get(i);
				
				boolean crossPeriodCharge = ValidationHelper.isTrue(excelData.get("Cross Period Charge").get(i));
				String externalReference = excelData.get("External Reference").get(i);
				boolean allowNegativeRates = ValidationHelper.isTrue(excelData.get("Allow Negative Rates").get(i));
				
				String usagePerUnit = excelData.get("Usage Per Unit").get(i);
				String minUsage = excelData.get("Min Usage").get(i);
				String setupUsage = excelData.get("Setup Usage").get(i);
				String minAmount = excelData.get("Min Amount").get(i);
				String maxAmount = excelData.get("Max Amount").get(i);
				String setupAmount = excelData.get("Setup Amount").get(i);
				
				String[] elementSets = testData.getStringValue(excelData.get("Element Set").get(i), firstLevelDelimiter);
				String[] elementSetTypes = testData.getStringValue(excelData.get("Element Set Type").get(i), firstLevelDelimiter);
				String[][] tariffRateNames = testData.getStringValue(excelData.get("Tariff Rate Names").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] rateDefinitions = testData.getStringValue(excelData.get("Rate Definitions").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String effectiveDate = excelData.get("Effective Date").get(i);
				boolean addAllBands = ValidationHelper.isTrue(excelData.get("Add All Bands").get(i));
				boolean saveFastEntry = ValidationHelper.isTrue(excelData.get("Save Fast Entry").get(i));
				
				createTariff(partition, tariffName, tariffClass, tariffType, trafficType, country, currency, cashflow, rounding, trafficDP,
						crossPeriodCharge, externalReference, allowNegativeRates, usagePerUnit, minUsage, setupUsage, minAmount, maxAmount,
						setupAmount, elementSets, elementSetTypes, tariffRateNames, rateDefinitions, effectiveDate, addAllBands, saveFastEntry);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createTariff (String partition, String tariffName, String tariffClass, String tariffType, String trafficType, String country,
			String currency, String cashflow, String rounding, String trafficDP, boolean crossPeriodCharge, String externalReference, 
			boolean allowNegativeRates, String usagePerUnit, String minUsage, String setupUsage, String minAmount, String maxAmount,
			String setupAmount, String[] elementSets, String[] elementSetTypes, String[][] tariffRateNames, String[][] rateDefinitions,
			String effectiveDate, boolean addAllBands, boolean saveFastEntry) throws Exception
	{
		try {
			Log4jHelper.logInfo("=== Starting Tariff Creation: " + tariffName + " ===");
			int row = navigateToTariff(tariffName);
			
			if (row > 0 ) {
				Log4jHelper.logWarning("Tariff '" + tariffName + " ' is already present.");
			}
			else {
				Log4jHelper.logInfo("Navigating to New Tariff screen for partition: " + partition);
				NavigationHelper.navigateToNew(partition);
				String detailScreenTitle = NavigationHelper.getScreenTitle();
				Log4jHelper.logInfo("Detail screen opened with title: " + detailScreenTitle);

				Log4jHelper.logInfo("Step 1: Entering basic tariff details");
				TextBoxHelper.type("Tariff_Name", tariffName);
				Log4jHelper.logInfo("  - Name: " + tariffName);

				ComboBoxHelper.select("Tariff_TariffClass", tariffClass);
				Log4jHelper.logInfo("  - Tariff Class: " + tariffClass);

				ComboBoxHelper.select("Tariff_TariffType", tariffType);
				Log4jHelper.logInfo("  - Tariff Type: " + tariffType);

				if (ComboBoxHelper.isEnabled("Tariff_TrafficType")) {
					ComboBoxHelper.select("Tariff_TrafficType", trafficType);
					Log4jHelper.logInfo("  - Traffic Type: " + trafficType);
				}

				ComboBoxHelper.select("Tariff_Country", country);
				Log4jHelper.logInfo("  - Country: " + country);

				ComboBoxHelper.select("Tariff_Currency", currency);
				Log4jHelper.logInfo("  - Currency: " + currency);

				ComboBoxHelper.select("Tariff_CashFlow", cashflow);
				Log4jHelper.logInfo("  - Cashflow: " + cashflow);

				ComboBoxHelper.select("Tariff_Rounding", rounding);
				Log4jHelper.logInfo("  - Rounding: " + rounding);

				ComboBoxHelper.select("Tariff_DP", trafficDP);
				Log4jHelper.logInfo("  - DP: " + trafficDP);

				if (crossPeriodCharge) {
					CheckBoxHelper.check("Tariff_CrossPeriodCharging");
					Log4jHelper.logInfo("  - Cross Period Charging: Enabled");
				}

				TextBoxHelper.type("Tariff_ExternalReference", externalReference);
				Log4jHelper.logInfo("  - External Reference: " + externalReference);

				if (allowNegativeRates) {
					CheckBoxHelper.check("Tariff_AllowNegativeRates");
					Log4jHelper.logInfo("  - Allow Negative Rates: Enabled");
				}

				Log4jHelper.logInfo("Step 2: Updating tariff defaults");
				updateTariffDefaults(usagePerUnit, minUsage, setupUsage, minAmount, maxAmount, setupAmount);
				
				Log4jHelper.logInfo("Step 3: Adding element sets (count: " + (elementSets != null ? elementSets.length : 0) + ")");
				TariffHelper tariff = new TariffHelper();
				tariff.addElementSets("Tariff_ElementSet_Grid", "Tariff_ElementSet_Add", elementSets, elementSetTypes);
				
				Log4jHelper.logInfo("Step 4: Switching to Rate Definitions tab");
				TabHelper.gotoTab("Rate Definitions");

				Log4jHelper.logInfo("Step 5: Adding rate names (count: " + (tariffRateNames != null ? tariffRateNames.length : 0) + ")");
				addRateName(tariffRateNames);
				
				Log4jHelper.logInfo("Step 6: Adding day groups/rate definitions (count: " + (rateDefinitions != null ? rateDefinitions.length : 0) + ")");
				addDayGroup("Tariff_RateDefinition_Grid", rateDefinitions);
				
				Log4jHelper.logInfo("Step 7: Setting effective date: " + effectiveDate);
				TextBoxHelper.type("Tariff_EffectiveDate", effectiveDate);
				GenericHelper.waitForLoadmask(detailScreenWaitSec);

				if (addAllBands) {
					Log4jHelper.logInfo("Step 8: Adding all bands");
					CheckBoxHelper.check("Tariff_AddAllBands");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
				}
				
				Log4jHelper.logInfo("Step 9: Saving tariff");
				saveTariff(tariffName, detailScreenTitle, false, saveFastEntry);
				Log4jHelper.logInfo("=== Tariff Creation Completed Successfully: " + tariffName + " ===");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createChildTariff(String path, String workBookName, String workSheetName, String testCaseName, int occurance) throws Exception 
	{
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurance);
			
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String tariffName = excelData.get("Name").get(i);
				String parentTariffName = excelData.get("Parent Tariff Name").get(i);
				String tariffType = excelData.get("Tariff Type").get(i);
				
				boolean crossPeriodCharge = ValidationHelper.isTrue(excelData.get("Cross Period Charge").get(i));
				String externalReference = excelData.get("External Reference").get(i);
				
				String usagePerUnit = excelData.get("Usage Per Unit").get(i);
				String minUsage = excelData.get("Min Usage").get(i);
				String setupUsage = excelData.get("Setup Usage").get(i);
				String minAmount = excelData.get("Min Amount").get(i);
				String maxAmount = excelData.get("Max Amount").get(i);
				String setupAmount = excelData.get("Setup Amount").get(i);
				
				String effectiveDate = excelData.get("Effective Date").get(i);
				boolean saveFastEntry = ValidationHelper.isTrue(excelData.get("Save Fast Entry").get(i));
				
				createChildTariff(partition, tariffName, parentTariffName, tariffType, crossPeriodCharge, externalReference, usagePerUnit,
						minUsage, setupUsage, minAmount, maxAmount, setupAmount, effectiveDate, saveFastEntry);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createChildTariff (String partition, String tariffName, String parentTariffName, String tariffType, boolean crossPeriodCharge,
			String externalReference, String usagePerUnit, String minUsage, String setupUsage, String minAmount, String maxAmount,
			String setupAmount, String effectiveDate, boolean saveFastEntry) throws Exception
	{
		try {
			int row = navigateToTariff(parentTariffName);
			
			if (row == 0) {
				FailureHelper.failTest("Parent Tariff '" + parentTariffName + "' is not present.");
			}
			else {
				NavigationHelper.navigateToAction("Expand/Collapse");
				if (NavigationHelper.isActionPresent("Expand All"))
					NavigationHelper.navigateToAction("Expand All");
				int childRow = GridHelper.getRowNumber("SearchGrid", tariffName, "Tariff Name");
				
				if (childRow > 0 ) {
					Log4jHelper.logWarning("Child Tariff '" + tariffName + " ' is already present.");
				}
				else {
					GridHelper.clickRow("SearchGrid", row, "Tariff Name");
					NavigationHelper.navigateToAction("Tariff Actions", "New Child Tariff");
					String detailScreenTitle = NavigationHelper.getScreenTitle();
					assertEquals(detailScreenTitle, "New Child Tariff", "New Child Tariff screen did not load");
					
					TextBoxHelper.type("Tariff_Name", tariffName);
					ComboBoxHelper.select("Tariff_TariffType", tariffType);
					
					if (crossPeriodCharge)
						CheckBoxHelper.check("Tariff_CrossPeriodCharging");
					TextBoxHelper.type("Tariff_ExternalReference", externalReference);
					
					updateTariffDefaults(usagePerUnit, minUsage, setupUsage, minAmount, maxAmount, setupAmount);
					
					TabHelper.gotoTab("Rate Definitions");
					TextBoxHelper.type("Tariff_EffectiveDate", effectiveDate);
					
					saveTariff(tariffName, detailScreenTitle, true, saveFastEntry);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createFastEntry(String path, String workBookName, String workSheetName, String testCaseName, int occurance) throws Exception {
		try {
			PSFastEntryHelper fastEntry = new PSFastEntryHelper();
			fastEntry.createFastEntry(path, workBookName, workSheetName, testCaseName, occurance);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createFastEntry(String tariffName, boolean isChildTariff, String parentTariffName, boolean newPeriod, String effectiveDate,
			boolean[] overrideParent, String[] bandNames, String[][] rateNames, String[][] rates, boolean updateForwardRate) throws Exception {
		try {
			PSFastEntryHelper fastEntry = new PSFastEntryHelper();
			fastEntry.createFastEntry(tariffName, isChildTariff, parentTariffName, newPeriod, effectiveDate, overrideParent, bandNames, rateNames, rates, updateForwardRate);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addElementSets(String gridId, String addButton, String[] elementSets, String[] elementSetTypes) throws Exception {
		try {
			int rows = GridHelper.getRowCount(gridId);
			rows++;
			int row = rows;
			
			for (int i = 0; i < elementSets.length; i++) {
				int[] rowNum = GridHelper.getRowNumbers(gridId, elementSets[i], "Element Set");
				boolean isPresent = false;
				
				if (ValidationHelper.isNotEmpty(rowNum)) {
					for (int j = 0; j < rowNum.length; j++) {
						String value = GridHelper.getCellValue(gridId, rowNum[j], "Type");
						if (value.equals(elementSetTypes[i])) {
							isPresent = true;
							break;
						}
					}
				}
				
				if (!isPresent) {
					ButtonHelper.click(addButton);
					GridHelper.updateGridComboBox(gridId, "ElementSet_Combo", row, "Element Set", "Type", elementSets[i]);
					GridHelper.updateGridComboBox(gridId, "ElementSetType_Combo", row, "Type", "Element Set", elementSetTypes[i]);
					row++;
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected int navigateToTariff(String tariffName) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Tariffs", "Tariff Search");
			//int row =PSSearchGridHelper.gridFilterSearchWithTextBox( "Tariff_Name", tariffName, "Tariff Name" );
			int row = SearchGridHelper.searchWithTextBox("Tariff_Name", tariffName, "Tariff");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void updateTariffDefaults (String usagePerUnit, String minUsage, String setupUsage, String minAmount, String maxAmount,
			String setupAmount) throws Exception
	{
		try {
			TextBoxHelper.type("Tariff_UsagePerUnit", usagePerUnit);
			TextBoxHelper.type("Tariff_MinUsage", minUsage);
			TextBoxHelper.type("Tariff_SetupUsage", setupUsage);
			TextBoxHelper.type("Tariff_MinAmount", minAmount);
			TextBoxHelper.type("Tariff_MaxAmount", maxAmount);
			TextBoxHelper.type("Tariff_SetupAmount", setupAmount);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void addRateName(String[][] tariffRateNames) throws Exception {
		try {
			for (int i = 0; i < tariffRateNames.length; i++){
				GridHelper.updateGridComboBox("Tariff_TariffRates_Grid", "Tariff_RateName", (i+1), "Rate Name", "Index", tariffRateNames[i][0]);
				
				GridHelper.updateGridTextBox("Tariff_TariffRates_Grid", "Tariff_RateColor", (i+1), "Colour", "Index", tariffRateNames[i][1]);
				
				GridHelper.updateGridTextBox("Tariff_TariffRates_Grid", "Tariff_RateCode", (i+1), "Code", "Index", tariffRateNames[i][2]);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected void saveTariff(String tariffName, String detailScreenTitle, boolean isChildTariff, boolean saveFastEntry) throws Exception {
		try {
			Log4jHelper.logInfo("Attempting to save tariff: " + tariffName);
			ButtonHelper.click("SaveButton");

			// Enhanced wait for save operation - increased timeout
			Log4jHelper.logInfo("Waiting for save operation to complete...");
			Thread.sleep(3000); // Wait 3 seconds for any client-side validations
			GenericHelper.waitForLoadmask(detailScreenWaitSec * 2); // Double the wait time

			// Check if detail screen has closed
			boolean detailScreenClosed = LabelHelper.isTitleNotPresent(detailScreenTitle);
			String currentTitle = NavigationHelper.getScreenTitle();

			Log4jHelper.logInfo("After save - Detail screen closed: " + detailScreenClosed + ", Current title: " + currentTitle);

			// If save failed, capture additional debug information
			if (!detailScreenClosed) {
				Log4jHelper.logError("SAVE FAILED - Detail screen still present. Current title: " + currentTitle);
				Log4jHelper.logError("Expected screen to close: " + detailScreenTitle);
				// Try to capture any validation error messages
				String errorInfo = capturePageErrors();
				if (!errorInfo.isEmpty()) {
					Log4jHelper.logError("Error messages found on page: " + errorInfo);
				}
			}

			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Tariff save did not happen. Current screen: " + currentTitle);

			// Check for Fast Entry popup
			boolean fastEntryPresent = LabelHelper.isTitlePresent("Open Fast Entry Screen");
			Log4jHelper.logInfo("Fast Entry popup present: " + fastEntryPresent);
			assertTrue(fastEntryPresent, "Open Fast Entry popup did not appear.");

			if (saveFastEntry) {
				ButtonHelper.click("Yes");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Edit Fast Entry"), "Fast entry screen did not appear.");
				ButtonHelper.click("FastEntry_Save");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
			else {
				ButtonHelper.click("No");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			if (isChildTariff) {
				NavigationHelper.navigateToAction("Expand/Collapse");
				if (NavigationHelper.isActionPresent("Expand All"))
					NavigationHelper.navigateToAction("Expand All");
			}
			
			assertTrue(GridHelper.isValuePresent("SearchGrid", tariffName, "Name"), "Value '" + tariffName + "' is not found in grid.");
			Log4jHelper.logInfo("Tariff '" + tariffName + "' created.");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	
	private void addDayGroup(String gridId, String[][] dayGroups) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(dayGroups)) {
				for ( int i = 0; i < dayGroups.length; i++ ) {
					int rowIndex = GridHelper.getRowNumber( gridId, dayGroups[i][0], 1 );
					
					if (rowIndex == 0) {
						String actionLocator = or.getProperty("DayGroup_Add");
						GridHelper.rightClickSubMenu( gridId, actionLocator, dayGroups[i][0] );
						rowIndex = GridHelper.getRowNumber( gridId, dayGroups[i][0], 1 );
						
					}
					
					gridId = GenericHelper.getORProperty(gridId);
					for ( int j = 1; j < dayGroups[i].length; j += 3 ) {
						int col1 = Integer.parseInt( dayGroups[i][j] );
						int col2 = Integer.parseInt( dayGroups[i][j+1] );
						WebElement element1 = GridElementHelper.getCellElement(gridId, rowIndex, col1);
						
						ElementHelper.scrollToView(element1, false);
						Thread.sleep(1000);
						MouseHelper.mouseDown( element1 );
						
						GridHelper.rightClick(gridId, rowIndex, 1);						
						
						if ( dayGroups[i][j+2].equalsIgnoreCase( "yes" ) )
							ButtonHelper.click( "DayGroup_YesButton" );
						else if ( dayGroups[i][j+2].equalsIgnoreCase( "No" ) )
							ButtonHelper.click( "DayGroup_NoButton" );
						else
							ButtonHelper.click(dayGroups[i][j+2]);
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	/**
	 * Helper method to capture any validation errors or error messages displayed on the page
	 * @return String containing error messages found on the page
	 */
	private String capturePageErrors() {
		try {
			StringBuilder errors = new StringBuilder();

			// Try to find common GXT error message patterns
			try {
				// Check for message boxes
				java.util.List<org.openqa.selenium.WebElement> messageBoxes = driver.findElements(
					org.openqa.selenium.By.xpath("//div[contains(@class, 'x-message-box') or contains(@class, 'x-window-dlg')]//span[@class='ext-mb-text']")
				);
				for (org.openqa.selenium.WebElement msg : messageBoxes) {
					if (msg.isDisplayed()) {
						errors.append("MessageBox: ").append(msg.getText()).append("; ");
					}
				}
			} catch (Exception e) {
				// Ignore if elements not found
			}

			// Check for field validation errors
			try {
				java.util.List<org.openqa.selenium.WebElement> fieldErrors = driver.findElements(
					org.openqa.selenium.By.xpath("//div[contains(@class, 'x-form-invalid-msg') or contains(@class, 'x-form-invalid-icon')]")
				);
				if (!fieldErrors.isEmpty()) {
					errors.append("Field validation errors found (").append(fieldErrors.size()).append(" fields); ");
				}
			} catch (Exception e) {
				// Ignore if elements not found
			}

			// Check for error labels
			try {
				java.util.List<org.openqa.selenium.WebElement> errorLabels = driver.findElements(
					org.openqa.selenium.By.xpath("//label[contains(@class, 'error')] | //span[contains(@class, 'error')] | //div[contains(@class, 'error-msg')]")
				);
				for (org.openqa.selenium.WebElement lbl : errorLabels) {
					if (lbl.isDisplayed() && !lbl.getText().trim().isEmpty()) {
						errors.append("Error: ").append(lbl.getText()).append("; ");
					}
				}
			} catch (Exception e) {
				// Ignore if elements not found
			}

			return errors.toString();
		} catch (Exception e) {
			return "Unable to capture error information: " + e.getMessage();
		}
	}
}