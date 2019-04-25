package gov.cancer.pageobject.crosscutting;

import org.openqa.selenium.WebElement;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;

/**
 * Represents a page which may have an Intro Text section.
 */
public class PageWithIntroText extends PageObjectBase {

  // The section being targeted. Because the section may not be present,
  // it must searched for in the constructor instead of with a FindBy annotation.
  WebElement targetedSection = null;

  // CSS selector for locating the section.
  String sectionSelector = "#cgvBody .blog-intro-text";

  /**
   * Constructor
   *
   * @param path
   *          server-relative path of the page to load.
   */
  public PageWithIntroText(String path) {
    super(path);

    // Locate the section if it's available.
    targetedSection = ElementHelper.findElement(this.getBrowser(), sectionSelector);
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
   * Repots whether the Intro Text section contains the specified text.
   *
   * @param searchText The text to seazrch for.
   * @return True if the text is found, false otherwise.
   *
   *         NOTE: If the Intro Text section does not appear on the page, it
   *         cannot contain text and this method will return false;
   */
  public boolean sectionContainsText(String searchText) {

    return ElementHelper.elementContainsText(targetedSection, searchText);
  }

}
