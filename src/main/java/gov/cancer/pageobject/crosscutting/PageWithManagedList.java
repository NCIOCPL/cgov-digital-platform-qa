package gov.cancer.pageobject.crosscutting;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;
import gov.cancer.pageobject.section.ManagedListItem;;

/**
 * Pseudo page object representing any page in the system. The ManagedList class
 * is used solely for verifying attributes of a page's ManagedList section.
 */
public class PageWithManagedList extends PageObjectBase {
	// The overall section.
	WebElement managedListSection = null;
	private List<ManagedListItem> managedListAllItems = new ArrayList<>();
	/********* ManagedList SELECTORS ***********************/
	final static String MANAGEDLISTSECTION_SELECTOR = "[class='managed list']";
	final static String LISTITEM_SELECTOR = ":scope ul li";

	/**
	 * Constructor
	 *
	 * @param path server-relative path of the page to load.
	 */
	public PageWithManagedList(String path) {
		 super(path);
		 this.managedListSection = ElementHelper.findElement(getBrowser(), MANAGEDLISTSECTION_SELECTOR);
		 if(managedListSection!=null) {
		     List<WebElement> elements = ElementHelper.findElements(managedListSection, LISTITEM_SELECTOR);
		     for (WebElement el : elements) {
		       ManagedListItem mnglist = new ManagedListItem(el);
		       managedListAllItems.add(mnglist);
		     }
		   }
		}

	/**
	 * Reports whether the managed list section was found.
	 *
	 * @return True if it was, false otherwise.
	 */
	public boolean isSectionPresent() {
		if (managedListSection != null)
			return managedListSection.isDisplayed();
		else
			return false;
	}

	/**
	 * Find all of ManagedLists objects on the page.
	 *
	 * @return A List of zero or more ManagedLists objects.
	 */
	public List<ManagedListItem> getListOfItems() {
		return managedListAllItems;
	}
}
