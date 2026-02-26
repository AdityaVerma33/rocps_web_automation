package com.subex.automation.helpers.application;

import com.subex.automation.helpers.config.PropertyReader;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.automation.helpers.util.RemoteMachineHelper;

public class RunTomcat implements Runnable{
	
	public PropertyReader propConfig;
	
	public RunTomcat(PropertyReader propConfig){
		this.propConfig=propConfig;
	}
	
	public void run() {
		try {
			String tomcatPath = propConfig.getTomcatPath();
			 
			if (propConfig.getOS().equalsIgnoreCase("Windows")) {
				if (tomcatPath.startsWith("\""))
					Runtime.getRuntime().exec("cmd /c start /min eclipse\\scripts\\RunTomcatServer.bat " + tomcatPath + " " + propConfig.getTomcatConsoleTitle());
				else
					Runtime.getRuntime().exec("cmd /c start /min eclipse\\scripts\\RunTomcatServer.bat \"" + tomcatPath + "\" " + propConfig.getTomcatConsoleTitle());
			}
			else {
				String command = "cd "+ tomcatPath + "/bin && ./startup.sh";
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				remoteMachine.executeScripts(command);
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
