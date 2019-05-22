package gov.cancer.tests.fields;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.PageWithMetaTag;
import gov.cancer.pageobject.helper.RobotMetaTag;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

public class SearchEngineRestrictions_Test extends TestObjectBase {

  /**
   * Verify that the "robot" tag is present
   *
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPagesWithIndex")
  public void verifyIncludeSearchIsSelected(String path) {

    TestRunner.run(PageWithMetaTag.class, path, (PageWithMetaTag page) -> {
      RobotMetaTag rmt = page.getRobotMetaTag();
      Assert.assertTrue(rmt.getAllowSearch(), "Found index as Robot Metatag content");

    });
  }

  /**
   * Verify that for meta property = robot the attribute "noindex" is rendered on
   * pages when "Exclude From Search" is set.
   *
   * @param path
   *          Path of the page to check.
   */

  @Test(dataProvider = "getPagesWithNoIndex")
  public void verifyExcludeFromSearchIsSelected(String path) {

    TestRunner.run(PageWithMetaTag.class, path, (PageWithMetaTag page) -> {
      RobotMetaTag rmt = page.getRobotMetaTag();
      Assert.assertFalse(rmt.getAllowSearch(), "Error: Found index as Robot Metatag content");

    });

  }

  /************** Data Providers *************/
  /**
   * Retrieves a list of paths to pages which are expected to have Include in
   * Search Selected
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getPagesWithIndex")
  public Iterator<Object[]> getPagesWithIndex() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("searchenginerestrictions_data.xlsx"), "getpages_with_index", columns);
  }

  /**
   * Retrieves a list of paths to pages which are expected to have Exclude from
   * Search Selected
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getPagesWithNoIndex")
  public Iterator<Object[]> getPagesWithNoIndex() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("searchenginerestrictions_data.xlsx"), "getpages_with_noindex", columns);
  }
}
