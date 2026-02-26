package com.subex.rocps.automation.helpers.application.roaming.roamingConfig;

import java.util.Map;

import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class TapOutConfigTypeBasicDetails implements ConfigTypeBasicDetailsStrategy
{

	protected Map<String, String> tapOutConfTypeBasicDetailsMap = null;
	protected String cdStartSeq;
	protected String tdStartSeq;
	protected String endNumber;
	protected String restartNumber;
	protected String ftpLocation;
	protected String fileSize;
	protected String notifiCutoffTime;
	protected String disableNotificFlg;
	protected String inclZeroRatedFlg;
	RoamingConfigBasicDetails roamingConfigBasicDetails = new RoamingConfigBasicDetails();

	/**Constructor
	 * @param tapOutConfTypeBasicDetailsMap
	 */
	public TapOutConfigTypeBasicDetails( Map<String, String> tapOutConfTypeBasicDetailsMap )
	{
		this.tapOutConfTypeBasicDetailsMap = tapOutConfTypeBasicDetailsMap;
	}
	/*This method is for initialize  = variable*/
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		cdStartSeq = ExcelHolder.getKey( map, "CDStartSequence" );
		tdStartSeq = ExcelHolder.getKey( map, "TDStartSequence" );
		endNumber = ExcelHolder.getKey( map, "EndNumber" );
		restartNumber = ExcelHolder.getKey( map, "RestartNumber" );
		ftpLocation = ExcelHolder.getKey( map, "FTPLocation" );
		fileSize = ExcelHolder.getKey( map, "FileSize" );
		notifiCutoffTime = ExcelHolder.getKey( map, "NotifcationCutoffTime" );
		disableNotificFlg = ExcelHolder.getKey( map, "DisableNotificationFlg" );
		inclZeroRatedFlg = ExcelHolder.getKey( map, "InclZeroRatedRecordFlg" );
	}

	@Override
	public void configureBasicDetails() throws Exception
	{
		initializeVariable( tapOutConfTypeBasicDetailsMap );
		configFileSeqDetails();
		configTAPOutAdvanceDetails();

	}

	private void configFileSeqDetails() throws Exception
	{
		roamingConfigBasicDetails.configFileSeqDetails( cdStartSeq, tdStartSeq, endNumber, restartNumber );
	}

	private void configTAPOutAdvanceDetails() throws Exception
	{
		ComboBoxHelper.select( "PS_Detail_roamConfig_BD_TAPOUT_ftpLoc_comboId", ftpLocation );
		TextBoxHelper.type( "PS_Detail_roamConfig_BD_TAPOUT_fileSize_txtId", fileSize );
		TextBoxHelper.type( "PS_Detail_roamConfig_BD_TAPOUT_notifiCutOff_txtId", notifiCutoffTime );
		if ( ValidationHelper.isTrue( disableNotificFlg ) )
			CheckBoxHelper.check( "PS_Detail_roamConfig_BD_TAPOUT_disabNot_chckBxId" );
		if ( ValidationHelper.isTrue( inclZeroRatedFlg ) )
			CheckBoxHelper.check( "PS_Detail_roamConfig_BD_TAPOUT_inclZero_chckBxId" );
	}
}
