package gov.cancer.pageobject.crosscutting;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;
import gov.cancer.framework.MouseActionHelper;
import gov.cancer.pageobject.helper.BlobOfText;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * Pseudo page class representing a Mega Menu field present on each page of an application
 */
public class MegaMenuPage extends PageObjectBase {

  // Element represents mainNavigation field on the desktop
  private WebElement mainNavigation;
  // Element represents mainNavigation field on the mobile
  private WebElement mainNavigationMobile;
  // Action object to perform hover over and click action on the elements
  private static MouseActionHelper menuItem;

  /**
   * Main Constructor
   *
   * @param path
   */
  public MegaMenuPage(String path) {
    super(path);
    this.mainNavigation = ElementHelper.findElement(getBrowser(), ".nav-menu");
    this.mainNavigationMobile = ElementHelper.findElement((getBrowser()), ".mobile-menu-bar");
    menuItem = new MouseActionHelper(getBrowser());
  }


  /**
   * Method to store elements containing Main Menu title in the list
   *
   * @return list of WebElements
   */
  private List<WebElement> getMenuTitles() {
    return ElementHelper.findElements(mainNavigation, ":scope >li>div>a");
  }

  /**
   * Retrieves the number of top-level menu items
   * @return number of menu items
   */
  public int numberOfMenuTitles(){return getMenuTitles().size();}
  /**
   * Method to store subMenu fields(drop down field for each of the main titles) in the list of WebElements
   *
   * @return list of WebElements
   */
  private List<WebElement> getSubMenuField() {
    return ElementHelper.findElements(mainNavigation, ":scope .sub-nav-group-subwrapper");
  }

  /**
   * Method to reflect whether the main menu is visible
   *
   * @return boolean value
   */
  public boolean isMenuVisible() {
    return mainNavigation.isDisplayed();
  }

  /**
   * Method will retrieve the title of a Main Menu elements
   *
   * @param num represent index of an object to retrieve from the list
   * @return text of Main Menu item
   */
  public String getMenuTitleText(int num) {
    List <WebElement> elements = getMenuTitles();
    return elements.get(num).getText();
  }

  /**
   * This method will retrieve the text that is contained within a drop down field of main menu
   * Using BlobOfText object
   * @param num represent index of an object to retrieve from the list
   * @return text of drop down field
   */
  public String getSubMenuFieldText(int num) {
    return new BlobOfText(getSubMenuField().get(num)).getText();
  }

  /**
   * Hover over one of the menu categories to reveal drop down field
   * @param index represent index of element stored in the menuTitles list
   */
  public void hoverOverMenuTitle(int index){ menuItem.hoverOver(getMenuTitles().get(index)); }


  /************************MOBILE BREAKPOINT**************************/

  /**
   * This method will return the length of all texts for mobile menu
   *
   * @return length of the text
   */
  public int getMobileSubMenuTextLength() {
    return new BlobOfText(mainNavigation).getText().length();
  }

  /**
   * Method to check whether mobile Menu field is visible
   */
  public boolean isMobileMenuVisible() {
    return mainNavigationMobile.isDisplayed();
  }

  /**
   * Method to click on mobile menu button
   */
  public void clickOnMobileMenu() {
    menuItem.clickOn(ElementHelper.findElement(mainNavigationMobile, ":scope button:nth-child(1)"));
  }

}
