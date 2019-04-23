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
   * This method is checking if the date display is present on the page.
   * 
   * @param path Path of the page to check.
   * 
   */
  @Test(dataProvider = "getDateDisplayPresentPath")
  public void verifyDateDisplayPresent(String path) {

    // Get the page.
    TestRunner.run(DatesPage.class, path, (DatesPage page) -> {

      // Asserting datetime field is visible
      Assert.assertTrue(page.dateDisplayIsPresent(), "Is Date field visible.");

    });
  }

  /**
   * Retrieves a list of paths to pages which are expected to have Dates.
   *
   * @return path Returns a list of paths for pages which Does Not expected to
   *         display dates
   */
  @DataProvider(name = "getDateDisplayPresentPath")
  public Iterator<Object[]> getDateDisplayPresentPath() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("dates-data.xlsx"), "pages_with_simple_dates", columns);
  }

  /**
   * This method is checking if the Date Text exists on the page and matches
   * expected date from data provider
   * 
   * @param path         Path of the page to check.
   * @param expectedText Date Text of date.
   * 
   */
  @Test(dataProvider = "getDateTextPath")
  public void verifyDateText(String path, String expectedText) {

    // Get the page.
    TestRunner.run(DatesPage.class, path, (DatesPage page) -> {

      // Asserting Date Text
      Assert.assertEquals(page.getDateText(), expectedText);

    });
  }

  /**
   * Retrieves a list of paths to pages which are expected to have Date.
   *
   * @return An iterable list of two element arrays, each containing a path and
   *         dateText.
   */
  @DataProvider(name = "getDateTextPath")
  public Iterator<Object[]> getDateTextPath() {
    String[] columns = { "path", "expectedText" };
    return new ExcelDataReader(getDataFilePath("dates-data.xlsx"), "pages_with_simple_dates", columns);
  }

  /**
   * This method is checking if the Date Label exists on the page and matches
   * expected label from data provider
   * 
   * @param path          Path of the page to check.
   * @param expectedLabel Date Label of date.
   * 
   */
  @Test(dataProvider = "getDateLabelPath")
  public void verifyDateLabel(String path, String expectedLabel) {

    // Get the page.
    TestRunner.run(DatesPage.class, path, (DatesPage page) -> {

      // Asserting Date Label match
      Assert.assertEquals(page.getDateLabel(), expectedLabel);

    });
  }

  /**
   * Retrieves a list of paths to pages which are expected to have Dates.
   *
   * @return An iterable list of two element arrays, each containing a path and
   *         dateLabel
   */
  @DataProvider(name = "getDateLabelPath")
  public Iterator<Object[]> getDateLabelPath() {
    String[] columns = { "path", "expectedLabel" };
    return new ExcelDataReader(getDataFilePath("dates-data.xlsx"), "pages_with_simple_dates", columns);
  }

  /**
   * This method is checking if the Date Label exists on the page and matches
   * expected label from data provider
   * 
   * @param path          Path of the page to check.
   * @param expectedStamp Date Stamp of date.
   * 
   */
  @Test(dataProvider = "getDateStampPath")
  public void verifyDateStamp(String path, String expectedStamp) {

    // Get the page.
    TestRunner.run(DatesPage.class, path, (DatesPage page) -> {

      // Asserting Date Stamp sting match
      Assert.assertEquals(page.getDateStamp(), expectedStamp);

    });
  }

  /**
   * Retrieves a list of paths to pages which are expected to have Dates.
   *
   * @return An iterable list of two element arrays, each containing a path and
   *         dateStamp
   */
  @DataProvider(name = "getDateStampPath")
  public Iterator<Object[]> getDateStampPath() {
    String[] columns = { "path", "expectedStamp" };
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