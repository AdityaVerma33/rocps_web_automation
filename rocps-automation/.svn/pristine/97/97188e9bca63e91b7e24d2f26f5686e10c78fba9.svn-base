package com.subex.rocps.sprintTestCase.bklg232;
import java.util.ArrayList;
import java.util.Map;
import org.testng.annotations.Test;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;


public class RoamingTaxation extends PSAcceptanceTest {
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> referenceTableMap = null;
	protected Map<String, String> roamMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;

	protected String clientPartition;
	protected String taxType;
	protected String tax;
	protected String chargeType;
	protected String taxIndicator;

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */

	public RoamingTaxation(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		referenceTableMap = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(referenceTableMap);
		colSize = excelHolderObj.totalColumns();
	}


	public void roamingTaxtationCreation() throws Exception {
		try {
			NavigationHelper.navigateToReferenceTable("Roaming Taxation");

			for ( paramVal = 0;paramVal < colSize; paramVal++ ) 
			{ 
				roamMap = excelHolderObj.dataMap( paramVal);
				initializeVariables(roamMap);
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				newRoamingTaxation();
				GenericHelper.waitForLoadmask( searchScreenWaitSec );

			}


		} catch (Exception e) {

			FailureHelper.setErrorMessage(e);
			throw e;
		}

	}
	protected void newRoamingTaxation() throws Exception {
		GenericHelper.waitForLoadmask();
		createNew();
		assertEquals(NavigationHelper.getScreenTitle(), "New Roaming Taxation");
		GenericHelper.waitForLoadmask();
		ComboBoxHelper.select(or.getProperty("Roaming_taxation_Taxtype"),taxType);//
		ComboBoxHelper.select(or.getProperty("Roaming_taxation_tax"),tax);
		ComboBoxHelper.select(or.getProperty("Roaming_charge_type"),chargeType);
		ComboBoxHelper.select(or.getProperty("Roaming_taxation_tax_Indicator"),taxIndicator);
		saveRoamingTaxation();


	}


	protected void createNew() throws Exception {
		genericHelperObj.clickNewAction(clientPartition);
		GenericHelper.waitForLoadmask();


	}

	protected  void saveRoamingTaxation() throws Exception {
		ButtonHelper.click(or.getProperty("Roaming_taxation_save"));
		GenericHelper.waitForLoadmask();
		assertEquals(NavigationHelper.getScreenTitle(), "Reference Table Search");
	}

	/*
	 * This method is to initialize instance variable
	 */

	void initializeVariables(Map<String, String> Map) throws Exception {

		taxType=Map.get("TaxType");
		tax=Map.get("Tax");
		chargeType=Map.get("ChargeType");
		taxIndicator=Map.get("TaxIndicator");
		clientPartition=Map.get("Partition");

	}


}







