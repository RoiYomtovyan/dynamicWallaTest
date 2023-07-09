package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class MainPage {

    public static WebDriver driver;

    public MainPage(WebDriver driver){

        this.driver = driver;
    }


    static By finance = By.xpath("//a[@href=\"https://finance.walla.co.il\"]");
    static By euro = By.xpath("//li//h3[text()[contains(.,'אירו €')]]");
    static By euroValueElement = By.xpath("/html/body/div[2]/div/section[3]/section[2]/ul/li[7]/div[1]");



    public void goToFinancePage (){
        driver.findElement(finance).click();
    }

    public String euroValue (){
        driver.findElement(euro);
        String euroValue = driver.findElement(euroValueElement).getText();
        System.out.println(euroValue);
        return euroValue;
    }




}
