package com.subex.rocps.automation.helpers.application.bills.billpackage;

import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class BillPackageDetailImpl extends PSAcceptanceTest
{
	protected String clientPartition;
	protected String name;
	protected String franchise;
	protected String salesTaxComponent;
	protected String numberComponent;
	protected String roundingMode;
	protected String applyLineItem;
	protected String billComponent;
	protected String billNumberGrp;
	protected String isDeltaBill;
	protected String multiCurrency;
	protected String carrierInvoiceNoLength;
	protected String billInputGrp;
	protected String billOutputgrp;
	protected String emailFormatFile;
	protected String emailSubject;
	protected String reportWriterComponent;
	protected String settlementOutput;
	protected String bilateralModelling;
	protected String billImageFile;
	protected String calculateRebate;
	protected String dateTimeMask;
	protected String displayThousandsSeperator;
	protected String durationDP;
	protected String fontName;
	protected String fontsDirPath;
	protected String logoDirPath;
	protected String longDateMask;
	protected String shortDateMask;
	protected String creditComponent;
	protected String creditNoGrp;
	protected String birtDesignerFile;
	protected String birtFileFormat;
	protected String birtTempFilePath;
	protected String creditImageName;
	protected String emailFormatFileCredit;
	protected String emailSubjectCredit;
	protected String logoFileCredit;
	protected String reportWriterCompCredit;
	protected String retainTempfiles;
	int WaitSecs;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected Map<String, String> billPackageImplMap = null;

	public BillPackageDetailImpl( Map<String, String> billPackageMap ) throws Exception
	{
		this.billPackageImplMap = billPackageMap;
		initializeVariables( billPackageImplMap );
	}

	/*
	 * This method is for bill package detail config
	 */
	public void billpackageDetailConfig() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_BillPackage_name_txtID", name );
		ComboBoxHelper.select( "PS_Detail_BillPackage_franchise_comboID", franchise );
		ComboBoxHelper.select( "PS_Detail_BillPackage_salesTaxComp_comboID", salesTaxComponent );
		ComboBoxHelper.select( "PS_Detail_BillPackage_numberComp_ComboID", numberComponent );
		ComboBoxHelper.select( "PS_Detail_BillPackage_roundingMode_comboID", roundingMode );
		if ( ValidationHelper.isTrue( applyLineItem ) )
			CheckBoxHelper.check( "PS_Detail_BillPackage_applyLineItem_chkbx" );
	}

	/*
	 * This method is to edit  bill package detail config
	 */
	public void editbillpackageDetail() throws Exception
	{

		TextBoxHelper.type( "PS_Detail_BillPackage_name_txtID", name );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_BillPackage_name_txtID" ), name, "Bill package name is not matched" );
		if ( ValidationHelper.isNotEmpty( franchise ) )
			ComboBoxHelper.select( "PS_Detail_BillPackage_franchise_comboID", franchise );
		if ( ValidationHelper.isNotEmpty( salesTaxComponent ) )
			ComboBoxHelper.select( "PS_Detail_BillPackage_salesTaxComp_comboID", salesTaxComponent );
		if ( ValidationHelper.isNotEmpty( numberComponent ) )
			assertEquals( ComboBoxHelper.getValue( "PS_Detail_BillPackage_numberComp_ComboID" ), numberComponent, "Number component is not matched" );
		if ( ValidationHelper.isNotEmpty( roundingMode ) )
			ComboBoxHelper.select( "PS_Detail_BillPackage_roundingMode_comboID", roundingMode );
		if ( ValidationHelper.isTrue( applyLineItem ) )
			CheckBoxHelper.check( "PS_Detail_BillPackage_applyLineItem_chkbx" );
		if ( ValidationHelper.isFalse( applyLineItem ) )
			CheckBoxHelper.uncheck( "PS_Detail_BillPackage_applyLineItem_chkbx" );
	}

	/*
	 * this method is for Control Properties Panel
	 */
	public void billControlProperties() throws Exception
	{

		if ( ValidationHelper.isTrue( isDeltaBill ) )
			CheckBoxHelper.check( "PS_Detail_BillPackage_isDeltaBill_chkbx" );
		if ( ValidationHelper.isTrue( multiCurrency ) )
			CheckBoxHelper.check( "PS_Detail_BillPackage_multiCurrency_chkbx" );
		ComboBoxHelper.select( "PS_Detail_BillPackage_billComponent_comboID", billComponent );
		ComboBoxHelper.select( "PS_Detail_BillPackage_billNumberGrp_comboID", billNumberGrp );
		TextBoxHelper.type( "PS_Detail_BillPackage_carrierInvoiceseq_txtID", carrierInvoiceNoLength );
		billComponentPropertySelection();
	}

	/*
	 * this method is for edit Control Properties Panel
	 */
	public void editbillControlProperties() throws Exception
	{

		if ( ValidationHelper.isTrue( isDeltaBill ) )
			assertTrue( CheckBoxHelper.isChecked( "PS_Detail_BillPackage_isDeltaBill_chkbx" ), "'Is Detla bill' is not checked as expected" );
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_BillPackage_billComponent_comboID" ), billComponent, "Bill compponenet is not matched" );
		if ( ValidationHelper.isTrue( multiCurrency ) )
			CheckBoxHelper.check( "PS_Detail_BillPackage_multiCurrency_chkbx" );
		if ( ValidationHelper.isFalse( multiCurrency ) )
			CheckBoxHelper.uncheck( "PS_Detail_BillPackage_multiCurrency_chkbx" );
		if ( ValidationHelper.isNotEmpty( billNumberGrp ) )
			ComboBoxHelper.select( "PS_Detail_BillPackage_billNumberGrp_comboID", billNumberGrp );
		if ( ValidationHelper.isNotEmpty( carrierInvoiceNoLength ) )
			assertEquals( TextBoxHelper.getValue( "PS_Detail_BillPackage_carrierInvoiceseq_txtID" ), carrierInvoiceNoLength );
	}

	/*
	 * This method is for component propertySelection
	 */
	public void billComponentPropertySelection() throws Exception
	{
		if ( billComponent.contentEquals( "Configurable Delta Bill Component" ) || billComponent.contentEquals( "Configurable Bill Component" ) )
			configurableDeltaBillCompoent();
		else if ( billComponent.contentEquals( "Configurable Settlement Component" ) )
			cofigurableSettlementComponent();
		else if ( billComponent.contentEquals( "Bilateral Bill Component" ) )
			bilateralBillComponent();
	}

	/*
	 * This method is for Configurable Delta&NonDelta bill component
	 */

	public void configurableDeltaBillCompoent() throws Exception
	{
		PropertyGridHelper.selectInComboBox( "Bill Breakdown Input Group *", billInputGrp );
		PropertyGridHelper.selectInComboBox( "Bill Breakdown Output Group *", billOutputgrp );
		PropertyGridHelper.typeInDataDir( "Email Format File", emailFormatFile );
		PropertyGridHelper.typeInTextBox( "Email Subject", emailSubject );
		PropertyGridHelper.selectInComboBox( "Report Writer Component *", reportWriterComponent );
	}

	/*
	 * This method is for Configurable Settlement component
	 */
	public void cofigurableSettlementComponent() throws Exception
	{
		PropertyGridHelper.typeInDataDir( "Email Format File", emailFormatFile );
		PropertyGridHelper.typeInTextBox( "Email Subject", emailSubject );
		PropertyGridHelper.selectInComboBox( "Report Writer Component *", reportWriterComponent );
		PropertyGridHelper.selectInComboBox( "Settlement Output *", settlementOutput );
	}

	/*
	 * This method is for bilateralBillcomponent
	 */
	public void bilateralBillComponent() throws Exception
	{
		PropertyGridHelper.selectInComboBox( "Bilateral Modelling *", bilateralModelling );
		PropertyGridHelper.typeInTextBox( "Bill Image File", billImageFile );
		PropertyGridHelper.checkCheckBox( "Calculate Rebate In Deal Currency *" );
		PropertyGridHelper.typeInTextBox( "Date Time Mask *", dateTimeMask );
		PropertyGridHelper.checkCheckBox( "Display Thousands Separator *" );
		PropertyGridHelper.typeInTextBox( "Duration DP *", durationDP );
		PropertyGridHelper.typeInDataDir( "Email Format File", emailFormatFile );
		PropertyGridHelper.typeInTextBox( "Email Subject", emailSubject );
		PropertyGridHelper.typeInTextBox( "Font Name *", fontName );
		PropertyGridHelper.typeInDataDir( "Fonts Directory Path *", fontsDirPath );
		PropertyGridHelper.typeInDataDir( "Logo Directory Path *", logoDirPath );
		PropertyGridHelper.typeInTextBox( "Long Date Mask *", longDateMask );
		PropertyGridHelper.typeInTextBox( "Short Date Mask *", shortDateMask );
	}

	/*
	 * This method is for credit Tab config
	 */
	public void creditTabConfig() throws Exception
	{
		TabHelper.gotoTab( "PS_Detail_BillPackage_creditTabID" );
		GenericHelper.waitTime( 3, "Loading tab with Credit Control" );
		ElementHelper.waitForElement( "PS_Detail_BillPackage_creditTabID", WaitSecs );
		ComboBoxHelper.select( "PS_Detail_BillPackage_creditComp_comboID", creditComponent );
		ComboBoxHelper.select( "PS_Detail_BillPackage_creditNumberGrp_comboID", creditNoGrp );
		creditPropertiesConfig();
	}

	/*
	 * This method is for  editcredit Tab config
	 */
	public void editCreditTabConfig() throws Exception
	{
		TabHelper.gotoTab( "PS_Detail_BillPackage_creditTabID" );
		GenericHelper.waitTime( 3, "Loading tab with Credit Control" );
		ElementHelper.waitForElement( "PS_Detail_BillPackage_creditTabID", WaitSecs );
		if ( ValidationHelper.isNotEmpty( creditComponent ) )
			ComboBoxHelper.select( "PS_Detail_BillPackage_creditComp_comboID", creditComponent );
		if ( ValidationHelper.isNotEmpty( creditNoGrp ) )
			ComboBoxHelper.select( "PS_Detail_BillPackage_creditNumberGrp_comboID", creditNoGrp );
		creditPropertiesConfig();
	}

	/*
	 * This method is for credit properties config
	 */
	public void creditPropertiesConfig() throws Exception
	{
		PropertyGridHelper.typeInDataDir( "creditControlPropertiesGridPanel", "Birt Designer File", birtDesignerFile, ";" );
		PropertyGridHelper.typeInTextBox( "creditControlPropertiesGridPanel", "Birt File Format", birtFileFormat );
		PropertyGridHelper.typeInDataDir( "creditControlPropertiesGridPanel", "Birt Temporary File Path", birtTempFilePath, ";" );
		PropertyGridHelper.typeInTextBox( "creditControlPropertiesGridPanel", "Credit Image Name", creditImageName );
		PropertyGridHelper.typeInDataDir( "creditControlPropertiesGridPanel", "Email Format File", emailFormatFileCredit, ";" );
		PropertyGridHelper.typeInTextBox( "creditControlPropertiesGridPanel", "Email Subject", emailSubjectCredit );
		PropertyGridHelper.typeInDataDir( "creditControlPropertiesGridPanel", "Logo File", logoFileCredit, ";" );
		PropertyGridHelper.selectInComboBox( "creditControlPropertiesGridPanel", "Report Writer Component", reportWriterCompCredit );
		PropertyGridHelper.clickCheckBox( "creditControlPropertiesGridPanel", "Retain Temporary Files", retainTempfiles );
	}

	/*
	 * This method is to initialize instance variables
	 */
	public void initializeVariables( Map<String, String> map ) throws Exception
	{

		clientPartition = ExcelHolder.getKey( map, "Partition" );
		name = ExcelHolder.getKey( map, "Name" );
		franchise = ExcelHolder.getKey( map, "Franchise" );
		salesTaxComponent = ExcelHolder.getKey( map, "SalesTaxComponent" );
		numberComponent = ExcelHolder.getKey( map, "NumberComponent" );
		roundingMode = ExcelHolder.getKey( map, "RoundingMode" );
		applyLineItem = ExcelHolder.getKey( map, "ApplyLineItem" );
		billComponent = ExcelHolder.getKey( map, "BillComponent" );
		billNumberGrp = ExcelHolder.getKey( map, "BillNumberGrp" );
		isDeltaBill = ExcelHolder.getKey( map, "IsDeltaBill" );
		multiCurrency = ExcelHolder.getKey( map, "MultiCurrency" );
		carrierInvoiceNoLength = ExcelHolder.getKey( map, "CarrierInvoiceNoLength" );
		billInputGrp = ExcelHolder.getKey( map, "BillInputGrp" );
		billOutputgrp = ExcelHolder.getKey( map, "BillOutputgrp" );
		emailFormatFile = ExcelHolder.getKey( map, "EmailFormatFile" );
		emailSubject = ExcelHolder.getKey( map, "EmailSubject" );
		reportWriterComponent = ExcelHolder.getKey( map, "ReportWriterComponent" );
		settlementOutput = ExcelHolder.getKey( map, "SettlementOutput" );
		bilateralModelling = ExcelHolder.getKey( map, "BilateralModelling" );
		billImageFile = ExcelHolder.getKey( map, "BillImageFile" );
		calculateRebate = ExcelHolder.getKey( map, "CalculateRebate" );
		dateTimeMask = ExcelHolder.getKey( map, "DateTimeMask" );
		displayThousandsSeperator = ExcelHolder.getKey( map, "DisplayThousandsSeperator" );
		durationDP = ExcelHolder.getKey( map, "DurationDP" );
		fontName = ExcelHolder.getKey( map, "FontName" );
		fontsDirPath = ExcelHolder.getKey( map, "FontsDirPath" );
		logoDirPath = ExcelHolder.getKey( map, "LogoDirPath" );
		longDateMask = ExcelHolder.getKey( map, "LongDateMask" );
		shortDateMask = ExcelHolder.getKey( map, "ShortDateMask" );
		creditComponent = ExcelHolder.getKey( map, "CreditComponent" );
		creditNoGrp = ExcelHolder.getKey( map, "CreditNoGrp" );
		birtDesignerFile = ExcelHolder.getKey( map, "BirtDesignerFile" );
		birtFileFormat = ExcelHolder.getKey( map, "BirtFileFormat" );
		birtTempFilePath = ExcelHolder.getKey( map, "BirtTempFilePath" );
		creditImageName = ExcelHolder.getKey( map, "CreditImageName" );
		emailFormatFileCredit = ExcelHolder.getKey( map, "EmailFormatFileCredit" );
		emailSubjectCredit = ExcelHolder.getKey( map, "EmailSubjectCredit" );
		logoFileCredit = ExcelHolder.getKey( map, "LogoFileCredit" );
		reportWriterCompCredit = ExcelHolder.getKey( map, "ReportWriterCompCredit" );
		retainTempfiles = ExcelHolder.getKey( map, "RetainTempfiles" );
	}
}
