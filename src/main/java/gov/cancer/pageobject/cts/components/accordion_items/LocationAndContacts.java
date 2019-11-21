package gov.cancer.pageobject.cts.components.accordion_items;


import gov.cancer.pageobject.cts.components.AccordionItem;

import org.openqa.selenium.WebElement;

/**
 * This class represents Location and Contacts section of an accordion
 */
public class LocationAndContacts extends AccordionItem {
  //Title element of a section
  private WebElement title;
  // Body element of a section
  private WebElement body;

  public LocationAndContacts (WebElement title, WebElement body){
   super(title);
    this.title = title;
    this.body = body;
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
   * @return throws UnsupportedOperationException - this particular section has specific behavior
   */
@Override
  public String getField(String field) {
  throw new UnsupportedOperationException();
}
}
