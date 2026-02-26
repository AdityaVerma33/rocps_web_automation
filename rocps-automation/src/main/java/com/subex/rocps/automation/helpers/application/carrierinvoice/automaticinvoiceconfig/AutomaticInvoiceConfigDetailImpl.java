package com.subex.rocps.automation.helpers.application.carrierinvoice.automaticinvoiceconfig;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class AutomaticInvoiceConfigDetailImpl extends PSAcceptanceTest
{
	protected String configurationName;
	protected String autoDispute;
	protected String autoCarrierInvoice;
	protected String disputeMissinginSystem;
	protected String disputeMissinginInvoice;
	protected String disputeMatchedinBoth;
	protected String disputeOverBilledViolating;
	protected String disputeOverBilledWithin;
	protected String disputeUnderBilledViolating;
	protected String disputeUnderBilledWithin;
	protected String disputePercentage;
	protected String disputeAbsolute;
	protected String lowerThresholdPercen;
	protected String lowerThreshold;
	protected String upperThresholdPercen;
	protected String upperThreshold;
	protected String lineItemAuthorization;
	protected String invoiceAuthorization;
	protected String invoiceMissinginSystem;
	protected String invoiceMissinginInvoice;
	protected String invoiceMatchedinBoth;
	protected String invoiceOverBilledViolating;
	protected String invoiceOverBilledWithin;
	protected String invoiceUnderBilledViolating;
	protected String invoiceUnderBilledWithin;
	protected String invoicePercentage;
	protected String invoiceAbsolute;
	protected String invoiceLowerThresholdPercen;
	protected String invoiceLowerThreshold;
	protected String invoiceUpperThresholdPercen;
	protected String invoiceUpperThreshold;
	protected String clientPartition;
	protected String compareTotalAmtFlg;
	protected String totalAmtThresld_percentageFlg;
	protected String totalAmtThresld_absoluteValFlg;
	protected String totalAmtThresld_percentage_lowerThreshold;
	protected String totalAmtThresld_percentage_uperThresold;
	protected String totalAmtThresld_absoluteVal_lowerThreshold;
	protected String totalAmtThresld_absoluteVal_uperThresold;

	PSGenericHelper genricObj = new PSGenericHelper();
	Map<String, String> map = null;

	public AutomaticInvoiceConfigDetailImpl( Map<String, String> map ) throws Exception
	{
		this.map = map;
		initializeVariables( map );

	}

	public void newAutoInvoice() throws Exception
	{
		genricObj.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "New Automatic Invoice Configuration" );

	}

	public void autoInvoiceConfig() throws Exception
	{
		TextBoxHelper.type( "piaiName", configurationName );
		if ( ValidationHelper.isTrue( autoDispute ) )
		{
			CheckBoxHelper.check( "piaiIsAutodispute_InputElement" );
			TabHelper.gotoTab( "//div[text()='Auto Dispute']" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			autoDisputeConfig();
		}
		if ( ValidationHelper.isTrue( autoCarrierInvoice ) )
		{
			CheckBoxHelper.check( "piaiIsAutoci_InputElement" );
			TabHelper.gotoTab( "//div[text()='Auto Carrier Invoice']" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			autoCarrierInvoiceConfig();
		}

	}

	public void autoDisputeConfig() throws Exception
	{
		configureTotalAmtThreshold();
		if ( ValidationHelper.isFalse( disputeMissinginSystem ) )
			CheckBoxHelper.uncheck( "invoiceReconAutoDispute.piraMissInSys_InputElement" );
		if ( ValidationHelper.isFalse( disputeMissinginInvoice ) )
			CheckBoxHelper.uncheck( "invoiceReconAutoDispute.piraMissInInvoice_InputElement" );

		if ( ValidationHelper.isTrue( disputeMatchedinBoth ) )
			CheckBoxHelper.check( "invoiceReconAutoDispute.piraMatchInBoth_InputElement" );

		if ( ValidationHelper.isFalse( disputeOverBilledViolating ) )
			CheckBoxHelper.uncheck( "invoiceReconAutoDispute.piraOverBilledViolatingFl_InputElement" );
		if ( ValidationHelper.isFalse( disputeOverBilledWithin ) )
			CheckBoxHelper.uncheck( "invoiceReconAutoDispute.piraOverBilledWithinFl_InputElement" );

		if ( ValidationHelper.isFalse( disputeUnderBilledViolating ) )
			CheckBoxHelper.uncheck( "invoiceReconAutoDispute.piraUnderBilledViolatingFl_InputElement" );
		if ( ValidationHelper.isFalse( disputeUnderBilledWithin ) )
			CheckBoxHelper.uncheck( "invoiceReconAutoDispute.piraUnderBilledWithinFl_InputElement" );

		if ( ValidationHelper.isFalse( disputePercentage ) )
			CheckBoxHelper.uncheck( "invoiceReconAutoDispute.piraIspercentageFl_InputElement" );
		else
		{
			TextBoxHelper.type( "invoiceReconAutoDispute.piraLowerPercentThreshold", lowerThresholdPercen );
			TextBoxHelper.type( "invoiceReconAutoDispute.piraUpperPercentThreshold", upperThresholdPercen );
		}

		if ( ValidationHelper.isFalse( disputeAbsolute ) )
			CheckBoxHelper.uncheck( "invoiceReconAutoDispute.piraIsabsoluteFl_InputElement" );
		else
		{
			TextBoxHelper.type( "invoiceReconAutoDispute.piraLowerAbsThreshold", lowerThreshold );
			TextBoxHelper.type( "invoiceReconAutoDispute.piraUpperAbsThreshold", upperThreshold );
		}

	}

	private void configureTotalAmtThreshold() throws Exception
	{
		if ( ValidationHelper.isTrue( compareTotalAmtFlg ) )
		{
			CheckBoxHelper.check( "piaiSummaryLevelFl_InputElement" );
			configTotalAmtThreshPercentage();
			configTotalAmtAbsoluteVal();
		}
		else if ( ValidationHelper.isFalse( compareTotalAmtFlg ) && CheckBoxHelper.isChecked( "piaiSummaryLevelFl_InputElement" ) )
			CheckBoxHelper.uncheck( "piaiSummaryLevelFl_InputElement" );
	}

	private void configTotalAmtThreshPercentage() throws Exception
	{
		if ( ValidationHelper.isFalse( totalAmtThresld_percentageFlg ) && CheckBoxHelper.isChecked( "autoDisputeSummaryThres.padsPercentageValidate_InputElement" ) )
			CheckBoxHelper.uncheck( "autoDisputeSummaryThres.padsPercentageValidate_InputElement" );
		if ( ValidationHelper.isTrue( totalAmtThresld_percentageFlg ) )
		{
			CheckBoxHelper.check( "autoDisputeSummaryThres.padsPercentageValidate_InputElement" );
			TextBoxHelper.type( "autoDisputeSummaryThres.padsMinPctThreshold", totalAmtThresld_percentage_lowerThreshold );
			TextBoxHelper.type( "autoDisputeSummaryThres.padsMaxPctThreshold", totalAmtThresld_percentage_uperThresold );

		}

	}

	private void configTotalAmtAbsoluteVal() throws Exception
	{
		if ( ValidationHelper.isFalse( totalAmtThresld_absoluteValFlg ) && CheckBoxHelper.isChecked( "autoDisputeSummaryThres.padsAbsoluteValidate_InputElement" ) )
			CheckBoxHelper.uncheck( "autoDisputeSummaryThres.padsAbsoluteValidate_InputElement" );
		if ( ValidationHelper.isTrue( totalAmtThresld_absoluteValFlg ) )
		{
			CheckBoxHelper.check( "autoDisputeSummaryThres.padsAbsoluteValidate_InputElement" );
			TextBoxHelper.type( "autoDisputeSummaryThres.padsMinAbsThreshold", totalAmtThresld_absoluteVal_lowerThreshold );
			TextBoxHelper.type( "autoDisputeSummaryThres.padsMaxAbsThreshold", totalAmtThresld_absoluteVal_uperThresold );

		}

	}

	public void autoCarrierInvoiceConfig() throws Exception
	{

		if ( ValidationHelper.isTrue( invoiceAuthorization ) )
		{
			CheckBoxHelper.check( "autoCiRadioGroupInvoiceReconAutoCi.pircIsInvoiceLevel_InputElement" );
			carrierInvoiceThreshold();
		}
		if ( ValidationHelper.isTrue( lineItemAuthorization ) )
		{
			CheckBoxHelper.check( "autoCiRadioGroupInvoiceReconAutoCi.pircIsLineLevel_InputElement" );
			if ( ValidationHelper.isFalse( invoiceMissinginSystem ) )
				CheckBoxHelper.uncheck( "invoiceReconAutoCi.pircMissInSys_InputElement" );
			if ( ValidationHelper.isFalse( invoiceMissinginInvoice ) )
				CheckBoxHelper.uncheck( "invoiceReconAutoCi.pircMissInInvoice_InputElement" );

			if ( ValidationHelper.isTrue( invoiceMatchedinBoth ) )
				CheckBoxHelper.check( "invoiceReconAutoCi.pircMatchInBoth_InputElement" );

			if ( ValidationHelper.isFalse( invoiceOverBilledViolating ) )
				CheckBoxHelper.uncheck( "invoiceReconAutoCi.pircOverBilledViolatingFl_InputElement" );
			if ( ValidationHelper.isFalse( invoiceOverBilledWithin ) )
				CheckBoxHelper.uncheck( "invoiceReconAutoCi.pircOverBilledWithinFl_InputElement" );

			if ( ValidationHelper.isFalse( invoiceUnderBilledViolating ) )
				CheckBoxHelper.uncheck( "invoiceReconAutoCi.pircUnderBilledViolatingFl_InputElement" );
			if ( ValidationHelper.isFalse( invoiceUnderBilledWithin ) )
				CheckBoxHelper.uncheck( "invoiceReconAutoCi.pircUnderBilledWithinFl_InputElement" );

		}
		carrierInvoiceThreshold();

	}

	public void carrierInvoiceThreshold() throws Exception
	{
		if ( ValidationHelper.isTrue( invoicePercentage ) )
		{
			CheckBoxHelper.check( "invoiceReconAutoCi.pircIspercentageFl_InputElement" );
			TextBoxHelper.type( "invoiceReconAutoCi.pircLowerPercentThreshold", invoiceLowerThresholdPercen );
			TextBoxHelper.type( "invoiceReconAutoCi.pircUpperPercentThreshold", invoiceUpperThresholdPercen );
		}
		if ( ValidationHelper.isTrue( invoiceAbsolute ) )
		{
			CheckBoxHelper.check( "invoiceReconAutoCi.pircIsabsoluteFl_InputElement" );
			TextBoxHelper.type( "invoiceReconAutoCi.pircLowerAbsThreshold", invoiceLowerThreshold );
			TextBoxHelper.type( "invoiceReconAutoCi.pircUpperAbsThreshold", invoiceUpperThreshold );
		}
	}

	public void saveAutomaticInvoiceConfig() throws Exception
	{
		ButtonHelper.click( "automaticInvoiceConfigDetail.save" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	public void edittAutoDisputeConfig() throws Exception
	{
		configureTotalAmtThreshold();
		if ( ValidationHelper.isFalse( disputeMissinginSystem ) && CheckBoxHelper.isChecked( "invoiceReconAutoDispute.piraMissInSys_InputElement" ) )
			CheckBoxHelper.uncheck( "invoiceReconAutoDispute.piraMissInSys_InputElement" );
		if ( ValidationHelper.isFalse( disputeMissinginInvoice ) && CheckBoxHelper.isChecked( "invoiceReconAutoDispute.piraMissInInvoice_InputElement" ) )
			CheckBoxHelper.uncheck( "invoiceReconAutoDispute.piraMissInInvoice_InputElement" );

		if ( ValidationHelper.isTrue( disputeMatchedinBoth ) && !CheckBoxHelper.isChecked( "invoiceReconAutoDispute.piraMatchInBoth_InputElement" ) )
			CheckBoxHelper.check( "invoiceReconAutoDispute.piraMatchInBoth_InputElement" );

		if ( ValidationHelper.isFalse( disputeOverBilledViolating ) && CheckBoxHelper.isChecked( "invoiceReconAutoDispute.piraOverBilledViolatingFl_InputElement" ) )
			CheckBoxHelper.uncheck( "invoiceReconAutoDispute.piraOverBilledViolatingFl_InputElement" );
		if ( ValidationHelper.isFalse( disputeOverBilledWithin ) && CheckBoxHelper.isChecked( "invoiceReconAutoDispute.piraOverBilledWithinFl_InputElement" ) )
			CheckBoxHelper.uncheck( "invoiceReconAutoDispute.piraOverBilledWithinFl_InputElement" );

		if ( ValidationHelper.isFalse( disputeUnderBilledViolating ) && CheckBoxHelper.isChecked( "invoiceReconAutoDispute.piraUnderBilledViolatingFl_InputElement" ) )
			CheckBoxHelper.uncheck( "invoiceReconAutoDispute.piraUnderBilledViolatingFl_InputElement" );
		if ( ValidationHelper.isFalse( disputeUnderBilledWithin ) && CheckBoxHelper.isChecked( "invoiceReconAutoDispute.piraUnderBilledWithinFl_InputElement" ) )
			CheckBoxHelper.uncheck( "invoiceReconAutoDispute.piraUnderBilledWithinFl_InputElement" );

		if ( ValidationHelper.isFalse( disputePercentage ) && CheckBoxHelper.isChecked( "invoiceReconAutoDispute.piraIspercentageFl_InputElement" ) )
			CheckBoxHelper.uncheck( "invoiceReconAutoDispute.piraIspercentageFl_InputElement" );
		else
		{
			if ( ValidationHelper.isNotEmpty( lowerThresholdPercen ) )
				TextBoxHelper.type( "invoiceReconAutoDispute.piraLowerPercentThreshold", lowerThresholdPercen );
			if ( ValidationHelper.isNotEmpty( upperThresholdPercen ) )
				TextBoxHelper.type( "invoiceReconAutoDispute.piraUpperPercentThreshold", upperThresholdPercen );
		}

		if ( ValidationHelper.isFalse( disputeAbsolute ) && CheckBoxHelper.isChecked( "invoiceReconAutoDispute.piraIsabsoluteFl_InputElement" ) )
			CheckBoxHelper.uncheck( "invoiceReconAutoDispute.piraIsabsoluteFl_InputElement" );
		else
		{
			if ( ValidationHelper.isNotEmpty( lowerThreshold ) )
				TextBoxHelper.type( "invoiceReconAutoDispute.piraLowerAbsThreshold", lowerThreshold );
			if ( ValidationHelper.isNotEmpty( upperThreshold ) )
				TextBoxHelper.type( "invoiceReconAutoDispute.piraUpperAbsThreshold", upperThreshold );
		}

	}

	public void editAutoInvoiceConfig() throws Exception
	{
		TextBoxHelper.type( "piaiName", configurationName );
		if ( ValidationHelper.isTrue( autoDispute ) && !CheckBoxHelper.isChecked( "piaiIsAutodispute_InputElement" ) )
		{
			CheckBoxHelper.check( "piaiIsAutodispute_InputElement" );
		}
		TabHelper.gotoTab( "//div[text()='Auto Dispute']" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		edittAutoDisputeConfig();

		if ( ValidationHelper.isTrue( autoCarrierInvoice ) && !CheckBoxHelper.isChecked( "piaiIsAutoci_InputElement" ) )
		{
			CheckBoxHelper.check( "piaiIsAutoci_InputElement" );
		}
		TabHelper.gotoTab( "//div[text()='Auto Carrier Invoice']" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		editautoCarrierInvoiceConfig();

	}

	public void editautoCarrierInvoiceConfig() throws Exception
	{

		if ( ValidationHelper.isTrue( invoiceAuthorization ) && !CheckBoxHelper.isChecked( "autoCiRadioGroupInvoiceReconAutoCi.pircIsInvoiceLevel_InputElement" ) )
		{
			CheckBoxHelper.check( "autoCiRadioGroupInvoiceReconAutoCi.pircIsInvoiceLevel_InputElement" );
			editCarrierInvoiceThreshold();
		}
		if ( ValidationHelper.isTrue( lineItemAuthorization ) && !CheckBoxHelper.isChecked( "autoCiRadioGroupInvoiceReconAutoCi.pircIsLineLevel_InputElement" ) )
		{
			CheckBoxHelper.check( "autoCiRadioGroupInvoiceReconAutoCi.pircIsLineLevel_InputElement" );
			if ( ValidationHelper.isFalse( invoiceMissinginSystem ) && CheckBoxHelper.isChecked( "invoiceReconAutoCi.pircMissInSys_InputElement" ) )
				CheckBoxHelper.uncheck( "invoiceReconAutoCi.pircMissInSys_InputElement" );
			if ( ValidationHelper.isFalse( invoiceMissinginInvoice ) && CheckBoxHelper.isChecked( "invoiceReconAutoCi.pircMissInInvoice_InputElement" ) )
				CheckBoxHelper.uncheck( "invoiceReconAutoCi.pircMissInInvoice_InputElement" );

			if ( ValidationHelper.isTrue( invoiceMatchedinBoth ) && !CheckBoxHelper.isChecked( "invoiceReconAutoCi.pircMatchInBoth_InputElement" ) )
				CheckBoxHelper.check( "invoiceReconAutoCi.pircMatchInBoth_InputElement" );

			if ( ValidationHelper.isFalse( invoiceOverBilledViolating ) && CheckBoxHelper.isChecked( "invoiceReconAutoCi.pircOverBilledViolatingFl_InputElement" ) )
				CheckBoxHelper.uncheck( "invoiceReconAutoCi.pircOverBilledViolatingFl_InputElement" );
			if ( ValidationHelper.isFalse( invoiceOverBilledWithin ) && CheckBoxHelper.isChecked( "invoiceReconAutoCi.pircOverBilledWithinFl_InputElement" ) )
				CheckBoxHelper.uncheck( "invoiceReconAutoCi.pircOverBilledWithinFl_InputElement" );

			if ( ValidationHelper.isFalse( invoiceUnderBilledViolating ) && CheckBoxHelper.isChecked( "invoiceReconAutoCi.pircUnderBilledViolatingFl_InputElement" ) )
				CheckBoxHelper.uncheck( "invoiceReconAutoCi.pircUnderBilledViolatingFl_InputElement" );
			if ( ValidationHelper.isFalse( invoiceUnderBilledWithin ) && CheckBoxHelper.isChecked( "invoiceReconAutoCi.pircUnderBilledWithinFl_InputElement" ) )
				CheckBoxHelper.uncheck( "invoiceReconAutoCi.pircUnderBilledWithinFl_InputElement" );

		}
		editCarrierInvoiceThreshold();

	}

	public void editCarrierInvoiceThreshold() throws Exception
	{
		if ( ValidationHelper.isTrue( invoicePercentage ) && !CheckBoxHelper.isChecked( "invoiceReconAutoCi.pircIspercentageFl_InputElement" ) )
		{
			CheckBoxHelper.check( "invoiceReconAutoCi.pircIspercentageFl_InputElement" );
			if ( ValidationHelper.isNotEmpty( invoiceLowerThresholdPercen ) )
				TextBoxHelper.type( "invoiceReconAutoCi.pircLowerPercentThreshold", invoiceLowerThresholdPercen );
			if ( ValidationHelper.isNotEmpty( invoiceUpperThresholdPercen ) )
				TextBoxHelper.type( "invoiceReconAutoCi.pircUpperPercentThreshold", invoiceUpperThresholdPercen );
		}
		if ( ValidationHelper.isTrue( invoiceAbsolute ) && !CheckBoxHelper.isChecked( "invoiceReconAutoCi.pircIsabsoluteFl_InputElement" ) )
		{
			CheckBoxHelper.check( "invoiceReconAutoCi.pircIsabsoluteFl_InputElement" );
			if ( ValidationHelper.isNotEmpty( invoiceLowerThreshold ) )
				TextBoxHelper.type( "invoiceReconAutoCi.pircLowerAbsThreshold", invoiceLowerThreshold );
			if ( ValidationHelper.isNotEmpty( invoiceUpperThreshold ) )
				TextBoxHelper.type( "invoiceReconAutoCi.pircUpperAbsThreshold", invoiceUpperThreshold );
		}
	}

	public void initializeVariables( Map<String, String> map ) throws Exception
	{
		configurationName = ExcelHolder.getKey( map, "ConfigurationName" );
		autoDispute = ExcelHolder.getKey( map, "AutoDispute" );
		autoCarrierInvoice = ExcelHolder.getKey( map, "AutoCarrierInvoice" );
		disputeMissinginSystem = ExcelHolder.getKey( map, "DisputeMissinginSystem" );
		disputeMissinginInvoice = ExcelHolder.getKey( map, "DisputeMissinginInvoice" );
		disputeMatchedinBoth = ExcelHolder.getKey( map, "DisputeMatchedinBoth" );
		disputeOverBilledViolating = ExcelHolder.getKey( map, "DisputeOverBilledViolating" );
		disputeOverBilledWithin = ExcelHolder.getKey( map, "DisputeOverBilledWithin" );
		disputeUnderBilledViolating = ExcelHolder.getKey( map, "DisputeUnderBilledViolating" );
		disputeUnderBilledWithin = ExcelHolder.getKey( map, "DisputeUnderBilledWithin" );
		disputePercentage = ExcelHolder.getKey( map, "DisputePercentage" );
		disputeAbsolute = ExcelHolder.getKey( map, "DisputeAbsolute" );
		lowerThresholdPercen = ExcelHolder.getKey( map, "LowerThresholdPercen" );
		lowerThreshold = ExcelHolder.getKey( map, "LowerThreshold" );
		upperThresholdPercen = ExcelHolder.getKey( map, "UpperThresholdPercen" );
		upperThreshold = ExcelHolder.getKey( map, "UpperThreshold" );
		lineItemAuthorization = ExcelHolder.getKey( map, "LineItemAuthorization" );
		invoiceAuthorization = ExcelHolder.getKey( map, "InvoiceAuthorization" );
		invoiceMissinginSystem = ExcelHolder.getKey( map, "InvoiceMissinginSystem" );
		invoiceMissinginInvoice = ExcelHolder.getKey( map, "InvoiceMissinginInvoice" );
		invoiceMatchedinBoth = ExcelHolder.getKey( map, "InvoiceatchedinBoth" );
		invoiceOverBilledViolating = ExcelHolder.getKey( map, "InvoiceOverBilledViolating" );
		invoiceOverBilledWithin = ExcelHolder.getKey( map, "InvoiceOverBilledWithin" );
		invoiceUnderBilledViolating = ExcelHolder.getKey( map, "InvoiceUnderBilledViolating" );
		invoiceUnderBilledWithin = ExcelHolder.getKey( map, "InvoiceUnderBilledWithin" );
		invoicePercentage = ExcelHolder.getKey( map, "InvoicePercentage" );
		invoiceAbsolute = ExcelHolder.getKey( map, "InvoiceAbsolute" );
		invoiceLowerThresholdPercen = ExcelHolder.getKey( map, "InvoiceThresholdPercen" );
		invoiceLowerThreshold = ExcelHolder.getKey( map, "InvoiceThreshold" );
		invoiceUpperThresholdPercen = ExcelHolder.getKey( map, "InvoiceUpperThresholdPercen" );
		invoiceUpperThreshold = ExcelHolder.getKey( map, "InvoiceUpperThreshold" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		compareTotalAmtFlg = ExcelHolder.getKey( map, "CompareTotalAmtFlg" );
		totalAmtThresld_percentageFlg = ExcelHolder.getKey( map, "TotalAmtThres_PercentFlg" );
		totalAmtThresld_percentage_lowerThreshold = ExcelHolder.getKey( map, "TotalAmtThres_Percent_lowerThreshold" );
		totalAmtThresld_percentage_uperThresold = ExcelHolder.getKey( map, "TotalAmtThres_Percent_upperThreshold" );
		totalAmtThresld_absoluteValFlg = ExcelHolder.getKey( map, "TotalAmtThres_absoluteValFlg" );
		totalAmtThresld_absoluteVal_lowerThreshold = ExcelHolder.getKey( map, "TotalAmtThres_absoluteVal_lowerThreshold" );
		totalAmtThresld_absoluteVal_uperThresold = ExcelHolder.getKey( map, "TotalAmtThres_absoluteVal_upperThreshold" );

	}
}
