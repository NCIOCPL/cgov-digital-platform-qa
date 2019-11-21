package gov.cancer.pageobject.components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.helper.Link;
import org.openqa.selenium.WebElement;

/**
 * This class represents delighter object
 */
public class Delighter extends Component {

  // Link of delighter
  private Link delighterLink;
  // Delighter title element
  private WebElement title;
  // Delighter element
  private WebElement delighter;

  private final static String DELIGHTER_LINK_LOCATOR = ":scope a";
  private final static String TITLE_LOCATOR = ":scope h4";


  /**
   * Constructor
   * Initializes  delighter, its link and title
   * @param element
   */
  public Delighter (WebElement element) {
    super(element);
    delighter = element;
    delighterLink = new Link(ElementHelper.findElement(delighter, DELIGHTER_LINK_LOCATOR));

  }
  /**
   * Icon is a pseudo styling element attached to link. Whether it's displayed is verified by value of computed parameter background image
   * @return
   */
  public boolean isIconDisplayed (){

    return !ElementHelper.findElement(delighter, DELIGHTER_LINK_LOCATOR).getCssValue("background-image").isEmpty();
  }

  /**
   * Method retrieves text of title link
   * @return
   */
  public String getLinkTitle(){
    title = ElementHelper.findElement(delighter, TITLE_LOCATOR);
    return title.getText();
  }

  /**
   * Getter for delighter url link
   * @return
   */
  public Link getLink (){
    return delighterLink;
  }

}
