package gov.cancer.pageobject.components;

import gov.cancer.framework.ParsedURL;
import org.openqa.selenium.WebDriver;

/**
 * This is pseudo Page class, that is used solely for verifying results of navigation event. Used for @see expectUrlChange
 */
public class NavigationResult {

  private ParsedURL parsedURL;

  /**
   * constructor retrieves current url and parses it
   * @param driver current instance of the driver
   */
  public NavigationResult(WebDriver driver){
    String url = driver.getCurrentUrl();
    parsedURL = new ParsedURL(url);
  }


  public ParsedURL getParsedUrl() {
    return parsedURL;
  }
}
