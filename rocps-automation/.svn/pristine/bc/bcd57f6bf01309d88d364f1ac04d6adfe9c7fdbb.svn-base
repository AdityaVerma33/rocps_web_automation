package com.subex.rocps.automation.helpers.application.matchandrate;

import org.testng.Assert;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.customexception.StringLengthException;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class PreRatingMatchRuleDetailImpl extends PSAcceptanceTest
{
	protected String name;
	protected String fromDate;
	protected String toDate;
	protected String matchRuleGroup;
	protected String ratingFrom;
	protected String ratingTo;
	protected String tariffFromDate;
	protected String tariffs;
	protected String identifierDefn;
	protected String identifierValue;
	Map<String, String> map;
	protected PSGenericHelper genHelperObj = new PSGenericHelper();
	PSStringUtils strUtilObj = new PSStringUtils();

	public PreRatingMatchRuleDetailImpl( Map<String, String> map ) throws Exception
	{

		this.map = map;
		initialiseVariables( map );

	}

	private void initialiseVariables( Map<String, String> map ) throws Exception
	{
		name = ExcelHolder.getKey( map, "Name" );
		fromDate = ExcelHolder.getKey( map, "FromDate" );
		toDate = ExcelHolder.getKey( map, "ToDate" );
		matchRuleGroup = ExcelHolder.getKey( map, "MatchRuleGroup" );
		ratingFrom = ExcelHolder.getKey( map, "RatingFrom" );
		ratingTo = ExcelHolder.getKey( map, "RatingTo" );
		tariffFromDate = ExcelHolder.getKey( map, "TariffFromDate" );
		tariffs = ExcelHolder.getKey( map, "Tariff" );
		identifierDefn = ExcelHolder.getKey( map, "IdentifierDefnition" );
		identifierValue = ExcelHolder.getKey( map, "IdentifierValue" );

	}

	public void preRatingMatchRuleBasicDetails() throws Exception
	{

		String screenTitle = NavigationHelper.getScreenTitle();
		Assert.assertEquals( screenTitle, "New Pre-rating Match Rule" );

		TextBoxHelper.type( or.getProperty( "PS_Details_pmrNameTxtId" ), name );
		TextBoxHelper.type( or.getProperty( "PS_Details_pmrFromDateTxtId" ), fromDate );
		if ( !toDate.isEmpty() )
			TextBoxHelper.type( or.getProperty( "PS_Details_pmrToDateTxtId" ), toDate );
		String mrgColName = "Name";
		PSEntityComboHelper.selectUsingGridFilterTextBox( "PSDetail_emrgEntityId", "Event Match Rule Group Search", "PSDetail_emrName_txtId", matchRuleGroup, mrgColName );

		if ( !ratingFrom.isEmpty() )
			ComboBoxHelper.select( or.getProperty( "PS_Details_pmrRatingFromComboId" ), ratingFrom );

		ComboBoxHelper.select( or.getProperty( "PS_Details_pmrRatingToComboId" ), ratingTo );

	}

	public void addingMatchCriteriaValues() throws Exception
	{
		String[] ideDfnArr = strUtilObj.stringSplitFirstLevel( identifierDefn );
		String[] ideValArr = strUtilObj.stringSplitFirstLevel( identifierValue );

		if ( ideDfnArr.length != ideValArr.length )
		{
			throw new StringLengthException( "Identifier definitions and identifier values are not matching" );
		}

		for ( int i = 0; i < ideDfnArr.length; i++ )
		{
			genHelperObj.clickPropertyValueColumn( ideDfnArr[i] );
			ButtonHelper.click( "trigger-identifierValueEditor" );
			GenericHelper.waitForLoadmask();
			genHelperObj.criteriaValueSelectionWithValueGroups( ideValArr[i] );
		}
	}

	public void tariffTimeLineConfig() throws Exception
	{
		String tariffArr[] = strUtilObj.stringSplitFirstLevel( tariffs );
		String tariffFromDttmArr[] = strUtilObj.stringSplitFirstLevel( tariffFromDate );
		for ( int index = 0; index < tariffArr.length; index++ )
		{
			genHelperObj.timeLineNew();
			String serviceTariffDateLocator = GenericHelper.getORProperty( "TextBox_DatePicker" ).replace( "idOrName", or.getProperty( "PS_PopUp_pemrTariffFromDttm" ) );
			TextBoxHelper.type( serviceTariffDateLocator, tariffFromDttmArr[index] );
			String tffScrnTitle = "Tariff Search";

			tariffSelection( tffScrnTitle, tariffArr[index] );

		}

	}

	public void detailSave() throws Exception
	{
		/*ButtonHelper.click( "PS_Details_pmrSaveBtnId"  );
		GenericHelper.waitForLoadmask(searchScreenWaitSec);*/
		genHelperObj.detailSave( "PS_Details_pmrSaveBtnId", name, "Name" );
	}

	private void tariffSelection( String tffScrnTitle, String tariffsPerTariffTypeArr ) throws Exception
	{
		EntityComboHelper.clickEntityIcon( "PSPopUp_emrSvcTffEntSrchId" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertTrue( LabelHelper.isTitlePresent( tffScrnTitle ), "Entity Search '" + tffScrnTitle + "' did not appear." );
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		SearchGridHelper.searchWithTextBox( "PSPopUp_emrSvcTffNameTxtId", tariffsPerTariffTypeArr, "Tariff Name" );
		NavigationHelper.navigateToAction( "Expand/Collapse" );
		if ( NavigationHelper.isActionPresent( "Expand All" ) )
			NavigationHelper.navigateToAction( "Expand All" );
		int row = GridHelper.getRowNumber( "SearchGrid", tariffsPerTariffTypeArr, "Tariff Name" );
		GridHelper.clickRow( "SearchGrid", row, "Tariff Name" );
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "PS_PopUp_pmrTariffSave" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}
	
	public void editPreRatingMatchRuleBasicDetails() throws Exception
	{
		TextBoxHelper.type( or.getProperty( "PS_Details_pmrNameTxtId" ), name );
		if(ValidationHelper.isNotEmpty( fromDate ))
			TextBoxHelper.type( or.getProperty( "PS_Details_pmrFromDateTxtId" ), fromDate );
		if ( !toDate.isEmpty() )
			TextBoxHelper.type( or.getProperty( "PS_Details_pmrToDateTxtId" ), toDate );
		String mrgColName = "Name";
		assertEquals( EntityComboHelper.getValue( "PSDetail_emrgEntityId" ), matchRuleGroup );
		if ( !ratingFrom.isEmpty() )
			ComboBoxHelper.select( or.getProperty( "PS_Details_pmrRatingFromComboId" ), ratingFrom );
		if(ValidationHelper.isNotEmpty( ratingTo ))
			ComboBoxHelper.select( or.getProperty( "PS_Details_pmrRatingToComboId" ), ratingTo );
	}
	
	public void editAddingMatchCriteriaValues() throws Exception
	{
		String[] ideDfnArr = strUtilObj.stringSplitFirstLevel( identifierDefn );
		String[] ideValArr = strUtilObj.stringSplitFirstLevel( identifierValue );

		if ( ideDfnArr.length != ideValArr.length )
		{
			throw new StringLengthException( "Identifier definitions and identifier values are not matching" );
		}

		for ( int i = 0; i < ideDfnArr.length; i++ )
		{
			int row =GridHelper.getRowNumber( "preratingMatchCriteriaGrid", ideValArr[i], "Identifier Value" );
			if(row == 0)
			{
			genHelperObj.clickPropertyValueColumn( ideDfnArr[i] );
			ButtonHelper.click( "trigger-identifierValueEditor" );
			GenericHelper.waitForLoadmask();
			genHelperObj.criteriaValueSelectionWithValueGroups( ideValArr[i] );
			}
			else			
				assertEquals( GridHelper.getCellValue( "preratingMatchCriteriaGrid", row, "Identifier Value" ), ideValArr[i] );
		}
	}
	
	public void editTariffTimeLineConfig() throws Exception
	{
		String tariffArr[] = strUtilObj.stringSplitFirstLevel( tariffs );
		String tariffFromDttmArr[] = strUtilObj.stringSplitFirstLevel( tariffFromDate );
		for ( int index = 0; index < tariffArr.length; index++ )
		{
			genHelperObj.timeLineEdit();
			if(ValidationHelper.isNotEmpty( tariffFromDttmArr[index]  ))
			{
			String serviceTariffDateLocator = GenericHelper.getORProperty( "TextBox_DatePicker" ).replace( "idOrName", or.getProperty( "PS_PopUp_pemrTariffFromDttm" ) );
			TextBoxHelper.type( serviceTariffDateLocator, tariffFromDttmArr[index] );
			}
			String tffScrnTitle = "Tariff Search";
			String actualVal = EntityComboHelper.getValue( "trigger-tariff" );
			if(!actualVal.contains( tariffArr[index] ))
				tariffSelection( tffScrnTitle, tariffArr[index] );
			else
			{
				ButtonHelper.click( "PS_PopUp_pmrTariffSave" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			}

		}

	}
}
