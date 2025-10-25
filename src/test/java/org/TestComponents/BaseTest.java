package org.TestComponents;

import org.example.LandingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    WebDriver driver;

    public WebDriver initializeDriver() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\org\\Resources\\GlobalData\\GlobalData.properties");
        properties.load(fileInputStream);

        //getting browser name
        String browserName = properties.getProperty("browser");


        if(browserName.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        }

        else if(browserName.equalsIgnoreCase("firefox")){
         driver = new FirefoxDriver();
        }

        else if(browserName.equalsIgnoreCase("edge")){
         driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public LandingPage launchLandingPage() throws IOException {
        driver = initializeDriver();
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }



}
