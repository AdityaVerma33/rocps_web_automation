package com.subex.automation.helpers.dataGeneration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReaderHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class SamplingDataHelper extends ROCAcceptanceTest {
	
	static int recordCount = 1;
	static int lookback = 0;
	static String sourceName = null;
	static String dateFormat = null;
	static String sourceDirectory = null;
	static boolean includeHeader = false;
	static int[] multiValueFields = new int[100];
	static boolean hasMultiValueField = false;
	static ArrayList<String> actualData = new ArrayList<String>();
	
	static String sampleFile = null;
	static int uniqueness = 1;
	
	public SamplingDataHelper(String srcName, String recCount, String samplingFile) throws Exception {
		try {
			
			sourceName = srcName;
			recordCount = Integer.parseInt(recCount);
			sampleFile = samplingFile;
			uniqueness = 1;
			lookback = 0;
			dateFormat = null;
			sourceDirectory = null;

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public SamplingDataHelper(String srcName, String recCount, String samplingFile, String value) throws Exception {
		try {
			
			sourceName = srcName;
			recordCount = Integer.parseInt(recCount);
			sampleFile = samplingFile;
			uniqueness = Integer.parseInt(value);
			lookback = 0;
			dateFormat = null;
			sourceDirectory = null;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public SamplingDataHelper(String srcName, String recCount, String samplingFile, String unique, String value) throws Exception {
		try {
			
			sourceName = srcName;
			recordCount = Integer.parseInt(recCount);
			sampleFile = samplingFile;
			uniqueness = Integer.parseInt(unique);
			
			if (ValidationHelper.isInteger(value)) {
				lookback = Integer.parseInt(value);
				sourceDirectory = null;
				dateFormat = null;
			}
			else if (ValidationHelper.isBoolean(value)) {
				if (ValidationHelper.isTrue(value))
					includeHeader = true;
				lookback = 0;
				sourceDirectory = null;
				dateFormat = null;
			}
			else if (ValidationHelper.isDirectory(value)) {
				sourceDirectory = value;
				lookback = 0;
				dateFormat = null;
			}
			else {
				dateFormat = value;
				lookback = 0;
				sourceDirectory = null;
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public SamplingDataHelper(String srcName, String recCount, String samplingFile, String unique, String lookBack, String value) throws Exception {
		try {
			
			sourceName = srcName;
			recordCount = Integer.parseInt(recCount);
			sampleFile = samplingFile;
			uniqueness = Integer.parseInt(unique);
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
	
	public SamplingDataHelper(String srcName, String recCount, String samplingFile, String unique, String lookBack, String dateformat, String value) throws Exception {
		try {
			
			sourceName = srcName;
			recordCount = Integer.parseInt(recCount);
			sampleFile = samplingFile;
			uniqueness = Integer.parseInt(unique);
			lookback = Integer.parseInt(lookBack);
			dateFormat = dateformat;
			
			if (ValidationHelper.isBoolean(value)) {
				if (ValidationHelper.isTrue(value))
					includeHeader = true;
				sourceDirectory = null;
			}
			else if (ValidationHelper.isDirectory(value)) {
				sourceDirectory = value;
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public SamplingDataHelper(String srcName, String recCount, String samplingFile, String unique, String lookBack, String dateformat, String sourceDir, String addHeader) throws Exception {
		try {
			
			sourceName = srcName;
			recordCount = Integer.parseInt(recCount);
			sampleFile = samplingFile;
			uniqueness = Integer.parseInt(unique);
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
	
	public void dataGeneration(HashMap<String, ArrayList<String>> testData, String fileName) throws Exception {
		try {
			SimpleDateFormat format = new SimpleDateFormat(configProp.getDateFormat() + " " + configProp.getTimeFormat());
			String filePath = automationPath + "\\src\\main\\resources";
			Date startDateTime = format.parse(DateHelper.currentDateTime(applicationOS));
			
			ExcelReaderHelper excelReader = new ExcelReaderHelper(filePath + "//" + fileName);
			Object[] obj = excelReader.getDataUsingColumnName(fileName, "Config", sourceName);
			@SuppressWarnings("unchecked")
			HashMap<Integer, ArrayList<String>> fileData = (HashMap<Integer, ArrayList<String>>) obj[0];
			int totalSamples = (int) obj[1];
			actualData = new ArrayList<String>();
			
			for (int i = 0; i < totalSamples; i++) {
				hasMultiValueField = false;
				String[] sampleData = FileHelper.readFileContent(automationOS, filePath + "\\" + sampleFile);
				String[][] data = DataGenerationHelper.getData(testData, fileData, i, hasMultiValueField, multiValueFields, dateFormat, lookback);
				
				generateRecord(data, sampleData);
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
	
	private static void generateRecord(String[][] data, String[] sampleData) throws Exception {
		try {
			
			if (includeHeader == true) {
				actualData = DataGenerationHelper.addHeader(data);
			}
			
			if (hasMultiValueField) {
				generateMultipleRecords(data, sampleData);
			}
			else {
				generateSimpleRecords(data, sampleData);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static void generateSimpleRecords(String[][] data, String[] sampleData) throws Exception {
		try {
			for (int x = 0; x < uniqueness; x++) {
				Random r = new Random();
				String rTemp = sampleData[r.nextInt(sampleData.length-1)];
				
				for (int i = 0; i < recordCount; i++) {
					String temp = "";
					
					for (int j = 0; j < data.length; j++) {
						String dValue = data[j][0];
						
						if (data[j][1].equals("Sampling")) {
							dValue = rTemp;
						}
						else if (data[i][1].equalsIgnoreCase("Integer")) {
							int vTemp = Integer.parseInt(dValue) - i;
							dValue = String.valueOf(vTemp);
						}
						
						if (j == 0)
							temp = dValue;
						else
							temp = temp + "," + dValue;
					}
					
					actualData.add(temp);
					actualData.add("\n");
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static void generateMultipleRecords(String[][] data, String[] sampleData) throws Exception {
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
			
			String[] temp = new String[totalRecords];
			for (int i = 0; i < data.length; i++) {
				String[] dTemp = new String[10];
				int count = 0;
				int index = Arrays.binarySearch(multiValueFields, i);
				
				if (index >= 0) {
					dTemp = data[i][0].split(";", -1);
				}
				
				for (int j = 0; j < totalRecords; j++) {
					if (index >= 0) {
						
						if (data[i][1].equalsIgnoreCase("Integer")) {
							int vTemp = Integer.parseInt(dTemp[count]) - j;
							dTemp[count] = String.valueOf(vTemp);
						}
						
						if (i == 0)
							temp[j] = dTemp[count];
						else
							temp[j] = temp[j] + "," + dTemp[count];
						count++;
						
						if (count == dTemp.length)
							count = 0;
					}
					else {
						String value = data[i][0];
						
						if (data[i][1].equalsIgnoreCase("Integer")) {
							int vTemp = Integer.parseInt(value) - j;
							value = String.valueOf(vTemp);
						}
						
						if (i == 0)
							temp[j] = data[i][0];
						else
							temp[j] = temp[j] + "," + data[i][0];
					}
				}
			}
			
			for (int i = 0; i < temp.length; i++) {
				actualData.add(temp[i]);
				actualData.add("\n");
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}