package com.subex.rocps.automation.helpers.application.admin.alertEvent;

import java.util.Map;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.utils.ExcelHolder;

public class OtherDetailsImpl {
	PSGenericHelper genObj = new PSGenericHelper();
	EmailConfigurationImpl emailObj = new EmailConfigurationImpl();
	AlertTextImpl alertTxtObj = new AlertTextImpl();
	AlertEvtOtherDetActImpl actionObj = new AlertEvtOtherDetActImpl();
	protected Map<String, String> map;
	String	emailConfiguration;
	String	emailColumn;
	String	emailJoiningTable;
	String	emailJoiningColumn;
	String	emailDisplayColumn;
	String	emailColumnHeader;
	String	emailSubject;
	String	emailHeader;
	String	emailFooter;
	String	alertJoiningTable;
	String	alertParameter;
	String	alertColumn;
	String	alertJoiningColumn;
	String	alertDisplayColumn;
	String	alertColumnHeader;
	String	actions;
	String	submitSqlValue;
	String	runProgramPath;
	String	runValue;
	String	component;

	
	
	public OtherDetailsImpl(Map<String,String> map) throws Exception{
		this.map = map;
		initializeVariables( map );
	}
	public void switchToOtherDetailsTab() throws Exception{
		TabHelper.gotoTab( "//div[@id='topPanelContainer']//div[text()='Other Details']" );
		GenericHelper.waitForLoadmask();
	}
	
	public void completeOtherDetails() throws Exception{
		emailObj.emailGrid(emailColumn, emailJoiningTable, emailJoiningColumn, emailDisplayColumn, emailColumnHeader);
		emailObj.message(emailHeader, emailFooter, emailSubject);
		alertTxtObj.switchToAlertTextTab();
		alertTxtObj.fieldInformationConfiguration(alertParameter, alertColumn, alertJoiningTable, alertJoiningColumn, alertDisplayColumn);
		actionObj.switchToActionsTab();
		actionObj.submitSql(submitSqlValue);
		actionObj.runProgram(runProgramPath, runValue);
		actionObj.components(component);
	}
	

	public void initializeVariables(Map<String,String> detMap) throws Exception{
		emailColumn=ExcelHolder.getKey(detMap, "EmailColumn");
		emailJoiningTable=ExcelHolder.getKey(detMap, "EmailJoiningTable");
		emailJoiningColumn=ExcelHolder.getKey(detMap, "EmailJoiningColumn");
		emailDisplayColumn=ExcelHolder.getKey(detMap, "EmailDisplayColumn");
		emailColumnHeader=ExcelHolder.getKey(detMap, "EmailColumnHeader");
		emailSubject=ExcelHolder.getKey(detMap, "EmailSubject");
		emailHeader=ExcelHolder.getKey(detMap, "EmailHeader");
		emailFooter=ExcelHolder.getKey(detMap, "EmailFooter");
		alertJoiningTable=ExcelHolder.getKey(detMap, "AlertJoiningTable");
		alertParameter=ExcelHolder.getKey(detMap, "AlertParameter");
		alertColumn=ExcelHolder.getKey(detMap, "AlertColumn");
		alertJoiningColumn=ExcelHolder.getKey(detMap, "AlertJoiningColumn");
		alertDisplayColumn=ExcelHolder.getKey(detMap, "AlertDisplayColumn");
		alertColumnHeader=ExcelHolder.getKey(detMap, "AlertColumnHeader");
		actions=ExcelHolder.getKey(detMap, "Actions");
		submitSqlValue=ExcelHolder.getKey(detMap, "SubmitSqlValue");
		runProgramPath=ExcelHolder.getKey(detMap, "RunProgramPath");
		runValue=ExcelHolder.getKey(detMap, "RunValue");
		component=ExcelHolder.getKey(detMap, "Component");

	}
	
	
}
