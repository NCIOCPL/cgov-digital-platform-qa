package gov.cancer.tests.cts;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.cts.TrialDescriptionPage;
import gov.cancer.pageobject.helper.CTS_Section;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Iterator;

/**
 * This set of tests is verifying all common behavior across all CTS pages
 */
public class CTS_Common_Test extends TestObjectBase {

  /**
   * Verify that the CTS is present on Basic, Advance search pages as well as Search results.
   * CTS_Section page is used as it is contains all common elements and methods for CTS pages
   * assertion purpose
   * @param path
   */
  @Test (dataProvider = "getCTSPages")
  public void verifyCTSPresent(String path) {
    TestRunner.run(CTS_Section.class, path, (CTS_Section page) -> {
      Assert.assertTrue(page.isHeaderDisplayed(), "header field is not present");

    });

  }


  /**
   * Verify that the main CTS Trial Description is present
   * Separated from above test due to it's unique behavior
   * @param path
   */
  @Test (dataProvider = "getTrialDesc")
  public void verifyTrialDescPresent(String path) {
    TestRunner.run(TrialDescriptionPage.class, path, (TrialDescriptionPage page) -> {
      Assert.assertTrue(page.isTrialDescVisible(), "header is not present");

    });

  }
  /**
   * Data provider retrieves paths to Basic and Advance Search paths, Search Results
   * @return
   */
  @DataProvider(name = "getCTSPages")
  public Iterator<Object[]> getCTSPages() {
    String[] columns = {"path"};
    return new ExcelDataReader(getDataFilePath("cts-data.xlsx"), "cts_pages", columns);
  }

  /**
   *
   * @return Trial Description path
   */
  @DataProvider(name = "getTrialDesc")
  public Iterator<Object[]> getTrialDesc() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("cts-data.xlsx"), "trial_description", columns);

  }
}
