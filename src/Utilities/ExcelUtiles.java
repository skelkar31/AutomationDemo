package Utilities;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelUtiles {

    private static XSSFSheet ExcelWSheet;

    private static XSSFWorkbook ExcelWBook;

    private static XSSFCell Cell;

    private static XSSFRow Row;

    public static void setExcelFile(String Path,String SheetName) throws Exception {



        // Open the Excel file

        FileInputStream ExcelFile = new FileInputStream(Path);

        // Access the required test data sheet

        ExcelWBook = new XSSFWorkbook(ExcelFile);

        ExcelWSheet = ExcelWBook.getSheet(SheetName);



    }

    public static String getCellData(int Rownum,int Colnum)	{

        Cell = ExcelWSheet.getRow(Rownum).getCell(Colnum);
        String getCellData = Cell.getStringCellValue();
        return getCellData;


    }
    public static int getlastrow() throws Exception {

        int lastrow = ExcelWSheet.getLastRowNum();
        return lastrow;
    }
}







