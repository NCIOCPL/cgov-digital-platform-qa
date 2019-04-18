package gov.cancer.pageobject.crosscutting;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;

/**
 * Pseudo page object representing any page in the system. The class is used
 * solely for verifying attributes of a page's Dates Display Text
 */
public class DatesPage extends PageObjectBase {

  // Date Display container
  final public String dateDisplay = "div.document-dates";
  // Date Text, date Label and date stamp selectors
  final public String dateText = "[datetime]";
  final public String dateLabel = "div.document-dates li strong";
  final public String dateStamp = "div.document-dates li time";

  /**
   * Constructor
   *
   * @param path server-relative path of the page to load.
   */
  public DatesPage(String path) {
    super(path);

  }

  /*
   * Returns true if DateTime Field is displayed on the page, false otherwise
   */
  public boolean dateDisplayIsPresent() {
    if (ElementHelper.findElement(getBrowser(), dateDisplay) != null)
      return ElementHelper.findElement(getBrowser(), dateDisplay).isDisplayed();
    else
      return false;
  }

  /* Returns Date Label as text sting */
  public String getDateLabel() {
    return ElementHelper.findElement(getBrowser(), dateLabel).getText();
  }

  /* Returns Date Text attribute as sting */
  public String getDateText() {
    return ElementHelper.findElement(getBrowser(), dateText).getAttribute("datetime");
  }

  /* Returns Date Stamp as text sting */
  public String getDateStamp() {
    return ElementHelper.findElement(getBrowser(), dateStamp).getText();
  }

}
