package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    By checkoutBtn = By.cssSelector(".totalRow button");

    @FindBy(xpath = "//div[@class='cartSection']/h3")
    List<WebElement> names;

    public boolean VerifyProduct(String productName){
       return names.stream().anyMatch(name -> name.getText().equalsIgnoreCase(productName));
    }

    public CheckoutPage goToCheckout(){
        driver.findElement(checkoutBtn).click();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        return checkoutPage;
    }








}
