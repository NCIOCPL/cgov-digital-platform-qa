package gov.cancer.pageobject.cts.advanced_search_page_components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.Component;
import gov.cancer.pageobject.components.TextField;
import gov.cancer.pageobject.helper.Link;
import org.openqa.selenium.WebElement;

/**
 * This class represents Keywords/Phrases section on Advance Search Form
 */
public class KeywordsPhrasesSection extends Component {

  //Element represent entire section
  private WebElement section;
  //Keywords/Phrases text field
  private TextField keywordPhrase;
  //Title of Age section
  private WebElement title;
  // help text under age input field
  private WebElement helptext;
  // help link
  private Link helpLink;
  // text field element
  private WebElement textField;

  private final static String KEYWORD_FIELD_LOCATOR= ":scope input#keywordPhrases";
  private final static String TITLE_LOCATOR =":scope legend >span" ;
  private final static String HELP_TEXT_LOCATOR =":scope div >div>span";
  private final static String HELP_LINK_LOCATOR = ":scope legend a";

  /**
   * Constructor
   *
   * @param element - component element
   */
  public KeywordsPhrasesSection(WebElement element) {
    super(element);
    section = element;
    title = ElementHelper.findElement(section,TITLE_LOCATOR);
    keywordPhrase = new TextField(ElementHelper.findElement(section, KEYWORD_FIELD_LOCATOR));
    helptext = ElementHelper.findElement(section, HELP_TEXT_LOCATOR);
    helpLink = new Link(ElementHelper.findElement(section, HELP_LINK_LOCATOR));
  }

  /**
   * Getter for keyword/phrases text field
   * @return
   */
  public TextField getKeywordPhrase() {
    return keywordPhrase;
  }

  /**
   * Getter for help link
   * @return
   */
  public Link getHelpLink() {
    return helpLink;
  }

  /**
   * Retrieves title text of age section
   * @return
   */
  public String getTitle (){
    return title.getText();
  }

  /**
   * Getter for help text under the age textfield
   * @return
   */
  public String getHelpText(){
    return helptext.getText();
  }

  /**
   * Method returns place holder helper text
   * @return
   */
  public String getPlaceHolderText () {
    textField = ElementHelper.findElement(section, KEYWORD_FIELD_LOCATOR);
    return textField.getAttribute("placeholder");
  }

}
