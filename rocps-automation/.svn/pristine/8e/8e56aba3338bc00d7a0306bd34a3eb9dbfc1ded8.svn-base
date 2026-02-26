package com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoiceemailconfig;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;

public class CarrierInvoiceEmailConfigDetailImpl extends PSAcceptanceTest
{

	PSGenericHelper genricObj = new PSGenericHelper();

	public void newCIEmailConfig( String partition ) throws Exception
	{
		genricObj.clickNewAction( partition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "New Carrier Invoice Email Config" );

	}

	public void basicConfig( String name, String streamStage ) throws Exception
	{
		TextBoxHelper.type( "piefName", name );
		ComboBoxHelper.select( "streamStage_gwt_uid_", streamStage );

	}

	public void addEmailConfig( String emailID, String emailSubject, String maxAttachment, String attachmentName ) throws Exception
	{
		String regex = new PSStringUtils().regexFirstLevelDelimeter();
		String[] emailIDArr = emailID.split( regex, -1 );
		String[] emailSubjectArr = emailSubject.split( regex, -1 );
		String[] maxAttachmentArr = maxAttachment.split( regex, -1 );
		String[] attachmentNameArr = attachmentName.split( regex, -1 );

		for ( int row = 0; row < emailIDArr.length; row++ )
		{
			int rowno = GridHelper.getRowNumber( "carrierInvoiceEmailParsGrid", emailIDArr[row], "Email Id" );
			if(rowno == 0)
			{
			ButtonHelper.click( "emailConfigGridToolbar.Add" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );

			GridHelper.updateGridTextBox( "carrierInvoiceEmailParsGrid", "emailIdEditor", row + 1, "Email Id", emailIDArr[row] );
			GridHelper.updateGridTextBox( "carrierInvoiceEmailParsGrid", "emailSubjectEditor", row + 1, "Email Subject", emailSubjectArr[row] );
			if( ValidationHelper.isNotEmpty( maxAttachment ) && ValidationHelper.isNotEmpty( maxAttachmentArr[row] ))
				GridHelper.updateGridTextBox( "carrierInvoiceEmailParsGrid", "attSizeEditor", row + 1, "Max Attachment", maxAttachmentArr[row] );
			GridHelper.updateGridTextBox( "carrierInvoiceEmailParsGrid", "attNameEditor", row + 1, "Attachment Name", attachmentNameArr[row] );
			}
			else
			{
				assertEquals( GridHelper.getCellValue( "carrierInvoiceEmailParsGrid", rowno, "Email Id" ), emailIDArr[row] );
				assertEquals( GridHelper.getCellValue( "carrierInvoiceEmailParsGrid", rowno, "Email Subject" ), emailSubjectArr[row] );
				assertEquals( GridHelper.getCellValue( "carrierInvoiceEmailParsGrid", rowno, "Attachment Name" ), attachmentNameArr[row] );
			}
		}

	}

	public void saveInvoiceEmailConfig( String name ) throws Exception
	{
		/*ButtonHelper.click( "carrierInvoiceEmailConDetail.save" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ) );*/
		genricObj.detailSave( "carrierInvoiceEmailConDetail.save",  name, "Name" );
		Log4jHelper.logInfo( "Carrier Invoice Email Config is created successfully for :" + name );
	}

}
