package com.subex.automation.helpers.performance;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.Iterator;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.file.CopyFile;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class FirefoxReader extends AcceptanceTest 
{
	public static String reportDirectory = null;
	public static JSONParser parser = null;
	public static PerformanceReportHelper prh = null;
	public static String[] commonServices = {"isStreamControllerActive", "isServerServiceActive", "checkForSessionExpiry"};
	
	public static void reader(String reportDir) throws Exception {
		try {
			reportDirectory = GenericHelper.getPath(automationOS, reportDir);
			String harFile = FileHelper.getLastModifiedFile(automationOS, reportDirectory, ".har");
			
			if (harFile != null) {
				File lastModifiedFile = new File(harFile);
				File dir = new File( reportDirectory );
				FilenameFilter filter = new HarFileFilter();
				File[] listOfFiles = dir.listFiles( filter );
				listOfFiles = PerformanceReporter.sortFiles(listOfFiles);
				parser = new JSONParser();
				
				for (File f : listOfFiles) {
					generateExcelReport(f, lastModifiedFile);
				}
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			parser = null;
		}
	}
	
	public static void reader(String reportDir,String filterName) throws Exception {
		try {
			String reportDirectory = GenericHelper.getPath(automationOS, reportDir);
			String harFile = FileHelper.getLastModifiedFile(automationOS, reportDirectory, ".har",filterName);
			
			if (harFile != null) {
				File lastModifiedFile = new File(harFile);
				File dir = new File( reportDirectory );
				FilenameFilter filter = new HarFileFilter();
				File[] listOfFiles = dir.listFiles( filter );
				listOfFiles = PerformanceReporter.sortFiles(listOfFiles);
				parser = new JSONParser();
				
				for (File f : listOfFiles) {
					String fileName = f.getName();
					if (fileName.startsWith(filterName)) {
						CopyFile.copyFile(reportDirectory+fileName, reportDirectory+filterName+"\\"+fileName,false);
						File f1 = new File(GenericHelper.getPath(automationOS, reportDirectory+filterName+"\\"+fileName));
						generateExcelReport(f1,reportDirectory+filterName, lastModifiedFile);
					}
					
				}
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			parser = null;
		}
	}
	
	private static JSONObject getJSONLog(File file) throws Exception {
		FileReader fr = null;
		try {
			fr = new FileReader(file);
			Object obj = parser.parse(fr);
			JSONObject jsonObject = (JSONObject) obj;
			JSONObject log = (JSONObject) jsonObject.get("log");
			
			return log;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			fr.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void generateExcelReport(File file, File lastModifiedFile) throws Exception {
		try {
			String fileName = file.getName();
			String timeTaken = null;
			String negativeValue = String.valueOf(-1);
			
			prh = new PerformanceReportHelper();
			
			if (fileName.endsWith(".har")) {
				JSONObject log = getJSONLog(file);
				String fileNameWithPath = file.getAbsolutePath().replace(".har", ".log");
				FileHelper.createFile(fileNameWithPath);
				FileHelper.writeToFile(fileNameWithPath, log.toString());
				JSONArray entries = (JSONArray) log.get("entries");
				String endtime = "";

				// Populate column headers in Net Report Sheet.
				String[] columnHeader = { "Path1", "Path2", "Path3", "Path4", "Time (ms)" };
				prh.addHeader(columnHeader);

				// Iterate through entries and populate relevant fields.
				Iterator<JSONObject> entriesIterator = entries.iterator();
				boolean hasData = populateReportData(entriesIterator, 1);

				// Calculate total time taken
				long time = 0;
				if (entries.size() > 0) {
					int length = entries.size();
					for (int x = length-1; x > 0; x--) {
						JSONObject response = (JSONObject) entries.get(x);
						boolean commonService = isCommonService(response);
						
						if (!commonService) {
							endtime = (String) response.get("startedDateTime");
							if (response.get("time") != null)
								time = (long) response.get("time");
							break;
						}
					}
				}
		
				if (endtime.equals(""))
					timeTaken = negativeValue;
				else
					timeTaken = getTimeTaken(fileName, log, endtime, time);
				
				// Write data to excel
				if (hasData)
					prh.createReportFile(reportDirectory, fileName);
				else
					prh.closeReportFile();
			}
			
			if (timeTaken != negativeValue) {
				prh.updatePerformanceSummary(fileName, timeTaken);
			}
			
			/*if ( file.equals( lastModifiedFile ) ) {
				finalTotalTime = timeTaken;
				Log4jHelper.logInfo( "File name is " + lastModifiedFile + ". Total time taken is " + finalTotalTime );
			}*/
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void generateExcelReport(File file,String reportDirectory, File lastModifiedFile) throws Exception {
		try {
			String fileName = file.getName();
			String timeTaken = null;
			String negativeValue = String.valueOf(-1);
			
			PerformanceReportHelper prh = new PerformanceReportHelper();
			
			if (fileName.endsWith(".har")) {
				JSONObject log = getJSONLog(file);
				String fileNameWithPath = file.getAbsolutePath().replace(".har", ".log");
				FileHelper.createFile(fileNameWithPath);
				FileHelper.writeToFile(fileNameWithPath, log.toString());
				JSONArray entries = (JSONArray) log.get("entries");
				String endtime = "";

				// Populate column headers in Net Report Sheet.
				String[] columnHeader = { "Path1", "Path2", "Path3", "Path4", "Time (ms)" };
				prh.addHeader(columnHeader);

				// Iterate through entries and populate relevant fields.
				Iterator<JSONObject> entriesIterator = entries.iterator();
				boolean hasData = populateReportData(prh,entriesIterator, 1);

				// Calculate total time taken
				long time = 0;
				if (entries.size() > 0) {
					int length = entries.size();
					for (int x = length-1; x > 0; x--) {
						JSONObject response = (JSONObject) entries.get(x);
						boolean commonService = isCommonService(response);
						
						if (!commonService) {
							endtime = (String) response.get("startedDateTime");
							if (response.get("time") != null)
								time = (long) response.get("time");
							break;
						}
					}
				}
		
				if (endtime.equals(""))
					timeTaken = negativeValue;
				else
					timeTaken = getTimeTaken(prh,fileName, log, endtime, time);
				
				// Write data to excel
				if (hasData)
					prh.createReportFile(reportDirectory, fileName);
				else
					prh.closeReportFile();
			}
			
			if (timeTaken != negativeValue) {
				prh.updatePerformanceSummary(fileName, timeTaken);
			}
			
			/*if ( file.equals( lastModifiedFile ) ) {
				finalTotalTime = timeTaken;
				Log4jHelper.logInfo( "File name is " + lastModifiedFile + ". Total time taken is " + finalTotalTime );
			}*/
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static boolean populateReportData(Iterator<JSONObject> entriesiterator, int rowNo) throws Exception {
		try {
			boolean hasData = false;
			
			while (entriesiterator.hasNext()) {
				JSONObject tempobj = entriesiterator.next();
				JSONObject request = (JSONObject) tempobj.get("request");
				JSONObject response = (JSONObject) tempobj.get("response");
				JSONObject postData = (JSONObject) request.get("postData");

				Object requestTime = tempobj.get("time");
				Object requestMethod = request.get("method");
				String url = (String) request.get("url");
				Object responseStatus = response.get("status");
				Object postDataText = null;
				if (postData != null && postData.get("text") != null)
					postDataText = postData.get("text");
				
				//Populate Net Report Excel
				populateData(requestMethod, responseStatus, postDataText, url);
				long time = 0;
				if (requestTime != null)
					time = (long) requestTime;
				
				prh.addData(rowNo++, 4, String.valueOf(time));
				hasData = true;
			}
			
			return hasData;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static boolean populateReportData(PerformanceReportHelper prh,Iterator<JSONObject> entriesiterator, int rowNo) throws Exception {
		try {
			boolean hasData = false;
			
			while (entriesiterator.hasNext()) {
				JSONObject tempobj = entriesiterator.next();
				JSONObject request = (JSONObject) tempobj.get("request");
				JSONObject response = (JSONObject) tempobj.get("response");
				JSONObject postData = (JSONObject) request.get("postData");

				Object requestTime = tempobj.get("time");
				Object requestMethod = request.get("method");
				String url = (String) request.get("url");
				Object responseStatus = response.get("status");
				Object postDataText = null;
				if (postData != null && postData.get("text") != null)
					postDataText = postData.get("text");
				
				//Populate Net Report Excel
				populateData(prh,requestMethod, responseStatus, postDataText, url);
				long time = 0;
				if (requestTime != null)
					time = (long) requestTime;
				
				prh.addData(rowNo++, 4, String.valueOf(time));
				hasData = true;
			}
			
			return hasData;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String getTimeTaken(String fileName, JSONObject log, String endtime, long time) throws Exception {
		try {
			String timeTaken = null;
			DateTime d2 = DateTime.parse(endtime, DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
			long endTime = d2.getMillis() + time;
			String startedTime = null;
			
			JSONArray pages = (JSONArray) log.get("pages");
			@SuppressWarnings("unchecked")
			Iterator<JSONObject> pageiterator = pages.iterator();
			
			while (pageiterator.hasNext()) {
				JSONObject temppageobj = pageiterator.next();
				startedTime = (String) temppageobj.get("startedDateTime");
			}
			
			JSONArray entries = (JSONArray) log.get("entries");
			@SuppressWarnings("unchecked")
			Iterator<JSONObject> entriesiterator = entries.iterator();
			while (entriesiterator.hasNext()) {
				JSONObject response = entriesiterator.next();
				boolean commonService = isCommonService(response);
				
				if (!commonService) {
					startedTime = (String) response.get("startedDateTime");
					break;
				}
			}
			
			DateTime d1 = DateTime.parse(startedTime, DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
			double totaltime = (double) (endTime - d1.getMillis()) / 1000;
			timeTaken = prh.convertTime(totaltime);
			Log4jHelper.logInfo(fileName + " --- " + totaltime);
			
			prh.addSummaryData(0, new String[] {"Total time taken (secs)", timeTaken});
			
			return timeTaken;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String getTimeTaken(PerformanceReportHelper prh,String fileName, JSONObject log, String endtime, long time) throws Exception {
		try {
			String timeTaken = null;
			DateTime d2 = DateTime.parse(endtime, DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
			long endTime = d2.getMillis() + time;
			String startedTime = null;
			
			JSONArray pages = (JSONArray) log.get("pages");
			@SuppressWarnings("unchecked")
			Iterator<JSONObject> pageiterator = pages.iterator();
			
			while (pageiterator.hasNext()) {
				JSONObject temppageobj = pageiterator.next();
				startedTime = (String) temppageobj.get("startedDateTime");
			}
			
			JSONArray entries = (JSONArray) log.get("entries");
			@SuppressWarnings("unchecked")
			Iterator<JSONObject> entriesiterator = entries.iterator();
			while (entriesiterator.hasNext()) {
				JSONObject response = entriesiterator.next();
				boolean commonService = isCommonService(response);
				
				if (!commonService) {
					startedTime = (String) response.get("startedDateTime");
					break;
				}
			}
			
			DateTime d1 = DateTime.parse(startedTime, DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
			double totaltime = (double) (endTime - d1.getMillis()) / 1000;
			timeTaken = prh.convertTime(totaltime);
			Log4jHelper.logInfo(fileName + " --- " + totaltime);
			
			prh.addSummaryData(0, new String[] {"Total time taken (secs)", timeTaken});
			
			return timeTaken;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static boolean isCommonService(JSONObject response) throws Exception {
		try {
			boolean commonService = false;
			JSONObject request = (JSONObject) response.get("request");
			Object postData = request.get("postData");
			
			if (postData != null) {
				String postText = postData.toString();
				
//				if (postText.contains("\"text\":\"\""))
//					commonService = true;
//				else {
					for (int i = 0; i < commonServices.length; i++) {
						if (postText.contains(commonServices[i])) {
							commonService = true;
							break;
						}
					}
//				}
			}
			
			return commonService;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static void populateData(Object requestMethod, Object responseStatus, Object postDataText, String url) throws Exception {
		try {
			int columnnumber = 0;
			
			// Populate data if the status code is equal to 200
			if (((Long) responseStatus).equals(200)) {
				String method = (String) requestMethod;

				if (method.equals("GET")) {
					prh.addData(columnnumber++, "GET Method");
					prh.addData(columnnumber++, url);
				}
				else {
					String text = "";
					if (postDataText != null)
						text = (String) postDataText;
					String[] s = new String[9];
					
					if (!text.isEmpty()) {
						s = text.split("\\|", 12);

						// populate 5,6,7,8th field after splitting text.
						for (int d = 5; d < 9; d++) {
							prh.addData(columnnumber++, s[d]);
						}
					}
				}
			}
			// Populate fields if status code is not 200
			else {
				// populate errorcode
				Long errorstatus = (Long) responseStatus;
				prh.addData(columnnumber++, "Status - " + errorstatus);

				// populate url
				prh.addData(columnnumber++, url);
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}


private static void populateData(PerformanceReportHelper prh,Object requestMethod, Object responseStatus, Object postDataText, String url) throws Exception {
	try {
		int columnnumber = 0;
		
		// Populate data if the status code is equal to 200
		if (((Long) responseStatus).equals(200)) {
			String method = (String) requestMethod;

			if (method.equals("GET")) {
				prh.addData(columnnumber++, "GET Method");
				prh.addData(columnnumber++, url);
			}
			else {
				String text = "";
				if (postDataText != null)
					text = (String) postDataText;
				String[] s = new String[9];
				
				if (!text.isEmpty()) {
					s = text.split("\\|", 12);

					// populate 5,6,7,8th field after splitting text.
					for (int d = 5; d < 9; d++) {
						prh.addData(columnnumber++, s[d]);
					}
				}
			}
		}
		// Populate fields if status code is not 200
		else {
			// populate errorcode
			Long errorstatus = (Long) responseStatus;
			prh.addData(columnnumber++, "Status - " + errorstatus);

			// populate url
			prh.addData(columnnumber++, url);
		}
		
	} catch (Exception e) {
		FailureHelper.setErrorMessage(e);
		throw e;
	}
}
}

class HarFileFilter implements FilenameFilter {

	@Override
	public boolean accept( File directory, String fileName )
	{
		if ( fileName.endsWith( ".har" ) )
		{
			return true;
		}
		return false;
	}
}