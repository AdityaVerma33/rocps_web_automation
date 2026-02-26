package com.subex.rocps.automation.helpers.application.deal;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;

public class BilateralResult extends PSAcceptanceTest
{
	protected String account;
	protected String bilateralModelling;
	protected String from;
	protected String to;
	protected String bilateralBandGroup;
	protected String direction;
	protected String colHeader;
	protected String results;

	protected Map<String, String> map;
	PSStringUtils strObj = new PSStringUtils();
	PSGenericHelper genericObj = new PSGenericHelper();

	public BilateralResult( Map<String, String> map ) throws Exception
	{
		this.map = map;
		initialiseVariables( map );
	}

	public void viewResults() throws Exception
	{
		
		assertTrue( ElementHelper.isElementPresent( "//*[@id='filterpanel-header']//*[text()='Bilateral Modelling']" ), "Bilateral Result Search Screen did not open" );
		filterOperations();
		if(!results.isEmpty())
		{
			DataVerificationHelper verifyObj = new DataVerificationHelper();
		verifyObj.validateData( "grid_column_header_searchGrid_", map, "SearchGrid", colHeader, results );
		}
		Log4jHelper.logInfo( "Bilateral results hs been validated sucessfully" );
		
	}
	
	public void filterOperations() throws Exception
	{
		genericObj.collapsableXpath();
		ComboBoxHelper.select( "brdModelling_gwt_uid_", bilateralModelling );
		/*if(ValidationHelper.isNotEmpty( from ))
			TextBoxHelper.type( "fromDttm", from );
		if(ValidationHelper.isNotEmpty( to ))
			TextBoxHelper.type( "toDttm", to );*/
		
		if(ValidationHelper.isNotEmpty( direction ))
		{
			ComboBoxHelper.select( "brdDirection_gwt_uid_", direction );
		
		String bilateralBG = ComboBoxHelper.getValue( "deal_gwt_uid_" );
		bilateralBG = bilateralBandGroup + " - " +bilateralBG +  " - " +direction;
		bilateralBandGroup = bilateralBG; 
		
		if(ValidationHelper.isNotEmpty( bilateralBandGroup ))
			ComboBoxHelper.select( "dealBandGroup_gwt_uid_", bilateralBandGroup );
		}
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}
	
	public void initialiseVariables(Map<String , String> map) throws Exception
	{		
		account = ExcelHolder.getKey( map, "Account" );		
		from =  ExcelHolder.getKey( map, "From Date" );
		to =  ExcelHolder.getKey( map, "To Date" );
		colHeader =  ExcelHolder.getKey( map, "ColHeader" );
		results =  ExcelHolder.getKey( map, "Results" );
		bilateralModelling = ExcelHolder.getKey( map, "BilateralModelling" );
		bilateralBandGroup = ExcelHolder.getKey( map, "BilateralBandGroup" );
		direction = ExcelHolder.getKey( map, "Direction" );		
	}
	
}
