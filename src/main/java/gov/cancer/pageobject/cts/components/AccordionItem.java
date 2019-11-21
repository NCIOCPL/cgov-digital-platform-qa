package gov.cancer.pageobject.cts.components;

import gov.cancer.pageobject.components.Component;
import org.openqa.selenium.WebElement;

/**
 * This class represents a generic Accordion Section
 * It's methods are implemented in section subclasses
 */
public abstract class AccordionItem  extends Component {

  private WebElement title;

  /**
   * Constructor
   * @param title
   */
   public AccordionItem(WebElement title){
    super(title);
    this.title = title;
  }
  /**
   * Checks if accordion is expanded
   * @return
   */
  public abstract boolean isExpanded ();

  /**
   * getting a title of a section
   * @return
   */
  public abstract String getTitle();

  /**
   * getter for specific field of a section
   * @param field
   * @return
   */
  public abstract String getField(String field);


}
