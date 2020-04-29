package gov.cancer.pageobject.crosscutting;

import gov.cancer.pageobject.helper.Link;
import gov.cancer.pageobject.helper.MetaTag;
import org.openqa.selenium.WebElement;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;
import gov.cancer.pageobject.helper.RobotMetaTag;

/**
 * Pseudo page object representing any page in the system. The class is used
 * solely for verifying the attributes of entire page meta names and properties
 */

public class PageWithMetaData extends PageObjectBase {
  private WebElement headField;
  private String cssRobotTag = ":scope meta[name='robots']";
  private String cssTwitterTag = ":scope meta[name='twitter:card']";
  private String ogType = ":scope meta[property='og:type']";
  private String dcTermsType = ":scope meta[name='dcterms.type']";
  private String dcTermsSub = ":scope meta[name='dcterms.subject']";
  private String dcTermsPartOf = ":scope meta[name='dcterms.isPartOf']";
  private String IsReferencedBy = ":scope meta[name='dcterms.isReferencedBy']";
  private String coverage = ":scope meta[name='dcterms.coverage']";
  private String ogTitle = ":scope meta[property='og:title']";
  private String siteName = ":scope meta[property='og:site_name']";
  private String ogURL = ":scope meta[property='og:url']";
  private String metaDesc = ":scope meta[name='description']";
  private String ogDesc = ":scope meta[property='og:description']";
  private String language = ":scope meta[http-equiv='content-language']";
  private String canonicalURL = ":scope link[rel='canonical']";
  private String dcTermsIssuedDate = ":scope meta[name='dcterms.issued']";

  /**
   * Constructor
   *
   * @param path server-relative path of the page to load.
   */
  public PageWithMetaData(String path) {
    super(path);
    headField = ElementHelper.findElement(getBrowser(),"head");
  }

  /**
   * Finds and returns content of 'robots' metatag
   */
  public RobotMetaTag getRobotMetaTag() {
    WebElement robotMetaTagElement = ElementHelper.findElement(headField, cssRobotTag);
    return new RobotMetaTag(robotMetaTagElement);
  }

  /**
   * Finds and returns 'twitter:card' metatag
   */
  public MetaTag getTwitterCardMetaTag() {
    WebElement twitterMetaTagElement = ElementHelper.findElement(headField, cssTwitterTag);
    return new MetaTag(twitterMetaTagElement);
  }

  /**
   * Finds and returns'og:type' metatag for Open Graph
   */
  public MetaTag getOGTypeMetaTag() {
    WebElement ogTypeElement = ElementHelper.findElement(headField, ogType);
    return new MetaTag(ogTypeElement);
  }

  /**
   * Finds and returns 'dcterms.isReferencedBy' metatag
   */
  public MetaTag getDCTermsReferencedBy() {
    WebElement isReferencedBy = ElementHelper.findElement(headField, IsReferencedBy);
    return new MetaTag(isReferencedBy);
  }

  /**
   * Finds and returns 'dcterms.coverage' metatag
   */
  public MetaTag getDCTermCoverage() {
    WebElement dcTermsCoverage = ElementHelper.findElement(headField, coverage);
    return new MetaTag(dcTermsCoverage);
  }

  /**
   * Finds and returns 'dcterms.type' metatag
   */
  public MetaTag getDCTermsType() {
    WebElement DCTermsType = ElementHelper.findElement(headField, dcTermsType);
    return new MetaTag(DCTermsType);
  }

  /**
   * Finds and returns dcterms.subject metatag
   */
  public MetaTag getDCTermsSubject() {
    WebElement DCTermsSub= ElementHelper.findElement(headField, dcTermsSub);
    return new MetaTag(DCTermsSub);
  }

  /**
   * Finds and returns dcterms.issued metatag
   */
  public MetaTag getDCTermsIssuedDate() {
    WebElement IssueDate = ElementHelper.findElement(headField, dcTermsIssuedDate);
    return new MetaTag(IssueDate);
  }

  /**
   * Finds and returns dcterms.isPartOf metatag
   */
  public MetaTag getDCTermsPartOf() {
    WebElement PartOf = ElementHelper.findElement(headField, dcTermsPartOf);
    return new MetaTag(PartOf);
  }

  /**
   * Finds and returns og:title metatag for Open Graph
   */
  public MetaTag getOGTitle() {
    WebElement Title = ElementHelper.findElement(headField, ogTitle);
    return new MetaTag(Title);
  }

  /**
   * Finds and returns og:site_name metatag for Open Graph
   */
  public MetaTag getMetaSiteName() {
      WebElement SiteName = ElementHelper.findElement(headField, siteName);
   return new MetaTag(SiteName);
  }

  /**
   * Finds and returns content of 'og:url' metatag for Open Graph
   */
  public MetaTag getOGURL() {
    WebElement OGURL = ElementHelper.findElement(headField, ogURL);
    return new MetaTag(OGURL);
  }

  /**
   * Finds and returns href attribute of link rel="canonical" metatag
   */
  public String getCanonicalURL() {
    WebElement CanonicalURL = ElementHelper.findElement(headField, canonicalURL);
    return new Link(CanonicalURL).getUrl().toString();
  }

  /**
   * Finds and returns Current Page URL
   */
  public String getCurrentURL() {
    return getBrowser().getCurrentUrl();
  }

  /**
   * Finds and returns description metatag
   */
  public MetaTag getMetaDescription() {
    WebElement Desc = ElementHelper.findElement(headField, metaDesc);
    return new MetaTag(Desc);
  }

  /**
   * Finds and returns og:description metatag for Open Graph
   */
  public MetaTag getOGMetaDescription() {
    WebElement Desc = ElementHelper.findElement(headField, ogDesc);
    return new MetaTag(Desc);
  }

  /**
   * Finds and returns metatag http-equiv="content-language"
   */
  public MetaTag getContentLanguage() {
    WebElement Language = ElementHelper.findElement(headField, language);
    return new MetaTag(Language);
  }

}
