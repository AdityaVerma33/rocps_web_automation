package com.subex.automation.helpers.performance.iePreformance;

import java.util.HashMap;

import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.thoughtworks.selenium.Selenium;

@SuppressWarnings("deprecation")
public class IEPerformanceSeleniumHelper extends IEPerformanceHelper {

	private Selenium defaultSelenium;

	// allow our javascript injection to fail twice. We might not be active when the browser starts before we actually browse to a URL
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

	/***
	 * Sets a Marker on the current timestamp
	 * @param marker
	 */
	@Override
	public void addMark(String marker) throws Exception {
		try {
			if (marker != null)
				defaultSelenium.runScript("try { _dt_addMark('" + marker + "') } catch(e) { }");
		}
		catch (com.thoughtworks.selenium.SeleniumException e) {
			// The exception "Current window or frame is closed!" can happen.
			// If this exception occurs, the js-call might have happened too soon (before selenium.open() was called).
			// In these cases the timer will get set as soon as selenium.open() gets called, as we override this
			// method and send the timer name with each url.
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
			// The exception "Current window or frame is closed!" can happen.
			// If this exception occurs, the js-call might have happened too soon (before selenium.open() was called).
			// In these cases the timer will get set as soon as selenium.open() gets called, as we override this
			// method and send the timer name with each url.
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