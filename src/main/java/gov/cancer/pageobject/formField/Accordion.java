package gov.cancer.pageobject.formField;

import org.openqa.selenium.WebElement;

/**
 * Class represents accordion element
 */
public class Accordion {

  //WebElement of accordion
  private WebElement accordion;


  /**
   * Constructor
   * @param element webelement of accordion
   */
  public Accordion (WebElement element){
    accordion = element;
  }

  /**
   * Checks if accordion is expanded
   * @return
   */
  public boolean isExpanded (){
  return accordion.getAttribute("aria-expanded").equals("true");
  }

  /**
   * Retrieves the text of accordion header
   * @return
   */
  public String getHeaderText(){
    return accordion.getText();
  }
}
