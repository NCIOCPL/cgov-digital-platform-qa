package gov.cancer.framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClickUtil {

  private static JavascriptExecutor javaScript;

  /**
   * Click method for cases where Selenium can't find or otherwise doesn't want to
   * click on a given WebElement.
   *
   * @param driver
   * @param element
   */
  public static void forceClick(WebDriver driver, WebElement element) {
    javaScript = (JavascriptExecutor) driver;
    javaScript.executeScript("arguments[0].click();", element);
  }

  /**
   * Click method for cases where Selenium can't find or otherwise doesn't want to
   * click on a given CSS selector.
   *
   * @param driver
   * @param selector
   */
  public static void forceClick(WebDriver driver, String selector) {
    javaScript = (JavascriptExecutor) driver;
    javaScript.executeScript("document.querySelector(arguments[0]).click();", selector);
  }

  /**
   * Method to set text values in cases where Selenium can't find a given CSS
   * selector.
   *
   * @param driver
   * @param selector
   * @param value
   */
  public static void setElementValue(WebDriver driver, String selector, String value) {
    javaScript = (JavascriptExecutor) driver;
    javaScript.executeScript("document.querySelector(arguments[0]).value = (arguments[1]);", selector, value);
  }

  /**
   * Mimic a user repeatedly pressing up and down keys on a given element.
   * Currently used for engagement tracking and to stall for analytics event after
   * the megamenu is expanded.
   *
   * @param webElement
   * @param reps
   */
  public static void stall(WebElement webElement, int reps) {
    // *Lifts piano cover*
    // They hate this.
    for (int i = 0; i <= reps; i++) {
      webElement.sendKeys(Keys.ARROW_DOWN);
      webElement.sendKeys(Keys.ARROW_UP);
    }
  }

  /**
   * Mimic a user repeatedly pressing up and down keys on a given element.
   *
   * @param webElement
   */
  public static void stall(WebElement webElement) {
    stall(webElement, 5);
  }

  /**
   * Mimic a user repeatedly scrolling up and down.
   *
   * @param driver
   */
  public static void stall(WebDriver driver) {
    javaScript = (JavascriptExecutor) driver;
    for (int i = 0; i <= 5; i++) {
      javaScript.executeScript("window.scrollTo(0,document.body.scrollHeight),"
          + "window.scrollTo(0,document.body.scrollHeight/2),window.scrollTo(0,0);");
    }
  }

}
