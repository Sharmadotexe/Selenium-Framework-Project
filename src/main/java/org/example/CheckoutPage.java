package org.example;

import org.AbstractComponents.AbstractComponents;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponents {
    WebDriver driver;

    public CheckoutPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "input[placeholder='Select Country']")
    WebElement element;

    @FindBy(css = ".btnn.action__submit")
    WebElement actionBtn;


    public void actionMethod(String country) throws InterruptedException {
        element.click();
        element.sendKeys(country);
        Thread.sleep(1000);
        element.sendKeys(Keys.DOWN);
        element.sendKeys(Keys.DOWN);
        element.sendKeys(Keys.ENTER);
    }


    public ConfirmationPage goToConfirmation() {
        actionBtn.click();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }
}
