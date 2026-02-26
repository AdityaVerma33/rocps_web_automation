package com.subex.rocps.automation.helpers.application.approvalworkflows.approvalworkflows;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class ApprovalWorkFlowDetailImpl extends PSAcceptanceTest
{
	protected PSGenericHelper genHelperObj = new PSGenericHelper();
	
	public void basicDetails(String approvalName, String duration, String durationDays, String approvalTeam, String deafultApprovalUser) throws Exception
	{
		
		TextBoxHelper.type( "paltApprovalStageName", approvalName );
		TextBoxHelper.type( "expiresInPaltDueDttmMultiplier", duration );
		ComboBoxHelper.select( "expiresInPaltDueMultiplierFreq_gwt_uid_", durationDays );
		ComboBoxHelper.select( "apprTeam_gwt_uid_", approvalTeam );
		ComboBoxHelper.select( "currApprUserTbl_gwt_uid_", deafultApprovalUser );
		
	}
	
	public void notificationDetails(String notifyEmail, String teams, String users) throws Exception
	{
		
		String[] teamsArr = teams.split(secondLevelDelimiter);
		String[] usersArr = users.split( secondLevelDelimiter);
		
		if(ValidationHelper.isTrue(notifyEmail ))
			CheckBoxHelper.check( "paltEmailNotifyFl_InputElement" );
		for (int j = 0; j < teamsArr.length; j++) {		
			genHelperObj.dualListSelectionWrapper( teamsArr[j] ,"notifyTeamConfig" ,"availableTeamList");
		}
		
		for (int j = 0; j < usersArr.length; j++) {		
			genHelperObj.dualListSelectionWrapper(usersArr[j],"notifyUserConfig", "availableUserList" );
		}
	}
	
	public void advancedConfigurations(String allowObjCreator, String allowNotification) throws Exception
	{
		if(ValidationHelper.isTrue( allowObjCreator ))
			CheckBoxHelper.check( "pawfDenyPrivilegeFl_InputElement" );
		if(ValidationHelper.isTrue( allowNotification ))
			CheckBoxHelper.check( "pawfNotifyAllFl_InputElement" );
		
	}
	
	public void saveApprovalWorkflow(String name) throws Exception
	{
		/*ButtonHelper.click("approvalWorkflowDetail.save");
		GenericHelper.waitForLoadmask();
		assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ));*/
		genHelperObj.detailSave( "approvalWorkflowDetail.save", name, "Name" );
	
	}
}
