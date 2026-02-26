package com.subex.rocps.automation.helpers.application.aggregation;

import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class AggregationProcessor extends PSAcceptanceTest
{

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> aggrProExcel = null;
	protected Map<String, String> aggrProMap = null;
	protected Map<String, String> defaultMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected String name;
	protected String type;
	protected String reratingComponent;
	protected String statisticsComponent;
	protected String rerateAuto;
	protected String aggrOutputFileDir;
	protected String aggrConfig;
	protected String batchSize;
	protected String defaultErrorStatus;
	protected String errorIDAllocationSize;
	protected String noOfRecordsPerFile;
	protected String statisticsFileOutputDir;
	protected String xdrAggregationConfig;

	protected String compressionType;
	protected String copyMove;
	protected String decimalFormat;
	protected String delimiter;
	protected String fileExtension;
	protected String fileHeaderComponent;
	protected String fileNmPrefix;
	protected String fileNmngConvComponent;
	protected String fileTrailerComponent;
	protected String maximumAggregatedFileSize;
	protected String metaDataFileGenComp;
	protected String noOfThreads;
	protected String partialFileNmDtFormat;
	protected String recordHeaderReq;
	protected String recordsDtFormat;
	protected String segmentFormatterComponent;
	protected String sequenceNoGenComponent;
	protected String splitSegXdr;
	protected String storeComponent;
	protected String xdrFullFileOutputDir;
	protected String xdrPartialFileOutputDir;
	protected String xdrExtTemplate;
	protected String xdrMaxSeqNumber;
	protected String xdrMinSeqNumber;
	protected String xdrSeqNumberIncrBy;
	protected String xdrSeqNumberPattern;
	protected String xdrTargetDir;
	PSStringUtils strObj = new PSStringUtils();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	@Test
	public AggregationProcessor( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		aggrProExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( aggrProExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public AggregationProcessor( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		aggrProExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( aggrProExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the ServiceType
	 * 
	 */
	public void aggregationProcessorCreation() throws Exception
	{
		try
		{

			NavigationHelper.navigateToScreen( "Aggregation Processor" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				aggrProMap = excelHolderObj.dataMap( paramVal );

				initializeVariables( aggrProMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isAggreationProPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_AggrProcessor_name_txtID", name, "Name" );
				if ( !isAggreationProPresent )
				{
					genericHelperObj.clickNewAction( clientPartition );
					GenericHelper.waitForLoadmask();
					newaggregationProcessor();
					ButtonHelper.click( "PS_Detail_AggrProcessor_save_btn" );
					GenericHelper.waitForSave();
					assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), "Aggregation Processor is not configured" + " with error message " + LabelHelper.getText( "errorText" ) );
					Log4jHelper.logInfo( "Aggregation Processor is created successfully with name " + name );
				}
				else				
					Log4jHelper.logInfo( "Aggregation Processor is available with name " + name );	
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is to create new RouteGroup
	 */
	protected void newaggregationProcessor() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_AggrProcessor_name_txtID", name );
		ComboBoxHelper.select( "PS_Detail_AggrProcessor_wrapperID", "PS_Detail_AggrProcessor_type_ComboID", type );
		ComboBoxHelper.select( "PS_Detail_AggrProcessor_wrapperID", "PS_Detail_AggrProcessor_reratingComp_comboID", reratingComponent );
		ComboBoxHelper.select( "PS_Detail_AggrProcessor_wrapperID", "PS_Detail_AggrProcessor_statisticsComp_ComboID", statisticsComponent );

		boolean isrerateAuto = CheckBoxHelper.isChecked( "PS_Detail_AggrProcessor_rerateAuto_chkBx" );
		if ( reratingComponent.contentEquals( "Default Rerate Summary Processor Component" ) && !isrerateAuto )
		{
			CheckBoxHelper.check( "PS_Detail_AggrProcessor_rerateAuto_chkBx" );
		}
		propertiesConfig();
	}

	/*
	 * This method is for properties Config
	 */
	protected void propertiesConfig() throws Exception
	{
		if ( type.contentEquals( "Default Aggregation Processor Factory" ) )		
			defaultAggregationProcessorFactory();	

		else if ( type.contentEquals( "XDR Extraction Processor Factory" ) )		
			xdrAggregationProcessorFactory();		
	}

	/*
	 * this method is for Default Aggregation Processor Factory component
	 */
	protected void defaultAggregationProcessorFactory() throws Exception
	{

		initializeDefaultAggrVariables( aggrProMap );
		PropertyGridHelper.typeInDataDir( "Aggregate File Output Directory *", aggrOutputFileDir, secondLevelDelimiter );
		PropertyGridHelper.selectInComboBox( "Aggregation Configuration *", aggrConfig );
		PropertyGridHelper.typeInTextBox( "Batch Size *", batchSize );
		PropertyGridHelper.selectInComboBox( "Default Error Status *", defaultErrorStatus );
		PropertyGridHelper.typeInTextBox( "Error Id Allocation Size *", errorIDAllocationSize );
		PropertyGridHelper.typeInTextBox( "Number Of Records Per File *", noOfRecordsPerFile );
		PropertyGridHelper.typeInDataDir( "Statistics File Output Directory *", statisticsFileOutputDir, secondLevelDelimiter );

	}

	/*
	 * This method is for XDR Aggregation Processor Factory component
	 */
	protected void xdrAggregationProcessorFactory() throws Exception
	{
		initializeXDRAggrVariables( aggrProMap );
		PropertyGridHelper.selectInComboBox( "Compression Type", compressionType );
		PropertyGridHelper.selectInComboBox( "Copy/Move", copyMove );
		PropertyGridHelper.selectInComboBox( "Decimal Format *", decimalFormat );
		PropertyGridHelper.typeInTextBox( "Delimiter *", delimiter );
		PropertyGridHelper.selectInComboBox( "File Extension *", fileExtension );
		if ( ValidationHelper.isNotEmpty( fileHeaderComponent ) )
			PropertyGridHelper.selectInComboBox( "File Header Component", fileHeaderComponent );
		if ( ValidationHelper.isNotEmpty( fileNmPrefix ) )
			PropertyGridHelper.typeInTextBox( "File Name Prefix", fileNmPrefix );
		PropertyGridHelper.selectInComboBox( "File Naming Convention Component", fileNmngConvComponent );
		if ( ValidationHelper.isNotEmpty( fileTrailerComponent ) )
			PropertyGridHelper.selectInComboBox( "File Trailer Component", fileTrailerComponent );
		PropertyGridHelper.typeInTextBox( "Maximum Aggregated File Size(in MB) *", maximumAggregatedFileSize );
		PropertyGridHelper.selectInComboBox( "Meta Data File Generation Component", metaDataFileGenComp );
		PropertyGridHelper.typeInTextBox( "No Of Threads For Parallel Execution *", noOfThreads );

		WebElement arrowElement = ElementHelper.getElement( GenericHelper.getORProperty( "PS_Detail_AggrProcessor_partialFNmDt_arrow_xpath" ) );
		MouseHelper.click( arrowElement );
		String dateSelectXpath = GenericHelper.getORProperty( "PS_Detail_AggrProcessor_partialFNmDt_dtSelection_xpath" );
		dateSelectXpath = dateSelectXpath.replace( "date", partialFileNmDtFormat );
		WebElement dateSelect = ElementHelper.getElement( dateSelectXpath );
		Actions actions = new Actions( driver );	
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		actions.moveToElement( dateSelect ).click().build().perform();

		if ( ValidationHelper.isTrue( recordHeaderReq ) )
			PropertyGridHelper.clickCheckBox( "Record Header Required", recordHeaderReq );

		PropertyGridHelper.selectInComboBox( "Records Date Format *", recordsDtFormat );
		PropertyGridHelper.selectInComboBox( "Segment Formatter Component *", segmentFormatterComponent );
		PropertyGridHelper.selectInComboBox( "Sequence Number Generation Component *", sequenceNoGenComponent );
		if ( ValidationHelper.isTrue( splitSegXdr ) )
			PropertyGridHelper.clickCheckBox( "Split Segment XDR", splitSegXdr );
		PropertyGridHelper.selectInComboBox( "Store Component *", storeComponent );
		PropertyGridHelper.typeInDataDir( "XDR Extraction Full File Output Directory *", xdrFullFileOutputDir, secondLevelDelimiter );
		PropertyGridHelper.typeInDataDir( "XDR Extraction Partial File Output Directory *", xdrPartialFileOutputDir, secondLevelDelimiter );
		PropertyGridHelper.selectInComboBox( "XDR Extraction Template *", xdrExtTemplate );
		
		PropertyGridHelper.typeInTextBox( "XDR Maximum Sequence Number *", xdrMaxSeqNumber );
		PropertyGridHelper.typeInTextBox( "XDR Minimum Sequence Number *", xdrMinSeqNumber);
		PropertyGridHelper.typeInTextBox( "XDR Sequence Number Increment By *", xdrSeqNumberIncrBy );
		PropertyGridHelper.typeInTextBox( "XDR Sequence Number Pattern", xdrSeqNumberPattern );
		
		PropertyGridHelper.typeInDataDir( "XDR Target Directory", xdrTargetDir, secondLevelDelimiter );

	}
	
	public void editaggregationProcessor() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Aggregation Processor" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				aggrProMap = excelHolderObj.dataMap( paramVal );
				initializeVariables( aggrProMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isAggreationProPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_AggrProcessor_name_txtID", name, "Name" );
				if ( isAggreationProPresent )
				{
					int row = GridHelper.getRowNumber( "SearchGrid", name, "Name" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );									
					GenericHelper.waitForLoadmask();
					newaggregationProcessor();				
					genericHelperObj.detailSave( "PS_Detail_AggrProcessor_save_btn", name, "Name" );				
					Log4jHelper.logInfo( "Aggregation Processor is edited successfully with name " + name );
				}
				else				
					Log4jHelper.logInfo( "Aggregation Processor is available with name " + name );	
			}

		}
		catch ( Exception e )
		{

			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	protected void editaggregationProcessorDetail() throws Exception
	{

		TextBoxHelper.type( "PS_Detail_AggrProcessor_name_txtID", name );
		if(ValidationHelper.isNotEmpty( type ))
			ComboBoxHelper.select( "PS_Detail_AggrProcessor_wrapperID", "PS_Detail_AggrProcessor_type_ComboID", type );
		if(ValidationHelper.isNotEmpty( reratingComponent ))
			assertEquals(ComboBoxHelper.getValue( "PS_Detail_AggrProcessor_wrapperID", "PS_Detail_AggrProcessor_reratingComp_comboID"), reratingComponent );
		if(ValidationHelper.isNotEmpty( statisticsComponent ))
			ComboBoxHelper.select( "PS_Detail_AggrProcessor_wrapperID", "PS_Detail_AggrProcessor_statisticsComp_ComboID", statisticsComponent );

		if ( type.contentEquals( "Default Aggregation Processor Factory" ) )		
			defaultAggregationProcessorFactory();	

		else if ( type.contentEquals( "XDR Extraction Processor Factory" ) )		
			xdrAggregationProcessorFactory();	

	}


	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Aggregation Processor" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			aggrProMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( aggrProMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( searchScreenColumns );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericHelperObj.totalColumns( excelColumnNames );
		}

	}

	/*
	 * This method is to initialize instance variables
	 */
	protected void initializeVariables( Map<String, String> map ) throws Exception
	{

		clientPartition = ExcelHolder.getKey( map, "Partition" );
		name = ExcelHolder.getKey( map, "Name" );
		type = ExcelHolder.getKey( map, "Type" );
		reratingComponent = ExcelHolder.getKey( map, "ReratingComponent" );
		statisticsComponent = ExcelHolder.getKey( map, "StatisticsComponent" );
		rerateAuto = ExcelHolder.getKey( map, "RerateAuto" );

	}

	/*
	 * This method is to initialize default Aggregation processor Factory
	 * component variables
	 */
	protected void initializeDefaultAggrVariables( Map<String, String> map ) throws Exception
	{
		aggrOutputFileDir = ExcelHolder.getKey( map, "AggrOutputFileDir" );
		aggrConfig = ExcelHolder.getKey( map, "AggrConfig" );
		batchSize = ExcelHolder.getKey( map, "BatchSize" );
		defaultErrorStatus = ExcelHolder.getKey( map, "DefaultErrorStatus" );
		errorIDAllocationSize = ExcelHolder.getKey( map, "ErrorIDAllocationSize" );
		noOfRecordsPerFile = ExcelHolder.getKey( map, "NoOfRecordsPerFile" );
		statisticsFileOutputDir = ExcelHolder.getKey( map, "StatisticsFileOutputDir" );
	}

	/*
	 * This method is to initialize XDR Aggregation processor Factory component
	 * Variables
	 */
	protected void initializeXDRAggrVariables( Map<String, String> xdrMap ) throws Exception
	{

		compressionType = ExcelHolder.getKey( xdrMap, "CompressionType" );
		copyMove = ExcelHolder.getKey( xdrMap, "CopyMove" );
		decimalFormat = ExcelHolder.getKey( xdrMap, "DecimalFormat" );
		delimiter = ExcelHolder.getKey( xdrMap, "Delimiter" );
		fileExtension = ExcelHolder.getKey( xdrMap, "FileExtension" );
		fileHeaderComponent = ExcelHolder.getKey( xdrMap, "FileHeaderComponent" );
		fileNmPrefix = ExcelHolder.getKey( xdrMap, "FileNamePrefix" );
		fileNmngConvComponent = ExcelHolder.getKey( xdrMap, "FileNamingConvenComponent" );
		fileTrailerComponent = ExcelHolder.getKey( xdrMap, "FileTrailerComponent" );
		maximumAggregatedFileSize = ExcelHolder.getKey( xdrMap, "MaximumAggregatedFileSize" );
		metaDataFileGenComp = ExcelHolder.getKey( xdrMap, "MetaDataFileGenComponent" );
		noOfThreads = ExcelHolder.getKey( xdrMap, "NoOfThreadsParallelExecution" );
		partialFileNmDtFormat = ExcelHolder.getKey( xdrMap, "PartialFileNameDateFormat" );
		recordHeaderReq = ExcelHolder.getKey( xdrMap, "RecordHeaderRequired" );
		recordsDtFormat = ExcelHolder.getKey( xdrMap, "RecordsDateFormat" );
		segmentFormatterComponent = ExcelHolder.getKey( xdrMap, "SegmentFormatterComponent" );
		sequenceNoGenComponent = ExcelHolder.getKey( xdrMap, "SequenceNumberGenerationComponent" );
		splitSegXdr = ExcelHolder.getKey( xdrMap, "SplitSegmentXDR" );
		storeComponent = ExcelHolder.getKey( xdrMap, "StoreComponent" );
		xdrFullFileOutputDir = ExcelHolder.getKey( xdrMap, "XDRExtrFullFileOutputDir" );
		xdrPartialFileOutputDir = ExcelHolder.getKey( xdrMap, "XDRExtrPartFileOutputDir" );
		xdrExtTemplate = ExcelHolder.getKey( xdrMap, "XDRExtractionTemplate" );
		xdrMaxSeqNumber = ExcelHolder.getKey( xdrMap, "XDRMaxSeqNumber" );
		xdrMinSeqNumber = ExcelHolder.getKey( xdrMap, "XDRMinSeqNumber" );
		xdrSeqNumberIncrBy = ExcelHolder.getKey( xdrMap, "XDRSeqNumberIncrBy" );
		xdrSeqNumberPattern = ExcelHolder.getKey( xdrMap, "XDRSeqNumberPattern" );
		xdrTargetDir = ExcelHolder.getKey( xdrMap, "XDRTargetDirectory" );

	}

}
