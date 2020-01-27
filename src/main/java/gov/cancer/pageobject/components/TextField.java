package gov.cancer.pageobject.components;

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
   * Retrieves the placeholder text of the field
   * @return
   */
  public String getPlaceholderText(){
    return formField.getAttribute("placeholder");
  }

}
