package gov.cancer.tests.metaData;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.PageWithMetaData;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Iterator;

/**
 * Class contains general tests related to metatag, such as twitter tag, url, title etc...
 */
public class MetaData_Test extends TestObjectBase {
  /**
   * Asserts 'twitter:card' metatag exist on the page.
   *
   * @param path Path of the page to check.
   */
  @Test(dataProvider = "getPagesWithTwitter")
  public void verifyPagesWithTwitterCardTag(String path, String twitter) {

    TestRunner.run(PageWithMetaData.class, path, (PageWithMetaData page) -> {
      Assert.assertEquals(page.getTwitterCardMetaTag().getContent(), twitter, "not found 'twitter:card' metatag");
    });
  }

  /**
   * This method is checking if 'description' matatag name matches dcTermsSubject
   * from data provider
   *
   * @param path     Path of the page to check.
   * @param metaDesc Label of metaDesc metatag.
   *
   */
  @Test(dataProvider = "getPagesWithMetaDescription")
  public void verifyPagesWithMetaDescription(String path, String metaDesc) {

    TestRunner.run(PageWithMetaData.class, path, (PageWithMetaData page) -> {
      Assert.assertEquals(page.getMetaDescription().getContent(), metaDesc, "meta tag 'description' is incorrect");
    });
  }

  /**
   * This method is checking if Content language metatag matches language
   * label from data provider
   *
   * @param path     Path of the page to check.
   * @param language Label of Content language.
   *
   */
  @Test(dataProvider = "getPagesWithMetaContentLanguage")
  public void verifyPagesWithMetaContentLanguage(String path, String language) {

    TestRunner.run(PageWithMetaData.class, path, (PageWithMetaData page) -> {
      Assert.assertEquals(page.getContentLanguage().getContent(), language, "meta tag 'content-language' is incorrect");
    });
  }

  /**
   * Asserts current page URL equals link rel="canonical" url metatag
   *
   * @param path Path of the page to check.
   */
  @Test(dataProvider = "getPagesWithAllMeta")
  public void verifyPagesWithMetaOGURL(String path) {

    TestRunner.run(PageWithMetaData.class, path, (PageWithMetaData page) -> {
      Assert.assertEquals(page.getCurrentURL(), page.getCanonicalURL(), "'canonical' url does not match current url");
    });
  }

  /************** Data Provider *************/
  /**
   * Retrieves a list of paths to pages with meta data
   *
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getPagesWithAllMeta")
  public Iterator<Object[]> getPagesWithAllMeta() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("meta_data.xlsx"), "pages_with_meta", columns);
  }

  /************** Data Provider *************/
  /**
   * Retrieves a list of paths to pages which are expected to have meta
   * name="twitter:card"
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getPagesWithTwitter")
  public Iterator<Object[]> getPagesWithTwitter() {
    String[] columns = { "path", "twitter" };
    return new ExcelDataReader(getDataFilePath("meta_data.xlsx"), "pages_with_meta", columns);
  }
  /**
   * Retrieves a list of paths to pages which are expected to have meta
   * name="description"
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getPagesWithMetaDescription")
  public Iterator<Object[]> getPagesWithMetaDescription() {
    String[] columns = { "path", "metaDesc" };
    return new ExcelDataReader(getDataFilePath("meta_data.xlsx"), "pages_with_meta", columns);
  }
  /**
   * Retrieves a list of paths to pages which are expected to have
   * http-equiv="content-language"
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getPagesWithMetaContentLanguage")
  public Iterator<Object[]> getPagesWithMetaContentLanguage() {
    String[] columns = { "path", "language" };
    return new ExcelDataReader(getDataFilePath("meta_data.xlsx"), "pages_with_meta", columns);
  }



}
