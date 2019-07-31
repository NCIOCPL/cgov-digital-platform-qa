package gov.cancer.tests.metaData;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.PageWithMetaData;
import gov.cancer.pageobject.helper.MetaTag;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Iterator;

/**
 * Class contains tests related to metatag OGType
 */
public class MetaData_OG_Test extends TestObjectBase {
  /**
   * This method is checking if 'og:description' matatag name matches ogDesc from
   * data provider
   *
   * @param path   Path of the page to check.
   * @param ogDesc Label of ogDesc metatag.
   *
   */
  @Test(dataProvider = "getPagesWithOGDescription")
  public void verifyPagesWithMetaDescription(String path, String ogDesc) {

    TestRunner.run(PageWithMetaData.class, path, (PageWithMetaData page) -> {
      Assert.assertEquals(page.getOGMetaDescription().getContent(), ogDesc, "'ogDesc' content is not correct");
    });
  }

  /**
   * This method is checking if 'og:site_name' matatag name matches siteName from
   * data provider
   *EndsWith assertion is used because dev-ac has 'CGDP - Dev - ' added in front of title, whereas ODE and PROD does not
   * @param path   Path of the page to check.
   * @param siteName Label of siteName metatag.
   *
   */
  @Test(dataProvider = "getPagesWithMetaSiteName")
  public void verifyPagesWithMetaSiteName(String path, String siteName) {

    TestRunner.run(PageWithMetaData.class, path, (PageWithMetaData page) -> {
      Assert.assertTrue(page.getMetaSiteName().getContent().endsWith(siteName), "meta 'site name' does not match");
    });
  }

  /**
   * This method is checking if 'og:title' matatag name matches OGTitle from
   * data provider
   *
   * @param path   Path of the page to check.
   * @param OGTitle Label of OGTitle metatag.
   *
   */
  @Test(dataProvider = "getPagesWithOGTitle")
  public void verifyPagesWithOGTitle(String path, String OGTitle) {

    TestRunner.run(PageWithMetaData.class, path, (PageWithMetaData page) -> {
      Assert.assertEquals(page.getOGTitle().getContent(), OGTitle, "'OGTitle' does not match");
    });
  }

  /**
   * Asserts if 'property="og:type' metatag exist on the page.
   *
   * @param path Path of the page to check.
   */
  @Test(dataProvider = "getPagesWithMetaOG")
  public void verifyPagesWithOGMetaTag(String path, String OGType) {

    TestRunner.run(PageWithMetaData.class, path, (PageWithMetaData page) -> {
      Assert.assertEquals(page.getOGTypeMetaTag().getContent(), OGType, "not found 'OGType' metatag");
    });
  }
  /**
   * Asserts current page URL equals 'og:url' metatag
   *
   * @param path Path of the page to check.
   */
  @Test(dataProvider = "getPagesWithMetaOG")
  public void verifyPagesWithMetaOGURL(String path, String OGType) {

    TestRunner.run(PageWithMetaData.class, path, (PageWithMetaData page) -> {
      Assert.assertEquals(page.getCurrentURL(), page.getOGURL().getContent(), "meta content 'OGURL' does not match");
    });
  }

  /********************************** Data Providers *********************************/


  /**
   * Retrieves a list of paths to pages which are expected to have meta
   * property="og:url", "og:type"
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getPagesWithMetaOG")
  public Iterator<Object[]> getPagesWithMetaOG() {
    String[] columns = { "path", "OGType" };
    return new ExcelDataReader(getDataFilePath("meta_data.xlsx"), "pages_with_meta", columns);
  }
  /**
   * Retrieves a list of paths to pages which are expected to have meta
   * property="og:title"
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getPagesWithOGTitle")
  public Iterator<Object[]> getPagesWithOGTitle() {
    String[] columns = { "path", "OGTitle" };
    return new ExcelDataReader(getDataFilePath("meta_data.xlsx"), "pages_with_meta", columns);
  }

  /**
   * Retrieves a list of paths to pages which are expected to have a
   * property="og:site_name"
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getPagesWithMetaSiteName")
  public Iterator<Object[]> getPagesWithMetaSiteName() {
    String[] columns = { "path", "siteName" };
    return new ExcelDataReader(getDataFilePath("meta_data.xlsx"), "pages_with_meta", columns);
  }

  /**
   * Retrieves a list of paths to pages which are expected to have meta
   * property="og:description"
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getPagesWithOGDescription")
  public Iterator<Object[]> getPagesWithOGDescription() {
    String[] columns = { "path", "ogDesc" };
    return new ExcelDataReader(getDataFilePath("meta_data.xlsx"), "pages_with_meta", columns);
  }
}
