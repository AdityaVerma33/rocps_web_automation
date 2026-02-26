package com.subex.rocps.automation.helpers.application.xdrextraction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Vector;

import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.automation.helpers.util.RemoteMachineHelper;

import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSFileHelper;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class XdrFileHelper extends PSAcceptanceTest
{
	PSStringUtils psStringUtils = new PSStringUtils();
	TaskSchedule taskScheduleOb = new TaskSchedule();

	//Method to Read the file  in the directory
	public void readFile( String path, String fileExtension, String cdrDate ) throws Exception
	{
		try
		{
			String fileXdrFullPath = configProp.getDataDirPath() + configProp.getProperty( path );
			if ( ( applicationOS.equalsIgnoreCase( "Linux" ) || applicationOS.equalsIgnoreCase( "Unix" ) ) )
				readFileInLinux( fileXdrFullPath, fileExtension, cdrDate );
			else
			{
				assertTrue( FileHelper.checkDirectoryExists( fileXdrFullPath ), "The given Directory not found :" + fileXdrFullPath );

				String fileExtensionArr[] = psStringUtils.stringSplitFirstLevel( fileExtension );
				String cdrDateArr[] = psStringUtils.stringSplitFirstLevel( cdrDate );
				String currDate = DateHelper.getCurrentDateTime( "yyyyMMdd" );
				int j = 0;
				while ( j < cdrDateArr.length )
				{
					for ( int i = 0; i < fileExtensionArr.length; i++ )
					{
						String fileName = currDate + "_" + cdrDateArr[j];
						String extension = fileExtensionArr[i];
						isFilePresentInDirectory( fileXdrFullPath, fileName, extension );
					}
					j++;
				}

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
		}
	}

	//Method: To  check file present in the directory
	private void isFilePresentInDirectory( String path, final String fileName, final String extension ) throws Exception
	{

		File file = new File( path );

		FilenameFilter filter = new FilenameFilter()
		{

			public boolean accept( File f, String name )
			{

				boolean fileStatus = ( name.endsWith( extension ) && name.contains( fileName ) );
				return fileStatus;
			}
		};
		File[] files = file.listFiles( filter );
		assertTrue( files.length > 0, " File name contains with name '" + fileName + "' and extension:-'" + extension + "' not found in the given directory " + path );
		for ( int i = 0; i < files.length; i++ )
		{
			Log4jHelper.logInfo( "File name found :- \n " + files[i].getName() );
		}
	}

	//Method: To  check file present in linux
	private void isFilePresentInLinux( String path, String fileName, String extension, ChannelSftp channel ) throws Exception
	{

		boolean flag = false;
		Vector<LsEntry> vector = channel.ls( path );
		for ( LsEntry name : vector )
		{
			if ( name.getFilename().endsWith( extension ) && name.getFilename().contains( fileName ) )
			{
				flag = true;
				Log4jHelper.logInfo( "File name found  :- \n " + name.getFilename() );
			}
		}
		assertTrue( flag, "File name contains with name '" + fileName + "' and extension:-'" + extension + "' not found in the given directory " + path );
	}

	//Method: To  read file in the linux
	private void readFileInLinux( String path, String fileExtension, String cdrDate ) throws Exception
	{
		String hostname = configProp.getRemoteHostname();
		String username = configProp.getRemoteUsername();
		String password = configProp.getRemotePassword();
		int port = configProp.getRemotePortNumber();
		Session session = null;
		ChannelSftp channel = null;
		try
		{
			JSch jsch = new JSch();

			session = jsch.getSession( username, hostname, port );
			session.setPassword( password );
			session.setConfig( "StrictHostKeyChecking", "no" );
			session.connect();

			channel = ( ChannelSftp ) session.openChannel( "sftp" );
			channel.connect();

			String fileExtensionArr[] = psStringUtils.stringSplitFirstLevel( fileExtension );
			String cdrDateArr[] = psStringUtils.stringSplitFirstLevel( cdrDate );
			String currDate = DateHelper.getCurrentDateTime( "yyyyMMdd" );
			int j = 0;
			while ( j < cdrDateArr.length )
			{
				for ( int i = 0; i < fileExtensionArr.length; i++ )
				{
					String fileName = currDate + "_" + cdrDateArr[j];
					String extension = fileExtensionArr[i];
					isFilePresentInLinux( path, fileName, extension, channel );

				}
				j++;
			}

		}

		catch ( SftpException e )
		{
			throw e;
		}
		catch ( JSchException e )
		{
			throw e;
		}

		finally
		{
			if ( channel != null )
				channel.disconnect();
			if ( session != null )
				session.disconnect();
		}

	}
}