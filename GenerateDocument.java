package cares.cwds.salesforce.pom.courts;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import cares.cwds.salesforce.constants.ModuleConstants;
import cares.cwds.salesforce.constants.ScreenConstants;
import cares.cwds.salesforce.utilities.common.TestRunSettings;
import cares.cwds.salesforce.utilities.common.Util;
import cares.cwds.salesforce.utilities.reports.common.ReportCommon;
import cares.cwds.salesforce.utilities.reports.extentmodel.PageDetails;
import cares.cwds.salesforce.utilities.reports.model.TestCaseParam;
import cares.cwds.salesforce.utilities.testng.TestNGCommon;
import cares.cwds.salesforce.utilities.web.GenericLocators;

import cares.cwds.salesforce.utilities.web.Webkeywords;

public class GenerateDocument {
	
	
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;
	LocalDateTime startTime= LocalDateTime.now();
	TestNGCommon testngCommon = new TestNGCommon();
	TestCaseParam testCaseParam = (TestCaseParam) testngCommon.getTestAttribute("testCaseParam");

	String moduleName = ModuleConstants.COURTS;
	String screenName = ScreenConstants.GENERATEDOCUMENT;
	
	
public GenerateDocument(){ }
	
	public GenerateDocument(WebDriver wDriver)
	{
		initializePage(wDriver);
	}

	public void initializePage(WebDriver wDriver) 
    {
    	 driver = wDriver;
         PageFactory.initElements(driver, this);
         ReportCommon testStepLogDetails = new ReportCommon(); 
         testStepLogDetails.logModuleAndScreenDetails( moduleName, screenName);
         genericLocators = new GenericLocators(wDriver);
    }
	
	
	
	
public void enterGenerateDocumentDetails(String scriptIteration, String pomIteration){
		
		PageDetails action = new PageDetails();
		action.setPageActionName("Enter Generate Document Details");
		action.setPageActionDescription("Enter Generate Document Details");
		
		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
		Webkeywords.instance().scrollUpPageToTheTop(driver);
		
		
		Webkeywords.instance().click(driver, genericLocators.button(driver, "Generate Document", testCaseDataSd.get("GENERATE_DOCUMENT_BTN").get(0)), testCaseDataSd.get("GENERATE_DOCUMENT_BTN").get(0),action);
		Webkeywords.instance().pause();
		
		Webkeywords.instance().waitElementToBeVisible(driver, genericLocators.dropdown(driver,"Document Type",testCaseDataSd.get("DOCUMENT_TYPE_VERIFY").get(0)));
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("DOCUMENT_TYPE").get(0),"DOCUMENT_TYPE",action);
		
		Webkeywords.instance().click(driver, genericLocators.button(driver, "Generate",testCaseDataSd.get("GENERATE_BTN").get(0)),testCaseDataSd.get("GENERATE_BTN").get(0), action);
		Webkeywords.instance().pause();
		

}

}
