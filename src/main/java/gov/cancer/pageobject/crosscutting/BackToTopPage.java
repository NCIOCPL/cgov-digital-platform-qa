package gov.cancer.pageobject.crosscutting;

import gov.cancer.framework.ElementHelper;
import gov.cancer.framework.ScrollUtil;
import gov.cancer.pageobject.PageObjectBase;
/**
 * Pseudo page object representing any page in the system. The BackToTop class
 * is used solely for verifying Top button attributes.
 */
public class BackToTopPage extends PageObjectBase {

  String backToTop = "a.back-to-top";

  /**
   * Constructor
   *
   * @param path server-relative path of the page to load.
   */
  public BackToTopPage (String path) {
    super(path);
  }

  /**
   * @return true is the Back-To-Top button is displayed on the page
   */

  public boolean BackToTopIsDisplayed (){
    return ElementHelper.findElement(getBrowser(), backToTop).isDisplayed();

  }

  /**
   *  is used to scroll to the set interval
   */

  public void ScrollToInterval (){
    ScrollUtil.scrollIntoView(getBrowser(),601);
  }

}
