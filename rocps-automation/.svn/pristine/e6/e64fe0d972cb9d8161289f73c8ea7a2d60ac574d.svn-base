package com.subex.rocps.sprintTestCase.bklg285;
	import com.subex.automation.helpers.util.FailureHelper;
	import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
	import com.subex.rocps.sprintTestCase.bklg113.NRTRDERoamingConfiguration;

	public class CallTypeGroupExecution extends PSAcceptanceTest {
		
		String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
		String workBookName = "TestData.xlsx";
		String sheetName = "BKLG285_CallTypeGroup_Conf";
		@org.testng.annotations.Test(priority = 16)
		public void newCallTypeGroupConfiguration() throws Exception {

			try {
				String testCaseName ="NewCallTypeGroupCreation";
				System.out.println(path);
				CallTypeGroup callobj = new CallTypeGroup(path, workBookName, sheetName, testCaseName);
				callobj.newCallTypeGroupConfiguration();

			} catch (Exception e) {
				FailureHelper.reportFailure(e);
				throw e;
			}

		}
		@org.testng.annotations.Test(priority = 17)
		public void editCallTypeGroupConfiguration() throws Exception {
			try {
				String testCaseName ="EditCallTypeGroupCreation";
				CallTypeGroup callobj = new CallTypeGroup(path, workBookName, sheetName, testCaseName);
				callobj.editCallTypeGroupConfiguration();
				;
			} catch (Exception e) {
				FailureHelper.reportFailure(e);
				throw e;
			}

		}

	}



