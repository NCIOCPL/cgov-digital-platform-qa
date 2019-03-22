package gov.cancer.tests.crosscutting;

import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.UtilityNavigationPage;
import gov.cancer.pageobject.helper.UtilityNavigation;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

/**
 * Tests for pages with bread crumbs.
 */
public class UtilityNavigation_Test extends TestObjectBase {

  /**
   * is section navigation displayed on pages where expected?
   *
   * @param path Path of the page to check.
   */
	@Test(dataProvider = "getutilityNavigationPaths", priority = 1 )
	public void utilityNavigationIsVisible(String path) {
	
		TestRunner.run(UtilityNavigationPage.class, path, (UtilityNavigationPage page) -> {

			Assert.assertTrue(page.isUtilityNavigationVisible(), "Section Navigation is visible.");

    });

  }

	
	@Test(dataProvider = "getutilityNavigationPaths",  priority = 2)
	  public void verifyutilityNavigationLinks(String path) {

	    // Get the page.
	    TestRunner.run(UtilityNavigationPage.class, path, (UtilityNavigationPage page) -> {

	      List<UtilityNavigation> utility = page.getutilityNavigation();

	      // Are there any related resources?
	      Assert.assertTrue(utility.size() > 0);

	      // For each resource.
	      for (UtilityNavigation item : utility) {

	        // Is it a link?
	        Assert.assertTrue(item.isLinkElement(), "Is a link tag.");

	        // Does it have non-blank text?
	        Assert.assertTrue(item.isLinkTextBlank(), "Is not blank.");
	        
	        // Does it have a non-empty href?
	        Assert.assertTrue(item.isLinkHrefBlank(), "HREF Is not blank.");
	        
	        // Does it have broken links?
	        Assert.assertTrue(item.isLinkBroken(), "Links not broken.");
	        


	        
	      }

	    });
	  }

  /**
   * Retrieves a list of paths to pages which are expected to have section navigation.
   *
   * @return An iterable list of single element arrays, each containing a single
   * path.
   */
	@DataProvider(name = "getutilityNavigationPaths")
	public Iterator<Object[]> getutilityNavigationPaths() {
    String[] columns = {"path"};
		return new ExcelDataReader(getDataFilePath("utility-navigation-data.xlsx"), "pages_with_utility_navigation",
				columns);
  }

}
