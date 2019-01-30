package gov.cancer.pageobject.crosscutting;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.cancer.pageobject.PageObjectBase;

public class PageOptions extends PageObjectBase {

  // public PageOptions() {
  //   super("");
  //   // pageOptionsControl = null;
  //   PageFactory.initElements(this.getBrowser(), this);
  // }

  /*
   * Testing if the PageOptionsControl exists on page and is visible
   */
  public boolean IsVisible() {
    List<WebElement> elementExists = getPageOptionsControl();

    if (elementExists.size() > 0) {
      WebElement pocElement = elementExists.get(0);
      return pocElement.isDisplayed();
    }
    return false;
  }

  public boolean ButtonVisible(String buttonSelector) {
    List<WebElement> elementExists = getButtonControl(buttonSelector);

    if (elementExists.size() > 0) {
      WebElement pocButton = elementExists.get(0);
      return pocButton.isDisplayed();
    }
    return false;
  }

  private List<WebElement> getPageOptionsControl() {
    List<WebElement> pocControls = getBrowser().findElements(By.cssSelector("#PageOptionsControl1"));
    // int controlCount = pocControls.size();
    // System.out.println(" Size = " + controlCount);

    return pocControls;
  }

  private List<WebElement> getButtonControl(String buttonSelector) {
    List<WebElement> buttonControls = getBrowser().findElements(By.cssSelector("li." + buttonSelector));
    // int controlCount = buttonControls.size();
    // System.out.println(" Size = " + controlCount);

    return buttonControls;
  }
}
