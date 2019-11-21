package gov.cancer.pageobject.components;

import gov.cancer.framework.ElementHelper;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents pager object
 */
public class Pager extends Component {

  //Collection of all pager buttons
  private List<Button> pagerButtons = new ArrayList<>();

  // Previous arrow button
  private Button previousButton;
  // Next arrow button
  private Button nextButton;
  // main pager element
  private WebElement section;


  private final static String PAGER_BUTTON_LOCATOR = ":scope >div";
  private final static String ACTIVE_PAGE_LOCATOR = ":scope div[class$='--active']";

  /**
   * Constructor
   *
   * @param element
   */
  public Pager(WebElement element) {
    super(element);
    section =element;
    List <WebElement> allButtons = ElementHelper.findElements(element, PAGER_BUTTON_LOCATOR);
   for (WebElement we : allButtons){
     pagerButtons.add(new Button(we));
     if(we.getText().equals("< Previous")){
       previousButton = new Button(we);
     }else if(we.getText().equals("Next >")){
       nextButton = new Button(we);
     }
   }

  }

  /**
   * Method returns number of pages displayed
   * @return
   */
  public int getNumberOfPages() {
    return pagerButtons.size();
  }

  /**
   * Method returns button according to the index it is stored under in the list
   */
  public Button getPageButton(int index) {
    return pagerButtons.get(index);
  }

  /**
   * Method return 'previous' button
   * @return
   */
  public Button getPreviousButton (){
    return previousButton;
  }
  /**
   * Method return 'next' button
   * @return
   */
  public Button getNextButton(){
    return nextButton;
  }
  /**
   * Method returns the active(selected) page button
   */
  public Button getCurrentButton() {
    return new Button(ElementHelper.findElement(section, ACTIVE_PAGE_LOCATOR));
  }
}
