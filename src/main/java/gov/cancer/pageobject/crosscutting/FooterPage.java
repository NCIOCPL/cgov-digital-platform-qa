package gov.cancer.pageobject.crosscutting;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import gov.cancer.pageobject.PageObjectBase;

/**
 * Pseudo page object representing any page in the system. The Section Navigation class is used solely for verifying
 * attributes of a page's Section Navigation
 */
public class FooterPage extends PageObjectBase {

	/*************** Page WebElements **********************/
	@FindBy(how = How.ID, using = "nvcgSlFooter")
	WebElement pageFooter;
	@FindBy(how = How.CSS, using = "a.back-to-top")
	WebElement backToTop;

	
	public WebElement getBackToTop() {
		return backToTop;
	}
	
	
  /**
   * Constructor
   *
   * @param path server-relative path of the page to load.
   */
  public FooterPage(String path) {
    super(path);
  }

  	/**
	 * Reports whether the Section Navigation is visible.
	 *
	 * @return True if the Section Navigation is visible.
	 */
	public boolean isPageFooterVisible() {
		return pageFooter.isDisplayed();
  }
	public boolean isbackToTopVisible() {
		return backToTop.isDisplayed();
  }

//ggughuyj7y8fjt7fj7r6f65ft7fgjyujg7
}
