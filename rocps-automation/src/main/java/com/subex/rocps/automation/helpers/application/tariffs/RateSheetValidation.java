package com.subex.rocps.automation.helpers.application.tariffs;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.tariffs.ratesheetimportrequest.BandValidationImpl;
import com.subex.rocps.automation.helpers.application.tariffs.ratesheetimportrequest.RateValidationImpl;
import com.subex.rocps.automation.helpers.application.tariffs.ratesheetimportrequest.TariffValidationImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class RateSheetValidation extends PSAcceptanceTest
{

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> ratesheetValExcel = null;
	protected Map<String, String> ratesheetValMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;

	String tariffName;
	String tariffClassName;
	String bands;
	String tariffcolHeaders;
	String tariffresults;
	String fromElements;
	String toElements;
	String bandcolHeaders;
	String bandresults;
	String bandValidation;
	String rateValidation;
	Map<String,String> configMap = null;
	protected Map<String, String> mapObj = null;
	String regex = new PSStringUtils().regexFirstLevelDelimeter();

	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	public RateSheetValidation( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		ratesheetValExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( ratesheetValExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public RateSheetValidation( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		ratesheetValExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( ratesheetValExcel );
		colSize = excelHolderObj.totalColumns();
	}

	
	public void tariffBandValidation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Tariff" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				ratesheetValMap = excelHolderObj.dataMap( paramVal );
				
				
				TariffValidationImpl trffValImplObj = new TariffValidationImpl( ratesheetValMap );
				trffValImplObj.tariffValidation();	
				trffValImplObj.deleteBandVerification();
				tariffBandInitialize( path, workBookName, sheetName, ratesheetValMap.get("BandValidation-TCName") );				
				trffValImplObj.closeTariffFastentry();
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	
	private void tariffBandInitialize(String path, String workBookName, String sheetName, String testCaseName) throws Exception{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		
		ratesheetValExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( ratesheetValExcel );
		colSize = excelHolderObj.totalColumns();
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
		ratesheetValMap = excelHolderObj.dataMap( paramVal );
		
		BandValidationImpl bandValImplObj = new BandValidationImpl( ratesheetValMap );
		bandValImplObj.tariffBandValidation();
		bandValImplObj.bandValidation();
		bandValImplObj.rateDetails();
		}
	}
}
