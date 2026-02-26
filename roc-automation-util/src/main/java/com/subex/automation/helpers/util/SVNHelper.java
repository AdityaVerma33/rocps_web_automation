package com.subex.automation.helpers.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/*import org.tmatesoft.svn.core.SVNAuthenticationException;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNCommitClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;*/

import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;

public class SVNHelper extends AcceptanceTest {

	/*
	 * static SVNURL url = null; static SVNClientManager clientManager = null;
	 * static SVNUpdateClient updateClient = null; static SVNRepository repository =
	 * null; static ISVNOptions options = null; static ISVNAuthenticationManager
	 * authManager;
	 * 
	 * @SuppressWarnings("deprecation") public SVNHelper(String svnURL, String
	 * svnUsername, String svnPassword) throws Exception { try {
	 * 
	 * url = SVNURL.parseURIEncoded(svnURL); repository =
	 * SVNRepositoryFactory.create(url);
	 * 
	 * if (ValidationHelper.isNotEmpty(svnUsername) &&
	 * ValidationHelper.isNotEmpty(svnPassword)) { authManager =
	 * SVNWCUtil.createDefaultAuthenticationManager(svnUsername, svnPassword);
	 * repository.setAuthenticationManager(authManager);
	 * repository.testConnection();
	 * 
	 * // options = SVNWCUtil.createDefaultOptions(true); // clientManager =
	 * SVNClientManager.newInstance(options, authManager); } } catch (SVNException
	 * e){
	 * Log4jHelper.logInfo("Authentication failed. So could not send report mail.");
	 * } catch (Exception e) { FailureHelper.setErrorMessage(e); throw e; }
	 */
	
	
	@SuppressWarnings("deprecation")
	public void checkout(String svnURL, String svnUsername, String svnPassword, String checkoutFolder) throws Exception {
		/*
		 * try { options = SVNWCUtil.createDefaultOptions(true); clientManager =
		 * SVNClientManager.newInstance(); updateClient =
		 * clientManager.getUpdateClient();
		 * 
		 * File dstPath = new File(checkoutFolder); updateClient.doCheckout(url,
		 * dstPath, SVNRevision.UNDEFINED, SVNRevision.HEAD, true);
		 * 
		 * } catch (Exception e) { FailureHelper.setErrorMessage(e); throw e; }
		 */
	}
	
	@SuppressWarnings("deprecation")
	public void update(String checkoutFolder) throws Exception {
		/*
		 * try { options = SVNWCUtil.createDefaultOptions(true); clientManager =
		 * SVNClientManager.newInstance(); updateClient =
		 * clientManager.getUpdateClient();
		 * 
		 * File dstPath = new File(checkoutFolder); updateClient.doUpdate(dstPath,
		 * SVNRevision.HEAD, true);
		 * 
		 * } catch (Exception e) { FailureHelper.setErrorMessage(e); throw e; }
		 */
	}
	
	public void commit(String checkinFile, String logMessage) throws Exception {
		/*
		 * try { // ISVNEditor editor = repository.getCommitEditor(logMessage, null); //
		 * editor = repository.getCommitEditor("file contents changed", null); // //
		 * SVNCommitInfo commitInfo = modifyFile(editor, "", checkinFile); //
		 * Log4jHelper.logInfo("The file was changed: " + commitInfo);
		 * 
		 * SVNCommitClient commitClient = new SVNCommitClient(authManager,
		 * SVNWCUtil.createDefaultOptions(true)); SVNProperties props = new
		 * SVNProperties(); File[] fileToCheckin = {new File(checkinFile)};
		 * 
		 * SVNCommitInfo importInfo = commitClient.doCommit(fileToCheckin, false,
		 * logMessage, props, null, false, true, SVNDepth.INFINITY);
		 * Log4jHelper.logInfo("Commit revision: " + importInfo.getNewRevision());
		 * 
		 * } catch (SVNAuthenticationException e) {
		 * Log4jHelper.logInfo("Authentication failed. So could not send report mail.");
		 * } catch (Exception e) { FailureHelper.setErrorMessage(e); throw e; }
		 */
	}
	
	public static void updateReport() throws Exception {
	/*	try {
			String lastReportDate = configProp.getLastReportDate();
			String dateFormat = "MM/dd/yyyy HH:mm:ss";
			boolean commit = false;
			String currentDateTime = DateHelper.getCurrentDateTime(dateFormat);
			
			if (lastReportDate.equals("")) {
				commit = true;
			}
			else {
				SimpleDateFormat originalSDF = new SimpleDateFormat(dateFormat);
				Date fDate = originalSDF.parse(currentDateTime);
				Date sDate = originalSDF.parse(lastReportDate);
				
				int diffInDays = (int) ((fDate.getTime() - sDate.getTime()) / (1000 * 60 * 60 * 24));
				
				if (diffInDays > 5)
					commit = true;
			}
			
			if (commit) {
				SVNHelper svn = new SVNHelper(configProp.getAutomationSVNPath(), "username", "password");
				svn.commit("test.csv", "Automation Framework Report");
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}*/
}}