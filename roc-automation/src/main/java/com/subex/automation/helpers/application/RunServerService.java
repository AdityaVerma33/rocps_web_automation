package com.subex.automation.helpers.application;

import com.subex.automation.helpers.config.PropertyReader;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.automation.helpers.util.RemoteMachineHelper;

public class RunServerService implements Runnable {

	public PropertyReader propConfig;
	public String deployPath;
	
	private boolean updateJava = false;
	private String exportJava = "export JAVA_HOME=JAVAPATH && export PATH=$JAVA_HOME/bin:$PATH";
	
	public  RunServerService(PropertyReader propConfig) throws Exception {
		this.propConfig = propConfig;
		this.deployPath = AcceptanceTest.deployPath;
		
		String java11Path = propConfig.getJava11Path();
 		if (ValidationHelper.isEmpty(java11Path)) {
 			this.updateJava = true;
			this.exportJava = exportJava.replace("JAVAPATH", java11Path);
 		}
	}
	
	public void run() {
		 try {
			 Log4jHelper.logInfo("Starting Server Service...");
		 	if (propConfig.getOS().equalsIgnoreCase("Windows")) {
		 		if (deployPath.startsWith("\""))
		 			Runtime.getRuntime().exec("cmd /c start /min title ServerService ^& eclipse\\scripts\\RunServerService.bat " + deployPath);
		 		else
		 			Runtime.getRuntime().exec("cmd /c start /min title ServerService ^& eclipse\\scripts\\RunServerService.bat \"" + deployPath + "\"");
		 	}
		 	else {
		 		String command = "cd " + deployPath + "/bin && chmod 777 server.sh && ./server.sh";
		 		if (updateJava) {
					command = exportJava + " && " + command;
				}
		 		
		 		RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				remoteMachine.executeScripts(command, "Server started successfully");
		 	}
		 }
		 catch (Exception e) {
	         try {
	        	FailureHelper.setErrorMessage(e);
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	     }
	}
}