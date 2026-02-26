package com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;

public class BirtReportType extends PSAcceptanceTest implements ReportAndExtTypeStrategy
{
	protected Map<String, String> birtRepTypeMap = null;

	protected String documentType;
	protected String fileName;
	protected String filePath;

	/**Constructor
	 * @param birtRepTypeMap
	 */
	public BirtReportType( Map<String, String> birtRepTypeMap )
	{
		this.birtRepTypeMap = birtRepTypeMap;
	}

	/*This method is for initialize variable*/
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		documentType = ExcelHolder.getKey( map, "DocumentType" );
		fileName = ExcelHolder.getKey( map, "FileName" );
		filePath = ExcelHolder.getKey( map, "FileName" );
	}
	/*
	 * This method is for configure Report and Extract Type parameters
	 */
	@Override
	public void configReportAndExtTypeParameters() throws Exception
	{
		initializeVariable( birtRepTypeMap );
		birtFileupload();
	}
	/*
	 * This method is for birt file upload
	 */
	private void birtFileupload() throws  Exception
	{
		String filePath = automationPath + configProp.getProperty( "birtReportPath" )+"event_statistic_report.rptdesign";
		
		ButtonHelper.click( "load" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		PSGenericHelper.psFileUploadSikuli("FileUpload_Browse", filePath);
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		ButtonHelper.click( "FileUpload-upload" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );

	}
	@Override
	public void modifyReportAndExtTypeParameters() throws Exception
	{
		// TODO Auto-generated method stub

	}

}
