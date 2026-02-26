package com.subex.automation.helpers.component;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.zeroturnaround.zip.commons.FileUtils;

import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.CopyFile;
import com.subex.automation.helpers.file.MakeDirectory;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.automation.helpers.util.RemoteMachineHelper;

public class FileHelper extends AcceptanceTest{
	
	/*
	 * Used to upload file in upload component
	 * @param fileFieldId - Div Id of the text box
	 * @param fileNamewithPath - File to be uploaded with path
	 * @throws AWTException 
	 * @throws Exception 
	 * @throws MalformedURLException 
	 */
	public static void fileUploadRobot(String fileFieldXpath, String fileNamewithPath) throws AWTException, Exception, MalformedURLException {
		try {
			fileFieldXpath = GenericHelper.getORProperty(fileFieldXpath);
			fileNamewithPath = GenericHelper.getPath(automationOS, fileNamewithPath);
			MouseHelper.click(fileFieldXpath);
//			Thread.sleep(1000);
			
			//StringSelection is a class that can be used for copy and paste operations.
			StringSelection stringSelection = new StringSelection(fileNamewithPath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		  
			Robot robot = new Robot();	   
			try {
	            //native key strokes for CTRL, V and ENTER keys
				robot.keyPress(KeyEvent.VK_CONTROL);
	            robot.keyPress(KeyEvent.VK_A);
	            robot.keyPress(KeyEvent.VK_BACK_SPACE);
	            
	            robot.keyPress(KeyEvent.VK_CONTROL);
	            robot.keyPress(KeyEvent.VK_V);
	            robot.keyRelease(KeyEvent.VK_V);
	            robot.keyRelease(KeyEvent.VK_CONTROL);
	            robot.delay(200);
	            
	            robot.keyPress(KeyEvent.VK_ENTER);
	            robot.keyRelease(KeyEvent.VK_ENTER);
	            robot.delay(300);
			}
			catch (Exception exp) {
				FailureHelper.setErrorMessage(exp);
				throw exp;
	        }
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void fileUploadSikuli(String fileFieldXpath, String fileNamewithPath) throws Exception {
		try {
			fileFieldXpath = GenericHelper.getORProperty(fileFieldXpath);
			fileNamewithPath = fileNamewithPath.replace("\\\\", "\\");
			fileNamewithPath = GenericHelper.getPath(automationOS, fileNamewithPath);
			
			// Moving focus to browser
			((JavascriptExecutor) driver).executeScript("window.focus();");
			MouseHelper.click(fileFieldXpath);
			
			FileUpload fileUpload = null;
			String resolution = GenericHelper.getScreenResolution();
			Log4jHelper.logInfo("Browser resolution: " + resolution + "\n");
			
			if (resolution.equals("1920*1080")) {
				fileUpload = FileUpload.R1920_1080;
			} else if (resolution.equals("1280*1024")) {
				fileUpload = FileUpload.R1280_1024;
			} else if (resolution.equals("1280*720")) {
				fileUpload = FileUpload.R1280_720;
			} else {
				fileUpload = FileUpload.Default;
			} 
			
			Screen screen = new Screen();
			Pattern filePath = new Pattern(automationPath + "\\Images\\FileUpload\\" + fileUpload.fileUpload).exact();
			Pattern openButton = new Pattern(automationPath + "\\Images\\FileUpload\\" + fileUpload.openButton).exact();
			try {
				screen.wait(filePath, 10);
				filePath.similar(0.7);
				screen.type(filePath, fileNamewithPath);
				screen.delayClick(200);
				
				if (screen.exists(openButton) != null) {
					openButton.similar(0.7);
					screen.hover();
					screen.click(openButton);
				}
				else {
					screen.type(Key.ENTER);
				}
				screen.delayClick(200);
	         }
	         catch(FindFailed e) {
	        	 Log4jHelper.logInfo("Exception occured while using Sikuli for File Upload.");
//	        	 fileUploadRobot(fileFieldXpath, fileNamewithPath);
	         }
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void setClipboardData(String string) {
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}
	
	public static void fileDownload() throws Exception {
		try {
			WebElement downloadButton = driver.findElement(By.id("messenger-download"));
			String sourceLocation = downloadButton.getAttribute("href");
			String wget_command = "cmd /c wget -P c: " + sourceLocation;
	
			Process exec = Runtime.getRuntime().exec(wget_command);
			int exitVal = exec.waitFor();
			Log4jHelper.logInfo("Exit value: " + exitVal);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String fileDownloadSikuli() throws Exception {
		try {
			String fileName = fileDownloadSikuli(5);
			
			return fileName;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String fileDownloadSikuli(int waitTimeInSecs) throws Exception {
		try {
			// Moving focus to browser
			((JavascriptExecutor) driver).executeScript("window.focus();");
			
			String fileName = null;
			FileDownload fileDownload = null;
			String resolution = GenericHelper.getScreenResolution();
			Log4jHelper.logInfo("Browser resolution: " + resolution + "\n");
			
			if (resolution.equals("1920*1080")) {
				fileDownload = FileDownload.R1920_1080;
			} else if (resolution.equals("1280*1024")) {
				fileDownload = FileDownload.R1280_1024;
			} else {
				fileDownload = FileDownload.Default;
			} 
			
			String downloadImagePath = automationPath + "\\Images\\FileDownload\\";
			Pattern SaveFileRadio = new Pattern(GenericHelper.getPath(automationOS, downloadImagePath + fileDownload.saveFile));
			Pattern OKButton = new Pattern(GenericHelper.getPath(automationOS, downloadImagePath + fileDownload.okButton));
			Screen screen = new Screen();
			
			try {
				String path = GenericHelper.getPath(automationOS, configProp.getDownloadDirectory());
				SaveFileRadio.similar(0.7);
				screen.wait(SaveFileRadio, 10);
				screen.click(SaveFileRadio);
				
				OKButton.similar(0.7);
				screen.delayClick(10);
				screen.click(OKButton);
				
				Thread.sleep(waitTimeInSecs * 1000);
				fileName = getLastModifiedFile(automationOS, path + "\\Client_Downloads");
	         }
	         catch(FindFailed e) {
	        	 Log4jHelper.logInfo("Exception occured while using Sikuli for File Download.");
	        	 fileName = FileHelper.fileDownloadRobot(waitTimeInSecs);
	         }
			
			return fileName;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String fileDownloadRobot() throws Exception {
		try {
			String fileName = fileDownloadRobot(5);
			
			return fileName;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String fileDownloadRobot(int waitTimeInSecs) throws Exception {
		try {
			String fileName = null;
			String path = GenericHelper.getPath(automationOS, configProp.getDownloadDirectory());
			Robot robot = new Robot();
			
			// Moving focus to browser
			((JavascriptExecutor) driver).executeScript("window.focus();");
			
            //native key strokes for DOWN and ENTER keys
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.delay(200);
            
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyPress(KeyEvent.VK_ENTER);
            
            Thread.sleep(waitTimeInSecs * 1000);
			fileName = getLastModifiedFile(automationOS, path + "\\Client_Downloads");
			
			return fileName;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getLastModifiedFile(String os, String directoryName) throws Exception {
		try {
			directoryName = GenericHelper.getPath(os, directoryName);
			String modifiedFile = null;
			
			if (os.equalsIgnoreCase("Windows")) {
				File dir = new File(directoryName);
				File[] files = dir.listFiles();
				File lastModifiedFile = null;
				
				if (files != null && files.length > 0 && files[0] != null) {
					lastModifiedFile = files[0];
					for (int i = 1; i < files.length; i++) {
						if (lastModifiedFile.lastModified() < files[i].lastModified() && (files[i].isFile()))
							lastModifiedFile = files[i];
					}
				}
				
				if (lastModifiedFile != null)
					modifiedFile = lastModifiedFile.toString();
			}
			else {
				String command = "cd " + directoryName + " && ls * | head -n 1";
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				String[] cmdResult = remoteMachine.executeScripts(command);

				if (ValidationHelper.isEmpty(cmdResult))
					modifiedFile = null;
				else
					modifiedFile = cmdResult[0];
			}
			
			return modifiedFile;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getLastModifiedFile(String os, String directoryName, String fileNameFilter) throws Exception {
		try {
			String modifiedFile = null;
			directoryName = GenericHelper.getPath(os, directoryName);
			
			if (os.equalsIgnoreCase("Windows")) {
				final String filter = fileNameFilter;
				File dir = new File(directoryName);
				File[] fileList = dir.listFiles();
				
				if (fileList != null && fileList.length > 0) {
					File[] files = dir.listFiles(new FilenameFilter() {
						@Override
						public boolean accept(File dir, String name) {
							if (name.contains(filter))
								return true;
							else
								return false;
						}
					});
					
					File lastModifiedFile = null;
					if (files != null && files.length > 0 && files[0] != null) {
						lastModifiedFile = files[0];
						for (int i = 1; i < files.length; i++) {
							if (lastModifiedFile.lastModified() < files[i].lastModified() && (files[i].isFile()))
								lastModifiedFile = files[i];
						}
					}
					
					if (lastModifiedFile != null)
						modifiedFile = lastModifiedFile.toString();
				}
			}
			else {
				String command = "cd " + directoryName + " && ls -t1 " + fileNameFilter + "* | head -n 1";
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				String[] cmdResult = remoteMachine.executeScripts(command);

				if (ValidationHelper.isEmpty(cmdResult))
					modifiedFile = null;
				else
					modifiedFile = cmdResult[0];
			}
			
			return modifiedFile;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getLastModifiedFile(String os, String directoryName, String fileNameFilter1, String fileNameFilter2) throws Exception {
		try {
			String modifiedFile = null;
			directoryName = GenericHelper.getPath(os, directoryName);
			
			if (os.equalsIgnoreCase("Windows")) {
				final String filter1 = fileNameFilter1;
				final String filter2 = fileNameFilter2;
				File dir = new File(directoryName);
				File[] fileList = dir.listFiles();
				
				if (fileList != null && fileList.length > 0) {
					File[] files = dir.listFiles(new FilenameFilter() {
						@Override
						public boolean accept(File dir, String name) {
							if (name.contains(filter1) && name.contains(filter2))
								return true;
							else
								return false;
						}
					});
					
					File lastModifiedFile = null;
					if (files != null && files[0] != null) {
						lastModifiedFile = files[0];
						for (int i = 1; i < files.length; i++) {
							if (lastModifiedFile.lastModified() < files[i].lastModified() && (files[i].isFile()))
								lastModifiedFile = files[i];
						}
					}
					
					modifiedFile = lastModifiedFile.toString();
				}
			}
			else {
				String command = "cd " + directoryName + " && ls -t1 " + fileNameFilter1 + "* | head -n 1";
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				String[] cmdResult = remoteMachine.executeScripts(command);
				
				if (ValidationHelper.isEmpty(cmdResult))
					modifiedFile = null;
				else
					modifiedFile = cmdResult[0];
			}
			
			return modifiedFile;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[] getLastModifiedFiles(String os, String directoryName, String fileNameFilter) throws Exception {
		try {
			String[] modifiedFiles = null;
			directoryName = GenericHelper.getPath(os, directoryName);
			
			final String filter = fileNameFilter;
			File dir = new File(directoryName);
			File[] fileList = dir.listFiles();
			
			if (fileList != null && fileList.length > 0) {
				File[] files = dir.listFiles(new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						if (name.contains(filter))
							return true;
						else
							return false;
					}
				});
				
				if (files != null && files.length > 0 && files[0] != null) {
					modifiedFiles = new String[files.length];
					
					for (int i = 0; i < files.length; i++) {
						modifiedFiles[i] = files[i].toString();
					}
				}
			}
			
			return modifiedFiles;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int countLines(BufferedReader br) throws Exception {
		try {
			int lines = 0;
			
			while (br.readLine() != null) {
			   lines++;
			}
			
	        return lines;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int countLines(String fileNameWithPath) throws Exception {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileNameWithPath));
			int lines = 0;
			
			while (br.readLine() != null) {
			   lines++;
			}
		   
	        return lines;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (br != null)
				br.close();
		}
	}
	
	public static ArrayList<String> readFile(String os, String fileNameWithPath) throws Exception {
		BufferedReader br = null;
		try {
			fileNameWithPath = GenericHelper.getPath(os, fileNameWithPath);
			if (os.equalsIgnoreCase("linux")) {
				int index = fileNameWithPath.lastIndexOf("/");
				String sourceDir = fileNameWithPath.substring(0, index);
				String destinationDir = configProp.getDownloadDirectory();
				String sourceFileName = fileNameWithPath.substring(index+1);
				copyFile(os, sourceDir, destinationDir, sourceFileName, sourceFileName, true);
				fileNameWithPath = destinationDir + "\\" + sourceFileName;
			}
			
			File fil = new File(fileNameWithPath);
			
			if (fil.exists()) {
				int lines = countLines(fileNameWithPath);
				ArrayList<String> content = new ArrayList<String>();
			
				br = new BufferedReader(new FileReader(fileNameWithPath));
				for (int i = 0; i < lines; i++) {
					content.add(br.readLine());
				}
				
			    return content;
			}
			else
				return null;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (br != null)
				br.close();
		}
	}
	
	public static String readFileContent(String fileNameWithPath) throws Exception {
		BufferedReader br = null;
		
		try {
			fileNameWithPath = GenericHelper.getPath(automationOS, fileNameWithPath);
			File fil = new File(fileNameWithPath);
			String content = null;
			
			if (fil.exists()) {
				StringBuilder sb = new StringBuilder();
			    
			    int lines = FileHelper.countLines(fileNameWithPath);
			
				br = new BufferedReader(new FileReader(fileNameWithPath));
				for (int i = 0; i < lines; i++) {
					String text = br.readLine();
					if (text != null)
						sb.append(text).append("\n");
				}
				
				content = sb.toString();
				return content;
			}
			else
				return null;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (br != null)
				br.close();
		}
	}
	
	public static String[] readFileContent(String os, String fileNameWithPath) throws Exception {
		BufferedReader br = null;
		
		try {
			fileNameWithPath = GenericHelper.getPath(os, fileNameWithPath);
			if (os.equalsIgnoreCase("linux")) {
				int index = fileNameWithPath.lastIndexOf("/");
				String sourceDir = fileNameWithPath.substring(0, index);
				String destinationDir = configProp.getDownloadDirectory();
				String sourceFileName = fileNameWithPath.substring(index+1);
				copyFile(os, sourceDir, destinationDir, sourceFileName, sourceFileName, true);
				fileNameWithPath = destinationDir + "\\" + sourceFileName;
			}
			
			File fil = new File(fileNameWithPath);
			
			if (fil.exists()) {
				int lines = countLines(fileNameWithPath);
				String[] content = new String[lines];
			
				br = new BufferedReader(new FileReader(fileNameWithPath));
				for (int i = 0; i < lines; i++) {
					String text = br.readLine();
					if (text != null)
						content[i] = text;
				}
				
				if (os.equalsIgnoreCase("linux")) {
					deleteFile(automationOS, fileNameWithPath);
				}
				return content;
			}
			else
				return null;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (br != null)
				br.close();
		}
	}
	
	static public void writeToFile(String fileNameWithPath, String aContents) throws Exception {
		try {
			fileNameWithPath = GenericHelper.getPath(fileNameWithPath);
			File aFile = new File(fileNameWithPath);
			
			if (aFile != null) {
		        if (!aFile.exists()) {
		        	FailureHelper.failTest("File does not exist: " + aFile);
		        }
		        
		        Writer output = new BufferedWriter(new FileWriter(aFile));
			    try {
			    	output.write( aContents );
			    }
			    finally {
			    	output.close();
			    }
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	static public void writeToFile(String fileNameWithPath, ArrayList<String> aContents) throws Exception {
		try {
			fileNameWithPath = GenericHelper.getPath(fileNameWithPath);
			File aFile = new File(fileNameWithPath);
			
			if (aFile != null) {
		        if (!aFile.exists()) {
		        	if (!aFile.createNewFile()) {
		        		FailureHelper.failTest("Could not create file '" + aFile + "'");
		        	}
		        }
		        
		        FileWriter output = new FileWriter(aFile);
			    try {
			    	for (String str : aContents)
			    		output.write( str );
			    }
			    finally {
			    	output.close();
			    }
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	static public void writeToFile(String fileNameWithPath, Map<Integer, ArrayList<String>> aContents) throws Exception {
		try {
			fileNameWithPath = GenericHelper.getPath(fileNameWithPath);
			File aFile = new File(fileNameWithPath);
			
			if (aFile != null) {
		        if (!aFile.exists()) {
		        	if (!aFile.createNewFile()) {
		        		FailureHelper.failTest("Could not create file '" + aFile + "'");
		        	}
		        }
		        
		        FileWriter output = new FileWriter(aFile);
			    try {
			    	Object[] keys = aContents.keySet().toArray();
		    		
			    	for (int i = 0; i < keys.length; i++) {
			    		ArrayList<String> content = aContents.get(keys[i]);
			    		
			    		for (int j = 0; j < content.size(); j++) {
			    			String line = content.get(j).toString();
			    			output.write( line );
			    		}
			    	}
			    }
			    finally {
			    	output.close();
			    }
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	static public void writeToFile(String fileNameWithPath, String[] contents) throws Exception {
		try {
			fileNameWithPath = GenericHelper.getPath(fileNameWithPath);
			File aFile = new File(fileNameWithPath);
			
			if (aFile != null) {
		        if (!aFile.exists()) {
		        	FailureHelper.failTest("File does not exist: " + aFile);
		        }
		        
		        Writer output = new BufferedWriter(new FileWriter(aFile));
			    
		        for (int i = 0; i < contents.length; i++)
		        	output.write( contents[i] );
		        
		        output.close();
			}
			
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void makeDirectory(String directoryName) throws Exception {
		try {
			File file = new File(directoryName);
			if (!file.exists()) {
				if (file.isDirectory())
					file.mkdir();
				else {
					String filename = file.getName();
					int length = directoryName.length() - filename.length();
					if (directoryName.endsWith("\\\\"))
						length = length - 2;
					else if (directoryName.endsWith("\\") || directoryName.endsWith("/"))
						length = length - 1;
					String temp = directoryName.substring(0, length);
					File tFile = new File(temp);
					if (!tFile.exists())
						tFile.mkdirs();
				}
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void makeDirectory(String inDirectory, String directoryName) throws Exception {
		try {
			inDirectory = GenericHelper.getPath(inDirectory);
			MakeDirectory.makeDirectory(inDirectory, directoryName);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void copyFile(String sourceDir, String destinationDir, String sourceFileName, String destinationFileName, boolean failTestCase) throws Exception {
		try {
			if (applicationOS.equalsIgnoreCase("Windows")) {
				Path destinationPath = Paths.get(destinationDir.replace("\"", ""));
					        
		        if (Files.notExists(destinationPath)) {
		        	String dirName = destinationPath.getFileName().toString();
		        	String path = destinationDir.substring(0, (destinationDir.length()-dirName.length()));
		        	makeDirectory(path, dirName);
		        }
	        
	        	CopyFile.copyFile(sourceDir + "\\" + sourceFileName, destinationDir + "\\" + destinationFileName, failTestCase);
			}
	        else {
	        	RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
	        	remoteMachine.copyDirectory(sourceDir + "/" + sourceFileName, destinationDir, destinationFileName, failTestCase);
	        }
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void copyFile(String os, String sourceDir, String destinationDir, String sourceFileName, String destinationFileName, boolean failTestCase) throws Exception {
		try {
			if (os.equalsIgnoreCase("Windows")) {
				Path destinationPath = Paths.get(destinationDir);
					        
		        if (Files.notExists(destinationPath)) {
		        	String dirName = destinationPath.getFileName().toString();
		        	String path = destinationDir.substring(0, (destinationDir.length()-dirName.length()));
		        	makeDirectory(path, dirName);
		        }
	        
	        	CopyFile.copyFile(sourceDir + "\\" + sourceFileName, destinationDir + "\\" + destinationFileName, failTestCase);
			}
	        else {
	        	RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
	        	if (!sourceDir.endsWith("/") && !sourceDir.endsWith("\\"))
	        		sourceDir = sourceDir + "/";
				remoteMachine.copyFile(sourceDir + sourceFileName, destinationDir, destinationFileName, failTestCase);
	        }
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void cleanUpDir(String os, String dirPath, String create) throws Exception {
		try {
			dirPath = GenericHelper.getPath(dirPath);
			
			if (os.equalsIgnoreCase("Windows"))
				FileUtils.deleteDirectory(new File(dirPath));
			else {
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				String command = "rm -rf " + dirPath;
				if (dirPath.endsWith("/"))
					command = command + "*";
				remoteMachine.executeScripts(command, true);
			}
		}
		catch (Exception e) {
			Log4jHelper.logInfo("Error occured + " + e.getMessage());
		}
		finally {
			if (!checkDirectoryExists(os, dirPath) && ValidationHelper.isTrue(create))
				createDir(os, dirPath, create);
		}
	}
	
	public static void cleanUpDir(String os, String dirPath, boolean createDirectory) throws Exception {
		try {
			dirPath = GenericHelper.getPath(dirPath);
			
			if (os.equalsIgnoreCase("Windows"))
				FileUtils.deleteDirectory(new File(dirPath));
			else {
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				String command = "rm -rf " + dirPath;
				remoteMachine.executeScripts(command, false);
			}
		}
		catch (Exception e) {
			Log4jHelper.logInfo("Error occured + " + e.getMessage());
		}
		finally {
			if (!checkDirectoryExists(os, dirPath))
				createDir(os, dirPath, createDirectory);
		}
	}
	
	public static void createDir(String os, String dirPath) throws Exception {
		try {
			File dir = null;
			dirPath = GenericHelper.getPath(dirPath);
			
			if (os.equalsIgnoreCase("Windows")) {
				dir=new File(dirPath);
				dir.mkdir();
			}
			else {
				if (!checkDirectoryExists(os, dirPath)) {
					String command = "mkdir '" + dirPath + "'";
					RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
					String[] cmdResult = remoteMachine.executeScripts(command);
					if (ValidationHelper.isNotEmpty(cmdResult)) {
						FailureHelper.failTest(cmdResult[0]);
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static void createDir(String os, String dirPath, String create) throws Exception {
		try {
			dirPath = GenericHelper.getPath(dirPath);
			
			if(ValidationHelper.isTrue(create)) {
				createDir(os, dirPath);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static void createDir(String os, String dirPath, boolean createDirectory) throws Exception {
		try {
			dirPath = GenericHelper.getPath(dirPath);
			
			if(createDirectory) {
				createDir(os, dirPath);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void deleteFile(String os, String FileWithPath) throws Exception {
		try {
			FileWithPath = GenericHelper.getPath(FileWithPath);
			
			if (os.equalsIgnoreCase("Windows")) {
				File deleteFile=new File(FileWithPath);
				
				if (deleteFile.exists()) {
					boolean deleted = deleteFile.delete();
					if (deleted)
						Log4jHelper.logInfo("File/folder '" + FileWithPath + "' deleted.");
					else
						deleteFile.delete();
				}
				else
					Log4jHelper.logInfo("Could not delete '" + FileWithPath + "' as file/folder does not exists.");
			}
			else {
				if (FileWithPath.contains("\\"))
					FileWithPath = FileWithPath.replace("\\", "/");
				
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				remoteMachine.deleteFile(FileWithPath);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void deleteFileIfExists(String os, String FileWithPath) throws Exception {
		try {
			FileWithPath = GenericHelper.getPath(FileWithPath);
			
			if (os.equalsIgnoreCase("Windows")) {
				File deleteFile=new File(FileWithPath);
				
				if (deleteFile.exists()) {
					boolean deleted = deleteFile.delete();
					if (deleted)
						Log4jHelper.logInfo("File/folder '" + FileWithPath + "' deleted.");
					else
						deleteFile.delete();
				}
			}
			else {
				if (FileWithPath.contains("\\"))
					FileWithPath = FileWithPath.replace("\\", "/");
				
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				remoteMachine.deleteFile(FileWithPath);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static File[] resizeFileArray(File[] fileArray) throws Exception {
		try {
			int count = 0;
			for (int i = 0; i < fileArray.length; i++) {
				if (fileArray[i] != null) {
					count++;
				}
			}
			
			File[] dummy = new File[count];
			System.arraycopy(fileArray, 0, dummy, 0, count);
			fileArray = dummy;
			
			return fileArray;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public static boolean checkDirectoryExists(String dirPath) throws Exception {
		try {
			dirPath = GenericHelper.getPath(dirPath);
			File dir = new File(dirPath);
			
			if (dir.exists())
				return true;
			else
				return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean checkDirectoryExists(String os, String dirPath) throws Exception {
		try {
			boolean directoryExists = false;
			
			if (os.equalsIgnoreCase("Windows")) {
				directoryExists = checkDirectoryExists(dirPath);
			}
			else {
				String command = "[ -d \"" + dirPath + "\" ] && echo \"yes\" || echo \"no\"";
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				String[] cmdResult = remoteMachine.executeScripts(command, false);
				if (ValidationHelper.isNotEmpty(cmdResult)) {
					cmdResult[0] = cmdResult[0].replace("\n", "");
					if (cmdResult[0].equals("yes"))
						directoryExists = true;
				}
			}
			
			return directoryExists;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean checkFileExists(String filePath) throws Exception {
		try {
			if (filePath.startsWith("/")) {
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				return remoteMachine.checkFileExists(filePath);
			}
			else
				return checkDirectoryExists(filePath);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isDirectoryEmpty(String os, String dirPath) throws Exception {
		try {
			boolean directoryEmpty = false;
			
			if (checkDirectoryExists(os, dirPath)) {
				if (os.equalsIgnoreCase("Windows")) {
					File dir = new File(dirPath);
					File[] files = dir.listFiles();
					
					if (files != null && files.length > 0 && files[0].exists())
						directoryEmpty = true;
				}
				else {
					String command = "ls " + dirPath;
					RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
					String[] cmdResult = remoteMachine.executeScripts(command, false);
					if (ValidationHelper.isNotEmpty(cmdResult)) {
						directoryEmpty = true;
					}
				}
			}
			
			return directoryEmpty;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void renameFile(String fileDirectory, String srcFileName, String destFileName) throws Exception {
		try {
			
			File srcFile = new File(GenericHelper.getPath(fileDirectory + "/" + srcFileName));
			File destFile = new File(GenericHelper.getPath(fileDirectory + "/" + destFileName));
			
			if (srcFile.exists()) {
				boolean renamed = srcFile.renameTo(destFile);
				
				if (!renamed) {
					FailureHelper.failTest("Could not rename '" + srcFileName + "' to '" + destFileName +"'");
				}
			}
		
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void renameFile(String os, String fileDirectory, String srcFileName, String destFileName) throws Exception {
		try {
			
			if (os.equalsIgnoreCase("Windows")) {
				renameFile(fileDirectory, srcFileName, destFileName);
			}
			else {
				String srcFile = GenericHelper.getPath(fileDirectory + "/" + srcFileName);
				String destFile = GenericHelper.getPath(fileDirectory + "/" + destFileName);
				
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				if (remoteMachine.checkFileExists(srcFile))
					remoteMachine.executeScripts("mv " + srcFile + " " + destFile);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void updatePropertyFile(String fileNameWithPath, String propertyName, String propertyValue) throws Exception {
		try {
			fileNameWithPath = GenericHelper.getPath(fileNameWithPath);
			File file = new File(fileNameWithPath);
			PropertiesConfiguration conf = new PropertiesConfiguration(file);
			conf.setProperty(propertyName, propertyValue);
			conf.save(new FileWriter(file));
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void updatePropertyFile(String fileNameWithPath, String[] propertyName, String[] propertyValue) throws Exception {
		try {
			fileNameWithPath = GenericHelper.getPath(fileNameWithPath);
			PropertiesConfiguration conf = new PropertiesConfiguration(fileNameWithPath);
			
			for (int i = 0; i < propertyName.length; i++) {
				if (propertyValue[i] == null)
					propertyValue[i] = "";
				conf.setProperty(propertyName[i], propertyValue[i]);
			}
			
			conf.save();
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void updatePropertyFile(String os, String path, String fileName, String[] propertyName, String[] propertyValue) throws Exception {
		try {
			String fileNameWithPath = path + "/" + fileName;
			if (os.equalsIgnoreCase("Windows")) {
				updatePropertyFile(fileNameWithPath, propertyName, propertyValue);
			}
			else {
				String downloadPath = configProp.getDownloadDirectory();
				copyFile(os, path, downloadPath, fileName, fileName, true);
				String copyFile = downloadPath + "/" + fileName;
				updatePropertyFile(copyFile, propertyName, propertyValue);
				copyFile(os, downloadPath, path, fileName, fileName, true);
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void copyFiles(String os, String sourceDir, String destinationDir, boolean failTestCase) throws Exception {
		try {
			if (os.equalsIgnoreCase("Windows")) {
				destinationDir = GenericHelper.getPath(destinationDir);
				Path destinationPath = Paths.get(destinationDir);
					        
		        if (Files.notExists(destinationPath)) {
		        	String dirName = destinationPath.getFileName().toString();
		        	String path = destinationDir.substring(0, (destinationDir.length()-dirName.length()));
		        	makeDirectory(path, dirName);
		        }
	        
	        	CopyFile.copyFile(sourceDir, destinationDir, failTestCase);
			}
	        else {
	        	RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
	        	remoteMachine.copyDirectory(sourceDir + "\\*", destinationDir, "", failTestCase);
	        }


		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void compareFiles(String fileName1, String fileName2) throws Exception {
		BufferedReader reader1 = null;
		BufferedReader reader2 = null;
		FileReader fileReader1 = null;
		FileReader fileReader2 = null;
				
		try {
			fileName1 = GenericHelper.updatePath(fileName1);
			fileName2 = GenericHelper.updatePath(fileName2);
			
			File file1 = new File(fileName1);
			File file2 = new File(fileName2);
			
			fileReader1 = new FileReader(file1);
			fileReader2 = new FileReader(file2);
			
			reader1 = new BufferedReader(fileReader1);
			reader2 = new BufferedReader(fileReader2);
			
			String line1 = null;
			String line2 = null;
			int flag = 1;
			
			while (flag == 1 && (((line1 = reader1.readLine()) != null) && ((line2 = reader2.readLine()) != null))) {
				if (!line1.trim().equals(line2.trim()))
					flag = 0;
			}
			
			if (flag == 0) {
				FailureHelper.failTest("Query result '" + fileName1 + "' does not match with the expected output file '" + fileName2 + "'");
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (reader1 != null)
				reader1.close();
			if (reader2 != null)
				reader2.close();
			if (fileReader1 != null)
				fileReader1.close();
			if (fileReader2 != null)
				fileReader2.close();
		}
	}
	
	public static void compareFiles(String fileName1, String fileName2, String[] linesValuesToIgnore) throws Exception {
		BufferedReader reader1 = null;
		BufferedReader reader2 = null;
		FileReader fileReader1 = null;
		FileReader fileReader2 = null;
				
		try {
			fileName1 = GenericHelper.updatePath(fileName1);
			fileName2 = GenericHelper.updatePath(fileName2);
			
			File file1 = new File(fileName1);
			File file2 = new File(fileName2);
			
			fileReader1 = new FileReader(file1);
			fileReader2 = new FileReader(file2);
			
			reader1 = new BufferedReader(fileReader1);
			reader2 = new BufferedReader(fileReader2);
			
			String line1 = null;
			String line2 = null;
			int flag = 1;
			
			while (flag == 1 && (((line1 = reader1.readLine()) != null) && ((line2 = reader2.readLine()) != null))) {
				if (ValidationHelper.isNotEmpty(linesValuesToIgnore)) {
					if (!line1.trim().equals(line2.trim())) {
						for (int i = 0; i < linesValuesToIgnore.length; i++) {
							if (!line2.contains(linesValuesToIgnore[i])) {
								flag = 0;
								break;
							}
						}
					}
				}
				else {
					if (!line1.trim().equals(line2.trim()))
						flag = 0;
				}
			}
			
			if (flag == 0) {
				FailureHelper.failTest("Query result '" + fileName1 + "' does not match with the expected output file '" + fileName2 + "'");
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (reader1 != null)
				reader1.close();
			if (reader2 != null)
				reader2.close();
			if (fileReader1 != null)
				fileReader1.close();
			if (fileReader2 != null)
				fileReader2.close();
		}
	}
	
	public static void createFile(String fileNameWithPath) throws Exception {
		try {
			makeDirectory(fileNameWithPath);
			
			File file = new File(fileNameWithPath);
			if (!file.exists()) {
				file.createNewFile();
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getFileName(String fileNameWithPath) throws Exception {
		try {
			return new File(fileNameWithPath).getName();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String convertToFilenameFormat(String fileNameWithoutPath) throws Exception {
		try {
			fileNameWithoutPath = fileNameWithoutPath.trim();
			if (fileNameWithoutPath.contains("/"))
				fileNameWithoutPath = fileNameWithoutPath.replace("/", "");
			if (fileNameWithoutPath.contains("\\"))
				fileNameWithoutPath = fileNameWithoutPath.replace("\\", "");
			if (fileNameWithoutPath.contains("'"))
				fileNameWithoutPath = fileNameWithoutPath.replace("'", "");
			if (fileNameWithoutPath.contains("\""))
				fileNameWithoutPath = fileNameWithoutPath.replace("\"", "");
			if (fileNameWithoutPath.contains(" : "))
				fileNameWithoutPath = fileNameWithoutPath.replace(" : ", "_");
			if (fileNameWithoutPath.contains(":"))
				fileNameWithoutPath = fileNameWithoutPath.replace(":", "");
			if (fileNameWithoutPath.contains(" "))
				fileNameWithoutPath = fileNameWithoutPath.replace(" ", "_");
			
			return fileNameWithoutPath.trim();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[] readFile(InputStream stream, BufferedReader br) throws Exception {
		try {
			int lines = countLines(br);
			String[] content = new String[lines];
			
			for (int i = 0; i < lines; i++) {
				String text = br.readLine();
				if (text != null)
					content[i] = text;
			}
			
			return content;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[] readFile(BufferedReader br, int lines) throws Exception {
		try {
			String[] content = new String[lines];
			
			for (int i = 0; i < lines; i++) {
				String text = br.readLine();
				if (text != null)
					content[i] = text;
			}
			
			return content;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getLineNumber(InputStream stream, String value) throws Exception {
		try {
			BufferedReader buffReader = new BufferedReader(new InputStreamReader(stream));
			int lineNumber = -1;
			String line = null;
			
			while ((line = buffReader.readLine()) != null) {
				lineNumber++;
				
				if (line.contains(value)) {
					break;
				}
			}
			
			buffReader.close();
			return lineNumber;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getLineNumber(String os, String path, String fileName, String value) throws Exception {
		try {
			String fileNameWithPath = GenericHelper.getPath(path + "/" + fileName);
			InputStream stream = null;
			
			if (os.equalsIgnoreCase("Windows")) {
				stream = new FileInputStream(fileNameWithPath);
			}
			else {
				stream = sftpChannel.get(fileNameWithPath);
			}
			
			int lineNumber = getLineNumber(stream, value);
			stream.close();
			return lineNumber;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String updateFile(String path, String fileName, int[] lineNumber, String[] propertyValue) throws Exception {
		try {
			if (!path.endsWith("/") || !path.endsWith("\\"))
				path = path + "/";
			InputStream stream = new FileInputStream(new File(path + fileName));
			BufferedReader br = new BufferedReader(new InputStreamReader(stream));
			String content = null;
			
			try {
				int lines = countLines(br);
				stream = new FileInputStream(new File(path + fileName));
				br = new BufferedReader(new InputStreamReader(stream));
				String[] contents = readFile(br, lines);
				
				if (ValidationHelper.isNotEmpty(contents)) {
					for (int i = 0, j = 0; i < contents.length; i++) {
						String text = contents[i];
						
						if ((lineNumber != null && lineNumber.length > j) && i == lineNumber[j]) {
							text = propertyValue[j];
							j++;
						}
						
						if (i == 0)
							content = text + "\n";
						else
							content = content + text + "\n";
					}
				}
			} catch (Exception e) {
				FailureHelper.setErrorMessage(e);
				throw e;
			} finally {
				if (br != null)
					br.close();
				if (stream != null)
					stream.close();
			}
			
			return content;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String updateFile(InputStream stream, BufferedReader br, String path, String fileName, int[] lineNumber, String[] propertyValue) throws Exception {
		try {
			String[] contents = readFile(stream, br);
			String content = null;
			
			if (ValidationHelper.isNotEmpty(contents)) {
				for (int i = 0, j = 0; i < contents.length; i++) {
					String text = contents[i];
					
					if (i == lineNumber[j]) {
						text = propertyValue[j];
						j++;
					}
					
					if (i == 0)
						content = text + "\n";
					else
						content = content + text + "\n";
				}
			}
			return content;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void updateFile(String os, String path, String fileName, int[] lineNumber, String[] propertyValue) throws Exception {
		try {
			String fileNameWithPath = GenericHelper.getPath(path + "/" + fileName);
			
			if (os.equalsIgnoreCase("Windows")) {
				String content = updateFile(path, fileName, lineNumber, propertyValue);
				writeToFile(fileNameWithPath, content);
			}
			else {
				String downloadPath = configProp.getDownloadDirectory();
				copyFile(os, path, downloadPath, fileName, fileName, true);
				String copyFile = downloadPath + "/" + fileName;
				String content = updateFile(downloadPath, fileName, lineNumber, propertyValue);
				writeToFile(copyFile, content);
				copyFile(os, downloadPath, path, fileName, fileName, true);
				
//				if (sftpChannel == null) {
//					RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
//					remoteMachine.createSFTPConnection();
//				}
//				if (!sftpChannel.isConnected())
//					sftpChannel.connect();
//				
//				InputStream stream = sftpChannel.get(fileNameWithPath);
//				BufferedReader br = new BufferedReader(new InputStreamReader(stream));
//				ByteArrayOutputStream bao = new ByteArrayOutputStream();
//				InputStream writer = null;
//				
//				try {
//					String content = updateFile(stream, br, path, fileName, lineNumber, propertyValue);
//					byte[] buff = new byte[8000];
//					int bytesRead = 0;
//					
//					while ((bytesRead = stream.read(buff)) != -1) {
//						bao.write(buff, 0, bytesRead);
//					}
//					
//					Log4jHelper.logInfo(bao.toString("UTF-8"));
//					writer = new ByteArrayInputStream(content.getBytes(Charset.forName("UTF-8")));
//					sftpChannel.cd(path);
//					sftpChannel.put(writer, fileName);
//				} catch (Exception e) {
//					FailureHelper.setErrorMessage(e);
//					throw e;
//				} finally {
//					if (br != null)
//						br.close();
//					if (stream != null)
//						stream.close();
//					if (writer != null)
//						writer.close();
//					if (bao != null)
//						bao.close();
//				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static long getFileSize(String os, String fileNameWithPath) throws Exception {
		try {
			fileNameWithPath = GenericHelper.getPath(fileNameWithPath);
			long size = 0;
			
			if (os.equalsIgnoreCase("Windows")) {
				size = new File(fileNameWithPath).length();
				size = size / (1024 * 1024);
			}
			
			return size;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}