package com.subex.automation.helpers.component;

import com.subex.automation.helpers.selenium.AcceptanceTest;

public abstract class ComponentHelper extends AcceptanceTest {
	public static boolean isPresent( String idOrXpath) throws Exception {
		return false;
	}
	
	public static boolean isPresent( String wrapper, String idOrXpath) throws Exception {
		return false;
	}
	
	public static boolean isEnabled( String idOrXpath ) throws Exception {
		return false;
	}
	
	public static boolean isEnabled( String wrapper, String idOrXpath ) throws Exception {
		return false;
	}
	
	public static boolean isDisabled( String idOrXpath ) throws Exception {
		return false;
	}
	
	public static boolean isDisabled(String buttonWrapper, String idOrXpath ) throws Exception {
		return false;
	}
}