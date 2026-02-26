package com.subex.rocps.sprintTestCase.bklg109;

import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class fileUpload extends PSAcceptanceTest
{

	int colSize;
	int paramVal;
	PSGenericHelper genHelperObj = new PSGenericHelper();
	String clientPartition;
	Map<String, String> dpMap = null;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	public fileUpload() throws Exception
	{
                 
		
		
		
		
	}

	public void file(String category,String fileCollName) throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "File Upload" );
			GenericHelper.waitForLoadmask();
			NavigationHelper.navigateToAction( "File Upload", "File Upload" );
			ComboBoxHelper.select("fileUploadCategory_gwt_uid_", category);
			ComboBoxHelper.select( "fileCollection_gwt_uid_",fileCollName);
			
			
			ButtonHelper.click("//div[@id='trigger-scucFilePath']");
			FileHelper.fileUploadRobot( "//div[@class='roc-trigger roc-fileupload-trigger']", "E:\\Demo_Aug2.txt" );
			GenericHelper.waitInSeconds( "5" );
			
			ButtonHelper.click( "//button[@id='FileUpload-upload']" );
			GenericHelper.waitInSeconds( "5" );
			
			

			ButtonHelper.click( "//button[@id='fileUploadDetail.save']" );
			System.out.println( "File Uploaded successfully" );
		}

		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public void initializeInstanceVariables()
	{

		clientPartition = dpMap.get( "ClientPartition" );

	}
}
