package uitests.testng.milestone8;

import java.awt.AWTException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.google.common.base.Objects;

import cares.cwds.salesforce.constants.SalesforceConstants;
import cares.cwds.salesforce.pom.Home;
import cares.cwds.salesforce.pom.Login;
import cares.cwds.salesforce.pom.courts.GenerateDocument;
import cares.cwds.salesforce.pom.folio.Assignments;
import cares.cwds.salesforce.pom.folio.FolioPerson;
import cares.cwds.salesforce.pom.folio.FolioTask;
import cares.cwds.salesforce.pom.foliocase.placement.FolioChildLocations;
import cares.cwds.salesforce.pom.foliocase.placement.FolioPlacement;
import cares.cwds.salesforce.pom.foliocase.placement.FolioPlacementNeeds;
import cares.cwds.salesforce.pom.foliocase.placement.FolioProviderSearch;
import cares.cwds.salesforce.pom.foliocase.placement.ParentGuardianPlacementPreferences;
import cares.cwds.salesforce.pom.foliocase.placement.Removal;
import cares.cwds.salesforce.pom.foliocase.placement.TribePlacementPreference;
import cares.cwds.salesforce.pom.foliocase.placement.YouthPlacementPreferences;
import cares.cwds.salesforce.utilities.testng.TestNGCommon;
import cares.cwds.salesforce.utilities.testng.TestNGListener;
import cares.cwds.salesforce.utilities.web.SalesforceCommon;
import cares.cwds.salesforce.utilities.web.Webkeywords;
import uitests.testng.datasetup.NonInvestigativeCaseDataSetup;

@Listeners(TestNGListener.class)
public class T4258 extends TestNGCommon {
	
	String testCaseName ="To Verify that the CMW and CMS will be able to Initiate Placement Search and Matching for a child";
	String moduleName = "cares";
	String fileName = "ScriptMasterSheet";

	
	@BeforeMethod
	public void setUpReport()
	{
        setTestAttributes("testCaseParam",testCaseParam);
	}
	
	@Test (dataProvider = "data-provider")
	public void testT4258(String scriptIteration) throws InterruptedException, AWTException{

		
		driver = (WebDriver) getTestAttribute("driver");
		
		
		                           /** PreReq Data ***/
	    //Read Investigative Case record from datasheet
    	//SalesforceConstants.setConstantValue("NON_INV_CASE_ID1", readOutputSheet("Case", false, "Contra"));
		//SalesforceConstants.setConstantValue("NON_INV_CASE_ID1", "FOL-000001395626");
		SalesforceConstants.setConstantValue("NON_INV_CASE_ID1", "FOL-000001530014");
    	
    	//PreData Setup scenario
    	//Running preData setup scenario if non inv case record is null from output data sheet
//    	if(Objects.isNull(SalesforceConstants.getConstantValue("NON_INV_CASE_ID1")) ){
//	    	NonInvestigativeCaseDataSetup nonInvestigative = new NonInvestigativeCaseDataSetup();
//	    	nonInvestigative.NonInvestigativeCaseDataSetup1("1");
//    	}
   
		
///////////////////////// Login As CM Worker /////////////////////////////////////////////////////

     Login login = new Login(driver);
     login.processLoginNew( scriptIteration,SalesforceConstants.POMITERATION1, SalesforceConstants.CMWORKER); 

     Home home = new Home(driver);
     home.closeAllTabs();
//   home.navigateToAppMenuPage(SalesforceConstants.FOLIO, scriptIteration, SalesforceConstants.POMITERATION1);
     home.searchFolioRecord(scriptIteration, SalesforceConstants.POMITERATION1);
     
     //--------------------------  Pre-Condition ----------------------------------------//
                     
//            FolioPerson folioPerson = new FolioPerson(driver);
// 	        folioPerson.navigateToFolioPersonsTab( scriptIteration, SalesforceConstants.POMITERATION1);
// 	        folioPerson.addFolioPerson( scriptIteration, SalesforceConstants.POMITERATION1);
//// 	       
   
 
//     //-----------------------create Placement record provider option--------------------------//
//	        FolioProviderSearch search= new FolioProviderSearch(driver);
//	        search.navigateToNonInvestigativeCaseProviderSearch(scriptIteration, SalesforceConstants.POMITERATION1);
//
//	        search.searchProvderType(scriptIteration, SalesforceConstants.POMITERATION1);
//	        search.searchTheProviderSearch(scriptIteration, SalesforceConstants.POMITERATION1);
//	        Webkeywords.instance().refreshPage(driver);
//	        search.enterNewPlacementDetails(scriptIteration, SalesforceConstants.POMITERATION1);
//	     //   search.captureNIFolioURL(SalesforceConstants.POMITERATION1);
//     
   
	        
//-----------------------create Removal Record and Validate------------------------------------//

   
      Removal removal=new Removal();
      removal.navigateToRemovalTab(scriptIteration, SalesforceConstants.POMITERATION1);
      removal.addRemovalDetails(scriptIteration, SalesforceConstants.POMITERATION1);
      removal.selectChildCircumstances(scriptIteration,SalesforceConstants.POMITERATION1);
      removal.selectFamilyCircumstances(scriptIteration,SalesforceConstants.POMITERATION1);
	  
//	 //removal.editRemovalDetails(scriptIteration, SalesforceConstants.POMITERATION1);
//	  removal.verifyRemovalHeaderColumnsInTable("Removal",scriptIteration, SalesforceConstants.POMITERATION1);
//	  SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.FOLIO);
//    
//      
//   //--------------------------Child Location Record with type as Placement record-----------------------//
//     FolioChildLocations folioChildLocations=new FolioChildLocations();
//     SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.FOLIO);
//     folioChildLocations.navigateToChildLocationTab(scriptIteration,SalesforceConstants.POMITERATION1);
//     folioChildLocations.clickNewChildLocationType(scriptIteration,SalesforceConstants.POMITERATION1);
//     folioChildLocations.enterChildLocationPlacementDetails(scriptIteration,SalesforceConstants.POMITERATION1);
//     //verify child location Id linking
//     
//    
//     
//    //Folio->Navigate to Focus child-click placement info-Removal record validate--------//
//     
//     
//     
//     
//     
//     
//     //----------------------------------------
//     FolioProviderSearch providerSearch= new FolioProviderSearch();
//     providerSearch.navigateToProviderSearch(scriptIteration,SalesforceConstants.POMITERATION1);
//  
//     
//     
//     
//    
//     
//     // ------------------Folio->Placement->Placement and prefrences->tribePlacements---------------   //
//    
//     FolioPlacement folioPlacement= new FolioPlacement(driver);
//     SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.FOLIO);
//     folioPlacement.navigateToPlacement(scriptIteration,SalesforceConstants.POMITERATION1);
//     folioPlacement.navigateToPlacementPreferencesAndNeeds(scriptIteration,SalesforceConstants.POMITERATION1);
//     
//     TribePlacementPreference tribePlacementPreference=new TribePlacementPreference();
//     tribePlacementPreference.enterTribalPlacementPreference(scriptIteration,SalesforceConstants.POMITERATION1);
//    
//     
// 
//    //--------------------------------Verify-task is generated---------------------------------------//
//     
//     FolioTask folioTask=new FolioTask();
//     folioTask.navigateToNewTask(scriptIteration,SalesforceConstants.POMITERATION1);
//     folioTask.addNewTask(scriptIteration,SalesforceConstants.POMITERATION1);
//    
//    
//   // ------------------Folio->Placement->Placement and prefrences->Placement needs---------------   //
//  
//     FolioPlacementNeeds folioPlacementNeeds= new FolioPlacementNeeds(driver);
//     SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.FOLIO);
//     folioPlacementNeeds.navigateToPlacementNeedsTab(scriptIteration,SalesforceConstants.POMITERATION1);
//     folioPlacementNeeds.enterPlacementNeedsDetails(scriptIteration,SalesforceConstants.POMITERATION1);
//     //Edit
//     
//     
//     // ------------------Folio->Placement->Placement and prefrences->Parent Guardian---------------   //
//     
//     ParentGuardianPlacementPreferences parentGuardianPlacementPreferences=new ParentGuardianPlacementPreferences();
//
//     folioPlacement.navigateToPlacement(scriptIteration,SalesforceConstants.POMITERATION1);
//     folioPlacement.navigateToPlacementPreferencesAndNeeds(scriptIteration,SalesforceConstants.POMITERATION1);
//     
//     parentGuardianPlacementPreferences.navigateToPlacementPreterence(scriptIteration,SalesforceConstants.POMITERATION1);
//     parentGuardianPlacementPreferences.enterParentGuardianPlacementPreference(scriptIteration,SalesforceConstants.POMITERATION1);
//     //Edit-Can't be edited
//     
//          
//     
//   // ----------------Folio->Placement->Placement and prefrences->Youth placement prefences----------- //
//  
//     YouthPlacementPreferences youthPlacementPreferences= new YouthPlacementPreferences(driver);
//     SalesforceCommon.navigateToRecordURL(driver, SalesforceConstants.FOLIO);
//     youthPlacementPreferences.navigateToPlacementPreterence(scriptIteration,SalesforceConstants.POMITERATION1);
//     youthPlacementPreferences.enterYouthPlacementPreference(scriptIteration,SalesforceConstants.POMITERATION1);
//     //Edit-Can't be edited
//     
////     //-----------------------create Placement record provider option--------------------------//
////      search= new FolioProviderSearch(driver);
////     search.navigateToProviderSearch(scriptIteration, SalesforceConstants.POMITERATION1);
////     search.searchProvderType(scriptIteration, SalesforceConstants.POMITERATION1);
////     search.searchTheProviderSearch(scriptIteration, SalesforceConstants.POMITERATION1);
////     Webkeywords.instance().refreshPage(driver);
////     search.enterNewPlacementDetails(scriptIteration, SalesforceConstants.POMITERATION1);
//     
//    
//     //----------------------Generate Documents-----------------------//
//     GenerateDocument doc = new GenerateDocument(driver);
//     doc.generateDocument(scriptIteration, SalesforceConstants.POMITERATION1);       
//     doc.submitDocument();	        
//     doc.generateDocument(scriptIteration, SalesforceConstants.POMITERATION2);
//     doc.submitDocument();
//     
//     //---ProviderOption->Create Contact Log Record-------------------------//
//     
//     folioChildLocations= new FolioChildLocations(driver);
//     folioChildLocations.navigateToChildLocationTab(scriptIteration, SalesforceConstants.POMITERATION1);
//     folioChildLocations.navigateToChildLocationRecord(scriptIteration, SalesforceConstants.POMITERATION1);
//     
//     
//     
//  
//     //-------------Child Location----------------------------//
//     
//     folioChildLocations= new FolioChildLocations(driver);
//     folioChildLocations.navigateToChildLocationTab(scriptIteration, SalesforceConstants.POMITERATION1);
//     folioChildLocations.navigateToChildLocationRecord(scriptIteration, SalesforceConstants.POMITERATION1);
//     //folioChildLocations.editChildLocationTab(scriptIteration, SalesforceConstants.POMITERATION1);
//     
     
     
     
     
     //--------------------------------------Foli-> Assignments----------------------------------//
     Assignments assignments=new Assignments();
     assignments.navigateToFolioAssignmentsTab(scriptIteration,SalesforceConstants.POMITERATION1);
     assignments.addAssignmentsInfoFolio(scriptIteration,SalesforceConstants.POMITERATION1);
    
   
     
     

	}
	 @DataProvider (name = "data-provider")
	    public String[] dpMethod(Method method){
		 testCaseParam.setTestCaseName(testCaseName);
	        testCaseParam.setModuleName(moduleName);
	        testCaseParam.setBrowser(browser);
	        testCaseParam.setTestCaseDescription(testCaseParam.getTestCaseName());
	        testCaseParam.setTestCaseDescription(testCaseParam.getTestCaseName());
	        testCaseParam.setTestNGTestMethodName(method.getName());
	    	return setScriptIterationFlag(fileName,moduleName, method.getName());
	   
	    }

}
