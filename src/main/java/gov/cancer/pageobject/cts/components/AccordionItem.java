package gov.cancer.pageobject.cts.components;

import gov.cancer.pageobject.components.Component;
import gov.cancer.pageobject.helper.BlobOfText;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents Accordion Section
 */
public class AccordionItem extends Component {

  private WebElement title;
  private WebElement body;
  //KEYS array is used to store all accrodion section that only has blob of text in the body (for easier retrieval logic)
  private final List<String> KEYS = new ArrayList<String>();
  // Accordion section's field constants - used to retrieve specific field
  public final String DESCRIPTION = "Description";
  public final String ELIGIBILITY = "Eligibility";
  public final String TRIAL_OBJECTIVES = "Trial Objectives";
  public final String PRIMARY_TRIAL_ID = "Primary ID";
  public final String SECONDARY_TRIAL_ID = "Secondary IDs";
  public final String CANCER_GOV_ID = "ClinicalTrials.gov ID";
  public final String TRIAL_PHASE = "Trial Phase";
  public final String TRIAL_TYPE = "Trial Type";
  public final String LEAD_ORGANIZATION = "Lead Organization";
  public final String PRINCIPAL_INVESTIGATOR = "Principal Investigator";


  /**
   * Constructor
   *
   * @param title
   */
  public AccordionItem(WebElement title, WebElement body) {
    super(title);
    this.title = title;
    this.body = body;
    KEYS.add(DESCRIPTION);
    KEYS.add(ELIGIBILITY);
    KEYS.add(TRIAL_OBJECTIVES);
  }

  /**
   * Checks if accordion is expanded
   *
   * @return
   */
  public boolean isExpanded() {
    return Boolean.parseBoolean(title.getAttribute("aria-expanded"));
  }

  /**
   * getting a title of a section
   *
   * @return
   */
  public String getTitle() {
    return title.getText();
  }

  /**
   * getter for specific field of a section
   *
   * @param field
   * @return
   */
  public String getField(String field) {
    if (KEYS.contains(field))
      return new BlobOfText(body).getText();
    else
      return null;
  }


  /**
   * Method to click on the title of an accordion to expand it's section
   */
  public void expand() {
    title.click();
  }

}
