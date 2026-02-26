package com.subex.rocps.automation.helpers.application.bcrManagement.bcrProduct;

import java.util.Map;

import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class BCRProductDetails extends PSAcceptanceTest
{
	protected Map<String, String> bcrProdDetailsMap;
	protected String name;
	protected String noOfBestOperators;
	protected String savingThreshold;
	protected String handleIntraNetworkflg;
	protected String intraProductName;
	protected String description;
	protected String applyModifiers;
	protected String setPriority;
	protected String selectedTariff;

	/**Constructor
	 * @param bcrProdDetailsMap
	 */
	public BCRProductDetails( Map<String, String> bcrProdDetailsMap )
	{
		this.bcrProdDetailsMap = bcrProdDetailsMap;
	}

	//Method for initializing instance variables
	public void initializeVariables( Map<String, String> map ) throws Exception
	{

		name = ExcelHolder.getKey( map, "Name" );
		noOfBestOperators = ExcelHolder.getKey( map, "NoOfBestOperators" );
		savingThreshold = ExcelHolder.getKey( map, "SavingThreshold" );
		handleIntraNetworkflg = ExcelHolder.getKey( map, "HandleIntraNetwork" );
		intraProductName = ExcelHolder.getKey( map, "IntraProductName" );
		description = ExcelHolder.getKey( map, "Description" );
		applyModifiers = ExcelHolder.getKey( map, "ApplyModifiers" );
		setPriority = ExcelHolder.getKey( map, "SetPriority" );
		selectedTariff = ExcelHolder.getKey( map, "SelectedTariff" );
	}

	protected void configBasicDetails() throws Exception
	{
		TextBoxHelper.type( description, name );
		TextBoxHelper.type( description, noOfBestOperators );
		TextBoxHelper.type( description, savingThreshold );
		if ( ValidationHelper.isTrue( handleIntraNetworkflg ) )
		{
			CheckBoxHelper.check( applyModifiers );
			TextBoxHelper.type( description, intraProductName );

		}
		else
			assertTrue( TextBoxHelper.isDisabled( applyModifiers ), "Intra Product name should be disabled if 'Handle Intra Network' is not checked" );
		TextBoxHelper.type( description, description );

	}
}
