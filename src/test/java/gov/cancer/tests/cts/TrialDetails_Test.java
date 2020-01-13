package gov.cancer.tests.cts;


import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.NavigationResult;
import gov.cancer.pageobject.cts.TrialDetailsPage;
import gov.cancer.pageobject.cts.components.AccordionItem;
import gov.cancer.pageobject.cts.components.accordion_items.LocationAndContacts;
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
 * This class contains Trial Details Page tests
 * To provide reliable test data, each test starts on Search Result Page and select first available trial
 * for further verification
 */
public class TrialDetails_Test extends TestObjectBase {

  /**
   * Verify that header is visible on the page
   *
   * @param path
   */
  @Test(dataProvider = "getTrialDetailsPage")
  public void verifyHeaderPresent(String path) {
    TestRunner.run(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      //Verify header is visible
      Assert.assertTrue(page.isHeaderDisplayed(), "header field is not present");
    });
  }

  /**
   * Verify search criteria section is visible and collapsed by default
   *
   * @param path
   */
  @Test(dataProvider = "getCriteriaSection")
  public void verifySearchCriteriaPresent(String path) {
    TestRunner.run(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      //verify that criteria section is present
      Assert.assertTrue(page.getCriteriaSection().isVisible(), "Search Criteria section is not present");
      //verify that criteria section is collapsed by default
      Assert.assertFalse(page.getCriteriaSection().isExpanded(), "search criteria dropdown is expanded");
    });
  }

  /**
   * Verify start over link is present and contains expected link url
   *
   * @param path
   * @param link
   */
  @Test(dataProvider = "getStartOverLink")
  public void verifyStartOverLink(String path, String link) {
    TestRunner.run(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      // verify link is present
      Assert.assertNotNull(page.getStartOverLink(), "Start Over link is not present");
      // verify link url
      Assert.assertEquals(page.getStartOverLink().getUrl().getPath(), link, "Start Over link path does not match");
    });
  }

  /**
   * Verify the correct navigation of back to search button
   *
   * @param path
   * @param link
   */
  @Test(dataProvider = "getBackToSearch")
  public void verifyBackToSearchButton(String path, String link) {
    TestRunner.run(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {

      Assert.assertTrue(page.getBackToSearch().isVisible(), "'Back to search results' button is not present");
      // click on the Back to Search
      NavigationResult resultPage = page.clickSubmit(page.getBackToSearch());
      // build the page path using the redirected page path and its query
      String pagePath = resultPage.getPageURL().getPath() + "?" + resultPage.getPageURL().getQuery();
      // verify redirected page url path
      Assert.assertEquals(pagePath, link, "Back to Search redirection path does not match");
    });
  }


  /**
   * Verify that the Trial Status is equaled to "Active"
   *
   * @param path
   * @param status
   */
  @Test(dataProvider = "getTrialStatus")
  public void verifyTrialStatus(String path, String status) {
    TestRunner.run(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      //verify  trial status is 'Active'
      Assert.assertEquals(page.getTrialStatus(), status, "Trial status does not match");
    });
  }

  /**
   * Verify that open all button is present on page
   *
   * @param path
   */
  @Test(dataProvider = "getTrialDetailsPage")
  public void verifyOpenAllButtonPresent(String path) {
    TestRunner.run(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {

      Assert.assertTrue(page.getOpenAll().isVisible(), "'Open All' button is not present");
    });
  }

  /**
   * Verify 'Close All' button is visible
   *
   * @param path
   */
  @Test(dataProvider = "getTrialDetailsPage")
  public void verifyCloseAllButtonPresent(String path) {
    TestRunner.run(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {

      Assert.assertTrue(page.getCloseAll().isVisible(), "'Close All' button is not present");
    });
  }

  /**
   * verify that share by email delighter is visible on desktop
   *
   * @param path
   */
  @Test(dataProvider = "getTrialDetailsPage")
  public void verifyShareByEmailDelighterPresent(String path) {
    TestRunner.runDesktop(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      //verify Email delighter is visible
      Assert.assertTrue(page.getShareByEmailDelighter().isVisible(), "'Email' delighter is not present on desktop");
    });
  }

  /**
   * Verify that share by email delighter is visible on Mobile breakpoint and Print delighter is hidden
   *
   * @param path
   */
  @Test(dataProvider = "getTrialDetailsPage")
  public void verifyShareDelighterOnMobileBreakpoint(String path) {
    TestRunner.runMobile(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      //verify Email delighter is visible
      Assert.assertTrue(page.getShareByEmailDelighter().isVisible(), "'Email' delighter is not present on mobile");
      //verify Print delighter is NOT visible
      Assert.assertFalse(page.getShareByPrintDelighter().isVisible(), "'Print' delighter is present on mobile");
    });
  }

  /**
   * Verify that share by email delighter is visible on Tablet breakpoint and Print delighter is hidden
   *
   * @param path
   */
  @Test(dataProvider = "getTrialDetailsPage")
  public void verifyShareDelighterOnTabletBreakpoint(String path) {
    TestRunner.runTablet(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      //verify Email delighter is visible
      Assert.assertTrue(page.getShareByEmailDelighter().isVisible(), "'Email' delighter is not present on tablet");
      //verify Print delighter is NOT visible
      Assert.assertFalse(page.getShareByPrintDelighter().isVisible(), "'Print' delighter is present on tablet");
    });
  }

  /**
   * Verify 'share by print' delighter is visible on desktop
   *
   * @param path
   */
  @Test(dataProvider = "getTrialDetailsPage")
  public void verifyPrintDelighterPresentDesktop(String path) {
    TestRunner.runDesktop(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      //verify Print delighter is present
      Assert.assertTrue(page.getShareByPrintDelighter().isVisible(), "'Print' delighter is not present on desktop");
    });
  }

  /**
   * Verify 'trial checklist' delighter and its icon are displayed
   *
   * @param path
   */
  @Test(dataProvider = "getTrialDetailsPage")
  public void verifyTrialChecklistDelighter(String path) {
    TestRunner.runDesktop(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      //verify that help delighter object is present on page
      Assert.assertTrue(page.getTrialCheckListDelighter().isVisible(), "'trial checklist' delighter is not present");
      //verify help delighter title
      Assert.assertTrue(page.getTrialCheckListDelighter().isIconDisplayed(), "'trial checklist delighter icon is not displayed");
    });
  }

  /**
   * Test verifies Trial CheckList  delighter link and title
   *
   * @param path
   */
  @Test(dataProvider = "getTrialCheckListDelighter")
  public void verifyTrialCheckListDelighterTitleAndLink(String path, String link, String title) {
    TestRunner.runDesktop(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      //verify delighter link is correct
      Assert.assertEquals(page.getTrialCheckListDelighter().getLink().getUrl().getPath(), link, "'Trial CheckList' delighter link is wrong");
      // verify delighter title
      Assert.assertEquals(page.getTrialCheckListDelighter().getLinkTitle(), title, "'Trial CheckList' delighter title is wrong");
    });

  }

  /**
   * Verify that help delighter is displayed and icon is visible
   *
   * @param path
   */
  @Test(dataProvider = "getTrialDetailsPage")
  public void verifyGetHelpDelighter(String path) {
    TestRunner.runDesktop(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      //verify that help delighter object is present on page
      Assert.assertTrue(page.getHelpDelighter().isVisible(), "'Get Help' delighter is not present");
      // verify that the delighter icon is displayed
      Assert.assertTrue(page.getHelpDelighter().isIconDisplayed(), "'Get Help' delighter icon is not displayed");
    });
  }

  /**
   * Test verifies Get Help delighter link and title
   *
   * @param path
   */
  @Test(dataProvider = "getHelpDelighter")
  public void verifyGetHelpDelighterLinkAndTitle(String path, String link, String title) {
    TestRunner.runDesktop(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {

      //verify help delighter link
      Assert.assertEquals(page.getHelpDelighter().getLink().getUrl().getPath(), link, "'Help' delighter link is wrong");
      //verify help delighter title
      Assert.assertEquals(page.getHelpDelighter().getLinkTitle(), title, "'Help' delighter title is wrong");

    });
  }


  /**
   * Test verifies that Search Criteria drop down table contains all labels and its expected values
   *
   * @param path
   * @param label
   * @param value
   */
  @Test(dataProvider = "getCriteria")
  public void verifyCriteriaSection(String path, List<String> label, List<String> value) {
    TestRunner.runDesktop(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      // Retrieves labels and values from search criteria table and stores it to map
      page.getCriteriaSection().expand();
      for (int i = 0; i < label.size(); i++) {
        // Verifies that label matches
        Assert.assertTrue(page.getCriteriaSection().isCategoryPresent(label.get(i)), "label is not present in the criteria table");
        // Verifies the value of each label
        Assert.assertEquals(page.getCriteriaSection().getSelectionValue(label.get(i)), value.get(i), "criteria value doesnt match");
      }
    });
  }

  /**
   * Verify accordion section is present
   *
   * @param path
   */
  @Test(dataProvider = "getTrialDetailsPage")
  public void verifyAccordionSectionPresent(String path) {
    TestRunner.runDesktop(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      // Verify entire Accordion section is present
      Assert.assertTrue(page.getAccordion().isVisible(), "'Accordion' section is not present");
    });
  }

  /**
   * Verify each accordion title is visible and title is correct
   *
   * @param path
   * @param titles accordion title
   */
  @Test(dataProvider = "getAccordionTitles")
  public void verifyAccordion(String path, List<String> titles) {
    TestRunner.runDesktop(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      //for each accordion verify that the title is visible and match expected value
      for (int i = 0; i < titles.size(); i++) {
        Assert.assertTrue(page.getAccordion().get(i).isVisible(), "Accordion section -" + titles.get(i) + " is not visible");
        Assert.assertEquals(page.getAccordion().get(i).getTitle(), titles.get(i), "Accordion title -" + titles.get(i) + " does not match");
      }
    });
  }

  /**
   * Verify that by default only first section of an Accordion is expanded
   * The rest of them are collapsed
   *
   * @param path
   * @param size total qt of accordion items
   */
  @Test(dataProvider = "getAccordionCount")
  public void verifyAccordionFirstOnlyExpanded(String path, int size) {
    TestRunner.runDesktop(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      //Verify that first accordion section is expanded by default
      Assert.assertTrue(page.getAccordion().get(0).isExpanded(), "Description section is collapsed");
      for (int i = 1; i < size; i++) {
        //verify the rest of accordion sections are collapsed
        Assert.assertFalse(page.getAccordion().get(i).isExpanded(), "second and forth accordion section is expanded by default");
      }
    });
  }

  /**
   * Verify that by pressing 'Close All' button all accordion sections are collapsing
   *
   * @param path
   * @param size total qt of accordion items
   */
  @Test(dataProvider = "getAccordionCount")
  public void verifyAccordionCollapsed(String path, int size) {
    TestRunner.runDesktop(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      //click Close All button
      page.getCloseAll().click();
      for (int i = 0; i < size; i++) {
        //verify Accordion section is collapsed
        Assert.assertFalse(page.getAccordion().get(i).isExpanded(), "accordion -" + page.getAccordion().get(i).getTitle() + " is expanded");
      }
    });
  }

  /**
   * Verify that by clicking 'Open All' button all accordion sections are expanded
   *
   * @param path
   * @param size
   */
  @Test(dataProvider = "getAccordionCount")
  public void verifyAccordionExpanded(String path, int size) {
    TestRunner.runDesktop(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      //click 'Open All' button
      page.getOpenAll().click();
      for (int i = 0; i < size; i++) {
        //Verify that the accordion section is expanded
        Assert.assertTrue(page.getAccordion().get(i).isExpanded(), "Accordion section - " + page.getAccordion().get(i).getTitle() + " is collapsed");
      }
    });
  }

  /**
   * Verify that accordion 'Description' section text contains more than minimum number of characters
   *
   * @param path
   * @param index index of accordion item
   * @param min   min number of characters
   */
  @Test(dataProvider = "getAccordionDescription")
  public void verifyAccordionDescriptionSection(String path, int index, int min) {
    TestRunner.runDesktop(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      AccordionItem ai = page.getAccordion().get(index);
     //verify text length
      Assert.assertTrue(ai.getField(ai.DESCRIPTION).length() >= min, "expected text length does not match");
    });
  }

  /**
   * Verify that accordion 'Eligibility' section text contains more than minimum number of characters
   *
   * @param path
   * @param index index of accordion item
   * @param min   min number of characters
   */
  @Test(dataProvider = "getAccordionEligibility")
  public void verifyAccordionEligibilitySection(String path, int index, int min) {
    TestRunner.runDesktop(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      AccordionItem ai = page.getAccordion().get(index);
      //expand accordion item
      ai.expand();
      //verify text length
      Assert.assertTrue(ai.getField(ai.ELIGIBILITY).length() >= min, "expected text length does not match");
    });
  }

  /**
   * This test verifies Location section of an accordion
   * it checks that after clicking on the title it is expanded
   * verifies that 'All' option is selected by default in the location dropdown
   * verifies that at least 1 location (1 state) is displayed in the section body
   * Verifies that the dropdown list of states is sorted
   *
   * @param path
   * @param defaul default value from location dropdown ('All')
   */
  @Test(dataProvider = "getAccordionLocationDropDown")
  public void verifyLocationSection(String path, String defaul, String selectState, String result) {
    TestRunner.runDesktop(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      LocationAndContacts ai = page.getAccordion().getLocationAccordion();
      //expand accordion item
      ai.expand();
      //Verify that location section is expanded
      Assert.assertTrue(ai.isExpanded(), "Location section is collapsed");
      //Verify that 'All' is selected by default in the dropdown
      Assert.assertEquals(ai.getSelectedState(), defaul, "'All' is not selected by default");
      // Verify at least 1 location is displayed in the body section
      Assert.assertTrue(ai.getNumberOfLocations() >= 1, "no locations are displayed");
      //Verify that the list of state is sorted in dropdown
      Assert.assertTrue(ai.isStateDropdownSorted(), "states dropdown is not sorted");
    });
  }

  /**
   * This test verifies states dropdown functionality
   * It selects the state from drop down and verifies that the number of states displayed is now 1 (in the section
   * body) and that single displayed location is the one that was selected
   *
   * @param path
   * @param selectState state to select from drop down
   * @param result      state that is displayed in the body of section after selecting state in the dropdown
   */
  @Test(dataProvider = "getAccordionLocationDropDown")
  public void verifyLocationSectionDropDownFunction(String path, String defaul, String selectState, String result) {
    TestRunner.runDesktop(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      //expand accordion item
      LocationAndContacts ai = page.getAccordion().getLocationAccordion();
      ai.expand();
      //select state from dropdown
      ai.selectState(selectState);
      // verify that only one state displayed in the section body
      Assert.assertEquals(ai.getNumberOfLocations(), 1, "More than state is displayed");
      // verify that displayed state is the same as selected
      Assert.assertEquals(ai.getHeaderOfSelectedLocation(), result, "incorrect state is displayed compared to what was selected");
    });
  }

  /**
   * Verify that accordion 'Trial Objectives and Outline' section text contains more than minimum number of characters
   *
   * @param path
   * @param index index of accordion item
   * @param min   min number of characters
   */
  @Test(dataProvider = "getAccordionObjectives")
  public void verifyAccordionObjectiveSection(String path, int index, int min) {
    TestRunner.runDesktop(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      //expand an accordion section
      AccordionItem ai = page.getAccordion().get(index);
      ai.expand();
      Assert.assertTrue(ai.getField(ai.TRIAL_OBJECTIVES).length() >= min, "expected text length does not match");
    });
  }

  /**
   * This test verifies phase field from 'Trial Phase & Type' accordion section
   *
   * @param path
   * @param index  index of an accordion item
   * @param phases list of all possible trial phases
   * @param types  list of all possible trial types
   */
  @Test(dataProvider = "getAccordionPhase")
  public void verifyAccordionPhaseAndTypeSectionPhaseField(String path, int index,
                                                           List<String> phases, List<String> types) {
    TestRunner.runDesktop(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      //expand an accordion section
      AccordionItem ai = page.getAccordion().get(index);
      ai.expand();
      //comparePhase boolean is set to false
      //if the trial type field matches expected data it's value is changed to 'true'
      boolean comparePhase = false;
      for (String phase : phases) {
        if (ai.getField(ai.TRIAL_PHASE).endsWith(phase)) {
          comparePhase = true;
          break;
        }
      }
      Assert.assertTrue(comparePhase, "Trial Phase field value is incorrect");
    });
  }

  /**
   * This test verifies type field from 'Trial Phase & Type' accordion section
   *
   * @param path
   * @param index  index of an accordion item
   * @param phases list of all possible trial phases
   * @param types  list of all possible trial types
   */
  @Test(dataProvider = "getAccordionPhase")
  public void verifyAccordionPhaseAndTypeSectionTypeField(String path, int index,
                                                          List<String> phases, List<String> types) {
    TestRunner.runDesktop(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      //expand an accordion section
      AccordionItem ai = page.getAccordion().get(index);
      ai.expand();
      //compareType boolean is set to false
      //if the trial type field matches expected data it's value is changed to 'true'
      boolean compareType = false;
      for (String type : types) {
        if (ai.getField(ai.TRIAL_TYPE).endsWith(type)) {
          compareType = true;
          break;
        }
      }
      Assert.assertTrue(compareType, "Trial Type field value is incorrect");

    });
  }

  /**
   * Test is verifying Organization section of an accordion
   * It retrieves 'Lead Organization' and 'Principal Investigator' fields and verifies that it's values
   * start as expected
   *
   * @param path
   * @param index              index of an accordion item to retrieve
   * @param lead_organization, principal - fields values
   */
  @Test(dataProvider = "getAccordionOrganization")
  public void verifyAccordionOrganizationSection(String path, int index, String lead_organization, String principal) {
    TestRunner.runDesktop(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      //expand accordion item
      AccordionItem ai = page.getAccordion().get(index);
      ai.expand();
      Assert.assertTrue(ai.getField(ai.LEAD_ORGANIZATION).startsWith(lead_organization), "lead organization field does not match");
      Assert.assertTrue(ai.getField(ai.PRINCIPAL_INVESTIGATOR).startsWith(principal), "principal investigator field  does not match");
    });
  }

  /**
   * Test is verifying Organization section of an accordion
   * It retrieves 'PrimaryID','SecondaryID' and 'CancerGovIDr' fields and verifies that it's values
   * are expected
   *
   * @param path
   * @param index            index of an accordion item to retrieve
   * @param fieldPrimaryID   primary id field label
   * @param secondaryID      sec id contains 'NCI-'
   * @param CTSID            - ClinicalTrials.gov ID starts with 'NCT' letters
   */
  @Test(dataProvider = "getAccordionTrialID")
  public void verifyAccordionTrialIDSection(String path, int index, String fieldPrimaryID, String secondaryID, String CTSID) {
    TestRunner.runDesktop(TrialDetailsPage.class, path, (TrialDetailsPage page) -> {
      //retrieve an accordion item
      AccordionItem ai = page.getAccordion().get(index);
      //expand accordion item
      ai.expand();
      Assert.assertTrue(ai.getField(ai.PRIMARY_TRIAL_ID).startsWith(fieldPrimaryID), "primary id does not match");
      Assert.assertTrue(ai.getField(ai.SECONDARY_TRIAL_ID).contains(secondaryID), " secondary id doesn not match");
      Assert.assertTrue(ai.getField(ai.CANCER_GOV_ID).startsWith(CTSID), "cancerGovID does not match");
    });
  }


  /**********************DATA PROVIDERS***************************/

  /**
   * retrieve paths for Search Results pages
   *
   * @return
   */
  @DataProvider(name = "getTrialDetailsPage")
  public Iterator<Object[]> getTrialDetailsPage() {
    String[] columns = {"path"};
    return new ExcelDataReader(getDataFilePath("clinical-trial-details-data.xlsx"), "trial_details_page", columns);

  }

  /**
   * retrieves paths with criteria section only
   *
   * @return
   */
  @DataProvider(name = "getCriteriaSection")
  public Iterator<Object[]> getCriteriaSection() {
    String[] columns = {"path", "criteriaLabel"};
    Iterator<Object[]> data = new ExcelDataReader(getDataFilePath("clinical-trial-details-data.xlsx"), "trial_details_page", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (data.hasNext()) {
      Object[] item = data.next();
      String rawLabel = (String) item[1];
      if (!rawLabel.equals("null")) {
        converted.add(new Object[]{item[0]});
      }
    }
    return converted.iterator();
  }

  /**
   * Retrieve path and start over link path
   *
   * @return
   */
  @DataProvider(name = "getStartOverLink")
  public Iterator<Object[]> getStartOverLink() {
    String[] columns = {"path", "startOverPath"};
    return new ExcelDataReader(getDataFilePath("clinical-trial-details-data.xlsx"), "trial_details_page", columns);

  }

  /**
   * retrieve path and BackToSearch links
   *
   * @return
   */
  @DataProvider(name = "getBackToSearch")
  public Iterator<Object[]> getBackToSearch() {
    String[] columns = {"path", "backToSearchPath"};
    return new ExcelDataReader(getDataFilePath("clinical-trial-details-data.xlsx"), "trial_details_page", columns);

  }

  /**
   * retrieve trial status text
   *
   * @return
   */
  @DataProvider(name = "getTrialStatus")
  public Iterator<Object[]> getTrialStatus() {
    String[] columns = {"path", "trialStatus"};
    return new ExcelDataReader(getDataFilePath("clinical-trial-details-data.xlsx"), "trial_details_page", columns);

  }

  /**
   * retrieve paths to pages, help delighter link and title
   *
   * @return
   */
  @DataProvider(name = "getHelpDelighter")
  public Iterator<Object[]> getHelpDelighter() {
    String[] columns = {"path", "helpDelighterLink", "helpDelighterTitle"};
    return new ExcelDataReader(getDataFilePath("clinical-trial-details-data.xlsx"), "trial_details_page", columns);

  }

  /**
   * Data provider retrieves  Search Results page paths, Trial Checklist link and title
   *
   * @return
   */
  @DataProvider(name = "getTrialCheckListDelighter")
  public Iterator<Object[]> getTrialCheckListDelighter() {
    String[] columns = {"path", "trialChecklistDelighterLink", "trialChecklistDelighterTitle"};
    return new ExcelDataReader(getDataFilePath("clinical-trial-details-data.xlsx"), "trial_details_page", columns);
  }

  /**
   * Data provider retrieves  Search Results page paths and Search Criteria labels and values
   *
   * @return
   */
  @DataProvider(name = "getCriteria")
  public Iterator<Object[]> getCriteria() {
    String[] columns = {"path", "criteriaLabel", "criteriaValue"};
    Iterator<Object[]> data = new ExcelDataReader(getDataFilePath("clinical-trial-details-data.xlsx"), "trial_details_page", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    List<String> labels;
    List<String> values;
    while (data.hasNext()) {
      Object[] item = data.next();
      String rawLabel = (String) item[1];
      String rawValue = (String) item[2];
      if (!rawLabel.equals("null")) {
        labels = Arrays.asList(rawLabel.split(","));
        values = Arrays.asList(rawValue.split(","));
        converted.add(new Object[]{item[0], labels, values});
      }
    }
    return converted.iterator();
  }

  /**
   * Data provider retrieves page paths and Accordion items titles
   *
   * @return
   */
  @DataProvider(name = "getAccordionTitles")
  public Iterator<Object[]> getAccordionTitles() {
    String[] columns = {"path", "accordionTitle"};
    Iterator<Object[]> data = new ExcelDataReader(getDataFilePath("clinical-trial-details-data.xlsx"), "trial_details_page", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    List<String> titles;
    while (data.hasNext()) {
      Object[] item = data.next();
      String rawTitle = (String) item[1];
      titles = Arrays.asList(rawTitle.split(","));
      converted.add(new Object[]{item[0], titles});
    }
    return converted.iterator();
  }

  /**
   * Data provider retrieves paths and number of Accordion
   *
   * @return
   */
  @DataProvider(name = "getAccordionCount")
  public Iterator<Object[]> getAccordionCount() {
    String[] columns = {"path"};
    Iterator<Object[]> data = new ExcelDataReader(getDataFilePath("clinical-trial-details-data.xlsx"), "trial_details_page", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    int count = 7;
    while (data.hasNext()) {
      Object[] item = data.next();
      converted.add(new Object[]{item[0], count});
    }
    return converted.iterator();
  }

  /**
   * Data provider retrieves  page paths and states drop down values to select with states names to be displayed
   *
   * @return
   */
  @DataProvider(name = "getAccordionLocationDropDown")
  public Iterator<Object[]> getAccordionLocationDropDown() {
    String[] columns = {"path", "stateDefault", "stateToSelect", "stateResult"};
    return new ExcelDataReader(getDataFilePath("clinical-trial-details-data.xlsx"), "accordion_locations", columns);
  }

  /**
   * Retrieves Description Section of an Accordion with it's index and field to validate
   *
   * @return
   */
  @DataProvider(name = "getAccordionDescription")
  public Iterator<Object[]> getAccordionDescription() {
    String[] columns = {"path", "index"};
    Iterator<Object[]> data = new ExcelDataReader(getDataFilePath("clinical-trial-details-data.xlsx"), "accordion_description", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    int min = 30;
    while (data.hasNext()) {
      Object[] item = data.next();
      String raw = (String) item[1];
      int ind = Integer.parseInt(raw);
      converted.add(new Object[]{item[0], ind, min});
    }
    return converted.iterator();
  }

  /**
   * Retrieves Eligibility Section of an Accordion with it's index and field to validate
   *
   * @return
   */
  @DataProvider(name = "getAccordionEligibility")
  public Iterator<Object[]> getAccordionEligibility() {
    String[] columns = {"path", "index"};
    Iterator<Object[]> data = new ExcelDataReader(getDataFilePath("clinical-trial-details-data.xlsx"), "accordion_eligibility", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    int min = 200;
    while (data.hasNext()) {
      Object[] item = data.next();
      String raw = (String) item[1];
      int ind = Integer.parseInt(raw);
      converted.add(new Object[]{item[0], ind, min});
    }
    return converted.iterator();
  }

  /**
   * Retrieves Trial Objectives Section of an Accordion with it's index and field to validate
   *
   * @return
   */
  @DataProvider(name = "getAccordionObjectives")
  public Iterator<Object[]> getAccordionObjectives() {
    String[] columns = {"path", "index"};
    Iterator<Object[]> data = new ExcelDataReader(getDataFilePath("clinical-trial-details-data.xlsx"), "accordion_objectives", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    int min = 80;
    while (data.hasNext()) {
      Object[] item = data.next();
      String raw = (String) item[1];
      int ind = Integer.parseInt(raw);
      converted.add(new Object[]{item[0], ind, min});
    }
    return converted.iterator();
  }

  /**
   * Retrieves Organization Section of an Accordion with it's index and fields to validate
   *
   * @return
   */
  @DataProvider(name = "getAccordionOrganization")
  public Iterator<Object[]> getAccordionOrganization() {
    String[] columns = {"path", "index", "field"};
    Iterator<Object[]> data = new ExcelDataReader(getDataFilePath("clinical-trial-details-data.xlsx"), "accordion_lead_organization", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    List<String> fieldsList;
    while (data.hasNext()) {
      Object[] item = data.next();
      String raw = (String) item[1];
      int ind = Integer.parseInt(raw);
      String fields = (String) item[2];
      fieldsList = Arrays.asList(fields.split(","));
      converted.add(new Object[]{item[0], ind, fieldsList.get(0), fieldsList.get(1)});
    }
    return converted.iterator();
  }

  /**
   * Retrieves Trial Phase and Type Section of an Accordion with it's index,fields to validate and all possible
   * variations of type and phase
   *
   * @return
   */
  @DataProvider(name = "getAccordionPhase")
  public Iterator<Object[]> getAccordionPhase() {
    String[] columns = {"path", "index", "phaseOptions", "typeOption"};
    Iterator<Object[]> data = new ExcelDataReader(getDataFilePath("clinical-trial-details-data.xlsx"), "accordion_phase", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    List<String> phaseoption;
    List<String> typeoption;
    while (data.hasNext()) {
      Object[] item = data.next();
      String raw = (String) item[1];
      int ind = Integer.parseInt(raw);
      String options = (String) item[2];
      String types = (String) item[3];
      phaseoption = Arrays.asList(options.split(","));
      typeoption = Arrays.asList(types.split(","));
      converted.add(new Object[]{item[0], ind, phaseoption, typeoption});
    }
    return converted.iterator();
  }

  /**
   * Retrieves Trial ID Section of an Accordion with it's index,fields to validate and all possible
   * variations of IDs
   *
   * @return
   */
  @DataProvider(name = "getAccordionTrialID")
  public Iterator<Object[]> getAccordionTrialID() {
    String[] columns = {"path", "index", "fieldPrimaryID", "secondaryID", "CTSID"};
    Iterator<Object[]> data = new ExcelDataReader(getDataFilePath("clinical-trial-details-data.xlsx"), "accordion_trial_id", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (data.hasNext()) {
      Object[] item = data.next();
      String raw = (String) item[1];
      int ind = Integer.parseInt(raw);
      converted.add(new Object[]{item[0], ind, item[2], item[3], item[4]});
    }
    return converted.iterator();

  }


}

