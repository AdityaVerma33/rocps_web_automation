package com.subex.automation.helpers.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class CopyFile extends AcceptanceTest {
	
	public static void copyFile(File src, File dest) throws Exception {
		try {

	    	if(src.isDirectory()) {
	
	    		if(!dest.exists() ){
	    		   dest.mkdir();
	    		}
	
	    		String files[] = src.list();
	    		for (String file : files) {
	    		   File srcFile = new File(src, file);
	    		   File destFile = new File(dest, file);
	
	    		   copyFile(srcFile,destFile);
	    		}
	
	    	}
	    	else {
	    		InputStream in = new FileInputStream(src);
	    	    OutputStream out = new FileOutputStream(dest);
	    	    
	    	    try {
		    	    byte[] buffer = new byte[1024];
		
		    	    int length;
		    	    while ((length = in.read(buffer)) > 0){
		    	    	out.write(buffer, 0, length);
		    	    }
	    	    } catch(Exception e) {
	            	FailureHelper.setErrorMessage(e);
	            	throw e;
	            } finally {
	            	if (in != null)
	            		in.close();
	            	if (out != null)
	            		out.close();	
				}
	    	}
		}
        catch(Exception e) {
        	FailureHelper.setErrorMessage(e);
        	throw e;
        }
    }
	
	public static void copyFile(String srcFileNameWithPath, String destFileNameWithPath, boolean failTestCase) throws Exception {
		try {
			
          File f1 = new File(GenericHelper.getPath(srcFileNameWithPath));
          File f2 = new File(GenericHelper.getPath(destFileNameWithPath));
          
          if (!f1.exists()) {
        	  if (failTestCase)
        		  FailureHelper.failTest("Source Directory '" + srcFileNameWithPath + "' does not exists.");
        	  else
        		  Log4jHelper.logInfo("Source Directory '" + srcFileNameWithPath + "' does not exists.");
          }
          else {
        	  copyFile(f1, f2);
        	  Log4jHelper.logInfo("Copied file '" + srcFileNameWithPath + "' as '" + destFileNameWithPath + "'");
          }
		}
        catch(FileNotFoundException ex) {
        	FailureHelper.setErrorMessage(ex);
        	throw ex;
        }
        catch(IOException e) {
        	FailureHelper.setErrorMessage(e);
        	throw e;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	public static void moveFile(String srcFileNameWithPath, String destFileNameWithPath, boolean failTestCase) throws Exception {
		try {
			
          File f1 = new File(GenericHelper.getPath(srcFileNameWithPath));
          File f2 = new File(GenericHelper.getPath(destFileNameWithPath));
          
          if (!f1.exists()) {
        	  if (failTestCase)
        		  FailureHelper.failTest("Source Directory '" + srcFileNameWithPath + "' does not exists.");
        	  else
        		  Log4jHelper.logInfo("Source Directory '" + srcFileNameWithPath + "' does not exists.");
          }
          else {
        	  moveFile(f1, f2);
        	  Log4jHelper.logInfo("Copied file '" + srcFileNameWithPath + "' as '" + destFileNameWithPath + "'");
          }
		}
        catch(FileNotFoundException ex) {
        	FailureHelper.setErrorMessage(ex);
        	throw ex;
        }
        catch(IOException e) {
        	FailureHelper.setErrorMessage(e);
        	throw e;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	public static void moveFile(File src, File dest) throws Exception {
		try {

	    	if(src.isDirectory()) {
	
	    		if(!dest.exists() ){
	    		   dest.mkdir();
	    		}
	
	    		String files[] = src.list();
	    		for (String file : files) {
	    		   File srcFile = new File(src, file);
	    		   File destFile = new File(dest, file);
	
	    		   moveFile(srcFile,destFile);
	    		}
	
	    	}
	    	else {
	    		src.renameTo(dest);
	    	}
		}
        catch(Exception e) {
        	FailureHelper.setErrorMessage(e);
        	throw e;
        }
    }
}