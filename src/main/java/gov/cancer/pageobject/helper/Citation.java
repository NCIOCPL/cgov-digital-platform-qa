package gov.cancer.pageobject.helper;

import org.openqa.selenium.WebElement;

import gov.cancer.framework.ElementHelper;

/**
 * Class representing a single citation.
 */
public class Citation {

  static final String TEXT_SELECTOR = ":scope p:nth-child(1)";

  // The selector for PUBMED links must look for an immediate child of the
  // scope in order to avoid catching links embedded in the citation text.
  static final String PUBMED_SELECTOR = ":scope > a";

  // The element representing the entire citation.
  private WebElement theCitation;

  // The citation text. This must be present.
  private WebElement textElement;

  // The optional PUBMED link.
  private WebElement pubmedLink;

  /**
   * Constructor
   *
   * @param element
   *          WebElement containing the markup for the entire citation.
   */
  public Citation(WebElement element) {
    this.theCitation = element;
    this.textElement = ElementHelper.findElement(theCitation, TEXT_SELECTOR);
    this.pubmedLink = ElementHelper.findElement(theCitation, PUBMED_SELECTOR);
  }

  /**
   * The text of the citation.
   *
   * @return String containing the citation text.
   */
  public String getText() {
    return textElement.getText();
  }

  /**
   * Reports whether the citaiton has a PUBMED link
   *
   * @return
   */
  public boolean hasPubMedLink() {
    return (pubmedLink != null && pubmedLink.getText().contains("[PubMed Abstract]"));
  }

  /**
   * Retrieve the citation's PUBMED link.
   *
   * @return Returns a Link object representing the link if one is present.
   *         Returns null otherwise.
   */
  public Link getPubMedLink() {

    if (pubmedLink != null && pubmedLink.getText().contains("[PubMed Abstract]")) {
      return new Link(pubmedLink);

    } else
      return null;
  }
}
