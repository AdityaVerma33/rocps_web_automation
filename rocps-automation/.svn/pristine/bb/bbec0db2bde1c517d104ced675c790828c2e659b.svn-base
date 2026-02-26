package com.subex.rocps.automation.helpers.application.accruals.accountingperioddefinition;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.RadioHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class AccountingPeriodDefnDetailImpl extends PSAcceptanceTest
{

	protected String partition;
	protected String name;
	protected String frequency;
	protected String firstPeriodDate;
	protected String anniversaryAlign;
	protected String alignUsing;
	protected String dayOfMonthWeek;
	protected String alignmentDate;
	protected String openPeriodDays;
	protected String referenceTable;
	protected String[] referenceTableArr;

	protected Map<String, String> map;
	PSStringUtils strObj = new PSStringUtils();
	PSGenericHelper genericObj = new PSGenericHelper();

	public AccountingPeriodDefnDetailImpl( Map<String, String> map ) throws Exception
	{
		this.map = map;
		initialiseVaribles( map );

	}

	public void newAccountingPeriodDefn() throws Exception
	{
		genericObj.clickNewAction( partition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertTrue( ElementHelper.isElementPresent( "//div[text()='Accounting  Period  Frequency']" ), "Detail Page is not loaded" );
	}

	public void accountingPeriodFrequency() throws Exception
	{
		TextBoxHelper.type( "papdName", name );

		ComboBoxHelper.select( "freqComponent_gwt_uid_", frequency );
		TextBoxHelper.type( "papdStartDttm", firstPeriodDate );
		if ( ValidationHelper.isTrue( anniversaryAlign ) )
			RadioHelper.click( "frequencyRadioGroupPivotOption1_InputElement" );
		if ( ValidationHelper.isTrue( alignUsing ) )
		{
			RadioHelper.click( "frequencyRadioGroupPivotOption2_InputElement" );
			ComboBoxHelper.select( "dayOfMonth_gwt_uid_", dayOfMonthWeek );
			String valArr[] = ComboBoxHelper.getAllValues( "alignmentDate_gwt_uid_" );
			alignmentDate = valArr[1];			
			ComboBoxHelper.select( "alignmentDate_gwt_uid_", alignmentDate );
		}

		ComboBoxHelper.select( "papdOpenPrdPriorDays_gwt_uid_", openPeriodDays );

	}

	public void apdCategories() throws Exception
	{
		String[] refTableArr = strObj.stringSplitFirstLevel( referenceTable );
		for ( int row = 0; row < refTableArr.length; row++ )
		{
			int rowNo = GridHelper.getRowNumber( "accPeriodDfnEntityGrid", refTableArr[row], "Reference Table" );
			if ( rowNo == 0 )
			{
				ButtonHelper.click( "accPeriodDfnEntityGridToolbar.add" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GridHelper.updateGridComboBox( "accPeriodDfnEntityGrid", "refCombo_gwt_uid_", row + 1, "Reference Table", refTableArr[row] );
			}
		}
	}

	public void saveAcountingPeriodDefn() throws Exception
	{
		ButtonHelper.click( "accountingPeriodDfnDetail.save" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	public void editAccountingPeriodFrequency() throws Exception
	{
		TextBoxHelper.type( "papdName", name );
		if ( ValidationHelper.isNotEmpty( frequency ) )
			ComboBoxHelper.select( "freqComponent_gwt_uid_", frequency );
		if ( ValidationHelper.isNotEmpty( firstPeriodDate ) )
			TextBoxHelper.type( "papdStartDttm", firstPeriodDate );
		if ( ValidationHelper.isTrue( anniversaryAlign ) && !RadioHelper.isChecked( "frequencyRadioGroupPivotOption1_InputElement" ) )
			RadioHelper.click( "frequencyRadioGroupPivotOption1_InputElement" );
		if ( ValidationHelper.isTrue( alignUsing ) )
		{
			if ( !RadioHelper.isChecked( "frequencyRadioGroupPivotOption2_InputElement" ) )
				RadioHelper.click( "frequencyRadioGroupPivotOption2_InputElement" );
			if ( ValidationHelper.isNotEmpty( dayOfMonthWeek ) )
				ComboBoxHelper.select( "dayOfMonth_gwt_uid_", dayOfMonthWeek );
			if ( ValidationHelper.isNotEmpty( alignmentDate ) )
				ComboBoxHelper.select( "alignmentDate_gwt_uid_", alignmentDate );
		}
		if ( ValidationHelper.isNotEmpty( openPeriodDays ) )
			ComboBoxHelper.select( "papdOpenPrdPriorDays_gwt_uid_", openPeriodDays );

	}

	public void initialiseVaribles( Map<String, String> map ) throws Exception
	{
		partition = ExcelHolder.getKey( map, "Partition" );
		name = ExcelHolder.getKey( map, "Name" );
		frequency = ExcelHolder.getKey( map, "Frequency" );
		firstPeriodDate = ExcelHolder.getKey( map, "FirstPeriodDate" );
		anniversaryAlign = ExcelHolder.getKey( map, "AnniversaryAlign" );
		alignUsing = ExcelHolder.getKey( map, "AlignUsing" );
		dayOfMonthWeek = ExcelHolder.getKey( map, "DayOfMonthWeek" );
		alignmentDate = ExcelHolder.getKey( map, "AlignmentDate" );
		openPeriodDays = ExcelHolder.getKey( map, "OpenPeriodDays" );
		referenceTable = ExcelHolder.getKey( map, "ReferenceTable" );
	}

}
