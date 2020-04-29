package gov.cancer.pageobject.cts.components.accordion_items;

import gov.cancer.pageobject.cts.components.AccordionItem;
import gov.cancer.pageobject.helper.BlobOfText;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Class represents Eligibility Criteria section of an accordion
 */
public class EligibilityCriteria extends AccordionItem {
  /**
   * Constructor
   *
   * @param title title element
   * @param body  body element
   */
  public EligibilityCriteria(WebElement title, WebElement body) {
    super(title, body);

  }


}
