package gov.cancer.framework;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * Breaks a URL string into its constituent components (path, query parameters,
 * etc.). This is a wrapper for the java.net.URL class, adding a dictionary of
 * query parameters.
 */
public class ParsedURL {
  private URL innerUrl;
  private Map<String, String> queryPairs = new LinkedHashMap<String, String>();

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
        String[] pairs = query.split("&");
        for (String pair : pairs) {
          int idx = pair.indexOf("=");

          String parameter = URLDecoder.decode(pair.substring(0, idx), "UTF-8");
          String value = URLDecoder.decode(pair.substring(idx + 1), "UTF-8");
          queryPairs.put(parameter, value);
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(String.format("Bad URL '{%s}'.", url));
    }
  }

  /**
   * Gets the path part of this URL.
   *
   * @return the path part of thsi URL, or an empty string if one does not exist.
   */
  public String getPath() {
    return innerUrl.getPath();
  }

  /**
   * Get the query portion of the URL.
   *
   * @return String containing the query portion of the URL.
   * @see getQueryParam for individual parameters.
   */
  public String getQuery() {
    return innerUrl.getQuery();
  }

  /**
   * Gets the value of a single query parameter.
   *
   * @param paramName The name of the query parameter to retrieve.
   * @return A String containing the parameter's value. If the parameter was
   *         present, but with no value, an empty string is return. If the
   *         parameter was not present, NULL is returned.
   */
  public String getQueryParam(String paramName) {
    if (queryPairs.containsKey(paramName))
      return queryPairs.get(paramName);
    else
      return null;
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

}
