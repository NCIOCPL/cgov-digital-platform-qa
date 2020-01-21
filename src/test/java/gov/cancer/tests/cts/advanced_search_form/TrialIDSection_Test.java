package gov.cancer.tests.cts.advanced_search_form;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.cts.AdvancedSearchPage;
import gov.cancer.pageobject.cts.SearchNavigationResult;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

/**
 * This test class contains all tests related to the Trial Id section on the Advanced Search Form
 */

public class TrialIDSection_Test extends TestObjectBase {
  /**
   * Verify that Trial ID Section is visible parameters
   *
   * @param path
   *          url
   *
   */
  @Test(dataProvider = "getTrialID")
  public void verifyTrialIDSectionIsVisible(String path, String TrialID) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {

      Assert.assertTrue(page.getTrialId().isVisible(), "Trial ID Section is not visible");

    });
  }

  /**
   * Verify the Trial Id Section Title, help Link and help link query param
   * parameters
   *
   * @param path
   *          url
   * @param ExpectedTitle
   *          Title
   * @param ExpectedHelpLinkPath
   *          redirection link
   * @param ExpectedHelpLinkReference
   *
   */
  @Test(dataProvider = "getTrialIDTitleAndHelpLink")
  public void verifyTrialIDSectionTitleAndHelpLink(String path, String ExpectedTitle, String ExpectedHelpLinkPath,
      String ExpectedHelpLinkReference) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {

      // verify the title on the page
      Assert.assertEquals(page.getTrialId().getTitle(), ExpectedTitle, "Trial ID Section Title does not match");
      // verify help link path
      Assert.assertEquals(page.getTrialId().getHelpLink().getUrl().getPath(), ExpectedHelpLinkPath,
          "Help link does not match in the Trial ID section");
      // verify help link query param
      Assert.assertEquals(page.getTrialId().getHelpLink().getUrl().getRef(), ExpectedHelpLinkReference,
          "Link reference to the Trial ID page section does not match");

    });
  }

  /**
   * Verify the Trial ID Section Helper Text parameters
   *
   * @param path
   *          url
   * @param ExpectedHelperText
   *
   */
  @Test(dataProvider = "getTrialIDHelperText")
  public void verifyTrialIDSectionHelperText(String path, String ExpectedHelperText) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {

      Assert.assertEquals(page.getTrialID().getHelpText(), ExpectedHelperText,
          "Trial ID Section helper text does not match");
          });
  }

  /**
   * When user inputs Trial ID and clicks Find Trials button, correct results are
   * displayed. Assertion is performed by verifying that url contains search
   * parameters
   *
   * @param path
   *          url
   * @param TrialID
   *          Trial Id value
   */

  @Test(dataProvider = "getTrialID")
  public void verifyTrialIDTextField(String path, String TrialID) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {

      page.getTrialID().getTrialID().enterText(TrialID);

      // create a navigation event redirect to search results page
      SearchNavigationResult search_result = page.clickSubmit(page.getFormAction().getFindTrialsButton());

      // assert that url contains expected query params
      Assert.assertEquals(search_result.getTrialIDParam(), TrialID, "query param does not match for TrialID");
          });
  }

  /************************
   * SUBMIT SEARCH WITH HITTING ENTER KEY
   ***********************/

  /**
   * When user inputs Trial ID and hits enter, correct results are displayed.
   * Assertion is performed by verifying that url contains search parameters
   *
   * @param path
   *          url
   * @param TrialID
   *          trial id value
   */
  @Test(dataProvider = "getTrialID")
  public void verifyTrialIDTextFieldWithEnter(String path, String TrialID) {

    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getTrialID().getTrialID().enterText(TrialID);
      page.getTrialID().getTrialID().setFocus();

      // create a navigation event redirect to search results page

      SearchNavigationResult res = page.hitEnterKey(page.getTrialID().getTrialID());
      // assert that url contains expected query params
      Assert.assertEquals(res.getTrialIDParam(),TrialID, "query param doesnt match for Trial Id");
       });
  }

  /**
   * When user inputs invalid Trial ID and clicks Find Trials button, no search results page is displayed
   * Assertion is performed by verifying that url contains search parameters and no search results message is displayed
   *
   * @param path
   *          url
   * @param TrialID
   *          trial id value
   * @param errormsg
   *          error message
   */
  @Test(dataProvider = "getTrialIDNegative")
  public void verifyTrialIDInvalidEntry(String path, String TrialID, String errormsg) {

    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getTrialID().getTrialID().enterText(TrialID);

      // create a navigation event redirect to search results page
      SearchNavigationResult search_result = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      // assert that url contains expected query params
      Assert.assertEquals(search_result.getTrialIDParam(),TrialID, "query param doesnt match for Trial Id");

      Assert.assertEquals(search_result.getNoResultMsg(),errormsg, "No search result message is not displayed for invalid Trial Id");
    });
  }

  /************************************************************
   * Data providers
   ************************************************************/

  /**
   * Data provider retrieves paths to Advanced Search page, Trial ID value
   *
   *
   */

  @DataProvider(name = "getTrialID")
  public Iterator<Object[]> getTrialID() {
    String[] columns = { "path", "TrialID" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_trial_id_data.xlsx"), "advanced_search", columns);
  }
  /**
   * Data provider retrieves paths to Advanced Search page, Trial ID value, error message
   *
   *
   */

  @DataProvider(name = "getTrialIDNegative")
  public Iterator<Object[]> getTrialIDNegative() {
    String[] columns = { "path", "TrialID","ErrorMsg" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_trial_id_data.xlsx"), "advanced_search_negative", columns);
  }

    /**
   * Data provider retrieves paths to Advanced Search page and the Title, Help
   * link and Help Link query param for the Trial ID Section
   *
   * @return
   */

  @DataProvider(name = "getTrialIDTitleAndHelpLink")
  public Iterator<Object[]> getTrialIDTitleAndHelpLink() {
    String[] columns = { "path", "ExpectedTitle", "ExpectedHelpLinkPath", "ExpectedHelpLinkReference" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_trial_id_data.xlsx"), "advanced_search", columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page and the Helper Text
   * for the Trial ID Section
   *
   * @return
   */

  @DataProvider(name = "getTrialIDHelperText")
  public Iterator<Object[]> getTrialIDHelperText() {
    String[] columns = { "path", "ExpectedHelperText" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_trial_id_data.xlsx"), "advanced_search", columns);
  }
}
