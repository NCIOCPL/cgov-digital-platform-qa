package gov.cancer.pageobject.cts;


import gov.cancer.pageobject.NavigationResult;
import org.openqa.selenium.WebDriver;

/**
 * Class represents a page returned by Navigation event. Extending Navigation Result parent class
 */
public class SearchNavigationResult extends NavigationResult {

  /**
   * constructor retrieves current url and parses it
   *
   * @param driver current instance of the driver
   *
   */
  public SearchNavigationResult(WebDriver driver) {

    super(driver);
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
   * Retrieves number of all search results
   * @return
   */
  public int getResultListCount (){
    return 0;
  }

}
