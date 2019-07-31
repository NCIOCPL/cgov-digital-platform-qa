package gov.cancer.pageobject.helper;

import org.openqa.selenium.WebElement;

/*
 * represents the metatags in a page
 */
public class MetaTag {
  /**
   * this is the element representing the entire metatag.
   */
  private WebElement element;

  /**
   * Metatag property
   */

  public MetaTag(WebElement element) {
    this.element = element;
  }

  /**
   * Returns the 'content' attribute of the meta tag
   */
  public String getContent() {
    return this.element.getAttribute("content");
  }

  /**
   * Returns the 'getHref' attribute of the meta tag
   */
  public String getHref() {
    return this.element.getAttribute("hreflang");
  }

}
