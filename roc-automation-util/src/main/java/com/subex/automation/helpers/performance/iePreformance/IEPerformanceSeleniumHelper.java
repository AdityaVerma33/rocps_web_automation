package com.subex.automation.helpers.performance.iePreformance;

// Entire class commented out — depends on Selenium RC (com.thoughtworks.selenium)
// which was removed in Selenium 4.x. This class is unused by the rest of the codebase.

/*
import java.util.HashMap;

import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.thoughtworks.selenium.Selenium;

@SuppressWarnings("deprecation")
public class IEPerformanceSeleniumHelper extends IEPerformanceHelper {

	private Selenium defaultSelenium;

	private int failedJSAttempts = 2;

	protected IEPerformanceSeleniumHelper(Selenium defaultSelenium) {
		this.defaultSelenium = defaultSelenium;
	}

	private static HashMap<Selenium, IEPerformanceSeleniumHelper> driverMap = new HashMap<Selenium, IEPerformanceSeleniumHelper>();
	public static IEPerformanceSeleniumHelper forSelenium(Selenium defaultSelenium) {
		IEPerformanceSeleniumHelper helper = driverMap.get(defaultSelenium);
		if(helper == null) {
			helper = new IEPerformanceSeleniumHelper(defaultSelenium);
			driverMap.put(defaultSelenium, helper);
		}
		return helper;
	}

	@Override
	public void addMark(String marker) throws Exception {
		try {
			if (marker != null)
				defaultSelenium.runScript("try { _dt_addMark('" + marker + "') } catch(e) { }");
		}
		catch (com.thoughtworks.selenium.SeleniumException e) {
			if(--failedJSAttempts == 0)
				try {
					{
						iePerformAgentActive = false;
						Log4jHelper.logInfo("Browser Agent not active!");
					}
				} catch (Exception e1) {
					FailureHelper.setErrorMessage(e1);
					throw e1;
				}
		}
	}

	@Override
	protected void setCurrentTimerNameViaJavaScript() throws Exception {
		if(!enableJavaScriptInjection) return;

		try {
			if (currentTimerName != null)
				defaultSelenium.runScript("try { _dt_setTimerName('" + currentTimerName + "') } catch(e) { }");
			else
				defaultSelenium.runScript("try { _dt_setTimerName() } catch(e) { }");
		}
		catch (com.thoughtworks.selenium.SeleniumException e) {
			if(--failedJSAttempts == 0) {
				iePerformAgentActive = false;
				try {
					Log4jHelper.logInfo("Browser Agent not active!");
				} catch (Exception e1) {
					FailureHelper.setErrorMessage(e1);
					throw e1;
				}
			}
		}
	}

	@Override
	protected void openUrl(String url) {
		defaultSelenium.open(url);
	}
}
*/
