package gov.cancer.pageobject.cts.components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.Component;
import gov.cancer.pageobject.cts.components.accordion_items.*;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


/**
 * Class represents an entire accordion
 */
public class Accordion extends Component {

  //list of all title elements
  List<WebElement> titles;
  // list of all body elements
  List<WebElement> bodies;
  //List of all accordion sections
  List<AccordionItem> sections = new ArrayList<>();
  //locators
  private final static String TITLE_LOCATOR = ":scope >h2";
  private final static String BODY_LOCATOR = ":scope >div";


  /**
   * Constructor
   * Creates a list of all polymorphic accordion items
   * Where each accordion item is pair of title element and body element
   */

  public Accordion(WebElement element) {
    super(element);
    titles = ElementHelper.findElements(element, TITLE_LOCATOR);
    bodies = ElementHelper.findElements(element, BODY_LOCATOR);
    sections.add(new Description(titles.get(0), bodies.get(0)));
    sections.add(new EligibilityCriteria(titles.get(1), bodies.get(1)));
    sections.add(new LocationAndContacts(titles.get(2), bodies.get(2)));
    sections.add(new TrialObjectivesAndOutline(titles.get(3), bodies.get(3)));
    sections.add(new TrialPhaseAndType(titles.get(4), bodies.get(4)));
    sections.add(new LeadOrganization(titles.get(5), bodies.get(5)));
    sections.add(new TrialIDs(titles.get(6), bodies.get(6)));
  }

  /**
   * Getter method for individual AccordionItem
   * Look up is based on an index of an item from a list
   * @param index
   * @return corresponding to index section of an accordion
   */

  public AccordionItem get(int index) {
    return sections.get(index);
  }
}
