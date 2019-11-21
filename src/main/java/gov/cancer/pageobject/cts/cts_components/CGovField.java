package gov.cancer.pageobject.cts.cts_components;

import gov.cancer.framework.ElementHelper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CGovField  {

 private WebElement theField;
 private final static String label = "//label[contains(@for,'search')]";



  /**
   * main constructor
   *
   * @param element
   */
  public CGovField(WebElement element) {
   theField = element;
  }

  public void setFocus(){
    theField.sendKeys(Keys.TAB);
  }

  public boolean isVisible(){
    return theField.isDisplayed();
  }

  public String getLabel(){
    return ElementHelper.findElement(theField, label).getAttribute("for");
  }
}
