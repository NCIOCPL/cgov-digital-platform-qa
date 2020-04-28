package gov.cancer.tests.fields;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.PageWithPDQCitations;
import gov.cancer.pageobject.helper.PDQCitation;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

public class PDQCitations_Test extends TestObjectBase {

  /**
   * This method is checking if the PDQ Citation section exists on the pages
   *
   * @param path Path of the page to check.
   */
  @Test(dataProvider = "getPagesWithPDQCitationsPaths")
  public void verifyPDQCitationSectionIsPresent(String path) {

    TestRunner.run(PageWithPDQCitations.class, path, (PageWithPDQCitations page) -> {

      Assert.assertTrue(page.isSectionPresent(), "PDQ Citation Section is not present");

    });
  }

  /**
   * This method is checking if the header on the PDQ Citation is displayed on the
   * pages
   *
   * @param path Path of the page to check.
   */
  @Test(dataProvider = "getPagesWithPDQCitationsPaths")
  public void verifyPDQCitationHeaderIsVisible(String path) {

    TestRunner.run(PageWithPDQCitations.class, path, (PageWithPDQCitations page) -> {

      Assert.assertTrue(page.isHeaderPresent(), "PDQ Citation Header is not visible");
    });
  }

  /**
   * This method is checking if the correct PDQ Citation Header is displayed on
   * pages
   *
   * @param path               Path of the page to check.
   * @param expectedHeaderText the Header to check.
   */
  @Test(dataProvider = "getPDQCitationHeaderTextPaths", dependsOnMethods = {"verifyPDQCitationHeaderIsVisible"})
  public void isHeaderTextCorrect(String path, String expectedHeaderText) {

    TestRunner.run(PageWithPDQCitations.class, path, (PageWithPDQCitations page) -> {

      Assert.assertEquals(page.getHeaderText(), expectedHeaderText, "Error: Header text is not correct");

    });
  }

  /**
   * This method is checking if the length of text displayed on the PDQ citation
   * is in considerable range
   *
   * @param path Path of the page to check.
   * @param min  Minimum text length
   * @param max  Maximum text length
   */

  @Test(dataProvider = "getPagesWithPDQCitations", dependsOnMethods = "verifyTotalNumberofPDQCitationsInASection")
  public void verifyTextlengthIsCorrect(String path, int min, int max) {

    TestRunner.run(PageWithPDQCitations.class, path, (PageWithPDQCitations page) -> {
      List<PDQCitation> pdqCitationList = page.getPDQCitationList();
      // For each PDQ citation execute the following assertion.
      for (PDQCitation item : pdqCitationList) {
        Assert.assertTrue(item.getText().length() > min && item.getText().length() < max,
          "PDQcitation text is not in range of 10 to 500 chars");
      }
    });
  }

  /**
   * This method is checking the total number of PDQCitations in a section
   *
   * @param path                  Path of the page to check.
   * @param expectedCitationCount The expected number of PDQCitations in a section
   */

  @Test(dataProvider = "getPagesWithPDQCitationsForNumberCount")
  public void verifyTotalNumberofPDQCitationsInASection(String path, int expectedCitationCount) {

    TestRunner.run(PageWithPDQCitations.class, path, (PageWithPDQCitations page) -> {
      Assert.assertEquals(page.getPDQCitationList().size(), expectedCitationCount, "The Citation count does not match");
    });
  }

  /**
   * This method is checking if the PDQ citation list contains PubMed links
   *
   * @param path                    Path of the page to check.
   * @param expectedPubMedLinkCount The expected number of PubMed Link in a
   *                                section
   */

  @Test(dataProvider = "getCitationsWithPubMedLinks")
  public void verifyPubMedLinkExists(String path, int expectedPubMedLinkCount) {
    TestRunner.run(PageWithPDQCitations.class, path, (PageWithPDQCitations page) -> {
      Assert.assertEquals(page.getPubMedLinkCount(), expectedPubMedLinkCount);
    });

  }

  /**
   * This method is checking if the PubMed links does NOT exist in a PDQ citation
   *
   * @param path                           Path of the page to check.
   * @param expectedWithoutPubMedLinkCount The expected number of citations
   *                                       without PubMed links in a section
   */

  @Test(dataProvider = "getCitationsWithoutPubmedLink")
  public void verifyPubMedLinkDoesNotExist(String path, int expectedWithoutPubMedLinkCount) {
    TestRunner.run(PageWithPDQCitations.class, path, (PageWithPDQCitations page) -> {
      Assert.assertEquals(page.getPDQCitationList().size() - page.getPubMedLinkCount(), expectedWithoutPubMedLinkCount, "Error: Pub Med link does not exist");
    });
  }

  /**
   * This method is checking if the PubMed link point to correct PubMed URL
   *
   * @param path              Path of the page to check.
   * @param expectedPubMedUrl The expected Pub Med URL
   * @param index             The index to find the location of the Pub Med
   */

  @Test(dataProvider = "getPubMedLinkPaths")
  public void verifyPubMedLinks(String path, String expectedPubMedUrl, int index) {
    TestRunner.run(PageWithPDQCitations.class, path, (PageWithPDQCitations page) -> {
      PDQCitation pubmedCitation = page.getPDQCitationList().get(index);
      String str = pubmedCitation.getPubMedLink().getUrl().toString();
      Assert.assertEquals(str, expectedPubMedUrl, "Error:PubMed link does not point to correct Publink url");
    });
  }

  /**
   * This method is checking if the PDQ Citation section does not exist on the
   * pages
   *
   * @param path Path of the page to check.
   */
  @Test(dataProvider = "getPagesWithoutPDQCitations")
  public void verifyPDQCitationSectionIsNotPresent(String path) {

    TestRunner.run(PageWithPDQCitations.class, path, (PageWithPDQCitations page) -> {
      Assert.assertFalse(page.isSectionPresent(), "Error: PDQ Citation Section is present");

    });
  }

  /*******************************************
   * DATA PROVIDER
   *******************************************/

  /**
   * Retrieves a list of paths to pages which are expected to have PDQ Citations
   *
   * @return An iterator over a collection of object arrays containing the
   * requested data
   */

  @DataProvider(name = "getPagesWithPDQCitationsPaths")
  public Iterator<Object[]> getPagesWithPDQCitationsPaths() {
    String[] columns = {"path"};
    return new ExcelDataReader(getDataFilePath("pdq-citations-data.xlsx"), "pubmedlink_existance", columns);
  }

  @DataProvider(name = "getPagesWithPDQCitations")
  public Iterator<Object[]> getPagesWithPDQCitations() {
    String[] columns = {"path"};
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("pdq-citations-data.xlsx"), "pubmedlink_existance", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    int min = 10;
    int max = 500;
    while (values.hasNext()) {
      Object[] item = values.next();
      converted.add(new Object[]{item[0], min, max});
    }
    return converted.iterator();
  }

  /**
   * Retrieves a list of paths to pages which are expected to have PDQ Citations
   * Header Text
   *
   * @return An iterator over a collection of object arrays containing the
   * requested data
   */
  @DataProvider(name = "getPDQCitationHeaderTextPaths")
  public Iterator<Object[]> getCitationHeaderTextPaths() {
    String[] columns = {"path", "expectedHeaderText"};
    return new ExcelDataReader(getDataFilePath("pdq-citations-data.xlsx"), "pages_with_pdq_citations", columns);
  }

  /**
   * Retrieves a list of paths to pages which are expected to have PUBMED Links in
   * PDQ Citations //
   *
   * @return An iterator over a collection of object arrays containing the
   * requested data
   */
  @DataProvider(name = "getPubMedLinkPaths")
  public Iterator<Object[]> getPubMedLinkPaths() {
    String[] columns = {"path", "expectedPubMedUrl", "index"};
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("pdq-citations-data.xlsx"), "verify_pubmedlink", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String count = (String) item[2];
      int expCount = Integer.parseInt(count);
      converted.add(new Object[]{item[0], item[1], expCount});
    }
    return converted.iterator();
  }

  /**
   * Retrieves a list of paths to pages which are NOT expected to have PUBMED
   * Links in PDQ Citations
   *
   * @return An iterator over a collection of object arrays containing the
   * requested data
   */
  @DataProvider(name = "getCitationsWithPubMedLinks")
  public Iterator<Object[]> getCitationsWithPubMedLinks() {
    String[] columns = {"path", "expectedPubMedLinkCount"};

    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("pdq-citations-data.xlsx"), "pubmedlink_existance", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String count = (String) item[1];
      int expCount = Integer.parseInt(count);
      converted.add(new Object[]{item[0], expCount});
    }
    return converted.iterator();
  }

  /**
   * Retrieves a list of paths to pages which are NOT expected to have PUBMED
   * Links in PDQ Citations
   *
   * @return An iterator over a collection of object arrays containing the
   * requested data
   */
  @DataProvider(name = "getCitationsWithoutPubmedLink")
  public Iterator<Object[]> getCitationsWithoutPubmedLink() {
    String[] columns = {"path", "expectedWithoutPubMedLinkCount"};
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("pdq-citations-data.xlsx"), "pubmedlink_existance", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String count = (String) item[1];
      int expCount = Integer.parseInt(count);
      converted.add(new Object[]{item[0], expCount});
    }
    return converted.iterator();
  }

  /**
   * Retrieves a list of paths to pages which are NOT expected to have PDQ
   * Citations
   *
   * @return An iterator over a collection of object arrays containing the
   * requested data
   */
  @DataProvider(name = "getPagesWithoutPDQCitations")
  public Iterator<Object[]> getPagesWithoutPDQCitations() {
    String[] columns = {"path"};
    return new ExcelDataReader(getDataFilePath("pdq-citations-data.xlsx"), "pages_without_PDQCitations", columns);
  }

  /**
   * Retrieves a list of paths to pages which are expected to have PDQ Citations
   *
   * @return An iterator over a collection of object arrays containing the
   * requested data
   */
  @DataProvider(name = "getPagesWithPDQCitationsForNumberCount")
  public Iterator<Object[]> getPagesWithPDQCitationsForNumberCount() {
    String[] columns = {"path", "expectedCitationCount"};
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("pdq-citations-data.xlsx"), "pages_with_pdq_citations", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String count = (String) item[1];
      int expCount = Integer.parseInt(count);
      converted.add(new Object[]{item[0], expCount});
    }
    return converted.iterator();


  }
}
