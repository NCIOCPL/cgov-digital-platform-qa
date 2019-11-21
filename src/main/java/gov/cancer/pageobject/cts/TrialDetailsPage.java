package gov.cancer.pageobject.cts;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.cts.components.Accordion;
import gov.cancer.pageobject.components.Button;
import gov.cancer.pageobject.components.Delighter;
import gov.cancer.pageobject.cts.components.CriteriaSection;
import gov.cancer.pageobject.helper.Link;
import org.openqa.selenium.WebElement;

/**
 * This class represents Trial description page of CTS module
 */
public class TrialDetailsPage extends CTSPage {

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
  private Delighter shareDelighter;
  //back to search results button
  private Button backToSearch;

  //webElement locators:
  private final static String BACK_TO_SEARCH_LOCATOR = "div.btnAsLink";
  private final static String CRITERIA_SECTION_LOCATOR = "div[class='cts-accordion  table-dropdown']";
  private final static String START_OVER_LINK_LOCATOR = "div [class$='page__header'] a";
  private final static String TRIAL_STATUS_LOCATOR = ".trial-status-indicator";
  private final static String OPEN_ALL_LOCATOR = "button[class$='button open']";
  private final static String CLOSE_ALL_LOCATOR = "button[class$='button close']";
  private final static String SHARE_DELIGHTER_LOCATOR = "div[class$='cts-share']";
  private final static String SHARE_PRINT_DELIGHTER_LOCATOR = "button[class$='print']";
  private final static String SHARE_EMAIL_DELIGHTER_LOCATOR = "button[class$='email']";
  private final static String ACCORDION_LOCATOR = "div[class$='page__content'] .cts-accordion";

  /**
   * Constructor
   *
   * @param path
   */
  public TrialDetailsPage(String path) {
    super(path);
    backToSearch = new Button(ElementHelper.findElement(getBrowser(), BACK_TO_SEARCH_LOCATOR));
    criteriaSection = new CriteriaSection(ElementHelper.findElement(getBrowser(), CRITERIA_SECTION_LOCATOR));
    accordion = new Accordion(ElementHelper.findElement(getBrowser(), ACCORDION_LOCATOR));
    startOverLink = new Link(ElementHelper.findElement(getBrowser(), START_OVER_LINK_LOCATOR));
    trialStatus = ElementHelper.findElement(getBrowser(), TRIAL_STATUS_LOCATOR);
    openAll = new Button(ElementHelper.findElement(getBrowser(), OPEN_ALL_LOCATOR));
    closeAll = new Button(ElementHelper.findElement(getBrowser(), CLOSE_ALL_LOCATOR));
    shareDelighter = new Delighter(ElementHelper.findElement(getBrowser(), SHARE_DELIGHTER_LOCATOR));
  }

  /**
   * Getter method for accordion
   * @return
   */
  public Accordion getAccordion() {
    return accordion;
  }

  /**
   * Getter method for Criteria section
   * @return
   */
  public CriteriaSection getCriteriaSection() {
    return criteriaSection;
  }

  /**
   * Getter method for Start Over link
   * @return
   */
  public Link getStartOverLink() {
    return startOverLink;
  }

  /**
   * Getter method for 'Open All' button
   * @return
   */
  public Button getOpenAll() {
    return openAll;
  }

  /**
   * Getter  method for 'Close All' button
   * @return
   */
  public Button getCloseAll() {
    return closeAll;
  }

  /**
   * getter method for 'Share with your doctor' delighter
   * @return
   */
  public Delighter getShareDelighter() {
    return shareDelighter;
  }

  /**
   * Getter for Back to Search button
   * @return
   */
  public Button getBackToSearch() {
    return backToSearch;
  }

  /**
   * Method returns trial status text
   *
   * @return
   */
  public String getTrialStatus() {
    return trialStatus.getText();
  }

}
