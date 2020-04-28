package gov.cancer.pageobject.image;

import java.net.URI;
import java.net.URISyntaxException;
import org.openqa.selenium.WebElement;

public class Image {


  // The element containing the image.
  private WebElement element;
  private URI imageUri = null;

  /**
   * Constructor
   *
   * @param element WebElement containing the markup for the image.
   */
  public Image(WebElement element) {
    this.element = element;

  }

  /**
   * Retrieves the content of the image's <b>alt</b> attribute.
   *
   * @return String containing the images alt text, or NULL if the attribute is
   * not present.
   */
  public String getAltText() {
    return element.getAttribute("alt");
  }

  /**
   * Returns the image src attribute as a URI object.
   *
   * @return A URI instance containing the parsed link from the elements href
   * attribute, or NULL if the href was missing, empty, or malformed.
   *
   */
  public URI getImageURI() {


    String href = element.getAttribute("src");
    if (href != null && !href.trim().isEmpty()) {
      try {
        imageUri = new URI(href);
    } catch (URISyntaxException e) {
      throw new RuntimeException("bad URI: " + href);
    }
  }
    return imageUri;
  }

  /**
   * Returns the token value in the image src attribute.
   *
   * @return A String instance containing the token value in the image src attribute
   *
   */

  public String getToken(){
    String token = getImageURI().getQuery();
    return token;
  }

}
