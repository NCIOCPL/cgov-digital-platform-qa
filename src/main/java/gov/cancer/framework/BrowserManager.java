package gov.cancer.framework;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.client.ClientUtil;

public class BrowserManager {

  /**
   * @param config
   * @param driver
   * @return String driverPath
   */
  private static String getDriverPath(Configuration config, String driver) {
    // Get the base path for the OS
    String driverPath = config.getDriverBasePath();

    // Get the driver name itself
    driverPath += "/" + config.getDriverName(driver);

    if (SystemUtils.IS_OS_WINDOWS) {
      driverPath += ".exe";
    }

    return driverPath;
  }

  /**
   * Create a new web driver for given browser and set that browser's options
   *
   * @param browserName
   * name of the browser
   * @param url
   *          URL to open
   * @return WebDriver driver
   */
  public static WebDriver GetBrowser() {

    Configuration config = new Configuration();
    String browserName = config.getBrowser();

    WebDriver driver = null;

    // Turn off Selenium noise logging for all browsers.
    // This should really be in a config file, but for now...
    java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(java.util.logging.Level.OFF);

    if (browserName.equalsIgnoreCase("chrome")) {
      WebDriverManager.chromedriver().setup();
      driver = new ChromeDriver();
    }

    else if (browserName.equalsIgnoreCase("firefox")) {
      WebDriverManager.firefoxdriver().setup();
      driver = new FirefoxDriver();
    }

    /* Headless Chrome */
    else if (browserName.equalsIgnoreCase("chromeheadless")) {

      WebDriverManager.chromedriver().setup();
      ChromeOptions options = new ChromeOptions();
      options.addArguments("headless");
      options.addArguments("window-size=1200x600"); // Testing large screens: Breakpoint 1024px
      driver = new ChromeDriver(options);
    }

    /* Headless Firefox */
    else if (browserName.equalsIgnoreCase("firefoxheadless")) {
      WebDriverManager.firefoxdriver().setup();
      driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
    }

    else if (browserName.equalsIgnoreCase("iphone6")) {
      WebDriverManager.chromedriver().setup();
      Map<String, Object> mobileEmulation = new HashMap<>();
      mobileEmulation.put("deviceName", "iPhone 6");
      ChromeOptions chromeOptions = new ChromeOptions();
      chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
      driver = new ChromeDriver(chromeOptions);
    }

    else if (browserName.equalsIgnoreCase("ipad")) {

      WebDriverManager.chromedriver().setup();

      Map<String, Object> mobileEmulation = new HashMap<>();
      mobileEmulation.put("deviceName", "iPad");

      ChromeOptions chromeOptions = new ChromeOptions();
      chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

      driver = new ChromeDriver(chromeOptions);
    }

    // Set up an implicit driver wait for FindElements (declared once, applied to all elements implicitly).
    // Value is configured through configuration.properties
    driver.manage().timeouts().implicitlyWait(config.getImplicitTimeout(), TimeUnit.SECONDS);

    return driver;
  }

  /**
   * Create a proxy web driver for a given browser and URL.<br/>
   * Modified from
   * https://github.com/lightbody/browsermob-proxy#using-with-selenium
   *
   * @param browserName
   *          name of the browser
   * @param url
   *          URL to open
   * @return WebDriver driver TODO: create headless Chrome driver TODO: reuse
   *         startBrowser where possible
   */
  public static WebDriver GetProxyBrowser(String browserName, Configuration config, String url, BrowserMobProxy bmp) {

    WebDriver driver = null;


    ChromeOptions chromeOptions = new ChromeOptions();
    FirefoxOptions firefoxOptions = new FirefoxOptions();

    // Get the Selenium proxy object and configure it as a desired capability
    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(bmp);
    DesiredCapabilities capabilities = new DesiredCapabilities();

    // Turn off Selenium logging for all browsers.
    // This should really be in a config file, but for now...
    java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(java.util.logging.Level.OFF);

    if (browserName.equalsIgnoreCase("Chrome")) {

      WebDriverManager.chromedriver().setup();

      /**
       * this could be the right config for WebDriverManager if capabilities don't function
       *
       * WebDriverManager.chromedriver().proxy(String proxy).setup();
       */
//      String driverFullPath = getDriverPath(config, "ChromeDriver");
//      System.setProperty("webdriver.chrome.driver", driverFullPath);
//      System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");

      chromeOptions.setCapability(CapabilityType.PROXY, seleniumProxy);
      driver = new ChromeDriver(chromeOptions);
      driver.manage().window().maximize();
      driver.get(url); // open proxy page
    } else if (browserName.equalsIgnoreCase("Firefox")) {
      String driverFullPath = getDriverPath(config, "FirefoxDriver");
      System.setProperty("webdriver.gecko.driver", driverFullPath);

      firefoxOptions.setCapability(CapabilityType.PROXY, seleniumProxy);
      driver = new FirefoxDriver(firefoxOptions);
      driver.manage().window().maximize();
      driver.get(url);
    } else if (browserName.toLowerCase().contains("headless")) {
      // Firefox/Geckdo driver are the same
      String driverFullPath = getDriverPath(config, "FirefoxDriver");
      System.setProperty("webdriver.gecko.driver", driverFullPath);

      FirefoxBinary firefoxBinary = new FirefoxBinary();
      firefoxBinary.addCommandLineOptions("--headless");
      firefoxOptions.setBinary(firefoxBinary);
      firefoxOptions.setCapability(CapabilityType.PROXY, seleniumProxy);
      driver = new FirefoxDriver(firefoxOptions);
      driver.manage().window().maximize();
      driver.get(url);
    } else if (browserName.equalsIgnoreCase("ChromeHeadless")) {
      String driverFullPath = getDriverPath(config, "ChromeDriver");
      System.setProperty("webdriver.chrome.driver", driverFullPath);
      System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");

      // TODO: handle NoSuchElementException
      // chromeOptions.addArguments("headless");
      // chromeOptions.addArguments("window-size=1200x600"); // Testing large screens:
      // Breakpoint 1024px
      chromeOptions.setCapability(CapabilityType.PROXY, seleniumProxy);
      driver = new ChromeDriver(chromeOptions);
      driver.manage().window().maximize();
      driver.get(url);
    } else {
      throw new IllegalArgumentException("Invalid browser type; check configuration settings.");
    }

    return driver;
  }

}

