package com.subex.rocps.automation.helpers.application.carrierinvoice;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoiceemailconfig.CarrierInvoiceEmailConfigDetailImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class CarrierInvoiceEmailConfig extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> citempExcel = null;
	protected Map<String, String> citempMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String partition;
	protected String name;
	protected String streamStage;
	protected String emailID;
	protected String emailSubject;
	protected String maxAttachment;
	protected String attachmentName;
	String regex = new PSStringUtils().regexFirstLevelDelimeter();	
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	@Test
	public CarrierInvoiceEmailConfig( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		citempExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( citempExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public CarrierInvoiceEmailConfig( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		citempExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( citempExcel );
		colSize = excelHolderObj.totalColumns();
	}
	
	/*
	 * Configuring the carrier Invoice Email Config
	 * 
	 */
	public void carrierInvoiceEmailConfigCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Carrier Invoice Email Config" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				citempMap = excelHolderObj.dataMap( paramVal );

				initializeVariables( citempMap );
				boolean isCIEmailConfigPresent = genericHelperObj.isGridTextValuePresent( "piefName", name, "Name");
				if(!isCIEmailConfigPresent)
					newCarrierInvoiceEmailConfig();
				else
					Log4jHelper.logInfo( "Carrier Invoice Email Config" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	
	public void newCarrierInvoiceEmailConfig() throws Exception
	{
		CarrierInvoiceEmailConfigDetailImpl detailObj = new CarrierInvoiceEmailConfigDetailImpl();
		detailObj.newCIEmailConfig(partition);
		detailObj.basicConfig(name, streamStage);
		detailObj.addEmailConfig(emailID,emailSubject,maxAttachment,attachmentName);
		detailObj.saveInvoiceEmailConfig(name);	
		
	}
	/*
	 * This method is for search screen column validation
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Carrier Invoice Email Config" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			citempMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( citempMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			String[] searchGridColumnsArr = searchScreenColumns.split( regex, -1 );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericHelperObj.totalColumns( excelColumnNames );
		}

	}	
	
	public void editCarrierInvoiceEmailConfig() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Carrier Invoice Email Config" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				citempMap = excelHolderObj.dataMap( paramVal );

				initializeVariables( citempMap );
				boolean isCIEmailConfigPresent = genericHelperObj.isGridTextValuePresent( "piefName", name, "Name");
				if(isCIEmailConfigPresent)
				{
					int row = GridHelper.getRowNumber( "SearchGrid", name, "Name" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					assertEquals( TextBoxHelper.getValue( "piefName"), name );
					if(ValidationHelper.isNotEmpty( streamStage ))
						ComboBoxHelper.select( "streamStage_gwt_uid_", streamStage );
					CarrierInvoiceEmailConfigDetailImpl detailObj = new CarrierInvoiceEmailConfigDetailImpl();
					detailObj.addEmailConfig( emailID, emailSubject, maxAttachment, attachmentName );
					detailObj.saveInvoiceEmailConfig( attachmentName );
					Log4jHelper.logInfo( "Carrier Invoice Email Config is edited successfully." );
				}
				else
					Log4jHelper.logInfo( "Carrier Invoice Email Config is not avaibale" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	
	/* This method is for Carrier Invoice Email Config delete
	 */
	public void carrierInvoiceEmailConfigDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Carrier Invoice Email Config" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			citempMap = excelHolderObj.dataMap( paramVal );

			partition = ExcelHolder.getKey( citempMap, "Partition" );
			name = ExcelHolder.getKey( citempMap, "Name" );
			genericHelperObj.selectPartitionFilter( partition, "Non Deleted Items" );
			boolean isCIEmailConfigPresent = genericHelperObj.isGridTextValuePresent( "piefName", name, "Name" );

			if ( isCIEmailConfigPresent )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Delete" );
				genericHelperObj.selectPartitionFilter( partition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "Carrier Invoice Email Config is deleted successfully :" + name );
			}
			else
				Log4jHelper.logInfo( "Carrier Invoice Email Config is not available with :" + name );
		}
	}

	/*
	 * This method is for Carrier Invoice Email Config un delete
	 */
	public void carrierInvoiceEmailConfigUnDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Carrier Invoice Email Config" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			citempMap = excelHolderObj.dataMap( paramVal );
			partition = ExcelHolder.getKey( citempMap, "Partition" );
			name = ExcelHolder.getKey( citempMap, "Name" );
			genericHelperObj.selectPartitionFilter( partition, "Deleted Items" );

			boolean isCIEmailConfigPresent = genericHelperObj.isGridTextValuePresent( "piefName", name, "Name" );

			if ( isCIEmailConfigPresent )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Undelete" );
				genericHelperObj.selectPartitionFilter( partition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "Carrier Invoice Email Config is un deleted successfully :" + name );
			}
			else
				Log4jHelper.logInfo( "Carrier Invoice Email Config is not available with :" + name );
		}

	}
	public void initializeVariables(Map<String, String> map) throws Exception
	{
		name = ExcelHolder.getKey( map, "Name" );
		partition = ExcelHolder.getKey( map, "Partition" );
		streamStage = ExcelHolder.getKey( map, "StreamStage" );
		emailID = ExcelHolder.getKey( map, "EmailID" );
		emailSubject = ExcelHolder.getKey( map, "EmailSubject" );
		maxAttachment = ExcelHolder.getKey( map, "MaxAttachment" );		
		attachmentName = ExcelHolder.getKey( map, "AttachmentName" );
	}
}
