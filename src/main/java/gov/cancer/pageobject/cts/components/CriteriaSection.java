package gov.cancer.pageobject.cts.components;


import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.Button;
import gov.cancer.pageobject.components.Component;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class represents Search Criteria section from search results page
 */
public class CriteriaSection extends Component {
  private WebElement criteriaSection;
  private Button showSearchCriteria;

  private final static String LABEL_LOCATOR = ":scope tbody tr th";
  private final static String VALUE_LOCATOR = ":scope tbody tr td";
  private final static String SHOW_SEARCH_CRITERIA_LOCATOR = ":scope button";


  /**
   * Constructor
   *
   * @param element
   *        - WebElement defining criteria section
   */
  public CriteriaSection( WebElement element) {
    super(element);
    criteriaSection = element;
    showSearchCriteria = new Button(ElementHelper.findElement(criteriaSection, SHOW_SEARCH_CRITERIA_LOCATOR));
  }

  //Case insensitive list of criteria
  private Map<String, String> criteriaMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

  /**
   * Adds all labels and values to the map.
   * Where label is 'Category'
   *       value is 'Your Selection'
   *
   */
  public void buildCriteriaMap() {
    List<WebElement> labelCell = ElementHelper.findElements(criteriaSection, LABEL_LOCATOR);
    List<WebElement> valueCell = ElementHelper.findElements(criteriaSection, VALUE_LOCATOR);
    for (int i = 0; i < labelCell.size(); i++) {
      criteriaMap.put(labelCell.get(i).getText(), valueCell.get(i).getText());
    }
  }

  /**
   * Method to expand Search Criteria table
   */
  public void expand (){
    showSearchCriteria.click();
  }
  /**
   * Gets the Criterion associated with label. Note that this method may return
   * null if the label was not defined.
   *
   * @param label The 'Category' value to retrieve.
   *
   * @return String 'Your Selection' value .
   */
  public String getSelectionValue(String label) {
    return criteriaMap.get(label);
  }

  /**
   * Checks if the label is a valid key in the criteria map
   * @param label
   *            - label to search for in the key set
   * @return
   */
  public boolean isCategoryPresent (String label){
    return criteriaMap.containsKey(label);
  }

  /**
   * Check is the criteria drop down table expanded
   */

  public boolean isExpanded(){
    return Boolean.parseBoolean(criteriaSection.getAttribute("aria-expanded"));
  }
}
