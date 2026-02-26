package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntitySearchHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class StreamHelper extends ROCAcceptanceTest {
	
	static String thirdLevelDelimiter = "!";
	
	public void createStream( String path, String workBookName, String workSheetName, String testCaseName, int occurence ) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
			
			for ( int i = 0; i < excelData.get( "Name" ).size(); i++ ) {
				String partition = excelData.get( "Partition" ).get( i );
				String streamName = excelData.get( "Name" ).get( i );
				String[] stageTypes = testData.getStringValue(excelData.get( "Stream Stage Type" ).get( i ), firstLevelDelimiter);
				String[] stageNames = testData.getStringValue(excelData.get( "Stream Stage Name" ).get( i ), firstLevelDelimiter);
				boolean[] restartTask = testData.getBooleanValue(excelData.get( "Enable Restart Task" ).get( i ), firstLevelDelimiter);
				String[] restartAttempts = testData.getStringValue(excelData.get( "Restart Attempts" ).get( i ), firstLevelDelimiter);
				String[] restartInterval = testData.getStringValue(excelData.get( "Restart Interval" ).get( i ), firstLevelDelimiter);
				String[] restartLookback = testData.getStringValue(excelData.get( "Restart Lookback" ).get( i ), firstLevelDelimiter);
				String[][] propertyValues = testData.getStringValue(excelData.get( "Property Values" ).get( i ), firstLevelDelimiter, secondLevelDelimiter);
				String[][] parseOutputMap = testData.getStringValue(excelData.get( "Parse Output Map" ).get( i ), firstLevelDelimiter, secondLevelDelimiter);
				
				createStream(partition, streamName, stageTypes, stageNames, restartTask, restartAttempts, restartInterval,
						restartLookback, propertyValues, parseOutputMap);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createStream( String partition, String streamName, String[] stageTypes, String[] stageNames, boolean[] restartTask,
			String[] restartAttempts, String[] restartInterval, String[] restartLookback, String[][] propertyValues, String[][] parseOutputMap ) throws Exception {
		try {
			int rowNo = navigateToStream(streamName);
			NavigationHelper.navigateToNewOrEdit(rowNo, partition, "Stream", "Stream_Name");
			GenericHelper.waitForElement("Stream_Name", detailScreenWaitSec);
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			updateStream(streamName, stageTypes, stageNames, restartTask, restartAttempts, restartInterval, restartLookback, propertyValues, parseOutputMap);
			
			saveStream(streamName, detailScreenTitle);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateStream( String streamName, String[] stageTypes, String[] stageNames, boolean[] restartTask, String[] restartAttempts,
			String[] restartInterval, String[] restartLookback, String[][] propertyValues, String[][] parseOutputMap ) throws Exception {
		try {
			thirdLevelDelimiter = configProp.getThirdLevelDelimiter();
			TextBoxHelper.type( "Stream_Name", streamName);
			
			if (ValidationHelper.isNotEmpty(stageTypes)) {
				for (int i = 0, pom = 0; i < stageTypes.length; i++) {
					String checkValue = stageTypes[i];
					
					if (ValidationHelper.isNotEmpty(stageNames[i]))
						checkValue = stageNames[i];
					boolean isPresent = GridHelper.isValuePresent("Stream_StreamStage_Grid", checkValue, "Name");
					
					if (isPresent) {
						Log4jHelper.logWarning("Stream Stage with name '" + checkValue + "' is already present in Stream '" + streamName + "'.");
						if (stageTypes[i].equals("Parse"))
							pom++;
					}
					else {
						if (ButtonHelper.isEnabled("Stream_StreamStage_Add")) {
							ButtonHelper.click("Stream_StreamStage_Add");
							GenericHelper.waitForLoadmask(detailScreenWaitSec);
							GenericHelper.waitForAJAXReady(detailScreenWaitSec);
							assertTrue(LabelHelper.isTitlePresent("New Stream Stage"), "Stream Stage popup did not appear.");
							
							ComboBoxHelper.select("Stream_StreamStage_Type", stageTypes[i]);
							GenericHelper.waitForLoadmask(detailScreenWaitSec);
							TextBoxHelper.type("Stream_StreamStage_Name", stageNames[i]);
							
							if (restartTask[i]) {
								CheckBoxHelper.check("Stream_StreamStage_RestartTask");
								TextBoxHelper.type("Stream_StreamStage_RestartAttempts", restartAttempts[i]);
								TextBoxHelper.type("Stream_StreamStage_RestartInterval", restartInterval[i]);
								TextBoxHelper.type("Stream_StreamStage_RestartLookback", restartLookback[i]);
							}
							
							if (ValidationHelper.isEmpty(propertyValues) || ValidationHelper.isEmpty(propertyValues[i]))
								pom = selectStreamStageType(stageTypes[i], null, parseOutputMap, pom);
							else
								pom = selectStreamStageType(stageTypes[i], propertyValues[i], parseOutputMap, pom);
							
							ButtonHelper.click( "OKButton" );
							GenericHelper.waitForLoadmask(detailScreenWaitSec);
							assertTrue(LabelHelper.isTitleNotPresent("New Stream Stage"), "Stream Stage did not get saved.");
							Log4jHelper.logInfo("Stream Stage with name '" + checkValue + "' is created in Stream '" + streamName + "'.");
						}
						else {
							FailureHelper.failTest("Stream Stage Add button is disabled in Stream detail screen.");
						}
					}
				}
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public int navigateToStream(String streamName) throws Exception {
		try {
			NavigationHelper.navigateToScreen( "Streams", "Stream Search" );
			int row = SearchGridHelper.gridFilterSearchWithTextBox("Stream_Name", streamName, "Name");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void saveStream(String streamName, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			if (ButtonHelper.isPresent("YesButton")) {
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Stream save did not happen.");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", streamName, "Name"), "Value '" + streamName + "' is not found in grid.");
			Log4jHelper.logInfo("Stream '" + streamName + "' created/updated.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private int selectStreamStageType( String stageType, String[] propertyValues, String[][] parseOutputMap, int pom ) throws Exception {
		try {
			switch (stageType) {
			case "Ageing":
				ageingStreamStage( propertyValues );
				break;
			case "Audit":
				auditStreamStage( propertyValues );
				break;
			case "Auto MRExtract Task":
				autoMRExtractTaskStreamStage( propertyValues );
				break;
			case "Bulk External Stage Data Load":
				bulkExternalStageDataLoadStreamStage( propertyValues );
				break;
			case "CDM Clean Up":
				cdmCleanUpStreamStage( propertyValues );
				break;
			case "Case Archiving":
				caseArchivingStreamStage( propertyValues );
				break;
			case "Case Group Manager":
				caseGroupManagerStreamStage( propertyValues );
				break;
			case "Correlation":
				correlationStreamStage( propertyValues );
				break;
			case "DICE Cleanup":
				diceCleanupStreamStage( propertyValues );
				break;
			case "Data Load":
				dataLoadStreamStage( propertyValues );
				break;
			case "Database Parse":
				databaseParseStreamStage( propertyValues, parseOutputMap[pom] );
				pom++;
				break;
			case "Direct Data Load":
				directDataLoadStreamStage( propertyValues );
				break;
			case "Direct Stage Data Load":
				directStageDataLoadStreamStage( propertyValues );
				break;
			case "Entity Export":
				entityExportStreamStage( propertyValues );
				break;
			case "Export All Rows":
				exportAllRowsStreamStage( propertyValues );
				break;
			case "Export Tariff Period":
				exportTariffPeriodStreamStage( propertyValues );
				break;
			case "External Stage Data Load":
				externalStageDataLoadStreamStage( propertyValues );
				break;
			case "File Merge":
				fileMergeStreamStage( propertyValues );
				break;
			case "File Sort Task":
				fileSortTaskStreamStage( propertyValues );
				break;
			case "File Transfer":
				fileTransferStreamStage( propertyValues );
				break;
			case "HLR Preprocessor":
				hlrPreprocessorStreamStage( propertyValues );
				break;
			case "Hybrid Data Maintenance":
				hybridDataMaintenancetreamStage( propertyValues );
				break;
			case "IN Preprocessor":
				inPreprocessorStreamStage( propertyValues );
				break;
			case "Inter File Record Sequence Check":
				interFileRSCStreamStage( propertyValues );
				break;
			case "LDC Correlator":
				ldcCorrelatorStreamStage( propertyValues );
				break;
			case "Light Weigth Files Data Load":
				lightWeightFilesDataLoadStreamStage( propertyValues );
				break;
			case "Manual Measure Request Cleanup":
				manualMeasureStreamStage( propertyValues );
				break;
			case "Mediation Reject Summary":
				mediationRejectSummaryStreamStage( propertyValues );
				break;
			case "Online LDC Correlator":
				onlineLDCCorrelatorStreamStage( propertyValues );
				break;
			case "Parse":
				parseStreamStage(propertyValues, parseOutputMap[pom]);
				pom++;
				break;
			case "Parse Summary":
				parseSummaryStreamStage( propertyValues );
				break;
			case "PreAggregation":
				preAggregationStreamStage( propertyValues );
				break;
			case "Rebuild Unusable Indexes":
				rebuildUnusableIndexesStreamStage( propertyValues );
				break;
			case "Run Script":
				runScriptStreamStage( propertyValues );
				break;
			case "Sequence Output":
				sequenceOutputStreamStage( propertyValues );
				break;
			case "Tag Duplicates":
				tagDuplicateStreamStage( propertyValues );
				break;
			case "Truncate And Load":
				trunacteAndLoadStreamStage( propertyValues );
				break;
			case "Upsert Task":
				upsertTaskStreamStage( propertyValues );
				break;
			case "Xlsx Preprocessor":
				xlsxPreprocessorStreamStage( propertyValues );
				break;
			case "Zip Collected Files":
				zipCollectedFilesStreamStage( propertyValues );
				break;
			default:
				break;
			}
			
			return pom;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateStreamStageProperties(String[] propertyNames, String[] propertyValues) throws Exception {
		try {
			for (int i = 0; i < propertyNames.length; i++)
				PropertyGridHelper.updateProperty(propertyNames[i], propertyValues[i], thirdLevelDelimiter);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	
	private void ageingStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.selectInComboBox("Ageing Entity *", propertyValues[0]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void auditStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.typeInTextBox("Measure Capability *", propertyValues[0]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void autoMRExtractTaskStreamStage( String[] propertyValues ) throws Exception {
		try {
			if (!ValidationHelper.isTrue(propertyValues[0]))
				PropertyGridHelper.unCheckCheckBox("Compress File *");
			if (!ValidationHelper.isTrue(propertyValues[1]))
				PropertyGridHelper.unCheckCheckBox("Consider Measure Request Created Time *");
			PropertyGridHelper.selectInComboBox("File Destination Type *", propertyValues[2]);
			PropertyGridHelper.typeInTextBox("Login name", propertyValues[3]);
			PropertyGridHelper.typeInTextBox("Login password", propertyValues[4]);
			PropertyGridHelper.typeInTextBox("Lookback Period *", propertyValues[5]);
			PropertyGridHelper.typeInTextBox("Max Thread", propertyValues[6]);
			PropertyGridHelper.typeInTextBox("Measure Extraction GroupId", propertyValues[7]);
			PropertyGridHelper.typeInTextBox("Server IP Address", propertyValues[8]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void bulkExternalStageDataLoadStreamStage( String[] propertyValues ) throws Exception {
		try {
			if (ValidationHelper.isTrue(propertyValues[0]))
				PropertyGridHelper.checkCheckBox("Delete Input File");
			if (ValidationHelper.isTrue(propertyValues[1]))
				PropertyGridHelper.checkCheckBox("Load Data into Reference and Target Reference Table *");
			
			PropertyGridHelper.typeInTextBox("Maximum No. of Files Per Task *", propertyValues[2]);
			PropertyGridHelper.typeInTextBox("Maximum No. of Parse Output Files Per Thread *", propertyValues[3]);
			PropertyGridHelper.typeInTextBox("Maximum No. of Threads *", propertyValues[4]);
			PropertyGridHelper.selectInComboBox("Parent Parse Stream Stage *", propertyValues[5]);
			PropertyGridHelper.selectInComboBox("Parent Stream Stage Group", propertyValues[6]);
			
			if (ValidationHelper.isTrue(propertyValues[7]))
				PropertyGridHelper.checkCheckBox("Populate DataLoad Statistics");
			
			PropertyGridHelper.selectInComboBox("Staging Database *", propertyValues[8]);
			PropertyGridHelper.typeInTextBox("Staging Directory Name (Oracle Directory) *", propertyValues[9]);
			PropertyGridHelper.selectInComboBox("Target Reference Database", propertyValues[10]);
			if (ValidationHelper.isTrue(propertyValues[11]))
				PropertyGridHelper.checkCheckBox("Truncate Before Load(Valid only for user defined Referenc...");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void cdmCleanUpStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.typeInTextBox("Look Back In Days", propertyValues[0]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void caseArchivingStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.typeInTextBox("Case Batch Commit Size *", propertyValues[0]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void caseGroupManagerStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.typeInTextBox("Max Group Manager Threads *", propertyValues[0]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void correlationStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.typeInTextBox("Minimum Required Data (%) *", propertyValues[0]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void diceCleanupStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.typeInTextBox("Retention Period(days) *", propertyValues[0]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void dataLoadStreamStage( String[] propertyValues ) throws Exception {
		try {
			if (ValidationHelper.isTrue(propertyValues[0]))
				PropertyGridHelper.checkCheckBox("Delete Input File");
			if (!ValidationHelper.isTrue(propertyValues[1]))
				PropertyGridHelper.unCheckCheckBox("Enable Retry On Failure");
			if (ValidationHelper.isTrue(propertyValues[2]))
				PropertyGridHelper.checkCheckBox("Log Data Load Output");
			
			PropertyGridHelper.typeInTextBox("Look Back Days To Do Conventional Data Load (In Days) *", propertyValues[3]);
			PropertyGridHelper.typeInTextBox("Maximum Error Count Per Parse Output File", propertyValues[4]);
			
			if (ValidationHelper.isTrue(propertyValues[5]))
				PropertyGridHelper.checkCheckBox("Populate DataLoad Statistics");
			if (ValidationHelper.isTrue(propertyValues[6]))
				PropertyGridHelper.checkCheckBox("Truncate Before Load(Valid only for user defined Referenc...");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void databaseParseStreamStage( String[] propertyValues, String[] parseOutputMap ) throws Exception {
		try {
			if (!ValidationHelper.isTrue(propertyValues[0]))
				PropertyGridHelper.unCheckCheckBox("Check For Stale Caches Every File");
			PropertyGridHelper.selectInComboBox("Data Source Connection *", propertyValues[1]);
			
			if (ValidationHelper.isTrue(propertyValues[2]))
				PropertyGridHelper.checkCheckBox("Log Error With Rejected Record");
			if (!ValidationHelper.isTrue(propertyValues[3]))
				PropertyGridHelper.unCheckCheckBox("Log Record Type Distributions");
			
			PropertyGridHelper.typeInDataDir("Output Directory *", propertyValues[4], thirdLevelDelimiter);
			PropertyGridHelper.typeInTextBox("Parse Table Name *", propertyValues[5]);
			PropertyGridHelper.typeInDataDir("Parser Definition File *", propertyValues[6], thirdLevelDelimiter);
			
			if (ValidationHelper.isTrue(propertyValues[7]))
				PropertyGridHelper.checkCheckBox("Skip Records On Error");
			
			ButtonHelper.click( "Stream_StreamStage_OK" );
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			addParseOutputMap(parseOutputMap);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void addParseOutputMap(String[] parseOutputMap) throws Exception {
		try {
			GenericHelper.waitForElement("YesButton", detailScreenWaitSec);
			assertTrue(PopupHelper.isTextPresent("Do you want to configure parse output maps ?"), "Parse Output Map Confirmation popup did not appear.");
			
			if ( ValidationHelper.isEmpty(parseOutputMap) ) {
				ButtonHelper.click( "NoButton" );
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
			else {
				ButtonHelper.click( "YesButton" );
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				GenericHelper.waitForElement("Stream_ParseOutput_Add", detailScreenWaitSec);
				assertTrue( LabelHelper.isTitlePresent( "Parse Output Maps" ), "Parse Output Map popup did not appear." );
				
				TestDataHelper testData = new TestDataHelper();
				String gridId = "Stream_ParseOutput_Grid";
				EntitySearchHelper entitySearch = new EntitySearchHelper();
				
				for ( int i = 0, row = 1; i < parseOutputMap.length; i++, row++ ) {
					String[] outputMap = testData.getStringValue(parseOutputMap[i], configProp.getThirdLevelDelimiter());
					ButtonHelper.click( "Stream_ParseOutput_Add" );
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					
					GridHelper.updateGridTextBox(gridId, "Stream_ParseOutput_OutputKey", row, "Output Key", "Table Instance", outputMap[0]);
					
					GridHelper.updateGridEntityCombo(gridId, "Stream_ParseOutput_TableInstance", row, "Table Instance", outputMap[1]);
					entitySearch.selectUsingGridFilterTextBox("Table Instance Search", "TableInst_TableName", outputMap[1], "Table Name");
					GridHelper.clickRow(gridId, row, "Output Key");
					
					GridHelper.updateGridCheckBox(gridId, "Stream_ParseOutput_DataLoad", row, "Data Load", outputMap[2]);
				}
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void directDataLoadStreamStage( String[] propertyValues ) throws Exception {
		try {
			if (ValidationHelper.isTrue(propertyValues[0]))
				PropertyGridHelper.checkCheckBox("Delete Input File");
			PropertyGridHelper.typeInTextBox("Maximum No of Files Per Task *", propertyValues[1]);
			PropertyGridHelper.typeInTextBox("Maximum No. of Parse Output Files Per Thread *", propertyValues[2]);
			PropertyGridHelper.typeInTextBox("Maximum No. of Threads *", propertyValues[3]);
			
			PropertyGridHelper.selectInComboBox("Parent Stream Stage *", propertyValues[4]);
			if (ValidationHelper.isTrue(propertyValues[5]))
				PropertyGridHelper.checkCheckBox("Truncate Before Load(Valid only for user defined Referenc...");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void directStageDataLoadStreamStage( String[] propertyValues ) throws Exception {
		try {
			if (ValidationHelper.isTrue(propertyValues[0]))
				PropertyGridHelper.checkCheckBox("Delete Input File");
			PropertyGridHelper.typeInTextBox("Maximum Error Count Per Parse Output File", propertyValues[1]);
			PropertyGridHelper.typeInTextBox("Maximum No. of Threads *", propertyValues[2]);
			
			if (ValidationHelper.isTrue(propertyValues[3]))
				PropertyGridHelper.checkCheckBox("Populate DataLoad Statistics");
			
			PropertyGridHelper.selectInComboBox("Staging Database *", propertyValues[4]);
			if (ValidationHelper.isTrue(propertyValues[5]))
				PropertyGridHelper.checkCheckBox("Truncate Before Load(Valid only for user defined Referenc...");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void entityExportStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.selectInComboBox("Entity Export Configuration *", propertyValues[0]);
			PropertyGridHelper.typeInTextBox("Export Directory *", propertyValues[1]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void exportAllRowsStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.selectInComboBox("File Compression Type *", propertyValues[0]);
			PropertyGridHelper.typeInTextBox("Number of Records Per File", propertyValues[1]);
			PropertyGridHelper.typeInDataDir("Root Directory *", propertyValues[2], thirdLevelDelimiter);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void exportTariffPeriodStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.typeInDataDir("Root Directory *", propertyValues[0], thirdLevelDelimiter);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void externalStageDataLoadStreamStage( String[] propertyValues ) throws Exception {
		try {
			if (ValidationHelper.isTrue(propertyValues[0]))
				PropertyGridHelper.checkCheckBox("Delete Input File");
			PropertyGridHelper.typeInTextBox("Maximum No. of Threads *", propertyValues[1]);
			if (ValidationHelper.isTrue(propertyValues[2]))
				PropertyGridHelper.checkCheckBox("Populate DataLoad Statistics");
			
			PropertyGridHelper.selectInComboBox("Staging Database *", propertyValues[3]);
			PropertyGridHelper.typeInTextBox("Staging Directory Name (Oracle Directory) *", propertyValues[4]);
			if (ValidationHelper.isTrue(propertyValues[5]))
				PropertyGridHelper.checkCheckBox("Truncate Before Load(Valid only for user defined Referenc...");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void fileMergeStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.typeInTextBox("Directory *", propertyValues[0]);
			PropertyGridHelper.selectInComboBox("File Collection *", propertyValues[1]);
			PropertyGridHelper.typeInTextBox("Number Of Files *", propertyValues[2]);
			PropertyGridHelper.typeInTextBox("Number Of Threads *", propertyValues[3]);
			PropertyGridHelper.selectInComboBox("Post Merge Action *", propertyValues[4]);
			PropertyGridHelper.typeInTextBox("Prefix *", propertyValues[5]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void fileSortTaskStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.typeInTextBox("Block Size (MB) *", propertyValues[0]);
			PropertyGridHelper.typeInDataDir("File Collection *", propertyValues[1], thirdLevelDelimiter);
			PropertyGridHelper.typeInTextBox("Output File Directory *", propertyValues[2]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void fileTransferStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.selectInComboBox("File Transfer Configuration *", propertyValues[0]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void hlrPreprocessorStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.typeInDataDir("Output Directory *", propertyValues[0], thirdLevelDelimiter);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void hybridDataMaintenancetreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.selectInComboBox("Usage Group *", propertyValues[0]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void inPreprocessorStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.typeInTextBox("Block Size *", propertyValues[0]);
			PropertyGridHelper.typeInTextBox("Dedicated Account Delimiter *", propertyValues[1]);
			PropertyGridHelper.typeInTextBox("Dedicated Account Prefix *", propertyValues[2]);
			PropertyGridHelper.typeInDataDir("IN Preprocessor Input Directory *", propertyValues[3], thirdLevelDelimiter);
			PropertyGridHelper.typeInDataDir("IN Preprocessor Output Directory *", propertyValues[4], thirdLevelDelimiter);
			
			if (!ValidationHelper.isTrue(propertyValues[5]))
				PropertyGridHelper.unCheckCheckBox("Is File Sorted");
			PropertyGridHelper.typeInTextBox("Output File Prefix *", propertyValues[6]);
			PropertyGridHelper.typeInTextBox("Subscriber Dump Prefix *", propertyValues[7]);
			PropertyGridHelper.typeInDataDir("Unprocessed File Output Directory *", propertyValues[8], thirdLevelDelimiter);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void interFileRSCStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.typeInTextBox("Maximum Threads for Group Names *", propertyValues[0]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void ldcCorrelatorStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.typeInTextBox("Block Size *", propertyValues[0]);
			if (!ValidationHelper.isTrue(propertyValues[1]))
				PropertyGridHelper.unCheckCheckBox("Fail Task On Missing Usage Partition");
			if (ValidationHelper.isTrue(propertyValues[2]))
				PropertyGridHelper.checkCheckBox("Generate File For Stitched CDR's With No Usage Partition");
			
			PropertyGridHelper.selectInComboBox("LDC Correlator *", propertyValues[3]);
			PropertyGridHelper.typeInTextBox("Maximum Thread *", propertyValues[4]);
			PropertyGridHelper.typeInTextBox("Memory Multiplier *", propertyValues[5]);
			PropertyGridHelper.typeInTextBox("Object Key *", propertyValues[6]);
			
			PropertyGridHelper.typeInDataDir("Output Directory", propertyValues[7], thirdLevelDelimiter);
			if (ValidationHelper.isTrue(propertyValues[8]))
				PropertyGridHelper.checkCheckBox("Real Time LDC");
			PropertyGridHelper.selectInComboBox("Usage Server *", propertyValues[9]);
			PropertyGridHelper.typeInTextBox("Waiting Time In Minutes On Data Load Tasks *", propertyValues[10]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void lightWeightFilesDataLoadStreamStage( String[] propertyValues ) throws Exception {
		try {
			if (ValidationHelper.isTrue(propertyValues[0]))
				PropertyGridHelper.checkCheckBox("Delete Input File");
			PropertyGridHelper.typeInTextBox("Maximum Error Count Per Thread", propertyValues[1]);
			PropertyGridHelper.typeInTextBox("Maximum No of Files Per Task *", propertyValues[2]);
			PropertyGridHelper.typeInTextBox("Maximum No. of Parse Output Files Per Thread *", propertyValues[3]);
			PropertyGridHelper.typeInTextBox("Maximum No. of Threads *", propertyValues[4]);
			PropertyGridHelper.selectInComboBox("Parent Stream Stage *", propertyValues[5]);
			if (ValidationHelper.isTrue(propertyValues[6]))
				PropertyGridHelper.checkCheckBox("Use Direct Load Mode *");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void manualMeasureStreamStage( String[] propertyValues ) throws Exception {
		try {
			if (!ValidationHelper.isTrue(propertyValues[0]))
				PropertyGridHelper.unCheckCheckBox("Drill Down Cleanup *");
			if (!ValidationHelper.isTrue(propertyValues[1]))
				PropertyGridHelper.unCheckCheckBox("KPI Result Cleanup *");
			if (!ValidationHelper.isTrue(propertyValues[2]))
				PropertyGridHelper.unCheckCheckBox("Measure Request Cleanup *");
			if (!ValidationHelper.isTrue(propertyValues[3]))
				PropertyGridHelper.unCheckCheckBox("Reporting Table Cleanup *");
			
			PropertyGridHelper.typeInTextBox("Retention Period(days) *", propertyValues[4]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void mediationRejectSummaryStreamStage( String[] propertyValues ) throws Exception {
		try {
			if (ValidationHelper.isTrue(propertyValues[0]))
				PropertyGridHelper.checkCheckBox("Delete Jar");
			
			PropertyGridHelper.typeInDataDir("Output Directory *", propertyValues[1], thirdLevelDelimiter);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void onlineLDCCorrelatorStreamStage(String[] propertyValues) throws Exception {
		try {
			PropertyGridHelper.typeInTextBox("Block Size *", propertyValues[0]);
			if (ValidationHelper.isTrue(propertyValues[1]))
				PropertyGridHelper.checkCheckBox("Generate File For Stitched CDR's With No Usage Partition");
			
			PropertyGridHelper.typeInTextBox("Maximum Thread *", propertyValues[2]);
			PropertyGridHelper.typeInTextBox("Memory Multiplier *", propertyValues[3]);
			PropertyGridHelper.typeInTextBox("Object Key *", propertyValues[4]);
			
			PropertyGridHelper.typeInDataDir("Online - LDC Directory", propertyValues[5], thirdLevelDelimiter);
			PropertyGridHelper.typeInTextBox("Online - Max Source Tasks Per Run", propertyValues[6]);
			
			if (!ValidationHelper.isTrue(propertyValues[7]))
				PropertyGridHelper.unCheckCheckBox("Online - Recoverable");
			PropertyGridHelper.selectInComboBox("Online - Source Stream Stage", propertyValues[8]);
			PropertyGridHelper.typeInTextBox("Online - Stitched Info Retention (seconds)", propertyValues[9]);
			
			if (ValidationHelper.isTrue(propertyValues[10]))
				PropertyGridHelper.checkCheckBox("Online - Unicode");
			PropertyGridHelper.selectInComboBox("Online LDC Correlator *", propertyValues[11]);
			PropertyGridHelper.selectInComboBox("Usage Server", propertyValues[12]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	
	private void parseStreamStage( String[] propertyValues, String[] parseOutputMap ) throws Exception {
		try {
			if (!ValidationHelper.isTrue(propertyValues[0]))
				PropertyGridHelper.unCheckCheckBox("Check For Stale Caches Every File");
			if (!ValidationHelper.isTrue(propertyValues[1]))
				PropertyGridHelper.unCheckCheckBox("Enable Retry On Failure(only for 'Ascii' parser)");
			if (ValidationHelper.isTrue(propertyValues[2]))
				PropertyGridHelper.checkCheckBox("Log Error With Rejected Record");
			if (!ValidationHelper.isTrue(propertyValues[3]))
				PropertyGridHelper.unCheckCheckBox("Log Record Type Distributions");
			
			PropertyGridHelper.typeInDataDir("Output Directory *", propertyValues[4], thirdLevelDelimiter);
			PropertyGridHelper.typeInDataDir("Parser Definition File *", propertyValues[5], thirdLevelDelimiter);
			
			if (ValidationHelper.isTrue(propertyValues[6]))
				PropertyGridHelper.checkCheckBox("Skip Records On Error");
	
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			addParseOutputMap(parseOutputMap);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	
	private void parseSummaryStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.typeInTextBox("Look Back (hours) *", propertyValues[0]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void preAggregationStreamStage( String[] propertyValues ) throws Exception {
		try {
			if (ValidationHelper.isTrue(propertyValues[0]))
				PropertyGridHelper.checkCheckBox("Delete Input File");
			PropertyGridHelper.selectInComboBox("Input Table *", propertyValues[1]);
			PropertyGridHelper.typeInTextBox("No Of Batch Threads *", propertyValues[2]);
			PropertyGridHelper.typeInTextBox("No Of Files Per Task", propertyValues[3]);
			PropertyGridHelper.typeInTextBox("No. of Files per Thread *", propertyValues[4]);
			
			PropertyGridHelper.typeInDataDir("Output Directory *", propertyValues[5], thirdLevelDelimiter);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void rebuildUnusableIndexesStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.typeInTextBox("Look Back Days To Rebuild Indexes(In Days) *", propertyValues[0]);
			PropertyGridHelper.typeInTextBox("Parallel Threads For Reference Table To Build Index *", propertyValues[1]);
			PropertyGridHelper.typeInTextBox("Parallel Threads To Build Index *", propertyValues[2]);
			PropertyGridHelper.typeInTextBox("Restricting number of LookBackDays To Rebuild Indexes(In ...", propertyValues[3]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void runScriptStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.typeInDataDir("Load Script Filename *", propertyValues[0], thirdLevelDelimiter);
			PropertyGridHelper.typeInTextBox("Parameters[delimited by ,]", propertyValues[1]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void sequenceOutputStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.typeInTextBox("Match Pattern", propertyValues[0]);
			PropertyGridHelper.selectInComboBox("Target Stream Stage *", propertyValues[1]);
			PropertyGridHelper.typeInTextBox("Tolerance (minutes)", propertyValues[2]);
			PropertyGridHelper.typeInTextBox("Touch Directory *", propertyValues[3]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void tagDuplicateStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.typeInTextBox("Duplicate Check - No of Oracle Parallelism Threads *", propertyValues[0]);
			PropertyGridHelper.typeInTextBox("Duplicate Check - No of Oracle Partition Threads *", propertyValues[1]);
			PropertyGridHelper.selectInComboBox("Duplicate Configuration *", propertyValues[2]);
			if (ValidationHelper.isTrue(propertyValues[3]))
				PropertyGridHelper.checkCheckBox("Enable Manual Lookback");
			
			PropertyGridHelper.typeInTextBox("Length (Applicable for Manual Lookback)", propertyValues[4]);
			PropertyGridHelper.typeInTextBox("Start (Applicable for Manual Lookback)", propertyValues[5]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void trunacteAndLoadStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.typeInTextBox("Date Format In File Name *", propertyValues[0]);
			if (ValidationHelper.isTrue(propertyValues[1]))
				PropertyGridHelper.checkCheckBox("Delete Input File");
			if (ValidationHelper.isTrue(propertyValues[2]))
				PropertyGridHelper.checkCheckBox("Log Data Load Output");
			
			PropertyGridHelper.typeInTextBox("Starting Position of Date String in File Name *", propertyValues[3]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void upsertTaskStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.typeInTextBox("Columns to Join [comma separated] *", propertyValues[0]);
			PropertyGridHelper.typeInTextBox("Degree Of Parallelism *", propertyValues[1]);
			PropertyGridHelper.typeInTextBox("Duplicate Check Aggregate Column", propertyValues[2]);
			PropertyGridHelper.selectInComboBox("Incremental Table Name *", propertyValues[3]);
			PropertyGridHelper.selectInComboBox("Main Table Name *", propertyValues[4]);
			PropertyGridHelper.typeInTextBox("Sequence Number/ Unique Column", propertyValues[5]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void xlsxPreprocessorStreamStage( String[] propertyValues ) throws Exception {
		try {
			if (ValidationHelper.isTrue(propertyValues[0]))
				PropertyGridHelper.checkCheckBox("Delete Input File");
			PropertyGridHelper.typeInDataDir("Output Directory *", propertyValues[1], thirdLevelDelimiter);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void zipCollectedFilesStreamStage( String[] propertyValues ) throws Exception {
		try {
			PropertyGridHelper.typeInTextBox("Compression Level *", propertyValues[0]);
			PropertyGridHelper.selectInComboBox("Compression Type *", propertyValues[1]);
			PropertyGridHelper.typeInTextBox("Length *", propertyValues[2]);
			PropertyGridHelper.typeInTextBox("Max Threads *", propertyValues[3]);
			PropertyGridHelper.typeInTextBox("Start *", propertyValues[4]);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}