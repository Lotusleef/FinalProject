package web.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Inventory
    private final By inventoryContainer = By.id("inventory_container");
    private final By backpackAdd = By.id("add-to-cart-sauce-labs-backpack");
    private final By bikeLightAdd = By.id("add-to-cart-sauce-labs-bike-light");
    private final By cartLink = By.cssSelector(".shopping_cart_link");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");

    // Cart
    private final By checkoutBtn = By.id("checkout");

    // Checkout Step One
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueBtn = By.id("continue");

    // Checkout Step Two
    private final By finishBtn = By.id("finish");

    // Complete
    private final By checkoutComplete = By.cssSelector(".complete-header");
    private final By backHomeBtn = By.id("back-to-products");

    // Helpers
    private WebElement waitVisible(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private void waitUrlContains(String part) {
        wait.until(ExpectedConditions.urlContains(part));
    }

    private void scrollIntoView(WebElement el) {
        try {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        } catch (JavascriptException ignore) {}
    }

    private void jsClick(By by) {
        WebElement el = waitVisible(by);
        scrollIntoView(el);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    private void waitCartBadgeAtLeast(int expected) {
        wait.until(d -> {
            var els = d.findElements(cartBadge);
            if (els.isEmpty()) return false;
            try {
                return Integer.parseInt(els.get(0).getText().trim()) >= expected;
            } catch (Exception e) {
                return false;
            }
        });
    }

    private void waitUrlOrElement(String urlPart, By marker) {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains(urlPart),
                ExpectedConditions.visibilityOfElementLocated(marker)
        ));
    }

    private void robustClick(By by) {
        WebElement el = waitVisible(by);
        scrollIntoView(el);
        try {
            el.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el); // fallback JS
        }
    }



    public boolean onInventoryPage() {
        return !driver.findElements(inventoryContainer).isEmpty();
    }

    public void waitInventory() {
        waitVisible(inventoryContainer);
        waitUrlContains("inventory.html");
    }

    // Flow actions
    public void addTwoItems() {
        waitInventory();
        jsClick(backpackAdd);
        jsClick(bikeLightAdd);

        waitCartBadgeAtLeast(2);
    }

    public void openCart() {
        jsClick(cartLink);
        waitUrlContains("cart.html");
        waitVisible(checkoutBtn);
    }

    public void startCheckout() {
        jsClick(checkoutBtn);
        waitUrlContains("checkout-step-one.html");
        waitVisible(firstName);
    }

    public void fillCheckout(String f, String l, String zip) {
        waitVisible(firstName).sendKeys(f);
        driver.findElement(lastName).sendKeys(l);
        driver.findElement(postalCode).sendKeys(zip);
        jsClick(continueBtn);
        waitUrlContains("checkout-step-two.html");
        waitVisible(finishBtn);
    }

    public void finishCheckout() {
        jsClick(finishBtn);
        waitUrlContains("checkout-complete.html");
        waitVisible(checkoutComplete);
    }

    public boolean isCheckoutComplete() {
        return !driver.findElements(checkoutComplete).isEmpty();
    }


    public void backHome() {

        waitVisible(backHomeBtn);


        robustClick(backHomeBtn);
        try {
            waitUrlOrElement("inventory", inventoryContainer);
        } catch (org.openqa.selenium.TimeoutException first) {

            robustClick(backHomeBtn);
            waitUrlOrElement("inventory", inventoryContainer);
        }

        waitInventory();
    }

}
