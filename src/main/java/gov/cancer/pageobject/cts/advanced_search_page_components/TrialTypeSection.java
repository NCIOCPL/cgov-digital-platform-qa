package gov.cancer.pageobject.cts.advanced_search_page_components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.CheckBox;
import gov.cancer.pageobject.components.Component;
import gov.cancer.pageobject.helper.Link;
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


  //Locator to find all trial type checkboxes
  private final static String GENERIC_CHECKBOX_LOCATOR = ".group-trial-types div.cts-checkbox ";
  private final static String LIMIT_RESULTS_LOCATOR = ":scope .cts-toggle__label";
  private final static String ALL_CHECK_BOX_LOCATOR = ":scope .select-all div";
  private final static String HELP_LINK_LOCATOR = ":scope legend a";
  private final static String TITLE_LOCATOR = ":scope legend span";


  /**
   * Constructor
   *
   * @param element
   */
  public TrialTypeSection(WebElement element) {
    super(element);
    limitResultsToggle = ElementHelper.findElement(element, LIMIT_RESULTS_LOCATOR);
    allCheckbox = new CheckBox(ElementHelper.findElement(element, ALL_CHECK_BOX_LOCATOR));
    List<WebElement> allOptionsWebElements = ElementHelper.findElements(element, GENERIC_CHECKBOX_LOCATOR);
    for (WebElement we : allOptionsWebElements) {
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
   * @param switchToggle if 'Yes'(true) and it is not selected already, then method clicks on a toggle
   *                     if 'No' (false) and it is selected, then click to unselect
   */
  public void limitToHealthyVolunteer(boolean switchToggle) {
    if (switchToggle && !limitResultsToggle.isSelected()) {
      limitResultsToggle.click();
    } else if (!switchToggle && limitResultsToggle.isSelected())
      limitResultsToggle.click();
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
}
