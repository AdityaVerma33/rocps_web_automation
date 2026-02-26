package com.subex.rocps.sprintTestCase.bklg269;


	import java.util.Map;

	import org.testng.Assert;

	import com.subex.automation.helpers.application.NavigationHelper;
	import com.subex.automation.helpers.component.ButtonHelper;
	import com.subex.automation.helpers.component.CheckBoxHelper;
	import com.subex.automation.helpers.component.ComboBoxHelper;
	import com.subex.automation.helpers.component.ElementHelper;
	import com.subex.automation.helpers.component.EntityComboHelper;
	import com.subex.automation.helpers.component.GenericHelper;
	import com.subex.automation.helpers.component.GridHelper;
	import com.subex.automation.helpers.component.MouseHelper;
	import com.subex.automation.helpers.component.TextBoxHelper;
	import com.subex.automation.helpers.data.ValidationHelper;
	import com.subex.rocps.automation.helpers.application.customexception.ScreenTitleException;
	import com.subex.rocps.automation.helpers.application.customexception.StringLengthException;
	import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
	import com.subex.rocps.automation.helpers.application.matchandrate.mnrInterfaces.MatchRuleService;
	import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
	import com.subex.rocps.automation.utils.ExcelHolder;
	import com.subex.rocps.automation.utils.PSStringUtils;

	public class EventMatchRuleService extends PSAcceptanceTest implements MatchRuleService {

		protected String services;
		protected String legCode;
		protected Map<String, String> map;
		protected String[] svcArr;
		protected String[] legCodeArr;
		protected PSStringUtils dataUtilObj = new PSStringUtils();

		@Override
		public void serviceConfiguration(Map<String, String> map) throws Exception {
			this.map = map;
			addService();

		}

		/*
		 * Method : spliting variable to configure more than one services in event
		 * match rule
		 */
		protected void addService() throws Exception {

			services = ExcelHolder.getKey(map, "Services");
			legCode = ExcelHolder.getKey(map, "LegCode");
			svcArr = dataUtilObj.stringSplitFirstLevel(services);
			legCodeArr = dataUtilObj.stringSplitFirstLevel(legCode);

			for (int index = 0; index < svcArr.length; index++) {
				String screenTitle = null;
				if (svcArr[index].equalsIgnoreCase("Service")) {
					matchRuleServiceConfig(index);
					screenTitle = NavigationHelper.getScreenTitle();
					if (screenTitle == null)
						throw new ScreenTitleException("screen title not found");
				} else if (svcArr[index].equalsIgnoreCase("RevenueShareService")) {
					matchRuleRevenueServiceConfig(index);
					screenTitle = NavigationHelper.getScreenTitle();
					if (screenTitle == null)
						throw new ScreenTitleException("screen title not found");
				}

			}

		}

		/*
		 * Method: for configuring event match rule revenue share service
		 */
		protected void matchRuleServiceConfig(int index) throws Exception {

			EventMatchRuleServiceDetails emrSvcObj = new EventMatchRuleServiceDetails(map);
			emrSvcObj.selectService("PSDetail_emrAddServiceMenuXpath", "PSDetail_emrServiceId", legCodeArr[index]);
			emrSvcObj.basicDetails(index);
			emrSvcObj.matchingInformation(index);
			emrSvcObj.ratingDetails(index);
			emrSvcObj.tariffConfigurationPerService(index);
			ButtonHelper.click("PSPopUp_emrSvcPopupSaveId");
			GenericHelper.waitForLoadmask();
		}

		/*
		 * Method: for configuring event match rule service
		 */
		protected void matchRuleRevenueServiceConfig(int index) throws Exception {

			EventMatchRuleServiceDetails emrSvcObj = new EventMatchRuleServiceDetails(map);
			emrSvcObj.selectService("PSDetail_emrAddServiceMenuXpath", "PSDetail_emrRevServiceId", legCodeArr[index]);
			emrSvcObj.basicDetails(index);
			emrSvcObj.ratingDetails(index);
			emrSvcObj.tariffConfigurationPerService(index);
			emrSvcObj.revenueRating(index);
			ButtonHelper.click("PSPopUp_emrSvcPopupSaveId");
			GenericHelper.waitForLoadmask();

		}
		
		protected void editService(Map<String, String> map) throws Exception
		{
			services = ExcelHolder.getKey(map, "Services");
			legCode = ExcelHolder.getKey(map, "LegCode");
			svcArr = dataUtilObj.stringSplitFirstLevel(services);
			legCodeArr = dataUtilObj.stringSplitFirstLevel(legCode);
			String serviceName = ExcelHolder.getKey(map, "ServiceName");
			String[] svcNameArr = dataUtilObj.stringSplitFirstLevel(serviceName);
			
			
			EventMatchRuleServiceDetails emrSvcObj = new EventMatchRuleServiceDetails(map);
			for (int index = 0; index < svcNameArr.length; index++) {
				int row = GridHelper.getRowNumber(  "serviceGrid", svcNameArr[index], "Name"  );
				if (row == 0)
				{
					this.map = map;
					matchRuleServiceConfig( index );
				}
				else if (row != 0) {
				
			GridHelper.clickRow( "serviceGrid", 1, "Name" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			MouseHelper.rightClick( "PSDetail_emrAddServiceMenuXpath" );
		
			MouseHelper.click("serviceGridContextMenu.EditService");
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Service" );
			
			emrSvcObj.editBasicDetails( index );
			ButtonHelper.click("PSPopUp_emrSvcPopupSaveId");
			GenericHelper.waitForLoadmask();
				}
			}
		}

	}



