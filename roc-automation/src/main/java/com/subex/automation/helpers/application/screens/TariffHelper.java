package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class TariffHelper extends ROCAcceptanceTest {

	public void createTariff(String path, String workBookName, String workSheetName, String testCaseName, int occurance)
			throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn(path, workBookName,
					workSheetName, testCaseName, occurance);

			for (int i = 0; i < excelData.get("Name").size(); i++) {
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

				String[] elementSets = testData.getStringValue(excelData.get("Element Set").get(i),
						firstLevelDelimiter);
				String[] elementSetTypes = testData.getStringValue(excelData.get("Element Set Type").get(i),
						firstLevelDelimiter);
				String[][] tariffRateNames = testData.getStringValue(excelData.get("Tariff Rate Names").get(i),
						firstLevelDelimiter, secondLevelDelimiter);
				String[][] rateDefinitions = testData.getStringValue(excelData.get("Rate Definitions").get(i),
						firstLevelDelimiter, secondLevelDelimiter);

				String effectiveDate = excelData.get("Effective Date").get(i);
				boolean addAllBands = ValidationHelper.isTrue(excelData.get("Add All Bands").get(i));
				boolean saveFastEntry = ValidationHelper.isTrue(excelData.get("Save Fast Entry").get(i));

				createTariff(partition, tariffName, tariffClass, tariffType, trafficType, country, currency, cashflow,
						rounding, trafficDP, crossPeriodCharge, externalReference, allowNegativeRates, usagePerUnit,
						minUsage, setupUsage, minAmount, maxAmount, setupAmount, elementSets, elementSetTypes,
						tariffRateNames, rateDefinitions, effectiveDate, addAllBands, saveFastEntry);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void createTariff(String partition, String tariffName, String tariffClass, String tariffType,
			String trafficType, String country, String currency, String cashflow, String rounding, String trafficDP,
			boolean crossPeriodCharge, String externalReference, boolean allowNegativeRates, String usagePerUnit,
			String minUsage, String setupUsage, String minAmount, String maxAmount, String setupAmount,
			String[] elementSets, String[] elementSetTypes, String[][] tariffRateNames, String[][] rateDefinitions,
			String effectiveDate, boolean addAllBands, boolean saveFastEntry) throws Exception {
		try {
			int row = navigateToTariff(tariffName);

			if (row > 0) {
				Log4jHelper.logWarning("Tariff '" + tariffName + " ' is already present.");
			} else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "Tariff_Name");

				TextBoxHelper.type("Tariff_Name", tariffName);
				ComboBoxHelper.select("Tariff_TariffClass", tariffClass);
				ComboBoxHelper.select("Tariff_TariffType", tariffType);
				if (ComboBoxHelper.isEnabled("Tariff_TrafficType"))
					ComboBoxHelper.select("Tariff_TrafficType", trafficType);

				ComboBoxHelper.select("Tariff_Country", country);
				ComboBoxHelper.select("Tariff_Currency", currency);
				ComboBoxHelper.select("Tariff_CashFlow", cashflow);
				ComboBoxHelper.select("Tariff_Rounding", rounding);
				ComboBoxHelper.select("Tariff_DP", trafficDP);

				if (crossPeriodCharge)
					CheckBoxHelper.check("Tariff_CrossPeriodCharging");
				TextBoxHelper.type("Tariff_ExternalReference", externalReference);
				if (allowNegativeRates)
					CheckBoxHelper.check("Tariff_AllowNegativeRates");

				updateTariffDefaults(usagePerUnit, minUsage, setupUsage, minAmount, maxAmount, setupAmount);

				addElementSets("Tariff_ElementSet_Grid", "Tariff_ElementSet_Add", elementSets, elementSetTypes);

				TabHelper.gotoTab("Rate Definitions");
				addRateName(tariffRateNames);

				ROCHelper rocHelper = new ROCHelper();
				rocHelper.addDayGroup("Tariff_RateDefinition_Grid", rateDefinitions);

				TextBoxHelper.type("Tariff_EffectiveDate", effectiveDate);
				if (addAllBands) {
					CheckBoxHelper.check("Tariff_AddAllBands");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
				}

				saveTariff(tariffName, detailScreenTitle, false, saveFastEntry);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void createChildTariff(String path, String workBookName, String workSheetName, String testCaseName,
			int occurance) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn(path, workBookName,
					workSheetName, testCaseName, occurance);

			for (int i = 0; i < excelData.get("Name").size(); i++) {
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

				createChildTariff(partition, tariffName, parentTariffName, tariffType, crossPeriodCharge,
						externalReference, usagePerUnit, minUsage, setupUsage, minAmount, maxAmount, setupAmount,
						effectiveDate, saveFastEntry);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void createChildTariff(String partition, String tariffName, String parentTariffName, String tariffType,
			boolean crossPeriodCharge, String externalReference, String usagePerUnit, String minUsage,
			String setupUsage, String minAmount, String maxAmount, String setupAmount, String effectiveDate,
			boolean saveFastEntry) throws Exception {
		try {
			int row = navigateToTariff(parentTariffName);

			if (row == 0) {
				FailureHelper.failTest("Parent Tariff '" + parentTariffName + "' is not present.");
			} else {
				NavigationHelper.navigateToAction("Expand/Collapse");
				if (NavigationHelper.isActionPresent("Expand All"))
					NavigationHelper.navigateToAction("Expand All");
				int childRow = GridHelper.getRowNumber("SearchGrid", tariffName, "Tariff Name");

				if (childRow > 0) {
					Log4jHelper.logWarning("Child Tariff '" + tariffName + " ' is already present.");
				} else {
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
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void createFastEntry(String path, String workBookName, String workSheetName, String testCaseName,
			int occurance) throws Exception {
		try {
			FastEntryHelper fastEntry = new FastEntryHelper();
			fastEntry.createFastEntry(path, workBookName, workSheetName, testCaseName, occurance);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void createFastEntry(String tariffName, boolean isChildTariff, String parentTariffName, boolean newPeriod,
			String effectiveDate, boolean[] overrideParent, String[] bandNames, String[][] rateNames, String[][] rates,
			boolean updateForwardRate) throws Exception {
		try {
			FastEntryHelper fastEntry = new FastEntryHelper();
			fastEntry.createFastEntry(tariffName, isChildTariff, parentTariffName, newPeriod, effectiveDate,
					overrideParent, bandNames, rateNames, rates, updateForwardRate);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void addElementSets(String gridId, String addButton, String[] elementSets, String[] elementSetTypes)
			throws Exception {
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
					GridHelper.updateGridComboBox(gridId, "ElementSet_Combo", row, "Element Set", "Type",
							elementSets[i]);
					GridHelper.updateGridComboBox(gridId, "ElementSetType_Combo", row, "Type", "Element Set",
							elementSetTypes[i]);
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
			int row = SearchGridHelper.searchWithTextBox("Tariff_Name", tariffName, "Tariff");

			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private void updateTariffDefaults(String usagePerUnit, String minUsage, String setupUsage, String minAmount,
			String maxAmount, String setupAmount) throws Exception {
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
			for (int i = 0; i < tariffRateNames.length; i++) {
				GridHelper.updateGridComboBox("Tariff_TariffRates_Grid", "Tariff_RateName", (i + 1), "Rate Name",
						"Index", tariffRateNames[i][0]);

				GridHelper.updateGridTextBox("Tariff_TariffRates_Grid", "Tariff_RateColor", (i + 1), "Colour", "Index",
						tariffRateNames[i][1]);

				GridHelper.updateGridTextBox("Tariff_TariffRates_Grid", "Tariff_RateCode", (i + 1), "Code", "Index",
						tariffRateNames[i][2]);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	protected void saveTariff(String tariffName, String detailScreenTitle, boolean isChildTariff, boolean saveFastEntry)
			throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Tariff save did not happen.");
			assertTrue(LabelHelper.isTitlePresent("Open Fast Entry Screen"), "Open Fast Entry popup did not appear.");

			if (saveFastEntry) {
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Edit Fast Entry"), "Fast entry screen did not appear.");
				ButtonHelper.click("FastEntry_Save");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			} else {
				ButtonHelper.click("NoButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}

			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);

			if (isChildTariff) {
				NavigationHelper.navigateToAction("Expand/Collapse");
				if (NavigationHelper.isActionPresent("Expand All"))
					NavigationHelper.navigateToAction("Expand All");
			}

			assertTrue(GridHelper.isValuePresent("SearchGrid", tariffName, "Name"),
					"Value '" + tariffName + "' is not found in grid.");
			Log4jHelper.logInfo("Tariff '" + tariffName + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}