package com.subex.rocps.sprintTestCase.bklg77;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
//import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.config.OR_Reader;


public class PrePaidBalance extends PSAcceptanceTest {
	
	OR_Reader orData = new OR_Reader();
	String path;
	public PrePaidBalance(String path)throws Exception{
		this.path = path;
	}

	public void prepaidBalance() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Prepaid Balance");
			GenericHelper.waitForLoadmask();
			Actions action = new Actions(driver);
			//WebElement element = driver.findElement(By.xpath("//*[@id='searchGrid']/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div[2]/div/div/table/tbody/tr[1]/td[1]/div/label"));
			WebElement element = ElementHelper.getElement(or.getProperty("Select_firstRow"));
			//action.doubleClick(element).perform();
			action.doubleClick(element).perform();
			GenericHelper.waitForLoadmask();
			//String PopUpTitle= driver.findElement(By.xpath("//div[@id='popupWindow']//div[@class='gwt-HTML roc-window-title']")).getText();
			System.out.println(NavigationHelper.getScreenTitle());
			Assert.assertEquals(NavigationHelper.getScreenTitle(), "Pre-Payment Balance History Search");
		
			//String  RowPresent = GridHelper.clickRow("searchGrid", 1, 3);
			
			Assert.assertEquals(isRowPresent(), true);
			
			
	
	}
		
		
	 catch (Exception e) {

		throw e;
	}
		
	}
	protected boolean isRowPresent() throws Exception {
		WebElement row = ElementHelper.getElement(or.getProperty("Select_popUpFirstRow"));
		try{
		if(row.getText() != null)
		{
			return true;
		}
		else {
			return false;
		}
		}
		catch (Exception e) {

			throw e;
		}
	}
}
