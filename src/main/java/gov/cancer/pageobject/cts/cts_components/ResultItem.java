package gov.cancer.pageobject.cts.cts_components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.CheckBox;
import gov.cancer.pageobject.components.Component;
import gov.cancer.pageobject.helper.BlobOfText;
import gov.cancer.pageobject.helper.Link;
import org.openqa.selenium.WebElement;

/**
 * This helper class is used to retrieve individual result item from CTS Search Result Page
 */
public class ResultItem extends Component {

  //result item checkbox
private  CheckBox checkBox;
// result item title link
private Link titleLink;
  // result item description text
private BlobOfText text;

private final static String checkBoxLocator = "";
  private final static String titleLinkLocator = "";
  private final static String blobOfTextLocator = "";


  /**
   * Constructor
   * Initializes all private objects
   * @param element
   */
  public ResultItem (WebElement element){
  super(element);
  checkBox = new CheckBox(ElementHelper.findElement(element,checkBoxLocator));
  titleLink = new Link(ElementHelper.findElement(element, titleLinkLocator));
  text = new BlobOfText(ElementHelper.findElement(element,blobOfTextLocator));

    /**
     * Below are getter methods for private objects
     */
  }
  public CheckBox getCheckBox() {
    return checkBox;
  }

  public Link getTitleLink() {
    return titleLink;
  }

  public BlobOfText getText() {
    return text;
  }

}
