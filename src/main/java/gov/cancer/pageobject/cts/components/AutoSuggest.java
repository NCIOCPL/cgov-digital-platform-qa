package gov.cancer.pageobject.cts.components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.AutoSuggestField;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This class represents any autosuggest field on CTS page
 */
public class AutoSuggest extends AutoSuggestField {

    //Autosuggest helper object used to select exact text value from the list
  private WebElement fieldMessage;
  private final static String MESSAGELOCATOR = ":scope .cts-autocomplete__menu-item";
  private WebElement scope;

    /**
     * Constructor
     *
     * @param scope
     */
    public AutoSuggest(WebDriver driver, WebElement scope){
      super(driver, ElementHelper.findElement(scope, ":scope input"));
      this.scope=scope;
    }

  /**
   * Getter for helper/no option message on auto suggest fields
   */
  public String getSectionFieldMessage() {
    fieldMessage = ElementHelper.findElement( scope, MESSAGELOCATOR);
    return fieldMessage.getText() ;
  }

}
