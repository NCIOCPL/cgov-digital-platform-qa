
package gov.cancer.pageobject.leftnavigation;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import gov.cancer.framework.ElementHelper;

/**
 * Represents a single node in the data structure representing a
 * page's left navigation structure.  The data structure includes a
 * list of NavItem objects containing its child items.
 */
public class NavItem {

  // The HTML markup making up this navigation item.
  private WebElement element;

  // The DIV tag containing this item's label.
  private WebElement textElement;

  // This item's child elements. If this item has no children,
  // the list has a size of zero.
  private List<NavItem> children = new ArrayList<NavItem>();
  /**
   * Constructor.
   * @param element The WebElement containing the HTML markup making up
   * the navigation item.
   */
  public NavItem(WebElement element) {

    this.element = element;

    // Find the element containing the item's label.
    this.textElement = ElementHelper.findElement(element, ":scope > div");
    // FInd the sub menu field
    // Only check for child items if the CSS says they should be there. This allows
    // to avoid waiting for Selenium's Implicit Wait to expire for each leaf node.
    // (In a scenario with 12 leaf nodes and a 5 second Implicit Wait, that would add up
    // to an extra minute for each test.)
    boolean hasChildren = false;
    String[] classList = ElementHelper.getClassList(this.element);
    for (String classname : classList) {
      if (classname.equals("has-children"))
        hasChildren = true;
    }

    if (hasChildren){
      // Find all the immediate child items and recursively build a tree.
      List<WebElement> subElements = ElementHelper.findElements(element, ":scope > ul > li");
      for (WebElement we : subElements) {
        NavItem child = new NavItem(we);
        children.add(child);
      }
    }

  }

  /**
   * Retrieves a boolean value reporting whether the element is
   * currently
   * @return
   */
  public boolean isVisible() {
    return this.element.isDisplayed();
  }

  /**
   * Get the Navigation item's label.
   *
   * @return String containing the navigation label.
   */
  public String getLabel() {
    return textElement.getText();
  }

  /**
   * Report whether this item has any child items.
   *
   * @return True if the number of children is one or greater. False otherwsie.
   */
  public boolean hasChildren() {
    return children.size() > 0;
  }

  public List<NavItem> getChildren(){return children;}

  /**
   * Check whether the curent instance is the selected navigation item.
   * @return True if this is the currently selected navigation item,
   * False otherwise.
   */
  public boolean isCurrent() {

    String[] classList = ElementHelper.getClassList(textElement);
    for (String classname : classList) {
      if(classname.equals("current-page"))
      return true;
    }

    return false;
  }

  /**
   * Recursively walks the tree of NavItem objects until it finds the element
   * representing the currently selected element in the page's navigation
   * tree.
   * @return A NavItem object representing the currently selected element,
   * or null if it wasn't found.
   */
  public NavItem getCurrentNavItem() {

    // We don't know the current element.
    NavItem current = null;

    // Is this item the one marked current?
    if (this.isCurrent()) {
      current = this;
    }
    else {

      // Ask each child item to find the one marked current.
      for (NavItem item : children) {
        current = item.getCurrentNavItem();

        // If we found it, exit the loop.
        if (current != null)
          break;
      }
    }

    return current;
  }

  /**
   * Recursively walks the tree of NavItem objects until it finds the visible
   * element with the targeted label. Stops at the first matching element.
   *
   * Due to limitations of Selenium, only <b>visible</b> elements may be
   * retrieved. See https://w3c.github.io/webdriver/#get-element-text
   * @param target The label to find.
   * @return A NavItem object with a label matching the one specified. Returns
   *         null if no match is found.
   */
  public NavItem findItemByLabel(String target) {

    // We haven't found it.
    NavItem found = null;

    if (this.getLabel().equals(target)){
      found = this;
    }
    else {

      // Ask each child to find an element with the target label.
      for (NavItem item : children) {
        found = item.findItemByLabel(target);

        // If we found it, exit the loop.
        if (found != null)
          break;
      }
    }

    return found;
  }

  /**
   * If a toggle button exists, this method will retrieve aria-expanded attribute of an element (true or false)
   * @return String value of attribute further to be parsed into boolean value
   */
  public String getToggleValue () {
      WebElement elementT = ElementHelper.findElement(textElement, ":scope >button.toggle");
      if (elementT != null) {
        return elementT.getAttribute("aria-expanded");
    }
    return null;
  }

  public String getTextField() {
    WebElement elementFild = ElementHelper.findElement(element, ":scope >ul>li");
    if (elementFild != null) {
      return elementFild.getText();
    } else return null;
  }
}

