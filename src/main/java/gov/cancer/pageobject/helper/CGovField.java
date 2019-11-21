package gov.cancer.pageobject.helper;

import gov.cancer.pageobject.PageObjectBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CGovField  {

 private WebElement theField;

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

//  public String getLabel(){
//    return ElementHelper.findElement(theField, label).getAttribute("for");
//  }
}
