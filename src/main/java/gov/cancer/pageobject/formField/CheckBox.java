package gov.cancer.pageobject.formField;

import org.openqa.selenium.WebElement;

/**
 * Class represents checkbox element
 */
public class CheckBox {
  // WebElement of checkbox
  private WebElement checkBox;

  /**
   * Constructor
   *
   * @param element webelement of checkbox
   */
  public CheckBox(WebElement element) {
    this.checkBox = element;
  }

  /**
   * Method is used to check the checkbox
   */
  public void check() {
    checkBox.click();
  }

  /**
   * Method is used to retrieve whether the checkbox element is selected (checked)
   * @return
   */
  public boolean isSelected() {
    return checkBox.isSelected();
  }
}
