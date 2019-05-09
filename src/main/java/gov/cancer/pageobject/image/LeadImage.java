package gov.cancer.pageobject.image;

import org.openqa.selenium.WebElement;
import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;

public class LeadImage extends PageObjectBase {

  /********* LEAD IMAGE SELECTORS ***********************/

  final public String imagecontainer = "div#cgvBody > div > figure";
  final public String imagecaption = "div.caption-container p";
  final public String imagecredit = ".image-photo-credit";
  final public String imagediv = "div[class='centered-element'] img";

  /**
   * Constructor
   *
   * @param path
   *          server-relative path of the page to load.
   */
  public LeadImage(String path) {
    super(path);

  }

  /* Returns true if Lead image is displayed on the page */
  public boolean isLeadImageVisible() {
    return ElementHelper.isVisible(getBrowser(), imagecontainer);
  }

  /* Returns the caption of the image */
  public String getCaption() {
    return ElementHelper.getText(getBrowser(), imagecaption);
  }

  /* Returns the credit of the image */
  public String getCredit() {
    return ElementHelper.getText(getBrowser(), imagecredit);
  }

  /* Returns the alt text of the image */
  public String getAltText() {
    WebElement ImageDiv = ElementHelper.findElement(getBrowser(), imagediv);
    return ImageDiv.getAttribute("alt");
  }

}
