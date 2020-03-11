package gov.cancer.pageobject.cts;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.NavigationResult;
import gov.cancer.pageobject.helper.Link;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a page to which browser is navigated to after clicking Print Selected trials button
 * on Trial Results Page
 */
public class PrintTrialNavigationResult extends NavigationResult {

  //List of all Check For Updates links
  private List<Link> checkForUpdates = new ArrayList<Link>();
  // List of all three action links (Print, E-mail, New Search)
  //email results link object
  private Link emailResults;
  //New Search link object
  private Link newSearch;
  //Print option
  private WebElement printAction;


  //Locators
  private static final String EMAIL_LOCATOR = "#bodyHeading a[id$='EmailResults']";
  private final static String NEW_SEARCH_LOCATOR = "#bodyHeading a#newSearch";
  private static final String PRINT_LOCATOR = "#bodyHeading a#printPage";
  private static final String CHECK_FOR_UPDATES_LOCATOR = ".trialContainer>a";

  /**
   * Constructor initializes all major Link elements present on Page
   * @param driver current instance of the driver
   */
  public PrintTrialNavigationResult(WebDriver driver) {
    super(driver);
    for (WebElement we : ElementHelper.findElements(driver, CHECK_FOR_UPDATES_LOCATOR)) {
      checkForUpdates.add(new Link(we));
    }
    emailResults = new Link(ElementHelper.findElement(driver, EMAIL_LOCATOR));
    newSearch = new Link(ElementHelper.findElement(driver,NEW_SEARCH_LOCATOR));
    printAction = ElementHelper.findElement(driver,PRINT_LOCATOR);
  }

  /**
   * 'Check for Updates' link contains a trial URL, therefore the number of rendered links
   * plus it's contents will provide a reasonable verification of print modal functionality
   * @return list of all links present
   */
  public List<Link> getCheckForUpdatesLinks() {
    return checkForUpdates;
  }

  /**
   * Getter for Email link object
   * @return email action Link
   */
  public Link getEmailActionLink(){
    return emailResults;
  }

  /**
   * Getter for New Search Link object
   * @return Link
   */
  public Link getNewSearchLink(){
    return newSearch;
  }

  /**
   * Print Action link contains a JavaScript statement to call a print pop-up
   * That statement is stored in href, however it is not a valid format to create
   * a proper Link object (Link is creating a URL of href content on the background)
   * For verification purposes we only retrieve the String JavaScript command
   * @return
   */
  public String getPrintActionLink(){
    return printAction.getAttribute("href");
  }



}
