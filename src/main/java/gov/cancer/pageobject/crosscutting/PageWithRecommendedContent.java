package gov.cancer.pageobject.crosscutting;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;
import gov.cancer.pageobject.section.FeatureCard;

public class PageWithRecommendedContent extends PageObjectBase {

  private final String RECOM_SECTION_SELECTOR = "div#blog-cards";
  private final String RECOM_SECTION_TITLE_SELECTOR = "div#blog-cards >h4";
  private final String RECOM_CARD_SECTION_SELECTOR = ":scope .feature-card";

  WebElement recomSection;
  List<FeatureCard> cardlist = new ArrayList<FeatureCard>();

  public PageWithRecommendedContent(String path) {
    super(path);
    recomSection = ElementHelper.findElement(getBrowser(), RECOM_SECTION_SELECTOR);

    List<WebElement> cardelement = ElementHelper.findElements(recomSection, RECOM_CARD_SECTION_SELECTOR);
    for (WebElement we : cardelement) {
      cardlist.add(new FeatureCard(we));
    }
  }

  /**
   * Returns true if the page has recommended content section.
   */
  public boolean hasRecomSection() {

    return recomSection.isDisplayed();

  }

  /**
   * Returns true if the page has recommended content card.
   */
  public String getSectionTitle() {

    return ElementHelper.findElement(getBrowser(), RECOM_SECTION_TITLE_SELECTOR).getText();

  }

   /**
   * Returns list of feature cards present on the page.
   */
  public List<FeatureCard> getRecomCards() {

    return cardlist;
  }

  /**
   * Returns list of internal cards present on the page.
   */
  public List<FeatureCard> getIntRecomCards() {
    List<FeatureCard> intcardlist = new ArrayList<FeatureCard>();
    for (int i = 0; i < cardlist.size(); i++) {
      if (!cardlist.get(i).isExternalLinkPresent())
        intcardlist.add(cardlist.get(i));
    }
    return intcardlist;

  }

  /**
   * Returns list of external cards present on the page.
   */
  public List<FeatureCard> getExternalRecomCards() {

    List<FeatureCard> extcardlist = new ArrayList<FeatureCard>();
    for (int i = 0; i < cardlist.size(); i++) {
      if (cardlist.get(i).isExternalLinkPresent())
        extcardlist.add(cardlist.get(i));
    }
    return extcardlist;

  }

}
