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
 * Tests for pages with bread crumbs.
 */
public class PageOptions_Test extends TestObjectBase {

  /**
   * is section navigation displayed on pages where expected?
   *
   * @param path Path of the page to check.
   */
	@Test(dataProvider = "getPageOptionsPaths")
	public void pageOptionsAreVisible(String path) {
			TestRunner.run(PageOptions.class, path, (PageOptions page) -> {

			Assert.assertTrue(page.IsVisible(), "Page Options are visible.");

    });

  }


  /**
   * Retrieves a list of paths to pages which are expected to have section navigation.
   *
   * @return An iterable list of single element arrays, each containing a single
   * path.
   */
	@DataProvider(name = "getPageOptionsPaths")
	public Iterator<Object[]> getPageOptionsPaths() {
    String[] columns = {"path"};
		return new ExcelDataReader(getDataFilePath("page-options-data.xlsx"), "page_options",
				columns);
  }

}
