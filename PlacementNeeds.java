package cares.cwds.salesforce.pom.referralcase.placement;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.By;
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

public class PlacementNeeds {
	private static final Logger logger =LoggerFactory.getLogger(PlacementNeeds.class.getName());
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;
	TestNGCommon testngCommon = new TestNGCommon();
	TestCaseParam testCaseParam = (TestCaseParam) testngCommon.getTestAttribute("testCaseParam");
	String moduleName = ModuleConstants.PLACEMENT;
	String screenName = ScreenConstants.PLACEMENTNEEDS;
	
	public PlacementNeeds(){ }
	
	public PlacementNeeds(WebDriver wDriver)
	{
		initializePage(wDriver);
	}
	
	public void initializePage(WebDriver wDriver) 
	    {
		logger.info(this.getClass().getName());
	    	 driver = wDriver;
	         PageFactory.initElements(driver, this);
	         ReportCommon testStepLogDetails = new ReportCommon(); 
	         testStepLogDetails.logModuleAndScreenDetails(moduleName, screenName);
	         genericLocators = new GenericLocators(wDriver);
	    }
	
	@FindBy(how = How.XPATH, using = "(//*[@apiname='New_Placement_Need']//button)[1]")
	public WebElement placementNeedsNewBtn;
	
	@FindBy(how = How.XPATH, using = "//label[text()='End Date']/../../following-sibling::lightning-input//input")
	public WebElement endDate;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Child Name']/..//input")
	public WebElement childName;
	
	@FindBy(xpath = "(//slot[@name='primaryField']//lightning-formatted-text)[2]")
	WebElement placementPreferenceID;
	
	String childSelect= "//span[normalize-space()='%s']";
	
	public void navigateToPlacementNeedsTab( String scriptIteration, String pomIteration)   {

		PageDetails action = new PageDetails();
		
		action.setPageActionName("Navigate to Placement Needs Tab");
		action.setPageActionDescription("Navigate to Placement Needs Tab");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().scrollDownToPage(driver);
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Placement", testCaseDataSd.get("PLACEMENT_TAB").get(0)), testCaseDataSd.get("PLACEMENT_TAB").get(0),action);
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Placement Preferences & Needs", testCaseDataSd.get("PLACEMENT_PREFERENCES_AND_NEEDS_TAB").get(0)), testCaseDataSd.get("PLACEMENT_PREFERENCES_AND_NEEDS_TAB").get(0),action);
			Webkeywords.instance().scrollUpPageToTheTop(driver);
			Webkeywords.instance().click(driver, placementNeedsNewBtn, testCaseDataSd.get("PLACEMENT_NEEDS_NEW_BTN").get(0),action);
	}
	
	public void enterPlacementNeedsDetails( String scriptIteration, String pomIteration)   {

		PageDetails action = new PageDetails();
		
		action.setPageActionName("Enter Placement Needs Details");
		action.setPageActionDescription("Enter Placement Needs Details");
		
		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);Webkeywords.instance().scrollUpPageToTheTop(driver);
			
		String childNameTD = testCaseDataSd.get("CHILD_NAME").get(0);
		String childNameValue = SalesforceConstants.getConstantValue( testCaseDataSd.get("CHILD_NAME").get(0));
		String permissionTD = testCaseDataSd.get("PERMISSION_TO_SHARE").get(0);
		String placementNeedNarativeTD =util.getRandom(testCaseDataSd.get("PLACEMENT_NEED_NARATIVE").get(0));
		String siblingsPlacedTogetherTD =util.getRandom(testCaseDataSd.get("SIBLINGS_PLACED_TOGETHER").get(0));
		String preferencesInPlacementTD = util.getRandom(testCaseDataSd.get("PREFERENCES_IN_PLACEMENT_PROVIDER").get(0));
		String childHobbiesTD = util.getRandom(testCaseDataSd.get("CHILD_HOBBIES").get(0));
		String childRoutinesTD = util.getRandom(testCaseDataSd.get("CHILD_ROUTINES").get(0));
		String relegionTD = testCaseDataSd.get("RELEGION").get(0);
		String relegiousActivitiesNarattiveTD = util.getRandom(testCaseDataSd.get("RELEGIOUS_ACTIVITIES_NARATTIVE").get(0));
		String attendTribalEventsTD = util.getRandom(testCaseDataSd.get("ATTEND_TRIBAL_EVENTS").get(0));
		String extracurricularActivitiesTD = util.getRandom(testCaseDataSd.get("EXTRA_CURRICULAR_ACTIVITIES").get(0));
		String foodPreferencesTD = util.getRandom(testCaseDataSd.get("CHILD_FOOD_PREFERENCES").get(0));
		String childHairCareTD = util.getRandom(testCaseDataSd.get("CHILD_HAIR_CARE").get(0));
		String fearTD = util.getRandom(testCaseDataSd.get("FEAR_ANXIETIES").get(0));
		String childEmploymentInformationTD = util.getRandom(testCaseDataSd.get("EMPLOYMENT_INFORMATION").get(0));
		String limitedLanguageProficiencyTD = util.getRandom(testCaseDataSd.get("LANGUAGE_PROFICIENCY").get(0));
		String mobilityChallengesTD = util.getRandom(testCaseDataSd.get("MOBILITY_CHALLENGES").get(0));
		String independentLivingSkillsTD = util.getRandom(testCaseDataSd.get("INDEPENDENT_LIVING_SKILLS").get(0));
		String childNeedTransportationAccessTD = util.getRandom(testCaseDataSd.get("TRANSPORTATION_ACCESS").get(0));
		String developmentNeedsTD = util.getRandom(testCaseDataSd.get("DEVELOPMENT_NEEDS").get(0));
		String otherNeedsTD = util.getRandom(testCaseDataSd.get("OTHER_NEEDS").get(0));
		String safetyConcernsTD = util.getRandom(testCaseDataSd.get("SAFETY_CONCERNS").get(0));
		String currentBehaviourConcernsTD = util.getRandom(testCaseDataSd.get("CURRENT_BEHAVIOUR_CONCERNS").get(0));
		String pastBehaviourConcernsTD = util.getRandom(testCaseDataSd.get("PAST_BEHAVIOUR_CONCERNS").get(0));
		String endDateTD = testCaseDataSd.get("END_DATE").get(0);
		String saveBtnTD = testCaseDataSd.get("SAVE_BTN").get(0);

			
		Webkeywords.instance().click(driver, childName,childNameTD,action);			
		Webkeywords.instance().click(driver,driver.findElement(By.xpath(format(childSelect,childNameValue))),childNameValue,action);
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Permission to Share Externally Granted",permissionTD),permissionTD, action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Placement Need Narrative",placementNeedNarativeTD),placementNeedNarativeTD, action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Do Siblings need to be placed together?",siblingsPlacedTogetherTD),siblingsPlacedTogetherTD, action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Preferences in a Placement Provider",preferencesInPlacementTD),preferencesInPlacementTD, action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Child's Hobbies",childHobbiesTD),childHobbiesTD, action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Child's Routines",childRoutinesTD),childRoutinesTD, action);
		Webkeywords.instance().selectValueInputDropdown(driver,relegionTD,"Religion",action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Religious Activities Narrative",relegiousActivitiesNarattiveTD),relegiousActivitiesNarattiveTD, action);
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Attend Tribal events?",attendTribalEventsTD),attendTribalEventsTD, action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Extracurricular or Community Activities",extracurricularActivitiesTD),extracurricularActivitiesTD, action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Child Food Preferences",foodPreferencesTD),foodPreferencesTD, action);
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Child Hair Care/Hair Styling Needs",childHairCareTD),childHairCareTD, action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Fears or Anxieties",fearTD),fearTD, action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Child Employment Information",childEmploymentInformationTD),childEmploymentInformationTD, action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Limited Language Proficiency",limitedLanguageProficiencyTD),limitedLanguageProficiencyTD, action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Mobility Challenges",mobilityChallengesTD),mobilityChallengesTD, action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Independent Living Skills Training Needs",independentLivingSkillsTD),independentLivingSkillsTD, action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Child Needs Transportation Access",childNeedTransportationAccessTD),childNeedTransportationAccessTD, action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Development Needs",developmentNeedsTD),developmentNeedsTD, action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Other Needs",otherNeedsTD),otherNeedsTD, action);
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Safety Concerns",safetyConcernsTD),safetyConcernsTD, action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Current Behavior Concerns",currentBehaviourConcernsTD),currentBehaviourConcernsTD, action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Past Behavior Concerns",pastBehaviourConcernsTD),pastBehaviourConcernsTD, action);
		Webkeywords.instance().setDateText(driver, endDate, CommonOperations.getDate("M/d/yyyy", endDateTD), action);		
		Webkeywords.instance().click(driver, genericLocators.button(driver, "Save",saveBtnTD), saveBtnTD, action);
		Webkeywords.instance().waitElementToBeVisible(driver, placementPreferenceID);
		SalesforceConstants.setConstantValue("FOLIO_PLACEMENT_NEEDID", placementPreferenceID.getText());	
	}
	
	public void editPlacementNeedsDetails( String scriptIteration, String pomIteration)   {

		PageDetails action = new PageDetails();
		
		action.setPageActionName("Edit Placement Needs Details");
		action.setPageActionDescription("Edit Placement Needs Details");
		
		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);Webkeywords.instance().scrollUpPageToTheTop(driver);
			
		String childNameTD = testCaseDataSd.get("CHILD_NAME").get(0);
		String childNameValue = SalesforceConstants.getConstantValue( testCaseDataSd.get("CHILD_NAME").get(0));
		String permissionTD = testCaseDataSd.get("PERMISSION_TO_SHARE").get(0);
		String placementNeedNarativeTD =util.getRandom(testCaseDataSd.get("PLACEMENT_NEED_NARATIVE").get(0));
		String saveBtnTD = testCaseDataSd.get("SAVE_BTN").get(0);
			
		Webkeywords.instance().click(driver, childName,childNameTD,action);			
		Webkeywords.instance().click(driver,driver.findElement(By.xpath(format(childSelect,childNameValue))),childNameValue,action);
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Permission to Share Externally Granted",permissionTD),permissionTD, action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Placement Need Narrative",placementNeedNarativeTD),placementNeedNarativeTD, action);
		Webkeywords.instance().click(driver, genericLocators.button(driver, "Save",saveBtnTD), saveBtnTD, action);
		Webkeywords.instance().waitElementToBeVisible(driver, placementPreferenceID);
		SalesforceConstants.setConstantValue("FOLIO_PLACEMENT_NEEDID", placementPreferenceID.getText());	
	}
}