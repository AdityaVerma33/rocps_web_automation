package com.subex.rocps.sprintTestCase.bklg207;

import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class BillDSColumnsDetail extends PSAcceptanceTest
{
	protected Map<String, String> billDSColumnsMap = null;
	protected String tableNameParent;
	protected String columnNameChild;
	protected String columnDetails_Aggregate;
	protected String columnDetails_Statement;
	protected String columnDetails_ColumnName;
	protected String  columnDetails_DisplayName;
	protected String columnDetails_Type;
	protected String columnDetails_NotNull;
	protected String columnDetails_Sort;
	
	BillDSColumnsDetail(Map<String,String> billDSColumnsMap) throws Exception{
		this.billDSColumnsMap=billDSColumnsMap;
		initialzeVariable(billDSColumnsMap);
	}
	
	public void initialzeVariable(Map<String,String> Map) throws Exception {
			tableNameParent=ExcelHolder.getKey( Map, "ColumnTable" );
			columnNameChild=ExcelHolder.getKey( Map, "ColumnToAdd" );
			columnDetails_Statement=ExcelHolder.getKey( Map, "ColumnDetails_Statement" );
			columnDetails_Aggregate=ExcelHolder.getKey( Map, "ColumnDetails_Aggregate" );
			columnDetails_ColumnName=ExcelHolder.getKey( Map, "ColumnDetails_ColumnName" );
			columnDetails_DisplayName=ExcelHolder.getKey( Map, "ColumnDetails_DisplayName" );
			columnDetails_Type=ExcelHolder.getKey( Map, "ColumnDetails_Type" );
			columnDetails_NotNull=ExcelHolder.getKey( Map, "ColumnDetails_NotNull" );
			columnDetails_Sort=ExcelHolder.getKey( Map, "ColumnDetails_Sort" );			
	}
	/*
	 * This method is for Configure Column 
	 */
	public void ConfigureColumnsTab() throws Exception {
		
		TabHelper.gotoTab("PSBillDataset_Detail_Column_ColumnTab");
		selectChildOfParent("availableColumnsTree",tableNameParent,columnNameChild);
		ButtonHelper.click( "PSBillDataset_Detail_Column_addID" );
		
	}
	
	public void selectColumnValues() {
		
		
	}
	
	/*Select child of parent with the child Text Value*/
	public static void selectChildOfParent(String treeName, String parentText, String childText ) throws Exception
	{
	treeName=GenericHelper.getORProperty( treeName );
	String expandXpath = "//div[@id='cell-list-panel']//div[text()='Expand All']";
	ElementHelper.scrollToView( expandXpath, false );
	ElementHelper.waitForClickableElement( expandXpath, searchScreenWaitSec );
	GenericHelper.waitForLoadmask( searchScreenWaitSec );
	MouseHelper.click( expandXpath );
	String childXpath = "//div[starts-with(@id,'TreeName') and contains(text(),'ParentText')]/ancestor::div[@role='treeitem'][1]//div[contains(text(),'ChildText')]";
	childXpath = childXpath.replace( "TreeName", treeName );
	childXpath = childXpath.replace( "ParentText", parentText );
	childXpath = childXpath.replace( "ChildText", childText );
	ElementHelper.scrollToView( childXpath, false );
	ElementHelper.waitForClickableElement( childXpath, searchScreenWaitSec );
	MouseHelper.click( childXpath );



	}
	

}
