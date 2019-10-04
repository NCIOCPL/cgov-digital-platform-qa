package gov.cancer.tests.fields;

import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.PageWithCitations;
import gov.cancer.pageobject.helper.Citation;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

public class Citations_Test extends TestObjectBase {

  /**
   * This method is checking if the Citation section exists on the pages
   *
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPagesWithCitations")
  public void verifyCitationSectionIsPresent(String path) {

    TestRunner.run(PageWithCitations.class, path, (PageWithCitations page) -> {

      Assert.assertTrue(page.isSectionPresent(), "Citation Section is present");

    });

  }

  /**
   * This method is checking if the header on the Citation is displayed on the
   * pages
   *
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPagesWithCitations")
  public void verifyCitationHeaderIsVisible(String path) {

    TestRunner.run(PageWithCitations.class, path, (PageWithCitations page) -> {

      Assert.assertTrue(page.isHeaderPresent(), "Citation Header is visible");
    });
  }

  /**
   * This method is checking if the correct Citation Header is displayed on pages
   *
   * @param path
   *          Path of the page to check.
   * @param expectedHeaderText
   *          the Header to check.
   */
  @Test(dataProvider = "getCitationHeaderTextPaths")
  public void isHeaderTextCorrect(String path, String expectedHeaderText) {

    TestRunner.run(PageWithCitations.class, path, (PageWithCitations page) -> {

      Assert.assertEquals(page.getHeaderText(), expectedHeaderText, "Citation header text is correct");

    });
  }

  /**
   * This method is checking if the length of text displayed on the citation is in
   * considerable range
   *
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPagesWithCitations")
  public void verifyTextlengthIsCorrect(String path) {

    TestRunner.run(PageWithCitations.class, path, (PageWithCitations page) -> {
      List<Citation> citationList = page.getCitationList();

      // Are there any Citations in the citations section?
      // If there are no citations at all no statement will get executed after this
      // line
      Assert.assertTrue(citationList.size() > 0);

      // For each citation execute the following assertion.
      for (Citation item : citationList) {

        Assert.assertTrue(item.getText().length() > 20 && item.getText().length() < 300,
            "citation text is in range of 20 to 300 chars");
      }
    });
  }

  /**
   * This method is checking if the citation list contains PubMed link
   *
   * @param path
   *          Path of the page to check.
   */

  @Test(dataProvider = "getPagesWithCitations")
  public void verifyPubMedLinkExists(String path) {
    TestRunner.run(PageWithCitations.class, path, (PageWithCitations page) -> {
      List<Citation> citationList = page.getCitationList();
      Citation pubmedCitation = citationList.get(2);

      Assert.assertTrue(pubmedCitation.hasPubMedLink(), "PubMed Link is Present");

    });
  }

  /**
   * This method is checking if the PubMed link does NOT exist in a citation
   *
   * @param path
   *          Path of the page to check.
   */

  @Test(dataProvider = "getPagesWithCitations")
  public void verifyPubMedLinkDoesNotExist(String path) {
    TestRunner.run(PageWithCitations.class, path, (PageWithCitations page) -> {
      List<Citation> citationList = page.getCitationList();
      Citation plainCitation = citationList.get(0);
      Assert.assertFalse(plainCitation.hasPubMedLink(), "Error: PubMed Link is Present");

    });
  }

  /**
   * This method is checking if the PubMed link point to PubMed URL
   *
   * @param path
   *          Path of the page to check.
   * @param expectedPubMedUrl
   *          The expected Pub Med URL
   */

  @Test(dataProvider = "getPubMedLinkPaths")
  public void verifyPubMedLinks(String path, String expectedPubMedUrl) {
    TestRunner.run(PageWithCitations.class, path, (PageWithCitations page) -> {
      List<Citation> citationList = page.getCitationList();
      Citation pubmedCitation = citationList.get(2);
      String str = pubmedCitation.getPubMedLink().getUrl().toString().trim();
      Assert.assertEquals(str, expectedPubMedUrl, "PubMed link points to PubMed url");
    });
  }

  /**
   * This method is checking if the Citation section does not exist on pages
   *
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPagesWithoutCitations")
  public void verifyCitationSectionAbsent(String path) {

    TestRunner.run(PageWithCitations.class, path, (PageWithCitations page) -> {

      Assert.assertFalse(page.isSectionPresent(), "Error: Citation Section is present");

    });

  }

  /*******************************************
   * DATA PROVIDER
   *******************************************/

  /**
   * Retrieves a list of paths to pages which are expected to have Citations
   *
   * @return An iterable list of two element arrays, each containing a path and
   *         language.
   */
  @DataProvider(name = "getPagesWithCitations")
  public Iterator<Object[]> getPagesWithCitations() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("citations-data.xlsx"), "pages_with_citations", columns);

  }

  /**
   * Retrieves a list of paths to pages which are expected to have Citations
   *
   * @return An iterable list of two element arrays, each containing a path and
   *         language.
   */
  @DataProvider(name = "getCitationHeaderTextPaths")
  public Iterator<Object[]> getCitationHeaderTextPaths() {
    String[] columns = { "path", "expectedHeaderText" };
    return new ExcelDataReader(getDataFilePath("citations-data.xlsx"), "pages_with_citations", columns);

  }

  /**
   * Retrieves a list of paths to pages which are expected to have Citations
   *
   * @return An iterable list of two element arrays, each containing a path and
   *         language.
   */
  @DataProvider(name = "getPubMedLinkPaths")
  public Iterator<Object[]> getPubMedLinkPaths() {
    String[] columns = { "path", "expectedPubMedUrl" };
    return new ExcelDataReader(getDataFilePath("citations-data.xlsx"), "pages_with_citations", columns);

  }

  /**
   * Retrieves a list of paths to pages which are expected NOT to have Citations
   *
   * @return An iterable list of two element arrays, each containing a path and
   *         language.
   */
  @DataProvider(name = "getPagesWithoutCitations")
  public Iterator<Object[]> getPagesWithoutCitations() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("citations-data.xlsx"), "pages_without_citations", columns);

  }
}
