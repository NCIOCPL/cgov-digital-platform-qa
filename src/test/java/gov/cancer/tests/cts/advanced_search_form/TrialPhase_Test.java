package gov.cancer.tests.cts.advanced_search_form;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.components.CheckBox;
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
 * This test class contains all tests related to the Trial Phase section on the Advanced Search Form
 */
public class TrialPhase_Test extends TestObjectBase {
  /**
   * Verify that Trial Phase Section is visible
   * parameters
   *
   * @param path url
   */
  @Test(dataProvider = "getAdvancedSearchTrialPhase")
  public void verifyTrialPhaseSectionIsVisible(String path) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      Assert.assertTrue(page.getTrialPhaseSection().isVisible(), "Trial Phase Section is not visible");
    });
  }

  /**
   * Verify that the check box "All" is selected by default for the 'Trial Phase' section.
   *
   * @param path url
   */
  @Test(dataProvider = "getAdvancedSearchTrialPhase")
  public void verifyAllCheckBoxIsSelectedByDefault(String path) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      // Verify that the checkbox 'All" is selected
      Assert.assertTrue(page.getTrialPhaseSection().getAllCheckBox().isSelected(), " 'All' checkbox is not selected");
    });
  }

  /**
   * Verify that when any other checkbox is selected for the Trial Phase , 'All' checkbox gets deselected.
   *
   * @param path url
   */
  @Test(dataProvider = "getAdvancedSearchTrialPhase")
  public void verifyAllCheckBoxGetsDeselectedOnSelectingOtherCheckboxes(String path) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      //get the list of all other trial phases checkboxes
      List<CheckBox> allOtherPhases = page.getTrialPhaseSection().getAllOptionsCheckBoxes();
      //scroll until the last phase checkbox is visible(to get rid of 'sticky block')
      page.getTrialPhaseSection().scrollUntilCheckBoxVisible();
      //select the last checkbox in the list
      allOtherPhases.get(allOtherPhases.size()-1).toggle();
        //Assert that the 'All' checkbox is not selected
        Assert.assertFalse(page.getTrialPhaseSection().getAllCheckBox().isSelected(), " 'All' checkbox is selected");
    });
  }

  /**
   * Verify that when none of the Other checkbox is selected, 'All' checkbox gets selected
   *
   * @param path url
   */
  @Test(dataProvider = "getAdvancedSearchTrialPhase")
  public void verifyAllCheckboxGetsSelectedWhenOtherCheckboxesAreDeselected(String path) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      //get the list of all other trial phases checkboxes
      List<CheckBox> allOtherPhases = page.getTrialPhaseSection().getAllOptionsCheckBoxes();
      //scroll until the last phase checkbox is visible(to get rid of 'sticky block')
      page.getTrialPhaseSection().scrollUntilCheckBoxVisible();
      //For each Trial Phase checkbox execute the following assertion.
      for (CheckBox ch : allOtherPhases) {
        //select the the trial phase checkbox
        ch.toggle();
        //verify that "all" is deselected
        Assert.assertFalse(page.getTrialPhaseSection().getAllCheckBox().isSelected(), " 'All' checkbox is not selected");
        //deselect the trial phase checkbox
        ch.toggle();
        //Assert that the 'All' checkbox is selected
        Assert.assertTrue(page.getTrialPhaseSection().getAllCheckBox().isSelected(), " 'All' checkbox is not selected");
      }
    });
  }

  /**
   * Verify that when 'All' checkbox is selected and user clicks Find Trials button, all results are displayed.
   *
   * @param path    url
   * @param Results query param for Advanced Search Results Page url
   */
  @Test(dataProvider = "getAdvancedSearchAllCheckboxSelected")
  public void verifyTrialPhaseAllCheckbox(String path, String Results) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      //verify that "all" is selected
      Assert.assertTrue(page.getTrialPhaseSection().getAllCheckBox().isSelected(), " 'All' checkbox is not selected");
      // create a navigation event redirect to search results page
      SearchNavigationResult searchResult = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //Assert that url contains all search params
      Assert.assertEquals(searchResult.getParameterValue("rl"), Results, "results are not displayed");
    });
  }
/**
  * Verify that when the user selects any one checkbox (e.g. Phase I) for the Trial Phase and clicks Find Trials
   * button, correct results are displayed.
    *@param path url
   * @param IndividualQueryParam the trial phases query parameters (e.g.tp=i)
   * @param Index - position of checkbox to toggle
 */
@Test(dataProvider = "getAdvancedSearchOtherIndividualCheckboxSelected")
public void verifyIndividualOtherTrialPhaseCheckbox(String path, String IndividualQueryParam , Integer Index) {
  TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
    //scroll until the last phase checkbox is visible(to get rid of 'sticky block')
    page.getTrialPhaseSection().scrollUntilCheckBoxVisible();
    //get the list of all other trial phases checkboxes
    List<CheckBox> allOtherPhases = page.getTrialPhaseSection().getAllOptionsCheckBoxes();
    //toggle specified checkboxes
      allOtherPhases.get(Index).toggle();
    // create a navigation event redirect to search results page
      SearchNavigationResult searchResult = page.clickSubmit(page.getFormAction().getFindTrialsButton());
    //Assert that url contains expected query params
     Assert.assertEquals(searchResult.getTrialPhaseParam(), IndividualQueryParam, " query param does not match for Trial Phases");

  });
}

/**
   * Verify that when the user selects more than one checkbox (e.g. Phase I and Phase II checkbox) for the Trial Phase and clicks Find Trials
   * button, correct results are displayed.
   * @param path url
   * @param queryValues the trial phases query parameters (ex.tp=i)
   * @param indexes - position of checkbox to toggle
   */
  @Test(dataProvider = "getComboParam")
  public void verifyAllOtherTrialPhasesCheckbox(String path, List<String> queryValues, List<Integer> indexes) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      //get the list of all trial phases checkboxes
      List<CheckBox> allOtherPhases = page.getTrialPhaseSection().getAllOptionsCheckBoxes();
      //scroll until the last phase checkbox is visible(to get rid of 'sticky block')
      page.getTrialPhaseSection().scrollUntilCheckBoxVisible();
      //toggle specified checkboxes
      for (int i = 0; i < indexes.size(); i++) {
        allOtherPhases.get(indexes.get(i)).toggle();
      }
      // create a navigation event redirect to search results page
      SearchNavigationResult searchResult = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //getQuery method is used to retrieve full query portion of an URL. Because retrieving individual query parameter's value
      //is working with Hashmap internally(and we know that Map stores unique keys), and because testing two or more Trial Phases selection passed
      //into the url it will return duplicate keys('tp'=i&'tp'=ii) - verification is done against complete query part of url with contains method
      for (int i = 0; i < queryValues.size(); i++) {
        //get query param for each option selected and verify it
        Assert.assertTrue(searchResult.getPageURL().getQuery().contains(queryValues.get(i)));
      }
    });
  }

  /************************************************************
   * Data providers
   ************************************************************/
  /**
   * Data provider retrieves paths to Advanced Search page
   * @return
   */

  @DataProvider(name = "getAdvancedSearchTrialPhase")
  public Iterator<Object[]> getAdvancedSearchTrialPhase() {
    String[] columns = {"path"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_trial_phase_data.xlsx"), "trialphase", columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page, results query parameter's value
   *
   * @return
   */
  @DataProvider(name = "getAdvancedSearchAllCheckboxSelected")
  public Iterator<Object[]> getAdvancedSearchAllCheckboxSelected() {
    String[] columns = {"path", "Results"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_trial_phase_data.xlsx"), "trialphase", columns);
  }

  /**
   *Data provider retrieves paths to Advanced Search page, individual query param and index
   *
   * @return
   */
  @DataProvider(name = "getAdvancedSearchOtherIndividualCheckboxSelected")
  public Iterator<Object[]> getAdvancedSearchOtherIndividualCheckboxSelected() {
    String[] columns = { "path", "IndividualQueryParam","Index" };
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("cts_advanced_search_trial_phase_data.xlsx"),
      "trialphase", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String index = (String) item[2];
      int expectedIndex = Integer.parseInt(index);
      converted.add(new Object[] { item[0],item [1], expectedIndex});
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
    String[] columns = {"path", "QueryParamValues", "Index"};
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("cts_advanced_search_trial_phase_data.xlsx"),
      "trialPhaseCombo", columns);
    List<Object[]> converted = new ArrayList<Object[]>();

    while (values.hasNext()) {
      Object[] item = values.next();
      String query = (String) item[1];
      String index = (String) item[2];
      List<String> expectedQuery = Arrays.asList(query.split(","));
      List<String> expectedIndex = Arrays.asList(index.split(","));
      List<Integer> indexAsInt = new ArrayList<>();
      for (String str : expectedIndex) {
        indexAsInt.add(Integer.parseInt(str));
      }
      converted.add(new Object[]{item[0], expectedQuery, indexAsInt});
    }
    return converted.iterator();
  }
}

