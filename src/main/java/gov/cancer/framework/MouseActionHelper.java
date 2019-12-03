package gov.cancer.framework;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * This helper class contains methods to perform specific mouse actions (like click and hover)
 */

public class MouseActionHelper {

  /**
   * Element representing one of the main menu categories
   */
 //Action class
  private Actions action;
  //Config object
  private Configuration config;
  /**
   * Main Constructor
   *
   * @param browser instance of a WebDriver
   */
  public MouseActionHelper(WebDriver browser) {
    this.config = new Configuration();
    action = new Actions (browser);
  }


  /**
   *
   * Helper method used to hover over the element for drop down field to appear.Pause time is configured through properties
   * @param we WebElement to move to (hover over one).
   */
  public void hoverOver (WebElement we){
    action.moveToElement(we).pause(config.getMouseTimeout()).build().perform();
  }
  /**
   * Helper method used to click on the element( to retrieve collapsed nodes for instance). Pause time is configured through properties
   * This method is not intended for page navigation.
   *
   * P.S. Navigation should be done through a method that returns a new page object
   */
  public void clickOn (WebElement we){
      action.moveToElement(we).click().pause(config.getMouseTimeout()).build().perform();
  }


}
