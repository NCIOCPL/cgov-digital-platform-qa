package gov.cancer.pageobject.crosscutting;



import org.openqa.selenium.WebElement;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;
import gov.cancer.pageobject.helper.RobotMetaTag;

/**
 * Pseudo page object representing any page in the system. The class is used
 * solely for verifying the attributes of meta name =robots
 */

public class PageWithMetaTag extends PageObjectBase {
  String cssRobotTag = "meta[name='robots']";

  /**
   * Constructor
   *
   * @param path
   *          server-relative path of the page to load.
   */
  public PageWithMetaTag(String path) {
    super(path);
  }

  /**
   * Finds and returns robot meta tags
   */
  public RobotMetaTag getRobotMetaTag() {
    WebElement robotMetaTagElement = ElementHelper.findElement(getBrowser(), cssRobotTag);
    RobotMetaTag rmt = new RobotMetaTag(robotMetaTagElement);
    return rmt;
  }

}
