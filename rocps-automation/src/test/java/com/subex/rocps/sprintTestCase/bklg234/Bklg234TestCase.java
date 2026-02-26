package com.subex.rocps.sprintTestCase.bklg234;

import java.util.HashMap;

import com.subex.automation.helpers.util.FailureHelper;   import
com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class Bklg234TestCase extends PSAcceptanceTest {

/*
@org.testng.annotations.Test(priority=1) public void Crossfxrate() throws
Exception {


try { String tblDfn = "cross_fx_rate"; String configName = "CrossFxRateDP";
String columnName="pcfx_rate"; int dpvalue=4; decimalprecision configdp = new
decimalprecision();

configdp.configureDecimalPrecision( tblDfn, configName,columnName,dpvalue );
Crossfxratedp ciObj = new Crossfxratedp(); ciObj.Crossfxrate(" Rate"
,dpvalue); } catch (Exception e) { FailureHelper.reportFailure(e); throw e; }


}



@org.testng.annotations.Test(priority=1) public void CarrierInvoice() throws
Exception {


try { String scrName = "CarrierInvoice"; String fieldType = "Rate"; int dp=4;
screendisplaydpconfig screendisplayDp= new screendisplaydpconfig();
screendisplayDp.screendisplaydpconfigdp(scrName,fieldType,dp); carrierinvoice
ciObj = new carrierinvoice();
ciObj.carrierinvoicesearch(fieldType,screendisplayDp.dp);//field type and
field name } catch (Exception e) { FailureHelper.reportFailure(e); throw e; }


}


@org.testng.annotations.Test(priority=5) public void Dispute() throws
Exception {


try { String scrName = "ReconciliationResult"; String fieldType = "Rate"; int
dp=6; screendisplaydpconfig screendisplayDp= new screendisplaydpconfig();
screendisplayDp.screendisplaydpconfigdp(scrName,fieldType,dp); dispute ciObj
= new dispute(); ciObj.disputediscrepancies(" Invoice(Rate)"
,screendisplayDp.dp); } catch (Exception e) { FailureHelper.reportFailure(e);
throw e; }


}



@org.testng.annotations.Test(priority=2) public void Billbreakdown() throws
Exception {


try { String tblDfn = "bbn"; String configName = "BillBreakdownDP"; String
columnName="bbb_txn_amt"; int dpvalue=4;

decimalprecision configdp = new decimalprecision();
configdp.configureDecimalPrecision( tblDfn, configName,columnName,dpvalue );
billbreakdown bbnObj = new billbreakdown(); bbnObj.billbreakdowndp(
" Transaction Amount", dpvalue ); } catch (Exception e) {
FailureHelper.reportFailure(e); throw e; }


}



@org.testng.annotations.Test( priority = 3 ) public void Testbillbreakdown()
throws Exception {

try { String tblDfn = "test_bbn"; String configName = "TestBillBreakdownDP";
String columnName = "tbbb_txn_amt"; int dpvalue = 4; decimalprecision
configdp = new decimalprecision(); configdp.configureDecimalPrecision(
tblDfn, configName, columnName, dpvalue ); testbillbreakdown ciObj = new
testbillbreakdown(); ciObj.testbillbreakdowndp( " Transaction Amount",
dpvalue ); } catch ( Exception e ) { FailureHelper.reportFailure( e ); throw
e; }

}



@org.testng.annotations.Test( priority = 5 ) public void
productBundleDrillDown() throws Exception {

try { ProductBundle ciObj = new ProductBundle(); ciObj.productInstance(
" Rate", 4 ); } catch ( Exception e ) { FailureHelper.reportFailure( e );
throw e; }

}



@org.testng.annotations.Test(priority = 4) public void RateSheet() throws
Exception {

try { String Path; String WorkbookName; String sheetName; String
testCaseName; Path = System.getProperty("user.dir") +
"\\src\\main\\resources\\"; WorkbookName = "TestData.xlsx"; sheetName =
"RatesheetImport_sheet5"; testCaseName = "RatesheetImport"; ratesheetImport
ratesheetobj = new ratesheetImport(); ratesheetobj.rateSheet(Path,
WorkbookName, sheetName, testCaseName);

} catch (Exception e) { FailureHelper.reportFailure(e); throw e; }

}



@org.testng.annotations.Test(priority = 4) public void
RateSheetImportFuturePeriod() throws Exception {

try { String Path; String WorkbookName; String sheetName;

String testCaseName; Path = System.getProperty("user.dir") +
"\\src\\main\\resources\\"; WorkbookName = "TestData.xlsx"; sheetName =
"RateSheetFutureDate_Sheet6"; testCaseName =
"RatesheetImportFuturePeriodUpdate"; ratesheetImportFuturePeriodUpdate
ratesheetimpobj = new ratesheetImportFuturePeriodUpdate();
ratesheetimpobj.rateSheetFuturePeriodUpdate(Path, WorkbookName, sheetName,
testCaseName);

} catch (Exception e) { FailureHelper.reportFailure(e); throw e; }

}



@org.testng.annotations.Test(priority = 4) public void FileUpload() throws
Exception {

try { String filetype = "txt"; String category = "CarrierInvoice"; String
fileCollectionConfig = "VOD - File Collections_VOD"; String fileCollName =
"File Collections_VOD"; uploadFileType filetypeobj = new uploadFileType();
filetypeobj.uploadFileTypeExtension(filetype); fileUploadCategory categoryobj
= new fileUploadCategory(); categoryobj.fileUploadCategoryctg(category);
categoryFileCollectionMapping filecollecobj = new
categoryFileCollectionMapping();
filecollecobj.categoryFileCollection(category, fileCollectionConfig);
fileUpload fileuploadobj = new fileUpload(); fileuploadobj.file(category,
fileCollName);

} catch (Exception e) { FailureHelper.reportFailure(e); throw e; }

}



@org.testng.annotations.Test(priority = 4) public void FileUpload() throws
Exception {

try {


accountTerm filetypeobj = new accountTerm(); filetypeobj.terminateAccount();


} catch (Exception e) { FailureHelper.reportFailure(e); throw e; }

}


@org.testng.annotations.Test(priority = 1)


public void TestSimManagement() throws Exception {

try {

TestSimManagement test = new TestSimManagement();

test.createNewTestSimManagement(); test.EditTestSimManagement();

} catch (Exception e) {

FailureHelper.reportFailure(e);

throw e;

} }



public void ViewRAPFile() throws Exception {

try {

ViewFatalRAPFile view = new ViewFatalRAPFile();

view.viewFatalFile();

} catch (Exception e) {

FailureHelper.reportFailure(e);

throw e;

}

public void ViewSevereRAPFile() throws Exception {

try {

ViewSevereRAPFile view = new ViewSevereRAPFile();

view.viewSevereFile(); view.viewSevereerror();

} catch (Exception e) {

FailureHelper.reportFailure(e);

throw e;

}
*/
	/* } */

String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
String workBookName = "PSSprintFunctional_ExcelData.xlsx"; 
String sheetName = "BKLG234_TAPErroredandExcluded";


@org.testng.annotations.Test( priority = 1, description ="TAP Errored and Excluded Records Search Screen" )
public void TCTapErroredandExcluded() throws Exception
{ 
	try
	{ 
		TAPErroredandExcludedRecordsSearchScreen tap = new TAPErroredandExcludedRecordsSearchScreen( path,workBookName, sheetName, "TAPErroredAndExcludedSearchScreen");
		tap.TAPView(); 
		}
	catch ( Exception e )
	{
	FailureHelper.reportFailure( e );
	throw e;
	} 
	}

/*
* @org.testng.annotations.Test( priority = 1, description =
* "Recording Entity Information" ) public void TCRecordingEntityInformation()
* throws Exception { try { RecordingEntityInformation rec = new
* RecordingEntityInformation( path, workBookName, sheetName,
* "RecEntityInformation"); rec.RecordingEntityInformationCreation(); } catch (
* Exception e ) { FailureHelper.reportFailure( e ); throw e; } } }
*/

}