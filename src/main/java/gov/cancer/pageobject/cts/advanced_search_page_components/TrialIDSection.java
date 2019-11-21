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
  // trial id text field
  private TextField IDText;
  //section title
  private WebElement title;
  // help link
  private Link helpLink;

  /******Locators*****/

  private final static String idTextLocator = ":scope input#trialId";
  private final static String HELP_LINK_LOCATOR = ":scope legend a";
  private final static String TITLE_LOCATOR = ":scope legend span";

  /**
   * Constructor
   * @param element
   *          - webelement of trial id section
   */
  public TrialIDSection (WebElement element){
    super(element);
    IDText = new TextField(ElementHelper.findElement(element, idTextLocator));
    helpLink = new Link(ElementHelper.findElement(element, HELP_LINK_LOCATOR));
    title = ElementHelper.findElement(element, TITLE_LOCATOR);
  }

  /**
   * Getter for trialId text field
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
   * Getter for Help Link
   *
   * @return
   */
  public Link getHelpLink() {
    return helpLink;
  }
}
