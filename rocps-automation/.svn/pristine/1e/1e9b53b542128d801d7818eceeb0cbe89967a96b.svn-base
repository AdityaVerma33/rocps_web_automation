package com.subex.rocps.automation.helpers.application.pdfToExcel.pdfToExcelConversion;

import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class PDFToExcelConversionImpl extends PSAcceptanceTest
{
	PSGenericHelper genObj = new PSGenericHelper();
	public void clickOnSave() throws Exception
	{
		ButtonHelper.click( "PS_Detail_pdfExlConv_saveButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	public void headerDetails(String name, String template, String pdfFile, String pdfPath) throws Exception
	{
		TextBoxHelper.type( "PS_Detail_pdfExlConv_name", name );
		ComboBoxHelper.select( "PS_Detail_pdfExlConv_template", template );
		ButtonHelper.click( "PS_Detail_pdfExlConv_pdfFilename" );
		PSGenericHelper.psFileUploadSikuli( "PS_Detail_pdfExlConv_uploadTrigger",automationPath+ configProp.getProperty( pdfPath) + pdfFile,"fileTypeFU1.png","openButtonFU1.png" );
		ButtonHelper.click( "PS_Detail_pdfExlConv_uploadButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForClickableElement( "PS_Detail_pdfExlConv_convertButton", 5 );
		ButtonHelper.click( "PS_Detail_pdfExlConv_convertButton" );
		ButtonHelper.click( "PS_Detail_pdfExlConv_convertButton" );

	}

	public boolean validateConversion(String name) throws Exception
	{
		
		return genObj.isDataPresent( name, "Name" );

	}


}
