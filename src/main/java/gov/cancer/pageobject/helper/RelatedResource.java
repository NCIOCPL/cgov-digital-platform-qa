package gov.cancer.pageobject.helper;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import gov.cancer.framework.Configuration;
import gov.cancer.framework.ElementHelper;
import gov.cancer.framework.LinkHelper;

/*
 * represents a single entry in a list of related resources
 */
public class RelatedResource {
  // this is the element representing the entire related resource.
  // its is the li tag
  WebElement element;

  // this is the anchor tag for related resource
  WebElement resourceLink;

  // Getting configuration file to access domain values
  Configuration config;

  static final String LINK_SELECTOR = ":scope a:nth-child(1)";
  static final String EXTLINK_SELECTOR = ":scope a:nth-child(2)";

  // Related Resources links
  public RelatedResource(WebElement element) {
    this.element = element;
    resourceLink = ElementHelper.findElement(element, LINK_SELECTOR);
    config = new Configuration();
  }

  /*
   * does exit disclaimer appears next to the title
   */
  public boolean hasExitDisclaimer() {
    // externalLink with icon-exit-notification
    WebElement externalLink = ElementHelper.findElement(element, EXTLINK_SELECTOR);
    return externalLink.isDisplayed() && externalLink.getAttribute("class").equals("icon-exit-notification");
  }

  /*
   * check if this element text has a tag 'a'
   */
  public boolean isLinkElement() {
    if (resourceLink != null) {
      return resourceLink.getTagName().equalsIgnoreCase("a");
    } else
      return false;
  }

  /*
   * if element is null return false; else trim the element text and if its
   * empty return false (ex: string between a tag)
   */
  public boolean isLinkTextBlank() {
    if (resourceLink != null)
      return !resourceLink.getText().trim().isEmpty();
    return false;
  }

  /*
   * Does it have a non-empty href? (ex: " ")
   */
  public boolean isLinkHrefBlank() {
    if (resourceLink != null)
      return !resourceLink.getAttribute("href").trim().isEmpty();
    else
      return false;
  }

  /*
   * Verifying Exit Disclaimer styling for Related Resource link that doesn't
   * end with .gov,
   */
  public boolean isExternal() {
    URL url = null;

    try {
      url = new URL(resourceLink.getAttribute("href"));

    } catch (MalformedURLException e) {
      throw new RuntimeException("Error loading URL", e);
    }

    if (LinkHelper.isGovermentUrl(url)) {

      return true;
    } else
      return false;

  }

}
