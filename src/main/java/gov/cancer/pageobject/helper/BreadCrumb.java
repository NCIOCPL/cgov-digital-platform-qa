package gov.cancer.pageobject.helper;

import org.openqa.selenium.WebElement;

import gov.cancer.framework.ElementHelper;

/**
 * This class represents each bread crumb link object
 */
public class BreadCrumb {
  private WebElement breadcrumbLink;
  /**
   * Constructor
   * @param element webElement that represents each breadcrumb
   */
  public BreadCrumb(WebElement element) {
    breadcrumbLink = ElementHelper.findElement(element, "a:nth-child(n)");
  }
  /**
   * retrieve the complete Breadcrumb path
   */
  public Link getBreadCrumbLink() {
    Link link= new Link(breadcrumbLink);
    return link;
  }
  /**
   * retrieve the complete Breadcrumb link titles
   */
  public String getBreadCrumbLinkTitle() {
    return breadcrumbLink.getText();
  }
}
