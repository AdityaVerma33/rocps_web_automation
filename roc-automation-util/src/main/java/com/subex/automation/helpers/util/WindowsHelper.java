package com.subex.automation.helpers.util;

import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.subex.automation.helpers.selenium.AcceptanceTest;

public class WindowsHelper extends AcceptanceTest {

	public String[] getWindowHandles() throws Exception {
		try {
			String originalWindow = driver.getWindowHandle();
			Set<String> windows = driver.getWindowHandles();
			String[] windowTitles = new String[windows.size()];
			int i = 0;
			
			for (String window : windows) {
				switchToWindow(window);
				windowTitles[i++] = driver.getWindowHandle();
			}
			
			if (i > 1)
				switchToWindow(originalWindow);
			
			return windowTitles;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String[] getWindowTitles() throws Exception {
		try {
			String originalWindow = driver.getWindowHandle();
			Set<String> windows = driver.getWindowHandles();
			String[] windowTitles = new String[windows.size()];
			int i = 0;
			
			for (String window : windows) {
				switchToWindow(window);
				windowTitles[i++] = driver.getTitle();
			}
			
			if (i > 1)
				switchToWindow(originalWindow);
			
			return windowTitles;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void switchToWindow(String title) throws Exception {
		try {
			driver.switchTo().window(title);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void switchToWindow(String closeWindowTitle, String mainWindowTitle) throws Exception {
		try {
			switchToWindow(closeWindowTitle);
			driver.close();
			switchToWindow(mainWindowTitle);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void switchToMainWindow() throws Exception {
		try {
			switchToWindow(currentWindowTitle);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void maximizeBrowser() throws Exception {
		try {
			driver.manage().window().maximize();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@SuppressWarnings("deprecation")
	public void fullScreen() throws Exception {
		try {
			String browser = configProp.getBrowser().toLowerCase();
			
			switch (browser) {
			case "firefox":
				((FirefoxDriver)driver).getKeyboard().pressKey(Keys.F11);
				break;
				
			case "chrome":
				((ChromeDriver)driver).getKeyboard().pressKey(Keys.F11);
				break;
				
			case "ie":
				((InternetExplorerDriver)driver).getKeyboard().pressKey(Keys.F11);
				break;

			default:
				break;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}