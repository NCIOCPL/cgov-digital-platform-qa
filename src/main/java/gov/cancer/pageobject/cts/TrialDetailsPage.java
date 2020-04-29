package gov.cancer.pageobject.cts;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.cts.components.Accordion;
import gov.cancer.pageobject.components.Button;
import gov.cancer.pageobject.components.Delighter;
import gov.cancer.pageobject.cts.components.CriteriaSection;
import gov.cancer.pageobject.cts.components.ResultListSection;
import gov.cancer.pageobject.helper.Link;
import org.openqa.selenium.WebElement;

/**
 * This class represents Trial description page of CTS module
 */
public class TrialDetailsPage extends CTSPage {
  private ResultListSection resultListSection;
  //accordion section
  private Accordion accordion;
  // search criteria drop down
  private CriteriaSection criteriaSection;
  // start over link
  private Link startOverLink;
  // box 'trial status'
  private WebElement trialStatus;
  // open all button
  private Button openAll;
  // close all button
  private Button closeAll;
  //'Share this clinical trial with your doctor' delighter
  private Delighter shareByEmailDelighter;
  //back to search results button
  private Button backToSearch;


  //webElement locators:
  private final static String BACK_TO_SEARCH_LOCATOR = "div.btnAsLink span";
  private final static String CRITERIA_SECTION_LOCATOR = "div[class$='table-dropdown']";
  private final static String START_OVER_LINK_LOCATOR = "div[class$='page__header'] >a";
  private final static String TRIAL_STATUS_LOCATOR = "div.trial-status-indicator";
  private final static String OPEN_ALL_LOCATOR = "button[class$='button open']";
  private final static String CLOSE_ALL_LOCATOR = "button[class$='button close']";
  private final static String SHARE_PRINT_DELIGHTER_LOCATOR = "button[class$='print']";
  private final static String SHARE_EMAIL_DELIGHTER_LOCATOR = "button[class$='email']";
  private final static String ACCORDION_LOCATOR = "div[class$='page__content'] .cts-accordion";

  /**
   * Constructor
   * Due to trial details page is dependent on whether or not trial is active, all tests are starting
   * from the Search Results Page, selecting the very first active trial and only then perform
   * verification - therefore Constructor will initialize resultListSection object and navigate to
   * the first trail - it's details page
   *
   * @param path
   */
  public TrialDetailsPage(String path) {
    super(path);
    resultListSection = new ResultListSection(ElementHelper.findElement(getBrowser(), "div.results-list"));
    resultListSection.getAllResultsItems().get(0).getTitleLink().click();
  }

  /**
   * Getter method for accordion
   *
   * @return
   */
  public Accordion getAccordion() {
    accordion = new Accordion(ElementHelper.findElement(getBrowser(), ACCORDION_LOCATOR));
    return accordion;
  }

  /**
   * Getter method for Criteria section
   *
   * @return
   */
  public CriteriaSection getCriteriaSection() {
    criteriaSection = new CriteriaSection(ElementHelper.findElement(getBrowser(), CRITERIA_SECTION_LOCATOR));
    return criteriaSection;
  }

  /**
   * Getter method for Start Over link
   *
   * @return
   */
  public Link getStartOverLink() {
    startOverLink = new Link(ElementHelper.findElement(getBrowser(), START_OVER_LINK_LOCATOR));
    return startOverLink;
  }

  /**
   * Getter method for 'Open All' button
   *
   * @return
   */
  public Button getOpenAll() {
    openAll = new Button(ElementHelper.findElement(getBrowser(), OPEN_ALL_LOCATOR));
    return openAll;
  }

  /**
   * Getter  method for 'Close All' button
   *
   * @return
   */
  public Button getCloseAll() {
    closeAll = new Button(ElementHelper.findElement(getBrowser(), CLOSE_ALL_LOCATOR));
    return closeAll;
  }

  /**
   * getter method for 'Share with your doctor' delighter
   *
   * @return
   */
  public Delighter getShareByEmailDelighter() {
    shareByEmailDelighter = new Delighter(ElementHelper.findElement(getBrowser(), SHARE_EMAIL_DELIGHTER_LOCATOR));
    return shareByEmailDelighter;
  }

  /**
   * Getter for Back to Search button
   *
   * @return
   */
  public Button getBackToSearch() {
    backToSearch = new Button(ElementHelper.findElement(getBrowser(), BACK_TO_SEARCH_LOCATOR));
    return backToSearch;
  }

  /**
   * Method returns trial status text
   *
   * @return
   */
  public String getTrialStatus() {
    trialStatus = ElementHelper.findElement(getBrowser(), TRIAL_STATUS_LOCATOR);
    return trialStatus.getText();
  }

  /**
   * Getter method for Print Delighter
   * Not initialized in constructor due to it's absence in mobile breakpoint
   */
  public Delighter getShareByPrintDelighter() {
    return new Delighter(ElementHelper.findElement(getBrowser(), SHARE_PRINT_DELIGHTER_LOCATOR));
  }

  /**
   * Method is overriding CTSPage method isHeaderDisplayed() - to ensure the return of Trial Detail Page header, not Search Result header
   *
   * @return whether or not header is displayed
   */
  @Override
  public boolean isHeaderDisplayed() {
    return ElementHelper.findElement(getBrowser(), ".trial-description-page h1").isDisplayed();
  }

}
