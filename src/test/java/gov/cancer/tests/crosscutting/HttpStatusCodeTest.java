package gov.cancer.tests.crosscutting;


import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.HttpProtocolPage;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class contains tests related to HTTP connection features, such as status code.
 *
 */
public class HttpStatusCodeTest extends TestObjectBase {

  /**
   * This test verifies received status code from get request
   * @param path
   * @param statusCode
   */
  @Test(dataProvider = "getPathAndStatusCode")
  public void verifyStatusCode(String path, int statusCode) {
    TestRunner.runRequestSender(HttpProtocolPage.class, path, (HttpProtocolPage page) -> {
      //verify status code
      Assert.assertEquals(page.getResponse().getStatusCode(), statusCode);
    });

  }


  /**
   * Retrieves the page path and the status code
   *
   * @return
   */
  @DataProvider(name = "getPathAndStatusCode")
  public Iterator<Object[]> getPathAndStatusCode() {
    String[] columns = {"path", "statusCode"};
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("http-status-code-data.xlsx"), "pages", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String code = (String) item[1];
      int expCode = Integer.parseInt(code);
      converted.add(new Object[]{item[0], expCode});
    }
    return converted.iterator();


  }

}
