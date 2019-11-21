package gov.cancer.pageobject.cts.components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.CheckBox;
import gov.cancer.pageobject.components.Component;
import gov.cancer.pageobject.helper.BlobOfText;
import gov.cancer.pageobject.helper.Link;
import org.openqa.selenium.WebElement;

import java.util.Map;
import java.util.TreeMap;

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

  /**
   * Map of categories for each result item
   * Key - category, e.g. Status, Age, Gender, Location
   * Value - retrieved value
   */
  private Map<String, String> categories = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);


  /**LOCATORS*/

  private final static String CHECK_BOX_LOCATOR = ":scope .cts-checkbox";
  private final static String TITLE_LINK_LOCATOR = ":scope a";
  private final static String STATUS_LOCATOR = ":scope div[class$='__contents'] div[class$='__category'] :nth-child(2)";
  private final static String AGE_LOCATOR = ":scope div[class$='__contents'] :nth-child(3)";
  private final static String GENDER_LOCATOR = ":scope div[class$='__contents'] :nth-child(4)";
  private final static String LOCATION_LOCATOR = ":scope div[class$='__contents'] :nth-child(5)";


  /**
   * Constructor
   * Initializes all private objects
   * @param element
   */
  public ResultItem (WebElement element){
    super(element);
    checkBox = new CheckBox(ElementHelper.findElement(element, CHECK_BOX_LOCATOR));
    titleLink = new Link(ElementHelper.findElement(element, TITLE_LINK_LOCATOR));
    categories.put("Status", ElementHelper.findElement(element, STATUS_LOCATOR).getText());
    categories.put( "Age", ElementHelper.findElement(element, AGE_LOCATOR).getText());
    categories.put("Gender", ElementHelper.findElement(element, GENDER_LOCATOR).getText());
    categories.put("Location",ElementHelper.findElement(element, LOCATION_LOCATOR).getText());

    /**
     *Method returns checkbox object
     */
  }
  public CheckBox getCheckBox() {
    return checkBox;
  }

  /**
   * Method returns title link
   * @return
   */
  public Link getTitleLink() {
    return titleLink;
  }

  /**
   * Method retrieve value of each category of Result Item
   *
   * @param category - Status, Age, Gender or Location
   * @return value of each category
   */
  public String getCategory(String category){
    return categories.get(category);
  }
}
