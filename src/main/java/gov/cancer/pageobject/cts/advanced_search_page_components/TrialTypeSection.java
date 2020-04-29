package gov.cancer.pageobject.cts.advanced_search_page_components;

import gov.cancer.framework.ElementChange;
import gov.cancer.framework.ElementHelper;
import gov.cancer.framework.ScrollUtil;
import gov.cancer.framework.WaitUtil;
import gov.cancer.pageobject.components.CheckBox;
import gov.cancer.pageobject.components.Component;
import gov.cancer.pageobject.helper.Link;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * This page represents Trial Type section from Advanced Search Form page
 */
public class TrialTypeSection extends Component {

  //Limit results to accepting healthy volunteers
  private WebElement limitResultsToggle;
  // 'All' checkbox
  private CheckBox allCheckbox;
  // List of all option checkboxes
  private List<CheckBox> allOptions = new ArrayList<>();
  //section title
  private WebElement title;
  // help link
  private Link helpLink;
  //webdriver is added to support scroll method (see below)
  private WebDriver driver;
  //private list of webelements is used for scroll method (to access the last checkbox and scroll to it)
  private List<WebElement> otherCheckBoxes;


  //Locator to find all trial type checkboxes
  private final static String GENERIC_CHECKBOX_LOCATOR = ".group-trial-types div.cts-checkbox ";
  private final static String LIMIT_RESULTS_LOCATOR = ":scope .cts-toggle__label[for='hv']";
  private final static String ALL_CHECK_BOX_LOCATOR = ":scope .select-all div";
  private final static String HELP_LINK_LOCATOR = ":scope legend a";
  private final static String TITLE_LOCATOR = ":scope legend span";


  /**
   * Constructor
   *
   * @param element
   */
  public TrialTypeSection(WebDriver driver, WebElement element) {
    super(element);
    this.driver=driver;
    limitResultsToggle = ElementHelper.findElement(element, LIMIT_RESULTS_LOCATOR);
    allCheckbox = new CheckBox(ElementHelper.findElement(element, ALL_CHECK_BOX_LOCATOR));
    otherCheckBoxes = ElementHelper.findElements(element, GENERIC_CHECKBOX_LOCATOR);
    for (WebElement we : otherCheckBoxes) {
      allOptions.add(new CheckBox(we));
    }
    helpLink = new Link(ElementHelper.findElement(element, HELP_LINK_LOCATOR));
    title = ElementHelper.findElement(element, TITLE_LOCATOR);
  }

  /**
   * Getter method to retrieve checkbox 'all'
   *
   * @return
   */
  public CheckBox getAllCheckbox() {
    return allCheckbox;
  }

  /**
   * Getter method for all checkboxes (Except 'All')
   *
   * @return all option of checkboxes
   */
  public List<CheckBox> getAllOptions() {
    return allOptions;
  }

  /**
   * Method to switch 'limit result' toggle
   *
   * @param switchToggle if 'Yes'(true) and 'yes' span is not displayed , then method clicks on a toggle
   *                     if 'No' (false) and 'yes' span is displayed, then click to unselect
   */
  public void limitToHealthyVolunteer(boolean switchToggle) {
    if (switchToggle && !(ElementHelper.findElement(limitResultsToggle, ":scope .pos").isDisplayed())) {
      limitResultsToggle.click();
      ElementChange.WaitForText(driver, limitResultsToggle, "Yes");
    } else if (!switchToggle && ElementHelper.findElement(limitResultsToggle, ":scope .pos").isDisplayed()){
      limitResultsToggle.click();
      ElementChange.WaitForText(driver, limitResultsToggle, "No");
    }
  }

  /**
   * Returns title text
   */
  public String getTitle() {
    return title.getText();
  }

  /**
   * Getter for Help Link
   *
   * @return
   */
  public Link getHelpLink() {
    return helpLink;
  }
  /**
   *This method is scrolling until the first checkbox is visible
   * It is necessary, because of the presence of 'sticky block (form action)' which receives the click, instead of
   * the checkbox
   */
  public void scrollUntilCheckBoxVisible(){
    ScrollUtil.scrollIntoview(driver, otherCheckBoxes.get(1));
  }

  /**
   * This method is using JavaScript to get the state of toggle (which behaves as an invisible checkbox)
   * The DOM constructed in the way that toggle is represented by two elements :
   * 1) input element type 'checkbox' which is hidden and not
   * accessible with Selenium
   * 2) Label element that holds the text values for End user
   * When clicking happens, input hidden element is responsible of changing the state of toggle, where label is not changing
   * it's attributes
   * That is why accessing DOM directly with JavaScript reports the current state of hidden input checkbox - see below
   */
  public boolean getToggleState() {
    JavascriptExecutor javaScript = (JavascriptExecutor)driver;
    return Boolean.parseBoolean(javaScript.executeScript("return document.getElementById('hv').checked").toString());
    }

}
