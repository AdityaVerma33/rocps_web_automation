package com.subex.rocps.automation.helpers.application.genericHelpers;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.App;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.sikuli.script.ScreenImage;
import org.testng.Assert;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.ImageHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.component.TreeHelper;
import com.subex.automation.helpers.componentHelpers.SearchHelper;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class PSGenericHelper extends PSAcceptanceTest
{

	private Object object;

	/*
	 * Method: For validating the texts under GridActions
	 */
	public boolean validateActionText( String parentText, String childText ) throws Exception
	{
		boolean flag = false;

		try
		{
			parentText = GenericHelper.getORProperty( parentText );
			String parentXpath = or.getProperty( "GroupActionName" ).replace( "actionName", parentText );
			String childXpath = or.getProperty( "childTextXpath" ).replace( "partialText", childText );

			MouseHelper.click( parentXpath );
			Thread.sleep( 500 );
			if ( driver.findElements( By.xpath( childXpath ) ).size() > 0 )
				flag = true;

			return flag;

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * Method: clicking New Action in detail Screen
	 * 
	 * @Param clientPartition : value is empty then partitionFlag
	 * "clientPartitionFlag" and partition "Partition" is checked in property file :
	 * value is passed in excel then partitionFLag "clientPartitionFlag" is checked
	 * in property file if true then partition is configureds
	 * 
	 */

	public void clickNewAction( String clientPartition ) throws Exception
	{
		if ( configProp.getProperty( "clientPartitionFlag" ) != null && Boolean.valueOf( configProp.getProperty( "clientPartitionFlag" ) ) && !clientPartition.isEmpty() )
			NavigationHelper.navigateToNew( clientPartition );
		else if ( configProp.getProperty( "clientPartitionFlag" ) != null && Boolean.valueOf( configProp.getProperty( "clientPartitionFlag" ) ) && !configProp.getProperty( "partition" ).isEmpty() )
			NavigationHelper.navigateToNew( configProp.getProperty( "partition" ) );
		else
			NavigationHelper.navigateToNew();
	}

	/*
	 * @Method: checking whether the cell value exists with the txtVal passed under
	 * the text column in search screen
	 * 
	 * @Param txtVal : pass the value to be verified in the text box
	 * 
	 * @Param coloumnHeader : pass the column header name
	 */
	public boolean isGridTextValuePresent( String gridColTextIdOrXpath, String txtVal, String coloumnHeder ) throws Exception
	{
		/*
		 * ButtonHelper.click( "ClearButton" );
		 * GenericHelper.waitForLoadmask(searchScreenWaitSec);
		 */
		SearchGridHelper.gridFilterSearchWithTextBox( gridColTextIdOrXpath, txtVal, coloumnHeder );
		return GridHelper.isValuePresent( "SearchGrid", txtVal, coloumnHeder );
	}

	public void detailSave( String saveBtnId, String value, String colHeader ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.clickIfEnabled( saveBtnId );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitForElementToDisappear( saveBtnId, detailScreenWaitSec );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );

		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		waitforHeaderElement( colHeader );
		assertTrue( GridHelper.isValuePresent( "SearchGrid", value, colHeader ), "Value '" + value + "' is not found in grid." );
	}

	/*
	 * Method: for selecting the items in the dual list and pushing the items in the
	 * dual list
	 */
	public void dualListSelection( String dualListValue ) throws Exception
	{

		String[] dualListValueArr = new PSStringUtils().stringSplitFirstLevel( dualListValue );
		for ( String str : dualListValueArr )
		{

			if ( dualListValueArr.length >= 1 )
			{

				Actions action = new Actions( driver );
				String locator = GenericHelper.getORProperty( "PSDetail_legCodes_inputDualListXpath" ).replace( "inputValues", str );
				if ( !ElementHelper.isElementPresent( locator ) )
					ElementHelper.waitForElement( locator, searchScreenWaitSec );
				WebElement element = driver.findElement( By.xpath( locator ) );
				ElementHelper.waitForClickableElement( element, searchScreenWaitSec );
				Thread.sleep( 3000 );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				action.moveToElement( element ).click().build().perform();
				ButtonHelper.click( "PSDetail_dualComp_selectBtnId" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );

			}
		}

	}

	/*
	 * Method: for selecting the items in the dual list and pushing the items in the
	 * dual list
	 */
	public void dualListSelectionWithTxtFilter( String dualListValue ) throws Exception
	{

		String[] dualListValueArr = new PSStringUtils().stringSplitFirstLevel( dualListValue );
		for ( String str : dualListValueArr )
		{

			if ( dualListValueArr.length >= 1 )
			{
				String leftWidgetListValueXpath = GenericHelper.getORProperty( "PSDetail_LeftWidgetListAvailable_valueXpath" );
				leftWidgetListValueXpath = leftWidgetListValueXpath.replace( "dualListValue", str );
				String rightWidgetListValueXpath = GenericHelper.getORProperty( "PSDetail_RightWidgetListAvailable_valueXpath" );
				rightWidgetListValueXpath = rightWidgetListValueXpath.replace( "dualListValue", str );
				TextBoxHelper.type( "PSDetail_leftWidgetText_inputDualListXpath", str );
				ElementHelper.waitForElement( leftWidgetListValueXpath, searchScreenWaitSec );
				Actions action = new Actions( driver );
				String locator = GenericHelper.getORProperty( "PSDetail_legCodes_inputDualListXpath" ).replace( "inputValues", str );
				if ( !ElementHelper.isElementPresent( locator ) )
					ElementHelper.waitForElement( locator, searchScreenWaitSec );
				WebElement element = driver.findElement( By.xpath( locator ) );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				ElementHelper.waitForClickableElement( element, searchScreenWaitSec );
				Thread.sleep( 3000 );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				for ( int i = 0; i < 5; i++ )
				{
					try
					{
						element.click();
						break;
					}
					catch ( StaleElementReferenceException e )
					{
						element = driver.findElement( By.xpath( locator ) );
						element.click();

					}
					catch ( Exception e )
					{
						Log4jHelper.logInfo( "Could not click on the element for PsDualListSelction" + e.toString() );
					}
					finally
					{
						Log4jHelper.logInfo( "Retrying 'dualListSelectionWithTxtFilter' with times-" + ( i + 1 ) );

					}
				}
				ButtonHelper.click( "PSDetail_dualComp_selectBtnId" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				ElementHelper.waitForElement( rightWidgetListValueXpath, searchScreenWaitSec );

			}
		}

	}

	public void dualListSelectionWrapper( String dualListValue, String buttonWrapper, String dualListID ) throws Exception
	{

		String[] dualListValueArr = new PSStringUtils().stringSplitFirstLevel( dualListValue );
		for ( String str : dualListValueArr )
		{

			if ( dualListValueArr.length >= 1 )
			{

				Actions action = new Actions( driver );
				String xpath = "//div[@id='" + dualListID + "']//*//div[text()='inputValues']";
				String locator = GenericHelper.getORProperty( xpath ).replace( "inputValues", str );
				if ( !ElementHelper.isElementPresent( locator ) )
					ElementHelper.waitForElement( locator, 120 );
				WebElement element = driver.findElement( By.xpath( locator ) );
				Thread.sleep( 3000 );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				ElementHelper.scrollToView( element, false );
				action.moveToElement( element ).click().build().perform();
				ButtonHelper.click( buttonWrapper, "PSDetail_dualComp_selectBtnId" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );

			}
		}

	}

	/*
	 * Method for selecting the value in the dual list
	 * 
	 * @Param leftFilterPanelText: Left panel filter label
	 * 
	 * @Param leftFilterPanelText: value to be selected in the dual list
	 */
	public void dualListSelection( String leftFilterPanelText, String dualListValue ) throws Exception
	{

		String btnXpath = GenericHelper.getORProperty( "PS_dualListSelectButtonXpath" );
		String btnLocator = btnXpath.replace( "filterHeaderText", leftFilterPanelText );

		String filterItemXpath = GenericHelper.getORProperty( "PS_dualListAvailableFilterItem" );
		String availableItemLocator = filterItemXpath.replace( "filterHeaderText", leftFilterPanelText ).replace( "selectValue", dualListValue );
		Thread.sleep( 3000 );
		driver.findElement( By.xpath( availableItemLocator ) ).click();
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		driver.findElement( By.xpath( btnLocator ) ).click();
		GenericHelper.waitForLoadmask( searchScreenWaitSec );

	}

	/*
	 * Method: for selecting the criteria value in the entities entities supported
	 * are route group, dial string set, event identifier value search
	 */
	public void criteriaValueSelection( String criteriaVal ) throws Exception
	{

		DataSelectionHelper dscObj = new DataSelectionHelper();
		if ( NavigationHelper.getScreenTitle().equals( "Route Group Search" ) )
			dscObj.routeGrpSelection( criteriaVal );
		else if ( NavigationHelper.getScreenTitle().equals( "Dialstring Set Search" ) )
			dscObj.dialStringSelection( criteriaVal );
		else if ( NavigationHelper.getScreenTitle().equals( "Event Identifier Value Search" ) )
			dscObj.stringSelection( criteriaVal );
		else
			FailureHelper.failTest( "The given title-" + NavigationHelper.getScreenTitle() + "- is not matched:-" );

	}

	/*
	 * Method: for selecting the criteria valuess in the entities entities supported
	 * are route group, dial string set, event identifier value search, value group
	 */
	public void criteriaValueSelectionWithValueGroups( String criteriaVal ) throws Exception
	{

		DataSelectionHelper dscObj = new DataSelectionHelper();
		if ( NavigationHelper.getScreenTitle().equals( "Route Group Search" ) )
			dscObj.routeGrpSelection( criteriaVal );
		else if ( NavigationHelper.getScreenTitle().equals( "Dialstring Set Search" ) )
			dscObj.dialStringSelection( criteriaVal );
		else if ( NavigationHelper.getScreenTitle().equals( "Event Identifier Value Search" ) )
			dscObj.stringSelection( criteriaVal );
		else if ( NavigationHelper.getScreenTitle().equals( "Event Identifier Value Group Search" ) )
			dscObj.valueGrpSelection( criteriaVal );
		else
			FailureHelper.failTest( "The given title-" + NavigationHelper.getScreenTitle() + "- is not matched:-" );
	}

	/*
	 * Method: Clicks the property value cell against propertyName
	 * 
	 * @Param : Property Name to be passed
	 */
	public void clickPropertyValueColumn( String propertyName ) throws Exception
	{
		String locator = GenericHelper.getORProperty( "PS_propertyValColXpath" ).replace( "propertyName", propertyName );
		ElementHelper.click( locator );
		ElementHelper.click( locator );

	}

	/*
	 * Method: Clicking new action on the timeline
	 */
	public void timeLineNew() throws Exception
	{

		WebElement element = ElementHelper.getElement( or.getProperty( "detail_bip_timeLineDisplayed_xpath" ) );
		WebElement newElement = ElementHelper.getElement( or.getProperty( "detail_bip_timeLineId" ) );
		newElement.click();
		ButtonHelper.click( "detail_bip_timeLineNew_actionXpath" );
		GenericHelper.waitForLoadmask();
	}

	/*
	 * Method: Clicking edit action on the timeline
	 */
	public void timeLineEdit() throws Exception
	{

		WebElement element = ElementHelper.getElement( or.getProperty( "detail_bip_timeLineDisplayed_xpath" ) );
		WebElement newElement = ElementHelper.getElement( or.getProperty( "detail_bip_timeLineId" ) );
		newElement.click();
		ButtonHelper.click( "detail_bip_timeLineEdit_actionXpath" );
		GenericHelper.waitForLoadmask();
	}

	/*
	 * Method for expanding collapsible icon
	 */
	public void collapsableXpath() throws Exception
	{
		ImageHelper.click( "PS_collapsableXpath" );
		Thread.sleep( 2000 );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/*
	 * Method for storing grid column names
	 */
	public List<String> getGridColumns( String columnHeaderId ) throws Exception
	{

		List<String> guiColumns = new ArrayList<String>();
		String locator = "//div[contains(@id,'" + columnHeaderId + "')]";
		List<WebElement> elements = ElementHelper.getElements( locator );
		Iterator<WebElement> itr = elements.iterator();
		while ( itr.hasNext() )
		{
			guiColumns.add( itr.next().getAttribute( "textContent" ).replaceAll( "[\\u00A0]", "" ).trim() );
		}
		return guiColumns;
	}

	/*
	 * Method for storing grid column names with wrapper ID or GridId
	 */
	public List<String> getGridColumns( String columnHeaderId, String wrapperOrGridID ) throws Exception
	{

		List<String> guiColumns = new ArrayList<String>();
		String locator = "//div[@id='" + wrapperOrGridID + "']//div[contains(@id,'" + columnHeaderId + "')]";
		List<WebElement> elements = ElementHelper.getElements( locator );
		Iterator<WebElement> itr = elements.iterator();
		while ( itr.hasNext() )
		{
			guiColumns.add( itr.next().getAttribute( "textContent" ).replaceAll( "[\\u00A0]", "" ).trim() );
		}
		return guiColumns;
	}

	/*
	 * This method is select the partition and include combo in search screen
	 */
	public void selectPartitionFilter( String partition, String include ) throws Exception
	{
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		if ( ValidationHelper.isTrue( configProp.getProperty( "clientPartitionFlag" ) ) && ValidationHelper.isNotEmpty( partition ) && configProp.getProperty( "partition" ).isEmpty() )
			ComboBoxHelper.select( "partitionId_gwt_uid_", partition );
		/*
		 * if(ValidationHelper.isTrue( configProp.getProperty( "clientPartitionFlag" ) )
		 * && ValidationHelper.isEmpty( partition ) &&
		 * !configProp.getProperty("partition").isEmpty())
		 * ComboBoxHelper.select("partitionId_gwt_uid_",
		 * configProp.getProperty("partition"));
		 */

		ComboBoxHelper.select( "deleteFl_gwt_uid_", include );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		ButtonHelper.click( "SearchButton" );
	}

	/*
	 * This method is to perform delete or undelete action
	 */
	public void clickDeleteOrUnDeleteAction( String rowVal, String colHeader, String actionName ) throws Exception
	{
		GridHelper.clickRow( "SearchGrid", rowVal, colHeader );
		NavigationHelper.navigateToAction( "Common Tasks", actionName );
		GenericHelper.waitForLoadmask();
		assertEquals( NavigationHelper.getScreenTitle(), actionName );
		ButtonHelper.click( "YesButton" );
		GenericHelper.waitForLoadmask();
	}

	/*
	 * This method is for calender SetOnDate
	 */
	public void setDate( String tableId, String date ) throws Exception

	{
		String xpath = "//table[@id='" + tableId + "']//div[@id='options']/img";
		String type = "On";
		ElementHelper.click( xpath );
		ElementHelper.click( "//div[@id='" + type + "']" );
		driver.findElement( By.xpath( "//input[@id='-fromDateLabel']" ) ).clear();
		driver.findElement( By.xpath( "//input[@id='-fromDateLabel']" ) ).sendKeys( date, Keys.ENTER );

		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask();
	}

	/* Method use date filter with between type */
	public static void setBetweenDate( String tableIdOfDateFilter, String fromdate, String toDt ) throws Exception
	{
		String xpath = GenericHelper.getORProperty( "PS_DateFilter_xpath" );
		xpath = xpath.replace( "tableId", tableIdOfDateFilter );
		ElementHelper.waitForClickableElement( xpath, 10 );
		ElementHelper.click( xpath );
		ElementHelper.click( "//div[@id='" + "Between" + "']" );
		ElementHelper.getElement( GenericHelper.getORProperty( "PS_DateFilter_FromDt_xpath" ) ).sendKeys( fromdate );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.getElement( GenericHelper.getORProperty( "PS_DateFilter_ToDt_xpath" ) ).sendKeys( toDt, Keys.ENTER );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/*
	 * This method is to get the verify the search screen total columns
	 */
	public void totalColumns( ArrayList<String> excelColumnNames ) throws Exception
	{
		PSStringUtils psStringObj = new PSStringUtils();
		assertTrue( GridHelper.isPresent( "SearchGrid" ) );
		List<String> actualColumnNames = getGridColumns( "grid_column_header_searchGrid_" );

		int index = actualColumnNames.size() - 1;
		String finalVal = actualColumnNames.get( index );
		if ( finalVal.isEmpty() )
			actualColumnNames.remove( index );
		assertEquals( actualColumnNames, excelColumnNames, "Search Screen total columns are not matching" );
		String actualValues = psStringObj.stringformation( actualColumnNames );
		String excelValues = psStringObj.stringformation( excelColumnNames );
		Log4jHelper.logInfo( "Actual Value :" + actualValues );
		Log4jHelper.logInfo( "Excel Value : " + excelValues );
	}

	/*
	 * This method is to get the verify the search screen total columns
	 */
	public void totalColumns( ArrayList<String> excelColumnNames, String gridID, String colHeaderID ) throws Exception
	{
		PSStringUtils psStringObj = new PSStringUtils();
		GridHelper.isPresent( gridID );
		List<String> actualColumnNames = getGridColumns( colHeaderID );

		int index = actualColumnNames.size() - 1;
		String finalVal = actualColumnNames.get( index );
		if ( finalVal.isEmpty() )
			actualColumnNames.remove( index );
		assertEquals( actualColumnNames, excelColumnNames, "Search Screen total columns are not matching" );
		String actualValues = psStringObj.stringformation( actualColumnNames );
		String excelValues = psStringObj.stringformation( excelColumnNames );
		Log4jHelper.logInfo( "Actual Value :" + actualValues );
		Log4jHelper.logInfo( "Excel Value : " + excelValues );
	}

	/*
	 * This method is to validateGridRow data
	 */
	public void validateGridRowData( LinkedHashSet<String> gridRowsData, String gridId, int extraColumns ) throws Exception
	{

		Set<String> rows = gridRowsData;
		int totalUIGridRows = GridHelper.getRowCount( gridId );
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
	 * This method is used for fileupload
	 */
	public static void psFileUploadSikuli( String fileFieldXpath, String fileNamewithPath ) throws Exception
	{
		try
		{
			fileFieldXpath = GenericHelper.getORProperty( fileFieldXpath );
			fileNamewithPath = GenericHelper.getPath( automationOS, fileNamewithPath );

			// Moving focus to browser
			ElementHelper.setFocus( fileFieldXpath );
			MouseHelper.click( fileFieldXpath );
			String fileImgPath = automationPath + "\\Images\\FileUpload\\" + "fileType.png";
			String openButtonImgPath = automationPath + "\\Images\\FileUpload\\" + "openButton.png";

			Screen screen = new Screen();
			Pattern filePath = new Pattern( fileImgPath );
			Pattern openButton = new Pattern( openButtonImgPath );

			screen.type( filePath, fileNamewithPath + Key.TAB );
			screen.delayClick( 2000 );
			screen.click( openButton );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is to facilitate Windows File Upload with parameters for FileType Image and OpenButton Image
	 */

	public static void psFileUploadSikuliWithoutRobot( String fileFieldXpath, String fileNamewithPath, String fileTypeImage, String openButtonImage ) throws Exception
	{
		try
		{
			fileFieldXpath = GenericHelper.getORProperty( fileFieldXpath );
			fileNamewithPath = GenericHelper.getPath( automationOS, fileNamewithPath );

			// Moving focus to browser
			ElementHelper.setFocus( fileFieldXpath );
			MouseHelper.click( fileFieldXpath );
			String fileImgPath = automationPath + "\\Images\\FileUpload\\" + fileTypeImage;
			String openButtonImgPath = automationPath + "\\Images\\FileUpload\\" + openButtonImage;

			Screen screen = new Screen();
			Pattern filePath = new Pattern( fileImgPath );
			Pattern openButton = new Pattern( openButtonImgPath );
			

	        screen.wait(filePath, 10);  // waits for the element

			



			screen.type( filePath, fileNamewithPath + Key.TAB );
			screen.delayClick( 2000 );
			screen.click( openButton );
			

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
		
		
	}
	/*
	 * This method is to facilitate Windows File Upload with parameters for FileType
	 * Image and OpenButton Image
	 */

	public static void psFileUploadSikuli( String fileFieldXpath, String fileNamewithPath, String fileTypeImage, String openButtonImage ) throws Exception
	{
		try
		{
			fileFieldXpath = GenericHelper.getORProperty( fileFieldXpath );
			fileNamewithPath = GenericHelper.getPath( automationOS, fileNamewithPath );

			// Moving focus to browser
			ElementHelper.setFocus( fileFieldXpath );
			MouseHelper.click( fileFieldXpath );
			String fileImgPath = automationPath + "\\Images\\FileUpload\\" + fileTypeImage;
			String openButtonImgPath = automationPath + "\\Images\\FileUpload\\" + openButtonImage;

			Screen screen = new Screen();

			/*
			//##################### ONE TIME STEP STARTS ###############################
			
			//A overlay screen will apear that asks you to take the screen shot
			// select the area you want to capture
			//App.open(fileImgPath);
			ScreenImage scrImgFile = screen.userCapture();
			Pattern pattern = null;
			
			//THis method will save the file to the current directory
			String path = scrImgFile.getFile(".");
			
			//This will print the full path of the save file
			System.out.println("Please note down the file Path: ");
			System.out.println(path);
			
			//  rerun the program by replacing the below path with the path we got above
			
			//##################### ONE TIME STEP ENDS ###############################
			
			*/

			Pattern filePath = new Pattern( fileImgPath );
			Pattern openButton = new Pattern( openButtonImgPath );

			screen.type( filePath, fileNamewithPath + Key.TAB );
			System.out.println( "AFTER TYPING....." );
			screen.delayClick( 6000 );

			Robot robot = new Robot();
			robot.keyPress( KeyEvent.VK_ENTER );
			GenericHelper.waitTime( 4, "waiting..." );
			robot.keyRelease( KeyEvent.VK_ENTER );
			System.out.println( "AFTER CLICKED....." );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public void waitforHeaderElement( String headerName ) throws Exception
	{
		String headerElement = "//div[@id='searchGrid']//th//div[contains(text(),'" + headerName + "')]";
		ElementHelper.waitForElement( headerElement, searchScreenWaitSec );
	}

	public boolean waitforPopupHeaderElement( String headerName ) throws Exception
	{
		String headerElement = "//div[@id='popupWindow']//div[@id='searchGrid']//th//div[contains(text(),'" + headerName + "')]";
		ElementHelper.waitForElement( headerElement, searchScreenWaitSec );
		return ElementHelper.isElementPresent( headerElement );
	}

	public boolean waitforPopupHeaderElement( String gridId, String headerName ) throws Exception
	{
		gridId = GenericHelper.getORProperty( gridId );
		String headerElement = "//div[@id='popupWindow']//div[@id='" + gridId + "']//th//div[contains(text(),'" + headerName + "')]";
		ElementHelper.waitForElement( headerElement, searchScreenWaitSec );
		return ElementHelper.isElementPresent( headerElement );
	}

	public boolean waitforPopupHeaderElement( String popupWindowId, String gridId, String headerName ) throws Exception
	{
		popupWindowId = GenericHelper.getORProperty( popupWindowId );
		gridId = GenericHelper.getORProperty( gridId );
		String headerElement = "//div[@id='" + popupWindowId + "']//div[@id='" + gridId + "']//th//div[contains(text(),'" + headerName + "')]";
		ElementHelper.waitForElement( headerElement, searchScreenWaitSec );
		return ElementHelper.isElementPresent( headerElement );
	}

	public void waitforPopupHeaderElementToDisappear( String headerName ) throws Exception
	{
		String headerElement = "//div[@id='popupWindow']//div[@id='searchGrid']//th//div[contains(text(),'" + headerName + "')]";
		ElementHelper.waitForElementToDisappear( headerElement, searchScreenWaitSec );
	}

	public void waitforPopupHeaderElementToDisappear( String gridId, String headerName ) throws Exception
	{
		gridId = GenericHelper.getORProperty( gridId );
		String headerElement = "//div[@id='popupWindow']//div[@id='" + gridId + "']//th//div[contains(text(),'" + headerName + "')]";
		ElementHelper.waitForElementToDisappear( headerElement, searchScreenWaitSec );
	}

	public void waitforPopupHeaderElementToDisappear( String popupWindowId, String gridId, String headerName ) throws Exception
	{
		popupWindowId = GenericHelper.getORProperty( popupWindowId );
		gridId = GenericHelper.getORProperty( gridId );
		String headerElement = "//div[@id='" + popupWindowId + "']//div[@id='" + gridId + "']//th//div[contains(text(),'" + headerName + "')]";
		ElementHelper.waitForElementToDisappear( headerElement, searchScreenWaitSec );
	}

	public void waitforEntityElement() throws Exception
	{
		String entityXpath = "//div[contains(@id,'trigger')]";
		ElementHelper.waitForElement( entityXpath, searchScreenWaitSec );
		ElementHelper.waitForClickableElement( entityXpath, searchScreenWaitSec );
	}

	/* Method get the key value from search screen of grid */
	public Map<String, String> getGridColumnValues( String gridId, String columnHeaderId, List<String> listColumn ) throws Exception
	{
		Map<String, String> hmap = new HashMap<String, String>();
		String key;
		String value;
		List<String> uIColumns = getGridColumns( columnHeaderId );

		for ( int col = 0; col < listColumn.size(); col++ )
		{
			key = listColumn.get( col );
			int colNum = uIColumns.indexOf( key );
			String colCellValues;
			colCellValues = GridHelper.getCellValue( "Detail_popUpWindowId", gridId, 1, colNum + 1 );
			hmap.put( key, colCellValues );
		}
		return hmap;

	}

	// Method :Get screenshots
	public static void takeSnapShot( String testCaseStatus, String testcaseNm, String fileName ) throws Exception
	{

		String dirPath = System.getProperty( "user.dir" ) + "//Screenshot//Report//";
		String month = DateHelper.getCurrentDateTime( "MM" );
		String day = DateHelper.getCurrentDateTime( "dd" );
		String year = DateHelper.getCurrentDateTime( "yyyy" );
		String hour = DateHelper.getCurrentDateTime( "HH" );
		String min = DateHelper.getCurrentDateTime( "mm" );
		String sec = DateHelper.getCurrentDateTime( "ss" );

		String currDt = month + "-" + day + "-" + year;
		TakesScreenshot scrShot = ( ( TakesScreenshot ) driver );
		File SrcFile = scrShot.getScreenshotAs( OutputType.FILE );
		if ( testCaseStatus.contains( "fail" ) )
			dirPath = dirPath + "Report(" + currDt + ")" + "//FailedScreenshot//" + testcaseNm + "//";
		if ( testCaseStatus.contains( "skip" ) )
			dirPath = dirPath + "Report(" + currDt + ")" + "//SkippedScreenshot//" + testcaseNm + "//";

		File DestFile = new File( dirPath + fileName + "_" + hour + "-" + min + "-" + sec + ".png" );
		Log4jHelper.logInfo( "file name " + DestFile );
		FileUtils.copyFile( SrcFile, DestFile );

	}

	/* Method wait for parent actionelement to be clickable */
	public static void waitForParentActionElementTOBeclickable( String parentActionName ) throws Exception
	{
		String parentActionxpath = "//*[@id='menuBarContainer']//div[text()='ParentActionName']";
		parentActionxpath = parentActionxpath.replace( "ParentActionName", parentActionName );
		ElementHelper.waitForClickableElement( parentActionxpath, searchScreenWaitSec );
	}

	/* This method is used to check popup header present */
	public boolean isPopupHeaderElementPresent( String headerName ) throws Exception
	{
		String headerElement = "//div[@id='popupWindow']//div[@id='searchGrid']//th//div[contains(text(),'" + headerName + "')]";
		ElementHelper.waitForElement( "//div[@id='popupWindow']", searchScreenWaitSec );
		return ElementHelper.isElementPresent( headerElement );
	}

	/* This method is used to check data present */
	public boolean isDataPresent( String txtValue, String txtColumnHeader ) throws Exception
	{
		int row = GridHelper.getRowCount( "SearchGrid" );
		boolean rowVal = false;
		if ( row > 0 )
		{
			for ( int i = 0; i < row; i++ )
			{
				String cellValue = GridHelper.getCellValue( "SearchGrid", i + 1, txtColumnHeader );
				GenericHelper.waitForLoadmask();
				rowVal = cellValue.contains( txtValue );
				if ( rowVal )
					return true;
			}
			return rowVal;
		}
		return false;
	}

	// Method: Validate the search result of 'Screen'
	public void validateSearchResult( String columnHeaders, String mapKeys, Map<String, String> map, String colmnHeaderId, String gridId ) throws Exception
	{
		DataVerificationHelper dataVerHelOb = new DataVerificationHelper();
		if ( ValidationHelper.isNotEmpty( columnHeaders ) )
			dataVerHelOb.validateData( GenericHelper.getORProperty( colmnHeaderId ), map, gridId, columnHeaders, mapKeys );
	}

	// Method: Validate the screen column validation
	public void screenColumnValidation( String waitForColumnHeader, String colmHdrs, String screenName ) throws Exception
	{
		PSStringUtils psStringUtils = new PSStringUtils();
		waitforHeaderElement( waitForColumnHeader );
		ArrayList<String> excelColumnNames = new ArrayList<String>();
		String screensGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
		for ( String column : screensGridColumnsArr )
			excelColumnNames.add( column );
		totalColumns( excelColumnNames );
		Log4jHelper.logInfo( "'" + screenName + "' " + " Columns are validated successfully" );
	}

	public void scrollforHeaderElement( String headerName ) throws Exception
	{
		String headerElement = "//div[@id='searchGrid']//th//div[contains(text(),'" + headerName + "')]";
		ElementHelper.scrollToView( headerElement, true );
	}

	public void scrollforHeaderElement( String gridId, String headerName ) throws Exception
	{
		gridId = GenericHelper.getORProperty( gridId );
		String headerElement = "//div[@id='" + gridId + "']//th//div[contains(text(),'" + headerName + "')]";
		ElementHelper.scrollToView( headerElement, false );
	}

	public void sortColumnHeaderGrid( String iconId, String ascendingMenuId ) throws Exception
	{
		SearchHelper searchHelper = new SearchHelper();
		searchHelper.clickFilterIcon( GenericHelper.getORProperty( iconId ) );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForClickableElement( GenericHelper.getORProperty( ascendingMenuId ), searchScreenWaitSec );
		Actions action = new Actions( driver );
		action.moveToElement( ElementHelper.getElement( GenericHelper.getORProperty( ascendingMenuId ) ) ).click().perform();

	}

	public void detailSaveWithRetry( String saveBtnId, String value, String colHeader ) throws Exception
	{
		int tryCount = 0;
		int maxCount = 5;
		while ( true )
		{
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			ButtonHelper.click( saveBtnId );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			ButtonHelper.click( "SearchButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			waitforHeaderElement( colHeader );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			if ( GridHelper.isValuePresent( "SearchGrid", value, colHeader ) || tryCount == maxCount )
				break;
			tryCount++;
			Log4jHelper.logInfo( "Retry 'detailSaveWithRetry' with count:- " + tryCount );
		}
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		waitforHeaderElement( colHeader );
		assertTrue( GridHelper.isValuePresent( "SearchGrid", value, colHeader ), "Value '" + value + "' is not found in grid." );
	}

	public void dualListSelectionWithTxtFilter( String leftWidgetTxtID, String rightWidgetTxtId, String dualListValue ) throws Exception
	{

		String[] dualListValueArr = new PSStringUtils().stringSplitFirstLevel( dualListValue );
		for ( String str : dualListValueArr )
		{

			if ( dualListValueArr.length >= 1 )
			{
				String leftWidgetListValueXpath = "//div[@id='Left Widget Container']//div[contains(@id,'leftWidgetTxtId') and text()='dualListValue']";
				leftWidgetListValueXpath = leftWidgetListValueXpath.replace( "dualListValue", str );
				leftWidgetListValueXpath = leftWidgetListValueXpath.replace( "leftWidgetTxtId", leftWidgetTxtID );
				String rightWidgetListValueXpath = "//div[@id='Right Widget Container']//div[contains(@id,'rightWidgetTxtId') and text()='dualListValue']";
				rightWidgetListValueXpath = rightWidgetListValueXpath.replace( "dualListValue", str );
				rightWidgetListValueXpath = rightWidgetListValueXpath.replace( "rightWidgetTxtId", rightWidgetTxtId );
				TextBoxHelper.type( "PSDetail_leftWidgetText_inputDualListXpath", str );
				ElementHelper.waitForElement( leftWidgetListValueXpath, searchScreenWaitSec );
				Actions action = new Actions( driver );
				String locator = GenericHelper.getORProperty( "PSDetail_legCodes_inputDualListXpath" ).replace( "inputValues", str );
				if ( !ElementHelper.isElementPresent( locator ) )
					ElementHelper.waitForElement( locator, searchScreenWaitSec );
				WebElement element = driver.findElement( By.xpath( locator ) );
				ElementHelper.waitForClickableElement( element, searchScreenWaitSec );
				Thread.sleep( 3000 );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				MouseHelper.click( element );
				ButtonHelper.click( "PSDetail_dualComp_selectBtnId" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				ElementHelper.waitForElement( rightWidgetListValueXpath, searchScreenWaitSec );

			}
		}

	}

	/*
	 * Method: Clicking new action on the timeline
	 */
	public void timeLineNew( String timeLineName ) throws Exception
	{

		String timeLineXpath = "//*[text()='" + timeLineName + "']/parent::td/parent::tr/following-sibling::tr[2]//div[@id='Label']";
		WebElement newElement = ElementHelper.getElement( timeLineXpath );
		newElement.click();
		ButtonHelper.click( "detail_bip_timeLineNew_actionXpath" );
		GenericHelper.waitForLoadmask();
	}

	/*
	 * Method: Clicking new action on the timeline
	 */
	public void timeLineNew( String timeLineName, String timeLineId ) throws Exception
	{

		String timeLineXpath = "//*[text()='" + timeLineName + "']/parent::td/parent::tr/following-sibling::tr[2]//div[@id='Label']";
		ElementHelper.scrollToView( "//*[text()='" + timeLineName + "']", false );
		MouseHelper.click( timeLineId );
		MouseHelper.click( timeLineXpath );
		if ( !ElementHelper.isElementPresent( GenericHelper.getORProperty( "detail_bip_timeLineNew_actionXpath" ) ) )
			MouseHelper.click( timeLineXpath );
		ButtonHelper.click( "detail_bip_timeLineNew_actionXpath" );
		GenericHelper.waitForLoadmask();
	}

	public void selectPageSearchRecordsFilter( String noOfPage, String columnHeader ) throws Exception
	{
		GenericHelper.waitForLoadmask();
		ComboBoxHelper.select( "pagersearchGrid_gwt_uid_", noOfPage );
		GenericHelper.waitForLoadmask();
		ButtonHelper.click( "SearchButton" );
		waitforPopupHeaderElement( columnHeader );

	}
}
