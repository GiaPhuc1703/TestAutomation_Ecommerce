package dev.selenium.hooks;

import dev.selenium.configs.DriverFactory;
import static dev.selenium.utils.constant.AUTOMATION_EXERCISE_URL;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(AUTOMATION_EXERCISE_URL);
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
