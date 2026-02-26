package com.subex.rocps.automation.helpers.application.carrierinvoice.invoicereconciliationrequest;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class InvoiceReconciliationRequestDetailImpl extends PSAcceptanceTest
{
	GridFilterSearchHelper gridHelperObj = new GridFilterSearchHelper();
	protected String billProfile;
	protected String invoiceReconConfig;
	protected String description;
	protected String select;
	protected String steps;
	protected String status;
	protected String percentage;
	protected String lowerThresholdPercen;
	protected String upperThresholdPercen;
	protected String absoluteVal;
	protected String lowerThreshold;
	protected String upperThreshold;
	protected String carrierInvoiceTemplate;
	protected String carrierInvoicePeriod;
	String[] selectArr;
	String[] stepsArr;
	String[] statusArr;
	String[] percentageArr;
	String[] lowerThresholdPercenArr;
	String[] upperThresholdPercenArr;
	String[] absoluteValArr;
	String[] lowerThresholdArr;
	String[] upperThresholdArr;
	PSStringUtils stringObj = new PSStringUtils();
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	
	protected Map<String, String> map;
	public InvoiceReconciliationRequestDetailImpl( Map<String, String> map ) throws Exception
	{
		this.map = map;
		initialiseVariables( map );
		initalizeArray();
	}
	
	
	/*
	 * This method is for recon request detail
	 */
	public void reconRequestDetailConfig() throws Exception
	{
		String entityXpath = "//*[@id='invoiceReconRequestDetail']//div[contains(@id,'trigger')]";
		if(!ElementHelper.isElementPresent( entityXpath))
			ElementHelper.waitForElement( entityXpath, 120 );
		EntityComboHelper.clickEntityIcon( "carrierInvoice" );
		gridHelperObj.billProfileAdvanceFilter( "SearchGrid", "Bill Profile", billProfile );
		
		if ( !carrierInvoicePeriod.isEmpty() )
			CalendarHelper.setOnDate( "PS_SearchFilter_BillAction_date_calenderID", carrierInvoicePeriod );
		SearchGridHelper.gridFilterAdvancedSearch( "invoiceTemplate", carrierInvoiceTemplate, "Template" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "SearchGrid", 1, "Bill Profile" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );	
		ButtonHelper.click( "OKButton" );	
		GenericHelper.waitForLoadmask( searchScreenWaitSec );		
		
		
		if(!ElementHelper.isElementPresent( entityXpath))
			ElementHelper.waitForElement( entityXpath, 120 );
		EntityComboHelper.clickEntityIcon( "invoiceReconciliation" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		SearchGridHelper.gridFilterAdvancedSearch( "invoiceTemplate", carrierInvoiceTemplate, "Template" );
		GridHelper.clickRow( "Detail_popUpWindowId", "SearchGrid", 1, 1);
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );	
		Thread.sleep( 1000 );
		TextBoxHelper.type( "pirrDesc", description );		
	}
	/*
	 * This method is for recon steps 
	 */
	public void reconSteps() throws Exception
	{
		for(int row = 0; row<stepsArr.length;row++)
		{
			GridHelper.updateGridCheckBox( "invoiceReconStepReqGrid", row+1, "Select", selectArr[row] );
			assertEquals( GridHelper.getCellValue("invoiceReconStepReqGrid", row+1, "Steps" ), stepsArr[row] );
			
			if(ValidationHelper.isTrue( percentageArr[row] )&&  !CheckBoxHelper.isChecked( "grid_check_editor" ))
			{
				CheckBoxHelper.isChecked( "grid_check_editor" );
				GridHelper.updateGridCheckBox( "invoiceReconStepReqGrid", row+1, "Percentage", percentageArr[row] );
				GridHelper.updateGridTextBox( "invoiceReconStepReqGrid", "prsrMinPerThresholdEditor", row+1, "Lower  Threshold(%)", lowerThresholdPercenArr[row] );
				GridHelper.updateGridTextBox( "invoiceReconStepReqGrid", "prsrMaxPerThresholdEditor", row+1, "Upper  Threshold(%)", upperThresholdPercenArr[row] );
			}
			if(ValidationHelper.isTrue( absoluteValArr[row] ) &&  !CheckBoxHelper.isChecked( "//*[@id='invoiceReconStepReqGrid']//img" ))
			{
				
				GridHelper.updateGridCheckBox( "invoiceReconStepReqGrid", row+1, "Absolute", absoluteValArr[row] );
				GridHelper.updateGridTextBox( "invoiceReconStepReqGrid", "prsrMinAbsThresholdEditor", row+1, "Lower  Threshold", lowerThresholdPercenArr[row] );
				GridHelper.updateGridTextBox( "invoiceReconStepReqGrid", "prsrMaxAbsThresholdEditor", row+1, "Upper  Threshold", upperThresholdPercenArr[row] );
			}
		}
	}
	
	/*
	 * This method is to save recon request
	 */
	public void saveInvoiceReconRequest() throws Exception
	{
		ButtonHelper.click( "invoiceReconRequestDetail.save" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitForElementToDisappear("invoiceReconRequestDetail.save", searchScreenWaitSec);
		GenericHelper.waitForLoadmask(detailScreenWaitSec);		
		//ButtonHelper.click("SearchButton");
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
	}
	
	/*
	 * This method is to view recon results
	 */
	public void reconViewResults(String overBilled, String underBilled, String equallyBilled, String showSystemFields) throws Exception
	{				
		if(ValidationHelper.isFalse( overBilled ) )
			CheckBoxHelper.uncheck( "Over Billed_InputElement" );
		if(ValidationHelper.isFalse( underBilled ) )
			CheckBoxHelper.uncheck( "Under Billed_InputElement" );
		
		if(ValidationHelper.isTrue( equallyBilled ) )
			CheckBoxHelper.check( "Equally Billed_InputElement" );
		if(ValidationHelper.isTrue( showSystemFields ) )
			CheckBoxHelper.check( "Show System Fields_InputElement" );
		
	}
	public void initalizeArray() throws Exception
	{
		selectArr = stringObj.stringSplitFirstLevel( select );
		stepsArr = stringObj.stringSplitFirstLevel( steps );
		statusArr = stringObj.stringSplitFirstLevel( status );
		percentageArr = stringObj.stringSplitFirstLevel( percentage );
		lowerThresholdPercenArr = stringObj.stringSplitFirstLevel( lowerThresholdPercen );
		upperThresholdPercenArr = stringObj.stringSplitFirstLevel( upperThresholdPercen );
		absoluteValArr = stringObj.stringSplitFirstLevel( absoluteVal );
		lowerThresholdArr = stringObj.stringSplitFirstLevel( lowerThreshold );
		upperThresholdArr = stringObj.stringSplitFirstLevel( upperThreshold );

	}
	
	public void initialiseVariables(Map<String, String> map) throws Exception
	{
		
		billProfile = ExcelHolder.getKey( map, "BillProfile" );
		invoiceReconConfig = ExcelHolder.getKey( map, "InvoiceReconConfig" );
		carrierInvoiceTemplate = ExcelHolder.getKey( map, "CITemplate" );
		description = ExcelHolder.getKey( map, "Description" );
		select = ExcelHolder.getKey( map, "Select" );
		steps = ExcelHolder.getKey( map, "Steps" );
		status = ExcelHolder.getKey( map, "Status" );
		percentage = ExcelHolder.getKey( map, "Percentage" );
		lowerThresholdPercen = ExcelHolder.getKey( map, "LowerThresholdPercen" );
		upperThresholdPercen = ExcelHolder.getKey( map, "UpperThresholdPercen" );
		absoluteVal = ExcelHolder.getKey( map, "AbsoluteVal" );
		lowerThreshold = ExcelHolder.getKey( map, "LowerThreshold" );
		upperThreshold = ExcelHolder.getKey( map, "UpperThreshold" );
		carrierInvoicePeriod = ExcelHolder.getKey( map, "CarrierInvoicePeriod" );
	}
}
