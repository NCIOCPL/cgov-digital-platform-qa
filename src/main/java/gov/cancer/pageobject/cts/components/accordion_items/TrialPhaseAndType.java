package gov.cancer.pageobject.cts.components.accordion_items;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.cts.components.AccordionItem;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents Trial Phase and Type section of an accordion
 */
public class TrialPhaseAndType extends AccordionItem {
//Title element of a section
 private WebElement title;
 // Body element of a section
 private WebElement body;
 //Map contains all fields of an section
 private Map <String, String> fields = new HashMap<>();

  /**
   * Constructor
   * @param title
   * @param body
   */

  public TrialPhaseAndType (WebElement title, WebElement body){
    super(title);
    this.title = title;
    this.body = body;
    WebElement trialType = ElementHelper.findElement(body, ":scope p:nth-child(2)");
    WebElement trialPhase = ElementHelper.findElement(body, ":scope p:nth-child(1)");
    fields.put("Trial Phase", trialPhase.getText());
    fields.put("Trial Type", trialType.getText());
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
 return fields.get(field);
  }
}
