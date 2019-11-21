package gov.cancer.pageobject.cts.advanced_search_page_components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.Component;
import gov.cancer.pageobject.components.TextField;
import gov.cancer.pageobject.cts.components.ErrorDisplay;
import gov.cancer.pageobject.helper.Link;
import org.openqa.selenium.WebElement;

/**
 * This class represents Age section on Advanced Search Form
 */
public class AgeSection extends Component {

  //Element represent entire section
  private WebElement section;
  //Age text field
  private TextField age;
  //Title of Age section
  private WebElement title;
  // help text under age input field
  private WebElement helptext;
  // help link
  private Link helpLink;

  private final static String AGE_FIELD_LOCATOR= ":scope input#age";
  private final static String TITLE_LOCATOR =":scope legend >span" ;
  private final static String HELP_TEXT_LOCATOR =":scope div >div>span";
  private final static String HELP_LINK_LOCATOR = ":scope legend a";

  /**
   * Constructor
   * @param element
   */
  public AgeSection (WebElement element){
    super(element);
    section = element;
    title = ElementHelper.findElement(section,TITLE_LOCATOR);
    age = new TextField(ElementHelper.findElement(section, AGE_FIELD_LOCATOR));
    helptext = ElementHelper.findElement(section, HELP_TEXT_LOCATOR);
    helpLink = new Link(ElementHelper.findElement(section, HELP_LINK_LOCATOR));
  }

  /**
   * Getter for age text field
   * @return
   */
  public TextField getAge() {
    return age;
  }

  /**
   * Getter for get Help Link
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
   * Method retrieves Error Message from Age section
   */

  public ErrorDisplay getErrorDisplay() {
    return new ErrorDisplay(section);
  }
}
