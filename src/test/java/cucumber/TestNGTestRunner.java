package cucumber;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber", glue = "stepdefinitions", monochrome = true, plugin = {
        "pretty",
        "html:target/cucumber-report.html",
        "json:target/cucumber.json"
})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
