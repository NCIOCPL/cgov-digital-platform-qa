package gov.cancer.pageobject.cts.advanced_search_page_components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.AutoSuggestField;
import gov.cancer.pageobject.components.Component;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This class represent Hospitals and Institutions SubSection on Location Section- hidden until
 * corresponding radioButton is selected
 */
public class HospitalsInstitutionSubSection extends Component {

  //AutoSuggest field for hospital/institutions
  private AutoSuggestField hospitalInput;
  //main element
  private WebElement element;

  /**
   * Constructor
   *
   * @param element - component element
   */
  public HospitalsInstitutionSubSection(WebDriver driver, WebElement element) {
    super(element);
    this.element = element;
    hospitalInput = new AutoSuggestField(driver, ElementHelper.findElement(element, ":scope input[role='combobox']"));
  }

  /**
   * Getter method for hospital input field
   *
   * @return
   */
  public AutoSuggestField getHospitalInput() {
    return hospitalInput;
  }

  /**
   * Method retrieves PlaceHolder text from the input field
   *
   * @return
   */
  public String getPlaceHolderText() {
    return element.getText();
  }
}
