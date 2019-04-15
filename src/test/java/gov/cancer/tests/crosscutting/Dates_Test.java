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
 * Tests for pages with Updated, Posted and Reviewed Datetimes.
 */
public class Dates_Test extends TestObjectBase {

  final public String Label_updated = "Updated:";
  final public String Label_updatedEsp = "Actualización:";

  /**
   * This method is checking if the Updated Date and Date Label exists on the
   * pages.
   * 
   * @param path        Path of the page to check.
   * @param updatedDate Updated Date of the page to check.
   * @param dateLabel   Date Label of the page to check.
   */
  @Test(dataProvider = "getUpdatedDatesPath")
  public void verifyUpdatedDates(String path, String updatedDate, String dateLabel, String language) {

    // Get the page.
    TestRunner.run(DatesPage.class, path, (DatesPage page) -> {

      // Asserting datetime field is visible
      Assert.assertTrue(page.isDateFieldVisible(), "Is Date field visible.");

      // Asserting datetime is displayed and matches date in data provider
      Assert.assertEquals(page.getDateTime(), updatedDate);

      // Asserting date label is displayed and matches language label text in data
      // provider
      Assert.assertEquals(page.getDateLabelText(), dateLabel);

      // Asserting date label is displayed and matches language label in data provider
      String dateActualLabel = page.getDateLabelDisplay().trim();
      Assert.assertTrue(dateActualLabel.matches(dateLabel), "Date label is in correct language.");

    });
  }

  /**
   * Retrieves a list of paths to pages which are expected to have Dates.
   *
   * @return An iterable list of two element arrays, each containing a path,
   *         DateTime, Date Label and language.
   */
  @DataProvider(name = "getUpdatedDatesPath")
  public Iterator<Object[]> getUpdatedDates() {
    String[] columns = { "path", "updatedDate", "dateLabel", "language" };
    return new ExcelDataReader(getDataFilePath("dates-data.xlsx"), "pages_with_updated_date", columns);
  }

  /**
   * 
   * This method is checking all pages that does not display DateTime element
   *
   * @param path Path of the page to check.
   * 
   */
  @Test(dataProvider = "getPagesWithoutUpdatedDates")
  public void verifyUpdatedDatesDoesNotAppear(String path) {

    // Get the page.
    TestRunner.run(DatesPage.class, path, (DatesPage page) -> {

      // Asserting datetime field doesnot display
      Assert.assertFalse(page.isDateFieldVisible(), "Pages without updated date.");

    });
  }

  /**
   * Retrieves a list of paths to pages which are expected to have No Dates.
   * 
   * @return path Returns a list of paths for pages which Does Not expected to
   *         display dates
   */
  @DataProvider(name = "getPagesWithoutUpdatedDates")
  public Iterator<Object[]> getPagesWithoutUpdatedDates() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("dates-data.xlsx"), "pages_without_updated_date", columns);
  }
}
