package com.subex.rocps.sprintTestCase.bklg285;
	import java.util.ArrayList;
	import java.util.Map;

	import org.testng.annotations.Test;

	import com.subex.automation.helpers.application.NavigationHelper;

	import com.subex.automation.helpers.component.ButtonHelper;
	import com.subex.automation.helpers.component.CheckBoxHelper;
	import com.subex.automation.helpers.component.ComboBoxHelper;
	import com.subex.automation.helpers.component.ElementHelper;

	import com.subex.automation.helpers.component.GenericHelper;
	import com.subex.automation.helpers.component.GridHelper;
	import com.subex.automation.helpers.component.SearchGridHelper;
	import com.subex.automation.helpers.component.TextBoxHelper;
	import com.subex.automation.helpers.componentHelpers.SearchHelper;
	import com.subex.automation.helpers.config.OR_Reader;
	import com.subex.automation.helpers.file.ExcelReader;
	import com.subex.automation.helpers.report.Log4jHelper;
	import com.subex.automation.helpers.util.FailureHelper;
	import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
	import com.subex.rocps.automation.helpers.application.partnerConfiguration.account.AccountActionImpl;
	import com.subex.rocps.automation.helpers.application.partnerConfiguration.account.AccountDetailImpl;
	import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
	import com.subex.rocps.automation.utils.ExcelHolder;
	import com.subex.rocps.sprintTestCase.bklg201.imsiManagementExecution;

	public class CallTypeGroup  extends PSAcceptanceTest {
		protected PSGenericHelper genericHelperObj = new PSGenericHelper();
		protected ExcelReader excelData = new ExcelReader();
		protected Map<String, ArrayList<String>> callConfExcelMap = null;
		protected Map<String, String> callMap = null;
		protected ExcelHolder excelHolderObj = null;
		String path;
		String workBookName;
		String sheetName;
		String testCaseName;
		int colSize;
		int paramVal;

		String clientPartition;

		static String dependantEntities;
		String groupName;
		String callTypeLevel1;
		String callTypeLevel2;
		String callTypeLevel3;
		public CallTypeGroup(String path, String workBookName, String sheetName, String testCaseName)
				throws Exception {
			this.path = path;
			this.workBookName = workBookName;
			this.sheetName = sheetName;
			this.testCaseName = testCaseName;
			excelData = new ExcelReader();
			callConfExcelMap = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
			excelHolderObj = new ExcelHolder(callConfExcelMap);
			colSize = excelHolderObj.totalColumns();
		}

		public void newCallTypeGroupConfiguration() throws Exception {
			navigate();
			boolean isCallTypeGroupNamePresent = isDataPresent(groupName,"Group Name");
			if (!isCallTypeGroupNamePresent) {
				genericHelperObj.clickNewAction(clientPartition);
				GenericHelper.waitForLoadmask();
				ElementHelper.waitForElement("PS_GroupName", 5);
				TextBoxHelper.type("PS_GroupName",groupName);
				ComboBoxHelper.select("PS_CallTypeLevel1",callTypeLevel1);
				ComboBoxHelper.select("PS_CallTypeLevel2",callTypeLevel2);
				TextBoxHelper.type("PS_CallTypeLevel3",callTypeLevel3);
				ButtonHelper.click("ok");
			} else {
				Log4jHelper.logInfo("Call Type Group Already Configured");
			}
		}

		public void editCallTypeGroupConfiguration() throws Exception {
			navigate();
			boolean isCallTypeGroupNamePresent = isDataPresent("Test","Group Name");
			if (isCallTypeGroupNamePresent) {
				NavigationHelper.navigateToAction("Common Tasks", "Edit");
				GenericHelper.waitForLoadmask();
				ComboBoxHelper.select("PS_CallTypeLevel1",callTypeLevel1);
				ComboBoxHelper.select("PS_CallTypeLevel2",callTypeLevel2);
				TextBoxHelper.type("PS_CallTypeLevel3",callTypeLevel3);
			} else {
				Log4jHelper.logInfo("");
			}
		}

		public boolean isDataPresent( String txtValue, String txtColumnHeader ) throws Exception
	    {
	        int row = GridHelper.getRowCount( "SearchGrid" );
	        boolean rowVal = false;
	        if ( row > 0 )
	        {
	            for ( int i = 0; i < row; i++ )
	            {
	                String cellValue = GridHelper.getCellValue( "SearchGrid", i + 1, txtColumnHeader );
	                GenericHelper.waitForLoadmask();
	                rowVal = cellValue.contentEquals(txtValue );
	                if ( rowVal )
	                    return true;
	            }
	            return rowVal;
	        }
	        return false;
	    }
		
		private void navigate() throws Exception {
			try {
				NavigationHelper.navigateToReferenceTable("Call Type Group");
				GenericHelper.waitForLoadmask();
				for (paramVal = 0; paramVal < colSize; paramVal++) {
					callMap = excelHolderObj.dataMap(paramVal);
					initializeInstanceVariables(callMap);
					
				}
			}catch(Exception e){
				
			}
		}
	

		public void initializeInstanceVariables(Map<String, String> map) throws Exception {
			groupName = ExcelHolder.getKey(map, "GroupName");
			callTypeLevel1 = ExcelHolder.getKey(map, "CallTypeLevel1");
			callTypeLevel2 = ExcelHolder.getKey(map, "CallTypeLevel2");
			callTypeLevel3 = ExcelHolder.getKey(map, "CallTypeLevel3");
			clientPartition = ExcelHolder.getKey(map, "Partition");
		}

		public void clickNewAction(String clientPartition) throws Exception {
			genericHelperObj.clickNewAction(clientPartition);
			GenericHelper.waitForLoadmask();
		}

		

	}
