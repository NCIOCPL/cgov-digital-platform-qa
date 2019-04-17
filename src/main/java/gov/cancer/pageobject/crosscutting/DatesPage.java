package gov.cancer.pageobject.crosscutting;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;

/**
 * Pseudo page object representing any page in the system. The class is used
 * solely for verifying attributes of a page's Dates Display Text
 */
public class DatesPage extends PageObjectBase {
  
  // Datetime container
  final public String dateTimeDisplay = "[datetime]";

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
  public boolean isDateFieldVisible() {
    if (ElementHelper.findElement(getBrowser(), dateTimeDisplay) != null)
      return ElementHelper.findElement(getBrowser(), dateTimeDisplay).isDisplayed();
    else
      return false;
  }

  /* Returns Datetime as text sting */
  public String getDateTimeTextString() {
    return ElementHelper.findElement(getBrowser(), dateTimeDisplay).getText();
  }

}
