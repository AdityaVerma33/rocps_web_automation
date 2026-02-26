package com.subex.rocps.automation.utils;

import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ExcelHolder {

	Map<String, ArrayList<String>> map;

	public ExcelHolder(Map<String, ArrayList<String>> map) {
		this.map = map;
	}

	/*
	 * @Method : for identifying total value columns in the map : map key =
	 * string and value = ArrayList<String>
	 */
	public int totalColumns() {
		int totalColumns = 0;
		for (Entry<String, ArrayList<String>> entry : map.entrySet()) {
			if (!entry.getValue().isEmpty()) {
				if (totalColumns < entry.getValue().size())
					totalColumns = entry.getValue().size();
			}
		}
		return totalColumns;

	}

	/*
	 * @Method for checking the mandatory values in a cell in excel sheet Not
	 * value is assigned in excel against key then exception is thrown
	 * 
	 * @Param map : Map should be of key: String and value : String
	 * 
	 * @Param key : pass the map key
	 */
	public String getValue(Map<String, String> dataMap, String key) throws Exception {
		String value = dataMap.get(key);
		if (value.isEmpty())
			throw new Exception(new NullPointerException("value is not assigned against field : " + key));
		return value;
	}

	/*
	 * Method : Read the excel Data and returns map
	 * 
	 * @Param map : where key: string and value: arraylist of strings
	 * 
	 * @Param index : colVal is passed
	 */
	public Map<String, String> dataMap(int index) {
		Map<String, String> hmap = new HashMap<>();
		String key;
		String value;

		for (Entry<String, ArrayList<String>> entry : map.entrySet()) {
			key = entry.getKey();
			value = entry.getValue().get(index);

			hmap.put(key, value);

		}
		return hmap;

	}

	/*
	 * @Method: checks key passed is used in code
	 * 
	 * @Param map : Map should be of key: String and value : String
	 * 
	 * @Param key : pass the map key
	 */
	public static String getKey(Map<String, String> dataMap, String key) throws Exception {
		String value;
		if (!dataMap.containsKey(key))
			throw new IllegalArgumentException("Key not found in excel: " + key);
		else
			value = dataMap.get(key);

		return value;
	}

}
