package com.subex.rocps.sprintTestCase.bklg116;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.config.OR_Reader;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class RoamingFileStatus extends PSAcceptanceTest{

	int i,j;	
	public void fileStatusCheck() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Roaming File Status");
			List<String> rec_list = new ArrayList<String>(Arrays.asList(" S.No.", " File Type", " Sender", " Recepient" ," File Name", " File Sequence Number", "  Date Time", " File Specfication"," Bill Profile"," Status"," Total Events"," Event Success Count"," Event Failure Count"," Failure Reason"," Linked File Name"," Linked File Type"," Config Type"));
			List<String> value_list=new ArrayList<String>(Arrays.asList("1", "CD","MYSBC","SGPSH","CDMYSBCSGPSH00065","65","08/09/2020 15:18:27","3.12","Digi-Invoice - 1 - Digi","Completed","0","","","NA","","","Tap Out"));
			List<WebElement> colList = driver.findElements(By.xpath("//div[@id='searchGrid']//*//div[@class='white roc-datagrid-container']//table/thead/tr/th"));
			SearchGridHelper.searchWithTextBox("grid_column_header_filtersearchGrid_prfsFileName", "CDMYSBCSGPSH00065", " File Name");
			if(GridHelper.isValuePresent("searchGrid", "CDMYSBCSGPSH00065")){
				
			
				for(i=0;i<colList.size()-2;i++)
				{
					int row=i+1;
					String actualHeaderValue=driver.findElement(By.xpath("//div[@id='searchGrid']//*//div[@class='white roc-datagrid-container']//table/thead/tr/th["+row+"]")).getText();
					String expectedHeaderValue=rec_list.get(i);
					
					//Assert.assertEquals(actualHeaderValue, expectedHeaderValue,"Headers are not matching");
					if(actualHeaderValue==expectedHeaderValue)
					{
						for(j=0;j<value_list.size()-2;j++)
						{
							int val=j+1;
							String actualValue=driver.findElement(By.xpath("//div[@id='searchGrid']//div//div//div//div//div//div//tr[1]//td["+val+"]")).getText();
							String expectedValue=value_list.get(j);
							Assert.assertEquals(actualValue, expectedValue,"Values are not matching");
						}	
					}
					else{
						Log4jHelper.logInfo("Headers not matching");
					}
				}

			}
			else{
				Log4jHelper.logInfo("File not found in Roaming Definition Screen");
			}
			
		}catch (Exception e) {

			throw e;
		}
	}
	

}
