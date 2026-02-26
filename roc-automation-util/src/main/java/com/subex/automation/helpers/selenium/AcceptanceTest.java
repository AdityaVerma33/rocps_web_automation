package com.subex.automation.helpers.selenium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.config.PropertyReader;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.StringHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.CSVReader;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.file.ExcelWriter;
import com.subex.automation.helpers.file.XMLReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.report.ReportHelper;
import com.subex.automation.helpers.util.EmailHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.automation.helpers.util.WindowsHelper;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * Base Class for automation tests. Instantiates selenium browser &
 * selenium server with configuration specified from the test xml file.   
 */

public class AcceptanceTest extends Assert {
	public static PropertyReader configProp = null;
	public static WebDriver driver = null;
	public static Properties or = null;

	public static String suiteName = null;
	public static String versionNo = "";

	public static String currentWindowTitle = null;
	public static String deployPath = null;
	public static String configFile = null;
	public static String result = null;

	// Extend report related variables
	public static ExtentReports report = null;
	public static ExtentTest testReport = null;
	public static String reportLocation = null;
	public static String testCaseName = null;
	public static String stepKeys = "";
	public static String stepName = "";
	public static String errorMsg = null;
//	public static Map<Integer, Object[]> performanceSummary = new TreeMap<Integer, Object[]>();
	public static Logger logger = null;

	public static String flow = null;
	public static int totalPass = 0;
	public static int totalFail = 0;
	public static int totalSkipped = 0;
	public static boolean isInstallationDone = true;

	public static ChannelSftp sftpChannel = null;
	public static ChannelExec execChannel = null;
	public static Session execSession = null;
	public static Session sftpSession = null;
	public static boolean calculatePerformance = false;

	public static String automationPath = null;
	public static String automationOS = null;
	public static String applicationOS = null;

	public static int detailScreenWaitSec = 0;
	public static int searchScreenWaitSec = 0;

	@BeforeSuite(alwaysRun = true)
	public void getSuiteName(ITestContext iContext) throws Exception {
		try {
			suiteName = iContext.getSuite().getName();
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
		}
	}

	@AfterSuite(alwaysRun = true)
	public static void stopWebDriver() throws Exception {
		try {
			Log4jHelper.logInfo("Stopping WebDriver ...");
			driver.quit();
			Log4jHelper.logInfo("WebDriver Stopped.");
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
		} finally {
			if (testReport != null)
				testReport = ReportHelper.endReport(report, testReport);
			ReportHelper.closeReport(report);
			printTimeTaken();

			if (ValidationHelper.isTrue(configProp.getSendMail())) {
				EmailHelper emailHelper = new EmailHelper();
				emailHelper.sendMail();
			}
		}
	}

	@BeforeClass(alwaysRun = true)
	public void startWebDriver() throws Exception {
		try {
//			if (result == null || !result.equals("skip")) {
			GenericHelper.calculatePerformance();
			System.setProperty("webdriver.gecko.driver",
					automationPath + "\\plugins\\" + configProp.getSeleniumDriver());

			FileHelper.makeDirectory(configProp.getDownloadDirectory(), "");
			FileHelper.makeDirectory(configProp.getDownloadDirectory(), "Client_Downloads");
			String downloadPath = GenericHelper.getPath(automationOS,
					configProp.getDownloadDirectory() + "\\Client_Downloads");
			String browser = configProp.getBrowser().toLowerCase();
			Log4jHelper.logInfo("Starting client with " + browser + " browser...");
			WindowsHelper windowsHelper = new WindowsHelper();

			switch (browser) {
			case "firefox":
				FirefoxProfile profile = setFirefoxProfile();
				profile = setFileDownloadProperties(profile, downloadPath);
				setFirefoxDriver(profile);
				break;

			case "chrome":
				String chromeFile = GenericHelper.getPath(automationOS,
						automationPath + "\\plugins\\" + configProp.getChromeDriver());
				System.setProperty("webdriver.chrome.driver", chromeFile);
				setChromeDriver(downloadPath);
				windowsHelper.maximizeBrowser();
				break;

			/*
			 * case "chrome": WebDriverManager.chromedriver().setup(); // no need to set
			 * System property setChromeDrivers(downloadPath); // your method that applies
			 * options //windowsHelper.maximizeBrowser();
			 * //driver.manage().window().setSize(new Dimension(907, 732)); break;
			 */
			case "ie":
				String ieFile = GenericHelper.getPath(automationOS,
						automationPath + "\\plugins\\" + configProp.getIEDriver());
				System.setProperty("webdriver.ie.driver", ieFile);
				setIEDriver();
				break;
			default:
				FailureHelper.failTest(
						"Invalid browser name, browser name should be firefox/ie/chrome in config.properties file");
			}

//				if(calculatePerformance) {
//					HarReader.clearHAR();
//					FileHelper.cleanUpDir(automationOS, configProp.getDownloadDirectory() + "\\Performance_Report", true);
//				}

			if (ValidationHelper.isTrue(configProp.getRunInFullScreenMode())) {
				windowsHelper.fullScreen();
			}

			driver.get(configProp.getClientURL());
			Log4jHelper.logInfo("WebDriver Started.");
			currentWindowTitle = driver.getTitle();
			WindowsHelper windows = new WindowsHelper();
			windows.maximizeBrowser();

//				if(calculatePerformance)
//					HarReader.generateHar();
//			}
		} catch (WebDriverException e) {
//			e.printStackTrace();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@AfterClass(alwaysRun = true)
	public static void closeBrowser() throws Exception {
		try {
			Log4jHelper.logInfo("Stopping WebDriver ...");
			driver.quit();
			Log4jHelper.logInfo("WebDriver Stopped.");
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
		}
	}

	private void setFirefoxDriver(FirefoxProfile profile) throws Exception {
		try {
			DesiredCapabilities capabilities = setCommonCapabilities();
			FirefoxOptions options = new FirefoxOptions();
			options.merge(capabilities);
			options.setProfile(profile);
			options.addPreference("layout.css.devPixelsPerPx", "0.9");
			driver = new FirefoxDriver(options);

//			if(calculatePerformance) {
//				performanceSummary.put(0, new Object[] {"Screen Name", "Time Taken"});
//			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private ChromeOptions getChromeOptions() throws Exception {
		try {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			options.addArguments("disable-infobars");
			options.addArguments("start-maximized");
			options.addArguments("--force-device-scale-factor=0.9");
			options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));

			return options;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@SuppressWarnings("deprecation")
	private void setChromeDriver(String downloadPath) throws Exception {
		try {
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			ChromeOptions options = getChromeOptions();
			DesiredCapabilities capabilities = new DesiredCapabilities();

//			if(calculatePerformance) {
//				capabilities = getChromePerformanceCapabilities(chromePrefs, options);
//			}
//			else {
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadPath);
			capabilities = setCommonCapabilities();
			options.setExperimentalOption("prefs", chromePrefs);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setJavascriptEnabled(true);
//			}

			driver = new ChromeDriver(capabilities);

			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}


	
	@SuppressWarnings("deprecation")
	private void setIEDriver() throws Exception {
		try {
			DesiredCapabilities capabilities = setIECommonCapabilities();

//			if(calculatePerformance) {
//				performanceSummary.put(0, new Object[] {"Screen Name", "Time Taken"});
//				capabilities.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
//		        capabilities.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
//				driver = new InternetExplorerDriver(capabilities);
//				
//				JavascriptExecutor executor = (JavascriptExecutor)driver;
//				IEPerformance.clearPerformance(executor);
//			}
//			else {
			driver = new InternetExplorerDriver(capabilities);
//			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private DesiredCapabilities setCommonCapabilities() throws Exception {
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setJavascriptEnabled(true);
			return capabilities;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private DesiredCapabilities setIECommonCapabilities() throws Exception {
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("EnableNativeEvents", false);
			capabilities.setCapability("ignoreZoomSetting", true);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setJavascriptEnabled(true);

			return capabilities;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public static WebDriver getDriver() {
		return driver;
	}

//	private DesiredCapabilities getChromePerformanceCapabilities(HashMap<String, Object> chromePrefs, ChromeOptions options) throws Exception {
//		try {
//			LoggingPreferences logPrefs = new LoggingPreferences();
//			logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
//			
//			DesiredCapabilities capabilities = setCommonCapabilities();
//			capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
//			
//			chromePrefs.put("traceCategories", "browser,devtools.timeline,devtools");
//			options.setExperimentalOption("perfLoggingPrefs", chromePrefs);
//			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//			capabilities.setJavascriptEnabled(true);
//			
//			return capabilities;
//		} catch (Exception e) {
//			FailureHelper.setErrorMessage(e);
//			throw e;
//		}
//	}

	public static FirefoxProfile setFirefoxProfile() throws Exception {
		try {
			FirefoxProfile profile = new FirefoxProfile();
			profile.setAcceptUntrustedCertificates(true);
			profile.setPreference("browser.cache.disk.enable", false);
			profile.setPreference("browser.cache.memory.enable", false);
			profile.setPreference("browser.cache.offline.enable", false);
			profile.setPreference("network.http.use-cache", false);

//			if(calculatePerformance) {
//				// Load extensions  
//				String harAppFile = GenericHelper.getPath(automationOS, configProp.getUtilPath() + "\\eclipse\\FirefoxTools\\harexporttrigger-0.5.0-beta.10.xpi");
//				File harExport = new File(harAppFile);
//				profile.addExtension( harExport );
//
//				// Enable the automation without having a new HAR file created for every loaded page.
//				profile.setPreference( "extensions.netmonitor.har.enableAutomation", false );
//				// Set to a token that is consequently passed into all HAR API calls to verify the user.
//				profile.setPreference( "extensions.netmonitor.har.contentAPIToken", "test" );
//				// Set if you want to have the HAR object available without the developer toolbox being open.
//				profile.setPreference( "extensions.netmonitor.har.autoConnect", true );
//
//				// Enable netmonitor
//				profile.setPreference( "devtools.netmonitor.enabled", true );
//				// If set to true the final HAR file is zipped. This might represents great disk-space optimization especially if HTTP response bodies are included.
//				profile.setPreference( "devtools.netmonitor.har.compress", false );
//				// Default name of the target HAR file. The default file name supports formatters
//				profile.setPreference( "devtools.netmonitor.har.defaultFileName", "Autoexport_%y%m%d_%H%M%S" );
//				// Default log directory for generate HAR files. If empty all automatically generated HAR files are stored in <FF-profile>/har/logs
//				profile.setPreference( "devtools.netmonitor.har.defaultLogDir", GenericHelper.getPath(automationOS, configProp.getDownloadDirectory() + "\\Performance_Report") );
//				// If true, a new HAR file is created for every loaded page automatically.
//				profile.setPreference( "devtools.netmonitor.har.enableAutoExportToFile", false );
//				// The result HAR file is created even if there are no HTTP requests.
//				profile.setPreference( "devtools.netmonitor.har.forceExport", true );
//				// If set to true, HTTP response bodies are also included in the HAR file (can produce significantly bigger amount of data).
//				profile.setPreference( "devtools.netmonitor.har.includeResponseBodies", false );
//				// If set to true the export format is HARP (support for JSONP syntax that is easily transferable cross domains)
//				profile.setPreference( "devtools.netmonitor.har.jsonp", false );
//				// Default name of JSONP callback (used for HARP format)
//				profile.setPreference( "devtools.netmonitor.har.jsonpCallback", false );
//				// Amount of time [ms] the auto-exporter should wait after the last finished request before exporting the HAR file.
//				profile.setPreference( "devtools.netmonitor.har.pageLoadedTimeout", "2500" );
//				
//				profile.setPreference("browser.privatebrowsing.autostart", true);
//			}

			return profile;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public static FirefoxProfile setFileDownloadProperties(FirefoxProfile profile, String downloadLocation)
			throws Exception {
		try {
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.download.dir", downloadLocation);
			profile.setPreference("browser.download.manager.focusWhenStarting", "true");
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/plain;" // MIME types Of text File.
					+ "text/csv;" // MIME types Of CSV File.
					+ "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;"// MIME types Of MS Excel
																							// File.
					+ "application/pdf;" // MIME types Of PDF File.
					+ "application/vnd.openxmlformats-officedocument.wordprocessingml.document"); // MIME types Of MS
																									// doc File.
			profile.setPreference("browser.download.manager.showWhenStarting", false);
			profile.setPreference("pdfjs.disabled", true);

			return profile;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public static void increaseCounter() throws Exception {
		try {
			String fileName = GenericHelper.getPath(automationOS, automationPath + "\\test.csv");
			String[][] content = CSVReader.readFile(automationOS, fileName, ",");
			content = StringHelper.resizeStringArray(content);
			String currentDate = DateHelper.getCurrentDate("MM/dd/yyyy");

			if (ValidationHelper.isEmpty(content)) {
				FileHelper.writeToFile(fileName, suiteName + "," + currentDate + ",1");
			} else {
				if (content[0].length > 2) {
					boolean updated = false;
					String[] addContent = new String[content.length];

					for (int i = 0; i < content.length; i++) {
						if (content[i][0].equals(suiteName) && content[i][1].equals(currentDate)) {
							content[i][2] = (Integer.parseInt(content[i][2]) + 1) + "";
							updated = true;
						}

						addContent[i] = content[i][0] + "," + content[i][1] + "," + content[i][2]
								+ System.getProperty("line.separator");
					}

					if (!updated) {
						addContent = StringHelper.resizeStringArray(addContent, content.length + 1);
						addContent[content.length] = suiteName + "," + currentDate + ",1"
								+ System.getProperty("line.separator");
					}

					FileHelper.writeToFile(fileName, addContent);
				} else
					FileHelper.writeToFile(fileName, suiteName + "," + currentDate + ",1");
			}

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private static long[] getTimeTaken(String[] totalTime) throws Exception {
		try {
			long hours = 0;
			long minutes = 0;
			long seconds = 0;
			long milliSeconds = 0;

			for (int i = 0; i < totalTime.length; i++) {
				String[] values = totalTime[i].split(" ", -1);
				int plusIndex = values[2].indexOf("+");
				String milliSec = values[2].substring(plusIndex + 1);
				values[2] = values[2].replace("+" + milliSec, "");
				milliSec = milliSec.replaceAll("[a-z]*", "");

				for (int j = 0; j < values.length; j++)
					values[j] = values[j].replaceAll("[a-z*]", "");

				hours = hours + Integer.parseInt(values[0]);
				minutes = minutes + Integer.parseInt(values[1]);
				seconds = seconds + Integer.parseInt(values[2]);
				milliSeconds = milliSeconds + Integer.parseInt(milliSec);
			}

			return new long[] { hours, minutes, seconds, milliSeconds };
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private static String getTimeInSeconds(long[] totalTime) throws Exception {
		try {
			long hours = totalTime[0];
			long minutes = totalTime[1];
			long seconds = totalTime[2];
			long milliSeconds = totalTime[3];

			long timeTaken = (hours * 3600) + (minutes * 60) + seconds + (milliSeconds / 1000);
			String totalTimeTaken = String.valueOf(timeTaken);
			return totalTimeTaken;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private static String getTime(long[] totalTime) throws Exception {
		try {
			long hours = totalTime[0];
			long minutes = totalTime[1];
			long seconds = totalTime[2];
			long milliSeconds = totalTime[3];

			if (milliSeconds > 1000) {
				long value = (long) (milliSeconds / 1000);
				long reminder = milliSeconds - (value * 1000);
				seconds = seconds + value;
				milliSeconds = reminder;
			}

			if (seconds > 60) {
				long value = (long) (seconds / 60);
				long reminder = seconds - (value * 60);
				minutes = minutes + value;
				seconds = reminder;
			}

			if (minutes > 60) {
				long value = (long) (minutes / 60);
				long reminder = minutes - (value * 60);
				hours = hours + value;
				minutes = reminder;
			}

			String totalTimeTaken = null;
			if (hours == 0)
				totalTimeTaken = minutes + " mins " + seconds + " secs " + milliSeconds + " ms";
			else
				totalTimeTaken = hours + " hours " + minutes + " mins " + seconds + " secs " + milliSeconds + " ms";
			return totalTimeTaken;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private static void printTimeTaken() throws Exception {
		long[] totalTimeTaken = null;
		String[] totalTime = null;

		try {
			String[] fileNames = FileHelper.getLastModifiedFiles(automationOS, reportLocation, ".html");

			if (ValidationHelper.isNotEmpty(fileNames)) {
				String fileName = fileNames[0];
				for (int i = 1; i < fileNames.length; i++) {
					fileName = fileName + ", " + fileNames[i];
				}

				Log4jHelper.logInfo("\nRefer '" + fileName + "' available in Report folder for Execution Status.\n");

				totalTime = new String[fileNames.length];
				for (int i = 0; i < fileNames.length; i++) {
					totalTime[i] = XMLReader.getTagValue(fileNames[i], "suite-total-time-overall-value");
				}

				totalTimeTaken = getTimeTaken(totalTime);
				String timeTaken = getTime(totalTimeTaken);
				Log4jHelper.logInfo("Total Time Taken: " + timeTaken);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			String timeTaken = getTimeInSeconds(totalTimeTaken);
			generateStatisticsReport(timeTaken);
		}
	}

	private static Object[] getCurrentStatistics(String totalTimeTaken) throws Exception {
		try {
			String product = configProp.getProduct();
			String currentDate = DateHelper.getCurrentDate("yyyy-MM-dd");

			int total = totalPass + totalFail + totalSkipped;
			int baselinedTCs = configProp.getIntegerProperty("baselinedTestCases", total);
			int automatedTCs = configProp.getIntegerProperty("automatedTestCases", total);
			if (total > automatedTCs)
				automatedTCs = total;
			if (automatedTCs > baselinedTCs)
				baselinedTCs = automatedTCs;
			else if (total > baselinedTCs)
				baselinedTCs = total;

			Object[] stats = { currentDate, product, baselinedTCs, automatedTCs, total, totalPass, totalFail,
					totalSkipped, totalTimeTaken };

			return stats;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private static void generateStatisticsReport(String totalTimeTaken) throws Exception {
		try {
			String product = configProp.getProduct();
			String currentDate = DateHelper.getCurrentDate("yyyy-MM-dd");
			String fileName = product + "_Automation_Report.xlsx";
			String fileNameWithPath = GenericHelper.getPath(automationOS, automationPath + "\\" + fileName);
			String[] header = { "Date", "Product", "Baselined Testcases", "No. of Automated Cases", "Total Executed",
					"Total Passed", "Total Failed", "Total Skipped", "Execution Duration (Secs)" };

			Map<Integer, Object[]> newReportStatistics = new HashMap<Integer, Object[]>();
			int count = 0;
			newReportStatistics.put(count, header);
			count++;

			if (FileHelper.checkFileExists(fileNameWithPath)) {
				ExcelReader excel = new ExcelReader();
				HashMap<String, ArrayList<String>> reportStatistics = excel.readDataByColumn(automationPath, fileName,
						"Statistics", header);

				for (int i = 0; i < reportStatistics.get("Date").size(); i++) {
					String dValue = reportStatistics.get("Date").get(i);
					if (dValue.contains(":"))
						dValue = DateHelper.convertDate("MM/dd/yyyy HH:mm:ss", "yyyy-MM-dd", dValue);
					if (!dValue.equals(currentDate)) {
						String[] oldValue = new String[header.length];
						oldValue[0] = dValue;

						for (int j = 1; j < header.length; j++) {
							oldValue[j] = reportStatistics.get(header[j]).get(i);
						}

						newReportStatistics.put(count, oldValue);
						count++;
					}
				}
			}

			Object[] stats = getCurrentStatistics(totalTimeTaken);
			newReportStatistics.put(count, stats);

			ExcelWriter.writeToExcel(fileNameWithPath, "Statistics", newReportStatistics);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}