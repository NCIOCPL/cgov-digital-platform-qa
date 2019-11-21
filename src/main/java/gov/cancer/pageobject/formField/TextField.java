package gov.cancer.pageobject.formField;

import gov.cancer.pageobject.helper.CGovField;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * This helper class is used to manipulate text fields in any form - send keys, focus on another field etc.
 */
public class TextField extends CGovField {

  //represent any text field on the form
  private  WebElement formField;

  /**
   * main constructor
   * @param element
   */
  public TextField (WebElement element){

    super(element);
    this.formField = element;
  }

  /**
   * Method is used to send any text to form
   * @param text
   */
  public void enterText (String text){
    this.formField.sendKeys(text);
  }

  /**
   * Method is used to hit ENTER key to trigger navigation event
   */
  public void hitEnter() {
      this.formField.sendKeys(Keys.ENTER);
  }

  /**
   * Method is used to check if the field is displayed on the form
   * @return
   */
  public boolean isDisplayed(){
    return this.formField.isDisplayed();
  }

  /**
   * Method is used to set focus away from form
   */
  public void setFocusAway(){
    this.formField.sendKeys(Keys.TAB);
  }
}
