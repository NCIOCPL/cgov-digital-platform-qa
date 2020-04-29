package gov.cancer.pageobject.cts.components.accordion_items;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.cts.components.AccordionItem;
import org.openqa.selenium.WebElement;

import java.util.Map;
import java.util.TreeMap;

/**
 * This class represents Trial Phase and Type section of an accordion
 */
public class TrialPhaseAndType extends AccordionItem {

  // Body element of a section
  private WebElement body;
  //Map contains all fields of an section
  private Map<String, String> fields = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);


  /**
   * Constructor
   *
   * @param title
   * @param body
   */
  public TrialPhaseAndType(WebElement title, WebElement body) {
    super(title, body);
    this.body = body;
  }

  /**
   * /**
   * Overridden method returns specified field text value
   *
   * @param field
   * @return
   */
  @Override
  public String getField(String field) {
    WebElement trialType = ElementHelper.findElement(this.body, ":scope p.trial-type");
    WebElement trialPhase = ElementHelper.findElement(this.body, ":scope p.trial-phase");
    fields.put(TRIAL_PHASE, trialPhase.getText());
    fields.put(TRIAL_TYPE, trialType.getText());
    return fields.get(field);
  }


}
