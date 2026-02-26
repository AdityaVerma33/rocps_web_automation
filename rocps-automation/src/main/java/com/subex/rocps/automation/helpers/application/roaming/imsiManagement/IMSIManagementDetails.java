package com.subex.rocps.automation.helpers.application.roaming.imsiManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.application.roaming.roamingAgreemConfig.RoamingAgreemConfigDetail;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class IMSIManagementDetails extends PSAcceptanceTest
{
	protected Map<String, String> imsiManagementDetailsMap = null;
	protected String tadigCode;
	protected String mcc;
	protected String mnc;
	protected String msinType;
	protected String msinValue;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();

	/**Constructor
	 * @param imsiManagementDetailsMap
	 */
	public IMSIManagementDetails( Map<String, String> imsiManagementDetailsMap )
	{
		this.imsiManagementDetailsMap = imsiManagementDetailsMap;
	}

	/*This method is for initialize   variable*/
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		tadigCode = ExcelHolder.getKey( map, "Tadig" );
		mcc = ExcelHolder.getKey( map, "MCC" );
		mnc = ExcelHolder.getKey( map, "MNC" );
		msinType = ExcelHolder.getKey( map, "MSIN_Type" );
		msinValue = ExcelHolder.getKey( map, "MSIN_Value" );
	}

	public void createIMSIManagement() throws Exception
	{
		initializeVariable( imsiManagementDetailsMap );
		configureBasicDetails();
		configureMSINPanel();
		psGenericHelper.detailSave( "PS_Detail_IMSIManag_save_btnId", tadigCode, "Roaming Definition" );
	}

	public void modifyIMSIManagement() throws Exception
	{
		initializeVariable( imsiManagementDetailsMap );
		modifyBasicDetails();
		configureMSINPanel();
		psGenericHelper.detailSave( "PS_Detail_IMSIManag_save_btnId", tadigCode, "Roaming Definition" );
	}

	private void configureBasicDetails() throws Exception
	{
		roamingDfnEnititySearch( tadigCode );
		vaildateTextBoxValue( "PS_Detail_IMSIManag_MCC_txtId", mcc );
		vaildateTextBoxValue( "PS_Detail_IMSIManag_MNC_txtId", mnc );

	}

	private void modifyBasicDetails() throws Exception
	{
		assertEquals( EntityComboHelper.getValue( "PS_Detail_IMSIManag_roamingDfn_entityId" ), tadigCode, "Tadig code is not matched" );
		vaildateTextBoxValue( "PS_Detail_IMSIManag_MCC_txtId", mcc );
		vaildateTextBoxValue( "PS_Detail_IMSIManag_MNC_txtId", mnc );

	}

	/*This method is for  configure MSIN Field*/
	private void configureMSINPanel() throws Exception
	{

		String msinTypeArr[] = psStringUtils.stringSplitFirstLevel( msinType );
		String msinValueArr[] = psStringUtils.stringSplitFirstLevel( msinValue );
		List<String> getKeysOfFieldMSINgrid = getKeysOfFieldMSINgrid();
		try
		{
			for ( int i = 0, j = 0; ( i < msinTypeArr.length ) && ( j < msinValueArr.length ); i++, j++ )
			{

				Map<String, ArrayList<String>> mapOfMSINGrid = ProductUtil.getGridColumnValues( "PS_Detail_IMSIManag_MSIN_gridId", "grid_column_header_undefined_", getKeysOfFieldMSINgrid );
				boolean isMsinTypePresent = ProductUtil.isDataPresentInGrid( "PS_Detail_IMSIManag_MSIN_gridId", mapOfMSINGrid, "Type", msinTypeArr[i] );
				if ( !isMsinTypePresent )
				{
					
					configureMSINField( i+1, msinTypeArr[i], msinValueArr[j] );
				}
				else
				{
					Log4jHelper.logInfo( "The given Type of MSIN is already present " + msinTypeArr[i] );
				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;

		}
	}

	/*This method is for  configure MSIN Field*/
	private void configureMSINField( int row, String msinType, String msinValue ) throws Exception
	{
		ButtonHelper.click( "PS_Detail_IMSIManag_MSIN_add_btnId" );
		GridHelper.updateGridComboBox( "PS_Detail_IMSIManag_MSIN_gridId", "PS_Detail_IMSIManag_MSIN_type_comboId", row, "Type", msinType );
		GridHelper.updateGridTextBox( "PS_Detail_IMSIManag_MSIN_gridId", "PS_Detail_IMSIManag_MSIN_value_txtId", row, "MSIN", msinValue );
	}

	/*This method is for roaming definition entity search*/
	private void roamingDfnEnititySearch( String tadigCode ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforEntityElement();
		RoamingAgreemConfigDetail.roamingDfnEnititySearch( "PS_Detail_IMSIManag_roamingDfn_entityId", tadigCode, "PS_Detail_IMSIManag_detail_xpath" );

	}

	/*This method is for validate the textbox value and check for editor is  disable*/
	private void vaildateTextBoxValue( String txtBxId, String txtValue ) throws Exception
	{
		boolean isDisabled = TextBoxHelper.isDisabled( txtBxId );
		assertTrue( isDisabled, " Text box should be disabled" );
		assertEquals( TextBoxHelper.getValue( txtBxId ), txtValue, "Text box value is not matched" );
	}

	// grid columns keys of 'MSIN Grid'
	private List<String> getKeysOfFieldMSINgrid()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "Type" );
		listColumn.add( "MSIN" );
		return listColumn;
	}
}
