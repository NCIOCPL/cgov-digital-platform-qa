package gov.cancer.pageobject.cts.advanced_search_page_components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.CheckBox;
import gov.cancer.pageobject.components.Component;
import gov.cancer.pageobject.helper.Link;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents trial phase section on the Advanced Search Page
 */
public class TrialPhaseSection extends Component {
  //checkBox 'All'
  private CheckBox allCheckBox;
  //list of checkboxes for each Phase
  private List<CheckBox> phasesCheckBoxes = new ArrayList<>();
  //section title
  private WebElement title;
  // help link
  private Link helpLink;

  /******Locators*****/

  private final static String HELP_LINK_LOCATOR = ":scope legend a";
  private final static String TITLE_LOCATOR = ":scope legend span";

  //locator for 'all' checkbox
  private final String allCheckBoxLocator = ".select-all";
  //locator for each of the 'Phase' checkbox
  private final String phaseCheckboxLocator = ".group-phases>div";

  /**
   * Constructor
   *
   * @param element
   */
  public TrialPhaseSection(WebElement element) {
    super(element);
    allCheckBox = new CheckBox(ElementHelper.findElement(element, allCheckBoxLocator));
    List<WebElement> otherCheckBoxes = ElementHelper.findElements(element, phaseCheckboxLocator);
    for (WebElement we : otherCheckBoxes) {
      phasesCheckBoxes.add(new CheckBox(we));
    }
    helpLink = new Link(ElementHelper.findElement(element, HELP_LINK_LOCATOR));
    title = ElementHelper.findElement(element, TITLE_LOCATOR);
  }

  /**
   * Method to retrieves all phases checkboxes
   * @return list of checkboxes of all trial phases
   */
  public List<CheckBox> getAllOptionsCheckBoxes() {
    return phasesCheckBoxes;
  }

  /**
   * Getter method to return 'All' checkbox
   * @return
   */
  public CheckBox getAllCheckBox() {
    return allCheckBox;
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
