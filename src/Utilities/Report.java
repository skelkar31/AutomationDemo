package Utilities;

import POJO.Table;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import config.ActionKeyword;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Report {


    static ExtentHtmlReporter htmlReporter;
    static ExtentReports extent;
    static ExtentTest logger;
    static String filepath = "/Users/swapnilkelkar/Desktop/TestReport/";
    public static WebDriver driver;
    static String TestName = null;


    public static void generateReport(int Row, int LRow) throws IOException {


        if (Row == 1) {

            System.out.println(" In Generate Report::" + Row);

            Date now = new Date();
            String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(now);

            //htmlReporter= new ExtentHtmlReporter("/Users/swapnilkelkar/Desktop/TestReport/"+"FBM_" +timestamp+".html");
            htmlReporter = new ExtentHtmlReporter(filepath + "FBM_" + timestamp + ".html");


            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            htmlReporter.config().setChartVisibilityOnOpen(true);
            htmlReporter.config().setDocumentTitle("FBM Report");
            htmlReporter.config().setReportName("FBM Automation Report");
            htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
            htmlReporter.config().setTheme(Theme.DARK);
            htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
            htmlReporter.config().setCSS("css-string");
            htmlReporter.config().setEncoding("utf-8");
            htmlReporter.config().setJS("js-string");


        }
        TestName = Table.GetDataMap().get("TestName");

        testCase();

        getResults();

        if (Row == LRow) {
            teardown();

        }

        String CapScreen = (Table.GetDataMap().get("ActionKey"));

//        if (CapScreen.equals("SCREENSHOT"))
//        {
//
//            if(driver ==null) {
//                driver = new ChromeDriver();
//            }
//            String screenShotPath = getScreenshot(driver, "screenShotName");
//
//            logger.log(Status.PASS, "Snapshot below: " + logger.addScreenCaptureFromPath(screenShotPath));
//
//
//        }

    }


    public static void testCase() {
        // TODO Auto-generated method stub

        try {

            String NewTestName = null;


            if (Table.DataMap.containsKey("NewTestName")) ;
            {
                NewTestName = Table.GetDataMap().get("NewTestName");
            }

            System.out.println("----NewTestName------" + NewTestName);
            System.out.println("----OldTestName------" + TestName);


            if (TestName.equals(NewTestName)) {

                System.out.println("::::In testcase::::::");
                System.out.println("In IgnoreCase::" + TestName);
                Table.GetDataMap().put("NewTestName", TestName);


            } else {

                logger = extent.createTest(TestName);
                Table.GetDataMap().put("NewTestName", TestName);


            }
            System.out.println("After testcase::" + logger);
        } catch (Exception e) {
            System.out.println("Test case creation failed");

        }

    }

    public static void getResults() throws IOException {
        // TODO Auto-generated method stub

        logger.log(Status.INFO, "Test Description::\n" + (Table.GetDataMap().get("TestDesc")));
        logger.log(Status.INFO, "Action Key::\n" + (Table.GetDataMap().get("ActionKey")));
        logger.log(Status.INFO, "Test Date::\n" + (Table.GetDataMap().get("TestData")));
        logger.log(Status.PASS, "Status::\n" + (Table.GetDataMap().get("Status")));
        String CapScreen = (Table.GetDataMap().get("ActionKey"));
        if (CapScreen.equals("SCREENSHOT")) {

            System.out.println("\n In Capture Screen:::");
            String screenShotPath = getScreenshot(ActionKeyword.driver, TestName);
            logger.log(Status.PASS, "Snapshot below: " + logger.addScreenCaptureFromPath(screenShotPath));
        }


    }

    //  TODO:: Take the Screenshot and add it to report
    public static String getScreenshot(WebDriver driver, String screenShotName) throws IOException {
        // TODO Auto-generated method stub
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = filepath + screenShotName + "Timestamp_" + dateName + ".png";
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);
        return dest;


    }

    public static void teardown() {
        // TODO Auto-generated method stub

        extent.flush();

    }

}

