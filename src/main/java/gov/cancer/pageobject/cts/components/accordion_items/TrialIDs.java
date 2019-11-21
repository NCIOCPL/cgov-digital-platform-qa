package gov.cancer.pageobject.cts.components.accordion_items;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.cts.components.AccordionItem;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents Trial Ids section of an accordion
 */
public class TrialIDs extends AccordionItem {
  //Title element of a section
  private WebElement title;
  // Body element of a section
  private WebElement body;
  //map contains all fields in the section
  private Map<String, String> fields = new HashMap<>();

  public TrialIDs(WebElement title, WebElement body) {
    super(title);
    this.title = title;
    this.body = body;
    WebElement primaryIDS = ElementHelper.findElement(body, "ul.trial-ids li:nth-child(1)");
    WebElement secID = ElementHelper.findElement(body, "ul.trial-ids li:nth-child(2)");
    WebElement clinTrialId = ElementHelper.findElement(body, "ul.trial-ids li:nth-child(3)");
    fields.put("Primary ID", primaryIDS.getText());
    fields.put("Secondary IDs", secID.getText());
    fields.put("ClinicalTrials.gov ID", clinTrialId.getText());

  }

  /**
   * Overriden method returns value of aria-expanded attribute of title element
   *
   * @return
   */
  @Override
  public boolean isExpanded() {
    return Boolean.parseBoolean(title.getAttribute("aria-expanded"));
  }

  /**
   * Overridden  method return title text
   *
   * @return
   */
  @Override
  public String getTitle() {
    return title.getText();
  }

  /**
   * Overridden methos returns specified field text value
   *
   * @param field
   * @return
   */
  @Override
  public String getField(String field) {
    return fields.get(field);
  }
}
