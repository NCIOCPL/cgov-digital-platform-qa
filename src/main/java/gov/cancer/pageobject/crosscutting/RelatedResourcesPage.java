package gov.cancer.pageobject.crosscutting;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;
import gov.cancer.pageobject.helper.RelatedResource;

public class RelatedResourcesPage extends PageObjectBase {

  /**
   * The related resources section. Set in the constructor. You *MUST* check
   * whether it is null before using.
   */
  WebElement relatedResourcesSection;
  final static String RELATED_LINK_SELECTOR = ":scope ul li";

  /**
   * Constructor
   *
   * @param path
   *          server-relative path of the page to load.
   */
  public RelatedResourcesPage(String path) {
    super(path);

    // Get the actual section, if it's present.
    relatedResourcesSection = ElementHelper.findElement(getBrowser(), "#nvcgRelatedResourcesArea");

  }

  /**
   * Returns true if the page has a related resources section, false otherwise.
   */
  public boolean hasRelatedResources() {

    return relatedResourcesSection != null && relatedResourcesSection.isDisplayed();

  }

  /**
   * Find all of the related resource objects on the page.
   */
  public List<RelatedResource> getRelatedResources() {

    List<RelatedResource> links = new ArrayList<RelatedResource>();

    List<WebElement> rawLinks = ElementHelper.findElements(relatedResourcesSection, RELATED_LINK_SELECTOR);
    for (WebElement link : rawLinks) {
      links.add(new RelatedResource(link));
    }
    return links;
  }

  /**
   * Finds non-.gov links and put it in the List.
   * 
   * @return non-.gov link List
   */
  public List<RelatedResource> getExternalResources() {

    List<RelatedResource> externalResources = new ArrayList<RelatedResource>();

    List<RelatedResource> allResources = getRelatedResources();

    for (RelatedResource item : allResources) {

      if (item.isExternal())
        externalResources.add((item));
    }
    return externalResources;
  }

}