package gov.cancer.pageobject.cts.components.accordion_items;

import gov.cancer.pageobject.cts.components.AccordionItem;
import gov.cancer.pageobject.helper.BlobOfText;
import org.openqa.selenium.WebElement;

/**
 * This class represents Trial Objectives and Outline section of an accordion
 */
public class TrialObjectivesAndOutline extends AccordionItem {
  //Title element of a section
  private WebElement title;
  // Body element of a section
  private WebElement body;
// text of a section's body
 private BlobOfText objectives;

  /**\
   * Constructor
   * @param title
   * @param body
   */
  public TrialObjectivesAndOutline (WebElement title, WebElement body){
    super(title);
    this.title = title;
    this.body = body;
    objectives = new BlobOfText(body);
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
    return null;
  }
  /**
   * Overridden methos returns specified field text value
   * @param field
   * @return
   */
  @Override
  public String getField(String field) {
    if(field.equalsIgnoreCase("Trial Objectives")){
      return objectives.getText();
    }else
      return null;
  }
}
