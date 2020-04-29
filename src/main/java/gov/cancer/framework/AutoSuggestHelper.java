package gov.cancer.framework;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * Helper class for selecting an entry from an autosuggest list.
 *
 * Based on sample code at:
 *
 * http://www.seleniumeasy.com/selenium-tutorials/working-with-ajax-or-jquery-auto-complete-text-box-using-webdriver
 *
 */
public class AutoSuggestHelper {
  // Maximum number of seconds to wait for auto suggest list to appear.
  private static final int AUTO_SUGGEST_TIMEOUT = 10;
  // WebDriver instance representing the browser.
  private WebDriver browser;
  // Wait object
  private WebDriverWait wait;
  private WebElement parentElement;

  /**
   * Constructor.
   *
   * @param browser WebDriver instance.
   */
  public AutoSuggestHelper(WebDriver browser, WebElement element) {
    this.browser = browser;
    wait = new WebDriverWait(this.browser, AUTO_SUGGEST_TIMEOUT);
    this.parentElement = element;
      }
  /**
   * Selects an item from an autosuggest list, using the *exact* text of the
   * desired entry. (e.g. search for "Adenosquamous Lung Cancer", not "Lung
   * Cancer".)
   *
   * @param text          The text to be selected.
   */
  public void SelectExact(String text) {
    parentElement.sendKeys(text);
    //wait until item with exact text match from autosuggest dropdown table is visible
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'"+text+"')]")));
    parentElement.sendKeys(Keys.ARROW_DOWN);
    parentElement.sendKeys(Keys.ENTER);
  }

}
