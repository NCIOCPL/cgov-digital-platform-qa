package gov.cancer.pageobject.crosscutting;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;
import gov.cancer.pageobject.helper.Citation;

/**
 * Pseudo page object representing any page in the system. The Citation class is
 * used solely for verifying attributes of a page's Citation section.
 */
public class PageWithCitations extends PageObjectBase {

  // The overall section.
  WebElement citationsSection = null;

  // Citation header
  WebElement citationHeader;

  /********* CITATION SELECTORS ***********************/

  final static String SECTION_SELECTOR = "#cgvCitationSl";
  final static String HEADER_SELECTOR = ":scope h6";
  final static String CITATION_SELECTOR = ":scope ol > li";

  /**
   * Constructor
   *
   * @param path
   *          server-relative path of the page to load.
   */
  public PageWithCitations(String path) {
    super(path);

    this.citationsSection = ElementHelper.findElement(getBrowser(), SECTION_SELECTOR);
  }

  /**
   * Reports whether the citation section was found.
   *
   * @return True if it was, false otherwise.
   */
  public boolean isSectionPresent() {
    if (citationsSection != null)
      return citationsSection.isDisplayed();
    else
      return false;
  }

  /* Returns true if header of Citation is displayed */
  /**
   * Is the Citation header present?
   *
   * @return True if present, false otherwise.
   */
  public boolean isHeaderPresent() {
    citationHeader = ElementHelper.findElement(citationsSection, HEADER_SELECTOR);
    if (citationHeader != null)
      return citationHeader.isDisplayed();
    else
      return false;
  }

  /**
   * Returns the citation section's header.
   *
   * @return A String containing the header if present.
   */
  public String getHeaderText() {
    this.citationHeader = ElementHelper.findElement(citationsSection, HEADER_SELECTOR);
    if (citationHeader != null)
      return citationHeader.getText();
    else
      return null;
  }

  /**
   * Find all of the citation objects on the page.
   *
   * @return A List of zero or more Citation objects.
   */
  public List<Citation> getCitationList() {

    List<Citation> citationList = new ArrayList<Citation>();

    List<WebElement> elements = ElementHelper.findElements(this.citationsSection, CITATION_SELECTOR);
    for (WebElement el : elements) {
      Citation citation = new Citation(el);
      citationList.add(citation);
    }

    return citationList;

  }

}
