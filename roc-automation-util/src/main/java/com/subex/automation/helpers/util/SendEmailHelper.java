package com.subex.automation.helpers.util;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;

public class SendEmailHelper extends AcceptanceTest {
	final String userName = "support.alerts@subex.com";
	
	private Session getSession(String host) throws Exception {
		try {
			Properties properties = System.getProperties();
			properties.setProperty("mail.transport.protocol", "smtp");
			 properties.setProperty("mail.smtp.host", host);
		    properties.setProperty("mail.smtp.port", "25");
		    properties.setProperty("mail.smtp.connectiontimeout", "30000");
		    
		    Session session = Session.getInstance(properties);
			
		   return session;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void sendMail(String host, String userName, String toMailId, String subject, StringBuffer content) throws Exception {
		try {
			Session session = getSession(host);
		    MimeMessage message = new MimeMessage(session);  
	        message.setFrom(new InternetAddress(userName));  
	        message.addRecipient(Message.RecipientType.TO,new InternetAddress(toMailId));  
	        message.setSubject(subject);  
	        message.setText(content.toString());
	  
	        Transport.send(message);  
	        Log4jHelper.logInfo("message sent successfully....");
		} 
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void sendMail(String host, String[] toMailId, String[] ccMailId, String subject, StringBuffer content, String fileName) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(toMailId)) {
				Session session = getSession(host);
			    
			    MimeMessage message = new MimeMessage(session);  
		        message.setFrom(new InternetAddress(userName));
		        for (int i = 0; i < toMailId.length; i++)
		        	message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMailId[i]));
		        
		        if (ValidationHelper.isNotEmpty(ccMailId) && !ccMailId[0].equals("") && !ccMailId[0].equals(" ")) {
		        	for (int j = 0; j < ccMailId.length; j++)
		        		message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccMailId[j]));
		        }
		        
		        message.setSubject(subject);
		        
		        BodyPart messageBodyPart1 = new MimeBodyPart();  
		        messageBodyPart1.setText(content.toString());
		        
		        MimeBodyPart messageBodyPart2 = new MimeBodyPart();
		        DataSource source = new FileDataSource(fileName);  
		        messageBodyPart2.setDataHandler(new DataHandler(source));
		        String filename = new File(fileName).getName();
		        messageBodyPart2.setFileName(filename);
		    
		        Multipart multipart = new MimeMultipart();  
		        multipart.addBodyPart(messageBodyPart1);
		        multipart.addBodyPart(messageBodyPart2);
		        
		        message.setContent(multipart);
		        Transport.send(message);
		        Log4jHelper.logInfo("Mail sent successfully....");
			}
		} catch (MessagingException me) {
			FailureHelper.setErrorMessage(me);
			throw me;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void sendMail(String host, String[] toMailId, String[] ccMailId, String subject, StringBuffer content, String[] fileName) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(toMailId)) {
				Session session = getSession(host);
			    
			    MimeMessage message = new MimeMessage(session);  
		        message.setFrom(new InternetAddress(userName));
		        for (int i = 0; i < toMailId.length; i++)
		        	message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMailId[i]));
		        
		        if (ValidationHelper.isNotEmpty(ccMailId) && !ccMailId[0].equals("") && !ccMailId[0].equals(" ")) {
		        	for (int j = 0; j < ccMailId.length; j++)
		        		message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccMailId[j]));
		        }
		        
		        BodyPart messageBodyPart1 = new MimeBodyPart();  
		        messageBodyPart1.setText(content.toString());
		        Multipart multipart = new MimeMultipart();  
		        multipart.addBodyPart(messageBodyPart1);
		        
		        for (int i = 0; i < fileName.length; i++) {
		        	MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			        DataSource source = new FileDataSource(fileName[i]);  
			        messageBodyPart2.setDataHandler(new DataHandler(source));
			        String filename = new File(fileName[i]).getName();
			        messageBodyPart2.setFileName(filename);
			        multipart.addBodyPart(messageBodyPart2);
		        }
		        
		        message.setSubject(subject);
		        message.setContent(multipart);
		        Transport.send(message);
		        Log4jHelper.logInfo("Mail sent successfully....");
			}
		} catch (MessagingException me) {
			FailureHelper.setErrorMessage(me);
			throw me;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}