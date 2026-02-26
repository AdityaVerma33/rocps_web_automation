package com.subex.automation.helpers.performance;

import java.io.File;
import java.io.FilenameFilter;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class ChromeReader extends AcceptanceTest 
{
	public static String reportDirectory = null;
	public static PerformanceReportHelper prh = null;
	
	public static void reader(String reportDir) throws Exception {
		try {
			reportDirectory = GenericHelper.getPath(automationOS, reportDir);
			
			File dir = new File( reportDirectory );
			FilenameFilter filter = new TextFileFilter();
			File[] listOfFiles = dir.listFiles( filter );
			listOfFiles = PerformanceReporter.sortFiles(listOfFiles);

			for (File f : listOfFiles) {
				generateExcelReport(f.getAbsolutePath());
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static void generateExcelReport(String fileName) throws Exception {
		try {
			DecimalFormat df = new DecimalFormat("#.##"); 
			double startTime = 0;
			double endTime = 0;
			boolean hasData = false;
			
			prh = new PerformanceReportHelper();
			
			// Populate column headers in Net Report Sheet.
			String[] columnHeader = { "Method Type", "Method", "URL", "Timestamp", "Time Taken (sec)" };
			prh.addHeader(columnHeader);
			
			ArrayList<String> fileContents = FileHelper.readFile(automationOS, fileName);
			String timeTaken = String.valueOf(0);
			int rowNo = 1;
			
			for (int i = 0; i < fileContents.size(); i++) {
				String[] contents = getDetails(fileContents.get(i));
				Double loadTime = (double) 0;
				
				if (contents != null && contents.length > 2 && contents[3] != null) {
					if (startTime == 0) {
						startTime = Double.parseDouble(contents[3]);
					}
					else {
						Double tsValue = Double.parseDouble(contents[3]);
						if (endTime < tsValue) {
							endTime = tsValue;
							loadTime = (endTime - startTime);
						}
						else
							loadTime = (tsValue - startTime);
						
						contents[4] = df.format(loadTime);
					}
					
					prh.addData(rowNo, contents);
					rowNo++;
					hasData = true;
				}
			}
			
			timeTaken = df.format(endTime - startTime);
			String file = new File (fileName).getName();
			if (startTime > 0 || endTime > 0) {
				timeTaken = getTimeTaken(timeTaken);
				prh.updatePerformanceSummary(file, timeTaken);
			}
			
			// Write data to excel
			if (hasData)
				prh.createReportFile(reportDirectory, file);
			else
				prh.closeReportFile();
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String getMethodType(String method, JSONObject response, JSONObject request) throws Exception {
		try {
			String methodType = null;
			if (response != null)
				methodType = (String) response.get("method");
			else if (request != null)
				methodType = (String) request.get("method");
			else {
				if (method.contains("Network.dataReceived"))
					methodType = "GET";
				else
					methodType = "POST";
			}
			
			return methodType;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String getURL(String method, JSONObject response, JSONObject request) throws Exception {
		try {
			String url = null;
			if (response != null)
				url = (String) response.get("url");
			else if (request != null)
				url = (String) request.get("url");
			else {
				url = "";
			}
			
			url = url.replace("\\/", "/");
			return url;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String[] getDetails(String entry) throws Exception {
		try {
			String[] content = new String[5];
			
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(entry);
			JSONObject jsonObject = (JSONObject) obj;
			
			JSONObject message = (JSONObject) jsonObject.get("message");
			String method = (String) message.get("method");
			
			if (method.startsWith("Network")) {
				JSONObject params = (JSONObject) message.get("params");
				
				if (params != null) {
					content[0] = method;
					
					double responseTime = 0;
					if (params.get("timestamp") != null)
						responseTime = (Double) params.get("timestamp");
					else if (params.get("ts") != null)
						responseTime = (Double) params.get("ts");
					
					JSONObject response = (JSONObject) params.get("response");
					JSONObject request = (JSONObject) params.get("request");
					String methodType = getMethodType(method, response, request);
					content[1] = methodType;
					
					String url = getURL(methodType, response, request);
					content[2] = url;
					
					content[3] = Double.toString(responseTime);
					content[4] = String.valueOf(0);
				}
			}
			
			return content;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String getTimeTaken(String responsetime) throws Exception {
		try {
			double totaltime = Double.valueOf(responsetime);
			String timeTaken = prh.convertTime(totaltime);
			
			prh.addSummaryData(0, new String[] {"Total Time Taken (secs)", timeTaken});
			
			return timeTaken;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}

class TextFileFilter implements FilenameFilter {

	@Override
	public boolean accept( File directory, String fileName ) {
		if ( fileName.endsWith( ".txt" ) )
		{
			return true;
		}
		return false;
	}
}