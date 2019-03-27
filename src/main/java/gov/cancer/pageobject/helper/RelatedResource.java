package gov.cancer.pageobject.helper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.cancer.framework.Configuration;

public class RelatedResource {

  WebElement element;
  Configuration config;

  public RelatedResource(WebElement element) {
    this.element = element;
    config = new Configuration();
  }

  /*
   * check if this element text has a tag 'a'
   */
  public Boolean isLinkElement() {
    if (element != null) {
      return element.getTagName().equalsIgnoreCase("a");
    } else
      return false;
  }

  /*
   * if element is null return false; else trim the element text and if its empty
   * return false (ex: string between a tag)
   */
  public boolean isLinkTextBlank() {
    if (element != null)
      return !element.getText().trim().isEmpty();
    return false;
  }

  /*
   * Does it have a non-empty href? (ex: " ")
   */
  public boolean isLinkHrefBlank() {
    if (element != null)
      return !element.getAttribute("href").trim().isEmpty();
    else
      return false;
  }

  /*
   * Does external links have Exit disclaimer styling if URL ends with .gov
   */
  public boolean isExternal() {
    URL url = null;
    try {
      url = new URL(element.getAttribute("href"));
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error loading URL", e);
    }
    String hostName = url.getHost();

    if (element != null && !hostName.endsWith(config.getInternalDomain())) {

      return true;
    } else
      return false;
  }

  /*
   * does exit disclaimer appears next to the title
   */
  public boolean hasExitDisclaimer() {

    return element.findElement(By.xpath("following-sibling::a")).getAttribute("title").equals("Exit Disclaimer");
  }

}
