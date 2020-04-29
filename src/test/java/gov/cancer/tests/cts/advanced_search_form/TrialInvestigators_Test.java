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
 * This test class contains all tests related to the Trial Investigators section on the Advanced Search Form
 */
public class TrialInvestigators_Test extends TestObjectBase {

  /**
   * Verify that Trial Investigators Section is visible
   * parameters
   *
   * @param path url
   */
  @Test(dataProvider = "getAdvancedSearchTrialInvestigators")
  public void verifyTrialInvestigatorsSectionIsVisible(String path) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      Assert.assertTrue(page.getTrialInvestigator().isVisible(), "Trial Investigators Section is not visible");
    });
  }

  /**
   * Verify the Trial Investigators Section Title, help Link and reference of url
   * parameters
   *
   * @param path                      url
   * @param ExpectedTitle             Title
   * @param ExpectedHelpLinkPath      redirection link
   * @param ExpectedHelpLinkReference reference of url
   */
  @Test(dataProvider = "getAdvancedSearchTitleAndHelpLink")
  public void verifyTrialInvestigatorsSectionTitleAndHelpLink(String path, String ExpectedTitle, String ExpectedHelpLinkPath, String ExpectedHelpLinkReference) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      //verify the title on the trial investigator section
      Assert.assertEquals(page.getTrialInvestigator().getTitle(), ExpectedTitle, "Trial Investigators Section Title does not match");
      //verify help link path on the trial investigator section
      Assert.assertEquals(page.getTrialInvestigator().getHelpLink().getUrl().getPath(), ExpectedHelpLinkPath, "Help link does not match in the Trial Investigators section");
      //verify the url reference on the trial investigator section
      Assert.assertEquals(page.getTrialInvestigator().getHelpLink().getUrl().getRef(), ExpectedHelpLinkReference, "Url reference to the Trial Investigators section does not match");

    });
  }

  /**
   * Verify the Trial Investigator Section Helper Text
   * parameters
   *
   * @param path                 url
   * @param ExpectedHelperText   the helper text under the Trial Investigator input box
   */
  @Test(dataProvider = "getAdvancedSearchHelperText")
  public void verifyTrialInvestigatorsHelperText(String path, String ExpectedHelperText) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      Assert.assertEquals(page.getTrialInvestigator().getHelpText(), ExpectedHelperText, "Trial Investigators Section helper text does not match");
    });
  }

  /**
   * Verify the Trial Investigator Section Autocomplete Helper Text
   * parameters
   *
   * @param path                           url
   * @param ExpectedAutocompleteHelperText the helper text that appears under the Trial Investigator input box
   *                                       when the user clicks on the Trial Investigator input box
   */
  @Test(dataProvider = "getAdvancedSearchAutoHelperText")
  public void verifyTrialInvestigatorsAutocompleteHelperText(String path, String ExpectedAutocompleteHelperText) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getTrialInvestigator().getInvestigators().setFocus();
      Assert.assertEquals(page.getTrialInvestigator().getAutoCompleteHelpText(), ExpectedAutocompleteHelperText, "Trial Investigators Section autohelper text does not match");
    });
  }

  /**
   * When user inputs Trial Investigators and clicks Find Trials button, correct results are
   * displayed. Assertion is performed by verifying that url contains search
   * parameters
   * @param path                         url
   * @param  TrialInvestigatorName       the trial investigator name
   */

  @Test(dataProvider = "getAdvancedSearchTrialInvestigatorsAutoSuggestField")
  public void verifyTrialInvestigatorsAutosuggestField(String path, String TrialInvestigatorName) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getTrialInvestigator().getInvestigators().selectItem(TrialInvestigatorName);
      // create a navigation event redirect to search results page
      SearchNavigationResult searchresult = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      // assert that url contains expected query params
      Assert.assertEquals(searchresult.getTrialInvestigatorsParam(), TrialInvestigatorName, "query param does not match for Trial Investigators");
    });
  }

  /**
   * When user inputs invalid Trial Investigators and clicks Find Trials button, no search results page is displayed.
   * Assertion is performed by verifying that url contains search parameters and no search result message is displayed
   * parameters
   * @param path                         url
   * @param  InvalidTrialInvestigatorName       the trial investigator name
   * @param ErrorMsg                            the error message
   */

  @Test(dataProvider = "getAdvancedSeachTrialInvestigatorsAutoSuggestFieldNegative")
  public void verifyTrialInvestigatorsNoResultsPage(String path, String InvalidTrialInvestigatorName, String ErrorMsg) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getTrialInvestigator().getInvestigators().enterText(InvalidTrialInvestigatorName);
      // create a navigation event redirect to search results page
      SearchNavigationResult searchresult = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      // assert that url contains expected query params
      Assert.assertEquals(searchresult.getTrialInvestigatorsParam(), InvalidTrialInvestigatorName, "query param does not match for Trial Investigators");
      Assert.assertEquals(searchresult.getNoResultMsg(),ErrorMsg, "No search result message is not displayed for invalid Trial investigators");

    });
  }

  /**
   * Verify the Trial Investigator Section Placeholder Text
   * parameters
   *
   * @param path                    url
   * @param ExpectedPlaceholderText the helper text under the Trial Investigator input box
   */
  @Test(dataProvider = "getAdvancedSearchPlaceholderText")
  public void verifyTrialInvestigatorsPlaceholderText(String path, String ExpectedPlaceholderText) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      Assert.assertEquals(page.getTrialInvestigator().getPlaceHolderText(), ExpectedPlaceholderText, "Trial Investigators Section placeholder text does not match");
    });
  }

  /**
   * Verify the Trial Investigator Section AutoComplete helper text for invalid Trial Investigators
   * parameters
   *
   * @param path                           url
   * @param ExpectedAutocompleteHelperText the helper text that appears under the Trial Investigator input box
   *                                       when the user clicks on the Trial Investigator input box
   * @param InvalidTrialInvestigatorName          the Trial investigator name
   */
  @Test(dataProvider = "getAdvancedSearchHelperTextForInvalidTrialInvestigators")
  public void verifyNoResultsErrorMessageOnTheTrialInvestigatorsField(String path, String ExpectedAutocompleteHelperText, String InvalidTrialInvestigatorName) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getTrialInvestigator().getInvestigators().enterText(InvalidTrialInvestigatorName);
      page.getTrialInvestigator().getInvestigators().setFocus();
      Assert.assertEquals(page.getTrialInvestigator().getAutoCompleteHelpText(), ExpectedAutocompleteHelperText, "Trial Investigators Section autohelper text for invalid Trial investigators does not match");
    });
  }

  /************************SUBMIT SEARCH WITH HITTING ENTER KEY***********************/
  /**
   * When user inputs Trial Investigators and hits Enter, correct results are
   * displayed. Assertion is performed by verifying that url contains search
   * parameters
   * @param path                         url
   * @param  TrialInvestigatorName       the trial investigator name
   */
  @Test(dataProvider = "getAdvancedSearchTrialInvestigatorsAutoSuggestField")
  public void verifyTrialInvestigatorsAutosuggestFieldWithEnter(String path, String TrialInvestigatorName) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
        page.getTrialInvestigator().getInvestigators().selectItem(TrialInvestigatorName);
      //create a navigation event redirect to search results page
      SearchNavigationResult res = page.hitEnterKey(page.getTrialInvestigator().getInvestigators());
      // assert that url contains expected query params
      Assert.assertEquals(res.getTrialInvestigatorsParam(), TrialInvestigatorName, "query param doesnot match for Trial Investigators");

    });
  }

  /************************************************************
   * Data providers
   ************************************************************/

  /**
   * Data provider retrieves paths to Advanced Search page
   *
   * @return
   */
  @DataProvider(name = "getAdvancedSearchTrialInvestigators")
  public Iterator<Object[]> getAdvancedSearchTrialInvestigators() {
    String[] columns = {"path"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_trialinvestigators_data.xlsx"), "trial_investigators", columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page, Title, HelpLinkPath and reference of url
   *@return
   *
   */
  @DataProvider(name = "getAdvancedSearchTitleAndHelpLink")
  public Iterator<Object[]> getAdvancedSearchTitleAndLink() {
    String[] columns = {"path", "ExpectedTitle", "ExpectedHelpLinkPath", "ExpectedHelpLinkReference"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_trialinvestigators_data.xlsx"), "trial_investigators",
      columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page and Helper text
   *@return
   *
   */
  @DataProvider(name = "getAdvancedSearchHelperText")
  public Iterator<Object[]> getAdvancedSearchHelperText() {
    String[] columns = {"path", "ExpectedHelperText"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_trialinvestigators_data.xlsx"), "trial_investigators",
      columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page and Autocomplete Helper text
   *@return
   */
  @DataProvider(name = "getAdvancedSearchAutoHelperText")
  public Iterator<Object[]> getAdvancedSearchAutoHelperText() {
    String[] columns = {"path", "ExpectedAutocompleteHelperText"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_trialinvestigators_data.xlsx"), "trial_investigators",
      columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page and Autocomplete Helper text for Invalid Trial Investigators
   *@return
   */
  @DataProvider(name = "getAdvancedSearchHelperTextForInvalidTrialInvestigators")
  public Iterator<Object[]> getAdvancedSearchHelperTextForInvalidTrialInvestigators() {
    String[] columns = {"path", "ExpectedAutocompleteHelperText", "InvalidTrialInvestigatorName"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_trialinvestigators_data.xlsx"), "trial_investigators_negative",
      columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page and Trial Investigator name
   * @return
   *
   */
  @DataProvider(name = "getAdvancedSearchTrialInvestigatorsAutoSuggestField")
  public Iterator<Object[]> getAdvancedSearchTrialInvestigatorsAutoSuggestield() {
    String[] columns = { "path", "TrialInvestigatorName" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_trialinvestigators_data.xlsx"), "trial_investigators",
      columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page and Placeholder text
   *@return
   */
  @DataProvider(name = "getAdvancedSearchPlaceholderText")
  public Iterator<Object[]> getAdvancedSearchPlaceholderText() {
    String[] columns = {"path", "ExpectedPlaceholderText"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_trialinvestigators_data.xlsx"), "trial_investigators",
      columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page and Invalid Trial Investigator name
   * @return
   *
   */
  @DataProvider(name = "getAdvancedSeachTrialInvestigatorsAutoSuggestFieldNegative")
  public Iterator<Object[]> getAdvancedSeachTrialInvestigatorsAutoSuggestFieldNegative() {
    String[] columns = { "path", "InvalidTrialInvestigatorName", "ErrorMsg" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_trialinvestigators_data.xlsx"), "trial_investigators_negative",
      columns);
  }
}

