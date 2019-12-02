package config;

import POJO.ObjRepo;
import POJO.Table;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class ActionKeyword {

    public static WebDriver driver;
    public static String Status = null;

    public static <Webelement> void executeAction() {
        // TODO Auto-generated method stub
        String action = Table.GetDataMap().get("ActionKey");
        System.out.print("action" +action);

        switch(action) {

            case "LAUNCHAPP":

            {
                System.out.println("In Launchchaction");
                String broswername = null;
                String url = null;

                {
                    broswername = "chrome";
                    url = Table.GetDataMap().get("TestData");
                    Status = launchbrowser(broswername, url);
                    Table.GetDataMap().put("Status", Status);
                }

            }break;
            case "USERNAME":{

                driver.findElement(By.id(ObjRepo.USERNAME)).sendKeys(Table.GetDataMap().get("TestData"));
                Table.GetDataMap().put("Status", "User Name Entered Successfully");
            }
            break;
            case "PASSWORD":{

                driver.findElement(By.id("pass")).sendKeys(Table.GetDataMap().get("TestData"));
                Table.GetDataMap().put("Status", "User Name Entered Successfully");
            }
            break;
            case "CLICK":{

                driver.findElement(By.id("loginbutton")).click();
                Table.GetDataMap().put("Status", "User Logged In Successfully");


            }
            break;
            case "SCREENSHOT":{

                System.out.println("Take Screenshot");


            }
            break;
            case "CLOSE":{

                driver.close();
                Table.GetDataMap().put("Status", "Driver Closed Successfully");


            }
            break;

        }
    }


    public static String launchbrowser(String broswername, String url) {

        // TODO Auto-generated method stub
        try {
            System.out.print("In Lunch broswer");
            String driverpath = "/Users/swapnilkelkar/eclipse-workspace_Swapnil/OnlineStore/Chrome/";
            String driverfile = driverpath + "chromedriver";
            System.setProperty("webdriver.chrome.driver", driverfile);
            ChromeOptions o = new ChromeOptions();
            o.addArguments("disable-extensions");
            o.addArguments("disable-plugins");
            o.addArguments("--start-maximized");
            o.addArguments("--disable-notification");
            o.addArguments("disable-infobars");
            Map<String, Object> obj = new HashMap<String, Object>();
            obj.put("credentials_enable_service", false);
            obj.put("profile.password_manager_enabled", false);
            o.setExperimentalOption("prefs", obj);
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY,o);
            driver = new ChromeDriver(capabilities);
            //driver=new ChromeDriver();
            driver.get(url);
            Status = ("Browser Launched Successfully");

        } catch (Exception e) {

            Status = ("Browser Not Launched");
        }

        return Status;
    }
}

