package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

public class StandAlone {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");

        String productin = "ZARA COAT 3";

        driver.findElement(By.id("userEmail")).sendKeys("vikas.sh@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Test@123");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));


        List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));

        WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productin)).findFirst().orElse(null);

        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        //applying explicit wait before clicking the cart button after adding products
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
//        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();


       List<WebElement> names = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));

        Boolean ans = names.stream().anyMatch(name -> name.getText().equalsIgnoreCase(productin));

        Assert.assertTrue(ans);

        driver.findElement(By.cssSelector(".totalRow button")).click();

        WebElement elee  = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));

        elee.click();
        elee.sendKeys("ind");
        Thread.sleep(1000);
        elee.sendKeys(Keys.DOWN);
        elee.sendKeys(Keys.DOWN);
        elee.sendKeys(Keys.ENTER);

        driver.findElement(By.cssSelector(".btnn.action__submit")).click();
        String confirmTxt = driver.findElement(By.cssSelector(".hero-primary")).getText();

        Assert.assertTrue(confirmTxt.equalsIgnoreCase("Thankyou for the order."));

        driver.close();
    }

}
