package com.subex.rocps.sprintTestCase.bklg114;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class HurFileStatus extends PSAcceptanceTest {
	protected PSGenericHelper genHelperObj = new PSGenericHelper();
	protected ExcelReader excelReader = null;
	protected Map<String, ArrayList<String>> excelReaderMapObj = null;
	protected Map<String, String> mapObj = null;
	Map<String, String> configuratonMap = null;
	protected ExcelHolder excelHolderObj = null;

	int colSize = 0;
	int occurence = 0;
	private String hurDatawithValues;
	String path;
	String workBook;
	String sheetName;
	String testCaseName;
	String isNotificationFileString;
	String fileName;

	public void HurFileColumnsVerification(String path, String workBook, String sheetName, String testCaseName)
			throws Exception {
		this.path = path;
		this.workBook = workBook;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelReader = new ExcelReader();
		excelReaderMapObj = excelReader.readDataByColumn(this.path, this.workBook, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(excelReaderMapObj);
		colSize = excelHolderObj.totalColumns();

	}

	public void searchScreenColumnsValidation() throws Exception {
		NavigationHelper.navigateToScreen("HUR Files");
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		for (int paramVal = 0; paramVal < colSize; paramVal++) {
			mapObj = excelHolderObj.dataMap(paramVal);
			String searchScreenColumns = ExcelHolder.getKey(mapObj, "SearchScreenColumns");
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			PSStringUtils strObj = new PSStringUtils();
			String[] searchGridColumnsArr = strObj.stringSplitFirstLevel(searchScreenColumns);
			for (int col = 0; col < searchGridColumnsArr.length; col++) {
				excelColumnNames.add(searchGridColumnsArr[col]);
			}
			genHelperObj.totalColumns(excelColumnNames);
		}

	}

	public void totalColumns(ArrayList<String> excelColumnNames) throws Exception {
		PSStringUtils psStringObj = new PSStringUtils();
		assertTrue(GridHelper.isPresent("SearchGrid"));
		List<String> actualColumnNames = getGridColumns("grid_column_header_searchGrid_");

		int index = actualColumnNames.size() - 1;
		String finalVal = actualColumnNames.get(index);
		if (finalVal.isEmpty())
			actualColumnNames.remove(index);

		assertEquals(actualColumnNames, excelColumnNames, "Search Screen total columns are not matching");
		String actualValues = psStringObj.stringformation(actualColumnNames);
		String excelValues = psStringObj.stringformation(excelColumnNames);
		Log4jHelper.logInfo("Header Value :" + actualValues);
	}

	public List<String> getGridColumns(String columnHeaderId) throws Exception {

		List<String> guiColumns = new ArrayList<String>();
		String locator = "//div[contains(@id,'" + columnHeaderId + "')]";
		List<WebElement> elements = ElementHelper.getElements(locator);
		Iterator<WebElement> itr = elements.iterator();
		while (itr.hasNext()) {
			guiColumns.add(itr.next().getAttribute("textContent").replaceAll("[\\u00A0]", "").trim());
		}
		return guiColumns;
	}

	public void filterCheckHurFileStatus() throws Exception {
		NavigationHelper.navigateToScreen("HUR files");
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		ElementHelper.waitForElement("phfsFileName", 10);
		TextBoxHelper.type("PS_FileName", fileName);
		ButtonHelper.click("search");
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
	}

	public void validateDataInHURSearch() throws Exception {
		try {
			int hurRowCount = GridHelper.getRowCount("searchGrid");
			if (hurRowCount > 0) {
				ArrayList<String> hurData = GridHelper.getRowValues("searchGrid", 1);
				StringBuilder strVal = new StringBuilder();
				for (String str : hurData) {
					strVal.append(str).append(";");
				}
				hurDatawithValues = strVal.toString();
				for (int paramVal = 0; paramVal < colSize; paramVal++) {
					mapObj = excelHolderObj.dataMap(paramVal);
					initialiseVariables(mapObj);
					String searchScreenColumns = ExcelHolder.getKey(mapObj, "SearchScreenColumns");
					ArrayList<String> excelColumnNames = new ArrayList<String>();
					PSStringUtils strObj = new PSStringUtils();
					String[] searchGridColumnsArr = strObj.stringSplitFirstLevel(searchScreenColumns);
					for (int col = 0; col < searchGridColumnsArr.length; col++) {
						excelColumnNames.add(searchGridColumnsArr[col]);
					}
					totalColumns(excelColumnNames);
					Log4jHelper.logInfo("HURData " + hurDatawithValues);
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void emailActionClick() throws Exception {
		try {
			GridHelper.clickRow("Detail_popUpWindowId", "SearchGrid", 1, 2);
			ButtonHelper.click("PS_SelectEmail");
			ButtonHelper.click("PS_SendEmail");

		} catch (Exception e) {
			System.out.println("No Email Stream Stage Configured");
		}
	}

	public void popUpDataVerfication() throws Exception {
		String emailMsg = "Send Email Task sucessfully scheduled with Task Id:";
		String completeEmailMsg = LabelHelper.getText("PS_CopyMsg");
		assertTrue(completeEmailMsg.contains(emailMsg));
		GenericHelper.waitForElement("PS_CopyMsg", 20);
		ButtonHelper.click("PS_Copied_popup");
	}

	private void initialiseVariables(Map<String, String> map) throws Exception {
		isNotificationFileString = ExcelHolder.getKey(map, "Is Notification");
		fileName = ExcelHolder.getKey(map, "File Name");

	}

}
