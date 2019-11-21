package gov.cancer.pageobject.cts;

import gov.cancer.framework.ElementHelper;
import gov.cancer.framework.ParsedURL;
import gov.cancer.pageobject.PageObjectBase;
import gov.cancer.pageobject.components.Button;
import gov.cancer.pageobject.components.Delighter;
import gov.cancer.pageobject.components.TextField;
import org.apache.http.NameValuePair;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class represents all common elements of all CTS pages.
 */
public class CTSPage extends PageObjectBase {
  //root element of CTS
  private WebElement rootElement;
  // Header element
  private WebElement header;
  //'HAVE A QUESTION? WE'RE HERE TO HELP' delighter
  private Delighter helpDelighter;
  // 'WHICH TRIALS ARE RIGHT FOR YOU?' delighter
  private Delighter trialCheckListDelighter;
  // 'WHAT ARE CANCER CLINICAL TRIALS?' delighter
  private Delighter whatAreClinicalTrialsDelighter;


  //children of root element
  private final static String CHILDREN = ":scope *";
  //header locator
  private final static String HEADER = ":scope h1";
  //root locator
  private final static String ROOT = "#NCI-CTS-root";

  private final static String HELP_DELIGHTER_LOCATOR = "div[class='delighter cts-livehelp']";

  private final static String TRIAL_CHECK_LIST_DELIGHTER_LOCATOR = "div[class='delighter cts-which']";

  private final static String WHAT_ARE_TRIALS_DELIGHTER_LOCATOR="div[class='delighter cts-what']";


  /**
   * Main constructor
   * Initializes Header element, Get Help and Trial CheckList delighters
   *
   * @param path
   */
  public CTSPage(String path) {
    super(path);
    this.rootElement = ElementHelper.findElement(getBrowser(), ROOT);
    header = ElementHelper.findElement(rootElement, HEADER);

    // The HTML shows two delighter element for 'Get Help'  and 'Trial Checklist" delighters on a page,
    // therefore we create a delighter object of an element that is displayed

    List<WebElement> helpDelight = ElementHelper.findElements(getBrowser(), HELP_DELIGHTER_LOCATOR);
    for (WebElement we : helpDelight) {
      if (we.isDisplayed())
        helpDelighter = new Delighter(we);
      break;
    }

    List<WebElement> trialCheckListDelight = ElementHelper.findElements(getBrowser(), TRIAL_CHECK_LIST_DELIGHTER_LOCATOR);
    for (WebElement we : trialCheckListDelight) {
      if (we.isDisplayed())
        trialCheckListDelighter = new Delighter(we);
      break;
    }

  }


  /**
   * Check if header element is displayed
   *
   * @return
   */
  public boolean isHeaderDisplayed() {
    try {
      getWait().until(ExpectedConditions.visibilityOf(header));
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }


  /**
   * navigation event that is triggered by clicking submit button.
   * MouseActionHelper is not used for clicking on element as this click will cause navigation event
   *
   * @return SearchNavigationResult object
   */
  public SearchNavigationResult clickSubmit(Button button) {
    expectUrlChange(() -> {
      button.click();
    });
    SearchNavigationResult page = new SearchNavigationResult(getBrowser());
    return page;
  }

  /**
   * navigation event that is triggered by hitting ENTER key.
   *
   * @return SearchNavigationResult object
   */
  public SearchNavigationResult hitEnterKey(TextField field) {
    expectUrlChange(() -> {
      field.hitEnter();
    });
    SearchNavigationResult page = new SearchNavigationResult(getBrowser());
    return page;
  }

  /**
   * Getter method for Get Help Delighter
   *
   * @return
   */
  public Delighter getHelpDelighter() {
    return helpDelighter;
  }

  /**
   * Getter method for Trial Checklist Delighter
   *
   * @return
   */
  public Delighter getTrialCheckListDelighter() {
    return trialCheckListDelighter;
  }

  /**
   * Getter method for What Are Cancer Clinical Trials delighter
   * It is only present on Search Forms pages, therefore is not instantiated in the constructor
   * @return
   */
  public Delighter getWhatAreClinicalTrialsDelighter() {
    whatAreClinicalTrialsDelighter = new Delighter(ElementHelper.findElement(getBrowser(), WHAT_ARE_TRIALS_DELIGHTER_LOCATOR));
    return whatAreClinicalTrialsDelighter;
  }

  /**
   * retrieve header text
   */
  public String getPageHeader(){
    return header.getText();
  }

  /**
   * Method retrieves list of current page url query parameters and value pairs
   */
  public List<NameValuePair> getCurrentUlrQueryParameters (){
    return ParsedURL.getParamArrayList(getBrowser().getCurrentUrl());
  }
}
