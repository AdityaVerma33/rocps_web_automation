package com.subex.rocps.automation.helpers.application.bulkentityexport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.subex.rocps.automation.helpers.application.bulkentityselection.*;
import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.*;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.componentHelpers.PopupElementHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.UnzipHelper;
import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class BulkEntityExportImpl extends PSAcceptanceTest {
	PSGenericHelper genericHelperObj = new PSGenericHelper();
	Map<String, Method> methodMap1 = new HashMap();
	Map<String, SelectItemInterface> methodMap = new HashMap();
	DataSelectionHelper dsObj = new DataSelectionHelper();
	Map<String, String> bulkMap = new HashMap();
	PSStringUtils psStringObj = new PSStringUtils();
	BulkEntityFileHelper bulkFileObj = new BulkEntityFileHelper();
	String[] entities;
	String[] expAllArr;
	String[] itemsArr;
	String[] dependentEntities;
	String[] itemsCount;
	String name;
	Map<Integer, String> itemMap1 = new HashMap();
	Map<String, String> itemMap = new HashMap();
	Map<Integer, String> dependentMap = new HashMap();
	Map<Integer, String> itemCountMap = new HashMap();
	boolean flag = false;
	long timeLimit;


	public BulkEntityExportImpl(Map<String, String> map, String entity, String exportAll, String items,
			String dependentEntity) throws Exception {
		bulkMap = map;
		entities = psStringObj.stringSplitFirstLevel(entity);
		expAllArr = psStringObj.stringSplitFirstLevel(exportAll);
		itemsArr = psStringObj.stringSplitFirstLevel(items);
		dependentEntities = psStringObj.stringSplitFirstLevel(dependentEntity);
		initializeVariables(map);
		mappingItemsToEntities();
	}

	public void newBulkEntityExport(String partition) throws Exception {
		genericHelperObj.clickNewAction(partition);
		TextBoxHelper.type("PS_Detail_bulkExport_name", name);
	}

	public void configureBulkEntityExport() throws Exception {
		addEntities();

	}

	public void mappingItemsToEntities() throws Exception {
		methodMap.put("Agent Conf", new AgentSelection() );
		methodMap.put("Route Conf", new RouteSelection());
		methodMap.put("TapInRoamingConfiguration Conf",new TapInSelection());
		methodMap.put("DialStringSet Conf", new DialStringSelection());
		methodMap.put("BillProfile Conf", new BillProfileSelection());
		methodMap.put("CrossFxRate Conf", new CrossFxRateSelection());
		methodMap.put("EventIdentifierDfn Conf", new EventIdentifierSelection());
		methodMap.put("EventIdentifierValue Conf", new EventIdentifierValueSelection());
		methodMap.put("EventIdentValueGroup Conf", new EventIdentifierValueGroupSelection());
		methodMap.put("EventLegCodeGroup Conf", new EventLegCodeGroupSelection());
		methodMap.put("EventMatchRuleGroup Conf", new EventMatchRuleGroupSelection());
		methodMap.put("HurOutRoamingConfiguration Conf", new HurOutSelection());
		methodMap.put("NrtrdeOutRoamingC0nfiguration Conf", new NrtrdeOutSelection());
		methodMap.put("RapInRoamingConfiguration Conf", new RapInSelection());
		methodMap.put("RapOutRoamingConfiguration Conf", new RapOutSelection());
		methodMap.put("RoamingDefinition", new RoamingSelection());
		methodMap.put("RouteGroup Conf", new RouteGroupSelection());
		methodMap.put("TapOutRoamingConfiguration Conf", new TapOutSelection());
		methodMap.put("TestSimManagement Conf", new TestSimSelection());
		
		
	}

	public void addEntities() throws Exception {
		for (int i = 0; i < entities.length; i++) {
			ElementHelper.click("toolbar-button-label-exportGridToolbar.Add");
			if (i == 0) {
				GridHelper.clickRow("PS_Detail_bulkExport_entityGrid", i + 1, 1);
			}
			GridHelper.clickRow("PS_Detail_bulkExport_entityGrid", i + 1, 1);
			ComboBoxHelper.select("PS_Detail_bulkExport_entityComboBox", entities[i]);
			if (ValidationHelper.isTrue(expAllArr[i])) {
				exportAllCheck(i);
			} else if ((itemMap.get(entities[i])).length() > 0) {
				addItems(entities[i], itemMap.get(entities[i]));
			}
			GenericHelper.waitForLoadmask();
			if (!dependentMap.get(i).equals("")) {
				MouseHelper.click("PS_Detail_bulkExport_dependentClick");
				validateDependentEntities(dependentMap.get(i));
			}
			GenericHelper.waitForLoadmask();
			
		}
	}

	public void addItems(String entity, String itemList) throws Exception {
		String[] items = psStringObj.stringSplitFirstLevel(itemList);
		for (int i = 0; i < items.length; i++) {
			GenericHelper.waitForLoadmask();
			ButtonHelper.click("PS_Detail_bulkExport_itemSelect");
			GenericHelper.waitForLoadmask();
			methodMap.get(entity).selectItems(items[i]);
		}

	}

	public void validateDependentEntities(String depEntity) throws Exception {
		ArrayList<String> clientEntities = GridHelper.getColumnValues("PS_Detail_bulkExport_dependentGrid", "Dependent Entities");
		String[] depEntities = psStringObj.stringSplitFirstLevel(depEntity);
		for (int i = 0; i < depEntities.length; i++) {
			assertEquals(depEntities[i], clientEntities.get(i).trim());
		}

	}

	public void saveAction(String action) throws Exception {
		if (!ValidationHelper.isTrue(action)) {
			ButtonHelper.click("PS_Detail_bulkExport_save");
			GenericHelper.waitForLoadmask();
			assertEquals(GridHelper.getCellValue("searchGrid", 1, 2), name);
		} else {
			ButtonHelper.click("PS_Detail_bulkExport_saveAndExport");
			GenericHelper.waitForLoadmask();
			ButtonHelper.click("ok");
			assertEquals(GridHelper.getCellValue("searchGrid", 1, 2), name);

		}
	}

	public void scheduleTask() throws Exception {
		NavigationHelper.navigateToScreen("Bulk Entity Export");
		assertEquals(GridHelper.getCellValue("searchGrid", 1, 2), name);
		GridHelper.clickRow("searchGrid", 1, 1);
		//MouseHelper.click("//*[@id='Schedule']");
		ButtonHelper.click("PS_Detail_bulkExport_schedule");
		//MouseHelper.click("//*[@id='ScheduleBulkDataExportTaskComponent']");
		ButtonHelper.click("PS_Detail_bulkExport_scheduleTask");
		GenericHelper.waitForElement("//*[@id='ok']", "30");
		ButtonHelper.click("ok");
		//waitForTaskCompletion();

	}

	public void validateNewExport() throws Exception {
		NavigationHelper.navigateToScreen("Bulk Entity Export");
		assertEquals(GridHelper.getCellValue("searchGrid", 1, 2), name);
		waitForTaskCompletion();

	}


	public void validateUnzippedFiles(String file, String path) throws Exception {
		UnzipHelper unZip = new UnzipHelper();
		unZip.unzip(applicationOS, file, path);
	}

	public void changeToMilliSeconds() throws Exception {
		long seconds = Long.parseLong(configProp.getProperty("SearchLoopTimeLimitInSecs"));
		timeLimit = seconds * 1000;
	}

	public void waitForTaskCompletion() throws Exception {
		ArrayList<String> acceptedStatus = new ArrayList<String>();
		changeToMilliSeconds();
		acceptedStatus.add("Failed");
		acceptedStatus.add("Completed");
		long startTime = System.currentTimeMillis();
		String actualValue = GridHelper.getCellValue("searchGrid", 1, "Task Status").trim();
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		while ((!actualValue.equals(acceptedStatus.get(0)) && !actualValue.equals(acceptedStatus.get(1)))
				&& (System.currentTimeMillis() - startTime) < timeLimit) {
			ButtonHelper.click("search");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			actualValue = GridHelper.getCellValue("searchGrid", 1, "Task Status").trim();
		}
		GenericHelper.waitForLoadmask();
		assertEquals(actualValue, "Completed", "Task Failed");
	}

	public void exportAllCheck(int number) throws Exception {
		ElementHelper.click("grid_check_editor");
		driver.findElement(By.xpath("(//img[@id='expotAllEditor_checked_column_field_image'])[position()="
				+ String.valueOf(number + 1) + "]")).click();
	}

	public void validateExtractedFiles(String path, final String fileName) throws Exception{
		String filePath = configProp.getDataDirPath() + configProp.getProperty(path);
		File file = new File(filePath);
		FilenameFilter filter = new FilenameFilter() {

			public boolean accept(File f, String name) {

				boolean fileStatus = (!name.endsWith("zip") && name.contains(fileName));
				return fileStatus;
			}
		};
		File[] files = file.listFiles(filter);
		String folderPath=filePath+files[0].getName();
		File folder = new File (folderPath);
		assertEquals(entities.length,(folder.listFiles().length-1),"File Count Validated");
		List<File> list = Arrays.asList(folder.listFiles());
		for(int i=0;i<entities.length;i++){
			String str1=entities[i].replaceAll("\\s+","");
			validateExcelFiles(folderPath, str1, "xlsx");
		}
		

	}
	
	private void validateExcelFiles(String path, final String fileName, final String extension) throws Exception {

		File file = new File(path);

		FilenameFilter filter = new FilenameFilter() {

			public boolean accept(File f, String name) {

				boolean fileStatus = (name.endsWith(extension) && name.contains(fileName));
				return fileStatus;
			}
		};
		File[] files = file.listFiles(filter);
		assertTrue(files.length > 0, " File name contains with name '" + fileName + "' and extension:-'" + extension
				+ "' not found in the given directory " + path);
		for (int i = 0; i < files.length; i++) {
			Log4jHelper.logInfo("File name found :- \n " + files[i].getName());
		}
	}
	
	public void initializeVariables(Map<String, String> map) throws Exception {
		name = ExcelHolder.getKey(map, "Name");
		for (int i = 0; i < itemsArr.length; i++) {
			if (!itemsArr[i].equals("")) {
				itemMap.put(entities[i], ExcelHolder.getKey(map, itemsArr[i]));
			} else {
				itemMap.put(entities[i], "");
			}

		}
		for (int i = 0; i < dependentEntities.length; i++) {
			if (!dependentEntities[i].equals("")) {
				dependentMap.put(i, ExcelHolder.getKey(map, dependentEntities[i]));
			} else {
				dependentMap.put(i, "");
			}
		}


	}
}
