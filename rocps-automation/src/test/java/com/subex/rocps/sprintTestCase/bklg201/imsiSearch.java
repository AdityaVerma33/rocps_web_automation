package com.subex.rocps.sprintTestCase.bklg201;
import java.util.ArrayList;
import java.util.List;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class imsiSearch extends PSAcceptanceTest {
	public void imsiSearch() throws Exception {
		try {
			NavigationHelper.navigateToScreen("IMSI Management");
			GenericHelper.waitForLoadmask();
			
			PSGenericHelper psGenericHelperObj = new PSGenericHelper();

			String[] imsiSearchColumnsSplit;

			imsiSearchColumnsSplit = imsiManagementExecution.imsiSearchColumns.split("\\|");

			for (int i = 0; i < imsiSearchColumnsSplit.length; i++)
				System.out.println(imsiSearchColumnsSplit[i]);

			List<String> tapErrorcolumnValues = psGenericHelperObj.getGridColumns("grid_column_header_searchGrid_");
			GenericHelper.waitForLoadmask();
			assertEquals(imsiSearchColumnsSplit.length, tapErrorcolumnValues.size() - 1);
			for (int i = 0; i < imsiSearchColumnsSplit.length; i++) {
				System.out.println(imsiSearchColumnsSplit[i]);
				assertTrue(tapErrorcolumnValues.contains(imsiSearchColumnsSplit[i]),
						"Value is not present in imsiSearchColumnValues" + imsiSearchColumnsSplit[i]);
			}

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	}



