package gov.cancer.pageobject.components;

import gov.cancer.framework.Configuration;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class Button extends CGovField {

  //Element represents any button
  private WebElement button;
  //Configuration object
  private Configuration config;

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
   */
  public void click(){
   button.click();
  }

}
