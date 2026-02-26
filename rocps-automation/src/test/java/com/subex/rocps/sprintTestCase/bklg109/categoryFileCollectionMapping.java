package com.subex.rocps.sprintTestCase.bklg109;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class categoryFileCollectionMapping extends PSAcceptanceTest
{

	

	int colSize;
	int paramVal;
	PSGenericHelper genHelperObj = new PSGenericHelper();
	String clientPartition;
	Map<String, String> dpMap = null;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	

	public categoryFileCollectionMapping() throws Exception
	{

	}

	public void categoryFileCollection(String category,String fileCollectionConfig) throws Exception
	{

		try
		{
			NavigationHelper.navigateToScreen( "Reference Tables" );
			ComboBoxHelper.select( "displayString_gwt_uid_", "Category File Collection Mapping" );
			GenericHelper.waitForLoadmask();

			int rowCount = GridHelper.getRowCount( "gridPanel" );
			boolean dataExistFlag = false;
			for ( int i = 1; i <= rowCount; i++ )
			{
				ArrayList<String> existingData = GridHelper.getRowValues( "gridPanel", i );
				if ( ( existingData.get( 1 ).equals(category ) ))
				{
					if ( existingData.get( 2 ).equals( fileCollectionConfig ) )
					{

					
					dataExistFlag = true;
					
					Log4jHelper.logInfo( "File Upload Category and File Collection already configured" );
					
					break;
				}

			}
			}
				
			if ( dataExistFlag == false )
			{
				
				NavigationHelper.navigateToAction( "Common Tasks", "New" );
				GenericHelper.waitForLoadmask();
				ComboBoxHelper.select( "fileUploadCategory_pfucName_gwt_uid_", category );
				ComboBoxHelper.select( "fileCollection_fcnName_gwt_uid_", "VOD - File Collections_VOD" );
				GenericHelper.waitInSeconds( "5" );
				Log4jHelper.logInfo( "Configured Category is " +category );

				ButtonHelper.click( "Save" );

			}
			
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
