package gov.cancer.framework;

import org.openqa.selenium.WebDriver;

/**
 * Interface for defining the resize().
 *
 * @param <T>
 *          Any page object class which extends PageObjectBase. (The same T as
 *          the class.)
 */

public interface IResize<T> {
  void resize(WebDriver browser);
}
