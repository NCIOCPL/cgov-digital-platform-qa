package gov.cancer.pageobject.cts;


import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.NavigationResult;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Class represents a page returned by Navigation event. Extending Navigation Result parent class
 */
public class SearchNavigationResult extends NavigationResult {
  // Search results message
  private WebElement searchmsg;

  private final static String SEARCH_RESULT_TITLE_LOCATOR = ".no-trials-found";
  /**
   * constructor retrieves current url and parses it
   *
   * @param driver current instance of the driver
   *
   */
  public SearchNavigationResult(WebDriver driver) {

    super(driver);
    searchmsg= ElementHelper.findElement(driver, SEARCH_RESULT_TITLE_LOCATOR);
  }

  /**
   * Returns query param value for an age
   * @return
   */
  public String getAgeParam (){
    String ageParam = "a";
    return getPageURL().getQueryParam(ageParam);
  }
  /**
   * Returns query param value for a zipcode
   * @return
   */
  public String getZipParam (){
    String zipParam = "z";
    return getPageURL().getQueryParam(zipParam);
  }
  /**
   * Returns query param value for cancer type id
   * @return
   */
  public String getCancerTypeParam (){
    String cancerTypeParam = "t";
    return getPageURL().getQueryParam(cancerTypeParam);
  }

  /**
   * Returns query param value for cancer type keyword
   * @return
   */
  public String getKeyWordParam (){
    String keyWordParam = "q";
    return getPageURL().getQueryParam(keyWordParam);
  }

  /**
   * Returns query param value for trial id keyword
   * @return
   */
  public String getTrialIDParam (){
    String trialIDParam = "tid";
    return getPageURL().getQueryParam(trialIDParam);
  }

  /**
   * Returns query param value for trial investigators
   * @return
   */
  public String getTrialInvestigatorsParam (){
    String trialInvestigatorsParam = "in";
    return getPageURL().getQueryParam(trialInvestigatorsParam);
  }

  /**
   * Returns query param value for lead organization
   * @return
   */
  public String getLeadorganizationParam (){
    String leadOrganizationParam = "lo";
    return getPageURL().getQueryParam(leadOrganizationParam);
  }

  /**
   * Retrieves number of all search results
   * @return
   */
  public int getResultListCount (){
    return 0;
  }

  /**
   * Getter for No Search Results page message
   *
   * @return
   */
  public String getNoResultMsg() {
    return searchmsg.getText();
  }
}
