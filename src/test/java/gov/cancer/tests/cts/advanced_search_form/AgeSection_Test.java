package gov.cancer.tests.cts.advanced_search_form;

import gov.cancer.pageobject.cts.AdvancedSearchPage;
import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.cts.SearchNavigationResult;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Iterator;

/**
 * This test class contains all tests related to the Age section on the Advanced Search Form
 * 
 *
 */
public class AgeSection_Test extends TestObjectBase {

  /** Verify that Age Section is visible
   * parameters
   * @param path       url
   *
   */
  @Test(dataProvider = "getAdvancedSearchAge")
    public void verifyAgeSectionIsVisible(String path, String Age) {
      TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
        Assert.assertTrue(page.getAge().isVisible(), "Age Section is not visible");
      });
    }

  /** Verify the Age Section Title, help Link and help link query param
   * parameters
   * @param path       url
   * @param ExpectedTitle Title
   * @param ExpectedHelpLinkPath redirection link
   * @param ExpectedHelpLinkReference
   *
   */
  @Test(dataProvider = "getAdvancedSearchTitleAndHelpLink")
  public void verifyAgeSectionTitleAndHelpLink(String path, String ExpectedTitle, String ExpectedHelpLinkPath, String ExpectedHelpLinkReference) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      //verify the title on the page
      Assert.assertEquals(page.getAge().getTitle(), ExpectedTitle, "Age Section Title does not match");
      //verify help link path
      Assert.assertEquals(page.getAge().getHelpLink().getUrl().getPath(),ExpectedHelpLinkPath,"Help link does not match in the Age section");
     //verify help link query param
     Assert.assertEquals(page.getAge().getHelpLink().getUrl().getRef(), ExpectedHelpLinkReference, "Link reference to the age page section does not match");

    });
  }

  /** Verify the Age Section Helper Text
   * parameters
   * @param path       url
   * @param ExpectedHelperText
   *
   */
  @Test(dataProvider = "getAdvancedSearchHelperText")
  public void verifyAgeSectionHelperText(String path, String ExpectedHelperText) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      Assert.assertEquals(page.getAge().getHelpText(), ExpectedHelperText, "Age Section helper text does not match");
    });
  }

    /**
   * When user inputs Age and clicks Find Trials button, correct results are
	 * displayed. Assertion is performed by verifying that url contains search
	 * parameters
	 * @param path       url
   * @param Age        age value
  */

	@Test(dataProvider = "getAdvancedSearchAge")
	public void verifyAgeTextField(String path, String Age) {
		TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
			page.getAge().getAge().enterText(Age);

			// create a navigation event redirect to search results page
			SearchNavigationResult searchresult = page.clickSubmit(page.getFormAction().getFindTrialsButton());

			// assert that url contains expected query params
			Assert.assertEquals(searchresult.getAgeParam(), Age, "query param does not match for age");

		});
	}

	/**
	 * Verify that when user inputs Age which is < 1 or > 120 or contains letters,
	 * the error message is displayed in the age field.
	 *parameters
	 * @param path       url
	 * @param Age        age value
	 *@param ErrorMsgAge error message for invalid age
	 *
	 */
	@Test(dataProvider = "getAdvancedSearchAgeInvalidEntries")
	public void verifyInvalidAgeErrorMessage(String path, String Age, String ErrorMsgAge) {

		TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
			page.getAge().getAge().enterText(Age);
			page.getAge().getAge().setFocusAway();

			// assert that error message is displayed on the age field
			Assert.assertTrue(page.getAge().getErrorDisplay().isVisible(),
					"error message is not displayed on age field");
			String res = page.getAge().getErrorDisplay().getMessage();

			// Assert error message matches
			Assert.assertEquals(res, ErrorMsgAge, "error message doesn't match");

		});
	}



	/************************
	 * SUBMIT SEARCH WITH HITTING ENTER KEY
	 ***********************/

	/**
	 * When user inputs Age and hits enter, correct results are
	 * displayed. Assertion is performed by verifying that url contains search
	 * parameters
	 *
	 * @param path url
	 * @param Age  age value
	 */
	@Test(dataProvider = "getAdvancedSearchAge")
	public void verifyAgeTextFieldWithEnter(String path, String Age) {

		TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
			page.getAge().getAge().enterText(Age);
			page.getAge().getAge().setFocus();

			// create a navigation event redirect to search results page

			SearchNavigationResult res = page.hitEnterKey(page.getAge().getAge());
			// assert that url contains expected query params
			Assert.assertEquals(res.getAgeParam(), Age, "query param doesnt match for age");

		});
	}

  /************************************************************
                  * Data providers
   ************************************************************/

	/**
	 * Data provider retrieves paths to Advanced Search page, Age value and url
	 * params
	 *
	 *
	 */

	@DataProvider(name = "getAdvancedSearchAge")
	public Iterator<Object[]> getAdvancedSearchAge() {
		String[] columns = { "path", "Age" };
		return new ExcelDataReader(getDataFilePath("cts_advanced_search_age_data.xlsx"), "advanced_search", columns);
	}

	/**
	 * Data provider retrieves paths to Advanced Search page, invalid values for age
	 * error messages
	 * @return
	 */

	@DataProvider(name = "getAdvancedSearchAgeInvalidEntries")
	public Iterator<Object[]> getAdvancedSearchInvalidEntries() {
		String[] columns = { "path", "Age", "ErrorMsgAge" };
		return new ExcelDataReader(getDataFilePath("cts_advanced_search_age_data.xlsx"), "advanced_search_negative",
				columns);
	}

	/**
    * Data provider retrieves paths to Advanced Search page and the Title, Help link and Help Link reference
   * for the age Section
	 *
	 * @return
     */

  @DataProvider(name = "getAdvancedSearchTitleAndHelpLink")
  public Iterator<Object[]> getAdvancedSearchTitleAndLink() {
    String[] columns = { "path","ExpectedTitle","ExpectedHelpLinkPath","ExpectedHelpLinkReference"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_age_data.xlsx"), "advanced_search",
      columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page and the Helper Text for the age Section
   *
   * @return
   */

  @DataProvider(name = "getAdvancedSearchHelperText")
  public Iterator<Object[]> getAdvancedSearchHelperText() {
    String[] columns = { "path","ExpectedHelperText"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_age_data.xlsx"), "advanced_search",
      columns);
  }
}
