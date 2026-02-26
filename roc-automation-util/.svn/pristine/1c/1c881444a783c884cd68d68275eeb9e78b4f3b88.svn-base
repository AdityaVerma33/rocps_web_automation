package com.subex.automation.helpers.dataGeneration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.joda.time.DateTime;

import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.config.PropertyReader;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.ReportHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.automation.helpers.util.RemoteMachineHelper;

public class DataGenerationHelper extends AcceptanceTest {
	
	public static long randomValue = -1;
	public static int recordCount = 0;
	
	static void addToReport(String sourceDirectory, String difference, String dataFilename, String filePath) throws Exception {
		try {
			ReportHelper.updateStepKey("Time Taken", "Blue", difference);
			ReportHelper.updateStepKey("Data Filename", "Blue", dataFilename);
			
			if (sourceDirectory == null)
				ReportHelper.updateStepKey("Data File Location", "Blue", filePath + "\\Data\\");
			else
				ReportHelper.updateStepKey("Data File Location", "Blue", sourceDirectory);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String createFile(String sourceName, String sourceDirectory, String filePath, ArrayList<String> actualData) throws Exception {
		try {
			
			String currentDT = DateHelper.getCurrentDateTime("yyyyMMdd_HHmmss");
			String dataFilename = sourceName + "_" + currentDT + ".txt";
			FileHelper.writeToFile(filePath + "\\Data\\" + dataFilename, actualData);
			
			if (sourceDirectory != null) {
				if (sourceDirectory.startsWith("/")) {
					RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
					remoteMachine.copyFile(filePath + "\\Data\\" + dataFilename, sourceDirectory, dataFilename, true);
				}
				else
					FileHelper.copyFile(filePath + "\\Data\\", sourceDirectory, dataFilename, dataFilename, true);
			}
			
			return dataFilename;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[][] getData(HashMap<String, ArrayList<String>> testData, HashMap<Integer, ArrayList<String>> fileData, int sampleNo, boolean hasMultiValueField, int[] multiValueFields, String dateFormat, int lookback) throws Exception {
		try {
			int length = fileData.size();
			String[][] data = new String[length][4];
			int count = 0;
			
			for (int j = 0; j < length; j++) {
				boolean testDataValue = false;
				String pValue = fileData.get(j+1).get(0);
				String dValue = fileData.get(j+1).get(sampleNo+1);
				
				if (testData != null && testData.get(pValue) != null && !testData.get(pValue).equals("")) {
					dValue = testData.get(pValue).get(0);
					testDataValue = true;
				}
				
				data[j][2] = pValue;
				
				if (dValue.contains(";")) {
					hasMultiValueField = true;
					data[j][1] = "Multiple";
					data[j][0] = dValue;
					String[] temp = dValue.split(";");
					
					data[j][3] = String.valueOf(temp.length);
					multiValueFields[count] = j;
					count++;
				}
				else {
					data[j][1] = getType(dValue, dateFormat);
					data[j][0] = getValue(dValue, data[j][1], testDataValue, dateFormat, lookback);
					data[j][3] = "1";
				}
			}
			
			if (hasMultiValueField)
				multiValueFields = GenericHelper.resizeIntArray(multiValueFields, count);
			
			return data;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String getValue(String dValue, String type, boolean testDataValue, String dateFormat, int lookback) throws Exception {
		try {
			String value = null;
			
			switch (type) {
			case "Date":
				if (testDataValue)
					value = convertDate(dValue, dateFormat, lookback);
				else
					value = convertDate(dValue, dateFormat, 0);
				break;

			case "Increment":
				value = dValue.replace("*", "");
				break;
				
			case "Random":
				value = dValue.replace("*R", "").replace("*r", "");
				break;
				
			default:
				value = dValue;
				break;
			}
			
			return value;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	static String getType(String dValue, String dateFormat) throws Exception {
		try {
			String type = "";
			
			switch (dValue) {
			case "":
				type = "Constant";
				break;

			case "Now":
				type = "Date";
				break;
				
			case "Now*":
				type = "Date";
				break;
				
			case "Now*R":
				type = "Date";
				break;
				
			case "Now*r":
				type = "Date";
				break;
				
			default:
				if (dValue.startsWith("$")) {
					type = "Sampling";
				}
				else if (dValue.endsWith("*")) {
					type = "Increment";
					
					if (isDate(dValue.replace("*", ""), dateFormat)) {
						type = "Date";
					}
				}
				else if (dValue.endsWith("*R") || dValue.endsWith("*r")) {
					type = "Random";
					
					if (isDate(dValue.replace("*R", "").replace("*r", ""), dateFormat)) {
						type = "Date";
					}
				}
				else {
					if (dValue.matches("[A-Za-z0-9]+")) {
						type = "Constant";
					}
					else if (dValue.matches("[0-9]+.[0-9]+")) {
						type = "Decimal";
					}
					else if (isDate(dValue, dateFormat)) {
						type = "Date";
					}
					else {
						type = "Constant";
					}
				}
				break;
			}
			
			return type;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static boolean isDate(String value, String dateFormat) throws Exception {
		String formattedDate = null;
		
		try {
			SimpleDateFormat originalSDF = null;
			
			if (ValidationHelper.isEmpty(dateFormat)) {
				if (configProp.getDataDateFormat().equals(""))
					FailureHelper.failTest("Date format should be specified in config.properties file or in TestScript file in Values.");
				else
					originalSDF = new SimpleDateFormat(configProp.getDataDateFormat());
			}
			else {
				originalSDF = new SimpleDateFormat(dateFormat);
			}
			
			Date date = originalSDF.parse(value);
			formattedDate = originalSDF.format(date);
			
		} catch (ParseException e) {
			//ignore conversion as value is not date
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		
		if (formattedDate != null)
	    	return true;
	    else
	    	return false;
	}
	
	static String convertDate(String value, String dateFormat, int lookback) throws Exception {
		try {
			
			String incrementSuffix = "";
			String format = null;
			if (ValidationHelper.isEmpty(dateFormat))
				format = configProp.getDataDateFormat();
			else
				format = dateFormat;
			
			if (ValidationHelper.isNotEmpty(format)) {
				if (value.equalsIgnoreCase("Now")) {
					value = getNow(value, format);
				}
				else if (value.equalsIgnoreCase("Now*")) {
					value = getNow(value.replace("*", ""), format);
					incrementSuffix = "*";
				}
				else if (value.equalsIgnoreCase("Now*R")) {
					value = getNow(value.replace("*R", ""), format);
					incrementSuffix = "*R";
				}
				else if (value.equalsIgnoreCase("Now*r")) {
					value = getNow(value.replace("*r", ""), format);
					incrementSuffix = "*r";
				}
				
				format = format.replaceAll("Y", "y").replace("D", "d");
				SimpleDateFormat originalSDF = new SimpleDateFormat(format);
				Date date = originalSDF.parse(value);
				
				DateTime dTemp = new DateTime(date).minusDays(lookback);
				date = dTemp.toDate();
				String temp = originalSDF.format(date);
				temp = temp + incrementSuffix;
				return temp;
			}
			else {
				FailureHelper.failTest("Date format should be specified in config.properties file or in TestScript file in Values.");
				return null;
			}
		}
		catch (ParseException e) {
			FailureHelper.setError("Could not convert date format for value '" + value + "'");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	static String incrementDate(String value, String dateFormat, long secondsIncrement) throws Exception {
		try {
			
			String format = null;
			if (ValidationHelper.isEmpty(dateFormat))
				format = configProp.getDataDateFormat();
			else
				format = dateFormat;
			
			if (ValidationHelper.isNotEmpty(format)) {
				format = format.replaceAll("Y", "y").replace("D", "d");
				SimpleDateFormat originalSDF = new SimpleDateFormat(format);
				Date date = originalSDF.parse(value);
				
				DateTime dTemp = new DateTime(date).plusSeconds((int) secondsIncrement);
				date = dTemp.toDate();
				String temp = originalSDF.format(date);
				return temp;
			}
			else {
				FailureHelper.failTest("Date format should be specified in config.properties file or in TestScript file in Values.");
				return null;
			}
		}
		catch (ParseException e) {
			FailureHelper.setError("Could not convert date format for value '" + value + "'");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String getNow(String value, String format) throws Exception {
		try {
			
			if (configProp.getCurrentNowDateTime().equals("")) {
				value = DateHelper.getCurrentDateTime(format);
			
				FileHelper.updatePropertyFile(configFile, "currentNowDateTime", value);
				FileHelper.updatePropertyFile(configFile, "dateTimeFormat", format);
				configProp = new PropertyReader( configFile );
			}
			else {
				String nowDateFormat = configProp.getDateTimeFormat();
				String dataDateFormat = configProp.getDataDateFormat();
				
				if (dataDateFormat.equals(nowDateFormat)) {
					value = configProp.getCurrentNowDateTime();
				}
				else {
					value = DateHelper.convertDate(nowDateFormat, dataDateFormat, configProp.getCurrentNowDateTime());
				}
			}
			
			return value;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	static String getValue(String type, String value, String dateFormat, int increment) throws Exception {
		try {
			
			switch (type) {
			case "Increment":
				value = getNextValue(value, increment);
				break;

			case "Random":
				value = getNextRandomValue(value);
				break;
				
			case "Date":
				if (value.endsWith("*")) {
					value = value.replace("*", "");
					value = incrementDate(value, dateFormat, increment);
				}
				else if (value.endsWith("*R") || value.endsWith("*r")) {
					value = value.replace("*R", "").replace("*r", "");
					long rValue = getRandomNumber(null);
					value = incrementDate(value, dateFormat, rValue);
				}
				break;
			default:
				break;
			}
			
			return value;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	static int getPreceedingZeroCount(String value) throws Exception {
		try {
			int count = 0;
			
			for (int i = 0; i < value.length(); i++) {
				char digit = value.charAt(i);
				if (Integer.parseInt(String.valueOf(digit)) == 0)
					count++;
				else
					break;
			}
			
			return count;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	static String addPreceedingZeroCount(long value, int preceedingZeros) throws Exception {
		try {
			if (preceedingZeros > 0) {
				String sValue = String.valueOf(value);
				int length = sValue.length();
				
				for (int i = 0; i < (preceedingZeros-length); i++) {
					sValue = "0" + sValue;
				}
				
				return sValue;
			}
			else
				return String.valueOf(value);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	static String getNextValue(String value, int increment) throws Exception {
		try {
			String dValue = value;
			String sTemp = value.replaceAll("[^0-9]", "");
			value = value.replace(sTemp, "$$$");
			
			if (value.contains("$$$")) {
				long iTemp = Long.parseLong(sTemp);
				int preceedingZeros = getPreceedingZeroCount(sTemp);
				
				long vTemp = iTemp + increment;
				String temp = addPreceedingZeroCount(vTemp, preceedingZeros);
				value = value.replace("$$$", temp);
			}
			else
				FailureHelper.failTest("Generate Data Value '" + dValue + "' has number in multiple places. Increment is not supported for this type of values. Number should be continuous.");
			
			return value;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	static String getNextRandomValue(String value) throws Exception {
		try {
			String dValue = value;
			String sTemp = value.replaceAll("[^0-9]", "");
			value = value.replace(sTemp, "$$$");
			
			if (value.contains("$$$")) {
				long iTemp = Long.parseLong(sTemp);
				int preceedingZeros = getPreceedingZeroCount(sTemp);
				
				if (randomValue == -1) {
					randomValue = getRandomNumber(value);
				}
				
				long vTemp = iTemp + randomValue;
				String temp = addPreceedingZeroCount(vTemp, preceedingZeros);
				value = value.replace("$$$", temp);
			}
			else
				FailureHelper.failTest("Generate Data Value '" + dValue + "' has number in multiple places. Increment is not supported for this type of values. Number should be continuous.");
			
			return value;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static long getRandomNumber(String value) throws Exception {
		try {
			
			Random rand = new Random();
			long iValue = 0;
			
			if (recordCount <= 1000)
				iValue = rand.nextInt(1000);
			else {
				if (value != null) {
					int length = value.length();
					iValue = rand.nextInt((int) Math.pow(10, length));
				}
				else {
					iValue = rand.nextInt(recordCount);
				}
			}
			
			return iValue;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	static ArrayList<String> addHeader(String[][] data) throws Exception {
		try {
			
			ArrayList<String> actualData = new ArrayList<>();
			String temp = "";
					
			for (int j = 0; j < data.length; j++) {
				String dValue = data[j][3];
				
				if (j == 0)
					temp = dValue;
				else
					temp = temp + "," + dValue;
			}
			
			actualData.add(temp);
			actualData.add("\n");
			
			return actualData;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	static String getRecord(String[][] data, String dateFormat, int increment) throws Exception {
		try {
			
			String temp = "";
			
			for (int j = 0; j < data.length; j++) {
				String dValue = getValue(data[j][1], data[j][0], dateFormat, increment);
				
				if (j == 0)
					temp = dValue;
				else
					temp = temp + "," + dValue;
			}
			
			return temp;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}