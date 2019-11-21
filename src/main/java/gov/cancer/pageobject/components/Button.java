package gov.cancer.pageobject.components;

import gov.cancer.framework.MouseActionHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Button extends CGovField {

  //Element represents any button
  private WebElement button;

  /**
   * Constructor
   * @param element
   */
  public Button (WebElement element){
    super(element);
    this.button = element;
  }

  /**
   * Method is used for clicking on the button
   * 'Timeout loop' is used to prevent StaleElement exception to be thrown. It artificially wait for 400 milli second
   * until attempts to click on element again
   */
  public void click() {
button.click();
  }

}
