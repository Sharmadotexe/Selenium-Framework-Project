package org.example;

import org.AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponents {
    WebDriver driver;

    public ProductCatalogue(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    @FindBy(css = ".col-lg-4")
    List<WebElement> products;

    //Selectors
    By elementBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");
    By overlayMask = By.cssSelector(".ngx-spinner-overlay");

    //method for getting all the products in a list
    public List<WebElement> getProductsList(){
        waitForElementToAppear(elementBy);
        return products;
    }

    //method for getting the specific webelement
    public WebElement getProductByName(String productin){
        WebElement reqProduct = products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productin)).findFirst().orElse(null);
        return reqProduct;
    }

    //method for clicking add to cart button for the specific product
    public void addProductToCart(String productName){
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(overlayMask);
    }


}
