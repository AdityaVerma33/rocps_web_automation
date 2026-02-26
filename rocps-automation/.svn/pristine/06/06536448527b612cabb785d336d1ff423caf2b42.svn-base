package com.subex.rocps.automation.helpers.application.aggregation.aggregationconfiguration;

import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class AggregationOptionDetailsImpl {

	protected String name;
	protected String tablePrefix;
	protected String filterComponent;
	protected String ratingComponent;
	protected String schema;
	protected String considerForMarginsFl;
	protected String customFields;
	protected String eventTypes;
	protected String considerEstimation;

	protected Map<String, String> map;
	PSGenericHelper genHelperObj = new PSGenericHelper();

	public AggregationOptionDetailsImpl(Map<String, String> map) throws Exception {

		this.map = map;
		initialiseVariables(this.map);
	}

	private void initialiseVariables(Map<String, String> map) throws Exception {
		name = ExcelHolder.getKey(map, "Name");
		tablePrefix = ExcelHolder.getKey(map, "TablePrefix");
		filterComponent = ExcelHolder.getKey(map, "FilterComponent");
		ratingComponent = ExcelHolder.getKey(map, "RatingComponent");
		schema = ExcelHolder.getKey(map, "Schema");
		considerForMarginsFl = ExcelHolder.getKey(map, "ConsiderForMargins");
		customFields = ExcelHolder.getKey(map, "CustomFields");
		eventTypes = ExcelHolder.getKey(map, "EventType");
		considerEstimation = ExcelHolder.getKey(map, "ConsiderEstimation");
	}

	/*
	 * Method for configuring the aggegation header detail
	 */
	public void aggregationHeaderConfig() throws Exception {

		TextBoxHelper.type("PSDetail_agcName_txtid", name);
		TextBoxHelper.type("PSDetail_agctablePrefix_txtId", tablePrefix);
		ComboBoxHelper.select("PSDetail_agcfilterComponent_comboId", filterComponent);
		ComboBoxHelper.select("PSDetail_agcratingComponent_comboid", ratingComponent);
		ComboBoxHelper.select("PSDetail_agcschema_comboId", schema);

		if (ValidationHelper.isTrue(considerForMarginsFl))
			CheckBoxHelper.check("PSDetail_agcConsiderForMargin_checkBoxId");
		
		if (ValidationHelper.isTrue(considerEstimation))
			CheckBoxHelper.check("pagcEstmationFl_InputElement");

	}

	/*
	 * Method for selecting aggregation custom fields
	 */
	public void selectAggregationCustomFields() throws Exception {
		if (!customFields.isEmpty()) {
			String[] customFieldsArr = new PSStringUtils().stringSplitFirstLevel(customFields);
			for (String strVal : customFieldsArr) {
				genHelperObj.dualListSelection("Available Custom Fields", strVal);
			}
		}
	}

	/*
	 * Method for selecting event types in the dual list
	 */
	public void selectEventTypes() throws Exception {
		String[] eventTypeArr = new PSStringUtils().stringSplitFirstLevel(eventTypes);
		for (String strVal : eventTypeArr) {
			genHelperObj.dualListSelection("Available Event Types", strVal);
		}
	}

	/*
	 * Method for saving the aggregation
	 */
	public void aggregationDetailSave() throws Exception {

		ButtonHelper.click("PSDetail_agcSaveBtnId");
		GenericHelper.waitForLoadmask();
		GenericHelper.waitForElementToDisappear("PSDetail_agcSaveBtnId", 120);
	}

}
