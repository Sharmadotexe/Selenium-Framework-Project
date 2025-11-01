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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class StandAlone extends BaseTest {
//    String productin = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"purchaseOrder"})
    public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {


        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("pass"));

        List<WebElement> products = productCatalogue.getProductsList();

        //calling addProductToCart By Name function
        productCatalogue.addProductToCart(input.get("productin"));
        CartPage cartPage = productCatalogue.goToCartPage();


        Boolean match = cartPage.VerifyProduct(input.get("productin"));
        Assert.assertTrue(match);

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.actionMethod("India");
        ConfirmationPage confirmationPage = checkoutPage.goToConfirmation();

        String confirmTxt = confirmationPage.verifyConfirmationMessage();
        Assert.assertTrue(confirmTxt.equalsIgnoreCase("Thankyou for the order."));
    }


    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistory() {
        //Zara Coat 3

        ProductCatalogue productCatalogue = landingPage.loginApplication("vikas.sh@gmail.com", "Test@123");
        OrderPage orderPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(orderPage.VerifyOrderDisplay("ZARA COAT 3"));
    }


//    @DataProvider
//    public Object [][] getData(){
//       return new Object [][] {{"vikas.sh@gmail.com","Test@123","ZARA COAT 3"},{"rockvikas300@gmail.com","Test@123456","ADIDAS ORIGINAL"}};
//    }

    @DataProvider
    public Object[][] getData() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("email", "vikas.sh@gmail.com");
        map.put("pass", "Test@123");
        map.put("productin", "ZARA COAT 3");

        HashMap<String, String> map1 = new HashMap<String, String>();
        map.put("email", "rockvikas300@gmail.com");
        map.put("pass", "Test@123456");
        map.put("productin", "ADIDAS ORIGINAL");

        return new Object[][] {{map}, {map1}};
    }
}