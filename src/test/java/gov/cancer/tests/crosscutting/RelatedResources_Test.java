package gov.cancer.tests.crosscutting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.RelatedResourcesPage;
import gov.cancer.pageobject.helper.RelatedResource;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

/**
 * Test Case: Verify Related Resources section and links
 * 
 * Test scenarios: Assert Related Resources section exists on the page Assert
 * Related Resources internal links exists on the page Assert non-zero number of
 * Related Resources internal links are present. Assert all Related Resources
 * internal links have non-blank hrefs. Assert all Related Resources internal
 * link hrefs contain no whitespace. (i.e. no leading/trailing/embedded spaces,
 * tabs, new lines.) Assert all Related Resources external links have “Exit
 * Disclaimer” styling
 * 
 * Negative Tests: 8 Assert no Related Resources section/links appear on the
 * page
 */

public class RelatedResources_Test extends TestObjectBase {

  /**
   * 
   * This method is checking if Related Resources section is visible on the page
   *
   * @param path Path of the page to check.
   *
   */
  @Test(dataProvider = "getPagesWithRelatedResources")
  public void verifyRelatedResourcesSectionAppears(String path) {

    TestRunner.run(RelatedResourcesPage.class, path, (RelatedResourcesPage page) -> {

      // Assert the related resources section is visible.
      Assert.assertTrue(page.hasRelatedResources(), "Related Resources section is visible");

    });
  }

  /**
   * 
   * This method is checking all of the related resource objects on the page and
   * verifying link Text/HREF is not blank
   *
   * @param path Path of the page to check.
   *
   */
  @Test(dataProvider = "getPagesWithRelatedResources")
  public void verifyRelatedResourcesLinks(String path) {

    TestRunner.run(RelatedResourcesPage.class, path, (RelatedResourcesPage page) -> {

      List<RelatedResource> resources = page.getRelatedResources();

      // Are there any related resources link?
      // If there are no links at all no statement will get executed after this line
      Assert.assertTrue(resources.size() > 0);

      // For each resource execute the following assertions.
      for (RelatedResource item : resources) {

        // Does this link have 'a' tag?
        Assert.assertTrue(item.isLinkElement(), "Does this link have 'a' tag.");

        // Does it have non-blank text?
        Assert.assertTrue(item.isLinkTextBlank(), "Is not blank.");

        // Does it have a non-empty href?
        Assert.assertTrue(item.isLinkHrefBlank(), "HREF Is not blank.");

      }

    });
  }

  /**
   * 
   * This method is to check each Related Resource link which does not end with
   * .gov is treated as external link. Test will assert exit disclaimer icon
   * displays for each External link
   *
   * @param path                  Path of the page to check.
   * @param expectedExternalCount Expected External Count
   */
  @Test(dataProvider = "getPagesWithExternalResources")
  public void verifyExitDisclaimerVisible(String path, int expectedExternalCount) {

    // Get the page.
    TestRunner.run(RelatedResourcesPage.class, path, (RelatedResourcesPage page) -> {

      List<RelatedResource> externalLinks = page.getExternalResources();

      Assert.assertEquals(externalLinks.size(), expectedExternalCount);

      for (RelatedResource item : externalLinks) {
        // does Exit disclaimer icon appears next to external link
        Assert.assertTrue(item.hasExitDisclaimer(), "Exit Disclaimer.");

      }

    });
  }

  /**
   * 
   * This method is checking all pages that does not display related resources
   * section
   *
   * @param path Path of the page to check.
   * 
   */
  @Test(dataProvider = "getPagesWithoutRelatedResources")
  public void verifyRelatedResourcesSectionDoesNotAppear(String path) {

    // Get the page.
    TestRunner.run(RelatedResourcesPage.class, path, (RelatedResourcesPage page) -> {

      // Assert the related resources section is NOT visible.
      Assert.assertFalse(page.hasRelatedResources(), "Page has no Related Resources section.");

    });
  }

  /**
   * 
   * This method is to check each Related Resource link which does not end with
   * .gov is treated as external link. Test will assert exit disclaimer icon
   * displays for each External link
   *
   * @return path Returns a list of paths for pages which are expected to display
   *         a related resources section.
   * @return expectedExternalCount Expected External Count
   */
  @DataProvider(name = "getPagesWithExternalResources")
  public Iterator<Object[]> getPagesWithExternalResources() {
    String[] columns = { "path", "expectedExternalCount" };

    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("related-resources-data.xlsx"),
        "pages_with_external_resources", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    //
    while (values.hasNext()) {
      Object[] item = values.next();
      String raw = (String) item[1];

      int expected = Integer.parseInt(raw);

      converted.add(new Object[] { item[0], expected });
    }

    return converted.iterator();
  }

  /**
   * 
   * @return path Returns a list of paths for pages which are expected to display
   *         a related resources section and/or links.
   */
  @DataProvider(name = "getPagesWithRelatedResources")
  public Iterator<Object[]> getPagesWithRelatedResources() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("related-resources-data.xlsx"), "pages_with_related_resources", columns);
  }

  /**
   * 
   * @return path Returns a list of paths for pages which Does Not expected to
   *         display a related resources links.
   */
  @DataProvider(name = "getPagesWithoutRelatedResources")
  public Iterator<Object[]> getPagesWithoutRelatedResources() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("related-resources-data.xlsx"), "pages_without_related_resources",
        columns);
  }

}