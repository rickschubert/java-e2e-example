package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Product {
    private WebDriver browser;
    private String productTitleCss = "#productTitle";
    private String addToBasketCss = "#add-to-cart-button";
    private String optionalPromptNoThanksButtonCss = "#attachSiNoCoverage-announce";
    private String optionalPromptCloseButtonCss = "#attach-close_sideSheet-link";

    public Product(WebDriver browser) {
        this.browser = browser;
        this.waitForFullyLoaded();
    }

    public void waitForFullyLoaded() {
        WebDriverWait wait = new WebDriverWait(this.browser, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(productTitleCss)));
    }

    public void add_to_basket() {
        Navigation navbar = new Navigation(this.browser);
        int basketCountPrev = navbar.getBasketCount();
        this.browser.findElement(By.cssSelector(addToBasketCss)).click();
        this.dismiss_optional_prompt();
        WebDriverWait wait = new WebDriverWait(this.browser, 10);
        wait.until(ExpectedConditions.textToBe(By.cssSelector(navbar.basketCount), Integer.toString(basketCountPrev + 1)));
    }

    public void dismiss_optional_prompt() {
        try {
            WebDriverWait wait1 = new WebDriverWait(this.browser, 10);
            wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector(optionalPromptNoThanksButtonCss))).click();
            wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(optionalPromptNoThanksButtonCss)));
            wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector(optionalPromptCloseButtonCss))).click();
            wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(optionalPromptCloseButtonCss)));
        } catch (WebDriverException error) {
            System.out.println(error);
            System.out.println("Optional insurance prompt was not visible. Proceeding with the tests.");
        }
    }

    public String getProductTitle() {
        return this.browser.findElement(By.cssSelector(productTitleCss)).getText();
    }
}
