package gov.cancer.framework;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.lang3.SystemUtils;


/**
 * Presents an interface for retieviving values stored in the config.properties file.
 */
public class Configuration {
  Properties properties;


  /**
   * Contains the dimensions for a responsive layout breakpoint.
   */
  public class Breakpoint {
    // Width in pixels.
    private int width;
    // Height in pixels.
    private int height;

    /**
     * Creates an instance of Breakpoint
     *
     * @param width  screen width in pixels.
     * @param height screen height in pixels.
     */
    public Breakpoint(int width, int height) {
      this.width = width;
      this.height = height;
    }

    /**
     * GetWidth().
     * @return the desired width of the browser window.
     */
    public int GetWidth() {
      return this.width;
    }

    /**
     * GetHeight().
     *
     * @return the desired height of the browser window.
     */
    public int GetHeight() {
      return this.height;
    }
  }


  /**
   * Constructor.
   */
  public Configuration() {
    try {
      // Load configuration properties.
      File file = new File("./configuration/config.properties");
      FileInputStream fis = new FileInputStream(file);
      properties = new Properties();
      properties.load(fis);

      File overrides = new File("./configuration/config.override.properties");
      if( overrides.exists()){
        FileInputStream ois = new FileInputStream(overrides);
        properties.load(ois);
      }

    } catch (Exception e) {
      System.out.println("Exception:" + e.getMessage());
    }
  }

  /**
   * GetHostName()
   *
   * Reads the environment.hostname.* properties to determine the host name to use
   * when retrieving pages for testing. The value returned is affected by
   *
   * environment.hostname.active from config.properties file.
   * environment.hostname.active from config.override.properties file.
   * Command line environment property definition.
   *
   * @return String containing the host name pages should be loaded from.
   */
  public String getHostName() {
    String key = "environment.hostname." + properties.getProperty("environment.active");
    return properties.getProperty(key);
  }

  public String getDataFileBasePath(){
    return properties.getProperty("filepath.testdatabase");
  }

  /**
   * getEnvironmentName()
   *
   * @return a String containing the name of the environment being tested.
   */
  public String getEnvironmentName(){
    return properties.getProperty("environment.active");
  }

  /**
   * Retrieves the dimensions for the small responsive layout.
   *
   * @return Breakpoint object containing the width and height.
   */
  public Breakpoint getSmallBreakpoint() {
    return getBreakpoint("layout.screensize.small");
  }

  /**
   * Retrieves the dimensions for the medium responsive layout.
   *
   * @return Breakpoint object containing the width and height.
   */
  public Breakpoint getMediumBreakpoint() {
    return getBreakpoint("layout.screensize.medium");
  }

  /**
   * Retrieves the dimensions for the large responsive layout.
   *
   * @return Breakpoint object containing the width and height.
   */
  public Breakpoint getLargeBreakpoint() {
    return getBreakpoint("layout.screensize.large");
  }

  /**
   * Retrieves the dimensions for the extra-large responsive layout.
   *
   * @return Breakpoint object containing the width and height.
   */
  public Breakpoint getXLargeBreakpoint() {
    return getBreakpoint("layout.screensize.xlarge");
  }

  private Breakpoint getBreakpoint(String sizeKey) {
    int width  = Integer.parseInt(properties.getProperty(sizeKey + ".width"));
    int height = Integer.parseInt(properties.getProperty(sizeKey + ".height"));

    return new Breakpoint(width, height);
  }

  /**
   * Returns the configured browser string.
   *
   * @return String containing one of the supported web browsers
   */
  public String getBrowser() {
    String browser = properties.getProperty("browser.active");
    if(browser != null)
      browser = browser.trim().toLowerCase();

    return browser;
  }

  /**
   * Retrieves the identified URL from the configuration file and returns a
   * version modified to reflect the current environment.
   *
   * @param pageURL Identifier for a specific page URL.
   */
  public String getPageURL(String pageURL) {

    String configUrl = properties.getProperty(pageURL);
    try {
      URL oldUrl = new URL(configUrl);
      URL modifiedURl = new URL(oldUrl.getProtocol(), getHostName(), oldUrl.getFile());
      return modifiedURl.toString();
    } catch (MalformedURLException e) {
      throw new RuntimeException(
          String.format("Config entry '%s' does not contain a valid URL. Found: '%s'.", pageURL, configUrl));
    }
  }

  /**
   * Whey didn't you just go home? That's your home! Are you too good for your
   * home? Answer me!
   *
   * @return homePage URL (String)
   */
  public String goHome() {
    return getPageURL("HomePage");
  }

  public String getDriverPath(String driverPath) {
    return properties.getProperty(driverPath);
  }

  /**
   * Gets the base driver path from the configuration properties file
   *
   * @return The base path for the drivers
   */
  public String getDriverBasePath() {
    if (SystemUtils.IS_OS_WINDOWS) {
      return properties.getProperty("DriverPath_Win");
    } else if (SystemUtils.IS_OS_MAC_OSX) {
      return properties.getProperty("DriverPath_Mac");
    } else if (SystemUtils.IS_OS_LINUX) {
      return properties.getProperty("DriverPath_Linux");
    } else {
      throw new RuntimeException("Could not get base driver path, unknown OS");
    }
  }

  /**
   * Gets the driver name (geckodriver) based on the selected driver
   * (FirefoxDriver)
   *
   * @param driver
   * @return
   */
  public String getDriverName(String driver) {
    return properties.getProperty("DriverName_" + driver);
  }

  public String getExtentReportPath() {
    return properties.getProperty("ExtentReports");
  }

  public String getScreenShotsPath() {
    return properties.getProperty("ScreenShots");
  }

  public String getProperty(String property) {
    return properties.getProperty(property);
  }

}
