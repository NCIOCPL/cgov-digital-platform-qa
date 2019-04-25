package gov.cancer.tests.crosscutting;

import java.util.Iterator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.PublicUsePage;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

/**
 * Tests for pages with public use text.
 */
public class PublicUse_Test extends TestObjectBase {

  /**
   * 
   * Is public use text displays on pages where they're expected?
   *
   * @param path Path of the page to check.
   */
  @Test(dataProvider = "getPublicUsePaths")
  public void verifyPublicUsePresent(String path) {

    TestRunner.run(PublicUsePage.class, path, (PublicUsePage page) -> {

      // Asserting if Public Use section is visible
      Assert.assertTrue(page.sectionIsVisible(), "Public Use section is displayed.");

    });
  }

  /**
   * 
   * Is public use contains page title in the text where it's expected?
   *
   * @param path Path of the page to check.
   */
  @Test(dataProvider = "getPublicUsePaths")
  public void verifyPublicUseContainsPageTitle(String path) {

    TestRunner.run(PublicUsePage.class, path, (PublicUsePage page) -> {

      // Asserting if Public Use Text contains Title
      Assert.assertTrue(page.getPublicUseText().contains(page.getPageTitle()));

    });
  }

  /**
   * 
   * Is public use text length within range?
   *
   * @param path Path of the page to check.
   */
  @Test(dataProvider = "getPublicUsePaths")
  public void verifyPublicUseTextLength(String path) {

    TestRunner.run(PublicUsePage.class, path, (PublicUsePage page) -> {

      // Asserting Public Use Text size
      Assert.assertTrue(page.getPublicUseTextLength().length() > 300 && page.getPublicUseTextLength().length() < 500,
          "Public Use text size");

    });
  }

  /**
   * Verify that the correct translation is used when displaying the Punlic Use
   * text field.
   *
   * @param path            Path of the page to check.
   * @param language        The language used on the page.
   * @param translationText A snippet of text which occurs on the expected
   *                        translation but does not appear on alternate
   *                        translations. NOTE: this text must not include tags in
   *                        the target markup.
   */
  @Test(dataProvider = "getPagesWithTranslations")
  public void verifyPublicUseTextLanguage(String path, String translationText) {

    TestRunner.run(PublicUsePage.class, path, (PublicUsePage page) -> {

      // Asserting Public Use text language
      Assert.assertTrue(page.sectionContainsText(translationText), "Correct translation found");

    });
  }

  /**
   * Retrieves a list of paths to pages which are NOT expected to have public use
   * text.
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @Test(dataProvider = "getPagesWithoutPublicUse")
  public void verifyPublicUseDoesNotAppear(String path) {

    TestRunner.run(PublicUsePage.class, path, (PublicUsePage page) -> {

      // Asserting Public Use section field doesnot display
      Assert.assertFalse(page.sectionIsVisible(), "Public Use section not displayed.");

    });
  }

  /*******************************************
   * DATA PROVIDERS
   *******************************************/

  /**
   * Retrieves a list of paths for pages where the Public Use Text field was set
   * and the page is therefore expected to be present.
   *
   * @return An iterator over a collection of object arrays containing the
   *         requested data.
   */
  @DataProvider(name = "getPagesWithTranslations")
  public Iterator<Object[]> getPagesWithTranslations() {
    String[] columns = { "path", "translated_text" };
    return new ExcelDataReader(getDataFilePath("public-use-data.xlsx"), "pages_with_public_use", columns);
  }

  /**
   * Retrieves a list of paths to pages which are expected to have public use
   * text.
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getPublicUsePaths")
  public Iterator<Object[]> getPublicUsePaths() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("public-use-data.xlsx"), "pages_with_public_use", columns);
  }

  /**
   * Retrieves a list of paths to pages which are expected to have public use
   * text.
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getPublicUseLanguagePaths")
  public Iterator<Object[]> getPublicUseLanguagePaths() {
    String[] columns = { "path", "language" };
    return new ExcelDataReader(getDataFilePath("public-use-data.xlsx"), "pages_with_public_use", columns);
  }

  /**
   * Retrieves a list of paths to pages which are expected to have public use
   * text.
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getPagesWithoutPublicUse")
  public Iterator<Object[]> getPagesWithoutPublicUse() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("public-use-data.xlsx"), "pages_without_public_use", columns);
  }

}
