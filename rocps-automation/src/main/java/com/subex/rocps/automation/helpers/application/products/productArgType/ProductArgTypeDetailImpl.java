package com.subex.rocps.automation.helpers.application.products.productArgType;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class ProductArgTypeDetailImpl extends PSAcceptanceTest
{

	/*This method is for configure basic details*/
	public void configureBasicDetail( String name, String type ) throws Exception
	{
		TextBoxHelper.type( "PS_Detail_prodArgType_name_textID", name );
		ComboBoxHelper.select( "PS_Detail_prodArgType_type_comboID", type );
	}

	/*This method is for configure Product Argument Field*/
	public void configProductArgumentField( int row, String param, String type, String mandatoryFlg ) throws Exception
	{
		ButtonHelper.click( "PS_Detail_prodArgType_add_btnID" );
		GridHelper.updateGridTextBox( "PS_Detail_prodArgType_productField_gridID", "PS_Detail_prodArgType_ArgField_param_textID", row, "Param", param );
		if ( ValidationHelper.isNotEmpty( type ) )
			GridHelper.updateGridComboBox( "PS_Detail_prodArgType_productField_gridID", "PS_Detail_prodArgType_ArgField_type_comboID", row, "Type", type );
		if ( ValidationHelper.isTrue( mandatoryFlg ) )
			GridHelper.updateGridCheckBox( "PS_Detail_prodArgType_productField_gridID", "PS_Detail_prodArgType_ArgField_Mandatory_chckBxID", row, "Mandatory", mandatoryFlg );
	}
}
