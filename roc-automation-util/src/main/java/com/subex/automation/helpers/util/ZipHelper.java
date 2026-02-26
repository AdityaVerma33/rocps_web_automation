package com.subex.automation.helpers.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.subex.automation.helpers.selenium.AcceptanceTest;

public class ZipHelper extends AcceptanceTest {
	
	public void gzip(String sourceFileWithPath, String destinationPath) throws Exception {
		FileOutputStream fos = null;
		GZIPOutputStream gos = null;
		FileInputStream fileInput = null;
		try{
            fos = new FileOutputStream(destinationPath);
            gos = new GZIPOutputStream(fos);
            fileInput = new FileInputStream(sourceFileWithPath);
            byte[] buffer = new byte[1024];
            int bytes_read;
            
            while ((bytes_read = fileInput.read(buffer)) > 0) {
            	gos.write(buffer, 0, bytes_read);
            }
 
            fileInput.close(); 
            gos.finish();
		} catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			if (gos != null)
				gos.close();
			if (fos != null)
				fos.close();
			if (fileInput != null)
				fileInput.close();
		}
	}
	
	public void zip(String zipFileFolder, String[] zipFiles, String zipFileName) throws Exception {
		FileOutputStream fos = null;
		ZipOutputStream zos = null;

		try{
			for (int i = 0; i < zipFiles.length; i++) {
				File zipFile = new File(zipFiles[i]);
	            fos = new FileOutputStream(zipFileName);
	            zos = new ZipOutputStream(fos);
	            
	            zos.putNextEntry(new ZipEntry(zipFile.getName()));
                byte[] bytes = Files.readAllBytes(Paths.get(zipFiles[i]));
                zos.write(bytes, 0, bytes.length);
                zos.closeEntry();
			}
		} catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			if (zos != null)
				zos.close();
			if (fos != null)
				fos.close();
		}
	}

	public void zip(String os, String zipFileFolder, String[] zipFiles, String zipFileName) throws Exception {
		try{
			if (os.equalsIgnoreCase("Windows")) {
				zip(zipFileFolder, zipFiles, zipFileName);
			}
			else {
				String zipFile = zipFiles[0];
				for (int i = 1; i < zipFiles.length; i++)
					zipFile = " " + zipFiles[i];
				
				String command = "cd " + zipFileFolder + " && zip " + zipFileName + " " + zipFile;
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				remoteMachine.executeScripts(command, true);
			}
		} catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}