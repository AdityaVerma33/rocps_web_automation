package com.subex.rocps.automation.helpers.application.bulkentityexport;

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
import com.subex.automation.helpers.util.UnzipHelper;
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

public class BulkEntityFileHelper extends PSAcceptanceTest {
	PSStringUtils psStringUtils = new PSStringUtils();
	TaskSchedule taskScheduleOb = new TaskSchedule();
	File[] files=null;


	// Method: To check file present in the directory
	public void isFilePresentInDirectory(String path, final String fileName, final String extension) throws Exception {

		File file = new File(path);

		FilenameFilter filter = new FilenameFilter() {

			public boolean accept(File f, String name) {

				boolean fileStatus = (name.endsWith(extension) && name.contains(fileName));
				return fileStatus;
			}
		};
		this.files = file.listFiles(filter);
		assertTrue(files.length > 0, " File name contains with name '" + fileName + "' and extension:-'" + extension
				+ "' not found in the given directory " + path);
		for (int i = 0; i < files.length; i++) {
			Log4jHelper.logInfo("File name found :- \n " + files[i].getName());
			
		}
	}

	// Method: To check file present in linux
	public void isFilePresentInLinux(String path, String fileName, String extension, ChannelSftp channel)
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
	
	public void unzipFile(String fileLocation) throws Exception{
		UnzipHelper unzip = new UnzipHelper();
		unzip.unzip(applicationOS, files[0].getName(), fileLocation);
	}
	
	

	}