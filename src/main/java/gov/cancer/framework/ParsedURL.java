package gov.cancer.framework;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Breaks a URL string into its constituent components (path, query parameters,
 * etc.). This is a wrapper for the java.net.URL class, adding a dictionary of
 * query parameters.
 */
public class ParsedURL {
  private URL innerUrl;

  //we want to return name value pairs instead of mapping the query params because maps does not hold duplicate keys and our url
  // might hae duplicate query params
  private List<NameValuePair> queryParams = new ArrayList<NameValuePair>();

  /**
   * Creates a ParsedURL object from its String representation.
   *
   * @param url - the String to parse as a URL.
   */
  public ParsedURL(String url) {

    try {
      innerUrl = new URL(url);
      String query = innerUrl.getQuery();
      if (query != null) {
        // splitting the query on the basis of "&"
        String[] pairs = query.split("&");
        // for each name value pair in the query, splitting the query on the basis of "=" and
        // adding the name value pair to the list
        for (String pair : pairs) {
          int idx = pair.indexOf("=");
          String parameter = URLDecoder.decode(pair.substring(0, idx), "UTF-8");
          String value = URLDecoder.decode(pair.substring(idx + 1), "UTF-8");
          queryParams.add(new BasicNameValuePair(parameter, value));
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(String.format("Bad URL '{%s}'.", url));
    }
  }

    /**
   * Gets the path part of this URL.
   *
   * @return the path part of this URL, or an empty string if one does not exist.
   */
  public String getPath() {
    return innerUrl.getPath();
  }

  /**
   * Get the query portion of the URL.
   *
   * @return String containing the query portion of the URL.
   *
   */
  public String getQuery() {
    return innerUrl.getQuery();
  }

  /**
   * Gets the value of a single query parameter. This method will only return a value of parameter if that is the only
   * query parameter present in the url. The method throws TooManyValuesException if multiple instances of the parameter are found.
   *
   * @param paramName The name of the query parameter to retrieve.
   * @return A String containing the query parameter's value.
   */
  public String getQueryParam(String paramName) {
   List <String> res = retrieveAllQueryValues(paramName);
    if (res.size() == 1)
    {
      return res.get(0);
    }
      else
        throw  new TooManyValuesException("Query contains more than one Params");
  }

  /**
   *  The method is intended for use when there may be multiple instances of a parameter.
   *  Gets the value of requested parameter and stores it in a list
   *
   * @param paramName The name of the query parameter to retrieve.
   * @return A list  containing query parameter's value (s)
   */

  public List<String> getMultipleQueryParam(String paramName) {
    return retrieveAllQueryValues(paramName);
  }

  /**
   * Retrieves the values of all the query parameters
   * @param paramName
   * @return the list with retrieved values
   */
  private List<String> retrieveAllQueryValues(String paramName){
    List <String> result = new ArrayList<String>();
    for (int i=0; i<queryParams.size();i++) {
      if (queryParams.get(i).getName().equalsIgnoreCase(paramName)) {
        result.add(queryParams.get(i).getValue());
      }
    }
    return result;
  }
  /**
   * Get a list of decoded query parameters and values.
   *
   * I realize this is very similar to the logic in the constructor, but trying to
   * build the queryPairs maps throws the following error:
   * java.lang.StringIndexOutOfBoundsException: String index out of range: -1 at
   * java.lang.String.substring(Unknown Source) at
   * gov.cancer.framework.ParsedURL.<init>(ParsedURL.java:43) Adding
   * getParamsArrayList as a static method for now b/c I'd rather not mess with
   * the constructor logic - daquinohd
   *
   * @param url (String)
   * @return
   */
  public static List<NameValuePair> getParamArrayList(String url) {
    List<NameValuePair> rtnParams = new ArrayList<NameValuePair>();
    try {
      URL myUrl = new URL(url);
      String queries = myUrl.getQuery(); // get encoded query string
      for (String parm : queries.split("&")) {
        String[] pair = parm.split("=");
        String name = URLDecoder.decode(pair[0], "UTF-8");
        String value = "";
        if (pair.length > 1) {
          value = URLDecoder.decode(pair[1], "UTF-8");
        }
        rtnParams.add(new BasicNameValuePair(name, value));
      }
    } catch (MalformedURLException ex) {
      System.out.println("Malformed URL in ParsedURL:getParamsArrayList()");
    } catch (UnsupportedEncodingException ex) {
      System.out.println("Error decoding URL in ParsedURL:getParamsArrayList()");
    }
    return rtnParams;
  }

  /**
   * Method return Host portion of URL
   * @return
   */
  public String getHost (){
    return innerUrl.getHost();
  }

}
