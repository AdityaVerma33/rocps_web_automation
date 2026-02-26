package com.subex.automation.helpers.performance.iePreformance;

import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.subex.automation.helpers.report.Log4jHelper;

public class IEPerformanceWebDriverHelper extends IEPerformanceHelper {

	private WebDriver defaultDriver;
	private JavascriptExecutor jsExecutor;

	// allow our javascript injection to fail twice. We might not be active when the browser starts before we actually browse to a URL
	private int failedJSAttempts = 2;

	protected IEPerformanceWebDriverHelper(WebDriver defaultDriver) {
		this.defaultDriver = defaultDriver;

		if(defaultDriver instanceof IEPerformanceWebDriver)
			this.jsExecutor = (JavascriptExecutor)((IEPerformanceWebDriver)defaultDriver).getWebDriver();
		else
			this.jsExecutor = (JavascriptExecutor)defaultDriver;
	}

	private static HashMap<WebDriver, IEPerformanceWebDriverHelper> driverMap = new HashMap<WebDriver, IEPerformanceWebDriverHelper>();
	public static IEPerformanceWebDriverHelper forDriver(WebDriver defaultDriver) {
		if(defaultDriver instanceof IEPerformanceWebDriver)
			defaultDriver = ((IEPerformanceWebDriver)defaultDriver).getWebDriver();

		IEPerformanceWebDriverHelper helper = driverMap.get(defaultDriver);
		if(helper == null) {
			helper = new IEPerformanceWebDriverHelper(defaultDriver);
			driverMap.put(defaultDriver, helper);
		}
		return helper;
	}

	@Override
	protected void setCurrentTimerNameViaJavaScript() throws Exception {
		if(!enableJavaScriptInjection) return;
		if(!iePerformAgentActive) return;

		try {
			if (currentTimerName != null)
				// jsExecutor.executeScript("try { _dt_setTimerName('" + currentTimerName + "') } catch(e) { }");
				jsExecutor.executeScript("_dt_setTimerName('" + currentTimerName + "')");
			else
				// jsExecutor.executeScript("try { _dt_setTimerName() } catch(e) { }");
				jsExecutor.executeScript("_dt_setTimerName()");
		}
		catch (WebDriverException e) {
			if(--failedJSAttempts == 0) {
				iePerformAgentActive = false;
				Log4jHelper.logInfo("Browser Agent not active!");
			}
		}
	}

	@Override
	public void addMark(String marker) throws Exception {
		try {
			if (marker != null && iePerformAgentActive)
				// jsExecutor.executeScript("try { _dt_addMark('" + marker + "') } catch(e) { }");
				jsExecutor.executeScript("_dt_addMark('" + marker + "')");
		}
		catch (WebDriverException e) {
			if(--failedJSAttempts == 0) {
				iePerformAgentActive = false;
				Log4jHelper.logInfo("Browser Agent not active!");
			}
		}
	}

	@Override
	protected void openUrl(String url) {
		defaultDriver.navigate().to(url);
	}
}