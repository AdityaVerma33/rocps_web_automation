package com.subex.automation.helpers.component;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.componentHelpers.ImageElementHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ImageHelper extends ComponentHelper {

	/**
	 * This method checks if image is present in GUI. If not present test case will fail.
	 * @param idOrXpath - id of the image
	 * @throws Exception 
	 */
	public static boolean isPresent( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = ImageElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				return false;
			}
			else
				return true;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method checks if image is present in GUI. If not present test case will fail.
	 * @param imgWrapper - Div or Table id within which the image is present.
	 * @param idOrXpath - id of the image
	 * @throws Exception
	 */
	public static boolean isPresent( String imgWrapper, String idOrXpath ) throws Exception {
		try {
			imgWrapper = GenericHelper.getORProperty(imgWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = ImageElementHelper.getElement(imgWrapper, idOrXpath);
			
			if (element == null) {
				return false;
			}
			else
				return true;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method checks if image is not present in GUI. If present test case will fail.
	 * @param idOrXpath - id of the image
	 * @throws Exception 
	 */
	public static boolean isNotPresent( String idOrXpath ) throws Exception {
		try {
			return !isPresent(idOrXpath);
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method checks if image is not present in GUI. If present test case will fail.
	 * @param imgWrapper - Div or Table id within which the image is present.
	 * @param idOrXpath - id of the image
	 * @throws Exception
	 */
	public static boolean isNotPresent( String imgWrapper, String idOrXpath ) throws Exception {
		try {
			return !isPresent(imgWrapper, idOrXpath);
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check is icon is present in GUI
	 * @param idOrXpath - id of the image
	 * @param key - Keyword for icon like warning, error, alert, stop, etc
	 * @return 
	 * @throws Exception
	 */
	public static boolean isIconPresent( String idOrXpath, String key ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = ImageElementHelper.getIconElement(idOrXpath, key);
			
			if (element == null)
				return false;
			else
				return true;	
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check is icon is present in GUI
	 * @param wrapperId - Div or Table id within which the image is present.
	 * @param idOrXpath - id of the image
	 * @param key - Keyword for icon like warning, error, alert, stop, etc
	 * @return 
	 * @throws Exception
	 */
	public static boolean isIconPresent(String imgWrapper, String idOrXpath, String key) throws Exception {
		try {
			imgWrapper = GenericHelper.getORProperty(imgWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = ImageElementHelper.getIconElement(imgWrapper, idOrXpath, key);
			
			if (element == null)
				return false;
			else
				return true;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to click an image
	 * @param idOrXpath - id of the image
	 * @throws Exception
	 */
	public static void click( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = ImageElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Image '" + idOrXpath + "' is not found.");
			}
			else
				MouseHelper.click(element);
		}
		catch(Exception e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to click an image
	 * @param imgWrapper - Div or Table id within which the image is present.
	 * @param idOrXpath - id of the image
	 * @throws Exception
	 */
	public static void click( String imgWrapper, String idOrXpath ) throws Exception {
		try {
			imgWrapper = GenericHelper.getORProperty(imgWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = ImageElementHelper.getElement(imgWrapper, idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Image '" + idOrXpath + "' is not found.");
			}
			else
				MouseHelper.click(element);
		}
		catch(Exception e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}