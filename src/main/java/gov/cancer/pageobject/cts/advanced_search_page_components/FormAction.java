package gov.cancer.pageobject.cts.advanced_search_page_components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.Button;
import gov.cancer.pageobject.components.Component;
import org.openqa.selenium.WebElement;

/**
 * This class represents sticky anchor block that contains two buttons : Find Trial and Clear Form
 */

public class FormAction extends Component {

  // Find Trials button
  private Button findTrialsButton;
  // Clear Form button
  private Button clearFormButton;

  private final static String findTrialsLocator = ":scope button[type='submit']";
  private final static String clearFormLocator = ":scope button[type='button']";

  /**
   * Constructor
   */
  public FormAction (WebElement element){
    super(element);
    findTrialsButton = new Button(ElementHelper.findElement(element,findTrialsLocator));
    clearFormButton = new Button(ElementHelper.findElement(element, clearFormLocator));
  }

  /**
   * Getter methods for private objects
   * @return buttons
   */
  public Button getFindTrialsButton() {
    return findTrialsButton;
  }

  public Button getClearFormButton() {
    return clearFormButton;
  }
}
