package cares.cwds.salesforce.pom.courts;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

public class GeneratePetition {
	
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;
	LocalDateTime startTime= LocalDateTime.now();
	TestNGCommon testngCommon = new TestNGCommon();
	TestCaseParam testCaseParam = (TestCaseParam) testngCommon.getTestAttribute("testCaseParam");

	String moduleName = ModuleConstants.COURTS;
	String screenName = ScreenConstants.GENERATEPETITION;
	
	
public GeneratePetition(){ }
	
	public GeneratePetition(WebDriver wDriver)
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
	
	@FindBy(xpath = "(//button[.//span[contains(text(),'Show more actions')]])[2]")
	WebElement generatePetitionOption;
	
public void enterGeneratePetitionDetails(String scriptIteration, String pomIteration){
		
		PageDetails action = new PageDetails();
		action.setPageActionName("Enter Generate Petition Details");
		action.setPageActionDescription("Enter Generate Petition Details");
		
      	Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
		Webkeywords.instance().scrollUpPageToTheTop(driver);
		
		Webkeywords.instance().click(driver, generatePetitionOption, testCaseDataSd.get("OPTION_PETITION").get(0), action);
		Webkeywords.instance().click(driver, genericLocators.button(driver, "Generate Petition", testCaseDataSd.get("GENERATE_PETITION_BTN").get(0)), testCaseDataSd.get("GENERATE_PETITION_BTN").get(0),action);
		Webkeywords.instance().pause();
		
		Webkeywords.instance().waitElementToBeVisible(driver, genericLocators.dropdown(driver,"Document Type",testCaseDataSd.get("PETITION_DOCUMENT_TYPE_VERIFY").get(0)));
        Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("PETITION_DOCUMENT_TYPE").get(0),"PETITION_DOCUMENT_TYPE",action);
		
        Webkeywords.instance().click(driver, genericLocators.button(driver, "Generate",testCaseDataSd.get("GENERATE_BTN").get(0)),testCaseDataSd.get("GENERATE_BTN").get(0), action);
		Webkeywords.instance().pause();
		

}

}
