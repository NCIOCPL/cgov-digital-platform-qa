package gov.cancer.tests.crosscutting;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.FooterPage;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

/**
 * Tests for pages with footer.
 */
public class Footer_Test extends TestObjectBase {

  final public String Footer_header = "National Cancer Institute";
  final public String Footer_headerEsp = "Instituto Nacional del Cáncer";

  /**
   * This method is checking if the Footer exists on the pages
   * 
   * @param path
   *          Path of the page to check.
   * @param language
   *          Language of the page to check.
   */
  @Test(dataProvider = "getPageFooterPaths")
  public void footerIsVisible(String path, String language) {

    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

      Assert.assertTrue(page.isFooterVisible(), "Page Footer is visible.");

    });

  }

  /**
   * This method is checking if the header on the Footer exists and is correctly
   * displayed on the pages
   * 
   * @param path
   *          Path of the page to check.
   * @param language
   *          Language of the page to check.
   */
  @Test(dataProvider = "getPageFooterPaths")
  public void verifyFooterHeader(String path, String language) {

    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

      Assert.assertTrue(page.isFooterHeaderVisible(), "Footer Header is visible.");

      Boolean header_match = false;
      if (language == "English") {
        header_match = page.getFooterHeader().startsWith(Footer_header);
        Assert.assertTrue(header_match, "Footer Header is displayed in English.");
      } else if (language == "Spanish") {
        header_match = page.getFooterHeader().startsWith(Footer_headerEsp);
        Assert.assertTrue(header_match, "Footer Header is displayed in Spanish.");
      }

    });

  }

  /**
   * This method is checking if the English footer is displayed on the English
   * page and Spanish footer is displayed on the Spanish page
   * 
   * @param path
   *          Path of the page to check.
   * @param Expectedlanguage
   *          Language of the page to check.
   */
  @Test(dataProvider = "getPageFooterPaths")
  public void verifyFooterHeaderLanguage(String path, String Expectedlanguage) {

    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

      String Currentlanguage = page.getFooterLanguage().trim();

      Assert.assertTrue(Currentlanguage.matches(Expectedlanguage), "Footer is displayed in correct language.");

    });

  }

  /**
   * This method is checking if the length of text displayed on footer on the
   * pages is in considerable range
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageFooterPaths")
  public void verifyFooterTextlengthCorrect(String path, String language) {

    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

      Assert.assertTrue(page.footerText().length() > 400 && page.footerText().length() < 800,
          "footer text is in range of 400 to 800 chrs");
    });

  }

  /**
   * This method is checking if the length of text displayed on footer on the
   * pages is in considerable range
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageFooterPaths")
  public void isFooterRenderCorrect(String path, String language) {

    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

      Assert.assertFalse(page.footerText().contains("<a"), "footer text contains '<a' ");
    });

  }

  /**
   * This method is checking if footer is displayed only once on all pages
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageFooterPaths")
  public void verifyFooterExistOnce(String path, String language) {

    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

      Assert.assertTrue(page.isFooterVisibleOnce(), "Footer is visible once.");

    });
  }

  /*******************************************
   * DATA PROVIDER
   *******************************************/

  /**
   * Retrieves a list of paths to pages which are expected to have Footer
   * 
   * @return An iterable list of two element arrays, each containing a path and
   *         language.
   */
  @DataProvider(name = "getPageFooterPaths")
  public Iterator<Object[]> getPageFooterPaths() {
    String[] columns = { "path", "language" };
    return new ExcelDataReader(getDataFilePath("footer-data.xlsx"), "pages_with_footer", columns);

  }

}
