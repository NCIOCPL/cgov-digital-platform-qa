package gov.cancer.pageobject.cts;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.*;
import gov.cancer.pageobject.cts.advanced_search_page_components.AgeSection;
import gov.cancer.pageobject.cts.components.ErrorDisplay;
import org.openqa.selenium.WebElement;


/**
 * This class represents Basic Search page of CTS module
 */
public class BasicSearchPage extends CTSPage {

  //AutoSuggest object to select option from drop down menu for Cancer Type field
  private AutoSuggestField selectExactCancerType;
  // Alert object represents error message on the form
  private ErrorDisplay errorMessage;
  // Cancer Type key word field (used for autosuggest and search by key word)
  private WebElement cancerTypeKeyword;
  // Age field
  private TextField age;
  //Zip code field
  private TextField zip;
  //Cancer Type field
  private TextField keyword;
  // Submit button
  private Button searchButton;

  /**
   * age_field and zip_field are elements representing the root html for age and zip fields accordingly - used
   * for verifying the correct location of error message
   */
  private WebElement age_field;
  private WebElement zip_field;

  //form fields locators
  private final static String CANCER_TYPE_KEYWORD_TEXT_BOX = "#ctk";
  private final static String AGE_TEXT_BOX = "#age";
  private final static String ZIP_TEXT_BOX = "#zip";
  private final static String BUTTONSUBMIT = "button.btn-submit";
  // locators of drop down autoSuggest and it's options
  private final static String ITEM = ":scope div :nth-of-type(n)";
  //locator for age html root element
  private final static String AGE_FIELD = "#fieldset--age";
  //locator for zip html root element
  private final static String ZIP_FIELD = "#fieldset--zip";


  /**
   * Constructor
   * Initializes age, cancer type, zip code fields + submit button
   * Creates an AutoSuggest object
   * TextField objects that are separate for each form field
   *
   * @param path
   */
  public BasicSearchPage(String path) {
    super(path);
    cancerTypeKeyword = ElementHelper.findElement(getBrowser(), CANCER_TYPE_KEYWORD_TEXT_BOX);
    age = new TextField(ElementHelper.findElement(getBrowser(), AGE_TEXT_BOX));
    zip = new TextField(ElementHelper.findElement(getBrowser(), ZIP_TEXT_BOX));
    keyword = new TextField(cancerTypeKeyword);
    selectExactCancerType = new AutoSuggestField(getBrowser(),cancerTypeKeyword);
    searchButton = new Button(ElementHelper.findElement(getBrowser(), BUTTONSUBMIT));
    //parent html of fields
    age_field = ElementHelper.findElement(getBrowser(), AGE_FIELD);
    zip_field = ElementHelper.findElement(getBrowser(), ZIP_FIELD);
      }

  /**
   * Getter method for AutoSuggestField CancerType
   */
  public AutoSuggestField getSelectExactCancerType() {
   return selectExactCancerType;
  }

  /**
   * Method returns error message on age field
   *
   * @return boolean
   */
  public ErrorDisplay getErrorDisplayOnAge() {
    errorMessage = new ErrorDisplay(age_field);
    return errorMessage;
  }

  /**
   * Method returns Method returns error message on the zip field
   *
   * @return boolean
   */
  public ErrorDisplay getErrorDisplayOnZip() {
    errorMessage = new ErrorDisplay(zip_field);
    return errorMessage;
  }

  /**
  * Getter for No available options message on Cancer Type/Condition field
  */
  public String getCancerTypeKeywordErrorMessage() {

    return ElementHelper.getText( getBrowser(),".cts-autocomplete__menu-item") ;
 }

  /**
   * Method returns cancer type text field
   */
  public TextField getKeyword() {
    return keyword;
  }

  /**
   * Method returns cancer type text field
   */
  public TextField getAge() {
    return age;
  }

  /**
   * Method returns cancer type text field
   */
  public TextField getZip() {
    return zip;
  }
  /**
   * Method returns Find trials button
   */
 public Button getSearchButton(){
    return searchButton;
 }

}
