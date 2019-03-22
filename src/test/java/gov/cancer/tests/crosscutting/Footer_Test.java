package gov.cancer.tests.crosscutting;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.FooterPage;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

/**
 * Tests for pages with bread crumbs.
 */
public class Footer_Test extends TestObjectBase {
	
	//public class driver {
	//	WebDriver driver;

  /**
   * is section navigation displayed on pages where expected?
   *
   * @param path Path of the page to check.
   */
	@Test(dataProvider = "getPageFooterPaths")
	public void pageFooterIsVisible(String path) {
		
		TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

			Assert.assertTrue(page.isPageFooterVisible(), "Page Footer is visible.");

    });

  }
	
	
	
  /**
   * Retrieves a list of paths to pages which are expected to have section navigation.
   *
   * @return An iterable list of single element arrays, each containing a single
   * path.
   */
	@DataProvider(name = "getPageFooterPaths")
	public Iterator<Object[]> getPageFooterPaths() {
    String[] columns = {"path"};
		return new ExcelDataReader(getDataFilePath("footer-data.xlsx"), "pages_with_footer",
				columns);
  
}


@Test(dataProvider = "getPageFooterPaths")
public void verifyBackToTop(String path) {
	
	TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

		Assert.assertTrue(page.getBackToTop().isDisplayed(), "Back to top link is visible.");
		
		
		
});
}
}
