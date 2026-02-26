package com.subex.rocps.automation.helpers.application.networkConfiguraiton.route;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;

public class RouteDetailImpl extends PSAcceptanceTest
{
	protected String clientPartition;
	protected String name;
	protected String routeGrp;
	protected String switchName;
	protected String matchString;
	protected String routeType;
	protected String routeClass;
	protected String direction;
	protected String validFrom;
	protected String validTo;
	protected String isInternalRoute;
	protected String elementName;
	protected String aNoRuleString;
	protected String bNoRuleString;
	protected String cNoRuleString;
	protected String matchStringDetail;
	protected String directionDetail;
	protected String continueDetail;
	protected String signallingType;
	protected String termCode;
	protected String totalCircuits;
	protected String usedCircuits;
	protected String priority;
	protected String remarks;
	protected String traffictypeMap;
	protected String active;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	PSStringUtils strObj = new PSStringUtils();
	protected Map<String, String> routeMap = null;
	DataSelectionHelper dataSelectionHelper = new DataSelectionHelper();

	public RouteDetailImpl( Map<String, String> map ) throws Exception
	{
		this.routeMap = map;
		initializeVariables();

	}

	/*
	 * This method is for basic Details Config of Route
	 */
	public void basicDetailsConfig() throws Exception
	{
		try
		{
			TextBoxHelper.type( "PS_Detail_Route_NameDetail_txtID", name );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			genericHelperObj.waitforEntityElement();
			PSEntityComboHelper.selectUsingGridFilterTextBox( "routeGroup", "Route Group Search", "pergName", routeGrp, "Name" );
			ComboBoxHelper.select( "PS_Detail_Route_Switch_comboID", switchName );
			TextBoxHelper.type( "PS_Detail_Route_MatchString_comboID", matchString );
			ComboBoxHelper.select( "PS_Detail_Route_RouteType_comboID", routeType );
			ComboBoxHelper.select( "PS_Detail_Route_RouteClass_comboID", routeClass );
			ComboBoxHelper.select( "PS_Detail_Route_Direction_comboID", direction );
			TextBoxHelper.type( "PS_Detail_Route_FromDate_txtID", validFrom );
			TextBoxHelper.type( "PS_Detail_Route_ToDate_txtID", validTo );

			if ( ValidationHelper.isTrue( isInternalRoute ) )
			{
				CheckBoxHelper.check( "PS_Detail_Route_InternalRoute_chkBX" );
			}
		}
		catch ( Exception e )
		{
			throw e;
		}
	}

	/*
	 * This method is for routing Details Tab
	 */
	public void routingDetailsTab() throws Exception
	{

		TabHelper.gotoTab( "PS_Detail_RoutingDetails_Tab" );
		GenericHelper.waitTime( 3, "Loading tab for Routing details" );
		//GenericHelper.waitForLoadmask( searchScreenWaitSec );

		ComboBoxHelper.select( "PS_Detail_Route_signallingType_comboID", signallingType );
		TextBoxHelper.type( "PS_Detail_Route_TermCode_txtID", termCode );
		TextBoxHelper.type( "PS_Detail_Route_TotalCircuits_txtID", totalCircuits );
		TextBoxHelper.type( "PS_Detail_Route_UsedCircuits_txtID", usedCircuits );
		TextBoxHelper.type( "PS_Detail_Route_Priority_txtID", priority );
		TextBoxHelper.type( "PS_Detail_Route_Remarks_txtID", remarks );

		if ( ValidationHelper.isTrue( active ) )
		{
			CheckBoxHelper.check( "PS_Detail_Route_active_chkbx" );
		}
		genericHelperObj.dualListSelection( traffictypeMap );

	}

	/*
	 * This method is for element association
	 */

	public void elementAssociation() throws Exception
	{
		if ( ValidationHelper.isNotEmpty( elementName ) )
		{
			String[] elementsArr = strObj.stringSplitFirstLevel( elementName );
			for ( int row = 0; row < elementsArr.length; row++ )
			{
				int rowno = GridHelper.getRowNumber( "elementGrid", elementsArr[row], 2 );
				if ( rowno == 0 )
				{
					ButtonHelper.click( "toolbar-button-label-elementGridToolBar.Add" );
					GenericHelper.waitForLoadmask( detailScreenWaitSec );
					dataSelectionHelper.elementSelection( elementsArr[row] );
					GenericHelper.waitForLoadmask( detailScreenWaitSec );
				}
				else
					assertEquals( GridHelper.getCellValue( "elementGrid", row + 1, 2 ), elementsArr[row] );

			}

		}
	}

	/*
	 * This method is for rule string set config
	 */
	public void routeRuleStringSet() throws Exception
	{
		if ( ValidationHelper.isNotEmpty( aNoRuleString ) || ValidationHelper.isNotEmpty( bNoRuleString ) || ValidationHelper.isNotEmpty( cNoRuleString ) )
		{
			String[] aNoRuleStringArr = strObj.stringSplitFirstLevel( aNoRuleString );
			String[] bNoRuleStringArr = strObj.stringSplitFirstLevel( bNoRuleString );
			String[] cNoRuleStringArr = strObj.stringSplitFirstLevel( cNoRuleString );
			String[] matchStringArr = strObj.stringSplitFirstLevel( matchStringDetail );
			String[] directionArr = strObj.stringSplitFirstLevel( directionDetail );
			String[] continueArr = strObj.stringSplitFirstLevel( continueDetail );

			for ( int row = 0; row < directionArr.length; row++ )
			{
				int rowno = GridHelper.getRowNumber( "ruleStringSetGrid", aNoRuleStringArr[row], 1 );
				if ( rowno == 0 )
				{
					ButtonHelper.click( "toolbar-button-label-ruleStringSetToolBar.Add" );
					GenericHelper.waitForLoadmask( detailScreenWaitSec );
					GridHelper.updateGridComboBox( "ruleStringSetGrid", "anoRuleStringSetEditor_gwt_uid_", row + 1, "A  No  Rule String", aNoRuleStringArr[row] );
					GridHelper.updateGridComboBox( "ruleStringSetGrid", "bnoRuleStringSetEditor_gwt_uid_", row + 1, "B  No  Rule String", bNoRuleStringArr[row] );
					GridHelper.updateGridComboBox( "ruleStringSetGrid", "cnoRuleStringSetEditor_gwt_uid_", row + 1, "C  No  Rule String", cNoRuleStringArr[row] );

					GridHelper.updateGridTextBox( "ruleStringSetGrid", "rrsMatchStrEditor", row + 1, "Match String", matchStringArr[row] );
					GridHelper.updateGridComboBox( "ruleStringSetGrid", "rrSDirectionCdEditor_gwt_uid_", row + 1, "Direction", directionArr[row] );
					if ( ValidationHelper.isNotEmpty( continueArr[row] ) && ValidationHelper.isTrue( continueArr[row] ) )
						GridHelper.updateGridCheckBox( "ruleStringSetGrid", row + 1, "Continue", continueArr[row] );
				}
				else
					assertEquals( GridHelper.getCellValue( "ruleStringSetGrid", row + 1, 1 ), aNoRuleStringArr[row] );

			}

		}
	}

	/*
	 * This method is for edit basic Details Config of Route
	 */
	public void editBasicDetailsConfig() throws Exception
	{
		try
		{

			TextBoxHelper.type( "PS_Detail_Route_NameDetail_txtID", name );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			if ( ValidationHelper.isNotEmpty( routeGrp ) )
			{
				genericHelperObj.waitforEntityElement();
				PSEntityComboHelper.selectUsingGridFilterTextBox( "routeGroup", "Route Group Search", "pergName", routeGrp, "Name" );
			}
			if ( ValidationHelper.isNotEmpty( switchName ) )
				ComboBoxHelper.select( "PS_Detail_Route_Switch_comboID", switchName );
			if ( ValidationHelper.isNotEmpty( matchString ) )
				TextBoxHelper.type( "PS_Detail_Route_MatchString_comboID", matchString );
			if ( ValidationHelper.isNotEmpty( routeType ) )
				ComboBoxHelper.select( "PS_Detail_Route_RouteType_comboID", routeType );
			if ( ValidationHelper.isNotEmpty( routeClass ) )
				ComboBoxHelper.select( "PS_Detail_Route_RouteClass_comboID", routeClass );
			if ( ValidationHelper.isNotEmpty( direction ) )
				ComboBoxHelper.select( "PS_Detail_Route_Direction_comboID", direction );
			TextBoxHelper.type( "PS_Detail_Route_FromDate_txtID", validFrom );
			TextBoxHelper.type( "PS_Detail_Route_ToDate_txtID", validTo );

			if ( ValidationHelper.isTrue( isInternalRoute ) )
			{
				CheckBoxHelper.check( "PS_Detail_Route_InternalRoute_chkBX" );
			}
		}
		catch ( Exception e )
		{
			throw e;
		}
	}

	/*
	 * This method is for routing Details Tab
	 */
	public void editRoutingDetailsTab() throws Exception
	{

		TabHelper.gotoTab( "PS_Detail_RoutingDetails_Tab" );
		GenericHelper.waitTime( searchScreenWaitSec, "Loading tab for Routing details" );
		if ( ValidationHelper.isNotEmpty( signallingType ) )
			ComboBoxHelper.select( "PS_Detail_Route_signallingType_comboID", signallingType );
		TextBoxHelper.type( "PS_Detail_Route_TermCode_txtID", termCode );
		TextBoxHelper.type( "PS_Detail_Route_TotalCircuits_txtID", totalCircuits );
		TextBoxHelper.type( "PS_Detail_Route_UsedCircuits_txtID", usedCircuits );
		TextBoxHelper.type( "PS_Detail_Route_Priority_txtID", priority );
		TextBoxHelper.type( "PS_Detail_Route_Remarks_txtID", remarks );

		if ( ValidationHelper.isTrue( active ) )
		{
			CheckBoxHelper.check( "PS_Detail_Route_active_chkbx" );
		}
		int row = GridHelper.getRowNumber( "availableValuesList", traffictypeMap, 1 );
		if ( row != 0 )
			genericHelperObj.dualListSelection( traffictypeMap );
		else
			Log4jHelper.logInfo( "Value is not present in available value list" );

	}

	/*
	 * This method is to initialize instance variables
	 */
	protected void initializeVariables() throws Exception
	{

		clientPartition = routeMap.get( "Partition" );
		name = routeMap.get( "Name" );
		routeGrp = routeMap.get( "RouteGroup" );
		switchName = routeMap.get( "Switch" );
		matchString = routeMap.get( "MatchString" );
		routeType = routeMap.get( "RouteType" );
		routeClass = routeMap.get( "RouteClass" );
		direction = routeMap.get( "Direction" );
		validFrom = routeMap.get( "ValidFrom" );
		validTo = routeMap.get( "ValidTo" );
		isInternalRoute = routeMap.get( "IsInternalRoute" );
		elementName = routeMap.get( "ElementName" );
		aNoRuleString = routeMap.get( "ANoRuleString" );
		bNoRuleString = routeMap.get( "BNoRuleString" );
		cNoRuleString = routeMap.get( "CNoRuleString" );
		matchStringDetail = routeMap.get( "MatchStringDetail" );
		directionDetail = routeMap.get( "DirectionDetail" );
		continueDetail = routeMap.get( "ContinueDetail" );
		signallingType = routeMap.get( "SignallingType" );
		termCode = routeMap.get( "TermCode" );
		totalCircuits = routeMap.get( "TotalCircuits" );
		usedCircuits = routeMap.get( "UsedCircuits" );
		priority = routeMap.get( "Priority" );
		remarks = routeMap.get( "Remarks" );
		traffictypeMap = routeMap.get( "TrafficTypeMap" );
		active = routeMap.get( "Active" );
	}
}
