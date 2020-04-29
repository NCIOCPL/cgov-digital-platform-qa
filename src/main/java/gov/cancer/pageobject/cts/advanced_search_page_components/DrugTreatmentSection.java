package gov.cancer.pageobject.cts.advanced_search_page_components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.Component;
import gov.cancer.pageobject.cts.components.AutoSuggest;
import gov.cancer.pageobject.helper.Link;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This class represents Drug/Treatment Section of Advanced Search Page
 */
public class DrugTreatmentSection extends Component {
  // Drug/Treatment field
  private AutoSuggest drug;
  // Other Treatments field
  private AutoSuggest treatment;
  // help link
  private Link helpLink;
  // Title Element
  private WebElement title;

  // LOCATORS :
  private final static String DRUG_LOCATOR = "#dt-autocomplete-wrapper";
  private final static String TREATMENT_LOCATOR = "#ti-autocomplete-wrapper";
  private final static String HELP_LINK_LOCATOR = ":scope legend a";
  private final static String TITLE_LOCATOR = ":scope legend span";


  /**
   * Constructor
   */
  public DrugTreatmentSection(WebDriver driver, WebElement element) {
    super(element);
    drug = new AutoSuggest(driver, ElementHelper.findElement(element, DRUG_LOCATOR));
    treatment = new AutoSuggest(driver, ElementHelper.findElement(element, TREATMENT_LOCATOR));
    helpLink = new Link(ElementHelper.findElement(element, HELP_LINK_LOCATOR));
    title = ElementHelper.findElement(element, TITLE_LOCATOR);
  }

  /**
   * Getter method for Drug/Treatment field
   *
   * @return autosuggest object
   */
  public AutoSuggest getDrugAutoSuggest() {
    return drug;
  }

  /**
   * Getter method for Other Treatment Autosuggest object
   *
   * @return
   */
  public AutoSuggest getTreatmentAutoSuggest() {
    return treatment;
  }

  /**
   * Getter for Help Link
   */
  public Link getHelpLink() {
    return helpLink;
  }
  /**
   * Returns title text
   */
  public String getTitle(){
    return title.getText();
  }

}

