package gov.cancer.pageobject.crosscutting;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;

/**
 * Pseudo page object representing any page in the system. The class is used
 * solely for verifying attributes of a page's Public Use Text
 */
public class PublicUsePage extends PageObjectBase {

  // Public Use Text in English and Spanish Languages
  final public String publicUse_english = "If you would like to reproduce some or all of this content";
  final public String publicUse_spanish = "Si desea copiar algo de este texto";

  // Public Use Text
  final public String publicUse = ".public-use";

  // Page Title Element
  final public String pageTitle = "#main h1";

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
    return ElementHelper.findElement(getBrowser(), publicUse).getText();
  }

  /*
   * Check if Public Use Text contains title of the page.
   */
  public boolean isPublicUseContainsTitle() {
    return ElementHelper.findElement(getBrowser(), publicUse).getText().contains(this.getPageTitle());
  }

  /**
   * Constructor
   *
   * @param path server-relative path of the page to load.
   */
  public PublicUsePage(String path) {
    super(path);
  }

  /**
   * Reports whether the Public Use Text is visible.
   *
   * @return True if the Public Use Text is visible. False if page has no Public
   *         Use text
   */
  public boolean isPublicUseTextPresent() {
    if (ElementHelper.findElement(getBrowser(), publicUse) != null)
      return ElementHelper.findElement(getBrowser(), publicUse).isDisplayed();
    else
      return false;
  }

  /*
   * Returns the language of the Public Use Text to compare with Data provider
   *
   */
  public String getPublicUseTextLanguage() {
    String language = null;
    String header = ElementHelper.findElement(getBrowser(), publicUse).getText();

    if (header.startsWith(publicUse_english)) {
      language = "English";
    } else if (header.startsWith(publicUse_spanish)) {
      language = "Spanish";
    }
    return language;
  }

}
