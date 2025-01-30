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

public class PetitionAllegations {
	
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;
	LocalDateTime startTime= LocalDateTime.now();
	TestNGCommon testngCommon = new TestNGCommon();
	TestCaseParam testCaseParam = (TestCaseParam) testngCommon.getTestAttribute("testCaseParam");

	String moduleName = ModuleConstants.COURTS;
	String screenName = ScreenConstants.PETITIONALLEGATION;
	
public PetitionAllegations(){ }
	
	public PetitionAllegations(WebDriver wDriver)
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
	
	
	
	
	public void navigateToPetitionAllegation(String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("Navigate to Petition Allegations Tab");
		action.setPageActionDescription("Navigate to Petition Allegations Tab");
	
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().click(driver, genericLocators.link(driver, "Petition Allegations", testCaseDataSd.get("PETITION_ALLEGATIONS_TAB").get(0)), testCaseDataSd.get("PETITION_ALLEGATIONS_TAB").get(0),action);
			Webkeywords.instance().pause();
	}
			
	
	public void enterPetitionAllegationsDetails(String scriptIteration, String pomIteration){
		
		PageDetails action = new PageDetails();
		action.setPageActionName("Enter Petition Allegations Details");
		action.setPageActionDescription("Enter Petition Allegations Details");
		
		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
		Webkeywords.instance().scrollUpPageToTheTop(driver);
		
		Webkeywords.instance().click(driver, genericLocators.button(driver, "New", testCaseDataSd.get("NEW_BTN").get(0)), testCaseDataSd.get("NEW_BTN").get(0),action);
		Webkeywords.instance().pause();
		
		Webkeywords.instance().waitElementToBeVisible(driver, genericLocators.dropdown(driver,"Allegation Type(s)",testCaseDataSd.get("ALLEGATION_TYPES_VERIFY").get(0)));
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("ALLEGATION_TYPES").get(0),"ALLEGATION_TYPES",action);
		
		
		Webkeywords.instance().click(driver, genericLocators.button(driver, "Save",testCaseDataSd.get("SAVE_BTN").get(0)),testCaseDataSd.get("SAVE_BTN").get(0), action);
		Webkeywords.instance().pause();
	}

}


