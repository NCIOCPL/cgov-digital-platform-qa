package gov.cancer.tests.crosscutting;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.PageTitle;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

public class PageTitle_Test extends TestObjectBase {

  /**
   * Verify that the page title is displayed.
   *
   * @param path
   *          Path of the page to check.
   * @param pageTitle
   *          Expected Page title
   */
  @Test(dataProvider = "getPagesWithPageTitle")
  public void verifyPageTitleIsVisible(String path, String pageTitle) {

    TestRunner.run(PageTitle.class, path, (PageTitle page) -> {
      Assert.assertTrue(page.isPageTitleVisible(), "Page title is not displayed.");

    });
  }

  /**
   * Verify that the correct Page title is displayed.
   *
   * @param path
   *          Path of the page to check.
   * @param pageTitle
   *          Expected Page title
   */
  @Test(dataProvider = "getPagesWithPageTitle")
  public void verifyPageTitleisCorrect(String path, String pageTitle) {

    TestRunner.run(PageTitle.class, path, (PageTitle page) -> {
      Assert.assertEquals(page.getPageTitle().trim(),pageTitle, "Page Title not correct.");

    });
  }

  /*******************************************
   * DATA PROVIDERS
   *******************************************/

  /**
   * Retrieves a list of paths for pages where the page title is expected to be
   * present.
   *
   * @return An iterable list of two element arrays, each containing a path and
   *         title.
   */
  @DataProvider(name = "getPagesWithPageTitle")
  public Iterator<Object[]> getPagesWithPageTitle() {
    String[] columns = { "path", "page_title" };
    return new ExcelDataReader(getDataFilePath("page-title-data.xlsx"), "page_title", columns);
  }

}
