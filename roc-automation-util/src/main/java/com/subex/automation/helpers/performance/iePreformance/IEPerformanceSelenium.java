package com.subex.automation.helpers.performance.iePreformance;

// Entire class commented out — depends on Selenium RC (com.thoughtworks.selenium)
// which was removed in Selenium 4.x. This class is unused by the rest of the codebase.

/*
import com.thoughtworks.selenium.CommandProcessor;
import com.thoughtworks.selenium.DefaultSelenium;


/**
 * In order for Timer Names to work correctly we need to override open and openWindow
 * The JavaScript injection of TimerNames only works once the browser starts requesting an URL.
 * To also assign this first activity to a timername we need to pass the timer Name via the URL to the browser agent
 * @author andreas.grabner
 *
 */
/*
@SuppressWarnings("deprecation")
public class IEPerformanceSelenium extends DefaultSelenium {

	private IEPerformanceSeleniumHelper iePerformSeleniumHelper;

	public IEPerformanceSelenium(String host, int port, String browser, String startUrl) {
        super(host, port, browser, startUrl);

        iePerformSeleniumHelper = new IEPerformanceSeleniumHelper(this);
    }

    public IEPerformanceSelenium(CommandProcessor commandProcessor) {
        super(commandProcessor);

        iePerformSeleniumHelper = new IEPerformanceSeleniumHelper(this);
    }

    public void setTimerName(String ... timerNames) throws Exception {
    	iePerformSeleniumHelper.setTimerName(timerNames);
    }

    public void addTimerName(String... addTimerNames) throws Exception {
    	iePerformSeleniumHelper.addTimerName(addTimerNames);
    }

    public void removeTimerName() throws Exception {
    	iePerformSeleniumHelper.removeTimerName();
    }

    public void clearTimerName() throws Exception {
    	iePerformSeleniumHelper.clearTimerName();
    }

    @Override
	public void open(String url) {
    	try {
			iePerformSeleniumHelper.setTimerName(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.open(iePerformSeleniumHelper.getIEPerfTimerUrl(url));
	}

	@Override
	public void openWindow(String url, String windowID) {
		try {
			iePerformSeleniumHelper.setTimerName(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.openWindow(iePerformSeleniumHelper.getIEPerfTimerUrl(url), windowID);
	}
}
*/
