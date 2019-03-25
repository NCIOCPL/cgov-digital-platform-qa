package gov.cancer.pageobject.crosscutting;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import gov.cancer.pageobject.PageObjectBase;

/**
 * Pseudo page object representing any page in the system. The BreadCrumb class
 * is used solely for verifying attributes of a page's content trail.
 */
public class ContentTitlePage extends PageObjectBase {

  @FindBy(how = How.CSS, using = "#main h1")
  WebElement contentTitle;

  /**
   * Constructor
   *
   * @param path server-relative path of the page to load.
   */
  public ContentTitlePage(String path) {
    super(path);
  }

  /**
   * Reports whether the content is visible.
   *
   * @return True if the content is visible.
   */
  public boolean isContentTitleVisible() {
    return contentTitle.isDisplayed();
  }

  /**
   * Retrieves the Text from all content title H1 elements, removing any
   * 
   */
  public String getContentTitleText() {
    String ContentTitle = contentTitle.getText();
    return ContentTitle;

  }

}
