package gov.cancer.pageobject.cts.cts_components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.Component;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class represents Search Criteria section from search results page
 */
public class CriteriaSection extends Component {
private WebElement criteriaSection;

private List<WebElement> labelCell ;
private List <WebElement> valueCell ;

String labelLocator = ":scope tbody tr th";
String valueLocator = ":scope tbody tr td";


      /**
       * Constructor, only available from the outer CriteriaList class.
       *
       * @param element
       *        - WebElement defining criteria section
       */
      protected CriteriaSection( WebElement element) {
        super(element);
        criteriaSection = element;
        labelCell = ElementHelper.findElements(element,labelLocator);
        valueCell = ElementHelper.findElements(element,valueLocator);
        for (int i=0; i<labelCell.size(); i++){
          criteriaMap.put(labelCell.get(i).getText(), valueCell.get(i).getText());
        }
      }

    //Case insensitive list of criteria
    private Map<String, String> criteriaMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    /**
     * Adds a new entry to the list.
     *
     * @param label The criterion's label.
     * @param value  Criterion description
     */
    public void Add(String label, String value){
      criteriaMap.put(label, value);
    }

    /**
     * Gets the Criterion associated with label. Note that this method may return
     * null if the label was not defined. The lookup is explicitly <b>not</b>
     * case-sensitive. The returned Criterion object contains the text with its
     * original casing.
     *
     * @param label The label to retrieve.
     *
     * @return A Criterion object containing both the label and the matching criterion.
     */
    public String GetCriterion(String label) {
      return criteriaMap.get(label);
    }
}


