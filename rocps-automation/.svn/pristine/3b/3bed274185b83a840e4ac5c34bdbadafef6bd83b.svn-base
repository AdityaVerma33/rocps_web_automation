package com.subex.rocps.automation.helpers.listener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class RetryListener implements IAnnotationTransformer {

	@SuppressWarnings("rawtypes")
	@Override
	public void transform(ITestAnnotation testannotation, Class testClass,Constructor testConstructor, Method testMethod)	
	{
		IRetryAnalyzer retry = testannotation.getRetryAnalyzer();

		try{
		if (retry == null)	{
//			SparkApplicationHelper.refresh();
			testannotation.setRetryAnalyzer(Retry.class);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
}