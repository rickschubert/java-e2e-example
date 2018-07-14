package amazone2e;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import page_objects.Basket;
import page_objects.Category;
import page_objects.Navigation;
import page_objects.Product;

public class NewUserJourneys {
    private WebDriver browser;
    private String productToPurchase;

    @Before
    public void setup(Scenario scenario) {
        this.browser = SeleniumSetup.setup();
    }

    @Given("^I am on the amazon \"([^\"]*)\" \"([^\"]*)\" page$")
    public void visitPage(String region, String page) {
        this.browser.get(Utils.baseUrl(region, page));
        assertTrue(this.browser.getTitle().contains("Amazon"));
    }

    @Given("^I search for \"([^\"]*)\"$")
    public void navbar_search(String product) {
        new Navigation(this.browser).search(product);
    }

    @Given("^I get redirected to the category \"([^\"]*)\"$")
    public void expect_transition_to_category(String category) {
        new Category(this.browser).verifyCategory(category);
    }

    @Given("^I select product number \"([^\"]*)\" from carousel number \"([^\"]*)\"$")
    public void select_product_from_carousel(int productNr, int carouselNr) {
        new Category(this.browser).selectCarouselByNumber(carouselNr, productNr);
    }

    @When("^I add the product to the basket$")
    public void add_to_basket() {
        Product productPage = new Product(this.browser);
        this.productToPurchase = productPage.getProductTitle();
        productPage.add_to_basket();
    }

    @When("^I open the basket$")
    public void openBasket() {
        new Navigation(this.browser).openBasket();
    }

    @Then("^I see the product in my basket$")
    public void verifyBasket() {
        Basket basket = new Basket(this.browser);
        List<String> items = basket.getProducts();
        Assertions.assertThat(items).contains(this.productToPurchase);
    }

    @After
    public void teardown() {
        SeleniumSetup.teardown(this.browser);
    }
}
