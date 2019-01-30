package gov.cancer.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Helper methods to waiti for DOM transition.
 */
public class WaitUtil {

  public static void waitForElement(WebDriver driver, WebElement elementToBeLoaded) {
    WebDriverWait wait = new WebDriverWait(driver, 15);
    wait.until(ExpectedConditions.visibilityOf(elementToBeLoaded));
  }

  public static boolean waitForTitle(WebDriver driver, String title) {
    WebDriverWait wait = new WebDriverWait(driver, 15);
    return wait.until(ExpectedConditions.titleContains(title));
  }

  public static boolean waitElementToContainText(WebDriver driver, WebElement element, String text) {
    WebDriverWait wait = new WebDriverWait(driver, 15);
    return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
  }

  public static boolean waitURLToBe(WebDriver driver, String url) {
    WebDriverWait wait = new WebDriverWait(driver, 15);
    return wait.until(ExpectedConditions.urlToBe(url));
  }

}
