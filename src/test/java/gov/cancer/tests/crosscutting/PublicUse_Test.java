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
      Assert.assertTrue(page.isPublicUseTextPresent(), "Public Use Text is visible.");

    });
  }

  /**
   * 
   * Is public use contains page title in the text where it's expected?
   *
   * @param path Path of the page to check.
   */
  @Test(dataProvider = "getPublicUsePaths")
  public void verifyContainsPageTitle(String path) {

    TestRunner.run(PublicUsePage.class, path, (PublicUsePage page) -> {

      // Asserting if Public Use Text contains Title
      Assert.assertTrue(page.isPublicUseContainsTitle(), "Public Use Text Contains page title.");

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
   * 
   * Is public use text displays in language it's expected?
   *
   * @param path     Path of the page to check.
   * @param language language of the public use text to verify.
   * 
   */
  @Test(dataProvider = "getPublicUseLanguagePaths")
  public void verifyPublicUseTextLanguage(String path, String language) {

    TestRunner.run(PublicUsePage.class, path, (PublicUsePage page) -> {

      // Asserting language of the Public Use Text to compare with Data provider
      String Currentlanguage = page.getPublicUseTextLanguage().trim();
      Assert.assertTrue(Currentlanguage.matches(language), "text is displayed in correct language.");

    });
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
      Assert.assertFalse(page.isPublicUseTextPresent(), "Pages without Public Use text.");

    });
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
