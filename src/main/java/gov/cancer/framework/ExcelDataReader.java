package gov.cancer.framework;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Helper class to allow convenient iteration over the rows of an Excel file. An
 * instance of ExcelDataReader is suitable for use as the return object in a
 * TestNG data provider.
 *
 * Rows will be returned with columns in the same order as their names in the
 * constructor's columns parameter.
 *
 * Important: The first row of each worksheet being used for data *MUST* contain
 * column names.
 */
public class ExcelDataReader implements Iterator<Object[]> {

  ExcelWorksheet worksheet;

  String[] columns;

  // Row offsets are zero-based, but we want to skip over the
  // header row.
  int currentRow = 0;

  // The last row number in the worksheet. Zero-based.
  int lastRow;

  /**
   * Constructor.
   *
   * @param workbookName Filename and path of an Excel file.
   * @param worksheetName Name of the worksheet containing the desired data.
   * @param columns An array of Strings, containing the names of the columns to be
   * retrieved. Rows will be returned with their columns in the same order they
   * appear in the columns parameter.
   */
  public ExcelDataReader(String workbookName, String worksheetName, String[] columns) {

    this.worksheet = ExcelWorksheet.loadWorksheet(workbookName, worksheetName);
    this.columns = columns;
    this.lastRow = this.worksheet.getRowCount() - 1;
  }

  /**
   * {@inheritdoc}
   */
  @Override
  public boolean hasNext() {
    return currentRow < this.lastRow;
  }

  /**
   * {@inheritdoc}
   */
  @Override
  public Object[] next() {
    if(hasNext()){

      // Move to the next row.
      currentRow++;

      Object[] row = new Object[this.columns.length];
      for (int i = 0; i < this.columns.length; i++) {
        row[i] = this.worksheet.getCellText(currentRow, this.columns[i]);
      }

      return row;
    }
    throw new NoSuchElementException();
  }


}
