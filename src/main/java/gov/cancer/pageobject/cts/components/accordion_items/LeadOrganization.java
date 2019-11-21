package gov.cancer.pageobject.cts.components.accordion_items;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.cts.components.AccordionItem;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * This class represents Lead Organization section of am accordion
 */
public class LeadOrganization extends AccordionItem {
  //Title element of a section
  private WebElement title;
  // Body element of a section
  private WebElement body;
  //Map contains all fields of an section
  private Map<String, String> fields =new HashMap<>();

  public LeadOrganization (WebElement title, WebElement body){
    super(title);
    this.title = title;
    this.body = body;
    WebElement leadOrganization = ElementHelper.findElement(body, ":scope p:nth-child(1)");
    WebElement principalInvestigators = ElementHelper.findElement(body, ":scope p:nth-child(2)");
    fields.put("Lead Organization", leadOrganization.getText());
    fields.put("Principal Investigator", principalInvestigators.getText());
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
