package gov.cancer.tests.cts;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.cts.BasicSearchPage;
import gov.cancer.pageobject.cts.SearchNavigationResult;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This test class contains all tests related to Basic Search functionality. All tests involved performing
 * certain actions that are mimicking User's behavior
 */
public class CTS_BasicSearchFunctionality_Test extends TestObjectBase {


  /**
   * When user inputs Cancer Type and clicks Find Trials button, correct results are displayed.
   * Assertion is performed by verifying that url contains search parameters
   *
   * @param path       url
   * @param CancerType value for Cancer Type
   * @param Results    The url parameters
   */
  @Test(dataProvider = "getBasicSearchCancerTypeKeyword")
  public void verifyBasicSearchCancerTypeKeyword(String path, String CancerType, String Results) {

    TestRunner.run(BasicSearchPage.class, path, (BasicSearchPage page) -> {
      page.getSelectExactCancerType().selectItem(CancerType);
      // create a navigation event redirect to search results page
      SearchNavigationResult res = page.clickSubmit(page.getSearchButton());
      //assert that url contains expected query params
      Assert.assertEquals(res.getCancerTypeParam(), Results, "query param doesnt match for cancer type");

    });

  }

  /**
   * When user inputs keyword in the Cancer Type field and no matching results are found, correct message "No available options found.  Your search will be based on the text above." is displayed.
   * Assertion is performed by verifying the message
   *
   * @param path       url
   * @param CancerType value for Cancer Type
   * @param Message    The message for no available options
   */
  @Test(dataProvider = "getBasicSearchCancerTypeKeywordAuto")
  public void verifyBasicSearchCancerTypeKeywordAutoSuggest(String path, String CancerType, String Message) {

    TestRunner.run(BasicSearchPage.class, path, (BasicSearchPage page) -> {
      page.getKeyword().enterText(CancerType);
      //assert that message displayed is correct
      Assert.assertEquals(page.getCancerTypeKeywordErrorMessage(), Message, "Correct message is not displayed for No available options");
    });

  }

  /**
   * When user inputs Age and clicks Find Trials button, correct results are displayed.
   * Assertion is performed by verifying that url contains search parameters
   *
   * @param path url
   * @param Age  age value
   */
  @Test(dataProvider = "getBasicSearchAge")
  public void verifyBasicSearchAge(String path, String Age) {

    TestRunner.run(BasicSearchPage.class, path, (BasicSearchPage page) -> {
      page.getAge().enterText(Age);
      // create a navigation event redirect to search results page
      SearchNavigationResult res = page.clickSubmit(page.getSearchButton());
      //assert that url contains expected query params
      Assert.assertEquals(res.getAgeParam(), Age, "query param doesnt match for age");

    });
  }

  /**
   * When user inputs Zip Code and clicks Find Trials button, correct results are displayed.
   * Assertion is performed by verifying that url contains search parameters
   *
   * @param path url
   * @param Zip  zip code value
   */
  @Test(dataProvider = "getBasicSearchZip")
  public void verifyBasicSearchZipCode(String path, String Zip) {

    TestRunner.run(BasicSearchPage.class, path, (BasicSearchPage page) -> {
      page.getZip().enterText(Zip);
      // create a navigation event redirect to search results page
      SearchNavigationResult res = page.clickSubmit(page.getSearchButton());
      //assert that url contains expected query params
      Assert.assertEquals(res.getZipParam(), Zip, "query param doesnt match for zipcode");

    });
  }

  /**
   * When user inputs Zip Code, Age, Cancer Type keyword and clicks Find Trials button, correct results are displayed.
   * Assertion is performed by verifying that url contains search parameters
   *
   * @param path    url
   * @param Zip     zip code value
   * @param Results url params
   * @param CancerTypeKeyword     cancer type keyword
   * @param Results url params for CancerTypeKeyword
   */
  @Test(dataProvider = "getBasicSearchCancerTypeKeywordAgeZip")
  public void verifyBasicSearchCancerTypeKeywordAgeZipCode(String path, String CancerTypeKeyword, String Zip, String Age, String Results) {

    TestRunner.run(BasicSearchPage.class, path, (BasicSearchPage page) -> {
      page.getZip().enterText(Zip);
      page.getAge().enterText(Age);
      page.getSelectExactCancerType().selectItem(CancerTypeKeyword);
      // create a navigation event redirect to search results page
      SearchNavigationResult res = page.clickSubmit(page.getSearchButton());
      //assert that url contains expected query params
      Assert.assertEquals(res.getCancerTypeParam(), Results, "query param doesnt match for cancer type");
      Assert.assertEquals(res.getAgeParam(), Age, "query param doesnt match for age");
      Assert.assertEquals(res.getZipParam(), Zip, "query param doesnt match for zipcode");
    });
  }

  /**
   * When user inputs Zip Code, Cancer Type keyword and clicks Find Trials button, correct results are displayed.
   * Assertion is performed by verifying that url contains search parameters
   *
   * @param path       url
   * @param CancerType Cancer Type keyword
   * @param Zip        zipcode
   * @param Results    zipcode and CancerTypeKeyword url params
   */
  @Test(dataProvider = "getBasicSearchCancerTypeKeywordZip")
  public void verifyBasicSearchCancerTypeKeywordZipCode(String path, String CancerType, String Zip, String Results) {

    TestRunner.run(BasicSearchPage.class, path, (BasicSearchPage page) -> {
      page.getZip().enterText(Zip);
      page.getSelectExactCancerType().selectItem(CancerType);
      // create a navigation event redirect to search results page
      SearchNavigationResult res = page.clickSubmit(page.getSearchButton());
      //assert that url contains expected query params
      Assert.assertEquals(res.getCancerTypeParam(), Results, "query param doesnt match for cancer type");
      Assert.assertEquals(res.getZipParam(), Zip, "query param doesnt match for zipcode");
    });

  }

  /**
   * When user inputs Age, Cancer Type keyword and clicks Find Trials button, correct results are displayed.
   * Assertion is performed by verifying that url contains search parameters
   *
   * @param path       url
   * @param CancerType Cancer Type Keyword
   * @param Age        age value
   * @param ResultsCancerTypeKeyword Cancer Type url param
   */
  @Test(dataProvider = "getBasicSearchCancerTypeKeywordAge")
  public void verifyBasicSearchCancerTypeKeywordAge(String path, String CancerType, String Age, String ResultsCancerTypeKeyword) {

    TestRunner.run(BasicSearchPage.class, path, (BasicSearchPage page) -> {

      page.getAge().enterText(Age);
      page.getSelectExactCancerType().selectItem(CancerType);
      // create a navigation event redirect to search results page
      SearchNavigationResult res = page.clickSubmit(page.getSearchButton());
      //assert that url contains expected query params
      Assert.assertEquals(res.getCancerTypeParam(), ResultsCancerTypeKeyword, "query param doesnt match for cancer type");
      Assert.assertEquals(res.getAgeParam(), Age, "query param doesnt match for age");
    });
  }

  /**
   * When user inputs Zip Code, Age, and clicks Find Trials button, correct results are displayed.
   * Assertion is performed by verifying that url contains search parameters
   *
   * @param path url
   * @param Zip  zip code value
   * @param Age  age value
   */
  @Test(dataProvider = "getBasicSearchAgeZip")
  public void verifyBasicSearchAgeZipCode(String path, String Zip, String Age) {

    TestRunner.run(BasicSearchPage.class, path, (BasicSearchPage page) -> {
      page.getAge().enterText(Age);
      page.getZip().enterText(Zip);
      // create a navigation event redirect to search results page
      SearchNavigationResult res = page.clickSubmit(page.getSearchButton());
      //assert that url contains expected query params
      Assert.assertEquals(res.getAgeParam(), Age, "query param doesnt match for age");
      Assert.assertEquals(res.getZipParam(), Zip, "query param doesnt match for zipcode");
    });
  }

  /**
   * Verify that when user does not any search criteria and clicks Find Trials button, all results are displayed.
   *
   * @param path url
   */
  @Test(dataProvider = "getBasicSearchNoCriteria")
  public void verifyBasicSearchAllResults(String path, String results) {

    TestRunner.run(BasicSearchPage.class, path, (BasicSearchPage page) -> {
      // create a navigation event redirect to search results page
      SearchNavigationResult res = page.clickSubmit(page.getSearchButton());
      //Assert that url contains all search params
      Assert.assertEquals(res.getParameterValue("rl"), results, "results are not displayed");
    });
  }

  /**
   * Verify that when user inputs Age which is < 1 or > 120 or contains letters,
   * the error message is displayed in the age field.
   *
   * @param path           url
   * @param Age            age value
   * @param ZipCodeInvalid invalid zipcode numbers and letters
   * @param ResultsAge     error message for invalid age
   * @param ResultsZip     error message for invalid zipcode format (letters)
   */
  @Test(dataProvider = "getBasicSearchInvalidEntries")
  public void verifyBasicSearchInvalidAge(String path, String Age, String ZipCodeInvalid,
                                          String ResultsAge, String ResultsZip) {

    TestRunner.run(BasicSearchPage.class, path, (BasicSearchPage page) -> {
      page.getAge().enterText(Age);
      page.getAge().setFocusAway();
      //assert that error message is displayed on the age field
      Assert.assertTrue(page.getErrorDisplayOnAge().isVisible(), "error message is not displayed on age field");
      String res = page.getErrorDisplayOnAge().getMessage();
      //Assert error message matches
      Assert.assertEquals(res, ResultsAge, "error message doesn't match ");

    });
  }


  /**
   * Verify that when user inputs invalid format of Zipcode (contain letters),
   * the error message is displayed on the zipcode field
   *
   * @param path           url
   * @param Age            age value
   * @param ZipCodeInvalid invalid zipcode numbers and letters
   * @param ResultsAge     error message for invalid age
   * @param ResultsZip     error message for invalid zipcode format (letters)
   */
  @Test(dataProvider = "getBasicSearchInvalidEntries")
  public void verifyBasicSearchInvalidZipAlert(String path, String Age, String ZipCodeInvalid,
                                               String ResultsAge, String ResultsZip) {

    TestRunner.run(BasicSearchPage.class, path, (BasicSearchPage page) -> {
      page.getZip().enterText(ZipCodeInvalid);
      page.getZip().setFocusAway();
      //assert that error message is displayed on the age field
      Assert.assertTrue(page.getErrorDisplayOnZip().isVisible(), "error message is not displayed on zipcode field");
      String res = page.getErrorDisplayOnZip().getMessage();
      //Assert error message matches
      Assert.assertEquals(res, ResultsZip, "error message text doesn't match");

    });
  }

  /************************SUBMIT SEARCH WITH HITTING ENTER KEY***********************/

  @Test(dataProvider = "getBasicCancerTypeKeyword")
  public void verifyBasicSearchCancerKeyWordWithEnter(String path, String Keyword) {

    TestRunner.run(BasicSearchPage.class, path, (BasicSearchPage page) -> {
      page.getKeyword().enterText(Keyword);
      //create a navigation event redirect to search results page
      SearchNavigationResult res = page.hitEnterKey(page.getKeyword());
      // assert that url contains expected query params
      Assert.assertEquals(res.getKeyWordParam(), Keyword, "query param doesnt match for age");

    });
  }

  @Test(dataProvider = "getBasicSearchAge")
  public void verifyBasicSearchAgeWithEnter(String path, String Age) {

    TestRunner.run(BasicSearchPage.class, path, (BasicSearchPage page) -> {
      page.getAge().enterText(Age);
      page.getAge().setFocus();
      // create a navigation event redirect to search results page
      SearchNavigationResult res = page.hitEnterKey(page.getAge());
      //assert that url contains expected query params
      Assert.assertEquals(res.getAgeParam(), Age, "query param doesnt match for age");

    });
  }

  /**
   * When user inputs Zip Code and clicks Find Trials button, correct results are displayed.
   * Assertion is performed by verifying that url contains search parameters
   *
   * @param path url
   * @param Zip  zip code value
   */
  @Test(dataProvider = "getBasicSearchZip")
  public void verifyBasicSearchZipCodeWithEnter(String path, String Zip) {

    TestRunner.run(BasicSearchPage.class, path, (BasicSearchPage page) -> {
      page.getZip().enterText(Zip);
      // create a navigation event redirect to search results page
      SearchNavigationResult res = page.hitEnterKey(page.getZip());
      //assert that url contains expected query params
      Assert.assertEquals(res.getZipParam(), Zip, "query param doesnt match for zipcode");

    });
  }

  /**
   * Verify the CancerTypeKeyword Section Placeholder Text
   * parameters
   *
   * @param path                    url
   * @param ExpectedPlaceholderText placeholder text
   */
  @Test(dataProvider = "getCancerTypeKeywordPlaceholderText")
  public void verifyCancerTypeKeywordSectionPlaceholderText(String path, String ExpectedPlaceholderText) {
    TestRunner.run(BasicSearchPage.class, path, (BasicSearchPage page) -> {
      Assert.assertEquals(page.getKeyword().getPlaceholderText(), ExpectedPlaceholderText, "CancerType Keywords Section placeholder text does not match");
    });
  }

  /**
   * Verify getHelp delighter link, title and icon
   *
   * @param path
   * @param title - title of delighter
   * @param link  - redirection link
   */
  @Test(dataProvider = "getHelpDelighter")
  public void verifyHelpDelighter(String path, String title, String link) {
    TestRunner.run(BasicSearchPage.class, path, (BasicSearchPage page) -> {
      //verify that delighter container is present on page
      Assert.assertTrue(page.getHelpDelighter().isVisible());
      //verify that icon is displayed
      Assert.assertTrue(page.getHelpDelighter().isIconDisplayed());
      //verify link
      Assert.assertEquals(page.getHelpDelighter().getLink().getUrl().getPath(), link);
      //verify title
      Assert.assertEquals(page.getHelpDelighter().getLinkTitle(), title);
    });
  }

  /**
   * Verify 'What Are Clinical Trials' delighter link, title and icon
   *
   * @param path
   * @param title - title of delighter
   * @param link  - redirection link
   */
  @Test(dataProvider = "getWhatAreDelighter")
  public void verifyWhatAreTrialsDelighter(String path, String title, String link) {
    TestRunner.run(BasicSearchPage.class, path, (BasicSearchPage page) -> {
      //verify that delighter container is present on page
      Assert.assertTrue(page.getWhatAreClinicalTrialsDelighter().isVisible());
      //verify that icon is displayed
      Assert.assertTrue(page.getWhatAreClinicalTrialsDelighter().isIconDisplayed());
      //verify link
      Assert.assertEquals(page.getWhatAreClinicalTrialsDelighter().getLink().getUrl().getPath(), link);
      //verify title
      Assert.assertEquals(page.getWhatAreClinicalTrialsDelighter().getLinkTitle(), title);
    });
  }

  /**
   * Verify 'What Are Clinical Trials' delighter link, title and icon
   *
   * @param path
   * @param title - title of delighter
   * @param link  - redirection link
   */
  @Test(dataProvider = "getWhichIsRightDelighter")
  public void verifyWhichTrialIsRightDelighter(String path, String title, String link) {
    TestRunner.run(BasicSearchPage.class, path, (BasicSearchPage page) -> {
      //verify that delighter container is present on page
      Assert.assertTrue(page.getTrialCheckListDelighter().isVisible());
      //verify that icon is displayed
      Assert.assertTrue(page.getTrialCheckListDelighter().isIconDisplayed());
      //verify link
      Assert.assertEquals(page.getTrialCheckListDelighter().getLink().getUrl().getPath(), link);
      //verify title
      Assert.assertEquals(page.getTrialCheckListDelighter().getLinkTitle(), title);
    });
  }

  /************************************************************
   * Data providers
   ************************************************************/

  /**
   * Data provider retrieves paths to Basic Search page, Cancer Type Keyword and url params
   *
   * @return
   */
  @DataProvider(name = "getBasicSearchCancerTypeKeyword")
  public Iterator<Object[]> getBasicSearchCancerTypeKeyword() {
    String[] columns = {"path", "CancerType", "ResultsCTK"};
    return new ExcelDataReader(getDataFilePath("cts_basic_search_functionality-data.xlsx"), "basic_search", columns);
  }

  /**
   * Data provider retrieves paths to Basic Search page, Cancer Type Keyword and message
   *
   * @return
   */
  @DataProvider(name = "getBasicSearchCancerTypeKeywordAuto")
  public Iterator<Object[]> getBasicSearchCancerTypeKeywordAuto() {
    String[] columns = {"path", "CancerType", "Message"};
    return new ExcelDataReader(getDataFilePath("cts_basic_search_functionality-data.xlsx"), "basic_search_negative", columns);
  }

  /**
   * Data provider retrieves paths to Basic Search page and the Placeholder Text for the CancerTypeKeyword Section
   *
   * @return
   */

  @DataProvider(name = "getCancerTypeKeywordPlaceholderText")
  public Iterator<Object[]> getCancerTypeKeywordPlaceholderText() {
    String[] columns = {"path", "ExpectedPlaceholderText"};
    return new ExcelDataReader(getDataFilePath("cts_basic_search_functionality-data.xlsx"), "basic_search", columns);

  }

  /**
   * Data provider retrieves paths to Basic Search page, Age value and url params
   *
   * @return
   */
  @DataProvider(name = "getBasicSearchAge")
  public Iterator<Object[]> getBasicSearchAge() {
    String[] columns = {"path", "Age"};
    return new ExcelDataReader(getDataFilePath("cts_basic_search_functionality-data.xlsx"), "basic_search", columns);
  }

  /**
   * Data provider retrieves paths to Basic Search page, ZipCode value and url params
   *
   * @return
   */
  @DataProvider(name = "getBasicSearchZip")
  public Iterator<Object[]> getBasicSearchZip() {
    String[] columns = {"path", "Zip"};
    return new ExcelDataReader(getDataFilePath("cts_basic_search_functionality-data.xlsx"), "basic_search", columns);
  }

  /**
   * Data provider retrieves paths to Basic Search page, Cancer Type Keyword, Age, zipcode and url params
   *
   * @return
   */
  @DataProvider(name = "getBasicSearchCancerTypeKeywordAgeZip")
  public Iterator<Object[]> getBasicSearchCancerTypeKeywordAgeZip() {
    String[] columns = {"path", "CancerType", "Zip", "Age", "ResultsCTK"};
    return new ExcelDataReader(getDataFilePath("cts_basic_search_functionality-data.xlsx"), "basic_search", columns);
  }

  /**
   * Data provider retrieves paths to Basic Search page, Cancer Type Keyword, zipcode and url params
   *
   * @return
   */
  @DataProvider(name = "getBasicSearchCancerTypeKeywordZip")
  public Iterator<Object[]> getBasicSearchCancerTypeKeywordZip() {
    String[] columns = {"path", "CancerType", "Zip", "ResultsCTK"};
    return new ExcelDataReader(getDataFilePath("cts_basic_search_functionality-data.xlsx"), "basic_search", columns);
  }

  /**
   * Data provider retrieves paths to Basic Search page, Cancer Type Keyword, Age and url params
   *
   * @return
   */
  @DataProvider(name = "getBasicSearchCancerTypeKeywordAge")
  public Iterator<Object[]> getBasicSearchCancerTypeKeywordAge() {
    String[] columns = {"path", "CancerType", "Age", "ResultsCTK"};
    return new ExcelDataReader(getDataFilePath("cts_basic_search_functionality-data.xlsx"), "basic_search", columns);
  }

  /**
   * Data provider retrieves paths to Basic Search page, Age, zipcode and url params
   *
   * @return
   */
  @DataProvider(name = "getBasicSearchAgeZip")
  public Iterator<Object[]> getBasicSearchAgeZip() {
    String[] columns = {"path", "Zip", "Age"};
    return new ExcelDataReader(getDataFilePath("cts_basic_search_functionality-data.xlsx"), "basic_search", columns);
  }

  /**
   * Data provider retrieves paths to Basic Search page,  invalid values for age and zipcode
   * and error messages
   *
   * @return
   */
  @DataProvider(name = "getBasicSearchInvalidEntries")
  public Iterator<Object[]> getBasicSearchInvalidEntries() {
    String[] columns = {"path", "Age", "ZipCodeInvalid", "ResultsAge", "ResultsZip"};
    return new ExcelDataReader(getDataFilePath("cts_basic_search_functionality-data.xlsx"), "basic_search_negative", columns);
  }

  /**
   * Data provider retrieves paths to Basic Search page, results query param and its value
   * and error messages
   *
   * @return
   */
  @DataProvider(name = "getBasicSearchNoCriteria")
  public Iterator<Object[]> getBasicSearchNoCriteria() {
    String[] columns = {"path", "Results"};
    List<Object[]> converted = new ArrayList<Object[]>();
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("cts_basic_search_functionality-data.xlsx"), "basic_search", columns);
    while (values.hasNext()) {
      Object[] item = values.next();
      String keyword = (String) item[1];
      if (!keyword.equals("null"))
        converted.add(new Object[]{item[0], keyword,});
    }
    return converted.iterator();
  }

  /**
   * Data provider retrieves paths, cancer type keyword without autosuggest match
   *
   * @return
   */
  @DataProvider(name = "getBasicCancerTypeKeyword")
  public Iterator<Object[]> getBasicCancerTypeKeyword() {
    String[] columns = {"path", "CancerKeyWords"};
    List<Object[]> converted = new ArrayList<Object[]>();
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("cts_basic_search_functionality-data.xlsx"), "basic_search", columns);
    while (values.hasNext()) {
      Object[] item = values.next();
      String keyword = (String) item[1];
      if (!keyword.equals("null"))
        converted.add(new Object[]{item[0], keyword});
    }
    return converted.iterator();
  }

  /**
   * Data Providers for delighters - each return path to Basic Search Form page, title and link for different delighter
   */

  @DataProvider(name = "getHelpDelighter")
  public Iterator<Object[]> getHelpDelighter() {
    String[] columns = {"path", "getHelpTitle", "getHelp"};
    return new ExcelDataReader(getDataFilePath("cts_basic_search_functionality-data.xlsx"), "delighters", columns);
  }

  @DataProvider(name = "getWhatAreDelighter")
  public Iterator<Object[]> getWhatAreDelighter() {
    String[] columns = {"path", "whatAreTrialsTitle", "whatAreTrials"};
    return new ExcelDataReader(getDataFilePath("cts_basic_search_functionality-data.xlsx"), "delighters", columns);
  }

  @DataProvider(name = "getWhichIsRightDelighter")
  public Iterator<Object[]> getWhichIsRightDelighter() {
    String[] columns = {"path", "whichTrialsAreRightTitle", "whichTrialsAreRight"};
    return new ExcelDataReader(getDataFilePath("cts_basic_search_functionality-data.xlsx"), "delighters", columns);
  }
}

