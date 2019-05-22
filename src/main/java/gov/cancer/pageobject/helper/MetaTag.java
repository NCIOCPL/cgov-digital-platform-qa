package gov.cancer.pageobject.helper;

import org.openqa.selenium.WebElement;

/*
 * represents the metatags in a page
 */
public class MetaTag {
  /**
   * this is the element representing the entire metatag.
   */
  WebElement element;

  /**
   * Metatag property
   */

  public MetaTag(WebElement element) {
    this.element = element;
  }

  /**
   * Returns the content of the robot meta tag
   */
  public String getContent() {
    return this.element.getAttribute("content");
  }

}