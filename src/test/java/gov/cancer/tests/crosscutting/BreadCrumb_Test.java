package gov.cancer.tests.crosscutting;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.BreadCrumbPage;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

/**
 * Tests for pages with bread crumbs.
 */
public class BreadCrumb_Test extends TestObjectBase {

  /**
   * Are breadcrumbs displayed on pages where they're expected?
   *
   * @param path Path of the page to check.
   */
  @Test(dataProvider = "getBreadCrumbPaths")
  public void breadcrumbIsVisible(String path){

    TestRunner.run(BreadCrumbPage.class, path, (BreadCrumbPage page)->{

      Assert.assertTrue(page.isBreadCrumbVisible(), "Bread crumb is visible.");

    });

  }


  /**
   * Retrieves a list of paths to pages which are expected to have breadcrumbs.
   *
   * @return An iterable list of single element arrays, each containing a single
   * path.
   */
  @DataProvider(name = "getBreadCrumbPaths")
  public Iterator<Object[]> getBreadCrumbPaths(){
    String[] columns = {"path"};
    return new ExcelDataReader(getDataFilePath("bread-crumb-data.xlsx"), "pages_with_breadcrumbs", columns);
  }

}
