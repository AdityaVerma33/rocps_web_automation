package com.subex.automation.helpers.component;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.componentHelpers.CheckBoxElementHelper;
import com.subex.automation.helpers.componentHelpers.ComboBoxElementHelper;
import com.subex.automation.helpers.componentHelpers.LocatorHelper;
import com.subex.automation.helpers.componentHelpers.PropertyGridElementHelper;
import com.subex.automation.helpers.componentHelpers.TextBoxElementHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.util.FailureHelper;


public class PropertyGridHelper extends ComponentHelper {
	
	private static WebElement wrapperElement = null;
	private static boolean useWrapper = false;
	final private static String textBoxKey = "PropertyGrid_TextBox";
	final private static String comboBoxKey1 = "PropertyGrid_Dropdown_1";
	final private static String comboBoxKey2 = "PropertyGrid_Dropdown_2";
	final private static String checkBoxKey = "PropertyGrid_CheckBox";
	final private static String datePickerKey = "PropertyGrid_Date";
	final private static String dataDirKey = "PropertyGrid_DataDir_Icon";
	final private static String dataDirInputKey = "PropertyGrid_DataDir";
	
	/**
	 * This method is used to check if Property Grid is present in GUI
	 * If not present, test case will fail.
	 * @param gridId - id of the Property Grid
	 * @throws Exception 
	 */
	public static boolean isPresent(String gridId) throws Exception {
		try {
			return GridHelper.isPresent(gridId);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if Property Grid is present in GUI
	 * If not present, test case will fail.
	 * @param gridWrapper - Div or Table id within which the Property Grid is present.
	 * @param gridId - id of the Property Grid
	 * @throws Exception 
	 */
	public static boolean isPresent( String gridWrapper, String gridId ) throws Exception {
		try {
			return GridHelper.isPresent(gridWrapper, gridId);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static int scroll(String propertyName) throws Exception {
		try {
			int index = scroll("Dialog_Wrapper", propertyName);
			
			return index;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static int scroll(String wrapperId, String propertyName) throws Exception {
		try {
			wrapperId = GenericHelper.getORProperty(wrapperId);
			int index = scroll(wrapperId, propertyName, 5);
			
			return index;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static int scroll(String wrapperId, String propertyName, int indexForScroll) throws Exception {
		try {
			wrapperElement = PropertyGridElementHelper.getWrapperElement(wrapperId);
    		if (wrapperElement != null)
    			useWrapper = true;
    		else
    			useWrapper = false;
    		int index = PropertyGridElementHelper.getPropertNames(propertyName, useWrapper, wrapperId);
			
    		if (index < 0) {
				FailureHelper.failTest("Property '" + propertyName + "' not found.");
			}
    		else {
				if (index > indexForScroll) {
					if (useWrapper) {
						scrollOneLevelDownPropertyGrid(wrapperId, "property", 1, 1);
					}
					else
						scrollOneLevelDownPropertyGrid("property", 1, 1);
				}
    		}
			
			return index;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to type value into Text Box present in Property Grid
	 * @param propertyName - Property Name as seen in GUI
	 * @param propertyValue - Value to be types in the Text Box
	 * @throws Exception
	 */
	public static void typeInTextBox( String propertyName, String propertyValue ) throws Exception {
    	try {
    		if (ValidationHelper.isNotEmpty(propertyValue)) {
    			propertyName = GenericHelper.getORProperty(propertyName);
	    		scroll(propertyName);
	    		WebElement element = PropertyGridElementHelper.getComponentElement(wrapperElement, textBoxKey, propertyName);
				
				if (element == null) {
					String[] orKeys = {"PropertyGrid_TextBox1", "PropertyGrid_TextBox2"};
					element = PropertyGridElementHelper.getComponentElement(wrapperElement, orKeys, propertyName);
				}
				
				if (element != null) {
					TextBoxElementHelper.typeInPropertyGridBox(element, propertyName, propertyValue);
					clickProperty(propertyName);
				}
				else {
					FailureHelper.failTest("Text box not found for Property '" + propertyName + "'");
				}
    		}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void typeInTextBox( String wrapperId, String propertyName, String propertyValue ) throws Exception {
    	try {
    		if (ValidationHelper.isNotEmpty(propertyValue)) {
    			wrapperId = GenericHelper.getORProperty(wrapperId);
    			propertyName = GenericHelper.getORProperty(propertyName);
	    		scroll(wrapperId, propertyName);
	    		WebElement element = PropertyGridElementHelper.getComponentElement(wrapperElement, textBoxKey, propertyName);
				
				if (element == null) {
					String[] orKeys = {"PropertyGrid_TextBox1", "PropertyGrid_TextBox2"};
					element = PropertyGridElementHelper.getComponentElement(wrapperElement, orKeys, propertyName);
				}
				
				if (element != null) {
					TextBoxElementHelper.typeInPropertyGridBox(element, propertyName, propertyValue);
					clickProperty(wrapperId, propertyName);
				}
				else {
					FailureHelper.failTest("Text box not found for Property '" + propertyName + "'");
				}
    		}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/** This method is used to select value in Combo Box present in Property Grid
	 * @param propertyName - Property Name as seen in GUI
	 * @param propertyValue - Value to be types in the Text Box
	 * @throws Exception
	 */
	public static void selectInComboBox( String propertyName, String propertyValue ) throws Exception {
    	try {
    		if (ValidationHelper.isNotEmpty(propertyValue)) {
    			propertyName = GenericHelper.getORProperty(propertyName);
    			scroll(propertyName);
	    		String locator = PropertyGridElementHelper.getComponentLocator(comboBoxKey1, propertyName, useWrapper);
				
				if (locator == null) {
					locator = PropertyGridElementHelper.getComponentLocator(comboBoxKey2, propertyName, useWrapper);
					
					if (locator == null) {
						String[] orKeys = {"PropertyGrid_Dropdown2", "PropertyGrid_Dropdown1"};
						locator = PropertyGridElementHelper.getComponentLocator(orKeys, propertyName, useWrapper);
						
						if (locator != null) {
							ComboBoxHelper.select(locator, "gwt_uid", propertyValue);
						}
						else {
							FailureHelper.failTest("Combo box not found for Property '" + propertyName + "'");
						}
					}
					else {
						ComboBoxHelper.select(locator, propertyValue);
					}
				}
				else {
					ComboBoxHelper.select(locator, propertyValue);
				}
    		}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
    
    public static void selectInComboBox( String wrapperId, String propertyName, String propertyValue ) throws Exception {
    	try {
    		if (ValidationHelper.isNotEmpty(propertyValue)) {
    			wrapperId = GenericHelper.getORProperty(wrapperId);
    			propertyName = GenericHelper.getORProperty(propertyName);
    			scroll(wrapperId, propertyName);
	    		String locator = PropertyGridElementHelper.getComponentLocator(comboBoxKey1, propertyName, wrapperId);
				
				if (locator == null) {
					locator = PropertyGridElementHelper.getComponentLocator(comboBoxKey2, propertyName, wrapperId);
					
					if (locator == null) {
						String[] orKeys = {"PropertyGrid_Dropdown2", "PropertyGrid_Dropdown1"};
						locator = PropertyGridElementHelper.getComponentLocator(orKeys, propertyName, wrapperId);
						
						if (locator != null) {
							ComboBoxHelper.select(locator, "gwt_uid", propertyValue);
						}
						else {
							FailureHelper.failTest("Combo box not found for Property '" + propertyName + "'");
						}
					}
					else {
						ComboBoxHelper.select(locator, propertyValue);
					}
				}
				else {
					ComboBoxHelper.select(locator, propertyValue);
				}
    		}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
    
    /**
     * This method is used to click Check Box present in Property Grid.
     * @param propertyName - Property Name as seen in GUI
     * @param propertyValue - True to check the check box and False to un-check it
     * @throws Exception
     */
	public static void clickCheckBox( String propertyName, String propertyValue ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(propertyValue)) {
    			propertyName = GenericHelper.getORProperty(propertyName);
    			scroll(propertyName);
	    		WebElement element = PropertyGridElementHelper.getComponentElement(wrapperElement, checkBoxKey, propertyName);
				
				if (element == null) {
					String[] orKeys = {"PropertyGrid_CheckBox1", "PropertyGrid_CheckBox2"};
					element = PropertyGridElementHelper.getComponentElement(wrapperElement, orKeys, propertyName);
				}
				
				if (element != null) {
					if (ValidationHelper.isTrue(propertyValue))
						CheckBoxElementHelper.check(element);
					else
						CheckBoxElementHelper.uncheck(element);
				}
				else {
					FailureHelper.failTest("Check box not found for Property '" + propertyName + "'");
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
    
    public static void clickCheckBox( String wrapperId, String propertyName, String propertyValue ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(propertyValue)) {
				wrapperId = GenericHelper.getORProperty(wrapperId);
    			propertyName = GenericHelper.getORProperty(propertyName);
    			scroll(wrapperId, propertyName);
	    		WebElement element = PropertyGridElementHelper.getComponentElement(wrapperElement, checkBoxKey, propertyName);
				
				if (element == null) {
					String[] orKeys = {"PropertyGrid_CheckBox1", "PropertyGrid_CheckBox2"};
					element = PropertyGridElementHelper.getComponentElement(wrapperElement, orKeys, propertyName);
				}
				
				if (element != null) {
					if (ValidationHelper.isTrue(propertyValue))
						CheckBoxElementHelper.check(element);
					else
						CheckBoxElementHelper.uncheck(element);
				}
				else {
					FailureHelper.failTest("Check box not found for Property '" + propertyName + "'");
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
    
    /**
     * This method is used to check Check Box present in Property Grid.
     * @param propertyName - Property Name as seen in GUI
     * @throws Exception
     */
    public static void checkCheckBox( String propertyName ) throws Exception {
		try {
			clickCheckBox(propertyName, "true");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
    
    /**
     * This method is used to un-check Check Box present in Property Grid.
     * @param propertyName - Property Name as seen in GUI
     * @throws Exception
     */
    public static void unCheckCheckBox( String propertyName ) throws Exception {
		try {
			clickCheckBox(propertyName, "false");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
    
    /**
     * This method is used to type date in Date text box present in Property Grid
     * @param propertyName - Property Name as seen in GUI
     * @param propertyValue - Value to be typed into the Text Box
     * @throws Exception
     */
    public static void typeInDatePicker( String propertyName, String propertyValue ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(propertyValue)) {
    			propertyName = GenericHelper.getORProperty(propertyName);
    			scroll(propertyName);
	    		WebElement element = PropertyGridElementHelper.getComponentElement(wrapperElement, datePickerKey, propertyName);
				
				if (element == null) {
					String[] orKeys = {"PropertyGrid_Date1", "PropertyGrid_Date2"};
					element = PropertyGridElementHelper.getComponentElement(wrapperElement, orKeys, propertyName);
				}
				
				String locator = LocatorHelper.getLocator(element);
				TextBoxElementHelper.typeInBox(element, locator, propertyValue);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
    
    public static void typeInDatePicker( String wrapperId, String propertyName, String propertyValue ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(propertyValue)) {
				wrapperId = GenericHelper.getORProperty(wrapperId);
    			propertyName = GenericHelper.getORProperty(propertyName);
    			scroll(wrapperId, propertyName);
	    		WebElement element = PropertyGridElementHelper.getComponentElement(wrapperElement, datePickerKey, propertyName);
				
				if (element == null) {
					String[] orKeys = {"PropertyGrid_Date1", "PropertyGrid_Date2"};
					element = PropertyGridElementHelper.getComponentElement(wrapperElement, orKeys, propertyName);
				}
				
				String locator = LocatorHelper.getLocator(element);
				TextBoxElementHelper.typeInBox(element, locator, propertyValue);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
    
    /**
     * This method is used to fill popup window with token information like in Local File Source > Token and Relative Path.
     * @param propertyName - Property Name as seen in GUI
     * @param propertyValue - Value to be typed in the pop-up ; separated
     * @throws Exception
     */
    public static void typeInDataDir( String propertyName, String propertyValue ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(propertyValue)) {
    			typeInDataDir(propertyName, propertyValue, ";");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
    
    public static void typeInDataDir( String propertyName, String propertyValue, String delimiter ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(propertyValue)) {
    			propertyName = GenericHelper.getORProperty(propertyName);
    			scroll(propertyName);
	    		
    			WebElement element = PropertyGridElementHelper.getComponentElement(wrapperElement, dataDirKey, propertyName);
				MouseHelper.click(element);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				TestDataHelper testData = new TestDataHelper();
				String[] propValue = testData.getStringValue(propertyValue, delimiter);
				GenericHelper.selectDataDir(propValue);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
    
    public static void typeInDataDir( String wrapperId, String propertyName, String propertyValue, String delimiter ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(propertyValue)) {
				wrapperId = GenericHelper.getORProperty(wrapperId);
    			propertyName = GenericHelper.getORProperty(propertyName);
    			scroll(wrapperId, propertyName);
	    		
	    		WebElement element = PropertyGridElementHelper.getComponentElement(wrapperElement, dataDirKey, propertyName);
				MouseHelper.click(element);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				TestDataHelper testData = new TestDataHelper();
				String[] propValue = testData.getStringValue(propertyValue, delimiter);
				GenericHelper.selectDataDir(propValue);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
    
    /**
     * This method is used to scroll down in a Property Grid
     * @param gridId - Grid's unique DIV ID.
     * @param firstVisibleRowNo - First visible row number in the grid.
     * @param fullScroll - True or false. Whether to do full scroll or not.
     * @throws Exception
     */
	public static void scrollDownPropertyGrid(String gridId, int firstVisibleRowNo, boolean fullScroll) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			String locator = PropertyGridElementHelper.getLocator(gridId, firstVisibleRowNo);
			PropertyGridElementHelper.scrollDown(locator, fullScroll);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
     * This method is used to scroll down in a Property Grid
     * @param gridWrapper - Div or Table id within which the Property Grid is present.
     * @param gridId - Grid's unique DIV ID.
     * @param firstVisibleRowNo - First visible row number in the grid.
     * @param fullScroll - True or false. Whether to do full scroll or not.
     * @throws Exception
     */
	public static void scrollDownPropertyGrid(String gridWrapper, String gridId, int firstVisibleRowNo, boolean fullScroll) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			String locator = PropertyGridElementHelper.getLocator(gridWrapper, gridId, firstVisibleRowNo);
			PropertyGridElementHelper.scrollDown(locator, fullScroll);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to scroll down in a Property Grid
	 * @param gridId - Grid's unique DIV ID.
	 * @param firstVisibleRowNo - First visible row number in the grid.
	 * @param numberOfMoveDown - number of times down arrow has to be pressed.
	 * @throws Exception
	 */
	public static void scrollOneLevelDownPropertyGrid(String gridId, int firstVisibleRowNo, int numberOfMoveDown) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			String locator = PropertyGridElementHelper.getLocator(gridId, firstVisibleRowNo);
			PropertyGridElementHelper.scrollOneLevelDownPropertyGrid(locator, numberOfMoveDown);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to scroll down in a Property Grid
	 * @param gridWrapper - Div or Table id within which the Property Grid is present.
	 * @param gridId - Grid's unique DIV ID.
	 * @param firstVisibleRowNo - First visible row number in the grid.
	 * @param numberOfMoveDown - number of times down arrow has to be pressed.
	 * @throws Exception
	 */
	public static void scrollOneLevelDownPropertyGrid(String gridWrapper, String gridId, int firstVisibleRowNo, int numberOfMoveDown) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			String locator = PropertyGridElementHelper.getLocator(gridWrapper, gridId, firstVisibleRowNo);
			
			if (locator != null) {
				PropertyGridElementHelper.scrollOneLevelDownPropertyGrid(locator, numberOfMoveDown);
			}
			else {
				FailureHelper.failTest("Property Grid '" + gridId + "' not found.");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickProperty( String propertyName ) throws Exception {
    	try {
    		propertyName = GenericHelper.getORProperty(propertyName);
    		int index = scroll(propertyName);
    		
    		PropertyGridElementHelper.clickProperty(wrapperElement, propertyName, index);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickProperty( String wrapperId, String propertyName ) throws Exception {
    	try {
    		propertyName = GenericHelper.getORProperty(propertyName);
    		int index = scroll(wrapperId, propertyName);
    		
    		PropertyGridElementHelper.clickProperty(wrapperElement, propertyName, index);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void updateProperty( String propertyName, String propertyValue, String delimiter ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(propertyValue)) {
    			propertyName = GenericHelper.getORProperty(propertyName);
    			scroll(propertyName);
	    		
    			String component = PropertyGridElementHelper.getComponent(wrapperElement, propertyName);
				switch (component) {
				case "textbox":
					typeInTextBox(propertyName, propertyValue);
					break;
					
				case "combobox":
					selectInComboBox(propertyName, propertyValue);
					break;
					
				case "checkbox":
					clickCheckBox(propertyName, propertyValue);
					break;
					
				case "datepicker":
					typeInDatePicker(propertyName, propertyValue);
					break;
					
				case "datadir":
					typeInDataDir(propertyName, propertyValue, delimiter);
					break;

				default:
					break;
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void updateProperty( String wrapperId, String propertyName, String propertyValue, String delimiter ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(propertyValue)) {
				wrapperId = GenericHelper.getORProperty(wrapperId);
    			propertyName = GenericHelper.getORProperty(propertyName);
    			scroll(wrapperId, propertyName, 15);
	    		
    			String component = PropertyGridElementHelper.getComponent(wrapperElement, propertyName);
				switch (component) {
				case "textbox":
					typeInTextBox(wrapperId, propertyName, propertyValue);
					break;
					
				case "combobox":
					selectInComboBox(wrapperId, propertyName, propertyValue);
					break;
					
				case "checkbox":
					clickCheckBox(wrapperId, propertyName, propertyValue);
					break;
					
				case "datepicker":
					typeInDatePicker(wrapperId, propertyName, propertyValue);
					break;
					
				case "datadir":
					typeInDataDir(wrapperId, propertyName, propertyValue, delimiter);
					break;

				default:
					break;
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getTextBoxValue( String propertyName ) throws Exception {
    	try {
    		propertyName = GenericHelper.getORProperty(propertyName);
    		scroll(propertyName);
    		WebElement element = PropertyGridElementHelper.getComponentElement(wrapperElement, textBoxKey, propertyName);
			
			if (element == null) {
				String[] orKeys = {"PropertyGrid_TextBox1", "PropertyGrid_TextBox2"};
				element = PropertyGridElementHelper.getComponentElement(wrapperElement, orKeys, propertyName);
			}
			
			if (element != null) {
				return TextBoxElementHelper.getValue(element);
			}
			else {
				FailureHelper.failTest("Text box not found for Property '" + propertyName + "'");
			}
			
    		return null;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getTextBoxValue( String wrapperId, String propertyName ) throws Exception {
    	try {
    		wrapperId = GenericHelper.getORProperty(wrapperId);
    		propertyName = GenericHelper.getORProperty(propertyName);
    		scroll(wrapperId, propertyName);
    		WebElement element = PropertyGridElementHelper.getComponentElement(wrapperElement, textBoxKey, propertyName);
			
			if (element == null) {
				String[] orKeys = {"PropertyGrid_TextBox1", "PropertyGrid_TextBox2"};
				element = PropertyGridElementHelper.getComponentElement(wrapperElement, orKeys, propertyName);
			}
			
			if (element != null) {
				return TextBoxElementHelper.getValue(element);
			}
			else {
				FailureHelper.failTest("Text box not found for Property '" + propertyName + "'");
			}
    		
    		return null;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getComboBoxValue( String propertyName ) throws Exception {
    	try {
    		propertyName = GenericHelper.getORProperty(propertyName);
			scroll(propertyName);
    		String locator = PropertyGridElementHelper.getComponentLocator(comboBoxKey1, propertyName, useWrapper);
			
			if (locator == null) {
				locator = PropertyGridElementHelper.getComponentLocator(comboBoxKey2, propertyName, useWrapper);
				
				if (locator == null) {
					String[] orKeys = {"PropertyGrid_Dropdown2", "PropertyGrid_Dropdown1"};
					locator = PropertyGridElementHelper.getComponentLocator(orKeys, propertyName, useWrapper);
					
					if (locator != null) {
						return ComboBoxHelper.getValue(locator, "gwt_uid");
					}
					else {
						FailureHelper.failTest("Combo box not found for Property '" + propertyName + "'");
					}
				}
				else {
					return ComboBoxHelper.getValue(locator);
				}
			}
			else {
				return ComboBoxHelper.getValue(locator);
			}
    		
    		return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getComboBoxValue( String wrapperId, String propertyName ) throws Exception {
    	try {
    		wrapperId = GenericHelper.getORProperty(wrapperId);
    		propertyName = GenericHelper.getORProperty(propertyName);
			scroll(wrapperId, propertyName);
    		String locator = PropertyGridElementHelper.getComponentLocator(comboBoxKey1, propertyName, useWrapper);
			
			if (locator == null) {
				locator = PropertyGridElementHelper.getComponentLocator(comboBoxKey2, propertyName, useWrapper);
				
				if (locator == null) {
					String[] orKeys = {"PropertyGrid_Dropdown2", "PropertyGrid_Dropdown1"};
					locator = PropertyGridElementHelper.getComponentLocator(orKeys, propertyName, useWrapper);
					
					if (locator != null) {
						return ComboBoxHelper.getValue(locator, "gwt_uid");
					}
					else {
						FailureHelper.failTest("Combo box not found for Property '" + propertyName + "'");
					}
				}
				else {
					return ComboBoxHelper.getValue(locator);
				}
			}
			else {
				return ComboBoxHelper.getValue(locator);
			}
    		
    		return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean getCheckBoxValue( String propertyName ) throws Exception {
		try {
			propertyName = GenericHelper.getORProperty(propertyName);
			scroll(propertyName);
    		WebElement element = PropertyGridElementHelper.getComponentElement(wrapperElement, checkBoxKey, propertyName);
			
			if (element == null) {
				String[] orKeys = {"PropertyGrid_CheckBox1", "PropertyGrid_CheckBox2"};
				element = PropertyGridElementHelper.getComponentElement(wrapperElement, orKeys, propertyName);
			}
			
			if (element != null) {
				return CheckBoxElementHelper.getValue(element);
			}
			else {
				FailureHelper.failTest("Check box not found for Property '" + propertyName + "'");
			}
    		
    		return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean getCheckBoxValue( String wrapperId, String propertyName ) throws Exception {
		try {
			wrapperId = GenericHelper.getORProperty(wrapperId);
			propertyName = GenericHelper.getORProperty(propertyName);
			scroll(wrapperId, propertyName);
    		WebElement element = PropertyGridElementHelper.getComponentElement(wrapperElement, checkBoxKey, propertyName);
			
			if (element == null) {
				String[] orKeys = {"PropertyGrid_CheckBox1", "PropertyGrid_CheckBox2"};
				element = PropertyGridElementHelper.getComponentElement(wrapperElement, orKeys, propertyName);
			}
			
			if (element != null) {
				return CheckBoxElementHelper.getValue(element);
			}
			else {
				FailureHelper.failTest("Check box not found for Property '" + propertyName + "'");
			}
    		
    		return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getDateValue( String propertyName ) throws Exception {
		try {
			propertyName = GenericHelper.getORProperty(propertyName);
			scroll(propertyName);
    		WebElement element = PropertyGridElementHelper.getComponentElement(wrapperElement, datePickerKey, propertyName);
			
			if (element == null) {
				String[] orKeys = {"PropertyGrid_Date1", "PropertyGrid_Date2"};
				element = PropertyGridElementHelper.getComponentElement(wrapperElement, orKeys, propertyName);
			}
			
			return TextBoxElementHelper.getValue(element);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getDateValue( String wrapperId, String propertyName ) throws Exception {
		try {
			wrapperId = GenericHelper.getORProperty(wrapperId);
			propertyName = GenericHelper.getORProperty(propertyName);
			scroll(wrapperId, propertyName);
    		WebElement element = PropertyGridElementHelper.getComponentElement(wrapperElement, datePickerKey, propertyName);
			
			if (element == null) {
				String[] orKeys = {"PropertyGrid_Date1", "PropertyGrid_Date2"};
				element = PropertyGridElementHelper.getComponentElement(wrapperElement, orKeys, propertyName);
			}
			
			return TextBoxElementHelper.getValue(element);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getDataDirValue( String propertyName ) throws Exception {
		try {
			propertyName = GenericHelper.getORProperty(propertyName);
			scroll(propertyName);
    		WebElement element = PropertyGridElementHelper.getComponentElement(wrapperElement, dataDirInputKey, propertyName);
				return TextBoxElementHelper.getValue(element);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getDataDirValue( String wrapperId, String propertyName ) throws Exception {
		try {
			wrapperId = GenericHelper.getORProperty(wrapperId);
			propertyName = GenericHelper.getORProperty(propertyName);
			scroll(propertyName);
    		
    		WebElement element = PropertyGridElementHelper.getComponentElement(wrapperElement, dataDirInputKey, propertyName);
			return TextBoxElementHelper.getValue(element);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getValue( String propertyName ) throws Exception {
		try {
			propertyName = GenericHelper.getORProperty(propertyName);
			scroll(propertyName);
    		
			String component = PropertyGridElementHelper.getComponent(wrapperElement, propertyName);
			String value = null;
			
			switch (component) {
			case "textbox":
				value = getTextBoxValue(propertyName);
				break;
				
			case "combobox":
				value = getComboBoxValue(propertyName);
				break;
				
			case "checkbox":
				value = String.valueOf(getCheckBoxValue(propertyName));
				break;
				
			case "datepicker":
				value = getDateValue(propertyName);
				break;
				
			case "datadir":
				value = getDataDirValue(propertyName);
				break;

			default:
				break;
			}
			
			return value;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getValue( String wrapperId, String propertyName ) throws Exception {
		try {
			wrapperId = GenericHelper.getORProperty(wrapperId);
			propertyName = GenericHelper.getORProperty(propertyName);
			scroll(wrapperId, propertyName);
    		
			String component = PropertyGridElementHelper.getComponent(wrapperElement, propertyName);
			String value = null;
			
			switch (component) {
			case "textbox":
				value = getTextBoxValue(wrapperId, propertyName);
				break;
				
			case "combobox":
				value = getComboBoxValue(wrapperId, propertyName);
				break;
				
			case "checkbox":
				value = String.valueOf(getCheckBoxValue(wrapperId, propertyName));
				break;
				
			case "datepicker":
				value = getDateValue(wrapperId, propertyName);
				break;
				
			case "datadir":
				value = getDataDirValue(wrapperId, propertyName);
				break;

			default:
				break;
			}
			
			return value;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isEnabled( String propertyName ) throws Exception {
		try {
			propertyName = GenericHelper.getORProperty(propertyName);
			scroll(propertyName);
    		boolean isEnabled = true;
    		
			WebElement element = PropertyGridElementHelper.getComponentElement(wrapperElement, textBoxKey, propertyName);
			
			if (element != null)
				isEnabled = TextBoxElementHelper.isEnabled(element);
			else {
				element = PropertyGridElementHelper.getComponentElement(wrapperElement, comboBoxKey1, propertyName);
				if (element != null)
					isEnabled = ComboBoxElementHelper.isEnabled(element);
				else {
					element = PropertyGridElementHelper.getComponentElement(wrapperElement, comboBoxKey2, propertyName);
					if (element != null)
						isEnabled = ComboBoxElementHelper.isEnabled(element);
					else {
						element = PropertyGridElementHelper.getComponentElement(wrapperElement, checkBoxKey, propertyName);
						if (element != null)
							isEnabled = CheckBoxElementHelper.enabled(element);
						else {
							element = PropertyGridElementHelper.getComponentElement(wrapperElement, datePickerKey, propertyName);
							if (element != null)
								isEnabled = TextBoxElementHelper.isEnabled(element);
							else {
								element = PropertyGridElementHelper.getComponentElement(wrapperElement, dataDirKey, propertyName);
								if (element != null)
									isEnabled = TextBoxElementHelper.isEnabled(element);
								else {
									FailureHelper.failTest("Property '" + propertyName + "' not found.");
								}
							}
						}
					}
				}
			}
			
			return isEnabled;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isEnabled( String wrapperId, String propertyName ) throws Exception {
		try {
			propertyName = GenericHelper.getORProperty(propertyName);
			scroll(propertyName);
    		boolean isEnabled = true;
    		
			WebElement element = PropertyGridElementHelper.getComponentElement(wrapperElement, textBoxKey, propertyName);
			
			if (element != null)
				isEnabled = TextBoxElementHelper.isEnabled(element);
			else {
				element = PropertyGridElementHelper.getComponentElement(wrapperElement, comboBoxKey1, propertyName);
				if (element != null)
					isEnabled = ComboBoxElementHelper.isEnabled(element);
				else {
					element = PropertyGridElementHelper.getComponentElement(wrapperElement, comboBoxKey2, propertyName);
					if (element != null)
						isEnabled = ComboBoxElementHelper.isEnabled(element);
					else {
						element = PropertyGridElementHelper.getComponentElement(wrapperElement, checkBoxKey, propertyName);
						if (element != null)
							isEnabled = CheckBoxElementHelper.enabled(element);
						else {
							element = PropertyGridElementHelper.getComponentElement(wrapperElement, datePickerKey, propertyName);
							if (element != null)
								isEnabled = TextBoxElementHelper.isEnabled(element);
							else {
								element = PropertyGridElementHelper.getComponentElement(wrapperElement, dataDirKey, propertyName);
								if (element != null)
									isEnabled = TextBoxElementHelper.isEnabled(element);
								else {
									FailureHelper.failTest("Property '" + propertyName + "' not found.");
								}
							}
						}
					}
				}
			}
			
			return isEnabled;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isDisabled( String propertyName ) throws Exception {
		try {
			return !isEnabled(propertyName);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isDisabled( String wrapperId, String propertyName ) throws Exception {
		try {
			return !isEnabled(wrapperId, propertyName);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isPropertyPresent( String propertyName ) throws Exception {
    	try {
    		propertyName = GenericHelper.getORProperty(propertyName);
    		int index = scroll(propertyName);
    		
    		if (index < 0) {
				return false;
			}
    		else
    			return true;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isPropertyPresent( String wrapperId, String propertyName ) throws Exception {
    	try {
    		propertyName = GenericHelper.getORProperty(propertyName);
    		int index = scroll(wrapperId, propertyName);
    		
    		if (index < 0) {
				return false;
			}
    		else
    			return true;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}