package org.example;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.io.FileReader;
import java.io.IOException;



public class SanityTests {

    public static WebDriver driver;

    MainPage mainPage= new MainPage(driver);

    // this will allow us to read the test configuration from a file
    static Object file;

    static {
        try {
            file = new JSONParser().parse(new FileReader("config.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    static JSONObject config = (JSONObject) file;

    @BeforeClass
    public static void start() throws Exception {
        System.setProperty(config.get("driverType").toString(), config.get("driverLocation").toString());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("w3c", true);
        driver = new ChromeDriver(options);


        // this code block is trying to open the web page
        boolean pageOpened = false;
        try {
            driver.navigate().to(config.get("url").toString());
            pageOpened = true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("webpage was not found " + e.getMessage());
            pageOpened = false;
        } finally {
            if (pageOpened) {
                System.out.println("Webpage opened successfully");

            }

        }

    }

    @Test
    public void SanityTest01_verify_Euro_value() {
        mainPage.goToFinancePage();
        String euroVALUE = mainPage.euroValue();
        assert config.get("currentEuroValue").toString().equals(euroVALUE);

    }


    @AfterClass
    public static void after (){
        driver.quit();

    }
}