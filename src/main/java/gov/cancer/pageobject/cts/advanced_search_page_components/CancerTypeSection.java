package gov.cancer.pageobject.cts.advanced_search_page_components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.AutoSuggestField;
import gov.cancer.pageobject.components.Button;
import gov.cancer.pageobject.components.Component;
import gov.cancer.pageobject.helper.Link;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This class represents Cancer Type/Condition section on the Advanced Search page
 */
public class CancerTypeSection extends Component {
  // button field to expand autoSuggest field Cancer Type
  private Button all;
  // autoSuggest field Cancer Type
  private AutoSuggestField cancerTypeCondition;
  //autoSuggest field Cancer SubType
  private AutoSuggestField subType;
  // autoSuggest field stage
  private AutoSuggestField stage;
  // autoSuggest field Side Effects
  private AutoSuggestField sideEffects;
  // help link
  private Link helpLink;
  // WebDriver used for creating Autosuggest objects
  private WebDriver driver;
  // Entire section element
  private WebElement section;

  /********LOCATORS********/
  private static final String ALL_BUTTON_LOCATOR = ":scope #ct-btn";
  private static final String CANCER_TYPE_CONDITION_LOCATOR = ":scope #ctMenu input";
  private static final String SUBTYPE_LOCATOR = ":scope input#st";
  private static final String STAGE_LOCATOR = ":scope input#stg";
  private static final String SIDE_EFFECTS_LOCATOR = ":scope input#fin";
  private final static String HELP_LINK_LOCATOR = ":scope legend a";

  /**
   * Constructor
   * The entire section is collapsed by default therefore only 'All' button
   * and help link object are  being initialized in the constructor
   */

  public CancerTypeSection(WebDriver driver, WebElement element) {
    super(element);
    this.section = element;
    this.driver = driver;
    all = new Button(ElementHelper.findElement(element, ALL_BUTTON_LOCATOR));
    helpLink = new Link(ElementHelper.findElement(section, HELP_LINK_LOCATOR));

  }

  /**
   * Getters for SubType Autosuggest field
   *
   * @return
   */

  public AutoSuggestField getSubType() {
    subType = new AutoSuggestField(driver, ElementHelper.findElement(section, SUBTYPE_LOCATOR));
    return subType;
  }

  /**
   * Getter for Stage Autosuggest field
   *
   * @return
   */
  public AutoSuggestField getStage() {
    stage = new AutoSuggestField(driver, ElementHelper.findElement(section, STAGE_LOCATOR));
    return stage;
  }

  /**
   * Getter for Side Effects field
   *
   * @return
   */
  public AutoSuggestField getSideEffects() {
    sideEffects = new AutoSuggestField(driver, ElementHelper.findElement(section, SIDE_EFFECTS_LOCATOR));
    return sideEffects;
  }

  /**
   * Getter method for Cancer Type/Condition Autosuggest field
   *
   * @return getType Autosuggest object
   */
  public AutoSuggestField getCancerTypeCondition() {
    cancerTypeCondition = new AutoSuggestField(driver, ElementHelper.findElement(section, CANCER_TYPE_CONDITION_LOCATOR));
    return cancerTypeCondition;
  }

  /**
   * Getter for 'All' button
   *
   * @return
   */
  public Button getAllButton() {
    return all;
  }
}
