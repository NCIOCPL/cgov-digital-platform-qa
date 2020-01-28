package gov.cancer.pageobject.cts;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.cts.advanced_search_page_components.*;

/**
 * This class represents Advanced Search  page of CTS module
 */
public class AdvancedSearchPage extends CTSPage {

  // page section with trial investigators field
  private InvestigatorsSection trialInvestigator;
  // page section with lead organizations field
  private LeadOrganizationSection leadOrganizationSection;
  //Age section
  private AgeSection ageSection;
  //Keywords/Phrases section
  private KeywordsPhrasesSection keywordsPhrasesSection;
  //page section with cancer type options
  private CancerTypeSection cancerTypeSection;
  // page section with trial type options
  private TrialTypeSection trialTypeSection;
  // page section with drug/treatment options
  private DrugTreatmentSection drugTreatmentSection;
  // page section with trial phase options
  private TrialPhaseSection trialPhaseSection;
  // page section with location options
  private LocationSection locationSection;
  //object represents section of the page with 'Find Trials' and 'Clear Form' buttons
  private FormAction formAction;
  // trial id page section
  private TrialIDSection trialID;


  /********LOCATORS************/
 private final static String CANCER_TYPE_SECTION_LOCATOR = "#fieldset--type";
 private final static String AGE_SECTION_LOCATOR ="#fieldset--age";
 private final static String KEYWORDS_PHRASES_LOCATOR="#fieldset--keyword";
 private final static String LOCATION_SECTION_LOCATOR = "#fieldset--location";
 private final static String TRIAL_TYPE_LOCATOR= "#fieldset--trialtype";
 private final static String DRUG_SECTION_LOCATOR ="#fieldset--drug-trtmt" ;
 private final static String TRIAL_PHASE_LOCATOR ="#fieldset--trialphase" ;
 private final static String TRIAL_ID_LOCATOR = "#fieldset--trialid";
 private final static String TRIAL_INVESTIGATOR_LOCATOR = "#fieldset--trialInvestigators";
 private final static String LEAD_ORGANIZATION_LOCATOR = "#fieldset--lead_organization";
private final static String FORM_ACTION_LOCATOR = "#stickyAnchor";

  /**
   * Constructor
   *
   * @param path
   */
  public AdvancedSearchPage(String path) {
    super(path);
    cancerTypeSection = new CancerTypeSection(getBrowser(), ElementHelper.findElement(getBrowser(),CANCER_TYPE_SECTION_LOCATOR));
    ageSection = new AgeSection(ElementHelper.findElement(getBrowser(), AGE_SECTION_LOCATOR));
    keywordsPhrasesSection = new KeywordsPhrasesSection(ElementHelper.findElement(getBrowser(), KEYWORDS_PHRASES_LOCATOR));
    locationSection = new LocationSection(getBrowser(),ElementHelper.findElement(getBrowser(), LOCATION_SECTION_LOCATOR));
    trialTypeSection = new TrialTypeSection(ElementHelper.findElement(getBrowser(), TRIAL_TYPE_LOCATOR));
    drugTreatmentSection = new DrugTreatmentSection(getBrowser(), ElementHelper.findElement(getBrowser(), DRUG_SECTION_LOCATOR));
    trialPhaseSection = new TrialPhaseSection(getBrowser(),ElementHelper.findElement(getBrowser(), TRIAL_PHASE_LOCATOR));
    trialID = new TrialIDSection(ElementHelper.findElement(getBrowser(), TRIAL_ID_LOCATOR));
    trialInvestigator = new InvestigatorsSection(getBrowser(), ElementHelper.findElement(getBrowser(), TRIAL_INVESTIGATOR_LOCATOR));
    leadOrganizationSection = new LeadOrganizationSection(getBrowser(), ElementHelper.findElement(getBrowser(), LEAD_ORGANIZATION_LOCATOR));
    formAction = new FormAction(ElementHelper.findElement(getBrowser(), FORM_ACTION_LOCATOR));

  }

  //Getter for age field
  public AgeSection getAge() {
    return ageSection;
  }

  //Getter for keyword field
  public KeywordsPhrasesSection getKeywordPhrase() {
    return keywordsPhrasesSection;
  }

  //Getter for Trial Id field
  public TrialIDSection getTrialId() {
    return trialID;
  }

  //Getter for Cancer Type/Condition section
  public CancerTypeSection getCancerTypeSection() {
    return cancerTypeSection;
  }

  //Getter for Trial Investigator Section
  public InvestigatorsSection getTrialInvestigator() {
    return trialInvestigator;
  }

  //Getter for Lead Organization Section
  public LeadOrganizationSection getLeadOrganizationSection() {
    return leadOrganizationSection;
  }

  //Getter for TrialID Section
  public TrialIDSection getTrialID() {
    return trialID;
  }

  //Getter for trial type section of the page
  public TrialTypeSection getTrialTypeSection() {
    return trialTypeSection;
  }

  //Getter for drug/treatment section of the page
  public DrugTreatmentSection getDrugTreatmentSection() {
    return drugTreatmentSection;
  }

  //Getter for trial phase section of the page
  public TrialPhaseSection getTrialPhaseSection() {
    return trialPhaseSection;
  }

  //Getter for location section of the page
  public LocationSection getLocationSection() {
    return locationSection;
  }

  //Getter for section of the page with 'Find Trials' and 'Clear Form' buttons
  public FormAction getFormAction() {
    return formAction;
  }

}
