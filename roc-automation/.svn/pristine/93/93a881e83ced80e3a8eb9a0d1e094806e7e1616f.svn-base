package com.subex.automation.helpers.dataGeneration;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.scripts.MethodHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class DataHelper extends ROCAcceptanceTest {
	
	static HashMap<String, ArrayList<String>> testData = null;
	String dataFileName = null;
	String dataSheetName = null;
	String excelPath = null;
	
	public DataHelper(String excelPath, String dataFileName, String dataSheetName) {
		this.excelPath = excelPath;
		this.dataFileName = dataFileName;
		this.dataSheetName = dataSheetName;
	}
	
	private void updateParameters(String srcName) throws Exception {
		try {
			MethodHelper methodHelper = new MethodHelper();
			methodHelper.updateMethodOccurance(srcName);
			int occurance = methodHelper.getMethodOccurance(srcName);
			
			testData = null;
			if (ValidationHelper.isNotEmpty(dataFileName) && ValidationHelper.isNotEmpty(dataSheetName)) {
				ExcelReader excelReader = new ExcelReader();
				testData = excelReader.readDataByColumn(excelPath, dataFileName, dataSheetName, srcName, occurance);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void dataGeneration(String sampleFileName, String srcName, String recCount) throws Exception {
		try {
			
			updateParameters(srcName);
			
			SimpleDataHelper sdh = new SimpleDataHelper(srcName, recCount);
			sdh.dataGeneration(testData, sampleFileName);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void dataGeneration(String sampleFileName, String srcName, String recCount, String value) throws Exception {
		try {
			
			updateParameters(srcName);
			
			SimpleDataHelper sdh = new SimpleDataHelper(srcName, recCount, value);
			sdh.dataGeneration(testData, sampleFileName);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void dataGeneration(String sampleFileName, String srcName, String recCount, String lookback, String value) throws Exception {
		try {
			
			updateParameters(srcName);
			
			SimpleDataHelper sdh = new SimpleDataHelper(srcName, recCount, lookback, value);
			sdh.dataGeneration(testData, sampleFileName);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void dataGeneration(String sampleFileName, String srcName, String recCount, String lookback, String dateformat, String value) throws Exception {
		try {
		
			updateParameters(srcName);
			
			SimpleDataHelper sdh = new SimpleDataHelper(srcName, recCount, lookback, dateformat, value);
			sdh.dataGeneration(testData, sampleFileName);
		
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void dataGeneration(String sampleFileName, String srcName, String recCount, String lookback, String dateformat, String sourceDir, String addHeader) throws Exception {
		try {
		
			updateParameters(srcName);
			
			SimpleDataHelper sdh = new SimpleDataHelper(srcName, recCount, lookback, dateformat, sourceDir, addHeader);
			sdh.dataGeneration(testData, sampleFileName);
		
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void sampleDataGeneration(String sampleFileName, String srcName, String recCount, String sampleFile) throws Exception {
		try {
			
			updateParameters(srcName);
			
			SamplingDataHelper sdh = new SamplingDataHelper(srcName, recCount, sampleFile);
			sdh.dataGeneration(testData, sampleFileName);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void sampleDataGeneration(String sampleFileName, String srcName, String recCount, String sampleFile, String value) throws Exception {
		try {
			
			updateParameters(srcName);
			
			SamplingDataHelper sdh = new SamplingDataHelper(srcName, recCount, sampleFile, value);
			sdh.dataGeneration(testData, sampleFileName);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void sampleDataGeneration(String sampleFileName, String srcName, String recCount, String sampleFile, String lookback, String value) throws Exception {
		try {
			
			updateParameters(srcName);
			
			SamplingDataHelper sdh = new SamplingDataHelper(srcName, recCount, sampleFile, lookback, value);
			sdh.dataGeneration(testData, sampleFileName);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void sampleDataGeneration(String sampleFileName, String srcName, String recCount, String sampleFile, String lookback, String dateformat, String value) throws Exception {
		try {
			
			updateParameters(srcName);
			
			SamplingDataHelper sdh = new SamplingDataHelper(srcName, recCount, sampleFile, lookback, dateformat, value);
			sdh.dataGeneration(testData, sampleFileName);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void sampleDataGeneration(String sampleFileName, String srcName, String recCount, String sampleFile, String lookback, String dateformat, String sourceDir, String value) throws Exception {
		try {
			
			updateParameters(srcName);
			
			SamplingDataHelper sdh = new SamplingDataHelper(srcName, recCount, sampleFile, lookback, dateformat, sourceDir, value);
			sdh.dataGeneration(testData, sampleFileName);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}