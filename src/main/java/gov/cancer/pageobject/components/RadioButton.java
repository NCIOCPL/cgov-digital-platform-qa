package gov.cancer.pageobject.components;

import org.openqa.selenium.WebElement;

/**
 * Class represents any radio button
 */
public class RadioButton extends CGovField {

  //WebElement of radioButton
 private WebElement radioButton;
  /**
   *Constructor
   * @param element
   */
  public RadioButton (WebElement element){
    super(element);
    radioButton= element;
  }

  /**
   * Method is used to check radioButton
   */
  public void select(){
    radioButton.click();
  }

  /**
   * Method checks if the radio button is selected
   * @return
   */
  public boolean isSelected(){
    return radioButton.isSelected();
  }

}
