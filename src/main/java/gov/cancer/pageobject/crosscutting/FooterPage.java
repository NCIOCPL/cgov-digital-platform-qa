package gov.cancer.pageobject.crosscutting;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;

/**
 * Pseudo page object representing any page in the system. The FooterPage class
 * is used solely for verifying attributes of a page's Footer section.
 */
public class FooterPage extends PageObjectBase {

  @FindBy(how = How.CSS, using = "#nvcgSlFooter")
  WebElement pageFooter;

  final public String Footer_header = "National Cancer Institute";
  final public String Footer_headerEsp = "Instituto Nacional del Cáncer";

  /********* FOOTER SELECTORS ***********************/

  final public String footerheader = "div[class='site-footer__header']>h1";

  /********* FOOTER Page Methods ***********************/

  /**
   * Constructor
   *
   * @param path
   *          server-relative path of the page to load.
   */
  public FooterPage(String path) {
    super(path);
  }

  /* Returns true if Footer is displayed on the page */
  public boolean isFooterVisible() {
    return pageFooter.isDisplayed();
  }

  /* Returns true if header of Footer is displayed */
  public boolean isFooterHeaderVisible() {
    return ElementHelper.findElement(pageFooter, footerheader).isDisplayed();
  }

  /* Returns the header of the Footer */
  public String getFooterHeader() {
    String header = ElementHelper.findElement(pageFooter, footerheader).getText();
    return header;
  }

  /* Returns the language of the Footer */
  public String getFooterLanguage() {
    String language = null;
    String header = ElementHelper.findElement(pageFooter, footerheader).getText();

    if (header.startsWith(Footer_header)) {
      language = "English";
    } else if (header.startsWith(Footer_headerEsp)) {
      language = "Spanish";
    }
    return language;
  }

  /* Returns text of Footer */
  public String footerText() {

    return pageFooter.getText().trim();
  }

  /* Returns true if Footer is displayed once on the page */
  public boolean isFooterVisibleOnce() {
    List<WebElement> findfooter = ElementHelper.findElements(pageFooter, "footer[class='site-footer']");
    int footerexists = findfooter.size();
    if (footerexists == 1) {
      return true;
    } else {
      return false;
    }
  }

}
