package gov.cancer.pageobject.crosscutting;

import org.openqa.selenium.WebElement;
import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;

/**
 * Pseudo page object representing any page in the system. The PageTitle class
 * is used solely for verifying page title.
 */
public class PageTitle extends PageObjectBase {

  WebElement pageTitle;

  /********* PAGE TITLE SELECTORS ***********************/

  final public String page_title = "div#main h1";

  /********* Page Title Methods ***********************/

  /**
   * Constructor
   *
   * @param path
   *          server-relative path of the page to load.
   */
  public PageTitle(String path) {
    super(path);
    pageTitle = ElementHelper.findElement(getBrowser(), page_title);
  }

  /* Returns true if title is displayed on the page */
  public boolean isPageTitleVisible() {
    return pageTitle.isDisplayed();
  }

  /* Returns the title of the Page */
  public String getPageTitle() {
    return pageTitle.getText();
  }

}
