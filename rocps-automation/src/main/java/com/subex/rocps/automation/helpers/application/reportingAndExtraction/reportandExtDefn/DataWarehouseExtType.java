package com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class DataWarehouseExtType extends PSAcceptanceTest implements ReportAndExtTypeStrategy
{
	protected Map<String, String> dataWareExtTypeMap = null;

	protected String repAndExtName;
	protected String repAndExtTypeAudit;
	protected String repAndExtTypeFlDelimiter;
	protected String repAndExtTypeNoOfRecord;
	protected String repAndExtTypeFlCompComponent;
	protected String repAndExtTypeFlNmComponent;
	protected String repAndExtTypeSeqNmGrpComponent;
	protected String repAndExtTypeMetaFlGenComponent;
	protected String repAndExtTypeDataFlLocation;
	protected String repAndExtTypeMetaFlLocation;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();

	/**Constructor
	 * @param dataWareExtTypeMap
	 */
	public DataWarehouseExtType( Map<String, String> dataWareExtTypeMap )
	{

		this.dataWareExtTypeMap = dataWareExtTypeMap;
	}

	/*This method is for initialize variable*/
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		repAndExtTypeAudit = ExcelHolder.getKey( map, "DataWareExt_Audit" );
		repAndExtTypeFlDelimiter = ExcelHolder.getKey( map, "DataWareExt_FileDelimiter" );
		repAndExtTypeNoOfRecord = ExcelHolder.getKey( map, "DataWareExt_NoOfRecordsPerFile" );
		repAndExtTypeFlCompComponent = ExcelHolder.getKey( map, "DataWareExt_FileCompComponenet" );
		repAndExtTypeFlNmComponent = ExcelHolder.getKey( map, "DataWareExt_FileNameComponent" );
		repAndExtTypeSeqNmGrpComponent = ExcelHolder.getKey( map, "DataWareExt_SeqNumGrpComponent" );
		repAndExtTypeMetaFlGenComponent = ExcelHolder.getKey( map, "DataWareExt_MetaFileGenComponent" );
		repAndExtTypeDataFlLocation = ExcelHolder.getKey( map, "DataWareExt_DataFileLocation" );
		repAndExtTypeMetaFlLocation = ExcelHolder.getKey( map, "DataWareExt_MetaFileLocation" );
	}

	/*This method is for configure ReportAndExtTypeParameters*/
	@Override
	public void configReportAndExtTypeParameters() throws Exception
	{
		initializeVariable( dataWareExtTypeMap );
		if ( ValidationHelper.isNotEmpty( repAndExtTypeAudit ) )
			auditEnititySearch( repAndExtTypeAudit );
		ComboBoxHelper.select( "PS_Detail_reportAndExtDefn_dataWareExtReport_fileDelimiter_comboID", repAndExtTypeFlDelimiter );
		TextBoxHelper.type( "PS_Detail_reportAndExtDefn_dataWareExtReport_NoRecPerFl_textID", repAndExtTypeNoOfRecord );
		ComboBoxHelper.select( "PS_Detail_reportAndExtDefn_dataWareExtReport_FlComComp_comboID", repAndExtTypeFlCompComponent );
		ComboBoxHelper.select( "PS_Detail_reportAndExtDefn_dataWareExtReport_FlNmComp_comboID", repAndExtTypeFlNmComponent );
		ComboBoxHelper.select( "PS_Detail_reportAndExtDefn_dataWareExtReport_SeqNmGrpComp_comboID", repAndExtTypeSeqNmGrpComponent );
		ComboBoxHelper.select( "PS_Detail_reportAndExtDefn_dataWareExtReport_MetaFlGenCom_comboID", repAndExtTypeMetaFlGenComponent );
		ComboBoxHelper.select( "PS_Detail_reportAndExtDefn_dataWareExtReport_DataFlLoc_comboID", repAndExtTypeDataFlLocation );
		ComboBoxHelper.select( "PS_Detail_reportAndExtDefn_dataWareExtReport_MetaFlLoc_comboID", repAndExtTypeMetaFlLocation );

	}

	/*This method is for audit entity search*/
	private void auditEnititySearch( String auditName ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforEntityElement();
		EntityComboHelper.clickEntityIcon( "PS_Detail_reportAndExtDefn_dataWareExtReport_audit_entityID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforPopupHeaderElement( "Description" );
		int row = SearchGridHelper.gridFilterSearchWithTextBox( "PS_Detail__auditNm_textID", repAndExtTypeAudit, "Name" );
		GridHelper.clickRow( "searchGrid", row, "Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.clickIfEnabled( "OKButton" );
		psGenericHelper.waitforPopupHeaderElementToDisappear( "Description" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/*This method is for  modify audit entity search*/
	private void modifyauditEnititySearch( String repAndExtTypeAudit ) throws Exception
	{

		if ( ValidationHelper.isNotEmpty( repAndExtTypeAudit ) && !EntityComboHelper.getValue( "PS_Detail_reportAndExtDefn_dataWareExtReport_audit_entityID" ).equals( repAndExtTypeAudit ) )
			auditEnititySearch( repAndExtTypeAudit );
	}

	/*This method is for modify ReportAndExtTypeParameters*/
	@Override
	public void modifyReportAndExtTypeParameters() throws Exception
	{
		initializeVariable( dataWareExtTypeMap );
		modifyauditEnititySearch( repAndExtTypeAudit );
		psDataComponentHelper.modifyComboBox( "PS_Detail_reportAndExtDefn_dataWareExtReport_fileDelimiter_comboID", repAndExtTypeFlDelimiter );
		psDataComponentHelper.modifyTextBox( "PS_Detail_reportAndExtDefn_dataWareExtReport_NoRecPerFl_textID", repAndExtTypeNoOfRecord );
		psDataComponentHelper.modifyComboBox( "PS_Detail_reportAndExtDefn_dataWareExtReport_FlComComp_comboID", repAndExtTypeFlCompComponent );
		psDataComponentHelper.modifyComboBox( "PS_Detail_reportAndExtDefn_dataWareExtReport_FlNmComp_comboID", repAndExtTypeFlNmComponent );
		psDataComponentHelper.modifyComboBox( "PS_Detail_reportAndExtDefn_dataWareExtReport_SeqNmGrpComp_comboID", repAndExtTypeSeqNmGrpComponent );
		psDataComponentHelper.modifyComboBox( "PS_Detail_reportAndExtDefn_dataWareExtReport_MetaFlGenCom_comboID", repAndExtTypeMetaFlGenComponent );
		psDataComponentHelper.modifyComboBox( "PS_Detail_reportAndExtDefn_dataWareExtReport_DataFlLoc_comboID", repAndExtTypeDataFlLocation );
		psDataComponentHelper.modifyComboBox( "PS_Detail_reportAndExtDefn_dataWareExtReport_MetaFlLoc_comboID", repAndExtTypeMetaFlLocation );

	}
}
