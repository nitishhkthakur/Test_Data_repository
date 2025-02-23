package cares.cwds.salesforce.pom.referralcase.placement;

import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cares.cwds.salesforce.constants.ModuleConstants;
import cares.cwds.salesforce.constants.SalesforceConstants;
import cares.cwds.salesforce.constants.ScreenConstants;
import cares.cwds.salesforce.utilities.common.TestRunSettings;
import cares.cwds.salesforce.utilities.common.Util;
import cares.cwds.salesforce.utilities.reports.common.ReportCommon;
import cares.cwds.salesforce.utilities.reports.extentmodel.PageDetails;
import cares.cwds.salesforce.utilities.reports.model.TestCaseParam;
import cares.cwds.salesforce.utilities.testng.TestNGCommon;
import cares.cwds.salesforce.utilities.web.CommonOperations;
import cares.cwds.salesforce.utilities.web.GenericLocators;
import cares.cwds.salesforce.utilities.web.SalesforceCommon;
import cares.cwds.salesforce.utilities.web.Webkeywords;

public class ParentGuardianPlacementPreferences {
	private static final Logger logger =LoggerFactory.getLogger(ParentGuardianPlacementPreferences.class.getName());

	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;
	TestNGCommon testngCommon = new TestNGCommon();
	TestCaseParam testCaseParam = (TestCaseParam) testngCommon.getTestAttribute("testCaseParam");
	
	
	String moduleName = ModuleConstants.PLACEMENT;
	String screenName = ScreenConstants.PARENTGUARDAIANPLACEMENTPREFERENCE;
	
	public ParentGuardianPlacementPreferences(){ }
	
	public ParentGuardianPlacementPreferences(WebDriver wDriver)
	{
		initializePage(wDriver);
	}
	
	public void initializePage(WebDriver wDriver) 
	    {
		logger.info(this.getClass().getName());
	    	 driver = wDriver;
	         PageFactory.initElements(driver, this);
	         ReportCommon testStepLogDetails = new ReportCommon(); 
	         testStepLogDetails.logModuleAndScreenDetails( moduleName, screenName);
	         genericLocators = new GenericLocators(wDriver);
	    }
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Date Preference Identified']/../../following-sibling::lightning-input//input)[1]")
	public WebElement datePreferenceIdentified;
	
	@FindBy(xpath = "//*[@title='Placement Preference ID']/..//lightning-formatted-text")
	WebElement parentGuardianPlacementPreferenceID;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Parent/Guardian Name']/..//input")
	public WebElement parentGuardianName;
	
	@FindBy(xpath = "//span[contains(text(),'Parent/Guardian Placement Preferences')]/ancestor::div[contains(@class,'slds-page-header')]//button[contains(text(),'New')]")
	WebElement parentGuardianNewButton;
	
	public void navigateToPlacementPreterence( String scriptIteration, String pomIteration)   {

		PageDetails action = new PageDetails();
		
		action.setPageActionName("Navigate to Placement Perference & Need Tab");
		action.setPageActionDescription("Navigate to Placement Perference & Need Tab");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().click(driver, genericLocators.link(driver, "Placement Preferences & Needs", testCaseDataSd.get("PLACEMENT_PREFERENCE_AND_NEED").get(0)), testCaseDataSd.get("PLACEMENT_PREFERENCE_AND_NEED").get(0),action);
	}
	
	public void enterParentGuardianPlacementPreference( String scriptIteration, String pomIteration)   {

		PageDetails action = new PageDetails();
		
		action.setPageActionName("Enter ParentGuardianPlacementPreference Details");
		action.setPageActionDescription("Enter ParentGuardianPlacementPreference Details");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			
			String parentGuardianNameTD = testCaseDataSd.get("PARENT_GUARDAIN_NAME_TD").get(0);
			
			Webkeywords.instance().scrollUpPageToTheTop(driver);
			Webkeywords.instance().pause();
			Webkeywords.instance().click(driver, genericLocators.button(driver, "New", testCaseDataSd.get("NEW_BTN").get(0)), testCaseDataSd.get("NEW_BTN").get(0),action);
			Webkeywords.instance().click(driver, parentGuardianNewButton, testCaseDataSd.get("PARENT_GUARDIAN_NEW_BTN").get(0),action);

			Webkeywords.instance().pause();
			Webkeywords.instance().setDateText(driver, datePreferenceIdentified, CommonOperations.getDate("M/d/yyyy", testCaseDataSd.get("CALL_DATE_AND_TIME").get(0)),  action);		
			
			Webkeywords.instance().click(driver, parentGuardianName,parentGuardianNameTD,action);			
			Webkeywords.instance().click(driver, genericLocators.link(driver, SalesforceConstants.getConstantValue(parentGuardianNameTD),parentGuardianNameTD),parentGuardianNameTD,action);
			
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Non Person Placement Preference",testCaseDataSd.get("NON_PERSON_PLACEMENT_REFERENCE").get(0)),util.getRandom(testCaseDataSd.get("NON_PERSON_PLACEMENT_REFERENCE").get(0)), action);
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Person Placement Preference",testCaseDataSd.get("PERSON_PLACEMENT_REFERENCE").get(0)),util.getRandom(testCaseDataSd.get("PERSON_PLACEMENT_REFERENCE").get(0)), action);
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Additional Information",testCaseDataSd.get("ADDITIONAL_iNFORMATION").get(0)),util.getRandom(testCaseDataSd.get("ADDITIONAL_iNFORMATION").get(0)), action);
			Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("PRIORITY").get(0),"Priority",action);
			
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Save",testCaseDataSd.get("SAVE_BTN").get(0)),testCaseDataSd.get("SAVE_BTN").get(0),action);
			
			Webkeywords.instance().waitElementToBeVisible(driver, parentGuardianPlacementPreferenceID);
			SalesforceConstants.setConstantValue("Folio_ParentGuardianPlacementPreferenceID" + pomIteration, parentGuardianPlacementPreferenceID.getText());	
	}
	
	public void editParentGuardianPlacementPreference( String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("Edit ParentGuardianPlacementPreference Details");
		action.setPageActionDescription("Edit ParentGuardianPlacementPreference Details");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			
			
		String saveBtnTD =testCaseDataSd.get("SAVE_BTN").get(0);

		
		Webkeywords.instance().click(driver, genericLocators.button(driver, "Save",saveBtnTD), saveBtnTD, action);
		SalesforceCommon.verifyToastMessage(driver,"This cannot be edited", action);
		
	}

}
