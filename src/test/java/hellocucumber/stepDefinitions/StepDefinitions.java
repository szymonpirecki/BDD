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

    //hooks

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


    //step definitions

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver.get(url);
    }

    @Given("I enter credentials for locked out user")
    public void i_enter_credentials_for_locked_out_user() {
        new LoginPage(driver)
                .provideCredentials(login_locked_out_user, password);
    }

    @Given("I enter invalid credentials")
    public void i_enter_invalid_credentials() {
        new LoginPage(driver)
                .provideCredentials(getRandomString(), getRandomString());
    }

    @Given("I enter only username")
    public void i_enter_only_username() {
        new LoginPage(driver)
                .provideCredentials(login_standard_user, "");
    }

    @Given("I enter only password")
    public void i_enter_only_password() {
        new LoginPage(driver)
                .provideCredentials("", password);
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

    @When("^I select sorting option by (.+) in (.+) order$")
    public void i_select_specific_sorting_option(String sortBy, String sortOrder) {
        new ProductListPage(driver)
                .sortProducts(SortBy.valueOf(sortBy), SortOrder.valueOf(sortOrder));
    }

    @Then("I am still on the login page")
    public void i_am_still_on_the_login_page() {
        Assertions.assertThat(driver.getCurrentUrl())
                .isEqualTo(url);
    }

    @Then("Error message {string} is displayed")
    public void error_msg_is_displayed(String errorMsg) {
        Assertions.assertThat(new LoginPage(driver).getErrorMsg())
                .isEqualTo(errorMsg);
    }

    @Then("^Products are displayed in (.+) order by (.+)$")
    public void products_are_displayed_in_specific_order(String sortOrder, String sortBy) {
        var products = new ProductListPage(driver)
                .getProducts();
        Assertions.assertThat(products)
                .isSortedAccordingTo(getProductComparator(SortBy.valueOf(sortBy), SortOrder.valueOf(sortOrder)));
    }

    private Comparator<? super ProductMiniatureComponent> getProductComparator(SortBy sortBy, SortOrder sortOrder) {
        Comparator<ProductMiniatureComponent> comparator;
        if (sortBy == SortBy.NAME) {
            comparator = Comparator.comparing(ProductMiniatureComponent::getName);
        } else {
            comparator = Comparator.comparing(ProductMiniatureComponent::getPrice);
        }
        return (sortOrder == SortOrder.DESC) ? comparator.reversed() : comparator;
    }


}
