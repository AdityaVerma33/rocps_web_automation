package com.subex.automation.helpers.application;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class BrowserHelper extends AcceptanceTest {

	public void refreshWithoutCheck() throws Exception {
		try {
			Robot key = new Robot();
			key.keyPress(KeyEvent.VK_ESCAPE);
			
			driver.navigate().refresh();
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void refresh() throws Exception {
		try {
			ROCHelper rocHelper = new ROCHelper();
			rocHelper.handleFailures();
			
			refreshWithoutCheck();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void back() throws Exception {
		try {
			
			driver.navigate().back();
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void forward() throws Exception {
		try {
			
			driver.navigate().forward();
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void launchURL(String url) throws Exception {
		try {
			
			driver.get( url );
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}