package com.subex.automation.helpers.file;

import java.io.File;

import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class DeleteFile extends AcceptanceTest {
	
	public static void deleteFiles(String dataDirPath, String fileName) throws Exception
	{
		try {
			dataDirPath = GenericHelper.getPath(dataDirPath);
			if (fileName.equals("*")) {
				deleteAllFiles(dataDirPath);
			}
			else {
				deleteFile(dataDirPath, fileName);
			}
		} catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void deleteAllFiles(String dataDirPath) throws Exception {
		try {
			File directory = new File(GenericHelper.getPath(dataDirPath));
			File[] files = directory.listFiles();
			File[] dirs = new File[1000];
			int count = 0;
			
			if(files!=null && files.length > 0) {
				for (File file : files) {
					if (file.isDirectory() && file.listFiles() != null) {
						dirs[count] = file;
						count++;
					}
					else {
						delete(file);
					}
				}
				
				dirs = FileHelper.resizeFileArray(dirs);
				
				for (int i = 0; i < dirs.length; i++) {
					deleteAllFiles(dirs[i].toString());
				}
				
				files = directory.listFiles();
				if (files == null || files.length < 1)
					delete(directory);
			}
			else {
				delete(directory);
			}
		} catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void deleteFile(String dataDirPath, String fileName) throws Exception {
		try {
			dataDirPath = GenericHelper.getPath(dataDirPath);
			File file = new File(dataDirPath + "\\" + fileName);
			
	    	if(!file.exists() ) {
    			if (file.isDirectory() && file.listFiles() != null) {
    				deleteAllFiles(dataDirPath);
    			}
    			else {
    				delete(file);
    			}
    		}
	    	else {
	    		Log4jHelper.logInfo("Could not delete " + file + " as file/folder does not exists.");
	    	}
		} catch(Exception e){
			FailureHelper.setErrorMessage(e);
			throw e;
		}
    }
	
	public static void delete(File file) throws Exception {
		try {
			boolean success = file.delete();
		    if (!success) {
		    	FailureHelper.failTest("Failed to delete " + file);
		    }
		} catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}