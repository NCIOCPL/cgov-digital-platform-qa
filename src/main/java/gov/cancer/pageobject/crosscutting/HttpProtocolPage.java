package gov.cancer.pageobject.crosscutting;


import gov.cancer.framework.Configuration;
import io.restassured.response.*;

import static io.restassured.RestAssured.when;

/**
 * This is a pseudo Page Class that is used solely for sending API requests
 * RestAssured library is used for sending a request
 *
 * Currently it supports 'GET' request only, but can be extended to support POST requests,
 * modifying headers, sending requests with provided JSON etc.
 */
public class HttpProtocolPage {

  private String fullUrl;

  /**
   * Constructor
   * Makes a fully qualified URL
   *
   * @param path url path provided
   */
  public HttpProtocolPage(String path) {
    Configuration config = new Configuration();
    String host = config.getHostName();

    if (path.startsWith("/"))
      fullUrl = host + path;
    else
      fullUrl = host + "/" + path;
  }


  /**
   * This method will send a get request to provided URL and provide a response object
   * @return response from 'get' request
   */
  public Response getResponse() {
    //Response object represents a response received from sending a 'get' request to an API
    Response response = when().get(fullUrl);
    return response;
  }

}
