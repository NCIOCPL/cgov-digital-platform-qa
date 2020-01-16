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
 * This test class contains all tests related to the KeywordsPhrases section on the Advanced Search Form
 */
public class KeywordsPhrases_Test extends TestObjectBase {

  /**
   * Verify that Keywords_Phrases Section is visible
   * parameters
   *
   * @param path url
   */
  @Test(dataProvider = "getAdvancedSearchKeywordsPhrases")
  public void verifyKeywordsPhrasesSectionIsVisible(String path, String KeywordsPhrases) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      Assert.assertTrue(page.getKeywordPhrase().isVisible(), "KeywordsPhrases Section is not visible");
    });
  }

  /**
   * Verify the KeywordsPhrases Section Title, help Link and help link reference
   * parameters
   *
   * @param path                      url
   * @param ExpectedTitle             Title
   * @param ExpectedHelpLinkPath      redirection link
   * @param ExpectedHelpLinkReference help link reference
   */
  @Test(dataProvider = "getAdvancedSearchTitleAndHelpLink")
  public void verifyKeywordsPhrasesSectionTitleAndHelpLink(String path, String ExpectedTitle, String ExpectedHelpLinkPath, String ExpectedHelpLinkReference) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      //verify the title on the page
      Assert.assertEquals(page.getKeywordPhrase().getTitle(), ExpectedTitle, "KeywordsPhrases Section Title does not match");
      //verify help link path
      Assert.assertEquals(page.getKeywordPhrase().getHelpLink().getUrl().getPath(), ExpectedHelpLinkPath, "Help link does not match in the KeywordsPhrases section");
      //verify help link query param
      Assert.assertEquals(page.getKeywordPhrase().getHelpLink().getUrl().getRef(), ExpectedHelpLinkReference, "Link reference to the KeywordsPhrases section does not match");

    });
  }

  /**
   * Verify the KeywordsPhrases Section Helper Text
   * parameters
   *
   * @param path               url
   * @param ExpectedHelperText the helper text
   */
  @Test(dataProvider = "getAdvancedSearchHelperText")
  public void verifyKeywordsPhrasesSectionHelperText(String path, String ExpectedHelperText) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      Assert.assertEquals(page.getKeywordPhrase().getHelpText(), ExpectedHelperText, "KeywordsPhrases Section helper text does not match");
    });
  }

  /**
   * Verify the KeywordsPhrases Section Placeholder Text
   * parameters
   *
   * @param path                    url
   * @param ExpectedPlaceholderText the helper text
   */
  @Test(dataProvider = "getAdvancedSearchPlaceholderText")
  public void verifyKeywordsPhrasesSectionPlaceholderText(String path, String ExpectedPlaceholderText) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      Assert.assertEquals(page.getKeywordPhrase().getPlaceHolderText(), ExpectedPlaceholderText, "KeywordsPhrases Section placeholder text does not match");
    });
  }

  /**
   * When user inputs Keywords or Phrases and clicks Find Trials button, correct results are
   * displayed. Assertion is performed by verifying that url contains search
   * parameters
   *
   * @param path            url
   * @param KeywordsPhrases the keyword or phrase
   */

  @Test(dataProvider = "getAdvancedSearchKeywordsPhrases")
  public void verifyKeywordsPhrasesTextField(String path, String KeywordsPhrases) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {

      page.getKeywordPhrase().getKeywordPhrase().enterText(KeywordsPhrases);

      // create a navigation event redirect to search results page
      SearchNavigationResult searchresult = page.clickSubmit(page.getFormAction().getFindTrialsButton());

      // assert that url contains expected query params
      Assert.assertEquals(searchresult.getKeyWordParam(), KeywordsPhrases, "query param does not match for Keyword or Phrase");

    });
  }

  /************************SUBMIT SEARCH WITH HITTING ENTER KEY***********************/

  @Test(dataProvider = "getAdvancedSearchKeywordsPhrasesWithEnter")
  public void verifyKeywordsPhrasesTextFieldWithEnter(String path, String KeywordsPhrases) {

    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getKeywordPhrase().getKeywordPhrase().enterText(KeywordsPhrases);
      //create a navigation event redirect to search results page
      SearchNavigationResult res = page.hitEnterKey(page.getKeywordPhrase().getKeywordPhrase());
      // assert that url contains expected query params
      Assert.assertEquals(res.getKeyWordParam(), KeywordsPhrases, "query param does not match for Keyword or Phrase");

    });
  }

  /************************************************************
   * Data providers
   ************************************************************/

  /**
   * Data provider retrieves paths to Advanced Search page, Keywords or Phrases
   * params
   */

  @DataProvider(name = "getAdvancedSearchKeywordsPhrases")
  public Iterator<Object[]> getAdvancedSearchKeywordPhrase() {
    String[] columns = {"path", "KeywordsPhrases"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_keywordsphrases_data.xlsx"), "keywordsphrases", columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page and the Title, Help link and Help Link reference
   * for the KeywordsPhrases Section
   *
   * @return
   */

  @DataProvider(name = "getAdvancedSearchTitleAndHelpLink")
  public Iterator<Object[]> getAdvancedSearchTitleAndHelpLink() {
    String[] columns = {"path", "ExpectedTitle", "ExpectedHelpLinkPath", "ExpectedHelpLinkReference"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_keywordsphrases_data.xlsx"), "keywordsphrases", columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page and the Helper Text for the KeywordsPhrases Section
   *
   * @return
   */

  @DataProvider(name = "getAdvancedSearchHelperText")
  public Iterator<Object[]> getAdvancedSearchHelperText() {
    String[] columns = {"path", "ExpectedHelperText"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_keywordsphrases_data.xlsx"), "keywordsphrases", columns);

  }

  /**
   * Data provider retrieves paths to Advanced Search page, Keywords or Phrases
   * params
   */

  @DataProvider(name = "getAdvancedSearchKeywordsPhrasesWithEnter")
  public Iterator<Object[]> getAdvancedSearchKeywordsPhrasesWithEnter() {
    String[] columns = {"path", "KeywordsPhrases"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_keywordsphrases_data.xlsx"), "keywordsphrases", columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page and the Placeholder Text for the KeywordsPhrases Section
   *
   * @return
   */

  @DataProvider(name = "getAdvancedSearchPlaceholderText")
  public Iterator<Object[]> getAdvancedSearchPlaceholderText() {
    String[] columns = {"path", "ExpectedPlaceholderText"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_keywordsphrases_data.xlsx"), "keywordsphrases", columns);

  }
}
