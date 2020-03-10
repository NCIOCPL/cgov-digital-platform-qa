package gov.cancer.tests.crosscutting;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.MegaMenuPage;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;
import org.apache.http.NameValuePair;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Mega Menu is tested in Desktop, Tablet and Mobile modes.
 * Tablet and Mobile have identical behavior, therefore Tablet tests are reusing Mobile test data and helper classes
 */

public class MegaMenu_Test extends TestObjectBase {

  /*****************************************  Desktop mode *********************************/

  /**
   * This test verifies visibility of Mega Menu field.
   * The rest of tests depend on this method to prevent executing further tests when the menu
   * is not present at all.
   * @param path
   */
  @Test (dataProvider = "getPagePaths")
  public void verifyMegaMenuVisible (String path, int minTextLength, int maxTextLength){
    TestRunner.runDesktop(MegaMenuPage.class, path, (MegaMenuPage page) -> {
  //verify that the mega menu is displayed
      Assert.assertTrue(page.isMenuVisible());
    });
  }

  /**
   * This test verifies the number of top-level menu items on Desktop breakpoint
   */
  @Test (dataProvider = "getPageTitles", dependsOnMethods = {"verifyMegaMenuVisible"})
  public void verifyTopLevelMenuItemNumber (String path, List<String> title){
    TestRunner.runDesktop(MegaMenuPage.class, path, (MegaMenuPage page) -> {
      //verify the main menu size (6 categories)
      Assert.assertEquals(page.numberOfMenuTitles(), title.size() , "main titles number matches");
    });
  }
  /**
   * This test verifies the main categories of MegaMenu
   */
  @Test  (dataProvider = "getPageTitles", dependsOnMethods = {"verifyMegaMenuVisible"})
  public void verifyMenuTitle(String path, List<String> title) {
    TestRunner.runDesktop(MegaMenuPage.class, path, (MegaMenuPage page) -> {

      for (int i=0; i<title.size(); i++){
      //verify the titles are matching expected data
        Assert.assertEquals(page.getMenuTitleText(i),title.get(i),"titles match");}
    });
  }

  /**
   * This test is verifying that the length of the dropdown sub menus text is within reasonable range
   * @param path
   * @param boundaries lower and highest range of acceptable text length
   * @param maxIndex En =6; Es = 5 (number of sub menus)
   */
  @Test (dataProvider = "getSubMenuTextRange",dependsOnMethods = {"verifyMegaMenuVisible"})
  public void verifyTextLengthPerSubMenu (String path, List<int[]> boundaries, int maxIndex){
    TestRunner.runDesktop(MegaMenuPage.class, path, (MegaMenuPage page) -> {
      for (int i=0; i<maxIndex; i++) {
        //hover over the sub menu on the position 'i' (from 0 to 4(es) or 5(en))
        page.hoverOverMenuTitle(i);
        //retrieve the text length
        int textLength = page.getSubMenuFieldText(i).length();
        //verify that the text retrieved from submenu is within reasonable range
        Assert.assertTrue(
          textLength>=boundaries.get(i)[0] && textLength <=boundaries.get(i)[1]
          ,"SubMenu at index: " + i  + " text length is not within reasonable range");
      }

    });
  }

  /*****************************************  Tablet mode *********************************/


  /**
   * This test verifies visibility of Mega Menu field in Tablet screen breakpoint.
   * The rest of mobile tests depend on this method and will be ignored if the visibility test fails.
   * @param path
   */
  @Test (dataProvider = "getPagePaths")
  public void verifyTabletMegaMenuVisible (String path, int minTextLength, int maxTextLength){
    TestRunner.runTablet(MegaMenuPage.class, path, (MegaMenuPage page) -> {
      //verify that the mega menu is displayed
      Assert.assertTrue(page.isMobileMenuVisible());
    });
  }

  /**
   * This test verifies the number of top-level menu items on Tablet
   */
  @Test (dataProvider = "getPagePathMobile", dependsOnMethods = {"verifyTabletMegaMenuVisible"})
  public void verifyTopLevelMenuItemNumberTablet (String path, List<String> title){
    TestRunner.runTablet(MegaMenuPage.class, path, (MegaMenuPage page) -> {
      //verify the main menu size (6 categories)
      Assert.assertEquals(page.numberOfMenuTitles(),title.size(), "main titles number matches");
    });
  }
  /**
   * this test verifies the main categories of MegaMenu in English on Tablet. If the number of them matches, it verifies each
   * title to be contained in the expected list of titles
   */
  @Test (dataProvider = "getPagePathMobile" , dependsOnMethods = {"verifyTabletMegaMenuVisible"})
  public void verifyTabletMenuTitle(String path,List<String> title) {

    TestRunner.runTablet(MegaMenuPage.class, path, (MegaMenuPage page) -> {
      //click on the menu button in mobile view to retrieve hidden menu
       page.clickOnMobileMenu();
      for (int i=0; i<title.size(); i++){
        //verify the titles are matching expected data
        Assert.assertEquals(page.getMenuTitleText(i),title.get(i) ,"titles match");
        }
    });
  }

  /**
   * This test is verifying that the length of the all categories in sub menu combined is within range
   * @param path
   * @param minTextLength lower range of acceptable text length
   * @param maxTextLength higher range of acceptable text length
   */
  @Test (dataProvider = "getPagePaths", dependsOnMethods = {"verifyTabletMegaMenuVisible"})
  public void verifyTabletTextLength (String path, int minTextLength, int maxTextLength){

    TestRunner.runTablet(MegaMenuPage.class, path, (MegaMenuPage page) -> {
       page.clickOnMobileMenu();
      //verify that submenu text is not exceeding the range
      Assert.assertTrue(page.getMobileSubMenuTextLength()>=minTextLength && page.getMobileSubMenuTextLength()<=maxTextLength);
    });
  }

  /*****************************************  Mobile mode *********************************/


  /**
   * This test verifies visibility of Mega Menu field in Mobile screen breakpoint.
   * The rest of mobile tests depend on this method and will be ignored if the visibility test fails.
   * @param path
   */
  @Test (dataProvider = "getPagePaths")
  public void verifyMobileMegaMenuVisible (String path, int minTextLength, int maxTextLength){
    TestRunner.runMobile(MegaMenuPage.class, path, (MegaMenuPage page) -> {
      //verify that the mega menu is displayed
      Assert.assertTrue(page.isMobileMenuVisible());
    });
  }

  /**
   * This test verifies the number of top-level menu items on Mobile
   */
  @Test (dataProvider = "getPagePathMobile", dependsOnMethods = {"verifyMobileMegaMenuVisible"})
  public void verifyTopLevelMenuItemNumberMobile (String path, List<String> title){
    TestRunner.runMobile(MegaMenuPage.class, path, (MegaMenuPage page) -> {
      //verify the main menu size (6 categories)
      Assert.assertEquals(page.numberOfMenuTitles(), title.size(), "main titles number matches");
    });
  }
  /**
   * this test verifies the main categories of MegaMenu in English on mobile. If the number of them matches, it verifies each
   * title to be contained in the expected list of titles
   */
  @Test (dataProvider = "getPagePathMobile" , dependsOnMethods = {"verifyMobileMegaMenuVisible"})
  public void verifyMobileMenuTitle(String path, List<String> title) {

    TestRunner.runMobile(MegaMenuPage.class, path, (MegaMenuPage page) -> {
      //click on the menu button in mobile view to retrieve hidden menu
      page.clickOnMobileMenu();
      for (int i=0; i<title.size(); i++){
        //verify the titles are matching expected data
        Assert.assertEquals(page.getMenuTitleText(i), title.get(i),"titles match");
      }
    });
  }

  /**
   * This test is verifying that the length of the all categories in sub menu combined is within range
   * @param path
   * @param minTextLength lower range of acceptable text length
   * @param maxTextLength higher range of acceptable text length
   */
  @Test (dataProvider = "getPagePaths", dependsOnMethods = {"verifyMobileMegaMenuVisible"})
  public void verifyMobileTextLength (String path, int minTextLength, int maxTextLength){

    TestRunner.runMobile(MegaMenuPage.class, path, (MegaMenuPage page) -> {
      page.clickOnMobileMenu();
      //verify that submenu text is not exceeding the range
      Assert.assertTrue(page.getMobileSubMenuTextLength()>=minTextLength && page.getMobileSubMenuTextLength()<=maxTextLength);
    });
  }

  /**
   * Data provider retrieves path to pages and expected length of the text on each menu category field
   * @return
   */
  @DataProvider(name = "getPagePaths")
  public Iterator<Object[]> getPagePaths() {
    String[] columns = { "path", "minTextLength", "maxTextLength" };
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("mega-menu-data.xlsx"),
      "pages_with_menu", columns);
    List<Object[]> converted = new ArrayList<Object[]>();

    while (values.hasNext()) {
      Object[] item = values.next();
      String minRaw = (String) item[1];
     int expectedMin = Integer.parseInt(minRaw);
      String maxRaw = (String) item[2];
      int expectedMax = Integer.parseInt(maxRaw);
      converted.add(new Object[] { item[0], expectedMin, expectedMax });
    }
    return converted.iterator();
  }

  /**
   * Data provider retrieves path to pages as well as titles that are stored as one string, then return titles as List of
   * Strings for verification purposes.
   * @return
   */
  @DataProvider(name = "getPageTitles")
  public Iterator<Object[]> getPageTitles() {
    String[] columns = { "path", "title" };
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("mega-menu-data.xlsx"),
      "pages_with_menu", columns);
    List<Object[]> converted = new ArrayList<Object[]>();

    while (values.hasNext()) {
      Object[] item = values.next();
      String raw = (String) item[1];
      List <String> expected = Arrays.asList(raw.split(","));
      converted.add(new Object[] { item[0], expected });
    }
    return converted.iterator();
  }


  /**
   * Data provider retrieves path to pages as well as titles that are stored as one string, then return titles as List of
   * Strings for verification purposes.
   * @return
   */
  @DataProvider(name = "getPagePathMobile")
  public Iterator<Object[]> getPagePathMobile() {
    String[] columns = { "path", "title" };
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("mega-menu-data.xlsx"),
      "mega_menu_mobile", columns);
    List<Object[]> converted = new ArrayList<Object[]>();

    while (values.hasNext()) {
      Object[] item = values.next();
      String raw = (String) item[1];
      List <String> expected = Arrays.asList(raw.split(","));
      converted.add(new Object[] { item[0], expected });
    }
    return converted.iterator();
  }

  /**
   * Data provider retrieves path to pages as well as boundaries of min and max text length and the maximum index of submenu per language -
   * for EN it's 6 sub menus, ES has 5
   * @return
   */
  @DataProvider(name = "getSubMenuTextRange")
  public Iterator<Object[]> getSubMenuTextRange() {
    String[] columns = { "path", "boundary","index" };
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("mega-menu-data.xlsx"),
      "sub_menu", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      List <int[]> ranges = new ArrayList<int[]>();
      String rawRange = (String) item[1];
      int maxIndex  = Integer.parseInt((String) item[2]);
      List <String> pairs = Arrays.asList(rawRange.split(","));
      for (String pair: pairs) {
        String [] convertPair = pair.split(":");
        ranges.add(new int[]{Integer.parseInt(convertPair[0]),Integer.parseInt(convertPair[1])});
      }
      converted.add(new Object[] { item[0], ranges, maxIndex });
    }
    return converted.iterator();
  }
}



