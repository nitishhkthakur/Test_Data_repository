package cares.cwds.salesforce.pom.referral;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import cares.cwds.salesforce.constants.ModuleConstants;
import cares.cwds.salesforce.constants.SalesforceConstants;
import cares.cwds.salesforce.constants.ScreenConstants;
import cares.cwds.salesforce.utilities.common.TestRunSettings;
import cares.cwds.salesforce.utilities.common.Util;
import cares.cwds.salesforce.utilities.reports.common.ReportCommon;
import cares.cwds.salesforce.utilities.reports.common.ScreenshotCommon;
import cares.cwds.salesforce.utilities.reports.extentmodel.PageDetails;
import cares.cwds.salesforce.utilities.reports.model.TestCaseParam;
import cares.cwds.salesforce.utilities.testng.TestNGCommon;
import cares.cwds.salesforce.utilities.web.CommonOperations;
import cares.cwds.salesforce.utilities.web.GenericLocators;
import cares.cwds.salesforce.utilities.web.SalesforceCommon;
import cares.cwds.salesforce.utilities.web.Webkeywords;

public class Person {

	private static final Logger logger = LoggerFactory.getLogger(Person.class.getName());
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;
	
	TestNGCommon testngCommon = new TestNGCommon();
	TestCaseParam testCaseParam = (TestCaseParam) testngCommon.getTestAttribute("testCaseParam");

	String moduleName = ModuleConstants.REFERRAL;
	String screenName = ScreenConstants.PERSONS;

	private static final String PERSONS = "Case Persons";
	private static final String NEW_BUTTON = "New";
	private static final String SAVE_BUTTON = "Save";
	
	@FindBy(how = How.XPATH, using = "//div[text()='Save']")
	public WebElement saveBtn;
	
	public Person() {
	}

	public Person(WebDriver wDriver) {
		initializePage(wDriver);
	}

	public void initializePage(WebDriver wDriver) {
		logger.info(this.getClass().getName());
		driver = wDriver;
		PageFactory.initElements(driver, this);
		ReportCommon testStepLogDetails = new ReportCommon();
		testStepLogDetails.logModuleAndScreenDetails( moduleName, screenName);
		genericLocators = new GenericLocators(wDriver);
	}

	@FindBy(how = How.XPATH, using = "//p[@title='Person ID']/..//following::p//slot//lightning-formatted-text")
	public WebElement newFoliopersonId;
	
	@FindBy(how = How.XPATH, using = "//records-entity-label[text()='Person Relationship']//ancestor::h1//following::slot//lightning-formatted-text[contains(text(),'REL')]")
	public WebElement personRelationshipId;
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Status'])[1]")
	public WebElement status;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Validate Person']")
	public WebElement validateSection;
		
	String columnHeader = "//table[@aria-label='%s']/thead/tr/th[@*='%s']//span//span[2]";
	
	String folioPerson1 = "//span[text()='%s']/../../../..//parent::a";
	
	@FindBy(how = How.XPATH, using = "//table[@aria-label='Persons']//ancestor::tr//*[@data-label='Person ID']//a//span//span//span")
	public List<WebElement> folioPersonIds;
	
	@FindBy(how = How.XPATH, using = "//table[@aria-label='Persons']//ancestor::tr//*[@data-label='Validated Person Name']//a//span")
	public List<WebElement> folioPersonNames;
	
	@FindBy(how = How.XPATH, using = "//table[@aria-label='Persons']//ancestor::tr//*[@data-label='Role']//span[@title]")
	public List<WebElement> folioPersonRoles;
	
	@FindBy(how = How.XPATH, using = "//table[@aria-label='Persons']//span[text()='Alleged Victim']//ancestor::tr//*[@data-label='Validated Person Name']//a//span")
	public List<WebElement> victimValidatedNames;
	
	@FindBy(how = How.XPATH, using = "//table[@aria-label='Persons']//span[text()='Alleged Perpetrator']//ancestor::tr//*[@data-label='Validated Person Name']//a//span")
	public List<WebElement> perpetratorValidatedNames;
	
	String validatedPerson = "//a[text()='%s']";

	@FindBy(how = How.XPATH, using = "//table[@aria-label='Persons']//ancestor::tr//*[@data-label='Role']//span[@title]")
	public WebElement folioRole;
	
	
	 
	 
	 @FindBy(how = How.XPATH, using = "(//tr[td[contains(.,'Focus Child')]]//a)[1]")
		public WebElement focusChildRecord ;
	 
	 @FindBy(how = How.XPATH, using = "(//records-highlights-details-item//a)[4]")
		public WebElement validatedPersonName ;

	
	public void navigateToFolioPersonsTab( String scriptIteration, String pomIteration){
		PageDetails action = new PageDetails();
		
		action.setPageActionName("Navigate to Folio Persons");
		action.setPageActionDescription("Navigate to Folio Persons");
		
		Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,
				testCaseParam.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),
				TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),
				scriptIteration, pomIteration);

		String personsTabFolio = testCaseDataSd.get("FOLIO_PERSONS_TAB").get(0);
		Webkeywords.instance().pause();
		Webkeywords.instance().click(driver, genericLocators.link(driver, PERSONS, personsTabFolio),
				personsTabFolio, action);
	}
	
	public void addFolioPerson( String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("Add to Folio Persons");
		action.setPageActionDescription("Add to Folio Persons");
	
		Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,
				testCaseParam.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),
				TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),
				scriptIteration, pomIteration);
		
		String newButton = testCaseDataSd.get("NEW_BTN").get(0);
		String lname = util.getRandom(testCaseDataSd.get("FOLIO_PERSON_LASTNAME").get(0));
		String fname = util.getRandom(testCaseDataSd.get("FOLIO_PERSON_FIRSTNAME").get(0));
		String folioRoleValue = testCaseDataSd.get("FOLIO_PERSON_ROLE").get(0);
		String svBtn = testCaseDataSd.get("SAVE_BTN").get(0);

		Webkeywords.instance().scrollUpPageToTheTop(driver);		
		Webkeywords.instance().pause();
		Webkeywords.instance().click(driver, genericLocators.button(driver, NEW_BUTTON, newButton), newButton,
				 action);
		Webkeywords.instance().refresh(driver, action);
		Webkeywords.instance().setText(driver,
				genericLocators.textbox(driver, "First Name", testCaseDataSd.get("FOLIO_PERSON_FIRSTNAME").get(0)), fname,
				 action);
		Webkeywords.instance().setText(driver,
				genericLocators.textbox(driver, "Last Name", testCaseDataSd.get("FOLIO_PERSON_LASTNAME").get(0)), lname,
				 action);

		Webkeywords.instance().click(driver,genericLocators.button(driver, "Search", testCaseDataSd.get("SEARCH_BTN").get(0)),
				testCaseDataSd.get("SEARCH_BTN").get(0), action);
		String newpersonBtnTD =testCaseDataSd.get("NEWPERSON_BTN").get(0);
		
		Webkeywords.instance().waitElementClickable(driver,  genericLocators.button(driver, "New Person",newpersonBtnTD));
		Webkeywords.instance().click(driver, genericLocators.button(driver, "New Person", newpersonBtnTD),
				newpersonBtnTD, action);
		Webkeywords.instance().pause();
		Webkeywords.instance().refresh(driver, action);
		Webkeywords.instance().pause();
		Webkeywords.instance().selectInputDropdownValue(driver,folioRoleValue,"Role",action);
		//Webkeywords.instance().selectValueInputDropdown(driver,folioRoleValue,"Role",action);
		ScreenshotCommon.captureFullPageScreenShot(driver, moduleName+"-"+screenName);	
		Webkeywords.instance().click(driver, genericLocators.button(driver, "Save", svBtn), svBtn,action);
		Webkeywords.instance().pause();
		String newFolioPersonId  =  newFoliopersonId.getText();
		SalesforceConstants.setConstantValue("FP_ID"+pomIteration,newFolioPersonId);
		SalesforceConstants.setConstantValue("folioPersonName"+pomIteration, fname+" "+lname);
	}
	
	public void navigateToPersonRelationships( String scriptIteration, String pomIteration)
			 {
		PageDetails action = new PageDetails();
		
		action.setPageActionName("Navigate to Persons Relationship");
		action.setPageActionDescription("Navigate to Persons Relationship");
		
		Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,
				testCaseParam.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),
				TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),
				scriptIteration, pomIteration);

        String personRelationships =testCaseDataSd.get("PERSON_RELATIONSHIPS").get(0);			
		Webkeywords.instance().click(driver, genericLocators.link(driver, "Person Relationships", personRelationships), personRelationships,
				 action);    
	}
	
	public void addPersonRelationships( String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("Add to Folio Persons Relationships");
		action.setPageActionDescription("Add to Folio Persons Relationships");
		
		Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,
				testCaseParam.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),
				TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),
				scriptIteration, pomIteration);
		
		String newButton = testCaseDataSd.get("NEW_BUTTON").get(0);
		String relationType = testCaseDataSd.get("RELATIONSHIP_TYPE").get(0);
		String reciprocalRelationshipType = testCaseDataSd.get("RECIPROCAL_RELATIONSHIP_TYPE").get(0);
		String relatedPerson = testCaseDataSd.get("RELATED_PERSON").get(0);
		String saveBTN = testCaseDataSd.get("SAVE_AND_PROCEED_BTN").get(0);
		
		Webkeywords.instance().click(driver, genericLocators.button(driver, NEW_BUTTON, newButton), newButton,
				 action);
		Webkeywords.instance().selectValueInputDropdown(driver, relatedPerson, "",
				 action);
		Webkeywords.instance().selectValueInputDropdown(driver, relationType, "Relationship Type",
				action);
		Webkeywords.instance().selectValueInputDropdown(driver, reciprocalRelationshipType,
				"Reciprocal Relationship Type", action);	
		Webkeywords.instance().click(driver, genericLocators.button(driver, SAVE_BUTTON,saveBTN),saveBTN,action);
		SalesforceCommon.verifyToastMessage(driver,testCaseDataSd.get("TOAST_MSG_VERIFY").get(0), action);			
		
		String personRelationshipIdNo = personRelationshipId.getText();
		SalesforceConstants.setConstantValue("REL_ID"+pomIteration, personRelationshipIdNo);
			
	}
	
	public void navigateToAuditHistory( String scriptIteration, String pomIteration)
			 {
		PageDetails action = new PageDetails();
		
		action.setPageActionName("Navigate to Audit History");
		action.setPageActionDescription("Navigate to Audit History");
		

		Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,
				testCaseParam.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),
				TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),
				scriptIteration, pomIteration);
        String auditHistory =testCaseDataSd.get("AUDIT_HISTORY_TB").get(0);			
		Webkeywords.instance().click(driver, genericLocators.link(driver, "Audit History", auditHistory), auditHistory,
				 action);    
	}
	
	public WebElement getElementBasedOnFlag(String flag, String columnHeaderXpath, String tableName, String columnName) {
		if(!(flag.equalsIgnoreCase("n/a"))) {
			return driver.findElement(By.xpath(format(columnHeaderXpath,tableName,columnName)));
		}
		else
			return null;
	}
	
	
	public void verifyFolioPersonHeaderColumnsInTable(String tableName, String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		
		action.setPageActionName("Verify Folio Person header columns in Table");
		action.setPageActionDescription("Verify Folio Person header columns in Table");
			

		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);

		Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("FOLIO_PERSON_ID").get(0),columnHeader,tableName,"Person ID"), testCaseDataSd.get("FOLIO_PERSON_ID").get(0), action);
		Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("VALIDATED_PERSON_NAME").get(0),columnHeader,tableName,"Validated Person Name"), testCaseDataSd.get("VALIDATED_PERSON_NAME").get(0), action);
		Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("FIRST_NAME").get(0),columnHeader,tableName,"First Name"), testCaseDataSd.get("FIRST_NAME").get(0), action);
		Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("LAST_NAME").get(0),columnHeader,tableName,"Last Name"), testCaseDataSd.get("LAST_NAME").get(0), action);
		Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("COLLATERAL_TYPE").get(0),columnHeader,tableName,"Collateral Type"), testCaseDataSd.get("COLLATERAL_TYPE").get(0), action);
		Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("ROLE").get(0),columnHeader,tableName,"Role"), testCaseDataSd.get("ROLE").get(0), action);
		Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("END_DATE").get(0),columnHeader,tableName,"End Date"), testCaseDataSd.get("END_DATE").get(0), action);
	}
	
	
	public void validateFieldText(String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("Verify Folio Person role");
		action.setPageActionDescription("Verify Folio Person role");
			
		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
		Webkeywords.instance().verifyTextDisplayed(driver, folioRole, testCaseDataSd.get("ROLE").get(0), action);
		
	}
	
	public void verifyHeaderColumnsInTable(String tableName, String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		
		action.setPageActionName("Verify header columns in Table");
		action.setPageActionDescription("Verify header columns in Table");
			

		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);


		Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("DATE_VERIFY").get(0),columnHeader,tableName,"Date"), testCaseDataSd.get("DATE_VERIFY").get(0), action);
		Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("FIELD_VERIFY").get(0),columnHeader,tableName,"Field"), testCaseDataSd.get("FIELD_VERIFY").get(0), action);
		Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("USER_VERIFY").get(0),columnHeader,tableName,"User"), testCaseDataSd.get("USER_VERIFY").get(0), action);
		Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("ORIGINAL_VALUE_VERIFY").get(0),columnHeader,tableName,"Original Value"), testCaseDataSd.get("ORIGINAL_VALUE_VERIFY").get(0), action);
		Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("NEW_VALUE_VERIFY").get(0),columnHeader,tableName,"New Value"), testCaseDataSd.get("NEW_VALUE_VERIFY").get(0), action);
	}

	public void verifyPersonRelationshipHeaderColumnsInTable(String tableName, String scriptIteration, String pomIteration){
		PageDetails action = new PageDetails();
		
		action.setPageActionName("Verify Person Relationship header columns in Table");
		action.setPageActionDescription("Verify Person Relationship header columns in Table");
			
		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
		String reciprocalRelationshipType = testCaseDataSd.get("RECIPROCAL_RELATIONSHIP_TYPE").get(0);
		String relatedPersonTD = testCaseDataSd.get("RELATED_PERSON").get(0);
		String relationType = testCaseDataSd.get("RELATIONSHIP_TYPE").get(0);

		Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("RELATIONSHIP_ID").get(0),columnHeader,tableName,"Relationship_Id"), testCaseDataSd.get("RELATIONSHIP_ID").get(0), action);
		Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(relatedPersonTD,columnHeader,tableName,"Related Person"), relatedPersonTD, action);
		Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(reciprocalRelationshipType,columnHeader,tableName,"Reciprocal Relationship Type"), reciprocalRelationshipType, action);
		Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(relationType,columnHeader,tableName,"Relationship Type"), relationType, action);
	}
	
	public void verifyScreeningPeronInFolio(String pomIteration)  {
		PageDetails action = new PageDetails();
		
		action.setPageActionName("Verify Screening Person in Folio");
		action.setPageActionDescription("Verify Screening Person in Folio");
		
		String actualScreeningPersonName = SalesforceConstants.getConstantValue("personName"+pomIteration);
		String formatedXpath = format(folioPerson1,actualScreeningPersonName);
		WebElement scpIdXpath = driver.findElement(By.xpath(formatedXpath));
	  String expectedFolioPersonName = scpIdXpath.getAttribute("title");

		Assert.assertEquals(actualScreeningPersonName, expectedFolioPersonName);				
	}

	public List<List<String>> getFolioPersonDetails()  {
		PageDetails action = new PageDetails();
		action.setPageActionName("Get Folio Person Details");
		action.setPageActionDescription("Get Folio Person Details");
		
		List<String> personIdsList= new ArrayList<>();
		List<String> personNamesList = new ArrayList<>();
		List<String> personRolesList = new ArrayList<>();
		List<String> perpetratorNamesList = new ArrayList<>();
		List<String> victimNamesList = new ArrayList<>();
		Map<String, String[]> folioPersonsMap = new HashMap<>();
		String personRole=null;
		String personId=null;
		String personName=null;
		String key = "";
		int victimCount = 1;
		int perpetratorCount = 1;
		int collateralCount = 1;

		for (int i = 0; i < folioPersonIds.size(); i++) {
			personIdsList.add(folioPersonIds.get(i).getText());
			SalesforceConstants.setConstantValue("FP_ID_"+(i+1),folioPersonIds.get(i).getText());
        }
		for (int i = 0; i < folioPersonNames.size(); i++) {
			personNamesList.add(folioPersonNames.get(i).getText());
			SalesforceConstants.setConstantValue("FP_NAME_"+(i+1),folioPersonNames.get(i).getText());
        }
		for (int i = 0; i < folioPersonRoles.size(); i++) {
			personRolesList.add(folioPersonRoles.get(i).getText());
			SalesforceConstants.setConstantValue("FP_ROLE_"+(i+1), folioPersonRoles.get(i).getText());
        }
		for (int i = 0; i < perpetratorValidatedNames.size(); i++) {
			perpetratorNamesList.add(perpetratorValidatedNames.get(i).getText());
			SalesforceConstants.setConstantValue("FP_PERPETRATOR_NAME_"+(i+1),perpetratorValidatedNames.get(i).getText());
        }
		for (int i = 0; i < victimValidatedNames.size(); i++) {
			victimNamesList.add(victimValidatedNames.get(i).getText());
			SalesforceConstants.setConstantValue("FP_VICTIM_NAME_"+(i+1),victimValidatedNames.get(i).getText());
        }
		
		for (int i = 0; i < folioPersonIds.size(); i++) {
			personId = folioPersonIds.get(i).getText();
			personRole = folioPersonRoles.get(i).getText();
			personName = folioPersonNames.get(i).getText();
		
            if (personRole.equals("Alleged Victim")) {
                key = "Victim" + victimCount++;
            } else if (personRole.equals("Alleged Perpetrator")) {
                key = "Perpetrator" + perpetratorCount++;
            }else if (personRole.equals("Collateral")) {
                key = "Collateral" + collateralCount++;
            }
            
            if (!key.isEmpty()) {
            	folioPersonsMap.put(key, new String[]{personId, personName});
            }
        }
    	SalesforceConstants.setObjectValue("FOLIO_PERSON_MAP",folioPersonsMap);
		
		List<List<String>> folioPersonsList = new ArrayList<>();
		folioPersonsList.add(personIdsList);
		folioPersonsList.add(personNamesList);
		folioPersonsList.add(personRolesList);
		folioPersonsList.add(perpetratorNamesList);
		folioPersonsList.add(victimNamesList);
       	return folioPersonsList;	
	}

	public void navigateToValidatedPerson(String scriptIteration, String pomIteration)  {
	PageDetails action = new PageDetails();
	action.setPageActionName("Navigate to Validated Person");
	action.setPageActionDescription("Navigate to Validated Person");
	
	Webkeywords.instance().scrollUpPageToTheTop(driver);
	Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
	Webkeywords.instance().click(driver,  driver.findElement(By.xpath(format(validatedPerson,SalesforceConstants.getConstantValue("folioPersonName"+pomIteration)))), testCaseDataSd.get("VALIDATED_PERSON_NAME").get(0),action);
}
	
	public void addTribalInfoFocusChild( String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("Add tribal Information Of Focus Child");
		action.setPageActionDescription("Add tribal Information Of Focus Child");
		
		Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,
				testCaseParam.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),
				TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),
				scriptIteration, pomIteration);
		
		String newButton = testCaseDataSd.get("NEW_BUTTON").get(0);
		String tribalInfoTab = testCaseDataSd.get("TRIBAL_INFO_TAB").get(0);
		
		String tribalName = testCaseDataSd.get("TRIBAL_NAME").get(0);
		String childMemeberStatus= testCaseDataSd.get("CHILD_MEMBERSHIP_STATUS").get(0);
		String relatedPerson = testCaseDataSd.get("RELATED_PERSON").get(0);
		String saveBTN = testCaseDataSd.get("SAVE_AND_PROCEED_BTN").get(0);
		
		Webkeywords.instance().click(driver, focusChildRecord,"",action);
		Webkeywords.instance().click(driver, validatedPersonName,"",action);
		
	   Webkeywords.instance().scrollUpPageToTheTop(driver);
	   Webkeywords.instance().click(driver, genericLocators.button(driver,"Tribal Information", tribalInfoTab), tribalInfoTab,
					 action);
		Webkeywords.instance().click(driver, genericLocators.button(driver, NEW_BUTTON, newButton), newButton,
				 action);
		
		
		Webkeywords.instance().selectValueInputDropdown(driver, tribalName, "Tribe Name",
				action);
		Webkeywords.instance().selectValueInputDropdown(driver, childMemeberStatus,
				"Child Membership Status", action);	
		
		
		
		Webkeywords.instance().setText(driver,
				genericLocators.textbox(driver, "", testCaseDataSd.get("TRIBE_LOCATION").get(0)),
				util.getRandom(testCaseDataSd.get("TRIBE_LOCATION").get(0)), action);
		
		 Webkeywords.instance().setDateText(driver,
					genericLocators.datetextbox(driver,"Enrollment Start Date", testCaseDataSd.get("ENROLLMENT_START_DATE").get(0)),
					CommonOperations.getDate("M/d/yyyy", testCaseDataSd.get("ENROLLMENT_START_DATE").get(0)), action);
	      
       
		Webkeywords.instance().click(driver, genericLocators.button(driver, SAVE_BUTTON,saveBTN),saveBTN,action);
//		SalesforceCommon.verifyToastMessage(driver,testCaseDataSd.get("TOAST_MSG_VERIFY").get(0), action);			
//		
//		String personRelationshipIdNo = personRelationshipId.getText();
//		SalesforceConstants.setConstantValue("REL_ID"+pomIteration, personRelationshipIdNo);
			
	}
	
	
}


