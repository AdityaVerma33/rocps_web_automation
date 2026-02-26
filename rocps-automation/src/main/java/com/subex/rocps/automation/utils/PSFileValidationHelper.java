package com.subex.rocps.automation.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Vector;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.automation.helpers.util.UnzipHelper;

public class PSFileValidationHelper extends PSAcceptanceTest {

	PSStringUtils psStringUtils = new PSStringUtils();
	TaskSchedule taskScheduleOb = new TaskSchedule();

	// Method to validate partial file name with file extension,curr date format
	// in the directory
	public void validatePartialFileNameInDir(String path, String partialFileName, String fileExtension,
			String currDateFormat) throws Exception {
		try {
			String filePath = configProp.getDataDirPath() + configProp.getProperty(path);
			if ((applicationOS.equalsIgnoreCase("Linux") || applicationOS.equalsIgnoreCase("Unix")))
				validatePartialFileNameInLinuxDir(filePath, partialFileName, fileExtension, currDateFormat);
			else {
				assertTrue(FileHelper.checkDirectoryExists(filePath), "The given Directory not found :" + filePath);

				String fileExtensionArr[] = psStringUtils.stringSplitFirstLevel(fileExtension);
				String partialFileNameArr[] = psStringUtils.stringSplitFirstLevel(partialFileName);
				String currDate = DateHelper.getCurrentDateTime(currDateFormat);// yyyyMMdd
																				// ,ddMMyyyy
				int j = 0;
				while (j < partialFileNameArr.length) {
					for (int i = 0; i < fileExtensionArr.length; i++) {
						String fileName = currDate + "_" + partialFileNameArr[j];
						String extension = fileExtensionArr[i];
						isFilePresentInDirectory(filePath, fileName, extension);
					}
					j++;
				}

			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
		}
	}

	// Method to validate partial file name with file extension in the directory
	public void validatePartialFileNameInDir(String path, String partialFileName, String fileExtension)
			throws Exception {
		try {
			String filePath = configProp.getDataDirPath() + configProp.getProperty(path);
			if ((applicationOS.equalsIgnoreCase("Linux") || applicationOS.equalsIgnoreCase("Unix")))
				validatePartialFileNameInLinuxDir(filePath, partialFileName, fileExtension);
			else {
				assertTrue(FileHelper.checkDirectoryExists(filePath), "The given Directory not found :" + filePath);

				String fileExtensionArr[] = psStringUtils.stringSplitFirstLevel(fileExtension);
				String partialFileNameArr[] = psStringUtils.stringSplitFirstLevel(partialFileName);
				int j = 0;
				while (j < partialFileNameArr.length) {
					for (int i = 0; i < fileExtensionArr.length; i++) {
						String fileName = partialFileNameArr[j];
						String extension = fileExtensionArr[i];
						isFilePresentInDirectory(filePath, fileName, extension);
					}
					j++;
				}

			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
		}
	}

	// Method to validate the full file name in the directory
	public void validateFullFileNameInDir(String path, String fileName) throws Exception {
		try {
			String filePath = configProp.getDataDirPath() + configProp.getProperty(path);
			if ((applicationOS.equalsIgnoreCase("Linux") || applicationOS.equalsIgnoreCase("Unix")))
				validateFullFileNameInLinux(filePath, fileName);
			else {
				assertTrue(FileHelper.checkDirectoryExists(filePath), "The given Directory not found :" + filePath);

				String fileNameArr[] = psStringUtils.stringSplitFirstLevel(fileName);
				for (String file : fileNameArr) {
					isFilePresentInDirectory(filePath, file);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
		}
	}

	// Method: To check file present in the directory
	private void isFilePresentInDirectory(String path, final String fileName, final String extension) throws Exception {

		File file = new File(path);

		FilenameFilter filter = new FilenameFilter() {

			public boolean accept(File f, String name) {

				boolean fileStatus = (name.endsWith(extension) && name.contains(fileName));
				return fileStatus;
			}
		};
		File[] files = file.listFiles(filter);
		assertTrue(files.length > 0, " File name contains with name '" + fileName + "' and extension:-'" + extension
				+ "' not found in the given directory " + path);
		for (int i = 0; i < files.length; i++) {
			Log4jHelper.logInfo("File name found :- \n " + files[i].getName());
		}
	}

	// Method: To check file present in the directory
	private void isFilePresentInDirectory(String path, final String fileName) throws Exception {

		File file = new File(path);

		FilenameFilter filter = new FilenameFilter() {

			public boolean accept(File f, String name) {

				boolean fileStatus = name.equals(fileName);
				return fileStatus;
			}
		};
		File[] files = file.listFiles(filter);
		assertTrue(files.length > 0,
				" File name  with name '" + fileName + "' not found in the given directory " + path);
		for (int i = 0; i < files.length; i++) {
			Log4jHelper.logInfo("File name found :- \n " + files[i].getName() + " in given path:- " + path);
		}
	}

	// Method: To check file present in linux
	private void isFilePresentInLinux(String path, String fileName, String extension, ChannelSftp channel)
			throws Exception {

		boolean flag = false;
		Vector<LsEntry> vector = channel.ls(path);
		for (LsEntry name : vector) {
			if (name.getFilename().endsWith(extension) && name.getFilename().contains(fileName)) {
				flag = true;
				Log4jHelper.logInfo("File name found  :- \n " + name.getFilename());
			}
		}
		assertTrue(flag, "File name contains with name '" + fileName + "' and extension:-'" + extension
				+ "' not found in the given directory " + path);
	}

	// Method: To check file present in linux
	private void isFilePresentInLinux(String path, String fileName, ChannelSftp channel) throws Exception {

		boolean flag = false;
		Vector<LsEntry> vector = channel.ls(path);
		for (LsEntry name : vector) {
			if (name.getFilename().equals(fileName)) {
				flag = true;
				Log4jHelper.logInfo("File name found  :- \n " + name.getFilename());
			}
		}
		assertTrue(flag, "File name contains with name '" + fileName + "' not found in the given directory " + path);
	}

	// Method: To validate partial file name with file extension,curr date
	// format in the linux
	private void validatePartialFileNameInLinuxDir(String path, String partialFileName, String fileExtension,
			String currDateFormat) throws Exception {
		String hostname = configProp.getRemoteHostname();
		String username = configProp.getRemoteUsername();
		String password = configProp.getRemotePassword();
		int port = configProp.getRemotePortNumber();
		Session session = null;
		ChannelSftp channel = null;
		try {
			JSch jsch = new JSch();

			session = jsch.getSession(username, hostname, port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();

			channel = (ChannelSftp) session.openChannel("sftp");
			channel.connect();

			String fileExtensionArr[] = psStringUtils.stringSplitFirstLevel(fileExtension);
			String partialFileNameArr[] = psStringUtils.stringSplitFirstLevel(partialFileName);
			String currDate = DateHelper.getCurrentDateTime(currDateFormat);// yyyyMMdd
																			// ,ddMMyyyy
			int j = 0;
			while (j < partialFileNameArr.length) {
				for (int i = 0; i < fileExtensionArr.length; i++) {
					String fileName = currDate + "_" + partialFileNameArr[j];
					String extension = fileExtensionArr[i];
					isFilePresentInLinux(path, fileName, extension, channel);

				}
				j++;
			}

		}

		catch (SftpException e) {
			throw e;
		} catch (JSchException e) {
			throw e;
		}

		finally {
			if (channel != null)
				channel.disconnect();
			if (session != null)
				session.disconnect();
		}

	}

	// Method: To validate partial file name with file extension,curr date
	// format in the linux
	private void validatePartialFileNameInLinuxDir(String path, String partialFileName, String fileExtension)
			throws Exception {
		String hostname = configProp.getRemoteHostname();
		String username = configProp.getRemoteUsername();
		String password = configProp.getRemotePassword();
		int port = configProp.getRemotePortNumber();
		Session session = null;
		ChannelSftp channel = null;
		try {
			JSch jsch = new JSch();

			session = jsch.getSession(username, hostname, port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();

			channel = (ChannelSftp) session.openChannel("sftp");
			channel.connect();

			String fileExtensionArr[] = psStringUtils.stringSplitFirstLevel(fileExtension);
			String partialFileNameArr[] = psStringUtils.stringSplitFirstLevel(partialFileName);
			int j = 0;
			while (j < partialFileNameArr.length) {
				for (int i = 0; i < fileExtensionArr.length; i++) {
					String fileName = partialFileNameArr[j];
					String extension = fileExtensionArr[i];
					isFilePresentInLinux(path, fileName, extension, channel);

				}
				j++;
			}

		}

		catch (SftpException e) {
			throw e;
		} catch (JSchException e) {
			throw e;
		}

		finally {
			if (channel != null)
				channel.disconnect();
			if (session != null)
				session.disconnect();
		}

	}

	// Method: To validate full file in the linux
	private void validateFullFileNameInLinux(String path, String fileName) throws Exception {
		String hostname = configProp.getRemoteHostname();
		String username = configProp.getRemoteUsername();
		String password = configProp.getRemotePassword();
		int port = configProp.getRemotePortNumber();
		Session session = null;
		ChannelSftp channel = null;
		try {
			JSch jsch = new JSch();

			session = jsch.getSession(username, hostname, port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();

			channel = (ChannelSftp) session.openChannel("sftp");
			channel.connect();

			String fileNameArr[] = psStringUtils.stringSplitFirstLevel(fileName);
			for (String file : fileNameArr) {
				isFilePresentInLinux(path, fileName, channel);
			}

		}

		catch (SftpException e) {
			throw e;
		} catch (JSchException e) {
			throw e;
		}

		finally {
			if (channel != null)
				channel.disconnect();
			if (session != null)
				session.disconnect();
		}

	}

	public void unzipZipFile( String path, final String fileName ) throws Exception{
		String filePath = configProp.getDataDirPath() + configProp.getProperty(path);
		File file = new File(filePath);

		FilenameFilter filter = new FilenameFilter() {

			public boolean accept(File f, String name) {

				boolean fileStatus = (name.endsWith("zip") && name.contains(fileName));
				return fileStatus;
			}
		};
		File[] files = file.listFiles(filter);
		assertTrue(files.length > 0, " File name contains with name '" + fileName + "' and extension:-'" + "zip"
				+ "' not found in the given directory " + path);
		String folderName=filePath+files[0].getName().substring(0,(files[0].getName().length())-14);
		UnzipHelper unzipper= new UnzipHelper();
		unzipper.unzip(filePath+files[0].getName().trim(), folderName);
		
	}
	
}
