package com.subex.automation.helpers.util;

import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;

public class EmailHelper extends AcceptanceTest {
	final String hostName = "10.113.45.18";
	
	public void sendMail() throws Exception {
		try {
			
			String subject = "Automation Execution Report ";
			if (versionNo != null)
				subject = versionNo + " Automation Execution Report " + "(" + suiteName + " Report)";
			
			String clientBuildNo = configProp.getClientBuildNo();
			String serverBuildNo = configProp.getServerBuildNo();
			if (ValidationHelper.isNotEmpty(clientBuildNo) && ValidationHelper.isNotEmpty(serverBuildNo))
				subject = subject + " (Client - " + clientBuildNo + "; Server - " + serverBuildNo + ") ";
			String reportFileName = FileHelper.getLastModifiedFile(automationOS, reportLocation, ".html");
			
			long fileSize = FileHelper.getFileSize(automationOS, reportFileName);
			String zipReportFile = reportFileName;
			if (fileSize > 10) {
				zipReportFile = reportFileName + ".gz";
				ZipHelper zip = new ZipHelper();
				zip.gzip(reportFileName, zipReportFile);
			}
			
//			String statisticsFileName = FileHelper.getLastModifiedFile(automationOS, automationPath, configProp.getProduct() + "_Automation_Report.xlsx");
			String[] fileName = {zipReportFile};
			
			if (!hostName.equals("") && !configProp.getMailToAddress().equals("")) {
				StringBuffer content = emailContent(versionNo, clientBuildNo, serverBuildNo);
				String[] toMailId = configProp.getMailToAddress().split(",");
				String[] ccMailId = configProp.getMailCCAddress().split(",");
				SendEmailHelper sendMailHelper = new SendEmailHelper();
				sendMailHelper.sendMail(hostName, toMailId, ccMailId, subject, content, fileName);
			}
			else {
				FailureHelper.failTest("Failed to send mail as mail related properties are not proper in config.properties file");
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private StringBuffer emailContent(String version, String clientBuildNo, String serverBuildNo) throws Exception {
		try {
			
			String user = configProp.getMailSendingUserName();
			StringBuffer content = new StringBuffer();
			content.append("Hi,\n");
			content.append("\n");
			
			if (ValidationHelper.isEmpty(version) || version.equals("null"))
				content.append("PFA Automation Execution Report as on " + DateHelper.getCurrentDate("dd MMM, yyyy") + "\n");
			else
				content.append("PFA " + version + " Automation Execution Report as on " + DateHelper.getCurrentDate("dd MMM, yyyy") + "\n");
			
			if (ValidationHelper.isNotEmpty(clientBuildNo) && ValidationHelper.isNotEmpty(serverBuildNo)) {
				content.append("\n");
				content.append("Client Build Number: " + clientBuildNo + "\n");
				content.append("Server Build Number: "+ serverBuildNo + "\n");
			}
			
//			if (calculatePerformance) {
//				content.append("\n");
//				content.append("Performance Calculated: " + calculatePerformance + "\n");
//			}
			
			content.append("\n");
			content.append("Regards,\n");
			content.append(user);
			return content;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}