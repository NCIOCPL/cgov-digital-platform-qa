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

  // Body element of a section
  private WebElement body;
  //map contains all fields in the section
  private Map<String, String> fields = new HashMap<>();

  /**
   * Constructor
   *
   * @param title
   * @param body
   */
  public TrialIDs(WebElement title, WebElement body) {
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
    WebElement primaryIDS = ElementHelper.findElement(body, "ul.trial-ids li:nth-child(1)");
    WebElement secID = ElementHelper.findElement(body, "ul.trial-ids li:nth-child(2)");
    WebElement clinTrialId = ElementHelper.findElement(body, "ul.trial-ids li:nth-child(3) a");
    fields.put(PRIMARY_TRIAL_ID, primaryIDS.getText());
    fields.put(SECONDARY_TRIAL_ID, secID.getText());
    fields.put(CANCER_GOV_ID, clinTrialId.getText());
    return fields.get(field);
  }


}
