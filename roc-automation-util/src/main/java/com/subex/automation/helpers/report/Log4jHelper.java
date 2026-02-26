package com.subex.automation.helpers.report;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class Log4jHelper extends AcceptanceTest {
	private static boolean initializationFlag = false;
	
	public static void setInitializationFlag(boolean value) throws Exception {
		try {
			initializationFlag = value;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static void intializeLogger(String fileName) throws Exception {
		try {
			logger = Logger.getLogger(fileName);
			logger.setLevel(Level.ALL);
	
		    RollingFileAppender appender = new RollingFileAppender();
		    appender.setAppend(true);
		    appender.setMaxFileSize("20MB");
		    appender.setMaxBackupIndex(1);
		    if (!fileName.endsWith(".log"))
		    	fileName = fileName + ".log";
		    appender.setFile(fileName);
	
		    PatternLayout layOut = new PatternLayout();
		    layOut.setConversionPattern("%d{yyyy-MM-dd HH:mm:ss} [%-5p] - %m%n");
		    appender.setLayout(layOut);
		    appender.activateOptions();
	
		    logger.addAppender(appender);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public static void getLogger(String fileName) throws Exception {
		try {
		    if(initializationFlag == false){
		        intializeLogger(fileName);
		        initializationFlag = true;
		    }
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void logInfo(String information) throws Exception {
		try {
			logger.info(information);
			System.out.println(information);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void logError(String errorMessage) throws Exception {
		try {
			logger.error(errorMessage);
			System.out.println(errorMessage);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void logWarning(String information) throws Exception {
		try {
			logger.warn(information);
			System.out.println(information);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void logDebug(String information) throws Exception {
		try {
			logger.debug(information);
			System.out.println(information);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}