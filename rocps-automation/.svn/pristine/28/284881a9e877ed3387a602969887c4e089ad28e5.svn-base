package com.subex.rocps.automation.helpers.application.system;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntitySearchHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.RadioHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class PSUsageGroupHelper extends PSAcceptanceTest
{
	/*
	 * This method is used to edit Usage Group
	 */

	public void editUsageGroup( String path, String WorkbookName, String workSheetName, String testCaseName, int occurance ) throws Exception
	{
		try
		{
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurance );

			for ( int i = 0; i < excelData.get( "Partition" ).size(); i++ )
			{

				String usageGroupName = excelData.get( "Name" ).get( i );
				String detailScreenTitle = "";
				if ( excelData.get( "Partition Name" ).size() > 0 )
				{
					String[] partitionName = testData.getStringValue( excelData.get( "Partition Name" ).get( i ), firstLevelDelimiter );
					String[] partitionSuffix = testData.getStringValue( excelData.get( "Table Suffix" ).get( i ), firstLevelDelimiter );
					String[] parititionFrom = testData.getStringValue( excelData.get( "Partition From" ).get( i ), firstLevelDelimiter );
					String[] partitionTo = testData.getStringValue( excelData.get( "Partition To" ).get( i ), firstLevelDelimiter );
					String[] applyIndex = testData.getStringValue( excelData.get( "Apply Indexes" ).get( i ), firstLevelDelimiter );

					String subpartitionType = excelData.get( "Subpartition Type" ).get( i );
					String subpartitionFunction = excelData.get( "Subpartition Function" ).get( i );
					String[] subpartitionName = testData.getStringValue( excelData.get( "Subpartition Name" ).get( i ), firstLevelDelimiter );
					String[] subpartitionSuffix = testData.getStringValue( excelData.get( "Subpartition Table Suffix" ).get( i ), firstLevelDelimiter );
					String[] subpartitionFrom = testData.getStringValue( excelData.get( "Subpartition From" ).get( i ), firstLevelDelimiter );
					String[] subpartitionTo = testData.getStringValue( excelData.get( "Subpartition To" ).get( i ), firstLevelDelimiter );

					detailScreenTitle = editUsageGroup( usageGroupName, partitionName, partitionSuffix, parititionFrom, partitionTo, applyIndex );
					updateSubpartition( subpartitionType, subpartitionFunction, subpartitionName, subpartitionSuffix, subpartitionFrom, subpartitionTo );
					saveUsageGroup( usageGroupName, detailScreenTitle );
				}
				else
					FailureHelper.failTest( "Partition details are not provided in the test data excel" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	private String editUsageGroup( String usageGroupName, String[] partitionName, String[] partitionSuffix, String[] parititionFrom, String[] partitionTo, String[] applyIndex ) throws Exception
	{
		try
		{
			int row = navigateToUsageGroup( usageGroupName );

			String detailScreenTitle = NavigationHelper.navigateToEdit( "SearchGrid", row, "UsageGroup_Name" );

			for ( int i = 0; i < partitionName.length; i++ )
			{
				boolean toApplyIndex = ValidationHelper.isTrue( applyIndex[i] );
				manualUsagePartition( partitionName[i], partitionSuffix[i], parititionFrom[i], partitionTo[i], toApplyIndex );
			}
			return detailScreenTitle;

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	private int navigateToUsageGroup( String usageGroupName ) throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Usage Groups", "Usage Group Search" );
			int row = SearchGridHelper.gridFilterSearchWithTextBox( "UsageGroup_Name", usageGroupName, "Name" );

			return row;
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	private void saveUsageGroup( String usageGroupName, String detailScreenTitle ) throws Exception
	{
		try
		{
			ButtonHelper.click( "SaveButton" );
			GenericHelper.waitForLoadmask( configProp.getCustomScreenWaitSec() );

			if ( LabelHelper.isTextPresent( "Saved Usage Group but failed to refresh physical Usage Partitions." ) )
			{
				ButtonHelper.click( "CancelButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );

				if ( ButtonHelper.isPresent( "DiscardButton" ) )
				{
					ButtonHelper.click( "DiscardButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
				}
			}

			assertTrue( LabelHelper.isTitleNotPresent( detailScreenTitle ), "Usage Group save did not happen." );
			ButtonHelper.click( "SearchButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertTrue( GridHelper.isValuePresent( "SearchGrid", usageGroupName, "Name" ), "Value '" + usageGroupName + "' is not found in grid." );
			Log4jHelper.logInfo( "Usage Group '" + usageGroupName + "' created" );
		}
		catch ( AssertionError e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	private void manualUsagePartition( String name, String suffix, String from, String to, boolean applyIndex ) throws Exception
	{
		try
		{
			int rowNum = GridHelper.getRowNumber( "UsageGroup_UsagePartition_Grid", name, "Name" );
			if ( rowNum > 0 )
			{
				GridHelper.clickRow( "UsageGroup_UsagePartition_Grid", rowNum, "Name" );
				ButtonHelper.click( "UsageGroup_UsagePartition_Edit" );
				GenericHelper.waitForLoadmask( detailScreenWaitSec );

				if ( ButtonHelper.isPresent( "YesButton" ) )
				{
					ButtonHelper.click( "YesButton" );
					GenericHelper.waitForLoadmask( detailScreenWaitSec );
				}
			}
			else
			{
				ButtonHelper.click( "UsageGroup_UsagePartition_Add" );
			}

			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			TextBoxHelper.type( "UsageGroup_ManualPartition_Name", name );
			TextBoxHelper.type( "UsageGroup_ManualPartition_TableSuffix", suffix );
			TextBoxHelper.type( "UsageGroup_ManualPartition_From", from );
			TextBoxHelper.type( "UsageGroup_ManualPartition_To", to );

			if ( !applyIndex )
				CheckBoxHelper.uncheck( "UsageGroup_ManualPartition_ApplyIndex" );
			ButtonHelper.click( "UsageGroup_ManualPartition_OK" );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			assertTrue( GridHelper.isValuePresent( "UsageGroup_UsagePartition_Grid", name, "Name" ), "Value '" + name + "' is not found in grid." );
		}
		catch ( AssertionError e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	private void updateSubpartition( String type, String function, String[] name, String[] suffix, String[] from, String[] to ) throws Exception
	{
		try
		{
			if ( ValidationHelper.isNotEmpty( name ) )
			{
				ComboBoxHelper.select( "UsageGroup_Subpartition_Type", type );
				ComboBoxHelper.select( "UsageGroup_Subpartition_Function", function );

				for ( int i = 0; i < name.length; i++ )
				{
					int row = GridHelper.getRowNumber( "UsageGroup_Subpartition_Grid", name[i], "Name" );

					if ( row == 0 )
					{
						ButtonHelper.click( "UsageGroup_Subpartition_Add" );
						row = GridHelper.getRowCount( "UsageGroup_Subpartition_Grid" );
					}

					GridHelper.updateGridTextBox( "UsageGroup_Subpartition_Grid", "UsageGroup_Subpartition_Name", row, "Name", "To", name[i] );

					GridHelper.updateGridTextBox( "UsageGroup_Subpartition_Grid", "UsageGroup_Subpartition_TableSuffix", row, "Table Suffix", "Name", suffix[i] );

					GridHelper.updateGridTextBox( "UsageGroup_Subpartition_Grid", "UsageGroup_Subpartition_From", row, "From", "Name", from[i] );

					GridHelper.updateGridTextBox( "UsageGroup_Subpartition_Grid", "UsageGroup_Subpartition_To", row, "To", "Name", to[i] );
				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public void refreshUsagePartitioned( String path, String WorkbookName, String workSheetName, String testCaseName, int occurance ) throws Exception
	{
		try
		{
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurance );

			for ( int i = 0; i < excelData.get( "Usage Group Name" ).size(); i++ )
			{
				
				String usageGroupName = excelData.get( "Usage Group Name" ).get( i );
				int row = navigateToUsageGroup(usageGroupName);
				GridHelper.clickRow( "SearchGrid", row, "Name" );
				NavigationHelper.navigateToAction( "Usage Actions", "Refresh Usage Partitions" );
				GenericHelper.waitForElement( "RefreshUsagePartitions_Msg", configProp.getCustomScreenWaitSec() );
				ButtonHelper.click( "OKButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				Log4jHelper.logInfo( "Successfully refreshed Usage Groups." );

			}

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}