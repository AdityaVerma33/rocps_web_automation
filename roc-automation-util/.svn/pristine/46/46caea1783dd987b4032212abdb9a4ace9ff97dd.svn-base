package com.subex.automation.helpers.file;

import java.io.File;

import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.report.Log4jHelper;

public class RenameFile implements Runnable {
	public String strSourceDir;
	public String strSourceFileName;
	public String strRenameFileName;
	public String strScriptsDirectory;

	public RenameFile(String sourceDir, String sourceFileName, String renameFileName) {
		this.strSourceDir = sourceDir;
		this.strSourceFileName = sourceFileName;
		this.strRenameFileName = renameFileName;
	}

	public void run() {
		try {
			File f1 = new File(GenericHelper.getPath(strSourceDir + "\\" + strSourceFileName));
			File f2 = new File(GenericHelper.getPath(strSourceDir + "\\" + strRenameFileName));
			  
			if (f1.renameTo(f2))
				Log4jHelper.logInfo("Renamed file " + strSourceFileName + " to " + strRenameFileName);
			else
				Log4jHelper.logInfo("Renaming file " + strSourceFileName + " to " + strRenameFileName + " failed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}