package gov.cancer.framework.webanalytics;


import gov.cancer.framework.ParsedURL;
import org.apache.http.NameValuePair;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class AnalyticsRequest {

  public String url;
  public String path;
  public List<NameValuePair> paramsList;

  public AnalyticsRequest(String url) {
    try {
      this.url = url;
      this.path = getPath(url);
      this.paramsList = ParsedURL.getParamArrayList(url);
    } catch (Exception e) {
      System.out.println("Error initializing AnalyticsRequest. Check the value of the URL being passed in.");
      e.printStackTrace();
    }
  }

  /**
   * Get the path only for this request URL.
   *
   * @param myUrl
   * @return
   */
  private static String getPath(String myUrl) {
    try {
      URL url = new URL(myUrl);
      return url.getPath();
    } catch (MalformedURLException e) {
      e.printStackTrace();
      return "";
    }
  }

}
