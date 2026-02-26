package com.subex.rocps.automation.helpers.application.amountthreshold.amountthreshold;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class AmountThresholdDetailImpl extends PSAcceptanceTest
{
	protected String clientPartition;
	protected String name;
	protected String addCurrency;
	String copyCurrency;
	String thresholdName;
	String color;
	String operator1;
	String value1;
	String operator2;
	String value2;
	String notification;
	String team;
	String thresholdPeriod;
	String emailType;
	String emailSubject;
	String emailBody;
	String emailId;
	String searchScreenColumns;
	String effectiveDate;
	String[] addcurrencyArr;
	String[] copyCurrencyArr;
	String[] effectiveDateArr;
	String[] threasholdNameArr;
	String[] operator1Arr;
	String[] value1Arr;
	String[] operator2Arr;
	String[] value2Arr;
	String[] teamArr;
	String[] notificationArr;
	String[] typeArr;
	String[] emailSubjectArr;
	String[] emailIDArr;
	String[] threshooldPeriodArr;
	String[] colorArr;
	private LinkedHashSet thresholdLegendhashSet = new LinkedHashSet();
	protected Map<String, String> map = null;

	public AmountThresholdDetailImpl( Map<String, String> map ) throws Exception
	{

		this.map = map;
		initializeVariables( this.map );
		initializeVariablesFirstLevelSplit();
	}

	public void basicDetails() throws Exception
	{
		//assertEquals( NavigationHelper.getScreenTitle(), "New Amount Threshold" );
		TextBoxHelper.type( "PS_Detail_Amtthreshold_name_txtId", name );
	}

	public void configureThresholdLegends() throws Exception
	{
		for ( int row = 0; row < addcurrencyArr.length; row++ )
		{
			basicDetails();
			ComboBoxHelper.select( "PS_Detail_Amtthreshold_addCurrency_comboID", addcurrencyArr[row] );
			if ( ValidationHelper.isEmpty( copyCurrency ) || ValidationHelper.isEmpty( copyCurrencyArr[row] ) )
			{
				ButtonHelper.click( "addNewCurrency" );
				GenericHelper.waitForLoadmask();
				assertTrue( PopupHelper.isPresent( "window-scroll-panel" ) );
				if ( !effectiveDate.isEmpty() && !effectiveDateArr[row].isEmpty() )
				{
				TextBoxHelper.type( "PS_Detail_Amtthreshold_effectiveDate_txtID", effectiveDateArr[row] );
				ElementHelper.click( "//*[@id='Set Date']" );
				ButtonHelper.click( "PS_Detail_Amtthreshold_effectiveDate_txtID" );
				GenericHelper.waitForLoadmask();
				ButtonHelper.click( "OKButton" );
				
				GenericHelper.waitForLoadmask();
				}

				thresholdLegendsPerCurrency( colorArr[row], threasholdNameArr[row], operator1Arr[row], value1Arr[row], operator2Arr[row], value2Arr[row], teamArr[row], typeArr[row], emailSubjectArr[row], emailIDArr[row], notificationArr[row] );

				thresholdPeriod( threshooldPeriodArr[row] );
			}

			else if ( row > 0 && ValidationHelper.isNotEmpty( copyCurrencyArr[row] ) )
			{
				switchToTab( copyCurrencyArr[row] );
				String srcDate = ElementHelper.getText( "PS_Detail_Amtthreshold_leftButton_LabelId" );
				ComboBoxHelper.select( "PS_Detail_Amtthreshold_copycurrency_comboID", copyCurrencyArr[row] );
				ButtonHelper.click( "PS_Detail_Amtthreshold_copycurrency_btnID" );
				GenericHelper.waitForLoadmask();
				switchToTab( addcurrencyArr[row] );
				String destinationDate = ElementHelper.getText( "PS_Detail_Amtthreshold_leftButton_LabelId" );
				//assertEquals( destinationDate, srcDate, "Dates are not matching" );	
			}
		}
	}

	public void thresholdLegendsPerCurrency( String colorArr, String thresholdNameArr, String operator1Arr, String value1Arr, String operator2Arr, String value2Arr, String teamArr, String emailTypeArr, String emailSubjectArr, String emailIDArr, String notificationArr ) throws Exception
	{
		int expectedcolLength = 0;
		String[] threasholdNameAr = thresholdNameArr.split( secondLevelDelimiter );
		String[] operator1Ar = operator1Arr.split( secondLevelDelimiter );
		String[] value1Ar = value1Arr.split( secondLevelDelimiter );
		String[] operator2Ar = operator2Arr.split( secondLevelDelimiter );
		String[] value2Ar = value2Arr.split( secondLevelDelimiter );
		String[] teamAr = teamArr.split( secondLevelDelimiter );
		String[] typeAr = emailTypeArr.split( secondLevelDelimiter );
		String[] emailSubjectAr = emailSubjectArr.split( secondLevelDelimiter );
		String[] emailIDAr = emailIDArr.split( secondLevelDelimiter );
		String[] notificationAr = notificationArr.split( secondLevelDelimiter );
		String[] colorAr = colorArr.split( secondLevelDelimiter );

		for ( int i = 0; i < threasholdNameAr.length; i++ )
		{
			if ( i > 2 )
				ButtonHelper.click( "thresholdCurrencyToolbar.add" );
			GenericHelper.waitForLoadmask();
			String operator1Str = "";
			String value1Str = "";
			String colorStr = "";
			String operator2Str = "";
			String value2Str = "";
			String teamStr = "";
			String notificationStr = "";
			int clickColNum = 7;

			if ( !thresholdNameArr.isEmpty() && !threasholdNameAr[i].isEmpty() )
				GridHelper.updateGridTextBox( "PS_Detail_Amtthreshold_currency_gridID", "PS_Detail_Amtthreshold_thresholdname_txtId", i + 1, 1, clickColNum, threasholdNameAr[i] );

			if ( !colorArr.isEmpty() && !colorAr[i].isEmpty() )
			{
				colorStr = colorAr[i];
				GridHelper.updateGridTextBox( "PS_Detail_Amtthreshold_currency_gridID", "PS_Detail_Amtthreshold_thresholdname_txtId", i + 1, 2, clickColNum, colorAr[i] );

			}
			if ( !operator1Arr.isEmpty() && !operator1Ar[i].isEmpty() )
			{
				operator1Str = operator1Ar[i];
				clickComboBox( "PS_Detail_Amtthreshold_currency_gridID", "PS_Detail_Amtthreshold_operator1_txtId", i + 1, 3, clickColNum, operator1Ar[i] );
			}

			if ( !value1Arr.isEmpty() && !value1Ar[i].isEmpty() )
			{
				value1Str = value1Ar[i];
				GridHelper.updateGridTextBox( "PS_Detail_Amtthreshold_currency_gridID", "PS_Detail_Amtthreshold_value1_txtId", i + 1, 4, clickColNum, value1Ar[i] );
			}

			if ( !operator2Arr.isEmpty() && !operator2Ar[i].isEmpty() )
			{
				operator2Str = operator2Ar[i];
				clickComboBox( "PS_Detail_Amtthreshold_currency_gridID", "PS_Detail_Amtthreshold_operator2_txtId", i + 1, 5, clickColNum, operator2Ar[i] );
			}
			if ( !value2Arr.isEmpty() && !value2Ar[i].isEmpty() )
			{
				value2Str = value2Ar[i];
				GridHelper.updateGridTextBox( "PS_Detail_Amtthreshold_currency_gridID", "PS_Detail_Amtthreshold_value2_txtId", i + 1, 6, clickColNum, value2Ar[i] );
			}
			if ( !teamArr.isEmpty() && !teamAr[i].isEmpty() )
			{
				teamStr = teamAr[i];
				clickComboBox( "PS_Detail_Amtthreshold_currency_gridID", "PS_Detail_Amtthreshold_team_txtId", i + 1, 8, clickColNum, teamAr[i] );
			}

			if ( !notificationArr.isEmpty() && !notificationAr[i].isEmpty() )
			{
				notificationStr = notificationAr[i];
				clicknotification( i + 1, clickColNum );
				if ( !emailTypeArr.isEmpty() && !typeAr[i].isEmpty() )
					emailNotificationDetails( i + 1, typeAr[i], emailSubjectAr[i], emailIDAr[i] );
				ButtonHelper.click( "PS_Detail_Amtthreshold_email_okBtnID" );
				GenericHelper.waitForLoadmask();
				GridHelper.clickRow( "PS_Detail_Amtthreshold_currency_gridID", i + 1, 8 );
			}

			List<String> stringActual = Arrays.asList( threasholdNameAr[i], colorStr, operator1Str, value1Str, operator2Str, value2Str, notificationStr, teamStr );
			expectedcolLength = stringActual.size();
			PSStringUtils strObj = new PSStringUtils();
			String expectedVal = strObj.stringformation( stringActual );
			thresholdLegendhashSet.add( expectedVal );

		}

	}

	public void clicknotification( int row, int col ) throws Exception
	{
		GridHelper.clickRow( "PS_Detail_Amtthreshold_currency_gridID", row, col );
		if ( !ButtonHelper.isPresent( "PS_Detail_Amtthreshold_notification_EntityID" ) )
			GridHelper.clickRow( "PS_Detail_Amtthreshold_currency_gridID", row, col );
		ButtonHelper.click( "PS_Detail_Amtthreshold_notification_EntityID" );
		assertEquals( NavigationHelper.getScreenTitle(), "Email and Alert Notification" );
	}

	public void clickComboBox( String gridId, String comboId, int row, int col, int clickcol, String value ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( value ) )
		{
			GridHelper.clickRow( gridId, row, col );
			if ( !ComboBoxHelper.isPresent( comboId ) )
				GridHelper.clickRow( gridId, row, col );
			ComboBoxHelper.select( gridId, comboId, row, col, value );
			GridHelper.clickRow( gridId, row, clickcol );
		}
	}

	public void emailNotificationDetails( int row, String emailtypeAr, String emailSubjectAr, String emailIDAr ) throws Exception
	{
		String regex = new PSStringUtils().regexThirdLevelDelimeter();
		String[] emailTypeFinalAr = emailtypeAr.split( regex, -1 );
		String[] emailSubjectFinalAr = emailSubjectAr.split( regex, -1 );
		String[] emailIDFinalAr = emailIDAr.split( regex, -1 );
		String path = GenericHelper.getUploadFilePath( "emailable-report.html" );
		String path1 = "\\Desktop\\emailable-report.html";

		for ( int i = 0; i < emailTypeFinalAr.length; i++ )
		{
			if ( !emailtypeAr.isEmpty() && !emailTypeFinalAr[i].isEmpty() )
			{
				ButtonHelper.click( "PS_Detail_Amtthreshold_email_add_BtnID" );
				GenericHelper.waitForLoadmask();
				assertEquals( NavigationHelper.getScreenTitle(), "Email Notification Details" );

				TextBoxHelper.type( "PS_Detail_Amtthreshold_email_gridID", "PS_Detail_Amtthreshold_emailtype_txtID", emailTypeFinalAr[i] );
				TextBoxHelper.type( "PS_Detail_Amtthreshold_email_gridID", "PS_Detail_Amtthreshold_emailsubject_txtID", emailSubjectFinalAr[i] );
				ButtonHelper.click( "trigger-fileTbl.filFilename" );
				GenericHelper.waitForLoadmask();
				GenericHelper.fileUpload( path );
				ButtonHelper.click( "PS_Detail_Amtthreshold_emailID_add_BtnID" );
				GenericHelper.waitForLoadmask();
				GridHelper.updateGridTextBox( "PS_Detail_Amtthreshold_email_gridID", "PS_Detail_Amtthreshold_emailID_txtID", row, "Email Id", emailIDFinalAr[i] );

			}
		}
		ButtonHelper.click( "PS_Detail_Amtthreshold_notification_okBtnID" );
		GenericHelper.waitForLoadmask();

	}

	public void thresholdPeriod( String thresholdPeriod ) throws Exception
	{

		WebElement newElement = ElementHelper.getElement( or.getProperty( "detail_bip_timeLineId" ) );
		if ( !thresholdPeriod.isEmpty() )
		{
			newElement.click();
			ButtonHelper.click( "detail_bip_timeLineNew_actionXpath" );
			GenericHelper.waitForLoadmask();

			if ( PopupHelper.isPresent( "window-scroll-panel" ) )
			{
				TextBoxHelper.type( "PS_Detail_Amtthreshold_effectiveDate_txtID", thresholdPeriod );
				ElementHelper.click( "//*[@id='Set Date']" );
				ButtonHelper.click( "PS_Detail_Amtthreshold_effectiveDate_txtID" );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				ButtonHelper.click( "OKButton" );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}

		}
	}

	public void switchToTab( String currency ) throws Exception
	{
		String xpath = "//div[text()='currency']";
		String tabname = GenericHelper.getORProperty( xpath ).replace( "currency", currency );
		TabHelper.gotoTab( tabname );
		GenericHelper.waitForLoadmask();
	}

	public void saveAmtThreshold() throws Exception
	{
		ButtonHelper.click( "PS_Detail_Amtthreshold_save_btnID" );
		GenericHelper.waitForLoadmask();
		
	}

	public LinkedHashSet<String> getGridData()
	{

		return thresholdLegendhashSet;
	}

	public void validateGridRowData( LinkedHashSet<String> gridRowsData, String gridId, int extraColumns ) throws Exception
	{

		Set<String> rows = gridRowsData;
		int totalUIGridRows = GridHelper.getRowCount( gridId );
		int trynew = rows.size();
		Assert.assertEquals( rows.size(), totalUIGridRows, "Number of rows not matching in the grid" );

		List<String> rowDataUIList = new ArrayList<String>();
		PSStringUtils strObj = new PSStringUtils();
		Iterator itr = rows.iterator();
		int rowNum = 1;
		while ( itr.hasNext() )
		{
			rowDataUIList = GridHelper.getRowValues( gridId, rowNum );
			String expectedValues = itr.next().toString();
			for ( int col = 0; col < extraColumns; col++ )
			{
				int actualCount = rowDataUIList.size() - 1;
				rowDataUIList.remove( actualCount );
			}
			String actaulUIValues = strObj.stringformation( rowDataUIList );
			Assert.assertEquals( actaulUIValues, expectedValues, " values are not matching in the row : " + rowNum );
			rowNum++;
		}
	}

	/*
	 * This method is to delete Extra Row in Key/Value Grid
	 */

	public void deleteExtraRow( String[] threasholdNameAr ) throws Exception
	{

		for ( int i = 0; i < threasholdNameAr.length; i++ )
		{
			int UIRowCount = GridHelper.getRowCount( "PS_Detail_Amtthreshold_currency_gridID" );
			if ( UIRowCount > threasholdNameAr.length )
			{
				GridHelper.clickRow( "PS_Detail_Amtthreshold_currency_gridID", UIRowCount, "Name" );
				ButtonHelper.click( "PS_Detail_Amtthreshold_thresholdCurrency_delete_BtnID" );
			}
		}

	}

	private void initializeVariablesFirstLevelSplit() throws Exception
	{
		String regex = new PSStringUtils().regexFirstLevelDelimeter();
		addcurrencyArr = addCurrency.split( regex, -1 );
		copyCurrencyArr = copyCurrency.split( regex, -1 );
		effectiveDateArr = effectiveDate.split( regex, -1 );
		threasholdNameArr = thresholdName.split( regex, -1 );
		operator1Arr = operator1.split( regex, -1 );
		value1Arr = value1.split( regex, -1 );
		operator2Arr = operator2.split( regex, -1 );
		value2Arr = value2.split( regex, -1 );
		teamArr = team.split( regex, -1 );
		notificationArr = notification.split( regex, -1 );
		typeArr = emailType.split( regex, -1 );
		emailSubjectArr = emailSubject.split( regex, -1 );
		emailIDArr = emailId.split( regex, -1 );
		threshooldPeriodArr = thresholdPeriod.split( regex, -1 );
		colorArr = color.split( regex, -1 );

	}

	/*
	 * This method is to initialize instance variables
	 */
	protected void initializeVariables( Map<String, String> map ) throws Exception
	{

		clientPartition = ExcelHolder.getKey( map, "Partition" );
		name = ExcelHolder.getKey( map, "Name" );
		addCurrency = ExcelHolder.getKey( map, "AddCurrency" );
		copyCurrency = ExcelHolder.getKey( map, "CopyCurrency" );
		thresholdName = ExcelHolder.getKey( map, "ThresholdName" );
		color = ExcelHolder.getKey( map, "Color" );
		operator1 = ExcelHolder.getKey( map, "Operator1" );
		value1 = ExcelHolder.getKey( map, "Value1" );
		operator2 = ExcelHolder.getKey( map, "Operator2" );
		value2 = ExcelHolder.getKey( map, "Value2" );
		notification = ExcelHolder.getKey( map, "Notification" );
		team = ExcelHolder.getKey( map, "Team" );
		thresholdPeriod = ExcelHolder.getKey( map, "ThresholdPeriod" );
		emailType = ExcelHolder.getKey( map, "EmailType" );
		emailSubject = ExcelHolder.getKey( map, "EmailSubject" );
		emailBody = ExcelHolder.getKey( map, "EmailBody" );
		emailId = ExcelHolder.getKey( map, "EmailId" );
		effectiveDate = ExcelHolder.getKey( map, "EffectiveDate" );
		//searchScreenColumns = ExcelHolder.getKey( map, "SearchScreenColumns" );
	}
}
