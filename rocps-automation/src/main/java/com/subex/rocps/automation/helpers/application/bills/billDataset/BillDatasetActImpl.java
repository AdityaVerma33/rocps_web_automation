package com.subex.rocps.automation.helpers.application.bills.billDataset;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class BillDatasetActImpl extends PSAcceptanceTest
{

	PSActionImpl psActionImpl = new PSActionImpl();
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	/* This method is used to click on 'New' action in 'Bill Dataset' */
	public void clickNewAction( String clientPartition ) throws Exception
	{
		psActionImpl.clickNewAction( clientPartition, "//*[@class='gwt-field-label' and text()='Dataset  Name']" );

	}

	/* This method is used to click on action in 'Bill Dataset' */
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		psActionImpl.clickOnAction( parentActionNm, childActionNm, "//*[@class='gwt-field-label' and text()='Dataset  Name']" );
	}

}
