package cares.cwds.salesforce.pom.referralcase.placement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import static java.lang.String.format;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

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

public class Removal {
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	GenericLocators genericLocators = null;
	Util util = new Util();
	TestNGCommon testngCommon = new TestNGCommon();
	TestCaseParam testCaseParam = (TestCaseParam) testngCommon.getTestAttribute("testCaseParam");
	
	
	String moduleName = ModuleConstants.PLACEMENT;
	String screenName = ScreenConstants.REMOVAL;
	LocalDateTime startTime= LocalDateTime.now();
	
	private static final String REMOVALTAB = "Removal";
	private static final String REMOVAL_ID = "Removal ID";
	private static final String EPISODE_NUMBER= "Episode Number";
	private static final String REMOVAL_DATE_TIME= "Removal Date/Time";
	private static final String DOCUMENTATION_STATUS= "Documentation Status";
	private static final String PRIMARY_REASON= "Primary Reason for Removal";
	
	public Removal(){ }
	
	public Removal(WebDriver wDriver)
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
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Removal Date & Time']/../../following-sibling::lightning-input//input)[1]")
	public WebElement removalDateAndTime;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Removed From (Person 1)']/..//input")
	public WebElement removedFrom;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@aria-label, 'County Agency/Staff Worker Name')]")
	public WebElement countyStaffSelect;
	

	@FindBy(how = How.XPATH, using = "//span[text()='View All']//parent::a")
	public WebElement viewAllButton;
	
	@FindBy(how = How.XPATH, using = "//h1[text()='Removal']")
	public WebElement removalHeader;
	
	@FindBy(how = How.XPATH, using = "//p[text()='Removal ID']//parent::div//lightning-formatted-text")
	public WebElement removalID;
	
	String recordIdLink = "//span[text()='%s']//ancestor::a";
	
	String columnHeader = "//table[@aria-label='%s']/thead/tr/th[@*='%s']";
	
	
	public void navigateToRemovalTab(String scriptIteration,String pomIteration) {
		PageDetails action = new PageDetails();

		action.setPageActionName("Navigate To Removal Tab");
		action.setPageActionDescription("Navigate To Removal Tab");

		Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,testCaseParam.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),
				TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),
				scriptIteration, pomIteration);
		Webkeywords.instance().scrollUpPageToTheTop(driver);
		String removalTD =  testCaseDataSd.get("REMOVAL_TAB").get(0);
		String placementTD =  testCaseDataSd.get("PLACEMENT_TAB").get(0);
		Webkeywords.instance().click(driver, genericLocators.link(driver, "Placement", placementTD),placementTD, action);
		Webkeywords.instance().click(driver, genericLocators.link(driver, REMOVALTAB, removalTD),removalTD,  action);
		
	}

	public void addRemovalDetails(String scriptIteration,String pomIteration) {
		PageDetails action = new PageDetails();

		action.setPageActionName("Add Removal Details");
		action.setPageActionDescription("Add Removal Details");

		Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,testCaseParam.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),
				TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),
				scriptIteration, pomIteration);
		
		String removedFromTD = testCaseDataSd.get("REMOVED_FROM").get(0);
		String removedByTD = testCaseDataSd.get("REMOVED_BY").get(0);
		
		Webkeywords.instance().scrollUpPageToTheTop(driver);
		Webkeywords.instance().click(driver, genericLocators.button(driver, "New", testCaseDataSd.get("NEW_BTN").get(0)), testCaseDataSd.get("NEW_BTN").get(0),action);
		Webkeywords.instance().pause();
		Webkeywords.instance().setDateText(driver, removalDateAndTime, CommonOperations.getDate("M/d/yyyy", testCaseDataSd.get("REMOVAL_DATE_AND_TIME").get(0)),action);		
		Webkeywords.instance().scrollIntoViewElement(driver, removalDateAndTime);
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("PRIMARY_REASON_FOR_REMOVAL").get(0),PRIMARY_REASON,action);
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("ENVIRONMENT_AT_REMOVAL").get(0),"Environment at Removal",action);
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("PERSON_PRESENT_AT_THE_HOME").get(0),"Persons Present at the Home",action);
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("SECONDARY_REASON_FOR_REMOVAL").get(0),"Secondary Reason for Removal",action);
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("PERSON_WHO_LIVED_WITH_THE_CHILD_AT_ANY_POINT").get(0),"Person who lived with the child at any point in the last 6 months",action);	
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("DOCUMENTATION_STATUS").get(0),DOCUMENTATION_STATUS,action);
		Webkeywords.instance().click(driver, removedFrom, removedFromTD,action);
		Webkeywords.instance().click(driver,genericLocators.link(driver, SalesforceConstants.getConstantValue(removedFromTD), removedFromTD),removedFromTD, action);
		Webkeywords.instance().selectInputDropdownValue(driver,removedByTD,"Removed By",action);
		Webkeywords.instance().click(driver, genericLocators.button(driver, "Save",testCaseDataSd.get("SAVE_BTN").get(0)),testCaseDataSd.get("SAVE_BTN").get(0),action);
		
		Webkeywords.instance().pause();
		Webkeywords.instance().waitElementToBeVisible(driver, removalID);
		SalesforceConstants.setConstantValue("Removal_ID"+pomIteration, removalID.getText());
		
	}
	
	public void selectChildCircumstances(String scriptIteration,String pomIteration) {
		PageDetails action = new PageDetails();

		action.setPageActionName("Add child circumstances");
		action.setPageActionDescription("Add child circumstances");

		Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,testCaseParam.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),
				TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),
				scriptIteration, pomIteration);
		
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("CHILD_CIRCUMSTANCES").get(0),"Child Circumstances",action);
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("CHILD_CIRCUMSTANCES2").get(0),"Child Circumstances",action);
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("CHILD_CIRCUMSTANCES3").get(0),"Child Circumstances",action);

		
		Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Alcohol of Choice", testCaseDataSd.get("ALCOHOL_OF_CHOICE_VERIFY").get(0)), testCaseDataSd.get("ALCOHOL_OF_CHOICE_VERIFY").get(0), action);
		Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver,"Alcohol Consumption Level",testCaseDataSd.get("ALCOHOL_CONSUMPTION_LEVEL_VERIFY").get(0)),testCaseDataSd.get("ALCOHOL_CONSUMPTION_LEVEL_VERIFY").get(0),action);
		
		
         Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("CHILD_ALCOHOL_USE_INFORMATION").get(0),"Alcohol Consumption Level",action);
        Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("STIMULANTS").get(0),"Stimulants",action);
        Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("DEPRESSANTS").get(0),"Depressants",action);

	}
	
	public void selectFamilyCircumstances(String scriptIteration,String pomIteration) {
		PageDetails action = new PageDetails();

		action.setPageActionName("Add family circumstances");
		action.setPageActionDescription("Add family circumstances");

		Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,testCaseParam.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),
				TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),
				scriptIteration, pomIteration);
		
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("FAMILY_CIRCUMSTANCES").get(0),"Family Circumstances",action);
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("SUBSTANCE_USER_1").get(0),"Substance User 1",action);

		Webkeywords.instance().click(driver, genericLocators.button(driver, "Save",testCaseDataSd.get("SAVE_BTN2").get(0)),testCaseDataSd.get("SAVE_BTN").get(0),action);


	}
	
	public void verifyRemovalHeaderColumnsInTable(String tableName, String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		
		action.setPageActionName("Verify removal record header columns in Table");
		action.setPageActionDescription("Verify removal record header columns in Table");
			
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);

			Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("HEADERREMOVALID").get(0),columnHeader,tableName,REMOVAL_ID), testCaseDataSd.get("HEADERREMOVALID").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("HEADEREPISODENUMBER").get(0),columnHeader,tableName,EPISODE_NUMBER), testCaseDataSd.get("HEADEREPISODENUMBER").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("HEADERREMOVALDATEANDTIME").get(0),columnHeader,tableName,REMOVAL_DATE_TIME), testCaseDataSd.get("HEADERREMOVALDATEANDTIME").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("HEADERDOCUMENTATIONSTATUS").get(0),columnHeader,tableName,DOCUMENTATION_STATUS), testCaseDataSd.get("HEADERDOCUMENTATIONSTATUS").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("HEADERPRIMERYREASONFORREMOVAL").get(0),columnHeader,tableName,PRIMARY_REASON), testCaseDataSd.get("HEADERPRIMERYREASONFORREMOVAL").get(0), action);
		
	}
	
	public WebElement getElementBasedOnFlag(String flag, String columnHeaderXpath, String tableName, String columnName) {
		if(!(flag.equalsIgnoreCase("n/a"))) {
			return driver.findElement(By.xpath(format(columnHeaderXpath,tableName,columnName)));
		}
		else
			return null;
	}
	
	public void editRemovalDetails(String scriptIteration,String pomIteration) {
		PageDetails action = new PageDetails();

		action.setPageActionName("Edit Removal Details");
		action.setPageActionDescription("Edit Removal Details");

		Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,testCaseParam.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),
				TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),
				scriptIteration, pomIteration);
		
		
		
		Webkeywords.instance().scrollUpPageToTheTop(driver);
		Webkeywords.instance().pause();

		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("DOCUMENTATION_STATUS").get(0),DOCUMENTATION_STATUS,action);
		Webkeywords.instance().click(driver, genericLocators.button(driver, "Save",testCaseDataSd.get("SAVE_BTN2").get(0)),testCaseDataSd.get("SAVE_BTN").get(0),action);
		
		
	}
	
	public void navigateToRemovalRecord(String scriptIteration, String pomIteration){
		PageDetails action = new PageDetails();
		action.setPageActionName("Verify Removal Record");
		action.setPageActionDescription("Verify Removal Record");
		
		Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,testCaseParam.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration, pomIteration);
		
		String removalId = SalesforceConstants.getConstantValue(testCaseDataSd.get("REMOVAL_ID").get(0));
		Webkeywords.instance().scrollUpPageToTheTop(driver);
		Webkeywords.instance().scrollIntoViewElement(driver, viewAllButton);
		Webkeywords.instance().jsClick(driver, viewAllButton, testCaseDataSd.get("VIEWALL").get(0),action);
		Webkeywords.instance().refreshPage(driver);
		Webkeywords.instance().pause();
		Webkeywords.instance().waitElementToBeVisible(driver, removalHeader);
		String formatedXpath = format(recordIdLink,removalId );
		WebElement recordIdXpath = driver.findElement(By.xpath(formatedXpath));	
		Webkeywords.instance().waitElementToBeVisible(driver, recordIdXpath);
		Webkeywords.instance().waitElementClickable(driver, recordIdXpath);
		Webkeywords.instance().jsClick(driver, recordIdXpath, testCaseDataSd.get("REMOVAL_ID").get(0), action);
											
	}
	
}