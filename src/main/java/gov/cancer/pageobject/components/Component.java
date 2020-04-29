package gov.cancer.pageobject.components;

import org.openqa.selenium.WebElement;

/**
 * Class represents any page component of CGOV pages
 */
public class Component {

  private WebElement element;

  /**
   * Constructor
   *
   * @param element - component element
   */
  public Component(WebElement element) {
    this.element = element;
  }

  /**
   * return whether or not component element is displayed
   *
   * @return
   */
  public boolean isVisible() {
    if (element != null)
      return this.element.isDisplayed();
    else
      return false;
  }

}
