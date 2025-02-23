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
import cares.cwds.salesforce.utilities.web.Webkeywords;

public class TribePlacementPreference {
	
	private static final Logger logger =LoggerFactory.getLogger(TribePlacementPreference.class.getName());

	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;
	TestNGCommon testngCommon = new TestNGCommon();
	TestCaseParam testCaseParam = (TestCaseParam) testngCommon.getTestAttribute("testCaseParam");
	
	String moduleName = ModuleConstants.PLACEMENT;
	String screenName = ScreenConstants.TRIBALPLACEMENTPREFERENCE;
	
	public TribePlacementPreference(){ }
	
	public TribePlacementPreference(WebDriver wDriver)
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
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Date Preferences were captured']/../../following-sibling::lightning-input//input)[1]")
	public WebElement datePreferenceseWereCaptured;
	
	@FindBy(xpath = "//*[@title='Placement Preference ID']/..//lightning-formatted-text")
	WebElement tribalPlacementPreferenceID;
	
	public void navigateToPlacementPreterence( String scriptIteration, String pomIteration)   {

		PageDetails action = new PageDetails();
		
		action.setPageActionName("Navigate to Placement Perference & Need Tab");
		action.setPageActionDescription("Navigate to Placement Perference & Need Tab");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().click(driver, genericLocators.link(driver, "Placement Preferences & Needs", testCaseDataSd.get("PLACEMENT_PREFERENCE_AND_NEED").get(0)), testCaseDataSd.get("PLACEMENT_PREFERENCE_AND_NEED").get(0),action);
	}
	
	public void enterTribalPlacementPreference( String scriptIteration, String pomIteration)   {

		PageDetails action = new PageDetails();
		
		action.setPageActionName("Enter TribalnPlacementPreference Details");
		action.setPageActionDescription("Enter TribalPlacementPreference Details");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().scrollUpPageToTheTop(driver);
			Webkeywords.instance().pause();
			Webkeywords.instance().click(driver, genericLocators.button(driver, "New", testCaseDataSd.get("NEW_BTN").get(0)), testCaseDataSd.get("NEW_BTN").get(0),action);
			Webkeywords.instance().pause();
			Webkeywords.instance().setDateText(driver, datePreferenceseWereCaptured, CommonOperations.getDate("M/d/yyyy", testCaseDataSd.get("CALL_DATE_AND_TIME").get(0)),  action);		
			
			Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("NAME_OF_TRIBE").get(0),"Name of Tribe",action);
			Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("TRIBE_PREFERENCE_MATCH").get(0),"Tribe Preference Match 25 USC § 1915(b)?",action);
			Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("TRIBE_PLACEMENT_PREFERENCE1").get(0),"Tribe Placement Preference 1",action);
			Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("TRIBE_PLACEMENT_PREFERENCE2").get(0),"Tribe Placement Preference 2",action);
			Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("TRIBE_PLACEMENT_PREFERENCE3").get(0),"Tribe Placement Preference 3",action);
			Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("TRIBE_PLACEMENT_PREFERENCE4").get(0),"Tribe Placement Preference 4",action);
					
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Name of Tribal Representative",testCaseDataSd.get("NAME_OF_TRIBAL_REPRESENTATIVE").get(0)),util.getRandom(testCaseDataSd.get("NAME_OF_TRIBAL_REPRESENTATIVE").get(0)), action);
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Tribal Preference Narrative",testCaseDataSd.get("TRIBAL_PREFERENCE_NARRATIVE").get(0)),util.getRandom(testCaseDataSd.get("TRIBAL_PREFERENCE_NARRATIVE").get(0)), action);
			Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("PRIORITY").get(0),"Priority",action);
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Additional Information",testCaseDataSd.get("ADDITIONAL_iNFORMATION").get(0)),util.getRandom(testCaseDataSd.get("ADDITIONAL_iNFORMATION").get(0)), action);
			
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Save",testCaseDataSd.get("SAVE_BTN").get(0)),testCaseDataSd.get("SAVE_BTN").get(0),action);
			
			Webkeywords.instance().waitElementToBeVisible(driver, tribalPlacementPreferenceID);
			SalesforceConstants.setConstantValue("Folio_TribalPlacementPreferenceID" + pomIteration, tribalPlacementPreferenceID.getText());	
	}

}
