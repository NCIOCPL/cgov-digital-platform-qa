package gov.cancer.tests.crosscutting;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.ContentTitlePage;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

/**
 * Tests for page content titles.
 */
public class ContentTitle_Test extends TestObjectBase {

  /**
   * Is the correct Content H1 title displayed?
   *
   * @param path          Path of the page to check.
   * @param expectedTitle Expected content title.
   */
  @Test(dataProvider = "getContentTitles")
  public void contentTitles(String path, String expectedTitle) {

    TestRunner.run(ContentTitlePage.class, path, (ContentTitlePage page) -> {
      

      Assert.assertTrue(page.isContentTitleVisible());

      Assert.assertEquals(page.getContentTitleText(), expectedTitle, "Content title matches.");

    });
  }

  /**
   * Retrieves a list of paths and expected content titles.
   *
   * @return An iterator over a collection of object arrays containing the
   *         requested data.
   */
  @DataProvider(name = "getContentTitles")
  public Iterator<Object[]> getcontentTitles() {
    String[] columns = { "path", "content_title" };
    return new ExcelDataReader(getDataFilePath("page-title-data.xlsx"), "browser_and_page_titles", columns);
  }
}
