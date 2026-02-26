package com.subex.rocps.automation.helpers.application.roaming;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.AdvanceSearchFiltersHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.roaming.imsiManagement.IMSIManagementActionImpl;
import com.subex.rocps.automation.helpers.application.roaming.imsiManagement.IMSIManagementDetails;
import com.subex.rocps.automation.helpers.application.roaming.roamingAgreemConfig.RoamingAgreeConfigAction;
import com.subex.rocps.automation.helpers.application.roaming.roamingAgreemConfig.RoamingAgreemConfigDetail;
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

public class IMSIManagement extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> imsiManagementExcelMap = null;
	protected Map<String, String> imsiManagementMap = null;
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
	AdvanceSearchFiltersHelper advanceSearchHelpOb = new AdvanceSearchFiltersHelper();

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public IMSIManagement( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		imsiManagementExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( imsiManagementExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/**Constructor : Initializing the excel with occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @param occurence
	 * @throws Exception 
	 */
	public IMSIManagement( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		imsiManagementExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( imsiManagementExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		tadigCode = ExcelHolder.getKey( map, "Tadig" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'IMSI Management' screen common method
	 */
	private void imsiManagementScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "IMSI Management" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		imsiManagementMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Roaming Definition" );
	}

	/*
	 * This method is for 'IMSI Management' screen column validation
	 */
	public void imsiManagementColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				imsiManagementScreen();
				colmHdrs = ExcelHolder.getKey( imsiManagementMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String imsiManagementGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : imsiManagementGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'IMSI Management' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'IMSI Management'  creation
	 */
	public void imsiManagementCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				imsiManagementScreen();
				initializeVariable( imsiManagementMap );
				boolean isRoamingDfnpresent = isRoamingDfnpresent();
				if ( !isRoamingDfnpresent )
				{
					IMSIManagementActionImpl imsiManagementActionImpl = new IMSIManagementActionImpl();
					imsiManagementActionImpl.clickNewAction( clientPartition );
					IMSIManagementDetails iMSManagementDetails = new IMSIManagementDetails( imsiManagementMap );
					iMSManagementDetails.createIMSIManagement();
					Log4jHelper.logInfo( "'IMSI Management'' is successfully created with tadig value:  '" + tadigCode );

				}
				else

					Log4jHelper.logInfo( "'IMSI Management'' is already avilable with tadig value:" + tadigCode );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'IMSI Management'  edit
	 */
	public void imsiManagementEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				imsiManagementScreen();
				initializeVariable( imsiManagementMap );
				boolean isRoamingDfnpresent = isRoamingDfnpresent();
				if ( isRoamingDfnpresent )
				{
					IMSIManagementActionImpl imsiManagementActionImpl = new IMSIManagementActionImpl();
					GridHelper.clickRow( "SearchGrid", tadigCode, "Roaming Definition" );
					imsiManagementActionImpl.clickOnAction( "Common Tasks", "Edit" );
					IMSIManagementDetails iMSManagementDetails = new IMSIManagementDetails( imsiManagementMap );
					iMSManagementDetails.modifyIMSIManagement();
					Log4jHelper.logInfo( "'IMSI Management'' is successfully updated with tadig value:  '" + tadigCode );

				}
				else

					Log4jHelper.logInfo( "'IMSI Management'' is not avilable with tadig value:" + tadigCode );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	private boolean isRoamingDfnpresent() throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		advanceSearchHelpOb.roamingDefnAdvanceSearch( "Roaming Definition", tadigCode );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforHeaderElement( "Roaming Definition" );
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforHeaderElement( "Roaming Definition" );
		return GridHelper.isValuePresent( "SearchGrid", tadigCode, "Roaming Definition" );
	}

}
