package gov.cancer.pageobject.helper;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;

/**
 * Exposes useful methods for working with an <b>a</b> tag.
 */
public class Link {
  WebElement element;

  /**
   * Constructor
   * @param element The tag's WebElement.
   */
  public Link(WebElement element) {
    this.element = element;
  }

  /**
   * Returns the href attribute as a URL object.
   *
   * @return A URL instance containing the parsed link from the elements href
   *         attribute, or NULL if the href was missing, empty, or malformed.
   */
  public URL getUrl() {

    URL theUrl = null;

    String href = element.getAttribute("href");
    if (href != null && !href.trim().isEmpty()) {
      try {
        theUrl = new URL(href);
      } catch (MalformedURLException e) {
        theUrl = null;
      }
    }

    return theUrl;
  }

	/**
	 * Retrieves the text of the link html element
	 * 
	 * @return
	 */
	public String getText() {
		return element.getText();
	}
}
