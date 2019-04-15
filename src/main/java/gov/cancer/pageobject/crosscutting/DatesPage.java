package gov.cancer.pageobject.crosscutting;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;

/**
 * Pseudo page object representing any page in the system. The class is used
 * solely for verifying attributes of a page's Dates Display Text
 */
public class DatesPage extends PageObjectBase {

  @FindBy(how = How.CSS, using = ".document-dates")
  WebElement dateDisplay;

  // Date Label values
  final public String Label_updated = "Updated:";
  final public String Label_updatedEsp = "Actualización:";

  // Date Label element
  final public String dateLabelText = ".document-dates ul li strong";

  // DateTime element
  WebElement dateTime;

  /**
   * Constructor
   *
   * @param path server-relative path of the page to load.
   */
  public DatesPage(String path) {
    super(path);

    // Get the DateTime section, if it's present.
    List<WebElement> findList = getBrowser().findElements(By.cssSelector("[datetime]"));
    if (findList.size() > 0)
      dateTime = findList.get(0);
    else
      dateTime = null;
  }

  /*
   * Returns true if DateTime Field is displayed on the page, false otherwise
   */
  public boolean isDateFieldVisible() {
    return dateTime != null && dateTime.isDisplayed();
  }

  /* Returns Datetime as text sting */
  public String getDateTime() {
    // System.out.println(dateTime.getText());
    return dateTime.getText();

  }

  /* Returns Date Label as text sting */
  public String getDateLabelText() {
    String dateLabel = ElementHelper.findElement(dateDisplay, dateLabelText).getText();
    // System.out.println(dateLabel);
    return dateLabel;
  }

  /* Returns the label text based on language */
  public String getDateLabelDisplay() {
    String label = null;
    String dateLabel = ElementHelper.findElement(dateDisplay, dateLabelText).getText();

    if (dateLabel.equals(Label_updated)) {
      label = "Updated:";
    } else if (dateLabel.equals(Label_updatedEsp)) {
      label = "Actualización:";
    }
    return label;
  }

}
