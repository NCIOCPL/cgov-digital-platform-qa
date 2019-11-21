package gov.cancer.pageobject.cts;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.Button;
import gov.cancer.pageobject.components.CheckBox;
import gov.cancer.pageobject.components.Pager;
import gov.cancer.pageobject.cts.components.CriteriaSection;
import gov.cancer.pageobject.cts.components.ResultListSection;
import gov.cancer.pageobject.helper.BlobOfText;
import gov.cancer.pageobject.helper.Link;
import org.openqa.selenium.WebElement;


/**
 * This page represents Search Results page of CTS
 */
public class SearchResultsPage extends CTSPage {
  //Result List section object
  private ResultListSection resultListSection;
  //Search Criteria section
  private CriteriaSection criteriaSection;
  //Start Over Link
  private Link startOverLink;
  //Modify Search Criteria Button
  private Button modifySearchCriteriaButton;
  //Select All checkBox
  private CheckBox selectAllOnPage;
  //Print Selected Button
  private Button printSelected;
  //Pager
  private Pager pager;

  //WebElement represents Top Control section of the page
  private WebElement pageControlTop;
  //WebElement represents Bottom Control section of the page
  private WebElement pageControlBottom;

  // LOCATORS
  private final static String RESULT_LIST_LOCATOR = "div.results-list";
  private final static String START_OVER_LINK_LOCATOR = "div.cts-results-header div a";
  private final static String SELECT_ALL_CHECK_BOX_LOCATOR = "div[class='cts-checkbox check-all']";
  private final static String CRITERIA_SECTION_LOCATOR = "div[class='cts-accordion  table-dropdown'";
  private final static String MODIFY_SEARCH_CRITERIA_LOCATOR = "div.reset-form .btnAsLink";
  private final static String PAGE_CONTROL_TOP_LOCATOR = "[class='results-page__control --top']";
  private final static String PAGE_CONTROL_BOTTOM_LOCATOR = "[class='results-page__control --bottom']";
  private final static String PRINT_BUTTON_LOCATOR = ":scope button";
  private final static String PAGER_LOCATOR = ":scope .pager__nav";
  private final static String PRINT_MODAL_LOCATOR = ".cts-modal";
  private final static String TRY_NEW_SEARCH_LINK_LOCATOR = "div.results-list a";
  private final static String NO_TRIALS_FOUND_HEADER_LOCATOR = "div.no-trials-found strong";


  /**
   * Main constructor
   * Initializes main section of the page
   *
   * @param path
   */
  public SearchResultsPage(String path) {
    super(path);
    resultListSection = new ResultListSection(ElementHelper.findElement(getBrowser(), RESULT_LIST_LOCATOR));


  }

  /**
   * Getter method for Search Result list section
   *
   * @return
   */
  public ResultListSection getResultListSection() {
    return resultListSection;
  }

  /**
   * Getter for Criteria Section
   *
   * @return
   */
  public CriteriaSection getCriteriaSection() {
    this.criteriaSection = new CriteriaSection(ElementHelper.findElement(getBrowser(), CRITERIA_SECTION_LOCATOR));
    return criteriaSection;
  }

  /**
   * Getter for StartOver Link
   *
   * @return
   */
  public Link getStartOverLink() {
    startOverLink = new Link(ElementHelper.findElement(getBrowser(), START_OVER_LINK_LOCATOR));
    return startOverLink;
  }

  /**
   * Getter for ModifySearchCriteria button
   *
   * @return
   */
  public Button getModifySearchCriteriaButton() {
    this.modifySearchCriteriaButton = new Button(ElementHelper.findElement(getBrowser(), MODIFY_SEARCH_CRITERIA_LOCATOR));
    return modifySearchCriteriaButton;
  }

  /**
   * Getter for Select All checkbox on top of page
   *
   * @return
   */
  public CheckBox getTopSelectAllOnPageCheckbox() {
    this.pageControlTop = ElementHelper.findElement(getBrowser(), PAGE_CONTROL_TOP_LOCATOR);
    this.selectAllOnPage = new CheckBox(ElementHelper.findElement(pageControlTop, SELECT_ALL_CHECK_BOX_LOCATOR));
    return selectAllOnPage;
  }

  /**
   * Getter for Select All checkbox at the bottom of page
   *
   * @return
   */
  public CheckBox getBottomSelectAllOnPageCheckbox() {
    this.pageControlBottom = ElementHelper.findElement(getBrowser(), PAGE_CONTROL_BOTTOM_LOCATOR);
    this.selectAllOnPage = new CheckBox(ElementHelper.findElement(pageControlBottom, SELECT_ALL_CHECK_BOX_LOCATOR));
    return selectAllOnPage;
  }

  /**
   * Getter for Print button on top of page
   *
   * @return
   */
  public Button getTopPrintSelected() {
    this.pageControlTop = ElementHelper.findElement(getBrowser(), PAGE_CONTROL_TOP_LOCATOR);
    this.printSelected = new Button(ElementHelper.findElement(pageControlTop, PRINT_BUTTON_LOCATOR));
    return printSelected;
  }

  /**
   * Getter for Print button at the bottom  of the page
   *
   * @return
   */
  public Button getBottomPrintSelected() {
    this.pageControlBottom = ElementHelper.findElement(getBrowser(), PAGE_CONTROL_BOTTOM_LOCATOR);
    this.printSelected = new Button(ElementHelper.findElement(pageControlBottom, PRINT_BUTTON_LOCATOR));
    return printSelected;
  }

  /**
   * Getter for Top Pager
   *
   * @return
   */
  public Pager getTopPager() {
    this.pageControlTop = ElementHelper.findElement(getBrowser(), PAGE_CONTROL_TOP_LOCATOR);
    this.pager = new Pager(ElementHelper.findElement(pageControlTop, PAGER_LOCATOR));
    return pager;
  }

  /**
   * Getter for bottom pager
   *
   * @return
   */
  public Pager getBottomPager() {
    this.pageControlBottom = ElementHelper.findElement(getBrowser(), PAGE_CONTROL_BOTTOM_LOCATOR);
    this.pager = new Pager(ElementHelper.findElement(pageControlBottom, PAGER_LOCATOR));
    return pager;
  }


  /**
   * When at least one search result item is selected and Print button clicked, Print Modal box is displayed
   * Also displayed when no item is selected
   * Method is only called when above condition met and it is verifying presence of Print Modal Box
   *
   * @return
   */
  public boolean isPrintModalDisplayed() {
    return ElementHelper.findElement(getBrowser(), PRINT_MODAL_LOCATOR).isDisplayed();
  }

  /**
   * When at least one search result item is selected and Print button clicked, Print Modal box is displayed
   * Also displayed when no item is selected
   * Method is only called when above condition met and it is verifying the text of Print Modal Box
   * Also used to verify the error message that Print Modal contains, when none or more than 100 trials is selected
   */
  public String getPrintModalText() {
    return ElementHelper.findElement(getBrowser(), PRINT_MODAL_LOCATOR).getText();
  }

  /**
   * Retrieve sub header text
   */
  public String getSubHeader() {
    //this.subHeader = ElementHelper.findElement(getBrowser(),"div.all-trials strong");
    return ElementHelper.getText(getBrowser(), "div.all-trials strong");
  }

  /**
   * Method returns "try a new search" link
   */
  public Link getTryNewSearchLink() {
    return new Link(ElementHelper.findElement(getBrowser(), TRY_NEW_SEARCH_LINK_LOCATOR));
  }

  /**
   * Method returns subHeader for no trials found scenario
   */
  public String getNoTrialsHeader() {
    return ElementHelper.getText(getBrowser(), NO_TRIALS_FOUND_HEADER_LOCATOR);
  }

  /**
   * Method returns blob of text for no search results found
   */
  public BlobOfText getNoResultsText() {
    return new BlobOfText(ElementHelper.findElement(getBrowser(), RESULT_LIST_LOCATOR));
  }

  /**
   * Due to 'No Results Found" page locator for 'Start Over' link differ from all other scenarios, this method
   * retrieves "Start Over' link object by using it's unique locator
   * @return link object
   */
  public Link getNoResultsStartOver(){
    return new Link(ElementHelper.findElement(getBrowser(), "div.cts-results-header div> a"));
  }
}
