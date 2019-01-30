package gov.cancer.framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScrollUtil {

  static final int MAX_SCROLL_DELAY = 10;

  public static void scrollIntoview(WebDriver driver, WebElement element) {

    WebDriverWait wait = new WebDriverWait(driver, MAX_SCROLL_DELAY);

    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);" + "window.scrollBy(0,-200);", element);
    wait.until(ExpectedConditions.visibilityOf(element));
  }


  public static void scrollIntoView(WebDriver driver, int l) {
    ((JavascriptExecutor) driver).executeScript("scroll(0, " + l + ");");
  }

}
