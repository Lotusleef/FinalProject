package web.stepdef;

import io.cucumber.java.en.*;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import web.pages.LoginPage;
import web.utils.Hooks;

public class LoginStepDef {
    private WebDriver driver;
    private LoginPage login;

    @Given("I open SauceDemo login page @web")
    public void i_open_login_page() {
        driver = Hooks.getDriver();
        login = new LoginPage(driver);
        login.open();
    }

    @When("I login with username {string} and password {string} @web")
    public void i_login_with_credentials(String user, String pass) {
        login.login(user, pass);
    }

    @Then("I should be on inventory page @web")
    public void i_should_be_on_inventory_page() {
        Assertions.assertThat(driver.getCurrentUrl()).contains("inventory.html");
    }

    @Then("I should see error message contains {string} @web")
    public void i_should_see_error_message_contains(String text) {
        Assertions.assertThat(login.getErrorText()).containsIgnoringCase(text);
    }
}
