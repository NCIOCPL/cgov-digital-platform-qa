package gov.cancer.tests.cts;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.framework.ParsedURL;
import gov.cancer.pageobject.cts.SearchNavigationResult;
import gov.cancer.pageobject.cts.SearchResultsPage;
import gov.cancer.pageobject.cts.components.ResultItem;
import gov.cancer.pageobject.helper.Link;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;
import org.apache.http.NameValuePair;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Search_Results_Test extends TestObjectBase {

  /**
    * Verify header of the page
   *
     * @param path
   */
  @Test(dataProvider = "getHeader")
  public void verifyHeaderOfPage(String path, String header) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      Assert.assertEquals(page.getPageHeader(), header, "Page Header is incorrect");
    });
  }

  /**
   * Due to clinical trials content being dynamic, the test is limited to verifying length of text
   * 30 characters being the shortest (for only 1 result retrieved)
   * Verify the length of Search Results subtitle field
   */
  @Test(dataProvider = "getSearchResultsItemPages")
  public void verifyResultsSubTitle(String path, int min, int max) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      //verify length of sub header text
      Assert.assertTrue(page.getSubHeader().length() >= min && page.getSubHeader().length() <= max);

    });
  }

  /**
   * Verify that the results section is present
   *
   * @param path
   */
  @Test(dataProvider = "getSearchResultsPages")
  public void verifyResultListIsPresent(String path) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      //verify is result list section is displayed
      Assert.assertTrue(page.getResultListSection().isVisible(), "List of results is not displayed");
      // verify that at least one result is displayed
      Assert.assertTrue(page.getResultListSection().getAllResultsItems().size() >= 1, "No search results are displayed");

    });
  }

  /**
   * Verify that Modify Search button is present
   *
   * @param path
   * @param links
   */
  @Test(dataProvider = "getModifySearchPaths")
  public void verifyModifySearchIsPresent(String path, String links) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      Assert.assertTrue(page.getModifySearchCriteriaButton().isVisible(), "Modify Search Criteria button is not displayed");

    });
  }

  /**
   * Verify that 'Select All' checkbox presents at the top of the page
   *
   * @param path
   */
  @Test(dataProvider = "getSearchResultsPages")
  public void verifyTopSelectAllOnPagePresent(String path) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      Assert.assertTrue(page.getTopSelectAllOnPageCheckbox().isVisible(), "Top 'Select All' checkbox is not displayed");

    });
  }

  /**
   * Verify that 'Select All' checkbox displays at the bottom of page
   *
   * @param path
   */
  @Test(dataProvider = "getSearchResultsPages")
  public void verifyBottomSelectAllOnPagePresent(String path) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      Assert.assertTrue(page.getBottomSelectAllOnPageCheckbox().isVisible(), "Bottom 'Select all' checkbox is not displayed ");

    });
  }

  /**
   * Verify that 'Print Selected" button is displayed at the top of page
   *
   * @param path
   */
  @Test(dataProvider = "getSearchResultsPages")
  public void verifyTopPrintButtonPresent(String path) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      Assert.assertTrue(page.getTopPrintSelected().isVisible(), "Top print button is not displayed");

    });
  }

  /**
   * Verify that 'Print Selected" button is displayed at the bottom of page
   *
   * @param path
   */
  @Test(dataProvider = "getSearchResultsPages")
  public void verifyBottomPrintButtonPresent(String path) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      Assert.assertTrue(page.getBottomPrintSelected().isVisible(), "Bottom print button is not displayed");

    });
  }

  /**
   * Verify top Pager:
   * 1) Is visible Next Button is displayed and Previous Button is not rendered
   * 2) Get to the last page number available and verify redirection is correct, Next button disappeared, Previous
   * button displayed
   *
   * @param path
   * @param pageParam - query param for page number
   */
  @Test(dataProvider = "getSearchResultsPageNumbers")
  public void verifyTopPagerLastPage(String path, String pageParam) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      //verify pager is present at the top
      Assert.assertTrue(page.getTopPager().isVisible(), "Top pager is not displayed");
      // verify Next> button is present
      Assert.assertTrue(page.getTopPager().getNextButton().isVisible(), "'Next' button is not visible");
      // verify Previous< button does not exist on the page
      Assert.assertNull(page.getTopPager().getPreviousButton(), "Previous< button is displayed");
      //get the last page available at the moment
      int indexOfLastPage = page.getTopPager().getNumberOfPages() - 2;
      //click on last page number
      SearchNavigationResult searchNavigationResult = page.clickSubmit(page.getTopPager().getPageButton(indexOfLastPage));
      // verify that user is redirected to the chosen page number
      Assert.assertEquals(searchNavigationResult.getParameterValue(pageParam), page.getTopPager().getCurrentButton().getText(), "current url parameter doesn't match with current page number");
      //verify Previous< button is visible on the page
      Assert.assertTrue(page.getTopPager().getPreviousButton().isVisible(), "Previous< button is not visible ");
      // verify Next> button does not exist on the page
      Assert.assertNull(page.getTopPager().getNextButton(), "Next> button is displayed");
    });
  }

  /**
   * Verify that by clicking on the next arrow, page is redirected to the next page
   * Previous button is now visible and will redirect to the previous page
   *
   * @param pageParam - query param for page number
   * @param path
   */
  @Test(dataProvider = "getSearchResultsPageNumbers")
  public void verifyTopPagerNextAndPrevious(String path, String pageParam) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      //verify pager is present at the top
      Assert.assertTrue(page.getTopPager().isVisible(), "Top pager is not displayed");
      //click on the 'Next>' pager arrow
      SearchNavigationResult searchNavigationResult = page.clickSubmit(page.getTopPager().getNextButton());
      // verify that user is redirected to the next page
      Assert.assertEquals(searchNavigationResult.getParameterValue(pageParam), page.getTopPager().getCurrentButton().getText(), "current url parameter doesn't match with current page number");
      //click on the 'Previous>' pager arrow
      searchNavigationResult = page.clickSubmit(page.getTopPager().getPreviousButton());
      // verify that user is redirected to the previous page
      Assert.assertEquals(searchNavigationResult.getParameterValue(pageParam), page.getTopPager().getCurrentButton().getText(), "current url parameter doesn't match with current page number");

    });
  }

  /**
   * Verify bottom Pager:
   * 1) Is visible Next Button is displayed and Previous Button is not rendered
   * 2) Get to the last page number available and verify redirection is correct, Next button disappeared, Previous
   * button displayed
   *
   * @param path
   * @param pageParam - query param for page number
   */
  @Test(dataProvider = "getSearchResultsPageNumbers")
  public void verifyBottomPagerLastPage(String path, String pageParam) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      //verify pager is present at the bottom
      Assert.assertTrue(page.getBottomPager().isVisible(), "Bottom pager is not displayed");
      // verify Next> button is present
      Assert.assertTrue(page.getBottomPager().getNextButton().isVisible(), "'Next' button is not visible");
      // verify Previous< button does not exist on the page
      Assert.assertNull(page.getBottomPager().getPreviousButton(), "Previous< button is displayed");
      //get the last page available at the moment
      int indexOfLastPage = page.getBottomPager().getNumberOfPages() - 2;
      //click on last page number
      SearchNavigationResult searchNavigationResult = page.clickSubmit(page.getBottomPager().getPageButton(indexOfLastPage));
      // verify that user is redirected to the chosen page number
      Assert.assertEquals(searchNavigationResult.getParameterValue(pageParam), page.getBottomPager().getCurrentButton().getText(), "current url parameter doesn't match with current page number");
      //verify Previous< button is visible on the page
      Assert.assertTrue(page.getBottomPager().getPreviousButton().isVisible(), "Previous< button is not visible ");
      // verify Next> button does not exist on the page
      Assert.assertNull(page.getBottomPager().getNextButton(), "Next> button is displayed");
    });
  }

  /**
   * Verify that by clicking on the next arrow, page is redirected to the next page
   * Previous button is now visible and will redirect to the previous page
   *
   * @param path
   * @param pageParam - query param for page number
   */
  @Test(dataProvider = "getSearchResultsPageNumbers")
  public void verifyBottomPagerNextAndPrevious(String path, String pageParam) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      //verify pager is present at the bottom
      Assert.assertTrue(page.getBottomPager().isVisible(), "Bottom pager is not displayed");
      //click on the 'Next>' pager arrow
      SearchNavigationResult searchNavigationResult = page.clickSubmit(page.getBottomPager().getNextButton());
      // verify that user is redirected to the next page
      Assert.assertEquals(searchNavigationResult.getParameterValue(pageParam), page.getBottomPager().getCurrentButton().getText(), "current url parameter doesn't match with current page number");
      //click on the 'Previous>' pager arrow
      searchNavigationResult = page.clickSubmit(page.getBottomPager().getPreviousButton());
      // verify that user is redirected to the previous page
      Assert.assertEquals(searchNavigationResult.getParameterValue(pageParam), page.getBottomPager().getCurrentButton().getText(), "current url parameter doesn't match with current page number");

    });
  }

  /**
   * Verify that Search Criteria table is displayed
   *
   * @param path
   */
  @Test(dataProvider = "getModifySearchPaths")
  public void verifySearchCriteriaIsPresent(String path, String searchLink) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      Assert.assertTrue(page.getCriteriaSection().isVisible(), "Search Criteria is not displayed");
      //Verify that Search Criteria is not expanded by default
      Assert.assertFalse(page.getCriteriaSection().isExpanded(), "search criteria is expanded");

    });
  }

  /**
   * Test verifies that Search Criteria drop down table contains all labels and verifies its expected values
   *
   * @param path
   * @param label
   * @param value
   */
  @Test(dataProvider = "getCriteria")
  public void verifyCriteriaSection(String path, List<String> label, List<String> value) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      // Clicks on show search criteria to expand drop down table
      page.getCriteriaSection().expand();
      //verify that number of categories in search criteria table matches
      Assert.assertEquals(page.getCriteriaSection().getNumberOfEntriesInTheMap(), label.size());
      for (int i = 0; i < label.size(); i++) {
        // Verifies that label matches
        Assert.assertTrue(page.getCriteriaSection().isCategoryPresent(label.get(i)), "label is not present in the criteria table");
        // Verifies the value of each label
        Assert.assertEquals(page.getCriteriaSection().getSelectionValue(label.get(i)), value.get(i), "criteria value doesnt match");
      }
    });
  }


  /**
   * Verify that 'Start Over' link is matching expected url
   *
   * @param path
   * @param link
   */
  @Test(dataProvider = "getStartOverPath")
  public void verifyStartOverLink(String path, String link) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      //Verify 'Start Over' link
      Assert.assertEquals(page.getStartOverLink().getUrl().getPath(), link, "Start Over link is incorrect");

    });
  }

  /**
   * Verify that Modify Search button redirects to advanced search page
   *
   * @param path
   * @param link
   */
  @Test(dataProvider = "getModifySearchPaths")
  public void verifyModifySearchButtonRedirection(String path, String link) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      // Trigger navigation even by clicking 'Modify Search Criteria' button
      SearchNavigationResult searchNavigationResult = page.clickSubmit(page.getModifySearchCriteriaButton());
      //Verify url of redirected page
      Assert.assertEquals(searchNavigationResult.getPageURL().getPath(), link, "Modify search redirection is incorrect");

    });
  }

  /**
   * Verify that by selecting 'Select All' checkbox at the top of the page selects all search results checkboxes
   *
   * @param path
   */
  @Test(dataProvider = "getSearchResultsPages")
  public void verifyTopSelectAllCheckBox(String path) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      //Click Select all on top of page
      page.getTopSelectAllOnPageCheckbox().toggle();
      // Verify that the checkbox 'Select All" is selected
      Assert.assertTrue(page.getTopSelectAllOnPageCheckbox().isSelected(), "'Select All' checkbox is not selected");
      // List of all result item on page
      List<ResultItem> allResults = page.getResultListSection().getAllResultsItems();
      //for each result item verify that checkbox is selected
      for (ResultItem ri : allResults) {
        Assert.assertTrue(ri.getCheckBox().isSelected(), "result item checkbox is not selected");
      }
    });
  }

  /**
   * Verify that by selecting 'Select All' checkbox at the bottom of the page selects all search results checkboxes
   *
   * @param path
   */
  @Test(dataProvider = "getSearchResultsPages")
  public void verifyBottomSelectAllCheckBox(String path) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      //Click Select all at the bottom of the page
      page.getBottomSelectAllOnPageCheckbox().toggle();
      // Verify that the checkbox 'Select All" is selected
      Assert.assertTrue(page.getBottomSelectAllOnPageCheckbox().isSelected(), "'Select All' checkbox is not selected");
      // List of all result item on page
      List<ResultItem> allResults = page.getResultListSection().getAllResultsItems();
      //for each result item verify that checkbox is selected
      for (ResultItem ri : allResults) {
        Assert.assertTrue(ri.getCheckBox().isSelected(), "result item checkbox is not selected");
      }
    });
  }

  /**
   * Test loops through all result items checkboxes present on the list, clicks each of them and verify that it is indeed been checked
   *
   * @param path
   */
  @Test(dataProvider = "getSearchResultsPages")
  public void verifyEachResultItemCheckBox(String path) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      List<ResultItem> allResults = page.getResultListSection().getAllResultsItems();
      //for each result item verify that checkbox is selected
      for (ResultItem ri : allResults) {
        //check the checkbox
        ri.getCheckBox().toggle();
        //verify checkbox is selected
        Assert.assertTrue(ri.getCheckBox().isSelected(), "result item checkbox is not selected");
      }
    });
  }

  /**
   * All checkboxes are selected
   * Test loops through all result items checkboxes present on the list, uncheck them and verify it was unchecked
   *
   * @param path
   */
  @Test(dataProvider = "getSearchResultsPages")
  public void verifyEachResultItemCheckBoxUnchecked(String path) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      page.getTopSelectAllOnPageCheckbox().toggle();
      List<ResultItem> allResults = page.getResultListSection().getAllResultsItems();
      //for each result item verify that checkbox is selected
      for (ResultItem ri : allResults) {
        //check the checkbox
        ri.getCheckBox().toggle();
        //verify checkbox is selected
        Assert.assertFalse(ri.getCheckBox().isSelected(), "result item checkbox is checked");
      }
    });
  }

  /**
   * Test loops through all result items  present on the list and checks the categories
   *
   * @param path
   */
  @Test(dataProvider = "getResultCategory")
  public void verifyEachResultItemCategory(String path, List<String> categories, String statusValue) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      List<ResultItem> allResults = page.getResultListSection().getAllResultsItems();
      //for each result item verify the pattern of categories
      for (ResultItem ri : allResults) {
        Assert.assertEquals(statusValue, ri.getCategory(categories.get(0)), "Status category  does not match");
        Assert.assertFalse(ri.getCategory(categories.get(1)).isEmpty(), "Age category is empty");
        Assert.assertFalse(ri.getCategory(categories.get(2)).isEmpty(), "Gender category is empty");
        Assert.assertFalse(ri.getCategory(categories.get(3)).isEmpty(), "Location category is empty");

      }
    });
  }

  /**
   * Below test verifies title link of each result item present on the page.
   * Test is dynamic - it retrieves real time values of query parameters from Page URL and Trial URL;
   * Every Trial title link should match all current URL params + have one NCI id parameter added,
   * therefore test removes all current page query params from Trial title link parameters and it must
   * expose that only NCI id param in the link left
   *
   * @param path
   * @param NCIid
   */
  @Test(dataProvider = "getTrialLinks")
  public void verifyResultItemLinks(String path, String NCIid) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      List<ResultItem> allResults = page.getResultListSection().getAllResultsItems();

      for (ResultItem ri : allResults) {
        //Verify host name
        Assert.assertEquals(ri.getTitleLink().getUrl().getHost(), page.getPageUrl().getHost(), "host name is incorrect");
        //get URL query pairs of trial link
        List<NameValuePair> paramsOfTrials = ParsedURL.getParamArrayList(ri.getTitleLink().getUrl().toString());
        //get URL query pairs of current page url
        List<NameValuePair> paramsOfCurrentUrl = page.getCurrentUlrQueryParameters();
        //subtract all pairs of current url query params from the trial link
        paramsOfTrials.removeAll(paramsOfCurrentUrl);
        //verify that there is only one extra param had been added to Trial Link
        Assert.assertEquals(paramsOfTrials.size(), 1, "Trial link does not have expected number of query params");
        //verify that this param is NCI id
        Assert.assertTrue(paramsOfTrials.get(0).getValue().startsWith(NCIid));
      }
    });
  }

  /**
   * Verify that when no item is selected and top 'Print Button' is clicked, print modal appears with error message
   *
   * @param path
   * @param errorMessage
   */
  @Test(dataProvider = "getPrintModalError")
  public void verifyTopPrintButtonModalErrorAlert(String path, String errorMessage) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      //click on Print Selected button
      page.getTopPrintSelected().click();
      //Verify that modal appeared
      Assert.assertTrue(page.isPrintModalDisplayed(), "Print Modal is not displayed");
      // Verify error message
      Assert.assertEquals(page.getPrintModalText(), errorMessage, "modal error message is incorrect");
    });
  }

  /**
   * Verify that when no item is selected and bottom 'Print Button' is clicked, print modal appears with error message
   *
   * @param path
   * @param errorMessage
   */
  @Test(dataProvider = "getPrintModalError")
  public void verifyBottomPrintButtonNoSelectionModalErrorAlert(String path, String errorMessage) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      //Click 'Print Selected' button at the bottom of the page
      page.getBottomPrintSelected().click();
      //Verify Print Modal is Displayed
      Assert.assertTrue(page.isPrintModalDisplayed(), "Print modal is not displayed");
      //Verify error message
      Assert.assertEquals(page.getPrintModalText(), errorMessage, "modal error message is incorrect");
    });
  }

  /**
   * Test reproduces user's actions - it clicks 'Select All' checkbox, then advances to the next page, checks 'Select All' again until it
   * reaches page #11 and verifies that Print Modal appeared with error message
   *
   * @param path
   * @param errorMessage
   */
  @Test(dataProvider = "getPrintModalTooManyTrialsError")
  public void verifyTooManyTrialsPrintError(String path, String errorMessage) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      for (int i = 1; i <= 10; i++) {
        //Click 'Select All' checkbox
        page.getTopSelectAllOnPageCheckbox().toggle();
        //Click 'Next' page arrow
        page.getTopPager().getNextButton().click();
      }
      //On the 11th page click 'select all' checkbox
      page.getTopSelectAllOnPageCheckbox().toggle();
      //Verify print modal is displayed
      Assert.assertTrue(page.isPrintModalDisplayed(), "Print modal is not displayed");
      //Verify error message
      Assert.assertEquals(page.getPrintModalText(), errorMessage, "Error message is incorrect");
    });
  }

  /**
   * Test verifies Get Help delighter is present and icon is displayed
   *
   * @param path
   */
  @Test(dataProvider = "getSearchResultsPages")
  public void verifyGetHelpDelighterDisplay(String path) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      Assert.assertTrue(page.getHelpDelighter().isVisible(), "delighter container is not visible");
      Assert.assertTrue(page.getHelpDelighter().isIconDisplayed(), "icon is not displayed");
    });
  }

  /**
   * Test verifies Get Help delighter link and title
   *
   * @param path
   */
  @Test(dataProvider = "getHelpDelighter")
  public void verifyGetHelpDelighterLinkAndTitle(String path, String link, String title) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      Assert.assertEquals(page.getHelpDelighter().getLink().getUrl().getPath(), link, "'Help' delighter link is wrong");
      Assert.assertEquals(page.getHelpDelighter().getLinkTitle(), title, "'Help' delighter title is wrong");

    });
  }

  /**
   * Test verifies Trial CheckList  delighter is present and icon is displayed
   *
   * @param path
   */
  @Test(dataProvider = "getSearchResultsPages")
  public void verifyTrialCheckListDelighterDisplay(String path) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      Assert.assertTrue(page.getTrialCheckListDelighter().isVisible(), "delighter container is not visible");
      Assert.assertTrue(page.getTrialCheckListDelighter().isIconDisplayed(), "icon is not displayed");
    });
  }

  /**
   * Test verifies Trial CheckList  delighter link and title
   *
   * @param path
   */
  @Test(dataProvider = "getTrialCheckListDelighter")
  public void verifyTrialCheckListDelighterTitleAndLink(String path, String link, String title) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      Assert.assertEquals(page.getTrialCheckListDelighter().getLink().getUrl().getPath(), link, "'Trial CheckList' delighter link is wrong");
      Assert.assertEquals(page.getTrialCheckListDelighter().getLinkTitle(), title, "'Trial CheckList' delighter title is wrong");
    });
  }


  /************ NO clinical trials found  tests ***************************/


  /**
   * Test verifies Get Help delighter is present and icon is displayed
   *
   * @param path
   */
  @Test(dataProvider = "noResultsHelpDelighter")
  public void verifyNoResultsGetHelpDelighterDisplay(String path, String link, String title) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      Assert.assertTrue(page.getHelpDelighter().isVisible(), "delighter container is not visible");
      Assert.assertTrue(page.getHelpDelighter().isIconDisplayed(), "icon is not displayed");
    });
  }

  /**
   * Test verifies Get Help delighter link and title
   *
   * @param path
   */
  @Test(dataProvider = "noResultsHelpDelighter")
  public void verifyNoresultsGetHelpDelighterLinkAndTitle(String path, String link, String title) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      Assert.assertEquals(page.getHelpDelighter().getLink().getUrl().getPath(), link, "'Help' delighter link is wrong");
      Assert.assertEquals(page.getHelpDelighter().getLinkTitle(), title, "'Help' delighter title is wrong");

    });
  }

  /**
   * Test verifies Trial CheckList  delighter is present and icon is displayed
   *
   * @param path
   */
  @Test(dataProvider = "noResultsTrialCheckListDelighter")
  public void verifyNoResultsTrialCheckListDelighterDisplay(String path, String link, String title) {
    TestRunner.runDesktop(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      Assert.assertTrue(page.getTrialCheckListDelighter().isVisible(), "delighter container is not visible");
      Assert.assertTrue(page.getTrialCheckListDelighter().isIconDisplayed(), "icon is not displayed");
    });
  }

  /**
   * Test verifies Trial CheckList  delighter link and title
   *
   * @param path
   */
  @Test(dataProvider = "noResultsTrialCheckListDelighter")
  public void verifyNoResultsTrialCheckListDelighterTitleAndLink(String path, String link, String title) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      Assert.assertEquals(page.getTrialCheckListDelighter().getLink().getUrl().getPath(), link, "'Trial CheckList' delighter link is wrong");
      Assert.assertEquals(page.getTrialCheckListDelighter().getLinkTitle(), title, "'Trial CheckList' delighter title is wrong");
    });
  }

  /**
   * Test verifies try a new search link
   *
   * @param path
   */
  @Test(dataProvider = "noResultsTryNewSearchLinks")
  public void verifyNoResultsTryNewSearch(String path, String link) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      //verify link is present
      Link newSearch = page.getTryNewSearchLink();
      Assert.assertNotNull(newSearch, "try a new search link is missing");
      //verify link is correct
      Assert.assertEquals(newSearch.getUrl().getPath(), link, "Try a new search link is incorrect");
    });
  }

  /**
   * Test verifies Start Over link
   *
   * @param path
   */
  @Test(dataProvider = "noResultsStartOver")
  public void verifyNoResultsStartOver(String path, String link) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      //verify link is present
      Link startOver = page.getNoResultsStartOver();
      Assert.assertNotNull(startOver, "Start Over link is missing");
      //verify link is correct
      Assert.assertEquals(startOver.getUrl().getPath(), link, "Try a new search link is incorrect");
    });
  }

  /**
   * Test verifies subHeader
   *
   * @param path
   */
  @Test(dataProvider = "noResultsSubHeader")
  public void verifyNoResultsSubHeader(String path, String text) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      //verify subHeader text
      Assert.assertEquals(page.getNoTrialsHeader(), text, "Sub Header does not match");
    });
  }

  /**
   * Test verifies Modify Search Criteria is present
   *
   * @param path
   */
  @Test(dataProvider = "noResultsSubHeader")
  public void verifyNoResultModifySearchCriteria(String path, String text) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      //verify modify search criteria button is visible
      Assert.assertTrue(page.getModifySearchCriteriaButton().isVisible());
    });
  }

  @Test(dataProvider = "noResultsText")
  public void verifyNoResultBlobOfText(String path, int min, int max) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      //verify blobOfText is within reasonable length
      int textSize = page.getNoResultsText().getText().length();
      Assert.assertTrue(textSize >= min && textSize <= max);
    });
  }

  /**
   * Verify that Search Criteria table is displayed
   *
   * @param path
   */
  @Test(dataProvider = "noResultsSubHeader")
  public void verifyNoResultsSearchCriteriaIsPresent(String path, String text) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      Assert.assertTrue(page.getCriteriaSection().isVisible(), "Search Criteria is not displayed");
      //Verify that Search Criteria is not expanded by default
      Assert.assertFalse(page.getCriteriaSection().isExpanded(), "search criteria is expanded");

    });
  }

  /**
   * Test verifies that Search Criteria drop down table contains all labels and verifies its expected values
   *
   * @param path
   * @param label
   * @param value
   */
  @Test(dataProvider = "noResultsSearchCriteria")
  public void verifyNoResultsCriteriaSection(String path, String label, String value) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      // Clicks on show search criteria to expand drop down table
      page.getCriteriaSection().expand();
      // Verifies that label matches
      Assert.assertTrue(page.getCriteriaSection().isCategoryPresent(label), "label is not present in the criteria table");
      // Verifies the value of each label
      Assert.assertEquals(page.getCriteriaSection().getSelectionValue(label), value, "criteria value doesnt match");
    });
  }

  /**
   * Verify that no Search Criteria  displayed when no criteria entered
   */
  @Test(dataProvider = "getNoModifySearchPath")
  public void verifyNoCriteriaSection(String path, String criteria) {
    TestRunner.run(SearchResultsPage.class, path, (SearchResultsPage page) -> {
      //Verify the absence of Search Criteria button
      Assert.assertFalse(page.getModifySearchCriteriaButton().isVisible());
    });
  }


  /***********************************DATA PROVIDERS***********************************/

  /**
   * Data provider retrieves Search Results page paths
   *
   * @return
   */
  @DataProvider(name = "getSearchResultsPages")
  public Iterator<Object[]> getSearchResultsPages() {
    String[] columns = {"path"};
    return new ExcelDataReader(getDataFilePath("cts-search-results-data.xlsx"), "search_results_pages", columns);
  }

  /**
   * Retrives path and Page Number query parameter
   *
   * @return
   */
  @DataProvider(name = "getSearchResultsPageNumbers")
  public Iterator<Object[]> getSearchResultsPageNumbers() {
    String[] columns = {"path", "pageNumber"};
    return new ExcelDataReader(getDataFilePath("cts-search-results-data.xlsx"), "search_results_pages", columns);
  }

  /**
   * Data provider retrieves Search Results page paths, text length boundaries for result item description text
   *
   * @return
   */
  @DataProvider(name = "getSearchResultsItemPages")
  public Iterator<Object[]> getSearchResultsItemPages() {
    String[] columns = {"path"};
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("cts-search-results-data.xlsx"), "search_results_pages", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    int min = 30;
    int max = 70;
    while (values.hasNext()) {
      Object[] item = values.next();
      converted.add(new Object[]{item[0], min, max});
    }
    return converted.iterator();
  }

  /**
   * Data provider retrieves  Search Results page and url of Start Over button
   *
   * @return
   */
  @DataProvider(name = "getStartOverPath")
  public Iterator<Object[]> getStartOverPath() {
    String[] columns = {"path", "startOver"};
    return new ExcelDataReader(getDataFilePath("cts-search-results-data.xlsx"), "search_results_pages", columns);
  }

  /**
   * Data provider retrieves  Search Results page paths, Get Help delighter link and title
   *
   * @return
   */
  @DataProvider(name = "getHelpDelighter")
  public Iterator<Object[]> getHelpDelighter() {
    String[] columns = {"path", "helpDelighterLink", "helpDelighterTitle"};
    return new ExcelDataReader(getDataFilePath("cts-search-results-data.xlsx"), "search_results_pages", columns);
  }

  /**
   * Data provider retrieves  Search Results page paths and header of page
   *
   * @return
   */
  @DataProvider(name = "getHeader")
  public Iterator<Object[]> getHeader() {
    String[] columns = {"path", "header"};
    return new ExcelDataReader(getDataFilePath("cts-search-results-data.xlsx"), "search_results_pages", columns);
  }

  /**
   * Data provider retrieves  Search Results page paths, Trial Checklist link and title
   *
   * @return
   */
  @DataProvider(name = "getTrialCheckListDelighter")
  public Iterator<Object[]> getTrialCheckListDelighter() {
    String[] columns = {"path", "trialChecklistDelighterLink", "trialChecklistDelighterTitle"};
    return new ExcelDataReader(getDataFilePath("cts-search-results-data.xlsx"), "search_results_pages", columns);
  }

  /**
   * Data provider retrieves  Search Results page paths and Print modal error message for no trials selected
   *
   * @return
   */
  @DataProvider(name = "getPrintModalError")
  public Iterator<Object[]> getPrintModalError() {
    String[] columns = {"path", "modalErrorNoTrials"};
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("cts-search-results-data.xlsx"), "search_results_pages", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String raw = (String) item[1];
      if (!raw.equals("null"))
        converted.add(new Object[]{item[0], raw});
    }
    return converted.iterator();
  }

  /**
   * Data provider retrieves  Search Results page and url of Modify Search Criteria button paths
   *
   * @return
   */
  @DataProvider(name = "getModifySearchPaths")
  public Iterator<Object[]> getModifySearchPaths() {
    String[] columns = {"path", "modifySearch"};
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("cts-search-results-data.xlsx"), "search_results_pages", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String raw = (String) item[1];
      if (!raw.equals("null"))
        converted.add(new Object[]{item[0], raw});
    }
    return converted.iterator();
  }

  /**
   * Data provider retrieves  Search Results page with no criteria entered
   *
   * @return
   */
  @DataProvider(name = "getNoModifySearchPath")
  public Iterator<Object[]> getNoModifySearchPath() {
    String[] columns = {"path", "modifySearch"};
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("cts-search-results-data.xlsx"), "search_results_pages", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String raw = (String) item[1];
      if (raw.equals("null"))
        converted.add(new Object[]{item[0], raw});
    }
    return converted.iterator();
  }

  /**
   * Data provider retrieves  Search Results page paths and Print modal error message for too many trials selected
   *
   * @return
   */
  @DataProvider(name = "getPrintModalTooManyTrialsError")
  public Iterator<Object[]> getPrintModalTooManyTrialsError() {
    String[] columns = {"path", "modalErrorHundredTrial"};
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("cts-search-results-data.xlsx"), "search_results_pages", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String raw = (String) item[1];
      if (!raw.equals("null"))
        converted.add(new Object[]{item[0], raw});
    }
    return converted.iterator();
  }

  /**
   * Data provider retrieves  Search Results page paths and Search Criteria labels and values
   *
   * @return
   */
  @DataProvider(name = "getCriteria")
  public Iterator<Object[]> getCriteria() {
    String[] columns = {"path", "criteriaLabel", "criteriaValue"};
    Iterator<Object[]> data = new ExcelDataReader(getDataFilePath("cts-search-results-data.xlsx"), "search_results_pages", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    List<String> labels;
    List<String> values;
    while (data.hasNext()) {
      Object[] item = data.next();
      String rawLabel = (String) item[1];
      String rawValue = (String) item[2];
      if (!rawLabel.equals("null")) {
        labels = Arrays.asList(rawLabel.split(","));
        values = Arrays.asList(rawValue.split(","));
        converted.add(new Object[]{item[0], labels, values});
      }
    }
    return converted.iterator();
  }


  @DataProvider(name = "getResultCategory")
  public Iterator<Object[]> getResultCategory() {
    String[] columns = {"path", "resultCategories"};
    Iterator<Object[]> data = new ExcelDataReader(getDataFilePath("cts-search-results-data.xlsx"), "search_results_pages", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    List<String> categories = new ArrayList<>();
    categories.add("Status");
    categories.add("Age");
    categories.add("Gender");
    categories.add("Location");
    while (data.hasNext()) {
      Object[] item = data.next();
      String rawValue = (String) item[1];
      converted.add(new Object[]{item[0], categories, rawValue});
    }
    return converted.iterator();
  }

  /**
   * Data provider retrieves  Search Results page paths and header of page
   *
   * @return
   */
  @DataProvider(name = "getTrialLinks")
  public Iterator<Object[]> getTrialLinks() {
    String[] columns = {"path", "trialID"};
    return new ExcelDataReader(getDataFilePath("cts-search-results-data.xlsx"), "search_results_pages", columns);
  }


  /********************** NO CLINICAL TRIALS FOUND DATA PROVIDERS *********************/

  /**
   * Data provider retrieves no Search Results page paths, title and link of Try New Search Link
   *
   * @return
   */
  @DataProvider(name = "noResultsTryNewSearchLinks")
  public Iterator<Object[]> noResultsTryNewSearchLinks() {
    String[] columns = {"path", "tryNewSearchLink"};
    return new ExcelDataReader(getDataFilePath("cts-search-results-data.xlsx"), "search_results_negative", columns);
  }

  /**
   * Data provider retrieves no Search Results page paths, search criteria label and value
   *
   * @return
   */
  @DataProvider(name = "noResultsSearchCriteria")
  public Iterator<Object[]> noResultsSearchCriteria() {
    String[] columns = {"path", "searchCriteriaLabel", "searchCriteriaValue"};
    return new ExcelDataReader(getDataFilePath("cts-search-results-data.xlsx"), "search_results_negative", columns);
  }

  /**
   * Data provider retrieves no Search Results page paths, title and link of Start Over Link
   *
   * @return
   */
  @DataProvider(name = "noResultsStartOver")
  public Iterator<Object[]> noResultsStartOver() {
    String[] columns = {"path", "startOver"};
    return new ExcelDataReader(getDataFilePath("cts-search-results-data.xlsx"), "search_results_negative", columns);
  }

  /**
   * Data provider retrieves no Search Results page paths, title and link of Get Help delighter
   *
   * @return
   */
  @DataProvider(name = "noResultsHelpDelighter")
  public Iterator<Object[]> noResultsHelpDelighter() {
    String[] columns = {"path", "helpDelighterLink", "helpDelighterTitle"};
    return new ExcelDataReader(getDataFilePath("cts-search-results-data.xlsx"), "search_results_negative", columns);
  }

  /**
   * Data provider retrieves no Search Results page paths, title and link of Trial CheckList delighter
   *
   * @return
   */
  @DataProvider(name = "noResultsTrialCheckListDelighter")
  public Iterator<Object[]> noResultsTrialCheckListDelighter() {
    String[] columns = {"path", "trialChecklistDelighterLink", "trialChecklistDelighterTitle"};
    return new ExcelDataReader(getDataFilePath("cts-search-results-data.xlsx"), "search_results_negative", columns);
  }

  /**
   * Data provider retrieves no Search Results page paths, title and link of Trial CheckList delighter
   *
   * @return
   */
  @DataProvider(name = "noResultsSubHeader")
  public Iterator<Object[]> noResultsSubHeader() {
    String[] columns = {"path", "subHeader"};
    return new ExcelDataReader(getDataFilePath("cts-search-results-data.xlsx"), "search_results_negative", columns);
  }

  /**
   * Data provider retrieves no Search Results page paths, min and max text length
   *
   * @return
   */
  @DataProvider(name = "noResultsText")
  public Iterator<Object[]> noResultsText() {
    String[] columns = {"path"};
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("cts-search-results-data.xlsx"), "search_results_negative", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    int min = 30;
    int max = 300;
    while (values.hasNext()) {
      Object[] item = values.next();
      converted.add(new Object[]{item[0], min, max});
    }
    return converted.iterator();
  }

}

