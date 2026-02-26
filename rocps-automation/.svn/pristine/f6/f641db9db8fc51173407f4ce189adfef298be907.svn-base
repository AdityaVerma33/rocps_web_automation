package com.subex.rocps.automation.helpers.application.tariffs.ratesheetimportrequest;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TariffValidationImpl extends PSAcceptanceTest
{
	Map<String, String> map = null;
	String tariffName;
	String tariffClassName;
	String deletedBands;
	String tariffcolHeaders;
	String tariffresults;
	String effectiveDate;
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();
	PSGenericHelper genericHelperObj = new PSGenericHelper();
	PSActionImpl psActionImpl=new PSActionImpl();
	String regex = new PSStringUtils().regexFirstLevelDelimeter();
	PSStringUtils psStringObj = new PSStringUtils();
	String tooltip;

	public TariffValidationImpl( Map<String, String> map ) throws Exception
	{
		this.map = map;
		initializeVariablesTariff( map );

	}

	public boolean filterOperation() throws Exception
	{
		boolean flag = false;
		SearchGridHelper.searchWithTextBox( "Tariff_Name", tariffName, "Tariff" );
		if ( ValidationHelper.isNotEmpty( tariffClassName ) )
			SearchGridHelper.searchWithComboBox( "tariffClass_gwt_uid_", tariffClassName, "Tariff Class" );
		int row = GridHelper.getRowCount( "SearchGrid", tariffName, "Tariff" );
		if ( row > 0 )
			flag = true;
		return flag;

	}

	public void tariffValidation() throws Exception
	{
		if ( filterOperation() )
		{
			GridHelper.clickRow( "SearchGrid", tariffName, "Tariff" );
			openFastentryAction();

			dataVerifyObj.validateDataWithoutSorting( "FastEntry_Grid","grid_column_header_undefined_", map, tariffcolHeaders, tariffresults, false );
		}
		else
			Log4jHelper.logInfo( "Tariff is not avaiable " );
	}

	public void deleteBandVerification() throws Exception
	{
		String[] deletedBandArr = deletedBands.split( regex, -1 );
		if ( ValidationHelper.isNotEmpty( deletedBands ) )
		{
			for ( int i = 0; i < deletedBandArr.length; i++ )
			{
				TextBoxHelper.type( "FastEntry_BandName", deletedBandArr[i] );
				ButtonHelper.click( "FastEntry_Search" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				int row = GridHelper.getRowNumber( "FastEntry_Grid", deletedBandArr[i], "Band Name" );
				if ( row == 0 )
					Log4jHelper.logInfo( "Specified Band is Deleted" );
				else
					FailureHelper.failTest( "Specified band is available for this tariff" + tariffName );
			}
		}
	}

	public void openFastentryAction() throws Exception
	{
		psActionImpl.clickOnAction( "Tariff Actions", "Open Fast Entry" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		if ( ValidationHelper.isNotEmpty( effectiveDate ) )
		{
			assertEquals( NavigationHelper.getScreenTitle(), "Open Fast Entry at Date" );
			TextBoxHelper.type( "PS_Detail_Amtthreshold_effectiveDate_txtID", effectiveDate );
			ButtonHelper.click( "fastEntryEffectiveDateSelectorDetail.OK" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
		}
		assertEquals( NavigationHelper.getScreenTitle(), "Edit Fast Entry" );
	}

	public void closeTariffFastentry() throws Exception
	{
		ButtonHelper.click( "fastEntryDetail.cancel" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "Tariff Search" );
	}

	public void initializeVariablesTariff( Map<String, String> map ) throws Exception
	{
		tariffName = ExcelHolder.getKey( map, "TariffName" );
		tariffClassName = ExcelHolder.getKey( map, "TariffClassName" );
		deletedBands = ExcelHolder.getKey( map, "DeletedBands" );
		tariffcolHeaders = ExcelHolder.getKey( map, "TariffColHeaders" );
		tariffresults = ExcelHolder.getKey( map, "TariffResults" );
		effectiveDate = ExcelHolder.getKey( map, "EffectiveDate" );

	}

}
