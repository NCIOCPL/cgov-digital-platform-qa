package gov.cancer.pageobject.cts.advanced_search_page_components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.Component;
import gov.cancer.pageobject.components.RadioButton;
import gov.cancer.pageobject.helper.Link;
import org.openqa.selenium.WebElement;


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

  //section title
  private WebElement title;
  // help link
  private Link helpLink;

  /************LOCATORS***************/
  private final static String limitResultLocator = "";
  private final static String zipCodeRadioButtonLocator = "";
  private final static String countryStateCityRadioButtonLocator = "";
  private final static String hospitalsRadioButtonLocator = "";
  private final static String atNIHRadioButtonLocator = "";
  private final static String HELP_LINK_LOCATOR = ":scope legend a";
  private final static String TITLE_LOCATOR = ":scope legend span";


  /**
   * Constructor
   */
  public LocationSection(WebElement element) {
    super(element);
    limitResultToggle = ElementHelper.findElement(element, limitResultLocator);
    zipCodeRadioButton = new RadioButton(ElementHelper.findElement(element, zipCodeRadioButtonLocator));
    countryStateCityRadioButton = new RadioButton(ElementHelper.findElement(element, countryStateCityRadioButtonLocator));
    hospitalsRadioButton = new RadioButton(ElementHelper.findElement(element, hospitalsRadioButtonLocator));
    atNIHRadioButton = new RadioButton(ElementHelper.findElement(element, atNIHRadioButtonLocator));
    title = ElementHelper.findElement(element, TITLE_LOCATOR);
    helpLink = new Link(ElementHelper.findElement(element, HELP_LINK_LOCATOR));

  }

  /**
   * Getters for radioButton objects
   * TODO Add Locators and replace throw NotImplemented to return RadioButton
   * @return
   */
  public RadioButton getZipCodeRadioButton() {
    throw new UnsupportedOperationException();
  }

  public RadioButton getCountryStateCityRadioButton() {
    throw new UnsupportedOperationException();
  }

  public RadioButton getHospitalsRadioButton() {
    throw new UnsupportedOperationException();
  }

  public RadioButton getAtNIHRadioButton() {
    throw new UnsupportedOperationException();
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
}
