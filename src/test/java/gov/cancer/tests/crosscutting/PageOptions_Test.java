package gov.cancer.tests.crosscutting;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.PageOptions;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

/**
 * Tests for pages with page options.
 */
public class PageOptions_Test extends TestObjectBase {

  /**
   * Are pageoptions container displayed on pages where they're expected?
   *
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageOptionsPaths")
  public void pageoptionsIsVisible(String path) {

    TestRunner.run(PageOptions.class, path, (PageOptions page) -> {

      Assert.assertTrue(page.isPageOptionsVisible(), "PageOption is visible.");

    });

  }

  /**
   * At desktop breakpoint, are the Print Buttons displayed on pages where they're
   * expected?
   *
   * @param path
   *          Path of the page to check.
   */

  @Test(dataProvider = "getPageOptionsPaths")
  public void verifyPrintButtonIsVisibleAtDesktop(String path) {

    TestRunner.runDesktop(PageOptions.class, path, (PageOptions page) -> {

      Assert.assertTrue(page.isPrintButtonVisible(), "Print Button is visible.");

    });

  }

  /**
   * At tablet breakpoint, are the Print Buttons displayed on pages where they're
   * NOT expected?
   *
   * @param path
   *          Path of the page to check.
   */

  @Test(dataProvider = "getPageOptionsPaths")
  public void verifyPrintButtonIsVisibleAtTablet(String path) {

    TestRunner.runTablet(PageOptions.class, path, (PageOptions page) -> {

      Assert.assertFalse(page.isPrintButtonVisible(), "Error: Print Button is visible at Tablet breakpoint");

    });

  }

  /**
   * At mobile breakpoint, are the Print Buttons displayed on pages where they're
   * NOT expected?
   *
   * @param path
   *          Path of the page to check.
   */

  @Test(dataProvider = "getPageOptionsPaths")
  public void verifyPrintButtonIsVisibleAtMobile(String path) {

    TestRunner.runMobile(PageOptions.class, path, (PageOptions page) -> {

      Assert.assertFalse(page.isPrintButtonVisible(), "Error: Print Button is visible at Mobile breakpoint");

    });

  }

  /**
   * At desktop breakpoint, are the Font Resizer Buttons displayed on pages where
   * they're expected?
   *
   * @param path
   *          Path of the page to check.
   */

  @Test(dataProvider = "getFontResizerPresentPaths")
  public void verifyFontResizerButtonIsVisibleAtDesktop(String path) {

    TestRunner.runDesktop(PageOptions.class, path, (PageOptions page) -> {

      Assert.assertTrue(page.isFontResizerButtonVisible(), "FontResizer Button is visible at Desktop breakpoint.");

    });

  }

  /**
   * At tablet breakpoint, are the Font Resizer Buttons displayed on pages where
   * they're NOT expected?
   *
   * @param path
   *          Path of the page to check.
   */

  @Test(dataProvider = "getFontResizerPresentPaths")
  public void verifyFontResizerButtonIsVisibleAtTablet(String path) {

    TestRunner.runTablet(PageOptions.class, path, (PageOptions page) -> {

      Assert.assertFalse(page.isFontResizerButtonVisible(),
          "Error: Font Resizer Button is visible at Tablet breakpoint");

    });

  }

  /**
   * At mobile breakpoint, are the Font Resizer Button displayed on pages where
   * they're NOT expected?
   *
   * @param path
   *          Path of the page to check.
   */

  @Test(dataProvider = "getFontResizerPresentPaths")
  public void verifyFontResizerButtonIsVisibleAtMobile(String path) {

    TestRunner.runMobile(PageOptions.class, path, (PageOptions page) -> {

      Assert.assertFalse(page.isFontResizerButtonVisible(),
          "Error: Font Resizer Button is visible at Mobile breakpoint");

    });

  }

  /**
   * Are the Font Resizer Buttons displayed on pages where they're NOT expected?
   *
   * @param path
   *          Path of the page to check.
   */

  @Test(dataProvider = "getFontResizerAbsentPaths")
  public void verifyFontResizerButtonIsNotVisible(String path) {

    TestRunner.run(PageOptions.class, path, (PageOptions page) -> {

      Assert.assertFalse(page.isFontResizerButtonVisible(), "Error: Font Resizer Button is visible.");

    });

  }

  /**
   * Are the Email Buttons displayed on pages where they're expected?
   *
   * @param path
   *          Path of the page to check.
   */

  @Test(dataProvider = "getPageOptionsPaths")
  public void verifyEmailButtonIsVisible(String path) {

    TestRunner.run(PageOptions.class, path, (PageOptions page) -> {

      Assert.assertTrue(page.isEmailButtonVisible(), "Email Button is visible.");

    });

  }

  /**
   * Are the Facebook Buttons displayed on pages where they're expected?
   *
   * @param path
   *          Path of the page to check.
   */

  @Test(dataProvider = "getPageOptionsPaths")
  public void verifyFacebookButtonIsVisible(String path) {

    TestRunner.run(PageOptions.class, path, (PageOptions page) -> {

      Assert.assertTrue(page.isFaceBookButtonVisible(), "Facebook Button is visible.");

    });

  }

  /**
   * Are the Twitter Buttons displayed on pages where they're expected?
   *
   * @param path
   *          Path of the page to check.
   */

  @Test(dataProvider = "getPageOptionsPaths")
  public void verifyTwitterButtonIsVisible(String path) {

    TestRunner.run(PageOptions.class, path, (PageOptions page) -> {

      Assert.assertTrue(page.isTwitterButtonVisible(), "Twitter Button is visible.");

    });

  }

  /**
   * Are the Pinterest Buttons displayed on pages where they're expected?
   *
   * @param path
   *          Path of the page to check.
   */

  @Test(dataProvider = "getPageOptionsPaths")
  public void verifyPinterestButtonIsVisible(String path) {

    TestRunner.run(PageOptions.class, path, (PageOptions page) -> {

      Assert.assertTrue(page.isPinterestButtonVisible(), "Pinterest Button is visible.");

    });

  }

  /************** Data Providers *************/
  /**
   * Retrieves a list of paths to pages which are expected to have pageoptions
   * container.
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getPageOptionsPaths")
  public Iterator<Object[]> getPageOptionsPaths() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("page-options-data.xlsx"), "pages_with_pageoptions", columns);
  }

  /**
   * Retrieves a list of paths to pages which are expected to have fontresizers
   *
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getFontResizerPresentPaths")
  public Iterator<Object[]> getFontResizerPresentPaths() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("page-options-data.xlsx"), "pages_with_fontresizers", columns);
  }

  /**
   * Retrieves a list of paths to pages which are expected NOT to have
   * fontresizers
   *
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getFontResizerAbsentPaths")
  public Iterator<Object[]> getFontResizerAbsentPaths() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("page-options-data.xlsx"), "pages_without_fontresizers", columns);
  }

}
