package gov.cancer.tests.crosscutting;

import java.util.Iterator;

import gov.cancer.pageobject.leftnavigation.NavItem;
import gov.cancer.pageobject.leftnavigation.PageWithSectionNav;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

public class SiteNavigation_Test extends TestObjectBase {

  /**
   * This method is verifying root level of left navigation - parent node and it's immediate children.
   * Verifying current node's label matches expected and children are visible.
   * For this test, test data provides paths to pages on which parent node HAS children elements.
   * @param path url
   * @param parentLabel is the Root node label
   */
  @Test(dataProvider = "getPagesOnRootLevel")
  public void verifyVisibility(String path, String parentLabel) {

      TestRunner.run(PageWithSectionNav.class, path, (PageWithSectionNav page) -> {
      //verify current node label is matching
      Assert.assertEquals(page.getCurrentNavItem().getLabel(), parentLabel, "current node label matches");
        if (page.getCurrentNavItem().hasChildren()) { //<--TODO this condition is added due to limited test data (delete later)
          for (NavItem child1 : page.getCurrentNavItem().getChildren()) {
            //verify each child of root is visible
            Assert.assertTrue(child1.isVisible(), "child element is visible.");
          }
        }
    });
  }

  /**
   * This method is verifying invisibility of grandchildren of the current node.
   * Test data will provide only urls of pages with at at least three levels of navigation.
   * @param path
   */
  @Test(dataProvider = "getPagesWithInvisibleChildren")
  public void verifyInvisibility(String path) {

    TestRunner.run(PageWithSectionNav.class, path, (PageWithSectionNav page) -> {
     for (NavItem child :page.getCurrentNavItem().getChildren()) {
         for (NavItem grandChild : child.getChildren()){
           //verify that grandChild element is not visible
           Assert.assertFalse(grandChild.isVisible(), "grandChild element is not visible");
         }
     }
    });
  }

  /**
   * The method is verifying the expected number of child elements for each navigation node
   * @param path url
   * @param childCount expected number of child elements
   */
  @Test (dataProvider = "getCountOfNodes")
  public void verifyNumberOfChildren (String path, String childCount){
    TestRunner.run(PageWithSectionNav.class, path, (PageWithSectionNav page) -> {
      //verify expected number of children of current node
      Assert.assertEquals(page.getCurrentNavItem().getChildren().size(),Integer.parseInt(childCount), "number of children matches");
    });
  }

  /**This method is verifying that the neighbouring nodes are collapsed, whilst parent node of current element is expanded
   * TODO uncomment assertion when enough test data present
   * @return
   */
  @Test(dataProvider = "getNeighbourNode")
  public void verifyCollapsedLevel(String path, String parentLabel, String neighborLabel) {

    TestRunner.run(PageWithSectionNav.class, path, (PageWithSectionNav page) -> {
      //verify that parent node is expanded
      Assert.assertTrue(page.isExpanded(page.findItemByLabel(parentLabel)), "current node is expanded");
      //verify that the neighbouring nodes are not expanded
      //TODO Below assertions is a mandatory part of test - commented out due to limited test data - uncomment later!
     // Assert.assertFalse(page.isExpanded(page.findItemByLabel(neighborLabel)),"neighbour node is collapsed");
    });
  }

  @DataProvider(name = "getPagesOnRootLevel")
  public Iterator<Object[]> getPagesOnRootLevel() {
    String[] columns = {"path","parentLabel"};
    return new ExcelDataReader(getDataFilePath("site-navigation-data.xlsx"), "pages_on_root_level", columns);
  }

  @DataProvider(name = "getCountOfNodes")
  public Iterator<Object[]> getCountOfNodes() {
    String[] columns = {"path","childCount"};
    return new ExcelDataReader(getDataFilePath("site-navigation-data.xlsx"), "count_of_nodes", columns);
  }

  @DataProvider(name = "getNeighbourNode")
  public Iterator<Object[]> getNeighbourNode() {
    String[] columns = {"path","parentLabel", "neighborLabel"};
    return new ExcelDataReader(getDataFilePath("site-navigation-data.xlsx"), "pages_with_neighbour_node", columns);
  }

  @DataProvider(name = "getPagesWithInvisibleChildren")
  public Iterator<Object[]> getPagesWithInvisibleChildren() {
    String[] columns = {"path"};
    return new ExcelDataReader(getDataFilePath("site-navigation-data.xlsx"), "pages_with_invisible_children", columns);
  }

}



