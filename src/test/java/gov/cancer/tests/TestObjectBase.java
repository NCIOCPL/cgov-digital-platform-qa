package gov.cancer.tests;

import java.lang.reflect.Method;
import org.apache.log4j.varia.NullAppender;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import gov.cancer.framework.Configuration;

public abstract class TestObjectBase {

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

  //Print out the test class name
  @BeforeClass(alwaysRun = true)
  public void beforeClass() {
    //suppress Log4J info warning
    org.apache.log4j.BasicConfigurator.configure(new NullAppender());
    System.out.println("\n  Running test: " + this.getClass().getSimpleName());
  }

  // Print out the name of the method before each is run
  // ------------------------------------------------------
  @BeforeMethod(alwaysRun = true)
  public void beforeMethod(Method method) {

    System.out.println(String.format("\tExecuting %s()", method.getName()));
  }

}
