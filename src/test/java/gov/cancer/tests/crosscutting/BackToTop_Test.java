package gov.cancer.tests.crosscutting;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.framework.ScrollUtil;
import gov.cancer.pageobject.crosscutting.BackToTopPage;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

/**
 * Test for back to top button on any page
 */
public class BackToTop_Test extends TestObjectBase {

  /**
   * Verify that the Back-to-Top button is not displayed when on the top of page
   * @param path
   */
  @Test(dataProvider = "getBackToTop")
  public void VerifyBackToTopIsNotDisplayed(String path) {
    TestRunner.run(BackToTopPage.class, path, (BackToTopPage page) -> {

      // verify that back to top button is not displayed while on the top of the page
      Assert.assertFalse(page.BackToTopIsDisplayed(), "back to top button is not displayed");
      }
    );
  }

  /**
   * Verify that the Back-to-Top button is displayed when scrolled to the 601 px down the page
   * @param path
   */
  @Test(dataProvider = "getBackToTop")
  public void VerifyBackToTopDisplayed (String path){
    TestRunner.run(BackToTopPage.class, path, (BackToTopPage page) -> {
         //Scroll 601 px interval
      page.ScrollToInterval();
        // verify that back to top button is displayed after scrolling 600 px
        Assert.assertTrue(page.BackToTopIsDisplayed(), "back to top button is displayed");
      }
    );
  }

  /**
   * Verify that the Back-to-Top button is not displayed on mobile devices
   * @param path
   */
  @Test(dataProvider = "getBackToTop")
  public void VerifyBackToTopIsNotDisplayedMobile(String path) {
    TestRunner.runMobile(BackToTopPage.class, path, (BackToTopPage page) -> {
      //Scroll 601 px interval
      page.ScrollToInterval();
        // verify that back to top button is not displayed
        Assert.assertFalse(page.BackToTopIsDisplayed(), "back to top button is not displayed");
      }
    );
  }


  @DataProvider(name = "getBackToTop")
    public Iterator<Object[]> getBackToTop () {
      String[] columns = {"path"};
      return new ExcelDataReader(getDataFilePath("back-to-top.xlsx"), "pages_with_back_to_top", columns);
    }
}
