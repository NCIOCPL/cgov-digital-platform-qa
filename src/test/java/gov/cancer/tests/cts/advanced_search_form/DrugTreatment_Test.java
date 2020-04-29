package gov.cancer.tests.cts.advanced_search_form;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.framework.QueryParametersComparator;
import gov.cancer.pageobject.cts.AdvancedSearchPage;
import gov.cancer.pageobject.cts.SearchNavigationResult;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * This test class contains all tests related to the DrugTreatment section on the Advanced Search Form
 */

public class DrugTreatment_Test extends TestObjectBase {
  /**
   * Verify that DrugTreatment Section is visible parameters
   *
   * @param path
   *          url
   *
   */
  @Test(dataProvider = "getDrug")
  public void verifyDrugTreatmentSectionIsVisible(String path) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {

      Assert.assertTrue(page.getDrugTreatmentSection().isVisible(), "DrugTreatment Section is not visible");

    });
  }


  /**
   * Verify the Placeholder Text for Drug/Drug Family and Other Treatments fields
   *
   * parameters
   * @param path
   *          url
   * @param expectedDrugText
   *          placeholder text for Drug field
   * @param expectedTreatmentText
   *          placeholder text for Treatment field
   */
  @Test(dataProvider = "getPlaceHolderText")
  public void verifyDrugSectionPlaceholderText(String path, String expectedDrugText, String expectedTreatmentText) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {

      Assert.assertEquals(page.getDrugTreatmentSection().getDrugAutoSuggest().getPlaceholderText(), expectedDrugText,
          "Drug field placeholder text does not match");
      Assert.assertEquals(page.getDrugTreatmentSection().getTreatmentAutoSuggest().getPlaceholderText(), expectedTreatmentText,
        "Treatment field placeholder text does not match");
          });
  }

  /**
   * When user inputs multiple values for Drug and clicks Find Trials button, correct results are
   * displayed. Assertion is performed by verifying that url contains search
   * parameters
   *
   * @param path
   *          url
   * @param drugNames
   *          first Drug value
   * @param queryParam
   *          list of query parameters
   */

  @Test(dataProvider = "getDrugMultiple")
  public void verifyDrugField(String path, List<String> drugNames, String queryParam) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
        for (String str : drugNames){
          page.getDrugTreatmentSection().getDrugAutoSuggest().selectItem(str);
      }
      // create a navigation event redirect to search results page
      SearchNavigationResult search_result = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(search_result.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(queryParam)," query param does not match for Drug");
          });
  }
  /**
   * When user inputs Treatment and clicks Find Trials button, correct results are
   * displayed. Assertion is performed by verifying that url contains search
   * parameters
   *
   * @param path
   *          url
   * @param treatmentNames
   *          Treatment value
   * @param queryParam
   *          list of query parameters
   */

  @Test(dataProvider = "getTreatmentMultiple")
  public void verifyTreatmentField(String path, List<String> treatmentNames, String queryParam) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
        for (String str : treatmentNames) {
          page.getDrugTreatmentSection().getTreatmentAutoSuggest().selectItem(str);
        }
      // create a navigation event redirect to search results page
      SearchNavigationResult search_result = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(search_result.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(queryParam)," query param does not match for Treatment");
    });
  }

  /************************
   * SUBMIT SEARCH WITH HITTING ENTER KEY
   ***********************/

  /**
   * When user inputs Drug ID and hits enter, correct results are displayed.
   * Assertion is performed by verifying that url contains search parameters
   *
   * @param path
   *          url
   * @param drugNames
   *          Drug value
   * @param queryParam
   *          list of query parameters
   */
  @Test(dataProvider = "getDrugMultiple")
  public void verifyDrugTextFieldWithEnter(String path, List<String> drugNames, String queryParam) {

    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {

      for (String str : drugNames){
        page.getDrugTreatmentSection().getDrugAutoSuggest().selectItem(str);
      }
      // create a navigation event redirect to search results page
      SearchNavigationResult search_result = page.hitEnterKey(page.getDrugTreatmentSection().getDrugAutoSuggest());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(search_result.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(queryParam)," query param does not match for Drug");
       });
  }


  /**
   * When user inputs Treatment and hits enter, correct results are displayed.
   * Assertion is performed by verifying that url contains search parameters
   *
   * @param path
   *          url
   * @param treatmentNames
   *          Treatment value
   * @param queryParam
   *          list of query parameters
   */
  @Test(dataProvider = "getTreatmentMultiple")
  public void verifyTreatmentTextFieldWithEnter(String path,List<String> treatmentNames, String queryParam) {

    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
        for (String str : treatmentNames) {
          page.getDrugTreatmentSection().getTreatmentAutoSuggest().selectItem(str);
        }
      // create a navigation event redirect to search results page
      SearchNavigationResult search_result = page.hitEnterKey(page.getDrugTreatmentSection().getTreatmentAutoSuggest());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(search_result.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(queryParam)," query param does not match for Treatment");
    });
  }
  /**
   * When user inputs keyword in the Drug field and no matching results are found, correct message "No available options found.  Your search will be based on the text above." is displayed.
   * Assertion is performed by verifying the message
   *
   * @param path   url
   * @param drug   value for Drug
   * @param noOptionsMessage    The message for no available options
   */
  @Test(dataProvider = "getDrugNegative")
  public void verifyDrugNoOptionsMsg(String path, String drug, String noOptionsMessage) {

    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getDrugTreatmentSection().getDrugAutoSuggest().enterText(drug);
      //assert that message displayed is correct
      Assert.assertEquals(page.getDrugTreatmentSection().getDrugAutoSuggest().getSectionFieldMessage(), noOptionsMessage, "Correct message is not displayed for No available options");
    });
  }
  /**
   * When user inputs keyword in the Other Treatment field and no matching results are found, correct message "No available options found.  Your search will be based on the text above." is displayed.
   * Assertion is performed by verifying the message
   *
   * @param path         url
   * @param treatment    value for Treatment
   * @param noOptionsMessage    The message for no available options
   */
  @Test(dataProvider = "getTreatmentNegative")
  public void verifyTreatmentNoOptionsMsg(String path, String treatment, String noOptionsMessage) {

    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getDrugTreatmentSection().getTreatmentAutoSuggest().enterText(treatment);
      //assert that message displayed is correct
      Assert.assertEquals(page.getDrugTreatmentSection().getTreatmentAutoSuggest().getSectionFieldMessage(), noOptionsMessage, "Correct message is not displayed for No available options");
    });
  }

  /**
   * When user clicks in the Drug field, correct message "Please enter 3 or more characters" is displayed.
   * Assertion is performed by verifying the message
   *
   * @param path   url
   *
   * @param enterMessage    The message for Enter characters
   */
  @Test(dataProvider = "getDrugMessage")
  public void verifyDrugEnterMessage(String path, String enterMessage) {

    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {

      page.getDrugTreatmentSection().getDrugAutoSuggest().setFocus();
     //assert that message displayed is correct
      Assert.assertEquals(page.getDrugTreatmentSection().getDrugAutoSuggest().getSectionFieldMessage(), enterMessage, "Correct message is not displayed for Enter characters");
    });
  }
  /**
   * When user clicks in the Treatment field, correct message "Please enter 3 or more characters" is displayed.
   * Assertion is performed by verifying the message
   *
   * @param path       url
   *
   * @param enterMessage    The message for no available options
   */
  @Test(dataProvider = "getTreatmentMessage")
  public void verifyTreatmentEnterMessage(String path, String enterMessage) {

    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getDrugTreatmentSection().getTreatmentAutoSuggest().setFocus();
      //assert that message displayed is correct
      Assert.assertEquals(page.getDrugTreatmentSection().getTreatmentAutoSuggest().getSectionFieldMessage(), enterMessage, "Correct message is not displayed for Enter characters");
    });
  }
  /**
   * When user inputs Drug and Treatment values and clicks Clear Form button, Drug and Treatment fields are cleared. Assertion is performed by verifying that the fields are empty
   * parameters
   *
   * @param path
   *          url
   * @param drugNames
   *          Drug value
   * @param treatmentNames
   *          Treatment value
   */

  @Test(dataProvider = "getDrugTreatment")
  public void verifyClearFormButton(String path, List<String> drugNames, List<String> treatmentNames) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
        for (String str : drugNames) {
          page.getDrugTreatmentSection().getDrugAutoSuggest().selectItem(str);
        }

        for (String string : treatmentNames) {
          page.getDrugTreatmentSection().getTreatmentAutoSuggest().selectItem(string);
        }
      page.getFormAction().getClearFormButton().click();
      // initialize all the form elements after clicking Clear Form button
      page.doPageInitialization();

      //assert that Drug and Treatment fields are empty
      Assert.assertTrue(page.getDrugTreatmentSection().getDrugAutoSuggest().getText().isEmpty(),  "Field is not empty");
      Assert.assertTrue(page.getDrugTreatmentSection().getTreatmentAutoSuggest().getText().isEmpty(),  "Field is not empty");
       });
  }

  /************************************************************
   * Data providers
   ************************************************************/

  /**
   * Data provider retrieves paths to Advanced Search page, Drug value
   *
   */

  @DataProvider(name = "getDrug")
  public Iterator<Object[]> getDrug() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_drug_data.xlsx"), "advanced_search", columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page, Drug 1 and Drug 2 values
   *
   */
   @DataProvider(name = "getDrugMultiple")
  public Iterator<Object[]> getDrugMultiple() {
    String[] columns = { "path", "Drug","ParamDrug"};
    Iterator<Object[]> data = new ExcelDataReader(getDataFilePath("cts_advanced_search_drug_data.xlsx"), "advanced_search", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    List<String> drugs;
    while (data.hasNext()) {
      Object[] item = data.next();
      String rawDrugs = (String) item[1];
      String rawDrugIDs = (String) item[2];
      drugs = Arrays.asList(rawDrugs.split(","));
      converted.add(new Object[]{item[0], drugs, item[2]});
    }
    return converted.iterator();
      }
  /**
   * Data provider retrieves paths to Advanced Search page, Treatment values
   *
   */

  @DataProvider(name = "getTreatmentMultiple")
  public Iterator<Object[]> getTreatmentMultiple() {
    String[] columns = { "path", "Treatment","ParamTreatment"};
    Iterator<Object[]> data = new ExcelDataReader(getDataFilePath("cts_advanced_search_drug_data.xlsx"), "advanced_search", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    List<String> treatments;
    while (data.hasNext()) {
      Object[] item = data.next();
      String rawTreatments = (String) item[1];
      treatments = Arrays.asList(rawTreatments.split(","));
      converted.add(new Object[]{item[0], treatments, item[2]});
    }
    return converted.iterator();
  }
  /**
   * Data provider retrieves paths to Advanced Search page, Drug value, enter characters message
   *
   */

  @DataProvider(name = "getDrugMessage")
  public Iterator<Object[]> getDrugMessage() {
    String[] columns = { "path", "EnterMessage" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_drug_data.xlsx"), "advanced_search_negative", columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page, Treatment value, enter characters message
   *
   */

  @DataProvider(name = "getTreatmentMessage")
  public Iterator<Object[]> getTreatmentMessage() {
    String[] columns = { "path", "EnterMessage" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_drug_data.xlsx"), "advanced_search_negative", columns);
  }
  /**
   * Data provider retrieves paths to Advanced Search page, Drug value, error message
   *
   */

  @DataProvider(name = "getDrugNegative")
  public Iterator<Object[]> getDrugNegative() {
    String[] columns = { "path", "Drug","NoOptionsMessage" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_drug_data.xlsx"), "advanced_search_negative", columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page, Treatment value, error message
   *
   */

  @DataProvider(name = "getTreatmentNegative")
  public Iterator<Object[]> getTreatmentNegative() {
    String[] columns = { "path", "Treatment","NoOptionsMessage" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_drug_data.xlsx"), "advanced_search_negative", columns);
  }
  /**
   * Data provider retrieves paths to Advanced Search page and the placeholder Text
   * for the Drug and Other Treatments Section
   *
   * @return
   */

  @DataProvider(name = "getPlaceHolderText")
  public Iterator<Object[]> getPlaceHolderText() {
    String[] columns = { "path", "ExpectedDrugText","ExpectedTreatmentText" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_drug_data.xlsx"), "advanced_search", columns);
  }
  /**
   * Data provider retrieves paths to Advanced Search page, Drug value, Treatment value
   *
   */

  @DataProvider(name = "getDrugTreatment")
  public Iterator<Object[]> getDrugTreatment() {
    String[] columns = { "path", "Drug","Treatment"};
    Iterator<Object[]> data = new ExcelDataReader(getDataFilePath("cts_advanced_search_drug_data.xlsx"), "advanced_search", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    List<String> drugs;
    List<String> treatments;
    while (data.hasNext()) {
      Object[] item = data.next();
      String rawDrugs = (String) item[1];
      String rawTreatments = (String) item[2];
      drugs = Arrays.asList(rawDrugs.split(","));
      treatments = Arrays.asList(rawTreatments.split(","));
      converted.add(new Object[]{item[0], drugs, treatments});
    }
    return converted.iterator();
      }

}
