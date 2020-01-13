package gov.cancer.pageobject.cts.components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.Component;
import org.openqa.selenium.WebElement;

/**
 * This class represents the error message on the fields.
 */
public class ErrorDisplay extends Component {
  //WebElement of error alert
  private WebElement alert;
  //Error element locator
  private final static String ALERT_LOCATOR = "span[role='alert']";

  /**
   * Main constructor -looks for an element within a certain scope
   *
   * @param scope - parent element
   */
  public ErrorDisplay(WebElement scope) {
    super(ElementHelper.findElement(scope, ALERT_LOCATOR));
    this.alert = ElementHelper.findElement(scope, ALERT_LOCATOR);
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

