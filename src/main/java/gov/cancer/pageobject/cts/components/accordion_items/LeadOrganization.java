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

  // Body element of a section
  private WebElement body;
  //Map contains all fields of an section
  private Map<String, String> fields = new HashMap<>();


  /**
   * Constructor
   *
   * @param title
   * @param body
   */

  public LeadOrganization(WebElement title, WebElement body) {
    super(title, body);
    this.body = body;
  }

  /**
   * Overridden method returns specified field text value
   *
   * @param field
   * @return
   */
  @Override
  public String getField(String field) {
    WebElement leadOrganization = ElementHelper.findElement(body, ":scope p:nth-child(1)");
    WebElement principalInvestigators = ElementHelper.findElement(body, ":scope p:nth-child(2)");
    fields.put(LEAD_ORGANIZATION, leadOrganization.getText());
    fields.put(PRINCIPAL_INVESTIGATOR, principalInvestigators.getText());
    return fields.get(field);
  }

}
