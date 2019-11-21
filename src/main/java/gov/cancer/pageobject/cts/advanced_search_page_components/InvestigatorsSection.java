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
  private AutoSuggestField investigators;
  // help link
  private Link helpLink;
  // Title Element
  private WebElement title;

  //LOCATORS
  private static final String INVESTIGATORS_LOCATOR = ":scope #inv";
  private final static String HELP_LINK_LOCATOR = ":scope legend a";
  private final static String TITLE_LOCATOR = ":scope legend span";


  /**
   * Constructor
   * @param element element defining section
   */
  public InvestigatorsSection (WebDriver driver, WebElement element){
    super(element);
    investigators = new AutoSuggestField(driver, ElementHelper.findElement(element, INVESTIGATORS_LOCATOR));
    title = ElementHelper.findElement(element, TITLE_LOCATOR);
    helpLink = new Link(ElementHelper.findElement(element, HELP_LINK_LOCATOR));

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

}
