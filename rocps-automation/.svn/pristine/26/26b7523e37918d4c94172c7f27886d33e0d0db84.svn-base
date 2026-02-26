package com.subex.rocps.automation.helpers.application.roaming;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.roaming.roamingConfig.RoamingConfigActionImpl;
import com.subex.rocps.automation.helpers.application.roaming.roamingConfig.RoamingConfigBasicDetails;
import com.subex.rocps.automation.helpers.application.roaming.roamingDfn.RoamingDfnActionImpl;
import com.subex.rocps.automation.helpers.application.roaming.roamingDfn.RoamingDfnDetail;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class RoamingConfiguration extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> roamingConfigExcelMap = null;
	protected Map<String, String> roamingConfigMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String clientPartition;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String tadigCode;
	protected String billProfile;
	protected String configType;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl psActionImpl = new PSActionImpl();
	RoamingConfigActionImpl roamingConfigActionImpl = new RoamingConfigActionImpl();

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public RoamingConfiguration( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		roamingConfigExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( roamingConfigExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/**Constructor :  Initializing the excel with occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @param occurence
	 * @throws Exception 
	 */
	public RoamingConfiguration( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		roamingConfigExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( roamingConfigExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		tadigCode = ExcelHolder.getKey( map, "RoamingDefinition" );
		billProfile = ExcelHolder.getKey( map, "BillProfile" );
		configType = ExcelHolder.getKey( map, "ConfigurationType" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'Roaming  Configuration' screen common method
	 */
	private void roamingConfigScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Roaming Configuration" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		roamingConfigMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Tadig" );
	}

	/*
	 * This method is for 'Roaming  Configuration' screen column validation
	 */
	public void roamingConfigColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingConfigScreen();
				colmHdrs = ExcelHolder.getKey( roamingConfigMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String roamingConfigGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : roamingConfigGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'Roaming Configuration' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Roaming Configuration' report creation
	 */
	public void roamingConfigCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingConfigScreen();
				initializeVariable( roamingConfigMap );
				boolean isRoamingConfigPresent = isRoamingConfigPresent();
				if ( !isRoamingConfigPresent )
				{
					roamingConfigActionImpl.clickNewAction( clientPartition );
					RoamingConfigBasicDetails roamingConfigBasicDetails = new RoamingConfigBasicDetails( roamingConfigMap );
					roamingConfigBasicDetails.createRoamingConfig();
					Log4jHelper.logInfo( "'Roaming Configuration' is successfully created with tadig value:  '" + tadigCode + "' for configType -: " + configType );
				}
				else

					Log4jHelper.logInfo( "'Roaming Configuration' is already avilable with tadig value:" + tadigCode + "' for configType -: " + configType );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Tadig Code' filter 
	 */
	private boolean isTadigCodePresent( String tadigCode ) throws Exception
	{
		boolean isTadigCodePresent = false;
		PSSearchGridHelper.gridFilterSearchWithComboBox( "roamingDefinition_tadigCode_gwt_uid_", tadigCode, "Tadig" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		isTadigCodePresent = GridHelper.isValuePresent( "SearchGrid", tadigCode, "Tadig" );
		return isTadigCodePresent;
	}

	private boolean isRoamingConfigPresent() throws Exception
	{
		PSSearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_roamingConfig_BD_conigType_comboId", configType, "Configuration Type" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		return isTadigCodePresent( tadigCode );
	}
}
