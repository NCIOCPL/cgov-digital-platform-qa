package gov.cancer.pageobject.cts.cts_components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;
import gov.cancer.pageobject.components.Button;
import gov.cancer.pageobject.components.TextField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class represents all common elements of all CTS pages.
 */
public class CTS_Section extends PageObjectBase {
  //root element of CTS
  private WebElement rootElement;
  // Header element
  private WebElement header;


  //children of root element
  private final static String CHILDREN = ":scope *";
  //header locator
  private final static String HEADER = ":scope h1";
  //root locator
  private final static String ROOT = "#NCI-CTS-root";


  private final static String ACCORDION = ".cts-accordion h2";


  /**
   * Main constructor
   * @param path
   */
    public CTS_Section (String path) {
      super(path);
      this.rootElement = ElementHelper.findElement(getBrowser(),ROOT );
      header = ElementHelper.findElement(rootElement, HEADER);}


  /**
   * Check if header element is displayed
   * @return
   */
   public boolean isHeaderDisplayed(){
    try {
      getWait().until(ExpectedConditions.visibilityOf(header));
      return true;
    }catch (NoSuchElementException e){
      return false;
    }
   }

  /**
   * Method to wait until header is loaded, e.g. ReactAPP is loaded
   */
  public void waitUntilLoaded(){
    getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(HEADER)));
  }

  /**
   * navigation event that is triggered by clicking submit button.
   * MouseActionHelper is not used for clicking on element as this click will cause navigation event
   *
   * @return NavigationResultPage object
   */
  public CTSSearchNavigationResult clickSubmit(Button button) {
    expectUrlChange(() -> {
      button.click();
    });
    CTSSearchNavigationResult page = new CTSSearchNavigationResult(getBrowser());
    return page;
  }

  public CTSSearchNavigationResult hitEnterKey(TextField field){
    expectUrlChange(() -> {
      field.hitEnter();
    });
    CTSSearchNavigationResult page = new CTSSearchNavigationResult(getBrowser());
    return page;
  }

}



