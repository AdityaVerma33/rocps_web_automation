package com.subex.rocps.automation.helpers.application.reportingAndExtraction.repAndExtSchedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.ReportAndExtScheduler;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportsAndExt.ReportsAndExtDetails;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class RepAndExtSchedulerDetails extends PSAcceptanceTest
{
	protected Map<String, String> repAndExtScheduleDetailsMap = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String repAndSchNm;
	protected String repAndSchType;
	protected String RestAttempt;
	protected String FtpLocation;
	protected String ChckMisTaskFlg;
	protected String inst_RepAndExtDfn;
	protected String inst_RepAndExtDfnArr[];
	protected String inst_RepAndExtLookBck;
	protected String inst_RepAndExtLookBckArr[];
	protected String parameter_FromDt;
	protected String parameter_FromDtArr[];
	protected String parameter_ToDt;
	protected String parameter_ToDtArr[];
	protected String freqMultiplier;
	protected String frequency;
	protected String nextSchedule;
	protected String dayGroups;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	ReportAndExtScheduler reportAndExtScheduler = new ReportAndExtScheduler();

	/**Constructor
	 * @param repAndExtScheduleDetailsMap
	 */
	public RepAndExtSchedulerDetails( Map<String, String> repAndExtScheduleDetailsMap )
	{

		this.repAndExtScheduleDetailsMap = repAndExtScheduleDetailsMap;
	}

	/*Method is for initialize variable scedule details panel*/
	private void initializeVarSchedDetails( Map<String, String> map ) throws Exception
	{
		repAndSchNm = ExcelHolder.getKey( map, "RepAndSchName" );
		repAndSchType = ExcelHolder.getKey( map, "RepAndSchType" );
		RestAttempt = ExcelHolder.getKey( map, "RepAndSchRestAttempt" );
		FtpLocation = ExcelHolder.getKey( map, "RepAndSchFtpLocation" );
		ChckMisTaskFlg = ExcelHolder.getKey( map, "RepAndSchChckMisTask" );
	}

	/*Method is for initialize variable path, workbooknm, sheetnm*/
	private void initializeWorkbookNmSheetNm()
	{
		workBookName = reportAndExtScheduler.getWorkbookName();
		sheetName = reportAndExtScheduler.getSheetName();
		path = reportAndExtScheduler.getPath();
	}

	/*Method is for initialize variable for Schedule time*/
	private void initializeVarSchTime( Map<String, String> map ) throws Exception
	{
		freqMultiplier = ExcelHolder.getKey( map, "FrequencyMultiplier" );
		frequency = ExcelHolder.getKey( map, "Frequency" );
		nextSchedule = ExcelHolder.getKey( map, "NextSchedule" );
		dayGroups = ExcelHolder.getKey( map, "DayGroups" );
	}

	/*Method is for creation of Report and Extract Schedule*/
	public void createRepAndExtSchedule() throws Exception
	{
		configScheduleDetail();
		configReportExtInstance();
		configScheduleTime();
		psGenericHelper.detailSave( "PS_Detail_reportAndExtSch_save_btnId", repAndSchNm, "Name" );
	}

	/*Method is for modify of Report and Extract Schedule*/
	public void modifyRepAndExtSchedule() throws Exception
	{
		modifyScheduleDetail();
		configReportExtInstance();
		configScheduleTime();
		psGenericHelper.detailSave( "PS_Detail_reportAndExtSch_save_btnId", repAndSchNm, "Name" );
	}

	/*Method is for configure of   Schedule  details*/
	protected void configScheduleDetail() throws Exception
	{
		initializeVarSchedDetails( repAndExtScheduleDetailsMap );
		TextBoxHelper.type( "PS_Detail_reportAndExtSch_name_txtId", repAndSchNm );
		ComboBoxHelper.select( "PS_Detail_reportAndExtSch_type_comboId", repAndSchType );
		TextBoxHelper.type( "PS_Detail_reportAndExtSch_restartAttemp_txtId", RestAttempt );
		ComboBoxHelper.select( "PS_Detail_reportAndExtSch_ftpLoc_comboId", FtpLocation );
		if ( ValidationHelper.isTrue( ChckMisTaskFlg ) )
			CheckBoxHelper.check( "PS_Detail_reportAndExtSch_chckMissTaks_chckBxId" );
	}

	/*Method is for modify of   Schedule  details*/
	protected void modifyScheduleDetail() throws Exception
	{
		initializeVarSchedDetails( repAndExtScheduleDetailsMap );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_reportAndExtSch_name_txtId" ), repAndSchNm, "Report and Extract scheduler name is not matched" );
		psDataComponentHelper.modifyComboBox( "PS_Detail_reportAndExtSch_type_comboId", repAndSchType );
		psDataComponentHelper.modifyTextBox( "PS_Detail_reportAndExtSch_restartAttemp_txtId", RestAttempt );
		psDataComponentHelper.modifyComboBox( "PS_Detail_reportAndExtSch_ftpLoc_comboId", FtpLocation );
		if ( ValidationHelper.isTrue( ChckMisTaskFlg ) )
			CheckBoxHelper.check( "PS_Detail_reportAndExtSch_chckMissTaks_chckBxId" );
		else
			CheckBoxHelper.uncheck( "PS_Detail_reportAndExtSch_chckMissTaks_chckBxId" );
	}

	/*
	 * This method is for configure Report Extracte Instance panel*/
	protected void configReportExtInstance() throws Exception
	{
		String configRepExtInstTestCase = ExcelHolder.getKey( repAndExtScheduleDetailsMap, "ConfigReportAndExtInstTestCase" );
		initializeWorkbookNmSheetNm();
		if ( ValidationHelper.isNotEmpty( configRepExtInstTestCase ) )
		{
			List<Map<String, String>> listOfmap = psDataComponentHelper.getListOfTestCaseMap( path, workBookName, sheetName, configRepExtInstTestCase );
			for ( int i = 0; i < listOfmap.size(); i++ )
			{
				Map<String, String> map = listOfmap.get( i );
				ReportAndExtInstance reportAndExtInstance = new ReportAndExtInstance( map );
				int row = i + 1;
				reportAndExtInstance.configReportExtInstance( row );
				map = null;
			}
		}
		else
			FailureHelper.failTest( "Configure Table Instance Test Case should not be empty" );

	}

	/*Method is for configure of   Schedule  time*/
	private void configScheduleTime() throws Exception
	{
		initializeVarSchTime( repAndExtScheduleDetailsMap );
		TestDataHelper testDataObj = new TestDataHelper();
		String[][] dayGroupsArr = testDataObj.getStringValue( dayGroups, firstLevelDelimiter, secondLevelDelimiter );
		psDataComponentHelper.updateScheduleTimes( freqMultiplier, frequency, nextSchedule, dayGroupsArr );
	}
}
