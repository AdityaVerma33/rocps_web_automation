package com.subex.rocps.automation.helpers.application.reaggregation.usgBackoutReq;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.reaggregation.UsageBackoutRequest;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.repAndExtSchedule.ReportAndExtInstance;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class UsgBackoutReqDetailImpl extends PSAcceptanceTest
{
	protected Map<String, String> usageBackoutReqDetailMap = null;
	protected String fileCollection;
	protected String description;
	protected String fileSource;
	protected String token;
	protected String fileName;
	protected String fileNameArr[];
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	DataSelectionHelper dataSelectionHelper = new DataSelectionHelper();
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	/**Constructor
	 * @param usageBackoutReqDetailMap
	 */
	public UsgBackoutReqDetailImpl( Map<String, String> usageBackoutReqDetailMap )
	{
		this.usageBackoutReqDetailMap = usageBackoutReqDetailMap;
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		fileCollection = ExcelHolder.getKey( map, "FileCollection" );
		description = ExcelHolder.getKey( map, "Description" );

	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeCollectedVariable( Map<String, String> map ) throws Exception
	{
		fileSource = ExcelHolder.getKey( map, "FileSource" );
		token = ExcelHolder.getKey( map, "Token" );
		fileName = ExcelHolder.getKey( map, "FileName" );
	}

	/*Method is for
	 * Config Usg backoutRequest
	 */
	public void configUsgBackoutRequest() throws Exception
	{
		initializeVariable( usageBackoutReqDetailMap );
		configTopFieldPanel();
		configCollectedFile();
		psGenericHelper.detailSave( "PS_Detail_usgBackoutReq_saveBtnId", description, "Description" );
	}

	/*Method is for
	 * Config Usg backoutRequest
	 */
	public void modifyUsgBackoutRequest() throws Exception
	{
		initializeVariable( usageBackoutReqDetailMap );
		modifyTopFieldPanel();
		configCollectedFile();
		psGenericHelper.detailSave( "PS_Detail_usgBackoutReq_saveBtnId", description, "Description" );
	}

	/*Method is for
	 * View Usg backoutRequest
	 */
	public void viewUsgBackoutRequest() throws Exception
	{
		initializeVariable( usageBackoutReqDetailMap );
		viewTopFieldPanel();
		viewCollectedFile();
		psGenericHelper.detailSave( "PS_Detail_usgBackoutReq_closeBtnId", description, "Description" );
	}

	/*Method is for
	 * Config TopField panel
	 */
	protected void configTopFieldPanel() throws Exception
	{
		ComboBoxHelper.select( "PS_Detail_usgBackoutReq_fileCollection_comboId", fileCollection );
		TextBoxHelper.type( "PS_Detail_usgBackoutReq_desc_txtId", description );
	}

	/*Method is for
	 * Config TopField panel
	 */
	protected void modifyTopFieldPanel() throws Exception
	{
		psDataComponentHelper.modifyComboBox( "PS_Detail_usgBackoutReq_fileCollection_comboId", fileCollection );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_usgBackoutReq_desc_txtId" ), description );
	}

	/*Method is for
	 * view TopField panel
	 */
	protected void viewTopFieldPanel() throws Exception
	{
		psDataComponentHelper.checkComboBoxDisabled( "PS_Detail_usgBackoutReq_fileCollection_comboId", fileCollection );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_usgBackoutReq_desc_txtId" ), description );
	}

	/*
	 * This method is for collected file
	 */
	protected void configCollectedFile() throws Exception
	{
		initializeCollectedVariable( usageBackoutReqDetailMap );
		fileNameArr = psStringUtils.stringSplitFirstLevel( fileName );
		List<String> getKeysOfCollectedFlGrid = getKeysOfCollectedFlGrid();
		for ( String fileName : fileNameArr )
		{
			Map<String, ArrayList<String>> mapOfcollectedFlgrid = psDataComponentHelper.getGridColumnValues( "PS_Detail_usgBackoutReq_collFile_gridId", "grid_column_header_undefined_", getKeysOfCollectedFlGrid );
			boolean isfileNmPresentInGrid = psDataComponentHelper.isDataPresentInGrid( "PS_Detail_usgBackoutReq_collFile_gridId", mapOfcollectedFlgrid, "File  Name", fileName );
			if ( !isfileNmPresentInGrid )
				configCollectedFileGridField( fileSource, token, fileName );
			else
				Log4jHelper.logInfo( "This File name value: " + fileName + " is already present in this grid:" + GenericHelper.getORProperty( "PS_Detail_usgBackoutReq_collFile_gridId" ) );
		}
	}

	/*
	 * This method is for collected file
	 */
	protected void viewCollectedFile() throws Exception
	{
		initializeCollectedVariable( usageBackoutReqDetailMap );
		fileNameArr = psStringUtils.stringSplitFirstLevel( fileName );
		List<String> getKeysOfCollectedFlGrid = getKeysOfCollectedFlGrid();
		Map<String, ArrayList<String>> mapOfcollectedFlgrid = psDataComponentHelper.getGridColumnValues( "PS_Detail_usgBackoutReq_collFile_gridId", "grid_column_header_undefined_", getKeysOfCollectedFlGrid );
		for ( String fileName : fileNameArr )
		{
			boolean isfileNmPresentInGrid = psDataComponentHelper.isDataPresentInGrid( "PS_Detail_usgBackoutReq_collFile_gridId", mapOfcollectedFlgrid, "File  Name", fileName );
			if ( isfileNmPresentInGrid )
				Log4jHelper.logInfo( "This File name value: " + fileName + " is  present in this grid:" + GenericHelper.getORProperty( "PS_Detail_usgBackoutReq_collFile_gridId" ) );
			else
				FailureHelper.failTest( "This File name value: " + fileName + " is not present in this grid:" + GenericHelper.getORProperty( "PS_Detail_usgBackoutReq_collFile_gridId" ) );
		}
	}

	/*
	 * This method is for collected file grid
	 */
	private void configCollectedFileGridField( String fileSource, String token, String fileName ) throws Exception
	{
		ButtonHelper.click( "PS_Detail_usgBackoutReq_collFile_add_btnId" );
		dataSelectionHelper.collectedFilesearch( fileSource, token, fileName );
	}

	// collectedFlgrid columns keys
	private List<String> getKeysOfCollectedFlGrid()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "File  Name" );
		return listColumn;
	}

}
