package gov.cancer.pageobject.cts.components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.Component;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents list of all results from SearchResultPage
 */
public class ResultListSection extends Component {
  // list of  results items objects
  private List<ResultItem> resultItemList = new ArrayList<>();

  private static final String RESULT_ITEM_LOCATOR = ":scope >div";

  /**
   * Constructor
   * Initializes the list of results items
   * @param element
   */
  public ResultListSection (WebElement element){
    super(element);
    List <WebElement> resItemsWebElement = ElementHelper.findElements(element, RESULT_ITEM_LOCATOR);
    for (WebElement we : resItemsWebElement){
      resultItemList.add(new ResultItem(we));
    }
  }

  /**
   * Getter for list of results items
   * @return
   */
  public List <ResultItem> getAllResultsItems(){
    return resultItemList;
  }
}
