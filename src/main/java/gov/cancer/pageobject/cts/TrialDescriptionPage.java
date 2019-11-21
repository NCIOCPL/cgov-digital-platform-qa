package gov.cancer.pageobject.cts;

import gov.cancer.pageobject.helper.CTS_Section;
import org.openqa.selenium.interactions.Actions;

/**
 * This class represents Trial description page of CTS module
 */
public class TrialDescriptionPage extends CTS_Section {
  // action object for pause
  Actions action;

  /**
   * Constructor
   * @param path
   */
  public TrialDescriptionPage (String path){
    super(path);
    action = new Actions(getBrowser());
  }

  /**
   * return true if element is present
   * @return
   */

  public boolean isTrialDescVisible(){
    //action object is made configurable trough Framework util (waiting for PR to get merged)
    //TODO this is a workaround, implement action util object
    action.pause(2).build().perform();
    return isHeaderDisplayed();
  }
}
