package gov.cancer.tests.cts.advanced_search_form;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.framework.QueryParametersComparator;
import gov.cancer.pageobject.components.CheckBox;
import gov.cancer.pageobject.cts.AdvancedSearchPage;
import gov.cancer.pageobject.cts.SearchNavigationResult;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * This test class contains all tests related to the Trial Type section on the Advanced Search Form
 */
public class TrialType_Test extends TestObjectBase {

  /**
   * Verify that Trial Type Section is visible
   * parameters
   *
   * @param path url
   */
  @Test(dataProvider = "getAdvancedSearchTrialType")
  public void verifyTrialTypeSectionIsVisible(String path) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      Assert.assertTrue(page.getTrialTypeSection().isVisible(), " Trial Type section is not visible");
    });
  }

  /**
   *  When the user switches the healthy volunteer toggle first to 'YES' and then  to "NO" in the Trial
   *  Type section and clicks Find Trials button, we will verify that system goes back to displaying default
   *  (not filtered by healthy volunteers) results after search submitted
   *
   * @param path    url
   * @param expectedQueryParams query param for Advanced Search Results Page url
   *
   */
  @Test(dataProvider = "getAdvancedSearchNoToggleSelected")
  public void verifyToggleSwitchPositions(String path, String expectedQueryParams) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      //scroll until the first trial type checkbox is visible(to get rid of 'sticky block')
      page.getTrialTypeSection().scrollUntilCheckBoxVisible();
      //healthy volunteer toggle switched to 'YES'
      page.getTrialTypeSection().limitToHealthyVolunteer(true);
      //verify that Toggle is indeed has been switched to Yes
      Assert.assertTrue(page.getTrialTypeSection().getToggleState());
      //now switch the toggle back to 'NO'
      page.getTrialTypeSection().limitToHealthyVolunteer(false);
      //verify that Toggle is indeed has been switched back to No
      Assert.assertFalse(page.getTrialTypeSection().getToggleState());
      // create a navigation event redirect to search results page
      SearchNavigationResult searchResult = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(searchResult.getPageURL().getQuery());
      //Assert that url contains expected query params
      Assert.assertTrue(querycomp.compareQueryParams(expectedQueryParams),
        "query param does not match for Trial Types" );
    });
  }

  /**
   * Verify that the query parameters are set correctly when the user switches the healthy volunteer toggle to "yes"
   * in the Trial Type section and clicks Find Trials button.
   *
   * @param path        url
   * @param expectedQueryParameters query param for Advanced Search Results Page url with healthy volunteer
   *                                toggle query parameters (ex. hv=1&loc=0&rl=2)
   */
  @Test(dataProvider = "getToggle")
  public void verifyToggleSwitchedToYesPosition(String path, String expectedQueryParameters
  ) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      //scroll until the first trial type checkbox is visible(to get rid of 'sticky block')
      page.getTrialTypeSection().scrollUntilCheckBoxVisible();
      //healthy volunteer toggle switched to 'YES'
      page.getTrialTypeSection().limitToHealthyVolunteer(true);
      // create a navigation event redirect to search results page
      SearchNavigationResult searchResult = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(searchResult.getPageURL().getQuery());
      //Assert that url contains expected query params
      Assert.assertTrue(querycomp.compareQueryParams(expectedQueryParameters),
        "query param does not match for Trial Types" );
    });

  }

  /**
   * Verify that the check box "All" is selected by default for the 'Trial Type' section.
   *
   * @param path url
   */
  @Test(dataProvider = "getAdvancedSearchTrialType")
  public void verifyAllCheckBoxIsSelectedByDefault(String path) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      // Verify that the checkbox 'All" is selected
      Assert.assertTrue(page.getTrialTypeSection().getAllCheckbox().isSelected(),
        " 'All' checkbox is not selected");
    });
  }

  /**
   * Verify “All” trial type criterion is passed correctly when 'All' checkbox is selected and user clicks
   * Find Trials button
   *
   * @param path    url
   * @param expectedQueryParams query param for Advanced Search Results Page url
   * @parm size number of params
   */
  @Test(dataProvider = "getAdvancedSearchNoToggleSelected")
  public void verifyAllCheckbox(String path, String expectedQueryParams) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      //Assert that the 'All' checkbox is selected
      Assert.assertTrue(page.getTrialTypeSection().getAllCheckbox().isSelected(),
        " 'All' checkbox is not selected");
      // create a navigation event redirect to search results page
      SearchNavigationResult searchResult = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(searchResult.getPageURL().getQuery());
      //Assert that url contains expected query params
      Assert.assertTrue(querycomp.compareQueryParams(expectedQueryParams),
        "query param does not match for Trial Types" );
    });
  }

  /**
   * Verify that 'All' checkbox gets deselected when any one Trial Type checkbox is selected
   *
   * @param path url
   */
  @Test(dataProvider = "getAdvancedSearchTrialType")
  public void verifyAllCheckBoxGetsDeselectedOnSelectingOtherCheckboxes(String path) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      //get the list of all other trial type checkboxes
      List<CheckBox> allOtherTrialTypes = page.getTrialTypeSection().getAllOptions();
      //scroll until the first trial type checkbox is visible(to get rid of 'sticky block')
      page.getTrialTypeSection().scrollUntilCheckBoxVisible();
      //select the last checkbox in the list
      allOtherTrialTypes.get(allOtherTrialTypes.size() - 1).toggle();
      //Assert that the 'All' checkbox is not selected
      Assert.assertFalse(page.getTrialTypeSection().getAllCheckbox().isSelected(),
        " 'All' checkbox is selected");
    });
  }

  /**
   * Verify that 'All' checkbox gets selected when none of the Other Trial Type checkbox is selected
   *
   * @param path url
   */
  @Test(dataProvider = "getAdvancedSearchTrialType")
  public void verifyAllCheckboxGetsSelectedWhenOtherCheckboxesAreDeselected(String path) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      //get the list of all other trial type checkboxes
      List<CheckBox> allOtherTrialTypes = page.getTrialTypeSection().getAllOptions();
      ///scroll until the first trial type checkbox is visible(to get rid of 'sticky block')
      page.getTrialTypeSection().scrollUntilCheckBoxVisible();
      //For each TrialType checkbox execute the following assertion.
      for (CheckBox ch : allOtherTrialTypes) {
        //select the other trial type checkboxes
        ch.toggle();
        //verify that "all" checkbox is deselected when the other trial types got selected
       Assert.assertFalse(page.getTrialTypeSection().getAllCheckbox().isSelected(),
         " 'All' checkbox is selected");
        //deselect the other trial type checkboxes
        ch.toggle();
        //verify that "all" checkbox is selected when the other trial types got deselected
        Assert.assertTrue(page.getTrialTypeSection().getAllCheckbox().isSelected(),
          " 'All' checkbox is not selected");
      }
    });
  }

  /**
   * Verify that the query parameters are set correctly when the user selects any one checkbox (e.g. Treatment) for
   * the Trial Type and clicks Find Trials button. The method compareQueryParams() compares if the
   * number of received url query parameters is equal to provided param number and all parameters are matching
   *
   * @param path                 url
   * @param allExpectedParameters the trial type query parameters (e.g.tt=treatment)
   * @param index                - position of checkbox to toggle
   */
  @Test(dataProvider = "getAdvancedSearchOtherIndividualCheckboxSelected")
  public void verifyIndividualOtherTrialTypeCheckbox(String path, String allExpectedParameters, Integer index) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      //scroll until the first trial type checkbox is visible(to get rid of 'sticky block')
      page.getTrialTypeSection().scrollUntilCheckBoxVisible();
      //get the list of all other trial type checkboxes
      List<CheckBox> allOtherTrialTypes = page.getTrialTypeSection().getAllOptions();
      //toggle specified checkboxes
      allOtherTrialTypes.get(index).toggle();
      // create a navigation event redirect to search results page
      SearchNavigationResult searchResult = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(searchResult.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(allExpectedParameters),
        " query param does not match for Trial Types");
    });
  }

  /**
   * Verify that the query parameters are set correctly when the user selects more than one checkbox
   * (e.g. Treatment and Prevention) for the Trial Type and clicks Find Trials button.The method compareQueryParams()
   * compares if the number of received url query parameters is equal to provided param number and all parameters
   * are matching
   *
   * @param path        url
   * @param queryValues the trial type query parameters (ex.tt=treatment)
   * @param indexes     position of checkbox to toggle
   */
  @Test(dataProvider = "getComboParam")
  public void verifyAllOtherTrialTypesCheckbox(String path, String queryValues, List<Integer> indexes) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      //get the list of all trial types checkboxes
      List<CheckBox> allOtherTrialTypes = page.getTrialTypeSection().getAllOptions();
      ///scroll until the first trial type checkbox is visible(to get rid of 'sticky block')
      page.getTrialTypeSection().scrollUntilCheckBoxVisible();
      //toggle specified checkboxes
      for (int i = 0; i < indexes.size(); i++) {
        allOtherTrialTypes.get(indexes.get(i)).toggle();
      }
      // create a navigation event redirect to search results page
      SearchNavigationResult searchResult = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(searchResult.getPageURL().getQuery());
      //Assert that url contains expected query params
      Assert.assertTrue(querycomp.compareQueryParams(queryValues),
        "query param does not match for Trial Types" );
    });
  }

/************************************************************
 * Data providers
 ************************************************************/
  /**
   * Data provider retrieves paths to Advanced Search page
   *
   * @return
   */

  @DataProvider(name = "getAdvancedSearchTrialType")
  public Iterator<Object[]> getAdvancedSearchTrialType() {
    String[] columns = {"path"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_trial_type_data.xlsx"),
      "trialtype", columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page, Toggle query parameter's value in the Trial Type section
   *
   * @return
   */
  @DataProvider(name = "getToggle")
  public Iterator<Object[]> getToggle() {
    String[] columns = {"path", "expectedQueryParameters"};
    Iterator<Object[]> values =
      new ExcelDataReader(getDataFilePath("cts_advanced_search_trial_type_data.xlsx"),
      "trialtype", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      converted.add(new Object[]{item[0], item[1]});
    }
    return converted.iterator();
  }


  /**
   * Data provider retrieves paths to Advanced Search page, individual query param and index
   *
   * @return
   */
  @DataProvider(name = "getAdvancedSearchOtherIndividualCheckboxSelected")
  public Iterator<Object[]> getAdvancedSearchOtherIndividualCheckboxSelected() {
    String[] columns = {"path", "allExpectedParameters", "index"};
    Iterator<Object[]> values =
      new ExcelDataReader(getDataFilePath("cts_advanced_search_trial_type_data.xlsx"),
      "trialtype", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String index = (String) item[2];
      int expectedIndex = Integer.parseInt(index);
      converted.add(new Object[]{item[0], item[1], expectedIndex});
    }
    return converted.iterator();
  }

  /**
   * Data provider retrieves path to pages as well as QueryParamValue and the index
   *
   * @return
   */
  @DataProvider(name = "getAdvancedSearchNoToggleSelected")
  public Iterator<Object[]> getAdvancedSearchNoToggleSelected() {
    String[] columns = {"path", "expectedQueryParams"};
    Iterator<Object[]> values =
      new ExcelDataReader(getDataFilePath("cts_advanced_search_trial_type_data.xlsx"),
        "trialtype", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String query = (String)item[1];
      if(!query.isEmpty())
        converted.add(new Object[]{item[0], query});
    }
    return converted.iterator();
  }

  /**
   * Data provider retrieves path to pages as well as QueryParamValue and the index
   *
   * @return
   */
  @DataProvider(name = "getComboParam")
  public Iterator<Object[]> getComboParam() {
    String[] columns = {"path", "queryParamValues","index"};
    Iterator<Object[]> values =
      new ExcelDataReader(getDataFilePath("cts_advanced_search_trial_type_data.xlsx"),
      "trialtypecombo", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String index = (String) item[2];
      List<String> expectedIndex = Arrays.asList(index.split(","));
      List<Integer> indexAsInt = new ArrayList<>();
      for (String str : expectedIndex) {
        indexAsInt.add(Integer.parseInt(str));
      }
        converted.add(new Object[]{item[0], item[1], indexAsInt});
    }
    return converted.iterator();
  }
}


