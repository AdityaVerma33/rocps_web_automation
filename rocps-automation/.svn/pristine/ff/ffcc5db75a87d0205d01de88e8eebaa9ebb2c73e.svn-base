package com.subex.rocps.automation.helpers.application.matchandrate;

import java.util.Map;

import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class EventNormalizationComponent extends PSAcceptanceTest {

	protected String inputXDRField;
	protected String normalisedXDRField;
	protected String digitCheckRule;
	protected String inRoute;
	protected String inRouteID;
	protected String inSwitch;
	protected String outRoute;
	protected String outRouteID;
	protected String outSwitch;
	protected String aNumber;
	protected String aNumbeNOA;
	protected String bNumber;
	protected String bNumberNOA;
	protected String cNumber;
	protected String cNumberNOA;
	protected String inRouteId;
	protected String normalisedANumber;
	protected String normalisedBNumber;
	protected String normalisedCNumber;
	protected String outRouteId;
	protected String switchFirst;
	protected String eventCount;
	protected String eventUsage;
	protected String revenueAmount;
	protected String revenueCurrency;
	protected String currencyISOCode;
	protected String currencyId;

	protected Map<String, String> eventCompMap = null;
	protected PSGenericHelper genericObj = new PSGenericHelper();

	public EventNormalizationComponent(Map<String, String> eventNorMap) throws Exception {

		this.eventCompMap = eventNorMap;

	}

	/*
	 * This method is for RouteLookup Component
	 */
	protected void routeLookUpCompoent() throws Exception {
		initializeRouteLookupComp(eventCompMap);
		genericObj.clickPropertyValueColumn("In Route");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", inRoute);
		genericObj.clickPropertyValueColumn("In Route Id");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", inRouteID);
		genericObj.clickPropertyValueColumn("In Switch");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", inSwitch);
		genericObj.clickPropertyValueColumn("Out Route");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", outRoute);
		genericObj.clickPropertyValueColumn("Out Route Id");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", outRouteID);
		genericObj.clickPropertyValueColumn("Out Switch");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", outSwitch);

	}

	/*
	 * This method is for Assign Component
	 */
	protected void assignComponent() throws Exception {
		initializeAssignCompVariables(eventCompMap);
		genericObj.clickPropertyValueColumn("Digit Check Rule");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", digitCheckRule);
		genericObj.clickPropertyValueColumn("Input XDR Field");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", inputXDRField);
		genericObj.clickPropertyValueColumn("Normalised XDR Field");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", normalisedXDRField);

	}

	/*
	 * This method is for Rule StringSet Component
	 */
	protected void ruleStringSetComponent() throws Exception {
		initializeRuleStringsetComp(eventCompMap);
		genericObj.clickPropertyValueColumn("A Number");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", aNumber);
		genericObj.clickPropertyValueColumn("A Number NOA");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", aNumbeNOA);
		genericObj.clickPropertyValueColumn("B Number");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", bNumber);
		genericObj.clickPropertyValueColumn("B Number NOA");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", bNumberNOA);
		genericObj.clickPropertyValueColumn("C Number");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", cNumber);
		genericObj.clickPropertyValueColumn("C Number NOA");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", cNumberNOA);
		genericObj.clickPropertyValueColumn("In Route Id");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", inRouteId);
		genericObj.clickPropertyValueColumn("Normalised A Number");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", normalisedANumber);
		genericObj.clickPropertyValueColumn("Normalised B Number");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", normalisedBNumber);
		genericObj.clickPropertyValueColumn("Normalised C Number");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", normalisedCNumber);
		genericObj.clickPropertyValueColumn("Out Route Id");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", outRouteId);
		genericObj.clickPropertyValueColumn("Switch First");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", switchFirst);

	}
	/*
	 * This method is for PreRating component
	 */

	protected void preratingComponent() throws Exception {
		initializePreratingComp(eventCompMap);
		genericObj.clickPropertyValueColumn("Event Count");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", eventCount);
		genericObj.clickPropertyValueColumn("Event Usage");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", eventUsage);
		genericObj.clickPropertyValueColumn("Revenue Amount");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", revenueAmount);
		genericObj.clickPropertyValueColumn("Revenue Currency");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", revenueCurrency);
	}
	/*
	 * This method is for currency Lookup Component
	 */

	protected void currencyLookupComponent() throws Exception {
		initializecurrencylookup(eventCompMap);
		genericObj.clickPropertyValueColumn("Currency ISO Code");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", currencyISOCode);
		genericObj.clickPropertyValueColumn("Currency Id");
		ComboBoxHelper.select("PS_Detail_properties_digitCheckrule_comboID", currencyId);
	}

	/*
	 * This method is to initialize instance variables for assign component
	 */

	protected void initializeAssignCompVariables(Map<String, String> map) throws Exception {

		inputXDRField = ExcelHolder.getKey(map, "InputXDRField");
		normalisedXDRField = ExcelHolder.getKey(map, "NormalisedXDRField");
		digitCheckRule = ExcelHolder.getKey(map, "DigitCheckRule");

	}

	/*
	 * This method is to initialize instance variables for RuleStringSet
	 * component
	 */
	protected void initializeRuleStringsetComp(Map<String, String> map) throws Exception {

		aNumber = ExcelHolder.getKey(map, "ANumber");
		aNumbeNOA = ExcelHolder.getKey(map, "ANumberNOA");
		bNumber = ExcelHolder.getKey(map, "BNumber");
		bNumberNOA = ExcelHolder.getKey(map, "BNumberNOA");
		cNumber = ExcelHolder.getKey(map, "CNumber");
		cNumberNOA = ExcelHolder.getKey(map, "CNumberNOA");
		inRouteId = ExcelHolder.getKey(map, "InRouteId");
		normalisedANumber = ExcelHolder.getKey(map, "NormalisedANumber");
		normalisedBNumber = ExcelHolder.getKey(map, "NormalisedBNumber");
		normalisedCNumber = ExcelHolder.getKey(map, "NormalisedCNumber");
		outRouteId = ExcelHolder.getKey(map, "OutRouteId");
		switchFirst = ExcelHolder.getKey(map, "SwitchFirst");
	}
	/*
	 * This method is to initialize instance variables for RouteLookup component
	 */

	protected void initializeRouteLookupComp(Map<String, String> map) throws Exception {
		inRoute = ExcelHolder.getKey(map, "InRoute");
		inRouteID = ExcelHolder.getKey(map, "InRouteID");
		inSwitch = ExcelHolder.getKey(map, "InSwitch");
		outRoute = ExcelHolder.getKey(map, "OutRoute");
		outRouteID = ExcelHolder.getKey(map, "OutRouteID");
		outSwitch = ExcelHolder.getKey(map, "OutSwitch");

	}

	/*
	 * This method is to initialize instance variables for prerating component
	 */
	protected void initializePreratingComp(Map<String, String> map) throws Exception {
		eventCount = ExcelHolder.getKey(map, "EventCount");
		eventUsage = ExcelHolder.getKey(map, "EventUsage");
		revenueAmount = ExcelHolder.getKey(map, "RevenueAmount");
		revenueCurrency = ExcelHolder.getKey(map, "RevenueCurrency");
	}

	/*
	 * This method is to initialize instance variables for currency Lookup
	 * component
	 */
	protected void initializecurrencylookup(Map<String, String> map) throws Exception {
		currencyISOCode = ExcelHolder.getKey(map, "CurrencyISOCode");
		currencyId = ExcelHolder.getKey(map, "CurrencyId");
	}

}
