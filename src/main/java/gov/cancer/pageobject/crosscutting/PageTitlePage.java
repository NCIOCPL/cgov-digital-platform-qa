package gov.cancer.pageobject.crosscutting;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import gov.cancer.pageobject.PageObjectBase;

/**
 * Pseudo page object representing any page in the system. The BreadCrumb class
 * is used solely for verifying attributes of a page's bread crumb trail.
 */
public class PageTitlePage extends PageObjectBase {

  /**
   * Constructor
   *
   * @param path server-relative path of the page to load.
   */
  public PageTitlePage(String path) {
    super(path);
  }

}
