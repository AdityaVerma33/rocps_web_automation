package com.subex.automation.helpers.component;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;
import com.subex.automation.helpers.componentHelpers.LocatorHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class ElementHelper extends AcceptanceTest {
	
	public static boolean isElementPresent(String xpath) throws Exception {
		try {
			WebElement element = getElement(xpath);
			
			if (element == null)
				return false;
			else
				return true;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isElementPresent(String wrapperId, String xpath) throws Exception {
		try {
			WebElement element = getElement(wrapperId, xpath);
			
			if (element == null)
				return false;
			else
				return true;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isElementPresent(WebElement element, String xpath) throws Exception {
		try {
			WebElement newElement = getElement(element, xpath);
			
			if (newElement == null)
				return false;
			else
				return true;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement(String idOrXpath) throws Exception {
		try {
			WebElement element = null;
			List<WebElement> elements = null;
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			
			if (idOrXpath.startsWith("/")) {
				elements = driver.findElements(By.xpath(idOrXpath));
				
				if (elements.size() >= 1)
					element = elements.get(elements.size()-1);
			}
			else {
				element = getElementByID(idOrXpath);
				
				if (element == null) {
					element = getElementByName(idOrXpath);
					
					if (element == null) {
						element = getElementByClass(idOrXpath);
						
						if (element == null) {
							element = getElementByCSSSelector(idOrXpath);
							
							if (element == null)
								element = getElementByTitle(idOrXpath);
							
							if (element == null)
								element = getElementByText(idOrXpath);
						}
					}
				}
			}
			
			return element;
		} catch (NullPointerException e) {
			FailureHelper.setError("Element with identifier '" + idOrXpath + "' is not found.");
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch(NoSuchElementException | InvalidSelectorException e) {
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement(String wrapperId, String idOrXpath) throws Exception {
		try {
			wrapperId = GenericHelper.getORProperty(wrapperId);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement wrapper = getWrapperElement(wrapperId);
			WebElement element = null;
			
			if (wrapper != null)
				element = getElement(wrapper, idOrXpath);
			
			return element;
		} catch (NullPointerException e) {
			FailureHelper.setError("Element with identifier '" + idOrXpath + "' is not found.");
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch(NoSuchElementException | InvalidSelectorException e) {
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement(WebElement element, String idOrXpath) throws Exception {
		try {
			WebElement childElement = null;
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			
			if (idOrXpath.startsWith("/")) {
				List<WebElement> elements = element.findElements(By.xpath(idOrXpath));
				
				if (elements.size() == 1) {
					childElement = elements.get(0);
				}
				else if (elements.size() == 0 || elements.size() > 1) {
					List<WebElement> temp = element.findElements(By.xpath("." + idOrXpath));
					
					if (temp.size() >= 1)
						childElement = temp.get(temp.size()-1);
					else if (elements.size() > 1)
						childElement = elements.get(elements.size()-1);
				}
			}
			else {
				childElement = getElementByID(element, idOrXpath);
				
				if (childElement == null) {
					childElement = getElementByName(element, idOrXpath);
					
					if (childElement == null) {
						childElement = getElementByClass(element, idOrXpath);
						
						if (childElement == null) {
							childElement = getElementByCSSSelector(element, idOrXpath);
							
							if (childElement == null) {
								childElement = getElementByTitle(element, idOrXpath);
								
								if (childElement == null) {
									childElement = getElementByText(element, idOrXpath);
								}
							}
						}
					}
				}
			}
			
			return childElement;
		} catch (NullPointerException e) {
			FailureHelper.setError("Element with identifier '" + idOrXpath + "' is not found.");
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch(NoSuchElementException | InvalidSelectorException e) {
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getWrapperElement(String wrapperId) throws Exception {
		try {
			WebElement element = null;

			if (wrapperId != null) {
				wrapperId = GenericHelper.getORProperty(wrapperId);
				element = getElement(wrapperId);

				if (element == null && !wrapperId.startsWith("/")) {
					String locator = or.getProperty("Wrapper_Locator").replace("wrapperId", wrapperId);
					element = getElement(locator);
				}
			}
			
			return element;
		} catch (NullPointerException e) {
			FailureHelper.setError("Wrapper element with identifier '" + wrapperId + "' is not found.");
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElementByID(String id) throws Exception {
		try {
			List<WebElement> elements = driver.findElements(By.id(id));
			if (elements != null && elements.size() > 0)
				return elements.get(elements.size()-1);
			else
				return null;
			
		} catch(NoSuchElementException | InvalidSelectorException e) {
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElementByID(WebElement element, String id) throws Exception {
		try {
			List<WebElement> elements = element.findElements(By.id(id));
			
			if (elements.size() >= 1)
				return elements.get(0);
			else
				return null;
		} catch(NoSuchElementException | InvalidSelectorException e) {
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElementByName(String name) throws Exception {
		try {
			
			return driver.findElement(By.name(name));
			
		} catch(NoSuchElementException | InvalidSelectorException e) {
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElementByName(WebElement element, String name) throws Exception {
		try {
			List<WebElement> elements = element.findElements(By.name(name));
			
			if (elements.size() >= 1)
				return elements.get(0);
			else
				return null;
		} catch(NoSuchElementException | InvalidSelectorException e) {
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElementByClass(String className) throws Exception {
		try {
			List<WebElement> elements = driver.findElements(By.className(className));
			
			if (elements.size() == 0)
				elements = driver.findElements(By.xpath("//*[contains(@class,'" + className + "')]"));
			
			if (elements.size() == 0)
				return null;
			else
				return elements.get(0);
		} catch(NoSuchElementException | InvalidSelectorException e) {
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElementByClass(WebElement element, String className) throws Exception {
		try {
			List<WebElement> elements = element.findElements(By.className(className));
			
			if (elements.size() >= 1)
				return elements.get(0);
			else
				return null;
		} catch(NoSuchElementException | InvalidSelectorException e) {
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElementByCSSSelector(String css) throws Exception {
		try {
			
			return driver.findElement(By.cssSelector(css));
			
		} catch(NoSuchElementException | InvalidSelectorException e) {
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElementByCSSSelector(WebElement element, String css) throws Exception {
		try {
			List<WebElement> elements = element.findElements(By.cssSelector(css));
			
			if (elements.size() >= 1)
				return elements.get(0);
			else
				return null;
		} catch(NoSuchElementException | InvalidSelectorException e) {
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElementByTitle(String title) throws Exception {
		try {
			
			return driver.findElement(By.xpath("//*[@title='" + title + "']"));
			
		} catch(NoSuchElementException | InvalidSelectorException e) {
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElementByTitle(WebElement element, String title) throws Exception {
		try {
			List<WebElement> elements = element.findElements(By.xpath("//*[@title='" + title + "']"));
			
			if (elements.size() >= 1)
				return elements.get(0);
			else
				return null;
		} catch(NoSuchElementException | InvalidSelectorException e) {
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElementByText(String text) throws Exception {
		try {
			List<WebElement> elements = driver.findElements(By.linkText(text));
			WebElement element = null;
			
			if (elements == null || elements.size() == 0) {
				elements = driver.findElements(By.partialLinkText(text));
				
				if (elements == null || elements.size() == 0) {
					elements = driver.findElements(By.xpath("//*[text()='" + text + "']"));
					
					if (elements.size() > 0)
						element = elements.get(0);
				}
				else {
					element = elements.get(0);
				}
			}
			else {
				element = elements.get(0);
			}
			
			return element;
		} catch(NoSuchElementException | InvalidSelectorException e) {
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElementByText(WebElement parentElement, String text) throws Exception {
		try {
			List<WebElement> elements = parentElement.findElements(By.linkText(text));
			WebElement element = null;
			
			if (elements == null || elements.size() == 0) {
				elements = parentElement.findElements(By.partialLinkText(text));
				
				if (elements == null || elements.size() == 0) {
					elements = parentElement.findElements(By.xpath("//*[text()='" + text + "']"));
					
					if (elements != null && elements.size() > 0)
						element = elements.get(0);
				}
				else {
					element = elements.get(0);
				}
			}
			else {
				element = elements.get(0);
			}
			
			return element;
		} catch(NoSuchElementException | InvalidSelectorException e) {
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static List<WebElement> getElements(String xpath) throws Exception {
		try {
			xpath = GenericHelper.getORProperty(xpath);
			return driver.findElements(By.xpath(xpath));
			
		} catch(NoSuchElementException | InvalidSelectorException e) {
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static List<WebElement> getElements(WebElement element, String xpath) throws Exception {
		try {
			String locator = LocatorHelper.getLocator(element);
			xpath = GenericHelper.getORProperty(xpath);
			int totalElements = getXpathCount(locator);
			
			if (totalElements == 0)
				return null;
			else if (totalElements == 1)
				return driver.findElements(By.xpath(locator + xpath));
			else {
				List<WebElement> elements = driver.findElements(By.xpath(locator + "[" + totalElements + "]" + xpath));
				if (elements == null || elements.size() == 0) {
					elements = driver.findElements(By.xpath(locator + xpath));
				}
				
				return elements;
			}
		} catch(NoSuchElementException | InvalidSelectorException e) {
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement(String[] locators) throws Exception {
		try {
			WebElement element = null;
			
			for (int i = 0; i < locators.length; i++) {
				element = ElementHelper.getElement(locators[i]);
				
				if (element != null) {
					break;
				}
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement(String[] locators, String targetString, String replaceString) throws Exception {
		try {
			WebElement element = null;
			
			for (int i = 0; i < locators.length; i++) {
				String cmpLocator = or.getProperty(locators[i]).replace(targetString, replaceString);
				element = ElementHelper.getElement(cmpLocator);
				
				if (element != null) {
					break;
				}
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement(String[] locators, WebElement wrapperElement) throws Exception {
		try {
			WebElement element = null;
			
			for (int i = 0; i < locators.length; i++) {
				element = ElementHelper.getElement(wrapperElement, locators[i]);
				
				if (element != null)
					break;
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement(String[] locators, WebElement wrapperElement, String targetString, String replaceString) throws Exception {
		try {
			WebElement element = null;
			
			for (int i = 0; i < locators.length; i++) {
				String ckbLocator = or.getProperty(locators[i]).replace(targetString, replaceString);
				element = ElementHelper.getElement(wrapperElement, ckbLocator);
				
				if (element != null)
					break;
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void waitForAttribute( String xpath, String attribute, String value, long waitSeconds) throws Exception {
    	try {
    		xpath = GenericHelper.getORProperty(xpath);
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds));

	    	if (xpath.startsWith("/"))
	    		wait.until(ExpectedConditions.attributeToBe(By.xpath(xpath), attribute, value));
	    	else
	    		wait.until(ExpectedConditions.attributeToBe(By.id(xpath), attribute, value));
    	} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
    }
	
	public static void waitForElement( String xpath, long waitSeconds) throws Exception {
    	try {
    		xpath = GenericHelper.getORProperty(xpath);
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds));

	    	if (xpath.startsWith("/"))
	    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	    	else
	    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(xpath)));
    	} catch (Exception e) {
    		if (e.getMessage().contains("Expected condition failed: waiting for visibility of element"))
    			FailureHelper.failTest("Element '" + xpath + "' did not appear within the expected waiting time '" + waitSeconds + "' secs.");
    		else
    			FailureHelper.setErrorMessage(e);
			throw e;
		}
    }
	
	public static void waitForClickableElement( String xpath, long waitSeconds) throws Exception {
    	try {
    		xpath = GenericHelper.getORProperty(xpath);
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds));

	    	if (xpath.startsWith("/"))
	    		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	    	else
	    		wait.until(ExpectedConditions.elementToBeClickable(By.id(xpath)));
    	} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
    }
	
	public static void waitForClickableElement( WebElement element, long waitSeconds) throws Exception {
    	try {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds));
			wait.until(ExpectedConditions.elementToBeClickable(element));
    	} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
    }
	
    public static void waitForElementToDisappear(String xpath, long waitSeconds) throws Exception {
    	try {
    		xpath = GenericHelper.getORProperty(xpath);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds));
			
			if (xpath.startsWith("/"))
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
			else
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(xpath)));
    	} catch (Exception e) {
    		if (e.getMessage().contains("Expected condition failed: waiting for element to no longer be visible"))
    			FailureHelper.failTest("Element '" + xpath + "' did not disappear within the expected waiting time '" + waitSeconds + "' secs.");
    		else
    			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
    
    public static String getText(String xpath) throws Exception {
		try {
			WebElement element = getElement(xpath);
			
			if (element == null)
				return null;
			else
				return getText(element);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getText(WebElement element) throws Exception {
		try {
			if (element != null)
				return element.getText();
			else
				return null;
		} catch (WebDriverException e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getText(WebElement element, String xpath) throws Exception {
		try {
			if (element != null) {
				WebElement childElement = getElement(element, xpath);
				
				if (childElement == null)
					return null;
				else
					return childElement.getText();
			}
			else
				return null;
		} catch (WebDriverException e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * method for double click action
	 * @param webelement
	 * @throws Exception 
	 */
	public static void doubleClick(String xpath) throws Exception {
		try {
			WebElement element = getElement(xpath);
			
			if (element == null)
				FailureHelper.failTest("Element with xpath '" + xpath + "' is not found.");
			else
				doubleClick(element);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void doubleClick(WebElement element) throws Exception {
		try {
			scrollToView(element, true);
			Actions action = new Actions(driver);
//			action.click(element);
			action.doubleClick(element);
			action.build().perform();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public static void click(String xpath) throws Exception {
		WebElement element = null;
		
		try {
			element = getElement(xpath);
			
			if (element == null) {
				waitForElement(xpath, configProp.getSearchScreenWaitSec());
				element = getElement(xpath);
			}
			
			scrollToView(element, true);
			Actions action = new Actions(driver);
			action.click(element).build().perform();
		}
		catch (TimeoutException e) {
			FailureHelper.setError("Element '" + xpath + "' is not clickable");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (WebDriverException e) {
			FailureHelper.handleClickException(xpath, element);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void click(WebElement element) throws Exception {
		try {
			boolean scrolled = scrollToView(element, true);
			if (scrolled)
				Thread.sleep(500);
			element.click();
		}
		catch (WebDriverException e) {
			FailureHelper.handleClickException(element);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void actionClick(WebElement element) throws Exception {
		try {
			Actions action = new Actions(driver);
			action.click(element).build().perform();
		}
		catch (WebDriverException e) {
			FailureHelper.handleClickException(element);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void javaScriptClick(String xpath) throws Exception {
		try {
			WebElement element = getElement(xpath);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		}
		catch (WebDriverException e) {
			FailureHelper.setError("Element '" + xpath + "' is not clickable");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public static int getXpathCount(String xpath) throws Exception {
		try {
			List<WebElement> elements = getElements(xpath);
			
			if (elements != null)
				return elements.size();
			else
				return 0;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getAttribute(String xpath, String attribute) throws Exception {
		try {
			WebElement element = getElement(xpath);
			
			if (element != null)
				return getAttribute(element, attribute);
			else
				return null;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getAttribute(WebElement element, String attribute) throws Exception {
		try {
			if (element != null)
				return element.getAttribute(attribute);
			else
				return null;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getAttribute(WebElement element, String xpath, String attribute) throws Exception {
		try {
			WebElement childElement = getElement(element, xpath);
			return getAttribute(childElement, attribute);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[] getAttributes(String xpath, String attribute) throws Exception {
		try {
			List<WebElement> elements = getElements(xpath);
			
			if (elements != null && elements.size() > 0) {
				String[] attributes = new String[elements.size()];
				
				for (int i = 0; i < elements.size(); i++)
					attributes[i] = elements.get(i).getAttribute(attribute);
				return attributes;
			}
			else
				return null;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public static void scrollDown(String xpath, boolean fullScroll) throws Exception {
		try {
			WebElement element = getElement(xpath);
			click(element);
			
			if (fullScroll)
				pressKey(element, Keys.END);
			else
				pressKey(element, Keys.PAGE_DOWN);
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void scrollUp(String xpath, boolean fullScroll) throws Exception {
		try {
			WebElement element = getElement(xpath);
			click(element);
			
			if (fullScroll)
				pressKey(element, Keys.HOME);
			else
				pressKey(element, Keys.PAGE_UP);
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * Method to scroll one row down.
	 * @param xpath - Element to click to scroll.
	 * @param numberOfMoveDown - Number of times to scroll.
	 * @throws Exception
	 */
	public static void scrollOneLevelDown(String xpath, int numberOfMoveDown) throws Exception {
		try {
			WebElement element = getElement(xpath);
			
			for (int i = 0; i < numberOfMoveDown; i++) {
				click(element);
				pressKey(element, Keys.ARROW_DOWN);
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * Method to scroll one row down.
	 * @param xpath - Element to click to scroll.
	 * @param numberOfMoveDown - Number of times to scroll.
	 * @throws Exception
	 */
	public static void scrollOneLevelUp(String xpath, int numberOfMoveDown) throws Exception {
		try {
			WebElement element = getElement(xpath);
			
			for (int i = 0; i < numberOfMoveDown; i++) {
				click(element);
				pressKey(element, Keys.ARROW_UP);
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void pressEscape(String xpath) throws Exception {
		try {
			WebElement element = getElement(xpath);
			if (element != null)
				pressEscape(element);
			else {
				FailureHelper.failTest("Element with xpath '" + xpath + "' not found");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void pressEscape(WebElement element) throws Exception {
		try {
			pressKey(element, Keys.ESCAPE);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isClickable(String locator) throws Exception {
		try {
			WebElement element = getElement( locator );
			
			return isClickable(element);
		} catch (WebDriverException e) {
			return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isClickable(WebElement element) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (WebDriverException e) {
			return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void dragAndDrop(String locator, int xIncrement, int yIncrement) throws Exception {
		try {
			WebElement element = getElement( locator );
			
			dragAndDrop(element, xIncrement, yIncrement);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void dragAndDrop(WebElement element, int xIncrement, int yIncrement) throws Exception {
		try {
			Actions action = new Actions(driver);
			action.dragAndDropBy(element, xIncrement, yIncrement).build().perform();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void dragAndDrop(WebElement sourceElement, WebElement targetElement) throws Exception {
		try {
			Actions action = new Actions(driver);
			action.dragAndDrop(sourceElement, targetElement).build().perform();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void scrollToView(String xpath, boolean checkIsClickable) throws Exception {
		try {
			WebElement element = getElement( xpath );
			
			scrollToView(element, checkIsClickable);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean scrollToView(WebElement element, boolean checkIsClickable) throws Exception {
		try {
			if (checkIsClickable) {
				if (!isClickable(element)) {
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
					return true;
				}
			}
			else {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				return true;
			}
			
			return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void pressEscape() throws Exception {
		try {
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).build().perform();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void pressKey(CharSequence key) throws Exception {
		try {
			Actions action = new Actions(driver);
			action.sendKeys(key).build();
			action.perform();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void pressKey(WebElement element, CharSequence key) throws Exception {
		try {
			Actions action = new Actions(driver);
			action.click(element).sendKeys(key).build();
			action.perform();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void keyPress(WebElement element, CharSequence key) throws Exception {
		try {
			element.sendKeys(key);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getValue(WebElement element) throws Exception {
		try {
			if (element != null)
				return element.getAttribute("value");
			else
				return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clear(WebElement element) throws Exception {
		try {
			if (element.isEnabled()) {
				element.clear();
				String currentValue = getValue(element);
				if (!currentValue.equals("")) {
					for (int i = 0; i < currentValue.length(); i++)
						pressKey(element, Keys.BACK_SPACE);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static List<WebElement> getDialogElements() throws Exception {
		try {
			String dialogWrapper = GenericHelper.getORProperty("Popup_Wrapper");
			
			if (isElementPresent(dialogWrapper)) {
				List<WebElement> dialogs = ElementHelper.getElements(dialogWrapper);
					
				if (dialogs != null && dialogs.size() > 0)
					return dialogs;
			}
			
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void setFocus(String xpathOrId) {
		if (xpathOrId.contains("//")) {
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath(xpathOrId))).perform();
		}
		else {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("document.getElementById('" + xpathOrId + "').focus();");
		}
	}
	
	public WebElement fluentWait(final By locator) {
	    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	            .withTimeout(Duration.ofSeconds(30))
	            .pollingEvery(Duration.ofSeconds(5))
	            .ignoring(NoSuchElementException.class, StaleElementReferenceException.class );

	    WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
	        public WebElement apply(WebDriver driver) {
	            return driver.findElement(locator);
	        }
	    });

	    return  foo;
	}
	
	public static boolean hasValidation(WebElement element) throws Exception {
		try {
			if (element == null) {
				return false;
			}
			else {
				String classValue = ElementHelper.getAttribute(element, "class");
				if (!classValue.contains("roc-field-invalid") && !classValue.contains("roc-field-mandatory")) {
					return false;
				}
				else
					return true;
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}