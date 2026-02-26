package com.subex.rocps.sprintTestCase.bklg76;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
//import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.config.OR_Reader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class ProductBundleDrillDown extends PSAcceptanceTest {
	
	OR_Reader orData = new OR_Reader();
	String path;
	
	int i;
	public ProductBundleDrillDown(String path)throws Exception{
		this.path = path;
	}

	public void checkRecurring() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Product Bundle Drill-Down");
			GenericHelper.waitForLoadmask();
			GenericHelper.waitInSeconds("1");	
			//ComboBoxHelper.select("//*[starts-with(@id,'productScreen_gwt_uid')]","//li[@title='Recurring Item Instance']");
			ElementHelper.click(or.getProperty("Select_combo"));
			GenericHelper.waitInSeconds("1");
			ElementHelper.click(or.getProperty("Select_recurring"));
			GenericHelper.waitInSeconds("1");
			ButtonHelper.click(or.getProperty("Search_button"));
			GenericHelper.waitForLoadmask();
			System.out.println(NavigationHelper.getScreenTitle());
			Thread.sleep(5000);
			List<String> rec_list = new ArrayList<String>(Arrays.asList(" Account", " Account Ref", " Name", " From" ," To", " Bill Profile", " Units", " Bill Text"," External Reference"," Product Argument Type"));
			List<WebElement> colList = driver.findElements(By.xpath("//div[@id='searchGrid']//*//div[@class='white roc-datagrid-container']//table/thead/tr/th"));
			
			for(i=0;i<colList.size()-2;i++)
			{
				//System.out.println(driver.findElement(By.xpath("//div[@id='searchGrid']//*//div[@class='white roc-datagrid-container']//table/thead/tr/th["+i+"]")).getText());
			 	//int j=i-1;
				int row=i+1;
				String actualValue=driver.findElement(By.xpath("//div[@id='searchGrid']//*//div[@class='white roc-datagrid-container']//table/thead/tr/th["+row+"]")).getText();
				String expectedValue=rec_list.get(i);
				//System.out.println(driver.findElement(By.xpath("//div[@id='searchGrid']//*//div[@class='white roc-datagrid-container']//table/thead/tr/th[10]/div[1]/div/div/div")).getText());
				System.out.println(expectedValue);
				System.out.println(actualValue);
				Assert.assertEquals(actualValue, expectedValue,"Values are not matching");
			 	//System.out.println(rec_list.get(j));

	}


			
			Assert.assertEquals(NavigationHelper.getScreenTitle(), "Recurring Item Inst...");
			

			
	}
		
		
	 catch (Exception e) {

		throw e;
	}
		
	}
	public void checkOneOff() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Product Bundle Drill-Down");
			GenericHelper.waitForLoadmask();
			GenericHelper.waitInSeconds("2");	
			//ComboBoxHelper.select("//*[starts-with(@id,'productScreen_gwt_uid')]","//li[@title='Recurring Item Instance']");
			ElementHelper.click(or.getProperty("Select_combo"));
			GenericHelper.waitInSeconds("2");
			ElementHelper.click(or.getProperty("Select_oneOff"));
			GenericHelper.waitInSeconds("2");
			ButtonHelper.click(or.getProperty("Search_button"));
			GenericHelper.waitForLoadmask();
			System.out.println(NavigationHelper.getScreenTitle());
			Assert.assertEquals(NavigationHelper.getScreenTitle(), "One Off Item Instan...");
			List<String> one_list = new ArrayList<String>(Arrays.asList(" Account", " Account Ref", " Name", " Date" ," Amount", " Currency", " Bill Profile", " Bill Text"," External Reference"));
			List<WebElement> colList = driver.findElements(By.xpath("//div[@id='searchGrid']//*//div[@class='white roc-datagrid-container']//table/thead/tr/th"));
			for(i=1;i<colList.size()-2;i++)
			{
				//System.out.println(driver.findElement(By.xpath("//div[@id='searchGrid']//*//div[@class='white roc-datagrid-container']//table/thead/tr/th["+i+"]")).getText());
			 	//int j=i-1;
				int row=i+1;
				String actualValue=driver.findElement(By.xpath("//div[@id='searchGrid']//*//div[@class='white roc-datagrid-container']//table/thead/tr/th["+row+"]")).getText();
				String expectedValue=one_list.get(i);
				Assert.assertEquals(actualValue, expectedValue,"Values are not matching");
			 	
			}
			
	}
		
		
	 catch (Exception e) {

		throw e;
	}
		
	}
	
}
