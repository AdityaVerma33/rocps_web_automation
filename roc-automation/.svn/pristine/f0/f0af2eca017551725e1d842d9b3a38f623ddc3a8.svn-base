package com.subex.automation.helpers.setup;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import com.subex.automation.helpers.data.StringHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.DownloadBinaries;
import com.subex.automation.helpers.util.FailureHelper;

public class JenkinsHelper extends AcceptanceTest{

	final private String clientURL = "/ws/spark-web/entrypoint/target/";
	final private String serverURL = "ws/spark-server-deploy/deploy/";
	
	private static int splitLimit = 3;
	private static String serverFileName = "spark-server-deploy.zip";
	
	final private String artifactClientURL = "com/subex/sparkguing/entrypoint/";
	final private String artifactClientFileStart = "entrypoint-";
	final private String artifactServerURL = "com/subex/spark/spark-server-deploy/";
	final private String artifactServerFileStart = "spark-server-deploy-";
	
	public String getDownloadURL(String baseURL, String downloadtype) throws Exception  {
		try {
			String url = baseURL;
			
			if (!url.endsWith("job/")) {
				if (url.endsWith("job"))
					url = baseURL + "/";
				else if (url.endsWith("/"))
					url = baseURL + "job/";
				else
					url = baseURL + "/job/";
			}
			
			if(downloadtype.equalsIgnoreCase("Client")) {
				url = url + configProp.getJenkinsClientProject();
				url = clientURL(url, configProp.getClientWarFilename());
			}
			else if(downloadtype.equalsIgnoreCase("Server")) {
				url = url + configProp.getJenkinsServerProject();
				url = serverURL(url);
			}

			return url;
		}
		catch (FileNotFoundException ex) {
			FailureHelper.setErrorMessage(ex);
			throw ex;
		} 
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String[] getURL(String baseURL, String downloadtype) throws Exception  {
		InputStream in = null;
		BufferedReader br = null;
		
		try {
			String buildNum[] = null;
			int num = 0;
			String[] urlDetails = new String[2];
			String url = baseURL;
			
			if (!url.endsWith("job/")) {
				if (url.endsWith("job"))
					url = baseURL + "/";
				else if (url.endsWith("/"))
					url = baseURL + "job/";
				else
					url = baseURL + "/job/";
			}
			
			if(downloadtype.equalsIgnoreCase("Client")) {
				url = url + configProp.getJenkinsClientProject();
			}
			else if(downloadtype.equalsIgnoreCase("Server")) {
				url = url + configProp.getJenkinsServerProject();
			}
			
			boolean success = false, trend = false, build = false;
			DownloadBinaries download = new DownloadBinaries();
			in = download.getConnection(url, null);
			br = new BufferedReader(new InputStreamReader(in));
			String line = null;
			
			if (ValidationHelper.isFalse(configProp.getUseBuildNumber())) {
				String SuccessTooltip = "tooltip=\"Success &gt; Console Output\"",
						FailedTooltip="tooltip=\"Failed &gt; Console Output\"";
				
				while ((line = br.readLine()) != null) {
					 
					if (line.contains("Build History")) {
						trend = true;
						if (trend && (line.contains(SuccessTooltip) || line.contains(FailedTooltip))) {
							success = true;
							if (line.contains(SuccessTooltip))
								build = true;
							else
								build = false;
						}
					}
					
					if (trend && success) {
						buildNum=line.trim().split("#");
						String[] temp = buildNum[1].split("<");
						buildNum[1] = temp[0];
						success = false;
						break;
					}
				}
				
				if (!build) {
					FailureHelper.failTest("No Proper Build hence exiting");
				}
				
				else
					num = Integer.parseInt(buildNum[1]);
				
				success = false;
			}
			else {
				buildNum = StringHelper.resizeStringArray(buildNum, 2);
				if(downloadtype.equalsIgnoreCase("Client")) {
					num = Integer.parseInt(configProp.getClientBuildNo());
					buildNum[1] = Integer.toString(num);
				}
				if(downloadtype.equalsIgnoreCase("Server")) {
					num = Integer.parseInt(configProp.getServerBuildNo());
					buildNum[1] = Integer.toString(num);
				}
			}
			
			if(downloadtype.equalsIgnoreCase("Client")) {
				urlDetails[0] = clientURL(url, num, configProp.getClientWarFilename());
				urlDetails[1] = buildNum[1].toString();
			}
			else if(downloadtype.equalsIgnoreCase("Server")) {
				urlDetails[0] = serverURL(url);
				urlDetails[1] = buildNum[1].toString();
			}

			return urlDetails;
		}
		catch (MalformedURLException ex) {
			FailureHelper.setErrorMessage(ex);
			throw ex;
		}
		catch (FileNotFoundException ex) {
			FailureHelper.setErrorMessage(ex);
			throw ex;
		} 
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (br != null)
				br.close();
			if (in != null)
				in.close();
		}
	}
	
	public String clientURL(String url, String clientWarFilename) throws Exception {
		try {
			String[] fileVersion = clientWarFilename.split("-", -1);
			splitLimit = fileVersion.length;
			
			if (fileVersion[splitLimit-1].contains("SNAPSHOT"))
				fileVersion[splitLimit-2] = fileVersion[splitLimit-2] + "-SNAPSHOT";
			
			if (!url.endsWith("/"))
				url = url + "/";
			
			return url + clientURL +  clientWarFilename;
		} 
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String clientURL(String url, int buildNumber, String clientWarFilename) throws Exception {
		try {
			String[] fileVersion = clientWarFilename.split("-", -1);
			splitLimit = fileVersion.length;
			
			if (fileVersion[splitLimit-1].contains("SNAPSHOT"))
				fileVersion[splitLimit-2] = fileVersion[splitLimit-2] + "-SNAPSHOT";
			
			if (!url.endsWith("/"))
				url = url + "/";
			
			return url + buildNumber + clientURL +  clientWarFilename;
		} 
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String serverURL(String url) throws Exception {
		try {
			if (url.endsWith("/"))
				return url + serverURL + serverFileName;
			else
				return url + "/" + serverURL + serverFileName;
		} 
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String artifactoryServerURL(String artifactoryURL) throws Exception {
		try {
			String serverProject = configProp.getArtifactoryServerProject();
			if (!artifactoryURL.endsWith("/"))
				artifactoryURL = artifactoryURL + "/";
			
			String version = serverProject;
			if (serverProject.startsWith(artifactServerFileStart))
				version = serverProject.replace(artifactServerFileStart, "");
			else
				serverProject = artifactServerFileStart + serverProject;
			
			if (serverProject.endsWith(".zip"))
				version = serverProject.replace(".zip", "");
			else
				serverProject = serverProject + ".zip";
			
			serverFileName = serverProject;
			return artifactoryURL + artifactServerURL + version + "/" + serverFileName;
		} 
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String artifactoryClientURL(String artifactoryURL) throws Exception {
		try {
			String clientProject = configProp.getArtifactoryClientProject();
			String warFilename = "entrypoint-" + clientProject + ".war";
			if (!artifactoryURL.endsWith("/"))
				artifactoryURL = artifactoryURL + "/";
			
			String version = clientProject;
			if (clientProject.startsWith(artifactClientFileStart))
				version = clientProject.replace(artifactClientFileStart, "");
			
			if (clientProject.endsWith(".war"))
				version = clientProject.replace(".war", "");
			
			return artifactoryURL + artifactClientURL + version + "/" + warFilename;
		} 
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String getServerFileName() throws Exception {
		try {
			return serverFileName;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}