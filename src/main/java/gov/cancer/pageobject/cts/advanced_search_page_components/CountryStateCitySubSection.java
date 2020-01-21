package gov.cancer.pageobject.cts.advanced_search_page_components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.AutoSuggestField;
import gov.cancer.pageobject.components.Component;
import gov.cancer.pageobject.components.TextField;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Iterator;
import java.util.List;

/**
 * This class represents Country/State/City subsection of Advanced Search Page Location Section - hidden until
 * corresponding radio button is selected
 */
public class CountryStateCitySubSection extends Component {

  //country dropdown list
  private Select countryDropdown;
  // State field - only visible for United States selected in country list
  private AutoSuggestField state;
  // city text field - always available in the subsection
  private TextField city;

  /**
   * Constructor
   *
   * @param element - component element
   */
  public CountryStateCitySubSection(WebDriver driver, WebElement element) {
    super(element);
    countryDropdown = new Select(ElementHelper.findElement(element, "select#country"));
    city = new TextField(ElementHelper.findElement(element, "input#city"));
    state = new AutoSuggestField(driver, ElementHelper.findElement(element, ":scope input#lst"));
  }

  /**
   * Getter for state Autosuggest
   *
   * @return
   */
  public AutoSuggestField getState() {
    return state;
  }

  /**
   * Getter for City textField
   *
   * @return
   */
  public TextField getCity() {
    return city;
  }

  /**
   * Method checks if country list is sorted
   *
   * @return
   */
  public boolean isCountrySorted() {
    List<WebElement> allOptions = countryDropdown.getOptions();
    //get all options texts
    Iterator<WebElement> iterator = allOptions.iterator();
    //skip the first option 'All'
    iterator.next();
    //start from the next State after 'All' option
    WebElement current, previous = iterator.next();
    while (iterator.hasNext()) {
      current = iterator.next();
      //lexicographically compare previous to current - if previous word is not sorted compared to current, method returns false
      if (previous.getText().compareTo(current.getText()) > 0) {
        return false;
      }
      previous = current;
    }
    return true;
  }

  /**
   * Method select a country from a list
   *
   * @param value Country name
   */
  public void selectCountry(String value) {
    countryDropdown.selectByValue(value);
  }

  /**
   * Method returns selected country
   *
   * @return name of selected country
   */
  public String getSelectedCountry() {
    if (countryDropdown.getAllSelectedOptions().size() == 1)
      return countryDropdown.getAllSelectedOptions().get(0).getText();
    else
      return null;
  }

}
