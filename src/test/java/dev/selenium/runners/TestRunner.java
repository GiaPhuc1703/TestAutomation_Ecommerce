package dev.selenium.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/dev/selenium/resources/features", glue = {
        "dev.selenium.stepdefinitions",
        "dev.selenium.hooks"
}, plugin = {
        "pretty",
        "html:target/cucumber-reports/report.html"
})
public class TestRunner {

}
