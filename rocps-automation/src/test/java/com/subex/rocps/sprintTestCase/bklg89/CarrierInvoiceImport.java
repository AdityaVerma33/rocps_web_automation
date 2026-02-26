package com.subex.rocps.sprintTestCase.bklg89;

import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class CarrierInvoiceImport extends PSAcceptanceTest
{

	String newCarrierInvoiceImport = "New Carrier Invoice Import";
	PSGenericHelper genHelperObj = new PSGenericHelper();
	String clientPartition;
	Map<String, String> carMap = null;

	public CarrierInvoiceImport()
	{

	}

	public void CIImportCreation() throws Exception
	{
		try
		{

			NavigationHelper.navigateToScreen( "Carrier Invoice Import" );

			initializeInstanceVariables();

			genHelperObj.clickNewAction( clientPartition );

			GenericHelper.waitForLoadmask();

			assertEquals( NavigationHelper.getScreenTitle(), newCarrierInvoiceImport );

			assertTrue( CheckBoxHelper.isEnabled( "//*[@id='pciiIsAutoschedule_InputElement']" ) );

			assertTrue( CheckBoxHelper.isChecked( "//*[@id='pciiIsAutoschedule_InputElement']" ) );

		}

		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public void initializeInstanceVariables()
	{

		clientPartition = carMap.get( "ClientPartition" );

	}

}
