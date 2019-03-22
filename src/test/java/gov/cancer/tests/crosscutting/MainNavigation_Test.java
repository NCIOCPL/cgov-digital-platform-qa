package gov.cancer.tests.crosscutting;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.MainNavigationPage;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

/**
 * Tests for pages with bread crumbs.
 */
public class MainNavigation_Test extends TestObjectBase {

  /**
   * is section navigation displayed on pages where expected?
   *
   * @param path Path of the page to check.
   */
	@Test(dataProvider = "getMainNavigationPaths")
	public void mainNavigationIsVisible(String path) {
		//System.out.println("--> SectionNavigation in SectionNavigationPage_Test");
		TestRunner.run(MainNavigationPage.class, path, (MainNavigationPage page) -> {

			Assert.assertTrue(page.isMainNavigationVisible(), "Main Navigation is visible.");

    });

  }


  /**
   * Retrieves a list of paths to pages which are expected to have section navigation.
   *
   * @return An iterable list of single element arrays, each containing a single
   * path.
   */
	@DataProvider(name = "getMainNavigationPaths")
	public Iterator<Object[]> getMainNavigationPaths() {
    String[] columns = {"path"};
		return new ExcelDataReader(getDataFilePath("main-navigation-data.xlsx"), "pages_with_main_navigation",
				columns);
  }

}
