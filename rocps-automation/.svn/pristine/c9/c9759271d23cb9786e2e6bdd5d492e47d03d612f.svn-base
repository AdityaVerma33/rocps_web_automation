package com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn;

import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class ReportAndExtDefnEmail extends PSAcceptanceTest
{
	protected Map<String, String> reportAndExtDefnEmailMap = null;
	protected String repAndExtEmailContactName;
	protected String repAndExtEmailAddress;
	protected String repAndExtEmailType;
	protected String repAndExtEmailContactNameArr[];
	protected String repAndExtEmailAddressArr[];
	protected String repAndExtEmailTypeArr[];
	PSStringUtils psStringUtils = new PSStringUtils();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();

	public ReportAndExtDefnEmail( Map<String, String> reportAndExtDefnEmailMap ) throws Exception
	{
		this.reportAndExtDefnEmailMap = reportAndExtDefnEmailMap;
		initializeVariable( reportAndExtDefnEmailMap );
	}

	/*This method is for initialize variable*/
	protected void initializeVariable( Map<String, String> map ) throws Exception
	{
		repAndExtEmailContactName = ExcelHolder.getKey( map, "EmailContactName" );
		repAndExtEmailAddress = ExcelHolder.getKey( map, "EmailAddress" );
		repAndExtEmailType = ExcelHolder.getKey( map, "EmailType" );

		if ( ValidationHelper.isNotEmpty( repAndExtEmailContactName ) )
			repAndExtEmailContactNameArr = psStringUtils.stringSplitFirstLevel( repAndExtEmailContactName );
		if ( ValidationHelper.isNotEmpty( repAndExtEmailAddress ) )
			repAndExtEmailAddressArr = psStringUtils.stringSplitFirstLevel( repAndExtEmailAddress );
		if ( ValidationHelper.isNotEmpty( repAndExtEmailType ) )
			repAndExtEmailTypeArr = psStringUtils.stringSplitFirstLevel( repAndExtEmailType );
	}

	/*This method is for navigation to report and extract email tab*/
	public void repAndExtEmailTabConfig() throws Exception
	{
		TabHelper.gotoTab( GenericHelper.getORProperty( "PS_Detail_reportAndExtDefn_email_emailTabXpath" ) );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertTrue( psDataComponentHelper.isTabSelected( "Email" ), "Email tab is not selected in Reports and Extracts Definition" );
		repAndExtEmailGridConfig();
	}

	/*This method is for configure report and extract email details*/
	private void repAndExtEmailGridConfig() throws Exception
	{
		try
		{
			String gridId = GenericHelper.getORProperty( "PS_Detail_reportAndExtDefn_email_selectEmailGridId" );
			for ( int i = 0; i < repAndExtEmailAddressArr.length; i++ )
			{
				int row = i + 1;
				ButtonHelper.click( "PS_Detail_reportAndExtDefn_email_add_btnId" );
				if ( ValidationHelper.isNotEmpty( repAndExtEmailContactNameArr[i] ) )
					GridHelper.updateGridTextBox( gridId, "PS_Detail_reportAndExtDefn_email_contactName_txtId", row, "Contact  Name", repAndExtEmailContactNameArr[i] );
				if ( ValidationHelper.isNotEmpty( repAndExtEmailAddressArr[i] ) )
					GridHelper.updateGridTextBox( gridId, "PS_Detail_reportAndExtDefn_email_emailAddress_txtId", row, "Email  Address", repAndExtEmailAddressArr[i] );
				if ( ValidationHelper.isNotEmpty( repAndExtEmailTypeArr[i] ) )
					GridHelper.updateGridComboBox( gridId, "PS_Detail_reportAndExtDefn_email_emailType_comboId", row, "Email  Type", repAndExtEmailTypeArr[i] );
			}
		}
		catch ( Exception e )
		{
			Log4jHelper.logError( "Exception-" + e );
		}

	}

}
