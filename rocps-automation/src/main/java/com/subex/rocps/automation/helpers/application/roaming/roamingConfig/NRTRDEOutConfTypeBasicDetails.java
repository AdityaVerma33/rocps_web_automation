package com.subex.rocps.automation.helpers.application.roaming.roamingConfig;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class NRTRDEOutConfTypeBasicDetails implements ConfigTypeBasicDetailsStrategy
{

	protected Map<String, String> nrtrdeOutConfTypeBasicDetailsMap = null;
	protected String startSeq;
	protected String endNumber;
	protected String restartNumber;
	protected String eventPerFile;
	protected String fileSize;
	protected String dailyFileLimit;
	protected String ftpLoc;
	protected String callAgeTimeFtame;
	protected String exclSMSEventFlg;
	protected String exclGPRSEventFlg;
	protected String cdStartDate;
	protected String cdEndDate;

	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	RoamingConfigBasicDetails roamingConfigBasicDetails = new RoamingConfigBasicDetails();

	/**Constructor
	 * @param nrtrdeOutConfTypeBasicDetailsMap
	 */
	public NRTRDEOutConfTypeBasicDetails( Map<String, String> nrtrdeOutConfTypeBasicDetailsMap )
	{
		this.nrtrdeOutConfTypeBasicDetailsMap = nrtrdeOutConfTypeBasicDetailsMap;
	}

	/*This method is for initialize  = variable*/
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		startSeq = ExcelHolder.getKey( map, "StartSequence" );
		endNumber = ExcelHolder.getKey( map, "EndNumber" );
		restartNumber = ExcelHolder.getKey( map, "RestartNumber" );
		eventPerFile = ExcelHolder.getKey( map, "EventPerFile" );
		fileSize = ExcelHolder.getKey( map, "FileSize" );
		dailyFileLimit = ExcelHolder.getKey( map, "DailyFileLimit" );
		ftpLoc = ExcelHolder.getKey( map, "FTPLocation" );
		callAgeTimeFtame = ExcelHolder.getKey( map, "CallAgeTimeFrame" );
		exclSMSEventFlg = ExcelHolder.getKey( map, "ExclSMSEventFlg" );
		exclGPRSEventFlg = ExcelHolder.getKey( map, "ExclGPRSEventFlg" );
	}

	/*This method is for initialize  date variable*/
	private void initializeDateVariables( Map<String, String> map ) throws Exception
	{
		cdStartDate = ExcelHolder.getKey( map, "CDStartDate" );
		cdEndDate = ExcelHolder.getKey( map, "CDEndDate" );
	}

	@Override
	public void configureBasicDetails() throws Exception
	{
		initializeVariable( nrtrdeOutConfTypeBasicDetailsMap );
		configFileSeqDetails();
		configNRTRDEOutAdvanceDetails();

	}

	private void configFileSeqDetails() throws Exception
	{
		if ( ValidationHelper.isNotEmpty( startSeq ) )
			TextBoxHelper.type( "PS_Detail_roamConfig_BD_NRTRDEOUT_StartSeq_txtId", startSeq );
		else
			assertEquals( TextBoxHelper.getValue( "PS_Detail_roamConfig_BD_NRTRDEOUT_StartSeq_txtId" ), "0", "Start sequence is not matched" );
		if ( ValidationHelper.isNotEmpty( endNumber ) )
			TextBoxHelper.type( "PS_Detail_roamConfig_BD_NRTRDEOUT_endNum_txtId", endNumber );
		else
			assertEquals( TextBoxHelper.getValue( "PS_Detail_roamConfig_BD_NRTRDEOUT_endNum_txtId" ), "9999999", "End Number is not matched" );
		if ( ValidationHelper.isNotEmpty( restartNumber ) )
			TextBoxHelper.type( "PS_Detail_roamConfig_BD_NRTRDEOUT_restartNum_txtId", restartNumber );
		else
			assertEquals( TextBoxHelper.getValue( "PS_Detail_roamConfig_BD_NRTRDEOUT_restartNum_txtId" ), "0", "Restart number is not matched" );
	}

	private void configNRTRDEOutAdvanceDetails() throws Exception
	{
		configDateDetails();
		TextBoxHelper.type( "PS_Detail_roamConfig_BD_NRTRDEOUT_eventPerFl_txtId", eventPerFile );
		TextBoxHelper.type( "PS_Detail_roamConfig_BD_NRTRDEOUT_FlSize_txtId", fileSize );
		TextBoxHelper.type( "PS_Detail_roamConfig_BD_NRTRDEOUT_dailyFlLim_txtId", dailyFileLimit );
		ComboBoxHelper.select( "PS_Detail_roamConfig_BD_NRTRDEOUT_ftpLoc_comboId", ftpLoc );
		if ( ValidationHelper.isNotEmpty( callAgeTimeFtame ) )
			TextBoxHelper.type( "PS_Detail_roamConfig_BD_NRTRDEOUT_callAgeTm_txtId", callAgeTimeFtame );
		else
			assertEquals( TextBoxHelper.getValue( "PS_Detail_roamConfig_BD_NRTRDEOUT_callAgeTm_txtId" ), "4", "'Call  Age  Time  Frame ( Hours)' is not matched" );
		if ( ValidationHelper.isFalse( exclSMSEventFlg ) )
			CheckBoxHelper.uncheck( "PS_Detail_roamConfig_BD_NRTRDEOUT_exclSMS_chkBXId" );
		else
			assertTrue( CheckBoxHelper.isChecked( "PS_Detail_roamConfig_BD_NRTRDEOUT_exclSMS_chkBXId" ), "'Exclude SMS-MT Events' is not checked" );
		if ( ValidationHelper.isNotEmpty( exclGPRSEventFlg ) )
			CheckBoxHelper.uncheck( "PS_Detail_roamConfig_BD_NRTRDEOUT_exclGPRS_chkBXId" );
		else
			assertTrue( CheckBoxHelper.isChecked( "PS_Detail_roamConfig_BD_NRTRDEOUT_exclGPRS_chkBXId" ), "'Exclude GPRS Events' is not checked" );
	}

	private void configDateDetails() throws Exception
	{
		initializeDateVariables( nrtrdeOutConfTypeBasicDetailsMap );
		TextBoxHelper.type( "PS_Detail_roamConfig_nrtrde_cdStartDt", cdStartDate );
		TextBoxHelper.type( "PS_Detail_roamConfig_nrtrde_cdEndDt", cdEndDate );
	}
}
