package com.subex.rocps.automation.helpers.application.products;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.ProductBundleDetailImpl;
import com.subex.rocps.automation.helpers.application.products.productBundles.ProductBundlesActionImpl;
import com.subex.rocps.automation.helpers.application.settlements.SettlementsDetailsImpl;
import com.subex.rocps.automation.helpers.application.settlements.SettlmentSearchImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ProductBundlesHelper extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> productBundleExcelMap = null;
	protected Map<String, String> productBundleMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected static String bookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String clientPartition;
	protected String colmHdrs;
	protected String productBundleNm;
	protected String productBundleCode;
	protected String productBundleVersion;
	protected int colSize;
	protected int index;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	ProductBundlesActionImpl productBundlesActionobj = new ProductBundlesActionImpl();

	public ProductBundlesHelper()
	{

	}

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public ProductBundlesHelper( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		productBundleExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( productBundleExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/** Constructor : Initializing the excel with occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @param occurence
	 * @throws Exception 
	 */
	public ProductBundlesHelper( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		productBundleExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( productBundleExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		productBundleNm = ExcelHolder.getKey( map, "BundleName" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );

	}

	/*
	 * This method is for Product Bundle screen common method
	 */
	private void productBundleScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Product Bundles" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		productBundleMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Product Type" );
	}

	/*
	 * This method is for Product Bundle screen column validation
	 */
	public void productBundleColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				productBundleScreen();
				colmHdrs = ExcelHolder.getKey( productBundleMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String productBundlesGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : productBundlesGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'Product Bundle screen' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for Product Bundle creation
	 */
	public void createProductBundle() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				productBundleScreen();
				initializeVariable( productBundleMap );
				bookName = workBookName;
				boolean isProductBundlePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_bundle_nameFilter_textID", productBundleNm, "Name" );
				if ( !isProductBundlePresent )
				{
					productBundlesActionobj.clickNewAction( clientPartition );
					ProductBundleDetailImpl productBundleDetailObj = new ProductBundleDetailImpl( productBundleMap );
					productBundleDetailObj.createProductBundle();
					productBundleDetailObj.saveBundle( productBundleNm );
					Log4jHelper.logInfo( "'Product Bundle' is successfully created with name " + productBundleNm );
				}
				else
				{
					Log4jHelper.logInfo( "'Product Bundle is already avilable with name '" + productBundleNm );
				}

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for Product Bundle creation with validate message for currency, Country, Billing Groups
	 */
	public void createBundleWithValidateMessage() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				productBundleScreen();
				initializeVariable( productBundleMap );
				bookName = workBookName;
				boolean isProductBundlePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_bundle_nameFilter_textID", productBundleNm, "Name" );
				if ( !isProductBundlePresent )
				{
					productBundlesActionobj.clickNewAction( clientPartition );
					ProductBundleDetailImpl productBundleDetailObj = new ProductBundleDetailImpl( productBundleMap );
					productBundleDetailObj.createWithValidateMessage();
					productBundleDetailObj.saveBundle( productBundleNm );
					Log4jHelper.logInfo( "'Product Bundle' is successfully created with name " + productBundleNm );
				}
				else
				{
					Log4jHelper.logInfo( "'Product Bundle is already avilable with name '" + productBundleNm );
				}

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}
	/*Method to check the error message with empty data*/

	public void productBundleValidation() throws Exception
	{

		try
		{
			productBundleScreen();
			initializeVariable( productBundleMap );
			for ( index = 0; index < colSize; index++ )
			{

				productBundlesActionobj.clickNewAction( clientPartition );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				ButtonHelper.click( "PS_Detail_bundle_save_BtnId" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean iserrorTextPresent = PopupHelper.isTextPresent( "One or more fields have to be filled or is having invalid value(s)." );
				assertTrue( iserrorTextPresent, "Product Bundle should not saved with empty data" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				if ( PopupHelper.isPresent() )
					ButtonHelper.click( "OKButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				ButtonHelper.click( "CancelButton" );
				Log4jHelper.logInfo( "'Empty Product Bundle creation' assertion is successfully completed" );

			}

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for Product Bundle 'Change status to Accepted Bundle' Action
	 */
	public void changeStatusBundleAction() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				productBundleScreen();
				initializeVariable( productBundleMap );
				productBundleVersion = ExcelHolder.getKey( productBundleMap, "BundleVersion" );
				boolean isProductBundlePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_bundle_nameFilter_textID", productBundleNm, "Name" );

				if ( isProductBundlePresent )
				{
					int row = getRowOfProductBundle( productBundleVersion );
					String bundleStatus = getBundleStatus( row );
					if ( bundleStatus.contentEquals( "Draft" ) )
					{
						GridHelper.clickRow( "SearchGrid", row, "Name" );
						productBundlesActionobj.changeStatusAccpted( "Change Status", "Set Status 'Accepted'" );
						String popUpXpath = GenericHelper.getORProperty( "PS_Detail_bundle_popUp_xpath" );
						ElementHelper.waitForElement( popUpXpath, searchScreenWaitSec );
						String confirmTxtPopup = ElementHelper.getText( popUpXpath );
						assertTrue( confirmTxtPopup.contains( "Accepted" ), "The confirmations message is not matched" );
						ButtonHelper.click( "YesButton" );
						ElementHelper.waitForElementToDisappear( popUpXpath, searchScreenWaitSec );
						GenericHelper.waitForLoadmask( searchScreenWaitSec );
						ButtonHelper.click( "SearchButton" );
						GenericHelper.waitForLoadmask( searchScreenWaitSec );
						psGenericHelper.waitforHeaderElement( "Product Type" );
						bundleStatus = getBundleStatus( row );
						assertEquals( bundleStatus, "Accepted", "Product Bundle status should be in Accepted state after changing the status : " + productBundleNm );
						Log4jHelper.logInfo( "'Product Bundle' is successfully changed to Accepted  for this : " + productBundleNm );
					}
					else
						Log4jHelper.logInfo( "'Product Bundle' is not in draft status but found: '"+ bundleStatus+"' for this : " + productBundleNm );
				}
				else
				{
					Log4jHelper.logInfo( "'Product Bundle is  not avilable with name '" + productBundleNm );
				}

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for Product Bundle 'View' Action
	 */
	public void viewProdutBundleAction() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				productBundleScreen();
				initializeVariable( productBundleMap );
				productBundleCode = ExcelHolder.getKey( productBundleMap, "BundleCode" );
				productBundleVersion = ExcelHolder.getKey( productBundleMap, "BundleVersion" );
				bookName = workBookName;
				boolean isProductBundlePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_bundle_nameFilter_textID", productBundleNm, "Name" );

				if ( isProductBundlePresent )
				{
					int row = getRowOfProductBundle( productBundleVersion );
					String bundleStatus = getBundleStatus( row );
					assertEquals( bundleStatus, "Accepted", "Product Bundle status should be in Draft state : " + productBundleNm );
					GridHelper.clickRow( "SearchGrid", row, "Name" );
					productBundlesActionobj.clickOnBundleAction( "Common Tasks", "View" );
					ProductBundleDetailImpl productBundleDetailObj = new ProductBundleDetailImpl( productBundleMap );
					productBundleDetailObj.viewProductBundle();
					ButtonHelper.click( "CloseButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					Log4jHelper.logInfo( "'Product Bundle' is successfully validated with name " + productBundleNm );
				}
				else
				{
					Log4jHelper.logInfo( "'Product Bundle is not  avilable with name '" + productBundleNm );
				}

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for Product Bundle 'Edit Bundle' Action
	 */
	public void editProdutBundleAction() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				productBundleScreen();
				initializeVariable( productBundleMap );
				productBundleCode = ExcelHolder.getKey( productBundleMap, "BundleCode" );
				productBundleVersion = ExcelHolder.getKey( productBundleMap, "BundleVersion" );
				bookName = workBookName;
				boolean isProductBundlePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_bundle_nameFilter_textID", productBundleNm, "Name" );

				if ( isProductBundlePresent )
				{
					int row = getRowOfProductBundle( productBundleVersion );
					String bundleStatus = getBundleStatus( row );
					assertEquals( bundleStatus, "Draft", "Product Bundle status should be in Draft state : " + productBundleNm );
					GridHelper.clickRow( "SearchGrid", row, "Name" );
					productBundlesActionobj.clickOnBundleAction( "Common Tasks", "Edit" );
					ProductBundleDetailImpl productBundleDetailObj = new ProductBundleDetailImpl( productBundleMap );
					productBundleDetailObj.modifyProductBundle( productBundleNm );
					productBundleDetailObj.saveBundle( productBundleNm );
					Log4jHelper.logInfo( "'Product Bundle' is successfully updated with name " + productBundleNm );
				}
				else
				{
					Log4jHelper.logInfo( "'Product Bundle is not avilable with name '" + productBundleNm );
				}

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for Product Bundle 'create New Bundle' Action
	 */
	public void createNewBundleAction() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				productBundleScreen();
				initializeVariable( productBundleMap );
				productBundleCode = ExcelHolder.getKey( productBundleMap, "BundleCode" );
				productBundleVersion = ExcelHolder.getKey( productBundleMap, "BundleVersion" );
				String newProductBundle = ExcelHolder.getKey( productBundleMap, "BundleNewName" );
				bookName = workBookName;
				boolean isProductBundlePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_bundle_nameFilter_textID", productBundleNm, "Name" );

				if ( isProductBundlePresent )
				{
					int row = getRowOfProductBundle( productBundleVersion );
					GridHelper.clickRow( "SearchGrid", row, "Name" );
					productBundlesActionobj.clickOnBundleAction( "Bundle", "Create New Bundle" );
					ProductBundleDetailImpl productBundleDetailObj = new ProductBundleDetailImpl( productBundleMap );
					productBundleDetailObj.modifyProductBundle( newProductBundle );
					productBundleDetailObj.saveBundle( newProductBundle );
					Log4jHelper.logInfo( "'Product Bundle' is successfully created with name " + newProductBundle );
				}
				else
				{
					Log4jHelper.logInfo( "'Product Bundle is not avilable with name '" + productBundleNm );
				}

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for Product Bundle 'Create New Version' Action
	 */
	public void createNewVersioneAction() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				productBundleScreen();
				initializeVariable( productBundleMap );
				productBundleCode = ExcelHolder.getKey( productBundleMap, "BundleCode" );
				productBundleVersion = ExcelHolder.getKey( productBundleMap, "BundleVersion" );
				bookName = workBookName;
				boolean isProductBundlePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_bundle_nameFilter_textID", productBundleNm, "Name" );

				if ( isProductBundlePresent )
				{
					int row = getRowOfProductBundle( productBundleVersion );
					ArrayList<String> listOfVersion = getAllVersionOfBundle();
					String maxOfVersion = listOfVersion.stream().max( Comparator.comparing( Integer::valueOf ) ).get();
					GridHelper.clickRow( "SearchGrid", row, "Name" );
					productBundlesActionobj.clickOnBundleAction( "Version", "Create New Version" );
					ProductBundleDetailImpl productBundleDetailObj = new ProductBundleDetailImpl( productBundleMap );
					productBundleDetailObj.modifyProductBundle( productBundleNm );
					productBundleDetailObj.saveBundle( productBundleNm );
					String expectedVersion = String.valueOf( Integer.valueOf( maxOfVersion ) + 1 );
					row = getRowOfProductBundle( expectedVersion );
					String actualBundleVersion = getBundleVersion( row );
					assertEquals( actualBundleVersion, expectedVersion, "New version didn't create" );
					Log4jHelper.logInfo( "'Product Bundle' is successfully created with name " + productBundleNm + " and new version :" + expectedVersion );
				}
				else
				{
					Log4jHelper.logInfo( "'Product Bundle is not avilable with name '" + productBundleNm );
				}

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method: for Product Bundle deletion action
	public void productBundleDelete() throws Exception
	{
		productBundleScreen();
		initializeVariable( productBundleMap );
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		boolean isProductBundlePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_bundle_nameFilter_textID", productBundleNm, "Name" );
		assertTrue( isProductBundlePresent, "Product Bundle is not available in the screen with  name: -'" + productBundleNm );
		psGenericHelper.clickDeleteOrUnDeleteAction( productBundleNm, "Name", "Delete" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
		isProductBundlePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_bundle_nameFilter_textID", productBundleNm, "Name" );
		assertTrue( isProductBundlePresent, productBundleNm + " is not present" );
		Log4jHelper.logInfo( "Product Bundle is deleted successfully with the value-:'" + productBundleNm );

	}

	// Method: for Product Bundle Undeletion action
	public void productBundleUnDelete() throws Exception
	{

		productBundleScreen();
		initializeVariable( productBundleMap );
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
		boolean isProductBundlePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_bundle_nameFilter_textID", productBundleNm, "Name" );
		assertTrue( isProductBundlePresent, "Product Bundle is not available in the screen with  name: -'" + productBundleNm );
		psGenericHelper.clickDeleteOrUnDeleteAction( productBundleNm, "Name", "Undelete" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.selectPartitionFilter( clientPartition, "Non Deleted Items" );
		isProductBundlePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_bundle_nameFilter_textID", productBundleNm, "Name" );
		assertTrue( isProductBundlePresent, productBundleNm + " is not present" );
		Log4jHelper.logInfo( "Product Bundle is undeleted successfully with the value:  '" + productBundleNm );

	}

	/*Methid to get row of product bundle*/
	private int getRowOfProductBundle( String version ) throws Exception
	{

		int row = 0;

		if ( GridHelper.getRowCount( "SearchGrid" ) > 1 && ValidationHelper.isNotEmpty( version ) )
			row = GridHelper.getRowNumber( "SearchGrid", version, "Version" );
		else
			row = GridHelper.getRowNumber( "SearchGrid", productBundleNm, "Name" );
		assertTrue( row <= GridHelper.getRowCount( "SearchGrid" ), "This row is not found in Grid :" + row );
		return row;

	}

	// Method: Get the status of Product Bundle
	private String getBundleStatus( int row ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitTime( 2, "" );
		GridHelper.clickRow( "searchGrid", row, "Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		return GridHelper.getCellValue( "searchGrid", row, "Status" );
	}

	// Method: Get the version of Product Bundle
	private String getBundleVersion( int row ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "searchGrid", row, "Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		return GridHelper.getCellValue( "searchGrid", row, "Version" );
	}

	// Method: Get all the version of Product Bundle
	private ArrayList<String> getAllVersionOfBundle() throws Exception
	{
		ArrayList<String> listOfVersion = new ArrayList<String>();
		int noOfRow = GridHelper.getRowCount( "SearchGrid" );
		for ( int i = 0; i < noOfRow; i++ )
			listOfVersion.add( GridHelper.getCellValue( "SearchGrid", i + 1, "Version" ) );
		return listOfVersion;
	}

	public String getWorkbookName()
	{
		return bookName;
	}

}
