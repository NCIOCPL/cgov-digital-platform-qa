package gov.cancer.pageobject.cts.components.accordion_items;

import gov.cancer.pageobject.cts.components.AccordionItem;
import gov.cancer.pageobject.helper.BlobOfText;
import org.openqa.selenium.WebElement;

/**
 * This class represents Description section of an accordion
 */
public class Description extends AccordionItem {

  /**
   * Constructor
   *
   * @param title title element
   * @param body  body element
   */
  public Description(WebElement title, WebElement body) {
    super(title, body);
  }

}
