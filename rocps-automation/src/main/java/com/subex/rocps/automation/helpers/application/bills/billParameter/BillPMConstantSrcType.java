package com.subex.rocps.automation.helpers.application.bills.billParameter;

import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class BillPMConstantSrcType implements BillPmSourceTypeStrategy
{
	protected Map<String, String> BillPMConstantSrcTypeMap = null;
	protected String constantDataType;
	protected String[] constantDataTypeArr;
	protected String constantValue;
	protected String[] constantValueArr;
	protected String token;
	protected String[] tokenArr;
	protected String relativePath;
	protected String[] relativePathArr;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();

	/*
	 *Constructor
	 */
	public BillPMConstantSrcType( Map<String, String> BillPMConstantSrcTypeMap ) throws Exception
	{

		this.BillPMConstantSrcTypeMap = BillPMConstantSrcTypeMap;
		initializeInstanceVariable( BillPMConstantSrcTypeMap );
		intialiseArray();
	}

	/*
	 * This method is to initialize the array 
	 */
	protected void intialiseArray() throws Exception
	{
		constantDataTypeArr = psStringUtils.stringSplitFirstLevel( constantDataType );
		constantValueArr = psStringUtils.stringSplitFirstLevel( constantValue );
		tokenArr = psStringUtils.stringSplitFirstLevel( token );
		relativePathArr = psStringUtils.stringSplitFirstLevel( relativePath );

	}

	/*
	 * This method is to initialize the variables
	 */
	public void initializeInstanceVariable( Map<String, String> map )
	{

		try
		{
			constantDataType = ExcelHolder.getKey( map, "ConstantDataType" );
			constantValue = ExcelHolder.getKey( map, "ConstantValue" );
			token = ExcelHolder.getKey( map, "Token" );
			relativePath = ExcelHolder.getKey( map, "RelativePath" );
		}
		catch ( Exception e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * This method is for Configure Source Type details
	 */
	public void configSourceTypeDetails( Map<String, String> map ) throws Exception
	{
		configConstantDetails();
	}

	/*
	 * This method is for configure constant bill parameter 
	 */
	public void configConstantDetails()
	{

		try
		{
			boolean ispopup = PopupHelper.isPresent( "PSDetail_Bill_Parameter_popUp_GridId" );

			if ( ispopup )
			{
				ButtonHelper.click( "PSDetail_Bill_Parameter_popUp_Yes_ButtonId" );
				ComboBoxHelper.select( "PSDetail_Bill_Parameter_Constant_DataType_comboId", constantDataType );

				if ( constantDataType.equals( "Context Directory" ) )
				{

					ButtonHelper.click( "PSDetail_Bill_Parameter_Context_Directory_ButtonId" );
					ElementHelper.waitForElement( "PSDetail_Bill_Parameter_Context_directory_popup_GridId", 300 );

					ComboBoxHelper.select( "PSDetail_Bill_Parameter_ContextDirectory_token_ComboId", token );
					TextBoxHelper.type( "PSDetail_Bill_Parameter_ContextDirectory_filepath_textId", relativePath );
					ButtonHelper.click( "PSDetail_Bill_Parameter_ContextDirectory_Save_ButtonId" );
					ElementHelper.waitForElementToDisappear( "PSDetail_Bill_Parameter_Context_directory_popup_GridId", 300 );

				}
				else
				{

					TextBoxHelper.type( "PSDetail_Bill_Parameter_ConstandValue_textId", constantValue );
				}

			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}

	}

}
