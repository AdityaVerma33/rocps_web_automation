package com.subex.automation.helpers.performance.iePreformance;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;

import com.subex.automation.helpers.data.ValidationHelper;

public abstract class IEPerformanceHelper {

	protected String currentTimerName = null;
	protected boolean enableJavaScriptInjection = true;
	protected boolean iePerformAgentActive = true;

	protected abstract void setCurrentTimerNameViaJavaScript() throws Exception;
	protected abstract void openUrl(String url);

	/***
	 * Sets a Marker on the current timestamp
	 * @param marker
	 * @throws Exception 
	 */
	public abstract void addMark(String marker) throws Exception;


	/**
	 * Allows disabling JavaScript injection of timernames
	 * This might be useful in cases before a first page request was executed to avoid an exception in Selenium
	 * @param enable
	 */
	public void enableJavaScriptInjection(boolean enable) {
		this.enableJavaScriptInjection = enable;
	}

	/**
	 * Clear the current timer.
	 * All activities in the browser from now on will not be assigned with a timer
	 * @throws Exception 
	 */
	public void clearTimerName() throws Exception {
		currentTimerName = null;
		setCurrentTimerNameViaJavaScript();
	}

	/***
	 * Sets a new Timer Name.
	 * All activities in the browser from now on will be assigned to that timer
	 * Timers allow you to subscribe measures such as #of Network Requests, Exec time of JavaScript, #of Database Calls, ...
	 * -> these measures will automatically be analyzed and regression across builds will be identified for you
	 * @param timerNames A list of names will be concatenated with / which allows you to group timers. Example: MyWebSite/Login, MyWebSite/Search, ...
	 * @throws Exception 
	 */
	public void setTimerName(String... timerNames) throws Exception {
		if (timerNames.length > 0) {
			String timerName = null;
			for (String name : timerNames) {
				if (timerName == null)
					timerName = name;
				else
					timerName += "/" + name;
			}
			currentTimerName = TestRunIdProvider.getTestRunId() + "#" + timerName;
		}
		else {
			currentTimerName = null;
		}
		setCurrentTimerNameViaJavaScript();
	}

	/***
	 * Adds additional timer names to the current timer
	 * @param addTimerNames
	 * @throws Exception 
	 */
	public void addTimerName(String... addTimerNames) throws Exception {
		if(ValidationHelper.isEmpty(currentTimerName) || addTimerNames.length == 0)
			setTimerName(addTimerNames);
		else {
			for (String name : addTimerNames) {
				currentTimerName += "/" + name;
			}
			setCurrentTimerNameViaJavaScript();
		}
	}

	/***
	 * Remove multiple levels of timer names
	 * @param levels
	 * @throws Exception 
	 */
	public void removeTimerName(int levels) throws Exception {
		if(currentTimerName == null) return;
		while(levels > 0) {
			int lastDivider = currentTimerName.lastIndexOf("/");
			if(lastDivider < 0) {
				currentTimerName = null;
				return;
			}

			currentTimerName = currentTimerName.substring(0, lastDivider);
			levels--;
		}
		setCurrentTimerNameViaJavaScript();
	}

	/***
	 * @return True if a timername is set
	 */
	public boolean hasTimerName() {
		return (currentTimerName != null) && (currentTimerName.length() > 0);
	}

	/***
	 * Removes the last level of timer names
	 * @throws Exception 
	 */
	public void removeTimerName() throws Exception {
		removeTimerName(1);
	}

	/***
	 * Uses the current stack trace and sets the name of the calling method as Timer Name
	 * @throws Exception 
	 */
	public void setTimerName() throws Exception {
		setTimerName(false);
	}

	/***
	 * Uses the current stack trace and sets the name of the calling method as Timer Name
	 * If excludeCaller = true the direct caller of setTimerName will be ignored
	 * @throws Exception 
	 */
	public void setTimerName(boolean excludeCaller) throws Exception {
		// we walk through the stack trace and pick the first method from outside this class
		StackTraceElement[] stackTrace = new Exception().getStackTrace();
		String helperClassName = this.getClass().getName();
		for(int i = excludeCaller ? 2 : 1; i < stackTrace.length; i++) {
			StackTraceElement elem = stackTrace[i];
			if(elem.getClassName().equals(helperClassName)) continue;
			setTimerName(elem.getClassName(), elem.getMethodName());
			break;
		}
	}


	/***
	 * This helper method returns a modified URL that contains the current timer name as part of the URL
	 * Use this method when opening a new URL that should be tagged and in case setting the timer via JavaScript doesn't work
	 * @param url Input url, e.g.: http://www.google.com
	 * @return this would for instance return http://www.google.com?dtTimerName=myGoogleTimer
	 */
	public String getIEPerfTimerUrl(String url) {
		if (currentTimerName == null)
			return url;
		try {
			String escapedTimerName = URLEncoder.encode(currentTimerName, "UTF-8").replace("+", "%20");
			if (url.contains("?"))
				return url + "&dtTimerName=" + escapedTimerName;
			return url + "?dtTimerName=" + escapedTimerName;
		} catch (UnsupportedEncodingException e) {
			return url;
		}
	}

	/***
	 * This helper method returns a modified URL with the passed Timer Name as part of the URL
	 * Use this method when opening a new URL that should be tagged and in case setting the timer via JavaScript doesn't work
	 * @param url Input url, e.g.: http://www.google.com
	 * @return this would for instance return http://www.google.com?dtTimerName=myGoogleTimer
	 * @throws Exception 
	 */
	public String getIEPerfTimerUrl(String url, String ... timerNames) throws Exception {
		setTimerName(timerNames);

		if (currentTimerName == null)
			return url;
		try {
			String escapedTimerName = URLEncoder.encode(currentTimerName, "UTF-8").replace("+", "%20");
			if (url.contains("?"))
				return url + "&dtTimerName=" + escapedTimerName;
			return url + "?dtTimerName=" + escapedTimerName;
		} catch (UnsupportedEncodingException e) {
			return url;
		}
	}

	/**
	 * Opens a new URL and sets a new timer name with the calling method
	 * @param url
	 * @throws Exception 
	 */
	public void open(String url) throws Exception {
		enableJavaScriptInjection(false);
		setTimerName(true);
		enableJavaScriptInjection(true);

		if(iePerformAgentActive)
			openUrl(getIEPerfTimerUrl(url));
		else
			openUrl(url);
	}

	/**
	 * Opens a new URL
	 * Either uses the existing timername or creates a new one with the callers method name
	 * @param url
	 * @throws Exception 
	 */
	public void open(String url, boolean useExistingTimerName) throws Exception {
		if(ValidationHelper.isEmpty(currentTimerName) || !useExistingTimerName) {
			// when we browse to a new page we do not need to set the time via JavaScript - we do it via the URL
			enableJavaScriptInjection(false);
			setTimerName(true);
			enableJavaScriptInjection(true);
		}

		if(iePerformAgentActive)
			openUrl(getIEPerfTimerUrl(url));
		else
			openUrl(url);
	}


	/**
	 * Opens a new URL using the passed Timer Name
	 * @param url
	 * @throws Exception 
	 */
	public void open(String url, String ... timerNames) throws Exception {
		if(iePerformAgentActive)
			openUrl(getIEPerfTimerUrl(url, timerNames));
		else
			openUrl(url);
	}

	private static class TestRunIdProvider {
		private static String testrunId;

		protected static String getTestRunId() {
			if (testrunId == null) {
				testrunId = System.getProperty("testRunId");
				if (testrunId == null) {
					testrunId = "Run started at " + DateFormat.getDateTimeInstance().format(new Date());
				}
			}
			return testrunId;
		}
	}

}