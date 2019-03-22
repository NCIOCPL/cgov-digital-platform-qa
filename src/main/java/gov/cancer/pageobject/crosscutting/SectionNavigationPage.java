package gov.cancer.pageobject.crosscutting;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import gov.cancer.pageobject.PageObjectBase;

/**
 * Pseudo page object representing any page in the system. The Section Navigation class is used solely for verifying
 * attributes of a page's Section Navigation
 */
public class SectionNavigationPage extends PageObjectBase {


	@FindBy(how = How.ID, using = "nvcgSlSectionNav")
	WebElement sectionNavigation;

  /**
   * Constructor
   *
   * @param path server-relative path of the page to load.
   */
  public SectionNavigationPage(String path) {
    super(path);
  }

  	/**
	 * Reports whether the Section Navigation is visible.
	 *
	 * @return True if the Section Navigation is visible.
	 */
	public boolean isSectionNavigationVisible() {
		return sectionNavigation.isDisplayed();
  }



}
