package gov.cancer.pageobject.cts.advanced_search_page_components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.Component;
import gov.cancer.pageobject.components.RadioButton;
import gov.cancer.pageobject.components.TextField;
import gov.cancer.pageobject.helper.Link;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;


/**
 * This class represents location section on Advanced Search Page
 */
public class LocationSection extends Component {

  // toggle
  private WebElement limitResultToggle;
  // ZipCode radio Button
  private RadioButton zipCodeRadioButton;
  // Country, State, City radio button
  private RadioButton countryStateCityRadioButton;
  // Hospitals radio button
  private RadioButton hospitalsRadioButton;
  //at NIH radio button
  private RadioButton atNIHRadioButton;
  //Search All radio button
  private RadioButton searchAllRadioButton;
  //section title
  private WebElement title;
  // help link
  private Link helpLink;
  //webdriver instance is used to initialize CountryStateCitySubSection and HospitalsInstitutionSubSection,
  //which in it's turn contain AutoSuggest fields (where driver is needed)
  private WebDriver driver;
  //main element
  private WebElement scope;


  /************LOCATORS***************/
  private final static String limitResultLocator = "div.cts-toggle label";
  private final static String zipCodeRadioButtonLocator = "div:nth-of-type(2) > .cts-radio__label";
  private final static String countryStateCityRadioButtonLocator = "div:nth-of-type(3) > .cts-radio__label";
  private final static String hospitalsRadioButtonLocator = "div:nth-of-type(4) > .cts-radio__label";
  private final static String atNIHRadioButtonLocator = "div:nth-of-type(5) > .cts-radio__label";
  private final static String HELP_LINK_LOCATOR = ":scope legend a";
  private final static String TITLE_LOCATOR = ":scope legend span";
  private final static String SEARCH_ALL_LOCATIONS_RADIO_BUTTON_LOCATOR = "div:nth-of-type(1) > .cts-radio__label";


  /**
   * Constructor
   */
  public LocationSection(WebDriver driver, WebElement element) {
    super(element);
    this.driver = driver;
    this.scope = element;
    limitResultToggle = ElementHelper.findElement(element, limitResultLocator);
    zipCodeRadioButton = new RadioButton(ElementHelper.findElement(element, zipCodeRadioButtonLocator));
    countryStateCityRadioButton = new RadioButton(ElementHelper.findElement(element, countryStateCityRadioButtonLocator));
    hospitalsRadioButton = new RadioButton(ElementHelper.findElement(element, hospitalsRadioButtonLocator));
    atNIHRadioButton = new RadioButton(ElementHelper.findElement(element, atNIHRadioButtonLocator));
    title = ElementHelper.findElement(element, TITLE_LOCATOR);
    helpLink = new Link(ElementHelper.findElement(element, HELP_LINK_LOCATOR));
    searchAllRadioButton = new RadioButton(ElementHelper.findElement(element, SEARCH_ALL_LOCATIONS_RADIO_BUTTON_LOCATOR));

  }

  /**
   * Getter for Zip Code radioButton
   */
  public RadioButton getZipCodeRadioButton() {
    return zipCodeRadioButton;
  }

  /**
   * Getters for Country State City radioBitton
   *
   * @return
   */
  public RadioButton getCountryStateCityRadioButton() {
    return countryStateCityRadioButton;
  }

  /**
   * Getters for Hospitals radioBitton
   *
   * @return
   */
  public RadioButton getHospitalsRadioButton() {
    return hospitalsRadioButton;
  }

  /**
   * Getters for At NIH radioBitton
   *
   * @return
   */
  public RadioButton getAtNIHRadioButton() {
    return atNIHRadioButton;
  }

  /**
   * methods to click on limitResultToggle
   *
   * @param limit - switchToggle if 'Yes'(true) and it is not selected already, then method clicks on a toggle
   *              *                     if 'No' (false) and it is selected, then click to unselect
   */

  public void limitToVaOnly(boolean limit) {
    if (limit && !limitResultToggle.isSelected()) {
      limitResultToggle.click();
    } else if (!limit && limitResultToggle.isSelected())
      limitResultToggle.click();
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
   * Getter for Search All Location radio button
   *
   * @return
   */
  public RadioButton getSearchAllRadioButton() {
    return searchAllRadioButton;
  }

  /**
   * Getter for Country/State/City SubSection
   *
   * @return
   */
  public CountryStateCitySubSection getCountryStateCitySubSection() {
    return new CountryStateCitySubSection(driver, ElementHelper.findElement(scope, ":scope div[class='search-location__block search-location__country']"));
  }

  /**
   * Getter for ZipCode subsection
   *
   * @return
   */
  public ZipCodeSubSection getZipCodeSubSection() {
    return new ZipCodeSubSection(ElementHelper.findElement(scope, ":scope div[class='search-location__block search-location__zip']"));
  }

  /**
   * Getter for hospitals subsection
   */
  public HospitalsInstitutionSubSection getHospitalsInstitutionSubSection() {
    return new HospitalsInstitutionSubSection(driver, ElementHelper.findElement(scope, ":scope div.cts-autocomplete"));
  }


}
