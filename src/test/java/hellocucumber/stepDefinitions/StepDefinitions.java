package hellocucumber.stepDefinitions;

import hellocucumber.base.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.login.LoginPage;
import pages.product.ProductListPage;
import pages.product.ProductMiniatureComponent;
import pages.product.sorting.SortBy;
import pages.product.sorting.SortOrder;

import java.util.Comparator;

public class StepDefinitions extends TestBase {

    @Before
    public void setUpDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
    }

    @After
    public void tearDownDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver.get(url);
    }

    @Given("I enter credentials for locked out user")
    public void i_entered_credentials_for_locked_out_user() {
        new LoginPage(driver)
                .provideCredentials(login_locked_out_user, password);
    }

    @Given("I am logged in as a standard user")
    public void i_am_logged_in_as_standard_user() {
        driver.get(url);
        new LoginPage(driver)
                .provideCredentials(login_standard_user, password)
                .clickLogInButton();
    }

    @When("I click the 'LOGIN' button")
    public void i_click_the_login_button() {
        new LoginPage(driver)
                .clickLogInButton();
    }

    @Then("I am still on the login page")
    public void i_am_still_on_the_login_page() {
        Assertions.assertThat(driver.getCurrentUrl())
                .isEqualTo(url);
    }

    @Then("Error message is displayed")
    public void error_msg_is_displayed() {
        Assertions.assertThat(new LoginPage(driver).getErrorMsg())
                .isEqualTo(warning_msg_locked_out_user);
    }

    @When("I select sorting option 'Price low to high'")
    public void i_select_sorting_option_price_low_to_high() {
        new ProductListPage(driver)
                .sortProducts(SortBy.PRICE, SortOrder.ASC);
    }

    @Then("The products are displayed in ascending order by price")
    public void theProductsAreDisplayedInAscendingOrderByPrice() {
        var products = new ProductListPage(driver)
                .getProducts();
        Assertions.assertThat(products)
                .isSortedAccordingTo(Comparator.comparing(ProductMiniatureComponent::getPrice));
    }
}
