package com.subex.rocps.automation.helpers.application.exchangeRates.imfExcRateImport;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;

public class ImfExchRateImportActImpl extends PSAcceptanceTest
{
	protected PSGenericHelper psgenericHelperobj = new PSGenericHelper();
	PSActionImpl psActionImpl = new PSActionImpl();

	/* This method is used to click on  action '*/
	public void clickOnExchangeRatesAction( Map<String, String> imfExchRateImportMap ) throws Exception
	{
		psActionImpl.clickOnAction( "View", "Exchange Rates", "PS_Detail_imfExchRateImp_exchRateAction_detailXpath" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		String mapkeys = ExcelHolder.getKey( imfExchRateImportMap, "MapRowKeys" );
		String colmHdrsRes = ExcelHolder.getKey( imfExchRateImportMap, "ColmnHeaders" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.sortGrid( "PS_Detail_imfExchRateImp_exchRateAction_gridId", "From Currency ISO Code" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psgenericHelperobj.validateSearchResult( colmHdrsRes, mapkeys, imfExchRateImportMap, "PS_Detail_PopupScreen_ColumnHeaderID", "PS_Detail_imfExchRateImp_exchRateAction_gridId" );
		Log4jHelper.logInfo( "'IMF Exchange Rate Import - Exchange Rates action' results are validated successfully" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.clickIfEnabled( "PS_Detail_imfExchRateImp_exchRateAction_cancel_BtnId" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_imfExchRateImp_exchRateAction_detailXpath" ), searchScreenWaitSec );
		psgenericHelperobj.waitforHeaderElement( "Task Status" );

	}
}
