package com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn;

import org.dbunit.operation.ExclusiveTransactionException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.ReportAndExtDefinition;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn.Comp.RepAndExtComponent;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn.Comp.RepAndExtComponentContext;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn.Comp.TrafficReportComponent;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn.Comp.UsageBirtRepComponent;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn.Comp.UsgRevCostDetailRepComp;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn.Comp.UsgRevCostOverviewRepComp;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ReportAndExtDefnDetails extends PSAcceptanceTest
{
	protected Map<String, String> reportAndExtDefnDetailsMap = null;
	protected String repAndExtName;
	protected String repAndExtGrp;
	protected String repAndExtType;
	protected String repAndExtComponent;
	protected String repAndExtValidComponent;
	protected String repAndExtAllowDownloadFlg;
	protected String repAndExtAllowEmailFlg;
	protected String path;
	protected String workbookName;
	protected String sheetName;

	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	ReportAndExtDefinition reportAndExtDefinition = new ReportAndExtDefinition();

	/**	constructor
	 * @param reportAndExtDefnDetailsMap
	 */
	public ReportAndExtDefnDetails( Map<String, String> reportAndExtDefnDetailsMap )
	{

		this.reportAndExtDefnDetailsMap = reportAndExtDefnDetailsMap;
	}

	/*This method is for initialize variable*/
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		repAndExtName = ExcelHolder.getKey( map, "ReportExtName" );
		repAndExtGrp = ExcelHolder.getKey( map, "ReportExtGroup" );
		repAndExtType = ExcelHolder.getKey( map, "ReportExtType" );
		repAndExtComponent = ExcelHolder.getKey( map, "ReportExtComponent" );
		repAndExtValidComponent = ExcelHolder.getKey( map, "ReportExtValidationComponent" );
		repAndExtAllowDownloadFlg = ExcelHolder.getKey( map, "AllowDownloadFlg" );
		repAndExtAllowEmailFlg = ExcelHolder.getKey( map, "AllowEmailFlg" );
		path = reportAndExtDefinition.getPath();
		workbookName = reportAndExtDefinition.getWorkbookName();
		sheetName = reportAndExtDefinition.getSheetName();
	}

	/*This method is for configure report and extract definition*/
	public void configureReportAndExtDefn() throws Exception
	{
		initializeVariable( reportAndExtDefnDetailsMap );
		configReportAndExtDetails();
		configReportAndExtTypeParam( repAndExtType );
		configReportAndExtComponent( repAndExtComponent );
		configReportExtParameters();
		configReportExtEmail();
		saveReportExtDefn();
	}

	private void configReportExtEmail() throws Exception
	{
		if ( ValidationHelper.isTrue( repAndExtAllowEmailFlg ) )
		{
			ReportAndExtDefnEmail reportExtDefnEmail = new ReportAndExtDefnEmail( reportAndExtDefnDetailsMap );
			reportExtDefnEmail.repAndExtEmailTabConfig();
		}

		else
		{
			TabHelper.gotoTab( GenericHelper.getORProperty( "PS_Detail_reportAndExtDefn_email_emailTabXpath" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertFalse( psDataComponentHelper.isTabSelected( "Email" ), "Email tab should not be selected if Allow Email checkbox is unchecked" );

		}
	}

	/*This method is for modify report and extract definition*/
	public void modifyReportAndExtDefn() throws Exception
	{
		modifyReportAndExtDetails();
		modifyReportAndExtTypeParam( repAndExtType );
		modifyReportAndExtComponent( repAndExtComponent );
		configReportExtParameters();
		saveReportExtDefn();
	}

	/*This method is for configure report and extract details*/
	protected void configReportAndExtDetails() throws Exception
	{

		TextBoxHelper.type( "PS_Detail_reportAndExtDefn_RepExtDefname_textID", repAndExtName );
		ComboBoxHelper.select( "PS_Detail_reportAndExtDefn_RepExtDefnGroup_comboID", repAndExtGrp );
		ComboBoxHelper.select( "PS_Detail_reportAndExtDefn_RepExtDefnType_comboID", repAndExtType );
		ComboBoxHelper.select( "PS_Detail_reportAndExtDefn_RepExtDefnComponent_comboID", repAndExtComponent );
		ComboBoxHelper.select( "PS_Detail_reportAndExtDefn_RepExtDefnValidationComp_comboID", repAndExtValidComponent );
		if ( ValidationHelper.isTrue( repAndExtAllowDownloadFlg ) )
			CheckBoxHelper.check( "PS_Detail_reportAndExtDefn_AllowDownload_chckBxID" );
		if ( ValidationHelper.isFalse( repAndExtAllowDownloadFlg ) )
			CheckBoxHelper.uncheck( "PS_Detail_reportAndExtDefn_AllowDownload_chckBxID" );
		if ( ValidationHelper.isTrue( repAndExtAllowEmailFlg ) )
			CheckBoxHelper.check( "PS_Detail_reportAndExtDefn_AllowEmail_chckBxID" );

	}

	/*This method is for modify report and extract details*/
	protected void modifyReportAndExtDetails() throws Exception
	{
		initializeVariable( reportAndExtDefnDetailsMap );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_reportAndExtDefn_RepExtDefname_textID" ), repAndExtName, "Report and Extract Definition name is not matched" );
		psDataComponentHelper.modifyComboBox( "PS_Detail_reportAndExtDefn_RepExtDefnGroup_comboID", repAndExtGrp );
		psDataComponentHelper.checkComboBoxDisabled( "PS_Detail_reportAndExtDefn_RepExtDefnType_comboID", repAndExtType );
		psDataComponentHelper.checkComboBoxDisabled( "PS_Detail_reportAndExtDefn_RepExtDefnComponent_comboID", repAndExtComponent );
		psDataComponentHelper.modifyComboBox( "PS_Detail_reportAndExtDefn_RepExtDefnValidationComp_comboID", repAndExtValidComponent );
		if ( ValidationHelper.isTrue( repAndExtAllowDownloadFlg ) )
			CheckBoxHelper.check( "PS_Detail_reportAndExtDefn_AllowDownload_chckBxID" );
		if ( ValidationHelper.isFalse( repAndExtAllowDownloadFlg ) )
			CheckBoxHelper.uncheck( "PS_Detail_reportAndExtDefn_AllowDownload_chckBxID" );
	}

	/*This method is for configure report and extract type paramters*/
	private void configReportAndExtTypeParam( String repAndExtType ) throws Exception
	{
		ReportAndExtTypeContext reportAndExtTypeContext = getObjectOfRepAndExtType( repAndExtType );
		reportAndExtTypeContext.configReportAndExTypeParmeters();
	}

	/*This method is for modify report and extract type paramters*/
	private void modifyReportAndExtTypeParam( String repAndExtType ) throws Exception
	{
		ReportAndExtTypeContext reportAndExtTypeContext = getObjectOfRepAndExtType( repAndExtType );
		reportAndExtTypeContext.modifyReportAndExTypeParmeters();
	}

	/*This method is for configure report and extract Component*/
	private void configReportAndExtComponent( String repAndExtComponent ) throws Exception
	{
		String repAndExtCompTestcase = ExcelHolder.getKey( reportAndExtDefnDetailsMap, "RepAndExtComponentTestCase" );
		if ( ValidationHelper.isNotEmpty( repAndExtCompTestcase ) )
		{
			Map<String, String> mapRepAndExtCompTestCase = psDataComponentHelper.getTestCaseMap( path, workbookName, sheetName, repAndExtCompTestcase );
			boolean isCompWithPropertyPanel = getComponentForPropertyPanel().stream().anyMatch( x -> x.equals( repAndExtComponent ) );
			if ( isCompWithPropertyPanel )
			{
				RepAndExtComponentContext repAndExtComponentContext = getObjectOfRepAndExtComponent( mapRepAndExtCompTestCase, repAndExtComponent );
				repAndExtComponentContext.configReportAndExtComponent();
			}
			/*
			 * if other component than use this common method
			 */
			else
			{
				RepAndExtComponent repAndExtCompOb = new RepAndExtComponent( mapRepAndExtCompTestCase );
				repAndExtCompOb.configReportAndExtComponent();
			}
		}
	}

	/*This method is for modify report and extract Component*/
	private void modifyReportAndExtComponent( String repAndExtComponent ) throws Exception
	{
		String repAndExtCompTestcase = ExcelHolder.getKey( reportAndExtDefnDetailsMap, "RepAndExtComponentTestCase" );
		if ( ValidationHelper.isNotEmpty( repAndExtCompTestcase ) )
		{
			Map<String, String> mapRepAndExtCompTestCase = psDataComponentHelper.getTestCaseMap( path, workbookName, sheetName, repAndExtCompTestcase );
			boolean isCompWithPropertyPanel = getComponentForPropertyPanel().stream().anyMatch( x -> x.equals( repAndExtComponent ) );
			if ( isCompWithPropertyPanel )
			{
				RepAndExtComponentContext repAndExtComponentContext = getObjectOfRepAndExtComponent( mapRepAndExtCompTestCase, repAndExtComponent );
				repAndExtComponentContext.modifyReportAndExtComponent();
			}
			/*
			 * if other component than use this common method
			 */
			else
			{
				RepAndExtComponent repAndExtCompOb = new RepAndExtComponent( mapRepAndExtCompTestCase );
				repAndExtCompOb.configReportAndExtComponent();
			}
		}

	}

	/*
	 * This method is for configure Report Extracte paramters panel*/
	private void configReportExtParameters() throws Exception
	{
		ParametersContext parametersContext = getObjectOfParamters();
		parametersContext.configReportExtParameters();
	}

	/*This method is for get object of Parameters with Type or Name*/
	private ParametersContext getObjectOfParamters()
	{
		ParametersContext parametersContext;
		/*Optional<String> optParamName = reportAndExtDefnDetailsMap.entrySet().stream().filter( x -> x.getKey().equals( "ParameterName" ) ).map( Map.Entry::getValue ).findFirst();
		if ( optParamName.isEmpty() )
			parametersContext = new ParametersContext( new ConfigParamtersWithFixedName( reportAndExtDefnDetailsMap ) );
		else
			parametersContext = new ParametersContext( new ConfigParameterWithName( reportAndExtDefnDetailsMap ) );
			*/
		parametersContext = new ParametersContext( new ConfigParameterWithName( reportAndExtDefnDetailsMap ) );
		return parametersContext;
	}

	/*This method is for get object  report and extract type paramters*/
	private ReportAndExtTypeContext getObjectOfRepAndExtType( String repAndExtType ) throws Exception
	{
		ReportAndExtTypeContext reportAndExtTypeContext = null;
		String repAndExtTypeTestcase = ExcelHolder.getKey( reportAndExtDefnDetailsMap, "RepAndExtTypeTestCase" );
		Map<String, String> mapRepAndExtTypeTestCase = psDataComponentHelper.getTestCaseMap( path, workbookName, sheetName, repAndExtTypeTestcase );
		switch( repAndExtType )
		{
		case "Data Warehouse Extract Report":
			reportAndExtTypeContext = new ReportAndExtTypeContext( new DataWarehouseExtType( mapRepAndExtTypeTestCase ) );

			break;
		case "Birt Report":
			reportAndExtTypeContext = new ReportAndExtTypeContext( new BirtReportType( mapRepAndExtTypeTestCase ) );
			break;
		case "Generic Usage Report":
			reportAndExtTypeContext = new ReportAndExtTypeContext( new GenericUsageReports( mapRepAndExtTypeTestCase ) );

			break;
		default:
			Log4jHelper.logInfo( "No type is matched" );
		}
		return reportAndExtTypeContext;

	}

	/*This method is for get object  report and extract Component */
	private RepAndExtComponentContext getObjectOfRepAndExtComponent( Map<String, String> mapRepAndExtCompTestCase, String repAndExtComponent ) throws Exception
	{
		RepAndExtComponentContext reportAndExtComponentContext = null;
		switch( repAndExtComponent )
		{
		case "Traffic Report Component":
			reportAndExtComponentContext = new RepAndExtComponentContext( new TrafficReportComponent( mapRepAndExtCompTestCase ) );

			break;
		case "Usage Birt Report Component":
			reportAndExtComponentContext = new RepAndExtComponentContext( new UsageBirtRepComponent( mapRepAndExtCompTestCase ) );
			break;
		case "Usage Revenue Cost Detail Report Component":
			reportAndExtComponentContext = new RepAndExtComponentContext( new UsgRevCostDetailRepComp( mapRepAndExtCompTestCase ) );
			break;
		case "Usage Revenue Cost Overview Report Component":
			reportAndExtComponentContext = new RepAndExtComponentContext( new UsgRevCostOverviewRepComp( mapRepAndExtCompTestCase ) );
			break;
		default:
			Log4jHelper.logInfo( "No Component is matched" );
		}
		return reportAndExtComponentContext;

	}

	//Component names with Propeerty Panel
	private List<String> getComponentForPropertyPanel()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "Traffic Report Component" );
		listColumn.add( "Usage Birt Report Component" );
		listColumn.add( "Usage Revenue Cost Detail Report Component" );
		listColumn.add( "Usage Revenue Cost Overview Report Component" );
		return listColumn;
	}

	/*This method is for save report and extract definition*/
	private void saveReportExtDefn() throws Exception
	{
		psGenericHelper.detailSave( "PS_Detail_reportAndExtDefn_save_btnID", repAndExtName, "Name" );
	}
}
