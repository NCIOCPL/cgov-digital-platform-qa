package gov.cancer.pageobject.formField;

import gov.cancer.framework.ElementHelper;
import org.openqa.selenium.WebElement;

/**
 * This class represents the error message on the fields.
 */
public class Alert {
  //WebElement of error alert
  private WebElement alert;
  //Error element locator
  private final static String ALERT_LOCATOR = "span[role='alert']";

  /**
   * Main constructor -looks for an element within a certain scope
   *
   * @param scope - parent element
   */
  public Alert(WebElement scope) {
    this.alert = ElementHelper.findElement(scope, ALERT_LOCATOR);
  }

  /**
   * Method checks if alert is displayed
   *
   * @return
   */
  public boolean isDisplayed() {
    return alert.isDisplayed();
  }

  /**
   * Retrieves a text message of an error alert
   *
   * @return
   */
  public String getMessage() {
    return alert.getText();
  }


}

