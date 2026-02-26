package com.subex.rocps.sprintTestCase.bklg141New;

import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.componentHelpers.LabelElementHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class dispute extends PSAcceptanceTest
{

	String dpName;

	int colSize;
	int paramVal;
	PSGenericHelper genHelperObj = new PSGenericHelper();
	String clientPartition;
	Map<String, String> dpMap = null;

	public dispute() throws Exception
	{

	}

	public void disputediscrepancies(String columnHeadername, int dpvalue) throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Dispute" );
	
			GenericHelper.waitForLoadmask();
			SearchGridHelper.gridFilterAdvancedSearch( "template","MultiCI_WSS","Carrier Invoice Template");
			GenericHelper.waitForLoadmask();
			filterStatus();
			GenericHelper.waitForLoadmask();
	      
			
			GridHelper.clickRow("searchGrid", 1, 1);
			//GridHelper.clickRow("search-filter-grid-container",1,1);
			NavigationHelper.navigateToAction( or.getProperty( "CommonTasks" ), or.getProperty( "Edit" ));
		
			//GridHelper.scrollDown( gridId, firstVisibleRowNo, fullScroll );
		    //ElementHelper.scrollOneLevelDown("//div[@id='disputeDetailsLabel']",1);
			WebElement element = LabelElementHelper.getElement("disputeTblDetail");
					MouseHelper.scrollAndClick( element );
			ButtonHelper.click( or.getProperty("Dispute"));
			GenericHelper.waitForLoadmask();
			GenericHelper.waitInSeconds("20");
			
			//GridHelper.scrollDown( "disputeTblDetail", "1", "Invoice(Rate)" );
			//GridHelper.clickCell("disputeTblDetail",1,GridHelper.getColumnNumber( "disputeTblDetail", columnHeadername ) );
			//ButtonHelper.click(" Invoice(Rate)");
			//GenericHelper.waitForLoadmask();
			
			
	        
	        
			 
			ArrayList<String> values = GridHelper.getColumnValues( "disputeTblDetail",columnHeadername );
			//ArrayList<String> values = GridHelper.getColumnValues( "discrepancyGrid","Rate");
			
			
			int ExpectedDecimalPrecision = dpvalue;
			for ( int i = 0; i < values.size(); i++ )
			{
				int integerPlaces = values.get( i ).indexOf( '.' )+1;
				int decimalPlaces = values.get( i ).length() - integerPlaces;
				System.out.println( " UI value : " + columnHeadername + " = "+ values.get( i )  );
				assertEquals( decimalPlaces, ExpectedDecimalPrecision );
			}   
	      
		}
	
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	public void filterStatus() throws Exception{
	    ElementHelper.click("//div[@id='grid_column_header_filtersearchGrid_statusCode$pscdDisplay']");
	    ComboBoxHelper.select("statusCode_gwt_uid_","Draft");
	    ButtonHelper.click("search");
	}
       
	public void initializeInstanceVariables()
	{

		clientPartition = dpMap.get( "ClientPartition" );

	}
}
