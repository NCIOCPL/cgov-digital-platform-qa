package gov.cancer.framework;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class is an object that exposes method to compare two Query Strings
 */
public class QueryParametersComparator {

  //List of NameValuePair is used for unitializing the QueryParamComparator and serves as
  //an object to compare the other String(or List<NameValuePair>) to
  private List<NameValuePair> valuesToCompare = new ArrayList<NameValuePair>();

  /**
   * Constructor
   * Takes a List and assigns it to main class variable
   */
  public QueryParametersComparator(List<NameValuePair> valuesToCompare) {
    this.valuesToCompare = valuesToCompare;
  }

  /**
   * Overloaded Constructor
   * Parses given String into List of  NameValuePair and initializes main class variable
   */
  public QueryParametersComparator(String queryString) {

    for (String parm : queryString.split("&")) {
      String[] pair = parm.split("=");
      String name = pair[0];
      String value = null;
      if (pair.length > 1) {
        value = pair[1];
      }
      valuesToCompare.add(new BasicNameValuePair(name, value));
    }
  }

  /**
   * This method is converting given String into List  <NameValuePair> and comparing an Object received through
   * constructor to the given Parameter
   * Comparison is based on two steps:
   * -number of received url query parameters is equal to provided param number
   * -all parameters are matching
   *
   * @param queryStringToCompare - string that represent full query part of an URL
   * @return boolean
   */
  public boolean compareQueryParams(String queryStringToCompare) {

    List<NameValuePair> convertedString = new ArrayList<NameValuePair>();
    for (String parm : queryStringToCompare.split("&")) {
      String[] pair = parm.split("=");
      String name = pair[0];
      String value = null;
      if (pair.length > 1) {
        value = pair[1];
      }
      convertedString.add(new BasicNameValuePair(name, value));
    }
    return compare(convertedString, valuesToCompare);
  }

  /**
   * Overloaded compare method -
   * This method is comparing given List  <NameValuePair> with List of  <NameValuePair> that was used for creating Comparator object
   * Comparison is based on two steps:
   * -number of received url query parameters is equal to provided param number
   * -all parameters are matching
   *
   * @param expectedValues -  List<NameValuePair> that contains expected values
   * @return boolean
   */
  public boolean compareQueryParams(List<NameValuePair> expectedValues) {
    return compare(expectedValues, valuesToCompare);
  }


  /**
   * Method to compare two given List of <NameValuePair></NameValuePair>
   *
   * @param first
   * @param second
   * @return
   */
  private boolean compare(List<NameValuePair> first, List<NameValuePair> second) {
    //first compare size - if not the same, then return false
    if (first.size() != second.size()) {
      return false;
    } else {
      //first we sort both collections by it's key
      Collections.sort(first, Comparator.comparing(NameValuePair::getName));
      Collections.sort(second, Comparator.comparing(NameValuePair::getName));
      //compare each pair object to another
      for (int i = 0; i < first.size(); i++) {
        if (!first.get(i).equals(second.get(i))) {
          return false;
        }
      }
    }
    return true;
  }
}

