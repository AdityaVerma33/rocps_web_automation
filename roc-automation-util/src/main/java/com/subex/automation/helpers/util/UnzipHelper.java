package com.subex.automation.helpers.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.subex.automation.helpers.data.StringHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;

public class UnzipHelper extends AcceptanceTest {
	
	final static int BUFFER = 2048;
	
	private void writeFile(String unzipfolder, ZipInputStream zis, String name) throws Exception {
		FileOutputStream fos = null;
		BufferedOutputStream dest = null;
		
		try {
			int count;
			byte data[] = new byte[BUFFER];
			fos = new FileOutputStream(unzipfolder + "\\" + name);
			dest = new BufferedOutputStream(fos, BUFFER);
	
			while ((count = zis.read(data, 0, BUFFER)) != -1)
				dest.write(data, 0, count);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			if (dest != null) {
				dest.flush();
				dest.close();
			}
			if (fos != null)
				fos.close();
		}
	}
	
	public String[] unzip(String zipfile, String unzipfolder) throws Exception {
		FileInputStream fis = null;
		ZipInputStream zis = null;

		try{
			String[] fileNames = new String[1000];
			int i = 0;
			zipfile = zipfile.replace("\n", "");
			File file = new File(zipfile);
			fis = new FileInputStream(file);
			zis = new ZipInputStream(new BufferedInputStream(fis));
			ZipEntry entry;
			File destFile;
			
			while ((entry = zis.getNextEntry()) != null) {
				String entryName = entry.getName();
				fileNames[i] = entryName;
				i++;
				destFile = new File(unzipfolder + "\\" + entryName);
				Log4jHelper.logInfo("Extracting: " + entry);
				
				if(entry.isDirectory() ) {
					destFile.mkdirs();
				}
				else {
					destFile.getParentFile().mkdirs();
					writeFile(unzipfolder, zis, entryName);
				}
			}
			
			fileNames = StringHelper.resizeStringArray(fileNames, i);
			return fileNames;
		} catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			if (zis != null)
				zis.close();
			if (fis != null)
				fis.close();
		}
	}

	public void unzip(String os, String zipfile, String unzipfolder) throws Exception {
		try{
			if (os.equalsIgnoreCase("Windows")) {
				unzip(zipfile, unzipfolder);
			}
			else {
				String command = "cd " + unzipfolder + " && unzip " + zipfile;
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				remoteMachine.executeScripts(command, true);
			}
		} catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}