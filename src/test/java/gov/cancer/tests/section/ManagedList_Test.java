package gov.cancer.tests.section;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.PageWithManagedList;
import gov.cancer.pageobject.section.ManagedListItem;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

public class ManagedList_Test extends TestObjectBase {
	/**
	 * This method is checking if the ManagedList section exists on the pages
	 *
	 * @param path Path of the page to check.
	 */
	@Test(dataProvider = "getPagesWithManagedLists")
	public void verifyManagedListSectionIsPresent(String path, int minLength, int maxLength) {
		TestRunner.run(PageWithManagedList.class, path, (PageWithManagedList page) -> {
			Assert.assertTrue(page.isSectionPresent(), "ManagedList Section is not present");
		});
	}

	/**
	 * This method is checking the count of all items of the ManagedList section
	 *
	 * @param path Path of the page to check.
	 * @pram expectedTitleCount Number of Titles on the managed List Section
	 */
	@Test(dataProvider = "getPagesWithManagedListTitles")
	public void verifyListItemCount(String path, int expectedTitleCount) {
		TestRunner.run(PageWithManagedList.class, path, (PageWithManagedList page) -> {
			Assert.assertEquals(page.getListOfItems().size(), expectedTitleCount, "The list item count does not match");
		});
	}

	/**
	 * This method is checking if the correct ManagedList Title is displayed on
	 * pages
	 *
	 * @param path              Path of the page to check.
	 * @param expectedTitleText The Title to check.
	 * @param index             The index of the title.
	 */
	@Test(dataProvider = "getManagedListsTitleText")
	public void isListItemTitleCorrect(String path, String expectedTitleText, int index) {
		TestRunner.run(PageWithManagedList.class, path, (PageWithManagedList page) -> {
			List<ManagedListItem> managedList = page.getListOfItems();
			ManagedListItem listmanage = managedList.get(index);
			Assert.assertEquals(listmanage.getTitle().getText(), expectedTitleText,"List Item Title is not correct");
		});
	}

	/**
	 * This method is checking if the length of description text is in considerable
	 * range
	 *
	 * @param path      Path of the page to check.
	 * @param minLength minimum expected length.
	 * @param maxLength maximum expected length.
	 */
	@Test(dataProvider = "getPagesWithManagedLists")
	public void verifyManagedListDescriptionTextLength(String path, int minLength, int maxLength) {
		TestRunner.run(PageWithManagedList.class, path, (PageWithManagedList page) -> {
			List<ManagedListItem> listDesc = page.getListOfItems();
			// Are there any List Descriptions in the ManagedList section?
			// If there are no List Descriptions at all no statement will get executed after
			// this line
			Assert.assertTrue(listDesc.size() > 0);
			// For each managed list execute the following assertion.
			for (ManagedListItem item : listDesc) {
				Assert.assertTrue(
						(item.getDescriptionText().length() > minLength
								&& item.getDescriptionText().length() < maxLength),
						"ManagedList text is in range of 20 to 300 chars");
			}
		});
	}

	/**
	 * This method is checking if the source of the image is correct
	 *
	 * @param path                Path of the page to check.
	 * @param expectedImageSource The expected image source.
	 * @param expectedImageFile   The expected image file.
	 * @param index               the index of the image on the managed list.
	 */
	@Test(dataProvider = "getImageSource")
	public void verifyManagedListThumbnailImageSource(String path, String expectedImageSource, String expectedImageFile,
			int index) {
		TestRunner.run(PageWithManagedList.class, path, (PageWithManagedList page) -> {
			List<ManagedListItem> managedList = page.getListOfItems();
			ManagedListItem listThumbnailImage = managedList.get(index);
			// Use StartsWith method and endswith to check the image path till the date
			// folder(e.g. 2019-11) location
			Assert.assertTrue(listThumbnailImage.getImagePath().startsWith(expectedImageSource),
					"ManagedList Thumbnail Image source is not correct");
			Assert.assertTrue(listThumbnailImage.getImagePath().endsWith(expectedImageFile),
					"ManagedList Thumbnail Image File name is not correct");
		});
	}

	/**
	 * This method is checking if the correct alt text is displayed for Thumbnail
	 * Image
	 *
	 * @param path            Path of the page to check.
	 * @param expectedAltText Alternative text of Image.
	 * @param index           The index of the thumbnail image.
	 */
	@Test(dataProvider = "getPagesWithManagedLists_Alttext")
	public void verifyManagedListImageAltText(String path, String expectedAltText, int index) {
		TestRunner.run(PageWithManagedList.class, path, (PageWithManagedList page) -> {
			List<ManagedListItem> managedList = page.getListOfItems();
			ManagedListItem listThumbnailImage = managedList.get(index);
			Assert.assertEquals(listThumbnailImage.getImage().getAltText(), expectedAltText,"The Alt text is not correct");
		});
	}

	/**
	 * This method is checking if the internal link of the title point to correct
	 * URL
	 *
	 * @param path             Path of the page to check.
	 * @param expectedTitleUrl The expected Title URL.
	 * @param index            The index of the Title links on the managed section.
	 */
	@Test(dataProvider = "getLinkPaths")
	public void verifyManagedListsInternalLinks(String path, String expectedTitleUrl, int index) {
		TestRunner.run(PageWithManagedList.class, path, (PageWithManagedList page) -> {
			List<ManagedListItem> managedList = page.getListOfItems();
			ManagedListItem listLink = managedList.get(index);
			Assert.assertTrue(listLink.getTitle().getUrl().toString().endsWith(expectedTitleUrl), "Expected link is not correct");
		});
	}

	/**
	 * This method is checking if the external link of the title point to correct
	 * URL and the exit disclaimer points to correct URL
	 *
	 * @param path                    Path of the page to check.
	 * @param externalLinkCount       Expected number of external links.
	 * @param externalLinkIndex       The index of managed list item.
	 * @param exitNotificationLink    Expected exit notification link.
	 */
	@Test(dataProvider = "getPageExternalManagedListLinks")
	public void verifyManagedListsExternalLinks(String path, int externalLinkIndex,
			String exitNotificationLink) {
		TestRunner.run(PageWithManagedList.class, path, (PageWithManagedList page) -> {
			List<ManagedListItem> managedList = page.getListOfItems();
			// Execute following assertions for external Links.
			Assert.assertEquals(managedList.get(externalLinkIndex).getExternalLinkNotification().getUrl().getPath(),
					exitNotificationLink, "Exit disclaimer link is not correct");
		});
	}

	/**
	 * This method is checking if the Managed List section exists does NOT exist on
	 * the pages
	 *
	 * @param path Path of the page to check.
	 */
	@Test(dataProvider = "getPagesWithoutManagedLists")
	public void verifyManagedListsSectionIsAbsent(String path) {
		TestRunner.run(PageWithManagedList.class, path, (PageWithManagedList page) -> {
			Assert.assertFalse(page.isSectionPresent(), "Error: Managed List Section is present");
		});
	}

	/*******************************************
	 * DATA PROVIDER
	 *******************************************/
	/**
	 * Retrieves a list of paths to pages which are expected to have Managed List
	 *
	 * @return An iterable list of one element array, containing a path
	 *
	 */
	@DataProvider(name = "getPagesWithManagedLists")
	public Iterator<Object[]> getPagesWithManagedList() {
		String[] columns = { "path" };
		Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("managedlist-data.xlsx"),
				"page_with_list_description", columns);
		List<Object[]> converted = new ArrayList<Object[]>();
		int min = 20;
		int max = 300;
		while (values.hasNext()) {
			Object[] item = values.next();
			converted.add(new Object[] { item[0], min, max });
		}
		return converted.iterator();
	}

	/**
	 * Retrieves a list of paths to pages which are expected to have Titles in the
	 * Managed List
	 *
	 * @return An iterable list of two element arrays, each containing a path and
	 *         expectedTitleCount.
	 */
	@DataProvider(name = "getPagesWithManagedListTitles")
	public Iterator<Object[]> getPagesWithManagedListTitles() {
		String[] columns = { "path", "expectedTitleCount" };
		Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("managedlist-data.xlsx"),
				"page_with_list_description", columns);
		List<Object[]> converted = new ArrayList<Object[]>();
		while (values.hasNext()) {
			Object[] item = values.next();
			String count = (String) item[1];
			int expectedCount = Integer.parseInt(count);
			converted.add(new Object[] { item[0], expectedCount });
		}
		return converted.iterator();
	}

	/**
	 * Retrieves a list of paths to pages which are expected to have Titles in
	 * Managed Lists
	 *
	 * @return An iterable list of three element arrays, each containing a path and
	 *         expectedTitleText and index.
	 */
	@DataProvider(name = "getManagedListsTitleText")
	public Iterator<Object[]> getManagedListTitleText() {
		String[] columns = { "path", "expectedTitleText", "index" };
		Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("managedlist-data.xlsx"),
				"page_with_list_description", columns);
		List<Object[]> converted = new ArrayList<Object[]>();
		while (values.hasNext()) {
			Object[] item = values.next();
			String headerText = (String) item[1];
			if (!headerText.equals("null")) {
				String expectedIndex = (String) item[2];
				int expectedIndexNumber = Integer.parseInt(expectedIndex);
				converted.add(new Object[] { item[0], item[1], expectedIndexNumber });
			}
		}
		return converted.iterator();
	}

	/**
	 * Retrieves a list of paths to pages which are expected to have Image Source in
	 * the Thumbnail image of the ManagedList
	 *
	 * @return An iterable list of four element arrays, each containing a
	 *         path,expectedImageSource expectedImageFile, index
	 *
	 */
	@DataProvider(name = "getImageSource")
	public Iterator<Object[]> getImageSource() {
		String[] columns = { "path", "expectedImageSource", "expectedImageFile", "index" };
		Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("managedlist-data.xlsx"),
				"page_with_list_description", columns);
		List<Object[]> converted = new ArrayList<Object[]>();
		while (values.hasNext()) {
			Object[] item = values.next();
			String count = (String) item[3];
			int expectedCount = Integer.parseInt(count);
			converted.add(new Object[] { item[0], item[1], item[2], expectedCount });
		}
		return converted.iterator();
	}

	/**
	 * Retrieves a list of paths to pages which are expected to have Thumbnail Image
	 * with Alt text
	 *
	 * @return An iterable list of three element arrays, each containing a path,
	 *         expectedAltText and index
	 *
	 */
	@DataProvider(name = "getPagesWithManagedLists_Alttext")
	public Iterator<Object[]> getPagesWithManagedLists_Alttext() {
		String[] columns = { "path", "expectedAltText", "index" };
		Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("managedlist-data.xlsx"),
				"page_with_list_description", columns);
		List<Object[]> converted = new ArrayList<Object[]>();
		while (values.hasNext()) {
			Object[] item = values.next();
			String altText = (String) item[1];
			if (!altText.equals("null")) {
				String expectedIndex = (String) item[2];
				int expectedIndexNumber = Integer.parseInt(expectedIndex);
				converted.add(new Object[] { item[0], item[1], expectedIndexNumber });
			}
		}
		return converted.iterator();
	}

	/**
	 * Retrieves a list of paths to pages which are expected to have internal Links
	 * in the title of the ManagedList
	 *
	 * @return An iterable list of three element arrays, each containing a path,
	 *         expectedTitleUrl, index.
	 *
	 */
	@DataProvider(name = "getLinkPaths")
	public Iterator<Object[]> getLinkPaths() {
		String[] columns = { "path", "expectedTitleUrl", "index" };
		Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("managedlist-data.xlsx"),
				"page_with_list_description", columns);
		List<Object[]> converted = new ArrayList<Object[]>();
		while (values.hasNext()) {
			Object[] item = values.next();
			String titleUrl = (String) item[1];
			if (!titleUrl.equals("null")) {
				String expectedIndex = (String) item[2];
				int expectedIndexNumber = Integer.parseInt(expectedIndex);
				converted.add(new Object[] { item[0], item[1], expectedIndexNumber });
			}
		}
		return converted.iterator();
	}

	/**
	 * Retrieves a list of paths to pages which are expected to have External links
	 * in the ManagedList
	 *
	 * @return An iterable list of three element arrays, each containing a path,
	 *         externalLinkIndex, externalManagedListLink, exitNotificationLink.
	 *
	 */
	@DataProvider(name = "getPageExternalManagedListLinks")
	public Iterator<Object[]> getPageExternalManagedListLinks() {
		String[] columns = { "path", "externalLinkIndex", "exitNotificationLink" };
		Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("managedlist-data.xlsx"),
				"page_with_list_description", columns);
		List<Object[]> converted = new ArrayList<Object[]>();
		while (values.hasNext()) {
			Object[] item = values.next();
			String externalLinkIndex = (String) item[1];
			String exitNotificationLink = (String) item[2];
			if (!externalLinkIndex.equals("null")) {
				int expectedIndex = Integer.parseInt(externalLinkIndex);
				converted.add(new Object[] { item[0], expectedIndex, exitNotificationLink });
			}
		}
		return converted.iterator();
	}

	/**
	 * Retrieves a list of paths to pages which are expected NOT to have List
	 * Descriptions
	 *
	 * @return An iterable list of one element array, each containing path
	 *
	 */
	@DataProvider(name = "getPagesWithoutManagedLists")
	public Iterator<Object[]> getPagesWithoutManagedLists() {
		String[] columns = { "path" };
		return new ExcelDataReader(getDataFilePath("managedlist-data.xlsx"), "page_without_list_description", columns);
	}
}
