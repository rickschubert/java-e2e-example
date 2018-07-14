package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private WebDriver browser;
    private String basketSummary = "#activeCartViewForm";

    public Basket(WebDriver browser) {
        this.browser = browser;
        this.waitForFullyLoaded();
    }

    public void waitForFullyLoaded() {
        WebDriverWait wait = new WebDriverWait(this.browser, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(basketSummary)));
    }

    public List<String> getProducts() {
        List<String> itemNames = new ArrayList<>();
        this.browser
            .findElement(By.cssSelector(basketSummary))
            .findElements(By.cssSelector(".sc-product-title"))
            .forEach(item -> itemNames.add(0, item.getText()));
        return itemNames;
    }
}
