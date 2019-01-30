package gov.cancer.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

  /**
   * Constructor.
   *
   * @param browser WebDriver instance.
   */
  public AutoSuggestHelper(WebDriver browser) {
    this.browser = browser;
    wait = new WebDriverWait(this.browser, AUTO_SUGGEST_TIMEOUT);
  }

  /**
   * Selects an item from an autosuggest list, using the *exact* text of the
   * desired entry. (e.g. search for "Adenosquamous Lung Cancer", not "Lung
   * Cancer".)
   *
   * @param text          The text to be selected.
   * @param parentElement The autosuggest list's parent element. (This is the
   *                      element where text is to be entered.)
   * @param listSelector  CSS selector of the list's placeholder entry. This is
   *                      assumed to be present before any text is entered into
   *                      the parent element.
   * @param itemSelector  CSS selector of an item in the autosuggest list. This
   *                      element may not be present until the list has received
   *                      entries.
   *
   */
  public void SelectExact(String text, WebElement parentElement, String listSelector, String itemSelector) {

    By autoSuggestionList = By.cssSelector(listSelector);
    By listEntry = By.cssSelector(itemSelector);
    WebElement dropdown = this.browser.findElement(autoSuggestionList);

    parentElement.sendKeys(text);

    wait.until(ExpectedConditions.presenceOfElementLocated(listEntry));
    WebElement entry = dropdown.findElement(listEntry);

    parentElement.sendKeys(Keys.ARROW_DOWN);
    parentElement.sendKeys(Keys.ENTER);
  }
}
