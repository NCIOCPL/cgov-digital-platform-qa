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
public class SimpleDates_Test extends TestObjectBase {

  /**
   * This method is checking if the Updated Date exists on the page and matches
   * expected date from data provider
   * 
   * @param path      Path of the page to check.
   * @param dateLabel Date label of date display.
   * @param dateStamp Date stamp of date display.
   * @param dateText  Date Text attribute of date display.
   * 
   */
  @Test(dataProvider = "getSimpleDatesPath")
  public void verifySimpleDates(String path, String dateLabel, String dateStamp, String dateText) {

    // Get the page.
    TestRunner.run(DatesPage.class, path, (DatesPage page) -> {

      // Asserting datetime field is visible
      Assert.assertTrue(page.dateDisplayIsPresent(), "Is Date field visible.");

      // Asserting Date Label match
      Assert.assertEquals(page.getDateLabel(), dateLabel);

      // Asserting Date Text attribute
      Assert.assertEquals(page.getDateText(), dateText);

      // Asserting Date Stamp sting match
      Assert.assertEquals(page.getDateStamp(), dateStamp);

    });
  }

  /**
   * Retrieves a list of paths to pages which are expected to have Dates.
   *
   * @return An iterable list of two element arrays, each containing a path and
   *         dateLabel, dateStamp and dateText.
   */
  @DataProvider(name = "getSimpleDatesPath")
  public Iterator<Object[]> getSimpleDatesPath() {
    String[] columns = { "path", "dateLabel", "dateStamp", "dateText" };
    return new ExcelDataReader(getDataFilePath("dates-data.xlsx"), "pages_with_simple_dates", columns);
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
      Assert.assertFalse(page.dateDisplayIsPresent(), "Pages without any dates.");

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