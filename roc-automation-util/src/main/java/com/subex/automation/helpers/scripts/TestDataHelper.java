package com.subex.automation.helpers.scripts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.config.PropertyReader;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class TestDataHelper extends AcceptanceTest {
	
	private final String[] delimiters = {";", "|"};
	
	public String getValue(String value) throws Exception {
		try {
			String actualValue = null;
			
			if (value != null) {
				if (value.contains("$$")) {
					actualValue = getPropertyValue(value);
				}
				else
					actualValue = value;
			}
			else
				actualValue = "";
			
			if (!actualValue.contains("\\\\") && actualValue.contains("\\"))
				actualValue = actualValue.replace("\\", "\\\\");
			actualValue = convertNow(actualValue);
			return actualValue.trim();
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String[] updateValue(HashMap<String, ArrayList<String>> testData, String value, int dataNo, boolean iterateScript) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				String[] values = value.split(";", -1);
				
				if (values != null && values.length > 0 && values[0] != null) {
					values = splitValues(testData, values, dataNo, iterateScript, false);
				}
				
				return values;
			}
			else
				return null;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String updateQueryValue(HashMap<String, String[]> testData, String value, int dataNo) throws Exception {
		try {
			
			if (value.contains("$")) {
				String[] dummy = value.split("\\$", -1);
				int length = dummy.length;
				
				for (int i = 1; i < length; i+=2) {
					String parameterName = dummy[i];
					String[] tempList = testData.get(parameterName);
					
					if (tempList != null) {
						String temp = tempList[dataNo];
						value = value.replace("$" + parameterName + "$", temp);
					}
					else{
						FailureHelper.failTest("ParameterName '" + parameterName + "' is not found in TestData excel.");
					}
				}
			}
			
			return value;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public int getTestDataCount(HashMap<String, String[]> value) throws Exception {
		try {
			int length = 0;
			
			if (value != null) {
				Set<String> keySet = value.keySet();
				Object[] keys = keySet.toArray();
				
				for (int i = 0; i < value.size(); i++) {
					String[] values = value.get(keys[i]);
					
					if (values != null) {
						if (length < values.length)
							length = values.length;
					}
				}
			}
			
			return length;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String[] getStringValue(String value, String delimiter) throws Exception {
		try {
			String[] values = value.split("\\" + delimiter + "", -1);
			char[] del = delimiter.toCharArray();
			
			if (values.length == 0 && value.contains(delimiter)) {
				int count = 1;
				for (int i = 0; i < value.length(); i++) {
					if (value.charAt(i) == del[0])
						count++;
				}
				
				values = new String[count];
			}
			else {
				for (int i = 0; i < values.length; i++)
					values[i] = getValue(values[i]);
			}
			
			return values;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String[][] getStringValue(String value, String delimiter1, String delimiter2) throws Exception {
		try {
			String[] tempValues = value.split("\\" + delimiter1 + "", -1);
			String[][] values = null;
			
			if (ValidationHelper.isNotEmpty(tempValues)) {
				int length = tempValues.length;
				values = new String[length][];
				
				for (int i = 0; i < length; i++) {
					values[i] = tempValues[i].split("\\" + delimiter2 + "", -1);
				}
				
				for (int i = 0; i < values.length; i++) {
					for (int j = 0; j < values[i].length; j++)
						values[i][j] = getValue(values[i][j]);
				}
			}
			
			return values;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean[] getBooleanValue(String value, String delimiter) throws Exception {
		try {
			String[] tempValues = value.split("\\" + delimiter + "", -1);
			int length = tempValues.length;
			boolean[] values = new boolean[length];
			
			for (int i = 0; i < length; i++) {
				tempValues[i] = getValue(tempValues[i]);
				values[i] = ValidationHelper.isTrue(tempValues[i]);
			}
			
			return values;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private String getPropertyValue(String value) throws Exception {
		try {
			String actualValue = value;
			String[] dummy = value.split("\\$\\$", -1);
			int length = dummy.length;
			
			if (length == 1) {
				actualValue = getPropertyValue(value, dummy[0]);
			}
			else if (length >= 2) {
				if (value.startsWith("$$")) {
					actualValue = "";
				}
				else
					actualValue = dummy[0];
				
				for (int i = 1; i < length; i+=2) {
					String temp = getPropertyValue(value, dummy[i]);
					if (temp != null)
						actualValue = actualValue + temp;
					
					if (dummy.length > i+1 && dummy[i+1] != null)
						actualValue = actualValue + dummy[i+1];
				}
			}
			
			return actualValue;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private String getPropertyValue(String value, String valueToChange) throws Exception {
		try {
			String actualValue = null;
			actualValue = configProp.getProperty(valueToChange);
			
			if (actualValue == null) {
				Pattern p = Pattern.compile(".*\\W+.*");
				Matcher m = p.matcher(valueToChange);
				if (m.matches())
					actualValue = value;
			}
			
			return actualValue;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private String getDataValue(HashMap<String, ArrayList<String>> testData, String parameterName, int dataNo, boolean iterateScript) throws Exception {
		try {
			ArrayList<String> tempList = testData.get(parameterName);
			String actualValue = null;
			
			if (tempList != null) {
				if (!iterateScript && tempList.size() == 1)
					dataNo = 0;
				if (dataNo == 0)
					actualValue = tempList.get(dataNo);
				else {
					if (tempList.size() >= dataNo)
						actualValue = tempList.get(dataNo-1);
					else
						FailureHelper.failTest("Test Data does not have '" + dataNo + "' number of values for parameter '" + parameterName + "'");
				}
			}
			else {
				FailureHelper.failTest("ParameterName '" + parameterName + "' is not found in TestData excel.");
			}
			
			return actualValue;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private String getTestDataValue(HashMap<String, ArrayList<String>> testData, String value, int dataNo, boolean iterateScript) throws Exception {
		try {
			String actualValue = null;
			
			if (testData != null) {
				String[] dummy = value.split("\\$", -1);
				int length = dummy.length;
				
				if (length == 1) {
					actualValue = getDataValue(testData, dummy[0], dataNo, iterateScript);
				}
				else if (length >= 2) {
					if (value.startsWith("$")) {
						actualValue = "";
					}
					else
						actualValue = dummy[0];
					
					for (int i = 1; i < length; i+=2) {
						String temp = getDataValue(testData, dummy[i], dataNo, iterateScript);
						if (temp != null)
							actualValue = actualValue + temp;
						
						if (dummy.length > i+1 && dummy[i+1] != null)
							actualValue = actualValue + dummy[i+1];
					}
				}
			}
			else
				actualValue = "";
			
			return actualValue;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private String getActualValue(HashMap<String, ArrayList<String>> testData, String value, int dataNo, boolean iterateScript, boolean dataGeneration) throws Exception {
		try {
			String actualValue = value;
			
			if (ValidationHelper.isNotEmpty(value)) {
				for (int i = 0; i < 3; i++) {
					if (actualValue.contains("$$")) {
						actualValue = getPropertyValue(actualValue);
					}
					else if (actualValue.contains("$") && ValidationHelper.isNotEmpty(testData)) {
						actualValue = getTestDataValue(testData, actualValue, dataNo, iterateScript);
					}
				}
				
				if (!dataGeneration) {
					actualValue = convertNow(actualValue);
				}
			}
			
			return actualValue;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private String convertNow(String actualValue) throws Exception {
		try {
			String dateFormat = configProp.getDateFormat();
			String currentNowDate = configProp.getCurrentNowDateTime();
			
			if (actualValue.startsWith("NOW") || actualValue.endsWith("NOW"))
			{
				if (currentNowDate.equals(""))
				{
					String temp = DateHelper.getCurrentDateTime(configProp.getDateTimeFormat());
					actualValue = actualValue.replace("NOW", temp);
				
					FileHelper.updatePropertyFile(configFile, "currentNowDateTime", temp);
					configProp = new PropertyReader( configFile );
				}
				else {
					actualValue = actualValue.replace("NOW", currentNowDate);
				}
			}
			else if (actualValue.startsWith("TODAY") || actualValue.endsWith("TODAY")) {
				if (currentNowDate.equals(""))
				{
					String temp = DateHelper.getCurrentDate(dateFormat) + " 00:00:00";
					actualValue = actualValue.replace("TODAY", temp);
				
					FileHelper.updatePropertyFile(configFile, "currentNowDateTime", temp);
					configProp = new PropertyReader( configFile );
				}
				else {
					actualValue = actualValue.replace("TODAY", currentNowDate);
				}
			}
			else if (actualValue.startsWith("DAYBEFOREYESTERDAY") || actualValue.endsWith("DAYBEFOREYESTERDAY")) {
				String temp = null;
				
				if (currentNowDate.equals("")) {
					temp = DateHelper.getDate(dateFormat, 2) + " 00:00:00";
				}
				else {
					temp = DateHelper.subtractDate(dateFormat, currentNowDate, 2);
				}
				
				actualValue = actualValue.replace("DAYBEFOREYESTERDAY", temp);
			}
			else if (actualValue.startsWith("YESTERDAY") || actualValue.endsWith("YESTERDAY")) {
				String temp = null;
				
				if (currentNowDate.equals("")) {
					temp = DateHelper.getDate(dateFormat, 1) + " 00:00:00";
				}
				else {
					temp = DateHelper.subtractDate(dateFormat, currentNowDate, 1);
				}
				
				actualValue = actualValue.replace("YESTERDAY", temp);
			}
			
			return actualValue;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private String[] splitValues(HashMap<String, ArrayList<String>> testData, String[] values, int dataNo, boolean iterateScript, boolean dataGeneration) throws Exception {
		try {
			for (int i = 0; i < values.length; i++) {
				String temp1 = getActualValue(testData, values[i], dataNo, iterateScript, dataGeneration);
				boolean converted = false;
				
				for (int x = 0; x < delimiters.length; x++) {
					if (temp1.contains(delimiters[x])) {
						String[] dummy = temp1.split("\\" + delimiters[x], -1);
						
						if (ValidationHelper.isNotEmpty(dummy)) {
							String dValue = "";
							
							for (int j = 0; j < dummy.length; j++) {
								String temp2 = getActualValue(testData, dummy[j], dataNo, iterateScript, dataGeneration);
								if (j == 0)
									dValue = temp2;
								else
									dValue = dValue + delimiters[x] + temp2;
							}
							
							values[i] = dValue;
							converted = true;
						}
					}
				}
				
				if (!converted) {
					values[i] = getActualValue(testData, temp1, dataNo, iterateScript, dataGeneration);
				}
			}
			
			return values;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public int[] getIntegerValue(String value, String delimiter) throws Exception {
		try {
			int[] values = null;
			String[] tempValues = value.split("\\" + delimiter + "", -1);
			char[] del = delimiter.toCharArray();
			
			if (tempValues.length == 0 && value.contains(delimiter)) {
				int count = 1;
				for (int i = 0; i < value.length(); i++) {
					if (value.charAt(i) == del[0])
						count++;
				}
				
				values = new int[count];
			}
			else {
				values = new int[tempValues.length];
				
				for (int i = 0; i < tempValues.length; i++)
					values[i] = Integer.parseInt(getValue(tempValues[i]));
			}
			
			return values;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public int[][] getIntegerValue(String value, String delimiter1, String delimiter2) throws Exception {
		try {
			int[][] values = null;
			String[] tempValues = value.split("\\" + delimiter1 + "", -1);
			
			if (tempValues.length == 0 && value.contains(delimiter1)) {
				char[] del1 = delimiter1.toCharArray();
				char[] del2 = delimiter2.toCharArray();
				int count1 = 1;
				int count2 = 1;
				int length = 1;
				
				for (int i = 0; i < value.length(); i++) {
					char val = value.charAt(i);
					
					if (val == del1[0]) {
						count1++;
						count2= length;
						length = 1;
					}
					else if (val == del2[0]) {
						length++;
					}
				}
				
				values = new int[count1][count2];
			}
			else {
				values = new int[tempValues.length][];
				
				for (int i = 0; i < tempValues.length; i++) {
					String[] tempValue = value.split("\\" + delimiter2 + "", -1);
					
					if (tempValues.length == 0 && value.contains(delimiter2)) {
						char[] del = delimiter2.toCharArray();
						int count = 1;
						for (int j = 0; j < value.length(); j++) {
							if (value.charAt(j) == del[0])
								count++;
						}
						
						values[i] = new int[count];
					}
					else {
						for (int j = 0; j < tempValue.length; j++) {
							values[i][j] = Integer.parseInt(getValue(tempValue[i]));
						}
					}
				}
			}
			
			return values;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public int getIntegerValue(String value) throws Exception {
		try {
			String actualValue = getValue(value);
			
			if (ValidationHelper.isEmpty(actualValue))
				return 0;
			else
				return Integer.parseInt(actualValue);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}