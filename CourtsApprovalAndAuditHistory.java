package cares.cwds.salesforce.pom.courts;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cares.cwds.salesforce.constants.ModuleConstants;
import cares.cwds.salesforce.constants.ScreenConstants;
import cares.cwds.salesforce.utilities.common.TestRunSettings;
import cares.cwds.salesforce.utilities.common.Util;
import cares.cwds.salesforce.utilities.reports.common.ReportCommon;
import cares.cwds.salesforce.utilities.reports.common.ScreenshotCommon;
import cares.cwds.salesforce.utilities.reports.extentmodel.PageDetails;
import cares.cwds.salesforce.utilities.reports.model.TestCaseParam;
import cares.cwds.salesforce.utilities.testng.TestNGCommon;
import cares.cwds.salesforce.utilities.web.GenericLocators;
import cares.cwds.salesforce.utilities.web.Webkeywords;

public class CourtsApprovalAndAuditHistory {
	

	private static final Logger logger = LoggerFactory.getLogger(CourtsApprovalAndAuditHistory.class.getName());
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;
	TestNGCommon testngCommon = new TestNGCommon();
	TestCaseParam testCaseParam = (TestCaseParam) testngCommon.getTestAttribute("testCaseParam");
	String moduleName = ModuleConstants.COURTS;
	String screenName = ScreenConstants.COURTSAPPROVALAUDITHISTORY;
	
	private static final String APPROVALAUDITHISTORY = "Approval/Audit History";
	
	CourtsAuditHistory auditHistory=new CourtsAuditHistory();
	
	public CourtsApprovalAndAuditHistory(){ }
	
	public CourtsApprovalAndAuditHistory(WebDriver wDriver) 
	{
		initializePage(wDriver);
	}
	
	public void initializePage(WebDriver wDriver) 
	    {
	    	 driver = wDriver;
	         PageFactory.initElements(driver, this);
	         ReportCommon testStepLogDetails = new ReportCommon(); 
	         testStepLogDetails.logModuleAndScreenDetails(moduleName, screenName);
	         genericLocators = new GenericLocators(wDriver);
	        		          
	    }
	
	String columnHeader = "//table[@aria-label='%s']/thead/tr/th[@*='%s']";
	

	
	

	public void navigateToCourtsApprovalAuditHistory(String scriptIteration, String pomIteration)  {
		PageDetails action = new PageDetails();
		action.setPageActionName("Navigate to Courts Approval Audit History");
		action.setPageActionDescription("Navigate to Courts Approval Audit History");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),
					TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);			
		    Webkeywords.instance().scrollDownToPage(driver);
		   
		    String auditHistoryTabTD = testCaseDataSd.get("APPROVAL_AUDIT_HISTORY_TAB").get(0);
		    Webkeywords.instance().waitElementToBeVisible(driver,  genericLocators.link(driver, APPROVALAUDITHISTORY,auditHistoryTabTD));
		    Webkeywords.instance().click(driver, genericLocators.link(driver, APPROVALAUDITHISTORY,auditHistoryTabTD), auditHistoryTabTD, action);
		    Webkeywords.instance().pause();
		    
		    ScreenshotCommon.captureFullPageScreenShot(driver, moduleName+"-"+screenName);
	
	}
	
	
	//Approval History Tab
	public void navigateToApprovalHistoryTab(String scriptIteration, String pomIteration)  {
		PageDetails action = new PageDetails();
		action.setPageActionName("Navigate to Court Approval History");
		action.setPageActionDescription("Navigate to Court Approval History");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().click(driver, genericLocators.link(driver, "Approval History",testCaseDataSd.get("Approval_History_TAB").get(0)), testCaseDataSd.get("Approval_History_TAB").get(0), action);
	}
	

	public void verifyHeaderColumnsInTableApprovalHistory(String tableName, String scriptIteration,
			String pomIteration) {
		
		PageDetails action = new PageDetails();

		action.setPageActionName("Verify header columns in Approval History Table");
		action.setPageActionDescription("Verify header columns in Approval History Table");

		Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,
				testCaseParam.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),
				TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),
				scriptIteration, pomIteration);
		

		Webkeywords.instance()
				.verifyElementDisplayed(
						driver, getElementBasedOnFlag(testCaseDataSd.get("STEP_NAME_VERIFY").get(0), columnHeader,
								tableName, "Step Name"),
						testCaseDataSd.get("STEP_NAME_VERIFY").get(0), action);

		Webkeywords.instance().verifyElementDisplayed(driver,
				getElementBasedOnFlag(testCaseDataSd.get("DATE_VERIFY").get(0), columnHeader, tableName, "Date"),
				testCaseDataSd.get("DATE_VERIFY").get(0), action);
		
	
		
		Webkeywords.instance().verifyElementDisplayed(driver,
				getElementBasedOnFlag(testCaseDataSd.get("STATUS_VERIFY").get(0), columnHeader, tableName, "Status"),
				testCaseDataSd.get("STATUS_VERIFY").get(0), action);
		
		Webkeywords.instance()
				.verifyElementDisplayed(
						driver, getElementBasedOnFlag(testCaseDataSd.get("ASSIGNED_TO_VERIFY").get(0), columnHeader,
								tableName, "Assigned To"),
						testCaseDataSd.get("ASSIGNED_TO_VERIFY").get(0), action);
		
		Webkeywords.instance()
		.verifyElementDisplayed(
				driver, getElementBasedOnFlag(testCaseDataSd.get("ACTUAL_APPROVER_VERIFY").get(0), columnHeader,
						tableName, "Actual Approver"),
				testCaseDataSd.get("ACTUAL_APPROVER_VERIFY").get(0), action);
		
		Webkeywords.instance()
		.verifyElementDisplayed(
				driver, getElementBasedOnFlag(testCaseDataSd.get("COMMENTS_VERIFY").get(0), columnHeader,
						tableName, "Comments"),
				testCaseDataSd.get("COMMENTS_VERIFY").get(0), action);
	}
	 
		
	// Court Work Item History 
		public void navigateToCourtWorkItemHistoryTab(String scriptIteration, String pomIteration)  {
			PageDetails action = new PageDetails();
			action.setPageActionName("Navigate to Court Work Item History");
			action.setPageActionDescription("Navigate to Court Work Item History");
			
				Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
				Webkeywords.instance().click(driver, genericLocators.link(driver, "Court Work Item History",testCaseDataSd.get("Court_Work_Item_History_TAB").get(0)), testCaseDataSd.get("Court_Work_Item_History_TAB").get(0), action);
		}
		

		public void verifyHeaderColumnsInTableCourtWorkItemHistory(String tableName, String scriptIteration,
				String pomIteration) {
			
			PageDetails action = new PageDetails();

			action.setPageActionName("Verify header columns in Court Work Item History Table");
			action.setPageActionDescription("Verify header columns in Court Work Item History Table");

			Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,
					testCaseParam.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),
					TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),
					scriptIteration, pomIteration);

			Webkeywords.instance().verifyElementDisplayed(driver,
					getElementBasedOnFlag(testCaseDataSd.get("DATE_VERIFY").get(0), columnHeader, tableName, "Date"),
					testCaseDataSd.get("DATE_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver,
					getElementBasedOnFlag(testCaseDataSd.get("FIELD_VERIFY").get(0), columnHeader, tableName, "Field"),
					testCaseDataSd.get("FIELD_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver,
					getElementBasedOnFlag(testCaseDataSd.get("USER_VERIFY").get(0), columnHeader, tableName, "User"),
					testCaseDataSd.get("USER_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(
					driver, getElementBasedOnFlag(testCaseDataSd.get("ORIGINAL_VALUE_VERIFY").get(0), columnHeader,
							tableName, "Original Value"),
					testCaseDataSd.get("ORIGINAL_VALUE_VERIFY").get(0), action);
			Webkeywords.instance()
					.verifyElementDisplayed(
							driver, getElementBasedOnFlag(testCaseDataSd.get("NEW_VALUE_VERIFY").get(0), columnHeader,
									tableName, "New Value"),
							testCaseDataSd.get("NEW_VALUE_VERIFY").get(0), action);
			
		 }
		
		public WebElement getElementBasedOnFlag(String flag, String columnHeaderXpath, String tableName,
				String columnName) {
			if (!(flag.equalsIgnoreCase("n/a"))) {
				return driver.findElement(By.xpath(format(columnHeaderXpath, tableName, columnName)));
			} else
				return null;
		}
			
			
}
		
		


