package Selenium.Locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class LocateMultipleElements {
    @Test

    public void multipleElement(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://amazon.com");
        WebElement searchBar = driver.findElement(By.id("twotabsearchtextbox"));
        Actions action = new Actions(driver);
        action.sendKeys(searchBar,"iphone 12" + Keys.ENTER).perform();

        List<WebElement> prices = driver.findElements(By.xpath("//span[@class='a-price']"));

        /*
        FIND:
        1. prices with $ sign
        2. prices with ','
        3. empty prices
        4. prices without $ sign

 */
        int totalPrice = 0;
        for (WebElement elementPrice : prices) {
            //    1. prices with $ sign - remove $ sign
            String temp = elementPrice.getText();
            if (temp.startsWith("$")){
                temp = temp.substring(1);
            }

           //   2. prices with ',' - remove ',' comma
            if (temp.contains(",")){
                temp = temp.replace(",","");
            }

            // 3. empty prices
            if (temp.isEmpty()){
                continue;
            }

            if (temp.contains("\n")){
                temp = temp.replace("\n",".");
            }

            totalPrice += Double.parseDouble(temp);


        }
        System.out.println(totalPrice);


    }


    @Test

    public void getAllProductNames(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://amazon.com");
        WebElement searchBar = driver.findElement(By.id("twotabsearchtextbox"));
        Actions action = new Actions(driver);
        action.sendKeys(searchBar,"sumsung" + Keys.ENTER).perform();

        List<WebElement> productNameList = driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
        List<String >productNames = new ArrayList<>();

        for (WebElement product : productNameList){
            String  temp = product.getText();
            productNames.add(temp);

        }

        System.out.println(productNames);


    }

}
