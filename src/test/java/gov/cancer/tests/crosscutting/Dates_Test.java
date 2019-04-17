package gov.cancer.tests.crosscutting;

import java.util.Iterator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.DatesPage;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

/**
 * Tests for pages with Updated, Posted and Reviewed Datetime display.
 * 
 */
public class Dates_Test extends TestObjectBase {

  /**
   * This method is checking if the Updated Date exists on the page and matches
   * expected date from data provider
   * 
   * @param path        Path of the page to check.
   * @param updatedDate Updated Date of the page to check.
   */
  @Test(dataProvider = "getUpdatedDatesPath")
  public void verifyUpdatedDates(String path, String updatedDate) {

    // Get the page.
    TestRunner.run(DatesPage.class, path, (DatesPage page) -> {

      // Asserting datetime field is visible
      Assert.assertTrue(page.isDateFieldVisible(), "Is Date field visible.");

      // Asserting datetime is displayed and matches date in data provider
      Assert.assertEquals(page.getDateTimeTextString(), updatedDate);

    });
  }

  /**
   * Retrieves a list of paths to pages which are expected to have Dates.
   *
   * @return An iterable list of two element arrays, each containing a path and
   *         datetime
   */
  @DataProvider(name = "getUpdatedDatesPath")
  public Iterator<Object[]> getUpdatedDates() {
    String[] columns = { "path", "updatedDate" };
    return new ExcelDataReader(getDataFilePath("dates-data.xlsx"), "pages_with_updated_date", columns);
  }

  /**
   * This method is checking if the Updated Date exists on the page and matches
   * expected date from data provider
   * 
   * @param path         Path of the page to check.
   * @param reviewedDate Reviewed Date of the page to check.
   */
  @Test(dataProvider = "getReviewedDatesPath")
  public void verifyRevieweddDates(String path, String reviewedDate) {

    // Get the page.
    TestRunner.run(DatesPage.class, path, (DatesPage page) -> {

      // Asserting datetime field is visible
      Assert.assertTrue(page.isDateFieldVisible(), "Is Date field visible.");

      // Asserting datetime is displayed and matches date in data provider
      Assert.assertEquals(page.getDateTimeTextString(), reviewedDate);

    });
  }

  /**
   * Retrieves a list of paths to pages which are expected to have Dates.
   *
   * @return An iterable list of two element arrays, each containing a path and
   *         datetime
   * 
   */
  @DataProvider(name = "getReviewedDatesPath")
  public Iterator<Object[]> getReviewedDates() {
    String[] columns = { "path", "reviewedDate" };
    return new ExcelDataReader(getDataFilePath("dates-data.xlsx"), "pages_with_reviewed_date", columns);
  }

  /**
   * This method is checking if the Posted Date exists on the page and matches
   * expected date from data provider
   * 
   * @param path       Path of the page to check.
   * @param postedDate Posted Date of the page to check.
   */
  @Test(dataProvider = "getPostedDatesPath")
  public void verifyPostedDates(String path, String postedDate) {

    // Get the page.
    TestRunner.run(DatesPage.class, path, (DatesPage page) -> {

      // Asserting datetime field is visible
      Assert.assertTrue(page.isDateFieldVisible(), "Is Date field visible.");

      // Asserting datetime is displayed and matches date in data provider
      Assert.assertEquals(page.getDateTimeTextString(), postedDate);

    });
  }

  /**
   * Retrieves a list of paths to pages which are expected to have Dates.
   *
   * @return An iterable list of two element arrays, each containing a path and
   *         datetime
   * 
   */
  @DataProvider(name = "getPostedDatesPath")
  public Iterator<Object[]> getPostedDates() {
    String[] columns = { "path", "postedDate" };
    return new ExcelDataReader(getDataFilePath("dates-data.xlsx"), "pages_with_posted_date", columns);
  }

  /**
   * 
   * This method is checking all pages that does not display DateTime element
   *
   * @param path Path of the page to check pages with no dates.
   * 
   */
  @Test(dataProvider = "getPagesWithoutDates")
  public void verifyDatesDoesNotAppear(String path) {

    // Get the page.
    TestRunner.run(DatesPage.class, path, (DatesPage page) -> {

      // Asserting datetime field doesnot display
      Assert.assertFalse(page.isDateFieldVisible(), "Pages without any dates.");

    });
  }

  /**
   * Retrieves a list of paths to pages which are expected to have No Dates.
   * 
   * @return path Returns a list of paths for pages which Does Not expected to
   *         display dates
   */
  @DataProvider(name = "getPagesWithoutDates")
  public Iterator<Object[]> getPagesWithoutDates() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("dates-data.xlsx"), "pages_without_dates", columns);
  }

}