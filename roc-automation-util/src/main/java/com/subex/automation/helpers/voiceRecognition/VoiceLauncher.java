package com.subex.automation.helpers.voiceRecognition;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

import com.subex.automation.helpers.file.ExcelWriter;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class VoiceLauncher {

	public static Configuration configuration;
	
	private void setConfiguration() throws Exception {
		try {
			// Configuration Object
	        configuration = new Configuration();
	 
	        // Set path to the acoustic model.
	        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
	        // Set path to the dictionary.
	        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
	        // Set path to the language model.
	        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void main(String[] args) throws Exception {
		try {
			setConfiguration();
	        
	        LiveSpeechRecognizer recognize = new LiveSpeechRecognizer(configuration);
	        recognize.startRecognition(true);
	        SpeechResult result;
	 
	        Map<Integer, Object[]> contents = new HashMap<Integer, Object[]>();
	        Object[] header = new Object[] {"TC Name", "TC Description", "TC Step Name", "Component", "Wrapper ID",
											"ID or Text or Xpath", "Component Action", "Value"};
	        int length = header.length;
	        		
	        Object[] values = new Object[length];
	        int vCount = 0;
	        boolean saveExcelFl = false;
	        boolean stopFl = false;
	        int count = 1;
	        contents.put(0, header);
	        
	        while ((result = recognize.getResult()) != null) {
	            String command = result.getHypothesis();
	            String work = null;
	            Process p;
 
//            if(command.equalsIgnoreCase("open file manager")) {
//                work = "explorer";
//            } else if (command.equalsIgnoreCase("close file manager")) {
//                work = "Taskkill /IM explorer.exe /F";
//            } else if (command.equalsIgnoreCase("open browser")) {
//                work = "chrome";
//            } else if (command.equalsIgnoreCase("close browser")) {
//                work = "Taskkill /IM chrome.exe /F";
//            } else
	            
	            command = command.toLowerCase();
	            switch (command) {
				case "open excel":
					work = "excel";
					break;
	
				case "save as":
					saveExcelFl = true;
					break;
					
				case "close excel":
					work = "Taskkill /IM excel.exe /F";
					break;
					
				case "stop":
				case "stop recording":
					stopFl = true;
					break;
					
				case "empty":
				case "leave empty":
					values[vCount] = "";
	            	vCount++;
					break;
					
				case "next line":
					if (vCount < length) {
						for (int j = vCount ; j < length; j++) {
							values[j] = "";
						}
					}
					
					contents.put(count, values);
					count++;
					values = new Object[length];
					vCount = 0;
					break;
				
				case "next":
				case "next cell":
				case "next column":
				default:
					values[vCount] = command;
	            	vCount++;
					break;
				}
	            
	           if (stopFl) {
	            	break;
	           }
	            
	           if(work != null) {
	        	   p = Runtime.getRuntime().exec(work);
	               int exitCode = p.exitValue();
	                
	               if (!(exitCode == 0)) {
	                	
	               }
	           }
	            
	           if (saveExcelFl) {
	        	   String filePath = System.getProperty("user.dir") + "\\src\\main\\resources";
	        	   int scriptNumber = getScriptNumber(filePath);
	        	   String workBookName = filePath + "\\TestScript" + scriptNumber + ".xlsx";
		           ExcelWriter.writeToExcel(workBookName, "Test_Scripts", contents);
	        	   saveExcelFl = false;
	        	   Log4jHelper.logInfo("Test Script file '" + workBookName + "' created.");
	           }
	        }
	        
	        recognize.stopRecognition();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public int getScriptNumber(String filePath) throws Exception {
		try {
			File dir = new File( filePath );
			FilenameFilter filter = new ScriptFileFilter();
			File[] listFolders = dir.listFiles( filter );
			
			if (listFolders != null && listFolders.length > 0 && listFolders[0] != null) {
				String folder1Name = listFolders[0].getName();
				int runCount = Integer.parseInt(folder1Name.substring(3));
				
				if (listFolders.length > 1 && listFolders[1] != null) {
					for (int i = 1; i < listFolders.length; i++) {
						String folder2Name = listFolders[i].getName();
						int folder2 = Integer.parseInt(folder2Name.substring(3));
						
						if (folder2 > runCount)
							runCount = folder2;
					}
				}
				
				return runCount+1;
			}
			else
				return 1;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}

class ScriptFileFilter implements FilenameFilter {

	@Override
	public boolean accept( File directory, String fileName ) {
		if ( fileName.startsWith( "TestScripts" ) )
		{
			return true;
		}
		return false;
	}
}