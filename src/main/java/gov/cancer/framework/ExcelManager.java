package gov.cancer.framework;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.NoSuchElementException;

/**
 * Consider using ExcelDataReader instead.
 */
@Deprecated
public class ExcelManager {


  public String path;
  public FileInputStream fis = null;
  public FileOutputStream fileOut = null;
  private XSSFWorkbook workbook = null;
  private XSSFSheet sheet = null;
  private XSSFRow row = null;
  private XSSFCell cell = null;

  public static ExcelManager load(String path){
    return new ExcelManager(path);
  }

  public ExcelManager(String path) {
    this.path = path;
    try {
      fis = new FileInputStream(path);
      workbook = new XSSFWorkbook(fis);
      sheet = workbook.getSheetAt(0);
      fis.close();
    } catch (Exception e) {
      // Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Retrieves a single worksheet from the Excel workbook.
   *
   * @param worksheetName Name of the worksheet to retrieve.
   * @return If worksheetName exists, an ExcelWorksheet object. Otherwise, throws
   *         NoSuchElementException with the name of the missing worksheet.
   */
  public ExcelWorksheet getWorkSheet(String worksheetName) {
    XSSFSheet sheet = workbook.getSheet(worksheetName);
    if(sheet == null)
      throw new NoSuchElementException(String.format("Cannot find worksheet '%s'.", worksheetName));

    return new ExcelWorksheet(sheet);
  }

  // This method is to get the total row count of the sheet

  public int getRowCount(String sheetName) {
    int index = workbook.getSheetIndex(sheetName);
    if (index == -1)
      return 0;
    else {
      sheet = workbook.getSheetAt(index);
      int number = sheet.getLastRowNum() + 1;
      return number;
    }

  }

  /*
   * This method returns the data from a cell by providing the Sheet name, Column
   * name and Row number.
   */

  @SuppressWarnings("deprecation")
  public String getCellData(String sheetName, String colName, int rowNum) {
    try {
      if (rowNum <= 0)
        return "";

      int index = workbook.getSheetIndex(sheetName);
      int col_Num = -1;
      if (index == -1)
        return "";

      sheet = workbook.getSheetAt(index);
      row = sheet.getRow(0);
      for (int i = 0; i < row.getLastCellNum(); i++) {
        // System.out.println(row.getCell(i).getStringCellValue().trim());
        if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
          col_Num = i;
      }
      if (col_Num == -1)
        return "";

      sheet = workbook.getSheetAt(index);
      row = sheet.getRow(rowNum - 1);
      if (row == null)
        return "";
      cell = row.getCell(col_Num);

      if (cell == null)
        return "";
      // System.out.println(cell.getCellType());
      if (cell.getCellType() == CellType.STRING)
        return cell.getStringCellValue();
      else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {

        String cellText = String.valueOf(cell.getNumericCellValue());
        if (HSSFDateUtil.isCellDateFormatted(cell)) {
          // format in form of M/D/YY
          double d = cell.getNumericCellValue();

          Calendar cal = Calendar.getInstance();
          cal.setTime(HSSFDateUtil.getJavaDate(d));
          cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
          cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;
          // System.out.println(cellText);

        }

        return cellText;
      } else if (cell.getCellType() == CellType.BLANK)
        return "";
      else
        return String.valueOf(cell.getBooleanCellValue());

    } catch (Exception e) {

      e.printStackTrace();
      return "row " + rowNum + " or column " + colName + " does not exist in xls";
    }
  }

  /*
   * This method returns the data from a cell by providing the Sheet name, Column
   * name and Row number.
   */

  public String getCellData(String sheetName, int colNum, int rowNum) {

    try {
      if (rowNum <= 0)
        return "";

      if(colNum < 1)
        return "";

      // Adjust to zero-based indexes.
      int accessRowNum = rowNum -1;
      int accessColNum = colNum - 1;

      int index = workbook.getSheetIndex(sheetName);
      sheet = workbook.getSheetAt(index);
      row = sheet.getRow(accessRowNum);
      if (row == null)
        return "";
      cell = row.getCell(accessColNum);

      if (cell == null)
        return "";
      // System.out.println(cell.getCellType());
      if (cell.getCellType() ==CellType.STRING)
        return cell.getStringCellValue();
      else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {

        String cellText = String.valueOf(cell.getNumericCellValue());
        if (HSSFDateUtil.isCellDateFormatted(cell)) {
          // format in form of M/D/YY
          double d = cell.getNumericCellValue();

          Calendar cal = Calendar.getInstance();
          cal.setTime(HSSFDateUtil.getJavaDate(d));
          cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
          cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;
          // System.out.println(cellText);

        }

        return cellText;
      } else if (cell.getCellType() ==CellType.BLANK)
        return "";
      else
        return String.valueOf(cell.getBooleanCellValue());

    } catch (Exception e) {

      e.printStackTrace();
      return "row " + rowNum + " or column " + colNum + " does not exist in xls";
    }
  }

  // This method is to find whether sheet exists and returns True or False
  public boolean isSheetExist(String sheetName) {
    int index = workbook.getSheetIndex(sheetName);
    if (index == -1) {
      index = workbook.getSheetIndex(sheetName.toUpperCase());
      if (index == -1)
        return false;
      else
        return true;
    } else
      return true;
  }

  // This method returns number of columns in a sheet by providing the sheet
  // name.
  public int getColumnCount(String sheetName) {
    // check if sheet exists
    if (!isSheetExist(sheetName))
      return -1;

    sheet = workbook.getSheet(sheetName);
    row = sheet.getRow(0);

    if (row == null)
      return -1;

    return row.getLastCellNum();

  }

}
