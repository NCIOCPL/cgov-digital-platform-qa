package gov.cancer.pageobject.cts.advanced_search_page_components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.AutoSuggestField;
import gov.cancer.pageobject.components.Component;
import gov.cancer.pageobject.helper.Link;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * class represents investigators section on Advanced Search page
 */
public class InvestigatorsSection extends Component {

  //trial investigators field
  private WebElement element;
  private AutoSuggestField investigators;
  // help link
  private Link helpLink;
  // Title Element
  private WebElement title;
  // help text under trial investigators input field
  private WebElement helptext;
  // element represents AutoSuggest text field
  private WebElement textField;

  //LOCATORS
  private static final String INVESTIGATORS_LOCATOR = ":scope #inv";
  private final static String HELP_LINK_LOCATOR = ":scope legend a";
  private final static String TITLE_LOCATOR = ":scope legend span";
  private final static String HELP_TEXT_LOCATOR =":scope div >div>span";

  /**
   * Constructor
   * @param element element defining section
   */
  public InvestigatorsSection (WebDriver driver, WebElement element){
    super(element);
    this.element=element;
    textField = ElementHelper.findElement(element, INVESTIGATORS_LOCATOR);
    investigators = new AutoSuggestField(driver,textField);
    title = ElementHelper.findElement(element, TITLE_LOCATOR);
    helpLink = new Link(ElementHelper.findElement(element, HELP_LINK_LOCATOR));
    helptext = ElementHelper.findElement(element, HELP_TEXT_LOCATOR);

  }

  /**
   * Getter for investigator autosuggest field
   */
  public AutoSuggestField getInvestigators() {
    return investigators;
  }

  /**
   * Returns title text
   */
  public String getTitle(){
    return title.getText();
  }

  /**
   * Getter for Help Link
   */
  public Link getHelpLink() {
    return helpLink;
  }

  /**
   * Getter for help text under the trial investigators textfield
   * @return
   */
  public String getHelpText(){
    return helptext.getText();
  }

  /**
   * Getter for autocomplete help text under the trial investigators textfield
   * @return
   */
  public String getAutoCompleteHelpText () {
    return ElementHelper.getText(element,":scope div.menu-anchor");
  }

  /**
   * Method returns place holder helper text
   * @return
   */
  public String getPlaceHolderText () { return textField.getAttribute("placeholder"); }
}
