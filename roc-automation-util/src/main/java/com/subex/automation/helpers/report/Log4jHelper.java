package com.subex.automation.helpers.report;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.appender.rolling.DefaultRolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.SizeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;

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
			if (!fileName.endsWith(".log"))
				fileName = fileName + ".log";

			LoggerContext context = (LoggerContext) LogManager.getContext(false);
			Configuration config = context.getConfiguration();

			PatternLayout layout = PatternLayout.newBuilder()
				.withPattern("%d{yyyy-MM-dd HH:mm:ss} [%-5p] - %m%n")
				.build();

			SizeBasedTriggeringPolicy triggeringPolicy = SizeBasedTriggeringPolicy.createPolicy("20MB");

			DefaultRolloverStrategy strategy = DefaultRolloverStrategy.newBuilder()
				.withMax("1")
				.withConfig(config)
				.build();

			RollingFileAppender appender = RollingFileAppender.newBuilder()
				.setName("RollingFileAppender")
				.withFileName(fileName)
				.withFilePattern(fileName + ".%i")
				.withAppend(true)
				.setLayout(layout)
				.withPolicy(triggeringPolicy)
				.withStrategy(strategy)
				.build();

			appender.start();

			config.addAppender(appender);

			LoggerConfig loggerConfig = config.getRootLogger();
			loggerConfig.setLevel(Level.ALL);
			loggerConfig.addAppender(appender, Level.ALL, null);

			context.updateLoggers();

			logger = LogManager.getRootLogger();
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
