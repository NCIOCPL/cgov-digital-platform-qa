package gov.cancer.pageobject.helper;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * This helper class is used to manipulate text fields in any form - send keys, focus on another field etc.
 */
public class TextField {

  //represent any text field on the form
  private  WebElement formField;

  /**
   * main constructor
   * @param element
   */
  public TextField (WebElement element){
    this.formField = element;
  }

  /**
   * Method is used to send any text to form
   * @param text
   */
  public void enterText (String text){
    formField.sendKeys(text);
  }

  /**
   * Method is used to hit ENTER key
   */
  public void hitEnter (){
    formField.sendKeys(Keys.ENTER);
  }

  /**
   * Method is used to set focus away from form
   */
  public void setFocus(){
    formField.sendKeys(Keys.TAB);
  }


}
