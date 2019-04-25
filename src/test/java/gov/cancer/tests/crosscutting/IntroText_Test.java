package gov.cancer.tests.crosscutting;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.PageWithIntroText;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

/**
 * Tests for the Intro Text field.
 */
public class IntroText_Test extends TestObjectBase {

  /**
   * Verify that the "Intro Text" section is rendered on pages where the Intro
   * Text field was set.
   *
   * @param path Path of the page to check.
   */
  @Test(dataProvider = "getPagesWithIntroText")
  public void verifyIntroTextIsVisible(String path) {

    TestRunner.run(PageWithIntroText.class, path, (PageWithIntroText page) -> {

      Assert.assertTrue(page.sectionIsVisible(), "Intro text is displayed.");

    });
  }


  /**
   * Verify that the "Intro Text" section is not rendered on pages where the
   * Intro Text field was not set.
   *
   * @param path Path of the page to check.
   */
  @Test(dataProvider = "getPagesWithoutIntroText")
  public void verifyIntroTextIsNotPresent(String path) {

    TestRunner.run(PageWithIntroText.class, path, (PageWithIntroText page) -> {
      Assert.assertFalse(page.sectionIsVisible(), "Intro text section not displayed.");

    });
  }

  /**
   * Verify that the correct translation is used when displaying the intro text
   * field.
   *
   * @param path            Path of the page to check.
   * @param language        The language used on the page.
   * @param translationText A snippet of text which occurs on the expected
   *                        translation but does not appear on alternate
   *                        translations.  NOTE: this text must not include
   *                        tags in the target markup.
   */
  @Test(dataProvider = "getPagesWithTranslations")
  public void verifyCorrectTranslation(String path, String translationText) {

    TestRunner.run(PageWithIntroText.class, path, (PageWithIntroText page) -> {
      Assert.assertTrue(page.sectionContainsText(translationText), "Correct translation found");

    });
  }

  /*******************************************
   * DATA PROVIDERS
   *******************************************/


  /**
   * Retrieves a list of paths for pages where the Intro Text field was not set
   * and the Intro Text section is therefore not expected to appear.
   *
   * @return An iterator over a collection of object arrays containing the
   *         requested data.
   */
  @DataProvider(name = "getPagesWithoutIntroText")
  public Iterator<Object[]> getPagesWithoutIntroText() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("intro_text-field.xlsx"), "pages_without_intro_text", columns);
  }

  /**
   * Retrieves a list of paths for pages where the Intro Text field was set and
   * the page is therefore expected to be present.
   *
   * @return An iterator over a collection of object arrays containing the
   *         requested data.
   */
  @DataProvider(name = "getPagesWithIntroText")
  public Iterator<Object[]> getPagesWithIntroText() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("intro_text-field.xlsx"), "pages_with_intro_text", columns);
  }

  /**
   * Retrieves a list of paths for pages where the Intro Text field was set and
   * the page is therefore expected to be present.
   *
   * @return An iterator over a collection of object arrays containing the
   *         requested data.
   */
  @DataProvider(name = "getPagesWithTranslations")
  public Iterator<Object[]> getPagesWithTranslations() {
    String[] columns = { "path", "translated_text" };
    return new ExcelDataReader(getDataFilePath("intro_text-field.xlsx"), "pages_with_intro_text", columns);
  }

}
