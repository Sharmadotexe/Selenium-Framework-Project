package org.example;

import org.AbstractComponents.AbstractComponents;
import org.TestComponents.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

public class StandAlone extends BaseTest {
    String productin = "ZARA COAT 3";

    @Test
    public void submitOrder() throws InterruptedException, IOException {


        ProductCatalogue productCatalogue = landingPage.loginApplication("vikas.sh@gmail.com","Test@123");

        List<WebElement> products =  productCatalogue.getProductsList();

        //calling addProductToCart By Name function
        productCatalogue.addProductToCart(productin);
        CartPage cartPage = productCatalogue.goToCartPage();


        Boolean match = cartPage.VerifyProduct(productin);
        Assert.assertTrue(match);

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.actionMethod("India");
        ConfirmationPage confirmationPage =  checkoutPage.goToConfirmation();

        String confirmTxt = confirmationPage.verifyConfirmationMessage();
        Assert.assertTrue(confirmTxt.equalsIgnoreCase("Thankyou for the order."));
    }


    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistory(){
        //Zara Coat 3

        ProductCatalogue productCatalogue = landingPage.loginApplication("vikas.sh@gmail.com","Test@123");
        OrderPage orderPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(orderPage.VerifyOrderDisplay(productin));
    }
}