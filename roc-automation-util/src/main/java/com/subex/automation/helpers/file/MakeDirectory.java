package com.subex.automation.helpers.file;

import java.io.File;

import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class MakeDirectory extends AcceptanceTest {

	
	public static void makeDirectory(String inDirectory, String directoryName) throws Exception {
		try {
	          String strDirectoy = null;
	          if (ValidationHelper.isEmpty(directoryName))
	        	  strDirectoy = GenericHelper.getPath(inDirectory);
	          else
	        	  strDirectoy = GenericHelper.getPath(inDirectory )+ "\\" + directoryName;
	          
	          File file = new File(strDirectoy);
	          boolean exists = file.exists();
	          
	          if (exists){
	        	  Log4jHelper.logInfo("Directory: " + strDirectoy + " is already present");
	          }  
	          else {
	        	  boolean success = (new File(strDirectoy)).mkdir();
		          if (success) 
		        	  Log4jHelper.logInfo("Directory: " + strDirectoy + " created");
		          else
		        	  Log4jHelper.logInfo("Directory: " + strDirectoy + " is NOT created");
	          }
		} 
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

}