package gov.cancer.pageobject.cts.advanced_search_page_components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.AutoSuggestField;
import gov.cancer.pageobject.components.Component;
import gov.cancer.pageobject.helper.Link;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Class represents lead organization section on Advanced Search page
 */
public class LeadOrganizationSection extends Component {
  // organizations field
  private WebElement element;
  private AutoSuggestField organizations;
  // help link
  private Link helpLink;
  // Title Element
  private WebElement title;
  // help text under Lead Organization input field
  private WebElement helptext;
  // element represents AutoSuggest text field
  private WebElement textField;

  private final static String ORGANIZATIONS_LOCATOR = ":scope div div input";
  private final static String HELP_LINK_LOCATOR = ":scope legend a";
  private final static String TITLE_LOCATOR = ":scope legend span";
  private final static String HELP_TEXT_LOCATOR =":scope div >div>span";

  /**
   * Constructor
   * @param element
   *          - webelement defining lead organization section
   */
  public LeadOrganizationSection (WebDriver driver, WebElement element){
    super(element);
    this.element=element;
    textField = ElementHelper.findElement(element, ORGANIZATIONS_LOCATOR);
    organizations = new AutoSuggestField(driver, textField);
    helpLink = new Link(ElementHelper.findElement(element, HELP_LINK_LOCATOR));
    title = ElementHelper.findElement(element, TITLE_LOCATOR);
    helptext = ElementHelper.findElement(element, HELP_TEXT_LOCATOR);

  }

  /**
   * Getter for organization field
   * @return autosuggest field
   */
  public AutoSuggestField getOrganizations() {
    return organizations;
  }

  /**
   * Returns title text
   */
  public String getTitle(){
    return title.getText();
  }

  /**
   * Getter for Help Link
   * @return
   */
  public Link getHelpLink(){
    return helpLink;
  }

  /**
   * Getter for help text under the Lead Organization textfield
   * @return
   */
  public String getHelpText(){
    return helptext.getText();
  }

  /**
   * Getter for autocomplete help text under the Lead Organization textfield
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



