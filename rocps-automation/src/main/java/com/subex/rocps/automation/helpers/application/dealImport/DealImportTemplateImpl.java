package com.subex.rocps.automation.helpers.application.dealImport;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

public class DealImportTemplateImpl extends PSAcceptanceTest
{

	PSStringUtils strObj = new PSStringUtils();
	PSGenericHelper genObj = new PSGenericHelper();

	public void headerDetails( String account, String fromDate, String toDate ) throws Exception
	{
		PSEntityComboHelper.selectUsingGridFilterTextBox( "PS_Detail_dealImportTemplate_account", "Account", "paccName", account, "Account Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		if ( !ValidationHelper.isEmpty( fromDate ) )
			TextBoxHelper.type( "PS_Detail_dealImportTemplate_fromDate", fromDate );
		if ( !ValidationHelper.isEmpty( toDate ) )
			TextBoxHelper.type( "PS_Detail_dealImportTemplate_toDate", toDate );
	}

	public void selectTrafficType( String trafficType ) throws Exception
	{

		String xpath = or.getProperty( "PS_Detail_dealImportTemplate_trafficType" );
		String selectXpath = or.getProperty( "PS_Detail_dealImportTemplate_trafficTypeSelect" );
		if ( !ValidationHelper.isEmpty( trafficType ) )
		{
			String[] trafficTypes = strObj.stringSplitFirstLevel( trafficType );
			for ( int i = 0; i < trafficTypes.length; i++ )
			{
				dualListSelection( trafficTypes[i], xpath, selectXpath );

			}
		}
	}

	public void selectElements( String elements ) throws Exception
	{

		String xpath = or.getProperty( "PS_Detail_dealImportTemplate_elementSet" );
		String selectXpath = or.getProperty( "PS_Detail_dealImportTemplate_elementSetSelect" );
		if ( !ValidationHelper.isEmpty( elements ) )
		{
			String[] elemntList = strObj.stringSplitFirstLevel( elements );
			for ( int i = 0; i < elemntList.length; i++ )
			{
				dualListSelection( elemntList[i], xpath, selectXpath );

			}
		}
	}

	public void selectBands( String bands ) throws Exception
	{

		String xpath = or.getProperty( "PS_Detail_dealImportTemplate_band" );
		String selectXpath = or.getProperty( "PS_Detail_dealImportTemplate_bandsSelect" );
		if ( !ValidationHelper.isEmpty( bands ) )
		{
			scrollToPanel( "Available Bands" );
			String[] bandList = strObj.stringSplitFirstLevel( bands );
			for ( int i = 0; i < bandList.length; i++ )
			{
				dualListSelection( bandList[i], xpath, selectXpath );

			}
		}
	}

	public void scrollToPanel( String panelName )
	{
		String xpath = "//*[@id='leftContainerLabel' and text()='" + panelName + "']";

		try
		{
			WebElement element = driver.findElement( By.xpath( xpath ) );
			( ( JavascriptExecutor ) driver ).executeScript( "arguments[0].scrollIntoView(true);", element );
		}
		catch ( StaleElementReferenceException e )
		{
			WebElement element = driver.findElement( By.xpath( xpath ) );
			( ( JavascriptExecutor ) driver ).executeScript( "arguments[0].scrollIntoView(true);", element );
		}

		finally
		{
			WebElement element = driver.findElement( By.xpath( xpath ) );
			( ( JavascriptExecutor ) driver ).executeScript( "arguments[0].scrollIntoView(true);", element );
		}

	}

	/*
	 * Method: for selecting the items in the dual list and pushing the items in the
	 * dual list
	 */
	public void dualListSelection( String dualListValue, String xpath, String selectButtonXpath ) throws Exception
	{

		String[] dualListValueArr = new PSStringUtils().stringSplitFirstLevel( dualListValue );
		for ( String str : dualListValueArr )
		{

			if ( dualListValueArr.length >= 1 )
			{

				Actions action = new Actions( driver );
				String locator = GenericHelper.getORProperty( xpath ).replace( "inputValues", str );
				if ( !ElementHelper.isElementPresent( locator ) )
					ElementHelper.waitForElement( locator, searchScreenWaitSec );
				WebElement element = driver.findElement( By.xpath( locator ) );
				ElementHelper.waitForClickableElement( element, searchScreenWaitSec );
				Thread.sleep( 3000 );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				action.moveToElement( element ).click().build().perform();
				MouseHelper.click( driver.findElement( By.xpath( selectButtonXpath ) ) );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );

			}
		}

	}

	public void clickOnSave( String value, String colHeader ) throws Exception
	{
		genObj.detailSave( "PS_Detail_dealImportTemplate_save", value, colHeader );
	}
}
