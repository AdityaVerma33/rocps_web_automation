package com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoiceimportsearch;

import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.tariffs.ratesheettemplate.RateSheetImportTemplateImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class CarrierInvoiceImportDetailImpl extends PSAcceptanceTest
{
	protected String isdleta;
	protected String fileName;
	protected String templateName;
	protected String billProfile;
	protected String from;
	protected String to;
	protected String priorCarrierInvoice;
	protected String autoSchedule;
	protected String clientPartition;
	protected String carrierInvoicePeriod;
	protected String priorCarrierInvoiceStatus;
	PSGenericHelper genricObj = new PSGenericHelper();
	Map<String, String> map = null;
	
	public CarrierInvoiceImportDetailImpl( Map<String, String> map ) throws Exception
	{
		this.map = map;
		initializeVariables( map );

	}

	public void newCIImportRequest() throws Exception
	{
		genricObj.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		//assertEquals( NavigationHelper.getScreenTitle(), "New Carrier Invoice Import" );		
	}
	/*
	 * this method id to config carrier invoice import
	 */
	public void configCarrierInvoiceImport() throws Exception
	{
		
		String filePath = automationPath + configProp.getProperty( "carrierInvoiecPath" ) + fileName;
		
		
		ButtonHelper.click( "trigger-scucFilePath" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		
		PSGenericHelper.psFileUploadSikuli("FileUpload_Browse", filePath);
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		ButtonHelper.click( "FileUpload-upload" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		
		
		PSEntityComboHelper.selectUsingGridFilterTextBox( "invoiceTemplate", "Carrier Invoice Template Search", "pintName", templateName, "Name" );
		PSEntityComboHelper.selectUsingGridFilterTextBox( "billProfile", "Bill Profile Search", "pbipName", billProfile, "Bill Profile Name" );
		TextBoxHelper.type( "pciiFromDttm", date( from ) );
		TextBoxHelper.type( "pciiToDttm", date( to ) );
		
		if(ValidationHelper.isFalse( autoSchedule ))
			CheckBoxHelper.uncheck( "pciiIsAutoschedule_InputElement" );		
		
		if(ValidationHelper.isTrue( isdleta ))
		{
			EntityComboHelper.clickEntityIcon( "priorCarrierInvoice" );
			GridFilterSearchHelper gridHelperObj = new GridFilterSearchHelper();
			gridHelperObj.billProfileAdvanceFilter( "SearchGrid", "Bill Profile", priorCarrierInvoice );
			if(!carrierInvoicePeriod.isEmpty())
				genricObj.setDate("dummyValidOnDataProperty", carrierInvoicePeriod );				
			if(!priorCarrierInvoiceStatus.isEmpty())
				SearchGridHelper.gridFilterSearchWithComboBox(  "statusCode_gwt_uid_", priorCarrierInvoiceStatus, "Status" );
			GridHelper.clickRow( "SearchGrid", 1, "Bill Profile" );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			CheckBoxHelper.check( "pciiIsDelta_InputElement" );
		}
		if ( ValidationHelper.isNotEmpty( priorCarrierInvoice ) )
		{		
			EntityComboHelper.clickEntityIcon( "priorCarrierInvoice" );
			GridFilterSearchHelper gridHelperObj = new GridFilterSearchHelper();
			gridHelperObj.billProfileAdvanceFilter( "SearchGrid", "Bill Profile", priorCarrierInvoice );
			if(!carrierInvoicePeriod.isEmpty())
				genricObj.setDate("dummyValidOnDataProperty", carrierInvoicePeriod );				
			if(!priorCarrierInvoiceStatus.isEmpty())
				SearchGridHelper.gridFilterSearchWithComboBox(  "statusCode_gwt_uid_", priorCarrierInvoiceStatus, "Status" );
			GridHelper.clickRow( "SearchGrid", 1, "Bill Profile" );
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );	
			
		}
		
	}
	/*
	 * This method is to remove the hr:min:ss from date
	 */
	private String date( String date )
	{
		String dateVal = null;
		if ( date.contains( "00:00:00" ) )
			dateVal = date.replace( "00:00:00", "" );
		else
			dateVal = date;

		return dateVal;
	}
	/*
	 * This  method is to save carrier invoice import request
	 */
	public void saveCarrierInvoiceImportRequest() throws Exception
	{
		ButtonHelper.click( "ciImportDetail.save" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		String rejectMsg = "Selected Prior CI will be Rejected on click of OK button";
		String replaceMsg = "Selected Prior CI will be Replaced on click of OK button";
		String taskmsg = "Carrier Invoice Import Task is Scheduled with Task id";
		if(PopupHelper.isTextPresent( "window-scroll-panel", rejectMsg ) || PopupHelper.isTextPresent( "window-scroll-panel", replaceMsg ))
		{
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}
		if(PopupHelper.isPresent("window-scroll-panel"))
		{
			String actualMsg = LabelHelper.getText( "window-scroll-panel" );
			if(actualMsg.contains( taskmsg ))
			{
				ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			}
		}
		assertTrue( GridHelper.isValuePresent( "SearchGrid", templateName, "Template" ) );
		Log4jHelper.logInfo( "carrier Invoice import request is saved successfuly" );
	}

	public void initializeVariables(Map<String, String> map) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		isdleta = ExcelHolder.getKey( map, "Isdelta" );
		fileName = ExcelHolder.getKey( map, "FileName" );
		templateName = ExcelHolder.getKey( map, "TemplateName" );
		billProfile = ExcelHolder.getKey( map, "BillProfile" );
		from = ExcelHolder.getKey( map, "From" );
		to = ExcelHolder.getKey( map, "To" );
		priorCarrierInvoice = ExcelHolder.getKey( map, "PriorCarrierInvoice" );
		autoSchedule = ExcelHolder.getKey( map, "AutoSchedule" );
		priorCarrierInvoiceStatus = ExcelHolder.getKey( map, "PriorCarrierInvoiceStatus" );
		carrierInvoicePeriod = ExcelHolder.getKey( map, "CarrierInvoicePeriod" );		
	}
	/*
	 * This method is for error validation
	 */
	public void errorValidation() throws Exception
	{
		String oneormoreMsg = "One or more mandatory fields has to be filled and cannot be empty or invalid. ";
		String dateError = "From Date should be less than To Date ";
		List<String> errorLst = new ArrayList<String>();
		errorLst.add( oneormoreMsg );
		errorLst.add( dateError );
		String actualMsg = LabelHelper.getText( "errorText" );
		if(errorLst.contains( actualMsg ))
			FailureHelper.failTest( "Unable to save due to mandatory fields missing" );

	}
}
