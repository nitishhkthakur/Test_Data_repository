package cares.cwds.salesforce.pom.courts;

import static java.lang.String.format;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import cares.cwds.salesforce.constants.ModuleConstants;
import cares.cwds.salesforce.constants.SalesforceConstants;
import cares.cwds.salesforce.constants.ScreenConstants;
import cares.cwds.salesforce.utilities.common.TestRunSettings;
import cares.cwds.salesforce.utilities.common.Util;
import cares.cwds.salesforce.utilities.reports.common.ReportCommon;
import cares.cwds.salesforce.utilities.reports.extentmodel.PageDetails;
import cares.cwds.salesforce.utilities.reports.model.TestCaseParam;
import cares.cwds.salesforce.utilities.testng.TestNGCommon;
import cares.cwds.salesforce.utilities.web.GenericLocators;
import cares.cwds.salesforce.utilities.web.Webkeywords;

public class AmendPetition {
	
	
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;
	LocalDateTime startTime= LocalDateTime.now();
	TestNGCommon testngCommon = new TestNGCommon();
	TestCaseParam testCaseParam = (TestCaseParam) testngCommon.getTestAttribute("testCaseParam");

	String moduleName = ModuleConstants.COURTS;
	String screenName = ScreenConstants.GENERATEPETITION;
	
	
public AmendPetition(){ }
	
	public AmendPetition(WebDriver wDriver)
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
	WebElement amendPetitionOption;
	
	String amendPetition = "//*[text()='Amend Petition']";
	
	@FindBy(how = How.XPATH, using = "(//*[@title='Status']/..//lightning-formatted-text)[3]")
	public WebElement status;
	
	@FindBy(how = How.XPATH, using = "(//lightning-formatted-lookup[contains(@class,'slds-form-element__static')])[4]")
	public WebElement PetitionWorkItemId;
	
	String columnHeader = "//table[@aria-label='%s']/thead/tr/th[@*='%s']";
	
public void performAmendPetition(String scriptIteration, String pomIteration){
		
		PageDetails action = new PageDetails();
		action.setPageActionName("Enter Amend Petition Details");
		action.setPageActionDescription("Enter Amend Petition Details");
		
      	Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
		Webkeywords.instance().scrollUpPageToTheTop(driver);
		
		Webkeywords.instance().click(driver, amendPetitionOption, testCaseDataSd.get("OPTION_AMEND_PETITION").get(0), action);
		
		Webkeywords.instance().verifyelementnotdisplayed(driver, amendPetition, testCaseDataSd.get("AMEND_PETITION_VISIBLE").get(0), action);
		
		
		Webkeywords.instance().click(driver, genericLocators.button(driver, "Amend Petition", testCaseDataSd.get("AMEND_PETITION_BTN").get(0)), testCaseDataSd.get("AMEND_PETITION_BTN").get(0),action);
		Webkeywords.instance().pause();
		
				

}

public void checkStatusOnAmendedRecord( String scriptIteration, String pomIteration) throws InterruptedException{

	PageDetails action = new PageDetails();
	action.setPageActionName("Check Status On Amended Record");
	action.setPageActionDescription("Check Status On Amended Record");
	
		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
		Webkeywords.instance().pause();
		
        Webkeywords.instance().waitElementToBeVisible(driver, status);
        Webkeywords.instance().scrollUpPageToTheTop(driver);
        
        Webkeywords.instance().softAssertTextPOM(status.getText(),testCaseDataSd.get("AMENDED_PETITION_STATUS").get(0), this.getClass().getName(),  "checkStatusOnAmendedRecord", "Status");

        Assert.assertTrue(genericLocators.checkbox(driver,"Amended Petition",testCaseDataSd.get("AMENDED_PETITION_CHECKBOX_VERIFY").get(0)).isSelected(),"Amend Petition Checkbox should be checked but is not");

}

public void verifyRecordsLinked(String scriptIteration, String pomIteration)  {
	PageDetails action = new PageDetails();
	
	action.setPageActionName("Verify Records are linked to Court Case");
	action.setPageActionDescription("Verify Records are linked to Court Case");
	
	Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, testCaseParam.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);

	String expectedPetitioWorkItemID = SalesforceConstants.getConstantValue("PETITION_WORK_ITEM_ID"+pomIteration);
	String actualPetitionWorkItemID = PetitionWorkItemId.getText();
	Webkeywords.instance().softAssertText(actualPetitionWorkItemID, expectedPetitioWorkItemID);
	    
	
	
	
}

public void verifyHeaderColumnsInRelatedTabInAmendedPetition(String tableName, String scriptIteration,
		String pomIteration) {
	
	PageDetails action = new PageDetails();

	action.setPageActionName("Verify header columns in Related Cases Table");
	action.setPageActionDescription("Verify header columns in Related Cases Table");

	Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,
			testCaseParam.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),
			TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),
			scriptIteration, pomIteration);
	Webkeywords.instance().click(driver, genericLocators.button(driver, "Related", testCaseDataSd.get("RELATED_TAB").get(0)), testCaseDataSd.get("RELATED_TAB").get(0),action);

	Webkeywords.instance().verifyElementDisplayed(driver,
			getElementBasedOnFlag(testCaseDataSd.get("CASE_REF_VERIFY").get(0), columnHeader, tableName, "CARES Item: Case Ref."),
			testCaseDataSd.get("CASE_REF_VERIFY").get(0), action);
	
	Webkeywords.instance().verifyElementDisplayed(driver,
			getElementBasedOnFlag(testCaseDataSd.get("CASE_NAME_VERIFY").get(0), columnHeader, tableName, "CARES Item: Case Name"),
			testCaseDataSd.get("CASE_NAME_VERIFY").get(0), action);
	
	Webkeywords.instance().verifyElementDisplayed(driver,
			getElementBasedOnFlag(testCaseDataSd.get("CASE_TYPE_VERIFY").get(0), columnHeader, tableName, "CARES Item: Case Type"),
			testCaseDataSd.get("CASE_TYPE_VERIFY").get(0), action);

	Webkeywords.instance().verifyElementDisplayed(driver,
			getElementBasedOnFlag(testCaseDataSd.get("SERVICE_COMPONENT_VERIFY").get(0), columnHeader, tableName, "CARES Item: Service Component"),
			testCaseDataSd.get("SERVICE_COMPONENT_VERIFY").get(0), action);

	Webkeywords.instance().verifyElementDisplayed(driver,
			getElementBasedOnFlag(testCaseDataSd.get("PRIMARY_WORKER_VERIFY").get(0), columnHeader, tableName, "CARES Item: Primary Worker"),
			testCaseDataSd.get("PRIMARY_WORKER_VERIFY").get(0), action);
	
	
 }
	
	public WebElement getElementBasedOnFlag(String flag, String columnHeaderXpath, String tableName,
			String columnName) {
		if (!(flag.equalsIgnoreCase("n/a"))) {
			return driver.findElement(By.xpath(format(columnHeaderXpath, tableName, columnName)));
		} else
			return null;
	}


}
