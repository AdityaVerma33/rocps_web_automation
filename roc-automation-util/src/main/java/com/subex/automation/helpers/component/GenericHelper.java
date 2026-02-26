package com.subex.automation.helpers.component;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.componentHelpers.ComboBoxElementHelper;
import com.subex.automation.helpers.data.StringHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.ExecuteScript;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.automation.helpers.util.JSoupHelper;

public class GenericHelper extends AcceptanceTest {
	
	public static String getORProperty(String key) throws Exception {
		try {
			if (key.startsWith("//")) {
				return key;
			}
			else {
				String temp = or.getProperty(key);
				
				if (temp == null || temp.equals(""))
					return key;
				else
					return temp;
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to wait for the load mask to disappear on click of Save button.
	 * @throws Exception
	 */
	public static void waitForSave() throws Exception {
		try {
			waitForLoadmask(detailScreenWaitSec);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void waitForSave(int waitTimeInSecs) throws Exception {
		try {
			waitForLoadmask(waitTimeInSecs);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to wait for the load mask to disappear.
	 * @throws Exception
	 */
	public static void waitForLoadmask() throws Exception {
		try {
			waitForLoadmask(searchScreenWaitSec);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to wait for the load mask to disappear for the specified time period.
	 * If the load mask disappears within the specified time, test case execution will continue.
	 * If the load mask does not appear within the specified time, test case execution will fail.
	 * @param waitTimeInSecs - Time period is seconds.
	 * @throws Exception
	 */
	public static void waitForLoadmask(int waitTimeInSecs) throws Exception {
		try {
			waitForElementToDisappear("Loading_Mask", waitTimeInSecs);
			String screenName = NavigationHelper.getScreenName();
			
			if (screenName != null && screenName.equals("Visualizer")) {
				if (!ElementHelper.isElementPresent("Angular_Loading_Mask"))
					Thread.sleep(1000);
				
				waitForElementToDisappear("Angular_Loading_Mask", waitTimeInSecs);
			}
			
			waitForAJAXReady(waitTimeInSecs);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void waitForLoadmaskToAppear(int waitTimeInSecs) throws Exception {
		try {
			waitForElement("Loading_Mask", waitTimeInSecs);
			String screenName = NavigationHelper.getScreenName();
			
			if (screenName != null && screenName.equals("Visualizer")) {
				if (!ElementHelper.isElementPresent("Angular_Loading_Mask"))
					Thread.sleep(1000);
				
				waitForElement("Angular_Loading_Mask", waitTimeInSecs);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to wait for the specified element to appear on the GUI within the specified time.
	 * If the element appears within the specified time, test case execution will continue.
	 * If the element does not appear within the specified time, test case execution will fail.
	 * @param xpath - id or xpath of the element
	 * @param waitInSec - Time period is seconds.
	 * @throws Exception
	 */
	public static void waitForElement(String xpath, String waitInSec) throws Exception {
		try {
			int waitSec = Integer.parseInt(waitInSec);
			
			waitForElement(xpath, waitSec);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void waitForElement(String xpath, int waitInSec) throws Exception {
		try {
			xpath = getORProperty(xpath);
			if (xpath.endsWith("gwt_uid_"))
				xpath = or.getProperty("ComboBox_ById").replace("comboId", xpath);
			
			ElementHelper.waitForElement(xpath, waitInSec);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void waitForElementToDisappear(String xpath, String waitInSec) throws Exception {
		try {
			int waitSec = Integer.parseInt(waitInSec);
			
			waitForElementToDisappear(xpath, waitSec);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void waitForElementToDisappear(String xpath, int waitInSec) throws Exception {
		try {
			xpath = getORProperty(xpath);
			if (xpath.endsWith("gwt_uid_"))
				xpath = ComboBoxElementHelper.getLocator(xpath);
			
			ElementHelper.waitForElementToDisappear(xpath, waitInSec);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean waitForAJAXReady(long waitInSec) throws Exception
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitInSec);

		    // wait for jQuery to load
		    ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
		    	@Override
		    	public Boolean apply(WebDriver driver) {
		    		try {
		    			return ((Long)((JavascriptExecutor)getDriver()).executeScript("return jQuery.active") == 0);
		    		} catch (Exception e) {
		    			return true;
		    		}
		    	}
		    };
		    
		    // wait for Javascript to load
		    ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			    @Override
			    public Boolean apply(WebDriver driver) {
			    	 return ((JavascriptExecutor)getDriver()).executeScript("return document.readyState").toString().equals("complete");
				}
		    };
		    
		    return wait.until(jQueryLoad) && wait.until(jsLoad);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to fill popup window with token information like in Local File Source > Token and Relative Path. 
	 * @param iconId - id of the elipse icon.
	 * @param value - Value to be set in the popup separated by semicolon. Example, DataDir;Polled/msc
	 * @throws Exception
	 */
	public static void selectDataDir(String iconId, String value) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				TestDataHelper testData = new TestDataHelper();
				String[] values = testData.getStringValue(value, ";");
				
				selectDataDir(iconId, values);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void selectDataDir(String iconId, String value, String delimiter) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				TestDataHelper testData = new TestDataHelper();
				String[] values = testData.getStringValue(value, delimiter);
				
				selectDataDir(iconId, values);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void selectDataDir(String iconId, String[] values) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(values)) {
				EntityComboHelper.clickEntityIcon(iconId);
				waitForLoadmask(detailScreenWaitSec);
				
				selectDataDir(values);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void selectDataDir(String[] values) throws Exception {
		try {
			GenericHelper.waitForAJAXReady(detailScreenWaitSec);
			ComboBoxHelper.select("DataDir_Popup", "DataDir_Token_Dropdown", values[0]);
			TextBoxHelper.type("DataDir_Popup", "DataDir_RelativePath_TextBox", values[1]);
			
			if (values.length > 2 && !values[2].equals("")) {
				if (TextBoxHelper.isPresent("DataDir_Popup", "DataDir_Dialog_Filename_TextBox"))
					TextBoxHelper.type("DataDir_Popup", "DataDir_Dialog_Filename_TextBox", values[2]);
			}
			
			ButtonHelper.click("DataDir_Popup", "DataDir_Dialog_OK_Button");
			waitForLoadmask(detailScreenWaitSec);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to upload a file in the ROC default file upload option.
	 * @param fileNamewithPath - Upload filename with path.
	 * @throws Exception
	 */
	public static void fileUpload(String fileNamewithPath) throws Exception {
		try {
			FileHelper.fileUploadSikuli("FileUpload_Browse", fileNamewithPath);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to upload a file in the ROC default file upload option.
	 * @param id - id of the file upload option.
	 * @param fileNamewithPath - Upload filename with path.
	 * @throws Exception
	 */
	public static void fileUpload(String id, String fileNamewithPath) throws Exception {
		try {
			id = GenericHelper.getORProperty(id);
			String locator = null;
			
			if (!id.equals("")) {
				if (ElementHelper.isElementPresent(or.getProperty("FileUpload_Browse_ById").replace("uploadId", id))) {
					locator = or.getProperty("FileUpload_Browse_ById").replace("uploadId", id);
				}
				else if (ElementHelper.isElementPresent(id)) {
					locator = id;
				}
			}
			else {
				locator = "FileUpload_Browse";
			}
			
			FileHelper.fileUploadSikuli(locator, fileNamewithPath);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void calculatePerformance() throws Exception {
		try {
//			if (ValidationHelper.isTrue(configProp.getCalculatePerformance()))
//				calculatePerformance = true;
//			else
//				calculatePerformance = false;
			
			calculatePerformance = false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * This method is used to fetch data from the search screen
	 * Here rowNo is the the row from which data is fetched
	 * colNo refers to the column from which the value is to fetched
	 * Index starts from 0, so if data is in the second row and third column
	 * rowNo should be 1 and colNo should be 2
	 */
	public static String getValueForAssertion(int rowNo, int colNo) throws Exception {
		try {
			JSoupHelper jSoup = new JSoupHelper();
			Elements tableRows = jSoup.getElements("#aggSummaryGrid tbody:nth-child(2)");
			boolean summaryGrid = true;
			if (tableRows == null) {
				tableRows = jSoup.getElements("#searchGrid tbody:nth-child(2)");
				summaryGrid = false;
			}
			
			Element rowValue = tableRows.select( "tr" ).get( rowNo );
			Element colValue = rowValue.select( "td" ).get( colNo );
			Elements booleanValue = colValue.select("img");
			
			if (booleanValue.size() > 0 && colValue.text().equals("")) {
				if (summaryGrid && rowNo == 0 && colNo == 0)
					return colValue.text().replace("�", "").replace("\u00A0", "");
				else {
					String value = String.valueOf(booleanValue.attr("src").replace("%20", " ").contains("yes _new.png"));
					return value.toUpperCase();
				}
			}
			else
				return colValue.text().replace("�", "").replace("\u00A0", "");   // replacing some unidentified character with no character or space.
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int[] resizeIntArray(int[] intArray, int length) throws Exception {
		try {
			int[] dummy = new int[length];
			int aLength = intArray.length;
			if (intArray != null && aLength > 0) {
				if (aLength > length)
					System.arraycopy(intArray, 0, dummy, 0, length);
				else
					System.arraycopy(intArray, 0, dummy, 0, aLength);
			}
			intArray = dummy;
			
			return intArray;
		}
		catch(Exception e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void waitTime(int sec, String Message) throws Exception {
		try {
			Log4jHelper.logInfo(Message );
			
			for(int i=0;i<sec;i++) {
				Thread.sleep(1000);
				Log4jHelper.logInfo(".");
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getPath(String fileName) throws Exception {
		try {
			String path = fileName;
			
			if (configProp != null) {
				path = path.replace("\\\\", "\\");
			}
			else {
				path = path.replace("\\\\", "/").replace("\\", "/");
				if (path.contains(" "))
					path = path.replace(" ", "\\ ");
			}
			
			if (path.contains("\""))
				path = path.replace("\"", "");
			
			String temp = path.substring(2);
			if (temp.contains(":"))
				path = path.substring(0, 2) + temp.replace(":", "");
			FileHelper.makeDirectory(path);
			return path.trim();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getPath(String os, String fileName) throws Exception {
		try {
			String path = fileName;
			
			if (configProp != null) {
				switch (os.toLowerCase()) {
				case "windows":
//					path = path.replace("/", "\\");
					path = path.replace("\\\\", "\\");
					break;
				
				case "unix":
				case "linux":
					if (path.contains("\\")) {
						path = path.replace("\\\\", "/").replace("\\", "/");
					
						if (path.contains(" "))
							path = path.replace(" ", "\\ ");
					}
					else {
						if (path.contains(" "))
							path = path.replace(" ", "\\ ");
					}
					break;
	
				default:
					break;
				}
			}
			else {
				path = path.replace("\\\\", "/").replace("\\", "/");
				if (path.contains(" "))
					path = path.replace(" ", "\\ ");
			}
			
			if (path.contains("\""))
				path = path.replace("\"", "");
			
			String temp = path.substring(2);
			if (temp.contains(":"))
				path = path.substring(0, 2) + temp.replace(":", "");
			FileHelper.makeDirectory(path);
			return path.trim();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String updatePath(String fileName) throws Exception {
		try {
			String path = getPath(fileName);
			
			if (!fileName.startsWith("/")) {
				if (fileName.substring(1, 2).equals(":")) {
					path = fileName;
				}
				else {
					if (!fileName.startsWith("\\"))
						fileName = "\\" + fileName;
					
					if (new File(automationPath + fileName).exists())
						path = automationPath + fileName;
					else
						path = automationPath + "\\src\\main\\resources" + fileName;
				}
				
				path = path.replace("\\\\", "\\").replace("\"", "");
				
				FileHelper.makeDirectory(path);
			}

			return path.trim();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[] updatePath(String[] fileName) throws Exception {
		try {
			String[] path = new String[fileName.length];
			
			for (int i = 0; i < fileName.length; i++) {
				path[i] = updatePath(fileName[i]);
			}
			
			return path;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static List<Integer> getIntegerKeySet(Map<Integer, Object[]> data) throws Exception {
		try {
			Set<Integer> keyset = data.keySet();
			List<Integer> lKeyset = new ArrayList<>(keyset);
			Collections.sort(lKeyset);
			
			return lKeyset;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int searchBooleanArray(boolean[] array, boolean searchValue) throws Exception {
		try {
			for (int i = 0; i < array.length; i++) {
				if (array[i] == searchValue)
					return i;
			}
			
			return -1;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int searchIntegerArray(int[] array, int searchValue) throws Exception {
		try {
			for (int i = 0; i < array.length; i++) {
				if (array[i] == searchValue)
					return i;
			}
			
			return -1;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static Object[] createObject(int length) throws Exception {
		try {
			Object[] obj = new Object[length];
			for (int i = 0; i < length; i++)
				obj[i] = "";
			
			return obj;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[][] getKeys(Map<String[], ArrayList<String>> expressions) throws Exception {
		try {
			Set<String[]> keyset = expressions.keySet();
			String[][] keys = new String[keyset.size()][];
			int kCount = 0;
			
			for (String[] set : keyset) {
				keys[kCount] = set;
				kCount++;
			}
			
			keys = StringHelper.sortArray(keys);
			return keys;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean[] resizeBooleanArray(boolean[] boolArray, int length) throws Exception {
		try {
			boolean[] dummy = new boolean[length];
			int aLength = boolArray.length;
			if (boolArray != null && aLength > 0) {
				if (aLength > length)
					System.arraycopy(boolArray, 0, dummy, 0, length);
				else
					System.arraycopy(boolArray, 0, dummy, 0, aLength);
			}
			
			boolArray = dummy;
			return boolArray;
		} catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean[][] resizeBooleanArray(boolean[][] boolArray, int length) throws Exception {
		try {
			boolean[][] dummy = new boolean[length][boolArray.length];
			int aLength = boolArray.length;
			if (boolArray != null && aLength > 0) {
				if (aLength > length)
					System.arraycopy(boolArray, 0, dummy, 0, length);
				else
					System.arraycopy(boolArray, 0, dummy, 0, aLength);
			}
			
			boolArray = dummy;
			return boolArray;
		} catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int searchBooleanArray(boolean[][] array, boolean searchValue) throws Exception {
		try {
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array[i].length; j++) {
					if (array[i][j] == searchValue)
						return i;
				}
			}
			
			return -1;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static  void expandSearchFilterPanel() throws Exception
	{
		try {
			String locator = or.getProperty("ExpandSearchFiler");
			
			if(ImageHelper.isPresent(locator) && ElementHelper.isClickable(locator)) {
				ImageHelper.click(locator);
				Thread.sleep(500);
			}
		} catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static  void collapseSearchFilterPanel() throws Exception
	{
		try {
			String locator = or.getProperty("CollapseSearchFilter");
			
			if(ImageHelper.isPresent(locator) && ElementHelper.isClickable(locator)) {
				ImageHelper.click(locator);
				Thread.sleep(500);
			}
		} catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to wait for the specified time period before going to next step.
	 * @param seconds - Time period is seconds.
	 * @throws Exception
	 */
	public static void waitInSeconds(String seconds) throws Exception {
		try {
			int milliSeconds = Integer.parseInt(seconds)*1000;
			Thread.sleep(milliSeconds);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to wait for the specified time period before going to next step.
	 * @param minutes - Time period is minutes.
	 * @throws Exception
	 */
	public static void waitInMinutes(String minutes) throws Exception {
		try {
			int milliSeconds = Integer.parseInt(minutes)*60000;
			Thread.sleep(milliSeconds);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to click Entity search icon.
	 * @param iconId - id of entity search icon.
	 * @throws Exception
	 */
	public static void clickCloseIcon() throws Exception {
		try {
			List<WebElement> dialogs = ElementHelper.getDialogElements();
			
			if (dialogs != null && dialogs.size() > 0) {
				WebElement dialog = dialogs.get(0);
				String locator = or.getProperty("Dialog_Close_Icon");
				
				if (locator != null) {
					WebElement element = ElementHelper.getElement(dialog, locator);
					String isHidden = ElementHelper.getAttribute(element, "aria-hidden");
										
					if (isHidden == null) {
						ElementHelper.click(element);
						
						boolean isClickable = ElementHelper.isClickable(dialog);
						if (isClickable)
							FailureHelper.failTest("The popup did not close on click of Close icon.");
					}
				}
				else {
					FailureHelper.failTest("The popup does not have Close icon.");
				}
			}
			else {
				FailureHelper.failTest("There is no popup on the screen.");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getDataPath(String resultExcelPath) throws Exception {
		try {
			String path = automationPath + "\\" + resultExcelPath;
			if (!FileHelper.checkDirectoryExists(automationOS, path)) {
				if (resultExcelPath.startsWith("\\"))
					path = automationPath + "\\src\\main\\resources" + resultExcelPath;
				else
					path = automationPath + "\\src\\main\\resources\\" + resultExcelPath;
			}
			
			return path;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getDataPath(String resultExcelPath, String fileName) throws Exception {
		try {
			String path = automationPath + "\\" + resultExcelPath;
			if (!path.endsWith("/") && !path.endsWith("\\"))
				path = path + "\\";
			
			if (!FileHelper.checkDirectoryExists(automationOS, path) || !FileHelper.checkFileExists(path + fileName)) {
				path = automationPath + "\\src\\main\\resources\\" + resultExcelPath;
			}
			
			return path;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getScreenResolution() throws Exception {
		try {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int screenHeight = screenSize.height;
			int screenWidth = screenSize.width;
			
			return screenWidth + "*" + screenHeight;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getUploadFilePath(String fileName) throws Exception {
		try {
			String fileNamewithPath = automationPath + "\\" + fileName;
			if (!FileHelper.checkFileExists(fileNamewithPath)) {
				if (fileName.startsWith("\\"))
					fileNamewithPath = automationPath + "\\src\\main\\resources" + fileName;
				else
					fileNamewithPath = automationPath + "\\src\\main\\resources\\" + fileName;
			}
			
			return fileNamewithPath;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getTooltip() throws Exception {
		try {
			String toolTip = LabelHelper.getText("Tooltip");
			
			return toolTip;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void insertPartition(String partitionName) throws Exception {
		ResultSet resultSet = null;
		
		try {
			int primaryKey = 2;
			resultSet = ExecuteScript.exeQuery("select max(ptn_id) from partition_tbl");
			if (resultSet != null) {
				resultSet.next();
				primaryKey = resultSet.getInt(1);
				primaryKey++;
			}
			
			String strQuery = "insert into partition_tbl (ptn_id,ptn_name,system_generated_fl,ptn_delete_fl,ptn_version_id) values (" + primaryKey + ", '" + partitionName + "','N','N', 1)";
			ExecuteScript.exeQuery(strQuery);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			ExecuteScript.closeConnection(resultSet);
		}
	}
	
	public static void insertPartition(String[] partitionName) throws Exception {
		ResultSet resultSet = null;
		
		try {
			int primaryKey = 2;
			resultSet = ExecuteScript.exeQuery("select max(ptn_id) from partition_tbl");
			if (resultSet != null) {
				resultSet.next();
				primaryKey = resultSet.getInt(1);
				primaryKey++;
			}
			
			for (int i = 0; i < partitionName.length; i++) {
				String strQuery = "insert into partition_tbl (ptn_id,ptn_name,system_generated_fl,ptn_delete_fl,ptn_version_id) values (" + primaryKey + ", '" + partitionName[i] + "','N','N', 1)";
				ExecuteScript.exeQuery(strQuery);
				primaryKey++;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			ExecuteScript.closeConnection(resultSet);
		}
	}
}