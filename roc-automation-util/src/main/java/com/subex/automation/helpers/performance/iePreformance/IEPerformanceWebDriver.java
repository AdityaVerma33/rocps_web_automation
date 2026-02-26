package com.subex.automation.helpers.performance.iePreformance;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.application.BrowserHelper;

public class IEPerformanceWebDriver implements WebDriver {

	private WebDriver driver;
	private IEPerfWebDriverNavigation iePerfNavigation;
	private static HashMap<WebDriver, IEPerformanceWebDriver> drivers = new HashMap<WebDriver, IEPerformanceWebDriver>();
	
	public IEPerformanceWebDriver(WebDriver driver) {
		this.driver = driver;
		this.iePerfNavigation = new IEPerfWebDriverNavigation(driver);
	}
	
	public static IEPerformanceWebDriver forWebDriver(WebDriver driver) {
		IEPerformanceWebDriver dtDriver = drivers.get(driver);
		if(dtDriver == null) {
			dtDriver = new IEPerformanceWebDriver(driver);
			drivers.put(driver, dtDriver);
		}
		return dtDriver;
	}
	
	@Override
	public void close() {
		driver.close();
	}

	@Override
	public WebElement findElement(By arg0) {
		return driver.findElement(arg0);
	}

	@Override
	public List<WebElement> findElements(By arg0) {
		return driver.findElements(arg0);
	}

	@Override
	public void get(String arg0) {
		driver.get(arg0);
	}

	@Override
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	@Override
	public String getPageSource() {
		return driver.getPageSource();
	}

	@Override
	public String getTitle() {
		return driver.getTitle();
	}

	@Override
	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	@Override
	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	@Override
	public Options manage() {
		return driver.manage();
	}

	@Override
	public Navigation navigate() {
		return iePerfNavigation;
	}

	@Override
	public void quit() {
		driver.quit();
	}

	@Override
	public TargetLocator switchTo() {
		return driver.switchTo();
	}

	private class IEPerfWebDriverNavigation implements WebDriver.Navigation
	{
		protected WebDriver driver;
		protected IEPerformanceWebDriverHelper iePerform;
		protected BrowserHelper browser = new BrowserHelper();
		
		public IEPerfWebDriverNavigation(WebDriver driver) {
			this.driver = driver;
			iePerform = IEPerformanceWebDriverHelper.forDriver(driver);
		}
		
		@Override
		public void back() {
			try {
				browser.back();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void forward() {
			try {
				browser.forward();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		@Override
		public void refresh() {
			try {
				browser.refresh();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void to(String arg0) {
			if(!iePerform.hasTimerName())
				try {
					iePerform.setTimerName(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			driver.navigate().to(iePerform.getIEPerfTimerUrl(arg0));
		}

		@Override
		public void to(URL arg0) {
			try {
				to(arg0.toURI().toString());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		
	}

	public WebDriver getWebDriver() {
		return driver;
	}
}