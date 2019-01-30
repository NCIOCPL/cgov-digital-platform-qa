package gov.cancer.tests.crosscutting;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.PageTitlePage;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

/**
 * Tests for page browser titles.
 */
public class PageTitle_Test extends TestObjectBase {

  /**
   * Is the correct browser title displayed?
   *
   * @param path          Path of the page to check.
   * @param expectedTitle Expected browser title.
   */
  @Test(dataProvider = "getBrowserTitles")
  public void browserTitles(String path, String expectedTitle){

    TestRunner.run(PageTitlePage.class, path, (PageTitlePage page)->{

      Assert.assertEquals(page.getPageTitle(), expectedTitle, "Browser title matches.");

    });
  }

  /**
   * Retrieves a list of paths and expected browser titles.
   *
   * @return An iterator over a collection of object arrays containing the
   *         requested data.
   */
  @DataProvider(name = "getBrowserTitles")
  public Iterator<Object[]> getBrowserTitles(){
    String[] columns = {"path", "browser_title"};
    return new ExcelDataReader(getDataFilePath("page-title-data.xlsx"), "browser_and_page_titles", columns);
  }
}
