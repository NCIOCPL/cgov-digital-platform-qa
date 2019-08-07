package gov.cancer.pageobject.helper;

import org.openqa.selenium.WebElement;
import gov.cancer.framework.ElementHelper;

/**
 * Class representing a single citation.
 */
public class PDQCitation {
	// The selector for PUBMED links must look for an immediate child of the
	// scope in order to avoid catching links embedded in the PDQ citation text.
	static final String PUBMED_SELECTOR = ":scope > a";
	// The element representing the entire citation.
	private WebElement theCitation;
	// The optional PUBMED link.
	private Link pubmedLink;

	/**
	 * Constructor
	 *
	 * @param element WebElement containing the markup for the entire PDQ citation.
	 */
	public PDQCitation(WebElement element) {
		this.theCitation = element;
		if (theCitation.getText().endsWith("[PUBMED Abstract]")) {
			this.pubmedLink = new Link(ElementHelper.findElement(theCitation, PUBMED_SELECTOR));
		}
	}

	/**
	 * The text of the citation.
	 *
	 * @return String containing the citation text.
	 */
	public String getText() {
		return theCitation.getText();
	}

	/**
	 * Reports whether the PDQ citation has a PUBMED link
	 *
	 * @return Returns pub med links
	 */
	public boolean hasPubMedLink() {
		return (pubmedLink != null);
	}

	/**
	 * Retrieve the PDQ citation's PUBMED link.
	 *
	 * @return Returns a Link object representing the link if one is present.
	 *         Returns null otherwise.
	 */

	public Link getPubMedLink() {
			return pubmedLink;
	}

}
