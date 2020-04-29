package gov.cancer.tests.cts.advanced_search_form;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.cts.AdvancedSearchPage;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

/**
 * This class contains tests to verify all three Delighters on Advanced Search Form Page
 *
 */
public class Delighters_Test extends TestObjectBase {
  /**
   * Verify getHelp delighter link, title and icon
   *
   * @param path
   * @param title - title of delighter
   * @param link  - redirection link
   */
  @Test(dataProvider = "getHelpDelighter")
  public void verifyHelpDelighter(String path, String title, String link) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      //verify that delighter container is present on page
      Assert.assertTrue(page.getHelpDelighter().isVisible());
      //verify that icon is displayed
      Assert.assertTrue(page.getHelpDelighter().isIconDisplayed());
      //verify link
      Assert.assertEquals(page.getHelpDelighter().getLink().getUrl().getPath(), link);
      //verify title
      Assert.assertEquals(page.getHelpDelighter().getLinkTitle(), title);
    });
  }

  /**
   * Verify 'What Are Clinical Trials' delighter link, title and icon
   *
   * @param path
   * @param title - title of delighter
   * @param link  - redirection link
   */
  @Test(dataProvider = "getWhatAreDelighter")
  public void verifyWhatAreTrialsDelighter(String path, String title, String link) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      //verify that delighter container is present on page
      Assert.assertTrue(page.getWhatAreClinicalTrialsDelighter().isVisible());
      //verify that icon is displayed
      Assert.assertTrue(page.getWhatAreClinicalTrialsDelighter().isIconDisplayed());
      //verify link
      Assert.assertEquals(page.getWhatAreClinicalTrialsDelighter().getLink().getUrl().getPath(), link);
      //verify title
      Assert.assertEquals(page.getWhatAreClinicalTrialsDelighter().getLinkTitle(), title);
    });
  }

  /**
   * Verify 'What Are Clinical Trials' delighter link, title and icon
   *
   * @param path
   * @param title - title of delighter
   * @param link  - redirection link
   */
  @Test(dataProvider = "getWhichIsRightDelighter")
  public void verifyWhichTrialIsRightDelighter(String path, String title, String link) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      //verify that delighter container is present on page
      Assert.assertTrue(page.getTrialCheckListDelighter().isVisible());
      //verify that icon is displayed
      Assert.assertTrue(page.getTrialCheckListDelighter().isIconDisplayed());
      //verify link
      Assert.assertEquals(page.getTrialCheckListDelighter().getLink().getUrl().getPath(), link);
      //verify title
      Assert.assertEquals(page.getTrialCheckListDelighter().getLinkTitle(), title);
    });
  }

  /**
   * Data Providers for delighters - each returns path to Advanced Search Form page, title and link for different delighters
   */

  @DataProvider(name = "getHelpDelighter")
  public Iterator<Object[]> getHelpDelighter() {
    String[] columns = {"path", "getHelpTitle", "getHelp"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_delighters-data.xlsx"), "delighters", columns);
  }

  @DataProvider(name = "getWhatAreDelighter")
  public Iterator<Object[]> getWhatAreDelighter() {
    String[] columns = {"path", "whatAreTrialsTitle", "whatAreTrials"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_delighters-data.xlsx"), "delighters", columns);
  }

  @DataProvider(name = "getWhichIsRightDelighter")
  public Iterator<Object[]> getWhichIsRightDelighter() {
    String[] columns = {"path", "whichTrialsAreRightTitle", "whichTrialsAreRight"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_delighters-data.xlsx"), "delighters", columns);
  }

}
