package com.subex.rocps.automation.helpers.application.deal.deal;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.sun.jna.platform.win32.COM.util.annotation.ComObject;

public class DealTierConfiguration extends PSAcceptanceTest
{
	protected String fromMinutes;
	protected String toMinutes;
	protected String commitment;
	protected String bestEffort;
	protected String baseTier;
	protected String backToNthTier;
	protected String tiersPerBandGroup;
	protected String bandGroup;
	protected String[] fromMinutesArr;
	protected String[] toMinutesArr;
	protected String[] commitmentArr;
	protected String[] bestEffortArr;
	protected String[] baseTierArr;
	protected String[] backToNthTierArr;
	protected String[] bandGroupArr;
	protected Map<String, String> outMap = new HashMap<String, String>();
	protected Map<String, String> inMap = new HashMap<String, String>();
	protected Map<String, String> map = null;
	PSStringUtils strObj = new PSStringUtils();
	PSGenericHelper genericObj = new PSGenericHelper();

	public DealTierConfiguration( Map<String, String> map ) throws Exception
	{
		this.map = map;
		initializeVariblesIncoming( map );
		initializeArray();
		orPropertiesMap();
	}

	public void tierConfiguration( String tierTestcase ) throws Exception
	{
		if ( tierTestcase.contains( "Incoming" ) )
			tierConfigDetail( inMap );
		if ( tierTestcase.contains( "Outgoing" ) )
			tierConfigDetail( outMap );

	}

	/*
	 * This method is for tier config detail grid
	 */
	private void tierConfigDetail( Map<String, String> map ) throws Exception
	{
		if ( ValidationHelper.isTrue( tiersPerBandGroup ) )
		{
			CheckBoxHelper.check( map.get( "TierPerBandGroup" ) );

			if ( PopupHelper.isTextPresent( "All tiers and caps will be deleted. Do you wish to continue?" ) )
				ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			ButtonHelper.click( map.get( "TierBtnID" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			ElementHelper.waitForElement( "//div[@id='dealTierConfigurationDetail.window']//*[contains(text(),'Sequence')]", searchScreenWaitSec );
			for ( int i = 0; i < bandGroupArr.length; i++ )
			{
				ComboBoxHelper.select( map.get( "BandGroup" ), bandGroupArr[i] );
				if ( !fromMinutes.isEmpty() || !toMinutes.isEmpty() || !commitment.isEmpty() || !bestEffort.isEmpty() || !backToNthTier.isEmpty() )
					dealTier( map, fromMinutesArr[i], toMinutesArr[i], commitmentArr[i], bestEffortArr[i], backToNthTierArr[i] );
			}
			ButtonHelper.click( "dealTierConfigurationDetail.oK" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );

		}
		else
		{
			ButtonHelper.click( map.get( "TierBtnID" ) );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			dealTier( map, fromMinutes, toMinutes, commitment, bestEffort, backToNthTier );
			ButtonHelper.click( "dealTierConfigurationDetail.oK" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}
	}

	/*
	 * This method is for deal Tier	
	 */
	private void dealTier( Map<String, String> map, String fromMinute, String toMinute, String commitments, String bestEfforts, String backToNthTiers ) throws Exception
	{
		String[] fromMinAr = fromMinute.split( secondLevelDelimiter );
		String[] toMinAr = toMinute.split( secondLevelDelimiter );
		String[] commitmentAr = commitments.split( secondLevelDelimiter );
		String[] bestEffortAr = bestEfforts.split( secondLevelDelimiter );
		String[] backToNthTierAr = backToNthTiers.split( secondLevelDelimiter );

		for ( int i = 0; i < fromMinAr.length; i++ )
		{
			//if(ValidationHelper.isNotEmpty( fromMinutes ) && ValidationHelper.isNotEmpty( fromMinute )  && ValidationHelper.isNotEmpty( fromMinAr[i] ))
			//	GridHelper.updateGridTextBox(map.get( "TierGridId" ), "fromMinuteEditor", i + 1, "From Minute", fromMinAr[i] );
			if ( ValidationHelper.isNotEmpty( toMinute ) && ValidationHelper.isNotEmpty( toMinAr[i] ) )
				clickGridTextBox( map.get( "TierGridId" ), "toMinuteEditor", i + 1, "To Minute", toMinAr[i] );
			if ( ValidationHelper.isNotEmpty( commitments ) && ValidationHelper.isNotEmpty( commitmentAr[i] ) && ValidationHelper.isTrue( commitmentAr[i] ) )
				GridHelper.updateGridCheckBox( map.get( "TierGridId" ), i + 1, "Commitment", commitmentAr[i] );
			if ( ValidationHelper.isNotEmpty( bestEfforts ) && ValidationHelper.isNotEmpty( bestEffortAr[i] ) && ValidationHelper.isTrue( bestEffortAr[i] ) )
				GridHelper.updateGridCheckBox( map.get( "TierGridId" ), i + 1, "Best Effort", bestEffortAr[i] );
			if ( ValidationHelper.isNotEmpty( backToNthTiers ) && ValidationHelper.isNotEmpty( backToNthTierAr[i] ) )
				GridHelper.updateGridCheckBox( map.get( "TierGridId" ), i + 1, "Back To Nth Tier", backToNthTierAr[i] );

		}
	}

	/*
	 * This method is to click textbox in grid
	 */
	public void clickGridTextBox( String gridId, String textBoxId, int rowNum, String columnName, String value ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( value ) )
		{
			GridHelper.clickRow( gridId, rowNum, columnName );
			if ( !TextBoxHelper.isPresent( textBoxId ) )
				GridHelper.clickRow( gridId, rowNum, columnName );
			/*driver.findElement( By.xpath( "//*[@id='tierConfigurationGrid']//table/tbody/tr[1]/td[4]" )).clear();
			WebElement element = driver.findElement( By.xpath( "//*[@id='tierConfigurationGrid']//table/tbody/tr[1]/td[4]" ) );
			//	ElementHelper.clear( element );
			Actions action = new Actions(driver);
			action.click(element).sendKeys(Keys.BACK_SPACE).build();
			action.perform();*/
		
		/*actions.click(driver.findElement(By.id( "//*[@id='toMinuteEditor']" )));
		   
		 actions.sendKeys(Keys.BACK_SPACE).build().perform(); */
		
		/*driver.findElement( By.id( "toMinuteEditor" ) ).sendKeys( value );*/
			TextBoxHelper.type( textBoxId, value );
			GridHelper.click( gridId );
		}
	}

	public void orPropertiesMap()
	{
		outMap.put( "TierBtnID", "outTierConfiguration" );
		outMap.put( "TierGridId", "tierConfigurationGrid" );
		outMap.put( "TierPerBandGroup", "outPdelTierPerBandGroupFl_InputElement" );
		outMap.put( "BandGroup", "dealBandGroup_gwt_uid_" );

		inMap.put( "TierBtnID", "inTierConfiguration" );
		inMap.put( "TierGridId", "tierConfigurationGrid" );
		inMap.put( "TierPerBandGroup", "pdelTierPerBandGroupFl_InputElement" );
		inMap.put( "BandGroup", "dealBandGroup_gwt_uid_" );
	

	}

	/*
	 * This metod is to initialize array variables
	 */
	public void initializeArray() throws Exception
	{
		bandGroupArr = strObj.stringSplitFirstLevel( bandGroup );
		fromMinutesArr = strObj.stringSplitFirstLevel( fromMinutes );
		toMinutesArr = strObj.stringSplitFirstLevel( toMinutes );
		commitmentArr = strObj.stringSplitFirstLevel( commitment );
		bestEffortArr = strObj.stringSplitFirstLevel( bestEffort );
		baseTierArr = strObj.stringSplitFirstLevel( baseTier );
		backToNthTierArr = strObj.stringSplitFirstLevel( backToNthTier );
	}

	/*
	 * This method is to initialize instance variables
	 */
	public void initializeVariblesIncoming( Map<String, String> map ) throws Exception
	{

		bandGroup = ExcelHolder.getKey( map, "BandGroup" );
		tiersPerBandGroup = ExcelHolder.getKey( map, "TiersPerBandGroup" );
		fromMinutes = ExcelHolder.getKey( map, "FromMinutes" );
		toMinutes = ExcelHolder.getKey( map, "ToMinutes" );
		commitment = ExcelHolder.getKey( map, "Commitment" );
		bestEffort = ExcelHolder.getKey( map, "BestEffort" );
		baseTier = ExcelHolder.getKey( map, "BaseTier" );
		backToNthTier = ExcelHolder.getKey( map, "BackToNthTier" );
	}

}
