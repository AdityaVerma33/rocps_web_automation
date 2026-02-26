package com.subex.automation.helpers.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.StringHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;

public class RemoteMachineHelper extends AcceptanceTest {
	
	final int connectionTimeout = 50000;
	static String hostName = null;
	static String username = null;
	static String password = null;
	static int portNumber = 22;

	public RemoteMachineHelper() throws Exception {
		try {
			hostName = configProp.getRemoteHostname();
			username = configProp.getRemoteUsername();
			password = configProp.getRemotePassword();
			portNumber = configProp.getRemotePortNumber();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public RemoteMachineHelper(String hostname, int port, String username, String password) throws Exception {
		try {
			RemoteMachineHelper.hostName = hostname;
			RemoteMachineHelper.username = username;
			RemoteMachineHelper.password = password;
			RemoteMachineHelper.portNumber = port;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public Session createSession() throws Exception {
		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession(username, hostName, portNumber);
			
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(connectionTimeout);
			
			return session;
		} catch (JSchException e) {
			FailureHelper.setError("Could not connect to Remote Machine '" + hostName + "'. May be machine is down or machine credentials are not correct");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createSFTPConnection() throws Exception {
		try {
//			if (sftpSession == null || !sftpSession.isConnected() || sftpChannel.isClosed())
				sftpSession = createSession();
			
			if (sftpChannel == null || !sftpChannel.isConnected() || sftpChannel.isClosed()) {
				sftpChannel = (ChannelSftp) sftpSession.openChannel("sftp");
				sftpChannel.connect();
			}
		} catch (JSchException e) {
			FailureHelper.setError("Could not connect to Remote Machine '" + hostName + "'. May be machine is down or machine credentials are not correct");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createExecConnection() throws Exception {
		try {
//			if (execSession == null || !execSession.isConnected() || execChannel.isClosed())
				execSession = createSession();
				
			if (execChannel == null || !execChannel.isConnected() || execChannel.isClosed()) {
				execChannel = (ChannelExec) execSession.openChannel("exec");
				execChannel.connect();
				Thread.sleep(1000);
			}
		} catch (JSchException e) {
			FailureHelper.setError("Could not connect to Remote Machine '" + hostName + "'. May be machine is down or machine credentials are not correct");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public Channel createConnection(String type) throws Exception {
		try {
			Session session = createSession();
			Channel channel = session.openChannel(type);
			channel.connect();
			
			return channel;
		} catch (JSchException e) {
			FailureHelper.setError("Could not connect to Remote Machine '" + hostName + "'. May be machine is down or machine credentials are not correct");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private String[] getCommandResult(ChannelExec channel, String command, boolean failForErrors) throws Exception {
		try {
			String[] cmdResult = getCommandResult(channel, command, failForErrors, null);
			
			return cmdResult;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private String[] getCommandResult(ChannelExec channel, String command, boolean failForErrors, int waitTime) throws Exception {
		try {
			String[] cmdResult = getCommandResult(channel, command, failForErrors, null, waitTime);
			
			return cmdResult;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private String[] getCommandResult(ChannelExec channel, String command, boolean failForErrors, String checkForMessage) throws Exception {
		try {
			String[] cmdResult = getCommandResult(channel, command, failForErrors, null, 0);
			
			return cmdResult;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private String[] getCommandResult(ChannelExec channel, String command, boolean failForErrors, String checkForMessage, int waitTime) throws Exception {
		InputStream in = null;
		InputStream err = null;
		
		try {
			if (!channel.isConnected() || channel.isClosed()) {
				Session session = channel.getSession();
				channel = (ChannelExec) session.openChannel("exec");
			}
			
			channel.setCommand(command);
			channel.setInputStream(null);
			channel.setErrStream(null);
			in = channel.getInputStream();
			err = channel.getErrStream();
			channel.connect(50000);
			
			String[] cmdResult = new String[100000];
			int i = 0;
			byte[] tmp = new byte[10240];
			boolean started = false;
			int wait = 0;
			
			while (true) {
				while(in.available() > 0) {
					int x = in.read(tmp, 0, 10240);
					cmdResult[i] = new String(tmp, 0, x);
					
			        if ((ValidationHelper.isNotEmpty(checkForMessage) && cmdResult[i].contains(checkForMessage)) || 
			        		(ValidationHelper.isNotEmpty(checkForMessage) && checkForMessage.contains("Stream controller started") && cmdResult[i].contains("Exception occured"))) {
			        	started = true;
			        	break;
			        }
			        
			        i++;
			        wait++;
		        }
				
				if (started)
					break;
				Thread.sleep(5);
				if(channel.isClosed()) {
			        if(in.available()>0)
				    	continue;
			        else
			        	break;
				}
				
				if (waitTime > 0 && wait >= waitTime)
					break;
			}
			
			int exitCode = channel.getExitStatus();
			if (exitCode != 0 && !started) {
				if (failForErrors) {
					String errorText = "";
					while(err.available() > 0) {
				    	int x = err.read(tmp, 0, 1024);
				    	errorText = errorText + new String(tmp, 0, x);
				    }
					
					if (errorText.equals(""))
						FailureHelper.failTest("Command failed with code " + exitCode + ". Please check the command '" + command + "'");
					else
						FailureHelper.failTest("Command failed with code " + exitCode + " : " + errorText);
					return null;
				}
				else
					return null;
			}
			else {
				cmdResult = StringHelper.resizeStringArray(cmdResult, i);
				return cmdResult;
			}
		}
		catch(SocketTimeoutException e) {
			FailureHelper.setError("Connection Timed out");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			if (in != null)
				in.close();
			if (err != null)
				err.close();
		}
	}
	
	private String[][] getCommandResult(ChannelExec channel, String[] command) throws Exception {
		try {
			String[][] cmdResult = new String[command.length][];
			
			for (int i = 0; i < command.length; i++) {
				cmdResult[i] = getCommandResult(channel, command[i], true);
			}
			
			return cmdResult;
		}
		catch(SocketTimeoutException e) {
			FailureHelper.setError("Connection Timed out");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	* Logs out and disconnects from the server
	 * @throws Exception 
	*/
	public void logoutExec(ChannelExec channel) throws Exception {
		try {
			Session session = channel.getSession();
			channel.disconnect();
			session.disconnect();
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void logoutExec(ChannelExec channel, Session session) throws Exception {
		try {
			channel.disconnect();
			session.disconnect();
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void logoutSFTP(ChannelSftp channel) throws Exception {
		try {
			Session session = channel.getSession();
			channel.exit();
			session.disconnect();
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void logoutSFTP(ChannelSftp channel, Session session) throws Exception {
		try {
			channel.exit();
			session.disconnect();
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * Determines whether a directory exists or not
	* @param dirPath
	* @return true if exists, false otherwise
	 * @throws Exception 
	*/
	public boolean checkDirectoryExists(String dirPath) throws Exception {
		try {
			createExecConnection();
			
			boolean exists = checkDirectory(execChannel, dirPath);
			return exists;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean checkDirectoryExists1(ChannelExec channel, String dirPath) throws Exception {
		try {
			if (channel == null)
				FailureHelper.failTest("Connection to remote machine did not get created.");
			else {
				boolean exists = checkDirectory(channel, dirPath);
				return exists;
			}
			
			return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	* Determines whether a file exists or not
	* @param filePath
	* @return true if exists, false otherwise
	 * @throws Exception 
	*/
	public boolean checkFileExists(String filePath) throws Exception {
		try {
			createExecConnection();
			
			boolean exists = checkFile(execChannel, filePath);
			return exists;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean checkFileExists(ChannelExec channel, String filePath) throws Exception {
		try {
			boolean exists = checkFile(channel, filePath);
			return exists;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void copyFile(String srcFileNameWithPath, String destPath, String destFilename, boolean failTestCase) throws Exception {
		try {
			srcFileNameWithPath = GenericHelper.getPath(applicationOS, srcFileNameWithPath);
			destPath = GenericHelper.getPath(applicationOS, destPath);
			
			if (srcFileNameWithPath.substring(1, 2).contains(":") || destPath.substring(1, 2).contains(":")) {
				createSFTPConnection();
				copyFile(sftpChannel, srcFileNameWithPath, destPath, destFilename, failTestCase);
			}
			else {
				String command = "cp " + srcFileNameWithPath + " " + destPath + "/" + destFilename;
				executeScripts(command, true);
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	public void copyFile(ChannelSftp channel, String srcFileNameWithPath, String destPath, String destFilename, boolean failTestCase) throws Exception {
		FileInputStream fis = null;
		
		try {
			srcFileNameWithPath = GenericHelper.getPath(applicationOS, srcFileNameWithPath);
			destPath = GenericHelper.getPath(applicationOS, destPath);
			if (!channel.isConnected())
				channel.connect();
			
			if (srcFileNameWithPath.substring(1, 2).contains(":")) {
				File f1 = new File(srcFileNameWithPath);
				if (!f1.exists() && failTestCase)
					FailureHelper.failTest("Source Directory '" + srcFileNameWithPath + "' does not exists.");
				
				fis = new FileInputStream(f1);
				channel.cd(destPath);
				channel.put(fis, f1.getName(), ChannelSftp.OVERWRITE);
				channel.rename(f1.getName(), destFilename);
			}
			else if (destPath.substring(1, 2).contains(":")) {
				int index = srcFileNameWithPath.lastIndexOf("/");
				String sourcePath = srcFileNameWithPath.substring(0, index);
				String sourceFile = srcFileNameWithPath.substring(index+1);
				
				if (!checkFileExists(srcFileNameWithPath) && failTestCase)
					FailureHelper.failTest("Source Directory '" + srcFileNameWithPath + "' does not exists.");
				
				if (!destPath.endsWith("\\") && !destPath.endsWith("/"))
					destPath = destPath + "/";
				channel.cd(sourcePath);
				channel.get(sourceFile, destPath);
			}
		} catch (SftpException e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		} finally {
			if (fis != null)
				fis.close();
		}
	}
	
	public void copyFiles(String[] srcFileNameWithPath, String[] destPath, String[] destFilename, boolean failTestCase) throws Exception {
		try {
			createSFTPConnection();
			createExecConnection();
			
			copyFiles(sftpChannel, execChannel, srcFileNameWithPath, destPath, destFilename, failTestCase);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	public void copyFiles(ChannelSftp sftpChannel, ChannelExec execChannel, String[] srcFileNameWithPath, String[] destPath, String[] destFilename, boolean failTestCase) throws Exception {
		try {
			int length = srcFileNameWithPath.length;
			String[] remoteCopySrc = new String[length];
			String[] remoteCopyDest = new String[length];
			String[] remoteCopyFile = new String[length];
			String[] command = new String[length];
			
			int remote = 0;
			int local = 0;
			for (int i = 0; i < srcFileNameWithPath.length; i++) {
				srcFileNameWithPath[i] = GenericHelper.getPath(applicationOS, srcFileNameWithPath[i]);
				destPath[i] = GenericHelper.getPath(applicationOS, destPath[i]);
				
				if (srcFileNameWithPath[i].substring(1, 2).contains(":")) {
					remoteCopySrc[remote] = srcFileNameWithPath[i];
					remoteCopyDest[remote] = destPath[i];
					remoteCopyFile[remote] = destFilename[i];
					remote++;
				}
				else {
					command[local] = "cp " + srcFileNameWithPath[i] + " " + destPath[i] + "/" + destFilename[i];
					local++;
				}
			}
			
			if (remote > 0) {
				remoteCopySrc = StringHelper.resizeStringArray(remoteCopySrc, remote);
				remoteCopyDest = StringHelper.resizeStringArray(remoteCopySrc, remote);
				remoteCopyFile = StringHelper.resizeStringArray(remoteCopySrc, remote);
				
				remoteCopy(sftpChannel, remoteCopySrc, remoteCopyDest, remoteCopyFile, failTestCase);
			}
			
			if (local > 0) {
				command = StringHelper.resizeStringArray(command, local);
				getCommandResult(execChannel, command);
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	private void remoteCopy(ChannelSftp channel, String[] srcFileNameWithPath, String[] destPath, String[] destFilename, boolean failTestCase) throws Exception {
		 FileInputStream fis = null;
		try {
			if (!channel.isConnected())
				channel.connect();
	           
	        for (int i = 0; i < srcFileNameWithPath.length; i++) {
		        File f1 = new File(GenericHelper.getPath(applicationOS, srcFileNameWithPath[i]));
		        fis = new FileInputStream(f1);
		        
		        destPath[i] = GenericHelper.getPath(applicationOS, destPath[i]);
		        if (!checkDirectory(destPath[i]))
		        	channel.mkdir(destPath[i]);
		        
		        if (!new File(destPath[i] + "/" + destFilename[i]).exists())
		        	FailureHelper.failTest("Source Directory '" + destPath[i] + "/" + destFilename[i] + "' does not exists.");
		        
		        channel.cd(destPath[i]);
		        channel.put(fis, f1.getName(), ChannelSftp.OVERWRITE);
		        channel.rename(f1.getName(), destFilename[i]);
		        Log4jHelper.logInfo("File '" + destPath[i] + "/" + destFilename[i] + "' copied");
	        }
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		} finally {
			if (fis != null)
				fis.close();
		}
	}
	
	/*
	* Deletes the specified file
	* @param fileWithPath
	* @return true if deleted, false otherwise
	 * @throws Exception 
	*/
	public void deleteFile(String fileWithPath) throws Exception {
		try {
			createSFTPConnection();
			
			deleteFile(sftpChannel, execChannel, fileWithPath);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void deleteFile(ChannelSftp sftpChannel, ChannelExec execChannel, String fileWithPath) throws Exception {
		try {
			if (!sftpChannel.isConnected())
				sftpChannel.connect();
			if (!execChannel.isConnected())
				execChannel.connect();
			
	        fileWithPath = GenericHelper.getPath(applicationOS, fileWithPath);
	        if (checkFile(execChannel, fileWithPath))
	        	sftpChannel.rm(fileWithPath);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String[] executeScripts(String command) throws Exception {
		try {
			createExecConnection();
			
			Thread.sleep(1000);
			String[] cmdResult = getCommandResult(execChannel, command, true);
			return cmdResult;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String[] executeScripts(String command, String checkForMessage) throws Exception {
		try {
			createExecConnection();
			
			String[] cmdResult = getCommandResult(execChannel, command, true, checkForMessage);
			return cmdResult;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String[] executeScripts(String command, boolean failForErrors) throws Exception {
		try {
			createExecConnection();
			
			String[] cmdResult = getCommandResult(execChannel, command, failForErrors);
			return cmdResult;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String[] executeScripts(String command, boolean failForErrors, int waitTime) throws Exception {
		try {
			createExecConnection();
			
			String[] cmdResult = getCommandResult(execChannel, command, failForErrors, waitTime);
			return cmdResult;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private String getExtractCommand(String filePath, String fileName) throws Exception {
		try {
			String command = null;
			
			if (fileName.endsWith("gz"))
				command = "cd " + filePath + " && gunzip " + fileName + " && tar -xvf " + fileName.replace(".gz", "");
			else if (fileName.endsWith("tar"))
				command = "cd " + filePath + " && tar -xzf " + fileName;
			else if (fileName.endsWith("zip") || fileName.endsWith("war"))
				command = "cd " + filePath + " && unzip -o " + fileName + " -d " + fileName.replace(".war", "");
			
			return command;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void extractFiles(String[] filePath, String[] fileName) throws Exception {
		try {
			createExecConnection();
			
			extractFiles(execChannel, filePath, fileName);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void extractFiles(ChannelExec channel, String[] filePath, String[] fileName) throws Exception {
		try {
			for (int i = 0; i < filePath.length; i++) {
	        	filePath[i] = GenericHelper.getPath(applicationOS, filePath[i]);
	        	
	        	if (fileName[i].endsWith("gz") || fileName[i].endsWith("tar") || fileName[i].endsWith("zip") || fileName[i].endsWith("war")) {
	        		if (checkFileExists(channel, filePath[i] + "/" + fileName[i])) {
	        			String command = getExtractCommand(filePath[i], fileName[i]);
						
						if (command != null) {
							if (!channel.isConnected())
		        				channel.connect();
							String[] cmdResult = getCommandResult(channel, command, true);
							
							if (ValidationHelper.isEmpty(cmdResult) || cmdResult[cmdResult.length-1].endsWith("...")) {
								FailureHelper.failTest("Extracting '" + filePath[i] + "/" + fileName[i] + "' failed.");
							}
						}
	        		}
	        	}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void getFile(String srcFileNameWithPath, String destFileNameWithPath) throws Exception {
		try {
			createSFTPConnection();
			
			getFile(sftpChannel, srcFileNameWithPath, destFileNameWithPath);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	public void getFile(ChannelSftp channel, String srcFileNameWithPath, String destFileNameWithPath) throws Exception {
		try {
			if (!channel.isConnected())
				channel.connect();
   
			String src = GenericHelper.getPath(applicationOS, srcFileNameWithPath);
	        File f1 = new File(GenericHelper.getPath(applicationOS, destFileNameWithPath));
	        channel.get(src, new FileOutputStream(f1));
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	public void deleteFolder(String directory) throws Exception {
		try {
			createSFTPConnection();
			
			deleteFolder(sftpChannel, directory);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void deleteFolder(ChannelSftp channel, String directory) throws Exception {
		try {
			if (!channel.isConnected())
				channel.connect();
			directory = GenericHelper.getPath(applicationOS, directory);
			SftpATTRS attr = null;
			
			try {
				attr = channel.stat(directory);
			} catch (Exception e) {
				
			}
			
			if (attr != null) {
				if (attr.isDir()) {
					channel.cd(directory);
		        	
		            @SuppressWarnings("unchecked")
					Vector <LsEntry> entries = channel.ls(directory);
		            for (LsEntry entry: entries) {
		            	if (entry != null && !entry.getFilename().equals(".") && !entry.getFilename().equals("..")) {
		            		deleteFolder(channel, directory + "/" + entry.getFilename());
		            	}
		            }
		            
		            channel.cd("..");
		            channel.rmdir(directory);
		            Log4jHelper.logInfo("Deleted folder '" + directory + "'");
		        }
				else {
					channel.rm(directory);
		        	Log4jHelper.logInfo("Deleted file '" + directory + "'");
		        }
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private boolean checkDirectory(String dirPath) throws Exception {
		try {
			boolean exists = checkDirectory(execChannel, dirPath);
			return exists;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private boolean checkDirectory(ChannelExec channel, String dirPath) throws Exception {
		try {
			dirPath = GenericHelper.getPath(applicationOS, dirPath);
			String command = "[ -d " + dirPath + " ] && echo 'Directory found' || echo 'Directory not found'";
			String[] cmdResult = getCommandResult(channel, command, true);
			
			if (ValidationHelper.isNotEmpty(cmdResult)) {
				cmdResult[0] = cmdResult[0].replace("\n", "").replace("\r", "");
				if (cmdResult[0].contains("Directory found"))
					return true;
				else
					return false;
			}
			
			return false;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private boolean checkFile(ChannelExec channel, String filePath) throws Exception {
		try {
			String command = "[ -f \"" + GenericHelper.getPath(applicationOS, filePath) + "\" ] && echo 'File found' || echo 'File not found'";
			String[] cmdResult = getCommandResult(channel, command, true);
			
			if (ValidationHelper.isNotEmpty(cmdResult) && cmdResult[0].contains("File found"))
				return true;
			else
				return false;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void copyDirectory(String srcFileNameWithPath, String destPath, String destinationFileName, boolean failTestCase) throws Exception {
		try {
			createSFTPConnection();
			
			copyDirectory(sftpChannel, srcFileNameWithPath, destPath, destinationFileName, failTestCase);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	public void copyDirectory(ChannelSftp channel, String srcFileNameWithPath, String destPath, String destinationFileName, boolean failTestCase) throws Exception {
		try {
			srcFileNameWithPath = GenericHelper.getPath(applicationOS, srcFileNameWithPath);
			destPath = GenericHelper.getPath(applicationOS, destPath);
			if (!destPath.endsWith("/"))
				destPath = destPath + "/";
			
			if (destPath.startsWith("/")) {
				folderUpload(srcFileNameWithPath, destPath, destinationFileName, failTestCase);
	        }
	        else {
	        	if (destPath.startsWith("/")) {
		        	if (!channel.isConnected())
						channel.connect();
		        	channel.get(srcFileNameWithPath, destPath);
	        	}
	        	else {
	        		String remoteMachine = configProp.getRemoteHostname();
	        		String remoteUsername = configProp.getRemoteUsername();
	        		String remotePassword = configProp.getRemotePassword();
	        		String command = "sshpass -p '" + remotePassword + "' scp " + remoteUsername + "@" + remoteMachine + ":" + srcFileNameWithPath + " " + destPath;
	        		executeScripts(command, true);
	        	}
	        }
	        
	        Log4jHelper.logInfo("Copied file '" + srcFileNameWithPath + "' as '" + destPath + "/" + destinationFileName + "'");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	public void moveDirectory(String srcFileNameWithPath, String destPath, boolean failTestCase) throws Exception {
		try {
			createSFTPConnection();
			
			moveDirectory(sftpChannel, srcFileNameWithPath, destPath, failTestCase);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	public void moveDirectory(ChannelSftp channel, String srcFileNameWithPath, String destPath, boolean failTestCase) throws Exception {
		try {
			String 	command = null;
			srcFileNameWithPath = GenericHelper.getPath(applicationOS, srcFileNameWithPath);
			destPath = GenericHelper.getPath(applicationOS, destPath);
        	
    		if (srcFileNameWithPath.startsWith("/"))
    			command = "mv " + srcFileNameWithPath + " " + destPath;
    		else {
    			String username = configProp.getRemoteUsername();
    			String password = configProp.getRemotePassword();
    			String delimiter = "\\\\";
    			if (srcFileNameWithPath.contains("/"))
    				delimiter = "/";
    			String[] temp = srcFileNameWithPath.split(delimiter, -1);
    			String outputFilename = temp[temp.length - 1];
    			command = "cd " + destPath + " && curl -o " + outputFilename + " -u " + username + ":" + password + " \"" + srcFileNameWithPath + "\"";
    		}
        	
        	executeScripts(command);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}

	private void folderUpload(String sourcePath, String destinationPath, String destinationFileName, boolean failTestCase) throws Exception {
		try {
        	sourcePath = GenericHelper.getPath(applicationOS, sourcePath);
        	destinationPath = GenericHelper.getPath(applicationOS, destinationPath);
        	
        	if (checkFileExists(sourcePath))
        		copyFile(sourcePath, destinationPath, destinationFileName, true);
        	else {
        		String 	command = null;
        		if (!sourcePath.endsWith("/"))
        			sourcePath = sourcePath + "/";
        		if (sourcePath.startsWith("/"))
        			command = "cp -r " + sourcePath + " " + destinationPath + destinationFileName;
        		else {
        			String username = configProp.getRemoteUsername();
        			String password = configProp.getRemotePassword();
        			command = "cd " + destinationPath + " && curl -o " + destinationFileName + " -u " + username + ":" + password + " \"" + sourcePath + "\"";
        		}
            	executeScripts(command);
        	}
        } catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
    }
	
	public void copyRemoteMachineFiles(String remoteMachine,String remoteMachineUserName, String remoteMachinePassword, String srcFileNameWithPath,String destFileNameWithPath) throws Exception {
		try {
			createSFTPConnection();
			
			String command = "sshpass -p '"+ remoteMachinePassword+ "' scp "+remoteMachineUserName+"@"+remoteMachine+":"+srcFileNameWithPath+" "+ destFileNameWithPath;
			executeScripts(command, true);
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
}