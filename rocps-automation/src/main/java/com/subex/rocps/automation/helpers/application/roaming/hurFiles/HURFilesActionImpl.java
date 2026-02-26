package com.subex.rocps.automation.helpers.application.roaming.hurFiles;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.report.Log4jHelper;

public class HURFilesActionImpl extends PSAcceptanceTest
{
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSActionImpl psActionImpl = new PSActionImpl();

	/*
	 * This method is for click on 'Email HUR' action
	 */
	public void clickOnEmailHurAction() throws Exception
	{
		psActionImpl.clickOnAction( "Email HUR", "Email HUR" );
		ElementHelper.waitForElement( "PS_Detail_HurFiles_popupXpath", searchScreenWaitSec );
		String popupText = ElementHelper.getText( GenericHelper.getORProperty( "PS_Detail_HurFiles_popupXpath" ) );
		if ( popupText.contains( "Send Email Task sucessfully scheduled with Task Id" ) )
			Log4jHelper.logInfo( popupText );
		else if ( popupText.contains( "Failed to schedule" ) )
			Log4jHelper.logInfo( popupText );
		ButtonHelper.click( "OKButton" );
		psGenericHelper.waitforHeaderElement( "File Name" );
	}
}
