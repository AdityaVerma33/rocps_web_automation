package com.subex.rocps.automation.helpers.application.xdrextraction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.db.DBHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.xdrextraction.XdrExtraTemplate.XDRExtractFilter;
import com.subex.rocps.automation.helpers.application.xdrextraction.XdrExtraTemplate.XdrExtrTempActionImpl;
import com.subex.rocps.automation.helpers.application.xdrextraction.XdrExtraTemplate.XdrExtrTempDetailImpl;
import com.subex.rocps.automation.helpers.dbscript.DBScriptImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class XdrExtrTempHelper extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> xdrExtTempExcelMap = null;
	protected Map<String, String> xdrExtTempMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String clientPartition;
	protected String fromDt;
	protected String toDt;
	protected String xdrExtTempNm;
	protected String newxdrExtTempNm;
	protected String updateActIncl;
	protected String updateActExcl;
	protected String paor_name;
	protected String tableNmWithColmn;
	protected String tableNmWithColmnArr[];
	protected String system_gen_fl;
	protected String dlt_fl;
	protected String version_id;
	protected String ptn_id;
	protected String pxfs_id;
	protected String pxfs_seq_no;
	protected String pxfs_min_seq_no;
	protected String pxfs_max_seq_no;
	protected String pxfs_seq_no_pattern;
	protected String pxfs_increment_by;
	protected String pxfs_ref_dt;
	protected String xdrFullFilePath;
	protected String cdrDates;
	protected String fileExtensions;
	protected int colSize;
	protected int index;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	DBScriptImpl dbScriptImpl = new DBScriptImpl();

	// Constructor : Initializing the excel without occurrence
	public XdrExtrTempHelper( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		xdrExtTempExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( xdrExtTempExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	// Constructor : Initializing the excel with occurrence
	public XdrExtrTempHelper( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		xdrExtTempExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( xdrExtTempExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	// Method: Initialize the variables for Xdr Aggregation keys table from backend
	protected void initializeAggKeysVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		paor_name = ExcelHolder.getKey( map, "PAOR_NAME" );
		system_gen_fl = ExcelHolder.getKey( map, "SYSTEM_GENERATED_FL" );
		dlt_fl = ExcelHolder.getKey( map, "DELETE_FL" );
		version_id = ExcelHolder.getKey( map, "VERSION_ID" );
		ptn_id = ExcelHolder.getKey( map, "PTN_ID" );
		tableNmWithColmn = ExcelHolder.getKey( map, "TableNmWithColumn" );
		if ( ValidationHelper.isNotEmpty( tableNmWithColmn ) )
			tableNmWithColmnArr = psStringUtils.stringSplitFirstLevel( tableNmWithColmn );

	}

	// Method: Initialize the variables for Xdr  Aggregation File sequence table from backend
	public void initializeVariableAggFileSeq( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		paor_name = ExcelHolder.getKey( map, "PAOR_NAME" );
		pxfs_seq_no = ExcelHolder.getKey( map, "PXFS_SEQUENCE_NO" );
		pxfs_min_seq_no = ExcelHolder.getKey( map, "PXFS_MIN_SEQ_NO" );
		pxfs_max_seq_no = ExcelHolder.getKey( map, "PXFS_MAX_SEQ_NO" );
		pxfs_seq_no_pattern = ExcelHolder.getKey( map, "PXFS_SEQ_NO_PATTERN" );
		pxfs_increment_by = ExcelHolder.getKey( map, "PXFS_INCREMENT_BY" );
		pxfs_ref_dt = ExcelHolder.getKey( map, "PXFS_REFERENCE_DATE" );
		system_gen_fl = ExcelHolder.getKey( map, "SYSTEM_GENERATED_FL" );
		dlt_fl = ExcelHolder.getKey( map, "DELETE_FL" );
		version_id = ExcelHolder.getKey( map, "VERSION_ID" );
		ptn_id = ExcelHolder.getKey( map, "PTN_ID" );
	}

	// Method: Initialize the variables for Xdr Extraction Full File
	protected void initializeXdrFullFileVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		xdrFullFilePath = xdrExtTempMap.get( "XdrExtFullFilePath" );
		cdrDates = xdrExtTempMap.get( "CdrDates" );
		fileExtensions = xdrExtTempMap.get( "FileExtensions" );
	}

	// Method: Initialize the variables to change from and to date of template from backend
	protected void initializeVariableChangeDt( Map<String, String> map ) throws Exception
	{
		initializeVariable( map );
		fromDt = ExcelHolder.getKey( map, "ValidFrom" );
		toDt = ExcelHolder.getKey( map, "ValidTo" );

	}

	// Method: Initialize the variables
	protected void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		xdrExtTempNm = ExcelHolder.getKey( map, "TemplateName" );
	}

	// Method: Common method to navigate to the XDR extraction template screen
	private void xdrExtrTempScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "XDR Extraction Template" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "XDR Extraction Temp..." );
		xdrExtTempMap = excelHolderObj.dataMap( index );
		initializeVariable( xdrExtTempMap );
	}

	// Method: Verify the column headers of XDR extraction template screen
	public void verifyColmnHeaderOfXdrExtrTempl() throws Exception
	{

		for ( index = 0; index < colSize; index++ )
		{
			NavigationHelper.navigateToScreen( "XDR Extraction Template" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "XDR Extraction Temp..." );
			xdrExtTempMap = excelHolderObj.dataMap( index );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			XdrExtrTempDetailImpl xdrExtrTempDetailImpl = new XdrExtrTempDetailImpl( xdrExtTempMap );
			xdrExtrTempDetailImpl.verifyColmnHeaderOfXdrExtrTempl();
			Log4jHelper.logInfo( "'XDR extraction template screen' Columns are validated successfully" );
		}
	}

	// Method: Create the new Xdr Extract Template
	public void xdrExtTemplCreation() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			xdrExtrTempScreen();
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isXdrExtTempPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_xdrExtTemp_name_textID", xdrExtTempNm, "Name" );
			if ( !isXdrExtTempPresent )
			{
				XdrExtrTempActionImpl xdrExtrTempActionObj = new XdrExtrTempActionImpl();
				XdrExtrTempDetailImpl xdrExtrTempDetailIObj = new XdrExtrTempDetailImpl( xdrExtTempMap );
				XDRExtractFilter xdrExtractFilterObj = new XDRExtractFilter(xdrExtTempMap);
				xdrExtrTempActionObj.clickNewAction( clientPartition, "XDR Extraction Template" );
				xdrExtrTempDetailIObj.configXdrExtTempl( xdrExtTempNm );
				xdrExtractFilterObj.xdrExtractFilterTabConfig();
				xdrExtrTempDetailIObj.saveXdrExtTempl( xdrExtTempNm );
				Log4jHelper.logInfo( "'XDR Extraction Template' is successfully saved  with the template name:- " + xdrExtTempNm );
			}
			else
			{
				Log4jHelper.logInfo( "'XDR Extraction Template' is availabe with the template name:- " + xdrExtTempNm );
			}

		}
	}

	// Method: Create the new Xdr Extract Template with 'Save As' action
	public void xdrExtTemplCrtWithSaveAs() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			xdrExtrTempScreen();
			newxdrExtTempNm = ExcelHolder.getKey( xdrExtTempMap, "NewTempName" );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isXdrExtTempPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_xdrExtTemp_name_textID", xdrExtTempNm, "Name" );
			if ( isXdrExtTempPresent )
			{
				GridHelper.clickRow( "searchGrid", xdrExtTempNm, "Name" );
				XdrExtrTempActionImpl xdrExtrTempActionObj = new XdrExtrTempActionImpl();
				XdrExtrTempDetailImpl xdrExtrTempDetailIObj = new XdrExtrTempDetailImpl( xdrExtTempMap );
				xdrExtrTempActionObj.clickSaveAsAction( clientPartition, "XDR Extraction Template", xdrExtTempNm );
				xdrExtrTempDetailIObj.saveAsXdrExtTempl( newxdrExtTempNm );
				xdrExtrTempDetailIObj.saveXdrExtTempl( newxdrExtTempNm );
				Log4jHelper.logInfo( "'XDR Extraction Template' is successfully saved  as with the template name:- " + newxdrExtTempNm );
			}
			else
			{
				Log4jHelper.logInfo( "'XDR Extraction Template' is not availabe with the template name:- " + xdrExtTempNm );
			}

		}
	}

	// Method: Create the new Xdr Extract Template with 'Edit' action
	public void xdrExtTemplEdit() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			xdrExtrTempScreen();
			String eventType = ExcelHolder.getKey( xdrExtTempMap, "EventType" );
			String inclExtrWithBill = ExcelHolder.getKey( xdrExtTempMap, "IncludeExtrWithBill" );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isIncludeBillExistFlg = isIncludeBillExist( eventType, inclExtrWithBill, xdrExtTempNm );
			ButtonHelper.click( "ClearButton" );
			boolean isXdrExtTempPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_xdrExtTemp_name_textID", xdrExtTempNm, "Name" );
			assertTrue( isXdrExtTempPresent, "Xdr Extraction Template is not available in the screen with the template value: -'" + xdrExtTempNm );
			int row = GridHelper.getRowNumber( "searchGrid", xdrExtTempNm, "Name" );
			String xdrTempStatus = GridHelper.getCellValue( "searchGrid", row, "Status" );
			if ( isIncludeBillExistFlg )
			{
				Log4jHelper.logInfo( "For one event type, only one template can be marked to be included with Bill:'" );
			}
			else if ( xdrTempStatus.contentEquals( "Draft" ) && !isIncludeBillExistFlg )
			{
				XdrExtrTempDetailImpl xdrExtrTempDetailIObj = new XdrExtrTempDetailImpl( xdrExtTempMap );
				GridHelper.clickRow( "searchGrid", xdrExtTempNm, "Name" );
				NavigationHelper.navigateToEdit( "searchGrid", row );
				xdrExtrTempDetailIObj.editXdrExtTempl( xdrExtTempNm );
				xdrExtrTempDetailIObj.saveXdrExtTempl( xdrExtTempNm );
				Log4jHelper.logInfo( "'XDR Extraction Template' is successfully updated   with the template name:- " + xdrExtTempNm );
			}
			else
			{
				Log4jHelper.logInfo( "Xdr Extraction Template is not as expeceted  'Draft'  State with the template value-:'" + xdrExtTempNm );
			}

		}
	}

	/*Method to check 'Include xdr extract with bill' exist for event type*/
	private boolean isIncludeBillExist( String eventType, String inclExtrWithBill, String templateNm ) throws Exception
	{
		psGenericHelper.waitforHeaderElement( "Event Type" );
		SearchGridHelper.gridFilterSearchWithComboBox( "eventType_gwt_uid_", eventType, "Event Type" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		SearchGridHelper.gridFilterSearchWithComboBox( "pxecIncludeXdrBillFl_gwt_uid_", "Yes", "Include extract with Bill" );
		if ( GridHelper.getRowCount( "searchGrid" )>0 && GridHelper.getCellValue( "searchGrid", 1, "Name" ).contentEquals( templateNm ) )
			return false;
		else
			return ( GridHelper.getRowCount( "searchGrid" ) > 0 && ValidationHelper.isTrue( inclExtrWithBill ) );

	}

	// Method: Validate the Xdr Extract Template screen with 'View' action
	public void xdrExtTemplViewAction() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			xdrExtrTempScreen();
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isXdrExtTempPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_xdrExtTemp_name_textID", xdrExtTempNm, "Name" );
			assertTrue( isXdrExtTempPresent, "Xdr Extraction Template is not available in the screen with the value: -'" + xdrExtTempNm );
			int row = GridHelper.getRowNumber( "searchGrid", xdrExtTempNm, "Name" );
			String xdrTempStatus = GridHelper.getCellValue( "searchGrid", row, "Status" );
			if ( xdrTempStatus.contentEquals( "Accepted" ) )
			{
				GridHelper.clickRow( "searchGrid", xdrExtTempNm, "Name" );
				XdrExtrTempActionImpl xdrExtrTempActionObj = new XdrExtrTempActionImpl();
				XdrExtrTempDetailImpl xdrExtrTempDetailIObj = new XdrExtrTempDetailImpl( xdrExtTempMap );
				xdrExtrTempActionObj.clickViewAction( clientPartition, "XDR Extraction Template" );
				xdrExtrTempDetailIObj.validateXdrDetailScreen();

				Log4jHelper.logInfo( "'XDR Extraction Template' is successfully validated  with view action for the template name:- " + xdrExtTempNm );
			}
			else
			{
				Log4jHelper.logInfo( "'XDR Extraction Template' is not in 'Accepted' status for the template name:- " + xdrExtTempNm );
			}

		}
	}

	// Method: Validate the Xdr Extract Template screen from other screen
	public void ViewXdrTemplatScreen() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			xdrExtTempMap = excelHolderObj.dataMap( index );
			initializeVariable( xdrExtTempMap );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			XdrExtrTempDetailImpl xdrExtrTempDetailIObj = new XdrExtrTempDetailImpl( xdrExtTempMap );
			xdrExtrTempDetailIObj.validateXdrDetailScreen();
			Log4jHelper.logInfo( "'XDR Extraction Template' is successfully validated  with view action for the template name:- " + xdrExtTempNm );
		}

	}

	// Method: Change the status of Xdr Extract Template as accepted
	public void changeToAcceptedStatus() throws Exception
	{

		for ( index = 0; index < colSize; index++ )
		{
			xdrExtrTempScreen();
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isXdrExtTempPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_xdrExtTemp_name_textID", xdrExtTempNm, "Name" );
			assertTrue( isXdrExtTempPresent, "Xdr Extraction Template is not available in the screen with the value: -'" + xdrExtTempNm );
			int row = GridHelper.getRowNumber( "searchGrid", xdrExtTempNm, "Name" );
			String xdrTempStatus = GridHelper.getCellValue( "searchGrid", row, "Status" );
			if ( xdrTempStatus.contentEquals( "Draft" ) )
			{
				GridHelper.clickRow( "searchGrid", xdrExtTempNm, "Name" );
				XdrExtrTempActionImpl xdrExtrTempActionOb = new XdrExtrTempActionImpl();
				xdrExtrTempActionOb.changeStatusToAccepted( "Confirmation" );
				ButtonHelper.click( "SearchButton" );
				xdrTempStatus = GridHelper.getCellValue( "searchGrid", row, "Status" );
				assertEquals( xdrTempStatus, "Accepted", "The status is not changed to 'Accepted' from 'Draft' state" );
				Log4jHelper.logInfo( "Xdr Extraction Template is successfully changed to 'Accepted' status with the template-'" + xdrExtTempNm );

			}
			else if ( ( xdrTempStatus.contentEquals( "Accepted" ) ) )
				Log4jHelper.logInfo( "Xdr Extraction Template is already in 'Accepted' status with the template-'" + xdrExtTempNm );
			else
			{
				Log4jHelper.logInfo( "Xdr Extraction Template status is not in 'Draft' State of this template -'" + xdrExtTempNm );
			}
		}
	}

	// Method: Update the Xdr Extract Template with include and exclude
	public void updateXdrExtOpAction() throws Exception
	{

		for ( index = 0; index < colSize; index++ )
		{
			xdrExtrTempScreen();
			updateActIncl = ExcelHolder.getKey( xdrExtTempMap, "UpdateActionInclude" );
			updateActExcl = ExcelHolder.getKey( xdrExtTempMap, "UpdateActionExclude" );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isXdrExtTempPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_xdrExtTemp_name_textID", xdrExtTempNm, "Name" );
			assertTrue( isXdrExtTempPresent, "Xdr Extraction Template is not available in the screen with the value: -'" + xdrExtTempNm );
			int row = GridHelper.getRowNumber( "searchGrid", xdrExtTempNm, "Name" );
			updateXdrOpSelect( xdrExtTempNm, updateActIncl, "No", "Yes" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			updateXdrOpSelect( xdrExtTempNm, updateActExcl, "Yes", "No" );

		}
	}

	// Method: select on Update the Xdr Extract Template
	private void updateXdrOpSelect( String tempNm, String updtActNm, String beforeUpdtStatus, String afterUdtStatus ) throws Exception
	{
		SearchGridHelper.gridFilterSearchWithComboBox( "pxecIncludeXdrBillFl_gwt_uid_", beforeUpdtStatus, "Include extract with Bill" );
		boolean isXdrExtTempPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_xdrExtTemp_name_textID", xdrExtTempNm, "Name" );
		assertTrue( isXdrExtTempPresent, "Xdr Extraction Template is not available in the screen with the value: -'" + xdrExtTempNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "searchGrid", tempNm, "Name" );
		XdrExtrTempActionImpl xdrExtrTempActionOb = new XdrExtrTempActionImpl();
		xdrExtrTempActionOb.updateXdrExtOpAction( updtActNm );
		SearchGridHelper.gridFilterSearchWithComboBox( "pxecIncludeXdrBillFl_gwt_uid_", afterUdtStatus, "Include extract with Bill" );
		isXdrExtTempPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_xdrExtTemp_name_textID", xdrExtTempNm, "Name" );
		assertTrue( isXdrExtTempPresent, "Xdr Extraction Template is not available in the screen with the value: -'" + xdrExtTempNm );
		Log4jHelper.logInfo( updtActNm + " is successfully updated for this template-'" + xdrExtTempNm );

	}

	// Method: for Xdr Extraction Template deletion action
	public void xdrExtTempDelete() throws Exception
	{
		xdrExtrTempScreen();
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		boolean isXdrExtTempPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_xdrExtTemp_name_textID", xdrExtTempNm, "Name" );
		assertTrue( isXdrExtTempPresent, "Xdr Extraction Template is not available in the screen with the template value: -'" + xdrExtTempNm );
		int row = GridHelper.getRowNumber( "searchGrid", xdrExtTempNm, "Name" );
		String xdrTempStatus = GridHelper.getCellValue( "searchGrid", row, "Status" );
		if ( xdrTempStatus.contentEquals( "Draft" ) )
		{
			psGenericHelper.clickDeleteOrUnDeleteAction( xdrExtTempNm, "Name", "Delete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			isXdrExtTempPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_xdrExtTemp_name_textID", xdrExtTempNm, "Name" );
			assertTrue( isXdrExtTempPresent, xdrExtTempNm + " is not present" );
			Log4jHelper.logInfo( "Xdr Extraction Template is deleted successfully with the template value-:'" + xdrExtTempNm );

		}
		else
		{
			Log4jHelper.logInfo( "Xdr Extraction Template is not as expeceted  'Draft'  State with the template value-:'" + xdrExtTempNm );

		}
	}

	// Method: for Xdr Extraction Template Undeletion action
	public void xdrExtTempUnDelete() throws Exception
	{

		for ( index = 0; index < colSize; index++ )
		{
			xdrExtrTempScreen();
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean isXdrExtTempPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_xdrExtTemp_name_textID", xdrExtTempNm, "Name" );
			assertTrue( isXdrExtTempPresent, "Xdr Extraction Template is not available in the screen with the template value: -'" + xdrExtTempNm );
			psGenericHelper.clickDeleteOrUnDeleteAction( xdrExtTempNm, "Name", "Undelete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			isXdrExtTempPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_xdrExtTemp_name_textID", xdrExtTempNm, "Name" );
			assertTrue( isXdrExtTempPresent, xdrExtTempNm + " is not present" );
			Log4jHelper.logInfo( "Xdr Extraction Template is undeleted successfully with the template value:  '" + xdrExtTempNm );

		}

	}

	// Method: for Xdr Extraction Template change from and to date  Backend update
	public void xdrExtTempUpdateDateBackend() throws Exception
	{

		for ( index = 0; index < colSize; index++ )
		{
			xdrExtTempMap = excelHolderObj.dataMap( index );
			initializeVariableChangeDt( xdrExtTempMap );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			dbScriptImpl.xdrExtTempScript( fromDt, toDt, xdrExtTempNm );
			Log4jHelper.logInfo( "Xdr Extraction Template is updated successfully from backend with the template value:  '" + xdrExtTempNm );

		}

	}

	// Method: for xdr_agg_keys Backend insertion
	public void xdrAggKeysInsertBackend() throws Exception
	{

		for ( index = 0; index < colSize; index++ )
		{
			xdrExtTempMap = excelHolderObj.dataMap( index );
			initializeAggKeysVariable( xdrExtTempMap );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			for ( int i = 0; i < tableNmWithColmnArr.length; i++ )
			{
				String tbdTclArr[] = psStringUtils.stringSplitSecondLevel( tableNmWithColmnArr[i] );
				String tbd_name = tbdTclArr[0];
				String tcl_name = tbdTclArr[1];
				dbScriptImpl.insertIntoAggKeys( paor_name, tbd_name, tcl_name, system_gen_fl, dlt_fl, version_id, ptn_id );
			}

		}

	}

	// Method: for Xdr_Agg_File_Sequence Backend insertion
	public void xdrAggFlSeqInsertBackend() throws Exception
	{

		for ( index = 0; index < colSize; index++ )
		{
			xdrExtTempMap = excelHolderObj.dataMap( index );
			initializeVariableAggFileSeq( xdrExtTempMap );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			dbScriptImpl.insertIntoAggFileSequence( paor_name, pxfs_seq_no, pxfs_min_seq_no, pxfs_max_seq_no, pxfs_seq_no_pattern, pxfs_increment_by, pxfs_ref_dt, system_gen_fl, dlt_fl, version_id, ptn_id );
		}

	}

	// Method: for check XDR Extraction full file
	public void xdrFullFileValidation() throws Exception
	{

		for ( index = 0; index < colSize; index++ )
		{
			xdrExtTempMap = excelHolderObj.dataMap( index );
			initializeXdrFullFileVariable( xdrExtTempMap );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			XdrFileHelper xdrFileHelper = new XdrFileHelper();
			xdrFileHelper.readFile( xdrFullFilePath, fileExtensions, cdrDates );
		}

	}
}
