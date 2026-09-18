package dev.selenium.stepdefinitions;

import dev.selenium.configs.DriverFactory;
import dev.selenium.pages.LoginPage;
import org.testng.Assert;
import static dev.selenium.utils.constant.LOGIN_EMAIL;
import static dev.selenium.utils.constant.LOGIN_PASSWORD;
import static dev.selenium.utils.constant.AUTOMATION_EXERCISE_TITLE;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Ecommerce {
    private LoginPage loginPage;

    private WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    private LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(getDriver());
        }
        return loginPage;
    }

    @Given("I am on the landing page.")
    public void i_am_on_the_landing_page() {
        Assert.assertTrue(getDriver().getTitle().equals(AUTOMATION_EXERCISE_TITLE));
    }

    @Then("User clicks on login button.")
    public void user_clicks_on_login_button() {
        getLoginPage().clickLogin1();
    }

    @Then("User enters email and password.")
    public void user_enters_email_and_password() {
        getLoginPage().enterEmail(LOGIN_EMAIL);
        getLoginPage().enterPassword(LOGIN_PASSWORD);
    }

    @Then("User click Login button.")
    public void user_click_login_button() {
        getLoginPage().clickLogin2();
    }

    @Then("User validates if login is success.")
    public void user_validates_if_login_is_success() {
        Assert.assertTrue(getLoginPage().isLoginSuccessfully());
        System.out.println("User logged in " + getLoginPage().isLoginSuccessfully());
    }

    @Then("User performs validation on the home page.")
    public void user_performs_validation_on_the_home_page() {
        Assert.assertTrue(getLoginPage().isValidateOnProductHomePage());
    }

    @Then("User clicks on View Product.")
    public void user_clicks_on_view_product() {
        getLoginPage().clickviewProduct();
    }

    @Then("User performs validation on product description page.")
    public void user_performs_validation_on_product_description_page() {
        Assert.assertTrue(getLoginPage().isValidateProductDescription());
    }

    @Then("User clicks on add to cart.")
    public void user_clicks_on_add_to_cart() {
        getLoginPage().enterQuantity();
        getLoginPage().clickAddToCartButton();
    }

    @Then("User validates the pop up.")
    public void user_validates_the_pop_up() {
        Assert.assertTrue(getLoginPage().validatePopUpAddToCart());
    }

    @When("User clicks on view cart.")
    public void user_clicks_on_view_cart() {
        getLoginPage().clickviewCart();
    }

    @Then("User performs validation on the cart page.")
    public void user_performs_validation_on_the_cart_page() {
        Assert.assertTrue(getLoginPage().validateOnTheCartPage());
    }

    @When("User clicks on Proceed to checkout.")
    public void user_clicks_on_proceed_to_checkout() {
        getLoginPage().ClickProcessToCheckOut();
    }

    @Then("User performs validation on checkout page.")
    public void user_performs_validation_on_checkout_page() {
        Assert.assertTrue(getLoginPage().validateOnTheAddressPage());
    }

    @When("User clicks on Place order button.")
    public void user_clicks_on_place_order_button() {
        getLoginPage().ClickPlaceOrderButton();
    }

    @Then("User enters the payment details.")
    public void user_enters_the_payment_details() {
        getLoginPage().enterThePaymentDetails();
    }

    @Then("User clicks on Pay and Confirm button.")
    public void user_clicks_on_pay_and_confirm_button() {
        getLoginPage().clickPayAndConfirmOderButton();
    }

    @Then("User performs validation on the Congratulations page.")
    public void user_performs_validation_on_the_congratulations_page() {
        getLoginPage().validateOrderSuccesfully();
    }

}
