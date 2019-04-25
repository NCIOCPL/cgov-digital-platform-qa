package gov.cancer.pageobject.crosscutting;

import org.openqa.selenium.WebElement;
import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;

/**
 * Pseudo page object representing any page in the system. The class is used
 * solely for verifying attributes of a page's Public Use Text
 */
public class PublicUsePage extends PageObjectBase {

  // The section being targeted. Because the section may not be present,
  // it must searched for in the constructor instead of with a FindBy annotation.
  WebElement targetedSection = null;

  // CSS selector for locating the section.
  String publicUseText = ".public-use";

  // Page Title Element
  final public String pageTitle = "#main h1";

  /**
   * Constructor
   *
   * @param path server-relative path of the page to load.
   */
  public PublicUsePage(String path) {
    super(path);
    // Locate the section if it's available.
    targetedSection = ElementHelper.findElement(this.getBrowser(), publicUseText);
  }

  /*
   * Getting Page Title text
   */
  public String getPageTitle() {
    return ElementHelper.findElement(getBrowser(), pageTitle).getText();
  }

  /*
   * Getting Public Use Text length
   */
  public String getPublicUseTextLength() {
    return ElementHelper.findElement(getBrowser(), publicUseText).getText();
  }

  /*
   * Check if Public Use Text contains title of the page.
   */
  public String getPublicUseText() {
    return ElementHelper.findElement(getBrowser(), publicUseText).getText();
  }

  /**
   * Reports whether the section this page is looking for is visible.
   *
   * Returns TRUE if the section is present and visible, returns false otherwise.
   * Note: This means that if the section is missing from the page altogether, it
   * is considered not visible.
   */
  public boolean sectionIsVisible() {
    if (targetedSection != null)
      return targetedSection.isDisplayed();
    else
      return false;
  }

  /**
   * Repots whether the Public Use Text section contains the specified text.
   *
   * @param searchText The text to seazrch for.
   * @return True if the text is found, false otherwise.
   *
   *         NOTE: If the Public Use Text section does not appear on the page, it
   *         cannot contain text and this method will return false;
   */
  public boolean sectionContainsText(String searchText) {

    return ElementHelper.elementContainsText(targetedSection, searchText);
  }

}
