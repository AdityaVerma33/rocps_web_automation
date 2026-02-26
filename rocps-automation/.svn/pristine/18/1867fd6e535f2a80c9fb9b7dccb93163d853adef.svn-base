package com.subex.rocps.automation.helpers.application.admin.alertEvent;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.AlertsHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.RadioHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.componentHelpers.RadioElementHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.utils.PSStringUtils;

public class AlertDetailsImpl
{
	PSGenericHelper genObj = new PSGenericHelper();
	PSStringUtils stringObj = new PSStringUtils();
	PSDataComponentHelper compObj = new PSDataComponentHelper();
	AlertsHelper alertObj = new AlertsHelper();

	public void headerDetails( String name, String group ) throws Exception
	{
		TextBoxHelper.type( "PS_Detail_AlertEvent_AlertEventName", name );
		ComboBoxHelper.select( "PS_Detail_AlertEvent_AlertGroup", group );
	}

	public void entityDetails( String entity, String definition ) throws Exception
	{
		PSEntityComboHelper.selectUsingGridFilterTextBox( "PS_Detail_AlertEvent_Entity", "Entity Search", "entEntity", entity, "Entity" );
		ComboBoxHelper.select( "PS_Detail_AlertEvent_Dfn", definition );
	}

	public void priorities( String priority ) throws Exception
	{
		if ( priority.trim().equalsIgnoreCase( "high" ) )
		{
			RadioHelper.click( "PS_Detail_AlertEvent_PriorityHigh" );
			RadioHelper.click( "PS_Detail_AlertEvent_PriorityHigh" );
		}
		else if ( priority.trim().equalsIgnoreCase( "medium" ) )
		{
			RadioHelper.click( "PS_Detail_AlertEvent_PriorityMedium" );
			RadioHelper.click( "PS_Detail_AlertEvent_PriorityMedium" );
		}
		else if ( priority.trim().equalsIgnoreCase( "low" ) )
		{
			RadioHelper.click( "PS_Detail_AlertEvent_PriorityLow" );
			RadioHelper.click( "PS_Detail_AlertEvent_PriorityLow" );
		}
	}

	public void alertEventProperties( String active, String mobileApp, String alertEmailLevel, String systemAlertLevel ) throws Exception
	{
		{
			if ( ValidationHelper.isTrue( active ) )
				CheckBoxHelper.check( "PS_Detail_AlertEvent_ActiveFlag" );
			else if ( ValidationHelper.isTrue( mobileApp ) )
				CheckBoxHelper.check( "PS_Detail_AlertEvent_SendMobile" );
			else if ( ValidationHelper.isTrue( alertEmailLevel ) )
				CheckBoxHelper.check( "PS_Detail_AlertEvent_alertEmail" );
			else if ( ValidationHelper.isTrue( systemAlertLevel ) )
				CheckBoxHelper.check( "PS_Detail_AlertEvent_emailSystem" );
		}

	}

	public void alertEventStream( String stream, String order ) throws Exception
	{
		ComboBoxHelper.select( "PS_Detail_AlertEvent_stream", stream );
		TextBoxHelper.type( "PS_Detail_AlertEvent_order", order );
	}

	public void save(String value, String colHeader) throws Exception
	{
		genObj.detailSave( "PS_Detail_entityAlert_save", value, colHeader );
		GenericHelper.waitForLoadmask();

	}

	public void validateAlertGeneration() throws Exception
	{
		NavigationHelper.navigateToScreen( "Alerts" );
	}

}
