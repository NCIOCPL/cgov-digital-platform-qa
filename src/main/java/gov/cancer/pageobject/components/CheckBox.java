package gov.cancer.pageobject.components;

import gov.cancer.framework.ElementHelper;
import org.openqa.selenium.WebElement;

/**
 * Class represents checkbox element
 */
public class CheckBox extends CGovField {
  // WebElement of checkbox
  private WebElement checkBox;
  private WebElement input;

  /**
   * Constructor
   *
   * @param element webelement of checkbox
   */
  public CheckBox(WebElement element) {
    super(element);
    this.checkBox = element;
    input = ElementHelper.findElement(element, "input[type='checkbox']");
  }

  /**
   * Method is used to check the checkbox
   */
  public void toggle() {
    checkBox.click();
  }

  /**
   * Method is used to retrieve whether the checkbox element is selected (checked)
   * @return
   */
  public boolean isSelected() {
    return input.isSelected();
  }

}
