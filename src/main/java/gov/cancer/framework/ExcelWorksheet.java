package gov.cancer.framework;

import java.io.FileInputStream;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.NoSuchElementException;

/**
 * Presents a single Excel worksheet as a randomly accessible collection of
 * (row, column) pairs. Cell values are returned as Strings containing the
 * cell's value, formatted as it would be viewed in the Excel user interface.
 *
 * The first row (row 0) is assumed to contain column names, allowing cells in
 * individual rows to be accessed with a human-friendly name.
 */
class ExcelWorksheet {

  XSSFSheet worksheet;

  // Total number of rows.
  int rowCount;

  // Total number of cells. Based on header row.
  int colCount;

  // Mapping of column names to offsets.
  HashMap<String, Integer> ordinals;

  /**
   * Constructor
   * @param worksheet a valid XSSFSheet object.
   */
  public ExcelWorksheet(XSSFSheet worksheet){
    this.worksheet = worksheet;
    this.rowCount = this.worksheet.getLastRowNum() + 1;
    this.colCount = this.worksheet.getRow(0).getLastCellNum() + 1;

    this.ordinals = ExcelWorksheet.retrieveOrdinals(this.worksheet);
  }

  /**
   * Reports the number of rows in the worksheet.
   *
   * @return Integer.
   */
  public int getRowCount(){
    return this.rowCount;
  }

  /**
   * Reports the number of columns in the worksheet (based on the header row).
   *
   * @return Integer.
   */
  public int getColumnCount(){
    return this.colCount;
  }

  /**
   * Retrieve an individual cell using a human-friendly column name.
   *
   * @param rowNum  The zero-based row number to be fetched. Must be non-negative
   *                but less than the number of rows in the worksheet.
   * @param colName String containing the column name. Must be non-null and
   *                non-empty.
   * @return A String containing the cell's value, formatted as it would be viewed
   *         in the Excel user interface.
   */
  public String getCellText(int rowNum, String colName){
    String text = "";

    if (rowNum < 0 || rowNum >= this.rowCount )
      throw new IndexOutOfBoundsException(String.format("Row number (%d) is out of range.", rowNum));
    if(colName == null || colName.trim().length() == 0)
      throw new IllegalArgumentException("Column name must not be empty.");

    if(this.ordinals.containsKey(colName)) {
      int colNumber = this.ordinals.get(colName);
      text = this.getCellText(rowNum, colNumber);
    }

    return text;
  }

  /**
   * Retrieve an individual cell using a (row,column) pair.
   *
   * @param rowNum  The zero-based row number to be fetched. Must be non-negative
   *                but less than the number of rows in the worksheet.
   * @param colName The zero-based column number to be fetched. Must be
   *                non-negative but less than the number of columns in the
   *                worksheet.
   * @return A String containing the cell's value, formatted as it would be viewed
   *         in the Excel user interface.
   */
  public String getCellText(int rowNum, int colNum) {
    String text ="";

    if (rowNum < 0 || colNum < 0 || rowNum >= this.rowCount || colNum >= this.colCount )
      throw new IndexOutOfBoundsException(String.format("Cell (%d,%d) is out of range.", rowNum, colNum));

    XSSFRow row = worksheet.getRow(rowNum);
    XSSFCell cell = row.getCell(colNum, MissingCellPolicy.RETURN_BLANK_AS_NULL);

    if(cell != null) {
      DataFormatter formatter = new DataFormatter();
      text = formatter.formatCellValue(cell);
    }

    return text;
  }


  public static ExcelWorksheet loadWorksheet(String workbookName, String worksheetName){

    if(workbookName == null || workbookName.trim().length() == 0)
      throw new IllegalArgumentException("workbookName must not be empty.");
    if (worksheetName == null || worksheetName.trim().length() == 0)
      throw new IllegalArgumentException("worksheetName must not be empty.");

    XSSFWorkbook workbook = loadWorkBook(workbookName);
    XSSFSheet sheet = workbook.getSheet(worksheetName);
    if (sheet == null)
      throw new NoSuchElementException(String.format("Cannot find worksheet '%s'.", worksheetName));

    return new ExcelWorksheet(sheet);

  }

  private static XSSFWorkbook loadWorkBook(String workbookName) {
    try {
      FileInputStream fis = new FileInputStream(workbookName);
      XSSFWorkbook workbook = new XSSFWorkbook(fis);
      fis.close();
      return workbook;
    } catch (Exception e) {
      throw new RuntimeException(String.format("Error opening file '%s'.", workbookName), e);
    }

  }

  /**
   * Scans the first row of the worksheet for column headers to create a mapping
   * between column names and offsets into the row.
   *
   * If a given cell is empty, is skipped.
   *
   * @param worksheet
   * @return Mapping of column names to indices.
   */
  private static HashMap<String,Integer> retrieveOrdinals(XSSFSheet worksheet){
    HashMap<String,Integer> map = new HashMap<String,Integer>();

    DataFormatter formatter = new DataFormatter();
    XSSFRow headers = worksheet.getRow(0);
    int cellCount = headers.getLastCellNum() + 1;

    for (int i = 0; i < cellCount; i++) {
      XSSFCell cell = headers.getCell(i, MissingCellPolicy.RETURN_BLANK_AS_NULL);

      // If a given cell is empty, skip it.
      if( cell == null)
        continue;

      String name = formatter.formatCellValue(cell);
      map.put(name, i);
    }

    return map;
  }

}
