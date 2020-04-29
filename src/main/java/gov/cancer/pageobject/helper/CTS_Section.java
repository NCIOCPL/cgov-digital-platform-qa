package gov.cancer.pageobject.helper;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

}



