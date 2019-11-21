package gov.cancer.pageobject.cts.components.accordion_items;

import gov.cancer.pageobject.cts.components.AccordionItem;
import gov.cancer.pageobject.helper.BlobOfText;
import org.openqa.selenium.WebElement;

/**
 * This class represents Description section of an accordion
 */
public class Description extends AccordionItem {

  //Title element of a section
  private WebElement title;
  // Body element of a section
  private WebElement body;

  private BlobOfText descriptionBody;

  public Description(WebElement title, WebElement body) {
    super(title);
    this.title = title;
    this.body = body;
    descriptionBody = new BlobOfText(body);
  }

  /**
   * Overriden method returns value of aria-expanded attribute of title element
   * @return
   */
  @Override
  public boolean isExpanded() {
    return Boolean.parseBoolean(title.getAttribute("aria-expanded"));
  }
  /**
   * Overridden  method return title text
   * @return
   */
  @Override
  public String getTitle() {
    return title.getText();
  }
  /**
   * Overridden methos returns specified field text value
   * @param field
   * @return
   */
  @Override
  public String getField(String field) {
    if (field.equalsIgnoreCase("Description"))
      return descriptionBody.getText();
    else return null;
  }
}
