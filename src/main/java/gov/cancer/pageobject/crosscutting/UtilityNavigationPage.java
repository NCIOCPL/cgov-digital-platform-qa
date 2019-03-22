package gov.cancer.pageobject.crosscutting;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import gov.cancer.pageobject.PageObjectBase;
import gov.cancer.pageobject.helper.UtilityNavigation;

/**
 * Pseudo page object representing any page in the system. The Section Navigation class is used solely for verifying
 * attributes of a page's Section Navigation
 */
public class UtilityNavigationPage extends PageObjectBase {
	
	WebElement UtilityNavigationSection;

	@FindBy(how = How.ID, using = "nvcgSlUtilityBar")
	WebElement utilityNavigation;

  /**
   * Constructor
   *
   * @param path server-relative path of the page to load.
   */
	/*
	 * public UtilityNavigationPage(String path) { super(path);
	 }*/

  
  
  public UtilityNavigationPage(String path) {
	    super(path);

	    // Get the actual section, if it's present.
	    List<WebElement> findList = getBrowser().findElements(By.cssSelector("#nvcgSlUtilityBar"));
	    if(findList.size() > 0)
	    	UtilityNavigationSection = findList.get(0);
	    else
	    	UtilityNavigationSection = null;

	  }    

  public boolean hasRelatedResources() {

	    return UtilityNavigationSection != null &&
	    		UtilityNavigationSection.isDisplayed();

	  }
  	/**
	 * Reports whether the Section Navigation is visible.
	 *
	 * @return True if the Section Navigation is visible.
	 */
	public boolean isUtilityNavigationVisible() {
		return utilityNavigation.isDisplayed();
  }

	
	/**
	   * Find all of the related resource objects on the page.
	   */
	  public List<UtilityNavigation> getutilityNavigation() {

	    List<UtilityNavigation> links = new ArrayList<UtilityNavigation>();
	    // Get the list of links.
	    List<WebElement> rawLinks = getBrowser().findElements(By.cssSelector("#nvcgSlUtilityBar a"));
	    for (WebElement link : rawLinks) {
	      links.add(new UtilityNavigation(link));
	    }

	    return links;
	  }


}
