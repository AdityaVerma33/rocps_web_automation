package com.subex.rocps.automation.helpers.application.products.provStatusGrp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Element;

import org.apache.tools.ant.helper.ProjectHelper2.ElementHandler;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class ProvisionStatusGrpDetailImpl extends PSAcceptanceTest
{
	protected Map<String, String> provStatusGrpDetailsMap = null;
	protected String provStatusGropNm;
	protected String statusName;
	protected String statusNameArr[];
	protected String statusNmInitialFl;
	protected String statusNmInitialFlArr[];
	protected String transitionFrom;
	protected String transitionFromArr[];
	protected String transitionTo;
	protected String transitionToArr[];
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	/**Constructor
	 * @param provStatusGrpDetailsMap
	 */
	public ProvisionStatusGrpDetailImpl( Map<String, String> provStatusGrpDetailsMap )
	{
		this.provStatusGrpDetailsMap = provStatusGrpDetailsMap;
	}

	/*
	 * This method is for initialize variable
	 */
	protected void initializeVariable( Map<String, String> map ) throws Exception
	{
		provStatusGropNm = ExcelHolder.getKey( map, "Name" );
		statusName = ExcelHolder.getKey( map, "StatusName" );
		statusNmInitialFl = ExcelHolder.getKey( map, "StatusInitial" );
		transitionFrom = ExcelHolder.getKey( map, "TransitionFrom" );
		transitionTo = ExcelHolder.getKey( map, "TransitionTo" );

	}

	/*
	 * This method is for configure provision status group
	 */
	public void configProvStatusGrp() throws Exception
	{
		initializeVariable( provStatusGrpDetailsMap );
		configNamePanel();
		configStatusGrid();
		configTransitionsGrid();
		psGenericHelper.detailSave( "PS_Detail_provStatusGrp_save_btnId", provStatusGropNm, "Name" );
	}

	/*
	 * This method is for configure provision status group
	 */
	public void modifyProvStatusGrp() throws Exception
	{
		initializeVariable( provStatusGrpDetailsMap );
		modifyNamePanel();
		configStatusGrid();
		configTransitionsGrid();
		psGenericHelper.detailSave( "PS_Detail_provStatusGrp_save_btnId", provStatusGropNm, "Name" );
	}

	/*
	 * This method is for configure name panel
	 */
	protected void modifyNamePanel() throws Exception
	{
		assertEquals( TextBoxHelper.getValue( "PS_Detail_provStatusGrp_name_txtId" ), provStatusGropNm, "Provision Status group name is not matched" );
	}

	/*
	 * This method is for configure name panel
	 */
	protected void configNamePanel() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_provStatusGrp_name_txtId", provStatusGropNm );
	}

	/*
	 * This method is for configure Status Grid
	 */
	protected void configStatusGrid() throws Exception
	{
		statusNameArr = psStringUtils.stringSplitFirstLevel( statusName );
		statusNmInitialFlArr = psStringUtils.stringSplitFirstLevel( statusNmInitialFl );
		List<String> getKeysOfStatusGrid = getKeysOfStatusGrid();
		for ( int i = 0; i < statusNameArr.length; i++ )
		{
			Map<String, ArrayList<String>> mapOfStatusFieldGrid = psDataComponentHelper.getGridColumnValues( "PS_Detail_provStatusGrp_status_gridId", "grid_column_header_undefined_", getKeysOfStatusGrid );
			boolean isStatusNmPresentInGrid = psDataComponentHelper.isDataPresentInGrid( "PS_Detail_provStatusGrp_status_gridId", mapOfStatusFieldGrid, "Status", statusNameArr[i] );
			if ( !isStatusNmPresentInGrid )
				configStatusGridField( "PS_Detail_provStatusGrp_status_gridId", statusNameArr[i], statusNmInitialFlArr[i] );
			else
			{
				modifyInitialFlgStatusGrid( "PS_Detail_provStatusGrp_status_gridId", statusNameArr[i], statusNmInitialFlArr[i] );
				Log4jHelper.logInfo( "The given Status name is already avilable with name: " + statusNameArr[i] + " in the status grid" );
			}
		}
	}

	/*
	 * This method is for configure Status Grid Gield
	 */
	private void configStatusGridField( String gridId, String statusName, String statusNmFl ) throws Exception
	{
		ButtonHelper.click( "PS_Detail_provStatusGrp_status_add_btnId" );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_provStatusGrp_status_popUpDetailXpath" ), searchScreenWaitSec );
		TextBoxHelper.type( "PS_Detail_provStatusGrp_status_name_txtId", statusName );
		ButtonHelper.click( "PS_Detail_provStatusGrp_status_ok_BtnId" );
		ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_provStatusGrp_status_popUpDetailXpath" ), searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		modifyInitialFlgStatusGrid( gridId, statusName, statusNmFl );

	}

	private void modifyInitialFlgStatusGrid( String gridId, String statusName, String statusNmFl ) throws Exception
	{
		int row = GridHelper.getRowNumber( gridId, statusName, "Status" );
		GridHelper.clickRow( gridId, row, "Status" );
		if ( ValidationHelper.isNotEmpty( statusNmFl ) )
			GridHelper.updateGridCheckBox( gridId, row, "Initial", statusNmFl );
	}

	/*
	 * This method is for configure Transitions Grid
	 */
	protected void configTransitionsGrid() throws Exception
	{
		transitionFromArr = psStringUtils.stringSplitFirstLevel( transitionFrom );
		transitionToArr = psStringUtils.stringSplitFirstLevel( transitionTo );
		for ( int i = 0; i < transitionFromArr.length; i++ )
		{
			String expectedRowValue = "[" + transitionFromArr[i] + ", --->, " + transitionToArr[i] + "]";
			boolean isDataPresent = isDataPresentInGrid( "PS_Detail_provStatusGrp_transitions_gridId", expectedRowValue );
			if ( !isDataPresent )
				configTransitionGridField( "PS_Detail_provStatusGrp_transitions_gridId", transitionFromArr[i], transitionToArr[i] );
			else
				Log4jHelper.logInfo( "The given cell value is already avilable with name: " + expectedRowValue + " in the transitions grid" );

		}
	}

	/*
	 * This method is for configure Transitions Grid Field
	 */
	private void configTransitionGridField( String gridId, String transitionFrom, String transitionTo ) throws Exception
	{
		if ( !transitionFrom.equals( transitionTo ) )
		{
			ButtonHelper.click( "PS_Detail_provStatusGrp_transitions_add_BtnId" );
			ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_provStatusGrp_transitions_popUpDetailXpath" ), searchScreenWaitSec );
			ComboBoxHelper.select( "PS_Detail_provStatusGrp_transitions_from_combId", transitionFrom );
			ComboBoxHelper.select( "PS_Detail_provStatusGrp_transitions_to_combId", transitionTo );
			ButtonHelper.click( "PS_Detail_provStatusGrp_transitions_ok_btnId" );
			ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_provStatusGrp_transitions_popUpDetailXpath" ), searchScreenWaitSec );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}
		else
			FailureHelper.failTest( "The 'from'-:'" + transitionFrom + "' and 'to'-:'" + transitionTo + "' statuses cannot be the same." );
	}

	//Paramters grid columns keys
	private List<String> getKeysOfStatusGrid()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "Status" );
		return listColumn;
	}

	/* Method to check is data present in grid */
	private boolean isDataPresentInGrid( String gridId, String expectedRowValue ) throws Exception
	{
		gridId = GenericHelper.getORProperty( gridId );
		boolean isDataPresentInGrid = false;
		ArrayList<String> rowValueInList = null;
		int rowCount = GridHelper.getRowCount( gridId );
		for ( int i = 0; i < rowCount; i++ )
		{
			rowValueInList = GridHelper.getRowValues( gridId, i + 1 );
			rowValueInList.remove( rowValueInList.size() - 1 );
			String currentRowValue = rowValueInList.toString();
			if ( currentRowValue.equals( expectedRowValue ) )
				return true;
		}
		return isDataPresentInGrid;
	}

}
