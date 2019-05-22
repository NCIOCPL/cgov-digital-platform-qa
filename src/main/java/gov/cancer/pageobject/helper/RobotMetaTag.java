package gov.cancer.pageobject.helper;

import org.openqa.selenium.WebElement;

public class RobotMetaTag extends MetaTag {

  /**
   * constructor
   */

  public RobotMetaTag(WebElement element) {
    super(element);
  }

  /**
   * Reports whether the content is index or noindex.
   */
  public boolean getAllowSearch() {
    String str = getContent();
    if (str != null && (str.trim().toLowerCase().equals("index")))
      return true;
    else
      return false;
  }
}
