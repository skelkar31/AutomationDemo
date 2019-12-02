package MainPackage;

import POJO.Table;
import Utilities.ExcelUtiles;
import Utilities.Report;
import config.ActionKeyword;

public class Driver {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub

        String filepath = "/Users/swapnilkelkar/Desktop/TestCases.xlsx";

        // Call ExcelUtils to get Excel details
        ExcelUtiles.setExcelFile(filepath, "Sheet1");
        int LastRow = 0;

        LastRow=	ExcelUtiles.getlastrow();
        System.out.print("Last Row:::" +LastRow);


        for (int IRow = 1; IRow <= LastRow; IRow++) {
            Table.GetDataMap().put("TestName", ExcelUtiles.getCellData(IRow, 0));
            Table.GetDataMap().put("TestDesc", ExcelUtiles.getCellData(IRow, 2));
            Table.GetDataMap().put("ActionKey", ExcelUtiles.getCellData(IRow, 3));
            Table.GetDataMap().put("TestData", ExcelUtiles.getCellData(IRow, 4));

            System.out.println("Rows Count::" +IRow);

            System.out.println("Map Values" + Table.DataMap);

            ActionKeyword.executeAction();
            Report.generateReport(IRow,LastRow);

        }

    }

}