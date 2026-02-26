package com.subex.automation.testcases.systemtesting.tariff;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testPrerequisite2 extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "Tariff_TestData.xlsx";
	final String sheetName = "Tariff";
	
	public testPrerequisite2() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=16, description="Create Element", dependsOnGroups = { "prerequisite1" }, groups = { "prerequisite2" })
	public void createElement()throws Exception
	{
		try {
			ElementCreateHelper elements = new ElementCreateHelper();
			elements.createElement(path, fileName, sheetName, "Elements", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=17, description="Create Bands", dependsOnMethods = { "createElement" }, groups = { "prerequisite2" })
	public void createBand()throws Exception
	{
		try {
			BandHelper bands = new BandHelper();
			bands.createBand(path, fileName, sheetName, "Bands", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=18, description="Create Tariff Class", dependsOnMethods = { "createBand" }, groups = { "prerequisite2" })
	public void createTariffClass()throws Exception
	{
		try {
			TariffClassHelper tariffClass = new TariffClassHelper();
			tariffClass.createTariffClass(path, fileName, sheetName, "TariffClass", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=19, description="Create Tariff", dependsOnMethods = { "createTariffClass" }, groups = { "prerequisite2" })
	public void createTariff()throws Exception
	{
		try {
			TariffHelper tariff = new TariffHelper();
			tariff.createTariff(path, fileName, sheetName, "Tariff", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=20, description="Create Child Tariff", dependsOnMethods = { "createTariff" }, groups = { "prerequisite2" })
	public void createChildTariff()throws Exception
	{
		try {
			TariffHelper tariff = new TariffHelper();
			tariff.createChildTariff(path, fileName, sheetName, "ChildTariff", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=21, description="Create Fast Entry", dependsOnMethods = { "createChildTariff" }, groups = { "prerequisite2" })
	public void createFastEntry()throws Exception
	{
		try {
			TariffHelper tariff = new TariffHelper();
			tariff.createFastEntry(path, fileName, sheetName, "FastEntry", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}