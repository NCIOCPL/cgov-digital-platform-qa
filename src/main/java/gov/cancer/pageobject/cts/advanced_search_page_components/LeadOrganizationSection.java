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
  private AutoSuggestField organizations;
  // help link
  private Link helpLink;
  // Title Element
  private WebElement title;

  private final static String ORGANIZATIONS_LOCATOR = ":scope div div input";
  private final static String HELP_LINK_LOCATOR = ":scope legend a";
  private final static String TITLE_LOCATOR = ":scope legend span";

  /**
   * Constructor
   * @param element
   *          - webelement defining lead organization section
   */
  public LeadOrganizationSection (WebDriver driver, WebElement element){
    super(element);
    organizations = new AutoSuggestField(driver, ElementHelper.findElement(element, ORGANIZATIONS_LOCATOR));
    helpLink = new Link(ElementHelper.findElement(element, HELP_LINK_LOCATOR));
    title = ElementHelper.findElement(element, TITLE_LOCATOR);

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
}
