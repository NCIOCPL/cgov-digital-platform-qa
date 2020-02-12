package gov.cancer.framework.webanalytics;

import org.apache.http.NameValuePair;

import java.util.ArrayList;

import java.util.List;

// Represents a single Adobe Analytics request beacon with query parameters
public class Beacon extends AnalyticsRequest {
  // TODO: Handle null exceptions in has() methods
  // TODO: Clean up comments
  // TODO: Rename cgov_pagename

  // Constants
  public static final String CGOV_PAGENAME = "www.cancer.gov";
  protected static final String DATA_FILEPATH = "./test-data/webanalytics-suitemap.xlsx";
  protected static final String DATA_SHEETNAME = "CancerGov";

  // Parameter values from URL
  static final String CHANNEL = "ch";
  static final String EVENTS = "events";
  static final String LINKTYPE = "pe";
  static final String LINKNAME = "pev2";
  static final String LINKURL = "pev1";
  static final String PAGE_NAME = "pageName";
  static final String PAGE_TYPE = "pageType";

  // Partial parameter values. Each prop, eVar, and hier is its own query
  // parameter.
  // The getNumberedParams() method handles the logic of appending the
  // number values to each of these query parameters
  static final String PROP_PARTIAL = "c";
  static final String EVAR_PARTIAL = "v";
  static final String HIER_PARTIAL = "h";

  // Testable variable names
  public String[] suites; // s.account or s_account
  public String[] events; // events
  public String channels; // s.channel
  public List<String> props; // props, aka "Traffic Variables"
  public List<String> eVars; // eVars, aka "Conversion Variables"
  public List<String> hiers; // Hierarchy variables
  public String linkName;
  public String pageName;

  // This class represents an Adobe Analytics request beacon
  public Beacon(String url) {
    super(url);

    // Set our variables
    //	this.suites = getSuites();
    this.channels = getChannels();
    this.events = getEvents();
    this.props = getPropList();
    this.eVars = geteVarList();
    this.hiers = getHierList();
    this.linkName = getLinkName();
    this.pageName = getPageName();
  }

  /****************
   * Methods to check for given values in a beacon
   ****************/

  /**
   * Get an array of suites (s.account) values from the URL path.
   *
   * @return suites (String[])
   */
  private String[] getSuites() {
    try {
      String[] path = this.url.split("/");
      String[] suites = path[5].split(",");
      return suites;
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Could not locate suite values in URL: " + url + "\nin Beacon: GetSuites()");
      return null;
    }
  }

  /**
   * Get channel parameter value.
   *
   * @return channel (String)
   */
  private String getChannels() {
    return getParamValue(CHANNEL);
  }

  /**
   * Get array of event values
   *
   * @param parms
   * @return
   */
  public String[] getEvents() {
    String rtnEvents = getParamValue(EVENTS);
    return rtnEvents.split(",");
  }

  /**
   * @return
   */
  private List<String> getPropList() {
    List<String> rtn = getNumParams(PROP_PARTIAL, paramsList, 75);
    return rtn;
  }

  /**
   * @return
   */
  private List<String> geteVarList() {
    List<String> rtn = getNumParams(EVAR_PARTIAL, paramsList, 75);
    return rtn;
  }

  /**
   * @return
   */
  private List<String> getHierList() {
    List<String> rtn = getNumParams(HIER_PARTIAL, paramsList, 2);
    return rtn;
  }

  /**
   * Get "Link Type" value (pe)(
   *
   * @return
   */
  public String getLinkType() {
    return getParamValue(LINKTYPE);
  }

  /**
   * Get "Link Name" value (pev2)(
   *
   * @return
   */
  public String getLinkName() {
    return getParamValue(LINKNAME);
  }

  /**
   * Get "Link URL" value (pev1)
   *
   * @return
   */
  public String getLinkUrl() {
    return getParamValue(LINKURL);
  }

  /**
   * Get the Page Type param value (pageType).
   *
   * @return string representing page type
   */
  public String getPageType() {
    return getParamValue(PAGE_TYPE);
  }

  /**
   * Get the Page Name param value (pageName).
   *
   * @return string representing page name
   */
  public String getPageName() {
    return getParamValue(PAGE_NAME);
  }

  /**
   * Get the string value of a query parameter if it exists on the beacon.
   *
   * @param param
   * @return String value of parameter
   */
  private String getParamValue(String param) {
    NameValuePair nvp = getBeaconParam(param);
    if (nvp != null) {
      return nvp.getValue().trim();
    } else {
      return "";
    }
  }

  /**
   * Get the name value pair param object if it exists on the beacon.
   *
   * @param param
   * @return
   */
  private NameValuePair getBeaconParam(String param) {
    for (NameValuePair nvp : this.paramsList) {
      if (nvp.getName().equalsIgnoreCase(param)) {
        return nvp;
      }
    }
    return null;
  }

  /**
   * Utility function to check for a user-specified variable and value
   *
   * @param name
   * @param value
   * @return bool
   */
  protected boolean hasVariable(String name, String value) {
    // TODO: fill this out
    return false;
  }

  /**
   * Check parameters to verify that this is a click-type event (s.tl).
   *
   * @return boolean
   */
  public boolean isClickTypeEvent() {
    if (getLinkType().isEmpty() && getLinkName().isEmpty() && getLinkUrl().isEmpty()) {
      return false;
    } else if (isExitClickTypeEvent() == true) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Check parameters to verify that this is an exit link click.
   *
   * @return boolean
   */
  public boolean isExitClickTypeEvent() {
    return getLinkType().equalsIgnoreCase("lnk_e");
  }

  /**
   * Check for an event value within a click beacon.
   *
   * @param eventNumber
   * @return
   */
  public boolean hasEvent(int eventNumber) {
    String evt = "event" + Integer.toString(eventNumber);
    for (String event : this.getEvents()) {
      if (evt.equalsIgnoreCase("event47")) {
        if (event.matches("^event47=\\d+")) {
          return true;
        }
      } else if (evt.equalsIgnoreCase("event92")) {
        if (event.matches("^event92=\\d+")) {
          return true;
        }
      } else if (event.equalsIgnoreCase(evt)) {
        return true;
      }
    }
    return false;
  }

  /******************** Utility methods ****************************************/

  /**
   * Initialize a numbered list of empty elements.
   *
   * @param size
   * @return rtnList List<String>
   */
  private List<String> initNumberedArrayList(int size) {
    List<String> myList = new ArrayList<String>();
    while (myList.size() <= size) {
      myList.add(null);
    }
    return myList;
  }

  /**
   * Get a list of numbered parameters and their values (e.g.
   * [prop1="www.cancer.gov", prop2="/home", prop3="NCI"])
   *
   * @param myParam
   * @param myList
   * @param total
   * @return List of values (String)
   */
  protected List<String> getNumParams(String myParam, List<NameValuePair> myList, int total) {
    // Create a list with x names and null values
    List<String> rtnList = initNumberedArrayList(total);

    // Go through the list of populated parameter values and add those to the return
    // list where the number matches
    for (NameValuePair pair : myList) {
      String name = pair.getName().trim();
      String val = pair.getValue().trim();
      // Regex: parameter name followed by 1 or more digits, starting with 1-9 only
      if (name.matches("^" + myParam + "[1-9]\\d*$")) {
        int index = Integer.parseInt(name.replace(myParam, ""));
        rtnList.set(index, val);
      }
    }
    return rtnList;
  }

}
