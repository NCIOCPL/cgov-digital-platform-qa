package gov.cancer.pageobject.crosscutting;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;
import gov.cancer.pageobject.helper.PDQCitation;

/**
 * Pseudo page object representing any page in the system. The Citation class is
 * used solely for verifying attributes of a page's Citation section. Page
 * contains multiple PDQ citation sections, for testing purposes only the first
 * section is selected. See below - list of all sections, first object of the
 * list is used.
 */
public class PageWithPDQCitations extends PageObjectBase {
	// The overall section.
	WebElement pdqCitationsSection = null;

	// Citation header
	WebElement pdqCitationHeader = null;

	/********* PDQ CITATION SELECTORS ***********************/

	final static String SECTION_SELECTOR = "div.pdq-sections>ol";
	final static String HEADER_SELECTOR = "div.pdq-sections>h6";
  final static String CITATION_SELECTOR = "li";

  private List <PDQCitation> allPDQCitations = new ArrayList<>();

	/**
	 * Constructor
	 */
	public PageWithPDQCitations(String path) {
		super(path);

		List<WebElement> allPDQCitation = ElementHelper.findElements(getBrowser(), SECTION_SELECTOR);
		if (allPDQCitation.size() > 0) {
      this.pdqCitationsSection = allPDQCitation.get(0);
      List<WebElement> elements = ElementHelper.findElements(this.pdqCitationsSection, CITATION_SELECTOR);
      for (WebElement el : elements) {
        PDQCitation pdqcitation = new PDQCitation(el);
        allPDQCitations.add(pdqcitation);
      }

      List<WebElement> allCitationHeaders = ElementHelper.findElements(getBrowser(), HEADER_SELECTOR);
        this.pdqCitationHeader = allCitationHeaders.get(0);
    }
	}


	/**
	 * Reports whether the PDQ citation section was found.
	 *
	 * @return True if it was, false otherwise.
	 */
	public boolean isSectionPresent() {
		if (pdqCitationsSection != null)
			return pdqCitationsSection.isDisplayed();
		else
			return false;
	}

	/** Returns true if header of PDQ Citation is displayed **/
	/**
	 * Is the PDQ Citation header present?
	 *
	 * @return True if present, false otherwise.
	 */
	public boolean isHeaderPresent() {
		if (pdqCitationHeader != null)
			return pdqCitationHeader.isDisplayed();
		else
			return false;
	}

	/**
	 * Returns the PDQ citation section's header.
	 *
	 * @return A String containing the header if present.
	 */
	public String getHeaderText() {
			return pdqCitationHeader.getText();
	}

	/**
	 * Find all of the PDQ citation objects in a section on the page.(all of them)
	 *
	 * @return A List of zero or more PDQ Citation objects in a section on the page.
	 */
	public List<PDQCitation> getPDQCitationList() {
  return  allPDQCitations;
  }

  /**
   * This method returns the count of PubMed links
   * @return
   */
	public int getPubMedLinkCount(){
    int count = 0;
    for (PDQCitation item : allPDQCitations) {
      if (item.hasPubMedLink()) {
        count++;
      }
    }
    return count;
  }

}


