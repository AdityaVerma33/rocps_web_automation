package com.subex.automation.helpers.scripts;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.data.StringHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class InvokeMethodHelper extends AcceptanceTest {
	
	private static String[] helpers = new String[100];
	private static String[] helperClass = new String[100];
	
	public InvokeMethodHelper(String fileNameWithPath) throws Exception {
		helpers = FileHelper.readFileContent(automationOS, fileNameWithPath);
		for (int i = 0; i < helpers.length; i++) {
			String[] temp = helpers[i].split("\\.");
			helperClass[i] = temp[temp.length-1].replace("Helper", "");
		}
		
		helperClass = StringHelper.resizeStringArray(helperClass);
	}
	
	public String[] getHelper() {
		return helpers;
	}
	
	public String[] getHelperClass() {
		return helperClass;
	}
	
	private static void invokeMethod( String className, String action, String[] methodParameters, boolean noValue ) throws Exception {
		try {
			Class< ? > applicationClass = null;
			applicationClass = Class.forName(className);
			
			if ( applicationClass != null ) {
				int count = 0;
				if (ValidationHelper.isNotEmpty(methodParameters))
					count = methodParameters.length;
				Class< ? >[] paramType = getParamTypes(count);
				
				Method methodName = applicationClass.getDeclaredMethod(action, paramType);
				if (methodName == null || methodName.equals("")) {
					FailureHelper.failTest("Method '" + action + "' not found in Class '" + className + "'");
				}
				
				Object[] parameters = methodParameters;
				if (paramType == null) {
					methodName.invoke( null );
				}
				else {
					methodName.invoke( null, parameters );
				}
			}
			
		}
		catch ( NoSuchMethodException e ) {
			if(noValue) {
				// Ignoring method call if no value is passed
			}
			else {
				FailureHelper.setError("Method '" + action + "' not found in Class '" + className + "' with number of arguments '" + methodParameters.length + "'");
				FailureHelper.setErrorMessage(e);
				throw e;
			}
		}
		catch (InvocationTargetException e) {
			FailureHelper.setError("Error occured inside '" + className + "." + action + "'");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch ( Exception e ) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static Class< ? >[] getParamTypes(int count) throws Exception {
		try {
			
			if (count == 0)
				return null;
			else {
				Class< ? >[] dummy = new Class< ? >[count];
			
				for (int j = 0; j < count; j++)
					dummy[j] = String.class;
			
				return dummy;
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getClassName(String helper) throws Exception {
		try {
			String className = null;
			for (int i = 0; i < helperClass.length; i++) {
				if (helperClass[i] != null && helper.equals(helperClass[i])) {
					return helpers[i];
				}
			}
			
			return className;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void callAction( String helper, String helperAction, String[] methodParameters, boolean noValue ) throws Exception {
		try {
			String className = getClassName(helper);
			
			if (className == null) {
				FailureHelper.failTest("Class file '" + helper + "' is not part of helpers.properties file. Please add the same.");
			}
			else
				invokeMethod( className, helperAction, methodParameters, noValue);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}