package gov.cancer.framework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

/**
 * ElementHelper class contains methods which work on web elements on the page.
 *
 */
public class ElementHelper {

  /**
   * This method returns the exact element on the page.
   *
   * @param parent
   *          - page WebDriver or WebElement.
   * @param selector
   *          - CSS selector string.
   */
  public static WebElement findElement(SearchContext parent, String selector) {

    List<WebElement> findList = parent.findElements(By.cssSelector(selector));

    if (findList.size() == 1) {
      return findList.get(0);
    } else if (findList.size() > 1) {
      throw new RuntimeException("There should be only one Element");

    } else
      return null;

  }

  /**
   * This method returns the list of elements on the page defined by a certain
   * selector.
   *
   * @param parent
   *          - page WebDriver or WebElement.
   * @param selector
   *          - CSS selector string.
   */
  public static List<WebElement> findElements(SearchContext parent, String selector) {

    List<WebElement> findList = parent.findElements(By.cssSelector(selector));
    if (findList.size() > 0) {
      return findList;

    } else
      return null;
  }

  /**
   * This method returns true if the element is visible.
   *
   * @param parent
   *          - page WebDriver or WebElement.
   * @param selector
   *          - CSS selector string.
   */
  public static boolean isVisible(SearchContext parent, String selector) {

    if (findElement(parent, selector) != null)
      return findElement(parent, selector).isDisplayed();
    else
      return false;
  }

}
