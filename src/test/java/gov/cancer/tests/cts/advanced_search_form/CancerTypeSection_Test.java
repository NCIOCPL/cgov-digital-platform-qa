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

import java.util.Iterator;

/**
 * This test class contains all tests related to the Cancer Type/Condition section on the Advanced Search Form
 *
 *
 */
public class CancerTypeSection_Test extends TestObjectBase {

  /** Verify that CancerTypeCondition dropdown is displayed when All button is clicked in CancerType Section
   * parameters
   * @param path       url
   *
   */
  @Test(dataProvider = "getCancerType")
  public void verifyDropdownVisibleOnClickAllButton(String path) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getCancerTypeSection().scrollUntilPrimaryCancerTypeVisible();
      //Click Primary Cancer Type dropdown
      page.getCancerTypeSection().ActivatePrimaryCancerTypeField();
      //asserting that primary Cancer Type dropdown is displayed
      Assert.assertTrue(page.getCancerTypeSection().getCancerTypeCondition().isVisible(),
        "All dropdown is not visible");
    });
  }

  /** Verify when user clicks on the Primary Cancer Type/Condition input box, a box appears with the placeholder text
   * "Start typing to narrow options below"
   * parameters
   * @param path       url
   *
   */
  @Test(dataProvider = "getCancerType")
  public void verifyInputBoxOnClickAllButton(String path) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getCancerTypeSection().scrollUntilPrimaryCancerTypeVisible();
      //Click Primary Cancer Type dropdown
      page.getCancerTypeSection().ActivatePrimaryCancerTypeField();
      //asserting that input box for the Primary Cancer Type dropdown is displayed
      Assert.assertTrue(page.getCancerTypeSection().getCancerTypeCondition().isVisible(), "Input box is not visible");
    });
  }
  /** Verify that when user inputs a value to the Primary Cancer Type/Condition and clicks Find Trials button,
   * correct results are displayed
   * parameters
   * @param path       url
   * @param cancerType cancer type value
   * @param expectedQueryParams  list of query parameters
   */
  @Test(dataProvider = "getCancerTypeCondition")
  public void verifyPrimaryCancerTypeField(String path, String cancerType, String expectedQueryParams ) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getCancerTypeSection().scrollUntilPrimaryCancerTypeVisible();
      //Click All button
      page.getCancerTypeSection().ActivatePrimaryCancerTypeField();
      //selecting the Cancer Type
      page.getCancerTypeSection().getCancerTypeCondition().selectItem(cancerType);
      // create a navigation event redirect to search results page
      SearchNavigationResult search_result = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(search_result.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(expectedQueryParams )," query parameters does not match for Cancer Type");
    });
  }

  /** Verify that when user inputs a value to the
   * Primary Cancer Type/Condition field the following input boxes are displayed:
   Subtype
   Stage
   Side Effects/Biomarkers/Participant Attributes
   * parameters
   * @param path       url
   * @param cancerType cancer type value
   * @param expectedQueryParams  list of query parameters
   */
  @Test(dataProvider = "getCancerTypeCondition")
  public void verifyFieldsDisplayedOnSelectPrimaryCancerType(String path, String cancerType,
                                                             String expectedQueryParams ) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getCancerTypeSection().scrollUntilPrimaryCancerTypeVisible();
      //Click Primary Cancer Type dropdown
      page.getCancerTypeSection().ActivatePrimaryCancerTypeField();
      //selecting the Cancer Type
      page.getCancerTypeSection().getCancerTypeCondition().selectItem(cancerType);
      //assert that Subtype field is displayed
      Assert.assertTrue(page.getCancerTypeSection().getSubType().isVisible()," SubType field is not visible");
      //assert that Stage field is displayed
      Assert.assertTrue(page.getCancerTypeSection().getStage().isVisible()," Stage field is not visible");
      //assert that Side Effects field is displayed
      Assert.assertTrue(page.getCancerTypeSection().getSideEffects().isVisible(),
        " SideEffects field is not visible");
    });
  }

  /** Verify that when user inputs a value to the Primary Cancer Type/Condition and SubType Fields and clicks Find
   * Trials button, correct parameters are passed.
   * parameters
   * @param path       url
   * @param cancerType cancer type value
   * @param subType cancer sub type value
   * @param expectedQueryParams  list of query parameters
   */
  @Test(dataProvider = "getCancerTypeConditionSubType")
  public void verifyPrimaryCancerTypeSubTypeField(String path, String cancerType, String subType,
                                                  String expectedQueryParams ) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getCancerTypeSection().scrollUntilPrimaryCancerTypeVisible();
      //Click Primary Cancer Type dropdown
      page.getCancerTypeSection().ActivatePrimaryCancerTypeField();
      //selecting the Cancer Type
      page.getCancerTypeSection().getCancerTypeCondition().selectItem(cancerType);
      //selecting the Cancer SubType
      page.getCancerTypeSection().getSubType().selectItem(subType);

      // create a navigation event redirect to search results page
      SearchNavigationResult search_result = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(search_result.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(expectedQueryParams ),
        " query parameters does not match for Cancer Type and Sub type");
    });
  }
  /** Verify that when user inputs a value to the Primary Cancer Type/Condition and Stage Fields and clicks Find Trials
   *  button, correct parameters are passed.
   * parameters
   * @param path       url
   * @param cancerType cancer type value
   * @param stage stage value
   * @param expectedQueryParams  list of query parameters
   */
  @Test(dataProvider = "getCancerTypeConditionStage")
  public void verifyPrimaryCancerTypeStageField(String path, String cancerType, String stage,
                                                String expectedQueryParams ) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getCancerTypeSection().scrollUntilPrimaryCancerTypeVisible();
      //Click Primary Cancer Type dropdown
      page.getCancerTypeSection().ActivatePrimaryCancerTypeField();
      //selecting the Cancer Type
      page.getCancerTypeSection().getCancerTypeCondition().selectItem(cancerType);
      //selecting the Stage
      page.getCancerTypeSection().getStage().selectItem(stage);

      // create a navigation event redirect to search results page
      SearchNavigationResult search_result = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(search_result.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(expectedQueryParams ),
        " query parameters does not match for Cancer Type and Sub type");
    });
  }
  /** Verify that when user inputs a value to the Primary Cancer Type/Condition, Subtype and Stage Fields and clicks
   * Find Trials button, correct parameters are passed.
   * parameters
   * @param path       url
   * @param cancerType cancer type value
   * @param subType cancer sub type value
   * @param stage stage value
   * @param expectedQueryParams  list of query parameters
   */
  @Test(dataProvider = "getCancerTypeConditionSubTypeStage")
  public void verifyPrimaryCancerTypeSubTypeStageField(String path, String cancerType, String subType, String stage,
                                                       String expectedQueryParams ) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getCancerTypeSection().scrollUntilPrimaryCancerTypeVisible();
      //Click Primary Cancer Type dropdown
      page.getCancerTypeSection().ActivatePrimaryCancerTypeField();
      //selecting the Cancer Type
      page.getCancerTypeSection().getCancerTypeCondition().selectItem(cancerType);
      //selecting the Cancer SubType
      page.getCancerTypeSection().getSubType().selectItem(subType);
      //selecting Stage
      page.getCancerTypeSection().getStage().selectItem(stage);
      // create a navigation event redirect to search results page
      SearchNavigationResult search_result = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(search_result.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(expectedQueryParams ),
        " query parameters  does not match for Cancer Type, Sub type and Stage");
    });
  }
  /** Verify that when user inputs a value to the Primary Cancer Type/Condition and Side Effects Fields and clicks Find
   * Trials button, correct parameters are passed.
   * parameters
   * @param path       url
   * @param cancerType cancer type value
   * @param sideEffect side effect value
   * @param expectedQueryParams  list of query parameters
   */
  @Test(dataProvider = "getCancerTypeConditionSideEffects")
  public void verifyPrimaryCancerTypeSideEffectsField(String path, String cancerType, String sideEffect,
                                                      String expectedQueryParams ) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getCancerTypeSection().scrollUntilPrimaryCancerTypeVisible();
      //Click Primary Cancer Type dropdown
      page.getCancerTypeSection().ActivatePrimaryCancerTypeField();
      //selecting the Cancer Type
      page.getCancerTypeSection().getCancerTypeCondition().selectItem(cancerType);
      //selecting the Side Effect
      page.getCancerTypeSection().getSideEffects().selectItem(sideEffect);

      // create a navigation event redirect to search results page
      SearchNavigationResult search_result = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(search_result.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(expectedQueryParams ),
        " query parameters does not match for Cancer Type and Side effects");
    });
  }
  /** Verify that when user inputs a value to the Primary Cancer Type/Condition,SubType and Side Effects Fields and
   * clicks Find Trials button, correct parameters are passed.
   * parameters
   * @param path       url
   * @param cancerType cancer type value
   * @param subType  cancer sub type value
   * @param sideEffect side effect value
   * @param expectedQueryParams  list of query parameters
   */
  @Test(dataProvider = "getCancerTypeConditionSubTypeSideEffects")
  public void verifyPrimaryCancerTypeSubTypeSideEffectsField(String path, String cancerType,String subType,
                                                             String sideEffect, String expectedQueryParams ) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getCancerTypeSection().scrollUntilPrimaryCancerTypeVisible();
      //Click Primary Cancer Type dropdown
      page.getCancerTypeSection().ActivatePrimaryCancerTypeField();
      //selecting the Cancer Type
      page.getCancerTypeSection().getCancerTypeCondition().selectItem(cancerType);
      //selecting the SubType
      page.getCancerTypeSection().getSubType().selectItem(subType);
      //selecting the Side Effect
      page.getCancerTypeSection().getSideEffects().selectItem(sideEffect);

      // create a navigation event redirect to search results page
      SearchNavigationResult search_result = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(search_result.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(expectedQueryParams ),
        " query parameters does not match for Cancer Type, Sub type and Side effects");
    });
  }
  /** Verify that when user inputs a value to the Primary Cancer Type/Condition,Stage and Side Effects Fields and clicks
   *  Find Trials button, correct parameters are passed.
   * parameters
   * @param path       url
   * @param cancerType cancer type value
   * @param stage  stage value
   * @param sideEffect side effect value
   * @param expectedQueryParams  list of query parameters
   */
  @Test(dataProvider = "getCancerTypeConditionStageSideEffects")
  public void verifyPrimaryCancerTypeStageSideEffectsField(String path, String cancerType,String stage,
                                                           String sideEffect, String expectedQueryParams ) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getCancerTypeSection().scrollUntilPrimaryCancerTypeVisible();
      //Click Primary Cancer Type dropdown
      page.getCancerTypeSection().ActivatePrimaryCancerTypeField();
      //selecting the Cancer Type
      page.getCancerTypeSection().getCancerTypeCondition().selectItem(cancerType);
      //selecting the Stage
      page.getCancerTypeSection().getStage().selectItem(stage);
      //selecting the Side Effect
      page.getCancerTypeSection().getSideEffects().selectItem(sideEffect);

      // create a navigation event redirect to search results page
      SearchNavigationResult search_result = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(search_result.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(expectedQueryParams ),
        " query parameters does not match for Cancer Type, Stage and Side effects");
    });
  }
  /** Verify that when user inputs a value to the Primary Cancer Type/Condition,SubType, Stage and Side Effects Fields
   * and clicks Find Trials button, correct parameters are passed.
   * parameters
   * @param path       url
   * @param cancerType cancer type value
   * @param subType  cancer sub type value
   * @param stage stage value
   * @param sideEffect side effect value
   * @param expectedQueryParams  list of query parameters
   */
  @Test(dataProvider = "getCancerTypeConditionSubTypeStageSideEffects")
  public void verifyPrimaryCancerTypeSubTypeStageSideEffectsField(String path, String cancerType,String subType,
                                                                  String stage, String sideEffect,
                                                                  String expectedQueryParams ) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getCancerTypeSection().scrollUntilPrimaryCancerTypeVisible();
      //Click Primary Cancer Type dropdown
      page.getCancerTypeSection().ActivatePrimaryCancerTypeField();
      //selecting the Cancer Type
      page.getCancerTypeSection().getCancerTypeCondition().selectItem(cancerType);
      //selecting the Sub Type
      page.getCancerTypeSection().getSubType().selectItem(subType);
      //selecting the Stage
      page.getCancerTypeSection().getStage().selectItem(stage);
      //selecting the Side Effect
      page.getCancerTypeSection().getSideEffects().selectItem(sideEffect);

      // create a navigation event redirect to search results page
      SearchNavigationResult search_result = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(search_result.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(expectedQueryParams ),
        " query parameters does not match for Cancer Type, SubType, Stage and Side effects");
    });
  }

  /** Verify that when user inputs an invalid value to the Primary Cancer Type/Condition field,
   * error message is displayed.
   * parameters
   * @param path       url
   * @param cancerType cancer type value
   * @param expectedMessage expected message
   */
  @Test(dataProvider = "getInvalidCancerType")
  public void verifyInvalidCancerTypeEntries(String path, String cancerType, String expectedMessage) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getCancerTypeSection().scrollUntilPrimaryCancerTypeVisible();
      //Click Primary Cancer Type dropdown
      page.getCancerTypeSection().ActivatePrimaryCancerTypeField();
      //selecting the Cancer Type
      page.getCancerTypeSection().getCancerTypeCondition().enterText(cancerType);
      //assert that message displayed is correct
      Assert.assertEquals(page.getCancerTypeSection().getCancerTypeCondition().getSectionFieldMessage(),
        expectedMessage, "Correct message is not displayed for No available options");
    });
  }

  /** Verify that when user inputs an invalid value to the SubType field,
   * error message is displayed.
   * parameters
   * @param path       url
   * @param cancerType cancer type value
   * @param subType cancer sub type
   * @param expectedMessage expected message
   */
  @Test(dataProvider = "getInvalidSubType")
  public void verifyInvalidSubTypeEntries(String path, String cancerType,String subType, String expectedMessage) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getCancerTypeSection().scrollUntilPrimaryCancerTypeVisible();
      //Click Primary Cancer Type dropdown
      page.getCancerTypeSection().ActivatePrimaryCancerTypeField();
      //selecting the Cancer Type
      page.getCancerTypeSection().getCancerTypeCondition().selectItem(cancerType);
      //selecting the Sub Type
      page.getCancerTypeSection().getSubType().enterText(subType);
      //assert that message displayed is correct
      Assert.assertEquals(page.getCancerTypeSection().getSubType().getSectionFieldMessage(),
        expectedMessage, "Correct message is not displayed for No available options");
    });
  }

  /** Verify that when user inputs an invalid value to the Stage field,
   * error message is displayed.
   * parameters
   * @param path       url
   * @param cancerType cancer type value
   * @param stage stage value
   * @param expectedMessage expected message
   */
  @Test(dataProvider = "getInvalidStage")
  public void verifyInvalidStageEntries(String path, String cancerType,String stage, String expectedMessage) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getCancerTypeSection().scrollUntilPrimaryCancerTypeVisible();
      //Click Primary Cancer Type dropdown
      page.getCancerTypeSection().ActivatePrimaryCancerTypeField();
      //selecting the Cancer Type
      page.getCancerTypeSection().getCancerTypeCondition().selectItem(cancerType);
      //selecting the Sub Type
      page.getCancerTypeSection().getStage().enterText(stage);
      //assert that message displayed is correct
      Assert.assertEquals(page.getCancerTypeSection().getStage().getSectionFieldMessage(),
        expectedMessage, "Correct message is not displayed for No available options");
    });
  }
  /** Verify that when user inputs an invalid value to the Stage field,
   * error message is displayed.
   * parameters
   * @param path       url
   * @param cancerType cancer type value
   * @param sideEffect side effect value
   * @param expectedMessage expected message
   */
  @Test(dataProvider = "getInvalidSideEffect")
  public void verifyInvalidSideEffectEntries(String path, String cancerType,String sideEffect, String expectedMessage) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getCancerTypeSection().scrollUntilPrimaryCancerTypeVisible();
      //Click Primary Cancer Type dropdown
      page.getCancerTypeSection().ActivatePrimaryCancerTypeField();
      //selecting the Cancer Type
      page.getCancerTypeSection().getCancerTypeCondition().selectItem(cancerType);
      //selecting the Sub Type
      page.getCancerTypeSection().getSideEffects().enterText(sideEffect);
      //assert that message displayed is correct
      Assert.assertEquals(page.getCancerTypeSection().getSideEffects().getSectionFieldMessage(),
        expectedMessage, "Correct message is not displayed for No available options");
    });
  }

  /************************
   * SUBMIT SEARCH WITH HITTING ENTER KEY
   ***********************/
  /** Verify that when user inputs a value to the Primary Cancer Type/Condition,Stage and Side Effects Fields and
   * hit ENTER key, correct parameters are passed.
   * parameters
   * @param path       url
   * @param cancerType cancer type value
   * @param stage stage value
   * @param sideEffect side effect value
   * @param expectedQueryParams  list of query parameters
   */
  @Test(dataProvider = "getCancerTypeConditionStageSideEffects")
  public void verifyPrimaryCancerTypeStageSideEffectsFieldOnEnter(String path, String cancerType,String stage,
                                                           String sideEffect, String expectedQueryParams ) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getCancerTypeSection().scrollUntilPrimaryCancerTypeVisible();
      //Click Primary Cancer Type dropdown
      page.getCancerTypeSection().ActivatePrimaryCancerTypeField();
      //selecting the Cancer Type
      page.getCancerTypeSection().getCancerTypeCondition().selectItem(cancerType);
      //selecting the Stage
      page.getCancerTypeSection().getStage().selectItem(stage);
      //selecting the Side Effect
      page.getCancerTypeSection().getSideEffects().selectItem(sideEffect);

      // create a navigation event redirect to search results page
      SearchNavigationResult search_result = page.hitEnterKey(page.getCancerTypeSection().getSideEffects());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(search_result.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(expectedQueryParams ),
        " query param does not match for Cancer Type, Stage and Side effects");
    });
  }

  /************************************************************
   * Data providers
   ************************************************************/

  /**
   * Data provider retrieves paths to Advanced Search page
   * params
   */

  @DataProvider(name = "getCancerType")
  public Iterator<Object[]> getCancerType() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_cancer_type_data.xlsx"),
      "cancertype", columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page, Cancer Type value and Query parameters
   * params
   */

  @DataProvider(name = "getCancerTypeCondition")
  public Iterator<Object[]> getCancerTypeCondition() {
    String[] columns = { "path","CancerType","ParamCancerType" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_cancer_type_data.xlsx"),
      "cancertype", columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page,Cancer Type value, Sub type value and Query parameters
   * params
   */

  @DataProvider(name = "getCancerTypeConditionSubType")
  public Iterator<Object[]> getCancerTypeConditionSubType() {
    String[] columns = { "path","CancerType","SubType","ParamCancerTypeSubtype" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_cancer_type_data.xlsx"),
      "cancertype", columns);
  }
  /**
   * Data provider retrieves paths to Advanced Search page, Cancer Type value, Stage value and Query parameters
   * params
   */

  @DataProvider(name = "getCancerTypeConditionStage")
  public Iterator<Object[]> getCancerTypeConditionStage() {
    String[] columns = { "path","CancerType","Stage","ParamCancerTypeStage" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_cancer_type_data.xlsx"),
      "cancertype", columns);
  }
  /**
   * Data provider retrieves paths to Advanced Search page, Cancer Type value, Side Effect value and Query parameters
   * params
   */

  @DataProvider(name = "getCancerTypeConditionSideEffects")
  public Iterator<Object[]> getCancerTypeConditionSideEffects() {
    String[] columns = { "path","CancerType","SideEffects","ParamCancerTypeSideEffects" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_cancer_type_data.xlsx"),
      "cancertype", columns);
  }
  /**
   * Data provider retrieves paths to Advanced Search page,Cancer Type value, SubType value, Stage value and
   * Query parameters
   * params
   */
  @DataProvider(name = "getCancerTypeConditionSubTypeStage")
  public Iterator<Object[]> getCancerTypeConditionSubTypeStage() {
    String[] columns = { "path","CancerType","SubType","Stage","ParamCancerTypeSubtypeStage" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_cancer_type_data.xlsx"),
      "cancertype", columns);
  }
  /**
   * Data provider retrieves paths to Advanced Search page,Cancer Type value, SubType value, Side Effects value and
   * Query parameters
   * params
   */
  @DataProvider(name = "getCancerTypeConditionSubTypeSideEffects")
  public Iterator<Object[]> getCancerTypeConditionSubTypeSideEffects() {
    String[] columns = { "path","CancerType","SubType","SideEffects","ParamCancerTypeSubTypeSideEffects" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_cancer_type_data.xlsx"),
      "cancertype", columns);
  }
  /**
   * Data provider retrieves paths to Advanced Search page,Cancer Type value, Stage value, Side effects value and Query
   * parameters
   * params
   */
  @DataProvider(name = "getCancerTypeConditionStageSideEffects")
  public Iterator<Object[]> getCancerTypeConditionStageSideEffects() {
    String[] columns = { "path","CancerType","Stage","SideEffects","ParamCancerTypeStageSideEffects" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_cancer_type_data.xlsx"),
      "cancertype", columns);
  }
  /**
   * Data provider retrieves paths to Advanced Search page,Cancer Type value, SubType value, Stage value, Side Effects
   * value and Query parameters
   * params
   */

  @DataProvider(name = "getCancerTypeConditionSubTypeStageSideEffects")
  public Iterator<Object[]> getCancerTypeConditionSubTypeStageSideEffects() {
    String[] columns = { "path","CancerType","SubType","Stage","SideEffects","ParamCancerTypeSubtypeStageSideEffects" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_cancer_type_data.xlsx"),
      "cancertype", columns);
  }
  /**
   * Data provider retrieves paths to Advanced Search page,Cancer Type value and Error message
   * params
   */

  @DataProvider(name = "getInvalidCancerType")
  public Iterator<Object[]> getInvalidCancerType() {
    String[] columns = { "path","CancerType","ErrorMessage" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_cancer_type_data.xlsx"),
      "cancertype_negative", columns);
  }
  /**
   * Data provider retrieves paths to Advanced Search page,Cancer Type value, SubType value and error message
   * params
   */

  @DataProvider(name = "getInvalidSubType")
  public Iterator<Object[]> getInvalidSubType() {
    String[] columns = { "path","CancerType","SubType","ErrorMessage" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_cancer_type_data.xlsx"),
      "cancertype_error", columns);
  }
  /**
   * Data provider retrieves paths to Advanced Search page, Cancer Type value, Stage value and Error message
   * params
   */

  @DataProvider(name = "getInvalidStage")
  public Iterator<Object[]> getInvalidStage() {
    String[] columns = { "path","CancerType","Stage","ErrorMessage" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_cancer_type_data.xlsx"), "cancertype_error", columns);
  }
  /**
   * Data provider retrieves paths to Advanced Search page,Cancer Type value, Side Effects value and Error message
   * params
   */

  @DataProvider(name = "getInvalidSideEffect")
  public Iterator<Object[]> getInvalidSideEffect() {
    String[] columns = { "path","CancerType","SideEffects","ErrorMessage" };
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_cancer_type_data.xlsx"),
      "cancertype_error", columns);
  }
}
