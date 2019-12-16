package gov.cancer.tests.section;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.PagewithFeatureCard;
import gov.cancer.pageobject.section.FeatureCard;

import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

public class FeatureCard_Test extends TestObjectBase {


  /**
   * This method is checking if the Feature Card is displayed and the count of Feature Cards on the pages is
   * correct
   *
   * @param path       Path of the page to check.
   * @param cardCount  number of feature cards on the page.
   * @param cardIndex serial number of feature card.
   *
   */
  @Test(dataProvider = "getPageFeatureCardInfo")
  public void verifyFeatureCardCount(String path, int cardCount, int cardIndex) {

    TestRunner.run(PagewithFeatureCard.class, path, (PagewithFeatureCard page) -> {

      Assert.assertFalse(page.getFeatureCards().isEmpty(), "Feature card is not visible.");
      Assert.assertEquals(page.getFeatureCards().size(), cardCount,"Count of Feature Cards on the pages is not correct");

    });

  }

  /**
   * This method is checking if the correct values of Card Title is displayed on Feature card on the pages with feature cards
   *
   * @param path       Path of the page to check.
   * @param cardIndex serial number of feature card.
   * @param cardTitle  Title of feature card to check.
   *
   */
  @Test(dataProvider = "getPageFeatureCardTitle")
  public void verifyFeatureCardTitle(String path, int cardIndex, String cardTitle ) {

    TestRunner.run(PagewithFeatureCard.class, path, (PagewithFeatureCard page) -> {
      // Retrieves a list of feature cards on the page.
      List<FeatureCard> cardList1 = page.getFeatureCards();
      FeatureCard card1 = cardList1.get(cardIndex);

      // For each feature card execute the following assertions.
      Assert.assertEquals(card1.getTitleText(), cardTitle, "Card Title is not displayed.");


    });

  }

  /**
   * This method is checking if the correct values of Card
   * description is displayed on Feature card on the pages with feature cards
   * @param path Path of the page to check.
   * @param cardIndex serial number of feature card.
   * @param cardDesc Description of feature card to check.
   */

  @Test(dataProvider = "getPageFeatureCardDescription")
  public void verifyFeatureCardDescription(String path, int cardIndex, String cardDesc) {

    TestRunner.run(PagewithFeatureCard.class, path, (PagewithFeatureCard page) -> {
      // Retrieves a list of feature cards on the page.
      List<FeatureCard> cardList1 = page.getFeatureCards();
      FeatureCard card1 = cardList1.get(cardIndex);

      Assert.assertEquals(cardDesc, card1.getDescriptionText(), "Card Description is not displayed.");

    });

  }

/**
   * This method is checking if the null values of Card
   * description is displayed on Feature card on the pages with feature cards with no description
   * @param path Path of the page to check.
   * @param cardIndex serial number of feature card.
   * @param cardDesc Description of feature card to check.
   */
  @Test(dataProvider = "getPageFeatureCardWithNoDescription")
  public void verifyFeatureCardWithNoDescription(String path, int cardIndex, String cardDesc) {
    TestRunner.run(PagewithFeatureCard.class, path, (PagewithFeatureCard page) -> {
      // Retrieves a list of feature cards on the page.
      List<FeatureCard> cardList1 = page.getFeatureCards();
      FeatureCard card1 = cardList1.get(cardIndex);
       Assert.assertNull(card1.getDescriptionText(), "Card Description is not displayed.");
    });
  }


  /**
   * This method is checking if the correct values of Image source, Image Alt
   * text is displayed on Feature card on the pages with feature cards
   *
   * @param path       Path of the page to check.
   * @param cardIndex serial number of feature card.
   * @param imgSrc     Source of image of feature card to check.
   * @param imgFile    Filename of image of feature card to check.
   * @param imgAlt     Alt text of image of feature card to check.
   */
  @Test(dataProvider = "getPageFeatureCardImage")
  public void verifyFeatureCardImage(String path, int cardIndex, String imgSrc, String imgFile, String imgAlt) {

    TestRunner.run(PagewithFeatureCard.class, path, (PagewithFeatureCard page) -> {
      // Retrieves a list of feature cards on the page.
      List<FeatureCard> cardList = page.getFeatureCards();
      FeatureCard card = cardList.get(cardIndex);

      // Use StartsWith method  and endswith to check the image path till the date
      // folder(e.g. 2019-11) location
      Assert.assertTrue(card.getImagePath().startsWith(imgSrc), "Card Image Source is not correct.");
      Assert.assertTrue(card.getImagePath().endsWith(imgFile), "Card Image file name is not correct.");
      Assert.assertEquals(imgAlt, card.getImage().getAltText(), "Card Image Alt Text is not correct.");

    });

  }

  /**
   * This method is checking if the correct values of card link and exit
   * disclaimer policy link is displayed on Feature card on the pages with
   * feature cards
   *
   * @param path       Path of the page to check.
   * @param cardIndex serial number of internal feature card.
   * @param cardLink   URL of link of feature card to check.
   * @param exitNotificationLink URL for exit notification
   */
  @Test(dataProvider = "getPageFeatureCardLinks")
  public void verifyInternalFeatureCardLinks(String path, int intcardIndex, String cardLink, String exitNotificationLink) {

    TestRunner.run(PagewithFeatureCard.class, path, (PagewithFeatureCard page) -> {
      // Retrieves a list of feature cards on the page.
      List<FeatureCard> cardList2 = page.getIntFeatureCards();

      FeatureCard card2 = cardList2.get(intcardIndex);
         Assert.assertEquals(card2.getLink().getUrl().getPath(),cardLink, "Card Link is not correct.");

    });

  }

  /**
   * This method is checking if the correct values of card link and exit
   * disclaimer policy link is displayed on Feature card on the pages with
   * feature cards
   *
   * @param path                 Path of the page to check.
   * @param externalCardCount    number of external feature cards on the page.
   * @param extCardIndex         serial number of external feature card.
   * @param extcardLink         URL of link of feature card to check.
   * @param exitNotificationLink URL of exit disclaimer policy link on external feature card to
   *                             check.
   */
  @Test(dataProvider = "getPageExtFeatureCardLinks")
  public void verifyExternalFeatureCardLinks(String path, int externalCardCount, int extCardIndex,
                                             String extcardLink, String exitNotificationLink) {

    TestRunner.run(PagewithFeatureCard.class, path, (PagewithFeatureCard page) -> {
      // Retrieves a list of feature cards on the page.
      List<FeatureCard> cardList2 = page.getExternalFeatureCards();
      int extcard = cardList2.size();

      // Execute following assertions for external Feature cards.
     //for (int i=0; i< cardList2.size();i++ ) {
      Assert.assertEquals(cardList2.get(extCardIndex).getLink().getUrl().toString(),extcardLink, "Card Link is not correct.");
      Assert.assertEquals(extcard, externalCardCount, "Count of external card is not same");
      Assert.assertEquals(cardList2.get(extCardIndex).getExternalLinkNotification().getUrl().getPath(),
          exitNotificationLink,
        "Exit disclaimer link is not correct");
     //}
    });

  }

  /*******************************************
   * DATA PROVIDER
   *******************************************/

  /**
   * Retrieves a list of paths to pages which are expected to have FeatureCard
   *
   * @return An iterable list of 5 element arrays, each containing a path, card count,
   * cardNumber
   */
  @DataProvider(name = "getPageFeatureCardInfo")
  public Iterator<Object[]> getPageFeatureCardInfo() {
    String[] columns = {"path", "cardCount", "cardNumber"};

    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("featurecard-data.xlsx"), "pages_feature_card",
      columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String cardno = (String) item[2];
      String cardCount = (String) item[1];
      int cardcount = Integer.parseInt(cardCount);
      int expectedcardno = Integer.parseInt(cardno);
      converted.add(new Object[]{item[0], cardcount, expectedcardno});
    }
    return converted.iterator();

  }

  /**
   * Retrieves a list of paths to pages which are expected to have FeatureCard
   *
   * @return An iterable list of 5 element arrays, each containing a path,
   * cardNumber, Feature card Title
   */
  @DataProvider(name = "getPageFeatureCardTitle")
  public Iterator<Object[]> getPageFeatureCardTitle() {
    String[] columns = {"path", "cardNumber", "Feature card Title"};

    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("featurecard-data.xlsx"), "pages_feature_card",
      columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String cardno = (String) item[1];
      int expectedcardno = Integer.parseInt(cardno);
       converted.add(new Object[]{item[0],expectedcardno, item[2]});
    }
    return converted.iterator();

  }

  /**
   * Retrieves a list of paths to pages which are expected to have Lead Image
   *
   * @return An iterable list of 5 element arrays, each containing a path,
   * cardNumber, Promo Image Src, Promo Alt text.
   */
  @DataProvider(name = "getPageFeatureCardImage")
  public Iterator<Object[]> getPageFeatureCardImage() {
    String[] columns = {"path", "cardNumber", "Promo Image Src Leading Path", "Promo Image File Name",
      "Promo Alt text"};
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("featurecard-data.xlsx"), "pages_feature_card",
      columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String cardno = (String) item[1];
      int expectedcardno = Integer.parseInt(cardno);

      converted.add(new Object[]{item[0], expectedcardno, item[2], item[3], item[4]});
    }
    return converted.iterator();

  }

  /**
  * Retrieves a list of paths to pages which are expected to have Card Description
  *
  * @return An iterable list of 5 element arrays, each containing a path,
  * cardNumber, Feature card Description.
  */
  @DataProvider(name = "getPageFeatureCardDescription")
  public Iterator<Object[]> getPageFeatureCardDescription() {
    String[] columns = {"path", "cardNumber", "Feature card Description"};
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("featurecard-data.xlsx"), "pages_feature_card",
      columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String cardDesc = (String) item[2];
      if (!cardDesc.equals("null")) {
        String cardno = (String) item[1];
        int expectedcardno = Integer.parseInt(cardno);
        converted.add(new Object[]{item[0], expectedcardno, item[2]});
      }
    }
      return converted.iterator();

    }

    /**
   * Retrieves a list of paths to pages which are expected to have no Feature card Description
   *
   * @return An iterable list of 5 element arrays, each containing a path,
   * cardNumber, Feature card Description.
   */
    @DataProvider(name = "getPageFeatureCardWithNoDescription")
    public Iterator<Object[]> getPageFeatureCardWithNoDescription() {
      String[] columns = {"path", "cardNumber", "Feature card Description"};
      Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("featurecard-data.xlsx"), "pages_feature_card",
        columns);
      List<Object[]> converted = new ArrayList<Object[]>();
      while (values.hasNext()) {
        Object[] item = values.next();
        String cardDesc = (String) item[2];
        if (cardDesc.equals("null")) {
          String cardno = (String) item[1];
          int expectedcardno = Integer.parseInt(cardno);
          converted.add(new Object[]{item[0], expectedcardno, item[2]});
        }
      }
      return converted.iterator();
    }


    /**
     * Retrieves a list of paths to pages which are expected to have feature cards
     *
     * @return An iterable list of 4 element arrays, each containing a path,
     *         internal card index, feature card link, ExitNotificationLink.
     */
    @DataProvider(name = "getPageFeatureCardLinks")
    public Iterator<Object[]> getPageFeatureCardLinks () {
      String[] columns = {"path", "internalCardIndex", "CardLink", "ExitNotificationLink"};
      Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("featurecard-data.xlsx"), "pages_feature_card",
        columns);
      List<Object[]> converted = new ArrayList<Object[]>();
      while (values.hasNext()) {
        Object[] item = values.next();
        String intcardno = (String) item[1];
        String exitnotificationlink = (String) item[3];
        if (exitnotificationlink.isEmpty()||(!intcardno.isEmpty())) {
          int expectedcardno = Integer.parseInt(intcardno);
        converted.add(new Object[]{item[0], expectedcardno, item[2], item[3]});
      }
    }
     return converted.iterator();

    }

    /**
     * Retrieves a list of paths to pages which are expected to have external feature cards
     *
     * @return An iterable list of 4 element arrays, each containing a path,
     *         cardNumber, external cards count, external card index, External Card Link, ExitNotificationLink.
     */
    @DataProvider(name = "getPageExtFeatureCardLinks")
    public Iterator<Object[]> getPageExternalFeatureCardLinks () {
      String[] columns = {"path", "externalCardCount", "externalCardIndex", "ExternalCardLink", "ExitNotificationLink"};
      Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("featurecard-data.xlsx"), "pages_feature_card",
        columns);
      List<Object[]> converted = new ArrayList<Object[]>();
      while (values.hasNext()) {
        Object[] item = values.next();
        String extcardcount = (String) item[1];
        String extcardlnk= (String) item[3];
        String extcardno = (String) item[2];
        if (!extcardcount.equals("null")||(!extcardlnk.isEmpty())||(!extcardno.isEmpty())) {
          int expectedextcardcount = Integer.parseInt(extcardcount);
          int expectedcardno = Integer.parseInt(extcardno);
          converted.add(new Object[]{item[0], expectedextcardcount, expectedcardno, extcardlnk, item[4]});
        }
      }
      return converted.iterator();

    }
  }

