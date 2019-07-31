package gov.cancer.tests.metaData;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.PageWithMetaData;
import gov.cancer.pageobject.helper.MetaTag;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Iterator;

/**
 * Class contains tests related to DCTerms metatag content
 */
public class MetaData_DCTerms_Test extends TestObjectBase {
  /**
   * Asserts 'dcterms.coverage' metatag exist on the page.
   *
   * @param path Path of the page to check.
   */
  @Test(dataProvider = "getPagesWithDCTerms")
  public void verifyPagesWithDCTermsCoverage(String path, String dcTermsCoverage) {

    TestRunner.run(PageWithMetaData.class, path, (PageWithMetaData page) -> {
      Assert.assertEquals(page.getDCTermCoverage().getContent(), dcTermsCoverage, "not found 'dcterms.coverage' metatag");
    });
  }

  /**
   * This method is checking if 'dcterms.isPartOf' matatag name matches
   * dcTermsPartOf from data provider
   *
   * @param path          Path of the page to check.
   * @param dcTermsPartOf Label of dcTermsPartOf metatag.
   *
   */
  @Test(dataProvider = "getPagesWithdcTermsPartOf")
  public void verifyPagesWithDCTermsPartOf(String path, String dcTermsPartOf) {

    TestRunner.run(PageWithMetaData.class, path, (PageWithMetaData page) -> {
      Assert.assertEquals(page.getDCTermsPartOf().getContent(), dcTermsPartOf, "'part of' content is not correct");
    });
  }

  /**
   * This method is checking if 'dcterms.issued' matatag name matches
   * issuedDate format from data provider
   *
   * @param path       Path of the page to check.
   * @param issuedDate Label of issuedDate metatag.
   *
   */
  @Test(dataProvider = "getPagesWithDCTermsIssuedDate")
  public void verifyPagesWithDCTermsIssuedDate(String path, String issuedDate) {

    TestRunner.run(PageWithMetaData.class, path, (PageWithMetaData page) -> {
      Assert.assertTrue(page.getDCTermsIssuedDate().getContent().matches(issuedDate), "issue date format is not correct");
    });
  }

  /**
   * Asserts if 'dcterms.isReferencedBy' metatag exist on the page.
   *
   * @param path Path of the page to check.
   */
  @Test(dataProvider = "getPagesWithDCTermsReferencedBy")
  public void verifyPagesWithDCTermsReferencedBy(String path, String referencedBy) {

    TestRunner.run(PageWithMetaData.class, path, (PageWithMetaData page) -> {
      Assert.assertEquals(page.getDCTermsReferencedBy().getContent(), referencedBy, "not found 'isReferencedBy' metatag");
    });
  }

  /**
   * This method is checking if 'dcterms.subject' matatag name matches
   * dcTermsSubject from data provider
   *
   * @param path           Path of the page to check.
   * @param dcTermsSubject Label of dcTermsSubject metatag.
   *
   */
  @Test(dataProvider = "getPagesWithdcTermsSubject")
  public void verifyPagesWithDCTermsSubject(String path, String dcTermsSubject) {

    TestRunner.run(PageWithMetaData.class, path, (PageWithMetaData page) -> {
      Assert.assertEquals(page.getDCTermsSubject().getContent(), dcTermsSubject, "'dcTermsSubject' content is not correct'");
    });
  }

  /**
   * This method is checking if 'dcterms.type' matatag name matches
   * dcTermsType from data provider
   *
   * @param path           Path of the page to check.
   * @param dcTermsType    Label of dcTermsType metatag.
   *
   */
  @Test(dataProvider = "getPagesWithdcTermType")
  public void verifyPagesWithDCTermsType(String path, String dcTermsType) {

    TestRunner.run(PageWithMetaData.class, path, (PageWithMetaData page) -> {
      Assert.assertEquals(page.getDCTermsType().getContent(), dcTermsType, "dcTermsType content is not correct");
    });
  }

  /************** Data Providers *************/
  /**
   * Retrieves a list of paths to pages which are expected to have meta
   * name="dcterms.type"
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getPagesWithdcTermType")
  public Iterator<Object[]> getPagesWithdcTermType() {
    String[] columns = { "path", "dcTermsType" };
    return new ExcelDataReader(getDataFilePath("meta_data.xlsx"), "pages_with_meta", columns);
  }

  /**
   * Retrieves a list of paths to pages which are expected to have meta
   * name="dcterms.subject"
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getPagesWithdcTermsSubject")
  public Iterator<Object[]> getPagesWithdcTermsSubject() {
    String[] columns = { "path", "dcTermsSubject" };
    return new ExcelDataReader(getDataFilePath("meta_data.xlsx"), "pages_with_meta", columns);
  }

  /**
   * Retrieves a list of paths to pages which are expected to have meta
   * name="dcterms.isReferencedBy"
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getPagesWithDCTerms")
  public Iterator<Object[]> getPagesWithDCTerms() {
    String[] columns = { "path", "dcTermsCoverage" };
    return new ExcelDataReader(getDataFilePath("meta_data.xlsx"), "pages_with_meta", columns);
  }

  /**
   * Retrieves a list of paths to pages which are expected to have Include
   * name="dcterms.issued"
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getPagesWithDCTermsIssuedDate")
  public Iterator<Object[]> getPagesWithDCTermsIssuedDate() {
    String[] columns = { "path", "issuedDate" };
    return new ExcelDataReader(getDataFilePath("meta_data.xlsx"), "pages_with_meta_Issued_date", columns);
  }

  /**
   * Retrieves a list of paths to pages which are expected to have meta
   * name="dcterms.isPartOf"
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getPagesWithdcTermsPartOf")
  public Iterator<Object[]> getPagesWithdcTermsPartOf() {
    String[] columns = { "path", "dcTermsPartOf" };
    return new ExcelDataReader(getDataFilePath("meta_data.xlsx"), "pages_with_meta", columns);
  }

  /**
   * Retrieves a list of paths to pages which are expected to have meta
   * name="dcterms.ReferencedBy"
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */

  @DataProvider(name = "getPagesWithDCTermsReferencedBy")
  public Iterator<Object[]>  getPagesWithDCTermsReferencedBy() {
    String[] columns = { "path", "dcTermsReferencedBy" };
    return new ExcelDataReader(getDataFilePath("meta_data.xlsx"), "pages_with_meta", columns);
  }


}




