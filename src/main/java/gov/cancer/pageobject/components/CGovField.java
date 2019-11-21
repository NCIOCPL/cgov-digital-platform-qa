package gov.cancer.pageobject.components;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * Parent class for page components, such as checkbox, text field, button.
 * Contains common methods
 */
public class CGovField extends Component  {

 private WebElement theField;

  /**
   * main constructor
   *
   * @param element
   */
  public CGovField(WebElement element) {
   super(element);
    theField = element;
  }

  /**
   * Method sets focus away by hitting tab key
   */
  public void setFocusAway(){
    theField.sendKeys(Keys.TAB);
  }

  /**
   * Set the focus on the field
   * WebDriver API does not have a dedicated method to focus on the element, instead we simply send an empty String to the field
   */
  public void setFocus(){
    theField.sendKeys("");
  }

}
