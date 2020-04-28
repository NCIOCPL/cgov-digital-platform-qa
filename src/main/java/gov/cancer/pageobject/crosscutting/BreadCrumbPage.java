package gov.cancer.pageobject.crosscutting;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;
import gov.cancer.pageobject.helper.BreadCrumb;

/**
 * Pseudo page object representing any page in the system. The BreadCrumb class
 * is used solely for verifying attributes of a page's bread crumb trail.
 */
public class BreadCrumbPage extends PageObjectBase {
  private List<BreadCrumb> crumbs = new ArrayList<BreadCrumb>();
  private WebElement breadCrumb;
  private final static String BREADCRUMB_SELECTOR = "div#cgvSlBreadcrumb";
  /**
   * Constructor
   *
   * @param path server-relative path of the page to load.
   */
  public BreadCrumbPage(String path) {
    super(path);
    this.breadCrumb = ElementHelper.findElement(getBrowser(), BREADCRUMB_SELECTOR);
    List<WebElement> breadcrumbpages = ElementHelper.findElements(this.breadCrumb, ":scope .breadcrumbs>li");
    for (WebElement link : breadcrumbpages) {
      crumbs.add(new BreadCrumb(link));
    }
  }
  /**
   * Reports whether the bread crumb is displayed.
   *
   * @return False if the bread crumb is not displayed.
   */
  public boolean isBreadCrumbVisible() {
    if (breadCrumb != null)
      return breadCrumb.isDisplayed();
    else
      return false;
  }
  /**
   * Retrieves all the links of the Breadcrumb of the page
   */
  public List<BreadCrumb> getBreadCrumbs() {
    return crumbs;
  }
}
