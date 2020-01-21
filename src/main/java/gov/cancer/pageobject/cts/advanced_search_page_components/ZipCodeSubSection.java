package gov.cancer.pageobject.cts.advanced_search_page_components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.Component;
import gov.cancer.pageobject.components.TextField;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents sub section of Location section of Advanced Search Page
 * Only becomes available once ZipCode radio button had been selected
 */
public class ZipCodeSubSection extends Component {

  //main WebElement
  private WebElement scope;
  // ZioCode Text Field
  private TextField USZipCode;
  // dropdown for radius
  private Select radius;

  /**
   * Constructor
   *
   * @param element - component element
   */
  public ZipCodeSubSection(WebElement element) {
    super(element);
    scope = element;
    USZipCode = new TextField(ElementHelper.findElement(scope, "input#zip"));
    radius = new Select(ElementHelper.findElement(scope, "select#zipRadius"));

  }

  /**
   * getter for zipcode text field
   */
  public TextField getUSZipCodeField() {
    return USZipCode;
  }

  /**
   * Method returns all options from Radius dropdown
   */

  public List<String> getRadiusOptions() {
    List<WebElement> radiusOptions = radius.getOptions();
    List<String> radiusText = new ArrayList<String>();
    for (WebElement we : radiusOptions) {
      radiusText.add(we.getText());
    }
    return radiusText;
  }

  /**
   * Method return selected radious
   * NOTE : only one option can be selected at a time
   *
   * @return text of selected optiom
   */
  public String getSelectedRadiusOption() {
    if (radius.getAllSelectedOptions().size() == 1) {
      return radius.getAllSelectedOptions().get(0).getText();
    } else
      return null;
  }

  /**
   * Method to select Radius
   *
   * @param value to select
   */
  public void selectRadius(String value) {
    radius.selectByValue(value);
  }
}
