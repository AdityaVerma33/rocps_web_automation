package com.subex.rocps.automation.helpers.application.admin.alertEvent;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;

public class QueryBasedImpl extends AlertDetailsImpl
{

	public void newAlertEvent( String partition ) throws Exception
	{
		PSActionImpl actionObj = new PSActionImpl();
		actionObj.clickOnActionWithPartition( "Common Tasks", "New", "Query Based Alert Event" , partition );
		GenericHelper.waitForLoadmask();
	}

	public void alertEventProperties( String active, String mobileApp, String alertEmailLevel, String systemAlertLevel, String trendRequired, String trendValue, String repeatAlert ) throws Exception
	{
		if ( ValidationHelper.isTrue( active ) )
			CheckBoxHelper.check( "PS_Detail_AlertEvent_ActiveFlag" );
		if ( ValidationHelper.isTrue( mobileApp ) )
			CheckBoxHelper.check( "PS_Detail_AlertEvent_SendMobile" );
		if ( ValidationHelper.isTrue( alertEmailLevel ) )
			CheckBoxHelper.check( "PS_Detail_AlertEvent_alertEmail" );
		if ( ValidationHelper.isTrue( systemAlertLevel ) )
			CheckBoxHelper.check( "PS_Detail_AlertEvent_emailSystem" );
		if ( ValidationHelper.isTrue( repeatAlert ) )
			CheckBoxHelper.check( "PS_Detail_AlertEvent_repeat" );
		if ( ValidationHelper.isTrue( trendRequired ) )
		{
			CheckBoxHelper.check( "PS_Detail_AlertEvent_trendReq" );
			TextBoxHelper.type( "PS_Detail_AlertEvent_trendVal", trendValue );
		}

	}

	public void scheduleFrequency( String frequency, String dayOf, String alignmentDate ) throws Exception
	{
		if ( !ValidationHelper.isEmpty( frequency ) )
			ComboBoxHelper.select( "PS_Detail_AlertEvent_scheduleFreq", frequency );
		if ( !ValidationHelper.isEmpty( dayOf ) )
			ComboBoxHelper.select( "PS_Detail_AlertEvent_dayOf", dayOf );
		if ( !ValidationHelper.isEmpty( alignmentDate ) )
			ComboBoxHelper.select( "PS_Detail_AlertEvent_alignmentDate", alignmentDate );
	}

	public void save(String value, String colHeader) throws Exception
	{

		PSGenericHelper genObj = new PSGenericHelper();
		genObj.detailSave( "PS_Detail_query_save", value, colHeader );
	
	}

}
