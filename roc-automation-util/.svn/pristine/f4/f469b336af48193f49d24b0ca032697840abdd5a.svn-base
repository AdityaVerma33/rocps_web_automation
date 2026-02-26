package com.subex.automation.helpers.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;

public class OR_Reader
{
	private static Properties OR;
	
	public  OR_Reader() {
	}
	
	public Properties getOR(String[] files) throws Exception {
		try {
			if( OR == null ) {
				initializeOR(files);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return OR;
	}
	
	public Properties getOR(InputStream ipStream, String fileName) throws Exception {
		try {
			initializeOR(ipStream, fileName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return OR;
	}
	
	private void initializeOR(InputStream ipStream, String fileName) throws Exception  {
		try {
			if (OR == null) {
				OR = new Properties();
				OR.load(ipStream);
			}
			else {
				try {
					if (!fileName.equals("OR.properties")) {
						Properties tempProperty = new Properties();
						tempProperty.load(ipStream);
						OR.putAll(tempProperty);
					}
				}
				catch (FileNotFoundException e)  {
					e.printStackTrace();
				}
				catch (IOException e)  {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initializeOR(String[] files) throws Exception  {
		try {
			OR = new Properties();
			FileInputStream fn = null;
			OR.load(fn);
			
			if (ValidationHelper.isNotEmpty(files)) {
				try {
					for (int i = 0; i < files.length; i++) {
						if (!files[i].equalsIgnoreCase("OR.properties")) {
							String fileName = GenericHelper.getPath(AcceptanceTest.automationOS, files[i]);
							fn = new FileInputStream(fileName);
							Properties tempProperty = new Properties();
							tempProperty.load(fn);
							OR.putAll(tempProperty);
						}
					}
				}
				catch (FileNotFoundException e)  {
					e.printStackTrace();
				}
				catch (IOException e)  {
					e.printStackTrace();
				}
				finally {
					if (fn != null)
						fn.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}