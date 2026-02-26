package com.subex.automation.helpers.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class StringHelper extends AcceptanceTest {
	
	public static String[] resizeStringArray(String[] stringArray) throws Exception {
		try {
			int count = 0;
			for (int i = 0; i < stringArray.length; i++) {
				if (stringArray[i] != null) {
					count++;
				}
			}
			
			String[] dummy = new String[count];
			System.arraycopy(stringArray, 0, dummy, 0, count);
			stringArray = dummy;
			
			return stringArray;
		}
		catch(Exception e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[][] resizeStringArray(String[][] stringArray) throws Exception {
		try {
			int firstCount = 0;
			for (int i = 0; i < stringArray.length; i++) {
				if (stringArray[i] != null && stringArray[i][0] != null) {
					firstCount++;
				}
			}
			
			String[][] dummy = new String[firstCount][];
			System.arraycopy(stringArray, 0, dummy, 0, firstCount);
			stringArray = dummy;
			
			for (int i = 0; i < firstCount; i++) {
				stringArray[i] = resizeStringArray(stringArray[i]);
			}
			
			return stringArray;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[][] resizeStringArray(String[][] stringArray, int length) throws Exception {
		try {
			String[][] dummy = new String[length][stringArray[0].length];
			if (ValidationHelper.isNotEmpty(stringArray)) {
				dummy = Arrays.copyOf(stringArray, length);
//				System.arraycopy(stringArray, 0, dummy, 0, stringArray.length);
			}
			stringArray = dummy;
			
			return stringArray;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[][] resizeStringArray(String[][] stringArray, int firstLength, int secondLength) throws Exception {
		try {
			if (firstLength == 0)
				firstLength = 1;
			String[][] dummy = new String[firstLength][secondLength];
			if (ValidationHelper.isNotEmpty(stringArray)) {
				System.arraycopy(stringArray, 0, dummy, 0, stringArray.length);
			}
			stringArray = dummy;
			
			return stringArray;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[] resizeStringArray(String[] stringArray, int length) throws Exception {
		try {
			String[] dummy = new String[length];
			if (stringArray != null && stringArray.length > 0)
				dummy = Arrays.copyOf(stringArray, length);
			stringArray = dummy;
			
			return stringArray;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[] resizeStringArray(String[] stringArray, int length, String nullValue) throws Exception {
		try {
			String[] dummy = new String[length];
			if (stringArray != null && stringArray.length > 0)
				dummy = Arrays.copyOf(stringArray, length);
			stringArray = dummy;
			
			for (int i = 0; i < stringArray.length; i++) {
				if (stringArray[i] == null)
					stringArray[i] = nullValue;
			}
			
			return stringArray;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static Object[] resizeObjectArray(Object[] objectArray) throws Exception {
		try {
			int count = 0;
			for (int i = 0; i < objectArray.length; i++) {
				if (objectArray[i] != null) {
					count++;
				}
			}
			
			Object[] dummy = new String[count];
			if (objectArray != null && count > 0)
				dummy = Arrays.copyOf(objectArray, count);
			objectArray = dummy;
			
			return objectArray;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static Object[] resizeObjectArray(Object[] objectArray, int length) throws Exception {
		try {
			Object[] dummy = new String[length];
			if (objectArray != null && objectArray.length > 0)
				dummy = Arrays.copyOf(objectArray, length);
			objectArray = dummy;
			
			return objectArray;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[][] convertTo2DArray(String value, String delimiter1, String delimiter2) throws Exception {
		try {
			if(ValidationHelper.isEmpty(value))
				return new String[1][1];
			
			String [] splitColumn = value.replace("\n", "").split("\\"+delimiter1+"", -1);
			String [][] returnColumn = new String[splitColumn.length][];
			for(int m = 0; m < splitColumn.length ; m++)
			{
				returnColumn[m] = splitColumn[m].trim().split(delimiter2, -1);
			}
			return returnColumn;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String removeConsecutiveDuplicate(String value) throws Exception {
		try {
			value = value + " ";
			String finalValue = "";
			int count = value.length();
			
			for (int i = 0; i < count-1; i++) {
				if (value.charAt(i)!=value.charAt(i+1))
					finalValue = finalValue + value.charAt(i); 
			}
			
			return finalValue;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[][] sortArray(String[][] array) throws Exception {
		try {
			int length = array.length;
			
			for (int i = 0; i < length; i++) {
				int aValue = Integer.parseInt(array[i][0]);
				int lValue = 0;
				int index = 0;
				int count = 0;
				
				for (int j = i+1; j < length; j++) {
					int eValue = Integer.parseInt(array[j][0]);
					
					if (eValue < aValue) {
						if (count == 0) {
							lValue = eValue;
							index = j;
							count++;
						}
						else {
							if (eValue < lValue) {
								lValue = eValue;
								index = j;
							}
						}
					}
				}
				
				if (count > 0) {
					String[] temp = array[index];
					array[index] = array[i];
					array[i] = temp;
				}
			}
			
			return array;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static ArrayList<String> sortAscending(ArrayList<String> values) throws Exception {
		try {
			Collections.sort(values);
			return values;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static ArrayList<String> sortDescending(ArrayList<String> values) throws Exception {
		try {
			Collections.sort(values, Collections.reverseOrder());
			return values;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int searchArray(String[] array, String searchValue) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(array)) {
				array = resizeStringArray(array);
				
				for (int i = 0; i < array.length; i++) {
					if (array[i] != null && array[i].equals(searchValue))
						return i;
				}
			}
			
			return -1;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int searchArray(Object[] array, String searchValue) throws Exception {
		try {
			array = resizeObjectArray(array);
			
			for (int i = 0; i < array.length; i++) {
				if (array[i] != null) {
					String temp = array[i].toString();
					if (temp.equals(searchValue))
						return i;
				}
			}
			
			return -1;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int arrayContains(String[] array, String searchValue) throws Exception {
		try {
			
			for (int i = 0; i < array.length; i++) {
				if (array[i] != null && (array[i].equalsIgnoreCase(searchValue) || array[i].contains(searchValue)))
					return i;
			}
			
			return -1;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int arrayContains(String[][] array, String searchValue, int index) throws Exception {
		try {
			
			for (int i = 0; i < array.length; i++) {
				if (array[i] != null && array[i].length > 0 && (array[i][index].equalsIgnoreCase(searchValue) || array[i][index].contains(searchValue) || searchValue.contains(array[i][index])))
					return i;
			}
			
			return -1;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int countChar(String string, String searchChar) throws Exception {
		try {
			
			int count = 0;
			while (string.contains(searchChar)) {
				string = string.replace(searchChar, "");
				count++;
			}
			
			return count;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String convertToCamelCase(String value) throws Exception {
		try {
			
			value = value.substring(0, 1).toUpperCase() + value.substring(1);
			
			return value;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int searchArray(String[][] array, String searchValue, int indexNo) throws Exception {
		try {
			array = resizeStringArray(array);
			
			for (int i = 0; i < array.length; i++) {
				if (array[i] != null && array[i].length >= (indexNo-1) && array[i][indexNo].equals(searchValue))
					return i;
			}
			
			return -1;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int searchArrayBackwards(String[][] array, String searchValue, int indexNo) throws Exception {
		try {
			array = resizeStringArray(array);
			
			if (array != null && array.length > 0) {
				for (int i = array.length-1; i > -1; i--) {
					if (array[i] != null && array[i].length >= (indexNo-1) && array[i][indexNo].equals(searchValue))
						return i;
				}
			}
			
			return -1;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[] removeDuplicates(String[] array) throws Exception {
		try {
			int length = array.length;
			String[] dummy = new String[length];
			int count = 0;
			
			for (int i = 0; i < length; i++) {
				int index = searchArray(dummy, array[i]);
				if (index == -1) {
					dummy[count++] = array[i];
				}
			}
			
			dummy = resizeStringArray(dummy, count);
			return dummy;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String convertToString(String[] stringArray, String delimiter) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(stringArray)) {
				String value = stringArray[0];
				for (int x = 1; x < stringArray.length; x++) {
					value = value + delimiter + stringArray[x];
				}
				
				return value;
			}
			else
				return null;
		}
		catch(Exception e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[] removeValue(String[] array, String valueToRemove) throws Exception {
		try {
			int length = array.length;
			String[] dummy = new String[length];
			int count = 0;
			
			for (int i = 0; i < length; i++) {
				if (!array[i].equals(valueToRemove))
					dummy[count++] = array[i];
			}
			
			dummy = resizeStringArray(dummy, count);
			return dummy;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int[] convertArrayToInt(String[] stringArray) throws Exception {
		if (stringArray != null && stringArray.length > 0 && stringArray[0] != null) {
			int[] intArray = new int[stringArray.length];
			for (int x = 0; x < stringArray.length; x++) {
				intArray[x] = Integer.parseInt(stringArray[x]);
			}
			return intArray;
		}
		else
			return null;
	}
	
	public static ArrayList<String> convertToArrayList(String[] array) throws Exception {
		try {
			ArrayList<String> value = new ArrayList<>();
			int length = array.length;
			
			for (int i = 0; i < length; i++) {
				value.add(i, array[i]);
			}
			
			return value;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static List<String> getKeySet(HashMap<String, ArrayList<String>> data) throws Exception {
		try {
			Set<String> keyset = data.keySet();
			List<String> lKeyset = new ArrayList<>(keyset);
			Collections.sort(lKeyset);
			
			return lKeyset;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}