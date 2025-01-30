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

public class CourtsAuditHistory {

	private static final Logger logger = LoggerFactory.getLogger(CourtsAuditHistory.class.getName());
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;
	TestNGCommon testngCommon = new TestNGCommon();
	TestCaseParam testCaseParam = (TestCaseParam) testngCommon.getTestAttribute("testCaseParam");
	String moduleName = ModuleConstants.COURTS;
	String screenName = ScreenConstants.AUDITHISTORY;
	
	private static final String AUDITHISTORY = "Audit History";
	
	public CourtsAuditHistory(){ }
	
	public CourtsAuditHistory(WebDriver wDriver) 
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
	

	
	

	public void navigateToCourtsAuditHistory(String scriptIteration, String pomIteration)  {
		PageDetails action = new PageDetails();
		action.setPageActionName("Navigate to Courts Audit History");
		action.setPageActionDescription("Navigate to Courts Audit History");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),
					TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);			
		    Webkeywords.instance().scrollDownToPage(driver);
		   
		    String auditHistoryTabTD = testCaseDataSd.get("AUDIT_HISTORY_TAB").get(0);
		    Webkeywords.instance().waitElementToBeVisible(driver,  genericLocators.link(driver, AUDITHISTORY,auditHistoryTabTD));
		    Webkeywords.instance().click(driver, genericLocators.link(driver, AUDITHISTORY,auditHistoryTabTD), auditHistoryTabTD, action);
		    Webkeywords.instance().pause();
		    
		    ScreenshotCommon.captureFullPageScreenShot(driver, moduleName+"-"+screenName);
	
	}
	
	public void navigateToCourtWorkItemHistoryTab(String scriptIteration, String pomIteration)  {
		PageDetails action = new PageDetails();
		action.setPageActionName("Navigate to Court Work Item History");
		action.setPageActionDescription("Navigate to Court Work Item History");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().click(driver, genericLocators.link(driver, "Court Work Item History",testCaseDataSd.get("Court_Work_Item_History_TAB").get(0)), testCaseDataSd.get("Court_Work_Item_History_TAB").get(0), action);
	}
	

	public void verifyHeaderColumnsInTable(String tableName, String scriptIteration,
			String pomIteration) {
		
		PageDetails action = new PageDetails();

		action.setPageActionName("Verify header columns in Table");
		action.setPageActionDescription("Verify header columns in Table");

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

