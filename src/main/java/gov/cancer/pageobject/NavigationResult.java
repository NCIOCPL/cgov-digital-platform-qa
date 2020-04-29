package gov.cancer.pageobject;

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

  /**
   * Method returns current page url
   * @return
   */
  public ParsedURL getPageURL() {
    return parsedURL;
  }

  /**
   * retrieves specific query parameter from url
   * @return value of query param
   */
  public String getParameterValue (String param){
    return parsedURL.getQueryParam(param);

  }
}
