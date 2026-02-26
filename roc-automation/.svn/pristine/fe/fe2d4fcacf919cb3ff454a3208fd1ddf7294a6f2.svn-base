package com.subex.automation.helpers.application;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.EntitySearchHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class NotesHelper extends ROCAcceptanceTest {
	
	public void addNotes(String path, String workBookName, String workSheetName, String testCaseName, int occurance) throws Exception 
	{
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurance);
			
			for(int i = 0; i < excelData.get("Note Name").size(); i++)
			{
				String noteName = excelData.get("Note Name").get(i);
				String description = excelData.get("Description").get(i);
				String[] attachmentType = testData.getStringValue(excelData.get("Attachment Type").get(i), firstLevelDelimiter);
				
				String[] entity = testData.getStringValue(excelData.get("Entity").get(i), firstLevelDelimiter);
				String[] entityScreenTitle = testData.getStringValue(excelData.get("Entity Screen Title").get(i), firstLevelDelimiter);
				String[] entityValue = testData.getStringValue(excelData.get("Entity Value").get(i), firstLevelDelimiter);
				String[] entityValueColHeader = testData.getStringValue(excelData.get("Entity Value Column Header").get(i), firstLevelDelimiter);
				
				String[] fileName = testData.getStringValue(excelData.get("Filename").get(i), firstLevelDelimiter);
				
				String[] label = testData.getStringValue(excelData.get("Label").get(i), firstLevelDelimiter);
				String[] hyperlink = testData.getStringValue(excelData.get("Hyperlink").get(i), firstLevelDelimiter);
				
				addNotes(noteName, description, attachmentType, entity, entityScreenTitle, entityValue, entityValueColHeader, fileName, label, hyperlink);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to add Notes that is enabled through Entities screen.
	 * @param noteName
	 * @param description
	 * @param attachmentType
	 * @param entity
	 * @param entityScreenTitle
	 * @param entityValue
	 * @param entityValueColHeader
	 * @param fileName
	 * @param label
	 * @param hyperlink
	 * @throws Exception
	 */
	public void addNotes(String noteName, String description, String[] attachmentType, String[] entity, String[] entityScreenTitle,
			String[] entityValue, String[] entityValueColHeader, String[] fileName, String[] label, String[] hyperlink) throws Exception {
		try {
			NavigationHelper.navigateToAction("Notes", "New");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("New Note"));
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			TextBoxHelper.type("Note_Name", noteName);
			TextAreaHelper.type("Note_Description", description);
			
			addAttachment(attachmentType, entity, entityScreenTitle, entityValue, entityValueColHeader, fileName, label, hyperlink);
			
			ButtonHelper.click("Note_Save");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addAttachment(String[] attachmentType, String[] entity, String[] entityScreenTitle, String[] entityValue, String[] entityValueColHeader,
			String[] fileName, String[] label, String[] hyperlink) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(attachmentType)) {
				int eCount = 0;
				int fCount = 0;
				int hCount = 0;
				int rows = GridHelper.getRowCount("Note_Attachments_Grid");
				rows++;
				
				for (int i = 0; i < attachmentType.length; i++, rows++) {
					String value = null;
					ButtonHelper.click("Note_Attachments_Add");
					
					switch (attachmentType[i]) {
					case "Entity":
						boolean attachEntity = true;
						if (ValidationHelper.isEmpty(entity) || entity.length <= eCount || ValidationHelper.isEmpty(entity[eCount]))
							attachEntity = false;
						
						if (ValidationHelper.isEmpty(entityScreenTitle) || entityScreenTitle.length <= eCount || ValidationHelper.isEmpty(entityScreenTitle[eCount]))
							attachEntity = false;
						
						if (ValidationHelper.isEmpty(entityValue) || entityValue.length <= eCount || ValidationHelper.isEmpty(entityValue[eCount]))
							attachEntity = false;
						
						if (ValidationHelper.isEmpty(entityValueColHeader) || entityValueColHeader.length <= eCount || ValidationHelper.isEmpty(entityValueColHeader[eCount]))
							attachEntity = false;
						
						if (attachEntity)
							value = attachEntity(entity[eCount], entityScreenTitle[eCount], entityValue[eCount], entityValueColHeader[eCount]);
						eCount++;
						break;
						
					case "File":
						if (ValidationHelper.isNotEmpty(fileName) && fileName.length > fCount && ValidationHelper.isNotEmpty(fileName[fCount])) {
							String fileNameWithPath = GenericHelper.getDataPath(fileName[fCount]);
							value = attachFile(fileNameWithPath);
						}
						
						fCount++;
						break;
						
					case "Hyperlink":
						boolean attachHyperlink = true;
						if (ValidationHelper.isEmpty(label) || label.length <= hCount || ValidationHelper.isEmpty(label[hCount]))
							attachHyperlink = false;
						
						if (ValidationHelper.isEmpty(hyperlink) || hyperlink.length <= hCount || ValidationHelper.isEmpty(hyperlink[hCount]))
							attachHyperlink = false;
						
						if (attachHyperlink)
							value = attachHyperlink(label[hCount], hyperlink[hCount]);
						hCount++;
						break;
						
					default:
						break;
					}
					
					if (ValidationHelper.isNotEmpty(value)) {
						assertTrue(GridHelper.isValuePresent("Note_Attachments_Grid", rows, attachmentType[i], "Type"));
						assertTrue(GridHelper.isValuePresent("Note_Attachments_Grid", rows, value, "Name"));
					}
				}
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String attachEntity(String entity, String entityScreenTitle, String entityValue, String entityValueColHeader) throws Exception {
		try {
			NavigationHelper.navigateToAction("Entity");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			EntitySearchHelper entitySearch = new EntitySearchHelper();
			entitySearch.selectUsingGridFilterTextBox("Entity Search", "Entities_Entity", entity, "Entity");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			Thread.sleep(1000);
			
			entitySearch.select(entityScreenTitle, entityValue, entityValueColHeader);
			return entity + ":" + entityValue;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String attachFile(String fileNameWithPath) throws Exception {
		try {
			NavigationHelper.navigateToAction("File");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Attach File"));
			
			fileNameWithPath = GenericHelper.getPath(fileNameWithPath);
			GenericHelper.fileUpload(fileNameWithPath);
			ButtonHelper.click("Note_Attachments_FileUpload");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			int index = fileNameWithPath.lastIndexOf("\\");
			String fileName = fileNameWithPath.substring(index+1);
			return fileName;
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String attachHyperlink(String label, String hyperlink) throws Exception {
		try {
			NavigationHelper.navigateToAction("Hyperlink");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Add Hyperlink"));
			
			TextBoxHelper.type("Note_Attachments_Hyperlink_Label", label);
			TextBoxHelper.type("Note_Attachments_Hyperlink_Hyperlink", hyperlink);
			ButtonHelper.click("Note_Attachments_Hyperlink_Save");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			return label + "(" + hyperlink + ")";
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}