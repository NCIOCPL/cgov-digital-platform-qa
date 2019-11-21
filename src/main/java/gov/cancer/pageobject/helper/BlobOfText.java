package gov.cancer.pageobject.helper;

import org.openqa.selenium.WebElement;

public class BlobOfText {
  //webElement representing html field
  private WebElement container;

  /**
   * Main constructor
   * @param el webElement of html field
   */
  public BlobOfText(WebElement el) {
    container = el;
  }

  /**
   * Retrieves text of a field
   * @return
   */
  public String getText(){
    return container.getText();
  }
}
