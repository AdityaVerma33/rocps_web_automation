package com.subex.rocps.automation.helpers.application.matchandrate;

import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.RadioHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.customexception.ScreenTitleException;
import com.subex.rocps.automation.helpers.application.customexception.StringLengthException;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class EventMatchRuleServiceDetails extends PSAcceptanceTest {

	protected String services;
	protected String legCode;
	protected String serviceName;
	protected String serviceFromDate;
	protected String serviceToDate;
	protected String billProfile;
	protected String salesTax;
	protected String trafficType;
	protected String chargeType;
	protected String billingGroupCode;
	protected String ratingFrom;
	protected String ratingTo;
	protected String defaultFromDigits;
	protected String defaultToDigits;
	protected String tariffType;
	protected String tariffs;
	protected String tariffsDttms;
	protected String revenueFieldfl;
	protected String revenueCurrency;
	protected String revenueAmount;
	protected String revenueFieldColumn;
	protected String revenueCurrencyColumn;
	protected String billProfileIdentifier;
	protected String eventType;
	protected String tadigCode;
	
	protected Map<String, String> map = null;
	protected PSStringUtils dataUtilObj = new PSStringUtils();
	protected PSDataComponentHelper dataCompObj = new PSDataComponentHelper();

	protected String[] svcArr;
	protected String[] svcNameArr;
	protected String[] svcFromDateArr;
	protected String[] svcToDateArr;
	protected String[] legCodeArr;
	protected String[] billProfileArr;
	protected String[] salesTaxArr;
	protected String[] trafficTypeArr;
	protected String[] chargeTypeArr;
	protected String[] billingGroupCodeArr;
	protected String[] ratingFromArr;
	protected String[] ratingToArr;
	protected String[] defaultFromDigitsArr;
	protected String[] defaultToDigitsArr;
	protected String[] tariffTypeArr;
	protected String[] tariffsArr;
	protected String[] tariffsDttmArr;
	protected String[] revenueFieldflArr;
	protected String[] revenueCurrencyArr;
	protected String[] revenueAmountArr;
	protected String[] revenueFieldColumnArr;
	protected String[] revenueCurrencyColumnArr;
	protected String[] billProfileIdentifierArr;
	protected String[] eventTypeArr;
	protected String[] tadigCodeArr;
	

	public EventMatchRuleServiceDetails(Map<String, String> map) throws Exception {

		this.map = map;
		intialiseVariables(this.map);
		intialiseArrays();

	}

	/*
	 * Method: for initialising variables
	 */
	protected void intialiseVariables(Map<String, String> map) throws Exception {
		serviceName = ExcelHolder.getKey(map, "ServiceName");
		serviceFromDate = ExcelHolder.getKey(map, "ServiceFromDate");
		serviceToDate = ExcelHolder.getKey(map, "ServiceToDate");
		legCode = ExcelHolder.getKey(map, "LegCode");
		billProfile = ExcelHolder.getKey(map, "BillProfile");
		salesTax = ExcelHolder.getKey(map, "SalesTax");
		trafficType = ExcelHolder.getKey(map, "TrafficType");
		chargeType = ExcelHolder.getKey(map, "ChargeType");
		billingGroupCode = ExcelHolder.getKey(map, "BillingGroupCode");
		ratingFrom = ExcelHolder.getKey(map, "RatingFrom");
		ratingTo = ExcelHolder.getKey(map, "RatingTo");
		defaultFromDigits = ExcelHolder.getKey(map, "DefaultFromDigits");
		defaultToDigits = ExcelHolder.getKey(map, "DefaultToDigits");
		tariffType = ExcelHolder.getKey(map, "TariffType");
		tariffs = ExcelHolder.getKey(map, "Tariffs");
		tariffsDttms = ExcelHolder.getKey(map, "TariffsDttm");
		revenueFieldfl = ExcelHolder.getKey(map, "RevenueField");
		revenueAmount = ExcelHolder.getKey(map, "RevenueAmount");
		revenueCurrency = ExcelHolder.getKey(map, "RevenueCurrency");
		revenueFieldColumn = ExcelHolder.getKey(map, "RevenueFieldColumn");
		revenueCurrencyColumn = ExcelHolder.getKey(map, "RevenueCurrencyColumn");
		billProfileIdentifier = ExcelHolder.getKey(map, "BillProfileIdentifier");
		eventType = ExcelHolder.getKey(map, "EventType");
		tadigCode = ExcelHolder.getKey(map, "TadigCode");
		
		
	}

	protected void intialiseArrays() throws Exception {

		svcNameArr = dataUtilObj.stringSplitFirstLevel(serviceName);
		svcFromDateArr = dataUtilObj.stringSplitFirstLevel(serviceFromDate);
		svcToDateArr = dataUtilObj.stringSplitFirstLevel(serviceToDate);
		legCodeArr = dataUtilObj.stringSplitFirstLevel(legCode);
		billProfileArr = dataUtilObj.stringSplitFirstLevel(billProfile);
		salesTaxArr = dataUtilObj.stringSplitFirstLevel(salesTax);
		trafficTypeArr = dataUtilObj.stringSplitFirstLevel(trafficType);
		chargeTypeArr = dataUtilObj.stringSplitFirstLevel(chargeType);
		billingGroupCodeArr = dataUtilObj.stringSplitFirstLevel(billingGroupCode);
		ratingFromArr = dataUtilObj.stringSplitFirstLevel(ratingFrom);
		ratingToArr = dataUtilObj.stringSplitFirstLevel(ratingTo);
		defaultFromDigitsArr = dataUtilObj.stringSplitFirstLevel(defaultFromDigits);
		defaultToDigitsArr = dataUtilObj.stringSplitFirstLevel(defaultToDigits);
		tariffTypeArr = dataUtilObj.stringSplitFirstLevel(tariffType);
		tariffsArr = dataUtilObj.stringSplitFirstLevel(tariffs);
		tariffsDttmArr = dataUtilObj.stringSplitFirstLevel(tariffsDttms);
		revenueFieldflArr = dataUtilObj.stringSplitFirstLevel(revenueFieldfl);
		revenueAmountArr = dataUtilObj.stringSplitFirstLevel(revenueAmount);
		revenueCurrencyArr = dataUtilObj.stringSplitFirstLevel(revenueCurrency);
		revenueFieldColumnArr = dataUtilObj.stringSplitFirstLevel(revenueFieldColumn);
		revenueCurrencyColumnArr = dataUtilObj.stringSplitFirstLevel(revenueCurrencyColumn);
		billProfileIdentifierArr = dataUtilObj.stringSplitFirstLevel( billProfileIdentifier );
		eventTypeArr = dataUtilObj.stringSplitFirstLevel( eventType );
		tadigCodeArr = dataUtilObj.stringSplitFirstLevel( tadigCode );
		
		
	}

	public void tariffConfigurationPerService(int index) throws Exception {

		String tffsPerService = null;
		String tffsPerServiceDttms = null;
		String childtffsPerService = null;

		if (!tariffType.isEmpty() && !tariffs.isEmpty()) {
			tffsPerService = tariffsArr[index];
			tffsPerServiceDttms = tariffsDttmArr[index];
			String tffsMetricTypesPerService = tariffTypeArr[index];
			
			
			tariffsPerTariffTypeConfiguration(tffsMetricTypesPerService, tffsPerService, tffsPerServiceDttms);
		} else if (!tariffs.isEmpty()) {
			tffsPerService = tariffsArr[index];
			tffsPerServiceDttms = tariffsDttmArr[index];
			
			selectTariffs(tffsPerService, tffsPerServiceDttms);
		}

	}

	/*
	 * Method: for configuring tariff type
	 */
	protected void tariffsPerTariffTypeConfiguration(String tffsMetricTypesPerService, String tffsPerService,
			String tffsPerServiceDttms) throws Exception {

		String[] tariffTypesPerServiceArr = dataUtilObj.stringSplitSecondLevel(tffsMetricTypesPerService);
		String[] tffsPerServiceArr = dataUtilObj.stringSplitSecondLevel(tffsPerService);
		String[] tffsPerServiceDttmsArr = dataUtilObj.stringSplitSecondLevel(tffsPerServiceDttms);
		
		

		for (int index = 0; index < tariffTypesPerServiceArr.length; index++) {
			if (!tariffTypesPerServiceArr[index].isEmpty())
				ComboBoxHelper.select("PSDetail_emrSvcTariffTypeComboId", tariffTypesPerServiceArr[index]);
			selectTariffs(tffsPerServiceArr[index], tffsPerServiceDttmsArr[index]);
		}

	}

	/*
	 * Method : Selecting One or More tariffs in timeline
	 * 
	 * @Param : tffsPerServiceArr : array passed using delimiter
	 * 
	 * @Param : tffsDttmTimeLineArr : array passed using delimiter
	 */
	public void selectTariffs(String tffsPerServiceArr, String tffsDttmTimeLineArr) throws Exception {
		String[] tariffsPerTariffTypeArr = dataUtilObj.stringSplitThirdLevel(tffsPerServiceArr);
		String[] tariffsDttmPerTariffTypeArr = dataUtilObj.stringSplitThirdLevel(tffsDttmTimeLineArr);
		
		

		if (tariffsPerTariffTypeArr.length != tariffsDttmPerTariffTypeArr.length)
			throw new StringLengthException(" The length of tariff and tariff timeline is not matching");

		for (int indexVal = 0; indexVal < tariffsPerTariffTypeArr.length; indexVal++) {
			PSGenericHelper genHelperObj = new PSGenericHelper();
			genHelperObj.timeLineNew("Tariff");

			try {
				ElementHelper.isElementPresent(" //div[text()='Service  Tariff']");
			} catch (Exception e) {
				throw new ScreenTitleException("Screen title is not found");
			}

			String serviceTariffDateLocator = GenericHelper.getORProperty("TextBox_DatePicker").replace("idOrName",
					or.getProperty("PSPopUp_emrSvcTffDttmTxtId"));
			TextBoxHelper.type(serviceTariffDateLocator, tariffsDttmPerTariffTypeArr[indexVal]);
			String tffScrnTitle = "Tariff Search";
			
			tariffSelection(tffScrnTitle, tariffsPerTariffTypeArr[indexVal]);
			/*EntityComboHelper.selectUsingSearchTextBox("PSPopUp_emrSvcTffEntSrchId", tffScrnTitle,
					"PSPopUp_emrSvcTffNameTxtId", tariffsPerTariffTypeArr[indexVal], "Tariff Name"); */
		
			ButtonHelper.click("PSPopUp_emrSvcTffPopupSaveId");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			//assertEquals( NavigationHelper.getScreenTitle(), "Service" );

		}

	}
	
	public void tariffSelection(String tffScrnTitle, String tariffsPerTariffTypeArr) throws Exception
	{
		EntityComboHelper.clickEntityIcon("PSPopUp_emrSvcTffEntSrchId");
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		//assertTrue(LabelHelper.isTitlePresent(tffScrnTitle), "Entity Search '" + tffScrnTitle + "' did not appear.");
		ButtonHelper.click("ClearButton");
		GenericHelper.waitForLoadmask(searchScreenWaitSec);	
		//PSSearchGridHelper.gridFilterSearchWithTextBox( "PSPopUp_emrSvcTffNameTxtId", tariffsPerTariffTypeArr, "Tariff Name" );
		
		SearchGridHelper.searchWithTextBox("PSPopUp_emrSvcTffNameTxtId", tariffsPerTariffTypeArr, "Tariff Name");
		//GridHelper.clickRow("popupWindow", "SearchGrid", tariffsPerTariffTypeArr, "Tariff Name");
	
		NavigationHelper.navigateToAction("Expand/Collapse");
		if (NavigationHelper.isActionPresent("Expand All"))
			NavigationHelper.navigateToAction("Expand All");
		int row = GridHelper.getRowNumber("SearchGrid", tariffsPerTariffTypeArr, "Tariff Name");
		GridHelper.clickRow("SearchGrid", row, "Tariff Name");
		ButtonHelper.click("OKButton");
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		
	}

	/*
	 * Method: for basic details configuration in service
	 */
	public void basicDetails(int index) throws Exception {
		String legCodeTextValue = TextBoxHelper.getValue("PSDetail_emrServiceLegCodeTxtId");
		Assert.assertEquals(legCodeTextValue, legCodeArr[index], "leg codes are not matching in service");

		TextBoxHelper.type("PSDetail_emrServiceNameTxtId", svcNameArr[index]);
		String svcFromDateXpath = GenericHelper.getORProperty("TextBox_DatePicker").replace("idOrName",
				or.getProperty("PSDetail_emrServiceFromDateId"));
		String svcToDateXpath = GenericHelper.getORProperty("TextBox_DatePicker").replace("idOrName",
				or.getProperty("PSDetail_emrServiceToDateId"));
		TextBoxHelper.type(svcFromDateXpath, svcFromDateArr[index]);
		if (!serviceToDate.isEmpty() && svcToDateArr[index] != null)
			TextBoxHelper.type(svcToDateXpath, svcToDateArr[index]);
		
		/*String bipColHeaderName = "Bill Profile Name";
		EntityComboHelper.selectUsingGridFilterTextBox("PSDetail_emrServiceBipEntSrchId", "Bill Profile Search",
				"PSPopUp_billProfileGridColTxtId", billProfileArr[index], bipColHeaderName);*/
		ComboBoxHelper.select("PSDetail_emrServiceSalesTaxComboId", salesTaxArr[index]);
		ComboBoxHelper.select("PSDetail_emrServiceTrafficTypeComboId", trafficTypeArr[index]);
		ComboBoxHelper.select("PSDetail_emrServiceChargeTypeComboId", chargeTypeArr[index]);
		ComboBoxHelper.select("PSDetail_emrServiceBillingGroupComboId", billingGroupCodeArr[index]);
		matchingInformation(index);

	}

	public void matchingInformation(int index) throws Exception
	{
		if(!billProfile.isEmpty() && ValidationHelper.isNotEmpty( billProfileArr[index] ))
		{
			String bipColHeaderName = "Bill Profile Name";
			PSEntityComboHelper.selectUsingGridFilterTextBox("PSDetail_emrServiceBipEntSrchId", "Bill Profile Search",
					"PSPopUp_billProfileGridColTxtId", billProfileArr[index], bipColHeaderName);
		}
		else if (!billProfileIdentifier.isEmpty()&& ValidationHelper.isNotEmpty( billProfileIdentifierArr[index] ))
		{
			RadioHelper.click( "psvcBillProfileIdentificationCmpFl_InputElement" );
			RadioHelper.click( "psvcBillProfileIdentificationCmpFl_InputElement" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			ComboBoxHelper.select( "component_gwt_uid_", billProfileIdentifierArr[index] );
			assertEquals( TextBoxHelper.getValue( "eventType"), eventTypeArr[index] );
			GridHelper.updateGridComboBox( "matchingTypePanel", "tclEditor_gwt_uid_", 1, "Value", tadigCodeArr[index] );
		}
			
	}
	/*
	 * Method : configuring rating details
	 */
	public void ratingDetails(int index) throws Exception {

		dataCompObj.comboBoxSelectOptional("PSDetail_emrServiceRatingFromComboId", ratingFrom, ratingFromArr, index);
		dataCompObj.comboBoxSelectOptional("PSDetail_emrServiceRatingToComboId", ratingTo, ratingToArr, index);

		if (!defaultFromDigits.isEmpty() && !defaultFromDigitsArr[index].isEmpty()) {
			CheckBoxHelper.check("PSDetail_emrSvcDefaultFromDigitsCheckBoxId");
			TextBoxHelper.type("PSDetail_emrSvcRatingFromDigitsTxtId", defaultFromDigitsArr[index]);
		}

		if (!defaultToDigits.isEmpty() && !defaultToDigitsArr[index].isEmpty()) {
			CheckBoxHelper.check("PSDetail_emrSvcDefaultToDigitsCheckBoxId");
			TextBoxHelper.type("PSDetail_emrSvcRatingToDigitsTxtId", defaultToDigitsArr[index]);
		}
	}

	/*
	 * Method: to add service in service grid
	 */
	public void selectService(String rightClickXpath, String serviceId, String serviceDirection) throws Exception {
		GenericHelper.waitForLoadmask();
		MouseHelper.rightClick(rightClickXpath);
		MouseHelper.mouseOver(serviceId);
		String serviceXpath = GenericHelper.getORProperty("PSDetail_emrServiceXpath").replace("direction",
				serviceDirection);
		MouseHelper.click(serviceXpath);
	}

	/*
	 * Method: for configuring revenue share rating parameters
	 */
	public void revenueRating(int index) throws Exception {
		if (ValidationHelper.isFalse(revenueFieldflArr[index])) {
			CheckBoxHelper.uncheck("PSDetail_emrSvcRevenueFieldCheckBoxId");
			TextBoxHelper.type("PSDetail_emrSvcRevAmtTextId", revenueAmountArr[index]);
			ComboBoxHelper.select("PSDetail_emrSvcCurrComboId", revenueCurrencyArr[index]);
		}

		dataCompObj.comboBoxSelectOptional("PSDetail_emrSvcRevFieldColComboId", revenueFieldColumn,
				revenueFieldColumnArr, index);
		dataCompObj.comboBoxSelectOptional("PSDetail_emrSvcRevCurrColComboId", revenueCurrencyColumn,
				revenueCurrencyColumnArr, index);
	}

	protected void comboBoxSelectOptional(String name, String arrayIndexVal, String idOrXpath) throws Exception {
		if (!name.isEmpty() && !arrayIndexVal.isEmpty())
			ComboBoxHelper.select(idOrXpath, arrayIndexVal);
	}
	
	protected void editBasicDetails(int index) throws Exception
	{
		String legCodeTextValue = TextBoxHelper.getValue("PSDetail_emrServiceLegCodeTxtId");
		Assert.assertEquals(legCodeTextValue, legCodeArr[index], "leg codes are not matching in service");

		
		assertEquals( TextBoxHelper.getValue( "PSDetail_emrServiceNameTxtId" ), svcNameArr[index] );
		String svcFromDateXpath = GenericHelper.getORProperty("TextBox_DatePicker").replace("idOrName",
				or.getProperty("PSDetail_emrServiceFromDateId"));
		String svcToDateXpath = GenericHelper.getORProperty("TextBox_DatePicker").replace("idOrName",
				or.getProperty("PSDetail_emrServiceToDateId"));
		if(ValidationHelper.isNotEmpty( serviceFromDate ) && ValidationHelper.isNotEmpty( svcFromDateArr[index] ))
			TextBoxHelper.type(svcFromDateXpath, svcFromDateArr[index]);
		if (!serviceToDate.isEmpty() && svcToDateArr[index] != null)
			TextBoxHelper.type(svcToDateXpath, svcToDateArr[index]);
		
		if(ValidationHelper.isNotEmpty( salesTax ) && ValidationHelper.isNotEmpty( salesTaxArr[index] ))
			ComboBoxHelper.select("PSDetail_emrServiceSalesTaxComboId", salesTaxArr[index]);
		if(ValidationHelper.isNotEmpty( trafficType ) && ValidationHelper.isNotEmpty( trafficTypeArr[index] ))
			ComboBoxHelper.select("PSDetail_emrServiceTrafficTypeComboId", trafficTypeArr[index]);
		if(ValidationHelper.isNotEmpty( chargeType ) && ValidationHelper.isNotEmpty( chargeTypeArr[index] ))
			ComboBoxHelper.select("PSDetail_emrServiceChargeTypeComboId", chargeTypeArr[index]);
		if(ValidationHelper.isNotEmpty( billingGroupCode ) && ValidationHelper.isNotEmpty( billingGroupCodeArr[index] ))
			ComboBoxHelper.select("PSDetail_emrServiceBillingGroupComboId", billingGroupCodeArr[index]);
		
	}
	
	
}
