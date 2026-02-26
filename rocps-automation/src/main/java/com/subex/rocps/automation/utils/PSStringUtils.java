package com.subex.rocps.automation.utils;

import java.util.List;
import java.util.regex.Pattern;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class PSStringUtils extends PSAcceptanceTest {

	String firstLevelDelimeter = null;
	String secondLevelDelimeter = null;
	String thirdLevelDelimeter = null;

	/*
	 * Method: FirstLevel split in string
	 */
	public String[] stringSplitFirstLevel(String name) throws Exception {
		String regex = regexFirstLevelDelimeter();
		String[] strArr = name.split(regex, -1);
		return strArr;
	}

	/*
	 * Method: SecondLevel split in string
	 */
	public String[] stringSplitSecondLevel(String name) throws Exception {
		String regex = regexSecondLevelDelimeter();
		String[] strArr = name.split(regex, -1);
		return strArr;
	}

	/*
	 * Method: ThirdLevel split in string
	 */
	public String[] stringSplitThirdLevel(String name) throws Exception {
		String regex = regexThirdLevelDelimeter();
		String[] strArr = name.split(regex, -1);
		return strArr;
	}

	/*
	 * Method: returning firstLevelDelimeter
	 */
	public String regexFirstLevelDelimeter() throws Exception {
		firstLevelDelimeter = configProp.getFirstLevelDelimiter();
		return "\\" + firstLevelDelimeter;
	}

	/*
	 * Method: returning secondLevelDelimeter
	 */
	public String regexSecondLevelDelimeter() throws Exception {
		secondLevelDelimeter = configProp.getSecondLevelDelimiter();
		return "\\" + secondLevelDelimeter;
	}

	/*
	 * Method: returning thirdLevelDelimeter
	 */
	public String regexThirdLevelDelimeter() throws Exception {
		thirdLevelDelimeter = configProp.getThirdLevelDelimiter();
		return "\\" + thirdLevelDelimeter;
	}
	
	/*
	 * This method is for string formation
	 */
	public String stringformation( List<String> strLstObj )
	{
		List<String> strObj = strLstObj;
		StringBuilder stringBuildObj = new StringBuilder();
		for ( int index = 0; index < strObj.size(); index++ )
		{
			stringBuildObj.append( strObj.get( index ).trim() ).append( index == strObj.size() - 1 ? "" : ";" );

		}

		return stringBuildObj.toString();
	}

}
