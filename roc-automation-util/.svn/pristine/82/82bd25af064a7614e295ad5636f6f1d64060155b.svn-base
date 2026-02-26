package com.subex.automation.helpers.performance.iePreformance;


import com.thoughtworks.selenium.CommandProcessor;
import com.thoughtworks.selenium.DefaultSelenium;


/**
 * In order for Timer Names to work correctly we need to override open and openWindow
 * The JavaScript injection of TimerNames only works once the browser starts requesting an URL. 
 * To also assign this first activity to a timername we need to pass the timer Name via the URL to the browser agent
 * @author andreas.grabner
 *
 */
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
        
    /**
     * Setting the TimerName, e.g.: from the SeleniumRunner by passing the TestMethod name 
     * @param timerNames
     * @throws Exception 
     */
    public void setTimerName(String ... timerNames) throws Exception {
    	iePerformSeleniumHelper.setTimerName(timerNames);
    }
    
    /**
     * Add additional timer names to the current timer hierarchy
     * @param addTimerNames
     * @throws Exception 
     */
    public void addTimerName(String... addTimerNames) throws Exception {
    	iePerformSeleniumHelper.addTimerName(addTimerNames);
    }
    
    /**
     * Removes the last specified Timer Name hierarchy
     * @throws Exception 
     */
    public void removeTimerName() throws Exception {
    	iePerformSeleniumHelper.removeTimerName();
    }
    
    /**
     * Clear the timer, e.g.: by the SeleniumRunner after a test method has finished
     * @throws Exception 
     */
    public void clearTimerName() throws Exception {
    	iePerformSeleniumHelper.clearTimerName();
    }

    /**
     * Need to override this method and add the timerName to the URL
     * This is a technical limitation as the JavaScript injection of the timername doesn't work until the browser starts requesting the first url
     */
    @Override
	public void open(String url) {   	
    	try {
			iePerformSeleniumHelper.setTimerName(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.open(iePerformSeleniumHelper.getIEPerfTimerUrl(url));
	}

    /**
     * Need to override this method and add the timerName to the URL
     * This is a technical limitation as the JavaScript injection of the timername doesn't work until the browser starts requesting the first url
     */
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