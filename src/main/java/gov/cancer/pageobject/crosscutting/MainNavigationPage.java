package gov.cancer.pageobject.crosscutting;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import gov.cancer.pageobject.PageObjectBase;

/**
 * Pseudo page object representing any page in the system. The main Navigation class is used solely for verifying
 * attributes of a page's main Navigation
 */
public class MainNavigationPage extends PageObjectBase {


	@FindBy(how = How.ID, using = "mega-nav")
	WebElement mainNavigation;

  /**
   * Constructor
   *
   * @param path server-relative path of the page to load.
   */
  public MainNavigationPage(String path) {
    super(path);
  }

  	/**
	 * Reports whether the Section Navigation is visible.
	 *
	 * @return True if the Section Navigation is visible.
	 */
	public boolean isMainNavigationVisible() {
		return mainNavigation.isDisplayed();
  }



}
