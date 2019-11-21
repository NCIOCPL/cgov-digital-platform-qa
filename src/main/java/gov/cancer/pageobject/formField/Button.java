package gov.cancer.pageobject.formField;

import org.openqa.selenium.WebElement;

public class Button {

  //Element represents any button
  private WebElement button;

  /**
   * Constructor
   * @param element
   */
  public Button (WebElement element){
    this.button = element;
  }

  /**
   * Method is used for clicking on the button
   */
  public void click(){
    button.click();
  }

  /**
   * Method is checking a visibility of a button
   * @return
   */
  public boolean isDisplayed(){
    return button.isDisplayed();
  }

}
