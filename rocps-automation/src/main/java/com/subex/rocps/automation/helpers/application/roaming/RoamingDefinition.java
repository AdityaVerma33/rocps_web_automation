package com.subex.rocps.automation.helpers.application.roaming;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.GridFilterHelper;
import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn.ReportAndExtDefnAction;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn.ReportAndExtDefnDetails;
import com.subex.rocps.automation.helpers.application.roaming.roamingDfn.RoamingDfnActionImpl;
import com.subex.rocps.automation.helpers.application.roaming.roamingDfn.RoamingDfnDetail;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class RoamingDefinition extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> roamingDefnExcelMap = null;
	protected Map<String, String> roamingDefnMap = null;
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
	protected String typeOfAgreement;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public RoamingDefinition( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		roamingDefnExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( roamingDefnExcelMap );
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
	public RoamingDefinition( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		roamingDefnExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( roamingDefnExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for 'Roaming Definition' screen common method
	 */
	private void roamingDefnScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Roaming Definition" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		roamingDefnMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Network" );
		checkConfirmationPopup();
	}

	private void checkConfirmationPopup() throws Exception
	{
		if ( PopupHelper.isTextPresent( "Currently there is no Home Operator configured in the system." ) )
			ButtonHelper.click( "OKButton" );
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		tadigCode = ExcelHolder.getKey( map, "Tadig" );
		typeOfAgreement = ExcelHolder.getKey( map, "TypeOfAgreement" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'Roaming Definition' screen column validation
	 */
	public void roamingDefnColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingDefnScreen();
				colmHdrs = ExcelHolder.getKey( roamingDefnMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String roamingDefnGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : roamingDefnGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'Roaming Definition' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Roaming Definition' report creation
	 */
	public void roamingDefnCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingDefnScreen();
				initializeVariable( roamingDefnMap );
				boolean isTadigCodePresent = isTadigCodePresent();
				if ( !isTadigCodePresent )
				{
					checkConfirmationPopup();
					RoamingDfnActionImpl roamingDfnActionImpl = new RoamingDfnActionImpl();
					roamingDfnActionImpl.clickNewAction( clientPartition );
					RoamingDfnDetail roamingDfnDetail = new RoamingDfnDetail( roamingDefnMap );
					roamingDfnDetail.createRoamingDefn();
					Log4jHelper.logInfo( "'Roaming Definition' is successfully created with tadig value:  '" + tadigCode+"' for type of agreement -: "+typeOfAgreement );

				}
				else

					Log4jHelper.logInfo( "'Roaming Definition' is already avilable with tadig value:" + tadigCode );
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
	private boolean isTadigCodePresent() throws Exception
	{
		boolean isTadigCodePresent = false;
		PSSearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_roamingDfn_tadig_comboId", tadigCode, "Tadig code" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		isTadigCodePresent = GridHelper.isValuePresent( "SearchGrid", tadigCode, "Tadig code" );
		return isTadigCodePresent;
	}

}
