package gov.cancer.pageobject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;

import gov.cancer.framework.ParsedURL;
import gov.cancer.framework.BrowserManager;
import gov.cancer.framework.Configuration;

/**
 * This is the root base class for all page objects. If a page object class
 * implments all the functionality of a given page, this class provides the
 * common core functionality of providing a parsed page URL, waiting for
 * navigation to complete, and so forth.
 * <p>
 * Page objects are intended to capture the state of the browser at the time
 * they were created. Navigation events (e.g. a form submission) explicitly do
 * *NOT* update the page. Instead, a new page object, representing the target
 * page should be returned by any method which performs navigation.
 */
public abstract class PageObjectBase {

  WebDriver browser;
  Configuration config = new Configuration();

  // Allow for a 20 second delay. Because network delays can be insane.
  static final int MAX_PAGE_LOAD_DELAY = 20;


  /**
   * Interface for passing actions which will cause the page to change. Used with
   * expectUrlChange.
   *
   * @see expectUrlChange
   */
  public interface IPageChangeAction {
    void operation();
  }

  /**
   * Constructor
   *
   */
  public PageObjectBase(String path) {
    String fullUrl = MakeUrl(path);

    browser = BrowserManager.GetBrowser();
    browser.get(fullUrl);
    PageFactory.initElements(this.getBrowser(), this);
  }

  /**
   * Default constructor. This is not used, but allows subclasses to exist without
   * a default constructor.
   */
  protected PageObjectBase() {

  }

  /**
   * Set up a version of the IDisposable pattern, but following Java conventions;
   * mainly naming it close() instead of Dispose(). For more, see
   * https://docs.microsoft.com/en-us/dotnet/api/system.idisposable?view=netframework-4.7.2
   */
  private boolean closed = false;

  public final void close(){
    close(true);
  }

  protected void close(boolean closing) {
    if(!this.closed) {
      if(closing) {
        // Clean up of additional disposable objects would go here.
      }

      // Close the browser window.
      browser.close();

      this.closed = true;
    }

  }

  /**
   * Finalizer. Don't rely on this getting called, make sure you
   * call close() instead to make sure things get cleaned up.
   */
  @Override
  protected void finalize() throws Throwable {
    try {
      close(false);
    } finally {
      super.finalize();
    }
  }

  protected WebDriver getBrowser() {
    return this.browser;
  }


  /**
   * Gets a URL object representing the current page's URL.
   *
   * @return an instance of ParsedURL representing the page URL at the time the
   *         page object was instantiated.
   * @throws UnsupportedEncodingException
   * @throws MalformedURLException
   */
  public ParsedURL getPageUrl() {
    return new ParsedURL(this.browser.getCurrentUrl());
  }

  /**
   * Returns the current page's title.
   *
   * @return A String representation of the page's title at the time the page
   *         object was created.
   */
  public String getPageTitle() {
    return browser.getTitle().trim();
  }


  /**
   * Performs an action which causes navigation, resulting in the browser moving
   * to a new URL (typically some variety of form submission). This method blocks
   * until the browser has loaded the new page *and* the resulting document is
   * ready to be used (i.e. It blocks until after document.ready has fired on the
   * new page.)
   * <p>
   * In practice, the action parameter is an anonymous method, taking no
   * parameters and returning no result. The actual code to trigger the event goes
   * in the method body. e.g.
   *
   * <pre>
   * expectUrlChange(() -> {
   *   form_Search.submit();
   * })
   * </pre>
   *
   * @param action A function taking no parameters with no return value.
   */
  public void expectUrlChange(IPageChangeAction action) {

    String oldURL = this.browser.getCurrentUrl();

    // Perform the action which will cause a page change.
    action.operation();

    // Wait for the page URL to change.
    new WebDriverWait(this.browser, MAX_PAGE_LOAD_DELAY).until(driver -> {
      return !driver.getCurrentUrl().equals(oldURL);
    });

    // Wait for the page initialization to complete and the document.ready
    // event to fire.
    new WebDriverWait(this.browser, MAX_PAGE_LOAD_DELAY).until(
        webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
  }

  /**
   * Turns a file path into a URL for the configured test environment.
   *
   * @param path The file path, and optional querystring, of a page URL.
   * @return String containing a fully qualified URL.
   */
  private String MakeUrl(String path) {

    String host = config.getHostName();
    String url;

    if(path.startsWith("/"))
      url = host + path;
    else
      url = host + "/" + path;

    return url;
  }
}
