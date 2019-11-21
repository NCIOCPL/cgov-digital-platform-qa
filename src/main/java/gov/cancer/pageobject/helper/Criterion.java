package gov.cancer.pageobject.helper;

import java.util.Map;
import java.util.TreeMap;

public class Criterion {

      String label;
      String value;

      /**
       * Constructor, only available from the outer CriteriaList class.
       *
       * @param label The criterion's label.
       * @param value Criterion description
       */
      protected Criterion(String label, String value) {
        this.label = label;
        this.value = value;
      }

    // Case-insensitive list of criteria
    private Map<String, Criterion> criteriaMap = new TreeMap<String, Criterion>(String.CASE_INSENSITIVE_ORDER);

    /**
     * Adds a new entry to the list.
     *
     * @param label The criterion's label.
     * @param text  Criterion description
     */
    public void Add(String label, String text){
      Criterion item = new Criterion(label, text);
      criteriaMap.put(label, item);
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
    public Criterion GetCriterion(String label) {
      return criteriaMap.get(label);
    }

    /**
     * Looks for <em>label</em> in the internal list of entries and reports whether
     * it was found. This lookup is explicitly <b>not</b> case-sensitive.
     *
     * @param label The label to retrieve.
     *
     * @return boolean. True if <em>label</em> was found, false otherwise.
     */
    public boolean ContainsCriterion(String label) {
      return criteriaMap.containsKey(label);
    }

}


