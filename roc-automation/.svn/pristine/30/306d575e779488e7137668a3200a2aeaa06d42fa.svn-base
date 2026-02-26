package com.subex.automation.helpers.dataGeneration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.file.ExcelReaderHelper;
import com.subex.automation.helpers.scripts.MethodHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class SimpleDataHelper extends ROCAcceptanceTest {
	
	static int recordCount = 1;
	static int lookback = 0;
	static String sourceName = null;
	static String dateFormat = null;
	static String sourceDirectory = null;
	static boolean includeHeader = false;
	static int[] multiValueFields = new int[100];
	static boolean hasMultiValueField = false;
	static ArrayList<String> actualData = new ArrayList<String>();
	
	public SimpleDataHelper(String srcName, String recCount) throws Exception {
		try {
			
			sourceName = srcName;
			recordCount = Integer.parseInt(recCount);
			lookback = 0;
			dateFormat = null;
			sourceDirectory = null;

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public SimpleDataHelper(String srcName, String recCount, String value) throws Exception {
		try {
			
			sourceName = srcName;
			recordCount = Integer.parseInt(recCount);
			
			if (ValidationHelper.isInteger(value)) {
				lookback = Integer.parseInt(value);
				sourceDirectory = null;
				dateFormat = null;
			}
			else if (ValidationHelper.isBoolean(value)) {
				if (ValidationHelper.isTrue(value))
					includeHeader = true;
				sourceDirectory = null;
				lookback = 0;
				dateFormat = null;
			}
			else if (ValidationHelper.isDirectory(value)) {
				sourceDirectory = value;
				lookback = 0;
				dateFormat = null;
			}
			else {
				dateFormat = value;
				sourceDirectory = null;
				lookback = 0;
			}

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public SimpleDataHelper(String srcName, String recCount, String lookBack, String value) throws Exception {
		try {
			
			sourceName = srcName;
			recordCount = Integer.parseInt(recCount);
			lookback = Integer.parseInt(lookBack);
			
			if (ValidationHelper.isBoolean(value)) {
				if (ValidationHelper.isTrue(value))
					includeHeader = true;
				sourceDirectory = null;
				dateFormat = null;
			}
			else if (ValidationHelper.isDirectory(value)) {
				sourceDirectory = value;
				dateFormat = null;
			}
			else {
				dateFormat = value;
				sourceDirectory = null;
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public SimpleDataHelper(String srcName, String recCount, String lookBack, String dateformat, String value) throws Exception {
		try {
			
			sourceName = srcName;
			recordCount = Integer.parseInt(recCount);
			lookback = Integer.parseInt(lookBack);
			dateFormat = dateformat;
			
			if (ValidationHelper.isBoolean(value)) {
				if (ValidationHelper.isTrue(value))
					includeHeader = true;
				sourceDirectory = null;
			}
			else {
				sourceDirectory = value;
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public SimpleDataHelper(String srcName, String recCount, String lookBack, String dateformat, String sourceDir, String addHeader) throws Exception {
		try {
			
			sourceName = srcName;
			recordCount = Integer.parseInt(recCount);
			lookback = Integer.parseInt(lookBack);
			dateFormat = dateformat;
			sourceDirectory = sourceDir;
			
			if (ValidationHelper.isTrue(addHeader)) {
				includeHeader = true;
			}
			else {
				includeHeader = false;
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void dataGeneration(HashMap<String, ArrayList<String>> testData, String sampleFileName) throws Exception {
		try {
			SimpleDateFormat format = new SimpleDateFormat(configProp.getDateFormat() + " " + configProp.getTimeFormat());
			String filePath = automationPath + "\\src\\main\\resources";
			Date startDateTime = format.parse(DateHelper.currentDateTime(applicationOS));
			
			ExcelReaderHelper excelReader = new ExcelReaderHelper(filePath + "//" + sampleFileName);
			Object[] obj = excelReader.getDataUsingColumnName(sampleFileName, "Config", sourceName);
			@SuppressWarnings("unchecked")
			HashMap<Integer, ArrayList<String>> fileData = (HashMap<Integer, ArrayList<String>>) obj[0];
			int totalSamples = (int) obj[1];
			actualData = new ArrayList<String>();
			
			for (int i = 0; i < totalSamples; i++) {
				hasMultiValueField = false;
				String[][] data = DataGenerationHelper.getData(testData, fileData, i, hasMultiValueField, multiValueFields, dateFormat, lookback);
				generateRecord(data);
			}
			actualData.remove(actualData.size()-1);  // this is to remove last newline character.
			
			String dataFilename = DataGenerationHelper.createFile(sourceName, sourceDirectory, filePath, actualData);
			Date endDateTime = format.parse(DateHelper.currentDateTime(applicationOS));
			String difference = DateHelper.getDuration(startDateTime, endDateTime);
			DataGenerationHelper.addToReport(sourceDirectory, difference, dataFilename, filePath);
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void dataGeneration(String dataFileName, String dataSheetName, String fileName) throws Exception {
		try {
			SimpleDateFormat format = new SimpleDateFormat(configProp.getDateFormat() + " " + configProp.getTimeFormat());
			String filePath = automationPath + "\\src\\main\\resources";
			Date startDateTime = format.parse(DateHelper.currentDateTime(applicationOS));
			MethodHelper methodHelper = new MethodHelper();
			int occurance = methodHelper.getMethodOccurance(sourceName);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> testData = excelReader.readDataByColumn(filePath, dataFileName, dataSheetName, sourceName, occurance);
			ExcelReaderHelper excelReaderHlp = new ExcelReaderHelper(filePath + "//" + fileName);
			Object[] obj = excelReaderHlp.getDataUsingColumnName(fileName, "Config", sourceName);
			
			@SuppressWarnings("unchecked")
			HashMap<Integer, ArrayList<String>> fileData = (HashMap<Integer, ArrayList<String>>) obj[0];
			int totalSamples = (int) obj[1];
			actualData = new ArrayList<String>();
			
			for (int i = 0; i < totalSamples; i++) {
				hasMultiValueField = false;
				String[][] data = DataGenerationHelper.getData(testData, fileData, i, hasMultiValueField, multiValueFields, dateFormat, lookback);
				generateRecord(data);
			}
			actualData.remove(actualData.size()-1);  // this is to remove last newline character.
			
			String dataFilename = DataGenerationHelper.createFile(sourceName, sourceDirectory, filePath, actualData);
			Date endDateTime = format.parse(DateHelper.currentDateTime(applicationOS));
			String difference = DateHelper.getDuration(startDateTime, endDateTime);
			DataGenerationHelper.addToReport(sourceDirectory, difference, dataFilename, filePath);
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static void generateRecord(String[][] data) throws Exception {
		try {
			DataGenerationHelper.recordCount = recordCount;
			
			if (includeHeader == true)
				actualData = DataGenerationHelper.addHeader(data);
			
			if (hasMultiValueField) {
				generateMultipleRecords(data);
			}
			else {
				for (int i = 0; i < recordCount; i++) {
					DataGenerationHelper.randomValue = -1;
					String temp = DataGenerationHelper.getRecord(data, dateFormat, i);
					
					actualData.add(temp);
					actualData.add("\n");
				}
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static void generateMultipleRecords(String[][] data) throws Exception {
		try {
			Arrays.sort(multiValueFields);
			int totalRecords = recordCount;
			
			if (multiValueFields.length == 1) {
				int index = multiValueFields[0];
				totalRecords = totalRecords * Integer.parseInt(data[index][3]);
			}
			else {
				for (int i = 0; i < multiValueFields.length; i++) {
					int index = multiValueFields[i];
					totalRecords = totalRecords * Integer.parseInt(data[index][3]);
				}
			}
			
			String[] temp = getMultipleRecord(data, totalRecords);
			
			for (int i = 0; i < temp.length; i++) {
				actualData.add(temp[i]);
				actualData.add("\n");
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String[] getMultipleRecord(String[][] data, int totalRecords) throws Exception {
		try {
			
			String[] temp = new String[totalRecords];
					
			for (int i = 0; i < data.length; i++) {
				String[] dTemp = new String[10];
				int count = 0;
				int index = Arrays.binarySearch(multiValueFields, i);
				
				if (index >= 0) {
					dTemp = data[i][0].split(";", -1);
				}
				
				
				if (index >= 0) {
					for (int j = 0; j < totalRecords; j++) {
						DataGenerationHelper.randomValue = -1;
						dTemp[count] = DataGenerationHelper.getValue(data[i][1], dTemp[count], dateFormat, j);
						
						if (i == 0)
							temp[j] = dTemp[count];
						else
							temp[j] = temp[j] + "," + dTemp[count];
						count++;
						
						if (count == dTemp.length)
							count = 0;
					}
				}
				else {
					for (int j = 0; j < totalRecords; j++) {
						String value = DataGenerationHelper.getValue(data[i][1], data[i][0], dateFormat, j);
						
						if (i == 0)
							temp[j] = value;
						else
							temp[j] = temp[j] + "," + value;
					}
				}
			}
			
			return temp;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}