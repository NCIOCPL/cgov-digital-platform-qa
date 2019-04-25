package gov.cancer.framework;

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
   * @param width
   *          screen width in pixels.
   * @param height
   *          screen height in pixels.
   */
  public Breakpoint(int width, int height) {
    this.width = width;
    this.height = height;
  }

  /**
   * GetWidth().
   *
   * @return the desired width of the browser window.
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * GetHeight().
   *
   * @return the desired height of the browser window.
   */
  public int getHeight() {
    return this.height;
  }
}
