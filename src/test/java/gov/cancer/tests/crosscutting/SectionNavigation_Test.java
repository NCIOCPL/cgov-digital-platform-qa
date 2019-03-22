package gov.cancer.tests.crosscutting;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.SectionNavigationPage;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

/**
 * Tests for pages with bread crumbs.
 */
public class SectionNavigation_Test extends TestObjectBase {

  /**
   * is section navigation displayed on pages where expected?
   *
   * @param path Path of the page to check.
   */
	@Test(dataProvider = "getSectionNavigationPaths")
	public void sectionNavigationIsVisible(String path) {
		
		TestRunner.run(SectionNavigationPage.class, path, (SectionNavigationPage page) -> {

			Assert.assertTrue(page.isSectionNavigationVisible(), "Section Navigation is visible.");

    });

  }


  /**
   * Retrieves a list of paths to pages which are expected to have section navigation.
   *
   * @return An iterable list of single element arrays, each containing a single
   * path.
   */
	@DataProvider(name = "getSectionNavigationPaths")
	public Iterator<Object[]> getSectionNavigationPaths() {
    String[] columns = {"path"};
		return new ExcelDataReader(getDataFilePath("section-navigation-data.xlsx"), "pages_with_section_navigation",
				columns);
  }

}
