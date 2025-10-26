package org.example;

import org.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidations extends BaseTest {

    @Test(groups = {"ErrorHandling"})
    public void LoginErrorValidation() throws InterruptedException, IOException {
        String productin = "ZARA COAT 3";
        landingPage.loginApplication("vikas.sh@gmail.com","Test@1234");
        Assert.assertEquals(landingPage.getErrorMessage(),"Incorrect email or password.");
    }

    @Test
    public void ProductErrorValidation() throws InterruptedException, IOException {
        String productin = "ZARA COAT 3";
        String productVerify = "ZARA COAT 33";
        ProductCatalogue productCatalogue = landingPage.loginApplication("vikas.sh@gmail.com","Test@123");
        List<WebElement> products =  productCatalogue.getProductsList();

        //calling addProductToCart By Name function
        productCatalogue.addProductToCart(productin);
        CartPage cartPage = productCatalogue.goToCartPage();


        Boolean match = cartPage.VerifyProduct(productVerify);
        Assert.assertFalse(match);
    }

}
