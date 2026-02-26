package com.subex.rocps.sprintTestCase.bklg13;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

@SuppressWarnings("unused")
public class bulkExportRequest extends PSAcceptanceTest {
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> bulkDataMap = null;
	protected Map<String, String> bulkMap = null;
	protected ExcelHolder excelHolderObj = null;
	String path;
	String workBookName;
	String sheetName;
	String testCaseName;
	int colSize;
	int paramVal;
	String requestName;
	String entityName;
	String clientPartition;
	String exportAll;
	static String dependantEntities;

	@org.testng.annotations.Test(priority = 1)
	public void bulkExportRequestSave() throws Exception {
		try {
			path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
			workBookName = "TestData.xlsx";
			sheetName = "bulkExport_sheet4";
			testCaseName = "bulkExportSave";
			bulkDataMap = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
			excelHolderObj = new ExcelHolder(bulkDataMap);
			colSize = excelHolderObj.totalColumns();

			for (paramVal = 0; paramVal < colSize; paramVal++) {
				bulkMap = excelHolderObj.dataMap(paramVal);
				requestName = bulkMap.get("request Name");
				entityName = bulkMap.get("entity Name");
				exportAll = bulkMap.get("Export All");
				dependantEntities = bulkMap.get("Dependant Entities");
				boolean exportAllBool = Boolean.valueOf(exportAll);
				bulkEntitySave bulkObj = new bulkEntitySave();
				if (exportAllBool == false) {
					bulkObj.bulkEntityConfigurationSelectSpecificItem(clientPartition, requestName, entityName);
				}
				bulkObj.bulkEntityConfigation(clientPartition, requestName, entityName);
			}
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	public void checkDependentEntities() throws Exception {
		try {
			String[] entitiesSplit;
			if (dependantEntities == null)
				throw new Exception();
			entitiesSplit = dependantEntities.split("\\|");

			for (int i = 0; i < entitiesSplit.length; i++)
				System.out.println(entitiesSplit[i]);

			List<WebElement> elements = ElementHelper.getElements("PS_Details_dependentEntities");
			assertEquals(entitiesSplit.length, elements.size());
			ArrayList<String> dependingValues = new ArrayList<String>();

			for (int i = 0; i < elements.size(); i++) {
				String ele = GenericHelper.getORProperty("PS_Details_dependentEntities") + "[" + (i + 1) + "]/td[1]";
				// System.out.println(ele);
				dependingValues.add(ElementHelper.getText(ele));

			}
			System.out.println(dependingValues);

			for (int i = 0; i < entitiesSplit.length; i++) {
				System.out.println(entitiesSplit[i]);
				assertTrue(dependingValues.contains(entitiesSplit[i]),
						"Value is not present in dependantValues" + entitiesSplit[i]);
			}

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

}
