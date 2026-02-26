package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.products.IPMeasuremConfigurationHelper;
import com.subex.rocps.automation.helpers.application.referenceTable.UploadFileType;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCIPMeasurementConfig extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "IPMeasurementConfiguration";

	@Test( priority = 1, enabled = true, description = "'IP Measurement Configuration'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			IPMeasuremConfigurationHelper ipConfigurationHelper = new IPMeasuremConfigurationHelper( path, workBookName, sheetName, "IPmeasurementConfigScreencolVal" );
			ipConfigurationHelper.ipMeasConfigurationColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'IP Measurement Configuration'  creation with Average Component" )
	public void iPMeasurementConfigCreationWithAvgComp() throws Exception
	{
		try
		{
			IPMeasuremConfigurationHelper ipConfigurationHelper = new IPMeasuremConfigurationHelper( path, workBookName, sheetName, "IPmeasurementConfigCreation_withAvgComponent" );
			ipConfigurationHelper.ipMeasConfigurationCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "'IP Measurement Configuration'  creation with Percentile Component" )
	public void iPMeasurementConfigCreationWithPercenComp() throws Exception
	{
		try
		{
			IPMeasuremConfigurationHelper ipConfigurationHelper = new IPMeasuremConfigurationHelper( path, workBookName, sheetName, "IPmeasurementConfigCreation_withPercentileComponent" );
			ipConfigurationHelper.ipMeasConfigurationCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 4, enabled = true, description = "'IP Measurement Configuration'  creation with Volume Component" )
	public void iPMeasurementConfigCreationWithVolumeComp() throws Exception
	{
		try
		{
			IPMeasuremConfigurationHelper ipConfigurationHelper = new IPMeasuremConfigurationHelper( path, workBookName, sheetName, "IPmeasurementConfigCreation_withVolumeComponent" );
			ipConfigurationHelper.ipMeasConfigurationCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 5, enabled = true, description = "'IP Measurement Configuration'  edit" )
	public void iPMeasurementConfigurationEdit() throws Exception
	{
		try
		{
			IPMeasuremConfigurationHelper ipConfigurationHelper = new IPMeasuremConfigurationHelper( path, workBookName, sheetName, "IPmeasurementConfigEdit" );
			ipConfigurationHelper.ipMeasConfigurationEdit();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 6, enabled = true, description = "'IP Measurement Configuration'  delete" )
	public void iPMeasurementConfigurationDelete() throws Exception
	{
		try
		{
			IPMeasuremConfigurationHelper ipConfigurationHelper = new IPMeasuremConfigurationHelper( path, workBookName, sheetName, "IPmeasurementConfigDelete" );
			ipConfigurationHelper.ipMeasConfigurationDelete();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 7, enabled = true, description = "'IP Measurement Configuration'  undelete" )
	public void iPMeasurementConfigurationUnDelete() throws Exception
	{
		try
		{
			IPMeasuremConfigurationHelper ipConfigurationHelper = new IPMeasuremConfigurationHelper( path, workBookName, sheetName, "IPmeasurementConfigUnDelete" );
			ipConfigurationHelper.ipMeasConfigurationUnDelete();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
