package gov.cancer.pageobject.components;

import gov.cancer.framework.AutoSuggestHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * This class represents any autosuggest field on a page
 */
public class AutoSuggestField extends TextField {

  //Autosuggest helper object used to select exact text value from the list
  private AutoSuggestHelper autoSuggestHelper;


  /**
   * Constructor
   * Initializes autosuggest helper object
   * @param element
   */
  public AutoSuggestField (WebDriver driver, WebElement element){
    super(element);
    autoSuggestHelper = new AutoSuggestHelper(driver,element);
  }

  /**
   *  Used to select  item from the drop down autosuggest field
   * @param text - the text of the item to select
   */
  public void selectItem (String text){
  autoSuggestHelper.SelectExact(text);
  }

}
