package gov.cancer.framework;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;
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
   * Create a new web driver for given browser and set that browser's options
   *
   * @param browserName name of the browser
   * @param url         URL to open
   * @return WebDriver driver
   */
  public static WebDriver GetBrowser() {

    Configuration config = new Configuration();
    String browserName = config.getBrowser();

    WebDriver driver = null;
    // Turn off Selenium noise logging for all browsers.
    // This should really be in a config file, but for now...
    java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);


    if (browserName.equalsIgnoreCase("chrome")) {
      WebDriverManager.chromedriver().setup();

      System.setProperty("webdriver.chrome.silentOutput", "true");
      java.util.logging.Logger.getLogger("org.openqa.selenium.remote.ProtocolHandshake").setLevel(Level.SEVERE);

      driver = new ChromeDriver();
    } else if (browserName.equalsIgnoreCase("firefox")) {
      WebDriverManager.firefoxdriver().setup();

      java.util.logging.Logger.getLogger("org.openqa.selenium.remote.ProtocolHandshake").setLevel(Level.SEVERE);
      System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
      System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

      driver = new FirefoxDriver();

    }

    /* Headless Chrome */
    else if (browserName.equalsIgnoreCase("chromeheadless")) {
      WebDriverManager.chromedriver().setup();

      java.util.logging.Logger.getLogger("org.openqa.selenium.remote.ProtocolHandshake").setLevel(Level.SEVERE);
      System.setProperty("webdriver.chrome.silentOutput", "true");

      ChromeOptions options = new ChromeOptions();
      options.addArguments("headless");
      options.addArguments("window-size=1200x600"); // Testing large screens: Breakpoint 1024px
      driver = new ChromeDriver(options);
    }

    /* Headless Firefox */
    else if (browserName.equalsIgnoreCase("firefoxheadless")) {
      WebDriverManager.firefoxdriver().setup();

      java.util.logging.Logger.getLogger("org.openqa.selenium.remote.ProtocolHandshake").setLevel(Level.SEVERE);
      System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
      System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

      driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
    } else if (browserName.equalsIgnoreCase("iphone6")) {
      WebDriverManager.chromedriver().setup();
      System.setProperty("webdriver.chrome.silentOutput", "true");
      java.util.logging.Logger.getLogger("org.openqa.selenium.remote.ProtocolHandshake").setLevel(Level.SEVERE);

      Map<String, Object> mobileEmulation = new HashMap<>();
      mobileEmulation.put("deviceName", "iPhone 6");
      ChromeOptions chromeOptions = new ChromeOptions();
      chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
      driver = new ChromeDriver(chromeOptions);
    } else if (browserName.equalsIgnoreCase("ipad")) {

      WebDriverManager.chromedriver().setup();
      System.setProperty("webdriver.chrome.silentOutput", "true");
      java.util.logging.Logger.getLogger("org.openqa.selenium.remote.ProtocolHandshake").setLevel(Level.SEVERE);

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
   * @param browserName name of the browser
   * @param url         URL to open
   * @return WebDriver driver TODO: create headless Chrome driver TODO: reuse
   * startBrowser where possible
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

    // Disable info messages on console
    System.setProperty("webdriver.chrome.silentOutput", "true");
    System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
    System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");


    if (browserName.equalsIgnoreCase("Chrome")) {

      WebDriverManager.chromedriver().setup();

      chromeOptions.setCapability(CapabilityType.PROXY, seleniumProxy);
      driver = new ChromeDriver(chromeOptions);
      driver.manage().window().maximize();
      driver.get(url); // open proxy page
    } else if (browserName.equalsIgnoreCase("Firefox")) {
      WebDriverManager.firefoxdriver().setup();

      firefoxOptions.setCapability(CapabilityType.PROXY, seleniumProxy);
      driver = new FirefoxDriver(firefoxOptions);
      driver.manage().window().maximize();
      driver.get(url);
    } else if (browserName.toLowerCase().contains("headless")) {
      // Firefox/Geckdo driver are the same
      WebDriverManager.firefoxdriver().setup();

      FirefoxBinary firefoxBinary = new FirefoxBinary();
      firefoxBinary.addCommandLineOptions("--headless");
      firefoxOptions.setBinary(firefoxBinary);
      firefoxOptions.setCapability(CapabilityType.PROXY, seleniumProxy);
      driver = new FirefoxDriver(firefoxOptions);
      driver.manage().window().maximize();
      driver.get(url);
    } else if (browserName.equalsIgnoreCase("ChromeHeadless")) {
      WebDriverManager.chromedriver().setup();
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

