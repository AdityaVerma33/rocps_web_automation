package com.subex.rocps.automation.helpers.application.system;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.ImageHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.component.TreeHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class PSTaskSearchHelper extends PSAcceptanceTest {
	static ControllerHelper controller = null;
	TaskSearchHelper taskObj = new TaskSearchHelper();
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	public void navigateToTaskSearch(boolean navigateToMonitor) throws Exception {
		try {
			controller = new ControllerHelper();
			boolean isSCRunning = controller.isSCRunning();

			NavigationHelper.navigateToScreen("Task Search", "Task Search");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);

			if (navigateToMonitor && isSCRunning) {
				if (ButtonHelper.isDisabled("MonitoringTab"))
					FailureHelper.failTest("Task Search Monitor tab is disabled.");
				TabHelper.gotoTab("MonitoringTab");
			} else
				TabHelper.gotoTab("SearchTab");

			Thread.sleep(500);
			GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
			taskObj.refreshLeftTree();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void psVerifyTaskStatus(String path, String workBookName, String workSheetName, String testCaseName,
			int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn(path, workBookName,
					workSheetName, testCaseName, occurence);
			ArrayList<String> tSortColumn = excelData.get("Sort Column");

			for (int i = 0; i < excelData.get("Stream").size(); i++) {
				String[] stream = testData.getStringValue(excelData.get("Stream").get(i), firstLevelDelimiter);
				String[] streamStage = testData.getStringValue(excelData.get("Stream Stage").get(i),
						firstLevelDelimiter);
				String[] showTasksIn = testData.getStringValue(excelData.get("Show Tasks In").get(i),
						firstLevelDelimiter);
				String[] expectedStatus = testData.getStringValue(excelData.get("Expected Status").get(i),
						firstLevelDelimiter);
				int[] waitTimeInSecs = testData.getIntegerValue(excelData.get("Task Wait Time In Secs").get(i),
						firstLevelDelimiter);

				String[] sortColumn = null;
				if (tSortColumn != null && tSortColumn.size() > i)
					sortColumn = testData.getStringValue(tSortColumn.get(i), firstLevelDelimiter);

				verifyTaskStatus(stream, streamStage, showTasksIn, expectedStatus, waitTimeInSecs, sortColumn);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void verifyTaskStatus(String[] stream, String[] streamStage, String[] showTasksIn, String[] expectedStatus,
			int[] waitTimeInSecs, String[] sortColumn) throws Exception {
		try {
			for (int i = 0; i < stream.length; i++) {
				String showTasks = null;
				if (ValidationHelper.isNotEmpty(showTasksIn) && ValidationHelper.isNotEmpty(showTasksIn[i]))
					showTasks = showTasksIn[i];

				String tempSortColumn = null;
				if (ValidationHelper.isNotEmpty(sortColumn) && ValidationHelper.isNotEmpty(sortColumn[i]))
					tempSortColumn = sortColumn[i];

				int row = getTask(stream[i], streamStage[i], showTasks, tempSortColumn);

				if (row > 0) {
					sortColumn(tempSortColumn);
					boolean isStatusUpdated = isStatusUpdated(stream[i], streamStage[i], row, expectedStatus[i],
							waitTimeInSecs[i]);
					String actualStatus = GridHelper.getCellValue("SearchGrid", row, "Status").trim();

					if (isStatusUpdated)
						Log4jHelper.logInfo("Task of Stream Stage '" + streamStage[i] + "' of Stream '" + stream[i]
								+ "' is in '" + expectedStatus[i] + "' status.");
					else
						FailureHelper.failTest(
								"Task of Stream Stage '" + streamStage[i] + "' of Stream '" + stream[i] + "' is in '"
										+ actualStatus + "' status. Expected status is '" + expectedStatus[i] + "'");
				} else {
					FailureHelper.failTest("Task of Stream Stage '" + streamStage[i] + "' of Stream '" + stream[i]
							+ "' is not found.");
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public int getTask(String stream, String streamStage, String showTasksIn, String sortColumn) throws Exception {
		try {
			int row = getTask(stream, streamStage, showTasksIn, null, sortColumn);

			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public int getTask(String stream, String streamStage, String showTasksIn, String currentStatus, String sortColumn)
			throws Exception {
		try {
			int waitTime = configProp.getCustomScreenWaitSec();
			boolean isrowPresent = false;
			int trycount = 0;

			navigateToTaskSearch(true);

			applyFilterInTree(streamStage);
			TreeHelper.clickChild("TaskSearch_LeftTree", stream, streamStage);
			if (ValidationHelper.isNotEmpty(showTasksIn) && controller.isSCRunning()) {
				GenericHelper.expandSearchFilterPanel();
				ComboBoxHelper.select("TaskSearch_ShowTasksIn", showTasksIn);
			}

			ButtonHelper.click("TaskSearch_SearchButton");
			GenericHelper.waitForLoadmask(waitTime);
			Thread.sleep(200);
			GenericHelper.waitForLoadmask(waitTime);
			GenericHelper.waitForAJAXReady(waitTime);
			int row = 0;
			int tryRow = 0;
			int maxWaitCount = 3;
			while (true) {
				row = GridHelper.getRowNumber("SearchGrid", streamStage, "Stream Stage");
				if (row > 0 || tryRow == maxWaitCount)
					break;
				Log4jHelper.logInfo("Retry again to get row num ");
				tryRow++;
			}

			while (true) {
				GenericHelper.waitTime(2, "waiting for task search row number.");
				int rowNo = GridHelper.getRowNumber("SearchGrid", streamStage, "Stream Stage");
				GenericHelper.waitForAJAXReady(waitTime);
				if (rowNo != 0) {
					isrowPresent = true;
					break;
				} else if (trycount > searchScreenWaitSec)
					FailureHelper.failTest("No Task found");
				else {
					Thread.sleep(2000);
					trycount++;
					Log4jHelper.logInfo("PS Trycount" + trycount);
					ButtonHelper.click("TaskSearch_SearchButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
				}
			}

			if (ValidationHelper.isNotEmpty(currentStatus))
				row = GridHelper.getRowNumber("SearchGrid", currentStatus, "Status");

			Log4jHelper.logInfo("Row  number returned" + row);
			ImageHelper.click("TaskSearch_LeftTree_FilterCross");
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private void sortColumn(String sortColumn) throws Exception {
		if (ValidationHelper.isNotEmpty(sortColumn)) {
			psGenericHelper.waitforHeaderElement("Task Id");
			ElementHelper.waitForClickableElement("//div[@id='grid_column_header_anchorsearchGrid_tskId']",
					searchScreenWaitSec);
			ButtonHelper.click("//div[@id='grid_column_header_anchorsearchGrid_tskId']");
			ElementHelper.waitForElement("//*[@id='_grid_header_context_menu_sort_descending_item']",
					searchScreenWaitSec); // small window xpath
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			ElementHelper.click("//*[text()='Sort Descending']");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
		}
	}

	private void applyFilterInTree(String filterValue) throws Exception {
		try {
			TextBoxHelper.type("TaskSearch_LeftTree_FilterTextBox", filterValue);
			Thread.sleep(2000);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private boolean isStatusUpdated(String stream, String streamStage, int row, String expectedStatus,
			int waitTimeInSecs) throws Exception {
		try {
			String actualStatus = null;
			int tryCount = 0;
			boolean isStatusUpdated = false;
			Thread.sleep(500);
			int colNo = GridHelper.getColumnNumber("SearchGrid", "Status");

			while (true) {
				actualStatus = GridHelper.getCellValue("SearchGrid", row, colNo);

				if (actualStatus != null && actualStatus.trim().equals(expectedStatus)) {
					isStatusUpdated = true;
					break;
				} else if (tryCount > waitTimeInSecs) {
					FailureHelper.failTest("Task of Stream Stage '" + streamStage + "' of Stream '" + stream
							+ "' is supposed to be of Status '" + expectedStatus + "'. But current Status is  '"
							+ actualStatus + "'");
				} else {
					Thread.sleep(1000);
					tryCount++;
					Log4jHelper.logInfo("ROC Trycount" + tryCount);
					ButtonHelper.click("TaskSearch_SearchButton");
					GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
				}
			}

			return isStatusUpdated;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

}
