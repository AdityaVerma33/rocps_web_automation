package com.subex.automation.helpers.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.automation.helpers.util.RemoteMachineHelper;


public class DateHelper extends AcceptanceTest {

	public static String getCurrentDate() throws Exception {
		try {
			if (applicationOS.equalsIgnoreCase("Windows")) {
				DateFormat df = new SimpleDateFormat(configProp.getDateFormat());
				Date date = new Date();
				return (df.format(date));
			}
			else {
				String format = StringHelper.removeConsecutiveDuplicate(configProp.getDateFormat());
				format = convertDateCase(format);
				String dateFormat = format.replaceAll("([a-z])", "%$1").replaceAll("([A-Z])", "%$1");
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				String[] result = remoteMachine.executeScripts("date +" + dateFormat);
				return result[0];
			}
		}
		catch(Exception e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getCurrentDate(String format) throws Exception {
		try {
			DateFormat df = new SimpleDateFormat(format);
			Date date = new Date();
			return (df.format(date));
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getCurrentDateTime() throws Exception {
		try {
			if (applicationOS.equalsIgnoreCase("Windows")) {
				DateFormat df = new SimpleDateFormat(configProp.getDateFormat() + " " + configProp.getTimeFormat());
				Date date = new Date();
				return (df.format(date));
			}
			else {
				String format = StringHelper.removeConsecutiveDuplicate(configProp.getDateFormat() + " " + configProp.getTimeFormat());
				format = convertDateCase(format);
				String dateFormat = format.replaceAll("([a-z])", "%$1").replaceAll("([A-Z])", "%$1");
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				String[] result = remoteMachine.executeScripts("date +" + dateFormat);
				return result[0];
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String currentDateTime(String os) throws Exception {
		try {
			if (os.equalsIgnoreCase("Windows")) {
				DateFormat df = new SimpleDateFormat(configProp.getDateFormat() + " " + configProp.getTimeFormat());
				Date date = new Date();
				return (df.format(date));
			}
			else {
				String format = StringHelper.removeConsecutiveDuplicate(configProp.getDateFormat() + " " + configProp.getTimeFormat());
				format = convertDateCase(format);
				String dateFormat = format.replaceAll("([a-z])", "%$1").replaceAll("([A-Z])", "%$1");
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				String[] result = remoteMachine.executeScripts("date +" + dateFormat);
				return result[0];
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getCurrentDateTime(String format) throws Exception {
		try {
			DateFormat df = new SimpleDateFormat(format);
			Date date = new Date();
			return (df.format(date));
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String convertDateCase(String value) throws Exception {
		try {
			String finalValue = "";
			String[][] toConvert = {{"M", "m"}, {"y", "Y"}, {"m", "M"}, {"s", "S"}};
			
			for (int i = 0; i < value.length(); i++) {
				boolean isConverted = false;
				char c = value.charAt(i);
				for (int j = 0; j < toConvert.length; j++) {
					if (c == toConvert[j][0].charAt(0)) {
						finalValue = finalValue + toConvert[j][1];
						isConverted = true;
						j = toConvert.length;
					}
				}
				
				if (!isConverted)
					finalValue = finalValue + c;
			}
			
			return finalValue;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getDuration(Date dt1, Date dt2) throws Exception {
		try {
			long diff = dt2.getTime() - dt1.getTime();
	        long diffSeconds = diff / 1000 % 60;
	        long diffMinutes = diff / (60 * 1000) % 60;
	        long diffHours = diff / (60 * 60 * 1000);
	        int diffInDays = (int) ((dt2.getTime() - dt1.getTime()) / (1000 * 60 * 60 * 24));

	        if (diffInDays > 1) {
	            return diffInDays + " days";
	        } else if (diffHours > 24) {
	           	return diffHours + " hrs";
	        } else if ((diffHours == 24) && (diffMinutes >= 1)) {
	            return diffMinutes + " mins";
	        }
	        else
	        	return diffSeconds + " secs";
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int dateDifference(String format, String firstDate, String secondDate) throws Exception {
		try {
			SimpleDateFormat originalSDF = new SimpleDateFormat(format);
			Date fDate = originalSDF.parse(firstDate);
			Date sDate = originalSDF.parse(secondDate);
			
			int diffInDays = (int) ((fDate.getTime() - sDate.getTime()) / (1000 * 60 * 60 * 24));
			
			return diffInDays;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String convertDate(String originalFormat, String expectedFormat, String value) throws Exception {
		try {
			
			originalFormat = originalFormat.replaceAll("Y", "y").replace("D", "d");
			expectedFormat = expectedFormat.replaceAll("Y", "y").replace("D", "d");
			SimpleDateFormat originalSDF = new SimpleDateFormat(originalFormat);
			SimpleDateFormat expectedSDF = new SimpleDateFormat(expectedFormat);
			
			Date date = originalSDF.parse(value);
			DateTime dTemp = new DateTime(date);
			date = dTemp.toDate();
			String temp = expectedSDF.format(date);
			
			return temp;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getDate(String format, int prune) throws Exception {
		try {
			DateFormat df = new SimpleDateFormat(format);
			final Calendar cal = Calendar.getInstance();
		    cal.add(Calendar.DATE, -prune);
		    
			return (df.format(cal.getTime()));
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String subtractDate(String format, String date, int daysToSubtract) throws Exception {
		try {
			DateFormat df = new SimpleDateFormat(format);
			LocalDate lDate = LocalDate.parse(date);
			lDate = lDate.minusDays(daysToSubtract);
			
			return (df.format(lDate));
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}