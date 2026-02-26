package com.subex.automation.testcases.systemtesting.etl;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testRun extends ROCAcceptanceTest {
	
	private static String path = null;
	private static String dataDir = null;
	final String fileName = "ETLFlow_TestData.xlsx";
	final String sheetName = "ETLFlow";
	final String asciiFolderName = "ASCII";
	final String asn1FolderName = "ASN1";
	final String asciiDataLocation = "\\src\\main\\resources\\Data\\ASCII";
	final String asn1DataLocation = "\\src\\main\\resources\\Data\\ASN1";
	final String asciiDataFileName = "Data_Load_Sample1.dat";
	final String asn1DataFileName = "CDR_03642_vmcpljl11.jawwal.ps_20200409173813_111.cdr.dat";
	
	public testRun() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Start Services", dependsOnGroups = { "prerequisite" }, groups = { "run" })
	public void startSC_TC() throws Exception {
		try {
			//Create output&Input directory for parse
			dataDir = configProp.getDataDirPath();
			FileHelper.createDir(applicationOS, dataDir + "/Input");
			FileHelper.createDir(applicationOS, dataDir + "/Collected Files");
			FileHelper.createDir(applicationOS, dataDir + "/ParseOutput");
			
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/" + asciiFolderName, true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Collected Files/" + asciiFolderName, true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/ParseOutput/" + asciiFolderName, true);
			
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/" + asn1FolderName, true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Collected Files/" + asn1FolderName, true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/ParseOutput/" + asn1FolderName, true);
			
			// Start Controllers
			ControllerHelper controller = new ControllerHelper();
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
			
			FileHelper.copyFile(applicationOS, automationPath + asciiDataLocation, dataDir + "\\Input\\" + asciiFolderName, asciiDataFileName, asciiDataFileName, true);
			FileHelper.copyFile(applicationOS, automationPath + asn1DataLocation, dataDir + "\\Input\\" + asn1FolderName, asn1DataFileName, asn1DataFileName, true);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Set Task Controller Capability for all the 3 stages", dependsOnMethods = { "startSC_TC" }, groups = { "run" })
	public void setTaskControllerCapability() throws Exception {
		try {
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.setTaskControllerCapability(path, fileName, sheetName, "TCCapability", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Pause Parse stage in Task Search", dependsOnMethods = { "setTaskControllerCapability" }, groups = { "run" })
	public void verifyTaskStatus() throws Exception {
		try {
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskStatus(path, fileName, sheetName, "ETLTask", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Stop Services", dependsOnMethods = { "verifyTaskStatus" }, groups = { "run" })
	public void stopSC_TC() throws Exception {
		try {
			ControllerHelper controller = new ControllerHelper();
			controller.stopTaskController("Task Controller");
			controller.stopStreamController();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}