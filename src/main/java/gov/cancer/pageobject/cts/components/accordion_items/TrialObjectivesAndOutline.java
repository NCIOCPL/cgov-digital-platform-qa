package gov.cancer.pageobject.cts.components.accordion_items;

import gov.cancer.pageobject.cts.components.AccordionItem;
import gov.cancer.pageobject.helper.BlobOfText;
import org.openqa.selenium.WebElement;

/**
 * This class represents Trial Objectives and Outline section of an accordion
 */
public class TrialObjectivesAndOutline extends AccordionItem {
  /**
   * \
   * Constructor
   *
   * @param title
   * @param body
   */
  public TrialObjectivesAndOutline(WebElement title, WebElement body) {
    super(title, body);
  }
}
