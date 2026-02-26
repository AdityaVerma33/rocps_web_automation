package com.subex.rocps.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class PSCopyFile extends PSAcceptanceTest
{
/*
 *@param- mainDirPath: is used to give the main directory  
 *@param- srcFileNameWithPath, provide the src file name path
 *@param- destFileNameWithPath: provide the destination file name path
 */
	public static void copyFile( String mainDirPath, String srcFileNameWithPath, String destFileNameWithPath ) throws Exception
	{
		try
		{
			if ( ( automationOS.equalsIgnoreCase( "Windows" ) && applicationOS.equalsIgnoreCase( "Linux" ) ) || applicationOS.equalsIgnoreCase( "Unix" ) )
			{
				String hostname = configProp.getRemoteHostname();
				String username = configProp.getRemoteUsername();
				String password = configProp.getRemotePassword();
				int portNumber = configProp.getRemotePortNumber();
				copyFileWindowsToLinux( hostname, username, password, portNumber, mainDirPath, srcFileNameWithPath, destFileNameWithPath );
			}
			else
				copyFile( srcFileNameWithPath, destFileNameWithPath, true );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public static void copyFileWindowsToLinux( String hostName, String userName, String password, int portNum, String mainDirPath, String srcPath, String destPath ) throws Exception
	{
		String hostname = hostName;
		String username = userName;
		String pwd = password;
		int port = portNum;
		String copyFrom = srcPath;
		String copyTo = destPath;
		Session session = null;
		ChannelSftp channel = null;
		String updatedDir = null;
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
			File destFile = new File( copyTo );
			channel.cd( mainDirPath );
			updatedDir = channel.pwd();
			updatedDir = makeDirecInLinux( channel, updatedDir, destFile );
			transferDirToRemote( channel, localFile.getAbsolutePath(), localFile, updatedDir );
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

	public static String makeDirecInLinux( ChannelSftp channel, String currentWorkDir, File destFile ) throws FileNotFoundException, SftpException
	{
		SftpATTRS sftpAtt = null;
		String makeDir = destFile.getName();
		String updatedDir = null;
		try
		{
			sftpAtt = channel.stat( destFile.getName() );

		}
		catch ( Exception e )
		{
			channel.mkdir( destFile.getName() );

		}
		finally
		{
			updatedDir = currentWorkDir + "/" + makeDir;
			channel.cd( updatedDir );
		}
		return updatedDir;
	}

	public static void transferDirToRemote( ChannelSftp channel, String srcFilePath, File localFile, String updatedDir ) throws SftpException, FileNotFoundException
	{
		String[] files = localFile.list();
		for ( String file : files )
		{
			File srcFile = new File( srcFilePath, file );
			if ( srcFile.isFile() )
			{
				transferFileToRemote( channel, srcFile, updatedDir );
			}
			else if ( srcFile.isDirectory() )
			{
				updatedDir = makeDirecInLinux( channel, updatedDir, srcFile );

				transferDirToRemote( channel, srcFile.getAbsolutePath(), srcFile, updatedDir );
				channel.cd( ".." );
				updatedDir = channel.pwd();
			}
		}
	}

	public static void transferFileToRemote( ChannelSftp channel, File localFile, String remoteDir ) throws SftpException, FileNotFoundException
	{
		channel.cd( remoteDir );
		channel.put( new FileInputStream( localFile ), localFile.getName(), ChannelSftp.OVERWRITE );
	}

	public static void copyFile( File src, File dest ) throws Exception
	{
		try
		{

			if ( src.isDirectory() )
			{

				if ( !dest.exists() )
				{
					dest.mkdir();
				}

				String files[] = src.list();
				for ( String file : files )
				{
					File srcFile = new File( src, file );
					File destFile = new File( dest, file );

					copyFile( srcFile, destFile );
				}

			}
			else
			{
				InputStream in = new FileInputStream( src );
				OutputStream out = new FileOutputStream( dest );

				try
				{
					byte[] buffer = new byte[1024];

					int length;
					while ( ( length = in.read( buffer ) ) > 0 )
					{
						out.write( buffer, 0, length );
					}
				}
				catch ( Exception e )
				{
					FailureHelper.setErrorMessage( e );
					throw e;
				}
				finally
				{
					if ( in != null )
						in.close();
					if ( out != null )
						out.close();
				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public static void copyFile( String srcFileNameWithPath, String destFileNameWithPath, boolean failTestCase ) throws Exception
	{
		try
		{

			File f1 = new File( GenericHelper.getPath( srcFileNameWithPath ) );
			File f2 = new File( GenericHelper.getPath( destFileNameWithPath ) );

			if ( !f1.exists() )
			{
				if ( failTestCase )
					FailureHelper.failTest( "Source Directory '" + srcFileNameWithPath + "' does not exists." );
				else
					Log4jHelper.logInfo( "Source Directory '" + srcFileNameWithPath + "' does not exists." );
			}
			else
			{
				copyFile( f1, f2 );
				Log4jHelper.logInfo( "Copied file '" + srcFileNameWithPath + "' as '" + destFileNameWithPath + "'" );
			}
		}
		catch ( FileNotFoundException ex )
		{
			FailureHelper.setErrorMessage( ex );
			throw ex;
		}
		catch ( IOException e )
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

	public static void moveFile( String srcFileNameWithPath, String destFileNameWithPath, boolean failTestCase ) throws Exception
	{
		try
		{

			File f1 = new File( GenericHelper.getPath( srcFileNameWithPath ) );
			File f2 = new File( GenericHelper.getPath( destFileNameWithPath ) );

			if ( !f1.exists() )
			{
				if ( failTestCase )
					FailureHelper.failTest( "Source Directory '" + srcFileNameWithPath + "' does not exists." );
				else
					Log4jHelper.logInfo( "Source Directory '" + srcFileNameWithPath + "' does not exists." );
			}
			else
			{
				moveFile( f1, f2 );
				Log4jHelper.logInfo( "Copied file '" + srcFileNameWithPath + "' as '" + destFileNameWithPath + "'" );
			}
		}
		catch ( FileNotFoundException ex )
		{
			FailureHelper.setErrorMessage( ex );
			throw ex;
		}
		catch ( IOException e )
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

	public static void moveFile( File src, File dest ) throws Exception
	{
		try
		{

			if ( src.isDirectory() )
			{

				if ( !dest.exists() )
				{
					dest.mkdir();
				}

				String files[] = src.list();
				for ( String file : files )
				{
					File srcFile = new File( src, file );
					File destFile = new File( dest, file );

					moveFile( srcFile, destFile );
				}

			}
			else
			{
				src.renameTo( dest );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
