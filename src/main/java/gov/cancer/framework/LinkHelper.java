package gov.cancer.framework;

import java.net.URL;

public class LinkHelper {

  /**
   * Retrieve the top-level domain which is considered to be "internal" for
   * purposes government / non-government.
   *
   * @return
   */
  public static boolean isGovermentUrl(URL url) {

    Configuration config = new Configuration();

    String hostName = url.getHost();
    if (hostName.endsWith(".gov") || hostName.endsWith(config.getInternalDomain())) {

      return false;
    } else
      return true;

  }

}
