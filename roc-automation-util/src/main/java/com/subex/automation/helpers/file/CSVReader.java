package com.subex.automation.helpers.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class CSVReader extends AcceptanceTest {

	public static String[][] readFile(String os, String fileNameWithPath, String delimiter) throws Exception {
		BufferedReader br = null;
		try {
			fileNameWithPath = GenericHelper.getPath(os, fileNameWithPath);
			String line = null;
			int lines = FileHelper.countLines(fileNameWithPath);
			String[][] data = new String[lines][];
			
			File file = new File(fileNameWithPath);
			if (!file.exists())
				file.createNewFile();
			br = new BufferedReader(new FileReader(file));
			
			int i = 0;
			while ((line = br.readLine()) != null) {
				data[i] = line.split(delimiter);
				i++;
			}
			
			br.close();
			return data;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (br != null)
				br.close();
		}
	}
}