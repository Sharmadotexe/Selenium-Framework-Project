package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.TestComponents.BaseTest;
import org.example.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinitions extends BaseTest {
    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        landingPage = launchLandingPage();
    }


    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_with_username_and_pass(String username, String password){
        productCatalogue = landingPage.loginApplication(username,password);
    }

    @When("^I add the (.+) from Cart$")
    public void i_add_the_product_from_cart(String productName) throws InterruptedException {
        List<WebElement> products = productCatalogue.getProductsList();
        productCatalogue.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_product_submit_order(String productName) throws InterruptedException {
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();


        Boolean match = cartPage.VerifyProduct(productName);
        Assert.assertTrue(match);

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.actionMethod("India");
        confirmationPage = checkoutPage.goToConfirmation();
    }

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmation_page(String string){
        String confirmTxt = confirmationPage.verifyConfirmationMessage();
        Assert.assertTrue(confirmTxt.equalsIgnoreCase(string));
    }
}
