package web.stepdef;

import io.cucumber.java.en.*;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import web.pages.CartPage;
import web.pages.LoginPage;
import web.utils.Hooks;

public class CartStepDef {
    private WebDriver driver;
    private LoginPage login;
    private CartPage cart;

    @Given("I am logged in as standard_user @web")
    public void i_am_logged_in_as_standard_user() {
        driver = Hooks.getDriver();
        login = new LoginPage(driver);
        cart = new CartPage(driver);

        login.open();
        login.login("standard_user", "secret_sauce");
        Assertions.assertThat(cart.onInventoryPage()).isTrue();
    }

    @When("I add products and proceed to checkout @web")
    public void i_add_products_and_checkout() {
        cart.addTwoItems();
        cart.openCart();
        cart.startCheckout();
    }

    @And("I complete checkout info {string} {string} {string} @web")
    public void i_complete_checkout_info(String fn, String ln, String zip) {
        cart.fillCheckout(fn, ln, zip);
        cart.finishCheckout();
    }

    @Then("Order should be completed and I can return to homepage @web")
    public void order_should_be_completed_and_return_homepage() {
        Assertions.assertThat(cart.isCheckoutComplete()).isTrue();
        cart.backHome();
        Assertions.assertThat(cart.onInventoryPage()).isTrue();
    }
}
