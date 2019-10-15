package gov.cancer.tests.crosscutting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.BreadCrumbPage;
import gov.cancer.pageobject.helper.BreadCrumb;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

/**
 * Tests for pages with bread crumbs.
 */
public class BreadCrumb_Test extends TestObjectBase {

  /**
   * Are breadcrumbs displayed on pages where they're expected?
   *
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getBreadCrumbPathsLinks")
  public void verifyBreadCrumbCount(String path, List<String> link, List<String> paths) {

    TestRunner.runDesktop(BreadCrumbPage.class, path, (BreadCrumbPage page) -> {

      Assert.assertEquals(page.getBreadCrumbs().size(),link.size(), "Bread crumb count is correct.");

    });

  }
  /**
   * On Desktop, Are breadcrumbs displayed on pages where they're expected?
   *
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getBreadCrumbPaths")
  public void breadcrumbIsVisibleDesktop(String path) {

    TestRunner.runDesktop(BreadCrumbPage.class, path, (BreadCrumbPage page) -> {

      Assert.assertTrue(page.isBreadCrumbVisible(), "Bread crumb is not visible at Desktop.");

    });

  }

  /**
   * At tablet breakpoint, are the BreadCrumbs displayed on pages where they're
   * expected?
   *
   * @param path
   *          Path of the page to check.
   */

  @Test(dataProvider = "getBreadCrumbPaths")
  public void verifyBreadcrumbIsVisibleAtTablet(String path) {

    TestRunner.runTablet(BreadCrumbPage.class, path, (BreadCrumbPage page) -> {

      Assert.assertTrue(page.isBreadCrumbVisible(), "Breadcrumb is not visible at Tablet breakpoint");

    });

  }

  /**
   * At mobile breakpoint, are the Breadcrumbs displayed on pages where they're
   * NOT expected?
   *
   * @param path
   *          Path of the page to check.
   */

  @Test(dataProvider = "getBreadCrumbPaths")
  public void verifyBreadcrumbIsVisibleAtMobile(String path) {

    TestRunner.runMobile(BreadCrumbPage.class, path, (BreadCrumbPage page) -> {

      Assert.assertFalse(page.isBreadCrumbVisible(), "Error: Breadcrumb is visible at Mobile breakpoint");

    });

  }

  /**
   * Is the Breadcrumbs displayed on pages where they're NOT expected?
   *
   * @param path
   *          Path of the page to check.
   */

  @Test(dataProvider = "getBreadCrumbPathsNegative")
  public void verifyBreadcrumbIsNotVisibleHomeLanding(String path) {

    TestRunner.runDesktop(BreadCrumbPage.class, path, (BreadCrumbPage page) -> {

      Assert.assertFalse(page.isBreadCrumbVisible(), "Error: Breadcrumb is visible at Home and Landing pages");

    });

  }

  /**
   *
   * This method is checking all the breadcrumbs on the page and verifying the
   * link is correct and whether it contains user's current page location
   *
   * @param path
   *          Path of the page to check.
   * @param link
   *          List of link titles of breadcrumbs.
   * @param paths
   *          List of links of breadcrumbs.
   *
   */

  @Test(dataProvider = "getBreadCrumbPathsLinks")
  public void isBreadcrumbLinkCorrect(String path, List<String> link, List<String> paths) {

    TestRunner.run(BreadCrumbPage.class, path, (BreadCrumbPage page) -> {

      List<BreadCrumb> breadcrumbs = page.getBreadCrumbs();
      Assert.assertTrue(breadcrumbs.size() >= 1);
      // For each breadcrumb execute the following assertions.
      for (int i = 0; i < breadcrumbs.size(); i++) {
        Assert.assertEquals(breadcrumbs.get(i).getBreadCrumbLink().getUrl().getPath(), paths.get(i), "Error: Breadcrumb link is wrong");

      }

    });
  }

  /**
   *
   * This method is checking all the breadcrumbs on the page and verifying link
   * title is correct
   *
   * @param path
   *          Path of the page to check.
   * @param link
   *          list of link titles of breadcrumbs.
   * @param paths
   *          list of links of breadcrumbs.
   */
  @Test(dataProvider = "getBreadCrumbPathsLinks")
  public void isBreadcrumbTitleCorrect(String path, List<String> link, List<String> paths) {
    TestRunner.run(BreadCrumbPage.class, path, (BreadCrumbPage page) -> {
      List<BreadCrumb> breadcrumbs = page.getBreadCrumbs();
      Assert.assertTrue(breadcrumbs.size() >= 1);
      // For each breadcrumb execute the following assertions.
      for (int i = 0; i < breadcrumbs.size(); i++) {

        Assert.assertEquals(breadcrumbs.get(i).getBreadCrumbLinkTitle(), link.get(i),
            "Error: Breadcrumb link title is wrong");
      }

    });
  }

  /*******************************************
   * DATA PROVIDER
   *******************************************/
  /**
   * Retrieves a list of paths to pages which are expected to have breadcrumbs.
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getBreadCrumbPaths")
  public Iterator<Object[]> getBreadCrumbPaths() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("bread-crumb-data.xlsx"), "pages_with_breadcrumbs", columns);
  }

  /**
   * Retrieves a list of paths to pages which are expected to have breadcrumbs.
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getBreadCrumbPathsLinks")
  public Iterator<Object[]> getBreadCrumbPathsLinks() {
    String[] columns = { "path", "links", "linkpath" };
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("bread-crumb-data.xlsx"), "pages_with_breadcrumbs",
        columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String raw = (String) item[1];
      List<String> expectedlinks = Arrays.asList(raw.split(","));
      String rawpaths = (String) item[2];
      List<String> expectedlinkpaths = Arrays.asList(rawpaths.split(","));
      converted.add(new Object[] { item[0], expectedlinks, expectedlinkpaths });
    }
    return converted.iterator();
  }

  /**
   * Retrieves a list of paths to pages which are not expected to have
   * breadcrumbs.
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getBreadCrumbPathsNegative")
  public Iterator<Object[]> getBreadCrumbPathsNegative() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("bread-crumb-data.xlsx"), "pages_with_no_breadcrumbs", columns);
  }
}
