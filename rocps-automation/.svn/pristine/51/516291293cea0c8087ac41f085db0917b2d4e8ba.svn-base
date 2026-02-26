package com.subex.rocps.automation.helpers.application.roaming.roamingAgreemConfig;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class RoamingAgreemConfigDetail extends PSAcceptanceTest
{
	protected Map<String, String> roamingAgreeConfigDetailsMap = null;
	protected String tadig;
	protected String technology;
	protected String fieldSelection;
	protected String fieldMapping;

	static PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();

	/** Constructor
	 * @param roamingAgreeConfigDetailsMap
	 */
	public RoamingAgreemConfigDetail( Map<String, String> roamingAgreeConfigDetailsMap )
	{
		this.roamingAgreeConfigDetailsMap = roamingAgreeConfigDetailsMap;
	}

	/*This method is for initialize  basic variable*/
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		tadig = ExcelHolder.getKey( map, "Tadig" );
		technology = ExcelHolder.getKey( map, "Technology" );
		fieldSelection = ExcelHolder.getKey( map, "FieldSelection" );
		fieldMapping = ExcelHolder.getKey( map, "FieldMapping" );
	}

	/*This method is for create Roaming Agreement configuration*/
	public void createRoamingAgreeConfig() throws Exception
	{
		initializeVariable( roamingAgreeConfigDetailsMap );
		configBasicDetails();
		configFieldPanel();
		configFieldMapping();
		configFieldProperties();
		psGenericHelper.detailSave( "PS_Detail_roamingAgreemConfig_save_btnId", tadig, "Tadig" );
	}

	/*This method is for configuration basic details*/
	private void configBasicDetails() throws Exception
	{

		roamingDfnEnititySearch( tadig );
		ComboBoxHelper.select( "PS_Detail_roamingAgreemConfig_technology_comboId", technology );
	}

	/*This method is for configuration field panel */
	private void configFieldPanel() throws Exception
	{
		psGenericHelper.dualListSelectionWithTxtFilter( fieldSelection );
	}

	/*This method is for configuration field mapping*/
	private void configFieldMapping() throws Exception
	{
		String fieldSelectionArr[] = psStringUtils.stringSplitFirstLevel( fieldSelection );
		String fieldMappingArr[] = psStringUtils.stringSplitFirstLevel( fieldMapping );
		if ( fieldSelectionArr.length >= 1 )
		{
			for ( int i = 0; i < fieldSelectionArr.length; i++ )
			{

				ButtonHelper.click( "PS_Detail_roamAgCon_fieldMaping_add_btnId" );
				GridHelper.clickRow( "PS_Detail_roamAgCon_fieldMaping_gridId", i + 1, "Call  Types" );
				GridHelper.clickRow( "PS_Detail_roamAgCon_fieldMaping_gridId", i + 1, "Field" );
				updateGridComboBox( "PS_Detail_roamAgCon_fieldMaping_gridId", "PS_Detail_roamAgCon_fieldMaping_roamingField_comboId", i + 1, "Field", fieldSelectionArr[i] );
				GridHelper.clickRow( "PS_Detail_roamAgCon_fieldMaping_gridId", i + 1, "Call  Types" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				ButtonHelper.click( "PS_Detail_roamAgCon_fieldMaping_confContexType_btnId" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				configContextCallType( fieldMappingArr[i] );

			}
		}

	}

	/*This method is for configuration field properties*/
	private void configFieldProperties() throws Exception
	{
		String fieldSelectionArr[] = psStringUtils.stringSplitFirstLevel( fieldSelection );
		List<String> getKeysOfFieldMappingGrid = getKeysOfFieldMappingGrid();
		Map<String, ArrayList<String>> mapOfieldMappingGrid = ProductUtil.getGridColumnValues( "PS_Detail_roamAgCon_fieldMaping_gridId", "grid_column_header_undefined_", getKeysOfFieldMappingGrid );

		for ( String field : fieldSelectionArr )
		{
			boolean isFieldCelleValuePresent = ProductUtil.isDataPresentInGrid( "PS_Detail_roamAgCon_fieldMaping_gridId", mapOfieldMappingGrid, "Field", field );
			assertTrue( isFieldCelleValuePresent, "Field value are not matched in Field mapping Grid" );
			GridHelper.clickRow( "PS_Detail_roamAgCon_fieldMaping_gridId", field, "Field" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			configfieldProperties( field );
		}
	}

	/*This method is for configuration field properties based on the field type*/
	private void configfieldProperties( String fieldType ) throws Exception
	{
		FieldPropertiesContext fieldPropertiesContext;
		waitForFieldProperty( fieldType );
		switch( fieldType )
		{
		case "IMSI":
			fieldPropertiesContext = new FieldPropertiesContext( new IMSIFieldProperties( roamingAgreeConfigDetailsMap ) );
			fieldPropertiesContext.configFieldProperties();
			break;
		case "MIN":
			fieldPropertiesContext = new FieldPropertiesContext( new MINFieldProperties( roamingAgreeConfigDetailsMap ) );
			fieldPropertiesContext.configFieldProperties();
			break;
		case "MSISDN":
			fieldPropertiesContext = new FieldPropertiesContext( new MSISDNFieldProperties( roamingAgreeConfigDetailsMap ) );
			fieldPropertiesContext.configFieldProperties();
			break;
		case "MDN":
			fieldPropertiesContext = new FieldPropertiesContext( new MDNFieldProperties( roamingAgreeConfigDetailsMap ) );
			fieldPropertiesContext.configFieldProperties();
			break;
		default:
			Log4jHelper.logInfo( "No Field Type is matched" );

		}
	}

	/*This method is for wait for field properties panel*/
	private void waitForFieldProperty( String fieldType ) throws Exception
	{
		String fieldPropertyXpath = GenericHelper.getORProperty( "PS_Detail_roamAgCon_fieldPropType_xpath" );
		fieldPropertyXpath = fieldPropertyXpath.replace( "fieldType", fieldType );
		ElementHelper.waitForElement( fieldPropertyXpath, searchScreenWaitSec );
	}

	/*This method is for configuration context call type*/
	private void configContextCallType( String fieldMapping ) throws Exception
	{
		ElementHelper.waitForElement( "PS_Detail_roamAgCon_confContCallType_detailxpath", searchScreenWaitSec );
		String[] fieldMappingValueArr = new PSStringUtils().stringSplitSecondLevel( fieldMapping );
		ArrayList<String> callTypeCellValues = new ArrayList<String>();
		for ( String fieldMappingValue : fieldMappingValueArr )
		{
			psGenericHelper.dualListSelectionWithTxtFilter( fieldMappingValue );
			callTypeCellValues.add( fieldMappingValue );
		}
		ButtonHelper.click( "PS_Detail_roamAgCon_confContCallType_save_btnId" );
		ElementHelper.waitForElement( "PS_Detail_roamingAgreemConfig_detail_xpath", searchScreenWaitSec );
		validateCallTypeGridValues( callTypeCellValues );
	}

	/*This method is for validate call type cell valye on grid*/
	private void validateCallTypeGridValues( ArrayList<String> callTypeCellValues ) throws Exception
	{
		String callTypeCellValue = callTypeCellValues.toString();
		List<String> getKeysOfFieldMappingGrid = getKeysOfFieldMappingGrid();
		Map<String, ArrayList<String>> mapOfieldMappingGrid = ProductUtil.getGridColumnValues( "PS_Detail_roamAgCon_fieldMaping_gridId", "grid_column_header_undefined_", getKeysOfFieldMappingGrid );
		boolean isCallTypeCelleValuePresent = ProductUtil.isDataPresentInGrid( "PS_Detail_roamAgCon_fieldMaping_gridId", mapOfieldMappingGrid, "Call  Types", callTypeCellValue );
		assertTrue( isCallTypeCelleValuePresent, "Call Types value are not matched in Field mapping Grid" );
	}

	/*This method is for roaming definition entity search*/
	private void roamingDfnEnititySearch( String tadigCode ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforEntityElement();
		roamingDfnEnititySearch( "PS_Detail_roamingAgreemConfig_roamDfn_entityId", tadigCode, "PS_Detail_roamingAgreemConfig_detail_xpath" );

	}

	/*This method is for report and extract definition entity search*/
	public static void roamingDfnEnititySearch( String entiTyId, String tadigCode, String detailsPageXpath ) throws Exception
	{
		EntityComboHelper.clickEntityIcon( entiTyId );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforPopupHeaderElement( "Network" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		PSSearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_roamingDfn_tadig_comboId", tadigCode, "Tadig code" );
		boolean istadigPresent = GridHelper.isValuePresent( "Detail_popUpWindowId", "SearchGrid", tadigCode, "Tadig code" );
		assertTrue( istadigPresent, "Roaming Definition  with tadig value :'" + tadigCode + "'  is not found in 'Roaming Definition  Search' popupScreen " );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "Detail_popUpWindowId", "SearchGrid", 1, "Tadig code" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.clickIfEnabled( "Detail_popUpWindowId", "OKButton" );
		GenericHelper.waitForLoadmask();
		ElementHelper.waitForElement( detailsPageXpath, searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	// grid columns keys of 'Field mapping Grid'
	private List<String> getKeysOfFieldMappingGrid()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "Call  Types" );
		listColumn.add( "Field" );
		return listColumn;
	}

	/*Method is to selection value from combo box*/
	private void selectValueFromComboBox( String arrowXpath, String selectValueXpath, String value ) throws Exception
	{

		
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( int i = 0; i < 5; i++ )
		{
			try
			{
				WebElement arrowElement = ElementHelper.getElement( GenericHelper.getORProperty( arrowXpath ) );
				MouseHelper.click( arrowElement );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				String selectValueFromComboXpath = GenericHelper.getORProperty( selectValueXpath );
				selectValueFromComboXpath = selectValueFromComboXpath.replace( "Value", value );
				WebElement selectValueFromComboElem = ElementHelper.getElement( selectValueFromComboXpath );
				Actions actions = new Actions( driver );
				actions.moveToElement( selectValueFromComboElem );
				MouseHelper.click( selectValueFromComboElem );
				break;
			}
			catch ( Exception e )
			{
				Log4jHelper.logInfo( " This exception " + e.getMessage() + " Roaming Aggrement Configuration at selection of field " );
			}
			finally
			{
				Log4jHelper.logInfo( " Retrying for selection of field  with " + ( i + 1 ) );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
			}
		}

	}

	/*This method is for update grid combo box of field in field mapping*/
	private void updateGridComboBox( String gridId, String comboId, int rowNum, String valueColumnHeader, String value ) throws Exception
	{
		try
		{
			if ( ValidationHelper.isNotEmpty( value ) )
			{
				GridHelper.clickRow( gridId, rowNum, valueColumnHeader );
				if ( !ComboBoxHelper.isPresent( comboId ) )
					GridHelper.clickRow( gridId, rowNum, valueColumnHeader );
				selectValueFromComboBox( "PS_Detail_roamAgCon_fieldMaping_field_arrowXpath", "PS_Detail_roamAgCon_fieldMaping_field_selecterXpath", value );
				GridHelper.click( gridId );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
