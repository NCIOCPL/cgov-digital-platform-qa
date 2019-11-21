package gov.cancer.pageobject.components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.helper.BlobOfText;
import org.openqa.selenium.WebElement;

/**
 * Class represents accordion element
 */
public class Accordion extends Component{

  //WebElement of one accordion item
  private WebElement accordion;
  private BlobOfText description;

  private final static String title = ":scope h2";

  /**
   * Constructor
   * @param element webelement of accordion
   */
  public Accordion (WebElement element){
    super(element);
    accordion = element;
  }

  /**
   * Checks if accordion is expanded
   * @return
   */
  public boolean isExpanded (){
  return accordion.getAttribute("aria-expanded").equals("true");
  }

  /**
   * Retrieves the text of accordion header
   * @return
   */
  public String getTitle(){
    return ElementHelper.getText(accordion,title);
  }

  public BlobOfText getDescription (){
    return description;
  }

}
