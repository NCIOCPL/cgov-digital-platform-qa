package gov.cancer.tests.section;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.PageWithRecommendedContent;
import gov.cancer.pageobject.section.FeatureCard;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

public class RecommendedContent_Test extends TestObjectBase {

  /**
   * This method is checking if the Recommended Content Card exists on the pages
   *
   * @param path
   *          Path of the page to check.
   * @param sectionTitle
   *          Title of card to check.
   *
   */
  @Test(dataProvider = "getPageRecommendedContentCard")
  public void verifyRecommendedContentSectionVisible(String path, String sectionTitle) {

    TestRunner.run(PageWithRecommendedContent.class, path, (PageWithRecommendedContent page) -> {

      Assert.assertTrue(page.hasRecomSection(), "Recommended Content is not visible.");

    });

  }

  /**
   * This method is checking if the Recommended Content section title is correct
   *
   * @param path
   *          Path of the page to check.
   * @param sectionTitle
   *          Title of card to check.
   *
   */
  @Test(dataProvider = "getPageRecommendedContentCard")
  public void verifyRecommendedContentSectionTitleIsCorrect(String path, String sectionTitle) {

    TestRunner.run(PageWithRecommendedContent.class, path, (PageWithRecommendedContent page) -> {

      Assert.assertEquals(page.getSectionTitle().trim(), sectionTitle, "Recommended Content title is wrong.");

    });

  }

  /**
   * This method is checking if the Card is displayed and the count of Cards on
   * the pages is correct
   *
   * @param path
   *          Path of the page to check.
   * @param cardCount
   *          number of feature cards on the page.
   * @param cardIndex
   *          serial number of feature card.
   *
   */
  @Test(dataProvider = "getPageRecommendedContentCardDetails")
  public void verifyRecommendedContentCardCount(String path, int cardCount, int cardIndex) {

    TestRunner.run(PageWithRecommendedContent.class, path, (PageWithRecommendedContent page) -> {

      Assert.assertEquals(page.getRecomCards().size(), cardCount, "Count of Feature Cards on the pages is not correct");

    });

  }

  /**
   * This method is checking if the correct values of Card Title is displayed on
   * Recommended Content card on the pages
   *
   * @param path
   *          Path of the page to check.
   * @param cardIndex
   *          serial number of card.
   * @param cardtitle
   *          Title of card to check.
   *
   */
  @Test(dataProvider = "getPageRecommendedContentCardTitle")
  public void verifyRecommendCardTitle(String path, int cardIndex, String cardTitle) {

    TestRunner.run(PageWithRecommendedContent.class, path, (PageWithRecommendedContent page) -> {

      // Retrieves a list of cards on the page.
      List<FeatureCard> cardList1 = page.getRecomCards();
      FeatureCard card = cardList1.get(cardIndex);

      Assert.assertEquals(card.getTitleText(), cardTitle, "Card Title is not displayed.");

    });

  }

  /**
   * This method is checking if the correct values of Image source, Image Alt
   * text is displayed on card on the pages with recommended Content
   *
   * @param path
   *          Path of the page to check.
   * @param cardIndex
   *          serial number of feature card.
   * @param imgSrc
   *          Source of image of feature card to check.
   * @param imgFile
   *          Filename of image of feature card to check.
   * @param imgAlt
   *          Alt text of image of feature card to check.
   */
  @Test(dataProvider = "getPageRecommendedContentCardImage")
  public void verifyRecommendedCardImage(String path, int cardIndex, String imgSrc, String imgFile, String imgAlt) {

    TestRunner.run(PageWithRecommendedContent.class, path, (PageWithRecommendedContent page) -> {
      // Retrieves a list of cards on the page.
      List<FeatureCard> cardList = page.getRecomCards();
      FeatureCard card = cardList.get(cardIndex);

      // Use StartsWith method and endswith to check the image path till the
      // date
      // folder(e.g. 2019-11) location
      Assert.assertTrue(card.getImagePath().startsWith(imgSrc), "Card Image Source is not correct.");
      Assert.assertTrue(card.getImagePath().endsWith(imgFile), "Card Image file name is not correct.");
      Assert.assertEquals(imgAlt, card.getImage().getAltText(), "Card Image Alt Text is not correct.");

    });

  }

  /**
   * This method is checking if the correct values of card link is displayed on
   * internal card on the pages with recommended Content
   *
   * @param path
   *          Path of the page to check.
   * @param intcardIndex
   *          serial number of internal feature card.
   * @param cardLink
   *          URL of link of feature card to check.
   * @param extCardIndex
   *          serial number of external feature card.
   */
  @Test(dataProvider = "getPageRecommendedContentInternalCardLinks")
  public void verifyInternalRecommendedCardLinks(String path, int intcardIndex, String cardLink, String extCardIndex) {

    TestRunner.run(PageWithRecommendedContent.class, path, (PageWithRecommendedContent page) -> {
      // Retrieves a list of feature cards on the page.
      List<FeatureCard> cardList2 = page.getIntRecomCards();

      FeatureCard card2 = cardList2.get(intcardIndex);
      Assert.assertEquals(card2.getLink().getUrl().getPath(), cardLink, "Card Link is not correct.");

    });

  }

  /**
   * This method is checking if the correct values of card link and exit
   * disclaimer policy link is displayed on Feature card on the pages with
   * recommended Content
   *
   * @param path
   *          Path of the page to check.
   * @param externalCardCount
   *          number of external feature cards on the page.
   * @param extCardIndex
   *          serial number of external feature card.
   * @param extcardLink
   *          URL of link of feature card to check.
   * @param exitNotificationLink
   *          URL of exit disclaimer policy link on external feature card to
   *          check.
   */
  // Excluding this test as we dont have test data for this on dev-ac, created a
  // YAML content request ticket #159
   @Test(dataProvider = "getPageRecommendedContentExternalCardLinks", enabled = false)
  public void verifyExternalRecommendedCardLinks(String path, int externalCardCount, int extCardIndex,
      String extcardLink, String exitNotificationLink) {

    TestRunner.run(PageWithRecommendedContent.class, path, (PageWithRecommendedContent page) -> {
      // Retrieves a list of feature cards on the page.
      List<FeatureCard> cardList2 = page.getExternalRecomCards();
      int extcard = cardList2.size();

      // Execute following assertions for external Feature cards.
      Assert.assertEquals(cardList2.get(extCardIndex).getLink().getUrl().toString(), extcardLink,
          "Card Link is not correct.");
      Assert.assertEquals(extcard, externalCardCount, "Count of external card is not same");
      Assert.assertEquals(cardList2.get(extCardIndex).getExternalLinkNotification().getUrl().getPath(),
          exitNotificationLink, "Exit disclaimer link is not correct");

    });

  }

  /*******************************************
   * DATA PROVIDER
   *******************************************/

  /**
   * Retrieves a list of paths to pages which are expected to have recommended
   * content
   *
   * @return An iterable list of 5 element arrays, each containing a path,
   *         cardNumber, card Title, image Src, CardLink.
   */
  @DataProvider(name = "getPageRecommendedContentCardDetails")
  public Iterator<Object[]> getPageRecommendedContentCardDetails() {
    String[] columns = { "path", "cardCount", "cardNumber" };

    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("recommendedcontent-data.xlsx"),
        "pages_with_recommended_content", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String cardno = (String) item[2];
      String cardCount = (String) item[1];
      int cardcount = Integer.parseInt(cardCount);
      int expectedcardno = Integer.parseInt(cardno);
      converted.add(new Object[] { item[0], cardcount, expectedcardno });
    }
    return converted.iterator();

  }

  /**
   * Retrieves a list of paths to pages which are expected to have recommended
   * content
   *
   * @return An iterable list of 5 element arrays, each containing a path,
   *         cardNumber, card Title, image Src, CardLink.
   */
  @DataProvider(name = "getPageRecommendedContentCardTitle")
  public Iterator<Object[]> getPageRecommendedContentCardTitle() {

    String[] columns = { "path", "cardNumber", "cardTitle" };
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("recommendedcontent-data.xlsx"),
        "pages_with_recommended_content", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String cardno = (String) item[1];
      int expectedcardno = Integer.parseInt(cardno);
      converted.add(new Object[] { item[0], expectedcardno, item[2] });
    }
    return converted.iterator();

  }

  /**
   * Retrieves a list of paths to pages which are expected to have recommended
   * content
   *
   * @return An iterable list of 5 element arrays, each containing a path,
   *         cardNumber, card Title, image Src, CardLink.
   */
  @DataProvider(name = "getPageRecommendedContentCardImage")
  public Iterator<Object[]> getPageRecommendedContentCardImage() {

    String[] columns = { "path", "cardNumber", "cardImageSrc", "cardImageFileName", "cardImageAltText" };
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("recommendedcontent-data.xlsx"),
        "pages_with_recommended_content", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String cardno = (String) item[1];
      int expectedcardno = Integer.parseInt(cardno);

      converted.add(new Object[] { item[0], expectedcardno, item[2], item[3], item[4] });
    }
    return converted.iterator();

  }

  /**
   * Retrieves a list of paths to pages which are expected to have feature cards
   *
   * @return An iterable list of 4 element arrays, each containing a path,
   *         internal card index, feature card link, ExitNotificationLink.
   */
  @DataProvider(name = "getPageRecommendedContentInternalCardLinks")
  public Iterator<Object[]> getPageRecommendedContentInternalCardLinks() {
    String[] columns = { "path", "internalCardIndex", "cardLink", "externalCardIndex" };
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("recommendedcontent-data.xlsx"),
        "pages_with_recommended_content", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String intcardno = (String) item[1];
      String externalCardIndex = (String) item[3];
      if (externalCardIndex.isEmpty() || (!intcardno.isEmpty())) {
        int expectedcardno = Integer.parseInt(intcardno);
        converted.add(new Object[] { item[0], expectedcardno, item[2], externalCardIndex });
      }
    }
    return converted.iterator();

  }

  /**
   * Retrieves a list of paths to pages which are expected to have external
   * cards
   *
   * @return An iterable list of 4 element arrays, each containing a path,
   *         cardNumber, external cards count, external card index, External
   *         Card Link, ExitNotificationLink.
   */
  @DataProvider(name = "getPageRecommendedContentExternalCardLinks")
  public Iterator<Object[]> getPageRecommendedContentExternalCardLinks() {
    String[] columns = { "path", "externalCardCount", "externalCardIndex", "ExternalCardLink", "ExitNotificationLink" };
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("recommendedcontent-data.xlsx"),
        "pages_with_recommended_content", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String extcardcount = (String) item[1];
      String extcardlnk = (String) item[3];
      String extcardno = (String) item[2];
      if (!extcardcount.equals("null") || (!extcardlnk.isEmpty()) || (!extcardno.isEmpty())) {
        int expectedextcardcount = Integer.parseInt(extcardcount);
        int expectedcardno = Integer.parseInt(extcardno);
        converted.add(new Object[] { item[0], expectedextcardcount, expectedcardno, extcardlnk, item[4] });
      }
    }
    return converted.iterator();

  }

  /**
   * Retrieves a list of paths to pages which are expected to have recommended
   * content
   *
   * @return An iterable list of 5 element arrays, each containing a path,
   *         cardNumber, card Title, image Src, CardLink.
   */
  @DataProvider(name = "getPageRecommendedContentCard")
  public Iterator<Object[]> getPageRecommendedContentCard() {
    String[] columns = { "path", "sectionTitle" };
    return new ExcelDataReader(getDataFilePath("recommendedcontent-data.xlsx"), "path_to_be_tested", columns);

  }
}
