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
 * This test class contains all tests related to the Lead Organization section on the Advanced Search Form
 */

public class LeadOrganization_Test extends TestObjectBase {
  /**
   * Verify that Lead Organization Section is visible
   * parameters
   *
   * @param path url
   */
  @Test(dataProvider = "getAdvancedSearchLeadOrganization")
  public void verifyLeadOrganizationSectionIsVisible(String path) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      Assert.assertTrue(page.getLeadOrganizationSection().isVisible(), "Lead Organization Section is not visible");
    });
  }

  /**
   * Verify the Lead Organization Section Title, help Link and and reference of url
   * parameters
   *
   * @param path                      url
   * @param ExpectedTitle             Title
   * @param ExpectedHelpLinkPath      redirection link
   * @param ExpectedHelpLinkReference and reference of url
   */
  @Test(dataProvider = "getAdvancedSearchTitleAndHelpLink")
  public void verifyLeadOrganizationSectionTitleAndHelpLink(String path, String ExpectedTitle, String ExpectedHelpLinkPath, String ExpectedHelpLinkReference) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      //verify the title on the page
      Assert.assertEquals(page.getLeadOrganizationSection().getTitle(), ExpectedTitle, "Lead Organization Section Title does not match");
      //verify help link path
      Assert.assertEquals(page.getLeadOrganizationSection().getHelpLink().getUrl().getPath(), ExpectedHelpLinkPath, "Help link does not match in the Lead Organization section");
      //verify help link query param
      Assert.assertEquals(page.getLeadOrganizationSection().getHelpLink().getUrl().getRef(), ExpectedHelpLinkReference, "Url reference to the Lead Organization section does not match");

    });
  }

  /**
   * Verify the Lead Organization Section Helper Text
   * parameters
   *
   * @param path               url
   * @param ExpectedHelperText the helper text under the Lead Organization input box
   */
  @Test(dataProvider = "getAdvancedSearchHelperText")
  public void verifyLeadOrganizationHelperText(String path, String ExpectedHelperText) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      Assert.assertEquals(page.getLeadOrganizationSection().getHelpText(), ExpectedHelperText, "Lead Organization Section helper text does not match");
    });
  }
  /**
   * Verify the Lead Organization Section Autocomplete Helper Text
   * parameters
   *
   * @param path                           url
   * @param ExpectedAutocompleteHelperText the helper text that appears under the Lead Organization input box
   *                                       when the user clicks on the Lead Organization input box
   */
  @Test(dataProvider = "getAdvancedSearchAutocompleteHelperText")
  public void verifyLeadOrganizationAutocompleteHelperText(String path, String ExpectedAutocompleteHelperText) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getLeadOrganizationSection().getOrganizations().setFocus();
      Assert.assertEquals(page.getLeadOrganizationSection().getAutoCompleteHelpText(), ExpectedAutocompleteHelperText, "Lead Organization Section autocomplete helper text does not match");
    });
  }
  /**
   * When user inputs Lead organization and clicks Find Trials button, correct results are
   * displayed. Assertion is performed by verifying that url contains search
   * parameters
   * @param path                         url
   * @param  LeadOrganizationName       the lead organization name
   */

  @Test(dataProvider = "getAdvancedSearchLeadOrganizationAutoSuggestField")
  public void verifyLeadOrganizationAutosuggestField(String path, String LeadOrganizationName) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getLeadOrganizationSection().getOrganizations().selectItem(LeadOrganizationName);
      // create a navigation event redirect to search results page
      SearchNavigationResult searchresult = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      // assert that url contains expected query params
      Assert.assertEquals(searchresult.getLeadorganizationParam(),LeadOrganizationName, "query param does not match for lead Organization");
    });
  }

  /**
   * When user inputs invalid Lead organization and clicks Find Trials button, no search results page is displayed.
   * Assertion is performed by verifying that url contains search parameters and no search result message is displayed
   * parameters
   * @param path                              url
   * @param  InvalidLeadOrganizationName      the invalid lead organization name
   * @param ErrorMsg                          the error message
   */

  @Test(dataProvider = "getAdvancedSearchLeadOrganizationAutoSuggestFieldNegative")
  public void verifyLeadOrganizationNoResultsPage(String path, String InvalidLeadOrganizationName, String ErrorMsg  ) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getLeadOrganizationSection().getOrganizations().enterText(InvalidLeadOrganizationName );
      // create a navigation event redirect to search results page
      SearchNavigationResult searchresult = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      // assert that url contains expected query params
      Assert.assertEquals(searchresult.getLeadorganizationParam(), InvalidLeadOrganizationName , "query param does not match for lead organization");
      Assert.assertEquals(searchresult.getNoResultMsg(),ErrorMsg , "No search result message is not displayed for invalid Lead Organization");
    });
  }

  /**
   * Verify the Lead Organization Section Placeholder Text
   * parameters
   *
   * @param path                        url
   * @param ExpectedPlaceholderText     the helper text under the Lead Organization input box
   */
  @Test(dataProvider = "getAdvancedSearchPlaceholderText")
  public void verifyLeadOrganizationPlaceholderText(String path, String ExpectedPlaceholderText) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      Assert.assertEquals(page.getLeadOrganizationSection().getPlaceHolderText(), ExpectedPlaceholderText, "Lead Organization Section placeholder text does not match");
    });
  }

  /**
   * Verify the Lead organization Section AutoComplete helper text for invalid Lead Organization
   * parameters
   *
   * @param path                           url
   * @param ExpectedAutocompleteHelperText the helper text that appears under the Lead organization input box
   *                                       when the user clicks on the Lead organization input box
   * @param InvalidLeadOrganizationName   the invalid Lead organization name
   */
  @Test(dataProvider = "getAdvancedSearchHelperTextForInvalidLeadOrganization")
  public void verifyNoResultsErrorMessageOnTheLeadOrganizationField(String path, String ExpectedAutocompleteHelperText, String InvalidLeadOrganizationName) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getLeadOrganizationSection().getOrganizations().enterText(InvalidLeadOrganizationName);
      page.getLeadOrganizationSection().getOrganizations().setFocus();
      Assert.assertEquals(page.getLeadOrganizationSection().getAutoCompleteHelpText(), ExpectedAutocompleteHelperText, "Lead organization Section autohelper text for invalid Lead Organization name does not match");
    });
  }

  /************************SUBMIT SEARCH WITH HITTING ENTER KEY***********************/
  /**
   * When user inputs Lead Organization and hits Enter, correct results are
   * displayed. Assertion is performed by verifying that url contains search
   * parameters
   * @param path                         url
   * @param  LeadOrganizationName       the lead organization name
   */
  @Test(dataProvider = "getAdvancedSearchLeadOrganizationAutoSuggestField")
  public void verifyLeadOrganizationAutosuggestFieldWithEnter(String path, String  LeadOrganizationName) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getLeadOrganizationSection().getOrganizations().selectItem( LeadOrganizationName);
      //create a navigation event redirect to search results page
      SearchNavigationResult res = page.hitEnterKey(page.getLeadOrganizationSection().getOrganizations());
      // assert that url contains expected query params
      Assert.assertEquals(res.getLeadorganizationParam(),  LeadOrganizationName, "query param doesnot match for Lead Organization");

    });
  }


/************************************************************
 * Data providers
 ************************************************************/

  /**
   * Data provider retrieves paths to Advanced Search page, Lead Organization
   * params
   */

  @DataProvider(name = "getAdvancedSearchLeadOrganization")
  public Iterator<Object[]> getAdvancedSearchLeadOrganization() {
    String[] columns = {"path"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_lead_organization_data.xlsx"), "leadorganization", columns);
  }


  /**
   * Data provider retrieves paths to Advanced Search page and the Title, Help link and Help Link reference
   * for the Lead Organization Section
   *
   * @return
   */

  @DataProvider(name = "getAdvancedSearchTitleAndHelpLink")
  public Iterator<Object[]> getAdvancedSearchTitleAndHelpLink() {
    String[] columns = {"path", "ExpectedTitle", "ExpectedHelpLinkPath", "ExpectedHelpLinkReference"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_lead_organization_data.xlsx"), "leadorganization", columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page and Helper text
   *
   * @return
   */
  @DataProvider(name = "getAdvancedSearchHelperText")
  public Iterator<Object[]> getAdvancedSearchHelperText() {
    String[] columns = {"path", "ExpectedHelperText"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_lead_organization_data.xlsx"), "leadorganization", columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page and Autocomplete Helper text
   *
   * @return
   */
  @DataProvider(name = "getAdvancedSearchAutocompleteHelperText")
  public Iterator<Object[]> getAdvancedSearchAutocompleteHelperText() {
    String[] columns = {"path", "ExpectedAutocompleteHelperText"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_lead_organization_data.xlsx"), "leadorganization", columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page and Lead Organization name
   * @return
   *
   */
  @DataProvider(name = "getAdvancedSearchLeadOrganizationAutoSuggestField")
  public Iterator<Object[]> getAdvancedSearchLeadOrganizationAutoSuggestField() {
    String[] columns = { "path", "LeadOrganizationName" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_lead_organization_data.xlsx"), "leadorganization", columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page and Invalid Lead Organization name
   * @return
   *
   */
  @DataProvider(name = "getAdvancedSearchLeadOrganizationAutoSuggestFieldNegative")
  public Iterator<Object[]> getAdvancedSearchLeadOrganizationAutoSuggestFieldNegative() {
    String[] columns = { "path", "InvalidLeadOrganizationName","ErrorMsg" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_lead_organization_data.xlsx"), "leadorganization_negative", columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page and Placeholder text
   *@return
   */
  @DataProvider(name = "getAdvancedSearchPlaceholderText")
  public Iterator<Object[]> getAdvancedSearchPlaceholderText() {
    String[] columns = {"path", "ExpectedPlaceholderText"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_lead_organization_data.xlsx"), "leadorganization", columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page and Autocomplete Helper text
   *@return
   */
  @DataProvider(name = "getAdvancedSearchHelperTextForInvalidLeadOrganization")
  public Iterator<Object[]> getAdvancedSearchHelperTextForInvalidLeadOrganization() {
    String[] columns = {"path", "ExpectedAutocompleteHelperText", "InvalidLeadOrganizationName"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_lead_organization_data.xlsx"), "leadorganization_negative", columns);
  }

}

