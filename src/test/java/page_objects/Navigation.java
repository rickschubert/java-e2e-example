package page_objects;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import amazone2e.Utils;

public class Navigation {

    private WebDriver browser;
    private static String searchBarInput = Utils.isMobile() ? "#nav-search-keywords" : "#twotabsearchtextbox";
    private static String searchBarSubmit = "#navbar [type='submit']";
    private static String basket = "#nav-cart";
    public String basketCount = "#nav-cart-count";

    public Navigation(WebDriver browser) {
        this.browser = browser;
    }

    public void search(String product) {
        WebElement searchbar = this.browser.findElement(By.cssSelector(searchBarInput));
        searchbar.sendKeys(product);
        assertEquals(searchbar.getAttribute("value"), product);
        this.browser.findElement(By.cssSelector(searchBarSubmit)).click();
    }

    public void openBasket() {
        this.browser.findElement(By.cssSelector(basket)).click();
    }

    public int getBasketCount() {
        String count = this.browser.findElement(By.cssSelector(basketCount)).getText();
        return Integer.parseInt(count);
    }
}
