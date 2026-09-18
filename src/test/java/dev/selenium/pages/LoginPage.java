package dev.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dev.selenium.utils.BasePage;
import dev.selenium.utils.ElementUtils;

import org.testng.Assert;
import java.util.List;

public class LoginPage extends BasePage {
    private final ElementUtils elementUtils;

    // Locator
    private final By email = By.xpath("(//input[@name='email'])[1]");
    private final By password = By.xpath("//input[@name='password']");
    private final By login1 = By.xpath("//i[@class='fa fa-lock']");
    private final By login2 = By.xpath("//button[text()='Login']");
    private final By logout = By.xpath("//a[text()=' Logout']");
    private final By deleteAccount = By.xpath("//i[@class='fa fa-trash-o']");
    private final By viewProduct = By.xpath("//a[@href='/product_details/1']");
    private final By homePageTitle = By.xpath("//h2[text()='Features Items']");
    private final By productName = By.xpath("//div[@class='product-information']/h2");
    private final By productCategory = By.xpath("//div[@class='product-information']/p[1]");
    private final By productPrice = By.xpath("//div[@class='product-information']/span/span");
    private final By addToCartButtton = By.xpath("//button[contains(., 'Add to cart')]");
    private final By inputQuantity = By.xpath("//input[@name='quantity']");
    private final By popUpAddToCart = By.xpath("//h4[contains(text(),'Added!')]");
    private final By continueShoppingButton = By.xpath("//button[text()='Continue Shopping']");
    private final By viewCart = By.xpath("//u[text()='View Cart']");
    private final By listCartProducts = By.xpath("//tr[contains(@id,'product')]");
    private final By cartPrice = By.xpath(".//td[@class='cart_price']/p");
    private final By cartQuantity = By.xpath(".//td[@class='cart_quantity']/button");
    private final By cartTotal = By.xpath(".//td[@class='cart_total']/p");
    private final By processToCheckoutButton = By.xpath("//a[text()='Proceed To Checkout']");
    private final By addressDetails = By.xpath("//h2[text()='Address Details']");
    private final By placeOrderButton = By.xpath("//a[text()='Place Order']");

    private final By inputNameOnCart = By.xpath("//input[@name='name_on_card']");
    private final By cardNumber = By.xpath("//input[@name='card_number']");
    private final By cvc = By.xpath("//input[@name='cvc']");
    private final By expirationMonth = By.xpath("//input[@name='expiry_month']");
    private final By expirationYear = By.xpath("//input[@name='expiry_year']");
    private final By payAndConfirmOderButton = By.xpath("//button[text()='Pay and Confirm Order']");
    private final By orderSuccessFull = By.xpath("//p[contains(text(), 'Congratulations')]");

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
        this.elementUtils = new ElementUtils(driver);
    }

    // Actions
    public void enterEmail(String email) {
        elementUtils.enterText(this.email, email);
    }

    public void enterPassword(String password) {
        elementUtils.enterText(this.password, password);
    }

    public void clickLogin1() {
        elementUtils.click(this.login1);
    }

    public void clickLogin2() {
        elementUtils.click(this.login2);
    }

    public void clickviewProduct() {
        elementUtils.click(this.viewProduct);
    }

    public boolean isLoginSuccessfully() {
        boolean logoutButtonIsDisplayed = elementUtils.isElementDisplayed(logout);
        boolean deleteAccountButtonIsDisplayed = elementUtils.isElementDisplayed(deleteAccount);
        return logoutButtonIsDisplayed && deleteAccountButtonIsDisplayed;
    }

    public boolean isValidateOnProductHomePage() {
        return (elementUtils.isElementDisplayed(homePageTitle));
    }

    public boolean isValidateProductDescription() {
        return (elementUtils.isElementDisplayed(productName) && elementUtils.isElementDisplayed(productCategory)
                && elementUtils.isElementDisplayed(productPrice));
    }

    public void enterQuantity() {
        elementUtils.enterText(inputQuantity, String.valueOf(4));
    }

    public void clickAddToCartButton() {
        elementUtils.click(this.addToCartButtton);
    }

    public boolean validatePopUpAddToCart() {
        return (elementUtils.isElementDisplayed(popUpAddToCart));
    }

    public void clickContinueShoppingButton() {
        elementUtils.click(this.continueShoppingButton);
    }

    public void clickviewCart() {
        elementUtils.click(this.viewCart);
    }

    public boolean validateOnTheCartPage() {
        List<WebElement> cartProducts = elementUtils.getListOfElements(listCartProducts);
        for (WebElement product : cartProducts) {

            String priceText = elementUtils.getElementText(product, cartPrice);
            String quantityText = elementUtils.getElementText(product, cartQuantity);
            String totalText = elementUtils.getElementText(product, cartTotal);
            System.out.println("RAW -> price=[" + priceText + "] qty=[" + quantityText + "] total=[" + totalText + "]");
            double price = elementUtils.parsePrice(priceText);
            double quantity = Integer.parseInt(quantityText);
            double total = elementUtils.parsePrice(totalText);
            System.out.println("PARSED -> price=" + price + " qty=" + quantity + " total=" + total);
            if (total != price * quantity) {
                return false;
            }
        }
        return true;
    }

    public void ClickProcessToCheckOut() {
        elementUtils.click(processToCheckoutButton);
    }

    public boolean validateOnTheAddressPage() {
        return elementUtils.isElementDisplayed(addressDetails);
    }

    public void ClickPlaceOrderButton() {
        elementUtils.click(placeOrderButton);
    }

    public void enterThePaymentDetails() {
        elementUtils.enterText(inputNameOnCart, "Gia Phuc");
        elementUtils.enterText(cardNumber, "4111 1111 1111 1111");
        elementUtils.enterText(cvc, "123");
        elementUtils.enterText(expirationMonth, "12");
        elementUtils.enterText(expirationYear, "2026");
    }

    public void clickPayAndConfirmOderButton() {
        elementUtils.click(payAndConfirmOderButton);
    }

    public boolean validateOrderSuccesfully() {
        return elementUtils.isElementDisplayed(orderSuccessFull);
    }

}
