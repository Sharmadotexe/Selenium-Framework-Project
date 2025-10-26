package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage {
    WebDriver driver;

    public OrderPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//tr/td[2]")
    List<WebElement> productNames;

    public boolean VerifyOrderDisplay(String productName){
       return productNames.stream().anyMatch(name -> name.getText().equalsIgnoreCase(productName));
    }
}