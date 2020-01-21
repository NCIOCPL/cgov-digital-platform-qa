package gov.cancer.pageobject.cts.advanced_search_page_components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.Component;
import gov.cancer.pageobject.components.TextField;
import gov.cancer.pageobject.helper.Link;
import org.openqa.selenium.WebElement;

/**
 * Class represents trial id section of Advanced Search page
 */
public class TrialIDSection extends Component {
  // Element represent entire section
  private WebElement section;
  // trial id text field
  private TextField IDText;
  // section title
  private WebElement title;
  // help link
  private Link helpLink;
  // help text under trial id input field
  private WebElement helptext;


  /****** Locators *****/

  private final static String idTextLocator = ":scope input#trialId";
  private final static String HELP_LINK_LOCATOR = ":scope legend a";
  private final static String HELP_TEXT_LOCATOR = ":scope .cts-input__help-text";
  private final static String TITLE_LOCATOR = ":scope legend span";


  /**
   * Constructor
   *
   * @param element
   *          - webelement of trial id section
   */
  public TrialIDSection(WebElement element) {
    super(element);
    section = element;
    IDText = new TextField(ElementHelper.findElement(section, idTextLocator));
    helpLink = new Link(ElementHelper.findElement(section, HELP_LINK_LOCATOR));
    title = ElementHelper.findElement(section, TITLE_LOCATOR);
    helptext = ElementHelper.findElement(section, HELP_TEXT_LOCATOR);
  }

  /**
   * Getter for trialId text field
   *
   * @return
   */
  public TextField getTrialID() {
    return IDText;
  }

  /**
   * Returns title text
   */
  public String getTitle() {
    return title.getText();
  }

  /**
   * Getter for help text under the Trial ID text field
   *
   * @return
   */
  public String getHelpText() {
    return helptext.getText();
  }

  /**
   * Getter for Help Link
   *
   * @return
   */
  public Link getHelpLink() {
    return helpLink;
  }

}
