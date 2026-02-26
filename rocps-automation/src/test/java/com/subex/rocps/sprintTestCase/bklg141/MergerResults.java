package com.subex.rocps.sprintTestCase.bklg141;

import org.testng.annotations.Test;

import java.util.ArrayList;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.config.OR_Reader;
import com.subex.automation.helpers.report.Log4jHelper;

public class MergerResults extends PSAcceptanceTest {
	OR_Reader orData = new OR_Reader();
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	String decimalPrecision="4";
	int expectedDecimalPlace=4;
	String columnHeadername=" Rate";
	String columnName="sss_rate";
	String tableName="mbrd_merged_tbl";
	String billProfile="Delta Inv BP";
	String dateMerger="05/01/2020 00:00:59";
	String dpName="Merger-dec";
	
	ArrayList<String> values=new ArrayList<String>();
	public MergerResults()throws Exception{
		
	}
	public void configureDecimalPrecision() throws Exception{
		try{
			NavigationHelper.navigateToScreen("Decimal Precision");
			GenericHelper.waitForLoadmask();
			boolean isDecimalPrecisionPresent = genericHelperObj.isGridTextValuePresent("tableDfn$tbdName", tableName, "Table Name");
			if (!isDecimalPrecisionPresent) {
				NavigationHelper.navigateToAction("Common Tasks", "New");
				GenericHelper.waitForLoadmask();
				TextBoxHelper.type(or.getProperty("decimalPrecisionName"),dpName );
				ElementHelper.click(or.getProperty("PS_Config_Name"));
				GenericHelper.waitForLoadmask();
				TextBoxHelper.type(or.getProperty("PS_TBL_Name"), tableName );
				ElementHelper.click("//button[@id='search']");
				GenericHelper.waitForLoadmask();
				
				GridHelper.clickRow("searchGrid", 1, 1);
				ElementHelper.click("//button[@id='ok']");
				GenericHelper.waitForLoadmask();
				GenericHelper.waitInSeconds("2");
				
				int rowNumber = GridHelper.getRowNumber("columnGrid", columnName, "Column  Name");
				GridHelper.clickRow("columnGrid", rowNumber, 4);
				
				TextBoxHelper.clear(or.getProperty("PS_DP_Text"));
				TextBoxHelper.type(or.getProperty("PS_DP_Text"), decimalPrecision);
				
				GenericHelper.waitInSeconds("2");
				ButtonHelper.click("decimalPrecisionDetail.save");
				GenericHelper.waitForLoadmask();
				
						
				
			} else {
				
				SearchGridHelper.gridFilterSearchWithTextBox("tableDfn$tbdName", tableName, "Table Name");
				GridHelper.clickRow("searchGrid", 1, 1);
				NavigationHelper.navigateToAction("Common Tasks", "Edit");
				
				GenericHelper.waitForLoadmask();
				int rowNumber = GridHelper.getRowNumber("columnGrid", columnName, "Column  Name");
				
				String cellValue=GridHelper.getCellValue("columnGrid", rowNumber, 4);
				expectedDecimalPlace = Integer.parseInt(cellValue);
				Log4jHelper.logInfo("Decimal Precision is availabe with decimal precision " + expectedDecimalPlace);
			}
			
		}
		catch (Exception e) {

			throw e;
		}
		}
	public void checkMergerResult() throws Exception{
		try{
			NavigationHelper.navigateToScreen("Bilateral Rated Details Modelling");
			GenericHelper.waitForLoadmask();
			GridHelper.clickRow("searchGrid", "BRD", "Name");
			
			NavigationHelper.navigateToAction("Merger Actions", "View Results");
			GenericHelper.waitForLoadmask();
			CalendarHelper.setBeforeDate(or.getProperty("PS_Merger_BeforeDate"), dateMerger);
			
			GenericHelper.waitForLoadmask();
			ElementHelper.click(or.getProperty("PS_Merger_AdvancedSearch"));
			GenericHelper.waitForLoadmask();
			SearchGridHelper.gridFilterSearchWithTextBox("pbipName", billProfile, "Bill Profile Name");
			GenericHelper.waitForLoadmask();
			
			GridHelper.clickRow("popupWindow","searchGrid",billProfile, "Bill Profile Name");
			
	
			ButtonHelper.click("ok");
			GenericHelper.waitForLoadmask();
			//GenericHelper.waitInSeconds("5");
			ButtonHelper.click("search");
			GenericHelper.waitForLoadmask();
			
			values = GridHelper.getColumnValues( "searchGrid", columnHeadername );
			
			System.out.println("the values"+values);
			for ( int i = 1; i < values.size(); i++ )
			{
				int integerPlaces = values.get( i ).indexOf( '.' )+1;
				int decimalPlaces = values.get( i ).length() - integerPlaces;
				System.out.println( " UI value : " + columnHeadername + " = "+ values.get( i )  );
				assertEquals( decimalPlaces, expectedDecimalPlace );
			}  
		}
		catch (Exception e) {

			throw e;
		}
		}
	

}
