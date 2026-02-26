package com.subex.rocps.automation.helpers.application.prepayments.prepayments;

import com.subex.rocps.automation.helpers.application.filters.AdvanceSearchFiltersHelper;
import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class PrePaymentsSearchImpl extends PSAcceptanceTest
{
	GridFilterSearchHelper gridHelperObj = new GridFilterSearchHelper();
	PSGenericHelper genericHelperObj = new PSGenericHelper();
	AdvanceSearchFiltersHelper advSearchObj = new AdvanceSearchFiltersHelper();
	
	public boolean prePaymentsSearch(String billProfile, String transactionReference, String createdBy, String account) throws Exception
	{
		boolean flag = false;
		if(ValidationHelper.isNotEmpty( account ))		
			advSearchObj.accountAdvanceSearch( "Account", account );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "SearchButton" );	
		//genericHelperObj.waitforHeaderElement( "Transaction Reference" );
		SearchGridHelper.gridFilterSearchWithTextBox( "PS_Detail_prepayments_transactionRef_txtID", transactionReference, "Transaction Reference" );
		gridHelperObj.billProfileAdvanceFilter( "SearchGrid", "Bill Profile", billProfile );
		
		if(ValidationHelper.isNotEmpty( createdBy ))
			ComboBoxHelper.select( "PS_Detail_prepayments_createdBy_GridTxtID", createdBy );
		
		int row = GridHelper.getRowNumberContains( "SearchGrid", billProfile, "Bill Profile" );
		
		if ( row > 0 )
		{
			flag = true;
		}
		return flag;
			
	}

}
