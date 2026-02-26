package com.subex.automation.helpers.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.select.Elements;

import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class ValidationHelper extends AcceptanceTest {

	public static String checkProduct(String product) throws Exception {
		try {
			String actualProduct = null;
			product = product.toUpperCase();
			
			switch (product) {
			case "ROC RA":
			case "ROCRA":
			case "RA":
				actualProduct = "RA";
				break;

			case "ROC":
			case "Spark":
				actualProduct = "ROC";
				break;
				
			case "ROC FM":
			case "ROCFM":
			case "FM":
				actualProduct = "FM";
				break;
				
			case "ROC PS":
			case "ROCPS":
			case "PS":
				actualProduct = "PS";
				break;
				
			default:
				actualProduct = "ROC";
				break;
			}
			
			return actualProduct;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isInteger(String value) throws Exception {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isBoolean(String value) throws Exception {
		try {
			if (value != null) {
				String[] expectedValues = {"FALSE", "N", "NO", "TRUE", "Y", "YES"};
				Arrays.sort(expectedValues);
				int index = Arrays.binarySearch(expectedValues, value.toUpperCase());
				
				if (index >= 0) {
					return true;
				}
				else
					return false;
			}
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isTrue(String value) throws Exception {
		try {
			if (value != null) {
				String[] expectedValues = {"TRUE", "Y", "YES"};
				Arrays.sort(expectedValues);
				int index = Arrays.binarySearch(expectedValues, value.toUpperCase());
				
				if (index >= 0) {
					return true;
				}
				else
					return false;
			}
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isFalse(String value) throws Exception {
		try {
			if (value != null) {
				String[] expectedValues = {"FALSE", "N", "NO"};
				Arrays.sort(expectedValues);
				int index = Arrays.binarySearch(expectedValues, value.toUpperCase());
				
				if (index >= 0) {
					return true;
				}
				else
					return false;
			}
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isDirectory(String value) throws Exception {
		try {
			if (value.startsWith("/") || value.substring(1, 2).equals(":")) {
				return true;
			}
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isEmpty(String value) throws Exception {
		try {
			if (value == null || value.trim().equals(""))
				return true;
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isEmpty(String[] value) throws Exception {
		try {
			if (value == null || value.length == 0 || value[0] == null)
				return true;
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isEmpty(String[][] value) throws Exception {
		try {
			if (value == null || value.length == 0 || value[0] == null || value[0].length == 0 || value[0][0] == null)
				return true;
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isEmpty(int[] value) throws Exception {
		try {
			if (value == null || value.length == 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isEmpty(ArrayList<String> value) throws Exception {
		try {
			if (value == null || value.size() == 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isEmpty(Map<Integer, ArrayList<String>> value) throws Exception {
		try {
			if (value == null || value.size() == 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isNotEmpty(String value) throws Exception {
		try {
			if (isEmpty(value))
				return false;
			else
				return true;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isNotEmpty(String[] value) throws Exception {
		try {
			if (value != null && value.length > 0 && value[0] != null && !value[0].equals(""))
				return true;
			else {
				if (value != null && value.length > 1)
					return true;
				else
					return false;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isNotEmpty(String[][] value) throws Exception {
		try {
			if (isEmpty(value))
				return false;
			else
				return true;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isNotEmpty(int[] value) throws Exception {
		try {
			if (value != null && value.length > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isNotEmpty(HashMap<String, ArrayList<String>> value) throws Exception {
		try {
			if (value != null && value.size() > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isNotEmpty(Map<Integer, ArrayList<String>> value) throws Exception {
		try {
			if (isEmpty(value))
				return false;
			else
				return true;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isNotEmpty(ArrayList<String> value) throws Exception {
		try {
			if (value != null && value.size() > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isNotEmpty(Elements value) throws Exception {
		try {
			if (value != null && value.size() > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isNotEmpty(boolean[][] value) throws Exception {
		try {
			if (value != null && value.length > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isReferenceDBDetailsEmpty() throws Exception {
		try {
			String dbType = configProp.getDbType();
			String dbHost = configProp.getDbMachineName();
			String dbUsername = configProp.getDbUserName();
			String dbPassword = configProp.getDbPassword();
			String dbDatabase = configProp.getDB();
			String dbInstance = configProp.getDbInstance();
			String dbPort = configProp.getDBPortNumber();
			String[] dbDetails = {dbType, dbHost, dbUsername, dbPassword, dbDatabase, dbInstance, dbPort};
			
			if (isEmpty(dbDetails))
				return true;
			else {
				for (int i = 0; i < dbDetails.length; i++) {
					if (dbDetails[i] == null)
						return true;
				}
			}
			return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}