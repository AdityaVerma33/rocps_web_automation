package com.subex.rocps.sprintTestCase.bklg82;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class ThresholdLegends extends PSAcceptanceTest {
	
	Map <String, String> threshLegMap = null;
	String name = null;
	String operator1= null;
	String value1=null;
	String operator2=null;
	String value2= null;
	String type=null;
	String subject = null;
	String numberOfLegends = null;
	String emailID = null;
	String bodyPath=null;
	String fileName=null;
	
	
	public ThresholdLegends(Map <String, String> threshLegMap ){
		this.threshLegMap = threshLegMap;
		initialiseVariables();
	}

		

	protected void initialiseVariables() {
		name = threshLegMap.get("Name");
		operator1 = threshLegMap.get("Operator1");
		value1=threshLegMap.get("Value1");
		operator2 = threshLegMap.get("Operator2");
		value2=threshLegMap.get("Value2");
		type=threshLegMap.get("Type");
		subject = threshLegMap.get("Subject");
		bodyPath= threshLegMap.get("BodyPath");
		emailID = threshLegMap.get("EmailId");
		fileName = threshLegMap.get("File");
		
	}
	
	
	protected void enterData() throws Exception{
		try{		GridHelper.clickRow(or.getProperty("Currency_Grid"), 2, 1);
					ButtonHelper.click("Grid_Delete");
					GridHelper.clickRow(or.getProperty("Currency_Grid"), 2, 1);
					ButtonHelper.click("Grid_Delete");
					GridHelper.clickRow(or.getProperty("Currency_Grid"), 1, 1);
					GridHelper.clickRow(or.getProperty("Currency_Grid"), 1, 1);
					TextBoxHelper.type(or.getProperty("Name_Box"), name);
					GridHelper.clickRow(or.getProperty("Currency_Grid"), 1, 3);
					GridHelper.clickRow(or.getProperty("Currency_Grid"), 1, 3);
					ComboBoxHelper.select("Operator1_Box", operator1);
					GridHelper.clickRow(or.getProperty("Currency_Grid"), 1, 4);
					GridHelper.clickRow(or.getProperty("Currency_Grid"), 1, 4);
					TextBoxHelper.type(or.getProperty("Value1_Box"), value1);
					GridHelper.clickRow(or.getProperty("Currency_Grid"), 1, 7);
					GridHelper.clickRow(or.getProperty("Currency_Grid"), 1, 7);
					ButtonHelper.click("Notification_Box");
					emailAlertNotification();
					
		}
		catch(Exception e){
			throw e;
		}
		
	}
	
	protected void emailAlertNotification() throws Exception{
		try{
			
			ButtonHelper.click("EmailDetails_Add");
			TextBoxHelper.type(or.getProperty("EmailDetails_Type"),type);
			TextBoxHelper.type(or.getProperty("EmailDetails_Subject"), subject);
			ButtonHelper.click(or.getProperty("EmailDetails_Body"));       
			FileHelper.fileUploadRobot("File_Trigger",bodyPath);
			ButtonHelper.click("FileUpload_Button");
			ButtonHelper.click("EmailIdDetails_Add");
			GridHelper.clickRow(or.getProperty("EmailIdDetails_Grid"), 1, 2);
			GridHelper.clickRow(or.getProperty("EmailIdDetails_Grid"), 1, 2);
			TextBoxHelper.type(or.getProperty("EmailIdDetails_Email"), emailID);
			ButtonHelper.click("EmailDetails_Ok");
			ButtonHelper.click("Notification_Ok");
			ButtonHelper.click("AmountThreshold_Save");
			GenericHelper.waitForLoadmask();
			
			
		}
		catch (Exception e){
			throw e;
		}
		
		
		
	}
	
	protected void downloadTemplate() throws Exception{
		try{
			GridHelper.clickRow(or.getProperty("EmailDetails_Grid"), 1, 1);
			ButtonHelper.click("EmailDetails_Download");

			
		}
		catch(Exception e){
			throw e;
			
		}
	}
	
	protected boolean isDownloadSuccesfull() throws Exception{
		try{
			String path = System.getProperty("downloadDirectory")+"\\"+fileName;
			File file = new File (path);
			if(file.exists())
				return true;
			else
				return false;
			
			
		}
		catch(Exception e){
			throw e;
		}
	}
	
}
