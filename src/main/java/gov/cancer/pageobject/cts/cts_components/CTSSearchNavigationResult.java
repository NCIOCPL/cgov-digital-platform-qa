package gov.cancer.pageobject.cts.cts_components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.framework.ParsedURL;
import gov.cancer.pageobject.components.NavigationResult;
import org.openqa.selenium.WebDriver;

public class CTSSearchNavigationResult extends NavigationResult {
  private String resultsListItem = ".results-list>div";
  ParsedURL url;
  /**
   * constructor retrieves current url and parses it
   *
   * @param driver current instance of the driver
   *
   */
  public CTSSearchNavigationResult(WebDriver driver) {
    super(driver);
    url = getParsedUrl();
  }

  public String getAgeParam (){
    String ageParam = "a";
    return url.getQueryParam(ageParam);
  }

  public String getZipParam (){
    String zipParam = "z";
    return url.getQueryParam(zipParam);
  }
  public String getCancerTypeParamParam (){
    String cancerTypeParam = "t";
    return url.getQueryParam(cancerTypeParam);
  }
  public String getKeyWordParam (){
    String keyWordParam = "q";
    return url.getQueryParam(keyWordParam);
  }
}
