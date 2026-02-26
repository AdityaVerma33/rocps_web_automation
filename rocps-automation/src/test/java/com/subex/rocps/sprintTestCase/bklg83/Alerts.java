package com.subex.rocps.sprintTestCase.bklg83;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.config.OR_Reader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class Alerts extends PSAcceptanceTest{
	OR_Reader orData = new OR_Reader();
	protected static String clientPartition;
	PSGenericHelper genericHelperObj = new PSGenericHelper();
	String alertEventName = "Auto-alert4";
	String alertEventGroup="Dispute Validation";
	String userName = "usr_name ( UserTbl )";
	String userForeName = "usr_forename ( UserTbl )";
	String userSurName= "usr_surname ( UserTbl )";
	String alertEventType = "Entity Added/Deleted Alert Event";
	String guidedEntity="DisputeTbl";
	String alertDefinition ="dispute";
	String userNameLabel = "User Name";
	String userForeNameLabel = "User Forename";
	String userSurNameLabel="User Surname";
	String subject = "test subject";
	String header="hi";
	String footer="regards";
	@Test
	public Alerts()throws Exception{
		
		
	}
	public void checkAlert() throws Exception{
		try {
			NavigationHelper.navigateToScreen("Alert Event");
			GenericHelper.waitForLoadmask();
			
			boolean isAlertEventPresent = genericHelperObj.isGridTextValuePresent("paevName",alertEventName, "Name");
			if(!isAlertEventPresent){
			
			navigateToAction("Common Tasks","New", alertEventType );
		
	
			
			GenericHelper.waitForLoadmask();
			TextBoxHelper.type("alertEvent.paevName", alertEventName);
			ComboBoxHelper.select("alertEvent_alertEventGroup_gwt_uid_",alertEventGroup );
			//Select the guided entity and the alert definition
			EntityComboHelper.select(or.getProperty("EntityCombo_Icon"), "Entity Search", guidedEntity,"Entity");
			ComboBoxHelper.select(or.getProperty("PS_Select_AlertDfn"), alertDefinition);
			GenericHelper.waitInSeconds("2");
			
			//Navigate to other details tab and add the row in email grid
			
			TabHelper.gotoTab("PS_Select_OtherDetails");
			
			ElementHelper.click(or.getProperty("PS_Select_Add"));
			ElementHelper.click(or.getProperty("PS_Select_Add"));
			ElementHelper.click(or.getProperty("PS_Select_Add"));
			GridHelper.clickRow("emailGrid", 1, 1);
			GridHelper.clickRow("emailGrid",  1, 1);
			//check if the user related fields are available for selection
			if(ComboBoxHelper.containsValue("columnEditor_gwt_uid_",userName ) && ComboBoxHelper.containsValue("columnEditor_gwt_uid_",userForeName ) && ComboBoxHelper.containsValue("columnEditor_gwt_uid_",userSurName ))
			{
				System.out.println("User fields are availabe for selection");
				ComboBoxHelper.select("PS_Email_Grid_Cell",userName);
				
				selectUserField(2, 1, userForeName);
				
				selectUserField(3, 1, userSurName);
				
				insertColumnHeader(1,userNameLabel);
				insertColumnHeader(2,userForeNameLabel);
				insertColumnHeader(3,userSurNameLabel);
						
				TextAreaHelper.type("alertEvent.alertEmailInfo.emailSubString",subject );
				TextAreaHelper.type("alertEvent.alertEmailInfo.emailHeaderString",header );
				TextAreaHelper.type("alertEvent.alertEmailInfo.emailFooterString",footer );
				ButtonHelper.click("//button[@id='entAddDelAlertEvtDetail.save']");
				GenericHelper.waitForLoadmask();
				
			}
			else{
				Log4jHelper.logInfo("User fields are not availabe for selection");
				Assert.assertTrue(false);
			}
			
			}
			else{
				Log4jHelper.logInfo("Alert event is already available with name " + alertEventName);
			}
						
			}
			catch (Exception e) {
			throw e;
		}
		
	}
	public void checkSavedAlert() throws Exception{
		try{
			NavigationHelper.navigateToScreen("Alert Event");
			GenericHelper.waitForLoadmask();
			SearchGridHelper.gridFilterSearchWithTextBox("paevName", alertEventName, "Name");
			GridHelper.clickRow("searchGrid", 1, 1);
			ElementHelper.click(or.getProperty("GroupActionName").replace("actionName", "Common Tasks"));
			ElementHelper.click(or.getProperty("SubMenuActionName").replace("actionName", "Edit"));
			GenericHelper.waitForLoadmask();
			TabHelper.gotoTab("PS_Select_OtherDetails");

			String actualUserNameField=GridHelper.getCellValue("email",1,1);
			String actualUserForeNameField=GridHelper.getCellValue("email",2,1);
			String actualUserSurNameField=GridHelper.getCellValue("email",3,1);
			System.out.println("the field values selected are: "+ actualUserNameField +"\n"+actualUserForeNameField+"\n"+actualUserSurNameField);
		
			
		
			Assert.assertEquals(userName, actualUserNameField);
			Assert.assertEquals(userForeName, actualUserForeNameField);
			Assert.assertEquals(userSurName,actualUserSurNameField);
		}
		catch (Exception e) {
			

			throw e;
		}	
		
	}
	public void navigateToAction(String parent, String child, String subchild) throws Exception{
		try{
		ElementHelper.click(or.getProperty("GroupActionName").replace("actionName", parent));
		ElementHelper.click(or.getProperty("SubMenuActionName").replace("actionName", child));
		ElementHelper.click(or.getProperty("SubSubMenuActionName").replace("actionName", subchild));
		}
		catch (Exception e) {
			

			throw e;
		}	
		
	}
	public void selectUserField(int rowNum, int columnNum, String userField) throws Exception{
		try{
			GridHelper.clickRow("emailGrid", rowNum, columnNum);
			GridHelper.clickRow("emailGrid",  rowNum, columnNum);
			ComboBoxHelper.select("columnEditor_gwt_uid_",userField);
			
		}
		catch (Exception e) {
			

			throw e;
		}	
	}
	public void insertColumnHeader(int rowNum, String userField) throws Exception{
		try{
			
			GridHelper.clickRow("email", rowNum, " Column  Header");
			GridHelper.clickRow("email", rowNum, " Column  Header");
	
			TextBoxHelper.type("grid_cell_popuppanel", userField);
			
		}
		catch (Exception e) {
			

			throw e;
		}	
	}
}