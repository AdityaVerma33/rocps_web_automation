package com.subex.rocps.automation.utils;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.openqa.selenium.JavascriptExecutor;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.automation.helpers.util.RemoteMachineHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class PSFileHelper extends PSAcceptanceTest
{
	public void copyFileWindowsToLinux( String hostName, String userName, String password, int portNum, String srcPath, String destPath ) throws FileNotFoundException, SftpException, JSchException
	{
		String hostname = hostName;
		String username = userName;
		String pwd = password;
		int port = portNum;
		String copyFrom = srcPath;
		String copyTo = destPath;
		Session session = null;
		ChannelSftp channel = null;

		try
		{
			JSch jsch = new JSch();
			session = jsch.getSession( username, hostname, port );
			session.setPassword( pwd );
			session.setConfig( "StrictHostKeyChecking", "no" );
			session.connect();
			channel = ( ChannelSftp ) session.openChannel( "sftp" );
			channel.connect();
			File localFile = new File( copyFrom );
			channel.cd( copyTo );
			if ( localFile.isFile() )
				channel.put( new FileInputStream( localFile ), localFile.getName() );
			else if ( localFile.isDirectory() )
			{
				String[] files = localFile.list();
				for ( String file : files )
				{
					File srcFile = new File( copyFrom, file );
					channel.put( new FileInputStream( srcFile ), srcFile.getName() );
				}
			}
			channel.disconnect();
			session.disconnect();

		}
		catch ( SftpException e )
		{
			throw e;
		}
		catch ( JSchException e )
		{
			throw e;
		}
		catch ( FileNotFoundException e )
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

	public static String fileDownloadSikuli() throws Exception
	{
		try
		{
			String fileName = fileDownloadSikuli( 5 );

			return fileName;
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public static String fileDownloadSikuli( int waitTimeInSecs ) throws Exception
	{
		try
		{
			// Moving focus to browser
			( ( JavascriptExecutor ) driver ).executeScript( "window.focus();" );

			String fileName = null;
			PSFileDownload fileDownload = null;
			String resolution = GenericHelper.getScreenResolution();
			Log4jHelper.logInfo( "Browser resolution: " + resolution + "\n" );

			if ( resolution.equals( "1920*1080" ) )
			{
				fileDownload = PSFileDownload.R1920_1080;
			}
			else if ( resolution.equals( "1280*1024" ) )
			{
				fileDownload = PSFileDownload.R1280_1024;
			}
			else
			{
				fileDownload = PSFileDownload.Default;
			}

			String downloadImagePath = automationPath + "\\Images\\FileDownload\\";
			Pattern SaveFileRadio = new Pattern( GenericHelper.getPath( automationOS, downloadImagePath + fileDownload.saveFile ) );
			Pattern OKButton = new Pattern( GenericHelper.getPath( automationOS, downloadImagePath + fileDownload.okButton ) );
			Screen screen = new Screen();

			try
			{
				String path = GenericHelper.getPath( automationOS, configProp.getDownloadDirectory() );
				SaveFileRadio.similar( 0.7 );
				screen.wait( SaveFileRadio, 10 );
				screen.click( SaveFileRadio );

				OKButton.similar( 0.7 );
				screen.delayClick( 10 );
				screen.click( OKButton );

				Thread.sleep( waitTimeInSecs * 1000 );
				fileName = getLastModifiedFile( automationOS, path + "\\Client_Downloads" );
			}
			catch ( FindFailed e )
			{
				Log4jHelper.logInfo( "Exception occured while using Sikuli for File Download." );
				fileName = PSFileHelper.fileDownloadRobot( waitTimeInSecs );
			}

			return fileName;
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public static String fileDownloadRobot( int waitTimeInSecs ) throws Exception
	{
		try
		{
			String fileName = null;
			String path = GenericHelper.getPath( automationOS, configProp.getDownloadDirectory() );
			Robot robot = new Robot();

			// Moving focus to browser
			( ( JavascriptExecutor ) driver ).executeScript( "window.focus();" );

			//native key strokes for DOWN and ENTER keys
			robot.keyPress( KeyEvent.VK_DOWN );
			robot.keyPress( KeyEvent.VK_DOWN );
			robot.delay( 200 );

			robot.keyPress( KeyEvent.VK_ENTER );
			robot.keyPress( KeyEvent.VK_ENTER );

			Thread.sleep( waitTimeInSecs * 1000 );
			fileName = getLastModifiedFile( automationOS, path + "\\Client_Downloads" );

			return fileName;
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public static String getLastModifiedFile( String os, String directoryName ) throws Exception
	{
		try
		{
			directoryName = GenericHelper.getPath( os, directoryName );
			String modifiedFile = null;

			if ( os.equalsIgnoreCase( "Windows" ) )
			{
				File dir = new File( directoryName );
				File[] files = dir.listFiles();
				File lastModifiedFile = null;

				if ( files != null && files.length > 0 && files[0] != null )
				{
					lastModifiedFile = files[0];
					for ( int i = 1; i < files.length; i++ )
					{
						if ( lastModifiedFile.lastModified() < files[i].lastModified() && ( files[i].isFile() ) )
							lastModifiedFile = files[i];
					}
				}

				if ( lastModifiedFile != null )
					modifiedFile = lastModifiedFile.toString();
			}
			else
			{
				String command = "cd " + directoryName + " && ls * | head -n 1";
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				String[] cmdResult = remoteMachine.executeScripts( command );

				if ( ValidationHelper.isEmpty( cmdResult ) )
					modifiedFile = null;
				else
					modifiedFile = cmdResult[0];
			}

			return modifiedFile;
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
