package gov.cancer.pageobject.crosscutting;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import gov.cancer.pageobject.PageObjectBase;

/**
 * Pseudo page object representing any page in the system. The BreadCrumb class
 * is used solely for verifying attributes of a page's bread crumb trail.
 */
public class BreadCrumbPage extends PageObjectBase {


  @FindBy(how = How.CSS, using = ".breadcrumbs")
  WebElement breadCrumb;

  /**
   * Constructor
   *
   * @param path server-relative path of the page to load.
   */
  public BreadCrumbPage(String path) {
    super(path);
  }

  /**
   * Reports whether the bread crumb is visible.
   *
   * @return True if the bread crumb is visible.
   */
  public boolean isBreadCrumbVisible() {
    return breadCrumb.isDisplayed();
  }

  /**
   * Retrieves the innerText from all breadcrumb elements, removing any
   * leading/trailing whitespace on each node of the trail.
   */
  public String getBreadCrumbText() {
    String Breadcrumb = breadCrumb.getText();
    return Breadcrumb;
  }

}
