package com.subex.rocps.sprintTestCase.bklg109;

import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TestCaseExec extends PSAcceptanceTest {

	@org.testng.annotations.Test(priority = 4)
	public void FileUpload() throws Exception {

		try {
			String filetype = "txt";
			String category = "CarrierInvoice";
			String fileCollectionConfig = "VOD - File Collections_VOD";
			String fileCollName = "File Collections_VOD";
			uploadFileType filetypeobj = new uploadFileType();
			filetypeobj.uploadFileTypeExtension(filetype);
			fileUploadCategory categoryobj = new fileUploadCategory();
			categoryobj.fileUploadCategoryctg(category);
			categoryFileCollectionMapping filecollecobj = new categoryFileCollectionMapping();
			filecollecobj.categoryFileCollection(category, fileCollectionConfig);
			fileUpload fileuploadobj = new fileUpload();
			fileuploadobj.file(category, fileCollName);

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}

	}

}
