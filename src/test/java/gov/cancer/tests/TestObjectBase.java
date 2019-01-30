package gov.cancer.tests;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import gov.cancer.framework.Configuration;
import gov.cancer.framework.ScreenShot;
import gov.cancer.pageobject.PageObjectBase;

public abstract class TestObjectBase {

  // private static Logger log=
  // LogManager.getLogger(BaseClass.class.getName());
  private static ExtentReports report;
  private static ExtentTest logger;
  private Configuration config;

  public TestObjectBase() {
    this.config = new Configuration();
  }

  protected String getDataFilePath(String filename) {
    return (config.getDataFileBasePath() + System.getProperty("file.separator") + filename);
  }

  /**
   * @return the config
   * @deprecated Does this really need to be exposed?  Shouldn't it be possible
   * to push configuration down to this class?
   */
  @Deprecated
  protected Configuration getConfig() {
    return config;
  }

  @BeforeTest(alwaysRun = true)
  public void beforeTest() {

    String environment = config.getEnvironmentName();

    String dateTime = new SimpleDateFormat("yyyy-MM-dd HH-mm-SS").format(new Date());
    String extentReportPath = config.getExtentReportPath();
    String fileName = environment.toUpperCase() + "-" + dateTime + ".html";

    report = new ExtentReports(extentReportPath + fileName);
    report.addSystemInfo("Environment", environment);
  }

  // Setting up path and filename for the log file
  // Printing location of log file and environment variables
  // -------------------------------------------------------
  @BeforeClass(alwaysRun = true)
  public void beforeClass() {

    logger = report.startTest(this.getClass().getSimpleName());
    System.out.println("\n  Running test: " + this.getClass().getSimpleName());
  }

  // Print out the name of the method before each is run
  // ------------------------------------------------------
  @BeforeMethod(alwaysRun = true)
  public void beforeMethod(Method method) {

    System.out.println(String.format("\tExecuting %s()", method.getName()));
  }

  // If a method fails, log the result and take a screenshot of the page
  // -------------------------------------------------------------------
  @AfterMethod(alwaysRun = true)
  public void tearDown(ITestResult result) throws InterruptedException {
    if (result.getStatus() == ITestResult.FAILURE) {

      // TODO: move capture screenshot to the page object
      //String screenshotPath = ScreenShot.captureScreenshot(driver, result.getName());
      //String image = logger.addScreenCapture(screenshotPath);

      //logger.log(LogStatus.FAIL, image + "Fail => " + result.getName());
    } else if (result.getStatus() == ITestResult.SKIP) {
      logger.log(LogStatus.SKIP, "Skipped => " + result.getName());
    } else {
      logger.log(LogStatus.PASS, "Passed => " + result.getName());
    }
    report.flush();
  }

  @AfterClass(alwaysRun = true)
  public void afterClass() {
    report.endTest(logger);
  }

}
