package com.subex.rocps.automation.helpers.application.roaming.nrtrdeFlStatus;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class NRTRDEFlStatusActionImpl extends PSAcceptanceTest
{

	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSActionImpl psActionImpl = new PSActionImpl();
	
	/*Thismethod is for Click on View Records action
	 *
	 */
	public void clickOnViewRecordsAction() throws Exception
	{
		psActionImpl.clickOnAction( "View", "View Records", "PS_Detail_NrtrdeRecordScreen_contextHeader_xpath" );
	}
}
