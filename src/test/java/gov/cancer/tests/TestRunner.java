package gov.cancer.tests;

import java.lang.reflect.InvocationTargetException;

import gov.cancer.pageobject.PageObjectBase;

/**
 * Helper class to encapsulate the logic of instantiating a page object and enforcing
 * clean up regardless of the tests' potential failure modes.
 *
 * @param <T> Any page object class which extends PageObjectBase.
 */
public class TestRunner<T extends PageObjectBase> {

  /**
   * Interface for defining the callback (lambda) function used by PerformTest().
   *
   * @param <T> Any page object class which extends PageObjectBase. (The same T as the class.)
   */
  public interface ITestAction<T>{
    /**
     * Defines a callback method which will perform
     * @param page
     */
    void test(T page);
  }

  /**
   * Static method for encapuslating the the logic of instantiating a page object
   * and enforcing that the object is cleaned up afterward. This is main piece for
   * guaranteeing that we don't end up wiht a bunch of zombie browser windows open
   * after a test fails.
   *
   * @param classImplementation A Class object. Typically passed along the lines
   *                            of {@code}MyPageObjectClass.class{@code}
   * @param path                The path of the URL to be tested. (e.g.
   *                            /types/lung)
   * @param callback            Callback method containing the tests to be
   *                            executed.
   */
  public static <T extends PageObjectBase> void run(Class<T> classImplementation, String path, ITestAction<T> callback) {

    try {
      /**
       * Find the constructor of the passed class which takes a String, and then create
       * a new instance, using the path parameter.
       *
       * Based on https://stackoverflow.com/a/300013/282194
       */
      T page = classImplementation.getConstructor(String.class).newInstance(path);

      try {
        // Invoke the callback.
        callback.test(page);
      } finally {
        // Regardless of what happens in the test, close the page object.
        page.close();
      }

      /**
       * Technically, the calls to getConstructor() and newInstance() are capable of
       * throwing various exceptions. In reality, because T is constrained to be a
       * subclass of PageObjectBase, we know that the constructor exists.  Because
       * of this, these specific exceptions may be safely swallowed.
       */
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
        | NoSuchMethodException | SecurityException e) {
      e.printStackTrace();
    }

  }

}
