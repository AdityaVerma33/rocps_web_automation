package com.subex.automation.testcases.regressiontesting.masking;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.MaskingConfigurationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class testValidations extends testMasking {
	
	final String sheetName = "TableUniqueness";
	
	public testValidations() throws Exception {
		super();
	}
	
	@Test(priority=5, description="Table Definition uniqueness validation", groups = { "GDPRValidations" })
	public void testTableUniqueness() throws Exception
	{
		try {
			// Validate that a privileged user is able to edit and save configurations on masked fields without modifying the masked fields
			MaskingConfigurationHelper masking = new MaskingConfigurationHelper();
			masking.createMaskingConfiguration(path, fileName, sheetName, "Masking", 1);
			
			NavigationHelper.navigateToScreen("Masking Configuration", "Masking Config Search");
			NavigationHelper.navigateToNew("Common", "Masking_Name");
			
			masking.updateMaskingConfiguration(path, fileName, sheetName, "Masking", 2);
			
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			Thread.sleep(1000);
			assertTrue(EntityComboHelper.hasValidation("Masking_TableDefinition"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Masking field validations", groups = { "GDPRValidations" })
	public void testMaskingValidation() throws Exception
	{
		try {
			// Validate that there is a validation message when improper masking configurations are done
			String name = "Currency Masking";
			String tableDfnName = "currency";
			String[] columnName = {"cur_id", "cur_culture", "cur_delete_fl"};
			String[][] masking = {{"(1,2)", "(0,0)"},
								{"(1,5k)", "(5,-5)", "(1.5,5)", "(1,2,3)", "1,5", "(1,2)"},
								{"(0,0)"}};
			
			NavigationHelper.navigateToScreen("Masking Configuration", "Masking Config Search");
			NavigationHelper.navigateToNew("Common", "Masking_Name");
			TextBoxHelper.type("Masking_Name", name);
			EntityComboHelper.selectUsingSearchTextBox("Masking_TableDefinition", "Table Definition Search", "TableDfn_Name", tableDfnName, "Name");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			Thread.sleep(1000);
			
			for (int i = 0; i < columnName.length; i++) {
				int rowNum = GridHelper.getRowNumber("Masking_Columns_Grid", columnName[i], "Column Name");
				
				if (rowNum > 0) {
					for(int j = 0; j < masking[i].length-1; j++)
					{
						GridHelper.updateGridTextBox("Masking_Columns_Grid", "Masking_Columns_Masking", rowNum, "Masking", "Type", masking[i][j]);
						assertTrue(GridHelper.isErrorIconPresent("Masking_Columns_Grid"));
					}
					
					if (!columnName[i].equals("cur_delete_fl")) {
						GridHelper.updateGridTextBox("Masking_Columns_Grid", "Masking_Columns_Masking", rowNum, "Masking", "Type", masking[i][masking[i].length-1]);
						assertFalse(GridHelper.isErrorIconPresent("Masking_Columns_Grid"));
					}
				}
				else {
					FailureHelper.failTest("Column '" + columnName[i] + "' is not found for Table Definition '" + tableDfnName + "'.");
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
}