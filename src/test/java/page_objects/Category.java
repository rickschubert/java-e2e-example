package page_objects;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Category {

    private WebDriver browser;
    private String categoryHeaderClassName = "bxw-pageheader__title";
    private String carouselCss = ".acswidget-carousel";
    private String carouselProductTitleCss = ".acs_product-title a";

    public Category(WebDriver browser) {
        this.browser = browser;
        this.waitForFullyLoaded();
    }

    public void waitForFullyLoaded() {
        WebDriverWait wait = new WebDriverWait(this.browser, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(categoryHeaderClassName)));
    }

    public void verifyCategory(String category) {
        Assertions.assertThat(this.browser.getTitle()).contains(" | " + category);
    }

    public void selectCarouselByNumber(int productNr, int carouselNr){
        carouselNr -= carouselNr;
        List<WebElement> carousels = this.browser.findElements(By.cssSelector(carouselCss));
        WebElement carousel = carousels.get(carouselNr);
        List<WebElement> products = carousel.findElements(By.cssSelector(carouselProductTitleCss));
        WebElement product = products.get(productNr);
        product.click();
        new Product(this.browser);
    }
}
