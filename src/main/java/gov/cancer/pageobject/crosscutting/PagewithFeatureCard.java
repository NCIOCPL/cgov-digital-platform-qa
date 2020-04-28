package gov.cancer.pageobject.crosscutting;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;
import gov.cancer.pageobject.section.FeatureCard;

/**
 * Pseudo page class representing a page having one or more feature cards
 */

public class PagewithFeatureCard extends PageObjectBase {

  private final String FEATURE_CARD_SELECTOR = ".feature-primary .feature-card";
  private List<FeatureCard> cardlist = new ArrayList<FeatureCard>();

  public PagewithFeatureCard(String path) {
    super(path);
    List<WebElement> cardelement = ElementHelper.findElements(getBrowser(), FEATURE_CARD_SELECTOR);
    for (WebElement we : cardelement) {
      cardlist.add(new FeatureCard(we));
    }

  }

  /**
   * Returns list of feature cards present on the page.
   */
  public List<FeatureCard> getFeatureCards() {
    return cardlist;

  }

  /**
   * Returns list of internal feature cards present on the page.
   */
  public List<FeatureCard> getIntFeatureCards() {
    List<FeatureCard> intcardlist = new ArrayList<FeatureCard>();
    for (int i = 0; i < cardlist.size(); i++) {
      if (!cardlist.get(i).isExternalLinkPresent())
        intcardlist.add(cardlist.get(i));
    }
    return intcardlist;

  }

  /**
   * Returns list of external feature cards present on the page.
   */
  public List<FeatureCard> getExternalFeatureCards() {

    List<FeatureCard> extcardlist = new ArrayList<FeatureCard>();
    for (int i = 0; i < cardlist.size(); i++) {
      if (cardlist.get(i).isExternalLinkPresent())
        extcardlist.add(cardlist.get(i));
    }
    return extcardlist;

  }
}
