package gov.cancer.pageobject.cts.advanced_search_page_components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.framework.ScrollUtil;
import gov.cancer.pageobject.components.Component;
import gov.cancer.pageobject.cts.components.AutoSuggest;
import gov.cancer.pageobject.helper.Link;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This class represents Cancer Type/Condition section on the Advanced Search page
 */
public class CancerTypeSection extends Component {
  // Cancer Type dropdown field
  private WebElement dropdown;
  // autoSuggest field Cancer Type
  private AutoSuggest cancerTypeCondition;
  // help link
  private Link helpLink;
  // WebDriver used for creating Autosuggest objects
  private WebDriver driver;
  // Entire section element
  private WebElement section;

  /********LOCATORS********/
  private static final String CANCER_TYPE_DROPDOWN = ":scope #ct-btn";
  private static final String CANCER_TYPE_CONDITION_LOCATOR = ":scope div#ct-searchTerm-autocomplete-wrapper";
  private static final String SUBTYPE_LOCATOR = ":scope div#st-autocomplete-wrapper";
  private static final String STAGE_LOCATOR = ":scope div#stg-autocomplete-wrapper";
  private static final String SIDE_EFFECTS_LOCATOR = ":scope div#fin-autocomplete-wrapper";
  private final static String HELP_LINK_LOCATOR = ":scope legend a";
   /**
   * Constructor
   * The entire section is collapsed by default therefore only Primary Cancer Type dropdown
   * and help link object are  being initialized in the constructor
   */

  public CancerTypeSection(WebDriver driver, WebElement element) {
    super(element);
    this.section = element;
    this.driver = driver;
    dropdown = ElementHelper.findElement(element, CANCER_TYPE_DROPDOWN);
    helpLink = new Link(ElementHelper.findElement(section, HELP_LINK_LOCATOR));
    cancerTypeCondition = new AutoSuggest(driver, ElementHelper.findElement(section, CANCER_TYPE_CONDITION_LOCATOR));
  }

  /**
   * Getters for SubType Autosuggest field
   * Initializing 'SubType' object here as it is only available after selecting Cancer Type
   * @return
   */

  public AutoSuggest getSubType() {
    return new AutoSuggest(driver, ElementHelper.findElement(section, SUBTYPE_LOCATOR));
  }

  /**
   * Getter for Stage Autosuggest field
   * Initializing 'Stage' object here as it is only available after after selecting Cancer Type
   * @return
   */
  public AutoSuggest getStage() {
    return new AutoSuggest(driver, ElementHelper.findElement(section, STAGE_LOCATOR));
  }

  /**
   * Getter for Side Effects field
   * Initializing 'SideEffects' object here as it is only available after selecting Cancer Type
   * @return
   */
  public AutoSuggest getSideEffects() {
    return new AutoSuggest(driver, ElementHelper.findElement(section, SIDE_EFFECTS_LOCATOR));
  }

  /**
   * Getter method for Cancer Type/Condition Autosuggest field
   * @return getType Autosuggest object
   */
  public AutoSuggest getCancerTypeCondition() {
    return cancerTypeCondition;
  }

  /**
   * This method is clicking the Primary CancerType dropdown
   *
   * @return
   */

  public void ActivatePrimaryCancerTypeField(){
  dropdown.click();
}
  /**
   * This method is scrolling until the Primary Cancer Type section is visible
   * It is necessary, because of the presence of 'sticky block (form action)' which receives the click
   */
  public void scrollUntilPrimaryCancerTypeVisible(){
    ScrollUtil.scrollIntoview(driver, dropdown );
  }
}
